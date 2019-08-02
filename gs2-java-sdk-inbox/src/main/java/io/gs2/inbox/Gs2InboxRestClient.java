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

package io.gs2.inbox;

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
import io.gs2.inbox.request.*;
import io.gs2.inbox.result.*;
import io.gs2.inbox.model.*;

/**
 * GS2 Inbox API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2InboxRestClient extends AbstractGs2Client<Gs2InboxRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2InboxRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "inbox")
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
                .replace("{service}", "inbox")
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
            if (this.request.getIsAutomaticDeletingEnabled() != null) {
                json.put("isAutomaticDeletingEnabled", this.request.getIsAutomaticDeletingEnabled());
            }
            if (this.request.getReceiveMessageTriggerScriptId() != null) {
                json.put("receiveMessageTriggerScriptId", this.request.getReceiveMessageTriggerScriptId());
            }
            if (this.request.getReceiveMessageDoneTriggerScriptId() != null) {
                json.put("receiveMessageDoneTriggerScriptId", this.request.getReceiveMessageDoneTriggerScriptId());
            }
            if (this.request.getReceiveMessageDoneTriggerNamespaceId() != null) {
                json.put("receiveMessageDoneTriggerNamespaceId", this.request.getReceiveMessageDoneTriggerNamespaceId());
            }
            if (this.request.getReadMessageTriggerScriptId() != null) {
                json.put("readMessageTriggerScriptId", this.request.getReadMessageTriggerScriptId());
            }
            if (this.request.getReadMessageDoneTriggerScriptId() != null) {
                json.put("readMessageDoneTriggerScriptId", this.request.getReadMessageDoneTriggerScriptId());
            }
            if (this.request.getReadMessageDoneTriggerNamespaceId() != null) {
                json.put("readMessageDoneTriggerNamespaceId", this.request.getReadMessageDoneTriggerNamespaceId());
            }
            if (this.request.getDeleteMessageTriggerScriptId() != null) {
                json.put("deleteMessageTriggerScriptId", this.request.getDeleteMessageTriggerScriptId());
            }
            if (this.request.getDeleteMessageDoneTriggerScriptId() != null) {
                json.put("deleteMessageDoneTriggerScriptId", this.request.getDeleteMessageDoneTriggerScriptId());
            }
            if (this.request.getDeleteMessageDoneTriggerNamespaceId() != null) {
                json.put("deleteMessageDoneTriggerNamespaceId", this.request.getDeleteMessageDoneTriggerNamespaceId());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getReceiveNotification() != null) {
                try {
                    json.put("receiveNotification", new JSONObject(mapper.writeValueAsString(this.request.getReceiveNotification())));
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
                .replace("{service}", "inbox")
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
                .replace("{service}", "inbox")
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getIsAutomaticDeletingEnabled() != null) {
                json.put("isAutomaticDeletingEnabled", this.request.getIsAutomaticDeletingEnabled());
            }
            if (this.request.getReceiveMessageTriggerScriptId() != null) {
                json.put("receiveMessageTriggerScriptId", this.request.getReceiveMessageTriggerScriptId());
            }
            if (this.request.getReceiveMessageDoneTriggerScriptId() != null) {
                json.put("receiveMessageDoneTriggerScriptId", this.request.getReceiveMessageDoneTriggerScriptId());
            }
            if (this.request.getReceiveMessageDoneTriggerNamespaceId() != null) {
                json.put("receiveMessageDoneTriggerNamespaceId", this.request.getReceiveMessageDoneTriggerNamespaceId());
            }
            if (this.request.getReadMessageTriggerScriptId() != null) {
                json.put("readMessageTriggerScriptId", this.request.getReadMessageTriggerScriptId());
            }
            if (this.request.getReadMessageDoneTriggerScriptId() != null) {
                json.put("readMessageDoneTriggerScriptId", this.request.getReadMessageDoneTriggerScriptId());
            }
            if (this.request.getReadMessageDoneTriggerNamespaceId() != null) {
                json.put("readMessageDoneTriggerNamespaceId", this.request.getReadMessageDoneTriggerNamespaceId());
            }
            if (this.request.getDeleteMessageTriggerScriptId() != null) {
                json.put("deleteMessageTriggerScriptId", this.request.getDeleteMessageTriggerScriptId());
            }
            if (this.request.getDeleteMessageDoneTriggerScriptId() != null) {
                json.put("deleteMessageDoneTriggerScriptId", this.request.getDeleteMessageDoneTriggerScriptId());
            }
            if (this.request.getDeleteMessageDoneTriggerNamespaceId() != null) {
                json.put("deleteMessageDoneTriggerNamespaceId", this.request.getDeleteMessageDoneTriggerNamespaceId());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getReceiveNotification() != null) {
                try {
                    json.put("receiveNotification", new JSONObject(mapper.writeValueAsString(this.request.getReceiveNotification())));
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
                .replace("{service}", "inbox")
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/message";

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
     * メッセージの一覧を取得<br>
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
     * メッセージの一覧を取得<br>
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

    class DescribeMessagesByUserIdTask extends Gs2RestSessionTask<DescribeMessagesByUserIdResult> {
        private DescribeMessagesByUserIdRequest request;

        public DescribeMessagesByUserIdTask(
            DescribeMessagesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeMessagesByUserIdResult>> userCallback,
            Class<DescribeMessagesByUserIdResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/message";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
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
     * メッセージの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMessagesByUserIdAsync(
            DescribeMessagesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeMessagesByUserIdResult>> callback
    ) {
        DescribeMessagesByUserIdTask task = new DescribeMessagesByUserIdTask(request, callback, DescribeMessagesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * メッセージの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMessagesByUserIdResult describeMessagesByUserId(
            DescribeMessagesByUserIdRequest request
    ) {
        final AsyncResult<DescribeMessagesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMessagesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendMessageByUserIdTask extends Gs2RestSessionTask<SendMessageByUserIdResult> {
        private SendMessageByUserIdRequest request;

        public SendMessageByUserIdTask(
            SendMessageByUserIdRequest request,
            AsyncAction<AsyncResult<SendMessageByUserIdResult>> userCallback,
            Class<SendMessageByUserIdResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/message";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getReadAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getReadAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("readAcquireActions", array);
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
     * メッセージを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void sendMessageByUserIdAsync(
            SendMessageByUserIdRequest request,
            AsyncAction<AsyncResult<SendMessageByUserIdResult>> callback
    ) {
        SendMessageByUserIdTask task = new SendMessageByUserIdTask(request, callback, SendMessageByUserIdResult.class);
        session.execute(task);
    }

    /**
     * メッセージを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public SendMessageByUserIdResult sendMessageByUserId(
            SendMessageByUserIdRequest request
    ) {
        final AsyncResult<SendMessageByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendMessageByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    class GetMessageByUserIdTask extends Gs2RestSessionTask<GetMessageByUserIdResult> {
        private GetMessageByUserIdRequest request;

        public GetMessageByUserIdTask(
            GetMessageByUserIdRequest request,
            AsyncAction<AsyncResult<GetMessageByUserIdResult>> userCallback,
            Class<GetMessageByUserIdResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * ユーザーIDを指定してメッセージを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMessageByUserIdAsync(
            GetMessageByUserIdRequest request,
            AsyncAction<AsyncResult<GetMessageByUserIdResult>> callback
    ) {
        GetMessageByUserIdTask task = new GetMessageByUserIdTask(request, callback, GetMessageByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してメッセージを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMessageByUserIdResult getMessageByUserId(
            GetMessageByUserIdRequest request
    ) {
        final AsyncResult<GetMessageByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMessageByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class OpenMessageTask extends Gs2RestSessionTask<OpenMessageResult> {
        private OpenMessageRequest request;

        public OpenMessageTask(
            OpenMessageRequest request,
            AsyncAction<AsyncResult<OpenMessageResult>> userCallback,
            Class<OpenMessageResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{messageName}", this.request.getMessageName() == null|| this.request.getMessageName().length() == 0 ? "null" : String.valueOf(this.request.getMessageName()));

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
     * メッセージを開封<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void openMessageAsync(
            OpenMessageRequest request,
            AsyncAction<AsyncResult<OpenMessageResult>> callback
    ) {
        OpenMessageTask task = new OpenMessageTask(request, callback, OpenMessageResult.class);
        session.execute(task);
    }

    /**
     * メッセージを開封<br>
     *
     * @param request リクエストパラメータ
     */
    public OpenMessageResult openMessage(
            OpenMessageRequest request
    ) {
        final AsyncResult<OpenMessageResult>[] resultAsyncResult = new AsyncResult[]{null};
        openMessageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class OpenMessageByUserIdTask extends Gs2RestSessionTask<OpenMessageByUserIdResult> {
        private OpenMessageByUserIdRequest request;

        public OpenMessageByUserIdTask(
            OpenMessageByUserIdRequest request,
            AsyncAction<AsyncResult<OpenMessageByUserIdResult>> userCallback,
            Class<OpenMessageByUserIdResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{messageName}", this.request.getMessageName() == null|| this.request.getMessageName().length() == 0 ? "null" : String.valueOf(this.request.getMessageName()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * ユーザーIDを指定してメッセージを開封<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void openMessageByUserIdAsync(
            OpenMessageByUserIdRequest request,
            AsyncAction<AsyncResult<OpenMessageByUserIdResult>> callback
    ) {
        OpenMessageByUserIdTask task = new OpenMessageByUserIdTask(request, callback, OpenMessageByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してメッセージを開封<br>
     *
     * @param request リクエストパラメータ
     */
    public OpenMessageByUserIdResult openMessageByUserId(
            OpenMessageByUserIdRequest request
    ) {
        final AsyncResult<OpenMessageByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        openMessageByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReadMessageTask extends Gs2RestSessionTask<ReadMessageResult> {
        private ReadMessageRequest request;

        public ReadMessageTask(
            ReadMessageRequest request,
            AsyncAction<AsyncResult<ReadMessageResult>> userCallback,
            Class<ReadMessageResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/{messageName}/read";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{messageName}", this.request.getMessageName() == null|| this.request.getMessageName().length() == 0 ? "null" : String.valueOf(this.request.getMessageName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
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
     * メッセージを開封<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void readMessageAsync(
            ReadMessageRequest request,
            AsyncAction<AsyncResult<ReadMessageResult>> callback
    ) {
        ReadMessageTask task = new ReadMessageTask(request, callback, ReadMessageResult.class);
        session.execute(task);
    }

    /**
     * メッセージを開封<br>
     *
     * @param request リクエストパラメータ
     */
    public ReadMessageResult readMessage(
            ReadMessageRequest request
    ) {
        final AsyncResult<ReadMessageResult>[] resultAsyncResult = new AsyncResult[]{null};
        readMessageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReadMessageByUserIdTask extends Gs2RestSessionTask<ReadMessageByUserIdResult> {
        private ReadMessageByUserIdRequest request;

        public ReadMessageByUserIdTask(
            ReadMessageByUserIdRequest request,
            AsyncAction<AsyncResult<ReadMessageByUserIdResult>> userCallback,
            Class<ReadMessageByUserIdResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/{messageName}/read";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{messageName}", this.request.getMessageName() == null|| this.request.getMessageName().length() == 0 ? "null" : String.valueOf(this.request.getMessageName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
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
     * ユーザーIDを指定してメッセージを開封<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void readMessageByUserIdAsync(
            ReadMessageByUserIdRequest request,
            AsyncAction<AsyncResult<ReadMessageByUserIdResult>> callback
    ) {
        ReadMessageByUserIdTask task = new ReadMessageByUserIdTask(request, callback, ReadMessageByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してメッセージを開封<br>
     *
     * @param request リクエストパラメータ
     */
    public ReadMessageByUserIdResult readMessageByUserId(
            ReadMessageByUserIdRequest request
    ) {
        final AsyncResult<ReadMessageByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        readMessageByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    class DeleteMessageByUserIdTask extends Gs2RestSessionTask<DeleteMessageByUserIdResult> {
        private DeleteMessageByUserIdRequest request;

        public DeleteMessageByUserIdTask(
            DeleteMessageByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteMessageByUserIdResult>> userCallback,
            Class<DeleteMessageByUserIdResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/{messageName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * ユーザーIDを指定してメッセージを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMessageByUserIdAsync(
            DeleteMessageByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteMessageByUserIdResult>> callback
    ) {
        DeleteMessageByUserIdTask task = new DeleteMessageByUserIdTask(request, callback, DeleteMessageByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してメッセージを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteMessageByUserIdResult deleteMessageByUserId(
            DeleteMessageByUserIdRequest request
    ) {
        final AsyncResult<DeleteMessageByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMessageByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class OpenByStampTaskTask extends Gs2RestSessionTask<OpenByStampTaskResult> {
        private OpenByStampTaskRequest request;

        public OpenByStampTaskTask(
            OpenByStampTaskRequest request,
            AsyncAction<AsyncResult<OpenByStampTaskResult>> userCallback,
            Class<OpenByStampTaskResult> clazz
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
                .replace("{service}", "inbox")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/open";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getStampTask() != null) {
                json.put("stampTask", this.request.getStampTask());
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
     * メッセージを作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void openByStampTaskAsync(
            OpenByStampTaskRequest request,
            AsyncAction<AsyncResult<OpenByStampTaskResult>> callback
    ) {
        OpenByStampTaskTask task = new OpenByStampTaskTask(request, callback, OpenByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * メッセージを作成<br>
     *
     * @param request リクエストパラメータ
     */
    public OpenByStampTaskResult openByStampTask(
            OpenByStampTaskRequest request
    ) {
        final AsyncResult<OpenByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        openByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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