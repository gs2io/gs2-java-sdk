
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

package io.gs2.ranking;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.concurrent.atomic.AtomicReference;
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
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;
import io.gs2.ranking.model.*;

public class Gs2RankingRestClient extends AbstractGs2Client<Gs2RankingRestClient> {

	public Gs2RankingRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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

    class GetServiceVersionTask extends Gs2RestSessionTask<GetServiceVersionResult> {
        private GetServiceVersionRequest request;

        public GetServiceVersionTask(
            GetServiceVersionRequest request,
            AsyncAction<AsyncResult<GetServiceVersionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetServiceVersionResult parse(JsonNode data) {
            return GetServiceVersionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/system/version";

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

    public void getServiceVersionAsync(
            GetServiceVersionRequest request,
            AsyncAction<AsyncResult<GetServiceVersionResult>> callback
    ) {
        GetServiceVersionTask task = new GetServiceVersionTask(request, callback);
        session.execute(task);
    }

    public GetServiceVersionResult getServiceVersion(
            GetServiceVersionRequest request
    ) {
        final AsyncResult<GetServiceVersionResult>[] resultAsyncResult = new AsyncResult[]{null};
        getServiceVersionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
                .replace("{service}", "ranking")
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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

    class DescribeCategoryModelsTask extends Gs2RestSessionTask<DescribeCategoryModelsResult> {
        private DescribeCategoryModelsRequest request;

        public DescribeCategoryModelsTask(
            DescribeCategoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCategoryModelsResult parse(JsonNode data) {
            return DescribeCategoryModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/category";

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

    public void describeCategoryModelsAsync(
            DescribeCategoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelsResult>> callback
    ) {
        DescribeCategoryModelsTask task = new DescribeCategoryModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeCategoryModelsResult describeCategoryModels(
            DescribeCategoryModelsRequest request
    ) {
        final AsyncResult<DescribeCategoryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCategoryModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCategoryModelTask extends Gs2RestSessionTask<GetCategoryModelResult> {
        private GetCategoryModelRequest request;

        public GetCategoryModelTask(
            GetCategoryModelRequest request,
            AsyncAction<AsyncResult<GetCategoryModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCategoryModelResult parse(JsonNode data) {
            return GetCategoryModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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

    public void getCategoryModelAsync(
            GetCategoryModelRequest request,
            AsyncAction<AsyncResult<GetCategoryModelResult>> callback
    ) {
        GetCategoryModelTask task = new GetCategoryModelTask(request, callback);
        session.execute(task);
    }

    public GetCategoryModelResult getCategoryModel(
            GetCategoryModelRequest request
    ) {
        final AsyncResult<GetCategoryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCategoryModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCategoryModelMastersTask extends Gs2RestSessionTask<DescribeCategoryModelMastersResult> {
        private DescribeCategoryModelMastersRequest request;

        public DescribeCategoryModelMastersTask(
            DescribeCategoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCategoryModelMastersResult parse(JsonNode data) {
            return DescribeCategoryModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category";

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

    public void describeCategoryModelMastersAsync(
            DescribeCategoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelMastersResult>> callback
    ) {
        DescribeCategoryModelMastersTask task = new DescribeCategoryModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeCategoryModelMastersResult describeCategoryModelMasters(
            DescribeCategoryModelMastersRequest request
    ) {
        final AsyncResult<DescribeCategoryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCategoryModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateCategoryModelMasterTask extends Gs2RestSessionTask<CreateCategoryModelMasterResult> {
        private CreateCategoryModelMasterRequest request;

        public CreateCategoryModelMasterTask(
            CreateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCategoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateCategoryModelMasterResult parse(JsonNode data) {
            return CreateCategoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("orderDirection", request.getOrderDirection());
                    put("scope", request.getScope());
                    put("globalRankingSetting", request.getGlobalRankingSetting() != null ? request.getGlobalRankingSetting().toJson() : null);
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
                    put("uniqueByUserId", request.getUniqueByUserId());
                    put("sum", request.getSum());
                    put("calculateFixedTimingHour", request.getCalculateFixedTimingHour());
                    put("calculateFixedTimingMinute", request.getCalculateFixedTimingMinute());
                    put("calculateIntervalMinutes", request.getCalculateIntervalMinutes());
                    put("additionalScopes", request.getAdditionalScopes() == null ? null :
                        request.getAdditionalScopes().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("ignoreUserIds", request.getIgnoreUserIds() == null ? null :
                        request.getIgnoreUserIds().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("generation", request.getGeneration());
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

    public void createCategoryModelMasterAsync(
            CreateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCategoryModelMasterResult>> callback
    ) {
        CreateCategoryModelMasterTask task = new CreateCategoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateCategoryModelMasterResult createCategoryModelMaster(
            CreateCategoryModelMasterRequest request
    ) {
        final AsyncResult<CreateCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCategoryModelMasterTask extends Gs2RestSessionTask<GetCategoryModelMasterResult> {
        private GetCategoryModelMasterRequest request;

        public GetCategoryModelMasterTask(
            GetCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetCategoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCategoryModelMasterResult parse(JsonNode data) {
            return GetCategoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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

    public void getCategoryModelMasterAsync(
            GetCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetCategoryModelMasterResult>> callback
    ) {
        GetCategoryModelMasterTask task = new GetCategoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetCategoryModelMasterResult getCategoryModelMaster(
            GetCategoryModelMasterRequest request
    ) {
        final AsyncResult<GetCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCategoryModelMasterTask extends Gs2RestSessionTask<UpdateCategoryModelMasterResult> {
        private UpdateCategoryModelMasterRequest request;

        public UpdateCategoryModelMasterTask(
            UpdateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCategoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCategoryModelMasterResult parse(JsonNode data) {
            return UpdateCategoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("orderDirection", request.getOrderDirection());
                    put("scope", request.getScope());
                    put("globalRankingSetting", request.getGlobalRankingSetting() != null ? request.getGlobalRankingSetting().toJson() : null);
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
                    put("uniqueByUserId", request.getUniqueByUserId());
                    put("sum", request.getSum());
                    put("calculateFixedTimingHour", request.getCalculateFixedTimingHour());
                    put("calculateFixedTimingMinute", request.getCalculateFixedTimingMinute());
                    put("calculateIntervalMinutes", request.getCalculateIntervalMinutes());
                    put("additionalScopes", request.getAdditionalScopes() == null ? null :
                        request.getAdditionalScopes().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("ignoreUserIds", request.getIgnoreUserIds() == null ? null :
                        request.getIgnoreUserIds().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("generation", request.getGeneration());
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

    public void updateCategoryModelMasterAsync(
            UpdateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCategoryModelMasterResult>> callback
    ) {
        UpdateCategoryModelMasterTask task = new UpdateCategoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCategoryModelMasterResult updateCategoryModelMaster(
            UpdateCategoryModelMasterRequest request
    ) {
        final AsyncResult<UpdateCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCategoryModelMasterTask extends Gs2RestSessionTask<DeleteCategoryModelMasterResult> {
        private DeleteCategoryModelMasterRequest request;

        public DeleteCategoryModelMasterTask(
            DeleteCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCategoryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteCategoryModelMasterResult parse(JsonNode data) {
            return DeleteCategoryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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

    public void deleteCategoryModelMasterAsync(
            DeleteCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCategoryModelMasterResult>> callback
    ) {
        DeleteCategoryModelMasterTask task = new DeleteCategoryModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteCategoryModelMasterResult deleteCategoryModelMaster(
            DeleteCategoryModelMasterRequest request
    ) {
        final AsyncResult<DeleteCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SubscribeTask extends Gs2RestSessionTask<SubscribeResult> {
        private SubscribeRequest request;

        public SubscribeTask(
            SubscribeRequest request,
            AsyncAction<AsyncResult<SubscribeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SubscribeResult parse(JsonNode data) {
            return SubscribeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void subscribeAsync(
            SubscribeRequest request,
            AsyncAction<AsyncResult<SubscribeResult>> callback
    ) {
        SubscribeTask task = new SubscribeTask(request, callback);
        session.execute(task);
    }

    public SubscribeResult subscribe(
            SubscribeRequest request
    ) {
        final AsyncResult<SubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        subscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SubscribeByUserIdTask extends Gs2RestSessionTask<SubscribeByUserIdResult> {
        private SubscribeByUserIdRequest request;

        public SubscribeByUserIdTask(
            SubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<SubscribeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SubscribeByUserIdResult parse(JsonNode data) {
            return SubscribeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void subscribeByUserIdAsync(
            SubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<SubscribeByUserIdResult>> callback
    ) {
        SubscribeByUserIdTask task = new SubscribeByUserIdTask(request, callback);
        session.execute(task);
    }

    public SubscribeByUserIdResult subscribeByUserId(
            SubscribeByUserIdRequest request
    ) {
        final AsyncResult<SubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        subscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeScoresTask extends Gs2RestSessionTask<DescribeScoresResult> {
        private DescribeScoresRequest request;

        public DescribeScoresTask(
            DescribeScoresRequest request,
            AsyncAction<AsyncResult<DescribeScoresResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeScoresResult parse(JsonNode data) {
            return DescribeScoresResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/scorer/{scorerUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null || this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));

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

    public void describeScoresAsync(
            DescribeScoresRequest request,
            AsyncAction<AsyncResult<DescribeScoresResult>> callback
    ) {
        DescribeScoresTask task = new DescribeScoresTask(request, callback);
        session.execute(task);
    }

    public DescribeScoresResult describeScores(
            DescribeScoresRequest request
    ) {
        final AsyncResult<DescribeScoresResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeScoresAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeScoresByUserIdTask extends Gs2RestSessionTask<DescribeScoresByUserIdResult> {
        private DescribeScoresByUserIdRequest request;

        public DescribeScoresByUserIdTask(
            DescribeScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeScoresByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeScoresByUserIdResult parse(JsonNode data) {
            return DescribeScoresByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/scorer/{scorerUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null || this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));

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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeScoresByUserIdAsync(
            DescribeScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeScoresByUserIdResult>> callback
    ) {
        DescribeScoresByUserIdTask task = new DescribeScoresByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeScoresByUserIdResult describeScoresByUserId(
            DescribeScoresByUserIdRequest request
    ) {
        final AsyncResult<DescribeScoresByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeScoresByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetScoreTask extends Gs2RestSessionTask<GetScoreResult> {
        private GetScoreRequest request;

        public GetScoreTask(
            GetScoreRequest request,
            AsyncAction<AsyncResult<GetScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetScoreResult parse(JsonNode data) {
            return GetScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null || this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null || this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

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

    public void getScoreAsync(
            GetScoreRequest request,
            AsyncAction<AsyncResult<GetScoreResult>> callback
    ) {
        GetScoreTask task = new GetScoreTask(request, callback);
        session.execute(task);
    }

    public GetScoreResult getScore(
            GetScoreRequest request
    ) {
        final AsyncResult<GetScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        getScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetScoreByUserIdTask extends Gs2RestSessionTask<GetScoreByUserIdResult> {
        private GetScoreByUserIdRequest request;

        public GetScoreByUserIdTask(
            GetScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetScoreByUserIdResult parse(JsonNode data) {
            return GetScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null || this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null || this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

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

    public void getScoreByUserIdAsync(
            GetScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetScoreByUserIdResult>> callback
    ) {
        GetScoreByUserIdTask task = new GetScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetScoreByUserIdResult getScoreByUserId(
            GetScoreByUserIdRequest request
    ) {
        final AsyncResult<GetScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRankingsTask extends Gs2RestSessionTask<DescribeRankingsResult> {
        private DescribeRankingsRequest request;

        public DescribeRankingsTask(
            DescribeRankingsRequest request,
            AsyncAction<AsyncResult<DescribeRankingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRankingsResult parse(JsonNode data) {
            return DescribeRankingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAdditionalScopeName() != null) {
                queryStrings.add("additionalScopeName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAdditionalScopeName()))));
            }
            if (this.request.getStartIndex() != null) {
                queryStrings.add("startIndex=" + String.valueOf(this.request.getStartIndex()));
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

    public void describeRankingsAsync(
            DescribeRankingsRequest request,
            AsyncAction<AsyncResult<DescribeRankingsResult>> callback
    ) {
        DescribeRankingsTask task = new DescribeRankingsTask(request, callback);
        session.execute(task);
    }

    public DescribeRankingsResult describeRankings(
            DescribeRankingsRequest request
    ) {
        final AsyncResult<DescribeRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRankingssByUserIdTask extends Gs2RestSessionTask<DescribeRankingssByUserIdResult> {
        private DescribeRankingssByUserIdRequest request;

        public DescribeRankingssByUserIdTask(
            DescribeRankingssByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRankingssByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRankingssByUserIdResult parse(JsonNode data) {
            return DescribeRankingssByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAdditionalScopeName() != null) {
                queryStrings.add("additionalScopeName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAdditionalScopeName()))));
            }
            if (this.request.getStartIndex() != null) {
                queryStrings.add("startIndex=" + String.valueOf(this.request.getStartIndex()));
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeRankingssByUserIdAsync(
            DescribeRankingssByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRankingssByUserIdResult>> callback
    ) {
        DescribeRankingssByUserIdTask task = new DescribeRankingssByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeRankingssByUserIdResult describeRankingssByUserId(
            DescribeRankingssByUserIdRequest request
    ) {
        final AsyncResult<DescribeRankingssByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRankingssByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeNearRankingsTask extends Gs2RestSessionTask<DescribeNearRankingsResult> {
        private DescribeNearRankingsRequest request;

        public DescribeNearRankingsTask(
            DescribeNearRankingsRequest request,
            AsyncAction<AsyncResult<DescribeNearRankingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeNearRankingsResult parse(JsonNode data) {
            return DescribeNearRankingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking/near";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAdditionalScopeName() != null) {
                queryStrings.add("additionalScopeName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAdditionalScopeName()))));
            }
            if (this.request.getScore() != null) {
                queryStrings.add("score=" + String.valueOf(this.request.getScore()));
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

    public void describeNearRankingsAsync(
            DescribeNearRankingsRequest request,
            AsyncAction<AsyncResult<DescribeNearRankingsResult>> callback
    ) {
        DescribeNearRankingsTask task = new DescribeNearRankingsTask(request, callback);
        session.execute(task);
    }

    public DescribeNearRankingsResult describeNearRankings(
            DescribeNearRankingsRequest request
    ) {
        final AsyncResult<DescribeNearRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeNearRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRankingTask extends Gs2RestSessionTask<GetRankingResult> {
        private GetRankingRequest request;

        public GetRankingTask(
            GetRankingRequest request,
            AsyncAction<AsyncResult<GetRankingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRankingResult parse(JsonNode data) {
            return GetRankingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null || this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null || this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAdditionalScopeName() != null) {
                queryStrings.add("additionalScopeName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAdditionalScopeName()))));
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

    public void getRankingAsync(
            GetRankingRequest request,
            AsyncAction<AsyncResult<GetRankingResult>> callback
    ) {
        GetRankingTask task = new GetRankingTask(request, callback);
        session.execute(task);
    }

    public GetRankingResult getRanking(
            GetRankingRequest request
    ) {
        final AsyncResult<GetRankingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRankingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRankingByUserIdTask extends Gs2RestSessionTask<GetRankingByUserIdResult> {
        private GetRankingByUserIdRequest request;

        public GetRankingByUserIdTask(
            GetRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRankingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRankingByUserIdResult parse(JsonNode data) {
            return GetRankingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/ranking/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null || this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null || this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAdditionalScopeName() != null) {
                queryStrings.add("additionalScopeName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAdditionalScopeName()))));
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

    public void getRankingByUserIdAsync(
            GetRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRankingByUserIdResult>> callback
    ) {
        GetRankingByUserIdTask task = new GetRankingByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetRankingByUserIdResult getRankingByUserId(
            GetRankingByUserIdRequest request
    ) {
        final AsyncResult<GetRankingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRankingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutScoreTask extends Gs2RestSessionTask<PutScoreResult> {
        private PutScoreRequest request;

        public PutScoreTask(
            PutScoreRequest request,
            AsyncAction<AsyncResult<PutScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutScoreResult parse(JsonNode data) {
            return PutScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
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

    public void putScoreAsync(
            PutScoreRequest request,
            AsyncAction<AsyncResult<PutScoreResult>> callback
    ) {
        PutScoreTask task = new PutScoreTask(request, callback);
        session.execute(task);
    }

    public PutScoreResult putScore(
            PutScoreRequest request
    ) {
        final AsyncResult<PutScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        putScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutScoreByUserIdTask extends Gs2RestSessionTask<PutScoreByUserIdResult> {
        private PutScoreByUserIdRequest request;

        public PutScoreByUserIdTask(
            PutScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutScoreByUserIdResult parse(JsonNode data) {
            return PutScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
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

    public void putScoreByUserIdAsync(
            PutScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutScoreByUserIdResult>> callback
    ) {
        PutScoreByUserIdTask task = new PutScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public PutScoreByUserIdResult putScoreByUserId(
            PutScoreByUserIdRequest request
    ) {
        final AsyncResult<PutScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        putScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CalcRankingTask extends Gs2RestSessionTask<CalcRankingResult> {
        private CalcRankingRequest request;

        public CalcRankingTask(
            CalcRankingRequest request,
            AsyncAction<AsyncResult<CalcRankingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CalcRankingResult parse(JsonNode data) {
            return CalcRankingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/category/{categoryName}/calc/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("additionalScopeName", request.getAdditionalScopeName());
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

    public void calcRankingAsync(
            CalcRankingRequest request,
            AsyncAction<AsyncResult<CalcRankingResult>> callback
    ) {
        CalcRankingTask task = new CalcRankingTask(request, callback);
        session.execute(task);
    }

    public CalcRankingResult calcRanking(
            CalcRankingRequest request
    ) {
        final AsyncResult<CalcRankingResult>[] resultAsyncResult = new AsyncResult[]{null};
        calcRankingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "ranking")
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

    class GetCurrentRankingMasterTask extends Gs2RestSessionTask<GetCurrentRankingMasterResult> {
        private GetCurrentRankingMasterRequest request;

        public GetCurrentRankingMasterTask(
            GetCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRankingMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentRankingMasterResult parse(JsonNode data) {
            return GetCurrentRankingMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
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

    public void getCurrentRankingMasterAsync(
            GetCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRankingMasterResult>> callback
    ) {
        GetCurrentRankingMasterTask task = new GetCurrentRankingMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentRankingMasterResult getCurrentRankingMaster(
            GetCurrentRankingMasterRequest request
    ) {
        final AsyncResult<GetCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PreUpdateCurrentRankingMasterTask extends Gs2RestSessionTask<PreUpdateCurrentRankingMasterResult> {
        private PreUpdateCurrentRankingMasterRequest request;

        public PreUpdateCurrentRankingMasterTask(
            PreUpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentRankingMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PreUpdateCurrentRankingMasterResult parse(JsonNode data) {
            return PreUpdateCurrentRankingMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

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

            builder
                .build()
                .send();
        }
    }

    public void preUpdateCurrentRankingMasterAsync(
            PreUpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentRankingMasterResult>> callback
    ) {
        PreUpdateCurrentRankingMasterTask task = new PreUpdateCurrentRankingMasterTask(request, callback);
        session.execute(task);
    }

    public PreUpdateCurrentRankingMasterResult preUpdateCurrentRankingMaster(
            PreUpdateCurrentRankingMasterRequest request
    ) {
        final AsyncResult<PreUpdateCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        preUpdateCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRankingMasterTask extends Gs2RestSessionTask<UpdateCurrentRankingMasterResult> {
        private UpdateCurrentRankingMasterRequest request;

        public UpdateCurrentRankingMasterTask(
            UpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentRankingMasterResult parse(JsonNode data) {
            return UpdateCurrentRankingMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {
            if (request.getSettings() != null) {
                AtomicReference<AsyncResult<PreUpdateCurrentRankingMasterResult>> resultAsyncResult = new AtomicReference<>();
                PreUpdateCurrentRankingMasterTask task = new PreUpdateCurrentRankingMasterTask(
                        new PreUpdateCurrentRankingMasterRequest()
                                .withContextStack(request.getContextStack())
                                .withNamespaceName(request.getNamespaceName()),
                        result -> resultAsyncResult.set(result)
                );
                session.execute(task);
                while (resultAsyncResult.get() == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
                if (resultAsyncResult.get().getError() != null) {
                    throw resultAsyncResult.get().getError();
                }
                {
                    byte[] b = request.getSettings().getBytes();
                    try (org.apache.http.impl.client.CloseableHttpClient client = org.apache.http.impl.client.HttpClients.createDefault()) {
                        org.apache.http.client.methods.HttpPut request = new org.apache.http.client.methods.HttpPut(resultAsyncResult.get().getResult().getUploadUrl());
                        request.addHeader("Content-Type", "application/json");
                        org.apache.http.entity.BasicHttpEntity entity = new org.apache.http.entity.BasicHttpEntity();
                        entity.setContent(new java.io.ByteArrayInputStream(b));
                        entity.setContentLength(b.length);
                        request.setEntity(entity);
                        org.apache.http.HttpResponse result = client.execute(request);
                    } catch (java.io.IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                request = request
                        .withMode("preUpload")
                        .withUploadToken(resultAsyncResult.get().getResult().getUploadToken())
                        .withSettings(null);
            }

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("mode", request.getMode());
                    put("settings", request.getSettings());
                    put("uploadToken", request.getUploadToken());
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

    public void updateCurrentRankingMasterAsync(
            UpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterResult>> callback
    ) {
        UpdateCurrentRankingMasterTask task = new UpdateCurrentRankingMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentRankingMasterResult updateCurrentRankingMaster(
            UpdateCurrentRankingMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRankingMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentRankingMasterFromGitHubResult> {
        private UpdateCurrentRankingMasterFromGitHubRequest request;

        public UpdateCurrentRankingMasterFromGitHubTask(
            UpdateCurrentRankingMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentRankingMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentRankingMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
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

    public void updateCurrentRankingMasterFromGitHubAsync(
            UpdateCurrentRankingMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentRankingMasterFromGitHubTask task = new UpdateCurrentRankingMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentRankingMasterFromGitHubResult updateCurrentRankingMasterFromGitHub(
            UpdateCurrentRankingMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRankingMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeTask extends Gs2RestSessionTask<GetSubscribeResult> {
        private GetSubscribeRequest request;

        public GetSubscribeTask(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeResult parse(JsonNode data) {
            return GetSubscribeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void getSubscribeAsync(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> callback
    ) {
        GetSubscribeTask task = new GetSubscribeTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeResult getSubscribe(
            GetSubscribeRequest request
    ) {
        final AsyncResult<GetSubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeByUserIdTask extends Gs2RestSessionTask<GetSubscribeByUserIdResult> {
        private GetSubscribeByUserIdRequest request;

        public GetSubscribeByUserIdTask(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeByUserIdResult parse(JsonNode data) {
            return GetSubscribeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void getSubscribeByUserIdAsync(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> callback
    ) {
        GetSubscribeByUserIdTask task = new GetSubscribeByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeByUserIdResult getSubscribeByUserId(
            GetSubscribeByUserIdRequest request
    ) {
        final AsyncResult<GetSubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnsubscribeTask extends Gs2RestSessionTask<UnsubscribeResult> {
        private UnsubscribeRequest request;

        public UnsubscribeTask(
            UnsubscribeRequest request,
            AsyncAction<AsyncResult<UnsubscribeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnsubscribeResult parse(JsonNode data) {
            return UnsubscribeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void unsubscribeAsync(
            UnsubscribeRequest request,
            AsyncAction<AsyncResult<UnsubscribeResult>> callback
    ) {
        UnsubscribeTask task = new UnsubscribeTask(request, callback);
        session.execute(task);
    }

    public UnsubscribeResult unsubscribe(
            UnsubscribeRequest request
    ) {
        final AsyncResult<UnsubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        unsubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnsubscribeByUserIdTask extends Gs2RestSessionTask<UnsubscribeByUserIdResult> {
        private UnsubscribeByUserIdRequest request;

        public UnsubscribeByUserIdTask(
            UnsubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<UnsubscribeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnsubscribeByUserIdResult parse(JsonNode data) {
            return UnsubscribeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void unsubscribeByUserIdAsync(
            UnsubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<UnsubscribeByUserIdResult>> callback
    ) {
        UnsubscribeByUserIdTask task = new UnsubscribeByUserIdTask(request, callback);
        session.execute(task);
    }

    public UnsubscribeByUserIdResult unsubscribeByUserId(
            UnsubscribeByUserIdRequest request
    ) {
        final AsyncResult<UnsubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        unsubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByCategoryNameTask extends Gs2RestSessionTask<DescribeSubscribesByCategoryNameResult> {
        private DescribeSubscribesByCategoryNameRequest request;

        public DescribeSubscribesByCategoryNameTask(
            DescribeSubscribesByCategoryNameRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribesByCategoryNameResult parse(JsonNode data) {
            return DescribeSubscribesByCategoryNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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

    public void describeSubscribesByCategoryNameAsync(
            DescribeSubscribesByCategoryNameRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameResult>> callback
    ) {
        DescribeSubscribesByCategoryNameTask task = new DescribeSubscribesByCategoryNameTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribesByCategoryNameResult describeSubscribesByCategoryName(
            DescribeSubscribesByCategoryNameRequest request
    ) {
        final AsyncResult<DescribeSubscribesByCategoryNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByCategoryNameAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByCategoryNameAndUserIdTask extends Gs2RestSessionTask<DescribeSubscribesByCategoryNameAndUserIdResult> {
        private DescribeSubscribesByCategoryNameAndUserIdRequest request;

        public DescribeSubscribesByCategoryNameAndUserIdTask(
            DescribeSubscribesByCategoryNameAndUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameAndUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribesByCategoryNameAndUserIdResult parse(JsonNode data) {
            return DescribeSubscribesByCategoryNameAndUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null || this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeSubscribesByCategoryNameAndUserIdAsync(
            DescribeSubscribesByCategoryNameAndUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameAndUserIdResult>> callback
    ) {
        DescribeSubscribesByCategoryNameAndUserIdTask task = new DescribeSubscribesByCategoryNameAndUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribesByCategoryNameAndUserIdResult describeSubscribesByCategoryNameAndUserId(
            DescribeSubscribesByCategoryNameAndUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscribesByCategoryNameAndUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByCategoryNameAndUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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