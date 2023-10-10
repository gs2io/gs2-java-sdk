
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

package io.gs2.stamina;

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
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;
import io.gs2.stamina.model.*;public class Gs2StaminaRestClient extends AbstractGs2Client<Gs2StaminaRestClient> {

	public Gs2StaminaRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "stamina")
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("overflowTriggerScript", request.getOverflowTriggerScript() != null ? request.getOverflowTriggerScript().toJson() : null);
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
                .replace("{service}", "stamina")
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
                .replace("{service}", "stamina")
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("overflowTriggerScript", request.getOverflowTriggerScript() != null ? request.getOverflowTriggerScript().toJson() : null);
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
                .replace("{service}", "stamina")
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

    class DumpUserDataByUserIdTask extends Gs2RestSessionTask<DumpUserDataByUserIdResult> {
        private DumpUserDataByUserIdRequest request;

        public DumpUserDataByUserIdTask(
            DumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<DumpUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DumpUserDataByUserIdResult parse(JsonNode data) {
            return DumpUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/system/user/{userId}/dump";

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

            builder
                .build()
                .send();
        }
    }

    public void dumpUserDataByUserIdAsync(
            DumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<DumpUserDataByUserIdResult>> callback
    ) {
        DumpUserDataByUserIdTask task = new DumpUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public DumpUserDataByUserIdResult dumpUserDataByUserId(
            DumpUserDataByUserIdRequest request
    ) {
        final AsyncResult<DumpUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        dumpUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckDumpUserDataByUserIdTask extends Gs2RestSessionTask<CheckDumpUserDataByUserIdResult> {
        private CheckDumpUserDataByUserIdRequest request;

        public CheckDumpUserDataByUserIdTask(
            CheckDumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckDumpUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CheckDumpUserDataByUserIdResult parse(JsonNode data) {
            return CheckDumpUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/system/user/{userId}/dump";

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void checkDumpUserDataByUserIdAsync(
            CheckDumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckDumpUserDataByUserIdResult>> callback
    ) {
        CheckDumpUserDataByUserIdTask task = new CheckDumpUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CheckDumpUserDataByUserIdResult checkDumpUserDataByUserId(
            CheckDumpUserDataByUserIdRequest request
    ) {
        final AsyncResult<CheckDumpUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkDumpUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CleanUserDataByUserIdTask extends Gs2RestSessionTask<CleanUserDataByUserIdResult> {
        private CleanUserDataByUserIdRequest request;

        public CleanUserDataByUserIdTask(
            CleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CleanUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CleanUserDataByUserIdResult parse(JsonNode data) {
            return CleanUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/system/user/{userId}/clean";

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

            builder
                .build()
                .send();
        }
    }

    public void cleanUserDataByUserIdAsync(
            CleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CleanUserDataByUserIdResult>> callback
    ) {
        CleanUserDataByUserIdTask task = new CleanUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CleanUserDataByUserIdResult cleanUserDataByUserId(
            CleanUserDataByUserIdRequest request
    ) {
        final AsyncResult<CleanUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        cleanUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckCleanUserDataByUserIdTask extends Gs2RestSessionTask<CheckCleanUserDataByUserIdResult> {
        private CheckCleanUserDataByUserIdRequest request;

        public CheckCleanUserDataByUserIdTask(
            CheckCleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckCleanUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CheckCleanUserDataByUserIdResult parse(JsonNode data) {
            return CheckCleanUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/system/user/{userId}/clean";

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void checkCleanUserDataByUserIdAsync(
            CheckCleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckCleanUserDataByUserIdResult>> callback
    ) {
        CheckCleanUserDataByUserIdTask task = new CheckCleanUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CheckCleanUserDataByUserIdResult checkCleanUserDataByUserId(
            CheckCleanUserDataByUserIdRequest request
    ) {
        final AsyncResult<CheckCleanUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkCleanUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminaModelMastersTask extends Gs2RestSessionTask<DescribeStaminaModelMastersResult> {
        private DescribeStaminaModelMastersRequest request;

        public DescribeStaminaModelMastersTask(
            DescribeStaminaModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStaminaModelMastersResult parse(JsonNode data) {
            return DescribeStaminaModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
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

    public void describeStaminaModelMastersAsync(
            DescribeStaminaModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelMastersResult>> callback
    ) {
        DescribeStaminaModelMastersTask task = new DescribeStaminaModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeStaminaModelMastersResult describeStaminaModelMasters(
            DescribeStaminaModelMastersRequest request
    ) {
        final AsyncResult<DescribeStaminaModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminaModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateStaminaModelMasterTask extends Gs2RestSessionTask<CreateStaminaModelMasterResult> {
        private CreateStaminaModelMasterRequest request;

        public CreateStaminaModelMasterTask(
            CreateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStaminaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateStaminaModelMasterResult parse(JsonNode data) {
            return CreateStaminaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("recoverIntervalMinutes", request.getRecoverIntervalMinutes());
                    put("recoverValue", request.getRecoverValue());
                    put("initialCapacity", request.getInitialCapacity());
                    put("isOverflow", request.getIsOverflow());
                    put("maxCapacity", request.getMaxCapacity());
                    put("maxStaminaTableName", request.getMaxStaminaTableName());
                    put("recoverIntervalTableName", request.getRecoverIntervalTableName());
                    put("recoverValueTableName", request.getRecoverValueTableName());
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

    public void createStaminaModelMasterAsync(
            CreateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStaminaModelMasterResult>> callback
    ) {
        CreateStaminaModelMasterTask task = new CreateStaminaModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateStaminaModelMasterResult createStaminaModelMaster(
            CreateStaminaModelMasterRequest request
    ) {
        final AsyncResult<CreateStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaModelMasterTask extends Gs2RestSessionTask<GetStaminaModelMasterResult> {
        private GetStaminaModelMasterRequest request;

        public GetStaminaModelMasterTask(
            GetStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<GetStaminaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStaminaModelMasterResult parse(JsonNode data) {
            return GetStaminaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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

    public void getStaminaModelMasterAsync(
            GetStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<GetStaminaModelMasterResult>> callback
    ) {
        GetStaminaModelMasterTask task = new GetStaminaModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetStaminaModelMasterResult getStaminaModelMaster(
            GetStaminaModelMasterRequest request
    ) {
        final AsyncResult<GetStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateStaminaModelMasterTask extends Gs2RestSessionTask<UpdateStaminaModelMasterResult> {
        private UpdateStaminaModelMasterRequest request;

        public UpdateStaminaModelMasterTask(
            UpdateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStaminaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateStaminaModelMasterResult parse(JsonNode data) {
            return UpdateStaminaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("recoverIntervalMinutes", request.getRecoverIntervalMinutes());
                    put("recoverValue", request.getRecoverValue());
                    put("initialCapacity", request.getInitialCapacity());
                    put("isOverflow", request.getIsOverflow());
                    put("maxCapacity", request.getMaxCapacity());
                    put("maxStaminaTableName", request.getMaxStaminaTableName());
                    put("recoverIntervalTableName", request.getRecoverIntervalTableName());
                    put("recoverValueTableName", request.getRecoverValueTableName());
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

    public void updateStaminaModelMasterAsync(
            UpdateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStaminaModelMasterResult>> callback
    ) {
        UpdateStaminaModelMasterTask task = new UpdateStaminaModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateStaminaModelMasterResult updateStaminaModelMaster(
            UpdateStaminaModelMasterRequest request
    ) {
        final AsyncResult<UpdateStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStaminaModelMasterTask extends Gs2RestSessionTask<DeleteStaminaModelMasterResult> {
        private DeleteStaminaModelMasterRequest request;

        public DeleteStaminaModelMasterTask(
            DeleteStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStaminaModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStaminaModelMasterResult parse(JsonNode data) {
            return DeleteStaminaModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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

    public void deleteStaminaModelMasterAsync(
            DeleteStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStaminaModelMasterResult>> callback
    ) {
        DeleteStaminaModelMasterTask task = new DeleteStaminaModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteStaminaModelMasterResult deleteStaminaModelMaster(
            DeleteStaminaModelMasterRequest request
    ) {
        final AsyncResult<DeleteStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMaxStaminaTableMastersTask extends Gs2RestSessionTask<DescribeMaxStaminaTableMastersResult> {
        private DescribeMaxStaminaTableMastersRequest request;

        public DescribeMaxStaminaTableMastersTask(
            DescribeMaxStaminaTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeMaxStaminaTableMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMaxStaminaTableMastersResult parse(JsonNode data) {
            return DescribeMaxStaminaTableMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable";

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

    public void describeMaxStaminaTableMastersAsync(
            DescribeMaxStaminaTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeMaxStaminaTableMastersResult>> callback
    ) {
        DescribeMaxStaminaTableMastersTask task = new DescribeMaxStaminaTableMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeMaxStaminaTableMastersResult describeMaxStaminaTableMasters(
            DescribeMaxStaminaTableMastersRequest request
    ) {
        final AsyncResult<DescribeMaxStaminaTableMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMaxStaminaTableMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateMaxStaminaTableMasterTask extends Gs2RestSessionTask<CreateMaxStaminaTableMasterResult> {
        private CreateMaxStaminaTableMasterRequest request;

        public CreateMaxStaminaTableMasterTask(
            CreateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<CreateMaxStaminaTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateMaxStaminaTableMasterResult parse(JsonNode data) {
            return CreateMaxStaminaTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("experienceModelId", request.getExperienceModelId());
                    put("values", request.getValues() == null ? new ArrayList<Integer>() :
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

    public void createMaxStaminaTableMasterAsync(
            CreateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<CreateMaxStaminaTableMasterResult>> callback
    ) {
        CreateMaxStaminaTableMasterTask task = new CreateMaxStaminaTableMasterTask(request, callback);
        session.execute(task);
    }

    public CreateMaxStaminaTableMasterResult createMaxStaminaTableMaster(
            CreateMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<CreateMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMaxStaminaTableMasterTask extends Gs2RestSessionTask<GetMaxStaminaTableMasterResult> {
        private GetMaxStaminaTableMasterRequest request;

        public GetMaxStaminaTableMasterTask(
            GetMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<GetMaxStaminaTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMaxStaminaTableMasterResult parse(JsonNode data) {
            return GetMaxStaminaTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable/{maxStaminaTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{maxStaminaTableName}", this.request.getMaxStaminaTableName() == null || this.request.getMaxStaminaTableName().length() == 0 ? "null" : String.valueOf(this.request.getMaxStaminaTableName()));

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

    public void getMaxStaminaTableMasterAsync(
            GetMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<GetMaxStaminaTableMasterResult>> callback
    ) {
        GetMaxStaminaTableMasterTask task = new GetMaxStaminaTableMasterTask(request, callback);
        session.execute(task);
    }

    public GetMaxStaminaTableMasterResult getMaxStaminaTableMaster(
            GetMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<GetMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateMaxStaminaTableMasterTask extends Gs2RestSessionTask<UpdateMaxStaminaTableMasterResult> {
        private UpdateMaxStaminaTableMasterRequest request;

        public UpdateMaxStaminaTableMasterTask(
            UpdateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateMaxStaminaTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateMaxStaminaTableMasterResult parse(JsonNode data) {
            return UpdateMaxStaminaTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable/{maxStaminaTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{maxStaminaTableName}", this.request.getMaxStaminaTableName() == null || this.request.getMaxStaminaTableName().length() == 0 ? "null" : String.valueOf(this.request.getMaxStaminaTableName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("experienceModelId", request.getExperienceModelId());
                    put("values", request.getValues() == null ? new ArrayList<Integer>() :
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

    public void updateMaxStaminaTableMasterAsync(
            UpdateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateMaxStaminaTableMasterResult>> callback
    ) {
        UpdateMaxStaminaTableMasterTask task = new UpdateMaxStaminaTableMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateMaxStaminaTableMasterResult updateMaxStaminaTableMaster(
            UpdateMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<UpdateMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMaxStaminaTableMasterTask extends Gs2RestSessionTask<DeleteMaxStaminaTableMasterResult> {
        private DeleteMaxStaminaTableMasterRequest request;

        public DeleteMaxStaminaTableMasterTask(
            DeleteMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteMaxStaminaTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMaxStaminaTableMasterResult parse(JsonNode data) {
            return DeleteMaxStaminaTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable/{maxStaminaTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{maxStaminaTableName}", this.request.getMaxStaminaTableName() == null || this.request.getMaxStaminaTableName().length() == 0 ? "null" : String.valueOf(this.request.getMaxStaminaTableName()));

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

    public void deleteMaxStaminaTableMasterAsync(
            DeleteMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteMaxStaminaTableMasterResult>> callback
    ) {
        DeleteMaxStaminaTableMasterTask task = new DeleteMaxStaminaTableMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteMaxStaminaTableMasterResult deleteMaxStaminaTableMaster(
            DeleteMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<DeleteMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRecoverIntervalTableMastersTask extends Gs2RestSessionTask<DescribeRecoverIntervalTableMastersResult> {
        private DescribeRecoverIntervalTableMastersRequest request;

        public DescribeRecoverIntervalTableMastersTask(
            DescribeRecoverIntervalTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeRecoverIntervalTableMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRecoverIntervalTableMastersResult parse(JsonNode data) {
            return DescribeRecoverIntervalTableMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverIntervalTable";

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

    public void describeRecoverIntervalTableMastersAsync(
            DescribeRecoverIntervalTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeRecoverIntervalTableMastersResult>> callback
    ) {
        DescribeRecoverIntervalTableMastersTask task = new DescribeRecoverIntervalTableMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeRecoverIntervalTableMastersResult describeRecoverIntervalTableMasters(
            DescribeRecoverIntervalTableMastersRequest request
    ) {
        final AsyncResult<DescribeRecoverIntervalTableMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRecoverIntervalTableMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRecoverIntervalTableMasterTask extends Gs2RestSessionTask<CreateRecoverIntervalTableMasterResult> {
        private CreateRecoverIntervalTableMasterRequest request;

        public CreateRecoverIntervalTableMasterTask(
            CreateRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<CreateRecoverIntervalTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateRecoverIntervalTableMasterResult parse(JsonNode data) {
            return CreateRecoverIntervalTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverIntervalTable";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("experienceModelId", request.getExperienceModelId());
                    put("values", request.getValues() == null ? new ArrayList<Integer>() :
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

    public void createRecoverIntervalTableMasterAsync(
            CreateRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<CreateRecoverIntervalTableMasterResult>> callback
    ) {
        CreateRecoverIntervalTableMasterTask task = new CreateRecoverIntervalTableMasterTask(request, callback);
        session.execute(task);
    }

    public CreateRecoverIntervalTableMasterResult createRecoverIntervalTableMaster(
            CreateRecoverIntervalTableMasterRequest request
    ) {
        final AsyncResult<CreateRecoverIntervalTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRecoverIntervalTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRecoverIntervalTableMasterTask extends Gs2RestSessionTask<GetRecoverIntervalTableMasterResult> {
        private GetRecoverIntervalTableMasterRequest request;

        public GetRecoverIntervalTableMasterTask(
            GetRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<GetRecoverIntervalTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRecoverIntervalTableMasterResult parse(JsonNode data) {
            return GetRecoverIntervalTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverIntervalTable/{recoverIntervalTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{recoverIntervalTableName}", this.request.getRecoverIntervalTableName() == null || this.request.getRecoverIntervalTableName().length() == 0 ? "null" : String.valueOf(this.request.getRecoverIntervalTableName()));

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

    public void getRecoverIntervalTableMasterAsync(
            GetRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<GetRecoverIntervalTableMasterResult>> callback
    ) {
        GetRecoverIntervalTableMasterTask task = new GetRecoverIntervalTableMasterTask(request, callback);
        session.execute(task);
    }

    public GetRecoverIntervalTableMasterResult getRecoverIntervalTableMaster(
            GetRecoverIntervalTableMasterRequest request
    ) {
        final AsyncResult<GetRecoverIntervalTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRecoverIntervalTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRecoverIntervalTableMasterTask extends Gs2RestSessionTask<UpdateRecoverIntervalTableMasterResult> {
        private UpdateRecoverIntervalTableMasterRequest request;

        public UpdateRecoverIntervalTableMasterTask(
            UpdateRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateRecoverIntervalTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateRecoverIntervalTableMasterResult parse(JsonNode data) {
            return UpdateRecoverIntervalTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverIntervalTable/{recoverIntervalTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{recoverIntervalTableName}", this.request.getRecoverIntervalTableName() == null || this.request.getRecoverIntervalTableName().length() == 0 ? "null" : String.valueOf(this.request.getRecoverIntervalTableName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("experienceModelId", request.getExperienceModelId());
                    put("values", request.getValues() == null ? new ArrayList<Integer>() :
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

    public void updateRecoverIntervalTableMasterAsync(
            UpdateRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateRecoverIntervalTableMasterResult>> callback
    ) {
        UpdateRecoverIntervalTableMasterTask task = new UpdateRecoverIntervalTableMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateRecoverIntervalTableMasterResult updateRecoverIntervalTableMaster(
            UpdateRecoverIntervalTableMasterRequest request
    ) {
        final AsyncResult<UpdateRecoverIntervalTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRecoverIntervalTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRecoverIntervalTableMasterTask extends Gs2RestSessionTask<DeleteRecoverIntervalTableMasterResult> {
        private DeleteRecoverIntervalTableMasterRequest request;

        public DeleteRecoverIntervalTableMasterTask(
            DeleteRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteRecoverIntervalTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRecoverIntervalTableMasterResult parse(JsonNode data) {
            return DeleteRecoverIntervalTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverIntervalTable/{recoverIntervalTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{recoverIntervalTableName}", this.request.getRecoverIntervalTableName() == null || this.request.getRecoverIntervalTableName().length() == 0 ? "null" : String.valueOf(this.request.getRecoverIntervalTableName()));

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

    public void deleteRecoverIntervalTableMasterAsync(
            DeleteRecoverIntervalTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteRecoverIntervalTableMasterResult>> callback
    ) {
        DeleteRecoverIntervalTableMasterTask task = new DeleteRecoverIntervalTableMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteRecoverIntervalTableMasterResult deleteRecoverIntervalTableMaster(
            DeleteRecoverIntervalTableMasterRequest request
    ) {
        final AsyncResult<DeleteRecoverIntervalTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRecoverIntervalTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRecoverValueTableMastersTask extends Gs2RestSessionTask<DescribeRecoverValueTableMastersResult> {
        private DescribeRecoverValueTableMastersRequest request;

        public DescribeRecoverValueTableMastersTask(
            DescribeRecoverValueTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeRecoverValueTableMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRecoverValueTableMastersResult parse(JsonNode data) {
            return DescribeRecoverValueTableMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverValueTable";

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

    public void describeRecoverValueTableMastersAsync(
            DescribeRecoverValueTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeRecoverValueTableMastersResult>> callback
    ) {
        DescribeRecoverValueTableMastersTask task = new DescribeRecoverValueTableMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeRecoverValueTableMastersResult describeRecoverValueTableMasters(
            DescribeRecoverValueTableMastersRequest request
    ) {
        final AsyncResult<DescribeRecoverValueTableMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRecoverValueTableMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRecoverValueTableMasterTask extends Gs2RestSessionTask<CreateRecoverValueTableMasterResult> {
        private CreateRecoverValueTableMasterRequest request;

        public CreateRecoverValueTableMasterTask(
            CreateRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<CreateRecoverValueTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateRecoverValueTableMasterResult parse(JsonNode data) {
            return CreateRecoverValueTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverValueTable";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("experienceModelId", request.getExperienceModelId());
                    put("values", request.getValues() == null ? new ArrayList<Integer>() :
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

    public void createRecoverValueTableMasterAsync(
            CreateRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<CreateRecoverValueTableMasterResult>> callback
    ) {
        CreateRecoverValueTableMasterTask task = new CreateRecoverValueTableMasterTask(request, callback);
        session.execute(task);
    }

    public CreateRecoverValueTableMasterResult createRecoverValueTableMaster(
            CreateRecoverValueTableMasterRequest request
    ) {
        final AsyncResult<CreateRecoverValueTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRecoverValueTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRecoverValueTableMasterTask extends Gs2RestSessionTask<GetRecoverValueTableMasterResult> {
        private GetRecoverValueTableMasterRequest request;

        public GetRecoverValueTableMasterTask(
            GetRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<GetRecoverValueTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRecoverValueTableMasterResult parse(JsonNode data) {
            return GetRecoverValueTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverValueTable/{recoverValueTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{recoverValueTableName}", this.request.getRecoverValueTableName() == null || this.request.getRecoverValueTableName().length() == 0 ? "null" : String.valueOf(this.request.getRecoverValueTableName()));

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

    public void getRecoverValueTableMasterAsync(
            GetRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<GetRecoverValueTableMasterResult>> callback
    ) {
        GetRecoverValueTableMasterTask task = new GetRecoverValueTableMasterTask(request, callback);
        session.execute(task);
    }

    public GetRecoverValueTableMasterResult getRecoverValueTableMaster(
            GetRecoverValueTableMasterRequest request
    ) {
        final AsyncResult<GetRecoverValueTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRecoverValueTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRecoverValueTableMasterTask extends Gs2RestSessionTask<UpdateRecoverValueTableMasterResult> {
        private UpdateRecoverValueTableMasterRequest request;

        public UpdateRecoverValueTableMasterTask(
            UpdateRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateRecoverValueTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateRecoverValueTableMasterResult parse(JsonNode data) {
            return UpdateRecoverValueTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverValueTable/{recoverValueTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{recoverValueTableName}", this.request.getRecoverValueTableName() == null || this.request.getRecoverValueTableName().length() == 0 ? "null" : String.valueOf(this.request.getRecoverValueTableName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("experienceModelId", request.getExperienceModelId());
                    put("values", request.getValues() == null ? new ArrayList<Integer>() :
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

    public void updateRecoverValueTableMasterAsync(
            UpdateRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateRecoverValueTableMasterResult>> callback
    ) {
        UpdateRecoverValueTableMasterTask task = new UpdateRecoverValueTableMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateRecoverValueTableMasterResult updateRecoverValueTableMaster(
            UpdateRecoverValueTableMasterRequest request
    ) {
        final AsyncResult<UpdateRecoverValueTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRecoverValueTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRecoverValueTableMasterTask extends Gs2RestSessionTask<DeleteRecoverValueTableMasterResult> {
        private DeleteRecoverValueTableMasterRequest request;

        public DeleteRecoverValueTableMasterTask(
            DeleteRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteRecoverValueTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRecoverValueTableMasterResult parse(JsonNode data) {
            return DeleteRecoverValueTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/recoverValueTable/{recoverValueTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{recoverValueTableName}", this.request.getRecoverValueTableName() == null || this.request.getRecoverValueTableName().length() == 0 ? "null" : String.valueOf(this.request.getRecoverValueTableName()));

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

    public void deleteRecoverValueTableMasterAsync(
            DeleteRecoverValueTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteRecoverValueTableMasterResult>> callback
    ) {
        DeleteRecoverValueTableMasterTask task = new DeleteRecoverValueTableMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteRecoverValueTableMasterResult deleteRecoverValueTableMaster(
            DeleteRecoverValueTableMasterRequest request
    ) {
        final AsyncResult<DeleteRecoverValueTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRecoverValueTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "stamina")
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

    class GetCurrentStaminaMasterTask extends Gs2RestSessionTask<GetCurrentStaminaMasterResult> {
        private GetCurrentStaminaMasterRequest request;

        public GetCurrentStaminaMasterTask(
            GetCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentStaminaMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentStaminaMasterResult parse(JsonNode data) {
            return GetCurrentStaminaMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
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

    public void getCurrentStaminaMasterAsync(
            GetCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentStaminaMasterResult>> callback
    ) {
        GetCurrentStaminaMasterTask task = new GetCurrentStaminaMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentStaminaMasterResult getCurrentStaminaMaster(
            GetCurrentStaminaMasterRequest request
    ) {
        final AsyncResult<GetCurrentStaminaMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentStaminaMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentStaminaMasterTask extends Gs2RestSessionTask<UpdateCurrentStaminaMasterResult> {
        private UpdateCurrentStaminaMasterRequest request;

        public UpdateCurrentStaminaMasterTask(
            UpdateCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentStaminaMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentStaminaMasterResult parse(JsonNode data) {
            return UpdateCurrentStaminaMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
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

    public void updateCurrentStaminaMasterAsync(
            UpdateCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentStaminaMasterResult>> callback
    ) {
        UpdateCurrentStaminaMasterTask task = new UpdateCurrentStaminaMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentStaminaMasterResult updateCurrentStaminaMaster(
            UpdateCurrentStaminaMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentStaminaMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentStaminaMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentStaminaMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentStaminaMasterFromGitHubResult> {
        private UpdateCurrentStaminaMasterFromGitHubRequest request;

        public UpdateCurrentStaminaMasterFromGitHubTask(
            UpdateCurrentStaminaMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentStaminaMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentStaminaMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentStaminaMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
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

    public void updateCurrentStaminaMasterFromGitHubAsync(
            UpdateCurrentStaminaMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentStaminaMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentStaminaMasterFromGitHubTask task = new UpdateCurrentStaminaMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentStaminaMasterFromGitHubResult updateCurrentStaminaMasterFromGitHub(
            UpdateCurrentStaminaMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentStaminaMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentStaminaMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminaModelsTask extends Gs2RestSessionTask<DescribeStaminaModelsResult> {
        private DescribeStaminaModelsRequest request;

        public DescribeStaminaModelsTask(
            DescribeStaminaModelsRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStaminaModelsResult parse(JsonNode data) {
            return DescribeStaminaModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
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

    public void describeStaminaModelsAsync(
            DescribeStaminaModelsRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelsResult>> callback
    ) {
        DescribeStaminaModelsTask task = new DescribeStaminaModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeStaminaModelsResult describeStaminaModels(
            DescribeStaminaModelsRequest request
    ) {
        final AsyncResult<DescribeStaminaModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminaModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaModelTask extends Gs2RestSessionTask<GetStaminaModelResult> {
        private GetStaminaModelRequest request;

        public GetStaminaModelTask(
            GetStaminaModelRequest request,
            AsyncAction<AsyncResult<GetStaminaModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStaminaModelResult parse(JsonNode data) {
            return GetStaminaModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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

    public void getStaminaModelAsync(
            GetStaminaModelRequest request,
            AsyncAction<AsyncResult<GetStaminaModelResult>> callback
    ) {
        GetStaminaModelTask task = new GetStaminaModelTask(request, callback);
        session.execute(task);
    }

    public GetStaminaModelResult getStaminaModel(
            GetStaminaModelRequest request
    ) {
        final AsyncResult<GetStaminaModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminasTask extends Gs2RestSessionTask<DescribeStaminasResult> {
        private DescribeStaminasRequest request;

        public DescribeStaminasTask(
            DescribeStaminasRequest request,
            AsyncAction<AsyncResult<DescribeStaminasResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStaminasResult parse(JsonNode data) {
            return DescribeStaminasResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina";

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

    public void describeStaminasAsync(
            DescribeStaminasRequest request,
            AsyncAction<AsyncResult<DescribeStaminasResult>> callback
    ) {
        DescribeStaminasTask task = new DescribeStaminasTask(request, callback);
        session.execute(task);
    }

    public DescribeStaminasResult describeStaminas(
            DescribeStaminasRequest request
    ) {
        final AsyncResult<DescribeStaminasResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminasAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminasByUserIdTask extends Gs2RestSessionTask<DescribeStaminasByUserIdResult> {
        private DescribeStaminasByUserIdRequest request;

        public DescribeStaminasByUserIdTask(
            DescribeStaminasByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStaminasByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStaminasByUserIdResult parse(JsonNode data) {
            return DescribeStaminasByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina";

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

    public void describeStaminasByUserIdAsync(
            DescribeStaminasByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStaminasByUserIdResult>> callback
    ) {
        DescribeStaminasByUserIdTask task = new DescribeStaminasByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeStaminasByUserIdResult describeStaminasByUserId(
            DescribeStaminasByUserIdRequest request
    ) {
        final AsyncResult<DescribeStaminasByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminasByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaTask extends Gs2RestSessionTask<GetStaminaResult> {
        private GetStaminaRequest request;

        public GetStaminaTask(
            GetStaminaRequest request,
            AsyncAction<AsyncResult<GetStaminaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStaminaResult parse(JsonNode data) {
            return GetStaminaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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

    public void getStaminaAsync(
            GetStaminaRequest request,
            AsyncAction<AsyncResult<GetStaminaResult>> callback
    ) {
        GetStaminaTask task = new GetStaminaTask(request, callback);
        session.execute(task);
    }

    public GetStaminaResult getStamina(
            GetStaminaRequest request
    ) {
        final AsyncResult<GetStaminaResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaByUserIdTask extends Gs2RestSessionTask<GetStaminaByUserIdResult> {
        private GetStaminaByUserIdRequest request;

        public GetStaminaByUserIdTask(
            GetStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<GetStaminaByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStaminaByUserIdResult parse(JsonNode data) {
            return GetStaminaByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
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

    public void getStaminaByUserIdAsync(
            GetStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<GetStaminaByUserIdResult>> callback
    ) {
        GetStaminaByUserIdTask task = new GetStaminaByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetStaminaByUserIdResult getStaminaByUserId(
            GetStaminaByUserIdRequest request
    ) {
        final AsyncResult<GetStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateStaminaByUserIdTask extends Gs2RestSessionTask<UpdateStaminaByUserIdResult> {
        private UpdateStaminaByUserIdRequest request;

        public UpdateStaminaByUserIdTask(
            UpdateStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateStaminaByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateStaminaByUserIdResult parse(JsonNode data) {
            return UpdateStaminaByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
                    put("maxValue", request.getMaxValue());
                    put("recoverIntervalMinutes", request.getRecoverIntervalMinutes());
                    put("recoverValue", request.getRecoverValue());
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

    public void updateStaminaByUserIdAsync(
            UpdateStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateStaminaByUserIdResult>> callback
    ) {
        UpdateStaminaByUserIdTask task = new UpdateStaminaByUserIdTask(request, callback);
        session.execute(task);
    }

    public UpdateStaminaByUserIdResult updateStaminaByUserId(
            UpdateStaminaByUserIdRequest request
    ) {
        final AsyncResult<UpdateStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeStaminaTask extends Gs2RestSessionTask<ConsumeStaminaResult> {
        private ConsumeStaminaRequest request;

        public ConsumeStaminaTask(
            ConsumeStaminaRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeStaminaResult parse(JsonNode data) {
            return ConsumeStaminaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeValue", request.getConsumeValue());
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

    public void consumeStaminaAsync(
            ConsumeStaminaRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaResult>> callback
    ) {
        ConsumeStaminaTask task = new ConsumeStaminaTask(request, callback);
        session.execute(task);
    }

    public ConsumeStaminaResult consumeStamina(
            ConsumeStaminaRequest request
    ) {
        final AsyncResult<ConsumeStaminaResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeStaminaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeStaminaByUserIdTask extends Gs2RestSessionTask<ConsumeStaminaByUserIdResult> {
        private ConsumeStaminaByUserIdRequest request;

        public ConsumeStaminaByUserIdTask(
            ConsumeStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeStaminaByUserIdResult parse(JsonNode data) {
            return ConsumeStaminaByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("consumeValue", request.getConsumeValue());
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

    public void consumeStaminaByUserIdAsync(
            ConsumeStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByUserIdResult>> callback
    ) {
        ConsumeStaminaByUserIdTask task = new ConsumeStaminaByUserIdTask(request, callback);
        session.execute(task);
    }

    public ConsumeStaminaByUserIdResult consumeStaminaByUserId(
            ConsumeStaminaByUserIdRequest request
    ) {
        final AsyncResult<ConsumeStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RecoverStaminaByUserIdTask extends Gs2RestSessionTask<RecoverStaminaByUserIdResult> {
        private RecoverStaminaByUserIdRequest request;

        public RecoverStaminaByUserIdTask(
            RecoverStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RecoverStaminaByUserIdResult parse(JsonNode data) {
            return RecoverStaminaByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/recover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("recoverValue", request.getRecoverValue());
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

    public void recoverStaminaByUserIdAsync(
            RecoverStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByUserIdResult>> callback
    ) {
        RecoverStaminaByUserIdTask task = new RecoverStaminaByUserIdTask(request, callback);
        session.execute(task);
    }

    public RecoverStaminaByUserIdResult recoverStaminaByUserId(
            RecoverStaminaByUserIdRequest request
    ) {
        final AsyncResult<RecoverStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        recoverStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RaiseMaxValueByUserIdTask extends Gs2RestSessionTask<RaiseMaxValueByUserIdResult> {
        private RaiseMaxValueByUserIdRequest request;

        public RaiseMaxValueByUserIdTask(
            RaiseMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RaiseMaxValueByUserIdResult parse(JsonNode data) {
            return RaiseMaxValueByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/raise";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("raiseValue", request.getRaiseValue());
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

    public void raiseMaxValueByUserIdAsync(
            RaiseMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByUserIdResult>> callback
    ) {
        RaiseMaxValueByUserIdTask task = new RaiseMaxValueByUserIdTask(request, callback);
        session.execute(task);
    }

    public RaiseMaxValueByUserIdResult raiseMaxValueByUserId(
            RaiseMaxValueByUserIdRequest request
    ) {
        final AsyncResult<RaiseMaxValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        raiseMaxValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecreaseMaxValueByUserIdTask extends Gs2RestSessionTask<DecreaseMaxValueByUserIdResult> {
        private DecreaseMaxValueByUserIdRequest request;

        public DecreaseMaxValueByUserIdTask(
            DecreaseMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<DecreaseMaxValueByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseMaxValueByUserIdResult parse(JsonNode data) {
            return DecreaseMaxValueByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/decrease";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("decreaseValue", request.getDecreaseValue());
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

    public void decreaseMaxValueByUserIdAsync(
            DecreaseMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<DecreaseMaxValueByUserIdResult>> callback
    ) {
        DecreaseMaxValueByUserIdTask task = new DecreaseMaxValueByUserIdTask(request, callback);
        session.execute(task);
    }

    public DecreaseMaxValueByUserIdResult decreaseMaxValueByUserId(
            DecreaseMaxValueByUserIdRequest request
    ) {
        final AsyncResult<DecreaseMaxValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseMaxValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMaxValueByUserIdTask extends Gs2RestSessionTask<SetMaxValueByUserIdResult> {
        private SetMaxValueByUserIdRequest request;

        public SetMaxValueByUserIdTask(
            SetMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<SetMaxValueByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetMaxValueByUserIdResult parse(JsonNode data) {
            return SetMaxValueByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("maxValue", request.getMaxValue());
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

    public void setMaxValueByUserIdAsync(
            SetMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<SetMaxValueByUserIdResult>> callback
    ) {
        SetMaxValueByUserIdTask task = new SetMaxValueByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetMaxValueByUserIdResult setMaxValueByUserId(
            SetMaxValueByUserIdRequest request
    ) {
        final AsyncResult<SetMaxValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaxValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRecoverIntervalByUserIdTask extends Gs2RestSessionTask<SetRecoverIntervalByUserIdResult> {
        private SetRecoverIntervalByUserIdRequest request;

        public SetRecoverIntervalByUserIdTask(
            SetRecoverIntervalByUserIdRequest request,
            AsyncAction<AsyncResult<SetRecoverIntervalByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRecoverIntervalByUserIdResult parse(JsonNode data) {
            return SetRecoverIntervalByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/recoverInterval/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("recoverIntervalMinutes", request.getRecoverIntervalMinutes());
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

    public void setRecoverIntervalByUserIdAsync(
            SetRecoverIntervalByUserIdRequest request,
            AsyncAction<AsyncResult<SetRecoverIntervalByUserIdResult>> callback
    ) {
        SetRecoverIntervalByUserIdTask task = new SetRecoverIntervalByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetRecoverIntervalByUserIdResult setRecoverIntervalByUserId(
            SetRecoverIntervalByUserIdRequest request
    ) {
        final AsyncResult<SetRecoverIntervalByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRecoverIntervalByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRecoverValueByUserIdTask extends Gs2RestSessionTask<SetRecoverValueByUserIdResult> {
        private SetRecoverValueByUserIdRequest request;

        public SetRecoverValueByUserIdTask(
            SetRecoverValueByUserIdRequest request,
            AsyncAction<AsyncResult<SetRecoverValueByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRecoverValueByUserIdResult parse(JsonNode data) {
            return SetRecoverValueByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/recoverValue/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("recoverValue", request.getRecoverValue());
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

    public void setRecoverValueByUserIdAsync(
            SetRecoverValueByUserIdRequest request,
            AsyncAction<AsyncResult<SetRecoverValueByUserIdResult>> callback
    ) {
        SetRecoverValueByUserIdTask task = new SetRecoverValueByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetRecoverValueByUserIdResult setRecoverValueByUserId(
            SetRecoverValueByUserIdRequest request
    ) {
        final AsyncResult<SetRecoverValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRecoverValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMaxValueByStatusTask extends Gs2RestSessionTask<SetMaxValueByStatusResult> {
        private SetMaxValueByStatusRequest request;

        public SetMaxValueByStatusTask(
            SetMaxValueByStatusRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetMaxValueByStatusResult parse(JsonNode data) {
            return SetMaxValueByStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("keyId", request.getKeyId());
                    put("signedStatusBody", request.getSignedStatusBody());
                    put("signedStatusSignature", request.getSignedStatusSignature());
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

    public void setMaxValueByStatusAsync(
            SetMaxValueByStatusRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStatusResult>> callback
    ) {
        SetMaxValueByStatusTask task = new SetMaxValueByStatusTask(request, callback);
        session.execute(task);
    }

    public SetMaxValueByStatusResult setMaxValueByStatus(
            SetMaxValueByStatusRequest request
    ) {
        final AsyncResult<SetMaxValueByStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaxValueByStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRecoverIntervalByStatusTask extends Gs2RestSessionTask<SetRecoverIntervalByStatusResult> {
        private SetRecoverIntervalByStatusRequest request;

        public SetRecoverIntervalByStatusTask(
            SetRecoverIntervalByStatusRequest request,
            AsyncAction<AsyncResult<SetRecoverIntervalByStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRecoverIntervalByStatusResult parse(JsonNode data) {
            return SetRecoverIntervalByStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}/recoverInterval/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("keyId", request.getKeyId());
                    put("signedStatusBody", request.getSignedStatusBody());
                    put("signedStatusSignature", request.getSignedStatusSignature());
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

    public void setRecoverIntervalByStatusAsync(
            SetRecoverIntervalByStatusRequest request,
            AsyncAction<AsyncResult<SetRecoverIntervalByStatusResult>> callback
    ) {
        SetRecoverIntervalByStatusTask task = new SetRecoverIntervalByStatusTask(request, callback);
        session.execute(task);
    }

    public SetRecoverIntervalByStatusResult setRecoverIntervalByStatus(
            SetRecoverIntervalByStatusRequest request
    ) {
        final AsyncResult<SetRecoverIntervalByStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRecoverIntervalByStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRecoverValueByStatusTask extends Gs2RestSessionTask<SetRecoverValueByStatusResult> {
        private SetRecoverValueByStatusRequest request;

        public SetRecoverValueByStatusTask(
            SetRecoverValueByStatusRequest request,
            AsyncAction<AsyncResult<SetRecoverValueByStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRecoverValueByStatusResult parse(JsonNode data) {
            return SetRecoverValueByStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}/recoverValue/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("keyId", request.getKeyId());
                    put("signedStatusBody", request.getSignedStatusBody());
                    put("signedStatusSignature", request.getSignedStatusSignature());
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

    public void setRecoverValueByStatusAsync(
            SetRecoverValueByStatusRequest request,
            AsyncAction<AsyncResult<SetRecoverValueByStatusResult>> callback
    ) {
        SetRecoverValueByStatusTask task = new SetRecoverValueByStatusTask(request, callback);
        session.execute(task);
    }

    public SetRecoverValueByStatusResult setRecoverValueByStatus(
            SetRecoverValueByStatusRequest request
    ) {
        final AsyncResult<SetRecoverValueByStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRecoverValueByStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStaminaByUserIdTask extends Gs2RestSessionTask<DeleteStaminaByUserIdResult> {
        private DeleteStaminaByUserIdRequest request;

        public DeleteStaminaByUserIdTask(
            DeleteStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStaminaByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStaminaByUserIdResult parse(JsonNode data) {
            return DeleteStaminaByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null || this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
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

    public void deleteStaminaByUserIdAsync(
            DeleteStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStaminaByUserIdResult>> callback
    ) {
        DeleteStaminaByUserIdTask task = new DeleteStaminaByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteStaminaByUserIdResult deleteStaminaByUserId(
            DeleteStaminaByUserIdRequest request
    ) {
        final AsyncResult<DeleteStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RecoverStaminaByStampSheetTask extends Gs2RestSessionTask<RecoverStaminaByStampSheetResult> {
        private RecoverStaminaByStampSheetRequest request;

        public RecoverStaminaByStampSheetTask(
            RecoverStaminaByStampSheetRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RecoverStaminaByStampSheetResult parse(JsonNode data) {
            return RecoverStaminaByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/recover";

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

    public void recoverStaminaByStampSheetAsync(
            RecoverStaminaByStampSheetRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByStampSheetResult>> callback
    ) {
        RecoverStaminaByStampSheetTask task = new RecoverStaminaByStampSheetTask(request, callback);
        session.execute(task);
    }

    public RecoverStaminaByStampSheetResult recoverStaminaByStampSheet(
            RecoverStaminaByStampSheetRequest request
    ) {
        final AsyncResult<RecoverStaminaByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        recoverStaminaByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RaiseMaxValueByStampSheetTask extends Gs2RestSessionTask<RaiseMaxValueByStampSheetResult> {
        private RaiseMaxValueByStampSheetRequest request;

        public RaiseMaxValueByStampSheetTask(
            RaiseMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RaiseMaxValueByStampSheetResult parse(JsonNode data) {
            return RaiseMaxValueByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/raise";

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

    public void raiseMaxValueByStampSheetAsync(
            RaiseMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByStampSheetResult>> callback
    ) {
        RaiseMaxValueByStampSheetTask task = new RaiseMaxValueByStampSheetTask(request, callback);
        session.execute(task);
    }

    public RaiseMaxValueByStampSheetResult raiseMaxValueByStampSheet(
            RaiseMaxValueByStampSheetRequest request
    ) {
        final AsyncResult<RaiseMaxValueByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        raiseMaxValueByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecreaseMaxValueByStampTaskTask extends Gs2RestSessionTask<DecreaseMaxValueByStampTaskResult> {
        private DecreaseMaxValueByStampTaskRequest request;

        public DecreaseMaxValueByStampTaskTask(
            DecreaseMaxValueByStampTaskRequest request,
            AsyncAction<AsyncResult<DecreaseMaxValueByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseMaxValueByStampTaskResult parse(JsonNode data) {
            return DecreaseMaxValueByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/decrease";

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

    public void decreaseMaxValueByStampTaskAsync(
            DecreaseMaxValueByStampTaskRequest request,
            AsyncAction<AsyncResult<DecreaseMaxValueByStampTaskResult>> callback
    ) {
        DecreaseMaxValueByStampTaskTask task = new DecreaseMaxValueByStampTaskTask(request, callback);
        session.execute(task);
    }

    public DecreaseMaxValueByStampTaskResult decreaseMaxValueByStampTask(
            DecreaseMaxValueByStampTaskRequest request
    ) {
        final AsyncResult<DecreaseMaxValueByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseMaxValueByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMaxValueByStampSheetTask extends Gs2RestSessionTask<SetMaxValueByStampSheetResult> {
        private SetMaxValueByStampSheetRequest request;

        public SetMaxValueByStampSheetTask(
            SetMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetMaxValueByStampSheetResult parse(JsonNode data) {
            return SetMaxValueByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/max/set";

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

    public void setMaxValueByStampSheetAsync(
            SetMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStampSheetResult>> callback
    ) {
        SetMaxValueByStampSheetTask task = new SetMaxValueByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetMaxValueByStampSheetResult setMaxValueByStampSheet(
            SetMaxValueByStampSheetRequest request
    ) {
        final AsyncResult<SetMaxValueByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaxValueByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRecoverIntervalByStampSheetTask extends Gs2RestSessionTask<SetRecoverIntervalByStampSheetResult> {
        private SetRecoverIntervalByStampSheetRequest request;

        public SetRecoverIntervalByStampSheetTask(
            SetRecoverIntervalByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRecoverIntervalByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRecoverIntervalByStampSheetResult parse(JsonNode data) {
            return SetRecoverIntervalByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/recoverInterval/set";

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

    public void setRecoverIntervalByStampSheetAsync(
            SetRecoverIntervalByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRecoverIntervalByStampSheetResult>> callback
    ) {
        SetRecoverIntervalByStampSheetTask task = new SetRecoverIntervalByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetRecoverIntervalByStampSheetResult setRecoverIntervalByStampSheet(
            SetRecoverIntervalByStampSheetRequest request
    ) {
        final AsyncResult<SetRecoverIntervalByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRecoverIntervalByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRecoverValueByStampSheetTask extends Gs2RestSessionTask<SetRecoverValueByStampSheetResult> {
        private SetRecoverValueByStampSheetRequest request;

        public SetRecoverValueByStampSheetTask(
            SetRecoverValueByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRecoverValueByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRecoverValueByStampSheetResult parse(JsonNode data) {
            return SetRecoverValueByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/recoverValue/set";

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

    public void setRecoverValueByStampSheetAsync(
            SetRecoverValueByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRecoverValueByStampSheetResult>> callback
    ) {
        SetRecoverValueByStampSheetTask task = new SetRecoverValueByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetRecoverValueByStampSheetResult setRecoverValueByStampSheet(
            SetRecoverValueByStampSheetRequest request
    ) {
        final AsyncResult<SetRecoverValueByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRecoverValueByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeStaminaByStampTaskTask extends Gs2RestSessionTask<ConsumeStaminaByStampTaskResult> {
        private ConsumeStaminaByStampTaskRequest request;

        public ConsumeStaminaByStampTaskTask(
            ConsumeStaminaByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumeStaminaByStampTaskResult parse(JsonNode data) {
            return ConsumeStaminaByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/consume";

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

    public void consumeStaminaByStampTaskAsync(
            ConsumeStaminaByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByStampTaskResult>> callback
    ) {
        ConsumeStaminaByStampTaskTask task = new ConsumeStaminaByStampTaskTask(request, callback);
        session.execute(task);
    }

    public ConsumeStaminaByStampTaskResult consumeStaminaByStampTask(
            ConsumeStaminaByStampTaskRequest request
    ) {
        final AsyncResult<ConsumeStaminaByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeStaminaByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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