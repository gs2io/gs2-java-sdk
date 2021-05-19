package io.gs2.core.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.exception.Gs2Exception;
import io.gs2.core.exception.NoInternetConnectionException;
import io.gs2.core.exception.UnknownException;
import io.gs2.core.model.BasicGs2Credential;
import io.gs2.core.model.Region;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Gs2RestSession extends Gs2Session {

    public static String EndpointHost = "https://{service}.{region}.gen2.gs2io.com";

    private boolean m_IsOpenCancelled;

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class LoginResult {
        /** プロジェクトトークン */
        public String access_token;
        /** Bearer */
        public String token_type;
        /** 有効期間(秒) */
        public Integer expires_in;
    }

    private class Gs2LoginTask
    {
        private Gs2RestSession gs2RestSession;

        Gs2LoginTask(Gs2RestSession gs2RestSession) {
            this.gs2RestSession = gs2RestSession;
        }

        void execute() throws IOException {
            JSONObject json = new JSONObject();
            json.put("client_id", gs2RestSession.getGs2Credential().getClientId());
            json.put("client_secret", gs2RestSession.getGs2Credential().getClientSecret());
            byte[] body = json.toString().getBytes();

            HttpTaskBuilder
                    .create()
                    .setMethod(HttpTask.Method.POST)
                    .setUrl(
                            EndpointHost
                                    .replace("{service}", "identifier")
                                    .replace("{region}", gs2RestSession.getRegion().getName()) + "/projectToken/login"
                    )
                    .setHeader("Content-Type", "application/json")
                    .setHttpResponseHandler((response) -> {
                        Gs2Exception error = response.getGs2Exception();
                        String accessToken = null;

                        if (error == null) {
                            try {
                                ObjectMapper mapper = new ObjectMapper();
                                accessToken = mapper.readValue(response.getMessage(), LoginResult.class).access_token;
                            } catch (Exception e) {
                                error = new UnknownException("JSON parsing error: \n" + response.getMessage());
                            }
                        }

                        this.gs2RestSession.openCallback(accessToken, error);
                    })
                    .setBody(body)
                    .build()
                    .send();
        }
    }

    public Gs2RestSession(BasicGs2Credential basicGs2Credential) {
        super(basicGs2Credential);
    }

    public Gs2RestSession(BasicGs2Credential basicGs2Credential, Region region) {
        super(basicGs2Credential, region);
    }

    public Gs2RestSession(BasicGs2Credential basicGs2Credential, String region) {
        super(basicGs2Credential, region);
    }

    public void execute(Gs2RestSessionTask gs2RestSessionTask) throws IOException {
        super.execute(gs2RestSessionTask);
    }

    @Override
    void openImpl() {
        m_IsOpenCancelled = false;

        try {
            (new Gs2LoginTask(this)).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void cancelOpenImpl()
    {
        m_IsOpenCancelled = true;
    }

    @Override
    boolean closeImpl() {
        Gs2Exception gs2ClientException = new NoInternetConnectionException("");  // TODO
        closeCallback(gs2ClientException, true);

        return true;
    }
}
