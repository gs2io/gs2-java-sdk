
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

package io.gs2.formation;

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
import io.gs2.formation.request.*;
import io.gs2.formation.result.*;
import io.gs2.formation.model.*;public class Gs2FormationRestClient extends AbstractGs2Client<Gs2FormationRestClient> {

	public Gs2FormationRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("updateMoldScript", request.getUpdateMoldScript() != null ? request.getUpdateMoldScript().toJson() : null);
                    put("updateFormScript", request.getUpdateFormScript() != null ? request.getUpdateFormScript().toJson() : null);
                    put("updatePropertyFormScript", request.getUpdatePropertyFormScript() != null ? request.getUpdatePropertyFormScript().toJson() : null);
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("updateMoldScript", request.getUpdateMoldScript() != null ? request.getUpdateMoldScript().toJson() : null);
                    put("updateFormScript", request.getUpdateFormScript() != null ? request.getUpdateFormScript().toJson() : null);
                    put("updatePropertyFormScript", request.getUpdatePropertyFormScript() != null ? request.getUpdatePropertyFormScript().toJson() : null);
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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

    class GetFormModelTask extends Gs2RestSessionTask<GetFormModelResult> {
        private GetFormModelRequest request;

        public GetFormModelTask(
            GetFormModelRequest request,
            AsyncAction<AsyncResult<GetFormModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFormModelResult parse(JsonNode data) {
            return GetFormModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{moldModelName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void getFormModelAsync(
            GetFormModelRequest request,
            AsyncAction<AsyncResult<GetFormModelResult>> callback
    ) {
        GetFormModelTask task = new GetFormModelTask(request, callback);
        session.execute(task);
    }

    public GetFormModelResult getFormModel(
            GetFormModelRequest request
    ) {
        final AsyncResult<GetFormModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFormModelMastersTask extends Gs2RestSessionTask<DescribeFormModelMastersResult> {
        private DescribeFormModelMastersRequest request;

        public DescribeFormModelMastersTask(
            DescribeFormModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeFormModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFormModelMastersResult parse(JsonNode data) {
            return DescribeFormModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form";

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

    public void describeFormModelMastersAsync(
            DescribeFormModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeFormModelMastersResult>> callback
    ) {
        DescribeFormModelMastersTask task = new DescribeFormModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeFormModelMastersResult describeFormModelMasters(
            DescribeFormModelMastersRequest request
    ) {
        final AsyncResult<DescribeFormModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFormModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateFormModelMasterTask extends Gs2RestSessionTask<CreateFormModelMasterResult> {
        private CreateFormModelMasterRequest request;

        public CreateFormModelMasterTask(
            CreateFormModelMasterRequest request,
            AsyncAction<AsyncResult<CreateFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateFormModelMasterResult parse(JsonNode data) {
            return CreateFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("slots", request.getSlots() == null ? new ArrayList<SlotModel>() :
                        request.getSlots().stream().map(item -> {
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

    public void createFormModelMasterAsync(
            CreateFormModelMasterRequest request,
            AsyncAction<AsyncResult<CreateFormModelMasterResult>> callback
    ) {
        CreateFormModelMasterTask task = new CreateFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateFormModelMasterResult createFormModelMaster(
            CreateFormModelMasterRequest request
    ) {
        final AsyncResult<CreateFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormModelMasterTask extends Gs2RestSessionTask<GetFormModelMasterResult> {
        private GetFormModelMasterRequest request;

        public GetFormModelMasterTask(
            GetFormModelMasterRequest request,
            AsyncAction<AsyncResult<GetFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFormModelMasterResult parse(JsonNode data) {
            return GetFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form/{formModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{formModelName}", this.request.getFormModelName() == null || this.request.getFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getFormModelName()));

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

    public void getFormModelMasterAsync(
            GetFormModelMasterRequest request,
            AsyncAction<AsyncResult<GetFormModelMasterResult>> callback
    ) {
        GetFormModelMasterTask task = new GetFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetFormModelMasterResult getFormModelMaster(
            GetFormModelMasterRequest request
    ) {
        final AsyncResult<GetFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateFormModelMasterTask extends Gs2RestSessionTask<UpdateFormModelMasterResult> {
        private UpdateFormModelMasterRequest request;

        public UpdateFormModelMasterTask(
            UpdateFormModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateFormModelMasterResult parse(JsonNode data) {
            return UpdateFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form/{formModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{formModelName}", this.request.getFormModelName() == null || this.request.getFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getFormModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("slots", request.getSlots() == null ? new ArrayList<SlotModel>() :
                        request.getSlots().stream().map(item -> {
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

    public void updateFormModelMasterAsync(
            UpdateFormModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateFormModelMasterResult>> callback
    ) {
        UpdateFormModelMasterTask task = new UpdateFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateFormModelMasterResult updateFormModelMaster(
            UpdateFormModelMasterRequest request
    ) {
        final AsyncResult<UpdateFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFormModelMasterTask extends Gs2RestSessionTask<DeleteFormModelMasterResult> {
        private DeleteFormModelMasterRequest request;

        public DeleteFormModelMasterTask(
            DeleteFormModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFormModelMasterResult parse(JsonNode data) {
            return DeleteFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form/{formModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{formModelName}", this.request.getFormModelName() == null || this.request.getFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getFormModelName()));

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

    public void deleteFormModelMasterAsync(
            DeleteFormModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteFormModelMasterResult>> callback
    ) {
        DeleteFormModelMasterTask task = new DeleteFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteFormModelMasterResult deleteFormModelMaster(
            DeleteFormModelMasterRequest request
    ) {
        final AsyncResult<DeleteFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldModelsTask extends Gs2RestSessionTask<DescribeMoldModelsResult> {
        private DescribeMoldModelsRequest request;

        public DescribeMoldModelsTask(
            DescribeMoldModelsRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMoldModelsResult parse(JsonNode data) {
            return DescribeMoldModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/mold";

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

    public void describeMoldModelsAsync(
            DescribeMoldModelsRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelsResult>> callback
    ) {
        DescribeMoldModelsTask task = new DescribeMoldModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeMoldModelsResult describeMoldModels(
            DescribeMoldModelsRequest request
    ) {
        final AsyncResult<DescribeMoldModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldModelTask extends Gs2RestSessionTask<GetMoldModelResult> {
        private GetMoldModelRequest request;

        public GetMoldModelTask(
            GetMoldModelRequest request,
            AsyncAction<AsyncResult<GetMoldModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMoldModelResult parse(JsonNode data) {
            return GetMoldModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void getMoldModelAsync(
            GetMoldModelRequest request,
            AsyncAction<AsyncResult<GetMoldModelResult>> callback
    ) {
        GetMoldModelTask task = new GetMoldModelTask(request, callback);
        session.execute(task);
    }

    public GetMoldModelResult getMoldModel(
            GetMoldModelRequest request
    ) {
        final AsyncResult<GetMoldModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldModelMastersTask extends Gs2RestSessionTask<DescribeMoldModelMastersResult> {
        private DescribeMoldModelMastersRequest request;

        public DescribeMoldModelMastersTask(
            DescribeMoldModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMoldModelMastersResult parse(JsonNode data) {
            return DescribeMoldModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold";

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

    public void describeMoldModelMastersAsync(
            DescribeMoldModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelMastersResult>> callback
    ) {
        DescribeMoldModelMastersTask task = new DescribeMoldModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeMoldModelMastersResult describeMoldModelMasters(
            DescribeMoldModelMastersRequest request
    ) {
        final AsyncResult<DescribeMoldModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateMoldModelMasterTask extends Gs2RestSessionTask<CreateMoldModelMasterResult> {
        private CreateMoldModelMasterRequest request;

        public CreateMoldModelMasterTask(
            CreateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMoldModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateMoldModelMasterResult parse(JsonNode data) {
            return CreateMoldModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("formModelName", request.getFormModelName());
                    put("initialMaxCapacity", request.getInitialMaxCapacity());
                    put("maxCapacity", request.getMaxCapacity());
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

    public void createMoldModelMasterAsync(
            CreateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMoldModelMasterResult>> callback
    ) {
        CreateMoldModelMasterTask task = new CreateMoldModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateMoldModelMasterResult createMoldModelMaster(
            CreateMoldModelMasterRequest request
    ) {
        final AsyncResult<CreateMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldModelMasterTask extends Gs2RestSessionTask<GetMoldModelMasterResult> {
        private GetMoldModelMasterRequest request;

        public GetMoldModelMasterTask(
            GetMoldModelMasterRequest request,
            AsyncAction<AsyncResult<GetMoldModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMoldModelMasterResult parse(JsonNode data) {
            return GetMoldModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void getMoldModelMasterAsync(
            GetMoldModelMasterRequest request,
            AsyncAction<AsyncResult<GetMoldModelMasterResult>> callback
    ) {
        GetMoldModelMasterTask task = new GetMoldModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetMoldModelMasterResult getMoldModelMaster(
            GetMoldModelMasterRequest request
    ) {
        final AsyncResult<GetMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateMoldModelMasterTask extends Gs2RestSessionTask<UpdateMoldModelMasterResult> {
        private UpdateMoldModelMasterRequest request;

        public UpdateMoldModelMasterTask(
            UpdateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMoldModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateMoldModelMasterResult parse(JsonNode data) {
            return UpdateMoldModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("formModelName", request.getFormModelName());
                    put("initialMaxCapacity", request.getInitialMaxCapacity());
                    put("maxCapacity", request.getMaxCapacity());
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

    public void updateMoldModelMasterAsync(
            UpdateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMoldModelMasterResult>> callback
    ) {
        UpdateMoldModelMasterTask task = new UpdateMoldModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateMoldModelMasterResult updateMoldModelMaster(
            UpdateMoldModelMasterRequest request
    ) {
        final AsyncResult<UpdateMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMoldModelMasterTask extends Gs2RestSessionTask<DeleteMoldModelMasterResult> {
        private DeleteMoldModelMasterRequest request;

        public DeleteMoldModelMasterTask(
            DeleteMoldModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMoldModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMoldModelMasterResult parse(JsonNode data) {
            return DeleteMoldModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void deleteMoldModelMasterAsync(
            DeleteMoldModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMoldModelMasterResult>> callback
    ) {
        DeleteMoldModelMasterTask task = new DeleteMoldModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteMoldModelMasterResult deleteMoldModelMaster(
            DeleteMoldModelMasterRequest request
    ) {
        final AsyncResult<DeleteMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePropertyFormModelsTask extends Gs2RestSessionTask<DescribePropertyFormModelsResult> {
        private DescribePropertyFormModelsRequest request;

        public DescribePropertyFormModelsTask(
            DescribePropertyFormModelsRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePropertyFormModelsResult parse(JsonNode data) {
            return DescribePropertyFormModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/propertyForm";

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

    public void describePropertyFormModelsAsync(
            DescribePropertyFormModelsRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormModelsResult>> callback
    ) {
        DescribePropertyFormModelsTask task = new DescribePropertyFormModelsTask(request, callback);
        session.execute(task);
    }

    public DescribePropertyFormModelsResult describePropertyFormModels(
            DescribePropertyFormModelsRequest request
    ) {
        final AsyncResult<DescribePropertyFormModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePropertyFormModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPropertyFormModelTask extends Gs2RestSessionTask<GetPropertyFormModelResult> {
        private GetPropertyFormModelRequest request;

        public GetPropertyFormModelTask(
            GetPropertyFormModelRequest request,
            AsyncAction<AsyncResult<GetPropertyFormModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPropertyFormModelResult parse(JsonNode data) {
            return GetPropertyFormModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/propertyForm/{propertyFormModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));

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

    public void getPropertyFormModelAsync(
            GetPropertyFormModelRequest request,
            AsyncAction<AsyncResult<GetPropertyFormModelResult>> callback
    ) {
        GetPropertyFormModelTask task = new GetPropertyFormModelTask(request, callback);
        session.execute(task);
    }

    public GetPropertyFormModelResult getPropertyFormModel(
            GetPropertyFormModelRequest request
    ) {
        final AsyncResult<GetPropertyFormModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPropertyFormModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePropertyFormModelMastersTask extends Gs2RestSessionTask<DescribePropertyFormModelMastersResult> {
        private DescribePropertyFormModelMastersRequest request;

        public DescribePropertyFormModelMastersTask(
            DescribePropertyFormModelMastersRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePropertyFormModelMastersResult parse(JsonNode data) {
            return DescribePropertyFormModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/propertyForm";

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

    public void describePropertyFormModelMastersAsync(
            DescribePropertyFormModelMastersRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormModelMastersResult>> callback
    ) {
        DescribePropertyFormModelMastersTask task = new DescribePropertyFormModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribePropertyFormModelMastersResult describePropertyFormModelMasters(
            DescribePropertyFormModelMastersRequest request
    ) {
        final AsyncResult<DescribePropertyFormModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePropertyFormModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreatePropertyFormModelMasterTask extends Gs2RestSessionTask<CreatePropertyFormModelMasterResult> {
        private CreatePropertyFormModelMasterRequest request;

        public CreatePropertyFormModelMasterTask(
            CreatePropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<CreatePropertyFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreatePropertyFormModelMasterResult parse(JsonNode data) {
            return CreatePropertyFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/propertyForm";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("slots", request.getSlots() == null ? new ArrayList<SlotModel>() :
                        request.getSlots().stream().map(item -> {
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

    public void createPropertyFormModelMasterAsync(
            CreatePropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<CreatePropertyFormModelMasterResult>> callback
    ) {
        CreatePropertyFormModelMasterTask task = new CreatePropertyFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreatePropertyFormModelMasterResult createPropertyFormModelMaster(
            CreatePropertyFormModelMasterRequest request
    ) {
        final AsyncResult<CreatePropertyFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPropertyFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPropertyFormModelMasterTask extends Gs2RestSessionTask<GetPropertyFormModelMasterResult> {
        private GetPropertyFormModelMasterRequest request;

        public GetPropertyFormModelMasterTask(
            GetPropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<GetPropertyFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPropertyFormModelMasterResult parse(JsonNode data) {
            return GetPropertyFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/propertyForm/{propertyFormModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));

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

    public void getPropertyFormModelMasterAsync(
            GetPropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<GetPropertyFormModelMasterResult>> callback
    ) {
        GetPropertyFormModelMasterTask task = new GetPropertyFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetPropertyFormModelMasterResult getPropertyFormModelMaster(
            GetPropertyFormModelMasterRequest request
    ) {
        final AsyncResult<GetPropertyFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPropertyFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdatePropertyFormModelMasterTask extends Gs2RestSessionTask<UpdatePropertyFormModelMasterResult> {
        private UpdatePropertyFormModelMasterRequest request;

        public UpdatePropertyFormModelMasterTask(
            UpdatePropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<UpdatePropertyFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdatePropertyFormModelMasterResult parse(JsonNode data) {
            return UpdatePropertyFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/propertyForm/{propertyFormModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("slots", request.getSlots() == null ? new ArrayList<SlotModel>() :
                        request.getSlots().stream().map(item -> {
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

    public void updatePropertyFormModelMasterAsync(
            UpdatePropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<UpdatePropertyFormModelMasterResult>> callback
    ) {
        UpdatePropertyFormModelMasterTask task = new UpdatePropertyFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdatePropertyFormModelMasterResult updatePropertyFormModelMaster(
            UpdatePropertyFormModelMasterRequest request
    ) {
        final AsyncResult<UpdatePropertyFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updatePropertyFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePropertyFormModelMasterTask extends Gs2RestSessionTask<DeletePropertyFormModelMasterResult> {
        private DeletePropertyFormModelMasterRequest request;

        public DeletePropertyFormModelMasterTask(
            DeletePropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<DeletePropertyFormModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePropertyFormModelMasterResult parse(JsonNode data) {
            return DeletePropertyFormModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/propertyForm/{propertyFormModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));

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

    public void deletePropertyFormModelMasterAsync(
            DeletePropertyFormModelMasterRequest request,
            AsyncAction<AsyncResult<DeletePropertyFormModelMasterResult>> callback
    ) {
        DeletePropertyFormModelMasterTask task = new DeletePropertyFormModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeletePropertyFormModelMasterResult deletePropertyFormModelMaster(
            DeletePropertyFormModelMasterRequest request
    ) {
        final AsyncResult<DeletePropertyFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePropertyFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "formation")
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

    class GetCurrentFormMasterTask extends Gs2RestSessionTask<GetCurrentFormMasterResult> {
        private GetCurrentFormMasterRequest request;

        public GetCurrentFormMasterTask(
            GetCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentFormMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentFormMasterResult parse(JsonNode data) {
            return GetCurrentFormMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
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

    public void getCurrentFormMasterAsync(
            GetCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentFormMasterResult>> callback
    ) {
        GetCurrentFormMasterTask task = new GetCurrentFormMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentFormMasterResult getCurrentFormMaster(
            GetCurrentFormMasterRequest request
    ) {
        final AsyncResult<GetCurrentFormMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentFormMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentFormMasterTask extends Gs2RestSessionTask<UpdateCurrentFormMasterResult> {
        private UpdateCurrentFormMasterRequest request;

        public UpdateCurrentFormMasterTask(
            UpdateCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentFormMasterResult parse(JsonNode data) {
            return UpdateCurrentFormMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
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

    public void updateCurrentFormMasterAsync(
            UpdateCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterResult>> callback
    ) {
        UpdateCurrentFormMasterTask task = new UpdateCurrentFormMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentFormMasterResult updateCurrentFormMaster(
            UpdateCurrentFormMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentFormMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentFormMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentFormMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentFormMasterFromGitHubResult> {
        private UpdateCurrentFormMasterFromGitHubRequest request;

        public UpdateCurrentFormMasterFromGitHubTask(
            UpdateCurrentFormMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentFormMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentFormMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
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

    public void updateCurrentFormMasterFromGitHubAsync(
            UpdateCurrentFormMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentFormMasterFromGitHubTask task = new UpdateCurrentFormMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentFormMasterFromGitHubResult updateCurrentFormMasterFromGitHub(
            UpdateCurrentFormMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentFormMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentFormMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldsTask extends Gs2RestSessionTask<DescribeMoldsResult> {
        private DescribeMoldsRequest request;

        public DescribeMoldsTask(
            DescribeMoldsRequest request,
            AsyncAction<AsyncResult<DescribeMoldsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMoldsResult parse(JsonNode data) {
            return DescribeMoldsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold";

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

    public void describeMoldsAsync(
            DescribeMoldsRequest request,
            AsyncAction<AsyncResult<DescribeMoldsResult>> callback
    ) {
        DescribeMoldsTask task = new DescribeMoldsTask(request, callback);
        session.execute(task);
    }

    public DescribeMoldsResult describeMolds(
            DescribeMoldsRequest request
    ) {
        final AsyncResult<DescribeMoldsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldsByUserIdTask extends Gs2RestSessionTask<DescribeMoldsByUserIdResult> {
        private DescribeMoldsByUserIdRequest request;

        public DescribeMoldsByUserIdTask(
            DescribeMoldsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeMoldsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMoldsByUserIdResult parse(JsonNode data) {
            return DescribeMoldsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold";

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

    public void describeMoldsByUserIdAsync(
            DescribeMoldsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeMoldsByUserIdResult>> callback
    ) {
        DescribeMoldsByUserIdTask task = new DescribeMoldsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeMoldsByUserIdResult describeMoldsByUserId(
            DescribeMoldsByUserIdRequest request
    ) {
        final AsyncResult<DescribeMoldsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldTask extends Gs2RestSessionTask<GetMoldResult> {
        private GetMoldRequest request;

        public GetMoldTask(
            GetMoldRequest request,
            AsyncAction<AsyncResult<GetMoldResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMoldResult parse(JsonNode data) {
            return GetMoldResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void getMoldAsync(
            GetMoldRequest request,
            AsyncAction<AsyncResult<GetMoldResult>> callback
    ) {
        GetMoldTask task = new GetMoldTask(request, callback);
        session.execute(task);
    }

    public GetMoldResult getMold(
            GetMoldRequest request
    ) {
        final AsyncResult<GetMoldResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldByUserIdTask extends Gs2RestSessionTask<GetMoldByUserIdResult> {
        private GetMoldByUserIdRequest request;

        public GetMoldByUserIdTask(
            GetMoldByUserIdRequest request,
            AsyncAction<AsyncResult<GetMoldByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMoldByUserIdResult parse(JsonNode data) {
            return GetMoldByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void getMoldByUserIdAsync(
            GetMoldByUserIdRequest request,
            AsyncAction<AsyncResult<GetMoldByUserIdResult>> callback
    ) {
        GetMoldByUserIdTask task = new GetMoldByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetMoldByUserIdResult getMoldByUserId(
            GetMoldByUserIdRequest request
    ) {
        final AsyncResult<GetMoldByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMoldCapacityByUserIdTask extends Gs2RestSessionTask<SetMoldCapacityByUserIdResult> {
        private SetMoldCapacityByUserIdRequest request;

        public SetMoldCapacityByUserIdTask(
            SetMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetMoldCapacityByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetMoldCapacityByUserIdResult parse(JsonNode data) {
            return SetMoldCapacityByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("capacity", request.getCapacity());
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

    public void setMoldCapacityByUserIdAsync(
            SetMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetMoldCapacityByUserIdResult>> callback
    ) {
        SetMoldCapacityByUserIdTask task = new SetMoldCapacityByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetMoldCapacityByUserIdResult setMoldCapacityByUserId(
            SetMoldCapacityByUserIdRequest request
    ) {
        final AsyncResult<SetMoldCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMoldCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddMoldCapacityByUserIdTask extends Gs2RestSessionTask<AddMoldCapacityByUserIdResult> {
        private AddMoldCapacityByUserIdRequest request;

        public AddMoldCapacityByUserIdTask(
            AddMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddMoldCapacityByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddMoldCapacityByUserIdResult parse(JsonNode data) {
            return AddMoldCapacityByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("capacity", request.getCapacity());
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

    public void addMoldCapacityByUserIdAsync(
            AddMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddMoldCapacityByUserIdResult>> callback
    ) {
        AddMoldCapacityByUserIdTask task = new AddMoldCapacityByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddMoldCapacityByUserIdResult addMoldCapacityByUserId(
            AddMoldCapacityByUserIdRequest request
    ) {
        final AsyncResult<AddMoldCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addMoldCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SubMoldCapacityByUserIdTask extends Gs2RestSessionTask<SubMoldCapacityByUserIdResult> {
        private SubMoldCapacityByUserIdRequest request;

        public SubMoldCapacityByUserIdTask(
            SubMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SubMoldCapacityByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SubMoldCapacityByUserIdResult parse(JsonNode data) {
            return SubMoldCapacityByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/sub";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("capacity", request.getCapacity());
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

    public void subMoldCapacityByUserIdAsync(
            SubMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SubMoldCapacityByUserIdResult>> callback
    ) {
        SubMoldCapacityByUserIdTask task = new SubMoldCapacityByUserIdTask(request, callback);
        session.execute(task);
    }

    public SubMoldCapacityByUserIdResult subMoldCapacityByUserId(
            SubMoldCapacityByUserIdRequest request
    ) {
        final AsyncResult<SubMoldCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        subMoldCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMoldTask extends Gs2RestSessionTask<DeleteMoldResult> {
        private DeleteMoldRequest request;

        public DeleteMoldTask(
            DeleteMoldRequest request,
            AsyncAction<AsyncResult<DeleteMoldResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMoldResult parse(JsonNode data) {
            return DeleteMoldResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void deleteMoldAsync(
            DeleteMoldRequest request,
            AsyncAction<AsyncResult<DeleteMoldResult>> callback
    ) {
        DeleteMoldTask task = new DeleteMoldTask(request, callback);
        session.execute(task);
    }

    public DeleteMoldResult deleteMold(
            DeleteMoldRequest request
    ) {
        final AsyncResult<DeleteMoldResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMoldAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMoldByUserIdTask extends Gs2RestSessionTask<DeleteMoldByUserIdResult> {
        private DeleteMoldByUserIdRequest request;

        public DeleteMoldByUserIdTask(
            DeleteMoldByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteMoldByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMoldByUserIdResult parse(JsonNode data) {
            return DeleteMoldByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void deleteMoldByUserIdAsync(
            DeleteMoldByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteMoldByUserIdResult>> callback
    ) {
        DeleteMoldByUserIdTask task = new DeleteMoldByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteMoldByUserIdResult deleteMoldByUserId(
            DeleteMoldByUserIdRequest request
    ) {
        final AsyncResult<DeleteMoldByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMoldByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/mold/capacity/add";

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

    class SubCapacityByStampTaskTask extends Gs2RestSessionTask<SubCapacityByStampTaskResult> {
        private SubCapacityByStampTaskRequest request;

        public SubCapacityByStampTaskTask(
            SubCapacityByStampTaskRequest request,
            AsyncAction<AsyncResult<SubCapacityByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SubCapacityByStampTaskResult parse(JsonNode data) {
            return SubCapacityByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/mold/capacity/sub";

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

    public void subCapacityByStampTaskAsync(
            SubCapacityByStampTaskRequest request,
            AsyncAction<AsyncResult<SubCapacityByStampTaskResult>> callback
    ) {
        SubCapacityByStampTaskTask task = new SubCapacityByStampTaskTask(request, callback);
        session.execute(task);
    }

    public SubCapacityByStampTaskResult subCapacityByStampTask(
            SubCapacityByStampTaskRequest request
    ) {
        final AsyncResult<SubCapacityByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        subCapacityByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/mold/capacity/set";

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

    class DescribeFormsTask extends Gs2RestSessionTask<DescribeFormsResult> {
        private DescribeFormsRequest request;

        public DescribeFormsTask(
            DescribeFormsRequest request,
            AsyncAction<AsyncResult<DescribeFormsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFormsResult parse(JsonNode data) {
            return DescribeFormsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));

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

    public void describeFormsAsync(
            DescribeFormsRequest request,
            AsyncAction<AsyncResult<DescribeFormsResult>> callback
    ) {
        DescribeFormsTask task = new DescribeFormsTask(request, callback);
        session.execute(task);
    }

    public DescribeFormsResult describeForms(
            DescribeFormsRequest request
    ) {
        final AsyncResult<DescribeFormsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFormsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFormsByUserIdTask extends Gs2RestSessionTask<DescribeFormsByUserIdResult> {
        private DescribeFormsByUserIdRequest request;

        public DescribeFormsByUserIdTask(
            DescribeFormsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFormsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFormsByUserIdResult parse(JsonNode data) {
            return DescribeFormsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
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

    public void describeFormsByUserIdAsync(
            DescribeFormsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFormsByUserIdResult>> callback
    ) {
        DescribeFormsByUserIdTask task = new DescribeFormsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeFormsByUserIdResult describeFormsByUserId(
            DescribeFormsByUserIdRequest request
    ) {
        final AsyncResult<DescribeFormsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFormsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormTask extends Gs2RestSessionTask<GetFormResult> {
        private GetFormRequest request;

        public GetFormTask(
            GetFormRequest request,
            AsyncAction<AsyncResult<GetFormResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFormResult parse(JsonNode data) {
            return GetFormResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

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

    public void getFormAsync(
            GetFormRequest request,
            AsyncAction<AsyncResult<GetFormResult>> callback
    ) {
        GetFormTask task = new GetFormTask(request, callback);
        session.execute(task);
    }

    public GetFormResult getForm(
            GetFormRequest request
    ) {
        final AsyncResult<GetFormResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormByUserIdTask extends Gs2RestSessionTask<GetFormByUserIdResult> {
        private GetFormByUserIdRequest request;

        public GetFormByUserIdTask(
            GetFormByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFormByUserIdResult parse(JsonNode data) {
            return GetFormByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

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

    public void getFormByUserIdAsync(
            GetFormByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormByUserIdResult>> callback
    ) {
        GetFormByUserIdTask task = new GetFormByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetFormByUserIdResult getFormByUserId(
            GetFormByUserIdRequest request
    ) {
        final AsyncResult<GetFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormWithSignatureTask extends Gs2RestSessionTask<GetFormWithSignatureResult> {
        private GetFormWithSignatureRequest request;

        public GetFormWithSignatureTask(
            GetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFormWithSignatureResult parse(JsonNode data) {
            return GetFormWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}/form/{index}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

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

    public void getFormWithSignatureAsync(
            GetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureResult>> callback
    ) {
        GetFormWithSignatureTask task = new GetFormWithSignatureTask(request, callback);
        session.execute(task);
    }

    public GetFormWithSignatureResult getFormWithSignature(
            GetFormWithSignatureRequest request
    ) {
        final AsyncResult<GetFormWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormWithSignatureByUserIdTask extends Gs2RestSessionTask<GetFormWithSignatureByUserIdResult> {
        private GetFormWithSignatureByUserIdRequest request;

        public GetFormWithSignatureByUserIdTask(
            GetFormWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFormWithSignatureByUserIdResult parse(JsonNode data) {
            return GetFormWithSignatureByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/form/{index}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

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

    public void getFormWithSignatureByUserIdAsync(
            GetFormWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureByUserIdResult>> callback
    ) {
        GetFormWithSignatureByUserIdTask task = new GetFormWithSignatureByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetFormWithSignatureByUserIdResult getFormWithSignatureByUserId(
            GetFormWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetFormWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormWithSignatureByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFormByUserIdTask extends Gs2RestSessionTask<SetFormByUserIdResult> {
        private SetFormByUserIdRequest request;

        public SetFormByUserIdTask(
            SetFormByUserIdRequest request,
            AsyncAction<AsyncResult<SetFormByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetFormByUserIdResult parse(JsonNode data) {
            return SetFormByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("slots", request.getSlots() == null ? new ArrayList<Slot>() :
                        request.getSlots().stream().map(item -> {
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

    public void setFormByUserIdAsync(
            SetFormByUserIdRequest request,
            AsyncAction<AsyncResult<SetFormByUserIdResult>> callback
    ) {
        SetFormByUserIdTask task = new SetFormByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetFormByUserIdResult setFormByUserId(
            SetFormByUserIdRequest request
    ) {
        final AsyncResult<SetFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFormWithSignatureTask extends Gs2RestSessionTask<SetFormWithSignatureResult> {
        private SetFormWithSignatureRequest request;

        public SetFormWithSignatureTask(
            SetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<SetFormWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetFormWithSignatureResult parse(JsonNode data) {
            return SetFormWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("slots", request.getSlots() == null ? new ArrayList<SlotWithSignature>() :
                        request.getSlots().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("keyId", request.getKeyId());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setFormWithSignatureAsync(
            SetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<SetFormWithSignatureResult>> callback
    ) {
        SetFormWithSignatureTask task = new SetFormWithSignatureTask(request, callback);
        session.execute(task);
    }

    public SetFormWithSignatureResult setFormWithSignature(
            SetFormWithSignatureRequest request
    ) {
        final AsyncResult<SetFormWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFormWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireActionsToFormPropertiesTask extends Gs2RestSessionTask<AcquireActionsToFormPropertiesResult> {
        private AcquireActionsToFormPropertiesRequest request;

        public AcquireActionsToFormPropertiesTask(
            AcquireActionsToFormPropertiesRequest request,
            AsyncAction<AsyncResult<AcquireActionsToFormPropertiesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireActionsToFormPropertiesResult parse(JsonNode data) {
            return AcquireActionsToFormPropertiesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/form/{index}/stamp/delegate";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("acquireAction", request.getAcquireAction() != null ? request.getAcquireAction().toJson() : null);
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

            builder
                .build()
                .send();
        }
    }

    public void acquireActionsToFormPropertiesAsync(
            AcquireActionsToFormPropertiesRequest request,
            AsyncAction<AsyncResult<AcquireActionsToFormPropertiesResult>> callback
    ) {
        AcquireActionsToFormPropertiesTask task = new AcquireActionsToFormPropertiesTask(request, callback);
        session.execute(task);
    }

    public AcquireActionsToFormPropertiesResult acquireActionsToFormProperties(
            AcquireActionsToFormPropertiesRequest request
    ) {
        final AsyncResult<AcquireActionsToFormPropertiesResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireActionsToFormPropertiesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFormTask extends Gs2RestSessionTask<DeleteFormResult> {
        private DeleteFormRequest request;

        public DeleteFormTask(
            DeleteFormRequest request,
            AsyncAction<AsyncResult<DeleteFormResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFormResult parse(JsonNode data) {
            return DeleteFormResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldModelName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

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

    public void deleteFormAsync(
            DeleteFormRequest request,
            AsyncAction<AsyncResult<DeleteFormResult>> callback
    ) {
        DeleteFormTask task = new DeleteFormTask(request, callback);
        session.execute(task);
    }

    public DeleteFormResult deleteForm(
            DeleteFormRequest request
    ) {
        final AsyncResult<DeleteFormResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFormAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFormByUserIdTask extends Gs2RestSessionTask<DeleteFormByUserIdResult> {
        private DeleteFormByUserIdRequest request;

        public DeleteFormByUserIdTask(
            DeleteFormByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFormByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFormByUserIdResult parse(JsonNode data) {
            return DeleteFormByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldModelName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldModelName}", this.request.getMoldModelName() == null || this.request.getMoldModelName().length() == 0 ? "null" : String.valueOf(this.request.getMoldModelName()));
            url = url.replace("{index}", this.request.getIndex() == null  ? "null" : String.valueOf(this.request.getIndex()));

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

    public void deleteFormByUserIdAsync(
            DeleteFormByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFormByUserIdResult>> callback
    ) {
        DeleteFormByUserIdTask task = new DeleteFormByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteFormByUserIdResult deleteFormByUserId(
            DeleteFormByUserIdRequest request
    ) {
        final AsyncResult<DeleteFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireActionToFormPropertiesByStampSheetTask extends Gs2RestSessionTask<AcquireActionToFormPropertiesByStampSheetResult> {
        private AcquireActionToFormPropertiesByStampSheetRequest request;

        public AcquireActionToFormPropertiesByStampSheetTask(
            AcquireActionToFormPropertiesByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireActionToFormPropertiesByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireActionToFormPropertiesByStampSheetResult parse(JsonNode data) {
            return AcquireActionToFormPropertiesByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/form/acquire";

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

    public void acquireActionToFormPropertiesByStampSheetAsync(
            AcquireActionToFormPropertiesByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireActionToFormPropertiesByStampSheetResult>> callback
    ) {
        AcquireActionToFormPropertiesByStampSheetTask task = new AcquireActionToFormPropertiesByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquireActionToFormPropertiesByStampSheetResult acquireActionToFormPropertiesByStampSheet(
            AcquireActionToFormPropertiesByStampSheetRequest request
    ) {
        final AsyncResult<AcquireActionToFormPropertiesByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireActionToFormPropertiesByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePropertyFormsTask extends Gs2RestSessionTask<DescribePropertyFormsResult> {
        private DescribePropertyFormsRequest request;

        public DescribePropertyFormsTask(
            DescribePropertyFormsRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePropertyFormsResult parse(JsonNode data) {
            return DescribePropertyFormsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/property/{propertyFormModelName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));

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

    public void describePropertyFormsAsync(
            DescribePropertyFormsRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormsResult>> callback
    ) {
        DescribePropertyFormsTask task = new DescribePropertyFormsTask(request, callback);
        session.execute(task);
    }

    public DescribePropertyFormsResult describePropertyForms(
            DescribePropertyFormsRequest request
    ) {
        final AsyncResult<DescribePropertyFormsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePropertyFormsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePropertyFormsByUserIdTask extends Gs2RestSessionTask<DescribePropertyFormsByUserIdResult> {
        private DescribePropertyFormsByUserIdRequest request;

        public DescribePropertyFormsByUserIdTask(
            DescribePropertyFormsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePropertyFormsByUserIdResult parse(JsonNode data) {
            return DescribePropertyFormsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/property/{propertyFormModelName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));

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

    public void describePropertyFormsByUserIdAsync(
            DescribePropertyFormsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribePropertyFormsByUserIdResult>> callback
    ) {
        DescribePropertyFormsByUserIdTask task = new DescribePropertyFormsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribePropertyFormsByUserIdResult describePropertyFormsByUserId(
            DescribePropertyFormsByUserIdRequest request
    ) {
        final AsyncResult<DescribePropertyFormsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePropertyFormsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPropertyFormTask extends Gs2RestSessionTask<GetPropertyFormResult> {
        private GetPropertyFormRequest request;

        public GetPropertyFormTask(
            GetPropertyFormRequest request,
            AsyncAction<AsyncResult<GetPropertyFormResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPropertyFormResult parse(JsonNode data) {
            return GetPropertyFormResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/property/{propertyFormModelName}/form/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
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

    public void getPropertyFormAsync(
            GetPropertyFormRequest request,
            AsyncAction<AsyncResult<GetPropertyFormResult>> callback
    ) {
        GetPropertyFormTask task = new GetPropertyFormTask(request, callback);
        session.execute(task);
    }

    public GetPropertyFormResult getPropertyForm(
            GetPropertyFormRequest request
    ) {
        final AsyncResult<GetPropertyFormResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPropertyFormAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPropertyFormByUserIdTask extends Gs2RestSessionTask<GetPropertyFormByUserIdResult> {
        private GetPropertyFormByUserIdRequest request;

        public GetPropertyFormByUserIdTask(
            GetPropertyFormByUserIdRequest request,
            AsyncAction<AsyncResult<GetPropertyFormByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPropertyFormByUserIdResult parse(JsonNode data) {
            return GetPropertyFormByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/property/{propertyFormModelName}/form/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
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

    public void getPropertyFormByUserIdAsync(
            GetPropertyFormByUserIdRequest request,
            AsyncAction<AsyncResult<GetPropertyFormByUserIdResult>> callback
    ) {
        GetPropertyFormByUserIdTask task = new GetPropertyFormByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetPropertyFormByUserIdResult getPropertyFormByUserId(
            GetPropertyFormByUserIdRequest request
    ) {
        final AsyncResult<GetPropertyFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPropertyFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPropertyFormWithSignatureTask extends Gs2RestSessionTask<GetPropertyFormWithSignatureResult> {
        private GetPropertyFormWithSignatureRequest request;

        public GetPropertyFormWithSignatureTask(
            GetPropertyFormWithSignatureRequest request,
            AsyncAction<AsyncResult<GetPropertyFormWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPropertyFormWithSignatureResult parse(JsonNode data) {
            return GetPropertyFormWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/property/{propertyFormModelName}/form/{propertyId}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
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

    public void getPropertyFormWithSignatureAsync(
            GetPropertyFormWithSignatureRequest request,
            AsyncAction<AsyncResult<GetPropertyFormWithSignatureResult>> callback
    ) {
        GetPropertyFormWithSignatureTask task = new GetPropertyFormWithSignatureTask(request, callback);
        session.execute(task);
    }

    public GetPropertyFormWithSignatureResult getPropertyFormWithSignature(
            GetPropertyFormWithSignatureRequest request
    ) {
        final AsyncResult<GetPropertyFormWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPropertyFormWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPropertyFormWithSignatureByUserIdTask extends Gs2RestSessionTask<GetPropertyFormWithSignatureByUserIdResult> {
        private GetPropertyFormWithSignatureByUserIdRequest request;

        public GetPropertyFormWithSignatureByUserIdTask(
            GetPropertyFormWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetPropertyFormWithSignatureByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPropertyFormWithSignatureByUserIdResult parse(JsonNode data) {
            return GetPropertyFormWithSignatureByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/property/{propertyFormModelName}/form/{propertyId}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
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

    public void getPropertyFormWithSignatureByUserIdAsync(
            GetPropertyFormWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetPropertyFormWithSignatureByUserIdResult>> callback
    ) {
        GetPropertyFormWithSignatureByUserIdTask task = new GetPropertyFormWithSignatureByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetPropertyFormWithSignatureByUserIdResult getPropertyFormWithSignatureByUserId(
            GetPropertyFormWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetPropertyFormWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPropertyFormWithSignatureByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetPropertyFormByUserIdTask extends Gs2RestSessionTask<SetPropertyFormByUserIdResult> {
        private SetPropertyFormByUserIdRequest request;

        public SetPropertyFormByUserIdTask(
            SetPropertyFormByUserIdRequest request,
            AsyncAction<AsyncResult<SetPropertyFormByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetPropertyFormByUserIdResult parse(JsonNode data) {
            return SetPropertyFormByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/property/{propertyFormModelName}/form/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("slots", request.getSlots() == null ? new ArrayList<Slot>() :
                        request.getSlots().stream().map(item -> {
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

    public void setPropertyFormByUserIdAsync(
            SetPropertyFormByUserIdRequest request,
            AsyncAction<AsyncResult<SetPropertyFormByUserIdResult>> callback
    ) {
        SetPropertyFormByUserIdTask task = new SetPropertyFormByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetPropertyFormByUserIdResult setPropertyFormByUserId(
            SetPropertyFormByUserIdRequest request
    ) {
        final AsyncResult<SetPropertyFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setPropertyFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetPropertyFormWithSignatureTask extends Gs2RestSessionTask<SetPropertyFormWithSignatureResult> {
        private SetPropertyFormWithSignatureRequest request;

        public SetPropertyFormWithSignatureTask(
            SetPropertyFormWithSignatureRequest request,
            AsyncAction<AsyncResult<SetPropertyFormWithSignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetPropertyFormWithSignatureResult parse(JsonNode data) {
            return SetPropertyFormWithSignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/property/{propertyFormModelName}/form/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("slots", request.getSlots() == null ? new ArrayList<SlotWithSignature>() :
                        request.getSlots().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("keyId", request.getKeyId());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setPropertyFormWithSignatureAsync(
            SetPropertyFormWithSignatureRequest request,
            AsyncAction<AsyncResult<SetPropertyFormWithSignatureResult>> callback
    ) {
        SetPropertyFormWithSignatureTask task = new SetPropertyFormWithSignatureTask(request, callback);
        session.execute(task);
    }

    public SetPropertyFormWithSignatureResult setPropertyFormWithSignature(
            SetPropertyFormWithSignatureRequest request
    ) {
        final AsyncResult<SetPropertyFormWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        setPropertyFormWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireActionsToPropertyFormPropertiesTask extends Gs2RestSessionTask<AcquireActionsToPropertyFormPropertiesResult> {
        private AcquireActionsToPropertyFormPropertiesRequest request;

        public AcquireActionsToPropertyFormPropertiesTask(
            AcquireActionsToPropertyFormPropertiesRequest request,
            AsyncAction<AsyncResult<AcquireActionsToPropertyFormPropertiesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireActionsToPropertyFormPropertiesResult parse(JsonNode data) {
            return AcquireActionsToPropertyFormPropertiesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/property/{propertyFormModelName}/form/{propertyId}/stamp/delegate";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null || this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("acquireAction", request.getAcquireAction() != null ? request.getAcquireAction().toJson() : null);
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

            builder
                .build()
                .send();
        }
    }

    public void acquireActionsToPropertyFormPropertiesAsync(
            AcquireActionsToPropertyFormPropertiesRequest request,
            AsyncAction<AsyncResult<AcquireActionsToPropertyFormPropertiesResult>> callback
    ) {
        AcquireActionsToPropertyFormPropertiesTask task = new AcquireActionsToPropertyFormPropertiesTask(request, callback);
        session.execute(task);
    }

    public AcquireActionsToPropertyFormPropertiesResult acquireActionsToPropertyFormProperties(
            AcquireActionsToPropertyFormPropertiesRequest request
    ) {
        final AsyncResult<AcquireActionsToPropertyFormPropertiesResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireActionsToPropertyFormPropertiesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePropertyFormTask extends Gs2RestSessionTask<DeletePropertyFormResult> {
        private DeletePropertyFormRequest request;

        public DeletePropertyFormTask(
            DeletePropertyFormRequest request,
            AsyncAction<AsyncResult<DeletePropertyFormResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePropertyFormResult parse(JsonNode data) {
            return DeletePropertyFormResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/property/{propertyFormModelName}/form/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
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

    public void deletePropertyFormAsync(
            DeletePropertyFormRequest request,
            AsyncAction<AsyncResult<DeletePropertyFormResult>> callback
    ) {
        DeletePropertyFormTask task = new DeletePropertyFormTask(request, callback);
        session.execute(task);
    }

    public DeletePropertyFormResult deletePropertyForm(
            DeletePropertyFormRequest request
    ) {
        final AsyncResult<DeletePropertyFormResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePropertyFormAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePropertyFormByUserIdTask extends Gs2RestSessionTask<DeletePropertyFormByUserIdResult> {
        private DeletePropertyFormByUserIdRequest request;

        public DeletePropertyFormByUserIdTask(
            DeletePropertyFormByUserIdRequest request,
            AsyncAction<AsyncResult<DeletePropertyFormByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePropertyFormByUserIdResult parse(JsonNode data) {
            return DeletePropertyFormByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/property/{propertyFormModelName}/form/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{propertyFormModelName}", this.request.getPropertyFormModelName() == null || this.request.getPropertyFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getPropertyFormModelName()));
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

    public void deletePropertyFormByUserIdAsync(
            DeletePropertyFormByUserIdRequest request,
            AsyncAction<AsyncResult<DeletePropertyFormByUserIdResult>> callback
    ) {
        DeletePropertyFormByUserIdTask task = new DeletePropertyFormByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeletePropertyFormByUserIdResult deletePropertyFormByUserId(
            DeletePropertyFormByUserIdRequest request
    ) {
        final AsyncResult<DeletePropertyFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePropertyFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireActionToPropertyFormPropertiesByStampSheetTask extends Gs2RestSessionTask<AcquireActionToPropertyFormPropertiesByStampSheetResult> {
        private AcquireActionToPropertyFormPropertiesByStampSheetRequest request;

        public AcquireActionToPropertyFormPropertiesByStampSheetTask(
            AcquireActionToPropertyFormPropertiesByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireActionToPropertyFormPropertiesByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireActionToPropertyFormPropertiesByStampSheetResult parse(JsonNode data) {
            return AcquireActionToPropertyFormPropertiesByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/property/form/acquire";

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

    public void acquireActionToPropertyFormPropertiesByStampSheetAsync(
            AcquireActionToPropertyFormPropertiesByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireActionToPropertyFormPropertiesByStampSheetResult>> callback
    ) {
        AcquireActionToPropertyFormPropertiesByStampSheetTask task = new AcquireActionToPropertyFormPropertiesByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquireActionToPropertyFormPropertiesByStampSheetResult acquireActionToPropertyFormPropertiesByStampSheet(
            AcquireActionToPropertyFormPropertiesByStampSheetRequest request
    ) {
        final AsyncResult<AcquireActionToPropertyFormPropertiesByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireActionToPropertyFormPropertiesByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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