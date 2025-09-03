
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

package io.gs2.adReward;

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
import io.gs2.adReward.request.*;
import io.gs2.adReward.result.*;
import io.gs2.adReward.model.*;

public class Gs2AdRewardRestClient extends AbstractGs2Client<Gs2AdRewardRestClient> {

	public Gs2AdRewardRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("admob", request.getAdmob() != null ? request.getAdmob().toJson() : null);
                    put("unityAd", request.getUnityAd() != null ? request.getUnityAd().toJson() : null);
                    put("appLovinMaxes", request.getAppLovinMaxes() == null ? null :
                        request.getAppLovinMaxes().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("acquirePointScript", request.getAcquirePointScript() != null ? request.getAcquirePointScript().toJson() : null);
                    put("consumePointScript", request.getConsumePointScript() != null ? request.getConsumePointScript().toJson() : null);
                    put("changePointNotification", request.getChangePointNotification() != null ? request.getChangePointNotification().toJson() : null);
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("admob", request.getAdmob() != null ? request.getAdmob().toJson() : null);
                    put("unityAd", request.getUnityAd() != null ? request.getUnityAd().toJson() : null);
                    put("appLovinMaxes", request.getAppLovinMaxes() == null ? null :
                        request.getAppLovinMaxes().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("acquirePointScript", request.getAcquirePointScript() != null ? request.getAcquirePointScript().toJson() : null);
                    put("consumePointScript", request.getConsumePointScript() != null ? request.getConsumePointScript().toJson() : null);
                    put("changePointNotification", request.getChangePointNotification() != null ? request.getChangePointNotification().toJson() : null);
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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
                .replace("{service}", "ad-reward")
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

    class GetPointTask extends Gs2RestSessionTask<GetPointResult> {
        private GetPointRequest request;

        public GetPointTask(
            GetPointRequest request,
            AsyncAction<AsyncResult<GetPointResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPointResult parse(JsonNode data) {
            return GetPointResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/point";

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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getPointAsync(
            GetPointRequest request,
            AsyncAction<AsyncResult<GetPointResult>> callback
    ) {
        GetPointTask task = new GetPointTask(request, callback);
        session.execute(task);
    }

    public GetPointResult getPoint(
            GetPointRequest request
    ) {
        final AsyncResult<GetPointResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPointAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPointByUserIdTask extends Gs2RestSessionTask<GetPointByUserIdResult> {
        private GetPointByUserIdRequest request;

        public GetPointByUserIdTask(
            GetPointByUserIdRequest request,
            AsyncAction<AsyncResult<GetPointByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPointByUserIdResult parse(JsonNode data) {
            return GetPointByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/point";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    public void getPointByUserIdAsync(
            GetPointByUserIdRequest request,
            AsyncAction<AsyncResult<GetPointByUserIdResult>> callback
    ) {
        GetPointByUserIdTask task = new GetPointByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetPointByUserIdResult getPointByUserId(
            GetPointByUserIdRequest request
    ) {
        final AsyncResult<GetPointByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPointByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquirePointByUserIdTask extends Gs2RestSessionTask<AcquirePointByUserIdResult> {
        private AcquirePointByUserIdRequest request;

        public AcquirePointByUserIdTask(
            AcquirePointByUserIdRequest request,
            AsyncAction<AsyncResult<AcquirePointByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquirePointByUserIdResult parse(JsonNode data) {
            return AcquirePointByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/point/acquire";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("point", request.getPoint());
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

    public void acquirePointByUserIdAsync(
            AcquirePointByUserIdRequest request,
            AsyncAction<AsyncResult<AcquirePointByUserIdResult>> callback
    ) {
        AcquirePointByUserIdTask task = new AcquirePointByUserIdTask(request, callback);
        session.execute(task);
    }

    public AcquirePointByUserIdResult acquirePointByUserId(
            AcquirePointByUserIdRequest request
    ) {
        final AsyncResult<AcquirePointByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquirePointByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumePointTask extends Gs2RestSessionTask<ConsumePointResult> {
        private ConsumePointRequest request;

        public ConsumePointTask(
            ConsumePointRequest request,
            AsyncAction<AsyncResult<ConsumePointResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumePointResult parse(JsonNode data) {
            return ConsumePointResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/point/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("point", request.getPoint());
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

    public void consumePointAsync(
            ConsumePointRequest request,
            AsyncAction<AsyncResult<ConsumePointResult>> callback
    ) {
        ConsumePointTask task = new ConsumePointTask(request, callback);
        session.execute(task);
    }

    public ConsumePointResult consumePoint(
            ConsumePointRequest request
    ) {
        final AsyncResult<ConsumePointResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumePointAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumePointByUserIdTask extends Gs2RestSessionTask<ConsumePointByUserIdResult> {
        private ConsumePointByUserIdRequest request;

        public ConsumePointByUserIdTask(
            ConsumePointByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumePointByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumePointByUserIdResult parse(JsonNode data) {
            return ConsumePointByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/point/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("point", request.getPoint());
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

    public void consumePointByUserIdAsync(
            ConsumePointByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumePointByUserIdResult>> callback
    ) {
        ConsumePointByUserIdTask task = new ConsumePointByUserIdTask(request, callback);
        session.execute(task);
    }

    public ConsumePointByUserIdResult consumePointByUserId(
            ConsumePointByUserIdRequest request
    ) {
        final AsyncResult<ConsumePointByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumePointByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePointByUserIdTask extends Gs2RestSessionTask<DeletePointByUserIdResult> {
        private DeletePointByUserIdRequest request;

        public DeletePointByUserIdTask(
            DeletePointByUserIdRequest request,
            AsyncAction<AsyncResult<DeletePointByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePointByUserIdResult parse(JsonNode data) {
            return DeletePointByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/point/delete";

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

    public void deletePointByUserIdAsync(
            DeletePointByUserIdRequest request,
            AsyncAction<AsyncResult<DeletePointByUserIdResult>> callback
    ) {
        DeletePointByUserIdTask task = new DeletePointByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeletePointByUserIdResult deletePointByUserId(
            DeletePointByUserIdRequest request
    ) {
        final AsyncResult<DeletePointByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePointByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumePointByStampTaskTask extends Gs2RestSessionTask<ConsumePointByStampTaskResult> {
        private ConsumePointByStampTaskRequest request;

        public ConsumePointByStampTaskTask(
            ConsumePointByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumePointByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ConsumePointByStampTaskResult parse(JsonNode data) {
            return ConsumePointByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/point/consume";

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

    public void consumePointByStampTaskAsync(
            ConsumePointByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumePointByStampTaskResult>> callback
    ) {
        ConsumePointByStampTaskTask task = new ConsumePointByStampTaskTask(request, callback);
        session.execute(task);
    }

    public ConsumePointByStampTaskResult consumePointByStampTask(
            ConsumePointByStampTaskRequest request
    ) {
        final AsyncResult<ConsumePointByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumePointByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquirePointByStampSheetTask extends Gs2RestSessionTask<AcquirePointByStampSheetResult> {
        private AcquirePointByStampSheetRequest request;

        public AcquirePointByStampSheetTask(
            AcquirePointByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquirePointByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquirePointByStampSheetResult parse(JsonNode data) {
            return AcquirePointByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ad-reward")
                .replace("{region}", session.getRegion().getName())
                + "/point/acquire";

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

    public void acquirePointByStampSheetAsync(
            AcquirePointByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquirePointByStampSheetResult>> callback
    ) {
        AcquirePointByStampSheetTask task = new AcquirePointByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquirePointByStampSheetResult acquirePointByStampSheet(
            AcquirePointByStampSheetRequest request
    ) {
        final AsyncResult<AcquirePointByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquirePointByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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