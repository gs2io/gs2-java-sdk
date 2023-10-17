
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

package io.gs2.enchant;

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
import io.gs2.enchant.request.*;
import io.gs2.enchant.result.*;
import io.gs2.enchant.model.*;public class Gs2EnchantRestClient extends AbstractGs2Client<Gs2EnchantRestClient> {

	public Gs2EnchantRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "enchant")
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
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
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
                .replace("{service}", "enchant")
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
                .replace("{service}", "enchant")
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
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
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
                .replace("{service}", "enchant")
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
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/dump/user/{userId}";

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
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/dump/user/{userId}";

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
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/clean/user/{userId}";

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
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/clean/user/{userId}";

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

    class PrepareImportUserDataByUserIdTask extends Gs2RestSessionTask<PrepareImportUserDataByUserIdResult> {
        private PrepareImportUserDataByUserIdRequest request;

        public PrepareImportUserDataByUserIdTask(
            PrepareImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareImportUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PrepareImportUserDataByUserIdResult parse(JsonNode data) {
            return PrepareImportUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/import/user/{userId}/prepare";

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

    public void prepareImportUserDataByUserIdAsync(
            PrepareImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareImportUserDataByUserIdResult>> callback
    ) {
        PrepareImportUserDataByUserIdTask task = new PrepareImportUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public PrepareImportUserDataByUserIdResult prepareImportUserDataByUserId(
            PrepareImportUserDataByUserIdRequest request
    ) {
        final AsyncResult<PrepareImportUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareImportUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ImportUserDataByUserIdTask extends Gs2RestSessionTask<ImportUserDataByUserIdResult> {
        private ImportUserDataByUserIdRequest request;

        public ImportUserDataByUserIdTask(
            ImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<ImportUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ImportUserDataByUserIdResult parse(JsonNode data) {
            return ImportUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/import/user/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("uploadToken", request.getUploadToken());
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

    public void importUserDataByUserIdAsync(
            ImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<ImportUserDataByUserIdResult>> callback
    ) {
        ImportUserDataByUserIdTask task = new ImportUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public ImportUserDataByUserIdResult importUserDataByUserId(
            ImportUserDataByUserIdRequest request
    ) {
        final AsyncResult<ImportUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        importUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckImportUserDataByUserIdTask extends Gs2RestSessionTask<CheckImportUserDataByUserIdResult> {
        private CheckImportUserDataByUserIdRequest request;

        public CheckImportUserDataByUserIdTask(
            CheckImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckImportUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CheckImportUserDataByUserIdResult parse(JsonNode data) {
            return CheckImportUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/system/import/user/{userId}/{uploadToken}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{uploadToken}", this.request.getUploadToken() == null || this.request.getUploadToken().length() == 0 ? "null" : String.valueOf(this.request.getUploadToken()));

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

    public void checkImportUserDataByUserIdAsync(
            CheckImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckImportUserDataByUserIdResult>> callback
    ) {
        CheckImportUserDataByUserIdTask task = new CheckImportUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CheckImportUserDataByUserIdResult checkImportUserDataByUserId(
            CheckImportUserDataByUserIdRequest request
    ) {
        final AsyncResult<CheckImportUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkImportUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBalanceParameterModelsTask extends Gs2RestSessionTask<DescribeBalanceParameterModelsResult> {
        private DescribeBalanceParameterModelsRequest request;

        public DescribeBalanceParameterModelsTask(
            DescribeBalanceParameterModelsRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBalanceParameterModelsResult parse(JsonNode data) {
            return DescribeBalanceParameterModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/balance";

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

    public void describeBalanceParameterModelsAsync(
            DescribeBalanceParameterModelsRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterModelsResult>> callback
    ) {
        DescribeBalanceParameterModelsTask task = new DescribeBalanceParameterModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeBalanceParameterModelsResult describeBalanceParameterModels(
            DescribeBalanceParameterModelsRequest request
    ) {
        final AsyncResult<DescribeBalanceParameterModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBalanceParameterModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBalanceParameterModelTask extends Gs2RestSessionTask<GetBalanceParameterModelResult> {
        private GetBalanceParameterModelRequest request;

        public GetBalanceParameterModelTask(
            GetBalanceParameterModelRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBalanceParameterModelResult parse(JsonNode data) {
            return GetBalanceParameterModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/balance/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

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

    public void getBalanceParameterModelAsync(
            GetBalanceParameterModelRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterModelResult>> callback
    ) {
        GetBalanceParameterModelTask task = new GetBalanceParameterModelTask(request, callback);
        session.execute(task);
    }

    public GetBalanceParameterModelResult getBalanceParameterModel(
            GetBalanceParameterModelRequest request
    ) {
        final AsyncResult<GetBalanceParameterModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBalanceParameterModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBalanceParameterModelMastersTask extends Gs2RestSessionTask<DescribeBalanceParameterModelMastersResult> {
        private DescribeBalanceParameterModelMastersRequest request;

        public DescribeBalanceParameterModelMastersTask(
            DescribeBalanceParameterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBalanceParameterModelMastersResult parse(JsonNode data) {
            return DescribeBalanceParameterModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/balance";

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

    public void describeBalanceParameterModelMastersAsync(
            DescribeBalanceParameterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterModelMastersResult>> callback
    ) {
        DescribeBalanceParameterModelMastersTask task = new DescribeBalanceParameterModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeBalanceParameterModelMastersResult describeBalanceParameterModelMasters(
            DescribeBalanceParameterModelMastersRequest request
    ) {
        final AsyncResult<DescribeBalanceParameterModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBalanceParameterModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateBalanceParameterModelMasterTask extends Gs2RestSessionTask<CreateBalanceParameterModelMasterResult> {
        private CreateBalanceParameterModelMasterRequest request;

        public CreateBalanceParameterModelMasterTask(
            CreateBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBalanceParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateBalanceParameterModelMasterResult parse(JsonNode data) {
            return CreateBalanceParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/balance";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("totalValue", request.getTotalValue());
                    put("initialValueStrategy", request.getInitialValueStrategy());
                    put("parameters", request.getParameters() == null ? new ArrayList<BalanceParameterValueModel>() :
                        request.getParameters().stream().map(item -> {
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

            builder
                .build()
                .send();
        }
    }

    public void createBalanceParameterModelMasterAsync(
            CreateBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBalanceParameterModelMasterResult>> callback
    ) {
        CreateBalanceParameterModelMasterTask task = new CreateBalanceParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateBalanceParameterModelMasterResult createBalanceParameterModelMaster(
            CreateBalanceParameterModelMasterRequest request
    ) {
        final AsyncResult<CreateBalanceParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createBalanceParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBalanceParameterModelMasterTask extends Gs2RestSessionTask<GetBalanceParameterModelMasterResult> {
        private GetBalanceParameterModelMasterRequest request;

        public GetBalanceParameterModelMasterTask(
            GetBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBalanceParameterModelMasterResult parse(JsonNode data) {
            return GetBalanceParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/balance/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

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

    public void getBalanceParameterModelMasterAsync(
            GetBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterModelMasterResult>> callback
    ) {
        GetBalanceParameterModelMasterTask task = new GetBalanceParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetBalanceParameterModelMasterResult getBalanceParameterModelMaster(
            GetBalanceParameterModelMasterRequest request
    ) {
        final AsyncResult<GetBalanceParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBalanceParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateBalanceParameterModelMasterTask extends Gs2RestSessionTask<UpdateBalanceParameterModelMasterResult> {
        private UpdateBalanceParameterModelMasterRequest request;

        public UpdateBalanceParameterModelMasterTask(
            UpdateBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBalanceParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateBalanceParameterModelMasterResult parse(JsonNode data) {
            return UpdateBalanceParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/balance/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("totalValue", request.getTotalValue());
                    put("initialValueStrategy", request.getInitialValueStrategy());
                    put("parameters", request.getParameters() == null ? new ArrayList<BalanceParameterValueModel>() :
                        request.getParameters().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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

    public void updateBalanceParameterModelMasterAsync(
            UpdateBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBalanceParameterModelMasterResult>> callback
    ) {
        UpdateBalanceParameterModelMasterTask task = new UpdateBalanceParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateBalanceParameterModelMasterResult updateBalanceParameterModelMaster(
            UpdateBalanceParameterModelMasterRequest request
    ) {
        final AsyncResult<UpdateBalanceParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateBalanceParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteBalanceParameterModelMasterTask extends Gs2RestSessionTask<DeleteBalanceParameterModelMasterResult> {
        private DeleteBalanceParameterModelMasterRequest request;

        public DeleteBalanceParameterModelMasterTask(
            DeleteBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBalanceParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBalanceParameterModelMasterResult parse(JsonNode data) {
            return DeleteBalanceParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/balance/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

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

    public void deleteBalanceParameterModelMasterAsync(
            DeleteBalanceParameterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBalanceParameterModelMasterResult>> callback
    ) {
        DeleteBalanceParameterModelMasterTask task = new DeleteBalanceParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteBalanceParameterModelMasterResult deleteBalanceParameterModelMaster(
            DeleteBalanceParameterModelMasterRequest request
    ) {
        final AsyncResult<DeleteBalanceParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBalanceParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRarityParameterModelsTask extends Gs2RestSessionTask<DescribeRarityParameterModelsResult> {
        private DescribeRarityParameterModelsRequest request;

        public DescribeRarityParameterModelsTask(
            DescribeRarityParameterModelsRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRarityParameterModelsResult parse(JsonNode data) {
            return DescribeRarityParameterModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/rarity";

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

    public void describeRarityParameterModelsAsync(
            DescribeRarityParameterModelsRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterModelsResult>> callback
    ) {
        DescribeRarityParameterModelsTask task = new DescribeRarityParameterModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeRarityParameterModelsResult describeRarityParameterModels(
            DescribeRarityParameterModelsRequest request
    ) {
        final AsyncResult<DescribeRarityParameterModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRarityParameterModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRarityParameterModelTask extends Gs2RestSessionTask<GetRarityParameterModelResult> {
        private GetRarityParameterModelRequest request;

        public GetRarityParameterModelTask(
            GetRarityParameterModelRequest request,
            AsyncAction<AsyncResult<GetRarityParameterModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRarityParameterModelResult parse(JsonNode data) {
            return GetRarityParameterModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/rarity/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

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

    public void getRarityParameterModelAsync(
            GetRarityParameterModelRequest request,
            AsyncAction<AsyncResult<GetRarityParameterModelResult>> callback
    ) {
        GetRarityParameterModelTask task = new GetRarityParameterModelTask(request, callback);
        session.execute(task);
    }

    public GetRarityParameterModelResult getRarityParameterModel(
            GetRarityParameterModelRequest request
    ) {
        final AsyncResult<GetRarityParameterModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRarityParameterModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRarityParameterModelMastersTask extends Gs2RestSessionTask<DescribeRarityParameterModelMastersResult> {
        private DescribeRarityParameterModelMastersRequest request;

        public DescribeRarityParameterModelMastersTask(
            DescribeRarityParameterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRarityParameterModelMastersResult parse(JsonNode data) {
            return DescribeRarityParameterModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/rarity";

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

    public void describeRarityParameterModelMastersAsync(
            DescribeRarityParameterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterModelMastersResult>> callback
    ) {
        DescribeRarityParameterModelMastersTask task = new DescribeRarityParameterModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeRarityParameterModelMastersResult describeRarityParameterModelMasters(
            DescribeRarityParameterModelMastersRequest request
    ) {
        final AsyncResult<DescribeRarityParameterModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRarityParameterModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRarityParameterModelMasterTask extends Gs2RestSessionTask<CreateRarityParameterModelMasterResult> {
        private CreateRarityParameterModelMasterRequest request;

        public CreateRarityParameterModelMasterTask(
            CreateRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRarityParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateRarityParameterModelMasterResult parse(JsonNode data) {
            return CreateRarityParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/rarity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("maximumParameterCount", request.getMaximumParameterCount());
                    put("parameterCounts", request.getParameterCounts() == null ? new ArrayList<RarityParameterCountModel>() :
                        request.getParameterCounts().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("parameters", request.getParameters() == null ? new ArrayList<RarityParameterValueModel>() :
                        request.getParameters().stream().map(item -> {
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

            builder
                .build()
                .send();
        }
    }

    public void createRarityParameterModelMasterAsync(
            CreateRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRarityParameterModelMasterResult>> callback
    ) {
        CreateRarityParameterModelMasterTask task = new CreateRarityParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateRarityParameterModelMasterResult createRarityParameterModelMaster(
            CreateRarityParameterModelMasterRequest request
    ) {
        final AsyncResult<CreateRarityParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRarityParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRarityParameterModelMasterTask extends Gs2RestSessionTask<GetRarityParameterModelMasterResult> {
        private GetRarityParameterModelMasterRequest request;

        public GetRarityParameterModelMasterTask(
            GetRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<GetRarityParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRarityParameterModelMasterResult parse(JsonNode data) {
            return GetRarityParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/rarity/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

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

    public void getRarityParameterModelMasterAsync(
            GetRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<GetRarityParameterModelMasterResult>> callback
    ) {
        GetRarityParameterModelMasterTask task = new GetRarityParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetRarityParameterModelMasterResult getRarityParameterModelMaster(
            GetRarityParameterModelMasterRequest request
    ) {
        final AsyncResult<GetRarityParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRarityParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRarityParameterModelMasterTask extends Gs2RestSessionTask<UpdateRarityParameterModelMasterResult> {
        private UpdateRarityParameterModelMasterRequest request;

        public UpdateRarityParameterModelMasterTask(
            UpdateRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRarityParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateRarityParameterModelMasterResult parse(JsonNode data) {
            return UpdateRarityParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/rarity/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("maximumParameterCount", request.getMaximumParameterCount());
                    put("parameterCounts", request.getParameterCounts() == null ? new ArrayList<RarityParameterCountModel>() :
                        request.getParameterCounts().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("parameters", request.getParameters() == null ? new ArrayList<RarityParameterValueModel>() :
                        request.getParameters().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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

    public void updateRarityParameterModelMasterAsync(
            UpdateRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRarityParameterModelMasterResult>> callback
    ) {
        UpdateRarityParameterModelMasterTask task = new UpdateRarityParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateRarityParameterModelMasterResult updateRarityParameterModelMaster(
            UpdateRarityParameterModelMasterRequest request
    ) {
        final AsyncResult<UpdateRarityParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRarityParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRarityParameterModelMasterTask extends Gs2RestSessionTask<DeleteRarityParameterModelMasterResult> {
        private DeleteRarityParameterModelMasterRequest request;

        public DeleteRarityParameterModelMasterTask(
            DeleteRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRarityParameterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRarityParameterModelMasterResult parse(JsonNode data) {
            return DeleteRarityParameterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/rarity/{parameterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));

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

    public void deleteRarityParameterModelMasterAsync(
            DeleteRarityParameterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRarityParameterModelMasterResult>> callback
    ) {
        DeleteRarityParameterModelMasterTask task = new DeleteRarityParameterModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteRarityParameterModelMasterResult deleteRarityParameterModelMaster(
            DeleteRarityParameterModelMasterRequest request
    ) {
        final AsyncResult<DeleteRarityParameterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRarityParameterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "enchant")
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

    class GetCurrentParameterMasterTask extends Gs2RestSessionTask<GetCurrentParameterMasterResult> {
        private GetCurrentParameterMasterRequest request;

        public GetCurrentParameterMasterTask(
            GetCurrentParameterMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentParameterMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentParameterMasterResult parse(JsonNode data) {
            return GetCurrentParameterMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
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

    public void getCurrentParameterMasterAsync(
            GetCurrentParameterMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentParameterMasterResult>> callback
    ) {
        GetCurrentParameterMasterTask task = new GetCurrentParameterMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentParameterMasterResult getCurrentParameterMaster(
            GetCurrentParameterMasterRequest request
    ) {
        final AsyncResult<GetCurrentParameterMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentParameterMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentParameterMasterTask extends Gs2RestSessionTask<UpdateCurrentParameterMasterResult> {
        private UpdateCurrentParameterMasterRequest request;

        public UpdateCurrentParameterMasterTask(
            UpdateCurrentParameterMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentParameterMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentParameterMasterResult parse(JsonNode data) {
            return UpdateCurrentParameterMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
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

    public void updateCurrentParameterMasterAsync(
            UpdateCurrentParameterMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentParameterMasterResult>> callback
    ) {
        UpdateCurrentParameterMasterTask task = new UpdateCurrentParameterMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentParameterMasterResult updateCurrentParameterMaster(
            UpdateCurrentParameterMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentParameterMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentParameterMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentParameterMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentParameterMasterFromGitHubResult> {
        private UpdateCurrentParameterMasterFromGitHubRequest request;

        public UpdateCurrentParameterMasterFromGitHubTask(
            UpdateCurrentParameterMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentParameterMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentParameterMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentParameterMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
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

    public void updateCurrentParameterMasterFromGitHubAsync(
            UpdateCurrentParameterMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentParameterMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentParameterMasterFromGitHubTask task = new UpdateCurrentParameterMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentParameterMasterFromGitHubResult updateCurrentParameterMasterFromGitHub(
            UpdateCurrentParameterMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentParameterMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentParameterMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBalanceParameterStatusesTask extends Gs2RestSessionTask<DescribeBalanceParameterStatusesResult> {
        private DescribeBalanceParameterStatusesRequest request;

        public DescribeBalanceParameterStatusesTask(
            DescribeBalanceParameterStatusesRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterStatusesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBalanceParameterStatusesResult parse(JsonNode data) {
            return DescribeBalanceParameterStatusesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/balance";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getParameterName() != null) {
                queryStrings.add("parameterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getParameterName()))));
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

    public void describeBalanceParameterStatusesAsync(
            DescribeBalanceParameterStatusesRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterStatusesResult>> callback
    ) {
        DescribeBalanceParameterStatusesTask task = new DescribeBalanceParameterStatusesTask(request, callback);
        session.execute(task);
    }

    public DescribeBalanceParameterStatusesResult describeBalanceParameterStatuses(
            DescribeBalanceParameterStatusesRequest request
    ) {
        final AsyncResult<DescribeBalanceParameterStatusesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBalanceParameterStatusesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBalanceParameterStatusesByUserIdTask extends Gs2RestSessionTask<DescribeBalanceParameterStatusesByUserIdResult> {
        private DescribeBalanceParameterStatusesByUserIdRequest request;

        public DescribeBalanceParameterStatusesByUserIdTask(
            DescribeBalanceParameterStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterStatusesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBalanceParameterStatusesByUserIdResult parse(JsonNode data) {
            return DescribeBalanceParameterStatusesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/balance";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getParameterName() != null) {
                queryStrings.add("parameterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getParameterName()))));
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

    public void describeBalanceParameterStatusesByUserIdAsync(
            DescribeBalanceParameterStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBalanceParameterStatusesByUserIdResult>> callback
    ) {
        DescribeBalanceParameterStatusesByUserIdTask task = new DescribeBalanceParameterStatusesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeBalanceParameterStatusesByUserIdResult describeBalanceParameterStatusesByUserId(
            DescribeBalanceParameterStatusesByUserIdRequest request
    ) {
        final AsyncResult<DescribeBalanceParameterStatusesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBalanceParameterStatusesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBalanceParameterStatusTask extends Gs2RestSessionTask<GetBalanceParameterStatusResult> {
        private GetBalanceParameterStatusRequest request;

        public GetBalanceParameterStatusTask(
            GetBalanceParameterStatusRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBalanceParameterStatusResult parse(JsonNode data) {
            return GetBalanceParameterStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/balance/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
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

    public void getBalanceParameterStatusAsync(
            GetBalanceParameterStatusRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterStatusResult>> callback
    ) {
        GetBalanceParameterStatusTask task = new GetBalanceParameterStatusTask(request, callback);
        session.execute(task);
    }

    public GetBalanceParameterStatusResult getBalanceParameterStatus(
            GetBalanceParameterStatusRequest request
    ) {
        final AsyncResult<GetBalanceParameterStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBalanceParameterStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBalanceParameterStatusByUserIdTask extends Gs2RestSessionTask<GetBalanceParameterStatusByUserIdResult> {
        private GetBalanceParameterStatusByUserIdRequest request;

        public GetBalanceParameterStatusByUserIdTask(
            GetBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBalanceParameterStatusByUserIdResult parse(JsonNode data) {
            return GetBalanceParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/balance/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
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

    public void getBalanceParameterStatusByUserIdAsync(
            GetBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetBalanceParameterStatusByUserIdResult>> callback
    ) {
        GetBalanceParameterStatusByUserIdTask task = new GetBalanceParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetBalanceParameterStatusByUserIdResult getBalanceParameterStatusByUserId(
            GetBalanceParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<GetBalanceParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBalanceParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteBalanceParameterStatusByUserIdTask extends Gs2RestSessionTask<DeleteBalanceParameterStatusByUserIdResult> {
        private DeleteBalanceParameterStatusByUserIdRequest request;

        public DeleteBalanceParameterStatusByUserIdTask(
            DeleteBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteBalanceParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBalanceParameterStatusByUserIdResult parse(JsonNode data) {
            return DeleteBalanceParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/balance/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteBalanceParameterStatusByUserIdAsync(
            DeleteBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteBalanceParameterStatusByUserIdResult>> callback
    ) {
        DeleteBalanceParameterStatusByUserIdTask task = new DeleteBalanceParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteBalanceParameterStatusByUserIdResult deleteBalanceParameterStatusByUserId(
            DeleteBalanceParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<DeleteBalanceParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBalanceParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReDrawBalanceParameterStatusByUserIdTask extends Gs2RestSessionTask<ReDrawBalanceParameterStatusByUserIdResult> {
        private ReDrawBalanceParameterStatusByUserIdRequest request;

        public ReDrawBalanceParameterStatusByUserIdTask(
            ReDrawBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<ReDrawBalanceParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReDrawBalanceParameterStatusByUserIdResult parse(JsonNode data) {
            return ReDrawBalanceParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/balance/{parameterName}/{propertyId}/redraw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("fixedParameterNames", request.getFixedParameterNames() == null ? new ArrayList<String>() :
                        request.getFixedParameterNames().stream().map(item -> {
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

    public void reDrawBalanceParameterStatusByUserIdAsync(
            ReDrawBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<ReDrawBalanceParameterStatusByUserIdResult>> callback
    ) {
        ReDrawBalanceParameterStatusByUserIdTask task = new ReDrawBalanceParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public ReDrawBalanceParameterStatusByUserIdResult reDrawBalanceParameterStatusByUserId(
            ReDrawBalanceParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<ReDrawBalanceParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        reDrawBalanceParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReDrawBalanceParameterStatusByStampSheetTask extends Gs2RestSessionTask<ReDrawBalanceParameterStatusByStampSheetResult> {
        private ReDrawBalanceParameterStatusByStampSheetRequest request;

        public ReDrawBalanceParameterStatusByStampSheetTask(
            ReDrawBalanceParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<ReDrawBalanceParameterStatusByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReDrawBalanceParameterStatusByStampSheetResult parse(JsonNode data) {
            return ReDrawBalanceParameterStatusByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/balance/redraw";

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

    public void reDrawBalanceParameterStatusByStampSheetAsync(
            ReDrawBalanceParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<ReDrawBalanceParameterStatusByStampSheetResult>> callback
    ) {
        ReDrawBalanceParameterStatusByStampSheetTask task = new ReDrawBalanceParameterStatusByStampSheetTask(request, callback);
        session.execute(task);
    }

    public ReDrawBalanceParameterStatusByStampSheetResult reDrawBalanceParameterStatusByStampSheet(
            ReDrawBalanceParameterStatusByStampSheetRequest request
    ) {
        final AsyncResult<ReDrawBalanceParameterStatusByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        reDrawBalanceParameterStatusByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetBalanceParameterStatusByUserIdTask extends Gs2RestSessionTask<SetBalanceParameterStatusByUserIdResult> {
        private SetBalanceParameterStatusByUserIdRequest request;

        public SetBalanceParameterStatusByUserIdTask(
            SetBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<SetBalanceParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetBalanceParameterStatusByUserIdResult parse(JsonNode data) {
            return SetBalanceParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/balance/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("parameterValues", request.getParameterValues() == null ? new ArrayList<BalanceParameterValue>() :
                        request.getParameterValues().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setBalanceParameterStatusByUserIdAsync(
            SetBalanceParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<SetBalanceParameterStatusByUserIdResult>> callback
    ) {
        SetBalanceParameterStatusByUserIdTask task = new SetBalanceParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetBalanceParameterStatusByUserIdResult setBalanceParameterStatusByUserId(
            SetBalanceParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<SetBalanceParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setBalanceParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetBalanceParameterStatusByStampSheetTask extends Gs2RestSessionTask<SetBalanceParameterStatusByStampSheetResult> {
        private SetBalanceParameterStatusByStampSheetRequest request;

        public SetBalanceParameterStatusByStampSheetTask(
            SetBalanceParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<SetBalanceParameterStatusByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetBalanceParameterStatusByStampSheetResult parse(JsonNode data) {
            return SetBalanceParameterStatusByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/balance/set";

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

    public void setBalanceParameterStatusByStampSheetAsync(
            SetBalanceParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<SetBalanceParameterStatusByStampSheetResult>> callback
    ) {
        SetBalanceParameterStatusByStampSheetTask task = new SetBalanceParameterStatusByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetBalanceParameterStatusByStampSheetResult setBalanceParameterStatusByStampSheet(
            SetBalanceParameterStatusByStampSheetRequest request
    ) {
        final AsyncResult<SetBalanceParameterStatusByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setBalanceParameterStatusByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRarityParameterStatusesTask extends Gs2RestSessionTask<DescribeRarityParameterStatusesResult> {
        private DescribeRarityParameterStatusesRequest request;

        public DescribeRarityParameterStatusesTask(
            DescribeRarityParameterStatusesRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterStatusesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRarityParameterStatusesResult parse(JsonNode data) {
            return DescribeRarityParameterStatusesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/rarity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getParameterName() != null) {
                queryStrings.add("parameterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getParameterName()))));
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

    public void describeRarityParameterStatusesAsync(
            DescribeRarityParameterStatusesRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterStatusesResult>> callback
    ) {
        DescribeRarityParameterStatusesTask task = new DescribeRarityParameterStatusesTask(request, callback);
        session.execute(task);
    }

    public DescribeRarityParameterStatusesResult describeRarityParameterStatuses(
            DescribeRarityParameterStatusesRequest request
    ) {
        final AsyncResult<DescribeRarityParameterStatusesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRarityParameterStatusesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRarityParameterStatusesByUserIdTask extends Gs2RestSessionTask<DescribeRarityParameterStatusesByUserIdResult> {
        private DescribeRarityParameterStatusesByUserIdRequest request;

        public DescribeRarityParameterStatusesByUserIdTask(
            DescribeRarityParameterStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterStatusesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRarityParameterStatusesByUserIdResult parse(JsonNode data) {
            return DescribeRarityParameterStatusesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getParameterName() != null) {
                queryStrings.add("parameterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getParameterName()))));
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

    public void describeRarityParameterStatusesByUserIdAsync(
            DescribeRarityParameterStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRarityParameterStatusesByUserIdResult>> callback
    ) {
        DescribeRarityParameterStatusesByUserIdTask task = new DescribeRarityParameterStatusesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeRarityParameterStatusesByUserIdResult describeRarityParameterStatusesByUserId(
            DescribeRarityParameterStatusesByUserIdRequest request
    ) {
        final AsyncResult<DescribeRarityParameterStatusesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRarityParameterStatusesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRarityParameterStatusTask extends Gs2RestSessionTask<GetRarityParameterStatusResult> {
        private GetRarityParameterStatusRequest request;

        public GetRarityParameterStatusTask(
            GetRarityParameterStatusRequest request,
            AsyncAction<AsyncResult<GetRarityParameterStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRarityParameterStatusResult parse(JsonNode data) {
            return GetRarityParameterStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/rarity/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
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

    public void getRarityParameterStatusAsync(
            GetRarityParameterStatusRequest request,
            AsyncAction<AsyncResult<GetRarityParameterStatusResult>> callback
    ) {
        GetRarityParameterStatusTask task = new GetRarityParameterStatusTask(request, callback);
        session.execute(task);
    }

    public GetRarityParameterStatusResult getRarityParameterStatus(
            GetRarityParameterStatusRequest request
    ) {
        final AsyncResult<GetRarityParameterStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRarityParameterStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRarityParameterStatusByUserIdTask extends Gs2RestSessionTask<GetRarityParameterStatusByUserIdResult> {
        private GetRarityParameterStatusByUserIdRequest request;

        public GetRarityParameterStatusByUserIdTask(
            GetRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetRarityParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRarityParameterStatusByUserIdResult parse(JsonNode data) {
            return GetRarityParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
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

    public void getRarityParameterStatusByUserIdAsync(
            GetRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetRarityParameterStatusByUserIdResult>> callback
    ) {
        GetRarityParameterStatusByUserIdTask task = new GetRarityParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetRarityParameterStatusByUserIdResult getRarityParameterStatusByUserId(
            GetRarityParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<GetRarityParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRarityParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRarityParameterStatusByUserIdTask extends Gs2RestSessionTask<DeleteRarityParameterStatusByUserIdResult> {
        private DeleteRarityParameterStatusByUserIdRequest request;

        public DeleteRarityParameterStatusByUserIdTask(
            DeleteRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRarityParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRarityParameterStatusByUserIdResult parse(JsonNode data) {
            return DeleteRarityParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteRarityParameterStatusByUserIdAsync(
            DeleteRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRarityParameterStatusByUserIdResult>> callback
    ) {
        DeleteRarityParameterStatusByUserIdTask task = new DeleteRarityParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteRarityParameterStatusByUserIdResult deleteRarityParameterStatusByUserId(
            DeleteRarityParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<DeleteRarityParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRarityParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReDrawRarityParameterStatusByUserIdTask extends Gs2RestSessionTask<ReDrawRarityParameterStatusByUserIdResult> {
        private ReDrawRarityParameterStatusByUserIdRequest request;

        public ReDrawRarityParameterStatusByUserIdTask(
            ReDrawRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<ReDrawRarityParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReDrawRarityParameterStatusByUserIdResult parse(JsonNode data) {
            return ReDrawRarityParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity/{parameterName}/{propertyId}/redraw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("fixedParameterNames", request.getFixedParameterNames() == null ? new ArrayList<String>() :
                        request.getFixedParameterNames().stream().map(item -> {
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

    public void reDrawRarityParameterStatusByUserIdAsync(
            ReDrawRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<ReDrawRarityParameterStatusByUserIdResult>> callback
    ) {
        ReDrawRarityParameterStatusByUserIdTask task = new ReDrawRarityParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public ReDrawRarityParameterStatusByUserIdResult reDrawRarityParameterStatusByUserId(
            ReDrawRarityParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<ReDrawRarityParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        reDrawRarityParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReDrawRarityParameterStatusByStampSheetTask extends Gs2RestSessionTask<ReDrawRarityParameterStatusByStampSheetResult> {
        private ReDrawRarityParameterStatusByStampSheetRequest request;

        public ReDrawRarityParameterStatusByStampSheetTask(
            ReDrawRarityParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<ReDrawRarityParameterStatusByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReDrawRarityParameterStatusByStampSheetResult parse(JsonNode data) {
            return ReDrawRarityParameterStatusByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rarity/parameter/redraw";

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

    public void reDrawRarityParameterStatusByStampSheetAsync(
            ReDrawRarityParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<ReDrawRarityParameterStatusByStampSheetResult>> callback
    ) {
        ReDrawRarityParameterStatusByStampSheetTask task = new ReDrawRarityParameterStatusByStampSheetTask(request, callback);
        session.execute(task);
    }

    public ReDrawRarityParameterStatusByStampSheetResult reDrawRarityParameterStatusByStampSheet(
            ReDrawRarityParameterStatusByStampSheetRequest request
    ) {
        final AsyncResult<ReDrawRarityParameterStatusByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        reDrawRarityParameterStatusByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddRarityParameterStatusByUserIdTask extends Gs2RestSessionTask<AddRarityParameterStatusByUserIdResult> {
        private AddRarityParameterStatusByUserIdRequest request;

        public AddRarityParameterStatusByUserIdTask(
            AddRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<AddRarityParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddRarityParameterStatusByUserIdResult parse(JsonNode data) {
            return AddRarityParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity/{parameterName}/{propertyId}/add";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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

    public void addRarityParameterStatusByUserIdAsync(
            AddRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<AddRarityParameterStatusByUserIdResult>> callback
    ) {
        AddRarityParameterStatusByUserIdTask task = new AddRarityParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddRarityParameterStatusByUserIdResult addRarityParameterStatusByUserId(
            AddRarityParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<AddRarityParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addRarityParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddRarityParameterStatusByStampSheetTask extends Gs2RestSessionTask<AddRarityParameterStatusByStampSheetResult> {
        private AddRarityParameterStatusByStampSheetRequest request;

        public AddRarityParameterStatusByStampSheetTask(
            AddRarityParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<AddRarityParameterStatusByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddRarityParameterStatusByStampSheetResult parse(JsonNode data) {
            return AddRarityParameterStatusByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rarity/parameter/add";

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

    public void addRarityParameterStatusByStampSheetAsync(
            AddRarityParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<AddRarityParameterStatusByStampSheetResult>> callback
    ) {
        AddRarityParameterStatusByStampSheetTask task = new AddRarityParameterStatusByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AddRarityParameterStatusByStampSheetResult addRarityParameterStatusByStampSheet(
            AddRarityParameterStatusByStampSheetRequest request
    ) {
        final AsyncResult<AddRarityParameterStatusByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addRarityParameterStatusByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyRarityParameterStatusTask extends Gs2RestSessionTask<VerifyRarityParameterStatusResult> {
        private VerifyRarityParameterStatusRequest request;

        public VerifyRarityParameterStatusTask(
            VerifyRarityParameterStatusRequest request,
            AsyncAction<AsyncResult<VerifyRarityParameterStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyRarityParameterStatusResult parse(JsonNode data) {
            return VerifyRarityParameterStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/rarity/{parameterName}/{propertyId}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("parameterValueName", request.getParameterValueName());
                    put("parameterCount", request.getParameterCount());
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

    public void verifyRarityParameterStatusAsync(
            VerifyRarityParameterStatusRequest request,
            AsyncAction<AsyncResult<VerifyRarityParameterStatusResult>> callback
    ) {
        VerifyRarityParameterStatusTask task = new VerifyRarityParameterStatusTask(request, callback);
        session.execute(task);
    }

    public VerifyRarityParameterStatusResult verifyRarityParameterStatus(
            VerifyRarityParameterStatusRequest request
    ) {
        final AsyncResult<VerifyRarityParameterStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyRarityParameterStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyRarityParameterStatusByUserIdTask extends Gs2RestSessionTask<VerifyRarityParameterStatusByUserIdResult> {
        private VerifyRarityParameterStatusByUserIdRequest request;

        public VerifyRarityParameterStatusByUserIdTask(
            VerifyRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyRarityParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyRarityParameterStatusByUserIdResult parse(JsonNode data) {
            return VerifyRarityParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity/{parameterName}/{propertyId}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("parameterValueName", request.getParameterValueName());
                    put("parameterCount", request.getParameterCount());
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

    public void verifyRarityParameterStatusByUserIdAsync(
            VerifyRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyRarityParameterStatusByUserIdResult>> callback
    ) {
        VerifyRarityParameterStatusByUserIdTask task = new VerifyRarityParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyRarityParameterStatusByUserIdResult verifyRarityParameterStatusByUserId(
            VerifyRarityParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<VerifyRarityParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyRarityParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyRarityParameterStatusByStampTaskTask extends Gs2RestSessionTask<VerifyRarityParameterStatusByStampTaskResult> {
        private VerifyRarityParameterStatusByStampTaskRequest request;

        public VerifyRarityParameterStatusByStampTaskTask(
            VerifyRarityParameterStatusByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyRarityParameterStatusByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyRarityParameterStatusByStampTaskResult parse(JsonNode data) {
            return VerifyRarityParameterStatusByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rarity/parameter/verify";

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

    public void verifyRarityParameterStatusByStampTaskAsync(
            VerifyRarityParameterStatusByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyRarityParameterStatusByStampTaskResult>> callback
    ) {
        VerifyRarityParameterStatusByStampTaskTask task = new VerifyRarityParameterStatusByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyRarityParameterStatusByStampTaskResult verifyRarityParameterStatusByStampTask(
            VerifyRarityParameterStatusByStampTaskRequest request
    ) {
        final AsyncResult<VerifyRarityParameterStatusByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyRarityParameterStatusByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRarityParameterStatusByUserIdTask extends Gs2RestSessionTask<SetRarityParameterStatusByUserIdResult> {
        private SetRarityParameterStatusByUserIdRequest request;

        public SetRarityParameterStatusByUserIdTask(
            SetRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<SetRarityParameterStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRarityParameterStatusByUserIdResult parse(JsonNode data) {
            return SetRarityParameterStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/rarity/{parameterName}/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{parameterName}", this.request.getParameterName() == null || this.request.getParameterName().length() == 0 ? "null" : String.valueOf(this.request.getParameterName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("parameterValues", request.getParameterValues() == null ? new ArrayList<RarityParameterValue>() :
                        request.getParameterValues().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setRarityParameterStatusByUserIdAsync(
            SetRarityParameterStatusByUserIdRequest request,
            AsyncAction<AsyncResult<SetRarityParameterStatusByUserIdResult>> callback
    ) {
        SetRarityParameterStatusByUserIdTask task = new SetRarityParameterStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetRarityParameterStatusByUserIdResult setRarityParameterStatusByUserId(
            SetRarityParameterStatusByUserIdRequest request
    ) {
        final AsyncResult<SetRarityParameterStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRarityParameterStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRarityParameterStatusByStampSheetTask extends Gs2RestSessionTask<SetRarityParameterStatusByStampSheetResult> {
        private SetRarityParameterStatusByStampSheetRequest request;

        public SetRarityParameterStatusByStampSheetTask(
            SetRarityParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRarityParameterStatusByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetRarityParameterStatusByStampSheetResult parse(JsonNode data) {
            return SetRarityParameterStatusByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "enchant")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rarity/parameter/set";

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

    public void setRarityParameterStatusByStampSheetAsync(
            SetRarityParameterStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRarityParameterStatusByStampSheetResult>> callback
    ) {
        SetRarityParameterStatusByStampSheetTask task = new SetRarityParameterStatusByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetRarityParameterStatusByStampSheetResult setRarityParameterStatusByStampSheet(
            SetRarityParameterStatusByStampSheetRequest request
    ) {
        final AsyncResult<SetRarityParameterStatusByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRarityParameterStatusByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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