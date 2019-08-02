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

import io.gs2.model.AsyncAction;
import io.gs2.model.AsyncResult;
import io.gs2.exception.*;
import io.gs2.net.*;
import io.gs2.util.EncodingUtil;

import io.gs2.AbstractGs2Client;
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
}