
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

package io.gs2.money2;

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
import io.gs2.money2.request.*;
import io.gs2.money2.result.*;
import io.gs2.money2.model.*;

public class Gs2Money2RestClient extends AbstractGs2Client<Gs2Money2RestClient> {

	public Gs2Money2RestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("currencyUsagePriority", request.getCurrencyUsagePriority());
                    put("description", request.getDescription());
                    put("sharedFreeCurrency", request.getSharedFreeCurrency());
                    put("platformSetting", request.getPlatformSetting() != null ? request.getPlatformSetting().toJson() : null);
                    put("depositBalanceScript", request.getDepositBalanceScript() != null ? request.getDepositBalanceScript().toJson() : null);
                    put("withdrawBalanceScript", request.getWithdrawBalanceScript() != null ? request.getWithdrawBalanceScript().toJson() : null);
                    put("subscribeScript", request.getSubscribeScript());
                    put("renewScript", request.getRenewScript());
                    put("unsubscribeScript", request.getUnsubscribeScript());
                    put("takeOverScript", request.getTakeOverScript() != null ? request.getTakeOverScript().toJson() : null);
                    put("changeSubscriptionStatusNotification", request.getChangeSubscriptionStatusNotification() != null ? request.getChangeSubscriptionStatusNotification().toJson() : null);
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("currencyUsagePriority", request.getCurrencyUsagePriority());
                    put("description", request.getDescription());
                    put("platformSetting", request.getPlatformSetting() != null ? request.getPlatformSetting().toJson() : null);
                    put("depositBalanceScript", request.getDepositBalanceScript() != null ? request.getDepositBalanceScript().toJson() : null);
                    put("withdrawBalanceScript", request.getWithdrawBalanceScript() != null ? request.getWithdrawBalanceScript().toJson() : null);
                    put("subscribeScript", request.getSubscribeScript());
                    put("renewScript", request.getRenewScript());
                    put("unsubscribeScript", request.getUnsubscribeScript());
                    put("takeOverScript", request.getTakeOverScript() != null ? request.getTakeOverScript().toJson() : null);
                    put("changeSubscriptionStatusNotification", request.getChangeSubscriptionStatusNotification() != null ? request.getChangeSubscriptionStatusNotification().toJson() : null);
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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
                .replace("{service}", "money2")
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

    class DescribeWalletsTask extends Gs2RestSessionTask<DescribeWalletsResult> {
        private DescribeWalletsRequest request;

