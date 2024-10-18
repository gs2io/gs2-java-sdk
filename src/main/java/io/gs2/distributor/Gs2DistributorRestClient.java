
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

package io.gs2.distributor;

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
import io.gs2.distributor.request.*;
import io.gs2.distributor.result.*;
import io.gs2.distributor.model.*;public class Gs2DistributorRestClient extends AbstractGs2Client<Gs2DistributorRestClient> {

	public Gs2DistributorRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "distributor")
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
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("assumeUserId", request.getAssumeUserId());
                    put("autoRunStampSheetNotification", request.getAutoRunStampSheetNotification() != null ? request.getAutoRunStampSheetNotification().toJson() : null);
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
                .replace("{service}", "distributor")
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
                .replace("{service}", "distributor")
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
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("assumeUserId", request.getAssumeUserId());
                    put("autoRunStampSheetNotification", request.getAutoRunStampSheetNotification() != null ? request.getAutoRunStampSheetNotification().toJson() : null);
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
                .replace("{service}", "distributor")
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

    class DescribeDistributorModelMastersTask extends Gs2RestSessionTask<DescribeDistributorModelMastersResult> {
        private DescribeDistributorModelMastersRequest request;

        public DescribeDistributorModelMastersTask(
            DescribeDistributorModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeDistributorModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeDistributorModelMastersResult parse(JsonNode data) {
            return DescribeDistributorModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/distributor";

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

    public void describeDistributorModelMastersAsync(
            DescribeDistributorModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeDistributorModelMastersResult>> callback
    ) {
        DescribeDistributorModelMastersTask task = new DescribeDistributorModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeDistributorModelMastersResult describeDistributorModelMasters(
            DescribeDistributorModelMastersRequest request
    ) {
        final AsyncResult<DescribeDistributorModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDistributorModelMastersAsync(
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

    class CreateDistributorModelMasterTask extends Gs2RestSessionTask<CreateDistributorModelMasterResult> {
        private CreateDistributorModelMasterRequest request;

        public CreateDistributorModelMasterTask(
            CreateDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<CreateDistributorModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateDistributorModelMasterResult parse(JsonNode data) {
            return CreateDistributorModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/distributor";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("inboxNamespaceId", request.getInboxNamespaceId());
                    put("whiteListTargetIds", request.getWhiteListTargetIds() == null ? new ArrayList<String>() :
                        request.getWhiteListTargetIds().stream().map(item -> {
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

    public void createDistributorModelMasterAsync(
            CreateDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<CreateDistributorModelMasterResult>> callback
    ) {
        CreateDistributorModelMasterTask task = new CreateDistributorModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateDistributorModelMasterResult createDistributorModelMaster(
            CreateDistributorModelMasterRequest request
    ) {
        final AsyncResult<CreateDistributorModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createDistributorModelMasterAsync(
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

    class GetDistributorModelMasterTask extends Gs2RestSessionTask<GetDistributorModelMasterResult> {
        private GetDistributorModelMasterRequest request;

        public GetDistributorModelMasterTask(
            GetDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<GetDistributorModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDistributorModelMasterResult parse(JsonNode data) {
            return GetDistributorModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/distributor/{distributorName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{distributorName}", this.request.getDistributorName() == null || this.request.getDistributorName().length() == 0 ? "null" : String.valueOf(this.request.getDistributorName()));

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

    public void getDistributorModelMasterAsync(
            GetDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<GetDistributorModelMasterResult>> callback
    ) {
        GetDistributorModelMasterTask task = new GetDistributorModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetDistributorModelMasterResult getDistributorModelMaster(
            GetDistributorModelMasterRequest request
    ) {
        final AsyncResult<GetDistributorModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDistributorModelMasterAsync(
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

    class UpdateDistributorModelMasterTask extends Gs2RestSessionTask<UpdateDistributorModelMasterResult> {
        private UpdateDistributorModelMasterRequest request;

        public UpdateDistributorModelMasterTask(
            UpdateDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateDistributorModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateDistributorModelMasterResult parse(JsonNode data) {
            return UpdateDistributorModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/distributor/{distributorName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{distributorName}", this.request.getDistributorName() == null || this.request.getDistributorName().length() == 0 ? "null" : String.valueOf(this.request.getDistributorName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("inboxNamespaceId", request.getInboxNamespaceId());
                    put("whiteListTargetIds", request.getWhiteListTargetIds() == null ? new ArrayList<String>() :
                        request.getWhiteListTargetIds().stream().map(item -> {
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

    public void updateDistributorModelMasterAsync(
            UpdateDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateDistributorModelMasterResult>> callback
    ) {
        UpdateDistributorModelMasterTask task = new UpdateDistributorModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateDistributorModelMasterResult updateDistributorModelMaster(
            UpdateDistributorModelMasterRequest request
    ) {
        final AsyncResult<UpdateDistributorModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateDistributorModelMasterAsync(
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

    class DeleteDistributorModelMasterTask extends Gs2RestSessionTask<DeleteDistributorModelMasterResult> {
        private DeleteDistributorModelMasterRequest request;

        public DeleteDistributorModelMasterTask(
            DeleteDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteDistributorModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteDistributorModelMasterResult parse(JsonNode data) {
            return DeleteDistributorModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/distributor/{distributorName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{distributorName}", this.request.getDistributorName() == null || this.request.getDistributorName().length() == 0 ? "null" : String.valueOf(this.request.getDistributorName()));

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

    public void deleteDistributorModelMasterAsync(
            DeleteDistributorModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteDistributorModelMasterResult>> callback
    ) {
        DeleteDistributorModelMasterTask task = new DeleteDistributorModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteDistributorModelMasterResult deleteDistributorModelMaster(
            DeleteDistributorModelMasterRequest request
    ) {
        final AsyncResult<DeleteDistributorModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteDistributorModelMasterAsync(
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

    class DescribeDistributorModelsTask extends Gs2RestSessionTask<DescribeDistributorModelsResult> {
        private DescribeDistributorModelsRequest request;

        public DescribeDistributorModelsTask(
            DescribeDistributorModelsRequest request,
            AsyncAction<AsyncResult<DescribeDistributorModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeDistributorModelsResult parse(JsonNode data) {
            return DescribeDistributorModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distributor";

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

    public void describeDistributorModelsAsync(
            DescribeDistributorModelsRequest request,
            AsyncAction<AsyncResult<DescribeDistributorModelsResult>> callback
    ) {
        DescribeDistributorModelsTask task = new DescribeDistributorModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeDistributorModelsResult describeDistributorModels(
            DescribeDistributorModelsRequest request
    ) {
        final AsyncResult<DescribeDistributorModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDistributorModelsAsync(
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

    class GetDistributorModelTask extends Gs2RestSessionTask<GetDistributorModelResult> {
        private GetDistributorModelRequest request;

        public GetDistributorModelTask(
            GetDistributorModelRequest request,
            AsyncAction<AsyncResult<GetDistributorModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDistributorModelResult parse(JsonNode data) {
            return GetDistributorModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distributor/{distributorName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{distributorName}", this.request.getDistributorName() == null || this.request.getDistributorName().length() == 0 ? "null" : String.valueOf(this.request.getDistributorName()));

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

    public void getDistributorModelAsync(
            GetDistributorModelRequest request,
            AsyncAction<AsyncResult<GetDistributorModelResult>> callback
    ) {
        GetDistributorModelTask task = new GetDistributorModelTask(request, callback);
        session.execute(task);
    }

    public GetDistributorModelResult getDistributorModel(
            GetDistributorModelRequest request
    ) {
        final AsyncResult<GetDistributorModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDistributorModelAsync(
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
                .replace("{service}", "distributor")
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

    class GetCurrentDistributorMasterTask extends Gs2RestSessionTask<GetCurrentDistributorMasterResult> {
        private GetCurrentDistributorMasterRequest request;

        public GetCurrentDistributorMasterTask(
            GetCurrentDistributorMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentDistributorMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentDistributorMasterResult parse(JsonNode data) {
            return GetCurrentDistributorMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
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

    public void getCurrentDistributorMasterAsync(
            GetCurrentDistributorMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentDistributorMasterResult>> callback
    ) {
        GetCurrentDistributorMasterTask task = new GetCurrentDistributorMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentDistributorMasterResult getCurrentDistributorMaster(
            GetCurrentDistributorMasterRequest request
    ) {
        final AsyncResult<GetCurrentDistributorMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentDistributorMasterAsync(
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

    class UpdateCurrentDistributorMasterTask extends Gs2RestSessionTask<UpdateCurrentDistributorMasterResult> {
        private UpdateCurrentDistributorMasterRequest request;

        public UpdateCurrentDistributorMasterTask(
            UpdateCurrentDistributorMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentDistributorMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentDistributorMasterResult parse(JsonNode data) {
            return UpdateCurrentDistributorMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
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

    public void updateCurrentDistributorMasterAsync(
            UpdateCurrentDistributorMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentDistributorMasterResult>> callback
    ) {
        UpdateCurrentDistributorMasterTask task = new UpdateCurrentDistributorMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentDistributorMasterResult updateCurrentDistributorMaster(
            UpdateCurrentDistributorMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentDistributorMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentDistributorMasterAsync(
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

    class UpdateCurrentDistributorMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentDistributorMasterFromGitHubResult> {
        private UpdateCurrentDistributorMasterFromGitHubRequest request;

        public UpdateCurrentDistributorMasterFromGitHubTask(
            UpdateCurrentDistributorMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentDistributorMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentDistributorMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentDistributorMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
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

    public void updateCurrentDistributorMasterFromGitHubAsync(
            UpdateCurrentDistributorMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentDistributorMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentDistributorMasterFromGitHubTask task = new UpdateCurrentDistributorMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentDistributorMasterFromGitHubResult updateCurrentDistributorMasterFromGitHub(
            UpdateCurrentDistributorMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentDistributorMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentDistributorMasterFromGitHubAsync(
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

    class DistributeTask extends Gs2RestSessionTask<DistributeResult> {
        private DistributeRequest request;

        public DistributeTask(
            DistributeRequest request,
            AsyncAction<AsyncResult<DistributeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DistributeResult parse(JsonNode data) {
            return DistributeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distribute/{distributorName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{distributorName}", this.request.getDistributorName() == null || this.request.getDistributorName().length() == 0 ? "null" : String.valueOf(this.request.getDistributorName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("distributeResource", request.getDistributeResource() != null ? request.getDistributeResource().toJson() : null);
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void distributeAsync(
            DistributeRequest request,
            AsyncAction<AsyncResult<DistributeResult>> callback
    ) {
        DistributeTask task = new DistributeTask(request, callback);
        session.execute(task);
    }

    public DistributeResult distribute(
            DistributeRequest request
    ) {
        final AsyncResult<DistributeResult>[] resultAsyncResult = new AsyncResult[]{null};
        distributeAsync(
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

    class DistributeWithoutOverflowProcessTask extends Gs2RestSessionTask<DistributeWithoutOverflowProcessResult> {
        private DistributeWithoutOverflowProcessRequest request;

        public DistributeWithoutOverflowProcessTask(
            DistributeWithoutOverflowProcessRequest request,
            AsyncAction<AsyncResult<DistributeWithoutOverflowProcessResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DistributeWithoutOverflowProcessResult parse(JsonNode data) {
            return DistributeWithoutOverflowProcessResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/distribute";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("distributeResource", request.getDistributeResource() != null ? request.getDistributeResource().toJson() : null);
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void distributeWithoutOverflowProcessAsync(
            DistributeWithoutOverflowProcessRequest request,
            AsyncAction<AsyncResult<DistributeWithoutOverflowProcessResult>> callback
    ) {
        DistributeWithoutOverflowProcessTask task = new DistributeWithoutOverflowProcessTask(request, callback);
        session.execute(task);
    }

    public DistributeWithoutOverflowProcessResult distributeWithoutOverflowProcess(
            DistributeWithoutOverflowProcessRequest request
    ) {
        final AsyncResult<DistributeWithoutOverflowProcessResult>[] resultAsyncResult = new AsyncResult[]{null};
        distributeWithoutOverflowProcessAsync(
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

    class RunVerifyTaskTask extends Gs2RestSessionTask<RunVerifyTaskResult> {
        private RunVerifyTaskRequest request;

        public RunVerifyTaskTask(
            RunVerifyTaskRequest request,
            AsyncAction<AsyncResult<RunVerifyTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunVerifyTaskResult parse(JsonNode data) {
            return RunVerifyTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distribute/stamp/verifyTask/run";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyTask", request.getVerifyTask());
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

    public void runVerifyTaskAsync(
            RunVerifyTaskRequest request,
            AsyncAction<AsyncResult<RunVerifyTaskResult>> callback
    ) {
        RunVerifyTaskTask task = new RunVerifyTaskTask(request, callback);
        session.execute(task);
    }

    public RunVerifyTaskResult runVerifyTask(
            RunVerifyTaskRequest request
    ) {
        final AsyncResult<RunVerifyTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        runVerifyTaskAsync(
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

    class RunStampTaskTask extends Gs2RestSessionTask<RunStampTaskResult> {
        private RunStampTaskRequest request;

        public RunStampTaskTask(
            RunStampTaskRequest request,
            AsyncAction<AsyncResult<RunStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunStampTaskResult parse(JsonNode data) {
            return RunStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distribute/stamp/task/run";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
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

    public void runStampTaskAsync(
            RunStampTaskRequest request,
            AsyncAction<AsyncResult<RunStampTaskResult>> callback
    ) {
        RunStampTaskTask task = new RunStampTaskTask(request, callback);
        session.execute(task);
    }

    public RunStampTaskResult runStampTask(
            RunStampTaskRequest request
    ) {
        final AsyncResult<RunStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        runStampTaskAsync(
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

    class RunStampSheetTask extends Gs2RestSessionTask<RunStampSheetResult> {
        private RunStampSheetRequest request;

        public RunStampSheetTask(
            RunStampSheetRequest request,
            AsyncAction<AsyncResult<RunStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunStampSheetResult parse(JsonNode data) {
            return RunStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distribute/stamp/sheet/run";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    public void runStampSheetAsync(
            RunStampSheetRequest request,
            AsyncAction<AsyncResult<RunStampSheetResult>> callback
    ) {
        RunStampSheetTask task = new RunStampSheetTask(request, callback);
        session.execute(task);
    }

    public RunStampSheetResult runStampSheet(
            RunStampSheetRequest request
    ) {
        final AsyncResult<RunStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        runStampSheetAsync(
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

    class RunStampSheetExpressTask extends Gs2RestSessionTask<RunStampSheetExpressResult> {
        private RunStampSheetExpressRequest request;

        public RunStampSheetExpressTask(
            RunStampSheetExpressRequest request,
            AsyncAction<AsyncResult<RunStampSheetExpressResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunStampSheetExpressResult parse(JsonNode data) {
            return RunStampSheetExpressResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/distribute/stamp/run";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    public void runStampSheetExpressAsync(
            RunStampSheetExpressRequest request,
            AsyncAction<AsyncResult<RunStampSheetExpressResult>> callback
    ) {
        RunStampSheetExpressTask task = new RunStampSheetExpressTask(request, callback);
        session.execute(task);
    }

    public RunStampSheetExpressResult runStampSheetExpress(
            RunStampSheetExpressRequest request
    ) {
        final AsyncResult<RunStampSheetExpressResult>[] resultAsyncResult = new AsyncResult[]{null};
        runStampSheetExpressAsync(
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

    class RunVerifyTaskWithoutNamespaceTask extends Gs2RestSessionTask<RunVerifyTaskWithoutNamespaceResult> {
        private RunVerifyTaskWithoutNamespaceRequest request;

        public RunVerifyTaskWithoutNamespaceTask(
            RunVerifyTaskWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunVerifyTaskWithoutNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunVerifyTaskWithoutNamespaceResult parse(JsonNode data) {
            return RunVerifyTaskWithoutNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/verifyTask/run";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyTask", request.getVerifyTask());
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

    public void runVerifyTaskWithoutNamespaceAsync(
            RunVerifyTaskWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunVerifyTaskWithoutNamespaceResult>> callback
    ) {
        RunVerifyTaskWithoutNamespaceTask task = new RunVerifyTaskWithoutNamespaceTask(request, callback);
        session.execute(task);
    }

    public RunVerifyTaskWithoutNamespaceResult runVerifyTaskWithoutNamespace(
            RunVerifyTaskWithoutNamespaceRequest request
    ) {
        final AsyncResult<RunVerifyTaskWithoutNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        runVerifyTaskWithoutNamespaceAsync(
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

    class RunStampTaskWithoutNamespaceTask extends Gs2RestSessionTask<RunStampTaskWithoutNamespaceResult> {
        private RunStampTaskWithoutNamespaceRequest request;

        public RunStampTaskWithoutNamespaceTask(
            RunStampTaskWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunStampTaskWithoutNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunStampTaskWithoutNamespaceResult parse(JsonNode data) {
            return RunStampTaskWithoutNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/task/run";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
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

    public void runStampTaskWithoutNamespaceAsync(
            RunStampTaskWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunStampTaskWithoutNamespaceResult>> callback
    ) {
        RunStampTaskWithoutNamespaceTask task = new RunStampTaskWithoutNamespaceTask(request, callback);
        session.execute(task);
    }

    public RunStampTaskWithoutNamespaceResult runStampTaskWithoutNamespace(
            RunStampTaskWithoutNamespaceRequest request
    ) {
        final AsyncResult<RunStampTaskWithoutNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        runStampTaskWithoutNamespaceAsync(
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

    class RunStampSheetWithoutNamespaceTask extends Gs2RestSessionTask<RunStampSheetWithoutNamespaceResult> {
        private RunStampSheetWithoutNamespaceRequest request;

        public RunStampSheetWithoutNamespaceTask(
            RunStampSheetWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunStampSheetWithoutNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunStampSheetWithoutNamespaceResult parse(JsonNode data) {
            return RunStampSheetWithoutNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/sheet/run";

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

    public void runStampSheetWithoutNamespaceAsync(
            RunStampSheetWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunStampSheetWithoutNamespaceResult>> callback
    ) {
        RunStampSheetWithoutNamespaceTask task = new RunStampSheetWithoutNamespaceTask(request, callback);
        session.execute(task);
    }

    public RunStampSheetWithoutNamespaceResult runStampSheetWithoutNamespace(
            RunStampSheetWithoutNamespaceRequest request
    ) {
        final AsyncResult<RunStampSheetWithoutNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        runStampSheetWithoutNamespaceAsync(
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

    class RunStampSheetExpressWithoutNamespaceTask extends Gs2RestSessionTask<RunStampSheetExpressWithoutNamespaceResult> {
        private RunStampSheetExpressWithoutNamespaceRequest request;

        public RunStampSheetExpressWithoutNamespaceTask(
            RunStampSheetExpressWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunStampSheetExpressWithoutNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RunStampSheetExpressWithoutNamespaceResult parse(JsonNode data) {
            return RunStampSheetExpressWithoutNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/run";

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

    public void runStampSheetExpressWithoutNamespaceAsync(
            RunStampSheetExpressWithoutNamespaceRequest request,
            AsyncAction<AsyncResult<RunStampSheetExpressWithoutNamespaceResult>> callback
    ) {
        RunStampSheetExpressWithoutNamespaceTask task = new RunStampSheetExpressWithoutNamespaceTask(request, callback);
        session.execute(task);
    }

    public RunStampSheetExpressWithoutNamespaceResult runStampSheetExpressWithoutNamespace(
            RunStampSheetExpressWithoutNamespaceRequest request
    ) {
        final AsyncResult<RunStampSheetExpressWithoutNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        runStampSheetExpressWithoutNamespaceAsync(
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

    class SetTransactionDefaultConfigTask extends Gs2RestSessionTask<SetTransactionDefaultConfigResult> {
        private SetTransactionDefaultConfigRequest request;

        public SetTransactionDefaultConfigTask(
            SetTransactionDefaultConfigRequest request,
            AsyncAction<AsyncResult<SetTransactionDefaultConfigResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetTransactionDefaultConfigResult parse(JsonNode data) {
            return SetTransactionDefaultConfigResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/transaction/user/me/config";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? new ArrayList<Config>() :
                        request.getConfig().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setTransactionDefaultConfigAsync(
            SetTransactionDefaultConfigRequest request,
            AsyncAction<AsyncResult<SetTransactionDefaultConfigResult>> callback
    ) {
        SetTransactionDefaultConfigTask task = new SetTransactionDefaultConfigTask(request, callback);
        session.execute(task);
    }

    public SetTransactionDefaultConfigResult setTransactionDefaultConfig(
            SetTransactionDefaultConfigRequest request
    ) {
        final AsyncResult<SetTransactionDefaultConfigResult>[] resultAsyncResult = new AsyncResult[]{null};
        setTransactionDefaultConfigAsync(
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

    class SetTransactionDefaultConfigByUserIdTask extends Gs2RestSessionTask<SetTransactionDefaultConfigByUserIdResult> {
        private SetTransactionDefaultConfigByUserIdRequest request;

        public SetTransactionDefaultConfigByUserIdTask(
            SetTransactionDefaultConfigByUserIdRequest request,
            AsyncAction<AsyncResult<SetTransactionDefaultConfigByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetTransactionDefaultConfigByUserIdResult parse(JsonNode data) {
            return SetTransactionDefaultConfigByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/transaction/user/{userId}/config";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? new ArrayList<Config>() :
                        request.getConfig().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void setTransactionDefaultConfigByUserIdAsync(
            SetTransactionDefaultConfigByUserIdRequest request,
            AsyncAction<AsyncResult<SetTransactionDefaultConfigByUserIdResult>> callback
    ) {
        SetTransactionDefaultConfigByUserIdTask task = new SetTransactionDefaultConfigByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetTransactionDefaultConfigByUserIdResult setTransactionDefaultConfigByUserId(
            SetTransactionDefaultConfigByUserIdRequest request
    ) {
        final AsyncResult<SetTransactionDefaultConfigByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setTransactionDefaultConfigByUserIdAsync(
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

    class FreezeMasterDataTask extends Gs2RestSessionTask<FreezeMasterDataResult> {
        private FreezeMasterDataRequest request;

        public FreezeMasterDataTask(
            FreezeMasterDataRequest request,
            AsyncAction<AsyncResult<FreezeMasterDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FreezeMasterDataResult parse(JsonNode data) {
            return FreezeMasterDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/masterdata/freeze";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void freezeMasterDataAsync(
            FreezeMasterDataRequest request,
            AsyncAction<AsyncResult<FreezeMasterDataResult>> callback
    ) {
        FreezeMasterDataTask task = new FreezeMasterDataTask(request, callback);
        session.execute(task);
    }

    public FreezeMasterDataResult freezeMasterData(
            FreezeMasterDataRequest request
    ) {
        final AsyncResult<FreezeMasterDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        freezeMasterDataAsync(
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

    class FreezeMasterDataByUserIdTask extends Gs2RestSessionTask<FreezeMasterDataByUserIdResult> {
        private FreezeMasterDataByUserIdRequest request;

        public FreezeMasterDataByUserIdTask(
            FreezeMasterDataByUserIdRequest request,
            AsyncAction<AsyncResult<FreezeMasterDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FreezeMasterDataByUserIdResult parse(JsonNode data) {
            return FreezeMasterDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/masterdata/freeze";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void freezeMasterDataByUserIdAsync(
            FreezeMasterDataByUserIdRequest request,
            AsyncAction<AsyncResult<FreezeMasterDataByUserIdResult>> callback
    ) {
        FreezeMasterDataByUserIdTask task = new FreezeMasterDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public FreezeMasterDataByUserIdResult freezeMasterDataByUserId(
            FreezeMasterDataByUserIdRequest request
    ) {
        final AsyncResult<FreezeMasterDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        freezeMasterDataByUserIdAsync(
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

    class IfExpressionByUserIdTask extends Gs2RestSessionTask<IfExpressionByUserIdResult> {
        private IfExpressionByUserIdRequest request;

        public IfExpressionByUserIdTask(
            IfExpressionByUserIdRequest request,
            AsyncAction<AsyncResult<IfExpressionByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IfExpressionByUserIdResult parse(JsonNode data) {
            return IfExpressionByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/expression/if";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("condition", request.getCondition() != null ? request.getCondition().toJson() : null);
                    put("trueActions", request.getTrueActions() == null ? new ArrayList<ConsumeAction>() :
                        request.getTrueActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("falseActions", request.getFalseActions() == null ? new ArrayList<ConsumeAction>() :
                        request.getFalseActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("multiplyValueSpecifyingQuantity", request.getMultiplyValueSpecifyingQuantity());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void ifExpressionByUserIdAsync(
            IfExpressionByUserIdRequest request,
            AsyncAction<AsyncResult<IfExpressionByUserIdResult>> callback
    ) {
        IfExpressionByUserIdTask task = new IfExpressionByUserIdTask(request, callback);
        session.execute(task);
    }

    public IfExpressionByUserIdResult ifExpressionByUserId(
            IfExpressionByUserIdRequest request
    ) {
        final AsyncResult<IfExpressionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        ifExpressionByUserIdAsync(
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

    class AndExpressionByUserIdTask extends Gs2RestSessionTask<AndExpressionByUserIdResult> {
        private AndExpressionByUserIdRequest request;

        public AndExpressionByUserIdTask(
            AndExpressionByUserIdRequest request,
            AsyncAction<AsyncResult<AndExpressionByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AndExpressionByUserIdResult parse(JsonNode data) {
            return AndExpressionByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/expression/and";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("actions", request.getActions() == null ? new ArrayList<VerifyAction>() :
                        request.getActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void andExpressionByUserIdAsync(
            AndExpressionByUserIdRequest request,
            AsyncAction<AsyncResult<AndExpressionByUserIdResult>> callback
    ) {
        AndExpressionByUserIdTask task = new AndExpressionByUserIdTask(request, callback);
        session.execute(task);
    }

    public AndExpressionByUserIdResult andExpressionByUserId(
            AndExpressionByUserIdRequest request
    ) {
        final AsyncResult<AndExpressionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        andExpressionByUserIdAsync(
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

    class OrExpressionByUserIdTask extends Gs2RestSessionTask<OrExpressionByUserIdResult> {
        private OrExpressionByUserIdRequest request;

        public OrExpressionByUserIdTask(
            OrExpressionByUserIdRequest request,
            AsyncAction<AsyncResult<OrExpressionByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public OrExpressionByUserIdResult parse(JsonNode data) {
            return OrExpressionByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/expression/or";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("actions", request.getActions() == null ? new ArrayList<VerifyAction>() :
                        request.getActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void orExpressionByUserIdAsync(
            OrExpressionByUserIdRequest request,
            AsyncAction<AsyncResult<OrExpressionByUserIdResult>> callback
    ) {
        OrExpressionByUserIdTask task = new OrExpressionByUserIdTask(request, callback);
        session.execute(task);
    }

    public OrExpressionByUserIdResult orExpressionByUserId(
            OrExpressionByUserIdRequest request
    ) {
        final AsyncResult<OrExpressionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        orExpressionByUserIdAsync(
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

    class IfExpressionByStampTaskTask extends Gs2RestSessionTask<IfExpressionByStampTaskResult> {
        private IfExpressionByStampTaskRequest request;

        public IfExpressionByStampTaskTask(
            IfExpressionByStampTaskRequest request,
            AsyncAction<AsyncResult<IfExpressionByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IfExpressionByStampTaskResult parse(JsonNode data) {
            return IfExpressionByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/expression/if";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
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

    public void ifExpressionByStampTaskAsync(
            IfExpressionByStampTaskRequest request,
            AsyncAction<AsyncResult<IfExpressionByStampTaskResult>> callback
    ) {
        IfExpressionByStampTaskTask task = new IfExpressionByStampTaskTask(request, callback);
        session.execute(task);
    }

    public IfExpressionByStampTaskResult ifExpressionByStampTask(
            IfExpressionByStampTaskRequest request
    ) {
        final AsyncResult<IfExpressionByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        ifExpressionByStampTaskAsync(
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

    class AndExpressionByStampTaskTask extends Gs2RestSessionTask<AndExpressionByStampTaskResult> {
        private AndExpressionByStampTaskRequest request;

        public AndExpressionByStampTaskTask(
            AndExpressionByStampTaskRequest request,
            AsyncAction<AsyncResult<AndExpressionByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AndExpressionByStampTaskResult parse(JsonNode data) {
            return AndExpressionByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/expression/and";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
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

    public void andExpressionByStampTaskAsync(
            AndExpressionByStampTaskRequest request,
            AsyncAction<AsyncResult<AndExpressionByStampTaskResult>> callback
    ) {
        AndExpressionByStampTaskTask task = new AndExpressionByStampTaskTask(request, callback);
        session.execute(task);
    }

    public AndExpressionByStampTaskResult andExpressionByStampTask(
            AndExpressionByStampTaskRequest request
    ) {
        final AsyncResult<AndExpressionByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        andExpressionByStampTaskAsync(
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

    class OrExpressionByStampTaskTask extends Gs2RestSessionTask<OrExpressionByStampTaskResult> {
        private OrExpressionByStampTaskRequest request;

        public OrExpressionByStampTaskTask(
            OrExpressionByStampTaskRequest request,
            AsyncAction<AsyncResult<OrExpressionByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public OrExpressionByStampTaskResult parse(JsonNode data) {
            return OrExpressionByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/expression/or";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
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

    public void orExpressionByStampTaskAsync(
            OrExpressionByStampTaskRequest request,
            AsyncAction<AsyncResult<OrExpressionByStampTaskResult>> callback
    ) {
        OrExpressionByStampTaskTask task = new OrExpressionByStampTaskTask(request, callback);
        session.execute(task);
    }

    public OrExpressionByStampTaskResult orExpressionByStampTask(
            OrExpressionByStampTaskRequest request
    ) {
        final AsyncResult<OrExpressionByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        orExpressionByStampTaskAsync(
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

    class GetStampSheetResultTask extends Gs2RestSessionTask<GetStampSheetResultResult> {
        private GetStampSheetResultRequest request;

        public GetStampSheetResultTask(
            GetStampSheetResultRequest request,
            AsyncAction<AsyncResult<GetStampSheetResultResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStampSheetResultResult parse(JsonNode data) {
            return GetStampSheetResultResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stampSheet/{transactionId}/result";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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

    public void getStampSheetResultAsync(
            GetStampSheetResultRequest request,
            AsyncAction<AsyncResult<GetStampSheetResultResult>> callback
    ) {
        GetStampSheetResultTask task = new GetStampSheetResultTask(request, callback);
        session.execute(task);
    }

    public GetStampSheetResultResult getStampSheetResult(
            GetStampSheetResultRequest request
    ) {
        final AsyncResult<GetStampSheetResultResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStampSheetResultAsync(
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

    class GetStampSheetResultByUserIdTask extends Gs2RestSessionTask<GetStampSheetResultByUserIdResult> {
        private GetStampSheetResultByUserIdRequest request;

        public GetStampSheetResultByUserIdTask(
            GetStampSheetResultByUserIdRequest request,
            AsyncAction<AsyncResult<GetStampSheetResultByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStampSheetResultByUserIdResult parse(JsonNode data) {
            return GetStampSheetResultByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "distributor")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stampSheet/{transactionId}/result";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getStampSheetResultByUserIdAsync(
            GetStampSheetResultByUserIdRequest request,
            AsyncAction<AsyncResult<GetStampSheetResultByUserIdResult>> callback
    ) {
        GetStampSheetResultByUserIdTask task = new GetStampSheetResultByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetStampSheetResultByUserIdResult getStampSheetResultByUserId(
            GetStampSheetResultByUserIdRequest request
    ) {
        final AsyncResult<GetStampSheetResultByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStampSheetResultByUserIdAsync(
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