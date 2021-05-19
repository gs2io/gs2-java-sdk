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

package io.gs2.deploy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.json.JSONArray;

import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.exception.*;
import io.gs2.core.net.*;
import io.gs2.core.util.EncodingUtil;

import io.gs2.core.AbstractGs2Client;
import io.gs2.deploy.request.*;
import io.gs2.deploy.result.*;
import io.gs2.deploy.model.*;

/**
 * GS2 Deploy API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2DeployRestClient extends AbstractGs2Client<Gs2DeployRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2DeployRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeStacksTask extends Gs2RestSessionTask<DescribeStacksResult> {
        private DescribeStacksRequest request;

        public DescribeStacksTask(
            DescribeStacksRequest request,
            AsyncAction<AsyncResult<DescribeStacksResult>> userCallback,
            Class<DescribeStacksResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack";

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

    /**
     * スタックの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStacksAsync(
            DescribeStacksRequest request,
            AsyncAction<AsyncResult<DescribeStacksResult>> callback
    ) {
        DescribeStacksTask task = new DescribeStacksTask(request, callback, DescribeStacksResult.class);
        session.execute(task);
    }

    /**
     * スタックの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStacksResult describeStacks(
            DescribeStacksRequest request
    ) {
        final AsyncResult<DescribeStacksResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStacksAsync(
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

    class CreateStackTask extends Gs2RestSessionTask<CreateStackResult> {
        private CreateStackRequest request;

        public CreateStackTask(
            CreateStackRequest request,
            AsyncAction<AsyncResult<CreateStackResult>> userCallback,
            Class<CreateStackResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getTemplate() != null) {
                json.put("template", this.request.getTemplate());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタックを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createStackAsync(
            CreateStackRequest request,
            AsyncAction<AsyncResult<CreateStackResult>> callback
    ) {
        CreateStackTask task = new CreateStackTask(request, callback, CreateStackResult.class);
        session.execute(task);
    }

    /**
     * スタックを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateStackResult createStack(
            CreateStackRequest request
    ) {
        final AsyncResult<CreateStackResult>[] resultAsyncResult = new AsyncResult[]{null};
        createStackAsync(
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

    class CreateStackFromGitHubTask extends Gs2RestSessionTask<CreateStackFromGitHubResult> {
        private CreateStackFromGitHubRequest request;

        public CreateStackFromGitHubTask(
            CreateStackFromGitHubRequest request,
            AsyncAction<AsyncResult<CreateStackFromGitHubResult>> userCallback,
            Class<CreateStackFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/from_git_hub";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getCheckoutSetting() != null) {
                try {
                    json.put("checkoutSetting", new JSONObject(mapper.writeValueAsString(this.request.getCheckoutSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタックを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createStackFromGitHubAsync(
            CreateStackFromGitHubRequest request,
            AsyncAction<AsyncResult<CreateStackFromGitHubResult>> callback
    ) {
        CreateStackFromGitHubTask task = new CreateStackFromGitHubTask(request, callback, CreateStackFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * スタックを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateStackFromGitHubResult createStackFromGitHub(
            CreateStackFromGitHubRequest request
    ) {
        final AsyncResult<CreateStackFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        createStackFromGitHubAsync(
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

    class ValidateTask extends Gs2RestSessionTask<ValidateResult> {
        private ValidateRequest request;

        public ValidateTask(
            ValidateRequest request,
            AsyncAction<AsyncResult<ValidateResult>> userCallback,
            Class<ValidateResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/validate";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTemplate() != null) {
                json.put("template", this.request.getTemplate());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * テンプレートを検証<br>
     *   <br>
     *   このAPIの検証内容は簡易検証を行うに過ぎず、<br>
     *   このAPIで検証をパスしたとしても、実行したらエラーが発生する場合もあります<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void validateAsync(
            ValidateRequest request,
            AsyncAction<AsyncResult<ValidateResult>> callback
    ) {
        ValidateTask task = new ValidateTask(request, callback, ValidateResult.class);
        session.execute(task);
    }

    /**
     * テンプレートを検証<br>
     *   <br>
     *   このAPIの検証内容は簡易検証を行うに過ぎず、<br>
     *   このAPIで検証をパスしたとしても、実行したらエラーが発生する場合もあります<br>
     *
     * @param request リクエストパラメータ
     */
    public ValidateResult validate(
            ValidateRequest request
    ) {
        final AsyncResult<ValidateResult>[] resultAsyncResult = new AsyncResult[]{null};
        validateAsync(
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

    class GetStackStatusTask extends Gs2RestSessionTask<GetStackStatusResult> {
        private GetStackStatusRequest request;

        public GetStackStatusTask(
            GetStackStatusRequest request,
            AsyncAction<AsyncResult<GetStackStatusResult>> userCallback,
            Class<GetStackStatusResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/status";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * スタックを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStackStatusAsync(
            GetStackStatusRequest request,
            AsyncAction<AsyncResult<GetStackStatusResult>> callback
    ) {
        GetStackStatusTask task = new GetStackStatusTask(request, callback, GetStackStatusResult.class);
        session.execute(task);
    }

    /**
     * スタックを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStackStatusResult getStackStatus(
            GetStackStatusRequest request
    ) {
        final AsyncResult<GetStackStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStackStatusAsync(
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

    class GetStackTask extends Gs2RestSessionTask<GetStackResult> {
        private GetStackRequest request;

        public GetStackTask(
            GetStackRequest request,
            AsyncAction<AsyncResult<GetStackResult>> userCallback,
            Class<GetStackResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * スタックを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStackAsync(
            GetStackRequest request,
            AsyncAction<AsyncResult<GetStackResult>> callback
    ) {
        GetStackTask task = new GetStackTask(request, callback, GetStackResult.class);
        session.execute(task);
    }

    /**
     * スタックを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStackResult getStack(
            GetStackRequest request
    ) {
        final AsyncResult<GetStackResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStackAsync(
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

    class UpdateStackTask extends Gs2RestSessionTask<UpdateStackResult> {
        private UpdateStackRequest request;

        public UpdateStackTask(
            UpdateStackRequest request,
            AsyncAction<AsyncResult<UpdateStackResult>> userCallback,
            Class<UpdateStackResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getTemplate() != null) {
                json.put("template", this.request.getTemplate());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタックを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateStackAsync(
            UpdateStackRequest request,
            AsyncAction<AsyncResult<UpdateStackResult>> callback
    ) {
        UpdateStackTask task = new UpdateStackTask(request, callback, UpdateStackResult.class);
        session.execute(task);
    }

    /**
     * スタックを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateStackResult updateStack(
            UpdateStackRequest request
    ) {
        final AsyncResult<UpdateStackResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStackAsync(
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

    class UpdateStackFromGitHubTask extends Gs2RestSessionTask<UpdateStackFromGitHubResult> {
        private UpdateStackFromGitHubRequest request;

        public UpdateStackFromGitHubTask(
            UpdateStackFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateStackFromGitHubResult>> userCallback,
            Class<UpdateStackFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/from_git_hub";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getCheckoutSetting() != null) {
                try {
                    json.put("checkoutSetting", new JSONObject(mapper.writeValueAsString(this.request.getCheckoutSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタックを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateStackFromGitHubAsync(
            UpdateStackFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateStackFromGitHubResult>> callback
    ) {
        UpdateStackFromGitHubTask task = new UpdateStackFromGitHubTask(request, callback, UpdateStackFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * スタックを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateStackFromGitHubResult updateStackFromGitHub(
            UpdateStackFromGitHubRequest request
    ) {
        final AsyncResult<UpdateStackFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStackFromGitHubAsync(
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

    class DeleteStackTask extends Gs2RestSessionTask<DeleteStackResult> {
        private DeleteStackRequest request;

        public DeleteStackTask(
            DeleteStackRequest request,
            AsyncAction<AsyncResult<DeleteStackResult>> userCallback,
            Class<DeleteStackResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * スタックを削除<br>
     *   <br>
     *   スタックによって作成されたリソースの削除を行い、成功すればエンティティを削除します。<br>
     *   何らかの理由でリソースの削除に失敗した場合はエンティティが残ります。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteStackAsync(
            DeleteStackRequest request,
            AsyncAction<AsyncResult<DeleteStackResult>> callback
    ) {
        DeleteStackTask task = new DeleteStackTask(request, callback, DeleteStackResult.class);
        session.execute(task);
    }

    /**
     * スタックを削除<br>
     *   <br>
     *   スタックによって作成されたリソースの削除を行い、成功すればエンティティを削除します。<br>
     *   何らかの理由でリソースの削除に失敗した場合はエンティティが残ります。<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteStackResult deleteStack(
            DeleteStackRequest request
    ) {
        final AsyncResult<DeleteStackResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStackAsync(
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

    class ForceDeleteStackTask extends Gs2RestSessionTask<ForceDeleteStackResult> {
        private ForceDeleteStackRequest request;

        public ForceDeleteStackTask(
            ForceDeleteStackRequest request,
            AsyncAction<AsyncResult<ForceDeleteStackResult>> userCallback,
            Class<ForceDeleteStackResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/force";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * スタックを強制的に最終削除<br>
     *   <br>
     *   スタックのエンティティを強制的に削除します。<br>
     *   スタックが作成したリソースが残っていても、それらは削除されません。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void forceDeleteStackAsync(
            ForceDeleteStackRequest request,
            AsyncAction<AsyncResult<ForceDeleteStackResult>> callback
    ) {
        ForceDeleteStackTask task = new ForceDeleteStackTask(request, callback, ForceDeleteStackResult.class);
        session.execute(task);
    }

    /**
     * スタックを強制的に最終削除<br>
     *   <br>
     *   スタックのエンティティを強制的に削除します。<br>
     *   スタックが作成したリソースが残っていても、それらは削除されません。<br>
     *
     * @param request リクエストパラメータ
     */
    public ForceDeleteStackResult forceDeleteStack(
            ForceDeleteStackRequest request
    ) {
        final AsyncResult<ForceDeleteStackResult>[] resultAsyncResult = new AsyncResult[]{null};
        forceDeleteStackAsync(
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

    class DeleteStackResourcesTask extends Gs2RestSessionTask<DeleteStackResourcesResult> {
        private DeleteStackResourcesRequest request;

        public DeleteStackResourcesTask(
            DeleteStackResourcesRequest request,
            AsyncAction<AsyncResult<DeleteStackResourcesResult>> userCallback,
            Class<DeleteStackResourcesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/resources";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * スタックのリソースを削除<br>
     *   <br>
     *   スタックによって作成されたリソースの削除を行います。<br>
     *   空のテンプレートでスタックを更新するのとほぼ同様の挙動ですが、スタックに適用されていたテンプレートが残るため、誤操作時に、残ったテンプレートからリソースを復元することができます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteStackResourcesAsync(
            DeleteStackResourcesRequest request,
            AsyncAction<AsyncResult<DeleteStackResourcesResult>> callback
    ) {
        DeleteStackResourcesTask task = new DeleteStackResourcesTask(request, callback, DeleteStackResourcesResult.class);
        session.execute(task);
    }

    /**
     * スタックのリソースを削除<br>
     *   <br>
     *   スタックによって作成されたリソースの削除を行います。<br>
     *   空のテンプレートでスタックを更新するのとほぼ同様の挙動ですが、スタックに適用されていたテンプレートが残るため、誤操作時に、残ったテンプレートからリソースを復元することができます。<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteStackResourcesResult deleteStackResources(
            DeleteStackResourcesRequest request
    ) {
        final AsyncResult<DeleteStackResourcesResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStackResourcesAsync(
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

    class DeleteStackEntityTask extends Gs2RestSessionTask<DeleteStackEntityResult> {
        private DeleteStackEntityRequest request;

        public DeleteStackEntityTask(
            DeleteStackEntityRequest request,
            AsyncAction<AsyncResult<DeleteStackEntityResult>> userCallback,
            Class<DeleteStackEntityResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/entity";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * スタックを最終削除<br>
     *   <br>
     *   スタックのエンティティを削除します。<br>
     *   リソースの残っているスタックを削除しようとするとエラーになります。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteStackEntityAsync(
            DeleteStackEntityRequest request,
            AsyncAction<AsyncResult<DeleteStackEntityResult>> callback
    ) {
        DeleteStackEntityTask task = new DeleteStackEntityTask(request, callback, DeleteStackEntityResult.class);
        session.execute(task);
    }

    /**
     * スタックを最終削除<br>
     *   <br>
     *   スタックのエンティティを削除します。<br>
     *   リソースの残っているスタックを削除しようとするとエラーになります。<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteStackEntityResult deleteStackEntity(
            DeleteStackEntityRequest request
    ) {
        final AsyncResult<DeleteStackEntityResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStackEntityAsync(
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

    class DescribeResourcesTask extends Gs2RestSessionTask<DescribeResourcesResult> {
        private DescribeResourcesRequest request;

        public DescribeResourcesTask(
            DescribeResourcesRequest request,
            AsyncAction<AsyncResult<DescribeResourcesResult>> userCallback,
            Class<DescribeResourcesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/resource";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * 作成されたのリソースの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeResourcesAsync(
            DescribeResourcesRequest request,
            AsyncAction<AsyncResult<DescribeResourcesResult>> callback
    ) {
        DescribeResourcesTask task = new DescribeResourcesTask(request, callback, DescribeResourcesResult.class);
        session.execute(task);
    }

    /**
     * 作成されたのリソースの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeResourcesResult describeResources(
            DescribeResourcesRequest request
    ) {
        final AsyncResult<DescribeResourcesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeResourcesAsync(
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

    class GetResourceTask extends Gs2RestSessionTask<GetResourceResult> {
        private GetResourceRequest request;

        public GetResourceTask(
            GetResourceRequest request,
            AsyncAction<AsyncResult<GetResourceResult>> userCallback,
            Class<GetResourceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/resource/{resourceName}";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));
            url = url.replace("{resourceName}", this.request.getResourceName() == null|| this.request.getResourceName().length() == 0 ? "null" : String.valueOf(this.request.getResourceName()));

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

    /**
     * 作成されたのリソースを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getResourceAsync(
            GetResourceRequest request,
            AsyncAction<AsyncResult<GetResourceResult>> callback
    ) {
        GetResourceTask task = new GetResourceTask(request, callback, GetResourceResult.class);
        session.execute(task);
    }

    /**
     * 作成されたのリソースを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetResourceResult getResource(
            GetResourceRequest request
    ) {
        final AsyncResult<GetResourceResult>[] resultAsyncResult = new AsyncResult[]{null};
        getResourceAsync(
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

    class DescribeEventsTask extends Gs2RestSessionTask<DescribeEventsResult> {
        private DescribeEventsRequest request;

        public DescribeEventsTask(
            DescribeEventsRequest request,
            AsyncAction<AsyncResult<DescribeEventsResult>> userCallback,
            Class<DescribeEventsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/event";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * 発生したイベントの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEventsAsync(
            DescribeEventsRequest request,
            AsyncAction<AsyncResult<DescribeEventsResult>> callback
    ) {
        DescribeEventsTask task = new DescribeEventsTask(request, callback, DescribeEventsResult.class);
        session.execute(task);
    }

    /**
     * 発生したイベントの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEventsResult describeEvents(
            DescribeEventsRequest request
    ) {
        final AsyncResult<DescribeEventsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEventsAsync(
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

    class GetEventTask extends Gs2RestSessionTask<GetEventResult> {
        private GetEventRequest request;

        public GetEventTask(
            GetEventRequest request,
            AsyncAction<AsyncResult<GetEventResult>> userCallback,
            Class<GetEventResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/event/{eventName}";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

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

    /**
     * 発生したイベントを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEventAsync(
            GetEventRequest request,
            AsyncAction<AsyncResult<GetEventResult>> callback
    ) {
        GetEventTask task = new GetEventTask(request, callback, GetEventResult.class);
        session.execute(task);
    }

    /**
     * 発生したイベントを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEventResult getEvent(
            GetEventRequest request
    ) {
        final AsyncResult<GetEventResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEventAsync(
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

    class DescribeOutputsTask extends Gs2RestSessionTask<DescribeOutputsResult> {
        private DescribeOutputsRequest request;

        public DescribeOutputsTask(
            DescribeOutputsRequest request,
            AsyncAction<AsyncResult<DescribeOutputsResult>> userCallback,
            Class<DescribeOutputsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/output";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    /**
     * アウトプットの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeOutputsAsync(
            DescribeOutputsRequest request,
            AsyncAction<AsyncResult<DescribeOutputsResult>> callback
    ) {
        DescribeOutputsTask task = new DescribeOutputsTask(request, callback, DescribeOutputsResult.class);
        session.execute(task);
    }

    /**
     * アウトプットの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeOutputsResult describeOutputs(
            DescribeOutputsRequest request
    ) {
        final AsyncResult<DescribeOutputsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeOutputsAsync(
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

    class GetOutputTask extends Gs2RestSessionTask<GetOutputResult> {
        private GetOutputRequest request;

        public GetOutputTask(
            GetOutputRequest request,
            AsyncAction<AsyncResult<GetOutputResult>> userCallback,
            Class<GetOutputResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/output/{outputName}";

            url = url.replace("{stackName}", this.request.getStackName() == null|| this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));
            url = url.replace("{outputName}", this.request.getOutputName() == null|| this.request.getOutputName().length() == 0 ? "null" : String.valueOf(this.request.getOutputName()));

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

    /**
     * アウトプットを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getOutputAsync(
            GetOutputRequest request,
            AsyncAction<AsyncResult<GetOutputResult>> callback
    ) {
        GetOutputTask task = new GetOutputTask(request, callback, GetOutputResult.class);
        session.execute(task);
    }

    /**
     * アウトプットを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetOutputResult getOutput(
            GetOutputRequest request
    ) {
        final AsyncResult<GetOutputResult>[] resultAsyncResult = new AsyncResult[]{null};
        getOutputAsync(
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