
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
}