
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.exception.*;
import io.gs2.core.net.*;
import io.gs2.core.util.EncodingUtil;

import io.gs2.core.AbstractGs2Client;
import io.gs2.deploy.request.*;
import io.gs2.deploy.result.*;
import io.gs2.deploy.model.*;public class Gs2DeployRestClient extends AbstractGs2Client<Gs2DeployRestClient> {

	public Gs2DeployRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeStacksTask extends Gs2RestSessionTask<DescribeStacksResult> {
        private DescribeStacksRequest request;

        public DescribeStacksTask(
            DescribeStacksRequest request,
            AsyncAction<AsyncResult<DescribeStacksResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStacksResult parse(JsonNode data) {
            return DescribeStacksResult.fromJson(data);
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

    public void describeStacksAsync(
            DescribeStacksRequest request,
            AsyncAction<AsyncResult<DescribeStacksResult>> callback
    ) {
        DescribeStacksTask task = new DescribeStacksTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<CreateStackResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateStackResult parse(JsonNode data) {
            return CreateStackResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("template", request.getTemplate());
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

    public void createStackAsync(
            CreateStackRequest request,
            AsyncAction<AsyncResult<CreateStackResult>> callback
    ) {
        CreateStackTask task = new CreateStackTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<CreateStackFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateStackFromGitHubResult parse(JsonNode data) {
            return CreateStackFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/from_git_hub";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("checkoutSetting", request.getCheckoutSetting() != null ? request.getCheckoutSetting().toJson() : null);
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

    public void createStackFromGitHubAsync(
            CreateStackFromGitHubRequest request,
            AsyncAction<AsyncResult<CreateStackFromGitHubResult>> callback
    ) {
        CreateStackFromGitHubTask task = new CreateStackFromGitHubTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<ValidateResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ValidateResult parse(JsonNode data) {
            return ValidateResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/validate";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("template", request.getTemplate());
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

    public void validateAsync(
            ValidateRequest request,
            AsyncAction<AsyncResult<ValidateResult>> callback
    ) {
        ValidateTask task = new ValidateTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetStackStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStackStatusResult parse(JsonNode data) {
            return GetStackStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/status";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void getStackStatusAsync(
            GetStackStatusRequest request,
            AsyncAction<AsyncResult<GetStackStatusResult>> callback
    ) {
        GetStackStatusTask task = new GetStackStatusTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetStackResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStackResult parse(JsonNode data) {
            return GetStackResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void getStackAsync(
            GetStackRequest request,
            AsyncAction<AsyncResult<GetStackResult>> callback
    ) {
        GetStackTask task = new GetStackTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UpdateStackResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateStackResult parse(JsonNode data) {
            return UpdateStackResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("template", request.getTemplate());
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

    public void updateStackAsync(
            UpdateStackRequest request,
            AsyncAction<AsyncResult<UpdateStackResult>> callback
    ) {
        UpdateStackTask task = new UpdateStackTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UpdateStackFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateStackFromGitHubResult parse(JsonNode data) {
            return UpdateStackFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/from_git_hub";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("checkoutSetting", request.getCheckoutSetting() != null ? request.getCheckoutSetting().toJson() : null);
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

    public void updateStackFromGitHubAsync(
            UpdateStackFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateStackFromGitHubResult>> callback
    ) {
        UpdateStackFromGitHubTask task = new UpdateStackFromGitHubTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteStackResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStackResult parse(JsonNode data) {
            return DeleteStackResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void deleteStackAsync(
            DeleteStackRequest request,
            AsyncAction<AsyncResult<DeleteStackResult>> callback
    ) {
        DeleteStackTask task = new DeleteStackTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<ForceDeleteStackResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ForceDeleteStackResult parse(JsonNode data) {
            return ForceDeleteStackResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/force";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void forceDeleteStackAsync(
            ForceDeleteStackRequest request,
            AsyncAction<AsyncResult<ForceDeleteStackResult>> callback
    ) {
        ForceDeleteStackTask task = new ForceDeleteStackTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteStackResourcesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStackResourcesResult parse(JsonNode data) {
            return DeleteStackResourcesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/resources";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void deleteStackResourcesAsync(
            DeleteStackResourcesRequest request,
            AsyncAction<AsyncResult<DeleteStackResourcesResult>> callback
    ) {
        DeleteStackResourcesTask task = new DeleteStackResourcesTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteStackEntityResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStackEntityResult parse(JsonNode data) {
            return DeleteStackEntityResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/entity";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void deleteStackEntityAsync(
            DeleteStackEntityRequest request,
            AsyncAction<AsyncResult<DeleteStackEntityResult>> callback
    ) {
        DeleteStackEntityTask task = new DeleteStackEntityTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeResourcesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeResourcesResult parse(JsonNode data) {
            return DescribeResourcesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/resource";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void describeResourcesAsync(
            DescribeResourcesRequest request,
            AsyncAction<AsyncResult<DescribeResourcesResult>> callback
    ) {
        DescribeResourcesTask task = new DescribeResourcesTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetResourceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetResourceResult parse(JsonNode data) {
            return GetResourceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/resource/{resourceName}";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));
            url = url.replace("{resourceName}", this.request.getResourceName() == null || this.request.getResourceName().length() == 0 ? "null" : String.valueOf(this.request.getResourceName()));

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

    public void getResourceAsync(
            GetResourceRequest request,
            AsyncAction<AsyncResult<GetResourceResult>> callback
    ) {
        GetResourceTask task = new GetResourceTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeEventsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeEventsResult parse(JsonNode data) {
            return DescribeEventsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/event";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void describeEventsAsync(
            DescribeEventsRequest request,
            AsyncAction<AsyncResult<DescribeEventsResult>> callback
    ) {
        DescribeEventsTask task = new DescribeEventsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetEventResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetEventResult parse(JsonNode data) {
            return GetEventResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/event/{eventName}";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));
            url = url.replace("{eventName}", this.request.getEventName() == null || this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

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

    public void getEventAsync(
            GetEventRequest request,
            AsyncAction<AsyncResult<GetEventResult>> callback
    ) {
        GetEventTask task = new GetEventTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeOutputsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeOutputsResult parse(JsonNode data) {
            return DescribeOutputsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/output";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));

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

    public void describeOutputsAsync(
            DescribeOutputsRequest request,
            AsyncAction<AsyncResult<DescribeOutputsResult>> callback
    ) {
        DescribeOutputsTask task = new DescribeOutputsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetOutputResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetOutputResult parse(JsonNode data) {
            return GetOutputResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "deploy")
                .replace("{region}", session.getRegion().getName())
                + "/stack/{stackName}/output/{outputName}";

            url = url.replace("{stackName}", this.request.getStackName() == null || this.request.getStackName().length() == 0 ? "null" : String.valueOf(this.request.getStackName()));
            url = url.replace("{outputName}", this.request.getOutputName() == null || this.request.getOutputName().length() == 0 ? "null" : String.valueOf(this.request.getOutputName()));

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

    public void getOutputAsync(
            GetOutputRequest request,
            AsyncAction<AsyncResult<GetOutputResult>> callback
    ) {
        GetOutputTask task = new GetOutputTask(request, callback);
        session.execute(task);
    }

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