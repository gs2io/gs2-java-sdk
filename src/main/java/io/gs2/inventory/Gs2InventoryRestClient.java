
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

package io.gs2.inventory;

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
import io.gs2.inventory.request.*;
import io.gs2.inventory.result.*;
import io.gs2.inventory.model.*;public class Gs2InventoryRestClient extends AbstractGs2Client<Gs2InventoryRestClient> {

	public Gs2InventoryRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "inventory")
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("acquireScript", request.getAcquireScript() != null ? request.getAcquireScript().toJson() : null);
                    put("overflowScript", request.getOverflowScript() != null ? request.getOverflowScript().toJson() : null);
                    put("consumeScript", request.getConsumeScript() != null ? request.getConsumeScript().toJson() : null);
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
                .replace("{service}", "inventory")
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
                .replace("{service}", "inventory")
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("acquireScript", request.getAcquireScript() != null ? request.getAcquireScript().toJson() : null);
                    put("overflowScript", request.getOverflowScript() != null ? request.getOverflowScript().toJson() : null);
                    put("consumeScript", request.getConsumeScript() != null ? request.getConsumeScript().toJson() : null);
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
                .replace("{service}", "inventory")
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

    class DescribeInventoryModelMastersTask extends Gs2RestSessionTask<DescribeInventoryModelMastersResult> {
        private DescribeInventoryModelMastersRequest request;

        public DescribeInventoryModelMastersTask(
            DescribeInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeInventoryModelMastersResult parse(JsonNode data) {
            return DescribeInventoryModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory";

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

    public void describeInventoryModelMastersAsync(
            DescribeInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelMastersResult>> callback
    ) {
        DescribeInventoryModelMastersTask task = new DescribeInventoryModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeInventoryModelMastersResult describeInventoryModelMasters(
            DescribeInventoryModelMastersRequest request
    ) {
        final AsyncResult<DescribeInventoryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoryModelMastersAsync(
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

    class CreateInventoryModelMasterTask extends Gs2RestSessionTask<CreateInventoryModelMasterResult> {
        private CreateInventoryModelMasterRequest request;

        public CreateInventoryModelMasterTask(
            CreateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateInventoryModelMasterResult parse(JsonNode data) {
            return CreateInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("initialCapacity", request.getInitialCapacity());
                    put("maxCapacity", request.getMaxCapacity());
                    put("protectReferencedItem", request.getProtectReferencedItem());
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

    public void createInventoryModelMasterAsync(
            CreateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateInventoryModelMasterResult>> callback
    ) {
        CreateInventoryModelMasterTask task = new CreateInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateInventoryModelMasterResult createInventoryModelMaster(
            CreateInventoryModelMasterRequest request
    ) {
        final AsyncResult<CreateInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createInventoryModelMasterAsync(
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

    class GetInventoryModelMasterTask extends Gs2RestSessionTask<GetInventoryModelMasterResult> {
        private GetInventoryModelMasterRequest request;

        public GetInventoryModelMasterTask(
            GetInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetInventoryModelMasterResult parse(JsonNode data) {
            return GetInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getInventoryModelMasterAsync(
            GetInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetInventoryModelMasterResult>> callback
    ) {
        GetInventoryModelMasterTask task = new GetInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetInventoryModelMasterResult getInventoryModelMaster(
            GetInventoryModelMasterRequest request
    ) {
        final AsyncResult<GetInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryModelMasterAsync(
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

    class UpdateInventoryModelMasterTask extends Gs2RestSessionTask<UpdateInventoryModelMasterResult> {
        private UpdateInventoryModelMasterRequest request;

        public UpdateInventoryModelMasterTask(
            UpdateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateInventoryModelMasterResult parse(JsonNode data) {
            return UpdateInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("initialCapacity", request.getInitialCapacity());
                    put("maxCapacity", request.getMaxCapacity());
                    put("protectReferencedItem", request.getProtectReferencedItem());
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

    public void updateInventoryModelMasterAsync(
            UpdateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateInventoryModelMasterResult>> callback
    ) {
        UpdateInventoryModelMasterTask task = new UpdateInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateInventoryModelMasterResult updateInventoryModelMaster(
            UpdateInventoryModelMasterRequest request
    ) {
        final AsyncResult<UpdateInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateInventoryModelMasterAsync(
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

    class DeleteInventoryModelMasterTask extends Gs2RestSessionTask<DeleteInventoryModelMasterResult> {
        private DeleteInventoryModelMasterRequest request;

        public DeleteInventoryModelMasterTask(
            DeleteInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteInventoryModelMasterResult parse(JsonNode data) {
            return DeleteInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void deleteInventoryModelMasterAsync(
            DeleteInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteInventoryModelMasterResult>> callback
    ) {
        DeleteInventoryModelMasterTask task = new DeleteInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteInventoryModelMasterResult deleteInventoryModelMaster(
            DeleteInventoryModelMasterRequest request
    ) {
        final AsyncResult<DeleteInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteInventoryModelMasterAsync(
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

    class DescribeInventoryModelsTask extends Gs2RestSessionTask<DescribeInventoryModelsResult> {
        private DescribeInventoryModelsRequest request;

        public DescribeInventoryModelsTask(
            DescribeInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeInventoryModelsResult parse(JsonNode data) {
            return DescribeInventoryModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory";

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

    public void describeInventoryModelsAsync(
            DescribeInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelsResult>> callback
    ) {
        DescribeInventoryModelsTask task = new DescribeInventoryModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeInventoryModelsResult describeInventoryModels(
            DescribeInventoryModelsRequest request
    ) {
        final AsyncResult<DescribeInventoryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoryModelsAsync(
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

    class GetInventoryModelTask extends Gs2RestSessionTask<GetInventoryModelResult> {
        private GetInventoryModelRequest request;

        public GetInventoryModelTask(
            GetInventoryModelRequest request,
            AsyncAction<AsyncResult<GetInventoryModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetInventoryModelResult parse(JsonNode data) {
            return GetInventoryModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getInventoryModelAsync(
            GetInventoryModelRequest request,
            AsyncAction<AsyncResult<GetInventoryModelResult>> callback
    ) {
        GetInventoryModelTask task = new GetInventoryModelTask(request, callback);
        session.execute(task);
    }

    public GetInventoryModelResult getInventoryModel(
            GetInventoryModelRequest request
    ) {
        final AsyncResult<GetInventoryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryModelAsync(
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

    class DescribeItemModelMastersTask extends Gs2RestSessionTask<DescribeItemModelMastersResult> {
        private DescribeItemModelMastersRequest request;

        public DescribeItemModelMastersTask(
            DescribeItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeItemModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeItemModelMastersResult parse(JsonNode data) {
            return DescribeItemModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeItemModelMastersAsync(
            DescribeItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeItemModelMastersResult>> callback
    ) {
        DescribeItemModelMastersTask task = new DescribeItemModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeItemModelMastersResult describeItemModelMasters(
            DescribeItemModelMastersRequest request
    ) {
        final AsyncResult<DescribeItemModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemModelMastersAsync(
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

    class CreateItemModelMasterTask extends Gs2RestSessionTask<CreateItemModelMasterResult> {
        private CreateItemModelMasterRequest request;

        public CreateItemModelMasterTask(
            CreateItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateItemModelMasterResult parse(JsonNode data) {
            return CreateItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("stackingLimit", request.getStackingLimit());
                    put("allowMultipleStacks", request.getAllowMultipleStacks());
                    put("sortValue", request.getSortValue());
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

    public void createItemModelMasterAsync(
            CreateItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateItemModelMasterResult>> callback
    ) {
        CreateItemModelMasterTask task = new CreateItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateItemModelMasterResult createItemModelMaster(
            CreateItemModelMasterRequest request
    ) {
        final AsyncResult<CreateItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createItemModelMasterAsync(
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

    class GetItemModelMasterTask extends Gs2RestSessionTask<GetItemModelMasterResult> {
        private GetItemModelMasterRequest request;

        public GetItemModelMasterTask(
            GetItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetItemModelMasterResult parse(JsonNode data) {
            return GetItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getItemModelMasterAsync(
            GetItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetItemModelMasterResult>> callback
    ) {
        GetItemModelMasterTask task = new GetItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetItemModelMasterResult getItemModelMaster(
            GetItemModelMasterRequest request
    ) {
        final AsyncResult<GetItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemModelMasterAsync(
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

    class UpdateItemModelMasterTask extends Gs2RestSessionTask<UpdateItemModelMasterResult> {
        private UpdateItemModelMasterRequest request;

        public UpdateItemModelMasterTask(
            UpdateItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateItemModelMasterResult parse(JsonNode data) {
            return UpdateItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("stackingLimit", request.getStackingLimit());
                    put("allowMultipleStacks", request.getAllowMultipleStacks());
                    put("sortValue", request.getSortValue());
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

    public void updateItemModelMasterAsync(
            UpdateItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateItemModelMasterResult>> callback
    ) {
        UpdateItemModelMasterTask task = new UpdateItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateItemModelMasterResult updateItemModelMaster(
            UpdateItemModelMasterRequest request
    ) {
        final AsyncResult<UpdateItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateItemModelMasterAsync(
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

    class DeleteItemModelMasterTask extends Gs2RestSessionTask<DeleteItemModelMasterResult> {
        private DeleteItemModelMasterRequest request;

        public DeleteItemModelMasterTask(
            DeleteItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteItemModelMasterResult parse(JsonNode data) {
            return DeleteItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void deleteItemModelMasterAsync(
            DeleteItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteItemModelMasterResult>> callback
    ) {
        DeleteItemModelMasterTask task = new DeleteItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteItemModelMasterResult deleteItemModelMaster(
            DeleteItemModelMasterRequest request
    ) {
        final AsyncResult<DeleteItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteItemModelMasterAsync(
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

    class DescribeItemModelsTask extends Gs2RestSessionTask<DescribeItemModelsResult> {
        private DescribeItemModelsRequest request;

        public DescribeItemModelsTask(
            DescribeItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeItemModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeItemModelsResult parse(JsonNode data) {
            return DescribeItemModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeItemModelsAsync(
            DescribeItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeItemModelsResult>> callback
    ) {
        DescribeItemModelsTask task = new DescribeItemModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeItemModelsResult describeItemModels(
            DescribeItemModelsRequest request
    ) {
        final AsyncResult<DescribeItemModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemModelsAsync(
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

    class GetItemModelTask extends Gs2RestSessionTask<GetItemModelResult> {
        private GetItemModelRequest request;

        public GetItemModelTask(
            GetItemModelRequest request,
            AsyncAction<AsyncResult<GetItemModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetItemModelResult parse(JsonNode data) {
            return GetItemModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getItemModelAsync(
            GetItemModelRequest request,
            AsyncAction<AsyncResult<GetItemModelResult>> callback
    ) {
        GetItemModelTask task = new GetItemModelTask(request, callback);
        session.execute(task);
    }

    public GetItemModelResult getItemModel(
            GetItemModelRequest request
    ) {
        final AsyncResult<GetItemModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemModelAsync(
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

    class DescribeSimpleInventoryModelMastersTask extends Gs2RestSessionTask<DescribeSimpleInventoryModelMastersResult> {
        private DescribeSimpleInventoryModelMastersRequest request;

        public DescribeSimpleInventoryModelMastersTask(
            DescribeSimpleInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSimpleInventoryModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSimpleInventoryModelMastersResult parse(JsonNode data) {
            return DescribeSimpleInventoryModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory";

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

    public void describeSimpleInventoryModelMastersAsync(
            DescribeSimpleInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSimpleInventoryModelMastersResult>> callback
    ) {
        DescribeSimpleInventoryModelMastersTask task = new DescribeSimpleInventoryModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeSimpleInventoryModelMastersResult describeSimpleInventoryModelMasters(
            DescribeSimpleInventoryModelMastersRequest request
    ) {
        final AsyncResult<DescribeSimpleInventoryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSimpleInventoryModelMastersAsync(
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

    class CreateSimpleInventoryModelMasterTask extends Gs2RestSessionTask<CreateSimpleInventoryModelMasterResult> {
        private CreateSimpleInventoryModelMasterRequest request;

        public CreateSimpleInventoryModelMasterTask(
            CreateSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSimpleInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSimpleInventoryModelMasterResult parse(JsonNode data) {
            return CreateSimpleInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory";

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

    public void createSimpleInventoryModelMasterAsync(
            CreateSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSimpleInventoryModelMasterResult>> callback
    ) {
        CreateSimpleInventoryModelMasterTask task = new CreateSimpleInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateSimpleInventoryModelMasterResult createSimpleInventoryModelMaster(
            CreateSimpleInventoryModelMasterRequest request
    ) {
        final AsyncResult<CreateSimpleInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSimpleInventoryModelMasterAsync(
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

    class GetSimpleInventoryModelMasterTask extends Gs2RestSessionTask<GetSimpleInventoryModelMasterResult> {
        private GetSimpleInventoryModelMasterRequest request;

        public GetSimpleInventoryModelMasterTask(
            GetSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetSimpleInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleInventoryModelMasterResult parse(JsonNode data) {
            return GetSimpleInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getSimpleInventoryModelMasterAsync(
            GetSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetSimpleInventoryModelMasterResult>> callback
    ) {
        GetSimpleInventoryModelMasterTask task = new GetSimpleInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetSimpleInventoryModelMasterResult getSimpleInventoryModelMaster(
            GetSimpleInventoryModelMasterRequest request
    ) {
        final AsyncResult<GetSimpleInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleInventoryModelMasterAsync(
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

    class UpdateSimpleInventoryModelMasterTask extends Gs2RestSessionTask<UpdateSimpleInventoryModelMasterResult> {
        private UpdateSimpleInventoryModelMasterRequest request;

        public UpdateSimpleInventoryModelMasterTask(
            UpdateSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSimpleInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSimpleInventoryModelMasterResult parse(JsonNode data) {
            return UpdateSimpleInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void updateSimpleInventoryModelMasterAsync(
            UpdateSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSimpleInventoryModelMasterResult>> callback
    ) {
        UpdateSimpleInventoryModelMasterTask task = new UpdateSimpleInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateSimpleInventoryModelMasterResult updateSimpleInventoryModelMaster(
            UpdateSimpleInventoryModelMasterRequest request
    ) {
        final AsyncResult<UpdateSimpleInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSimpleInventoryModelMasterAsync(
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

    class DeleteSimpleInventoryModelMasterTask extends Gs2RestSessionTask<DeleteSimpleInventoryModelMasterResult> {
        private DeleteSimpleInventoryModelMasterRequest request;

        public DeleteSimpleInventoryModelMasterTask(
            DeleteSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSimpleInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSimpleInventoryModelMasterResult parse(JsonNode data) {
            return DeleteSimpleInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void deleteSimpleInventoryModelMasterAsync(
            DeleteSimpleInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSimpleInventoryModelMasterResult>> callback
    ) {
        DeleteSimpleInventoryModelMasterTask task = new DeleteSimpleInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteSimpleInventoryModelMasterResult deleteSimpleInventoryModelMaster(
            DeleteSimpleInventoryModelMasterRequest request
    ) {
        final AsyncResult<DeleteSimpleInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSimpleInventoryModelMasterAsync(
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

    class DescribeSimpleInventoryModelsTask extends Gs2RestSessionTask<DescribeSimpleInventoryModelsResult> {
        private DescribeSimpleInventoryModelsRequest request;

        public DescribeSimpleInventoryModelsTask(
            DescribeSimpleInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeSimpleInventoryModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSimpleInventoryModelsResult parse(JsonNode data) {
            return DescribeSimpleInventoryModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/simple/inventory";

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

    public void describeSimpleInventoryModelsAsync(
            DescribeSimpleInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeSimpleInventoryModelsResult>> callback
    ) {
        DescribeSimpleInventoryModelsTask task = new DescribeSimpleInventoryModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeSimpleInventoryModelsResult describeSimpleInventoryModels(
            DescribeSimpleInventoryModelsRequest request
    ) {
        final AsyncResult<DescribeSimpleInventoryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSimpleInventoryModelsAsync(
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

    class GetSimpleInventoryModelTask extends Gs2RestSessionTask<GetSimpleInventoryModelResult> {
        private GetSimpleInventoryModelRequest request;

        public GetSimpleInventoryModelTask(
            GetSimpleInventoryModelRequest request,
            AsyncAction<AsyncResult<GetSimpleInventoryModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleInventoryModelResult parse(JsonNode data) {
            return GetSimpleInventoryModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/simple/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getSimpleInventoryModelAsync(
            GetSimpleInventoryModelRequest request,
            AsyncAction<AsyncResult<GetSimpleInventoryModelResult>> callback
    ) {
        GetSimpleInventoryModelTask task = new GetSimpleInventoryModelTask(request, callback);
        session.execute(task);
    }

    public GetSimpleInventoryModelResult getSimpleInventoryModel(
            GetSimpleInventoryModelRequest request
    ) {
        final AsyncResult<GetSimpleInventoryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleInventoryModelAsync(
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

    class DescribeSimpleItemModelMastersTask extends Gs2RestSessionTask<DescribeSimpleItemModelMastersResult> {
        private DescribeSimpleItemModelMastersRequest request;

        public DescribeSimpleItemModelMastersTask(
            DescribeSimpleItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSimpleItemModelMastersResult parse(JsonNode data) {
            return DescribeSimpleItemModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeSimpleItemModelMastersAsync(
            DescribeSimpleItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemModelMastersResult>> callback
    ) {
        DescribeSimpleItemModelMastersTask task = new DescribeSimpleItemModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeSimpleItemModelMastersResult describeSimpleItemModelMasters(
            DescribeSimpleItemModelMastersRequest request
    ) {
        final AsyncResult<DescribeSimpleItemModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSimpleItemModelMastersAsync(
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

    class CreateSimpleItemModelMasterTask extends Gs2RestSessionTask<CreateSimpleItemModelMasterResult> {
        private CreateSimpleItemModelMasterRequest request;

        public CreateSimpleItemModelMasterTask(
            CreateSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSimpleItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSimpleItemModelMasterResult parse(JsonNode data) {
            return CreateSimpleItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void createSimpleItemModelMasterAsync(
            CreateSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSimpleItemModelMasterResult>> callback
    ) {
        CreateSimpleItemModelMasterTask task = new CreateSimpleItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateSimpleItemModelMasterResult createSimpleItemModelMaster(
            CreateSimpleItemModelMasterRequest request
    ) {
        final AsyncResult<CreateSimpleItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSimpleItemModelMasterAsync(
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

    class GetSimpleItemModelMasterTask extends Gs2RestSessionTask<GetSimpleItemModelMasterResult> {
        private GetSimpleItemModelMasterRequest request;

        public GetSimpleItemModelMasterTask(
            GetSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetSimpleItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleItemModelMasterResult parse(JsonNode data) {
            return GetSimpleItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getSimpleItemModelMasterAsync(
            GetSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetSimpleItemModelMasterResult>> callback
    ) {
        GetSimpleItemModelMasterTask task = new GetSimpleItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetSimpleItemModelMasterResult getSimpleItemModelMaster(
            GetSimpleItemModelMasterRequest request
    ) {
        final AsyncResult<GetSimpleItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleItemModelMasterAsync(
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

    class UpdateSimpleItemModelMasterTask extends Gs2RestSessionTask<UpdateSimpleItemModelMasterResult> {
        private UpdateSimpleItemModelMasterRequest request;

        public UpdateSimpleItemModelMasterTask(
            UpdateSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSimpleItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSimpleItemModelMasterResult parse(JsonNode data) {
            return UpdateSimpleItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void updateSimpleItemModelMasterAsync(
            UpdateSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSimpleItemModelMasterResult>> callback
    ) {
        UpdateSimpleItemModelMasterTask task = new UpdateSimpleItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateSimpleItemModelMasterResult updateSimpleItemModelMaster(
            UpdateSimpleItemModelMasterRequest request
    ) {
        final AsyncResult<UpdateSimpleItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSimpleItemModelMasterAsync(
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

    class DeleteSimpleItemModelMasterTask extends Gs2RestSessionTask<DeleteSimpleItemModelMasterResult> {
        private DeleteSimpleItemModelMasterRequest request;

        public DeleteSimpleItemModelMasterTask(
            DeleteSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSimpleItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSimpleItemModelMasterResult parse(JsonNode data) {
            return DeleteSimpleItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/simple/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void deleteSimpleItemModelMasterAsync(
            DeleteSimpleItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSimpleItemModelMasterResult>> callback
    ) {
        DeleteSimpleItemModelMasterTask task = new DeleteSimpleItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteSimpleItemModelMasterResult deleteSimpleItemModelMaster(
            DeleteSimpleItemModelMasterRequest request
    ) {
        final AsyncResult<DeleteSimpleItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSimpleItemModelMasterAsync(
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

    class DescribeSimpleItemModelsTask extends Gs2RestSessionTask<DescribeSimpleItemModelsResult> {
        private DescribeSimpleItemModelsRequest request;

        public DescribeSimpleItemModelsTask(
            DescribeSimpleItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSimpleItemModelsResult parse(JsonNode data) {
            return DescribeSimpleItemModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/simple/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeSimpleItemModelsAsync(
            DescribeSimpleItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemModelsResult>> callback
    ) {
        DescribeSimpleItemModelsTask task = new DescribeSimpleItemModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeSimpleItemModelsResult describeSimpleItemModels(
            DescribeSimpleItemModelsRequest request
    ) {
        final AsyncResult<DescribeSimpleItemModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSimpleItemModelsAsync(
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

    class GetSimpleItemModelTask extends Gs2RestSessionTask<GetSimpleItemModelResult> {
        private GetSimpleItemModelRequest request;

        public GetSimpleItemModelTask(
            GetSimpleItemModelRequest request,
            AsyncAction<AsyncResult<GetSimpleItemModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleItemModelResult parse(JsonNode data) {
            return GetSimpleItemModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/simple/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getSimpleItemModelAsync(
            GetSimpleItemModelRequest request,
            AsyncAction<AsyncResult<GetSimpleItemModelResult>> callback
    ) {
        GetSimpleItemModelTask task = new GetSimpleItemModelTask(request, callback);
        session.execute(task);
    }

    public GetSimpleItemModelResult getSimpleItemModel(
            GetSimpleItemModelRequest request
    ) {
        final AsyncResult<GetSimpleItemModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleItemModelAsync(
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

    class DescribeBigInventoryModelMastersTask extends Gs2RestSessionTask<DescribeBigInventoryModelMastersResult> {
        private DescribeBigInventoryModelMastersRequest request;

        public DescribeBigInventoryModelMastersTask(
            DescribeBigInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBigInventoryModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBigInventoryModelMastersResult parse(JsonNode data) {
            return DescribeBigInventoryModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory";

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

    public void describeBigInventoryModelMastersAsync(
            DescribeBigInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBigInventoryModelMastersResult>> callback
    ) {
        DescribeBigInventoryModelMastersTask task = new DescribeBigInventoryModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeBigInventoryModelMastersResult describeBigInventoryModelMasters(
            DescribeBigInventoryModelMastersRequest request
    ) {
        final AsyncResult<DescribeBigInventoryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBigInventoryModelMastersAsync(
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

    class CreateBigInventoryModelMasterTask extends Gs2RestSessionTask<CreateBigInventoryModelMasterResult> {
        private CreateBigInventoryModelMasterRequest request;

        public CreateBigInventoryModelMasterTask(
            CreateBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBigInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateBigInventoryModelMasterResult parse(JsonNode data) {
            return CreateBigInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory";

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

    public void createBigInventoryModelMasterAsync(
            CreateBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBigInventoryModelMasterResult>> callback
    ) {
        CreateBigInventoryModelMasterTask task = new CreateBigInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateBigInventoryModelMasterResult createBigInventoryModelMaster(
            CreateBigInventoryModelMasterRequest request
    ) {
        final AsyncResult<CreateBigInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createBigInventoryModelMasterAsync(
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

    class GetBigInventoryModelMasterTask extends Gs2RestSessionTask<GetBigInventoryModelMasterResult> {
        private GetBigInventoryModelMasterRequest request;

        public GetBigInventoryModelMasterTask(
            GetBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetBigInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBigInventoryModelMasterResult parse(JsonNode data) {
            return GetBigInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getBigInventoryModelMasterAsync(
            GetBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetBigInventoryModelMasterResult>> callback
    ) {
        GetBigInventoryModelMasterTask task = new GetBigInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetBigInventoryModelMasterResult getBigInventoryModelMaster(
            GetBigInventoryModelMasterRequest request
    ) {
        final AsyncResult<GetBigInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBigInventoryModelMasterAsync(
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

    class UpdateBigInventoryModelMasterTask extends Gs2RestSessionTask<UpdateBigInventoryModelMasterResult> {
        private UpdateBigInventoryModelMasterRequest request;

        public UpdateBigInventoryModelMasterTask(
            UpdateBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBigInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateBigInventoryModelMasterResult parse(JsonNode data) {
            return UpdateBigInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void updateBigInventoryModelMasterAsync(
            UpdateBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBigInventoryModelMasterResult>> callback
    ) {
        UpdateBigInventoryModelMasterTask task = new UpdateBigInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateBigInventoryModelMasterResult updateBigInventoryModelMaster(
            UpdateBigInventoryModelMasterRequest request
    ) {
        final AsyncResult<UpdateBigInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateBigInventoryModelMasterAsync(
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

    class DeleteBigInventoryModelMasterTask extends Gs2RestSessionTask<DeleteBigInventoryModelMasterResult> {
        private DeleteBigInventoryModelMasterRequest request;

        public DeleteBigInventoryModelMasterTask(
            DeleteBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBigInventoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBigInventoryModelMasterResult parse(JsonNode data) {
            return DeleteBigInventoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void deleteBigInventoryModelMasterAsync(
            DeleteBigInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBigInventoryModelMasterResult>> callback
    ) {
        DeleteBigInventoryModelMasterTask task = new DeleteBigInventoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteBigInventoryModelMasterResult deleteBigInventoryModelMaster(
            DeleteBigInventoryModelMasterRequest request
    ) {
        final AsyncResult<DeleteBigInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBigInventoryModelMasterAsync(
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

    class DescribeBigInventoryModelsTask extends Gs2RestSessionTask<DescribeBigInventoryModelsResult> {
        private DescribeBigInventoryModelsRequest request;

        public DescribeBigInventoryModelsTask(
            DescribeBigInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeBigInventoryModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBigInventoryModelsResult parse(JsonNode data) {
            return DescribeBigInventoryModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/big/inventory";

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

    public void describeBigInventoryModelsAsync(
            DescribeBigInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeBigInventoryModelsResult>> callback
    ) {
        DescribeBigInventoryModelsTask task = new DescribeBigInventoryModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeBigInventoryModelsResult describeBigInventoryModels(
            DescribeBigInventoryModelsRequest request
    ) {
        final AsyncResult<DescribeBigInventoryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBigInventoryModelsAsync(
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

    class GetBigInventoryModelTask extends Gs2RestSessionTask<GetBigInventoryModelResult> {
        private GetBigInventoryModelRequest request;

        public GetBigInventoryModelTask(
            GetBigInventoryModelRequest request,
            AsyncAction<AsyncResult<GetBigInventoryModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBigInventoryModelResult parse(JsonNode data) {
            return GetBigInventoryModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/big/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getBigInventoryModelAsync(
            GetBigInventoryModelRequest request,
            AsyncAction<AsyncResult<GetBigInventoryModelResult>> callback
    ) {
        GetBigInventoryModelTask task = new GetBigInventoryModelTask(request, callback);
        session.execute(task);
    }

    public GetBigInventoryModelResult getBigInventoryModel(
            GetBigInventoryModelRequest request
    ) {
        final AsyncResult<GetBigInventoryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBigInventoryModelAsync(
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

    class DescribeBigItemModelMastersTask extends Gs2RestSessionTask<DescribeBigItemModelMastersResult> {
        private DescribeBigItemModelMastersRequest request;

        public DescribeBigItemModelMastersTask(
            DescribeBigItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBigItemModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBigItemModelMastersResult parse(JsonNode data) {
            return DescribeBigItemModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeBigItemModelMastersAsync(
            DescribeBigItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBigItemModelMastersResult>> callback
    ) {
        DescribeBigItemModelMastersTask task = new DescribeBigItemModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeBigItemModelMastersResult describeBigItemModelMasters(
            DescribeBigItemModelMastersRequest request
    ) {
        final AsyncResult<DescribeBigItemModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBigItemModelMastersAsync(
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

    class CreateBigItemModelMasterTask extends Gs2RestSessionTask<CreateBigItemModelMasterResult> {
        private CreateBigItemModelMasterRequest request;

        public CreateBigItemModelMasterTask(
            CreateBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBigItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateBigItemModelMasterResult parse(JsonNode data) {
            return CreateBigItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void createBigItemModelMasterAsync(
            CreateBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBigItemModelMasterResult>> callback
    ) {
        CreateBigItemModelMasterTask task = new CreateBigItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateBigItemModelMasterResult createBigItemModelMaster(
            CreateBigItemModelMasterRequest request
    ) {
        final AsyncResult<CreateBigItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createBigItemModelMasterAsync(
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

    class GetBigItemModelMasterTask extends Gs2RestSessionTask<GetBigItemModelMasterResult> {
        private GetBigItemModelMasterRequest request;

        public GetBigItemModelMasterTask(
            GetBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetBigItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBigItemModelMasterResult parse(JsonNode data) {
            return GetBigItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getBigItemModelMasterAsync(
            GetBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetBigItemModelMasterResult>> callback
    ) {
        GetBigItemModelMasterTask task = new GetBigItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetBigItemModelMasterResult getBigItemModelMaster(
            GetBigItemModelMasterRequest request
    ) {
        final AsyncResult<GetBigItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBigItemModelMasterAsync(
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

    class UpdateBigItemModelMasterTask extends Gs2RestSessionTask<UpdateBigItemModelMasterResult> {
        private UpdateBigItemModelMasterRequest request;

        public UpdateBigItemModelMasterTask(
            UpdateBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBigItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateBigItemModelMasterResult parse(JsonNode data) {
            return UpdateBigItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void updateBigItemModelMasterAsync(
            UpdateBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBigItemModelMasterResult>> callback
    ) {
        UpdateBigItemModelMasterTask task = new UpdateBigItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateBigItemModelMasterResult updateBigItemModelMaster(
            UpdateBigItemModelMasterRequest request
    ) {
        final AsyncResult<UpdateBigItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateBigItemModelMasterAsync(
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

    class DeleteBigItemModelMasterTask extends Gs2RestSessionTask<DeleteBigItemModelMasterResult> {
        private DeleteBigItemModelMasterRequest request;

        public DeleteBigItemModelMasterTask(
            DeleteBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBigItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBigItemModelMasterResult parse(JsonNode data) {
            return DeleteBigItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void deleteBigItemModelMasterAsync(
            DeleteBigItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBigItemModelMasterResult>> callback
    ) {
        DeleteBigItemModelMasterTask task = new DeleteBigItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteBigItemModelMasterResult deleteBigItemModelMaster(
            DeleteBigItemModelMasterRequest request
    ) {
        final AsyncResult<DeleteBigItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBigItemModelMasterAsync(
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

    class DescribeBigItemModelsTask extends Gs2RestSessionTask<DescribeBigItemModelsResult> {
        private DescribeBigItemModelsRequest request;

        public DescribeBigItemModelsTask(
            DescribeBigItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeBigItemModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBigItemModelsResult parse(JsonNode data) {
            return DescribeBigItemModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/big/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeBigItemModelsAsync(
            DescribeBigItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeBigItemModelsResult>> callback
    ) {
        DescribeBigItemModelsTask task = new DescribeBigItemModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeBigItemModelsResult describeBigItemModels(
            DescribeBigItemModelsRequest request
    ) {
        final AsyncResult<DescribeBigItemModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBigItemModelsAsync(
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

    class GetBigItemModelTask extends Gs2RestSessionTask<GetBigItemModelResult> {
        private GetBigItemModelRequest request;

        public GetBigItemModelTask(
            GetBigItemModelRequest request,
            AsyncAction<AsyncResult<GetBigItemModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBigItemModelResult parse(JsonNode data) {
            return GetBigItemModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getBigItemModelAsync(
            GetBigItemModelRequest request,
            AsyncAction<AsyncResult<GetBigItemModelResult>> callback
    ) {
        GetBigItemModelTask task = new GetBigItemModelTask(request, callback);
        session.execute(task);
    }

    public GetBigItemModelResult getBigItemModel(
            GetBigItemModelRequest request
    ) {
        final AsyncResult<GetBigItemModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBigItemModelAsync(
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
                .replace("{service}", "inventory")
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

    class GetCurrentItemModelMasterTask extends Gs2RestSessionTask<GetCurrentItemModelMasterResult> {
        private GetCurrentItemModelMasterRequest request;

        public GetCurrentItemModelMasterTask(
            GetCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentItemModelMasterResult parse(JsonNode data) {
            return GetCurrentItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
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

    public void getCurrentItemModelMasterAsync(
            GetCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentItemModelMasterResult>> callback
    ) {
        GetCurrentItemModelMasterTask task = new GetCurrentItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentItemModelMasterResult getCurrentItemModelMaster(
            GetCurrentItemModelMasterRequest request
    ) {
        final AsyncResult<GetCurrentItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentItemModelMasterAsync(
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

    class UpdateCurrentItemModelMasterTask extends Gs2RestSessionTask<UpdateCurrentItemModelMasterResult> {
        private UpdateCurrentItemModelMasterRequest request;

        public UpdateCurrentItemModelMasterTask(
            UpdateCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentItemModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentItemModelMasterResult parse(JsonNode data) {
            return UpdateCurrentItemModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
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

    public void updateCurrentItemModelMasterAsync(
            UpdateCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentItemModelMasterResult>> callback
    ) {
        UpdateCurrentItemModelMasterTask task = new UpdateCurrentItemModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentItemModelMasterResult updateCurrentItemModelMaster(
            UpdateCurrentItemModelMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentItemModelMasterAsync(
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

    class UpdateCurrentItemModelMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentItemModelMasterFromGitHubResult> {
        private UpdateCurrentItemModelMasterFromGitHubRequest request;

        public UpdateCurrentItemModelMasterFromGitHubTask(
            UpdateCurrentItemModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentItemModelMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentItemModelMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentItemModelMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
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

    public void updateCurrentItemModelMasterFromGitHubAsync(
            UpdateCurrentItemModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentItemModelMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentItemModelMasterFromGitHubTask task = new UpdateCurrentItemModelMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentItemModelMasterFromGitHubResult updateCurrentItemModelMasterFromGitHub(
            UpdateCurrentItemModelMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentItemModelMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentItemModelMasterFromGitHubAsync(
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

    class DescribeInventoriesTask extends Gs2RestSessionTask<DescribeInventoriesResult> {
        private DescribeInventoriesRequest request;

        public DescribeInventoriesTask(
            DescribeInventoriesRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeInventoriesResult parse(JsonNode data) {
            return DescribeInventoriesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory";

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

    public void describeInventoriesAsync(
            DescribeInventoriesRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesResult>> callback
    ) {
        DescribeInventoriesTask task = new DescribeInventoriesTask(request, callback);
        session.execute(task);
    }

    public DescribeInventoriesResult describeInventories(
            DescribeInventoriesRequest request
    ) {
        final AsyncResult<DescribeInventoriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoriesAsync(
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

    class DescribeInventoriesByUserIdTask extends Gs2RestSessionTask<DescribeInventoriesByUserIdResult> {
        private DescribeInventoriesByUserIdRequest request;

        public DescribeInventoriesByUserIdTask(
            DescribeInventoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeInventoriesByUserIdResult parse(JsonNode data) {
            return DescribeInventoriesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory";

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

    public void describeInventoriesByUserIdAsync(
            DescribeInventoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesByUserIdResult>> callback
    ) {
        DescribeInventoriesByUserIdTask task = new DescribeInventoriesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeInventoriesByUserIdResult describeInventoriesByUserId(
            DescribeInventoriesByUserIdRequest request
    ) {
        final AsyncResult<DescribeInventoriesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoriesByUserIdAsync(
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

    class GetInventoryTask extends Gs2RestSessionTask<GetInventoryResult> {
        private GetInventoryRequest request;

        public GetInventoryTask(
            GetInventoryRequest request,
            AsyncAction<AsyncResult<GetInventoryResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetInventoryResult parse(JsonNode data) {
            return GetInventoryResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void getInventoryAsync(
            GetInventoryRequest request,
            AsyncAction<AsyncResult<GetInventoryResult>> callback
    ) {
        GetInventoryTask task = new GetInventoryTask(request, callback);
        session.execute(task);
    }

    public GetInventoryResult getInventory(
            GetInventoryRequest request
    ) {
        final AsyncResult<GetInventoryResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryAsync(
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

    class GetInventoryByUserIdTask extends Gs2RestSessionTask<GetInventoryByUserIdResult> {
        private GetInventoryByUserIdRequest request;

        public GetInventoryByUserIdTask(
            GetInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<GetInventoryByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetInventoryByUserIdResult parse(JsonNode data) {
            return GetInventoryByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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

    public void getInventoryByUserIdAsync(
            GetInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<GetInventoryByUserIdResult>> callback
    ) {
        GetInventoryByUserIdTask task = new GetInventoryByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetInventoryByUserIdResult getInventoryByUserId(
            GetInventoryByUserIdRequest request
    ) {
        final AsyncResult<GetInventoryByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryByUserIdAsync(
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

    class AddCapacityByUserIdTask extends Gs2RestSessionTask<AddCapacityByUserIdResult> {
        private AddCapacityByUserIdRequest request;

        public AddCapacityByUserIdTask(
            AddCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddCapacityByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddCapacityByUserIdResult parse(JsonNode data) {
            return AddCapacityByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/capacity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("addCapacityValue", request.getAddCapacityValue());
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

    public void addCapacityByUserIdAsync(
            AddCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddCapacityByUserIdResult>> callback
    ) {
        AddCapacityByUserIdTask task = new AddCapacityByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddCapacityByUserIdResult addCapacityByUserId(
            AddCapacityByUserIdRequest request
    ) {
        final AsyncResult<AddCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addCapacityByUserIdAsync(
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

    class SetCapacityByUserIdTask extends Gs2RestSessionTask<SetCapacityByUserIdResult> {
        private SetCapacityByUserIdRequest request;

        public SetCapacityByUserIdTask(
            SetCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetCapacityByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetCapacityByUserIdResult parse(JsonNode data) {
            return SetCapacityByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/capacity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("newCapacityValue", request.getNewCapacityValue());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setCapacityByUserIdAsync(
            SetCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetCapacityByUserIdResult>> callback
    ) {
        SetCapacityByUserIdTask task = new SetCapacityByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetCapacityByUserIdResult setCapacityByUserId(
            SetCapacityByUserIdRequest request
    ) {
        final AsyncResult<SetCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setCapacityByUserIdAsync(
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

    class DeleteInventoryByUserIdTask extends Gs2RestSessionTask<DeleteInventoryByUserIdResult> {
        private DeleteInventoryByUserIdRequest request;

        public DeleteInventoryByUserIdTask(
            DeleteInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteInventoryByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteInventoryByUserIdResult parse(JsonNode data) {
            return DeleteInventoryByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteInventoryByUserIdAsync(
            DeleteInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteInventoryByUserIdResult>> callback
    ) {
        DeleteInventoryByUserIdTask task = new DeleteInventoryByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteInventoryByUserIdResult deleteInventoryByUserId(
            DeleteInventoryByUserIdRequest request
    ) {
        final AsyncResult<DeleteInventoryByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteInventoryByUserIdAsync(
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

    class AddCapacityByStampSheetTask extends Gs2RestSessionTask<AddCapacityByStampSheetResult> {
        private AddCapacityByStampSheetRequest request;

        public AddCapacityByStampSheetTask(
            AddCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<AddCapacityByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddCapacityByStampSheetResult parse(JsonNode data) {
            return AddCapacityByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/inventory/capacity/add";

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

    public void addCapacityByStampSheetAsync(
            AddCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<AddCapacityByStampSheetResult>> callback
    ) {
        AddCapacityByStampSheetTask task = new AddCapacityByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AddCapacityByStampSheetResult addCapacityByStampSheet(
            AddCapacityByStampSheetRequest request
    ) {
        final AsyncResult<AddCapacityByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addCapacityByStampSheetAsync(
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

    class SetCapacityByStampSheetTask extends Gs2RestSessionTask<SetCapacityByStampSheetResult> {
        private SetCapacityByStampSheetRequest request;

        public SetCapacityByStampSheetTask(
            SetCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<SetCapacityByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetCapacityByStampSheetResult parse(JsonNode data) {
            return SetCapacityByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/inventory/capacity/set";

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

    public void setCapacityByStampSheetAsync(
            SetCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<SetCapacityByStampSheetResult>> callback
    ) {
        SetCapacityByStampSheetTask task = new SetCapacityByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetCapacityByStampSheetResult setCapacityByStampSheet(
            SetCapacityByStampSheetRequest request
    ) {
        final AsyncResult<SetCapacityByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setCapacityByStampSheetAsync(
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

    class DescribeItemSetsTask extends Gs2RestSessionTask<DescribeItemSetsResult> {
        private DescribeItemSetsRequest request;

        public DescribeItemSetsTask(
            DescribeItemSetsRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeItemSetsResult parse(JsonNode data) {
            return DescribeItemSetsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeItemSetsAsync(
            DescribeItemSetsRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsResult>> callback
    ) {
        DescribeItemSetsTask task = new DescribeItemSetsTask(request, callback);
        session.execute(task);
    }

    public DescribeItemSetsResult describeItemSets(
            DescribeItemSetsRequest request
    ) {
        final AsyncResult<DescribeItemSetsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemSetsAsync(
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

    class DescribeItemSetsByUserIdTask extends Gs2RestSessionTask<DescribeItemSetsByUserIdResult> {
        private DescribeItemSetsByUserIdRequest request;

        public DescribeItemSetsByUserIdTask(
            DescribeItemSetsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeItemSetsByUserIdResult parse(JsonNode data) {
            return DescribeItemSetsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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

    public void describeItemSetsByUserIdAsync(
            DescribeItemSetsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsByUserIdResult>> callback
    ) {
        DescribeItemSetsByUserIdTask task = new DescribeItemSetsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeItemSetsByUserIdResult describeItemSetsByUserId(
            DescribeItemSetsByUserIdRequest request
    ) {
        final AsyncResult<DescribeItemSetsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemSetsByUserIdAsync(
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

    class GetItemSetTask extends Gs2RestSessionTask<GetItemSetResult> {
        private GetItemSetRequest request;

        public GetItemSetTask(
            GetItemSetRequest request,
            AsyncAction<AsyncResult<GetItemSetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetItemSetResult parse(JsonNode data) {
            return GetItemSetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getItemSetName() != null) {
                queryStrings.add("itemSetName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getItemSetName()))));
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

    public void getItemSetAsync(
            GetItemSetRequest request,
            AsyncAction<AsyncResult<GetItemSetResult>> callback
    ) {
        GetItemSetTask task = new GetItemSetTask(request, callback);
        session.execute(task);
    }

    public GetItemSetResult getItemSet(
            GetItemSetRequest request
    ) {
        final AsyncResult<GetItemSetResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemSetAsync(
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

    class GetItemSetByUserIdTask extends Gs2RestSessionTask<GetItemSetByUserIdResult> {
        private GetItemSetByUserIdRequest request;

        public GetItemSetByUserIdTask(
            GetItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<GetItemSetByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetItemSetByUserIdResult parse(JsonNode data) {
            return GetItemSetByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getItemSetName() != null) {
                queryStrings.add("itemSetName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getItemSetName()))));
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

    public void getItemSetByUserIdAsync(
            GetItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<GetItemSetByUserIdResult>> callback
    ) {
        GetItemSetByUserIdTask task = new GetItemSetByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetItemSetByUserIdResult getItemSetByUserId(
            GetItemSetByUserIdRequest request
    ) {
        final AsyncResult<GetItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemSetByUserIdAsync(
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

    class GetItemWithSignatureTask extends Gs2RestSessionTask<GetItemWithSignatureResult> {
        private GetItemWithSignatureRequest request;

        public GetItemWithSignatureTask(
            GetItemWithSignatureRequest request,
            AsyncAction<AsyncResult<GetItemWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetItemWithSignatureResult parse(JsonNode data) {
            return GetItemWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getItemSetName() != null) {
                queryStrings.add("itemSetName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getItemSetName()))));
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

    public void getItemWithSignatureAsync(
            GetItemWithSignatureRequest request,
            AsyncAction<AsyncResult<GetItemWithSignatureResult>> callback
    ) {
        GetItemWithSignatureTask task = new GetItemWithSignatureTask(request, callback);
        session.execute(task);
    }

    public GetItemWithSignatureResult getItemWithSignature(
            GetItemWithSignatureRequest request
    ) {
        final AsyncResult<GetItemWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemWithSignatureAsync(
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

    class GetItemWithSignatureByUserIdTask extends Gs2RestSessionTask<GetItemWithSignatureByUserIdResult> {
        private GetItemWithSignatureByUserIdRequest request;

        public GetItemWithSignatureByUserIdTask(
            GetItemWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetItemWithSignatureByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetItemWithSignatureByUserIdResult parse(JsonNode data) {
            return GetItemWithSignatureByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getItemSetName() != null) {
                queryStrings.add("itemSetName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getItemSetName()))));
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

    public void getItemWithSignatureByUserIdAsync(
            GetItemWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetItemWithSignatureByUserIdResult>> callback
    ) {
        GetItemWithSignatureByUserIdTask task = new GetItemWithSignatureByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetItemWithSignatureByUserIdResult getItemWithSignatureByUserId(
            GetItemWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetItemWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemWithSignatureByUserIdAsync(
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

    class AcquireItemSetByUserIdTask extends Gs2RestSessionTask<AcquireItemSetByUserIdResult> {
        private AcquireItemSetByUserIdRequest request;

        public AcquireItemSetByUserIdTask(
            AcquireItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireItemSetByUserIdResult parse(JsonNode data) {
            return AcquireItemSetByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/acquire";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("acquireCount", request.getAcquireCount());
                    put("expiresAt", request.getExpiresAt());
                    put("createNewItemSet", request.getCreateNewItemSet());
                    put("itemSetName", request.getItemSetName());
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

    public void acquireItemSetByUserIdAsync(
            AcquireItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByUserIdResult>> callback
    ) {
        AcquireItemSetByUserIdTask task = new AcquireItemSetByUserIdTask(request, callback);
        session.execute(task);
    }

    public AcquireItemSetByUserIdResult acquireItemSetByUserId(
            AcquireItemSetByUserIdRequest request
    ) {
        final AsyncResult<AcquireItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireItemSetByUserIdAsync(
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

    class ConsumeItemSetTask extends Gs2RestSessionTask<ConsumeItemSetResult> {
        private ConsumeItemSetRequest request;

        public ConsumeItemSetTask(
            ConsumeItemSetRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeItemSetResult parse(JsonNode data) {
            return ConsumeItemSetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeCount", request.getConsumeCount());
                    put("itemSetName", request.getItemSetName());
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

    public void consumeItemSetAsync(
            ConsumeItemSetRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetResult>> callback
    ) {
        ConsumeItemSetTask task = new ConsumeItemSetTask(request, callback);
        session.execute(task);
    }

    public ConsumeItemSetResult consumeItemSet(
            ConsumeItemSetRequest request
    ) {
        final AsyncResult<ConsumeItemSetResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeItemSetAsync(
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

    class ConsumeItemSetByUserIdTask extends Gs2RestSessionTask<ConsumeItemSetByUserIdResult> {
        private ConsumeItemSetByUserIdRequest request;

        public ConsumeItemSetByUserIdTask(
            ConsumeItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeItemSetByUserIdResult parse(JsonNode data) {
            return ConsumeItemSetByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeCount", request.getConsumeCount());
                    put("itemSetName", request.getItemSetName());
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

    public void consumeItemSetByUserIdAsync(
            ConsumeItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByUserIdResult>> callback
    ) {
        ConsumeItemSetByUserIdTask task = new ConsumeItemSetByUserIdTask(request, callback);
        session.execute(task);
    }

    public ConsumeItemSetByUserIdResult consumeItemSetByUserId(
            ConsumeItemSetByUserIdRequest request
    ) {
        final AsyncResult<ConsumeItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeItemSetByUserIdAsync(
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

    class DeleteItemSetByUserIdTask extends Gs2RestSessionTask<DeleteItemSetByUserIdResult> {
        private DeleteItemSetByUserIdRequest request;

        public DeleteItemSetByUserIdTask(
            DeleteItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteItemSetByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteItemSetByUserIdResult parse(JsonNode data) {
            return DeleteItemSetByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getItemSetName() != null) {
                queryStrings.add("itemSetName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getItemSetName()))));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteItemSetByUserIdAsync(
            DeleteItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteItemSetByUserIdResult>> callback
    ) {
        DeleteItemSetByUserIdTask task = new DeleteItemSetByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteItemSetByUserIdResult deleteItemSetByUserId(
            DeleteItemSetByUserIdRequest request
    ) {
        final AsyncResult<DeleteItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteItemSetByUserIdAsync(
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

    class VerifyItemSetTask extends Gs2RestSessionTask<VerifyItemSetResult> {
        private VerifyItemSetRequest request;

        public VerifyItemSetTask(
            VerifyItemSetRequest request,
            AsyncAction<AsyncResult<VerifyItemSetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyItemSetResult parse(JsonNode data) {
            return VerifyItemSetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("itemSetName", request.getItemSetName());
                    put("count", request.getCount());
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

    public void verifyItemSetAsync(
            VerifyItemSetRequest request,
            AsyncAction<AsyncResult<VerifyItemSetResult>> callback
    ) {
        VerifyItemSetTask task = new VerifyItemSetTask(request, callback);
        session.execute(task);
    }

    public VerifyItemSetResult verifyItemSet(
            VerifyItemSetRequest request
    ) {
        final AsyncResult<VerifyItemSetResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyItemSetAsync(
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

    class VerifyItemSetByUserIdTask extends Gs2RestSessionTask<VerifyItemSetByUserIdResult> {
        private VerifyItemSetByUserIdRequest request;

        public VerifyItemSetByUserIdTask(
            VerifyItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyItemSetByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyItemSetByUserIdResult parse(JsonNode data) {
            return VerifyItemSetByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("itemSetName", request.getItemSetName());
                    put("count", request.getCount());
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

    public void verifyItemSetByUserIdAsync(
            VerifyItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyItemSetByUserIdResult>> callback
    ) {
        VerifyItemSetByUserIdTask task = new VerifyItemSetByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyItemSetByUserIdResult verifyItemSetByUserId(
            VerifyItemSetByUserIdRequest request
    ) {
        final AsyncResult<VerifyItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyItemSetByUserIdAsync(
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

    class AcquireItemSetByStampSheetTask extends Gs2RestSessionTask<AcquireItemSetByStampSheetResult> {
        private AcquireItemSetByStampSheetRequest request;

        public AcquireItemSetByStampSheetTask(
            AcquireItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireItemSetByStampSheetResult parse(JsonNode data) {
            return AcquireItemSetByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/acquire";

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

    public void acquireItemSetByStampSheetAsync(
            AcquireItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByStampSheetResult>> callback
    ) {
        AcquireItemSetByStampSheetTask task = new AcquireItemSetByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquireItemSetByStampSheetResult acquireItemSetByStampSheet(
            AcquireItemSetByStampSheetRequest request
    ) {
        final AsyncResult<AcquireItemSetByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireItemSetByStampSheetAsync(
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

    class ConsumeItemSetByStampTaskTask extends Gs2RestSessionTask<ConsumeItemSetByStampTaskResult> {
        private ConsumeItemSetByStampTaskRequest request;

        public ConsumeItemSetByStampTaskTask(
            ConsumeItemSetByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeItemSetByStampTaskResult parse(JsonNode data) {
            return ConsumeItemSetByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/consume";

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

    public void consumeItemSetByStampTaskAsync(
            ConsumeItemSetByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByStampTaskResult>> callback
    ) {
        ConsumeItemSetByStampTaskTask task = new ConsumeItemSetByStampTaskTask(request, callback);
        session.execute(task);
    }

    public ConsumeItemSetByStampTaskResult consumeItemSetByStampTask(
            ConsumeItemSetByStampTaskRequest request
    ) {
        final AsyncResult<ConsumeItemSetByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeItemSetByStampTaskAsync(
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

    class VerifyItemSetByStampTaskTask extends Gs2RestSessionTask<VerifyItemSetByStampTaskResult> {
        private VerifyItemSetByStampTaskRequest request;

        public VerifyItemSetByStampTaskTask(
            VerifyItemSetByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyItemSetByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyItemSetByStampTaskResult parse(JsonNode data) {
            return VerifyItemSetByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/verify";

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

    public void verifyItemSetByStampTaskAsync(
            VerifyItemSetByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyItemSetByStampTaskResult>> callback
    ) {
        VerifyItemSetByStampTaskTask task = new VerifyItemSetByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyItemSetByStampTaskResult verifyItemSetByStampTask(
            VerifyItemSetByStampTaskRequest request
    ) {
        final AsyncResult<VerifyItemSetByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyItemSetByStampTaskAsync(
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

    class DescribeReferenceOfTask extends Gs2RestSessionTask<DescribeReferenceOfResult> {
        private DescribeReferenceOfRequest request;

        public DescribeReferenceOfTask(
            DescribeReferenceOfRequest request,
            AsyncAction<AsyncResult<DescribeReferenceOfResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReferenceOfResult parse(JsonNode data) {
            return DescribeReferenceOfResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));

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

    public void describeReferenceOfAsync(
            DescribeReferenceOfRequest request,
            AsyncAction<AsyncResult<DescribeReferenceOfResult>> callback
    ) {
        DescribeReferenceOfTask task = new DescribeReferenceOfTask(request, callback);
        session.execute(task);
    }

    public DescribeReferenceOfResult describeReferenceOf(
            DescribeReferenceOfRequest request
    ) {
        final AsyncResult<DescribeReferenceOfResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReferenceOfAsync(
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

    class DescribeReferenceOfByUserIdTask extends Gs2RestSessionTask<DescribeReferenceOfByUserIdResult> {
        private DescribeReferenceOfByUserIdRequest request;

        public DescribeReferenceOfByUserIdTask(
            DescribeReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReferenceOfByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReferenceOfByUserIdResult parse(JsonNode data) {
            return DescribeReferenceOfByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));

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

    public void describeReferenceOfByUserIdAsync(
            DescribeReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReferenceOfByUserIdResult>> callback
    ) {
        DescribeReferenceOfByUserIdTask task = new DescribeReferenceOfByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeReferenceOfByUserIdResult describeReferenceOfByUserId(
            DescribeReferenceOfByUserIdRequest request
    ) {
        final AsyncResult<DescribeReferenceOfByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReferenceOfByUserIdAsync(
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

    class GetReferenceOfTask extends Gs2RestSessionTask<GetReferenceOfResult> {
        private GetReferenceOfRequest request;

        public GetReferenceOfTask(
            GetReferenceOfRequest request,
            AsyncAction<AsyncResult<GetReferenceOfResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReferenceOfResult parse(JsonNode data) {
            return GetReferenceOfResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference/{referenceOf}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));
            url = url.replace("{referenceOf}", this.request.getReferenceOf() == null || this.request.getReferenceOf().length() == 0 ? "null" : String.valueOf(this.request.getReferenceOf()));

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

    public void getReferenceOfAsync(
            GetReferenceOfRequest request,
            AsyncAction<AsyncResult<GetReferenceOfResult>> callback
    ) {
        GetReferenceOfTask task = new GetReferenceOfTask(request, callback);
        session.execute(task);
    }

    public GetReferenceOfResult getReferenceOf(
            GetReferenceOfRequest request
    ) {
        final AsyncResult<GetReferenceOfResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReferenceOfAsync(
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

    class GetReferenceOfByUserIdTask extends Gs2RestSessionTask<GetReferenceOfByUserIdResult> {
        private GetReferenceOfByUserIdRequest request;

        public GetReferenceOfByUserIdTask(
            GetReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<GetReferenceOfByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReferenceOfByUserIdResult parse(JsonNode data) {
            return GetReferenceOfByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference/{referenceOf}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));
            url = url.replace("{referenceOf}", this.request.getReferenceOf() == null || this.request.getReferenceOf().length() == 0 ? "null" : String.valueOf(this.request.getReferenceOf()));

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

    public void getReferenceOfByUserIdAsync(
            GetReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<GetReferenceOfByUserIdResult>> callback
    ) {
        GetReferenceOfByUserIdTask task = new GetReferenceOfByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetReferenceOfByUserIdResult getReferenceOfByUserId(
            GetReferenceOfByUserIdRequest request
    ) {
        final AsyncResult<GetReferenceOfByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReferenceOfByUserIdAsync(
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

    class VerifyReferenceOfTask extends Gs2RestSessionTask<VerifyReferenceOfResult> {
        private VerifyReferenceOfRequest request;

        public VerifyReferenceOfTask(
            VerifyReferenceOfRequest request,
            AsyncAction<AsyncResult<VerifyReferenceOfResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyReferenceOfResult parse(JsonNode data) {
            return VerifyReferenceOfResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference/{referenceOf}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));
            url = url.replace("{referenceOf}", this.request.getReferenceOf() == null || this.request.getReferenceOf().length() == 0 ? "null" : String.valueOf(this.request.getReferenceOf()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

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

    public void verifyReferenceOfAsync(
            VerifyReferenceOfRequest request,
            AsyncAction<AsyncResult<VerifyReferenceOfResult>> callback
    ) {
        VerifyReferenceOfTask task = new VerifyReferenceOfTask(request, callback);
        session.execute(task);
    }

    public VerifyReferenceOfResult verifyReferenceOf(
            VerifyReferenceOfRequest request
    ) {
        final AsyncResult<VerifyReferenceOfResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyReferenceOfAsync(
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

    class VerifyReferenceOfByUserIdTask extends Gs2RestSessionTask<VerifyReferenceOfByUserIdResult> {
        private VerifyReferenceOfByUserIdRequest request;

        public VerifyReferenceOfByUserIdTask(
            VerifyReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyReferenceOfByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyReferenceOfByUserIdResult parse(JsonNode data) {
            return VerifyReferenceOfByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference/{referenceOf}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));
            url = url.replace("{referenceOf}", this.request.getReferenceOf() == null || this.request.getReferenceOf().length() == 0 ? "null" : String.valueOf(this.request.getReferenceOf()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

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

            builder
                .build()
                .send();
        }
    }

    public void verifyReferenceOfByUserIdAsync(
            VerifyReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyReferenceOfByUserIdResult>> callback
    ) {
        VerifyReferenceOfByUserIdTask task = new VerifyReferenceOfByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyReferenceOfByUserIdResult verifyReferenceOfByUserId(
            VerifyReferenceOfByUserIdRequest request
    ) {
        final AsyncResult<VerifyReferenceOfByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyReferenceOfByUserIdAsync(
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

    class AddReferenceOfTask extends Gs2RestSessionTask<AddReferenceOfResult> {
        private AddReferenceOfRequest request;

        public AddReferenceOfTask(
            AddReferenceOfRequest request,
            AsyncAction<AsyncResult<AddReferenceOfResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddReferenceOfResult parse(JsonNode data) {
            return AddReferenceOfResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("referenceOf", request.getReferenceOf());
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

    public void addReferenceOfAsync(
            AddReferenceOfRequest request,
            AsyncAction<AsyncResult<AddReferenceOfResult>> callback
    ) {
        AddReferenceOfTask task = new AddReferenceOfTask(request, callback);
        session.execute(task);
    }

    public AddReferenceOfResult addReferenceOf(
            AddReferenceOfRequest request
    ) {
        final AsyncResult<AddReferenceOfResult>[] resultAsyncResult = new AsyncResult[]{null};
        addReferenceOfAsync(
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

    class AddReferenceOfByUserIdTask extends Gs2RestSessionTask<AddReferenceOfByUserIdResult> {
        private AddReferenceOfByUserIdRequest request;

        public AddReferenceOfByUserIdTask(
            AddReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<AddReferenceOfByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddReferenceOfByUserIdResult parse(JsonNode data) {
            return AddReferenceOfByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("referenceOf", request.getReferenceOf());
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

    public void addReferenceOfByUserIdAsync(
            AddReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<AddReferenceOfByUserIdResult>> callback
    ) {
        AddReferenceOfByUserIdTask task = new AddReferenceOfByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddReferenceOfByUserIdResult addReferenceOfByUserId(
            AddReferenceOfByUserIdRequest request
    ) {
        final AsyncResult<AddReferenceOfByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addReferenceOfByUserIdAsync(
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

    class DeleteReferenceOfTask extends Gs2RestSessionTask<DeleteReferenceOfResult> {
        private DeleteReferenceOfRequest request;

        public DeleteReferenceOfTask(
            DeleteReferenceOfRequest request,
            AsyncAction<AsyncResult<DeleteReferenceOfResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteReferenceOfResult parse(JsonNode data) {
            return DeleteReferenceOfResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference/{referenceOf}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));
            url = url.replace("{referenceOf}", this.request.getReferenceOf() == null || this.request.getReferenceOf().length() == 0 ? "null" : String.valueOf(this.request.getReferenceOf()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteReferenceOfAsync(
            DeleteReferenceOfRequest request,
            AsyncAction<AsyncResult<DeleteReferenceOfResult>> callback
    ) {
        DeleteReferenceOfTask task = new DeleteReferenceOfTask(request, callback);
        session.execute(task);
    }

    public DeleteReferenceOfResult deleteReferenceOf(
            DeleteReferenceOfRequest request
    ) {
        final AsyncResult<DeleteReferenceOfResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteReferenceOfAsync(
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

    class DeleteReferenceOfByUserIdTask extends Gs2RestSessionTask<DeleteReferenceOfByUserIdResult> {
        private DeleteReferenceOfByUserIdRequest request;

        public DeleteReferenceOfByUserIdTask(
            DeleteReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteReferenceOfByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteReferenceOfByUserIdResult parse(JsonNode data) {
            return DeleteReferenceOfByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/{itemSetName}/reference/{referenceOf}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{itemSetName}", this.request.getItemSetName() == null || this.request.getItemSetName().length() == 0 ? "null" : String.valueOf(this.request.getItemSetName()));
            url = url.replace("{referenceOf}", this.request.getReferenceOf() == null || this.request.getReferenceOf().length() == 0 ? "null" : String.valueOf(this.request.getReferenceOf()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteReferenceOfByUserIdAsync(
            DeleteReferenceOfByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteReferenceOfByUserIdResult>> callback
    ) {
        DeleteReferenceOfByUserIdTask task = new DeleteReferenceOfByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteReferenceOfByUserIdResult deleteReferenceOfByUserId(
            DeleteReferenceOfByUserIdRequest request
    ) {
        final AsyncResult<DeleteReferenceOfByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteReferenceOfByUserIdAsync(
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

    class AddReferenceOfItemSetByStampSheetTask extends Gs2RestSessionTask<AddReferenceOfItemSetByStampSheetResult> {
        private AddReferenceOfItemSetByStampSheetRequest request;

        public AddReferenceOfItemSetByStampSheetTask(
            AddReferenceOfItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<AddReferenceOfItemSetByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddReferenceOfItemSetByStampSheetResult parse(JsonNode data) {
            return AddReferenceOfItemSetByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/reference/add";

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

    public void addReferenceOfItemSetByStampSheetAsync(
            AddReferenceOfItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<AddReferenceOfItemSetByStampSheetResult>> callback
    ) {
        AddReferenceOfItemSetByStampSheetTask task = new AddReferenceOfItemSetByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AddReferenceOfItemSetByStampSheetResult addReferenceOfItemSetByStampSheet(
            AddReferenceOfItemSetByStampSheetRequest request
    ) {
        final AsyncResult<AddReferenceOfItemSetByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addReferenceOfItemSetByStampSheetAsync(
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

    class DeleteReferenceOfItemSetByStampSheetTask extends Gs2RestSessionTask<DeleteReferenceOfItemSetByStampSheetResult> {
        private DeleteReferenceOfItemSetByStampSheetRequest request;

        public DeleteReferenceOfItemSetByStampSheetTask(
            DeleteReferenceOfItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<DeleteReferenceOfItemSetByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteReferenceOfItemSetByStampSheetResult parse(JsonNode data) {
            return DeleteReferenceOfItemSetByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/reference/delete";

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

    public void deleteReferenceOfItemSetByStampSheetAsync(
            DeleteReferenceOfItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<DeleteReferenceOfItemSetByStampSheetResult>> callback
    ) {
        DeleteReferenceOfItemSetByStampSheetTask task = new DeleteReferenceOfItemSetByStampSheetTask(request, callback);
        session.execute(task);
    }

    public DeleteReferenceOfItemSetByStampSheetResult deleteReferenceOfItemSetByStampSheet(
            DeleteReferenceOfItemSetByStampSheetRequest request
    ) {
        final AsyncResult<DeleteReferenceOfItemSetByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteReferenceOfItemSetByStampSheetAsync(
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

    class VerifyReferenceOfByStampTaskTask extends Gs2RestSessionTask<VerifyReferenceOfByStampTaskResult> {
        private VerifyReferenceOfByStampTaskRequest request;

        public VerifyReferenceOfByStampTaskTask(
            VerifyReferenceOfByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyReferenceOfByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyReferenceOfByStampTaskResult parse(JsonNode data) {
            return VerifyReferenceOfByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/verify";

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

    public void verifyReferenceOfByStampTaskAsync(
            VerifyReferenceOfByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyReferenceOfByStampTaskResult>> callback
    ) {
        VerifyReferenceOfByStampTaskTask task = new VerifyReferenceOfByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyReferenceOfByStampTaskResult verifyReferenceOfByStampTask(
            VerifyReferenceOfByStampTaskRequest request
    ) {
        final AsyncResult<VerifyReferenceOfByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyReferenceOfByStampTaskAsync(
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

    class DescribeSimpleItemsTask extends Gs2RestSessionTask<DescribeSimpleItemsResult> {
        private DescribeSimpleItemsRequest request;

        public DescribeSimpleItemsTask(
            DescribeSimpleItemsRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSimpleItemsResult parse(JsonNode data) {
            return DescribeSimpleItemsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/simple/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeSimpleItemsAsync(
            DescribeSimpleItemsRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemsResult>> callback
    ) {
        DescribeSimpleItemsTask task = new DescribeSimpleItemsTask(request, callback);
        session.execute(task);
    }

    public DescribeSimpleItemsResult describeSimpleItems(
            DescribeSimpleItemsRequest request
    ) {
        final AsyncResult<DescribeSimpleItemsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSimpleItemsAsync(
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

    class DescribeSimpleItemsByUserIdTask extends Gs2RestSessionTask<DescribeSimpleItemsByUserIdResult> {
        private DescribeSimpleItemsByUserIdRequest request;

        public DescribeSimpleItemsByUserIdTask(
            DescribeSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSimpleItemsByUserIdResult parse(JsonNode data) {
            return DescribeSimpleItemsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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

    public void describeSimpleItemsByUserIdAsync(
            DescribeSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSimpleItemsByUserIdResult>> callback
    ) {
        DescribeSimpleItemsByUserIdTask task = new DescribeSimpleItemsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSimpleItemsByUserIdResult describeSimpleItemsByUserId(
            DescribeSimpleItemsByUserIdRequest request
    ) {
        final AsyncResult<DescribeSimpleItemsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSimpleItemsByUserIdAsync(
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

    class GetSimpleItemTask extends Gs2RestSessionTask<GetSimpleItemResult> {
        private GetSimpleItemRequest request;

        public GetSimpleItemTask(
            GetSimpleItemRequest request,
            AsyncAction<AsyncResult<GetSimpleItemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleItemResult parse(JsonNode data) {
            return GetSimpleItemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/simple/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getSimpleItemAsync(
            GetSimpleItemRequest request,
            AsyncAction<AsyncResult<GetSimpleItemResult>> callback
    ) {
        GetSimpleItemTask task = new GetSimpleItemTask(request, callback);
        session.execute(task);
    }

    public GetSimpleItemResult getSimpleItem(
            GetSimpleItemRequest request
    ) {
        final AsyncResult<GetSimpleItemResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleItemAsync(
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

    class GetSimpleItemByUserIdTask extends Gs2RestSessionTask<GetSimpleItemByUserIdResult> {
        private GetSimpleItemByUserIdRequest request;

        public GetSimpleItemByUserIdTask(
            GetSimpleItemByUserIdRequest request,
            AsyncAction<AsyncResult<GetSimpleItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleItemByUserIdResult parse(JsonNode data) {
            return GetSimpleItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getSimpleItemByUserIdAsync(
            GetSimpleItemByUserIdRequest request,
            AsyncAction<AsyncResult<GetSimpleItemByUserIdResult>> callback
    ) {
        GetSimpleItemByUserIdTask task = new GetSimpleItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSimpleItemByUserIdResult getSimpleItemByUserId(
            GetSimpleItemByUserIdRequest request
    ) {
        final AsyncResult<GetSimpleItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleItemByUserIdAsync(
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

    class GetSimpleItemWithSignatureTask extends Gs2RestSessionTask<GetSimpleItemWithSignatureResult> {
        private GetSimpleItemWithSignatureRequest request;

        public GetSimpleItemWithSignatureTask(
            GetSimpleItemWithSignatureRequest request,
            AsyncAction<AsyncResult<GetSimpleItemWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleItemWithSignatureResult parse(JsonNode data) {
            return GetSimpleItemWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/simple/inventory/{inventoryName}/item/{itemName}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getSimpleItemWithSignatureAsync(
            GetSimpleItemWithSignatureRequest request,
            AsyncAction<AsyncResult<GetSimpleItemWithSignatureResult>> callback
    ) {
        GetSimpleItemWithSignatureTask task = new GetSimpleItemWithSignatureTask(request, callback);
        session.execute(task);
    }

    public GetSimpleItemWithSignatureResult getSimpleItemWithSignature(
            GetSimpleItemWithSignatureRequest request
    ) {
        final AsyncResult<GetSimpleItemWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleItemWithSignatureAsync(
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

    class GetSimpleItemWithSignatureByUserIdTask extends Gs2RestSessionTask<GetSimpleItemWithSignatureByUserIdResult> {
        private GetSimpleItemWithSignatureByUserIdRequest request;

        public GetSimpleItemWithSignatureByUserIdTask(
            GetSimpleItemWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetSimpleItemWithSignatureByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSimpleItemWithSignatureByUserIdResult parse(JsonNode data) {
            return GetSimpleItemWithSignatureByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}/item/{itemName}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getSimpleItemWithSignatureByUserIdAsync(
            GetSimpleItemWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetSimpleItemWithSignatureByUserIdResult>> callback
    ) {
        GetSimpleItemWithSignatureByUserIdTask task = new GetSimpleItemWithSignatureByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSimpleItemWithSignatureByUserIdResult getSimpleItemWithSignatureByUserId(
            GetSimpleItemWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetSimpleItemWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSimpleItemWithSignatureByUserIdAsync(
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

    class AcquireSimpleItemsByUserIdTask extends Gs2RestSessionTask<AcquireSimpleItemsByUserIdResult> {
        private AcquireSimpleItemsByUserIdRequest request;

        public AcquireSimpleItemsByUserIdTask(
            AcquireSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireSimpleItemsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireSimpleItemsByUserIdResult parse(JsonNode data) {
            return AcquireSimpleItemsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}/acquire";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("acquireCounts", request.getAcquireCounts() == null ? new ArrayList<AcquireCount>() :
                        request.getAcquireCounts().stream().map(item -> {
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

            builder
                .build()
                .send();
        }
    }

    public void acquireSimpleItemsByUserIdAsync(
            AcquireSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireSimpleItemsByUserIdResult>> callback
    ) {
        AcquireSimpleItemsByUserIdTask task = new AcquireSimpleItemsByUserIdTask(request, callback);
        session.execute(task);
    }

    public AcquireSimpleItemsByUserIdResult acquireSimpleItemsByUserId(
            AcquireSimpleItemsByUserIdRequest request
    ) {
        final AsyncResult<AcquireSimpleItemsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireSimpleItemsByUserIdAsync(
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

    class ConsumeSimpleItemsTask extends Gs2RestSessionTask<ConsumeSimpleItemsResult> {
        private ConsumeSimpleItemsRequest request;

        public ConsumeSimpleItemsTask(
            ConsumeSimpleItemsRequest request,
            AsyncAction<AsyncResult<ConsumeSimpleItemsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeSimpleItemsResult parse(JsonNode data) {
            return ConsumeSimpleItemsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/simple/inventory/{inventoryName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeCounts", request.getConsumeCounts() == null ? new ArrayList<ConsumeCount>() :
                        request.getConsumeCounts().stream().map(item -> {
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

    public void consumeSimpleItemsAsync(
            ConsumeSimpleItemsRequest request,
            AsyncAction<AsyncResult<ConsumeSimpleItemsResult>> callback
    ) {
        ConsumeSimpleItemsTask task = new ConsumeSimpleItemsTask(request, callback);
        session.execute(task);
    }

    public ConsumeSimpleItemsResult consumeSimpleItems(
            ConsumeSimpleItemsRequest request
    ) {
        final AsyncResult<ConsumeSimpleItemsResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeSimpleItemsAsync(
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

    class ConsumeSimpleItemsByUserIdTask extends Gs2RestSessionTask<ConsumeSimpleItemsByUserIdResult> {
        private ConsumeSimpleItemsByUserIdRequest request;

        public ConsumeSimpleItemsByUserIdTask(
            ConsumeSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeSimpleItemsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeSimpleItemsByUserIdResult parse(JsonNode data) {
            return ConsumeSimpleItemsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeCounts", request.getConsumeCounts() == null ? new ArrayList<ConsumeCount>() :
                        request.getConsumeCounts().stream().map(item -> {
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

            builder
                .build()
                .send();
        }
    }

    public void consumeSimpleItemsByUserIdAsync(
            ConsumeSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeSimpleItemsByUserIdResult>> callback
    ) {
        ConsumeSimpleItemsByUserIdTask task = new ConsumeSimpleItemsByUserIdTask(request, callback);
        session.execute(task);
    }

    public ConsumeSimpleItemsByUserIdResult consumeSimpleItemsByUserId(
            ConsumeSimpleItemsByUserIdRequest request
    ) {
        final AsyncResult<ConsumeSimpleItemsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeSimpleItemsByUserIdAsync(
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

    class DeleteSimpleItemsByUserIdTask extends Gs2RestSessionTask<DeleteSimpleItemsByUserIdResult> {
        private DeleteSimpleItemsByUserIdRequest request;

        public DeleteSimpleItemsByUserIdTask(
            DeleteSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteSimpleItemsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSimpleItemsByUserIdResult parse(JsonNode data) {
            return DeleteSimpleItemsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteSimpleItemsByUserIdAsync(
            DeleteSimpleItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteSimpleItemsByUserIdResult>> callback
    ) {
        DeleteSimpleItemsByUserIdTask task = new DeleteSimpleItemsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteSimpleItemsByUserIdResult deleteSimpleItemsByUserId(
            DeleteSimpleItemsByUserIdRequest request
    ) {
        final AsyncResult<DeleteSimpleItemsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSimpleItemsByUserIdAsync(
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

    class VerifySimpleItemTask extends Gs2RestSessionTask<VerifySimpleItemResult> {
        private VerifySimpleItemRequest request;

        public VerifySimpleItemTask(
            VerifySimpleItemRequest request,
            AsyncAction<AsyncResult<VerifySimpleItemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifySimpleItemResult parse(JsonNode data) {
            return VerifySimpleItemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/simple/inventory/{inventoryName}/item/{itemName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void verifySimpleItemAsync(
            VerifySimpleItemRequest request,
            AsyncAction<AsyncResult<VerifySimpleItemResult>> callback
    ) {
        VerifySimpleItemTask task = new VerifySimpleItemTask(request, callback);
        session.execute(task);
    }

    public VerifySimpleItemResult verifySimpleItem(
            VerifySimpleItemRequest request
    ) {
        final AsyncResult<VerifySimpleItemResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifySimpleItemAsync(
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

    class VerifySimpleItemByUserIdTask extends Gs2RestSessionTask<VerifySimpleItemByUserIdResult> {
        private VerifySimpleItemByUserIdRequest request;

        public VerifySimpleItemByUserIdTask(
            VerifySimpleItemByUserIdRequest request,
            AsyncAction<AsyncResult<VerifySimpleItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifySimpleItemByUserIdResult parse(JsonNode data) {
            return VerifySimpleItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/simple/inventory/{inventoryName}/item/{itemName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void verifySimpleItemByUserIdAsync(
            VerifySimpleItemByUserIdRequest request,
            AsyncAction<AsyncResult<VerifySimpleItemByUserIdResult>> callback
    ) {
        VerifySimpleItemByUserIdTask task = new VerifySimpleItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifySimpleItemByUserIdResult verifySimpleItemByUserId(
            VerifySimpleItemByUserIdRequest request
    ) {
        final AsyncResult<VerifySimpleItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifySimpleItemByUserIdAsync(
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

    class AcquireSimpleItemsByStampSheetTask extends Gs2RestSessionTask<AcquireSimpleItemsByStampSheetResult> {
        private AcquireSimpleItemsByStampSheetRequest request;

        public AcquireSimpleItemsByStampSheetTask(
            AcquireSimpleItemsByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireSimpleItemsByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireSimpleItemsByStampSheetResult parse(JsonNode data) {
            return AcquireSimpleItemsByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/simple/item/acquire";

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

    public void acquireSimpleItemsByStampSheetAsync(
            AcquireSimpleItemsByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireSimpleItemsByStampSheetResult>> callback
    ) {
        AcquireSimpleItemsByStampSheetTask task = new AcquireSimpleItemsByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquireSimpleItemsByStampSheetResult acquireSimpleItemsByStampSheet(
            AcquireSimpleItemsByStampSheetRequest request
    ) {
        final AsyncResult<AcquireSimpleItemsByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireSimpleItemsByStampSheetAsync(
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

    class ConsumeSimpleItemsByStampTaskTask extends Gs2RestSessionTask<ConsumeSimpleItemsByStampTaskResult> {
        private ConsumeSimpleItemsByStampTaskRequest request;

        public ConsumeSimpleItemsByStampTaskTask(
            ConsumeSimpleItemsByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeSimpleItemsByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeSimpleItemsByStampTaskResult parse(JsonNode data) {
            return ConsumeSimpleItemsByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/simple/item/consume";

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

    public void consumeSimpleItemsByStampTaskAsync(
            ConsumeSimpleItemsByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeSimpleItemsByStampTaskResult>> callback
    ) {
        ConsumeSimpleItemsByStampTaskTask task = new ConsumeSimpleItemsByStampTaskTask(request, callback);
        session.execute(task);
    }

    public ConsumeSimpleItemsByStampTaskResult consumeSimpleItemsByStampTask(
            ConsumeSimpleItemsByStampTaskRequest request
    ) {
        final AsyncResult<ConsumeSimpleItemsByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeSimpleItemsByStampTaskAsync(
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

    class VerifySimpleItemByStampTaskTask extends Gs2RestSessionTask<VerifySimpleItemByStampTaskResult> {
        private VerifySimpleItemByStampTaskRequest request;

        public VerifySimpleItemByStampTaskTask(
            VerifySimpleItemByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifySimpleItemByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifySimpleItemByStampTaskResult parse(JsonNode data) {
            return VerifySimpleItemByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/simple/item/verify";

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

    public void verifySimpleItemByStampTaskAsync(
            VerifySimpleItemByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifySimpleItemByStampTaskResult>> callback
    ) {
        VerifySimpleItemByStampTaskTask task = new VerifySimpleItemByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifySimpleItemByStampTaskResult verifySimpleItemByStampTask(
            VerifySimpleItemByStampTaskRequest request
    ) {
        final AsyncResult<VerifySimpleItemByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifySimpleItemByStampTaskAsync(
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

    class DescribeBigItemsTask extends Gs2RestSessionTask<DescribeBigItemsResult> {
        private DescribeBigItemsRequest request;

        public DescribeBigItemsTask(
            DescribeBigItemsRequest request,
            AsyncAction<AsyncResult<DescribeBigItemsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBigItemsResult parse(JsonNode data) {
            return DescribeBigItemsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/big/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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

    public void describeBigItemsAsync(
            DescribeBigItemsRequest request,
            AsyncAction<AsyncResult<DescribeBigItemsResult>> callback
    ) {
        DescribeBigItemsTask task = new DescribeBigItemsTask(request, callback);
        session.execute(task);
    }

    public DescribeBigItemsResult describeBigItems(
            DescribeBigItemsRequest request
    ) {
        final AsyncResult<DescribeBigItemsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBigItemsAsync(
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

    class DescribeBigItemsByUserIdTask extends Gs2RestSessionTask<DescribeBigItemsByUserIdResult> {
        private DescribeBigItemsByUserIdRequest request;

        public DescribeBigItemsByUserIdTask(
            DescribeBigItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBigItemsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBigItemsByUserIdResult parse(JsonNode data) {
            return DescribeBigItemsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/big/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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

    public void describeBigItemsByUserIdAsync(
            DescribeBigItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBigItemsByUserIdResult>> callback
    ) {
        DescribeBigItemsByUserIdTask task = new DescribeBigItemsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeBigItemsByUserIdResult describeBigItemsByUserId(
            DescribeBigItemsByUserIdRequest request
    ) {
        final AsyncResult<DescribeBigItemsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBigItemsByUserIdAsync(
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

    class GetBigItemTask extends Gs2RestSessionTask<GetBigItemResult> {
        private GetBigItemRequest request;

        public GetBigItemTask(
            GetBigItemRequest request,
            AsyncAction<AsyncResult<GetBigItemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBigItemResult parse(JsonNode data) {
            return GetBigItemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getBigItemAsync(
            GetBigItemRequest request,
            AsyncAction<AsyncResult<GetBigItemResult>> callback
    ) {
        GetBigItemTask task = new GetBigItemTask(request, callback);
        session.execute(task);
    }

    public GetBigItemResult getBigItem(
            GetBigItemRequest request
    ) {
        final AsyncResult<GetBigItemResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBigItemAsync(
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

    class GetBigItemByUserIdTask extends Gs2RestSessionTask<GetBigItemByUserIdResult> {
        private GetBigItemByUserIdRequest request;

        public GetBigItemByUserIdTask(
            GetBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<GetBigItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBigItemByUserIdResult parse(JsonNode data) {
            return GetBigItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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

    public void getBigItemByUserIdAsync(
            GetBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<GetBigItemByUserIdResult>> callback
    ) {
        GetBigItemByUserIdTask task = new GetBigItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetBigItemByUserIdResult getBigItemByUserId(
            GetBigItemByUserIdRequest request
    ) {
        final AsyncResult<GetBigItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBigItemByUserIdAsync(
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

    class AcquireBigItemByUserIdTask extends Gs2RestSessionTask<AcquireBigItemByUserIdResult> {
        private AcquireBigItemByUserIdRequest request;

        public AcquireBigItemByUserIdTask(
            AcquireBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireBigItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireBigItemByUserIdResult parse(JsonNode data) {
            return AcquireBigItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/big/inventory/{inventoryName}/item/{itemName}/acquire";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("acquireCount", request.getAcquireCount());
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

    public void acquireBigItemByUserIdAsync(
            AcquireBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireBigItemByUserIdResult>> callback
    ) {
        AcquireBigItemByUserIdTask task = new AcquireBigItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public AcquireBigItemByUserIdResult acquireBigItemByUserId(
            AcquireBigItemByUserIdRequest request
    ) {
        final AsyncResult<AcquireBigItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireBigItemByUserIdAsync(
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

    class ConsumeBigItemTask extends Gs2RestSessionTask<ConsumeBigItemResult> {
        private ConsumeBigItemRequest request;

        public ConsumeBigItemTask(
            ConsumeBigItemRequest request,
            AsyncAction<AsyncResult<ConsumeBigItemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeBigItemResult parse(JsonNode data) {
            return ConsumeBigItemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/big/inventory/{inventoryName}/item/{itemName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeCount", request.getConsumeCount());
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

    public void consumeBigItemAsync(
            ConsumeBigItemRequest request,
            AsyncAction<AsyncResult<ConsumeBigItemResult>> callback
    ) {
        ConsumeBigItemTask task = new ConsumeBigItemTask(request, callback);
        session.execute(task);
    }

    public ConsumeBigItemResult consumeBigItem(
            ConsumeBigItemRequest request
    ) {
        final AsyncResult<ConsumeBigItemResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeBigItemAsync(
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

    class ConsumeBigItemByUserIdTask extends Gs2RestSessionTask<ConsumeBigItemByUserIdResult> {
        private ConsumeBigItemByUserIdRequest request;

        public ConsumeBigItemByUserIdTask(
            ConsumeBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeBigItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeBigItemByUserIdResult parse(JsonNode data) {
            return ConsumeBigItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/big/inventory/{inventoryName}/item/{itemName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeCount", request.getConsumeCount());
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

    public void consumeBigItemByUserIdAsync(
            ConsumeBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeBigItemByUserIdResult>> callback
    ) {
        ConsumeBigItemByUserIdTask task = new ConsumeBigItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public ConsumeBigItemByUserIdResult consumeBigItemByUserId(
            ConsumeBigItemByUserIdRequest request
    ) {
        final AsyncResult<ConsumeBigItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeBigItemByUserIdAsync(
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

    class DeleteBigItemByUserIdTask extends Gs2RestSessionTask<DeleteBigItemByUserIdResult> {
        private DeleteBigItemByUserIdRequest request;

        public DeleteBigItemByUserIdTask(
            DeleteBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteBigItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBigItemByUserIdResult parse(JsonNode data) {
            return DeleteBigItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/big/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteBigItemByUserIdAsync(
            DeleteBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteBigItemByUserIdResult>> callback
    ) {
        DeleteBigItemByUserIdTask task = new DeleteBigItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteBigItemByUserIdResult deleteBigItemByUserId(
            DeleteBigItemByUserIdRequest request
    ) {
        final AsyncResult<DeleteBigItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBigItemByUserIdAsync(
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

    class VerifyBigItemTask extends Gs2RestSessionTask<VerifyBigItemResult> {
        private VerifyBigItemRequest request;

        public VerifyBigItemTask(
            VerifyBigItemRequest request,
            AsyncAction<AsyncResult<VerifyBigItemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyBigItemResult parse(JsonNode data) {
            return VerifyBigItemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/big/inventory/{inventoryName}/item/{itemName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void verifyBigItemAsync(
            VerifyBigItemRequest request,
            AsyncAction<AsyncResult<VerifyBigItemResult>> callback
    ) {
        VerifyBigItemTask task = new VerifyBigItemTask(request, callback);
        session.execute(task);
    }

    public VerifyBigItemResult verifyBigItem(
            VerifyBigItemRequest request
    ) {
        final AsyncResult<VerifyBigItemResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyBigItemAsync(
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

    class VerifyBigItemByUserIdTask extends Gs2RestSessionTask<VerifyBigItemByUserIdResult> {
        private VerifyBigItemByUserIdRequest request;

        public VerifyBigItemByUserIdTask(
            VerifyBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyBigItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyBigItemByUserIdResult parse(JsonNode data) {
            return VerifyBigItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/big/inventory/{inventoryName}/item/{itemName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null || this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null || this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void verifyBigItemByUserIdAsync(
            VerifyBigItemByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyBigItemByUserIdResult>> callback
    ) {
        VerifyBigItemByUserIdTask task = new VerifyBigItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyBigItemByUserIdResult verifyBigItemByUserId(
            VerifyBigItemByUserIdRequest request
    ) {
        final AsyncResult<VerifyBigItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyBigItemByUserIdAsync(
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

    class AcquireBigItemByStampSheetTask extends Gs2RestSessionTask<AcquireBigItemByStampSheetResult> {
        private AcquireBigItemByStampSheetRequest request;

        public AcquireBigItemByStampSheetTask(
            AcquireBigItemByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireBigItemByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireBigItemByStampSheetResult parse(JsonNode data) {
            return AcquireBigItemByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/big/item/acquire";

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

    public void acquireBigItemByStampSheetAsync(
            AcquireBigItemByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireBigItemByStampSheetResult>> callback
    ) {
        AcquireBigItemByStampSheetTask task = new AcquireBigItemByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquireBigItemByStampSheetResult acquireBigItemByStampSheet(
            AcquireBigItemByStampSheetRequest request
    ) {
        final AsyncResult<AcquireBigItemByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireBigItemByStampSheetAsync(
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

    class ConsumeBigItemByStampTaskTask extends Gs2RestSessionTask<ConsumeBigItemByStampTaskResult> {
        private ConsumeBigItemByStampTaskRequest request;

        public ConsumeBigItemByStampTaskTask(
            ConsumeBigItemByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeBigItemByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeBigItemByStampTaskResult parse(JsonNode data) {
            return ConsumeBigItemByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/big/item/consume";

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

    public void consumeBigItemByStampTaskAsync(
            ConsumeBigItemByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeBigItemByStampTaskResult>> callback
    ) {
        ConsumeBigItemByStampTaskTask task = new ConsumeBigItemByStampTaskTask(request, callback);
        session.execute(task);
    }

    public ConsumeBigItemByStampTaskResult consumeBigItemByStampTask(
            ConsumeBigItemByStampTaskRequest request
    ) {
        final AsyncResult<ConsumeBigItemByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeBigItemByStampTaskAsync(
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

    class VerifyBigItemByStampTaskTask extends Gs2RestSessionTask<VerifyBigItemByStampTaskResult> {
        private VerifyBigItemByStampTaskRequest request;

        public VerifyBigItemByStampTaskTask(
            VerifyBigItemByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyBigItemByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyBigItemByStampTaskResult parse(JsonNode data) {
            return VerifyBigItemByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/big/item/verify";

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

    public void verifyBigItemByStampTaskAsync(
            VerifyBigItemByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyBigItemByStampTaskResult>> callback
    ) {
        VerifyBigItemByStampTaskTask task = new VerifyBigItemByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyBigItemByStampTaskResult verifyBigItemByStampTask(
            VerifyBigItemByStampTaskRequest request
    ) {
        final AsyncResult<VerifyBigItemByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyBigItemByStampTaskAsync(
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