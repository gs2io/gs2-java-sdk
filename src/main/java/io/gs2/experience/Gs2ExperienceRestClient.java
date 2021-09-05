
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

package io.gs2.experience;

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
import io.gs2.experience.request.*;
import io.gs2.experience.result.*;
import io.gs2.experience.model.*;public class Gs2ExperienceRestClient extends AbstractGs2Client<Gs2ExperienceRestClient> {

	public Gs2ExperienceRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeNamespacesTask extends Gs2RestSessionTask<DescribeNamespacesResult> {
        private DescribeNamespacesRequest request;

        public DescribeNamespacesTask(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
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

    class CreateNamespaceTask extends Gs2RestSessionTask<CreateNamespaceResult> {
        private CreateNamespaceRequest request;

        public CreateNamespaceTask(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("experienceCapScriptId", request.getExperienceCapScriptId());
                    put("changeExperienceScript", request.getChangeExperienceScript() != null ? request.getChangeExperienceScript().toJson() : null);
                    put("changeRankScript", request.getChangeRankScript() != null ? request.getChangeRankScript().toJson() : null);
                    put("changeRankCapScript", request.getChangeRankCapScript() != null ? request.getChangeRankCapScript().toJson() : null);
                    put("overflowExperienceScript", request.getOverflowExperienceScript() != null ? request.getOverflowExperienceScript().toJson() : null);
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

    class GetNamespaceStatusTask extends Gs2RestSessionTask<GetNamespaceStatusResult> {
        private GetNamespaceStatusRequest request;

        public GetNamespaceStatusTask(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
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

    class GetNamespaceTask extends Gs2RestSessionTask<GetNamespaceResult> {
        private GetNamespaceRequest request;

        public GetNamespaceTask(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
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

    class UpdateNamespaceTask extends Gs2RestSessionTask<UpdateNamespaceResult> {
        private UpdateNamespaceRequest request;

        public UpdateNamespaceTask(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("experienceCapScriptId", request.getExperienceCapScriptId());
                    put("changeExperienceScript", request.getChangeExperienceScript() != null ? request.getChangeExperienceScript().toJson() : null);
                    put("changeRankScript", request.getChangeRankScript() != null ? request.getChangeRankScript().toJson() : null);
                    put("changeRankCapScript", request.getChangeRankCapScript() != null ? request.getChangeRankCapScript().toJson() : null);
                    put("overflowExperienceScript", request.getOverflowExperienceScript() != null ? request.getOverflowExperienceScript().toJson() : null);
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

    class DeleteNamespaceTask extends Gs2RestSessionTask<DeleteNamespaceResult> {
        private DeleteNamespaceRequest request;

        public DeleteNamespaceTask(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
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

    class DescribeExperienceModelMastersTask extends Gs2RestSessionTask<DescribeExperienceModelMastersResult> {
        private DescribeExperienceModelMastersRequest request;

        public DescribeExperienceModelMastersTask(
            DescribeExperienceModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeExperienceModelMastersResult parse(JsonNode data) {
            return DescribeExperienceModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

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

            builder
                .build()
                .send();
        }
    }

    public void describeExperienceModelMastersAsync(
            DescribeExperienceModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelMastersResult>> callback
    ) {
        DescribeExperienceModelMastersTask task = new DescribeExperienceModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeExperienceModelMastersResult describeExperienceModelMasters(
            DescribeExperienceModelMastersRequest request
    ) {
        final AsyncResult<DescribeExperienceModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeExperienceModelMastersAsync(
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

    class CreateExperienceModelMasterTask extends Gs2RestSessionTask<CreateExperienceModelMasterResult> {
        private CreateExperienceModelMasterRequest request;

        public CreateExperienceModelMasterTask(
            CreateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<CreateExperienceModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateExperienceModelMasterResult parse(JsonNode data) {
            return CreateExperienceModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("defaultExperience", request.getDefaultExperience());
                    put("defaultRankCap", request.getDefaultRankCap());
                    put("maxRankCap", request.getMaxRankCap());
                    put("rankThresholdName", request.getRankThresholdName());
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

    public void createExperienceModelMasterAsync(
            CreateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<CreateExperienceModelMasterResult>> callback
    ) {
        CreateExperienceModelMasterTask task = new CreateExperienceModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateExperienceModelMasterResult createExperienceModelMaster(
            CreateExperienceModelMasterRequest request
    ) {
        final AsyncResult<CreateExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createExperienceModelMasterAsync(
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

    class GetExperienceModelMasterTask extends Gs2RestSessionTask<GetExperienceModelMasterResult> {
        private GetExperienceModelMasterRequest request;

        public GetExperienceModelMasterTask(
            GetExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<GetExperienceModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetExperienceModelMasterResult parse(JsonNode data) {
            return GetExperienceModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

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

    public void getExperienceModelMasterAsync(
            GetExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<GetExperienceModelMasterResult>> callback
    ) {
        GetExperienceModelMasterTask task = new GetExperienceModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetExperienceModelMasterResult getExperienceModelMaster(
            GetExperienceModelMasterRequest request
    ) {
        final AsyncResult<GetExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getExperienceModelMasterAsync(
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

    class UpdateExperienceModelMasterTask extends Gs2RestSessionTask<UpdateExperienceModelMasterResult> {
        private UpdateExperienceModelMasterRequest request;

        public UpdateExperienceModelMasterTask(
            UpdateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateExperienceModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateExperienceModelMasterResult parse(JsonNode data) {
            return UpdateExperienceModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("defaultExperience", request.getDefaultExperience());
                    put("defaultRankCap", request.getDefaultRankCap());
                    put("maxRankCap", request.getMaxRankCap());
                    put("rankThresholdName", request.getRankThresholdName());
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

    public void updateExperienceModelMasterAsync(
            UpdateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateExperienceModelMasterResult>> callback
    ) {
        UpdateExperienceModelMasterTask task = new UpdateExperienceModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateExperienceModelMasterResult updateExperienceModelMaster(
            UpdateExperienceModelMasterRequest request
    ) {
        final AsyncResult<UpdateExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateExperienceModelMasterAsync(
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

    class DeleteExperienceModelMasterTask extends Gs2RestSessionTask<DeleteExperienceModelMasterResult> {
        private DeleteExperienceModelMasterRequest request;

        public DeleteExperienceModelMasterTask(
            DeleteExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteExperienceModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteExperienceModelMasterResult parse(JsonNode data) {
            return DeleteExperienceModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

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

    public void deleteExperienceModelMasterAsync(
            DeleteExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteExperienceModelMasterResult>> callback
    ) {
        DeleteExperienceModelMasterTask task = new DeleteExperienceModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteExperienceModelMasterResult deleteExperienceModelMaster(
            DeleteExperienceModelMasterRequest request
    ) {
        final AsyncResult<DeleteExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteExperienceModelMasterAsync(
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

    class DescribeExperienceModelsTask extends Gs2RestSessionTask<DescribeExperienceModelsResult> {
        private DescribeExperienceModelsRequest request;

        public DescribeExperienceModelsTask(
            DescribeExperienceModelsRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeExperienceModelsResult parse(JsonNode data) {
            return DescribeExperienceModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model";

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

    public void describeExperienceModelsAsync(
            DescribeExperienceModelsRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelsResult>> callback
    ) {
        DescribeExperienceModelsTask task = new DescribeExperienceModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeExperienceModelsResult describeExperienceModels(
            DescribeExperienceModelsRequest request
    ) {
        final AsyncResult<DescribeExperienceModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeExperienceModelsAsync(
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

    class GetExperienceModelTask extends Gs2RestSessionTask<GetExperienceModelResult> {
        private GetExperienceModelRequest request;

        public GetExperienceModelTask(
            GetExperienceModelRequest request,
            AsyncAction<AsyncResult<GetExperienceModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetExperienceModelResult parse(JsonNode data) {
            return GetExperienceModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

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

    public void getExperienceModelAsync(
            GetExperienceModelRequest request,
            AsyncAction<AsyncResult<GetExperienceModelResult>> callback
    ) {
        GetExperienceModelTask task = new GetExperienceModelTask(request, callback);
        session.execute(task);
    }

    public GetExperienceModelResult getExperienceModel(
            GetExperienceModelRequest request
    ) {
        final AsyncResult<GetExperienceModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getExperienceModelAsync(
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

    class DescribeThresholdMastersTask extends Gs2RestSessionTask<DescribeThresholdMastersResult> {
        private DescribeThresholdMastersRequest request;

        public DescribeThresholdMastersTask(
            DescribeThresholdMastersRequest request,
            AsyncAction<AsyncResult<DescribeThresholdMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeThresholdMastersResult parse(JsonNode data) {
            return DescribeThresholdMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold";

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

            builder
                .build()
                .send();
        }
    }

    public void describeThresholdMastersAsync(
            DescribeThresholdMastersRequest request,
            AsyncAction<AsyncResult<DescribeThresholdMastersResult>> callback
    ) {
        DescribeThresholdMastersTask task = new DescribeThresholdMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeThresholdMastersResult describeThresholdMasters(
            DescribeThresholdMastersRequest request
    ) {
        final AsyncResult<DescribeThresholdMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeThresholdMastersAsync(
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

    class CreateThresholdMasterTask extends Gs2RestSessionTask<CreateThresholdMasterResult> {
        private CreateThresholdMasterRequest request;

        public CreateThresholdMasterTask(
            CreateThresholdMasterRequest request,
            AsyncAction<AsyncResult<CreateThresholdMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateThresholdMasterResult parse(JsonNode data) {
            return CreateThresholdMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("values", request.getValues() == null ? new ArrayList<Long>() :
                        request.getValues().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
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

    public void createThresholdMasterAsync(
            CreateThresholdMasterRequest request,
            AsyncAction<AsyncResult<CreateThresholdMasterResult>> callback
    ) {
        CreateThresholdMasterTask task = new CreateThresholdMasterTask(request, callback);
        session.execute(task);
    }

    public CreateThresholdMasterResult createThresholdMaster(
            CreateThresholdMasterRequest request
    ) {
        final AsyncResult<CreateThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createThresholdMasterAsync(
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

    class GetThresholdMasterTask extends Gs2RestSessionTask<GetThresholdMasterResult> {
        private GetThresholdMasterRequest request;

        public GetThresholdMasterTask(
            GetThresholdMasterRequest request,
            AsyncAction<AsyncResult<GetThresholdMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetThresholdMasterResult parse(JsonNode data) {
            return GetThresholdMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold/{thresholdName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{thresholdName}", this.request.getThresholdName() == null || this.request.getThresholdName().length() == 0 ? "null" : String.valueOf(this.request.getThresholdName()));

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

    public void getThresholdMasterAsync(
            GetThresholdMasterRequest request,
            AsyncAction<AsyncResult<GetThresholdMasterResult>> callback
    ) {
        GetThresholdMasterTask task = new GetThresholdMasterTask(request, callback);
        session.execute(task);
    }

    public GetThresholdMasterResult getThresholdMaster(
            GetThresholdMasterRequest request
    ) {
        final AsyncResult<GetThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getThresholdMasterAsync(
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

    class UpdateThresholdMasterTask extends Gs2RestSessionTask<UpdateThresholdMasterResult> {
        private UpdateThresholdMasterRequest request;

        public UpdateThresholdMasterTask(
            UpdateThresholdMasterRequest request,
            AsyncAction<AsyncResult<UpdateThresholdMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateThresholdMasterResult parse(JsonNode data) {
            return UpdateThresholdMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold/{thresholdName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{thresholdName}", this.request.getThresholdName() == null || this.request.getThresholdName().length() == 0 ? "null" : String.valueOf(this.request.getThresholdName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("values", request.getValues() == null ? new ArrayList<Long>() :
                        request.getValues().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
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

    public void updateThresholdMasterAsync(
            UpdateThresholdMasterRequest request,
            AsyncAction<AsyncResult<UpdateThresholdMasterResult>> callback
    ) {
        UpdateThresholdMasterTask task = new UpdateThresholdMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateThresholdMasterResult updateThresholdMaster(
            UpdateThresholdMasterRequest request
    ) {
        final AsyncResult<UpdateThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateThresholdMasterAsync(
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

    class DeleteThresholdMasterTask extends Gs2RestSessionTask<DeleteThresholdMasterResult> {
        private DeleteThresholdMasterRequest request;

        public DeleteThresholdMasterTask(
            DeleteThresholdMasterRequest request,
            AsyncAction<AsyncResult<DeleteThresholdMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteThresholdMasterResult parse(JsonNode data) {
            return DeleteThresholdMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold/{thresholdName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{thresholdName}", this.request.getThresholdName() == null || this.request.getThresholdName().length() == 0 ? "null" : String.valueOf(this.request.getThresholdName()));

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

    public void deleteThresholdMasterAsync(
            DeleteThresholdMasterRequest request,
            AsyncAction<AsyncResult<DeleteThresholdMasterResult>> callback
    ) {
        DeleteThresholdMasterTask task = new DeleteThresholdMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteThresholdMasterResult deleteThresholdMaster(
            DeleteThresholdMasterRequest request
    ) {
        final AsyncResult<DeleteThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteThresholdMasterAsync(
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

    class ExportMasterTask extends Gs2RestSessionTask<ExportMasterResult> {
        private ExportMasterRequest request;

        public ExportMasterTask(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ExportMasterResult parse(JsonNode data) {
            return ExportMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/export";

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

    public void exportMasterAsync(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> callback
    ) {
        ExportMasterTask task = new ExportMasterTask(request, callback);
        session.execute(task);
    }

    public ExportMasterResult exportMaster(
            ExportMasterRequest request
    ) {
        final AsyncResult<ExportMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        exportMasterAsync(
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

    class GetCurrentExperienceMasterTask extends Gs2RestSessionTask<GetCurrentExperienceMasterResult> {
        private GetCurrentExperienceMasterRequest request;

        public GetCurrentExperienceMasterTask(
            GetCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentExperienceMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentExperienceMasterResult parse(JsonNode data) {
            return GetCurrentExperienceMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

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

    public void getCurrentExperienceMasterAsync(
            GetCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentExperienceMasterResult>> callback
    ) {
        GetCurrentExperienceMasterTask task = new GetCurrentExperienceMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentExperienceMasterResult getCurrentExperienceMaster(
            GetCurrentExperienceMasterRequest request
    ) {
        final AsyncResult<GetCurrentExperienceMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentExperienceMasterAsync(
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

    class UpdateCurrentExperienceMasterTask extends Gs2RestSessionTask<UpdateCurrentExperienceMasterResult> {
        private UpdateCurrentExperienceMasterRequest request;

        public UpdateCurrentExperienceMasterTask(
            UpdateCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentExperienceMasterResult parse(JsonNode data) {
            return UpdateCurrentExperienceMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("settings", request.getSettings());
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

    public void updateCurrentExperienceMasterAsync(
            UpdateCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterResult>> callback
    ) {
        UpdateCurrentExperienceMasterTask task = new UpdateCurrentExperienceMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentExperienceMasterResult updateCurrentExperienceMaster(
            UpdateCurrentExperienceMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentExperienceMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentExperienceMasterAsync(
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

    class UpdateCurrentExperienceMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentExperienceMasterFromGitHubResult> {
        private UpdateCurrentExperienceMasterFromGitHubRequest request;

        public UpdateCurrentExperienceMasterFromGitHubTask(
            UpdateCurrentExperienceMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentExperienceMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentExperienceMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/from_git_hub";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void updateCurrentExperienceMasterFromGitHubAsync(
            UpdateCurrentExperienceMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentExperienceMasterFromGitHubTask task = new UpdateCurrentExperienceMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentExperienceMasterFromGitHubResult updateCurrentExperienceMasterFromGitHub(
            UpdateCurrentExperienceMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentExperienceMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentExperienceMasterFromGitHubAsync(
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

    class DescribeStatusesTask extends Gs2RestSessionTask<DescribeStatusesResult> {
        private DescribeStatusesRequest request;

        public DescribeStatusesTask(
            DescribeStatusesRequest request,
            AsyncAction<AsyncResult<DescribeStatusesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStatusesResult parse(JsonNode data) {
            return DescribeStatusesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getExperienceName() != null) {
                queryStrings.add("experienceName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getExperienceName()))));
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

    public void describeStatusesAsync(
            DescribeStatusesRequest request,
            AsyncAction<AsyncResult<DescribeStatusesResult>> callback
    ) {
        DescribeStatusesTask task = new DescribeStatusesTask(request, callback);
        session.execute(task);
    }

    public DescribeStatusesResult describeStatuses(
            DescribeStatusesRequest request
    ) {
        final AsyncResult<DescribeStatusesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStatusesAsync(
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

    class DescribeStatusesByUserIdTask extends Gs2RestSessionTask<DescribeStatusesByUserIdResult> {
        private DescribeStatusesByUserIdRequest request;

        public DescribeStatusesByUserIdTask(
            DescribeStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStatusesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStatusesByUserIdResult parse(JsonNode data) {
            return DescribeStatusesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getExperienceName() != null) {
                queryStrings.add("experienceName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getExperienceName()))));
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

    public void describeStatusesByUserIdAsync(
            DescribeStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStatusesByUserIdResult>> callback
    ) {
        DescribeStatusesByUserIdTask task = new DescribeStatusesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeStatusesByUserIdResult describeStatusesByUserId(
            DescribeStatusesByUserIdRequest request
    ) {
        final AsyncResult<DescribeStatusesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStatusesByUserIdAsync(
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

    class GetStatusTask extends Gs2RestSessionTask<GetStatusResult> {
        private GetStatusRequest request;

        public GetStatusTask(
            GetStatusRequest request,
            AsyncAction<AsyncResult<GetStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStatusResult parse(JsonNode data) {
            return GetStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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

    public void getStatusAsync(
            GetStatusRequest request,
            AsyncAction<AsyncResult<GetStatusResult>> callback
    ) {
        GetStatusTask task = new GetStatusTask(request, callback);
        session.execute(task);
    }

    public GetStatusResult getStatus(
            GetStatusRequest request
    ) {
        final AsyncResult<GetStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusAsync(
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

    class GetStatusByUserIdTask extends Gs2RestSessionTask<GetStatusByUserIdResult> {
        private GetStatusByUserIdRequest request;

        public GetStatusByUserIdTask(
            GetStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStatusByUserIdResult parse(JsonNode data) {
            return GetStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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

    public void getStatusByUserIdAsync(
            GetStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetStatusByUserIdResult>> callback
    ) {
        GetStatusByUserIdTask task = new GetStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetStatusByUserIdResult getStatusByUserId(
            GetStatusByUserIdRequest request
    ) {
        final AsyncResult<GetStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusByUserIdAsync(
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

    class GetStatusWithSignatureTask extends Gs2RestSessionTask<GetStatusWithSignatureResult> {
        private GetStatusWithSignatureRequest request;

        public GetStatusWithSignatureTask(
            GetStatusWithSignatureRequest request,
            AsyncAction<AsyncResult<GetStatusWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStatusWithSignatureResult parse(JsonNode data) {
            return GetStatusWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/model/{experienceName}/property/{propertyId}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getKeyId() != null) {
                queryStrings.add("keyId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getKeyId()))));
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

    public void getStatusWithSignatureAsync(
            GetStatusWithSignatureRequest request,
            AsyncAction<AsyncResult<GetStatusWithSignatureResult>> callback
    ) {
        GetStatusWithSignatureTask task = new GetStatusWithSignatureTask(request, callback);
        session.execute(task);
    }

    public GetStatusWithSignatureResult getStatusWithSignature(
            GetStatusWithSignatureRequest request
    ) {
        final AsyncResult<GetStatusWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusWithSignatureAsync(
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

    class GetStatusWithSignatureByUserIdTask extends Gs2RestSessionTask<GetStatusWithSignatureByUserIdResult> {
        private GetStatusWithSignatureByUserIdRequest request;

        public GetStatusWithSignatureByUserIdTask(
            GetStatusWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetStatusWithSignatureByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStatusWithSignatureByUserIdResult parse(JsonNode data) {
            return GetStatusWithSignatureByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getKeyId() != null) {
                queryStrings.add("keyId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getKeyId()))));
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

    public void getStatusWithSignatureByUserIdAsync(
            GetStatusWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetStatusWithSignatureByUserIdResult>> callback
    ) {
        GetStatusWithSignatureByUserIdTask task = new GetStatusWithSignatureByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetStatusWithSignatureByUserIdResult getStatusWithSignatureByUserId(
            GetStatusWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetStatusWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusWithSignatureByUserIdAsync(
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

    class AddExperienceByUserIdTask extends Gs2RestSessionTask<AddExperienceByUserIdResult> {
        private AddExperienceByUserIdRequest request;

        public AddExperienceByUserIdTask(
            AddExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<AddExperienceByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddExperienceByUserIdResult parse(JsonNode data) {
            return AddExperienceByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("experienceValue", request.getExperienceValue());
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

    public void addExperienceByUserIdAsync(
            AddExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<AddExperienceByUserIdResult>> callback
    ) {
        AddExperienceByUserIdTask task = new AddExperienceByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddExperienceByUserIdResult addExperienceByUserId(
            AddExperienceByUserIdRequest request
    ) {
        final AsyncResult<AddExperienceByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addExperienceByUserIdAsync(
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

    class SetExperienceByUserIdTask extends Gs2RestSessionTask<SetExperienceByUserIdResult> {
        private SetExperienceByUserIdRequest request;

        public SetExperienceByUserIdTask(
            SetExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<SetExperienceByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetExperienceByUserIdResult parse(JsonNode data) {
            return SetExperienceByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("experienceValue", request.getExperienceValue());
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

    public void setExperienceByUserIdAsync(
            SetExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<SetExperienceByUserIdResult>> callback
    ) {
        SetExperienceByUserIdTask task = new SetExperienceByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetExperienceByUserIdResult setExperienceByUserId(
            SetExperienceByUserIdRequest request
    ) {
        final AsyncResult<SetExperienceByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setExperienceByUserIdAsync(
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

    class AddRankCapByUserIdTask extends Gs2RestSessionTask<AddRankCapByUserIdResult> {
        private AddRankCapByUserIdRequest request;

        public AddRankCapByUserIdTask(
            AddRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<AddRankCapByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddRankCapByUserIdResult parse(JsonNode data) {
            return AddRankCapByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}/cap";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("rankCapValue", request.getRankCapValue());
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

    public void addRankCapByUserIdAsync(
            AddRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<AddRankCapByUserIdResult>> callback
    ) {
        AddRankCapByUserIdTask task = new AddRankCapByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddRankCapByUserIdResult addRankCapByUserId(
            AddRankCapByUserIdRequest request
    ) {
        final AsyncResult<AddRankCapByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addRankCapByUserIdAsync(
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

    class SetRankCapByUserIdTask extends Gs2RestSessionTask<SetRankCapByUserIdResult> {
        private SetRankCapByUserIdRequest request;

        public SetRankCapByUserIdTask(
            SetRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<SetRankCapByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRankCapByUserIdResult parse(JsonNode data) {
            return SetRankCapByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}/cap";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("rankCapValue", request.getRankCapValue());
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

    public void setRankCapByUserIdAsync(
            SetRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<SetRankCapByUserIdResult>> callback
    ) {
        SetRankCapByUserIdTask task = new SetRankCapByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetRankCapByUserIdResult setRankCapByUserId(
            SetRankCapByUserIdRequest request
    ) {
        final AsyncResult<SetRankCapByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRankCapByUserIdAsync(
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

    class DeleteStatusByUserIdTask extends Gs2RestSessionTask<DeleteStatusByUserIdResult> {
        private DeleteStatusByUserIdRequest request;

        public DeleteStatusByUserIdTask(
            DeleteStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStatusByUserIdResult parse(JsonNode data) {
            return DeleteStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null || this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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

    public void deleteStatusByUserIdAsync(
            DeleteStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStatusByUserIdResult>> callback
    ) {
        DeleteStatusByUserIdTask task = new DeleteStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteStatusByUserIdResult deleteStatusByUserId(
            DeleteStatusByUserIdRequest request
    ) {
        final AsyncResult<DeleteStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStatusByUserIdAsync(
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

    class AddExperienceByStampSheetTask extends Gs2RestSessionTask<AddExperienceByStampSheetResult> {
        private AddExperienceByStampSheetRequest request;

        public AddExperienceByStampSheetTask(
            AddExperienceByStampSheetRequest request,
            AsyncAction<AsyncResult<AddExperienceByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddExperienceByStampSheetResult parse(JsonNode data) {
            return AddExperienceByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/experience/add";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampSheet", request.getStampSheet());
                    put("keyId", request.getKeyId());
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

    public void addExperienceByStampSheetAsync(
            AddExperienceByStampSheetRequest request,
            AsyncAction<AsyncResult<AddExperienceByStampSheetResult>> callback
    ) {
        AddExperienceByStampSheetTask task = new AddExperienceByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AddExperienceByStampSheetResult addExperienceByStampSheet(
            AddExperienceByStampSheetRequest request
    ) {
        final AsyncResult<AddExperienceByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addExperienceByStampSheetAsync(
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

    class AddRankCapByStampSheetTask extends Gs2RestSessionTask<AddRankCapByStampSheetResult> {
        private AddRankCapByStampSheetRequest request;

        public AddRankCapByStampSheetTask(
            AddRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<AddRankCapByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddRankCapByStampSheetResult parse(JsonNode data) {
            return AddRankCapByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rankCap/add";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampSheet", request.getStampSheet());
                    put("keyId", request.getKeyId());
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

    public void addRankCapByStampSheetAsync(
            AddRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<AddRankCapByStampSheetResult>> callback
    ) {
        AddRankCapByStampSheetTask task = new AddRankCapByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AddRankCapByStampSheetResult addRankCapByStampSheet(
            AddRankCapByStampSheetRequest request
    ) {
        final AsyncResult<AddRankCapByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addRankCapByStampSheetAsync(
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

    class SetRankCapByStampSheetTask extends Gs2RestSessionTask<SetRankCapByStampSheetResult> {
        private SetRankCapByStampSheetRequest request;

        public SetRankCapByStampSheetTask(
            SetRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRankCapByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRankCapByStampSheetResult parse(JsonNode data) {
            return SetRankCapByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rankCap/set";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampSheet", request.getStampSheet());
                    put("keyId", request.getKeyId());
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

    public void setRankCapByStampSheetAsync(
            SetRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRankCapByStampSheetResult>> callback
    ) {
        SetRankCapByStampSheetTask task = new SetRankCapByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetRankCapByStampSheetResult setRankCapByStampSheet(
            SetRankCapByStampSheetRequest request
    ) {
        final AsyncResult<SetRankCapByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRankCapByStampSheetAsync(
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