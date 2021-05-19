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

package io.gs2.quest;

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
import io.gs2.quest.request.*;
import io.gs2.quest.result.*;
import io.gs2.quest.model.*;

/**
 * GS2 Quest API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2QuestRestClient extends AbstractGs2Client<Gs2QuestRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2QuestRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "quest")
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
     * クエストを分類するカテゴリーの一覧を取得<br>
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
     * クエストを分類するカテゴリーの一覧を取得<br>
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
                .replace("{service}", "quest")
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
            if (this.request.getStartQuestScript() != null) {
                try {
                    json.put("startQuestScript", new JSONObject(mapper.writeValueAsString(this.request.getStartQuestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCompleteQuestScript() != null) {
                try {
                    json.put("completeQuestScript", new JSONObject(mapper.writeValueAsString(this.request.getCompleteQuestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getFailedQuestScript() != null) {
                try {
                    json.put("failedQuestScript", new JSONObject(mapper.writeValueAsString(this.request.getFailedQuestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
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
     * クエストを分類するカテゴリーを新規作成<br>
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
     * クエストを分類するカテゴリーを新規作成<br>
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
                .replace("{service}", "quest")
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
     * クエストを分類するカテゴリーの状態を取得<br>
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
     * クエストを分類するカテゴリーの状態を取得<br>
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
                .replace("{service}", "quest")
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
     * クエストを分類するカテゴリーを取得<br>
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
     * クエストを分類するカテゴリーを取得<br>
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getStartQuestScript() != null) {
                try {
                    json.put("startQuestScript", new JSONObject(mapper.writeValueAsString(this.request.getStartQuestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCompleteQuestScript() != null) {
                try {
                    json.put("completeQuestScript", new JSONObject(mapper.writeValueAsString(this.request.getCompleteQuestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getFailedQuestScript() != null) {
                try {
                    json.put("failedQuestScript", new JSONObject(mapper.writeValueAsString(this.request.getFailedQuestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
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
     * クエストを分類するカテゴリーを更新<br>
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
     * クエストを分類するカテゴリーを更新<br>
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
                .replace("{service}", "quest")
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
     * クエストを分類するカテゴリーを削除<br>
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
     * クエストを分類するカテゴリーを削除<br>
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

    class DescribeQuestGroupModelMastersTask extends Gs2RestSessionTask<DescribeQuestGroupModelMastersResult> {
        private DescribeQuestGroupModelMastersRequest request;

        public DescribeQuestGroupModelMastersTask(
            DescribeQuestGroupModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeQuestGroupModelMastersResult>> userCallback,
            Class<DescribeQuestGroupModelMastersResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

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
     * クエストグループマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeQuestGroupModelMastersAsync(
            DescribeQuestGroupModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeQuestGroupModelMastersResult>> callback
    ) {
        DescribeQuestGroupModelMastersTask task = new DescribeQuestGroupModelMastersTask(request, callback, DescribeQuestGroupModelMastersResult.class);
        session.execute(task);
    }

    /**
     * クエストグループマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeQuestGroupModelMastersResult describeQuestGroupModelMasters(
            DescribeQuestGroupModelMastersRequest request
    ) {
        final AsyncResult<DescribeQuestGroupModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeQuestGroupModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateQuestGroupModelMasterTask extends Gs2RestSessionTask<CreateQuestGroupModelMasterResult> {
        private CreateQuestGroupModelMasterRequest request;

        public CreateQuestGroupModelMasterTask(
            CreateQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<CreateQuestGroupModelMasterResult>> userCallback,
            Class<CreateQuestGroupModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

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
            if (this.request.getChallengePeriodEventId() != null) {
                json.put("challengePeriodEventId", this.request.getChallengePeriodEventId());
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
     * クエストグループマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createQuestGroupModelMasterAsync(
            CreateQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<CreateQuestGroupModelMasterResult>> callback
    ) {
        CreateQuestGroupModelMasterTask task = new CreateQuestGroupModelMasterTask(request, callback, CreateQuestGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストグループマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateQuestGroupModelMasterResult createQuestGroupModelMaster(
            CreateQuestGroupModelMasterRequest request
    ) {
        final AsyncResult<CreateQuestGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createQuestGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetQuestGroupModelMasterTask extends Gs2RestSessionTask<GetQuestGroupModelMasterResult> {
        private GetQuestGroupModelMasterRequest request;

        public GetQuestGroupModelMasterTask(
            GetQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<GetQuestGroupModelMasterResult>> userCallback,
            Class<GetQuestGroupModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
     * クエストグループマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getQuestGroupModelMasterAsync(
            GetQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<GetQuestGroupModelMasterResult>> callback
    ) {
        GetQuestGroupModelMasterTask task = new GetQuestGroupModelMasterTask(request, callback, GetQuestGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストグループマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetQuestGroupModelMasterResult getQuestGroupModelMaster(
            GetQuestGroupModelMasterRequest request
    ) {
        final AsyncResult<GetQuestGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getQuestGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateQuestGroupModelMasterTask extends Gs2RestSessionTask<UpdateQuestGroupModelMasterResult> {
        private UpdateQuestGroupModelMasterRequest request;

        public UpdateQuestGroupModelMasterTask(
            UpdateQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateQuestGroupModelMasterResult>> userCallback,
            Class<UpdateQuestGroupModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getChallengePeriodEventId() != null) {
                json.put("challengePeriodEventId", this.request.getChallengePeriodEventId());
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
     * クエストグループマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateQuestGroupModelMasterAsync(
            UpdateQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateQuestGroupModelMasterResult>> callback
    ) {
        UpdateQuestGroupModelMasterTask task = new UpdateQuestGroupModelMasterTask(request, callback, UpdateQuestGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストグループマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateQuestGroupModelMasterResult updateQuestGroupModelMaster(
            UpdateQuestGroupModelMasterRequest request
    ) {
        final AsyncResult<UpdateQuestGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateQuestGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteQuestGroupModelMasterTask extends Gs2RestSessionTask<DeleteQuestGroupModelMasterResult> {
        private DeleteQuestGroupModelMasterRequest request;

        public DeleteQuestGroupModelMasterTask(
            DeleteQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteQuestGroupModelMasterResult>> userCallback,
            Class<DeleteQuestGroupModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
     * クエストグループマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteQuestGroupModelMasterAsync(
            DeleteQuestGroupModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteQuestGroupModelMasterResult>> callback
    ) {
        DeleteQuestGroupModelMasterTask task = new DeleteQuestGroupModelMasterTask(request, callback, DeleteQuestGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストグループマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteQuestGroupModelMasterResult deleteQuestGroupModelMaster(
            DeleteQuestGroupModelMasterRequest request
    ) {
        final AsyncResult<DeleteQuestGroupModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteQuestGroupModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeQuestModelMastersTask extends Gs2RestSessionTask<DescribeQuestModelMastersResult> {
        private DescribeQuestModelMastersRequest request;

        public DescribeQuestModelMastersTask(
            DescribeQuestModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeQuestModelMastersResult>> userCallback,
            Class<DescribeQuestModelMastersResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}/quest";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
     * クエストモデルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeQuestModelMastersAsync(
            DescribeQuestModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeQuestModelMastersResult>> callback
    ) {
        DescribeQuestModelMastersTask task = new DescribeQuestModelMastersTask(request, callback, DescribeQuestModelMastersResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeQuestModelMastersResult describeQuestModelMasters(
            DescribeQuestModelMastersRequest request
    ) {
        final AsyncResult<DescribeQuestModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeQuestModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateQuestModelMasterTask extends Gs2RestSessionTask<CreateQuestModelMasterResult> {
        private CreateQuestModelMasterRequest request;

        public CreateQuestModelMasterTask(
            CreateQuestModelMasterRequest request,
            AsyncAction<AsyncResult<CreateQuestModelMasterResult>> userCallback,
            Class<CreateQuestModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}/quest";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
            if (this.request.getContents() != null) {
                JSONArray array = new JSONArray();
                for(Contents item : this.request.getContents())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("contents", array);
            }
            if (this.request.getChallengePeriodEventId() != null) {
                json.put("challengePeriodEventId", this.request.getChallengePeriodEventId());
            }
            if (this.request.getConsumeActions() != null) {
                JSONArray array = new JSONArray();
                for(ConsumeAction item : this.request.getConsumeActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("consumeActions", array);
            }
            if (this.request.getFailedAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getFailedAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("failedAcquireActions", array);
            }
            if (this.request.getPremiseQuestNames() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getPremiseQuestNames())
                {
                    array.put(item);
                }
                json.put("premiseQuestNames", array);
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
     * クエストモデルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createQuestModelMasterAsync(
            CreateQuestModelMasterRequest request,
            AsyncAction<AsyncResult<CreateQuestModelMasterResult>> callback
    ) {
        CreateQuestModelMasterTask task = new CreateQuestModelMasterTask(request, callback, CreateQuestModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateQuestModelMasterResult createQuestModelMaster(
            CreateQuestModelMasterRequest request
    ) {
        final AsyncResult<CreateQuestModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createQuestModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetQuestModelMasterTask extends Gs2RestSessionTask<GetQuestModelMasterResult> {
        private GetQuestModelMasterRequest request;

        public GetQuestModelMasterTask(
            GetQuestModelMasterRequest request,
            AsyncAction<AsyncResult<GetQuestModelMasterResult>> userCallback,
            Class<GetQuestModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}/quest/{questName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
            url = url.replace("{questName}", this.request.getQuestName() == null|| this.request.getQuestName().length() == 0 ? "null" : String.valueOf(this.request.getQuestName()));

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
     * クエストモデルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getQuestModelMasterAsync(
            GetQuestModelMasterRequest request,
            AsyncAction<AsyncResult<GetQuestModelMasterResult>> callback
    ) {
        GetQuestModelMasterTask task = new GetQuestModelMasterTask(request, callback, GetQuestModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetQuestModelMasterResult getQuestModelMaster(
            GetQuestModelMasterRequest request
    ) {
        final AsyncResult<GetQuestModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getQuestModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateQuestModelMasterTask extends Gs2RestSessionTask<UpdateQuestModelMasterResult> {
        private UpdateQuestModelMasterRequest request;

        public UpdateQuestModelMasterTask(
            UpdateQuestModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateQuestModelMasterResult>> userCallback,
            Class<UpdateQuestModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}/quest/{questName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
            url = url.replace("{questName}", this.request.getQuestName() == null|| this.request.getQuestName().length() == 0 ? "null" : String.valueOf(this.request.getQuestName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getContents() != null) {
                JSONArray array = new JSONArray();
                for(Contents item : this.request.getContents())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("contents", array);
            }
            if (this.request.getChallengePeriodEventId() != null) {
                json.put("challengePeriodEventId", this.request.getChallengePeriodEventId());
            }
            if (this.request.getConsumeActions() != null) {
                JSONArray array = new JSONArray();
                for(ConsumeAction item : this.request.getConsumeActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("consumeActions", array);
            }
            if (this.request.getFailedAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getFailedAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("failedAcquireActions", array);
            }
            if (this.request.getPremiseQuestNames() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getPremiseQuestNames())
                {
                    array.put(item);
                }
                json.put("premiseQuestNames", array);
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
     * クエストモデルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateQuestModelMasterAsync(
            UpdateQuestModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateQuestModelMasterResult>> callback
    ) {
        UpdateQuestModelMasterTask task = new UpdateQuestModelMasterTask(request, callback, UpdateQuestModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateQuestModelMasterResult updateQuestModelMaster(
            UpdateQuestModelMasterRequest request
    ) {
        final AsyncResult<UpdateQuestModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateQuestModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteQuestModelMasterTask extends Gs2RestSessionTask<DeleteQuestModelMasterResult> {
        private DeleteQuestModelMasterRequest request;

        public DeleteQuestModelMasterTask(
            DeleteQuestModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteQuestModelMasterResult>> userCallback,
            Class<DeleteQuestModelMasterResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{questGroupName}/quest/{questName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
            url = url.replace("{questName}", this.request.getQuestName() == null|| this.request.getQuestName().length() == 0 ? "null" : String.valueOf(this.request.getQuestName()));

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
     * クエストモデルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteQuestModelMasterAsync(
            DeleteQuestModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteQuestModelMasterResult>> callback
    ) {
        DeleteQuestModelMasterTask task = new DeleteQuestModelMasterTask(request, callback, DeleteQuestModelMasterResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteQuestModelMasterResult deleteQuestModelMaster(
            DeleteQuestModelMasterRequest request
    ) {
        final AsyncResult<DeleteQuestModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteQuestModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "quest")
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
     * 現在有効なクエストマスターのマスターデータをエクスポートします<br>
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
     * 現在有効なクエストマスターのマスターデータをエクスポートします<br>
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

    class GetCurrentQuestMasterTask extends Gs2RestSessionTask<GetCurrentQuestMasterResult> {
        private GetCurrentQuestMasterRequest request;

        public GetCurrentQuestMasterTask(
            GetCurrentQuestMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentQuestMasterResult>> userCallback,
            Class<GetCurrentQuestMasterResult> clazz
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
                .replace("{service}", "quest")
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
     * 現在有効なクエストマスターを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentQuestMasterAsync(
            GetCurrentQuestMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentQuestMasterResult>> callback
    ) {
        GetCurrentQuestMasterTask task = new GetCurrentQuestMasterTask(request, callback, GetCurrentQuestMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なクエストマスターを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentQuestMasterResult getCurrentQuestMaster(
            GetCurrentQuestMasterRequest request
    ) {
        final AsyncResult<GetCurrentQuestMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentQuestMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentQuestMasterTask extends Gs2RestSessionTask<UpdateCurrentQuestMasterResult> {
        private UpdateCurrentQuestMasterRequest request;

        public UpdateCurrentQuestMasterTask(
            UpdateCurrentQuestMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentQuestMasterResult>> userCallback,
            Class<UpdateCurrentQuestMasterResult> clazz
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
                .replace("{service}", "quest")
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
     * 現在有効なクエストマスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentQuestMasterAsync(
            UpdateCurrentQuestMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentQuestMasterResult>> callback
    ) {
        UpdateCurrentQuestMasterTask task = new UpdateCurrentQuestMasterTask(request, callback, UpdateCurrentQuestMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なクエストマスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentQuestMasterResult updateCurrentQuestMaster(
            UpdateCurrentQuestMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentQuestMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentQuestMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentQuestMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentQuestMasterFromGitHubResult> {
        private UpdateCurrentQuestMasterFromGitHubRequest request;

        public UpdateCurrentQuestMasterFromGitHubTask(
            UpdateCurrentQuestMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentQuestMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentQuestMasterFromGitHubResult> clazz
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
                .replace("{service}", "quest")
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
     * 現在有効なクエストマスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentQuestMasterFromGitHubAsync(
            UpdateCurrentQuestMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentQuestMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentQuestMasterFromGitHubTask task = new UpdateCurrentQuestMasterFromGitHubTask(request, callback, UpdateCurrentQuestMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なクエストマスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentQuestMasterFromGitHubResult updateCurrentQuestMasterFromGitHub(
            UpdateCurrentQuestMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentQuestMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentQuestMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeProgressesByUserIdTask extends Gs2RestSessionTask<DescribeProgressesByUserIdResult> {
        private DescribeProgressesByUserIdRequest request;

        public DescribeProgressesByUserIdTask(
            DescribeProgressesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProgressesByUserIdResult>> userCallback,
            Class<DescribeProgressesByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/progress";

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
     * クエスト挑戦の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeProgressesByUserIdAsync(
            DescribeProgressesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProgressesByUserIdResult>> callback
    ) {
        DescribeProgressesByUserIdTask task = new DescribeProgressesByUserIdTask(request, callback, DescribeProgressesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * クエスト挑戦の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeProgressesByUserIdResult describeProgressesByUserId(
            DescribeProgressesByUserIdRequest request
    ) {
        final AsyncResult<DescribeProgressesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProgressesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateProgressByUserIdTask extends Gs2RestSessionTask<CreateProgressByUserIdResult> {
        private CreateProgressByUserIdRequest request;

        public CreateProgressByUserIdTask(
            CreateProgressByUserIdRequest request,
            AsyncAction<AsyncResult<CreateProgressByUserIdResult>> userCallback,
            Class<CreateProgressByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getQuestModelId() != null) {
                json.put("questModelId", this.request.getQuestModelId());
            }
            if (this.request.getForce() != null) {
                json.put("force", this.request.getForce());
            }
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
     * ユーザIDを指定してクエスト挑戦を作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createProgressByUserIdAsync(
            CreateProgressByUserIdRequest request,
            AsyncAction<AsyncResult<CreateProgressByUserIdResult>> callback
    ) {
        CreateProgressByUserIdTask task = new CreateProgressByUserIdTask(request, callback, CreateProgressByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエスト挑戦を作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateProgressByUserIdResult createProgressByUserId(
            CreateProgressByUserIdRequest request
    ) {
        final AsyncResult<CreateProgressByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createProgressByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProgressTask extends Gs2RestSessionTask<GetProgressResult> {
        private GetProgressRequest request;

        public GetProgressTask(
            GetProgressRequest request,
            AsyncAction<AsyncResult<GetProgressResult>> userCallback,
            Class<GetProgressResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress";

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
     * クエスト挑戦を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProgressAsync(
            GetProgressRequest request,
            AsyncAction<AsyncResult<GetProgressResult>> callback
    ) {
        GetProgressTask task = new GetProgressTask(request, callback, GetProgressResult.class);
        session.execute(task);
    }

    /**
     * クエスト挑戦を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProgressResult getProgress(
            GetProgressRequest request
    ) {
        final AsyncResult<GetProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProgressAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProgressByUserIdTask extends Gs2RestSessionTask<GetProgressByUserIdResult> {
        private GetProgressByUserIdRequest request;

        public GetProgressByUserIdTask(
            GetProgressByUserIdRequest request,
            AsyncAction<AsyncResult<GetProgressByUserIdResult>> userCallback,
            Class<GetProgressByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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
     * ユーザIDを指定してクエスト挑戦を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProgressByUserIdAsync(
            GetProgressByUserIdRequest request,
            AsyncAction<AsyncResult<GetProgressByUserIdResult>> callback
    ) {
        GetProgressByUserIdTask task = new GetProgressByUserIdTask(request, callback, GetProgressByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエスト挑戦を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProgressByUserIdResult getProgressByUserId(
            GetProgressByUserIdRequest request
    ) {
        final AsyncResult<GetProgressByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProgressByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class StartTask extends Gs2RestSessionTask<StartResult> {
        private StartRequest request;

        public StartTask(
            StartRequest request,
            AsyncAction<AsyncResult<StartResult>> userCallback,
            Class<StartResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress/group/{questGroupName}/quest/{questName}/start";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
            url = url.replace("{questName}", this.request.getQuestName() == null|| this.request.getQuestName().length() == 0 ? "null" : String.valueOf(this.request.getQuestName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getForce() != null) {
                json.put("force", this.request.getForce());
            }
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
     * クエストを開始<br>
     *   <br>
     *   同一カテゴリ内でゲームプレイヤーは同時に1つのクエストを実行できます。<br>
     *   すでに同一カテゴリ内で実行中のクエストがある場合、このAPIはエラーを返します。<br>
     *   進行中のクエストを取得するAPIを呼び出すことで、クエストを再開するために必要な情報を得ることができます。<br>
     *   強制的にクエストを開始するには force パラメータに true を指定することで強制的にクエストを開始できます。<br>
     *   <br>
     *   クエストが正常に開始できた場合、Progress オブジェクトを応答します。<br>
     *   Progress オブジェクトはクエストを実行するために必要ないくつかの情報を応答します。<br>
     *   <br>
     *   transactionId は実行中のクエスト固有のIDです。<br>
     *   クエストの完了報告にはこのIDを指定する必要があります。<br>
     *   <br>
     *   randomSeed はクエストの内容を決定するために使用できる乱数シードです。<br>
     *   クエストを開始するたびに異なる乱数が払い出されますので、この値をシード値としてゲームを進行させることで<br>
     *   クエスト中にアプリケーションを強制終了したとしても同一条件で再開することができます。<br>
     *   <br>
     *   rewards にはこのクエストにおいて入手可能な報酬とその数量の"最大値"が得られます。<br>
     *   クエストの完了報告にも rewards を渡すことができ、そこでクエスト中に実際に入手したアイテムの数量を指定します。<br>
     *   詳細はクエストの完了報告APIのドキュメントを参照してください。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void startAsync(
            StartRequest request,
            AsyncAction<AsyncResult<StartResult>> callback
    ) {
        StartTask task = new StartTask(request, callback, StartResult.class);
        session.execute(task);
    }

    /**
     * クエストを開始<br>
     *   <br>
     *   同一カテゴリ内でゲームプレイヤーは同時に1つのクエストを実行できます。<br>
     *   すでに同一カテゴリ内で実行中のクエストがある場合、このAPIはエラーを返します。<br>
     *   進行中のクエストを取得するAPIを呼び出すことで、クエストを再開するために必要な情報を得ることができます。<br>
     *   強制的にクエストを開始するには force パラメータに true を指定することで強制的にクエストを開始できます。<br>
     *   <br>
     *   クエストが正常に開始できた場合、Progress オブジェクトを応答します。<br>
     *   Progress オブジェクトはクエストを実行するために必要ないくつかの情報を応答します。<br>
     *   <br>
     *   transactionId は実行中のクエスト固有のIDです。<br>
     *   クエストの完了報告にはこのIDを指定する必要があります。<br>
     *   <br>
     *   randomSeed はクエストの内容を決定するために使用できる乱数シードです。<br>
     *   クエストを開始するたびに異なる乱数が払い出されますので、この値をシード値としてゲームを進行させることで<br>
     *   クエスト中にアプリケーションを強制終了したとしても同一条件で再開することができます。<br>
     *   <br>
     *   rewards にはこのクエストにおいて入手可能な報酬とその数量の"最大値"が得られます。<br>
     *   クエストの完了報告にも rewards を渡すことができ、そこでクエスト中に実際に入手したアイテムの数量を指定します。<br>
     *   詳細はクエストの完了報告APIのドキュメントを参照してください。<br>
     *
     * @param request リクエストパラメータ
     */
    public StartResult start(
            StartRequest request
    ) {
        final AsyncResult<StartResult>[] resultAsyncResult = new AsyncResult[]{null};
        startAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class StartByUserIdTask extends Gs2RestSessionTask<StartByUserIdResult> {
        private StartByUserIdRequest request;

        public StartByUserIdTask(
            StartByUserIdRequest request,
            AsyncAction<AsyncResult<StartByUserIdResult>> userCallback,
            Class<StartByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress/group/{questGroupName}/quest/{questName}/start";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
            url = url.replace("{questName}", this.request.getQuestName() == null|| this.request.getQuestName().length() == 0 ? "null" : String.valueOf(this.request.getQuestName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getForce() != null) {
                json.put("force", this.request.getForce());
            }
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
     * ユーザIDを指定してクエストを開始<br>
     *   <br>
     *   同一カテゴリ内でゲームプレイヤーは同時に1つのクエストを実行できます。<br>
     *   すでに同一カテゴリ内で実行中のクエストがある場合、このAPIはエラーを返します。<br>
     *   進行中のクエストを取得するAPIを呼び出すことで、クエストを再開するために必要な情報を得ることができます。<br>
     *   強制的にクエストを開始するには force パラメータに true を指定することで強制的にクエストを開始できます。<br>
     *   <br>
     *   クエストが正常に開始できた場合、Progress オブジェクトを応答します。<br>
     *   Progress オブジェクトはクエストを実行するために必要ないくつかの情報を応答します。<br>
     *   <br>
     *   transactionId は実行中のクエスト固有のIDです。<br>
     *   クエストの完了報告にはこのIDを指定する必要があります。<br>
     *   <br>
     *   randomSeed はクエストの内容を決定するために使用できる乱数シードです。<br>
     *   クエストを開®®始するたびに異なる乱数が払い出されますので、この値をシード値としてゲームを進行させることで<br>
     *   クエスト中にアプリケーションを強制終了したとしても同一条件で再開することができます。<br>
     *   <br>
     *   rewards にはこのクエストにおいて入手可能な報酬とその数量の"最大値"が得られます。<br>
     *   クエストの完了報告にも rewards を渡すことができ、そこでクエスト中に実際に入手したアイテムの数量を指定します。<br>
     *   詳細はクエストの完了報告APIのドキュメントを参照してください。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void startByUserIdAsync(
            StartByUserIdRequest request,
            AsyncAction<AsyncResult<StartByUserIdResult>> callback
    ) {
        StartByUserIdTask task = new StartByUserIdTask(request, callback, StartByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエストを開始<br>
     *   <br>
     *   同一カテゴリ内でゲームプレイヤーは同時に1つのクエストを実行できます。<br>
     *   すでに同一カテゴリ内で実行中のクエストがある場合、このAPIはエラーを返します。<br>
     *   進行中のクエストを取得するAPIを呼び出すことで、クエストを再開するために必要な情報を得ることができます。<br>
     *   強制的にクエストを開始するには force パラメータに true を指定することで強制的にクエストを開始できます。<br>
     *   <br>
     *   クエストが正常に開始できた場合、Progress オブジェクトを応答します。<br>
     *   Progress オブジェクトはクエストを実行するために必要ないくつかの情報を応答します。<br>
     *   <br>
     *   transactionId は実行中のクエスト固有のIDです。<br>
     *   クエストの完了報告にはこのIDを指定する必要があります。<br>
     *   <br>
     *   randomSeed はクエストの内容を決定するために使用できる乱数シードです。<br>
     *   クエストを開®®始するたびに異なる乱数が払い出されますので、この値をシード値としてゲームを進行させることで<br>
     *   クエスト中にアプリケーションを強制終了したとしても同一条件で再開することができます。<br>
     *   <br>
     *   rewards にはこのクエストにおいて入手可能な報酬とその数量の"最大値"が得られます。<br>
     *   クエストの完了報告にも rewards を渡すことができ、そこでクエスト中に実際に入手したアイテムの数量を指定します。<br>
     *   詳細はクエストの完了報告APIのドキュメントを参照してください。<br>
     *
     * @param request リクエストパラメータ
     */
    public StartByUserIdResult startByUserId(
            StartByUserIdRequest request
    ) {
        final AsyncResult<StartByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        startByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EndTask extends Gs2RestSessionTask<EndResult> {
        private EndRequest request;

        public EndTask(
            EndRequest request,
            AsyncAction<AsyncResult<EndResult>> userCallback,
            Class<EndResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress/end";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTransactionId() != null) {
                json.put("transactionId", this.request.getTransactionId());
            }
            if (this.request.getRewards() != null) {
                JSONArray array = new JSONArray();
                for(Reward item : this.request.getRewards())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("rewards", array);
            }
            if (this.request.getIsComplete() != null) {
                json.put("isComplete", this.request.getIsComplete());
            }
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
     * クエストを完了<br>
     *   <br>
     *   開始時に受け取ったクエストにおいて入手可能な報酬とその数量の"最大値"のうち、クエスト内で実際に入手した報酬を rewards で報告します。<br>
     *   isComplete にはクエストをクリアできたかを報告します。クエストに失敗した場合、rewards の値は無視してクエストに設定された失敗した場合の報酬が付与されます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void endAsync(
            EndRequest request,
            AsyncAction<AsyncResult<EndResult>> callback
    ) {
        EndTask task = new EndTask(request, callback, EndResult.class);
        session.execute(task);
    }

    /**
     * クエストを完了<br>
     *   <br>
     *   開始時に受け取ったクエストにおいて入手可能な報酬とその数量の"最大値"のうち、クエスト内で実際に入手した報酬を rewards で報告します。<br>
     *   isComplete にはクエストをクリアできたかを報告します。クエストに失敗した場合、rewards の値は無視してクエストに設定された失敗した場合の報酬が付与されます。<br>
     *
     * @param request リクエストパラメータ
     */
    public EndResult end(
            EndRequest request
    ) {
        final AsyncResult<EndResult>[] resultAsyncResult = new AsyncResult[]{null};
        endAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EndByUserIdTask extends Gs2RestSessionTask<EndByUserIdResult> {
        private EndByUserIdRequest request;

        public EndByUserIdTask(
            EndByUserIdRequest request,
            AsyncAction<AsyncResult<EndByUserIdResult>> userCallback,
            Class<EndByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress/end";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTransactionId() != null) {
                json.put("transactionId", this.request.getTransactionId());
            }
            if (this.request.getRewards() != null) {
                JSONArray array = new JSONArray();
                for(Reward item : this.request.getRewards())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("rewards", array);
            }
            if (this.request.getIsComplete() != null) {
                json.put("isComplete", this.request.getIsComplete());
            }
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
     * ユーザIDを指定してクエストを完了<br>
     *   <br>
     *   開始時に受け取ったクエストにおいて入手可能な報酬とその数量の"最大値"のうち、クエスト内で実際に入手した報酬を rewards で報告します。<br>
     *   isComplete にはクエストをクリアできたかを報告します。クエストに失敗した場合、rewards の値は無視してクエストに設定された失敗した場合の報酬が付与されます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void endByUserIdAsync(
            EndByUserIdRequest request,
            AsyncAction<AsyncResult<EndByUserIdResult>> callback
    ) {
        EndByUserIdTask task = new EndByUserIdTask(request, callback, EndByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエストを完了<br>
     *   <br>
     *   開始時に受け取ったクエストにおいて入手可能な報酬とその数量の"最大値"のうち、クエスト内で実際に入手した報酬を rewards で報告します。<br>
     *   isComplete にはクエストをクリアできたかを報告します。クエストに失敗した場合、rewards の値は無視してクエストに設定された失敗した場合の報酬が付与されます。<br>
     *
     * @param request リクエストパラメータ
     */
    public EndByUserIdResult endByUserId(
            EndByUserIdRequest request
    ) {
        final AsyncResult<EndByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        endByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProgressTask extends Gs2RestSessionTask<DeleteProgressResult> {
        private DeleteProgressRequest request;

        public DeleteProgressTask(
            DeleteProgressRequest request,
            AsyncAction<AsyncResult<DeleteProgressResult>> userCallback,
            Class<DeleteProgressResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress";

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
     * クエスト挑戦を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProgressAsync(
            DeleteProgressRequest request,
            AsyncAction<AsyncResult<DeleteProgressResult>> callback
    ) {
        DeleteProgressTask task = new DeleteProgressTask(request, callback, DeleteProgressResult.class);
        session.execute(task);
    }

    /**
     * クエスト挑戦を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProgressResult deleteProgress(
            DeleteProgressRequest request
    ) {
        final AsyncResult<DeleteProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProgressAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProgressByUserIdTask extends Gs2RestSessionTask<DeleteProgressByUserIdResult> {
        private DeleteProgressByUserIdRequest request;

        public DeleteProgressByUserIdTask(
            DeleteProgressByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProgressByUserIdResult>> userCallback,
            Class<DeleteProgressByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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
     * ユーザIDを指定してクエスト挑戦を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProgressByUserIdAsync(
            DeleteProgressByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProgressByUserIdResult>> callback
    ) {
        DeleteProgressByUserIdTask task = new DeleteProgressByUserIdTask(request, callback, DeleteProgressByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエスト挑戦を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProgressByUserIdResult deleteProgressByUserId(
            DeleteProgressByUserIdRequest request
    ) {
        final AsyncResult<DeleteProgressByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProgressByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateProgressByStampSheetTask extends Gs2RestSessionTask<CreateProgressByStampSheetResult> {
        private CreateProgressByStampSheetRequest request;

        public CreateProgressByStampSheetTask(
            CreateProgressByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateProgressByStampSheetResult>> userCallback,
            Class<CreateProgressByStampSheetResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/progress/create";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getStampSheet() != null) {
                json.put("stampSheet", this.request.getStampSheet());
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
     * スタンプシートでクエストを開始<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createProgressByStampSheetAsync(
            CreateProgressByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateProgressByStampSheetResult>> callback
    ) {
        CreateProgressByStampSheetTask task = new CreateProgressByStampSheetTask(request, callback, CreateProgressByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでクエストを開始<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateProgressByStampSheetResult createProgressByStampSheet(
            CreateProgressByStampSheetRequest request
    ) {
        final AsyncResult<CreateProgressByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        createProgressByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProgressByStampTaskTask extends Gs2RestSessionTask<DeleteProgressByStampTaskResult> {
        private DeleteProgressByStampTaskRequest request;

        public DeleteProgressByStampTaskTask(
            DeleteProgressByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteProgressByStampTaskResult>> userCallback,
            Class<DeleteProgressByStampTaskResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/progress/delete";

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
     * スタンプタスクで クエスト挑戦 を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProgressByStampTaskAsync(
            DeleteProgressByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteProgressByStampTaskResult>> callback
    ) {
        DeleteProgressByStampTaskTask task = new DeleteProgressByStampTaskTask(request, callback, DeleteProgressByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプタスクで クエスト挑戦 を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProgressByStampTaskResult deleteProgressByStampTask(
            DeleteProgressByStampTaskRequest request
    ) {
        final AsyncResult<DeleteProgressByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProgressByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCompletedQuestListsTask extends Gs2RestSessionTask<DescribeCompletedQuestListsResult> {
        private DescribeCompletedQuestListsRequest request;

        public DescribeCompletedQuestListsTask(
            DescribeCompletedQuestListsRequest request,
            AsyncAction<AsyncResult<DescribeCompletedQuestListsResult>> userCallback,
            Class<DescribeCompletedQuestListsResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/completed";

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
     * クエスト進行の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCompletedQuestListsAsync(
            DescribeCompletedQuestListsRequest request,
            AsyncAction<AsyncResult<DescribeCompletedQuestListsResult>> callback
    ) {
        DescribeCompletedQuestListsTask task = new DescribeCompletedQuestListsTask(request, callback, DescribeCompletedQuestListsResult.class);
        session.execute(task);
    }

    /**
     * クエスト進行の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCompletedQuestListsResult describeCompletedQuestLists(
            DescribeCompletedQuestListsRequest request
    ) {
        final AsyncResult<DescribeCompletedQuestListsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCompletedQuestListsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCompletedQuestListsByUserIdTask extends Gs2RestSessionTask<DescribeCompletedQuestListsByUserIdResult> {
        private DescribeCompletedQuestListsByUserIdRequest request;

        public DescribeCompletedQuestListsByUserIdTask(
            DescribeCompletedQuestListsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCompletedQuestListsByUserIdResult>> userCallback,
            Class<DescribeCompletedQuestListsByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/completed";

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
     * ユーザIDを指定してクエスト進行の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCompletedQuestListsByUserIdAsync(
            DescribeCompletedQuestListsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCompletedQuestListsByUserIdResult>> callback
    ) {
        DescribeCompletedQuestListsByUserIdTask task = new DescribeCompletedQuestListsByUserIdTask(request, callback, DescribeCompletedQuestListsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエスト進行の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCompletedQuestListsByUserIdResult describeCompletedQuestListsByUserId(
            DescribeCompletedQuestListsByUserIdRequest request
    ) {
        final AsyncResult<DescribeCompletedQuestListsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCompletedQuestListsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCompletedQuestListTask extends Gs2RestSessionTask<GetCompletedQuestListResult> {
        private GetCompletedQuestListRequest request;

        public GetCompletedQuestListTask(
            GetCompletedQuestListRequest request,
            AsyncAction<AsyncResult<GetCompletedQuestListResult>> userCallback,
            Class<GetCompletedQuestListResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/completed/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
     * クエスト進行を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCompletedQuestListAsync(
            GetCompletedQuestListRequest request,
            AsyncAction<AsyncResult<GetCompletedQuestListResult>> callback
    ) {
        GetCompletedQuestListTask task = new GetCompletedQuestListTask(request, callback, GetCompletedQuestListResult.class);
        session.execute(task);
    }

    /**
     * クエスト進行を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCompletedQuestListResult getCompletedQuestList(
            GetCompletedQuestListRequest request
    ) {
        final AsyncResult<GetCompletedQuestListResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCompletedQuestListAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCompletedQuestListByUserIdTask extends Gs2RestSessionTask<GetCompletedQuestListByUserIdResult> {
        private GetCompletedQuestListByUserIdRequest request;

        public GetCompletedQuestListByUserIdTask(
            GetCompletedQuestListByUserIdRequest request,
            AsyncAction<AsyncResult<GetCompletedQuestListByUserIdResult>> userCallback,
            Class<GetCompletedQuestListByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/completed/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
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
     * ユーザIDを指定してクエスト進行を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCompletedQuestListByUserIdAsync(
            GetCompletedQuestListByUserIdRequest request,
            AsyncAction<AsyncResult<GetCompletedQuestListByUserIdResult>> callback
    ) {
        GetCompletedQuestListByUserIdTask task = new GetCompletedQuestListByUserIdTask(request, callback, GetCompletedQuestListByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエスト進行を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCompletedQuestListByUserIdResult getCompletedQuestListByUserId(
            GetCompletedQuestListByUserIdRequest request
    ) {
        final AsyncResult<GetCompletedQuestListByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCompletedQuestListByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCompletedQuestListByUserIdTask extends Gs2RestSessionTask<DeleteCompletedQuestListByUserIdResult> {
        private DeleteCompletedQuestListByUserIdRequest request;

        public DeleteCompletedQuestListByUserIdTask(
            DeleteCompletedQuestListByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCompletedQuestListByUserIdResult>> userCallback,
            Class<DeleteCompletedQuestListByUserIdResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/completed/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
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
     * ユーザIDを指定してクエスト進行を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteCompletedQuestListByUserIdAsync(
            DeleteCompletedQuestListByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCompletedQuestListByUserIdResult>> callback
    ) {
        DeleteCompletedQuestListByUserIdTask task = new DeleteCompletedQuestListByUserIdTask(request, callback, DeleteCompletedQuestListByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してクエスト進行を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteCompletedQuestListByUserIdResult deleteCompletedQuestListByUserId(
            DeleteCompletedQuestListByUserIdRequest request
    ) {
        final AsyncResult<DeleteCompletedQuestListByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCompletedQuestListByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeQuestGroupModelsTask extends Gs2RestSessionTask<DescribeQuestGroupModelsResult> {
        private DescribeQuestGroupModelsRequest request;

        public DescribeQuestGroupModelsTask(
            DescribeQuestGroupModelsRequest request,
            AsyncAction<AsyncResult<DescribeQuestGroupModelsResult>> userCallback,
            Class<DescribeQuestGroupModelsResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group";

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
     * クエストグループの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeQuestGroupModelsAsync(
            DescribeQuestGroupModelsRequest request,
            AsyncAction<AsyncResult<DescribeQuestGroupModelsResult>> callback
    ) {
        DescribeQuestGroupModelsTask task = new DescribeQuestGroupModelsTask(request, callback, DescribeQuestGroupModelsResult.class);
        session.execute(task);
    }

    /**
     * クエストグループの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeQuestGroupModelsResult describeQuestGroupModels(
            DescribeQuestGroupModelsRequest request
    ) {
        final AsyncResult<DescribeQuestGroupModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeQuestGroupModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetQuestGroupModelTask extends Gs2RestSessionTask<GetQuestGroupModelResult> {
        private GetQuestGroupModelRequest request;

        public GetQuestGroupModelTask(
            GetQuestGroupModelRequest request,
            AsyncAction<AsyncResult<GetQuestGroupModelResult>> userCallback,
            Class<GetQuestGroupModelResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{questGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
     * クエストグループを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getQuestGroupModelAsync(
            GetQuestGroupModelRequest request,
            AsyncAction<AsyncResult<GetQuestGroupModelResult>> callback
    ) {
        GetQuestGroupModelTask task = new GetQuestGroupModelTask(request, callback, GetQuestGroupModelResult.class);
        session.execute(task);
    }

    /**
     * クエストグループを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetQuestGroupModelResult getQuestGroupModel(
            GetQuestGroupModelRequest request
    ) {
        final AsyncResult<GetQuestGroupModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getQuestGroupModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeQuestModelsTask extends Gs2RestSessionTask<DescribeQuestModelsResult> {
        private DescribeQuestModelsRequest request;

        public DescribeQuestModelsTask(
            DescribeQuestModelsRequest request,
            AsyncAction<AsyncResult<DescribeQuestModelsResult>> userCallback,
            Class<DescribeQuestModelsResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{questGroupName}/quest";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));

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
     * クエストモデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeQuestModelsAsync(
            DescribeQuestModelsRequest request,
            AsyncAction<AsyncResult<DescribeQuestModelsResult>> callback
    ) {
        DescribeQuestModelsTask task = new DescribeQuestModelsTask(request, callback, DescribeQuestModelsResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeQuestModelsResult describeQuestModels(
            DescribeQuestModelsRequest request
    ) {
        final AsyncResult<DescribeQuestModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeQuestModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetQuestModelTask extends Gs2RestSessionTask<GetQuestModelResult> {
        private GetQuestModelRequest request;

        public GetQuestModelTask(
            GetQuestModelRequest request,
            AsyncAction<AsyncResult<GetQuestModelResult>> userCallback,
            Class<GetQuestModelResult> clazz
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
                .replace("{service}", "quest")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{questGroupName}/quest/{questName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{questGroupName}", this.request.getQuestGroupName() == null|| this.request.getQuestGroupName().length() == 0 ? "null" : String.valueOf(this.request.getQuestGroupName()));
            url = url.replace("{questName}", this.request.getQuestName() == null|| this.request.getQuestName().length() == 0 ? "null" : String.valueOf(this.request.getQuestName()));

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
     * クエストモデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getQuestModelAsync(
            GetQuestModelRequest request,
            AsyncAction<AsyncResult<GetQuestModelResult>> callback
    ) {
        GetQuestModelTask task = new GetQuestModelTask(request, callback, GetQuestModelResult.class);
        session.execute(task);
    }

    /**
     * クエストモデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetQuestModelResult getQuestModel(
            GetQuestModelRequest request
    ) {
        final AsyncResult<GetQuestModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getQuestModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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