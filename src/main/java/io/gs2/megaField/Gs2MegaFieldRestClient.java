
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

package io.gs2.megaField;

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
import io.gs2.megaField.request.*;
import io.gs2.megaField.result.*;
import io.gs2.megaField.model.*;public class Gs2MegaFieldRestClient extends AbstractGs2Client<Gs2MegaFieldRestClient> {

	public Gs2MegaFieldRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "mega-field")
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
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
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
                .replace("{service}", "mega-field")
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
                .replace("{service}", "mega-field")
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
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
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
                .replace("{service}", "mega-field")
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

    class DescribeAreaModelsTask extends Gs2RestSessionTask<DescribeAreaModelsResult> {
        private DescribeAreaModelsRequest request;

        public DescribeAreaModelsTask(
            DescribeAreaModelsRequest request,
            AsyncAction<AsyncResult<DescribeAreaModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeAreaModelsResult parse(JsonNode data) {
            return DescribeAreaModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/area";

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

    public void describeAreaModelsAsync(
            DescribeAreaModelsRequest request,
            AsyncAction<AsyncResult<DescribeAreaModelsResult>> callback
    ) {
        DescribeAreaModelsTask task = new DescribeAreaModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeAreaModelsResult describeAreaModels(
            DescribeAreaModelsRequest request
    ) {
        final AsyncResult<DescribeAreaModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAreaModelsAsync(
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

    class GetAreaModelTask extends Gs2RestSessionTask<GetAreaModelResult> {
        private GetAreaModelRequest request;

        public GetAreaModelTask(
            GetAreaModelRequest request,
            AsyncAction<AsyncResult<GetAreaModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetAreaModelResult parse(JsonNode data) {
            return GetAreaModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/area/{areaModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

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

    public void getAreaModelAsync(
            GetAreaModelRequest request,
            AsyncAction<AsyncResult<GetAreaModelResult>> callback
    ) {
        GetAreaModelTask task = new GetAreaModelTask(request, callback);
        session.execute(task);
    }

    public GetAreaModelResult getAreaModel(
            GetAreaModelRequest request
    ) {
        final AsyncResult<GetAreaModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAreaModelAsync(
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

    class DescribeAreaModelMastersTask extends Gs2RestSessionTask<DescribeAreaModelMastersResult> {
        private DescribeAreaModelMastersRequest request;

        public DescribeAreaModelMastersTask(
            DescribeAreaModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeAreaModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeAreaModelMastersResult parse(JsonNode data) {
            return DescribeAreaModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area";

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

    public void describeAreaModelMastersAsync(
            DescribeAreaModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeAreaModelMastersResult>> callback
    ) {
        DescribeAreaModelMastersTask task = new DescribeAreaModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeAreaModelMastersResult describeAreaModelMasters(
            DescribeAreaModelMastersRequest request
    ) {
        final AsyncResult<DescribeAreaModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAreaModelMastersAsync(
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

    class CreateAreaModelMasterTask extends Gs2RestSessionTask<CreateAreaModelMasterResult> {
        private CreateAreaModelMasterRequest request;

        public CreateAreaModelMasterTask(
            CreateAreaModelMasterRequest request,
            AsyncAction<AsyncResult<CreateAreaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateAreaModelMasterResult parse(JsonNode data) {
            return CreateAreaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
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

    public void createAreaModelMasterAsync(
            CreateAreaModelMasterRequest request,
            AsyncAction<AsyncResult<CreateAreaModelMasterResult>> callback
    ) {
        CreateAreaModelMasterTask task = new CreateAreaModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateAreaModelMasterResult createAreaModelMaster(
            CreateAreaModelMasterRequest request
    ) {
        final AsyncResult<CreateAreaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createAreaModelMasterAsync(
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

    class GetAreaModelMasterTask extends Gs2RestSessionTask<GetAreaModelMasterResult> {
        private GetAreaModelMasterRequest request;

        public GetAreaModelMasterTask(
            GetAreaModelMasterRequest request,
            AsyncAction<AsyncResult<GetAreaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetAreaModelMasterResult parse(JsonNode data) {
            return GetAreaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

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

    public void getAreaModelMasterAsync(
            GetAreaModelMasterRequest request,
            AsyncAction<AsyncResult<GetAreaModelMasterResult>> callback
    ) {
        GetAreaModelMasterTask task = new GetAreaModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetAreaModelMasterResult getAreaModelMaster(
            GetAreaModelMasterRequest request
    ) {
        final AsyncResult<GetAreaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAreaModelMasterAsync(
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

    class UpdateAreaModelMasterTask extends Gs2RestSessionTask<UpdateAreaModelMasterResult> {
        private UpdateAreaModelMasterRequest request;

        public UpdateAreaModelMasterTask(
            UpdateAreaModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateAreaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateAreaModelMasterResult parse(JsonNode data) {
            return UpdateAreaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
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

    public void updateAreaModelMasterAsync(
            UpdateAreaModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateAreaModelMasterResult>> callback
    ) {
        UpdateAreaModelMasterTask task = new UpdateAreaModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateAreaModelMasterResult updateAreaModelMaster(
            UpdateAreaModelMasterRequest request
    ) {
        final AsyncResult<UpdateAreaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateAreaModelMasterAsync(
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

    class DeleteAreaModelMasterTask extends Gs2RestSessionTask<DeleteAreaModelMasterResult> {
        private DeleteAreaModelMasterRequest request;

        public DeleteAreaModelMasterTask(
            DeleteAreaModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteAreaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteAreaModelMasterResult parse(JsonNode data) {
            return DeleteAreaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

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

    public void deleteAreaModelMasterAsync(
            DeleteAreaModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteAreaModelMasterResult>> callback
    ) {
        DeleteAreaModelMasterTask task = new DeleteAreaModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteAreaModelMasterResult deleteAreaModelMaster(
            DeleteAreaModelMasterRequest request
    ) {
        final AsyncResult<DeleteAreaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAreaModelMasterAsync(
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

    class DescribeLayerModelsTask extends Gs2RestSessionTask<DescribeLayerModelsResult> {
        private DescribeLayerModelsRequest request;

        public DescribeLayerModelsTask(
            DescribeLayerModelsRequest request,
            AsyncAction<AsyncResult<DescribeLayerModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeLayerModelsResult parse(JsonNode data) {
            return DescribeLayerModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/area/{areaModelName}/layer";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

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

    public void describeLayerModelsAsync(
            DescribeLayerModelsRequest request,
            AsyncAction<AsyncResult<DescribeLayerModelsResult>> callback
    ) {
        DescribeLayerModelsTask task = new DescribeLayerModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeLayerModelsResult describeLayerModels(
            DescribeLayerModelsRequest request
    ) {
        final AsyncResult<DescribeLayerModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLayerModelsAsync(
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

    class GetLayerModelTask extends Gs2RestSessionTask<GetLayerModelResult> {
        private GetLayerModelRequest request;

        public GetLayerModelTask(
            GetLayerModelRequest request,
            AsyncAction<AsyncResult<GetLayerModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLayerModelResult parse(JsonNode data) {
            return GetLayerModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/area/{areaModelName}/layer/{layerModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

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

    public void getLayerModelAsync(
            GetLayerModelRequest request,
            AsyncAction<AsyncResult<GetLayerModelResult>> callback
    ) {
        GetLayerModelTask task = new GetLayerModelTask(request, callback);
        session.execute(task);
    }

    public GetLayerModelResult getLayerModel(
            GetLayerModelRequest request
    ) {
        final AsyncResult<GetLayerModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLayerModelAsync(
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

    class DescribeLayerModelMastersTask extends Gs2RestSessionTask<DescribeLayerModelMastersResult> {
        private DescribeLayerModelMastersRequest request;

        public DescribeLayerModelMastersTask(
            DescribeLayerModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLayerModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeLayerModelMastersResult parse(JsonNode data) {
            return DescribeLayerModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}/layer";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

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

    public void describeLayerModelMastersAsync(
            DescribeLayerModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLayerModelMastersResult>> callback
    ) {
        DescribeLayerModelMastersTask task = new DescribeLayerModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeLayerModelMastersResult describeLayerModelMasters(
            DescribeLayerModelMastersRequest request
    ) {
        final AsyncResult<DescribeLayerModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLayerModelMastersAsync(
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

    class CreateLayerModelMasterTask extends Gs2RestSessionTask<CreateLayerModelMasterResult> {
        private CreateLayerModelMasterRequest request;

        public CreateLayerModelMasterTask(
            CreateLayerModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLayerModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateLayerModelMasterResult parse(JsonNode data) {
            return CreateLayerModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}/layer";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
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

    public void createLayerModelMasterAsync(
            CreateLayerModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLayerModelMasterResult>> callback
    ) {
        CreateLayerModelMasterTask task = new CreateLayerModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateLayerModelMasterResult createLayerModelMaster(
            CreateLayerModelMasterRequest request
    ) {
        final AsyncResult<CreateLayerModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createLayerModelMasterAsync(
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

    class GetLayerModelMasterTask extends Gs2RestSessionTask<GetLayerModelMasterResult> {
        private GetLayerModelMasterRequest request;

        public GetLayerModelMasterTask(
            GetLayerModelMasterRequest request,
            AsyncAction<AsyncResult<GetLayerModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLayerModelMasterResult parse(JsonNode data) {
            return GetLayerModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}/layer/{layerModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

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

    public void getLayerModelMasterAsync(
            GetLayerModelMasterRequest request,
            AsyncAction<AsyncResult<GetLayerModelMasterResult>> callback
    ) {
        GetLayerModelMasterTask task = new GetLayerModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetLayerModelMasterResult getLayerModelMaster(
            GetLayerModelMasterRequest request
    ) {
        final AsyncResult<GetLayerModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLayerModelMasterAsync(
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

    class UpdateLayerModelMasterTask extends Gs2RestSessionTask<UpdateLayerModelMasterResult> {
        private UpdateLayerModelMasterRequest request;

        public UpdateLayerModelMasterTask(
            UpdateLayerModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLayerModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateLayerModelMasterResult parse(JsonNode data) {
            return UpdateLayerModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}/layer/{layerModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
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

    public void updateLayerModelMasterAsync(
            UpdateLayerModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLayerModelMasterResult>> callback
    ) {
        UpdateLayerModelMasterTask task = new UpdateLayerModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateLayerModelMasterResult updateLayerModelMaster(
            UpdateLayerModelMasterRequest request
    ) {
        final AsyncResult<UpdateLayerModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateLayerModelMasterAsync(
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

    class DeleteLayerModelMasterTask extends Gs2RestSessionTask<DeleteLayerModelMasterResult> {
        private DeleteLayerModelMasterRequest request;

        public DeleteLayerModelMasterTask(
            DeleteLayerModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLayerModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteLayerModelMasterResult parse(JsonNode data) {
            return DeleteLayerModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/area/{areaModelName}/layer/{layerModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

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

    public void deleteLayerModelMasterAsync(
            DeleteLayerModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLayerModelMasterResult>> callback
    ) {
        DeleteLayerModelMasterTask task = new DeleteLayerModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteLayerModelMasterResult deleteLayerModelMaster(
            DeleteLayerModelMasterRequest request
    ) {
        final AsyncResult<DeleteLayerModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteLayerModelMasterAsync(
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
                .replace("{service}", "mega-field")
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

    class GetCurrentFieldMasterTask extends Gs2RestSessionTask<GetCurrentFieldMasterResult> {
        private GetCurrentFieldMasterRequest request;

        public GetCurrentFieldMasterTask(
            GetCurrentFieldMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentFieldMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentFieldMasterResult parse(JsonNode data) {
            return GetCurrentFieldMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
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

    public void getCurrentFieldMasterAsync(
            GetCurrentFieldMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentFieldMasterResult>> callback
    ) {
        GetCurrentFieldMasterTask task = new GetCurrentFieldMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentFieldMasterResult getCurrentFieldMaster(
            GetCurrentFieldMasterRequest request
    ) {
        final AsyncResult<GetCurrentFieldMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentFieldMasterAsync(
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

    class UpdateCurrentFieldMasterTask extends Gs2RestSessionTask<UpdateCurrentFieldMasterResult> {
        private UpdateCurrentFieldMasterRequest request;

        public UpdateCurrentFieldMasterTask(
            UpdateCurrentFieldMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFieldMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentFieldMasterResult parse(JsonNode data) {
            return UpdateCurrentFieldMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
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

    public void updateCurrentFieldMasterAsync(
            UpdateCurrentFieldMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFieldMasterResult>> callback
    ) {
        UpdateCurrentFieldMasterTask task = new UpdateCurrentFieldMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentFieldMasterResult updateCurrentFieldMaster(
            UpdateCurrentFieldMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentFieldMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentFieldMasterAsync(
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

    class UpdateCurrentFieldMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentFieldMasterFromGitHubResult> {
        private UpdateCurrentFieldMasterFromGitHubRequest request;

        public UpdateCurrentFieldMasterFromGitHubTask(
            UpdateCurrentFieldMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFieldMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentFieldMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentFieldMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
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

    public void updateCurrentFieldMasterFromGitHubAsync(
            UpdateCurrentFieldMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFieldMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentFieldMasterFromGitHubTask task = new UpdateCurrentFieldMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentFieldMasterFromGitHubResult updateCurrentFieldMasterFromGitHub(
            UpdateCurrentFieldMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentFieldMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentFieldMasterFromGitHubAsync(
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

    class PutPositionTask extends Gs2RestSessionTask<PutPositionResult> {
        private PutPositionRequest request;

        public PutPositionTask(
            PutPositionRequest request,
            AsyncAction<AsyncResult<PutPositionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutPositionResult parse(JsonNode data) {
            return PutPositionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/spatial/{areaModelName}/{layerModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("position", request.getPosition() != null ? request.getPosition().toJson() : null);
                    put("vector", request.getVector() != null ? request.getVector().toJson() : null);
                    put("r", request.getR());
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

    public void putPositionAsync(
            PutPositionRequest request,
            AsyncAction<AsyncResult<PutPositionResult>> callback
    ) {
        PutPositionTask task = new PutPositionTask(request, callback);
        session.execute(task);
    }

    public PutPositionResult putPosition(
            PutPositionRequest request
    ) {
        final AsyncResult<PutPositionResult>[] resultAsyncResult = new AsyncResult[]{null};
        putPositionAsync(
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

    class PutPositionByUserIdTask extends Gs2RestSessionTask<PutPositionByUserIdResult> {
        private PutPositionByUserIdRequest request;

        public PutPositionByUserIdTask(
            PutPositionByUserIdRequest request,
            AsyncAction<AsyncResult<PutPositionByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutPositionByUserIdResult parse(JsonNode data) {
            return PutPositionByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/spatial/{areaModelName}/{layerModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("position", request.getPosition() != null ? request.getPosition().toJson() : null);
                    put("vector", request.getVector() != null ? request.getVector().toJson() : null);
                    put("r", request.getR());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void putPositionByUserIdAsync(
            PutPositionByUserIdRequest request,
            AsyncAction<AsyncResult<PutPositionByUserIdResult>> callback
    ) {
        PutPositionByUserIdTask task = new PutPositionByUserIdTask(request, callback);
        session.execute(task);
    }

    public PutPositionByUserIdResult putPositionByUserId(
            PutPositionByUserIdRequest request
    ) {
        final AsyncResult<PutPositionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        putPositionByUserIdAsync(
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

    class FetchPositionTask extends Gs2RestSessionTask<FetchPositionResult> {
        private FetchPositionRequest request;

        public FetchPositionTask(
            FetchPositionRequest request,
            AsyncAction<AsyncResult<FetchPositionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FetchPositionResult parse(JsonNode data) {
            return FetchPositionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/area/{areaModelName}/layer/{layerModelName}/spatial/fetch";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userIds", request.getUserIds() == null ? new ArrayList<String>() :
                        request.getUserIds().stream().map(item -> {
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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void fetchPositionAsync(
            FetchPositionRequest request,
            AsyncAction<AsyncResult<FetchPositionResult>> callback
    ) {
        FetchPositionTask task = new FetchPositionTask(request, callback);
        session.execute(task);
    }

    public FetchPositionResult fetchPosition(
            FetchPositionRequest request
    ) {
        final AsyncResult<FetchPositionResult>[] resultAsyncResult = new AsyncResult[]{null};
        fetchPositionAsync(
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

    class FetchPositionFromSystemTask extends Gs2RestSessionTask<FetchPositionFromSystemResult> {
        private FetchPositionFromSystemRequest request;

        public FetchPositionFromSystemTask(
            FetchPositionFromSystemRequest request,
            AsyncAction<AsyncResult<FetchPositionFromSystemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FetchPositionFromSystemResult parse(JsonNode data) {
            return FetchPositionFromSystemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/system/area/{areaModelName}/layer/{layerModelName}/spatial/fetch";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userIds", request.getUserIds() == null ? new ArrayList<String>() :
                        request.getUserIds().stream().map(item -> {
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void fetchPositionFromSystemAsync(
            FetchPositionFromSystemRequest request,
            AsyncAction<AsyncResult<FetchPositionFromSystemResult>> callback
    ) {
        FetchPositionFromSystemTask task = new FetchPositionFromSystemTask(request, callback);
        session.execute(task);
    }

    public FetchPositionFromSystemResult fetchPositionFromSystem(
            FetchPositionFromSystemRequest request
    ) {
        final AsyncResult<FetchPositionFromSystemResult>[] resultAsyncResult = new AsyncResult[]{null};
        fetchPositionFromSystemAsync(
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

    class NearUserIdsTask extends Gs2RestSessionTask<NearUserIdsResult> {
        private NearUserIdsRequest request;

        public NearUserIdsTask(
            NearUserIdsRequest request,
            AsyncAction<AsyncResult<NearUserIdsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public NearUserIdsResult parse(JsonNode data) {
            return NearUserIdsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/area/{areaModelName}/layer/{layerModelName}/spatial/near";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("point", request.getPoint() != null ? request.getPoint().toJson() : null);
                    put("r", request.getR());
                    put("limit", request.getLimit());
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

    public void nearUserIdsAsync(
            NearUserIdsRequest request,
            AsyncAction<AsyncResult<NearUserIdsResult>> callback
    ) {
        NearUserIdsTask task = new NearUserIdsTask(request, callback);
        session.execute(task);
    }

    public NearUserIdsResult nearUserIds(
            NearUserIdsRequest request
    ) {
        final AsyncResult<NearUserIdsResult>[] resultAsyncResult = new AsyncResult[]{null};
        nearUserIdsAsync(
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

    class NearUserIdsFromSystemTask extends Gs2RestSessionTask<NearUserIdsFromSystemResult> {
        private NearUserIdsFromSystemRequest request;

        public NearUserIdsFromSystemTask(
            NearUserIdsFromSystemRequest request,
            AsyncAction<AsyncResult<NearUserIdsFromSystemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public NearUserIdsFromSystemResult parse(JsonNode data) {
            return NearUserIdsFromSystemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/system/area/{areaModelName}/layer/{layerModelName}/spatial/near";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("point", request.getPoint() != null ? request.getPoint().toJson() : null);
                    put("r", request.getR());
                    put("limit", request.getLimit());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void nearUserIdsFromSystemAsync(
            NearUserIdsFromSystemRequest request,
            AsyncAction<AsyncResult<NearUserIdsFromSystemResult>> callback
    ) {
        NearUserIdsFromSystemTask task = new NearUserIdsFromSystemTask(request, callback);
        session.execute(task);
    }

    public NearUserIdsFromSystemResult nearUserIdsFromSystem(
            NearUserIdsFromSystemRequest request
    ) {
        final AsyncResult<NearUserIdsFromSystemResult>[] resultAsyncResult = new AsyncResult[]{null};
        nearUserIdsFromSystemAsync(
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

    class ActionTask extends Gs2RestSessionTask<ActionResult> {
        private ActionRequest request;

        public ActionTask(
            ActionRequest request,
            AsyncAction<AsyncResult<ActionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ActionResult parse(JsonNode data) {
            return ActionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/spatial/{areaModelName}/{layerModelName}/action";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("position", request.getPosition() != null ? request.getPosition().toJson() : null);
                    put("scope", request.getScope() != null ? request.getScope().toJson() : null);
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

    public void actionAsync(
            ActionRequest request,
            AsyncAction<AsyncResult<ActionResult>> callback
    ) {
        ActionTask task = new ActionTask(request, callback);
        session.execute(task);
    }

    public ActionResult action(
            ActionRequest request
    ) {
        final AsyncResult<ActionResult>[] resultAsyncResult = new AsyncResult[]{null};
        actionAsync(
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

    class ActionByUserIdTask extends Gs2RestSessionTask<ActionByUserIdResult> {
        private ActionByUserIdRequest request;

        public ActionByUserIdTask(
            ActionByUserIdRequest request,
            AsyncAction<AsyncResult<ActionByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ActionByUserIdResult parse(JsonNode data) {
            return ActionByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mega-field")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/spatial/{areaModelName}/{layerModelName}/action";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{areaModelName}", this.request.getAreaModelName() == null || this.request.getAreaModelName().length() == 0 ? "null" : String.valueOf(this.request.getAreaModelName()));
            url = url.replace("{layerModelName}", this.request.getLayerModelName() == null || this.request.getLayerModelName().length() == 0 ? "null" : String.valueOf(this.request.getLayerModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("position", request.getPosition() != null ? request.getPosition().toJson() : null);
                    put("scope", request.getScope() != null ? request.getScope().toJson() : null);
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void actionByUserIdAsync(
            ActionByUserIdRequest request,
            AsyncAction<AsyncResult<ActionByUserIdResult>> callback
    ) {
        ActionByUserIdTask task = new ActionByUserIdTask(request, callback);
        session.execute(task);
    }

    public ActionByUserIdResult actionByUserId(
            ActionByUserIdRequest request
    ) {
        final AsyncResult<ActionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        actionByUserIdAsync(
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