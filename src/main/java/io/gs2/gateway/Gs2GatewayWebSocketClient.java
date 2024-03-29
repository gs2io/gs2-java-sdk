
/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.gs2.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.AbstractGs2Client;
import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.net.Gs2WebSocketSession;
import io.gs2.core.net.Gs2WebSocketSessionTask;
import io.gs2.core.net.HttpTask;
import io.gs2.core.util.EncodingUtil;
import io.gs2.gateway.request.*;
import io.gs2.gateway.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Gs2GatewayWebSocketClient extends AbstractGs2Client<Gs2GatewayWebSocketClient> {

	public Gs2GatewayWebSocketClient(Gs2WebSocketSession gs2WebSocketSession) {
		super(gs2WebSocketSession);
	}

    class DescribeNamespacesTask extends Gs2WebSocketSessionTask<DescribeNamespacesResult> {
        private DescribeNamespacesRequest request;

        public DescribeNamespacesTask(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeNamespacesResult parse(JsonNode data) {
            return DescribeNamespacesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/";

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeNamespacesAsync(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> callback
    ) {
        DescribeNamespacesTask task = new DescribeNamespacesTask(request, callback);
        session.execute(task);
    }

    public DescribeNamespacesResult describeNamespaces(
            DescribeNamespacesRequest request
    ) {
        final AsyncResult<DescribeNamespacesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeNamespacesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateNamespaceTask extends Gs2WebSocketSessionTask<CreateNamespaceResult> {
        private CreateNamespaceRequest request;

        public CreateNamespaceTask(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateNamespaceResult parse(JsonNode data) {
            return CreateNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("firebaseSecret", request.getFirebaseSecret());
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void createNamespaceAsync(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> callback
    ) {
        CreateNamespaceTask task = new CreateNamespaceTask(request, callback);
        session.execute(task);
    }

    public CreateNamespaceResult createNamespace(
            CreateNamespaceRequest request
    ) {
        final AsyncResult<CreateNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        createNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetNamespaceStatusTask extends Gs2WebSocketSessionTask<GetNamespaceStatusResult> {
        private GetNamespaceStatusRequest request;

        public GetNamespaceStatusTask(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetNamespaceStatusResult parse(JsonNode data) {
            return GetNamespaceStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getNamespaceStatusAsync(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> callback
    ) {
        GetNamespaceStatusTask task = new GetNamespaceStatusTask(request, callback);
        session.execute(task);
    }

    public GetNamespaceStatusResult getNamespaceStatus(
            GetNamespaceStatusRequest request
    ) {
        final AsyncResult<GetNamespaceStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getNamespaceStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetNamespaceTask extends Gs2WebSocketSessionTask<GetNamespaceResult> {
        private GetNamespaceRequest request;

        public GetNamespaceTask(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetNamespaceResult parse(JsonNode data) {
            return GetNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getNamespaceAsync(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> callback
    ) {
        GetNamespaceTask task = new GetNamespaceTask(request, callback);
        session.execute(task);
    }

    public GetNamespaceResult getNamespace(
            GetNamespaceRequest request
    ) {
        final AsyncResult<GetNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        getNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateNamespaceTask extends Gs2WebSocketSessionTask<UpdateNamespaceResult> {
        private UpdateNamespaceRequest request;

        public UpdateNamespaceTask(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateNamespaceResult parse(JsonNode data) {
            return UpdateNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("firebaseSecret", request.getFirebaseSecret());
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateNamespaceAsync(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> callback
    ) {
        UpdateNamespaceTask task = new UpdateNamespaceTask(request, callback);
        session.execute(task);
    }

    public UpdateNamespaceResult updateNamespace(
            UpdateNamespaceRequest request
    ) {
        final AsyncResult<UpdateNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteNamespaceTask extends Gs2WebSocketSessionTask<DeleteNamespaceResult> {
        private DeleteNamespaceRequest request;

        public DeleteNamespaceTask(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteNamespaceResult parse(JsonNode data) {
            return DeleteNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteNamespaceAsync(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> callback
    ) {
        DeleteNamespaceTask task = new DeleteNamespaceTask(request, callback);
        session.execute(task);
    }

    public DeleteNamespaceResult deleteNamespace(
            DeleteNamespaceRequest request
    ) {
        final AsyncResult<DeleteNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeWebSocketSessionsTask extends Gs2WebSocketSessionTask<DescribeWebSocketSessionsResult> {
        private DescribeWebSocketSessionsRequest request;

        public DescribeWebSocketSessionsTask(
            DescribeWebSocketSessionsRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeWebSocketSessionsResult parse(JsonNode data) {
            return DescribeWebSocketSessionsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeWebSocketSessionsAsync(
            DescribeWebSocketSessionsRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsResult>> callback
    ) {
        DescribeWebSocketSessionsTask task = new DescribeWebSocketSessionsTask(request, callback);
        session.execute(task);
    }

    public DescribeWebSocketSessionsResult describeWebSocketSessions(
            DescribeWebSocketSessionsRequest request
    ) {
        final AsyncResult<DescribeWebSocketSessionsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWebSocketSessionsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeWebSocketSessionsByUserIdTask extends Gs2WebSocketSessionTask<DescribeWebSocketSessionsByUserIdResult> {
        private DescribeWebSocketSessionsByUserIdRequest request;

        public DescribeWebSocketSessionsByUserIdTask(
            DescribeWebSocketSessionsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeWebSocketSessionsByUserIdResult parse(JsonNode data) {
            return DescribeWebSocketSessionsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeWebSocketSessionsByUserIdAsync(
            DescribeWebSocketSessionsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsByUserIdResult>> callback
    ) {
        DescribeWebSocketSessionsByUserIdTask task = new DescribeWebSocketSessionsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeWebSocketSessionsByUserIdResult describeWebSocketSessionsByUserId(
            DescribeWebSocketSessionsByUserIdRequest request
    ) {
        final AsyncResult<DescribeWebSocketSessionsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWebSocketSessionsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetUserIdTask extends Gs2WebSocketSessionTask<SetUserIdResult> {
        private SetUserIdRequest request;

        public SetUserIdTask(
            SetUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetUserIdResult parse(JsonNode data) {
            return SetUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/me/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("allowConcurrentAccess", request.getAllowConcurrentAccess());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void setUserIdAsync(
            SetUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdResult>> callback
    ) {
        SetUserIdTask task = new SetUserIdTask(request, callback);
        session.execute(task);
    }

    public SetUserIdResult setUserId(
            SetUserIdRequest request
    ) {
        final AsyncResult<SetUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetUserIdByUserIdTask extends Gs2WebSocketSessionTask<SetUserIdByUserIdResult> {
        private SetUserIdByUserIdRequest request;

        public SetUserIdByUserIdTask(
            SetUserIdByUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetUserIdByUserIdResult parse(JsonNode data) {
            return SetUserIdByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/{userId}/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("allowConcurrentAccess", request.getAllowConcurrentAccess());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void setUserIdByUserIdAsync(
            SetUserIdByUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdByUserIdResult>> callback
    ) {
        SetUserIdByUserIdTask task = new SetUserIdByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetUserIdByUserIdResult setUserIdByUserId(
            SetUserIdByUserIdRequest request
    ) {
        final AsyncResult<SetUserIdByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setUserIdByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendNotificationTask extends Gs2WebSocketSessionTask<SendNotificationResult> {
        private SendNotificationRequest request;

        public SendNotificationTask(
            SendNotificationRequest request,
            AsyncAction<AsyncResult<SendNotificationResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendNotificationResult parse(JsonNode data) {
            return SendNotificationResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/{userId}/notification";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("subject", request.getSubject());
                    put("payload", request.getPayload());
                    put("enableTransferMobileNotification", request.getEnableTransferMobileNotification());
                    put("sound", request.getSound());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void sendNotificationAsync(
            SendNotificationRequest request,
            AsyncAction<AsyncResult<SendNotificationResult>> callback
    ) {
        SendNotificationTask task = new SendNotificationTask(request, callback);
        session.execute(task);
    }

    public SendNotificationResult sendNotification(
            SendNotificationRequest request
    ) {
        final AsyncResult<SendNotificationResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendNotificationAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFirebaseTokenTask extends Gs2WebSocketSessionTask<SetFirebaseTokenResult> {
        private SetFirebaseTokenRequest request;

        public SetFirebaseTokenTask(
            SetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetFirebaseTokenResult parse(JsonNode data) {
            return SetFirebaseTokenResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("token", request.getToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void setFirebaseTokenAsync(
            SetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenResult>> callback
    ) {
        SetFirebaseTokenTask task = new SetFirebaseTokenTask(request, callback);
        session.execute(task);
    }

    public SetFirebaseTokenResult setFirebaseToken(
            SetFirebaseTokenRequest request
    ) {
        final AsyncResult<SetFirebaseTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFirebaseTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFirebaseTokenByUserIdTask extends Gs2WebSocketSessionTask<SetFirebaseTokenByUserIdResult> {
        private SetFirebaseTokenByUserIdRequest request;

        public SetFirebaseTokenByUserIdTask(
            SetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetFirebaseTokenByUserIdResult parse(JsonNode data) {
            return SetFirebaseTokenByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("token", request.getToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void setFirebaseTokenByUserIdAsync(
            SetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenByUserIdResult>> callback
    ) {
        SetFirebaseTokenByUserIdTask task = new SetFirebaseTokenByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetFirebaseTokenByUserIdResult setFirebaseTokenByUserId(
            SetFirebaseTokenByUserIdRequest request
    ) {
        final AsyncResult<SetFirebaseTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFirebaseTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFirebaseTokenTask extends Gs2WebSocketSessionTask<GetFirebaseTokenResult> {
        private GetFirebaseTokenRequest request;

        public GetFirebaseTokenTask(
            GetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFirebaseTokenResult parse(JsonNode data) {
            return GetFirebaseTokenResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getFirebaseTokenAsync(
            GetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenResult>> callback
    ) {
        GetFirebaseTokenTask task = new GetFirebaseTokenTask(request, callback);
        session.execute(task);
    }

    public GetFirebaseTokenResult getFirebaseToken(
            GetFirebaseTokenRequest request
    ) {
        final AsyncResult<GetFirebaseTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFirebaseTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFirebaseTokenByUserIdTask extends Gs2WebSocketSessionTask<GetFirebaseTokenByUserIdResult> {
        private GetFirebaseTokenByUserIdRequest request;

        public GetFirebaseTokenByUserIdTask(
            GetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFirebaseTokenByUserIdResult parse(JsonNode data) {
            return GetFirebaseTokenByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getFirebaseTokenByUserIdAsync(
            GetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenByUserIdResult>> callback
    ) {
        GetFirebaseTokenByUserIdTask task = new GetFirebaseTokenByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetFirebaseTokenByUserIdResult getFirebaseTokenByUserId(
            GetFirebaseTokenByUserIdRequest request
    ) {
        final AsyncResult<GetFirebaseTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFirebaseTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFirebaseTokenTask extends Gs2WebSocketSessionTask<DeleteFirebaseTokenResult> {
        private DeleteFirebaseTokenRequest request;

        public DeleteFirebaseTokenTask(
            DeleteFirebaseTokenRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFirebaseTokenResult parse(JsonNode data) {
            return DeleteFirebaseTokenResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteFirebaseTokenAsync(
            DeleteFirebaseTokenRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenResult>> callback
    ) {
        DeleteFirebaseTokenTask task = new DeleteFirebaseTokenTask(request, callback);
        session.execute(task);
    }

    public DeleteFirebaseTokenResult deleteFirebaseToken(
            DeleteFirebaseTokenRequest request
    ) {
        final AsyncResult<DeleteFirebaseTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFirebaseTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFirebaseTokenByUserIdTask extends Gs2WebSocketSessionTask<DeleteFirebaseTokenByUserIdResult> {
        private DeleteFirebaseTokenByUserIdRequest request;

        public DeleteFirebaseTokenByUserIdTask(
            DeleteFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFirebaseTokenByUserIdResult parse(JsonNode data) {
            return DeleteFirebaseTokenByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteFirebaseTokenByUserIdAsync(
            DeleteFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenByUserIdResult>> callback
    ) {
        DeleteFirebaseTokenByUserIdTask task = new DeleteFirebaseTokenByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteFirebaseTokenByUserIdResult deleteFirebaseTokenByUserId(
            DeleteFirebaseTokenByUserIdRequest request
    ) {
        final AsyncResult<DeleteFirebaseTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFirebaseTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendMobileNotificationByUserIdTask extends Gs2WebSocketSessionTask<SendMobileNotificationByUserIdResult> {
        private SendMobileNotificationByUserIdRequest request;

        public SendMobileNotificationByUserIdTask(
            SendMobileNotificationByUserIdRequest request,
            AsyncAction<AsyncResult<SendMobileNotificationByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2WebSocketSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendMobileNotificationByUserIdResult parse(JsonNode data) {
            return SendMobileNotificationByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2WebSocketSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token/notification";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("subject", request.getSubject());
                    put("payload", request.getPayload());
                    put("sound", request.getSound());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void sendMobileNotificationByUserIdAsync(
            SendMobileNotificationByUserIdRequest request,
            AsyncAction<AsyncResult<SendMobileNotificationByUserIdResult>> callback
    ) {
        SendMobileNotificationByUserIdTask task = new SendMobileNotificationByUserIdTask(request, callback);
        session.execute(task);
    }

    public SendMobileNotificationByUserIdResult sendMobileNotificationByUserId(
            SendMobileNotificationByUserIdRequest request
    ) {
        final AsyncResult<SendMobileNotificationByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendMobileNotificationByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }
}