        public DescribeWalletsTask(
            DescribeWalletsRequest request,
            AsyncAction<AsyncResult<DescribeWalletsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeWalletsResult parse(JsonNode data) {
            return DescribeWalletsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/wallet";

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

    public void describeWalletsAsync(
            DescribeWalletsRequest request,
            AsyncAction<AsyncResult<DescribeWalletsResult>> callback
    ) {
        DescribeWalletsTask task = new DescribeWalletsTask(request, callback);
        session.execute(task);
    }

    public DescribeWalletsResult describeWallets(
            DescribeWalletsRequest request
    ) {
        final AsyncResult<DescribeWalletsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWalletsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeWalletsByUserIdTask extends Gs2RestSessionTask<DescribeWalletsByUserIdResult> {
        private DescribeWalletsByUserIdRequest request;

        public DescribeWalletsByUserIdTask(
            DescribeWalletsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWalletsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeWalletsByUserIdResult parse(JsonNode data) {
            return DescribeWalletsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet";

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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeWalletsByUserIdAsync(
            DescribeWalletsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWalletsByUserIdResult>> callback
    ) {
        DescribeWalletsByUserIdTask task = new DescribeWalletsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeWalletsByUserIdResult describeWalletsByUserId(
            DescribeWalletsByUserIdRequest request
    ) {
        final AsyncResult<DescribeWalletsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWalletsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetWalletTask extends Gs2RestSessionTask<GetWalletResult> {
        private GetWalletRequest request;

        public GetWalletTask(
            GetWalletRequest request,
            AsyncAction<AsyncResult<GetWalletResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetWalletResult parse(JsonNode data) {
            return GetWalletResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/wallet/{slot}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{slot}", this.request.getSlot() == null  ? "null" : String.valueOf(this.request.getSlot()));

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

    public void getWalletAsync(
            GetWalletRequest request,
            AsyncAction<AsyncResult<GetWalletResult>> callback
    ) {
        GetWalletTask task = new GetWalletTask(request, callback);
        session.execute(task);
    }

    public GetWalletResult getWallet(
            GetWalletRequest request
    ) {
        final AsyncResult<GetWalletResult>[] resultAsyncResult = new AsyncResult[]{null};
        getWalletAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetWalletByUserIdTask extends Gs2RestSessionTask<GetWalletByUserIdResult> {
        private GetWalletByUserIdRequest request;

        public GetWalletByUserIdTask(
            GetWalletByUserIdRequest request,
            AsyncAction<AsyncResult<GetWalletByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetWalletByUserIdResult parse(JsonNode data) {
            return GetWalletByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null  ? "null" : String.valueOf(this.request.getSlot()));

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

    public void getWalletByUserIdAsync(
            GetWalletByUserIdRequest request,
            AsyncAction<AsyncResult<GetWalletByUserIdResult>> callback
    ) {
        GetWalletByUserIdTask task = new GetWalletByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetWalletByUserIdResult getWalletByUserId(
            GetWalletByUserIdRequest request
    ) {
        final AsyncResult<GetWalletByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getWalletByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DepositByUserIdTask extends Gs2RestSessionTask<DepositByUserIdResult> {
        private DepositByUserIdRequest request;

        public DepositByUserIdTask(
            DepositByUserIdRequest request,
            AsyncAction<AsyncResult<DepositByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DepositByUserIdResult parse(JsonNode data) {
            return DepositByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}/deposit";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null  ? "null" : String.valueOf(this.request.getSlot()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("depositTransactions", request.getDepositTransactions() == null ? null :
                        request.getDepositTransactions().stream().map(item -> {
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

    public void depositByUserIdAsync(
            DepositByUserIdRequest request,
            AsyncAction<AsyncResult<DepositByUserIdResult>> callback
    ) {
        DepositByUserIdTask task = new DepositByUserIdTask(request, callback);
        session.execute(task);
    }

    public DepositByUserIdResult depositByUserId(
            DepositByUserIdRequest request
    ) {
        final AsyncResult<DepositByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        depositByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class WithdrawTask extends Gs2RestSessionTask<WithdrawResult> {
        private WithdrawRequest request;

        public WithdrawTask(
            WithdrawRequest request,
            AsyncAction<AsyncResult<WithdrawResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WithdrawResult parse(JsonNode data) {
            return WithdrawResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/wallet/{slot}/withdraw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{slot}", this.request.getSlot() == null  ? "null" : String.valueOf(this.request.getSlot()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("withdrawCount", request.getWithdrawCount());
                    put("paidOnly", request.getPaidOnly());
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

    public void withdrawAsync(
            WithdrawRequest request,
            AsyncAction<AsyncResult<WithdrawResult>> callback
    ) {
        WithdrawTask task = new WithdrawTask(request, callback);
        session.execute(task);
    }

    public WithdrawResult withdraw(
            WithdrawRequest request
    ) {
        final AsyncResult<WithdrawResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class WithdrawByUserIdTask extends Gs2RestSessionTask<WithdrawByUserIdResult> {
        private WithdrawByUserIdRequest request;

        public WithdrawByUserIdTask(
            WithdrawByUserIdRequest request,
            AsyncAction<AsyncResult<WithdrawByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WithdrawByUserIdResult parse(JsonNode data) {
            return WithdrawByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}/withdraw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null  ? "null" : String.valueOf(this.request.getSlot()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("withdrawCount", request.getWithdrawCount());
                    put("paidOnly", request.getPaidOnly());
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

    public void withdrawByUserIdAsync(
            WithdrawByUserIdRequest request,
            AsyncAction<AsyncResult<WithdrawByUserIdResult>> callback
    ) {
        WithdrawByUserIdTask task = new WithdrawByUserIdTask(request, callback);
        session.execute(task);
    }

    public WithdrawByUserIdResult withdrawByUserId(
            WithdrawByUserIdRequest request
    ) {
        final AsyncResult<WithdrawByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DepositByStampSheetTask extends Gs2RestSessionTask<DepositByStampSheetResult> {
        private DepositByStampSheetRequest request;

        public DepositByStampSheetTask(
            DepositByStampSheetRequest request,
            AsyncAction<AsyncResult<DepositByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DepositByStampSheetResult parse(JsonNode data) {
            return DepositByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/deposit";

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

    public void depositByStampSheetAsync(
            DepositByStampSheetRequest request,
            AsyncAction<AsyncResult<DepositByStampSheetResult>> callback
    ) {
        DepositByStampSheetTask task = new DepositByStampSheetTask(request, callback);
        session.execute(task);
    }

    public DepositByStampSheetResult depositByStampSheet(
            DepositByStampSheetRequest request
    ) {
        final AsyncResult<DepositByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        depositByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class WithdrawByStampTaskTask extends Gs2RestSessionTask<WithdrawByStampTaskResult> {
        private WithdrawByStampTaskRequest request;

        public WithdrawByStampTaskTask(
            WithdrawByStampTaskRequest request,
            AsyncAction<AsyncResult<WithdrawByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WithdrawByStampTaskResult parse(JsonNode data) {
            return WithdrawByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/withdraw";

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

    public void withdrawByStampTaskAsync(
            WithdrawByStampTaskRequest request,
            AsyncAction<AsyncResult<WithdrawByStampTaskResult>> callback
    ) {
        WithdrawByStampTaskTask task = new WithdrawByStampTaskTask(request, callback);
        session.execute(task);
    }

    public WithdrawByStampTaskResult withdrawByStampTask(
            WithdrawByStampTaskRequest request
    ) {
        final AsyncResult<WithdrawByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeEventsByUserIdTask extends Gs2RestSessionTask<DescribeEventsByUserIdResult> {
        private DescribeEventsByUserIdRequest request;

        public DescribeEventsByUserIdTask(
            DescribeEventsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeEventsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeEventsByUserIdResult parse(JsonNode data) {
            return DescribeEventsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/event/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
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

    public void describeEventsByUserIdAsync(
            DescribeEventsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeEventsByUserIdResult>> callback
    ) {
        DescribeEventsByUserIdTask task = new DescribeEventsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeEventsByUserIdResult describeEventsByUserId(
            DescribeEventsByUserIdRequest request
    ) {
        final AsyncResult<DescribeEventsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEventsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEventByTransactionIdTask extends Gs2RestSessionTask<GetEventByTransactionIdResult> {
        private GetEventByTransactionIdRequest request;

        public GetEventByTransactionIdTask(
            GetEventByTransactionIdRequest request,
            AsyncAction<AsyncResult<GetEventByTransactionIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetEventByTransactionIdResult parse(JsonNode data) {
            return GetEventByTransactionIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/event/{transactionId}";

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

            builder
                .build()
                .send();
        }
    }

    public void getEventByTransactionIdAsync(
            GetEventByTransactionIdRequest request,
            AsyncAction<AsyncResult<GetEventByTransactionIdResult>> callback
    ) {
        GetEventByTransactionIdTask task = new GetEventByTransactionIdTask(request, callback);
        session.execute(task);
    }

    public GetEventByTransactionIdResult getEventByTransactionId(
            GetEventByTransactionIdRequest request
    ) {
        final AsyncResult<GetEventByTransactionIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEventByTransactionIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyReceiptTask extends Gs2RestSessionTask<VerifyReceiptResult> {
        private VerifyReceiptRequest request;

        public VerifyReceiptTask(
            VerifyReceiptRequest request,
            AsyncAction<AsyncResult<VerifyReceiptResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyReceiptResult parse(JsonNode data) {
            return VerifyReceiptResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/content/{contentName}/receipt/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("receipt", request.getReceipt() != null ? request.getReceipt().toJson() : null);
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

    public void verifyReceiptAsync(
            VerifyReceiptRequest request,
            AsyncAction<AsyncResult<VerifyReceiptResult>> callback
    ) {
        VerifyReceiptTask task = new VerifyReceiptTask(request, callback);
        session.execute(task);
    }

    public VerifyReceiptResult verifyReceipt(
            VerifyReceiptRequest request
    ) {
        final AsyncResult<VerifyReceiptResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyReceiptAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyReceiptByUserIdTask extends Gs2RestSessionTask<VerifyReceiptByUserIdResult> {
        private VerifyReceiptByUserIdRequest request;

        public VerifyReceiptByUserIdTask(
            VerifyReceiptByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyReceiptByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyReceiptByUserIdResult parse(JsonNode data) {
            return VerifyReceiptByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/content/{contentName}/receipt/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("receipt", request.getReceipt() != null ? request.getReceipt().toJson() : null);
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

    public void verifyReceiptByUserIdAsync(
            VerifyReceiptByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyReceiptByUserIdResult>> callback
    ) {
        VerifyReceiptByUserIdTask task = new VerifyReceiptByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyReceiptByUserIdResult verifyReceiptByUserId(
            VerifyReceiptByUserIdRequest request
    ) {
        final AsyncResult<VerifyReceiptByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyReceiptByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyReceiptByStampTaskTask extends Gs2RestSessionTask<VerifyReceiptByStampTaskResult> {
        private VerifyReceiptByStampTaskRequest request;

        public VerifyReceiptByStampTaskTask(
            VerifyReceiptByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyReceiptByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyReceiptByStampTaskResult parse(JsonNode data) {
            return VerifyReceiptByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/receipt/verify";

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

    public void verifyReceiptByStampTaskAsync(
            VerifyReceiptByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyReceiptByStampTaskResult>> callback
    ) {
        VerifyReceiptByStampTaskTask task = new VerifyReceiptByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyReceiptByStampTaskResult verifyReceiptByStampTask(
            VerifyReceiptByStampTaskRequest request
    ) {
        final AsyncResult<VerifyReceiptByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyReceiptByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscriptionStatusesTask extends Gs2RestSessionTask<DescribeSubscriptionStatusesResult> {
        private DescribeSubscriptionStatusesRequest request;

        public DescribeSubscriptionStatusesTask(
            DescribeSubscriptionStatusesRequest request,
            AsyncAction<AsyncResult<DescribeSubscriptionStatusesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscriptionStatusesResult parse(JsonNode data) {
            return DescribeSubscriptionStatusesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscription";

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

    public void describeSubscriptionStatusesAsync(
            DescribeSubscriptionStatusesRequest request,
            AsyncAction<AsyncResult<DescribeSubscriptionStatusesResult>> callback
    ) {
        DescribeSubscriptionStatusesTask task = new DescribeSubscriptionStatusesTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscriptionStatusesResult describeSubscriptionStatuses(
            DescribeSubscriptionStatusesRequest request
    ) {
        final AsyncResult<DescribeSubscriptionStatusesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscriptionStatusesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscriptionStatusesByUserIdTask extends Gs2RestSessionTask<DescribeSubscriptionStatusesByUserIdResult> {
        private DescribeSubscriptionStatusesByUserIdRequest request;

        public DescribeSubscriptionStatusesByUserIdTask(
            DescribeSubscriptionStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscriptionStatusesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscriptionStatusesByUserIdResult parse(JsonNode data) {
            return DescribeSubscriptionStatusesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscription";

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

    public void describeSubscriptionStatusesByUserIdAsync(
            DescribeSubscriptionStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscriptionStatusesByUserIdResult>> callback
    ) {
        DescribeSubscriptionStatusesByUserIdTask task = new DescribeSubscriptionStatusesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscriptionStatusesByUserIdResult describeSubscriptionStatusesByUserId(
            DescribeSubscriptionStatusesByUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscriptionStatusesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscriptionStatusesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscriptionStatusTask extends Gs2RestSessionTask<GetSubscriptionStatusResult> {
        private GetSubscriptionStatusRequest request;

        public GetSubscriptionStatusTask(
            GetSubscriptionStatusRequest request,
            AsyncAction<AsyncResult<GetSubscriptionStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscriptionStatusResult parse(JsonNode data) {
            return GetSubscriptionStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscription/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void getSubscriptionStatusAsync(
            GetSubscriptionStatusRequest request,
            AsyncAction<AsyncResult<GetSubscriptionStatusResult>> callback
    ) {
        GetSubscriptionStatusTask task = new GetSubscriptionStatusTask(request, callback);
        session.execute(task);
    }

    public GetSubscriptionStatusResult getSubscriptionStatus(
            GetSubscriptionStatusRequest request
    ) {
        final AsyncResult<GetSubscriptionStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscriptionStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscriptionStatusByUserIdTask extends Gs2RestSessionTask<GetSubscriptionStatusByUserIdResult> {
        private GetSubscriptionStatusByUserIdRequest request;

        public GetSubscriptionStatusByUserIdTask(
            GetSubscriptionStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscriptionStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscriptionStatusByUserIdResult parse(JsonNode data) {
            return GetSubscriptionStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscription/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void getSubscriptionStatusByUserIdAsync(
            GetSubscriptionStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscriptionStatusByUserIdResult>> callback
    ) {
        GetSubscriptionStatusByUserIdTask task = new GetSubscriptionStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSubscriptionStatusByUserIdResult getSubscriptionStatusByUserId(
            GetSubscriptionStatusByUserIdRequest request
    ) {
        final AsyncResult<GetSubscriptionStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscriptionStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AllocateSubscriptionStatusTask extends Gs2RestSessionTask<AllocateSubscriptionStatusResult> {
        private AllocateSubscriptionStatusRequest request;

        public AllocateSubscriptionStatusTask(
            AllocateSubscriptionStatusRequest request,
            AsyncAction<AsyncResult<AllocateSubscriptionStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AllocateSubscriptionStatusResult parse(JsonNode data) {
            return AllocateSubscriptionStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/allocate/subscription";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("receipt", request.getReceipt());
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

    public void allocateSubscriptionStatusAsync(
            AllocateSubscriptionStatusRequest request,
            AsyncAction<AsyncResult<AllocateSubscriptionStatusResult>> callback
    ) {
        AllocateSubscriptionStatusTask task = new AllocateSubscriptionStatusTask(request, callback);
        session.execute(task);
    }

    public AllocateSubscriptionStatusResult allocateSubscriptionStatus(
            AllocateSubscriptionStatusRequest request
    ) {
        final AsyncResult<AllocateSubscriptionStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        allocateSubscriptionStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AllocateSubscriptionStatusByUserIdTask extends Gs2RestSessionTask<AllocateSubscriptionStatusByUserIdResult> {
        private AllocateSubscriptionStatusByUserIdRequest request;

        public AllocateSubscriptionStatusByUserIdTask(
            AllocateSubscriptionStatusByUserIdRequest request,
            AsyncAction<AsyncResult<AllocateSubscriptionStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AllocateSubscriptionStatusByUserIdResult parse(JsonNode data) {
            return AllocateSubscriptionStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/allocate/subscription";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("receipt", request.getReceipt());
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

    public void allocateSubscriptionStatusByUserIdAsync(
            AllocateSubscriptionStatusByUserIdRequest request,
            AsyncAction<AsyncResult<AllocateSubscriptionStatusByUserIdResult>> callback
    ) {
        AllocateSubscriptionStatusByUserIdTask task = new AllocateSubscriptionStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public AllocateSubscriptionStatusByUserIdResult allocateSubscriptionStatusByUserId(
            AllocateSubscriptionStatusByUserIdRequest request
    ) {
        final AsyncResult<AllocateSubscriptionStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        allocateSubscriptionStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class TakeoverSubscriptionStatusTask extends Gs2RestSessionTask<TakeoverSubscriptionStatusResult> {
        private TakeoverSubscriptionStatusRequest request;

        public TakeoverSubscriptionStatusTask(
            TakeoverSubscriptionStatusRequest request,
            AsyncAction<AsyncResult<TakeoverSubscriptionStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public TakeoverSubscriptionStatusResult parse(JsonNode data) {
            return TakeoverSubscriptionStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/takeover/subscription";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("receipt", request.getReceipt());
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

    public void takeoverSubscriptionStatusAsync(
            TakeoverSubscriptionStatusRequest request,
            AsyncAction<AsyncResult<TakeoverSubscriptionStatusResult>> callback
    ) {
        TakeoverSubscriptionStatusTask task = new TakeoverSubscriptionStatusTask(request, callback);
        session.execute(task);
    }

    public TakeoverSubscriptionStatusResult takeoverSubscriptionStatus(
            TakeoverSubscriptionStatusRequest request
    ) {
        final AsyncResult<TakeoverSubscriptionStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        takeoverSubscriptionStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class TakeoverSubscriptionStatusByUserIdTask extends Gs2RestSessionTask<TakeoverSubscriptionStatusByUserIdResult> {
        private TakeoverSubscriptionStatusByUserIdRequest request;

        public TakeoverSubscriptionStatusByUserIdTask(
            TakeoverSubscriptionStatusByUserIdRequest request,
            AsyncAction<AsyncResult<TakeoverSubscriptionStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public TakeoverSubscriptionStatusByUserIdResult parse(JsonNode data) {
            return TakeoverSubscriptionStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/takeover/subscription";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("receipt", request.getReceipt());
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

    public void takeoverSubscriptionStatusByUserIdAsync(
            TakeoverSubscriptionStatusByUserIdRequest request,
            AsyncAction<AsyncResult<TakeoverSubscriptionStatusByUserIdResult>> callback
    ) {
        TakeoverSubscriptionStatusByUserIdTask task = new TakeoverSubscriptionStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public TakeoverSubscriptionStatusByUserIdResult takeoverSubscriptionStatusByUserId(
            TakeoverSubscriptionStatusByUserIdRequest request
    ) {
        final AsyncResult<TakeoverSubscriptionStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        takeoverSubscriptionStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRefundHistoriesByUserIdTask extends Gs2RestSessionTask<DescribeRefundHistoriesByUserIdResult> {
        private DescribeRefundHistoriesByUserIdRequest request;

        public DescribeRefundHistoriesByUserIdTask(
            DescribeRefundHistoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRefundHistoriesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRefundHistoriesByUserIdResult parse(JsonNode data) {
            return DescribeRefundHistoriesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/refund/user/{userId}";

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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeRefundHistoriesByUserIdAsync(
            DescribeRefundHistoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRefundHistoriesByUserIdResult>> callback
    ) {
        DescribeRefundHistoriesByUserIdTask task = new DescribeRefundHistoriesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeRefundHistoriesByUserIdResult describeRefundHistoriesByUserId(
            DescribeRefundHistoriesByUserIdRequest request
    ) {
        final AsyncResult<DescribeRefundHistoriesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRefundHistoriesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRefundHistoriesByDateTask extends Gs2RestSessionTask<DescribeRefundHistoriesByDateResult> {
        private DescribeRefundHistoriesByDateRequest request;

        public DescribeRefundHistoriesByDateTask(
            DescribeRefundHistoriesByDateRequest request,
            AsyncAction<AsyncResult<DescribeRefundHistoriesByDateResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRefundHistoriesByDateResult parse(JsonNode data) {
            return DescribeRefundHistoriesByDateResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/refund/date/{year}/{month}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null  ? "null" : String.valueOf(this.request.getMonth()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getDay() != null) {
                queryStrings.add("day=" + String.valueOf(this.request.getDay()));
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

    public void describeRefundHistoriesByDateAsync(
            DescribeRefundHistoriesByDateRequest request,
            AsyncAction<AsyncResult<DescribeRefundHistoriesByDateResult>> callback
    ) {
        DescribeRefundHistoriesByDateTask task = new DescribeRefundHistoriesByDateTask(request, callback);
        session.execute(task);
    }

    public DescribeRefundHistoriesByDateResult describeRefundHistoriesByDate(
            DescribeRefundHistoriesByDateRequest request
    ) {
        final AsyncResult<DescribeRefundHistoriesByDateResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRefundHistoriesByDateAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRefundHistoryTask extends Gs2RestSessionTask<GetRefundHistoryResult> {
        private GetRefundHistoryRequest request;

        public GetRefundHistoryTask(
            GetRefundHistoryRequest request,
            AsyncAction<AsyncResult<GetRefundHistoryResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRefundHistoryResult parse(JsonNode data) {
            return GetRefundHistoryResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/refund/{transactionId}";

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

            builder
                .build()
                .send();
        }
    }

    public void getRefundHistoryAsync(
            GetRefundHistoryRequest request,
            AsyncAction<AsyncResult<GetRefundHistoryResult>> callback
    ) {
        GetRefundHistoryTask task = new GetRefundHistoryTask(request, callback);
        session.execute(task);
    }

    public GetRefundHistoryResult getRefundHistory(
            GetRefundHistoryRequest request
    ) {
        final AsyncResult<GetRefundHistoryResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRefundHistoryAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStoreContentModelsTask extends Gs2RestSessionTask<DescribeStoreContentModelsResult> {
        private DescribeStoreContentModelsRequest request;

        public DescribeStoreContentModelsTask(
            DescribeStoreContentModelsRequest request,
            AsyncAction<AsyncResult<DescribeStoreContentModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStoreContentModelsResult parse(JsonNode data) {
            return DescribeStoreContentModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/content";

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

    public void describeStoreContentModelsAsync(
            DescribeStoreContentModelsRequest request,
            AsyncAction<AsyncResult<DescribeStoreContentModelsResult>> callback
    ) {
        DescribeStoreContentModelsTask task = new DescribeStoreContentModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeStoreContentModelsResult describeStoreContentModels(
            DescribeStoreContentModelsRequest request
    ) {
        final AsyncResult<DescribeStoreContentModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStoreContentModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStoreContentModelTask extends Gs2RestSessionTask<GetStoreContentModelResult> {
        private GetStoreContentModelRequest request;

        public GetStoreContentModelTask(
            GetStoreContentModelRequest request,
            AsyncAction<AsyncResult<GetStoreContentModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStoreContentModelResult parse(JsonNode data) {
            return GetStoreContentModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/content/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void getStoreContentModelAsync(
            GetStoreContentModelRequest request,
            AsyncAction<AsyncResult<GetStoreContentModelResult>> callback
    ) {
        GetStoreContentModelTask task = new GetStoreContentModelTask(request, callback);
        session.execute(task);
    }

    public GetStoreContentModelResult getStoreContentModel(
            GetStoreContentModelRequest request
    ) {
        final AsyncResult<GetStoreContentModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStoreContentModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStoreContentModelMastersTask extends Gs2RestSessionTask<DescribeStoreContentModelMastersResult> {
        private DescribeStoreContentModelMastersRequest request;

        public DescribeStoreContentModelMastersTask(
            DescribeStoreContentModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStoreContentModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStoreContentModelMastersResult parse(JsonNode data) {
            return DescribeStoreContentModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
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

    public void describeStoreContentModelMastersAsync(
            DescribeStoreContentModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStoreContentModelMastersResult>> callback
    ) {
        DescribeStoreContentModelMastersTask task = new DescribeStoreContentModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeStoreContentModelMastersResult describeStoreContentModelMasters(
            DescribeStoreContentModelMastersRequest request
    ) {
        final AsyncResult<DescribeStoreContentModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStoreContentModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateStoreContentModelMasterTask extends Gs2RestSessionTask<CreateStoreContentModelMasterResult> {
        private CreateStoreContentModelMasterRequest request;

        public CreateStoreContentModelMasterTask(
            CreateStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStoreContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateStoreContentModelMasterResult parse(JsonNode data) {
            return CreateStoreContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("appleAppStore", request.getAppleAppStore() != null ? request.getAppleAppStore().toJson() : null);
                    put("googlePlay", request.getGooglePlay() != null ? request.getGooglePlay().toJson() : null);
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

    public void createStoreContentModelMasterAsync(
            CreateStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStoreContentModelMasterResult>> callback
    ) {
        CreateStoreContentModelMasterTask task = new CreateStoreContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateStoreContentModelMasterResult createStoreContentModelMaster(
            CreateStoreContentModelMasterRequest request
    ) {
        final AsyncResult<CreateStoreContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createStoreContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStoreContentModelMasterTask extends Gs2RestSessionTask<GetStoreContentModelMasterResult> {
        private GetStoreContentModelMasterRequest request;

        public GetStoreContentModelMasterTask(
            GetStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<GetStoreContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStoreContentModelMasterResult parse(JsonNode data) {
            return GetStoreContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void getStoreContentModelMasterAsync(
            GetStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<GetStoreContentModelMasterResult>> callback
    ) {
        GetStoreContentModelMasterTask task = new GetStoreContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetStoreContentModelMasterResult getStoreContentModelMaster(
            GetStoreContentModelMasterRequest request
    ) {
        final AsyncResult<GetStoreContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStoreContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateStoreContentModelMasterTask extends Gs2RestSessionTask<UpdateStoreContentModelMasterResult> {
        private UpdateStoreContentModelMasterRequest request;

        public UpdateStoreContentModelMasterTask(
            UpdateStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStoreContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateStoreContentModelMasterResult parse(JsonNode data) {
            return UpdateStoreContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("appleAppStore", request.getAppleAppStore() != null ? request.getAppleAppStore().toJson() : null);
                    put("googlePlay", request.getGooglePlay() != null ? request.getGooglePlay().toJson() : null);
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

    public void updateStoreContentModelMasterAsync(
            UpdateStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStoreContentModelMasterResult>> callback
    ) {
        UpdateStoreContentModelMasterTask task = new UpdateStoreContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateStoreContentModelMasterResult updateStoreContentModelMaster(
            UpdateStoreContentModelMasterRequest request
    ) {
        final AsyncResult<UpdateStoreContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStoreContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStoreContentModelMasterTask extends Gs2RestSessionTask<DeleteStoreContentModelMasterResult> {
        private DeleteStoreContentModelMasterRequest request;

        public DeleteStoreContentModelMasterTask(
            DeleteStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStoreContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStoreContentModelMasterResult parse(JsonNode data) {
            return DeleteStoreContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void deleteStoreContentModelMasterAsync(
            DeleteStoreContentModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStoreContentModelMasterResult>> callback
    ) {
        DeleteStoreContentModelMasterTask task = new DeleteStoreContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteStoreContentModelMasterResult deleteStoreContentModelMaster(
            DeleteStoreContentModelMasterRequest request
    ) {
        final AsyncResult<DeleteStoreContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStoreContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStoreSubscriptionContentModelsTask extends Gs2RestSessionTask<DescribeStoreSubscriptionContentModelsResult> {
        private DescribeStoreSubscriptionContentModelsRequest request;

        public DescribeStoreSubscriptionContentModelsTask(
            DescribeStoreSubscriptionContentModelsRequest request,
            AsyncAction<AsyncResult<DescribeStoreSubscriptionContentModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStoreSubscriptionContentModelsResult parse(JsonNode data) {
            return DescribeStoreSubscriptionContentModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/subscription/content";

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

    public void describeStoreSubscriptionContentModelsAsync(
            DescribeStoreSubscriptionContentModelsRequest request,
            AsyncAction<AsyncResult<DescribeStoreSubscriptionContentModelsResult>> callback
    ) {
        DescribeStoreSubscriptionContentModelsTask task = new DescribeStoreSubscriptionContentModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeStoreSubscriptionContentModelsResult describeStoreSubscriptionContentModels(
            DescribeStoreSubscriptionContentModelsRequest request
    ) {
        final AsyncResult<DescribeStoreSubscriptionContentModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStoreSubscriptionContentModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStoreSubscriptionContentModelTask extends Gs2RestSessionTask<GetStoreSubscriptionContentModelResult> {
        private GetStoreSubscriptionContentModelRequest request;

        public GetStoreSubscriptionContentModelTask(
            GetStoreSubscriptionContentModelRequest request,
            AsyncAction<AsyncResult<GetStoreSubscriptionContentModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStoreSubscriptionContentModelResult parse(JsonNode data) {
            return GetStoreSubscriptionContentModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/subscription/content/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void getStoreSubscriptionContentModelAsync(
            GetStoreSubscriptionContentModelRequest request,
            AsyncAction<AsyncResult<GetStoreSubscriptionContentModelResult>> callback
    ) {
        GetStoreSubscriptionContentModelTask task = new GetStoreSubscriptionContentModelTask(request, callback);
        session.execute(task);
    }

    public GetStoreSubscriptionContentModelResult getStoreSubscriptionContentModel(
            GetStoreSubscriptionContentModelRequest request
    ) {
        final AsyncResult<GetStoreSubscriptionContentModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStoreSubscriptionContentModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStoreSubscriptionContentModelMastersTask extends Gs2RestSessionTask<DescribeStoreSubscriptionContentModelMastersResult> {
        private DescribeStoreSubscriptionContentModelMastersRequest request;

        public DescribeStoreSubscriptionContentModelMastersTask(
            DescribeStoreSubscriptionContentModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStoreSubscriptionContentModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStoreSubscriptionContentModelMastersResult parse(JsonNode data) {
            return DescribeStoreSubscriptionContentModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/subscription";

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

    public void describeStoreSubscriptionContentModelMastersAsync(
            DescribeStoreSubscriptionContentModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStoreSubscriptionContentModelMastersResult>> callback
    ) {
        DescribeStoreSubscriptionContentModelMastersTask task = new DescribeStoreSubscriptionContentModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeStoreSubscriptionContentModelMastersResult describeStoreSubscriptionContentModelMasters(
            DescribeStoreSubscriptionContentModelMastersRequest request
    ) {
        final AsyncResult<DescribeStoreSubscriptionContentModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStoreSubscriptionContentModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateStoreSubscriptionContentModelMasterTask extends Gs2RestSessionTask<CreateStoreSubscriptionContentModelMasterResult> {
        private CreateStoreSubscriptionContentModelMasterRequest request;

        public CreateStoreSubscriptionContentModelMasterTask(
            CreateStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStoreSubscriptionContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateStoreSubscriptionContentModelMasterResult parse(JsonNode data) {
            return CreateStoreSubscriptionContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/subscription";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("scheduleNamespaceId", request.getScheduleNamespaceId());
                    put("triggerName", request.getTriggerName());
                    put("triggerExtendMode", request.getTriggerExtendMode());
                    put("rollupHour", request.getRollupHour());
                    put("reallocateSpanDays", request.getReallocateSpanDays());
                    put("appleAppStore", request.getAppleAppStore() != null ? request.getAppleAppStore().toJson() : null);
                    put("googlePlay", request.getGooglePlay() != null ? request.getGooglePlay().toJson() : null);
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

    public void createStoreSubscriptionContentModelMasterAsync(
            CreateStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStoreSubscriptionContentModelMasterResult>> callback
    ) {
        CreateStoreSubscriptionContentModelMasterTask task = new CreateStoreSubscriptionContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateStoreSubscriptionContentModelMasterResult createStoreSubscriptionContentModelMaster(
            CreateStoreSubscriptionContentModelMasterRequest request
    ) {
        final AsyncResult<CreateStoreSubscriptionContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createStoreSubscriptionContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStoreSubscriptionContentModelMasterTask extends Gs2RestSessionTask<GetStoreSubscriptionContentModelMasterResult> {
        private GetStoreSubscriptionContentModelMasterRequest request;

        public GetStoreSubscriptionContentModelMasterTask(
            GetStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<GetStoreSubscriptionContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStoreSubscriptionContentModelMasterResult parse(JsonNode data) {
            return GetStoreSubscriptionContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/subscription/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void getStoreSubscriptionContentModelMasterAsync(
            GetStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<GetStoreSubscriptionContentModelMasterResult>> callback
    ) {
        GetStoreSubscriptionContentModelMasterTask task = new GetStoreSubscriptionContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetStoreSubscriptionContentModelMasterResult getStoreSubscriptionContentModelMaster(
            GetStoreSubscriptionContentModelMasterRequest request
    ) {
        final AsyncResult<GetStoreSubscriptionContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStoreSubscriptionContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateStoreSubscriptionContentModelMasterTask extends Gs2RestSessionTask<UpdateStoreSubscriptionContentModelMasterResult> {
        private UpdateStoreSubscriptionContentModelMasterRequest request;

        public UpdateStoreSubscriptionContentModelMasterTask(
            UpdateStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStoreSubscriptionContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateStoreSubscriptionContentModelMasterResult parse(JsonNode data) {
            return UpdateStoreSubscriptionContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/subscription/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("scheduleNamespaceId", request.getScheduleNamespaceId());
                    put("triggerName", request.getTriggerName());
                    put("triggerExtendMode", request.getTriggerExtendMode());
                    put("rollupHour", request.getRollupHour());
                    put("reallocateSpanDays", request.getReallocateSpanDays());
                    put("appleAppStore", request.getAppleAppStore() != null ? request.getAppleAppStore().toJson() : null);
                    put("googlePlay", request.getGooglePlay() != null ? request.getGooglePlay().toJson() : null);
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

    public void updateStoreSubscriptionContentModelMasterAsync(
            UpdateStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStoreSubscriptionContentModelMasterResult>> callback
    ) {
        UpdateStoreSubscriptionContentModelMasterTask task = new UpdateStoreSubscriptionContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateStoreSubscriptionContentModelMasterResult updateStoreSubscriptionContentModelMaster(
            UpdateStoreSubscriptionContentModelMasterRequest request
    ) {
        final AsyncResult<UpdateStoreSubscriptionContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStoreSubscriptionContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStoreSubscriptionContentModelMasterTask extends Gs2RestSessionTask<DeleteStoreSubscriptionContentModelMasterResult> {
        private DeleteStoreSubscriptionContentModelMasterRequest request;

        public DeleteStoreSubscriptionContentModelMasterTask(
            DeleteStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStoreSubscriptionContentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteStoreSubscriptionContentModelMasterResult parse(JsonNode data) {
            return DeleteStoreSubscriptionContentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/subscription/{contentName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{contentName}", this.request.getContentName() == null || this.request.getContentName().length() == 0 ? "null" : String.valueOf(this.request.getContentName()));

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

    public void deleteStoreSubscriptionContentModelMasterAsync(
            DeleteStoreSubscriptionContentModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStoreSubscriptionContentModelMasterResult>> callback
    ) {
        DeleteStoreSubscriptionContentModelMasterTask task = new DeleteStoreSubscriptionContentModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteStoreSubscriptionContentModelMasterResult deleteStoreSubscriptionContentModelMaster(
            DeleteStoreSubscriptionContentModelMasterRequest request
    ) {
        final AsyncResult<DeleteStoreSubscriptionContentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStoreSubscriptionContentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "money2")
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

    class GetCurrentModelMasterTask extends Gs2RestSessionTask<GetCurrentModelMasterResult> {
        private GetCurrentModelMasterRequest request;

        public GetCurrentModelMasterTask(
            GetCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentModelMasterResult parse(JsonNode data) {
            return GetCurrentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
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

    public void getCurrentModelMasterAsync(
            GetCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentModelMasterResult>> callback
    ) {
        GetCurrentModelMasterTask task = new GetCurrentModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentModelMasterResult getCurrentModelMaster(
            GetCurrentModelMasterRequest request
    ) {
        final AsyncResult<GetCurrentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PreUpdateCurrentModelMasterTask extends Gs2RestSessionTask<PreUpdateCurrentModelMasterResult> {
        private PreUpdateCurrentModelMasterRequest request;

        public PreUpdateCurrentModelMasterTask(
            PreUpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PreUpdateCurrentModelMasterResult parse(JsonNode data) {
            return PreUpdateCurrentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
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

    public void preUpdateCurrentModelMasterAsync(
            PreUpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentModelMasterResult>> callback
    ) {
        PreUpdateCurrentModelMasterTask task = new PreUpdateCurrentModelMasterTask(request, callback);
        session.execute(task);
    }

    public PreUpdateCurrentModelMasterResult preUpdateCurrentModelMaster(
            PreUpdateCurrentModelMasterRequest request
    ) {
        final AsyncResult<PreUpdateCurrentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        preUpdateCurrentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentModelMasterTask extends Gs2RestSessionTask<UpdateCurrentModelMasterResult> {
        private UpdateCurrentModelMasterRequest request;

        public UpdateCurrentModelMasterTask(
            UpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentModelMasterResult parse(JsonNode data) {
            return UpdateCurrentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {
            if (request.getSettings() != null) {
                AtomicReference<AsyncResult<PreUpdateCurrentModelMasterResult>> resultAsyncResult = new AtomicReference<>();
                PreUpdateCurrentModelMasterTask task = new PreUpdateCurrentModelMasterTask(
                        new PreUpdateCurrentModelMasterRequest()
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
                .replace("{service}", "money2")
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

    public void updateCurrentModelMasterAsync(
            UpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterResult>> callback
    ) {
        UpdateCurrentModelMasterTask task = new UpdateCurrentModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentModelMasterResult updateCurrentModelMaster(
            UpdateCurrentModelMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentModelMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentModelMasterFromGitHubResult> {
        private UpdateCurrentModelMasterFromGitHubRequest request;

        public UpdateCurrentModelMasterFromGitHubTask(
            UpdateCurrentModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentModelMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentModelMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
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

    public void updateCurrentModelMasterFromGitHubAsync(
            UpdateCurrentModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentModelMasterFromGitHubTask task = new UpdateCurrentModelMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentModelMasterFromGitHubResult updateCurrentModelMasterFromGitHub(
            UpdateCurrentModelMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentModelMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentModelMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeDailyTransactionHistoriesByCurrencyTask extends Gs2RestSessionTask<DescribeDailyTransactionHistoriesByCurrencyResult> {
        private DescribeDailyTransactionHistoriesByCurrencyRequest request;

        public DescribeDailyTransactionHistoriesByCurrencyTask(
            DescribeDailyTransactionHistoriesByCurrencyRequest request,
            AsyncAction<AsyncResult<DescribeDailyTransactionHistoriesByCurrencyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeDailyTransactionHistoriesByCurrencyResult parse(JsonNode data) {
            return DescribeDailyTransactionHistoriesByCurrencyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/transaction/daily/currency/{currency}/date/{year}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{currency}", this.request.getCurrency() == null || this.request.getCurrency().length() == 0 ? "null" : String.valueOf(this.request.getCurrency()));
            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getMonth() != null) {
                queryStrings.add("month=" + String.valueOf(this.request.getMonth()));
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

    public void describeDailyTransactionHistoriesByCurrencyAsync(
            DescribeDailyTransactionHistoriesByCurrencyRequest request,
            AsyncAction<AsyncResult<DescribeDailyTransactionHistoriesByCurrencyResult>> callback
    ) {
        DescribeDailyTransactionHistoriesByCurrencyTask task = new DescribeDailyTransactionHistoriesByCurrencyTask(request, callback);
        session.execute(task);
    }

    public DescribeDailyTransactionHistoriesByCurrencyResult describeDailyTransactionHistoriesByCurrency(
            DescribeDailyTransactionHistoriesByCurrencyRequest request
    ) {
        final AsyncResult<DescribeDailyTransactionHistoriesByCurrencyResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDailyTransactionHistoriesByCurrencyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeDailyTransactionHistoriesTask extends Gs2RestSessionTask<DescribeDailyTransactionHistoriesResult> {
        private DescribeDailyTransactionHistoriesRequest request;

        public DescribeDailyTransactionHistoriesTask(
            DescribeDailyTransactionHistoriesRequest request,
            AsyncAction<AsyncResult<DescribeDailyTransactionHistoriesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeDailyTransactionHistoriesResult parse(JsonNode data) {
            return DescribeDailyTransactionHistoriesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/transaction/daily/{year}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getMonth() != null) {
                queryStrings.add("month=" + String.valueOf(this.request.getMonth()));
            }
            if (this.request.getDay() != null) {
                queryStrings.add("day=" + String.valueOf(this.request.getDay()));
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

    public void describeDailyTransactionHistoriesAsync(
            DescribeDailyTransactionHistoriesRequest request,
            AsyncAction<AsyncResult<DescribeDailyTransactionHistoriesResult>> callback
    ) {
        DescribeDailyTransactionHistoriesTask task = new DescribeDailyTransactionHistoriesTask(request, callback);
        session.execute(task);
    }

    public DescribeDailyTransactionHistoriesResult describeDailyTransactionHistories(
            DescribeDailyTransactionHistoriesRequest request
    ) {
        final AsyncResult<DescribeDailyTransactionHistoriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDailyTransactionHistoriesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetDailyTransactionHistoryTask extends Gs2RestSessionTask<GetDailyTransactionHistoryResult> {
        private GetDailyTransactionHistoryRequest request;

        public GetDailyTransactionHistoryTask(
            GetDailyTransactionHistoryRequest request,
            AsyncAction<AsyncResult<GetDailyTransactionHistoryResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDailyTransactionHistoryResult parse(JsonNode data) {
            return GetDailyTransactionHistoryResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/transaction/daily/{year}/{month}/{day}/currency/{currency}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null  ? "null" : String.valueOf(this.request.getMonth()));
            url = url.replace("{day}", this.request.getDay() == null  ? "null" : String.valueOf(this.request.getDay()));
            url = url.replace("{currency}", this.request.getCurrency() == null || this.request.getCurrency().length() == 0 ? "null" : String.valueOf(this.request.getCurrency()));

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

    public void getDailyTransactionHistoryAsync(
            GetDailyTransactionHistoryRequest request,
            AsyncAction<AsyncResult<GetDailyTransactionHistoryResult>> callback
    ) {
        GetDailyTransactionHistoryTask task = new GetDailyTransactionHistoryTask(request, callback);
        session.execute(task);
    }

    public GetDailyTransactionHistoryResult getDailyTransactionHistory(
            GetDailyTransactionHistoryRequest request
    ) {
        final AsyncResult<GetDailyTransactionHistoryResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDailyTransactionHistoryAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeUnusedBalancesTask extends Gs2RestSessionTask<DescribeUnusedBalancesResult> {
        private DescribeUnusedBalancesRequest request;

        public DescribeUnusedBalancesTask(
            DescribeUnusedBalancesRequest request,
            AsyncAction<AsyncResult<DescribeUnusedBalancesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeUnusedBalancesResult parse(JsonNode data) {
            return DescribeUnusedBalancesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/balance/unused";

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

    public void describeUnusedBalancesAsync(
            DescribeUnusedBalancesRequest request,
            AsyncAction<AsyncResult<DescribeUnusedBalancesResult>> callback
    ) {
        DescribeUnusedBalancesTask task = new DescribeUnusedBalancesTask(request, callback);
        session.execute(task);
    }

    public DescribeUnusedBalancesResult describeUnusedBalances(
            DescribeUnusedBalancesRequest request
    ) {
        final AsyncResult<DescribeUnusedBalancesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeUnusedBalancesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetUnusedBalanceTask extends Gs2RestSessionTask<GetUnusedBalanceResult> {
        private GetUnusedBalanceRequest request;

        public GetUnusedBalanceTask(
            GetUnusedBalanceRequest request,
            AsyncAction<AsyncResult<GetUnusedBalanceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetUnusedBalanceResult parse(JsonNode data) {
            return GetUnusedBalanceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/balance/unused/{currency}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{currency}", this.request.getCurrency() == null || this.request.getCurrency().length() == 0 ? "null" : String.valueOf(this.request.getCurrency()));

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

    public void getUnusedBalanceAsync(
            GetUnusedBalanceRequest request,
            AsyncAction<AsyncResult<GetUnusedBalanceResult>> callback
    ) {
        GetUnusedBalanceTask task = new GetUnusedBalanceTask(request, callback);
        session.execute(task);
    }

    public GetUnusedBalanceResult getUnusedBalance(
            GetUnusedBalanceRequest request
    ) {
        final AsyncResult<GetUnusedBalanceResult>[] resultAsyncResult = new AsyncResult[]{null};
        getUnusedBalanceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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