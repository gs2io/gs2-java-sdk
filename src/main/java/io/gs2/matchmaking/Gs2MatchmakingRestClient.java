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

package io.gs2.matchmaking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.json.JSONArray;

import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.exception.*;
import io.gs2.core.net.*;
import io.gs2.core.util.EncodingUtil;

import io.gs2.core.AbstractGs2Client;
import io.gs2.matchmaking.request.*;
import io.gs2.matchmaking.result.*;
import io.gs2.matchmaking.model.*;

/**
 * GS2 Matchmaking API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2MatchmakingRestClient extends AbstractGs2Client<Gs2MatchmakingRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2MatchmakingRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeNamespacesTask extends Gs2RestSessionTask<DescribeNamespacesResult> {
        private DescribeNamespacesRequest request;

        public DescribeNamespacesTask(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> userCallback,
            Class<DescribeNamespacesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
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

    /**
     * ネームスペースの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeNamespacesAsync(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> callback
    ) {
        DescribeNamespacesTask task = new DescribeNamespacesTask(request, callback, DescribeNamespacesResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateNamespaceResult>> userCallback,
            Class<CreateNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getEnableRating() != null) {
                json.put("enableRating", this.request.getEnableRating());
            }
            if (this.request.getCreateGatheringTriggerType() != null) {
                json.put("createGatheringTriggerType", this.request.getCreateGatheringTriggerType());
            }
            if (this.request.getCreateGatheringTriggerRealtimeNamespaceId() != null) {
                json.put("createGatheringTriggerRealtimeNamespaceId", this.request.getCreateGatheringTriggerRealtimeNamespaceId());
            }
            if (this.request.getCreateGatheringTriggerScriptId() != null) {
                json.put("createGatheringTriggerScriptId", this.request.getCreateGatheringTriggerScriptId());
            }
            if (this.request.getCompleteMatchmakingTriggerType() != null) {
                json.put("completeMatchmakingTriggerType", this.request.getCompleteMatchmakingTriggerType());
            }
            if (this.request.getCompleteMatchmakingTriggerRealtimeNamespaceId() != null) {
                json.put("completeMatchmakingTriggerRealtimeNamespaceId", this.request.getCompleteMatchmakingTriggerRealtimeNamespaceId());
            }
            if (this.request.getCompleteMatchmakingTriggerScriptId() != null) {
                json.put("completeMatchmakingTriggerScriptId", this.request.getCompleteMatchmakingTriggerScriptId());
            }
            if (this.request.getJoinNotification() != null) {
                try {
                    json.put("joinNotification", new JSONObject(mapper.writeValueAsString(this.request.getJoinNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getLeaveNotification() != null) {
                try {
                    json.put("leaveNotification", new JSONObject(mapper.writeValueAsString(this.request.getLeaveNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCompleteNotification() != null) {
                try {
                    json.put("completeNotification", new JSONObject(mapper.writeValueAsString(this.request.getCompleteNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getLogSetting() != null) {
                try {
                    json.put("logSetting", new JSONObject(mapper.writeValueAsString(this.request.getLogSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ネームスペースを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createNamespaceAsync(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> callback
    ) {
        CreateNamespaceTask task = new CreateNamespaceTask(request, callback, CreateNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> userCallback,
            Class<GetNamespaceStatusResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ネームスペースの状態を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getNamespaceStatusAsync(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> callback
    ) {
        GetNamespaceStatusTask task = new GetNamespaceStatusTask(request, callback, GetNamespaceStatusResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースの状態を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetNamespaceResult>> userCallback,
            Class<GetNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ネームスペースを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getNamespaceAsync(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> callback
    ) {
        GetNamespaceTask task = new GetNamespaceTask(request, callback, GetNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateNamespaceResult>> userCallback,
            Class<UpdateNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getEnableRating() != null) {
                json.put("enableRating", this.request.getEnableRating());
            }
            if (this.request.getCreateGatheringTriggerType() != null) {
                json.put("createGatheringTriggerType", this.request.getCreateGatheringTriggerType());
            }
            if (this.request.getCreateGatheringTriggerRealtimeNamespaceId() != null) {
                json.put("createGatheringTriggerRealtimeNamespaceId", this.request.getCreateGatheringTriggerRealtimeNamespaceId());
            }
            if (this.request.getCreateGatheringTriggerScriptId() != null) {
                json.put("createGatheringTriggerScriptId", this.request.getCreateGatheringTriggerScriptId());
            }
            if (this.request.getCompleteMatchmakingTriggerType() != null) {
                json.put("completeMatchmakingTriggerType", this.request.getCompleteMatchmakingTriggerType());
            }
            if (this.request.getCompleteMatchmakingTriggerRealtimeNamespaceId() != null) {
                json.put("completeMatchmakingTriggerRealtimeNamespaceId", this.request.getCompleteMatchmakingTriggerRealtimeNamespaceId());
            }
            if (this.request.getCompleteMatchmakingTriggerScriptId() != null) {
                json.put("completeMatchmakingTriggerScriptId", this.request.getCompleteMatchmakingTriggerScriptId());
            }
            if (this.request.getJoinNotification() != null) {
                try {
                    json.put("joinNotification", new JSONObject(mapper.writeValueAsString(this.request.getJoinNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getLeaveNotification() != null) {
                try {
                    json.put("leaveNotification", new JSONObject(mapper.writeValueAsString(this.request.getLeaveNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCompleteNotification() != null) {
                try {
                    json.put("completeNotification", new JSONObject(mapper.writeValueAsString(this.request.getCompleteNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getLogSetting() != null) {
                try {
                    json.put("logSetting", new JSONObject(mapper.writeValueAsString(this.request.getLogSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ネームスペースを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateNamespaceAsync(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> callback
    ) {
        UpdateNamespaceTask task = new UpdateNamespaceTask(request, callback, UpdateNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteNamespaceResult>> userCallback,
            Class<DeleteNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ネームスペースを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteNamespaceAsync(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> callback
    ) {
        DeleteNamespaceTask task = new DeleteNamespaceTask(request, callback, DeleteNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを削除<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeGatheringsTask extends Gs2RestSessionTask<DescribeGatheringsResult> {
        private DescribeGatheringsRequest request;

        public DescribeGatheringsTask(
            DescribeGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeGatheringsResult>> userCallback,
            Class<DescribeGatheringsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ギャザリングの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeGatheringsAsync(
            DescribeGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeGatheringsResult>> callback
    ) {
        DescribeGatheringsTask task = new DescribeGatheringsTask(request, callback, DescribeGatheringsResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeGatheringsResult describeGatherings(
            DescribeGatheringsRequest request
    ) {
        final AsyncResult<DescribeGatheringsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGatheringsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGatheringTask extends Gs2RestSessionTask<CreateGatheringResult> {
        private CreateGatheringRequest request;

        public CreateGatheringTask(
            CreateGatheringRequest request,
            AsyncAction<AsyncResult<CreateGatheringResult>> userCallback,
            Class<CreateGatheringResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPlayer() != null) {
                try {
                    json.put("player", new JSONObject(mapper.writeValueAsString(this.request.getPlayer())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getAttributeRanges() != null) {
                JSONArray array = new JSONArray();
                for(AttributeRange item : this.request.getAttributeRanges())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("attributeRanges", array);
            }
            if (this.request.getCapacityOfRoles() != null) {
                JSONArray array = new JSONArray();
                for(CapacityOfRole item : this.request.getCapacityOfRoles())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("capacityOfRoles", array);
            }
            if (this.request.getAllowUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAllowUserIds())
                {
                    array.put(item);
                }
                json.put("allowUserIds", array);
            }
            if (this.request.getExpiresAt() != null) {
                json.put("expiresAt", this.request.getExpiresAt());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ギャザリングを作成して募集を開始<br>
     *   <br>
     *   `募集条件` には、作成したギャザリングに参加を許可する各属性値の範囲を指定します。<br>
     *   <br>
     *   たとえば、同一ゲームモードを希望するプレイヤーを募集したい場合は、ゲームモードに対応した属性値が完全一致する参加条件プレイヤーとマッチメイキングするように<br>
     *   `属性名：ゲームモード` `属性最小値: ゲームモードを表す数値` `属性最大値: ゲームモードを表す数値`<br>
     *   とすることで、同一ゲームモードを希望するプレイヤー同士をマッチメイキングできます。<br>
     *   <br>
     *   他にレーティングをベースにしたマッチメイキングを実施したい場合は、<br>
     *   ルーム作成者のレーティング値を中心とした属性値の範囲を指定することで、レーティング値の近いプレイヤー同士をマッチメイキングできます。<br>
     *   この `募集条件` はあとで更新することができますので、徐々に条件を緩和していくことができます。<br>
     *   <br>
     *   ロール とは 盾役1人・回復役1人・攻撃役2人 などの役割ごとに募集人数を設定したい場合に使用します。<br>
     *   ロールにはエイリアスを指定できます。<br>
     *   たとえば、盾役は パラディン と ナイト の2種類の `ジョブ` に更に分類できるとします。<br>
     *   この場合、ロール名 に `盾役` エイリアス に `パラディン` `ナイト` として募集を出すようにゲームを実装します。<br>
     *   そして、プレイヤーは自分自身の `ジョブ` を自身のプレイヤー情報のロールに指定します。<br>
     *   <br>
     *   こうすることで、募集条件が `盾役` になっているギャザリングには `パラディン` も `ナイト` も参加できます。<br>
     *   一方で、ギャザリングを作成するときに、 `パラディン` だけ募集したくて、 `ナイト` を募集したくない場合は、<br>
     *   募集するロール名に `パラディン` を直接指定したり、エイリアスに `ナイト` を含めないようにすることで実現できます。<br>
     *   <br>
     *   `参加者` の `募集人数` はプレイヤーの募集人数を指定します。ロール名を指定することで、ロール名ごとの募集人数を設定できます。<br>
     *   <br>
     *   `参加者` の `参加者のプレイヤー情報リスト` には事前にプレイヤー間でパーティを構築している場合や、参加者が離脱したあとの追加募集で使用します。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createGatheringAsync(
            CreateGatheringRequest request,
            AsyncAction<AsyncResult<CreateGatheringResult>> callback
    ) {
        CreateGatheringTask task = new CreateGatheringTask(request, callback, CreateGatheringResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングを作成して募集を開始<br>
     *   <br>
     *   `募集条件` には、作成したギャザリングに参加を許可する各属性値の範囲を指定します。<br>
     *   <br>
     *   たとえば、同一ゲームモードを希望するプレイヤーを募集したい場合は、ゲームモードに対応した属性値が完全一致する参加条件プレイヤーとマッチメイキングするように<br>
     *   `属性名：ゲームモード` `属性最小値: ゲームモードを表す数値` `属性最大値: ゲームモードを表す数値`<br>
     *   とすることで、同一ゲームモードを希望するプレイヤー同士をマッチメイキングできます。<br>
     *   <br>
     *   他にレーティングをベースにしたマッチメイキングを実施したい場合は、<br>
     *   ルーム作成者のレーティング値を中心とした属性値の範囲を指定することで、レーティング値の近いプレイヤー同士をマッチメイキングできます。<br>
     *   この `募集条件` はあとで更新することができますので、徐々に条件を緩和していくことができます。<br>
     *   <br>
     *   ロール とは 盾役1人・回復役1人・攻撃役2人 などの役割ごとに募集人数を設定したい場合に使用します。<br>
     *   ロールにはエイリアスを指定できます。<br>
     *   たとえば、盾役は パラディン と ナイト の2種類の `ジョブ` に更に分類できるとします。<br>
     *   この場合、ロール名 に `盾役` エイリアス に `パラディン` `ナイト` として募集を出すようにゲームを実装します。<br>
     *   そして、プレイヤーは自分自身の `ジョブ` を自身のプレイヤー情報のロールに指定します。<br>
     *   <br>
     *   こうすることで、募集条件が `盾役` になっているギャザリングには `パラディン` も `ナイト` も参加できます。<br>
     *   一方で、ギャザリングを作成するときに、 `パラディン` だけ募集したくて、 `ナイト` を募集したくない場合は、<br>
     *   募集するロール名に `パラディン` を直接指定したり、エイリアスに `ナイト` を含めないようにすることで実現できます。<br>
     *   <br>
     *   `参加者` の `募集人数` はプレイヤーの募集人数を指定します。ロール名を指定することで、ロール名ごとの募集人数を設定できます。<br>
     *   <br>
     *   `参加者` の `参加者のプレイヤー情報リスト` には事前にプレイヤー間でパーティを構築している場合や、参加者が離脱したあとの追加募集で使用します。<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateGatheringResult createGathering(
            CreateGatheringRequest request
    ) {
        final AsyncResult<CreateGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGatheringByUserIdTask extends Gs2RestSessionTask<CreateGatheringByUserIdResult> {
        private CreateGatheringByUserIdRequest request;

        public CreateGatheringByUserIdTask(
            CreateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGatheringByUserIdResult>> userCallback,
            Class<CreateGatheringByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPlayer() != null) {
                try {
                    json.put("player", new JSONObject(mapper.writeValueAsString(this.request.getPlayer())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getAttributeRanges() != null) {
                JSONArray array = new JSONArray();
                for(AttributeRange item : this.request.getAttributeRanges())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("attributeRanges", array);
            }
            if (this.request.getCapacityOfRoles() != null) {
                JSONArray array = new JSONArray();
                for(CapacityOfRole item : this.request.getCapacityOfRoles())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("capacityOfRoles", array);
            }
            if (this.request.getAllowUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAllowUserIds())
                {
                    array.put(item);
                }
                json.put("allowUserIds", array);
            }
            if (this.request.getExpiresAt() != null) {
                json.put("expiresAt", this.request.getExpiresAt());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ギャザリングを作成して募集を開始<br>
     *   <br>
     *   `募集条件` には、作成したギャザリングに参加を許可する各属性値の範囲を指定します。<br>
     *   <br>
     *   たとえば、同一ゲームモードを希望するプレイヤーを募集したい場合は、ゲームモードに対応した属性値が完全一致する参加条件プレイヤーとマッチメイキングするように<br>
     *   `属性名：ゲームモード` `属性最小値: ゲームモードを表す数値` `属性最大値: ゲームモードを表す数値`<br>
     *   とすることで、同一ゲームモードを希望するプレイヤー同士をマッチメイキングできます。<br>
     *   <br>
     *   他にレーティングをベースにしたマッチメイキングを実施したい場合は、<br>
     *   ルーム作成者のレーティング値を中心とした属性値の範囲を指定することで、レーティング値の近いプレイヤー同士をマッチメイキングできます。<br>
     *   この `募集条件` はあとで更新することができますので、徐々に条件を緩和していくことができます。<br>
     *   <br>
     *   ロール とは 盾役1人・回復役1人・攻撃役2人 などの役割ごとに募集人数を設定したい場合に使用します。<br>
     *   ロールにはエイリアスを指定できます。<br>
     *   たとえば、盾役は パラディン と ナイト の2種類の `ジョブ` に更に分類できるとします。<br>
     *   この場合、ロール名 に `盾役` エイリアス に `パラディン` `ナイト` として募集を出すようにゲームを実装します。<br>
     *   そして、プレイヤーは自分自身の `ジョブ` を自身のプレイヤー情報のロールに指定します。<br>
     *   <br>
     *   こうすることで、募集条件が `盾役` になっているギャザリングには `パラディン` も `ナイト` も参加できます。<br>
     *   一方で、ギャザリングを作成するときに、 `パラディン` だけ募集したくて、 `ナイト` を募集したくない場合は、<br>
     *   募集するロール名に `パラディン` を直接指定したり、エイリアスに `ナイト` を含めないようにすることで実現できます。<br>
     *   <br>
     *   `参加者` の `募集人数` はプレイヤーの募集人数を指定します。ロール名を指定することで、ロール名ごとの募集人数を設定できます。<br>
     *   <br>
     *   `参加者` の `参加者のプレイヤー情報リスト` には事前にプレイヤー間でパーティを構築している場合や、参加者が離脱したあとの追加募集で使用します。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createGatheringByUserIdAsync(
            CreateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGatheringByUserIdResult>> callback
    ) {
        CreateGatheringByUserIdTask task = new CreateGatheringByUserIdTask(request, callback, CreateGatheringByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングを作成して募集を開始<br>
     *   <br>
     *   `募集条件` には、作成したギャザリングに参加を許可する各属性値の範囲を指定します。<br>
     *   <br>
     *   たとえば、同一ゲームモードを希望するプレイヤーを募集したい場合は、ゲームモードに対応した属性値が完全一致する参加条件プレイヤーとマッチメイキングするように<br>
     *   `属性名：ゲームモード` `属性最小値: ゲームモードを表す数値` `属性最大値: ゲームモードを表す数値`<br>
     *   とすることで、同一ゲームモードを希望するプレイヤー同士をマッチメイキングできます。<br>
     *   <br>
     *   他にレーティングをベースにしたマッチメイキングを実施したい場合は、<br>
     *   ルーム作成者のレーティング値を中心とした属性値の範囲を指定することで、レーティング値の近いプレイヤー同士をマッチメイキングできます。<br>
     *   この `募集条件` はあとで更新することができますので、徐々に条件を緩和していくことができます。<br>
     *   <br>
     *   ロール とは 盾役1人・回復役1人・攻撃役2人 などの役割ごとに募集人数を設定したい場合に使用します。<br>
     *   ロールにはエイリアスを指定できます。<br>
     *   たとえば、盾役は パラディン と ナイト の2種類の `ジョブ` に更に分類できるとします。<br>
     *   この場合、ロール名 に `盾役` エイリアス に `パラディン` `ナイト` として募集を出すようにゲームを実装します。<br>
     *   そして、プレイヤーは自分自身の `ジョブ` を自身のプレイヤー情報のロールに指定します。<br>
     *   <br>
     *   こうすることで、募集条件が `盾役` になっているギャザリングには `パラディン` も `ナイト` も参加できます。<br>
     *   一方で、ギャザリングを作成するときに、 `パラディン` だけ募集したくて、 `ナイト` を募集したくない場合は、<br>
     *   募集するロール名に `パラディン` を直接指定したり、エイリアスに `ナイト` を含めないようにすることで実現できます。<br>
     *   <br>
     *   `参加者` の `募集人数` はプレイヤーの募集人数を指定します。ロール名を指定することで、ロール名ごとの募集人数を設定できます。<br>
     *   <br>
     *   `参加者` の `参加者のプレイヤー情報リスト` には事前にプレイヤー間でパーティを構築している場合や、参加者が離脱したあとの追加募集で使用します。<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateGatheringByUserIdResult createGatheringByUserId(
            CreateGatheringByUserIdRequest request
    ) {
        final AsyncResult<CreateGatheringByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGatheringByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateGatheringTask extends Gs2RestSessionTask<UpdateGatheringResult> {
        private UpdateGatheringRequest request;

        public UpdateGatheringTask(
            UpdateGatheringRequest request,
            AsyncAction<AsyncResult<UpdateGatheringResult>> userCallback,
            Class<UpdateGatheringResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAttributeRanges() != null) {
                JSONArray array = new JSONArray();
                for(AttributeRange item : this.request.getAttributeRanges())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("attributeRanges", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ギャザリングを更新する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateGatheringAsync(
            UpdateGatheringRequest request,
            AsyncAction<AsyncResult<UpdateGatheringResult>> callback
    ) {
        UpdateGatheringTask task = new UpdateGatheringTask(request, callback, UpdateGatheringResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングを更新する<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateGatheringResult updateGathering(
            UpdateGatheringRequest request
    ) {
        final AsyncResult<UpdateGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateGatheringByUserIdTask extends Gs2RestSessionTask<UpdateGatheringByUserIdResult> {
        private UpdateGatheringByUserIdRequest request;

        public UpdateGatheringByUserIdTask(
            UpdateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateGatheringByUserIdResult>> userCallback,
            Class<UpdateGatheringByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAttributeRanges() != null) {
                JSONArray array = new JSONArray();
                for(AttributeRange item : this.request.getAttributeRanges())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("attributeRanges", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ギャザリングを更新する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateGatheringByUserIdAsync(
            UpdateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateGatheringByUserIdResult>> callback
    ) {
        UpdateGatheringByUserIdTask task = new UpdateGatheringByUserIdTask(request, callback, UpdateGatheringByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングを更新する<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateGatheringByUserIdResult updateGatheringByUserId(
            UpdateGatheringByUserIdRequest request
    ) {
        final AsyncResult<UpdateGatheringByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGatheringByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoMatchmakingByPlayerTask extends Gs2RestSessionTask<DoMatchmakingByPlayerResult> {
        private DoMatchmakingByPlayerRequest request;

        public DoMatchmakingByPlayerTask(
            DoMatchmakingByPlayerRequest request,
            AsyncAction<AsyncResult<DoMatchmakingByPlayerResult>> userCallback,
            Class<DoMatchmakingByPlayerResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/player/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPlayer() != null) {
                try {
                    json.put("player", new JSONObject(mapper.writeValueAsString(this.request.getPlayer())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getMatchmakingContextToken() != null) {
                json.put("matchmakingContextToken", this.request.getMatchmakingContextToken());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * Player が参加できるギャザリングを探して参加する<br>
     *   <br>
     *   一定時間 検索を行い、対象が見つからなかったときには `マッチメイキングの状態を保持するトークン` を返す。<br>
     *   次回 `マッチメイキングの状態を保持するトークン` をつけて再度リクエストを出すことで、前回の続きから検索処理を再開できる。<br>
     *   すべてのギャザリングを検索したが、参加できるギャザリングが存在しなかった場合はギャザリングもトークンもどちらも null が応答される。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void doMatchmakingByPlayerAsync(
            DoMatchmakingByPlayerRequest request,
            AsyncAction<AsyncResult<DoMatchmakingByPlayerResult>> callback
    ) {
        DoMatchmakingByPlayerTask task = new DoMatchmakingByPlayerTask(request, callback, DoMatchmakingByPlayerResult.class);
        session.execute(task);
    }

    /**
     * Player が参加できるギャザリングを探して参加する<br>
     *   <br>
     *   一定時間 検索を行い、対象が見つからなかったときには `マッチメイキングの状態を保持するトークン` を返す。<br>
     *   次回 `マッチメイキングの状態を保持するトークン` をつけて再度リクエストを出すことで、前回の続きから検索処理を再開できる。<br>
     *   すべてのギャザリングを検索したが、参加できるギャザリングが存在しなかった場合はギャザリングもトークンもどちらも null が応答される。<br>
     *
     * @param request リクエストパラメータ
     */
    public DoMatchmakingByPlayerResult doMatchmakingByPlayer(
            DoMatchmakingByPlayerRequest request
    ) {
        final AsyncResult<DoMatchmakingByPlayerResult>[] resultAsyncResult = new AsyncResult[]{null};
        doMatchmakingByPlayerAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoMatchmakingTask extends Gs2RestSessionTask<DoMatchmakingResult> {
        private DoMatchmakingRequest request;

        public DoMatchmakingTask(
            DoMatchmakingRequest request,
            AsyncAction<AsyncResult<DoMatchmakingResult>> userCallback,
            Class<DoMatchmakingResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPlayer() != null) {
                try {
                    json.put("player", new JSONObject(mapper.writeValueAsString(this.request.getPlayer())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getMatchmakingContextToken() != null) {
                json.put("matchmakingContextToken", this.request.getMatchmakingContextToken());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 自分が参加できるギャザリングを探して参加する<br>
     *   <br>
     *   一定時間 検索を行い、対象が見つからなかったときには `マッチメイキングの状態を保持するトークン` を返す。<br>
     *   次回 `マッチメイキングの状態を保持するトークン` をつけて再度リクエストを出すことで、前回の続きから検索処理を再開できる。<br>
     *   すべてのギャザリングを検索したが、参加できるギャザリングが存在しなかった場合はギャザリングもトークンもどちらも null が応答される。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void doMatchmakingAsync(
            DoMatchmakingRequest request,
            AsyncAction<AsyncResult<DoMatchmakingResult>> callback
    ) {
        DoMatchmakingTask task = new DoMatchmakingTask(request, callback, DoMatchmakingResult.class);
        session.execute(task);
    }

    /**
     * 自分が参加できるギャザリングを探して参加する<br>
     *   <br>
     *   一定時間 検索を行い、対象が見つからなかったときには `マッチメイキングの状態を保持するトークン` を返す。<br>
     *   次回 `マッチメイキングの状態を保持するトークン` をつけて再度リクエストを出すことで、前回の続きから検索処理を再開できる。<br>
     *   すべてのギャザリングを検索したが、参加できるギャザリングが存在しなかった場合はギャザリングもトークンもどちらも null が応答される。<br>
     *
     * @param request リクエストパラメータ
     */
    public DoMatchmakingResult doMatchmaking(
            DoMatchmakingRequest request
    ) {
        final AsyncResult<DoMatchmakingResult>[] resultAsyncResult = new AsyncResult[]{null};
        doMatchmakingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGatheringTask extends Gs2RestSessionTask<GetGatheringResult> {
        private GetGatheringRequest request;

        public GetGatheringTask(
            GetGatheringRequest request,
            AsyncAction<AsyncResult<GetGatheringResult>> userCallback,
            Class<GetGatheringResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

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

    /**
     * ギャザリングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getGatheringAsync(
            GetGatheringRequest request,
            AsyncAction<AsyncResult<GetGatheringResult>> callback
    ) {
        GetGatheringTask task = new GetGatheringTask(request, callback, GetGatheringResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetGatheringResult getGathering(
            GetGatheringRequest request
    ) {
        final AsyncResult<GetGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CancelMatchmakingTask extends Gs2RestSessionTask<CancelMatchmakingResult> {
        private CancelMatchmakingRequest request;

        public CancelMatchmakingTask(
            CancelMatchmakingRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingResult>> userCallback,
            Class<CancelMatchmakingResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

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

    /**
     * マッチメイキングをキャンセルする<br>
     *   <br>
     *   ギャザリングから離脱する前にマッチメイキングが完了した場合は、NotFoundException(404エラー) が発生し失敗します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void cancelMatchmakingAsync(
            CancelMatchmakingRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingResult>> callback
    ) {
        CancelMatchmakingTask task = new CancelMatchmakingTask(request, callback, CancelMatchmakingResult.class);
        session.execute(task);
    }

    /**
     * マッチメイキングをキャンセルする<br>
     *   <br>
     *   ギャザリングから離脱する前にマッチメイキングが完了した場合は、NotFoundException(404エラー) が発生し失敗します<br>
     *
     * @param request リクエストパラメータ
     */
    public CancelMatchmakingResult cancelMatchmaking(
            CancelMatchmakingRequest request
    ) {
        final AsyncResult<CancelMatchmakingResult>[] resultAsyncResult = new AsyncResult[]{null};
        cancelMatchmakingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CancelMatchmakingByUserIdTask extends Gs2RestSessionTask<CancelMatchmakingByUserIdResult> {
        private CancelMatchmakingByUserIdRequest request;

        public CancelMatchmakingByUserIdTask(
            CancelMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingByUserIdResult>> userCallback,
            Class<CancelMatchmakingByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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

    /**
     * ユーザIDを指定してマッチメイキングをキャンセルする<br>
     *   <br>
     *   ギャザリングから離脱する前にマッチメイキングが完了した場合は、NotFoundException(404エラー) が発生し失敗します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void cancelMatchmakingByUserIdAsync(
            CancelMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingByUserIdResult>> callback
    ) {
        CancelMatchmakingByUserIdTask task = new CancelMatchmakingByUserIdTask(request, callback, CancelMatchmakingByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してマッチメイキングをキャンセルする<br>
     *   <br>
     *   ギャザリングから離脱する前にマッチメイキングが完了した場合は、NotFoundException(404エラー) が発生し失敗します<br>
     *
     * @param request リクエストパラメータ
     */
    public CancelMatchmakingByUserIdResult cancelMatchmakingByUserId(
            CancelMatchmakingByUserIdRequest request
    ) {
        final AsyncResult<CancelMatchmakingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        cancelMatchmakingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteGatheringTask extends Gs2RestSessionTask<DeleteGatheringResult> {
        private DeleteGatheringRequest request;

        public DeleteGatheringTask(
            DeleteGatheringRequest request,
            AsyncAction<AsyncResult<DeleteGatheringResult>> userCallback,
            Class<DeleteGatheringResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

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

    /**
     * ギャザリングを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteGatheringAsync(
            DeleteGatheringRequest request,
            AsyncAction<AsyncResult<DeleteGatheringResult>> callback
    ) {
        DeleteGatheringTask task = new DeleteGatheringTask(request, callback, DeleteGatheringResult.class);
        session.execute(task);
    }

    /**
     * ギャザリングを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteGatheringResult deleteGathering(
            DeleteGatheringRequest request
    ) {
        final AsyncResult<DeleteGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingModelMastersTask extends Gs2RestSessionTask<DescribeRatingModelMastersResult> {
        private DescribeRatingModelMastersRequest request;

        public DescribeRatingModelMastersTask(
            DescribeRatingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelMastersResult>> userCallback,
            Class<DescribeRatingModelMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * レーティングモデルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRatingModelMastersAsync(
            DescribeRatingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelMastersResult>> callback
    ) {
        DescribeRatingModelMastersTask task = new DescribeRatingModelMastersTask(request, callback, DescribeRatingModelMastersResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRatingModelMastersResult describeRatingModelMasters(
            DescribeRatingModelMastersRequest request
    ) {
        final AsyncResult<DescribeRatingModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRatingModelMasterTask extends Gs2RestSessionTask<CreateRatingModelMasterResult> {
        private CreateRatingModelMasterRequest request;

        public CreateRatingModelMasterTask(
            CreateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRatingModelMasterResult>> userCallback,
            Class<CreateRatingModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getVolatility() != null) {
                json.put("volatility", this.request.getVolatility());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * レーティングモデルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createRatingModelMasterAsync(
            CreateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRatingModelMasterResult>> callback
    ) {
        CreateRatingModelMasterTask task = new CreateRatingModelMasterTask(request, callback, CreateRatingModelMasterResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateRatingModelMasterResult createRatingModelMaster(
            CreateRatingModelMasterRequest request
    ) {
        final AsyncResult<CreateRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingModelMasterTask extends Gs2RestSessionTask<GetRatingModelMasterResult> {
        private GetRatingModelMasterRequest request;

        public GetRatingModelMasterTask(
            GetRatingModelMasterRequest request,
            AsyncAction<AsyncResult<GetRatingModelMasterResult>> userCallback,
            Class<GetRatingModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

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

    /**
     * レーティングモデルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRatingModelMasterAsync(
            GetRatingModelMasterRequest request,
            AsyncAction<AsyncResult<GetRatingModelMasterResult>> callback
    ) {
        GetRatingModelMasterTask task = new GetRatingModelMasterTask(request, callback, GetRatingModelMasterResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRatingModelMasterResult getRatingModelMaster(
            GetRatingModelMasterRequest request
    ) {
        final AsyncResult<GetRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRatingModelMasterTask extends Gs2RestSessionTask<UpdateRatingModelMasterResult> {
        private UpdateRatingModelMasterRequest request;

        public UpdateRatingModelMasterTask(
            UpdateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRatingModelMasterResult>> userCallback,
            Class<UpdateRatingModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getVolatility() != null) {
                json.put("volatility", this.request.getVolatility());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * レーティングモデルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateRatingModelMasterAsync(
            UpdateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRatingModelMasterResult>> callback
    ) {
        UpdateRatingModelMasterTask task = new UpdateRatingModelMasterTask(request, callback, UpdateRatingModelMasterResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateRatingModelMasterResult updateRatingModelMaster(
            UpdateRatingModelMasterRequest request
    ) {
        final AsyncResult<UpdateRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRatingModelMasterTask extends Gs2RestSessionTask<DeleteRatingModelMasterResult> {
        private DeleteRatingModelMasterRequest request;

        public DeleteRatingModelMasterTask(
            DeleteRatingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRatingModelMasterResult>> userCallback,
            Class<DeleteRatingModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

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

    /**
     * レーティングモデルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRatingModelMasterAsync(
            DeleteRatingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRatingModelMasterResult>> callback
    ) {
        DeleteRatingModelMasterTask task = new DeleteRatingModelMasterTask(request, callback, DeleteRatingModelMasterResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteRatingModelMasterResult deleteRatingModelMaster(
            DeleteRatingModelMasterRequest request
    ) {
        final AsyncResult<DeleteRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingModelsTask extends Gs2RestSessionTask<DescribeRatingModelsResult> {
        private DescribeRatingModelsRequest request;

        public DescribeRatingModelsTask(
            DescribeRatingModelsRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelsResult>> userCallback,
            Class<DescribeRatingModelsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * レーティングモデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRatingModelsAsync(
            DescribeRatingModelsRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelsResult>> callback
    ) {
        DescribeRatingModelsTask task = new DescribeRatingModelsTask(request, callback, DescribeRatingModelsResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRatingModelsResult describeRatingModels(
            DescribeRatingModelsRequest request
    ) {
        final AsyncResult<DescribeRatingModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingModelTask extends Gs2RestSessionTask<GetRatingModelResult> {
        private GetRatingModelRequest request;

        public GetRatingModelTask(
            GetRatingModelRequest request,
            AsyncAction<AsyncResult<GetRatingModelResult>> userCallback,
            Class<GetRatingModelResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

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

    /**
     * レーティングモデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRatingModelAsync(
            GetRatingModelRequest request,
            AsyncAction<AsyncResult<GetRatingModelResult>> callback
    ) {
        GetRatingModelTask task = new GetRatingModelTask(request, callback, GetRatingModelResult.class);
        session.execute(task);
    }

    /**
     * レーティングモデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRatingModelResult getRatingModel(
            GetRatingModelRequest request
    ) {
        final AsyncResult<GetRatingModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            AsyncAction<AsyncResult<ExportMasterResult>> userCallback,
            Class<ExportMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/export";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * 現在有効なレーティングマスターのマスターデータをエクスポートします<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void exportMasterAsync(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> callback
    ) {
        ExportMasterTask task = new ExportMasterTask(request, callback, ExportMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なレーティングマスターのマスターデータをエクスポートします<br>
     *
     * @param request リクエストパラメータ
     */
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

    class GetCurrentRatingModelMasterTask extends Gs2RestSessionTask<GetCurrentRatingModelMasterResult> {
        private GetCurrentRatingModelMasterRequest request;

        public GetCurrentRatingModelMasterTask(
            GetCurrentRatingModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRatingModelMasterResult>> userCallback,
            Class<GetCurrentRatingModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * 現在有効なレーティングマスターを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentRatingModelMasterAsync(
            GetCurrentRatingModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRatingModelMasterResult>> callback
    ) {
        GetCurrentRatingModelMasterTask task = new GetCurrentRatingModelMasterTask(request, callback, GetCurrentRatingModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なレーティングマスターを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentRatingModelMasterResult getCurrentRatingModelMaster(
            GetCurrentRatingModelMasterRequest request
    ) {
        final AsyncResult<GetCurrentRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRatingModelMasterTask extends Gs2RestSessionTask<UpdateCurrentRatingModelMasterResult> {
        private UpdateCurrentRatingModelMasterRequest request;

        public UpdateCurrentRatingModelMasterTask(
            UpdateCurrentRatingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRatingModelMasterResult>> userCallback,
            Class<UpdateCurrentRatingModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSettings() != null) {
                json.put("settings", this.request.getSettings());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 現在有効なレーティングマスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentRatingModelMasterAsync(
            UpdateCurrentRatingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRatingModelMasterResult>> callback
    ) {
        UpdateCurrentRatingModelMasterTask task = new UpdateCurrentRatingModelMasterTask(request, callback, UpdateCurrentRatingModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なレーティングマスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentRatingModelMasterResult updateCurrentRatingModelMaster(
            UpdateCurrentRatingModelMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRatingModelMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentRatingModelMasterFromGitHubResult> {
        private UpdateCurrentRatingModelMasterFromGitHubRequest request;

        public UpdateCurrentRatingModelMasterFromGitHubTask(
            UpdateCurrentRatingModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRatingModelMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentRatingModelMasterFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/from_git_hub";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCheckoutSetting() != null) {
                try {
                    json.put("checkoutSetting", new JSONObject(mapper.writeValueAsString(this.request.getCheckoutSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 現在有効なレーティングマスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentRatingModelMasterFromGitHubAsync(
            UpdateCurrentRatingModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRatingModelMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentRatingModelMasterFromGitHubTask task = new UpdateCurrentRatingModelMasterFromGitHubTask(request, callback, UpdateCurrentRatingModelMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なレーティングマスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentRatingModelMasterFromGitHubResult updateCurrentRatingModelMasterFromGitHub(
            UpdateCurrentRatingModelMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentRatingModelMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRatingModelMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingsTask extends Gs2RestSessionTask<DescribeRatingsResult> {
        private DescribeRatingsRequest request;

        public DescribeRatingsTask(
            DescribeRatingsRequest request,
            AsyncAction<AsyncResult<DescribeRatingsResult>> userCallback,
            Class<DescribeRatingsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * レーティングの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRatingsAsync(
            DescribeRatingsRequest request,
            AsyncAction<AsyncResult<DescribeRatingsResult>> callback
    ) {
        DescribeRatingsTask task = new DescribeRatingsTask(request, callback, DescribeRatingsResult.class);
        session.execute(task);
    }

    /**
     * レーティングの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRatingsResult describeRatings(
            DescribeRatingsRequest request
    ) {
        final AsyncResult<DescribeRatingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingsByUserIdTask extends Gs2RestSessionTask<DescribeRatingsByUserIdResult> {
        private DescribeRatingsByUserIdRequest request;

        public DescribeRatingsByUserIdTask(
            DescribeRatingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRatingsByUserIdResult>> userCallback,
            Class<DescribeRatingsByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * ユーザIDを指定してレーティングの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRatingsByUserIdAsync(
            DescribeRatingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRatingsByUserIdResult>> callback
    ) {
        DescribeRatingsByUserIdTask task = new DescribeRatingsByUserIdTask(request, callback, DescribeRatingsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してレーティングの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRatingsByUserIdResult describeRatingsByUserId(
            DescribeRatingsByUserIdRequest request
    ) {
        final AsyncResult<DescribeRatingsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingTask extends Gs2RestSessionTask<GetRatingResult> {
        private GetRatingRequest request;

        public GetRatingTask(
            GetRatingRequest request,
            AsyncAction<AsyncResult<GetRatingResult>> userCallback,
            Class<GetRatingResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * レーティングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRatingAsync(
            GetRatingRequest request,
            AsyncAction<AsyncResult<GetRatingResult>> callback
    ) {
        GetRatingTask task = new GetRatingTask(request, callback, GetRatingResult.class);
        session.execute(task);
    }

    /**
     * レーティングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRatingResult getRating(
            GetRatingRequest request
    ) {
        final AsyncResult<GetRatingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingByUserIdTask extends Gs2RestSessionTask<GetRatingByUserIdResult> {
        private GetRatingByUserIdRequest request;

        public GetRatingByUserIdTask(
            GetRatingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRatingByUserIdResult>> userCallback,
            Class<GetRatingByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

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

    /**
     * レーティングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRatingByUserIdAsync(
            GetRatingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRatingByUserIdResult>> callback
    ) {
        GetRatingByUserIdTask task = new GetRatingByUserIdTask(request, callback, GetRatingByUserIdResult.class);
        session.execute(task);
    }

    /**
     * レーティングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRatingByUserIdResult getRatingByUserId(
            GetRatingByUserIdRequest request
    ) {
        final AsyncResult<GetRatingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutResultTask extends Gs2RestSessionTask<PutResultResult> {
        private PutResultRequest request;

        public PutResultTask(
            PutResultRequest request,
            AsyncAction<AsyncResult<PutResultResult>> userCallback,
            Class<PutResultResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/rating/{ratingName}/vote";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getGameResults() != null) {
                JSONArray array = new JSONArray();
                for(GameResult item : this.request.getGameResults())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("gameResults", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * レーティング値の再計算を実行<br>
     *   <br>
     *   レーティングの計算処理には Glicko-2 rating system をベースとした計算アルゴリズムを採用しています。<br>
     *   レーティング値の初期値は1500で、レーティングの値が離れた相手に勝利するほど上昇幅は大きく、同じく負けた側は減少幅は大きくなります。<br>
     *   <br>
     *   レーティングの計算には参加したユーザIDのリストが必要となります。<br>
     *   そのため、クライアントから直接このAPIを呼び出すのは適切ではありません。ゲームの勝敗を判断できるゲームサーバから呼び出すようにしてください。<br>
     *   P2P 対戦など、クライアント主導で対戦を実現している場合は、投票機能を利用して勝敗を決定するようにしてください。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void putResultAsync(
            PutResultRequest request,
            AsyncAction<AsyncResult<PutResultResult>> callback
    ) {
        PutResultTask task = new PutResultTask(request, callback, PutResultResult.class);
        session.execute(task);
    }

    /**
     * レーティング値の再計算を実行<br>
     *   <br>
     *   レーティングの計算処理には Glicko-2 rating system をベースとした計算アルゴリズムを採用しています。<br>
     *   レーティング値の初期値は1500で、レーティングの値が離れた相手に勝利するほど上昇幅は大きく、同じく負けた側は減少幅は大きくなります。<br>
     *   <br>
     *   レーティングの計算には参加したユーザIDのリストが必要となります。<br>
     *   そのため、クライアントから直接このAPIを呼び出すのは適切ではありません。ゲームの勝敗を判断できるゲームサーバから呼び出すようにしてください。<br>
     *   P2P 対戦など、クライアント主導で対戦を実現している場合は、投票機能を利用して勝敗を決定するようにしてください。<br>
     *
     * @param request リクエストパラメータ
     */
    public PutResultResult putResult(
            PutResultRequest request
    ) {
        final AsyncResult<PutResultResult>[] resultAsyncResult = new AsyncResult[]{null};
        putResultAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRatingTask extends Gs2RestSessionTask<DeleteRatingResult> {
        private DeleteRatingRequest request;

        public DeleteRatingTask(
            DeleteRatingRequest request,
            AsyncAction<AsyncResult<DeleteRatingResult>> userCallback,
            Class<DeleteRatingResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

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

    /**
     * レーティングを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRatingAsync(
            DeleteRatingRequest request,
            AsyncAction<AsyncResult<DeleteRatingResult>> callback
    ) {
        DeleteRatingTask task = new DeleteRatingTask(request, callback, DeleteRatingResult.class);
        session.execute(task);
    }

    /**
     * レーティングを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteRatingResult deleteRating(
            DeleteRatingRequest request
    ) {
        final AsyncResult<DeleteRatingResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRatingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBallotTask extends Gs2RestSessionTask<GetBallotResult> {
        private GetBallotRequest request;

        public GetBallotTask(
            GetBallotRequest request,
            AsyncAction<AsyncResult<GetBallotResult>> userCallback,
            Class<GetBallotResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/vote/{ratingName}/{gatheringName}/ballot";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNumberOfPlayer() != null) {
                json.put("numberOfPlayer", this.request.getNumberOfPlayer());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 投票用紙を取得します。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getBallotAsync(
            GetBallotRequest request,
            AsyncAction<AsyncResult<GetBallotResult>> callback
    ) {
        GetBallotTask task = new GetBallotTask(request, callback, GetBallotResult.class);
        session.execute(task);
    }

    /**
     * 投票用紙を取得します。<br>
     *
     * @param request リクエストパラメータ
     */
    public GetBallotResult getBallot(
            GetBallotRequest request
    ) {
        final AsyncResult<GetBallotResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBallotAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBallotByUserIdTask extends Gs2RestSessionTask<GetBallotByUserIdResult> {
        private GetBallotByUserIdRequest request;

        public GetBallotByUserIdTask(
            GetBallotByUserIdRequest request,
            AsyncAction<AsyncResult<GetBallotByUserIdResult>> userCallback,
            Class<GetBallotByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/vote/{ratingName}/{gatheringName}/ballot";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNumberOfPlayer() != null) {
                json.put("numberOfPlayer", this.request.getNumberOfPlayer());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 投票用紙を取得します。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getBallotByUserIdAsync(
            GetBallotByUserIdRequest request,
            AsyncAction<AsyncResult<GetBallotByUserIdResult>> callback
    ) {
        GetBallotByUserIdTask task = new GetBallotByUserIdTask(request, callback, GetBallotByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 投票用紙を取得します。<br>
     *
     * @param request リクエストパラメータ
     */
    public GetBallotByUserIdResult getBallotByUserId(
            GetBallotByUserIdRequest request
    ) {
        final AsyncResult<GetBallotByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBallotByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VoteTask extends Gs2RestSessionTask<VoteResult> {
        private VoteRequest request;

        public VoteTask(
            VoteRequest request,
            AsyncAction<AsyncResult<VoteResult>> userCallback,
            Class<VoteResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/action/vote";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getBallotBody() != null) {
                json.put("ballotBody", this.request.getBallotBody());
            }
            if (this.request.getBallotSignature() != null) {
                json.put("ballotSignature", this.request.getBallotSignature());
            }
            if (this.request.getGameResults() != null) {
                JSONArray array = new JSONArray();
                for(GameResult item : this.request.getGameResults())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("gameResults", array);
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 対戦結果を投票します。<br>
     *   <br>
     *   投票は最初の投票が行われてから5分以内に行う必要があります。<br>
     *   つまり、結果は即座に反映されず、投票開始からおよそ5分後または全てのプレイヤーが投票を行った際に結果が反映されます。<br>
     *   5分以内に全ての投票用紙を回収できなかった場合はその時点の投票内容で多数決をとって結果を決定します。<br>
     *   各結果の投票数が同一だった場合は結果は捨てられます（スクリプトで挙動を変更可）。<br>
     *   <br>
     *   結果を即座に反映したい場合は、勝利した側の代表プレイヤーが投票用紙を各プレイヤーから集めて voteMultiple を呼び出すことで結果を即座に反映できます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void voteAsync(
            VoteRequest request,
            AsyncAction<AsyncResult<VoteResult>> callback
    ) {
        VoteTask task = new VoteTask(request, callback, VoteResult.class);
        session.execute(task);
    }

    /**
     * 対戦結果を投票します。<br>
     *   <br>
     *   投票は最初の投票が行われてから5分以内に行う必要があります。<br>
     *   つまり、結果は即座に反映されず、投票開始からおよそ5分後または全てのプレイヤーが投票を行った際に結果が反映されます。<br>
     *   5分以内に全ての投票用紙を回収できなかった場合はその時点の投票内容で多数決をとって結果を決定します。<br>
     *   各結果の投票数が同一だった場合は結果は捨てられます（スクリプトで挙動を変更可）。<br>
     *   <br>
     *   結果を即座に反映したい場合は、勝利した側の代表プレイヤーが投票用紙を各プレイヤーから集めて voteMultiple を呼び出すことで結果を即座に反映できます。<br>
     *
     * @param request リクエストパラメータ
     */
    public VoteResult vote(
            VoteRequest request
    ) {
        final AsyncResult<VoteResult>[] resultAsyncResult = new AsyncResult[]{null};
        voteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VoteMultipleTask extends Gs2RestSessionTask<VoteMultipleResult> {
        private VoteMultipleRequest request;

        public VoteMultipleTask(
            VoteMultipleRequest request,
            AsyncAction<AsyncResult<VoteMultipleResult>> userCallback,
            Class<VoteMultipleResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/action/vote/multiple";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSignedBallots() != null) {
                JSONArray array = new JSONArray();
                for(SignedBallot item : this.request.getSignedBallots())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("signedBallots", array);
            }
            if (this.request.getGameResults() != null) {
                JSONArray array = new JSONArray();
                for(GameResult item : this.request.getGameResults())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("gameResults", array);
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 対戦結果をまとめて投票します。<br>
     *   <br>
     *   ゲームに勝利した側が他プレイヤーの投票用紙を集めてまとめて投票するのに使用します。<br>
     *   『勝利した側』としているのは、敗北した側が自分たちが勝ったことにして報告することにインセンティブはありますが、その逆はないためです。<br>
     *   負けた側が投票用紙を渡してこない可能性がありますが、その場合も過半数の投票用紙があれば結果を通すことができます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void voteMultipleAsync(
            VoteMultipleRequest request,
            AsyncAction<AsyncResult<VoteMultipleResult>> callback
    ) {
        VoteMultipleTask task = new VoteMultipleTask(request, callback, VoteMultipleResult.class);
        session.execute(task);
    }

    /**
     * 対戦結果をまとめて投票します。<br>
     *   <br>
     *   ゲームに勝利した側が他プレイヤーの投票用紙を集めてまとめて投票するのに使用します。<br>
     *   『勝利した側』としているのは、敗北した側が自分たちが勝ったことにして報告することにインセンティブはありますが、その逆はないためです。<br>
     *   負けた側が投票用紙を渡してこない可能性がありますが、その場合も過半数の投票用紙があれば結果を通すことができます。<br>
     *
     * @param request リクエストパラメータ
     */
    public VoteMultipleResult voteMultiple(
            VoteMultipleRequest request
    ) {
        final AsyncResult<VoteMultipleResult>[] resultAsyncResult = new AsyncResult[]{null};
        voteMultipleAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CommitVoteTask extends Gs2RestSessionTask<CommitVoteResult> {
        private CommitVoteRequest request;

        public CommitVoteTask(
            CommitVoteRequest request,
            AsyncAction<AsyncResult<CommitVoteResult>> userCallback,
            Class<CommitVoteResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/vote/{ratingName}/{gatheringName}/action/vote/commit";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null|| this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null|| this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 投票状況を強制確定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void commitVoteAsync(
            CommitVoteRequest request,
            AsyncAction<AsyncResult<CommitVoteResult>> callback
    ) {
        CommitVoteTask task = new CommitVoteTask(request, callback, CommitVoteResult.class);
        session.execute(task);
    }

    /**
     * 投票状況を強制確定<br>
     *
     * @param request リクエストパラメータ
     */
    public CommitVoteResult commitVote(
            CommitVoteRequest request
    ) {
        final AsyncResult<CommitVoteResult>[] resultAsyncResult = new AsyncResult[]{null};
        commitVoteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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