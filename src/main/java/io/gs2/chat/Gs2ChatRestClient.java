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

package io.gs2.chat;

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
import io.gs2.chat.request.*;
import io.gs2.chat.result.*;
import io.gs2.chat.model.*;

/**
 * GS2 Chat API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ChatRestClient extends AbstractGs2Client<Gs2ChatRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2ChatRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "chat")
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
                .replace("{service}", "chat")
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
            if (this.request.getAllowCreateRoom() != null) {
                json.put("allowCreateRoom", this.request.getAllowCreateRoom());
            }
            if (this.request.getPostMessageScript() != null) {
                try {
                    json.put("postMessageScript", new JSONObject(mapper.writeValueAsString(this.request.getPostMessageScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCreateRoomScript() != null) {
                try {
                    json.put("createRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getCreateRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDeleteRoomScript() != null) {
                try {
                    json.put("deleteRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getDeleteRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getSubscribeRoomScript() != null) {
                try {
                    json.put("subscribeRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getSubscribeRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUnsubscribeRoomScript() != null) {
                try {
                    json.put("unsubscribeRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getUnsubscribeRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getPostNotification() != null) {
                try {
                    json.put("postNotification", new JSONObject(mapper.writeValueAsString(this.request.getPostNotification())));
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
                .replace("{service}", "chat")
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
     * ネームスペースを取得<br>
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
     * ネームスペースを取得<br>
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
                .replace("{service}", "chat")
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getAllowCreateRoom() != null) {
                json.put("allowCreateRoom", this.request.getAllowCreateRoom());
            }
            if (this.request.getPostMessageScript() != null) {
                try {
                    json.put("postMessageScript", new JSONObject(mapper.writeValueAsString(this.request.getPostMessageScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCreateRoomScript() != null) {
                try {
                    json.put("createRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getCreateRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDeleteRoomScript() != null) {
                try {
                    json.put("deleteRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getDeleteRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getSubscribeRoomScript() != null) {
                try {
                    json.put("subscribeRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getSubscribeRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUnsubscribeRoomScript() != null) {
                try {
                    json.put("unsubscribeRoomScript", new JSONObject(mapper.writeValueAsString(this.request.getUnsubscribeRoomScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getPostNotification() != null) {
                try {
                    json.put("postNotification", new JSONObject(mapper.writeValueAsString(this.request.getPostNotification())));
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
                .replace("{service}", "chat")
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

    class DescribeRoomsTask extends Gs2RestSessionTask<DescribeRoomsResult> {
        private DescribeRoomsRequest request;

        public DescribeRoomsTask(
            DescribeRoomsRequest request,
            AsyncAction<AsyncResult<DescribeRoomsResult>> userCallback,
            Class<DescribeRoomsResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room";

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
     * ルームの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRoomsAsync(
            DescribeRoomsRequest request,
            AsyncAction<AsyncResult<DescribeRoomsResult>> callback
    ) {
        DescribeRoomsTask task = new DescribeRoomsTask(request, callback, DescribeRoomsResult.class);
        session.execute(task);
    }

    /**
     * ルームの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRoomsResult describeRooms(
            DescribeRoomsRequest request
    ) {
        final AsyncResult<DescribeRoomsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRoomsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRoomTask extends Gs2RestSessionTask<CreateRoomResult> {
        private CreateRoomRequest request;

        public CreateRoomTask(
            CreateRoomRequest request,
            AsyncAction<AsyncResult<CreateRoomResult>> userCallback,
            Class<CreateRoomResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
            }
            if (this.request.getWhiteListUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getWhiteListUserIds())
                {
                    array.put(item);
                }
                json.put("whiteListUserIds", array);
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
     * ルームを作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createRoomAsync(
            CreateRoomRequest request,
            AsyncAction<AsyncResult<CreateRoomResult>> callback
    ) {
        CreateRoomTask task = new CreateRoomTask(request, callback, CreateRoomResult.class);
        session.execute(task);
    }

    /**
     * ルームを作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateRoomResult createRoom(
            CreateRoomRequest request
    ) {
        final AsyncResult<CreateRoomResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRoomAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRoomFromBackendTask extends Gs2RestSessionTask<CreateRoomFromBackendResult> {
        private CreateRoomFromBackendRequest request;

        public CreateRoomFromBackendTask(
            CreateRoomFromBackendRequest request,
            AsyncAction<AsyncResult<CreateRoomFromBackendResult>> userCallback,
            Class<CreateRoomFromBackendResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getUserId() != null) {
                json.put("userId", this.request.getUserId());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
            }
            if (this.request.getWhiteListUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getWhiteListUserIds())
                {
                    array.put(item);
                }
                json.put("whiteListUserIds", array);
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
     * ルームを作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createRoomFromBackendAsync(
            CreateRoomFromBackendRequest request,
            AsyncAction<AsyncResult<CreateRoomFromBackendResult>> callback
    ) {
        CreateRoomFromBackendTask task = new CreateRoomFromBackendTask(request, callback, CreateRoomFromBackendResult.class);
        session.execute(task);
    }

    /**
     * ルームを作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateRoomFromBackendResult createRoomFromBackend(
            CreateRoomFromBackendRequest request
    ) {
        final AsyncResult<CreateRoomFromBackendResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRoomFromBackendAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRoomTask extends Gs2RestSessionTask<GetRoomResult> {
        private GetRoomRequest request;

        public GetRoomTask(
            GetRoomRequest request,
            AsyncAction<AsyncResult<GetRoomResult>> userCallback,
            Class<GetRoomResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

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
     * ルームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRoomAsync(
            GetRoomRequest request,
            AsyncAction<AsyncResult<GetRoomResult>> callback
    ) {
        GetRoomTask task = new GetRoomTask(request, callback, GetRoomResult.class);
        session.execute(task);
    }

    /**
     * ルームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRoomResult getRoom(
            GetRoomRequest request
    ) {
        final AsyncResult<GetRoomResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRoomAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRoomTask extends Gs2RestSessionTask<UpdateRoomResult> {
        private UpdateRoomRequest request;

        public UpdateRoomTask(
            UpdateRoomRequest request,
            AsyncAction<AsyncResult<UpdateRoomResult>> userCallback,
            Class<UpdateRoomResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
            }
            if (this.request.getWhiteListUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getWhiteListUserIds())
                {
                    array.put(item);
                }
                json.put("whiteListUserIds", array);
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
     * ルームを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateRoomAsync(
            UpdateRoomRequest request,
            AsyncAction<AsyncResult<UpdateRoomResult>> callback
    ) {
        UpdateRoomTask task = new UpdateRoomTask(request, callback, UpdateRoomResult.class);
        session.execute(task);
    }

    /**
     * ルームを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateRoomResult updateRoom(
            UpdateRoomRequest request
    ) {
        final AsyncResult<UpdateRoomResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRoomAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRoomTask extends Gs2RestSessionTask<DeleteRoomResult> {
        private DeleteRoomRequest request;

        public DeleteRoomTask(
            DeleteRoomRequest request,
            AsyncAction<AsyncResult<DeleteRoomResult>> userCallback,
            Class<DeleteRoomResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

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
     * ルームを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRoomAsync(
            DeleteRoomRequest request,
            AsyncAction<AsyncResult<DeleteRoomResult>> callback
    ) {
        DeleteRoomTask task = new DeleteRoomTask(request, callback, DeleteRoomResult.class);
        session.execute(task);
    }

    /**
     * ルームを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteRoomResult deleteRoom(
            DeleteRoomRequest request
    ) {
        final AsyncResult<DeleteRoomResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRoomAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRoomFromBackendTask extends Gs2RestSessionTask<DeleteRoomFromBackendResult> {
        private DeleteRoomFromBackendRequest request;

        public DeleteRoomFromBackendTask(
            DeleteRoomFromBackendRequest request,
            AsyncAction<AsyncResult<DeleteRoomFromBackendResult>> userCallback,
            Class<DeleteRoomFromBackendResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
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
     * ルームを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRoomFromBackendAsync(
            DeleteRoomFromBackendRequest request,
            AsyncAction<AsyncResult<DeleteRoomFromBackendResult>> callback
    ) {
        DeleteRoomFromBackendTask task = new DeleteRoomFromBackendTask(request, callback, DeleteRoomFromBackendResult.class);
        session.execute(task);
    }

    /**
     * ルームを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteRoomFromBackendResult deleteRoomFromBackend(
            DeleteRoomFromBackendRequest request
    ) {
        final AsyncResult<DeleteRoomFromBackendResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRoomFromBackendAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMessagesTask extends Gs2RestSessionTask<DescribeMessagesResult> {
        private DescribeMessagesRequest request;

        public DescribeMessagesTask(
            DescribeMessagesRequest request,
            AsyncAction<AsyncResult<DescribeMessagesResult>> userCallback,
            Class<DescribeMessagesResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/message";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPassword() != null) {
                queryStrings.add("password=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPassword()))));
            }
            if (this.request.getStartAt() != null) {
                queryStrings.add("startAt=" + String.valueOf(this.request.getStartAt()));
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
     * メッセージの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMessagesAsync(
            DescribeMessagesRequest request,
            AsyncAction<AsyncResult<DescribeMessagesResult>> callback
    ) {
        DescribeMessagesTask task = new DescribeMessagesTask(request, callback, DescribeMessagesResult.class);
        session.execute(task);
    }

    /**
     * メッセージの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMessagesResult describeMessages(
            DescribeMessagesRequest request
    ) {
        final AsyncResult<DescribeMessagesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMessagesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PostTask extends Gs2RestSessionTask<PostResult> {
        private PostRequest request;

        public PostTask(
            PostRequest request,
            AsyncAction<AsyncResult<PostResult>> userCallback,
            Class<PostResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/message";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCategory() != null) {
                json.put("category", this.request.getCategory());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * メッセージを投稿<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void postAsync(
            PostRequest request,
            AsyncAction<AsyncResult<PostResult>> callback
    ) {
        PostTask task = new PostTask(request, callback, PostResult.class);
        session.execute(task);
    }

    /**
     * メッセージを投稿<br>
     *
     * @param request リクエストパラメータ
     */
    public PostResult post(
            PostRequest request
    ) {
        final AsyncResult<PostResult>[] resultAsyncResult = new AsyncResult[]{null};
        postAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PostByUserIdTask extends Gs2RestSessionTask<PostByUserIdResult> {
        private PostByUserIdRequest request;

        public PostByUserIdTask(
            PostByUserIdRequest request,
            AsyncAction<AsyncResult<PostByUserIdResult>> userCallback,
            Class<PostByUserIdResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/message/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCategory() != null) {
                json.put("category", this.request.getCategory());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * ユーザIDを指定してメッセージを投稿<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void postByUserIdAsync(
            PostByUserIdRequest request,
            AsyncAction<AsyncResult<PostByUserIdResult>> callback
    ) {
        PostByUserIdTask task = new PostByUserIdTask(request, callback, PostByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してメッセージを投稿<br>
     *
     * @param request リクエストパラメータ
     */
    public PostByUserIdResult postByUserId(
            PostByUserIdRequest request
    ) {
        final AsyncResult<PostByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        postByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMessageTask extends Gs2RestSessionTask<GetMessageResult> {
        private GetMessageRequest request;

        public GetMessageTask(
            GetMessageRequest request,
            AsyncAction<AsyncResult<GetMessageResult>> userCallback,
            Class<GetMessageResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/message/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
            url = url.replace("{messageName}", this.request.getMessageName() == null|| this.request.getMessageName().length() == 0 ? "null" : String.valueOf(this.request.getMessageName()));

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
     * メッセージを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMessageAsync(
            GetMessageRequest request,
            AsyncAction<AsyncResult<GetMessageResult>> callback
    ) {
        GetMessageTask task = new GetMessageTask(request, callback, GetMessageResult.class);
        session.execute(task);
    }

    /**
     * メッセージを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMessageResult getMessage(
            GetMessageRequest request
    ) {
        final AsyncResult<GetMessageResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMessageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMessageTask extends Gs2RestSessionTask<DeleteMessageResult> {
        private DeleteMessageRequest request;

        public DeleteMessageTask(
            DeleteMessageRequest request,
            AsyncAction<AsyncResult<DeleteMessageResult>> userCallback,
            Class<DeleteMessageResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/message/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
            url = url.replace("{messageName}", this.request.getMessageName() == null|| this.request.getMessageName().length() == 0 ? "null" : String.valueOf(this.request.getMessageName()));

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
     * メッセージを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMessageAsync(
            DeleteMessageRequest request,
            AsyncAction<AsyncResult<DeleteMessageResult>> callback
    ) {
        DeleteMessageTask task = new DeleteMessageTask(request, callback, DeleteMessageResult.class);
        session.execute(task);
    }

    /**
     * メッセージを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteMessageResult deleteMessage(
            DeleteMessageRequest request
    ) {
        final AsyncResult<DeleteMessageResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMessageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesTask extends Gs2RestSessionTask<DescribeSubscribesResult> {
        private DescribeSubscribesRequest request;

        public DescribeSubscribesTask(
            DescribeSubscribesRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesResult>> userCallback,
            Class<DescribeSubscribesResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/room/subscribe";

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
     * 購読しているルームの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSubscribesAsync(
            DescribeSubscribesRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesResult>> callback
    ) {
        DescribeSubscribesTask task = new DescribeSubscribesTask(request, callback, DescribeSubscribesResult.class);
        session.execute(task);
    }

    /**
     * 購読しているルームの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSubscribesResult describeSubscribes(
            DescribeSubscribesRequest request
    ) {
        final AsyncResult<DescribeSubscribesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByUserIdTask extends Gs2RestSessionTask<DescribeSubscribesByUserIdResult> {
        private DescribeSubscribesByUserIdRequest request;

        public DescribeSubscribesByUserIdTask(
            DescribeSubscribesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByUserIdResult>> userCallback,
            Class<DescribeSubscribesByUserIdResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/room/subscribe";

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
     * ユーザIDを指定して購読しているルームの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSubscribesByUserIdAsync(
            DescribeSubscribesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByUserIdResult>> callback
    ) {
        DescribeSubscribesByUserIdTask task = new DescribeSubscribesByUserIdTask(request, callback, DescribeSubscribesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して購読しているルームの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSubscribesByUserIdResult describeSubscribesByUserId(
            DescribeSubscribesByUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscribesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByRoomNameTask extends Gs2RestSessionTask<DescribeSubscribesByRoomNameResult> {
        private DescribeSubscribesByRoomNameRequest request;

        public DescribeSubscribesByRoomNameTask(
            DescribeSubscribesByRoomNameRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByRoomNameResult>> userCallback,
            Class<DescribeSubscribesByRoomNameResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

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
     * ルーム名を指定して購読しているユーザの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSubscribesByRoomNameAsync(
            DescribeSubscribesByRoomNameRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByRoomNameResult>> callback
    ) {
        DescribeSubscribesByRoomNameTask task = new DescribeSubscribesByRoomNameTask(request, callback, DescribeSubscribesByRoomNameResult.class);
        session.execute(task);
    }

    /**
     * ルーム名を指定して購読しているユーザの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSubscribesByRoomNameResult describeSubscribesByRoomName(
            DescribeSubscribesByRoomNameRequest request
    ) {
        final AsyncResult<DescribeSubscribesByRoomNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByRoomNameAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            AsyncAction<AsyncResult<SubscribeResult>> userCallback,
            Class<SubscribeResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNotificationTypes() != null) {
                JSONArray array = new JSONArray();
                for(NotificationType item : this.request.getNotificationTypes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("notificationTypes", array);
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
     * ルームを購読<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void subscribeAsync(
            SubscribeRequest request,
            AsyncAction<AsyncResult<SubscribeResult>> callback
    ) {
        SubscribeTask task = new SubscribeTask(request, callback, SubscribeResult.class);
        session.execute(task);
    }

    /**
     * ルームを購読<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<SubscribeByUserIdResult>> userCallback,
            Class<SubscribeByUserIdResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNotificationTypes() != null) {
                JSONArray array = new JSONArray();
                for(NotificationType item : this.request.getNotificationTypes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("notificationTypes", array);
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
     * ユーザIDを指定してルームを購読<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void subscribeByUserIdAsync(
            SubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<SubscribeByUserIdResult>> callback
    ) {
        SubscribeByUserIdTask task = new SubscribeByUserIdTask(request, callback, SubscribeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してルームを購読<br>
     *
     * @param request リクエストパラメータ
     */
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

    class GetSubscribeTask extends Gs2RestSessionTask<GetSubscribeResult> {
        private GetSubscribeRequest request;

        public GetSubscribeTask(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> userCallback,
            Class<GetSubscribeResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

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
     * 購読を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSubscribeAsync(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> callback
    ) {
        GetSubscribeTask task = new GetSubscribeTask(request, callback, GetSubscribeResult.class);
        session.execute(task);
    }

    /**
     * 購読を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> userCallback,
            Class<GetSubscribeByUserIdResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
     * ユーザIDを指定して購読を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSubscribeByUserIdAsync(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> callback
    ) {
        GetSubscribeByUserIdTask task = new GetSubscribeByUserIdTask(request, callback, GetSubscribeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して購読を取得<br>
     *
     * @param request リクエストパラメータ
     */
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

    class UpdateNotificationTypeTask extends Gs2RestSessionTask<UpdateNotificationTypeResult> {
        private UpdateNotificationTypeRequest request;

        public UpdateNotificationTypeTask(
            UpdateNotificationTypeRequest request,
            AsyncAction<AsyncResult<UpdateNotificationTypeResult>> userCallback,
            Class<UpdateNotificationTypeResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/room/{roomName}/subscribe/notification";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNotificationTypes() != null) {
                JSONArray array = new JSONArray();
                for(NotificationType item : this.request.getNotificationTypes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("notificationTypes", array);
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
     * 通知方法を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateNotificationTypeAsync(
            UpdateNotificationTypeRequest request,
            AsyncAction<AsyncResult<UpdateNotificationTypeResult>> callback
    ) {
        UpdateNotificationTypeTask task = new UpdateNotificationTypeTask(request, callback, UpdateNotificationTypeResult.class);
        session.execute(task);
    }

    /**
     * 通知方法を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateNotificationTypeResult updateNotificationType(
            UpdateNotificationTypeRequest request
    ) {
        final AsyncResult<UpdateNotificationTypeResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateNotificationTypeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateNotificationTypeByUserIdTask extends Gs2RestSessionTask<UpdateNotificationTypeByUserIdResult> {
        private UpdateNotificationTypeByUserIdRequest request;

        public UpdateNotificationTypeByUserIdTask(
            UpdateNotificationTypeByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateNotificationTypeByUserIdResult>> userCallback,
            Class<UpdateNotificationTypeByUserIdResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/room/{roomName}/subscribe/notification";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNotificationTypes() != null) {
                JSONArray array = new JSONArray();
                for(NotificationType item : this.request.getNotificationTypes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("notificationTypes", array);
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
     * ユーザIDを指定して通知方法を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateNotificationTypeByUserIdAsync(
            UpdateNotificationTypeByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateNotificationTypeByUserIdResult>> callback
    ) {
        UpdateNotificationTypeByUserIdTask task = new UpdateNotificationTypeByUserIdTask(request, callback, UpdateNotificationTypeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して通知方法を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateNotificationTypeByUserIdResult updateNotificationTypeByUserId(
            UpdateNotificationTypeByUserIdRequest request
    ) {
        final AsyncResult<UpdateNotificationTypeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateNotificationTypeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            AsyncAction<AsyncResult<UnsubscribeResult>> userCallback,
            Class<UnsubscribeResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));

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
     * 購読の購読を解除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unsubscribeAsync(
            UnsubscribeRequest request,
            AsyncAction<AsyncResult<UnsubscribeResult>> callback
    ) {
        UnsubscribeTask task = new UnsubscribeTask(request, callback, UnsubscribeResult.class);
        session.execute(task);
    }

    /**
     * 購読の購読を解除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UnsubscribeByUserIdResult>> userCallback,
            Class<UnsubscribeByUserIdResult> clazz
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
                .replace("{service}", "chat")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/room/{roomName}/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{roomName}", this.request.getRoomName() == null|| this.request.getRoomName().length() == 0 ? "null" : String.valueOf(this.request.getRoomName()));
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
     * ユーザIDを指定して購読の購読を解除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unsubscribeByUserIdAsync(
            UnsubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<UnsubscribeByUserIdResult>> callback
    ) {
        UnsubscribeByUserIdTask task = new UnsubscribeByUserIdTask(request, callback, UnsubscribeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して購読の購読を解除<br>
     *
     * @param request リクエストパラメータ
     */
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
}