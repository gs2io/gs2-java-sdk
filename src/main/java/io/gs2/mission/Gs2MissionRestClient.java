
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

package io.gs2.mission;

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
import io.gs2.mission.request.*;
import io.gs2.mission.result.*;
import io.gs2.mission.model.*;public class Gs2MissionRestClient extends AbstractGs2Client<Gs2MissionRestClient> {

	public Gs2MissionRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeCompletesTask extends Gs2RestSessionTask<DescribeCompletesResult> {
        private DescribeCompletesRequest request;

        public DescribeCompletesTask(
            DescribeCompletesRequest request,
            AsyncAction<AsyncResult<DescribeCompletesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCompletesResult parse(JsonNode data) {
            return DescribeCompletesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete";

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

    public void describeCompletesAsync(
            DescribeCompletesRequest request,
            AsyncAction<AsyncResult<DescribeCompletesResult>> callback
    ) {
        DescribeCompletesTask task = new DescribeCompletesTask(request, callback);
        session.execute(task);
    }

    public DescribeCompletesResult describeCompletes(
            DescribeCompletesRequest request
    ) {
        final AsyncResult<DescribeCompletesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCompletesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCompletesByUserIdTask extends Gs2RestSessionTask<DescribeCompletesByUserIdResult> {
        private DescribeCompletesByUserIdRequest request;

        public DescribeCompletesByUserIdTask(
            DescribeCompletesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCompletesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCompletesByUserIdResult parse(JsonNode data) {
            return DescribeCompletesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete";

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

    public void describeCompletesByUserIdAsync(
            DescribeCompletesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCompletesByUserIdResult>> callback
    ) {
        DescribeCompletesByUserIdTask task = new DescribeCompletesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeCompletesByUserIdResult describeCompletesByUserId(
            DescribeCompletesByUserIdRequest request
    ) {
        final AsyncResult<DescribeCompletesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCompletesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CompleteTask extends Gs2RestSessionTask<CompleteResult> {
        private CompleteRequest request;

        public CompleteTask(
            CompleteRequest request,
            AsyncAction<AsyncResult<CompleteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CompleteResult parse(JsonNode data) {
            return CompleteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? null :
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

    public void completeAsync(
            CompleteRequest request,
            AsyncAction<AsyncResult<CompleteResult>> callback
    ) {
        CompleteTask task = new CompleteTask(request, callback);
        session.execute(task);
    }

    public CompleteResult complete(
            CompleteRequest request
    ) {
        final AsyncResult<CompleteResult>[] resultAsyncResult = new AsyncResult[]{null};
        completeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CompleteByUserIdTask extends Gs2RestSessionTask<CompleteByUserIdResult> {
        private CompleteByUserIdRequest request;

        public CompleteByUserIdTask(
            CompleteByUserIdRequest request,
            AsyncAction<AsyncResult<CompleteByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CompleteByUserIdResult parse(JsonNode data) {
            return CompleteByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? null :
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

    public void completeByUserIdAsync(
            CompleteByUserIdRequest request,
            AsyncAction<AsyncResult<CompleteByUserIdResult>> callback
    ) {
        CompleteByUserIdTask task = new CompleteByUserIdTask(request, callback);
        session.execute(task);
    }

    public CompleteByUserIdResult completeByUserId(
            CompleteByUserIdRequest request
    ) {
        final AsyncResult<CompleteByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        completeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class BatchCompleteTask extends Gs2RestSessionTask<BatchCompleteResult> {
        private BatchCompleteRequest request;

        public BatchCompleteTask(
            BatchCompleteRequest request,
            AsyncAction<AsyncResult<BatchCompleteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public BatchCompleteResult parse(JsonNode data) {
            return BatchCompleteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete/group/{missionGroupName}/task/any/batch";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("missionTaskNames", request.getMissionTaskNames() == null ? null :
                        request.getMissionTaskNames().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("config", request.getConfig() == null ? null :
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

    public void batchCompleteAsync(
            BatchCompleteRequest request,
            AsyncAction<AsyncResult<BatchCompleteResult>> callback
    ) {
        BatchCompleteTask task = new BatchCompleteTask(request, callback);
        session.execute(task);
    }

    public BatchCompleteResult batchComplete(
            BatchCompleteRequest request
    ) {
        final AsyncResult<BatchCompleteResult>[] resultAsyncResult = new AsyncResult[]{null};
        batchCompleteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class BatchCompleteByUserIdTask extends Gs2RestSessionTask<BatchCompleteByUserIdResult> {
        private BatchCompleteByUserIdRequest request;

        public BatchCompleteByUserIdTask(
            BatchCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<BatchCompleteByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public BatchCompleteByUserIdResult parse(JsonNode data) {
            return BatchCompleteByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/any/batch";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("missionTaskNames", request.getMissionTaskNames() == null ? null :
                        request.getMissionTaskNames().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("config", request.getConfig() == null ? null :
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

    public void batchCompleteByUserIdAsync(
            BatchCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<BatchCompleteByUserIdResult>> callback
    ) {
        BatchCompleteByUserIdTask task = new BatchCompleteByUserIdTask(request, callback);
        session.execute(task);
    }

    public BatchCompleteByUserIdResult batchCompleteByUserId(
            BatchCompleteByUserIdRequest request
    ) {
        final AsyncResult<BatchCompleteByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        batchCompleteByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReceiveByUserIdTask extends Gs2RestSessionTask<ReceiveByUserIdResult> {
        private ReceiveByUserIdRequest request;

        public ReceiveByUserIdTask(
            ReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveByUserIdResult parse(JsonNode data) {
            return ReceiveByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/{missionTaskName}/receive";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
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

    public void receiveByUserIdAsync(
            ReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveByUserIdResult>> callback
    ) {
        ReceiveByUserIdTask task = new ReceiveByUserIdTask(request, callback);
        session.execute(task);
    }

    public ReceiveByUserIdResult receiveByUserId(
            ReceiveByUserIdRequest request
    ) {
        final AsyncResult<ReceiveByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class BatchReceiveByUserIdTask extends Gs2RestSessionTask<BatchReceiveByUserIdResult> {
        private BatchReceiveByUserIdRequest request;

        public BatchReceiveByUserIdTask(
            BatchReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<BatchReceiveByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public BatchReceiveByUserIdResult parse(JsonNode data) {
            return BatchReceiveByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/any/receive/batch";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("missionTaskNames", request.getMissionTaskNames() == null ? null :
                        request.getMissionTaskNames().stream().map(item -> {
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void batchReceiveByUserIdAsync(
            BatchReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<BatchReceiveByUserIdResult>> callback
    ) {
        BatchReceiveByUserIdTask task = new BatchReceiveByUserIdTask(request, callback);
        session.execute(task);
    }

    public BatchReceiveByUserIdResult batchReceiveByUserId(
            BatchReceiveByUserIdRequest request
    ) {
        final AsyncResult<BatchReceiveByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        batchReceiveByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RevertReceiveByUserIdTask extends Gs2RestSessionTask<RevertReceiveByUserIdResult> {
        private RevertReceiveByUserIdRequest request;

        public RevertReceiveByUserIdTask(
            RevertReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<RevertReceiveByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RevertReceiveByUserIdResult parse(JsonNode data) {
            return RevertReceiveByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/{missionTaskName}/revert";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
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

    public void revertReceiveByUserIdAsync(
            RevertReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<RevertReceiveByUserIdResult>> callback
    ) {
        RevertReceiveByUserIdTask task = new RevertReceiveByUserIdTask(request, callback);
        session.execute(task);
    }

    public RevertReceiveByUserIdResult revertReceiveByUserId(
            RevertReceiveByUserIdRequest request
    ) {
        final AsyncResult<RevertReceiveByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        revertReceiveByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCompleteTask extends Gs2RestSessionTask<GetCompleteResult> {
        private GetCompleteRequest request;

        public GetCompleteTask(
            GetCompleteRequest request,
            AsyncAction<AsyncResult<GetCompleteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCompleteResult parse(JsonNode data) {
            return GetCompleteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void getCompleteAsync(
            GetCompleteRequest request,
            AsyncAction<AsyncResult<GetCompleteResult>> callback
    ) {
        GetCompleteTask task = new GetCompleteTask(request, callback);
        session.execute(task);
    }

    public GetCompleteResult getComplete(
            GetCompleteRequest request
    ) {
        final AsyncResult<GetCompleteResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCompleteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCompleteByUserIdTask extends Gs2RestSessionTask<GetCompleteByUserIdResult> {
        private GetCompleteByUserIdRequest request;

        public GetCompleteByUserIdTask(
            GetCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<GetCompleteByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCompleteByUserIdResult parse(JsonNode data) {
            return GetCompleteByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
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

    public void getCompleteByUserIdAsync(
            GetCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<GetCompleteByUserIdResult>> callback
    ) {
        GetCompleteByUserIdTask task = new GetCompleteByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetCompleteByUserIdResult getCompleteByUserId(
            GetCompleteByUserIdRequest request
    ) {
        final AsyncResult<GetCompleteByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCompleteByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCompleteByUserIdTask extends Gs2RestSessionTask<DeleteCompleteByUserIdResult> {
        private DeleteCompleteByUserIdRequest request;

        public DeleteCompleteByUserIdTask(
            DeleteCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCompleteByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteCompleteByUserIdResult parse(JsonNode data) {
            return DeleteCompleteByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void deleteCompleteByUserIdAsync(
            DeleteCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCompleteByUserIdResult>> callback
    ) {
        DeleteCompleteByUserIdTask task = new DeleteCompleteByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteCompleteByUserIdResult deleteCompleteByUserId(
            DeleteCompleteByUserIdRequest request
    ) {
        final AsyncResult<DeleteCompleteByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCompleteByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyCompleteTask extends Gs2RestSessionTask<VerifyCompleteResult> {
        private VerifyCompleteRequest request;

        public VerifyCompleteTask(
            VerifyCompleteRequest request,
            AsyncAction<AsyncResult<VerifyCompleteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCompleteResult parse(JsonNode data) {
            return VerifyCompleteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete/group/{missionGroupName}/task/{missionTaskName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
            url = url.replace("{multiplyValueSpecifyingQuantity}", this.request.getMultiplyValueSpecifyingQuantity() == null  ? "null" : String.valueOf(this.request.getMultiplyValueSpecifyingQuantity()));

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

    public void verifyCompleteAsync(
            VerifyCompleteRequest request,
            AsyncAction<AsyncResult<VerifyCompleteResult>> callback
    ) {
        VerifyCompleteTask task = new VerifyCompleteTask(request, callback);
        session.execute(task);
    }

    public VerifyCompleteResult verifyComplete(
            VerifyCompleteRequest request
    ) {
        final AsyncResult<VerifyCompleteResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCompleteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyCompleteByUserIdTask extends Gs2RestSessionTask<VerifyCompleteByUserIdResult> {
        private VerifyCompleteByUserIdRequest request;

        public VerifyCompleteByUserIdTask(
            VerifyCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyCompleteByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCompleteByUserIdResult parse(JsonNode data) {
            return VerifyCompleteByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/{missionTaskName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
            url = url.replace("{multiplyValueSpecifyingQuantity}", this.request.getMultiplyValueSpecifyingQuantity() == null  ? "null" : String.valueOf(this.request.getMultiplyValueSpecifyingQuantity()));

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

    public void verifyCompleteByUserIdAsync(
            VerifyCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyCompleteByUserIdResult>> callback
    ) {
        VerifyCompleteByUserIdTask task = new VerifyCompleteByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyCompleteByUserIdResult verifyCompleteByUserId(
            VerifyCompleteByUserIdRequest request
    ) {
        final AsyncResult<VerifyCompleteByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCompleteByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReceiveByStampTaskTask extends Gs2RestSessionTask<ReceiveByStampTaskResult> {
        private ReceiveByStampTaskRequest request;

        public ReceiveByStampTaskTask(
            ReceiveByStampTaskRequest request,
            AsyncAction<AsyncResult<ReceiveByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveByStampTaskResult parse(JsonNode data) {
            return ReceiveByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/receive";

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

    public void receiveByStampTaskAsync(
            ReceiveByStampTaskRequest request,
            AsyncAction<AsyncResult<ReceiveByStampTaskResult>> callback
    ) {
        ReceiveByStampTaskTask task = new ReceiveByStampTaskTask(request, callback);
        session.execute(task);
    }

    public ReceiveByStampTaskResult receiveByStampTask(
            ReceiveByStampTaskRequest request
    ) {
        final AsyncResult<ReceiveByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class BatchReceiveByStampTaskTask extends Gs2RestSessionTask<BatchReceiveByStampTaskResult> {
        private BatchReceiveByStampTaskRequest request;

        public BatchReceiveByStampTaskTask(
            BatchReceiveByStampTaskRequest request,
            AsyncAction<AsyncResult<BatchReceiveByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public BatchReceiveByStampTaskResult parse(JsonNode data) {
            return BatchReceiveByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/receive/batch";

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

    public void batchReceiveByStampTaskAsync(
            BatchReceiveByStampTaskRequest request,
            AsyncAction<AsyncResult<BatchReceiveByStampTaskResult>> callback
    ) {
        BatchReceiveByStampTaskTask task = new BatchReceiveByStampTaskTask(request, callback);
        session.execute(task);
    }

    public BatchReceiveByStampTaskResult batchReceiveByStampTask(
            BatchReceiveByStampTaskRequest request
    ) {
        final AsyncResult<BatchReceiveByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        batchReceiveByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RevertReceiveByStampSheetTask extends Gs2RestSessionTask<RevertReceiveByStampSheetResult> {
        private RevertReceiveByStampSheetRequest request;

        public RevertReceiveByStampSheetTask(
            RevertReceiveByStampSheetRequest request,
            AsyncAction<AsyncResult<RevertReceiveByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RevertReceiveByStampSheetResult parse(JsonNode data) {
            return RevertReceiveByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/revert";

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

    public void revertReceiveByStampSheetAsync(
            RevertReceiveByStampSheetRequest request,
            AsyncAction<AsyncResult<RevertReceiveByStampSheetResult>> callback
    ) {
        RevertReceiveByStampSheetTask task = new RevertReceiveByStampSheetTask(request, callback);
        session.execute(task);
    }

    public RevertReceiveByStampSheetResult revertReceiveByStampSheet(
            RevertReceiveByStampSheetRequest request
    ) {
        final AsyncResult<RevertReceiveByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        revertReceiveByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyCompleteByStampTaskTask extends Gs2RestSessionTask<VerifyCompleteByStampTaskResult> {
        private VerifyCompleteByStampTaskRequest request;

        public VerifyCompleteByStampTaskTask(
            VerifyCompleteByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyCompleteByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCompleteByStampTaskResult parse(JsonNode data) {
            return VerifyCompleteByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/complete/verify";

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

    public void verifyCompleteByStampTaskAsync(
            VerifyCompleteByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyCompleteByStampTaskResult>> callback
    ) {
        VerifyCompleteByStampTaskTask task = new VerifyCompleteByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyCompleteByStampTaskResult verifyCompleteByStampTask(
            VerifyCompleteByStampTaskRequest request
    ) {
        final AsyncResult<VerifyCompleteByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCompleteByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCounterModelMastersTask extends Gs2RestSessionTask<DescribeCounterModelMastersResult> {
        private DescribeCounterModelMastersRequest request;

        public DescribeCounterModelMastersTask(
            DescribeCounterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCounterModelMastersResult parse(JsonNode data) {
            return DescribeCounterModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter";

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

    public void describeCounterModelMastersAsync(
            DescribeCounterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelMastersResult>> callback
    ) {
        DescribeCounterModelMastersTask task = new DescribeCounterModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeCounterModelMastersResult describeCounterModelMasters(
            DescribeCounterModelMastersRequest request
    ) {
        final AsyncResult<DescribeCounterModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCounterModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateCounterModelMasterTask extends Gs2RestSessionTask<CreateCounterModelMasterResult> {
        private CreateCounterModelMasterRequest request;

        public CreateCounterModelMasterTask(
            CreateCounterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCounterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateCounterModelMasterResult parse(JsonNode data) {
            return CreateCounterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("metadata", request.getMetadata());
                    put("description", request.getDescription());
                    put("scopes", request.getScopes() == null ? null :
                        request.getScopes().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("challengePeriodEventId", request.getChallengePeriodEventId());
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

    public void createCounterModelMasterAsync(
            CreateCounterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCounterModelMasterResult>> callback
    ) {
        CreateCounterModelMasterTask task = new CreateCounterModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateCounterModelMasterResult createCounterModelMaster(
            CreateCounterModelMasterRequest request
    ) {
        final AsyncResult<CreateCounterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createCounterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCounterModelMasterTask extends Gs2RestSessionTask<GetCounterModelMasterResult> {
        private GetCounterModelMasterRequest request;

        public GetCounterModelMasterTask(
            GetCounterModelMasterRequest request,
            AsyncAction<AsyncResult<GetCounterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCounterModelMasterResult parse(JsonNode data) {
            return GetCounterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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

    public void getCounterModelMasterAsync(
            GetCounterModelMasterRequest request,
            AsyncAction<AsyncResult<GetCounterModelMasterResult>> callback
    ) {
        GetCounterModelMasterTask task = new GetCounterModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetCounterModelMasterResult getCounterModelMaster(
            GetCounterModelMasterRequest request
    ) {
        final AsyncResult<GetCounterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCounterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCounterModelMasterTask extends Gs2RestSessionTask<UpdateCounterModelMasterResult> {
        private UpdateCounterModelMasterRequest request;

        public UpdateCounterModelMasterTask(
            UpdateCounterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCounterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCounterModelMasterResult parse(JsonNode data) {
            return UpdateCounterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("metadata", request.getMetadata());
                    put("description", request.getDescription());
                    put("scopes", request.getScopes() == null ? null :
                        request.getScopes().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("challengePeriodEventId", request.getChallengePeriodEventId());
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

    public void updateCounterModelMasterAsync(
            UpdateCounterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCounterModelMasterResult>> callback
    ) {
        UpdateCounterModelMasterTask task = new UpdateCounterModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCounterModelMasterResult updateCounterModelMaster(
            UpdateCounterModelMasterRequest request
    ) {
        final AsyncResult<UpdateCounterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCounterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCounterModelMasterTask extends Gs2RestSessionTask<DeleteCounterModelMasterResult> {
        private DeleteCounterModelMasterRequest request;

        public DeleteCounterModelMasterTask(
            DeleteCounterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCounterModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteCounterModelMasterResult parse(JsonNode data) {
            return DeleteCounterModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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

    public void deleteCounterModelMasterAsync(
            DeleteCounterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCounterModelMasterResult>> callback
    ) {
        DeleteCounterModelMasterTask task = new DeleteCounterModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteCounterModelMasterResult deleteCounterModelMaster(
            DeleteCounterModelMasterRequest request
    ) {
        final AsyncResult<DeleteCounterModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCounterModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMissionGroupModelMastersTask extends Gs2RestSessionTask<DescribeMissionGroupModelMastersResult> {
        private DescribeMissionGroupModelMastersRequest request;

        public DescribeMissionGroupModelMastersTask(
            DescribeMissionGroupModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMissionGroupModelMastersResult parse(JsonNode data) {
            return DescribeMissionGroupModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

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

    public void describeMissionGroupModelMastersAsync(
            DescribeMissionGroupModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelMastersResult>> callback
    ) {
        DescribeMissionGroupModelMastersTask task = new DescribeMissionGroupModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeMissionGroupModelMastersResult describeMissionGroupModelMasters(
            DescribeMissionGroupModelMastersRequest request
    ) {
        final AsyncResult<DescribeMissionGroupModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMissionGroupModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateMissionGroupModelMasterTask extends Gs2RestSessionTask<CreateMissionGroupModelMasterResult> {
        private CreateMissionGroupModelMasterRequest request;

        public CreateMissionGroupModelMasterTask(
            CreateMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMissionGroupModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateMissionGroupModelMasterResult parse(JsonNode data) {
            return CreateMissionGroupModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("metadata", request.getMetadata());
                    put("description", request.getDescription());
                    put("resetType", request.getResetType());
                    put("resetDayOfMonth", request.getResetDayOfMonth());
                    put("resetDayOfWeek", request.getResetDayOfWeek());
                    put("resetHour", request.getResetHour());
                    put("completeNotificationNamespaceId", request.getCompleteNotificationNamespaceId());
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

    public void createMissionGroupModelMasterAsync(
            CreateMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMissionGroupModelMasterResult>> callback
    ) {
        CreateMissionGroupModelMasterTask task = new CreateMissionGroupModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateMissionGroupModelMasterResult createMissionGroupModelMaster(
            CreateMissionGroupModelMasterRequest request
    ) {
        final AsyncResult<CreateMissionGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createMissionGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMissionGroupModelMasterTask extends Gs2RestSessionTask<GetMissionGroupModelMasterResult> {
        private GetMissionGroupModelMasterRequest request;

        public GetMissionGroupModelMasterTask(
            GetMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<GetMissionGroupModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMissionGroupModelMasterResult parse(JsonNode data) {
            return GetMissionGroupModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void getMissionGroupModelMasterAsync(
            GetMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<GetMissionGroupModelMasterResult>> callback
    ) {
        GetMissionGroupModelMasterTask task = new GetMissionGroupModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetMissionGroupModelMasterResult getMissionGroupModelMaster(
            GetMissionGroupModelMasterRequest request
    ) {
        final AsyncResult<GetMissionGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMissionGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateMissionGroupModelMasterTask extends Gs2RestSessionTask<UpdateMissionGroupModelMasterResult> {
        private UpdateMissionGroupModelMasterRequest request;

        public UpdateMissionGroupModelMasterTask(
            UpdateMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMissionGroupModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateMissionGroupModelMasterResult parse(JsonNode data) {
            return UpdateMissionGroupModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("metadata", request.getMetadata());
                    put("description", request.getDescription());
                    put("resetType", request.getResetType());
                    put("resetDayOfMonth", request.getResetDayOfMonth());
                    put("resetDayOfWeek", request.getResetDayOfWeek());
                    put("resetHour", request.getResetHour());
                    put("completeNotificationNamespaceId", request.getCompleteNotificationNamespaceId());
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

    public void updateMissionGroupModelMasterAsync(
            UpdateMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMissionGroupModelMasterResult>> callback
    ) {
        UpdateMissionGroupModelMasterTask task = new UpdateMissionGroupModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateMissionGroupModelMasterResult updateMissionGroupModelMaster(
            UpdateMissionGroupModelMasterRequest request
    ) {
        final AsyncResult<UpdateMissionGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMissionGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMissionGroupModelMasterTask extends Gs2RestSessionTask<DeleteMissionGroupModelMasterResult> {
        private DeleteMissionGroupModelMasterRequest request;

        public DeleteMissionGroupModelMasterTask(
            DeleteMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMissionGroupModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMissionGroupModelMasterResult parse(JsonNode data) {
            return DeleteMissionGroupModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void deleteMissionGroupModelMasterAsync(
            DeleteMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMissionGroupModelMasterResult>> callback
    ) {
        DeleteMissionGroupModelMasterTask task = new DeleteMissionGroupModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteMissionGroupModelMasterResult deleteMissionGroupModelMaster(
            DeleteMissionGroupModelMasterRequest request
    ) {
        final AsyncResult<DeleteMissionGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMissionGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("missionCompleteScript", request.getMissionCompleteScript() != null ? request.getMissionCompleteScript().toJson() : null);
                    put("counterIncrementScript", request.getCounterIncrementScript() != null ? request.getCounterIncrementScript().toJson() : null);
                    put("receiveRewardsScript", request.getReceiveRewardsScript() != null ? request.getReceiveRewardsScript().toJson() : null);
                    put("completeNotification", request.getCompleteNotification() != null ? request.getCompleteNotification().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("queueNamespaceId", request.getQueueNamespaceId());
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("missionCompleteScript", request.getMissionCompleteScript() != null ? request.getMissionCompleteScript().toJson() : null);
                    put("counterIncrementScript", request.getCounterIncrementScript() != null ? request.getCounterIncrementScript().toJson() : null);
                    put("receiveRewardsScript", request.getReceiveRewardsScript() != null ? request.getReceiveRewardsScript().toJson() : null);
                    put("completeNotification", request.getCompleteNotification() != null ? request.getCompleteNotification().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("queueNamespaceId", request.getQueueNamespaceId());
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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

    class DescribeCountersTask extends Gs2RestSessionTask<DescribeCountersResult> {
        private DescribeCountersRequest request;

        public DescribeCountersTask(
            DescribeCountersRequest request,
            AsyncAction<AsyncResult<DescribeCountersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCountersResult parse(JsonNode data) {
            return DescribeCountersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter";

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

    public void describeCountersAsync(
            DescribeCountersRequest request,
            AsyncAction<AsyncResult<DescribeCountersResult>> callback
    ) {
        DescribeCountersTask task = new DescribeCountersTask(request, callback);
        session.execute(task);
    }

    public DescribeCountersResult describeCounters(
            DescribeCountersRequest request
    ) {
        final AsyncResult<DescribeCountersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCountersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCountersByUserIdTask extends Gs2RestSessionTask<DescribeCountersByUserIdResult> {
        private DescribeCountersByUserIdRequest request;

        public DescribeCountersByUserIdTask(
            DescribeCountersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCountersByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCountersByUserIdResult parse(JsonNode data) {
            return DescribeCountersByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter";

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

    public void describeCountersByUserIdAsync(
            DescribeCountersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCountersByUserIdResult>> callback
    ) {
        DescribeCountersByUserIdTask task = new DescribeCountersByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeCountersByUserIdResult describeCountersByUserId(
            DescribeCountersByUserIdRequest request
    ) {
        final AsyncResult<DescribeCountersByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCountersByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncreaseCounterByUserIdTask extends Gs2RestSessionTask<IncreaseCounterByUserIdResult> {
        private IncreaseCounterByUserIdRequest request;

        public IncreaseCounterByUserIdTask(
            IncreaseCounterByUserIdRequest request,
            AsyncAction<AsyncResult<IncreaseCounterByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncreaseCounterByUserIdResult parse(JsonNode data) {
            return IncreaseCounterByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
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

    public void increaseCounterByUserIdAsync(
            IncreaseCounterByUserIdRequest request,
            AsyncAction<AsyncResult<IncreaseCounterByUserIdResult>> callback
    ) {
        IncreaseCounterByUserIdTask task = new IncreaseCounterByUserIdTask(request, callback);
        session.execute(task);
    }

    public IncreaseCounterByUserIdResult increaseCounterByUserId(
            IncreaseCounterByUserIdRequest request
    ) {
        final AsyncResult<IncreaseCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        increaseCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetCounterByUserIdTask extends Gs2RestSessionTask<SetCounterByUserIdResult> {
        private SetCounterByUserIdRequest request;

        public SetCounterByUserIdTask(
            SetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<SetCounterByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetCounterByUserIdResult parse(JsonNode data) {
            return SetCounterByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("values", request.getValues() == null ? null :
                        request.getValues().stream().map(item -> {
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void setCounterByUserIdAsync(
            SetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<SetCounterByUserIdResult>> callback
    ) {
        SetCounterByUserIdTask task = new SetCounterByUserIdTask(request, callback);
        session.execute(task);
    }

    public SetCounterByUserIdResult setCounterByUserId(
            SetCounterByUserIdRequest request
    ) {
        final AsyncResult<SetCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecreaseCounterTask extends Gs2RestSessionTask<DecreaseCounterResult> {
        private DecreaseCounterRequest request;

        public DecreaseCounterTask(
            DecreaseCounterRequest request,
            AsyncAction<AsyncResult<DecreaseCounterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseCounterResult parse(JsonNode data) {
            return DecreaseCounterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{counterName}/decrease";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
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

    public void decreaseCounterAsync(
            DecreaseCounterRequest request,
            AsyncAction<AsyncResult<DecreaseCounterResult>> callback
    ) {
        DecreaseCounterTask task = new DecreaseCounterTask(request, callback);
        session.execute(task);
    }

    public DecreaseCounterResult decreaseCounter(
            DecreaseCounterRequest request
    ) {
        final AsyncResult<DecreaseCounterResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseCounterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecreaseCounterByUserIdTask extends Gs2RestSessionTask<DecreaseCounterByUserIdResult> {
        private DecreaseCounterByUserIdRequest request;

        public DecreaseCounterByUserIdTask(
            DecreaseCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DecreaseCounterByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseCounterByUserIdResult parse(JsonNode data) {
            return DecreaseCounterByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}/decrease";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
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

    public void decreaseCounterByUserIdAsync(
            DecreaseCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DecreaseCounterByUserIdResult>> callback
    ) {
        DecreaseCounterByUserIdTask task = new DecreaseCounterByUserIdTask(request, callback);
        session.execute(task);
    }

    public DecreaseCounterByUserIdResult decreaseCounterByUserId(
            DecreaseCounterByUserIdRequest request
    ) {
        final AsyncResult<DecreaseCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCounterTask extends Gs2RestSessionTask<GetCounterResult> {
        private GetCounterRequest request;

        public GetCounterTask(
            GetCounterRequest request,
            AsyncAction<AsyncResult<GetCounterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCounterResult parse(JsonNode data) {
            return GetCounterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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

    public void getCounterAsync(
            GetCounterRequest request,
            AsyncAction<AsyncResult<GetCounterResult>> callback
    ) {
        GetCounterTask task = new GetCounterTask(request, callback);
        session.execute(task);
    }

    public GetCounterResult getCounter(
            GetCounterRequest request
    ) {
        final AsyncResult<GetCounterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCounterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCounterByUserIdTask extends Gs2RestSessionTask<GetCounterByUserIdResult> {
        private GetCounterByUserIdRequest request;

        public GetCounterByUserIdTask(
            GetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<GetCounterByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCounterByUserIdResult parse(JsonNode data) {
            return GetCounterByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
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

    public void getCounterByUserIdAsync(
            GetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<GetCounterByUserIdResult>> callback
    ) {
        GetCounterByUserIdTask task = new GetCounterByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetCounterByUserIdResult getCounterByUserId(
            GetCounterByUserIdRequest request
    ) {
        final AsyncResult<GetCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyCounterValueTask extends Gs2RestSessionTask<VerifyCounterValueResult> {
        private VerifyCounterValueRequest request;

        public VerifyCounterValueTask(
            VerifyCounterValueRequest request,
            AsyncAction<AsyncResult<VerifyCounterValueResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCounterValueResult parse(JsonNode data) {
            return VerifyCounterValueResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{counterName}/verify/counter/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("scopeType", request.getScopeType());
                    put("resetType", request.getResetType());
                    put("conditionName", request.getConditionName());
                    put("value", request.getValue());
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

    public void verifyCounterValueAsync(
            VerifyCounterValueRequest request,
            AsyncAction<AsyncResult<VerifyCounterValueResult>> callback
    ) {
        VerifyCounterValueTask task = new VerifyCounterValueTask(request, callback);
        session.execute(task);
    }

    public VerifyCounterValueResult verifyCounterValue(
            VerifyCounterValueRequest request
    ) {
        final AsyncResult<VerifyCounterValueResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCounterValueAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyCounterValueByUserIdTask extends Gs2RestSessionTask<VerifyCounterValueByUserIdResult> {
        private VerifyCounterValueByUserIdRequest request;

        public VerifyCounterValueByUserIdTask(
            VerifyCounterValueByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyCounterValueByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCounterValueByUserIdResult parse(JsonNode data) {
            return VerifyCounterValueByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}/verify/counter/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("scopeType", request.getScopeType());
                    put("resetType", request.getResetType());
                    put("conditionName", request.getConditionName());
                    put("value", request.getValue());
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

    public void verifyCounterValueByUserIdAsync(
            VerifyCounterValueByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyCounterValueByUserIdResult>> callback
    ) {
        VerifyCounterValueByUserIdTask task = new VerifyCounterValueByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyCounterValueByUserIdResult verifyCounterValueByUserId(
            VerifyCounterValueByUserIdRequest request
    ) {
        final AsyncResult<VerifyCounterValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCounterValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCounterTask extends Gs2RestSessionTask<DeleteCounterResult> {
        private DeleteCounterRequest request;

        public DeleteCounterTask(
            DeleteCounterRequest request,
            AsyncAction<AsyncResult<DeleteCounterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteCounterResult parse(JsonNode data) {
            return DeleteCounterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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

    public void deleteCounterAsync(
            DeleteCounterRequest request,
            AsyncAction<AsyncResult<DeleteCounterResult>> callback
    ) {
        DeleteCounterTask task = new DeleteCounterTask(request, callback);
        session.execute(task);
    }

    public DeleteCounterResult deleteCounter(
            DeleteCounterRequest request
    ) {
        final AsyncResult<DeleteCounterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCounterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCounterByUserIdTask extends Gs2RestSessionTask<DeleteCounterByUserIdResult> {
        private DeleteCounterByUserIdRequest request;

        public DeleteCounterByUserIdTask(
            DeleteCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCounterByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteCounterByUserIdResult parse(JsonNode data) {
            return DeleteCounterByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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

    public void deleteCounterByUserIdAsync(
            DeleteCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCounterByUserIdResult>> callback
    ) {
        DeleteCounterByUserIdTask task = new DeleteCounterByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteCounterByUserIdResult deleteCounterByUserId(
            DeleteCounterByUserIdRequest request
    ) {
        final AsyncResult<DeleteCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncreaseByStampSheetTask extends Gs2RestSessionTask<IncreaseByStampSheetResult> {
        private IncreaseByStampSheetRequest request;

        public IncreaseByStampSheetTask(
            IncreaseByStampSheetRequest request,
            AsyncAction<AsyncResult<IncreaseByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncreaseByStampSheetResult parse(JsonNode data) {
            return IncreaseByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/increase";

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

    public void increaseByStampSheetAsync(
            IncreaseByStampSheetRequest request,
            AsyncAction<AsyncResult<IncreaseByStampSheetResult>> callback
    ) {
        IncreaseByStampSheetTask task = new IncreaseByStampSheetTask(request, callback);
        session.execute(task);
    }

    public IncreaseByStampSheetResult increaseByStampSheet(
            IncreaseByStampSheetRequest request
    ) {
        final AsyncResult<IncreaseByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        increaseByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetByStampSheetTask extends Gs2RestSessionTask<SetByStampSheetResult> {
        private SetByStampSheetRequest request;

        public SetByStampSheetTask(
            SetByStampSheetRequest request,
            AsyncAction<AsyncResult<SetByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetByStampSheetResult parse(JsonNode data) {
            return SetByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/set";

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

    public void setByStampSheetAsync(
            SetByStampSheetRequest request,
            AsyncAction<AsyncResult<SetByStampSheetResult>> callback
    ) {
        SetByStampSheetTask task = new SetByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetByStampSheetResult setByStampSheet(
            SetByStampSheetRequest request
    ) {
        final AsyncResult<SetByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecreaseByStampTaskTask extends Gs2RestSessionTask<DecreaseByStampTaskResult> {
        private DecreaseByStampTaskRequest request;

        public DecreaseByStampTaskTask(
            DecreaseByStampTaskRequest request,
            AsyncAction<AsyncResult<DecreaseByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseByStampTaskResult parse(JsonNode data) {
            return DecreaseByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/decrease";

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

    public void decreaseByStampTaskAsync(
            DecreaseByStampTaskRequest request,
            AsyncAction<AsyncResult<DecreaseByStampTaskResult>> callback
    ) {
        DecreaseByStampTaskTask task = new DecreaseByStampTaskTask(request, callback);
        session.execute(task);
    }

    public DecreaseByStampTaskResult decreaseByStampTask(
            DecreaseByStampTaskRequest request
    ) {
        final AsyncResult<DecreaseByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyCounterValueByStampTaskTask extends Gs2RestSessionTask<VerifyCounterValueByStampTaskResult> {
        private VerifyCounterValueByStampTaskRequest request;

        public VerifyCounterValueByStampTaskTask(
            VerifyCounterValueByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyCounterValueByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCounterValueByStampTaskResult parse(JsonNode data) {
            return VerifyCounterValueByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/counter/verify";

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

    public void verifyCounterValueByStampTaskAsync(
            VerifyCounterValueByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyCounterValueByStampTaskResult>> callback
    ) {
        VerifyCounterValueByStampTaskTask task = new VerifyCounterValueByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyCounterValueByStampTaskResult verifyCounterValueByStampTask(
            VerifyCounterValueByStampTaskRequest request
    ) {
        final AsyncResult<VerifyCounterValueByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCounterValueByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "mission")
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

    class GetCurrentMissionMasterTask extends Gs2RestSessionTask<GetCurrentMissionMasterResult> {
        private GetCurrentMissionMasterRequest request;

        public GetCurrentMissionMasterTask(
            GetCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentMissionMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentMissionMasterResult parse(JsonNode data) {
            return GetCurrentMissionMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
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

    public void getCurrentMissionMasterAsync(
            GetCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentMissionMasterResult>> callback
    ) {
        GetCurrentMissionMasterTask task = new GetCurrentMissionMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentMissionMasterResult getCurrentMissionMaster(
            GetCurrentMissionMasterRequest request
    ) {
        final AsyncResult<GetCurrentMissionMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentMissionMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentMissionMasterTask extends Gs2RestSessionTask<UpdateCurrentMissionMasterResult> {
        private UpdateCurrentMissionMasterRequest request;

        public UpdateCurrentMissionMasterTask(
            UpdateCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentMissionMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentMissionMasterResult parse(JsonNode data) {
            return UpdateCurrentMissionMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
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

    public void updateCurrentMissionMasterAsync(
            UpdateCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentMissionMasterResult>> callback
    ) {
        UpdateCurrentMissionMasterTask task = new UpdateCurrentMissionMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentMissionMasterResult updateCurrentMissionMaster(
            UpdateCurrentMissionMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentMissionMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentMissionMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentMissionMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentMissionMasterFromGitHubResult> {
        private UpdateCurrentMissionMasterFromGitHubRequest request;

        public UpdateCurrentMissionMasterFromGitHubTask(
            UpdateCurrentMissionMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentMissionMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentMissionMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentMissionMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
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

    public void updateCurrentMissionMasterFromGitHubAsync(
            UpdateCurrentMissionMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentMissionMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentMissionMasterFromGitHubTask task = new UpdateCurrentMissionMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentMissionMasterFromGitHubResult updateCurrentMissionMasterFromGitHub(
            UpdateCurrentMissionMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentMissionMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentMissionMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCounterModelsTask extends Gs2RestSessionTask<DescribeCounterModelsResult> {
        private DescribeCounterModelsRequest request;

        public DescribeCounterModelsTask(
            DescribeCounterModelsRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCounterModelsResult parse(JsonNode data) {
            return DescribeCounterModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/counter";

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

    public void describeCounterModelsAsync(
            DescribeCounterModelsRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelsResult>> callback
    ) {
        DescribeCounterModelsTask task = new DescribeCounterModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeCounterModelsResult describeCounterModels(
            DescribeCounterModelsRequest request
    ) {
        final AsyncResult<DescribeCounterModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCounterModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCounterModelTask extends Gs2RestSessionTask<GetCounterModelResult> {
        private GetCounterModelRequest request;

        public GetCounterModelTask(
            GetCounterModelRequest request,
            AsyncAction<AsyncResult<GetCounterModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCounterModelResult parse(JsonNode data) {
            return GetCounterModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null || this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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

    public void getCounterModelAsync(
            GetCounterModelRequest request,
            AsyncAction<AsyncResult<GetCounterModelResult>> callback
    ) {
        GetCounterModelTask task = new GetCounterModelTask(request, callback);
        session.execute(task);
    }

    public GetCounterModelResult getCounterModel(
            GetCounterModelRequest request
    ) {
        final AsyncResult<GetCounterModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCounterModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMissionGroupModelsTask extends Gs2RestSessionTask<DescribeMissionGroupModelsResult> {
        private DescribeMissionGroupModelsRequest request;

        public DescribeMissionGroupModelsTask(
            DescribeMissionGroupModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMissionGroupModelsResult parse(JsonNode data) {
            return DescribeMissionGroupModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group";

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

    public void describeMissionGroupModelsAsync(
            DescribeMissionGroupModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelsResult>> callback
    ) {
        DescribeMissionGroupModelsTask task = new DescribeMissionGroupModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeMissionGroupModelsResult describeMissionGroupModels(
            DescribeMissionGroupModelsRequest request
    ) {
        final AsyncResult<DescribeMissionGroupModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMissionGroupModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMissionGroupModelTask extends Gs2RestSessionTask<GetMissionGroupModelResult> {
        private GetMissionGroupModelRequest request;

        public GetMissionGroupModelTask(
            GetMissionGroupModelRequest request,
            AsyncAction<AsyncResult<GetMissionGroupModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMissionGroupModelResult parse(JsonNode data) {
            return GetMissionGroupModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void getMissionGroupModelAsync(
            GetMissionGroupModelRequest request,
            AsyncAction<AsyncResult<GetMissionGroupModelResult>> callback
    ) {
        GetMissionGroupModelTask task = new GetMissionGroupModelTask(request, callback);
        session.execute(task);
    }

    public GetMissionGroupModelResult getMissionGroupModel(
            GetMissionGroupModelRequest request
    ) {
        final AsyncResult<GetMissionGroupModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMissionGroupModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMissionTaskModelsTask extends Gs2RestSessionTask<DescribeMissionTaskModelsResult> {
        private DescribeMissionTaskModelsRequest request;

        public DescribeMissionTaskModelsTask(
            DescribeMissionTaskModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMissionTaskModelsResult parse(JsonNode data) {
            return DescribeMissionTaskModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{missionGroupName}/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void describeMissionTaskModelsAsync(
            DescribeMissionTaskModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelsResult>> callback
    ) {
        DescribeMissionTaskModelsTask task = new DescribeMissionTaskModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeMissionTaskModelsResult describeMissionTaskModels(
            DescribeMissionTaskModelsRequest request
    ) {
        final AsyncResult<DescribeMissionTaskModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMissionTaskModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMissionTaskModelTask extends Gs2RestSessionTask<GetMissionTaskModelResult> {
        private GetMissionTaskModelRequest request;

        public GetMissionTaskModelTask(
            GetMissionTaskModelRequest request,
            AsyncAction<AsyncResult<GetMissionTaskModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMissionTaskModelResult parse(JsonNode data) {
            return GetMissionTaskModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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

    public void getMissionTaskModelAsync(
            GetMissionTaskModelRequest request,
            AsyncAction<AsyncResult<GetMissionTaskModelResult>> callback
    ) {
        GetMissionTaskModelTask task = new GetMissionTaskModelTask(request, callback);
        session.execute(task);
    }

    public GetMissionTaskModelResult getMissionTaskModel(
            GetMissionTaskModelRequest request
    ) {
        final AsyncResult<GetMissionTaskModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMissionTaskModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMissionTaskModelMastersTask extends Gs2RestSessionTask<DescribeMissionTaskModelMastersResult> {
        private DescribeMissionTaskModelMastersRequest request;

        public DescribeMissionTaskModelMastersTask(
            DescribeMissionTaskModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMissionTaskModelMastersResult parse(JsonNode data) {
            return DescribeMissionTaskModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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

    public void describeMissionTaskModelMastersAsync(
            DescribeMissionTaskModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelMastersResult>> callback
    ) {
        DescribeMissionTaskModelMastersTask task = new DescribeMissionTaskModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeMissionTaskModelMastersResult describeMissionTaskModelMasters(
            DescribeMissionTaskModelMastersRequest request
    ) {
        final AsyncResult<DescribeMissionTaskModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMissionTaskModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateMissionTaskModelMasterTask extends Gs2RestSessionTask<CreateMissionTaskModelMasterResult> {
        private CreateMissionTaskModelMasterRequest request;

        public CreateMissionTaskModelMasterTask(
            CreateMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMissionTaskModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateMissionTaskModelMasterResult parse(JsonNode data) {
            return CreateMissionTaskModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("metadata", request.getMetadata());
                    put("description", request.getDescription());
                    put("verifyCompleteType", request.getVerifyCompleteType());
                    put("targetCounter", request.getTargetCounter() != null ? request.getTargetCounter().toJson() : null);
                    put("verifyCompleteConsumeActions", request.getVerifyCompleteConsumeActions() == null ? null :
                        request.getVerifyCompleteConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("completeAcquireActions", request.getCompleteAcquireActions() == null ? null :
                        request.getCompleteAcquireActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("challengePeriodEventId", request.getChallengePeriodEventId());
                    put("premiseMissionTaskName", request.getPremiseMissionTaskName());
                    put("counterName", request.getCounterName());
                    put("targetResetType", request.getTargetResetType());
                    put("targetValue", request.getTargetValue());
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

    public void createMissionTaskModelMasterAsync(
            CreateMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMissionTaskModelMasterResult>> callback
    ) {
        CreateMissionTaskModelMasterTask task = new CreateMissionTaskModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateMissionTaskModelMasterResult createMissionTaskModelMaster(
            CreateMissionTaskModelMasterRequest request
    ) {
        final AsyncResult<CreateMissionTaskModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createMissionTaskModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMissionTaskModelMasterTask extends Gs2RestSessionTask<GetMissionTaskModelMasterResult> {
        private GetMissionTaskModelMasterRequest request;

        public GetMissionTaskModelMasterTask(
            GetMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<GetMissionTaskModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetMissionTaskModelMasterResult parse(JsonNode data) {
            return GetMissionTaskModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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

    public void getMissionTaskModelMasterAsync(
            GetMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<GetMissionTaskModelMasterResult>> callback
    ) {
        GetMissionTaskModelMasterTask task = new GetMissionTaskModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetMissionTaskModelMasterResult getMissionTaskModelMaster(
            GetMissionTaskModelMasterRequest request
    ) {
        final AsyncResult<GetMissionTaskModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMissionTaskModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateMissionTaskModelMasterTask extends Gs2RestSessionTask<UpdateMissionTaskModelMasterResult> {
        private UpdateMissionTaskModelMasterRequest request;

        public UpdateMissionTaskModelMasterTask(
            UpdateMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMissionTaskModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateMissionTaskModelMasterResult parse(JsonNode data) {
            return UpdateMissionTaskModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("metadata", request.getMetadata());
                    put("description", request.getDescription());
                    put("verifyCompleteType", request.getVerifyCompleteType());
                    put("targetCounter", request.getTargetCounter() != null ? request.getTargetCounter().toJson() : null);
                    put("verifyCompleteConsumeActions", request.getVerifyCompleteConsumeActions() == null ? null :
                        request.getVerifyCompleteConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("completeAcquireActions", request.getCompleteAcquireActions() == null ? null :
                        request.getCompleteAcquireActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("challengePeriodEventId", request.getChallengePeriodEventId());
                    put("premiseMissionTaskName", request.getPremiseMissionTaskName());
                    put("counterName", request.getCounterName());
                    put("targetResetType", request.getTargetResetType());
                    put("targetValue", request.getTargetValue());
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

    public void updateMissionTaskModelMasterAsync(
            UpdateMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMissionTaskModelMasterResult>> callback
    ) {
        UpdateMissionTaskModelMasterTask task = new UpdateMissionTaskModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateMissionTaskModelMasterResult updateMissionTaskModelMaster(
            UpdateMissionTaskModelMasterRequest request
    ) {
        final AsyncResult<UpdateMissionTaskModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMissionTaskModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMissionTaskModelMasterTask extends Gs2RestSessionTask<DeleteMissionTaskModelMasterResult> {
        private DeleteMissionTaskModelMasterRequest request;

        public DeleteMissionTaskModelMasterTask(
            DeleteMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMissionTaskModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMissionTaskModelMasterResult parse(JsonNode data) {
            return DeleteMissionTaskModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null || this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null || this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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

    public void deleteMissionTaskModelMasterAsync(
            DeleteMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMissionTaskModelMasterResult>> callback
    ) {
        DeleteMissionTaskModelMasterTask task = new DeleteMissionTaskModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteMissionTaskModelMasterResult deleteMissionTaskModelMaster(
            DeleteMissionTaskModelMasterRequest request
    ) {
        final AsyncResult<DeleteMissionTaskModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMissionTaskModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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