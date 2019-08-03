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
import io.gs2.mission.request.*;
import io.gs2.mission.result.*;
import io.gs2.mission.model.*;

/**
 * GS2 Mission API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2MissionRestClient extends AbstractGs2Client<Gs2MissionRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2MissionRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "mission")
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
            if (this.request.getMissionCompleteTriggerScriptId() != null) {
                json.put("missionCompleteTriggerScriptId", this.request.getMissionCompleteTriggerScriptId());
            }
            if (this.request.getMissionCompleteDoneTriggerScriptId() != null) {
                json.put("missionCompleteDoneTriggerScriptId", this.request.getMissionCompleteDoneTriggerScriptId());
            }
            if (this.request.getMissionCompleteDoneTriggerQueueNamespaceId() != null) {
                json.put("missionCompleteDoneTriggerQueueNamespaceId", this.request.getMissionCompleteDoneTriggerQueueNamespaceId());
            }
            if (this.request.getCounterIncrementTriggerScriptId() != null) {
                json.put("counterIncrementTriggerScriptId", this.request.getCounterIncrementTriggerScriptId());
            }
            if (this.request.getCounterIncrementDoneTriggerScriptId() != null) {
                json.put("counterIncrementDoneTriggerScriptId", this.request.getCounterIncrementDoneTriggerScriptId());
            }
            if (this.request.getCounterIncrementDoneTriggerQueueNamespaceId() != null) {
                json.put("counterIncrementDoneTriggerQueueNamespaceId", this.request.getCounterIncrementDoneTriggerQueueNamespaceId());
            }
            if (this.request.getReceiveRewardsTriggerScriptId() != null) {
                json.put("receiveRewardsTriggerScriptId", this.request.getReceiveRewardsTriggerScriptId());
            }
            if (this.request.getReceiveRewardsDoneTriggerScriptId() != null) {
                json.put("receiveRewardsDoneTriggerScriptId", this.request.getReceiveRewardsDoneTriggerScriptId());
            }
            if (this.request.getReceiveRewardsDoneTriggerQueueNamespaceId() != null) {
                json.put("receiveRewardsDoneTriggerQueueNamespaceId", this.request.getReceiveRewardsDoneTriggerQueueNamespaceId());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMissionCompleteTriggerScriptId() != null) {
                json.put("missionCompleteTriggerScriptId", this.request.getMissionCompleteTriggerScriptId());
            }
            if (this.request.getMissionCompleteDoneTriggerScriptId() != null) {
                json.put("missionCompleteDoneTriggerScriptId", this.request.getMissionCompleteDoneTriggerScriptId());
            }
            if (this.request.getMissionCompleteDoneTriggerQueueNamespaceId() != null) {
                json.put("missionCompleteDoneTriggerQueueNamespaceId", this.request.getMissionCompleteDoneTriggerQueueNamespaceId());
            }
            if (this.request.getCounterIncrementTriggerScriptId() != null) {
                json.put("counterIncrementTriggerScriptId", this.request.getCounterIncrementTriggerScriptId());
            }
            if (this.request.getCounterIncrementDoneTriggerScriptId() != null) {
                json.put("counterIncrementDoneTriggerScriptId", this.request.getCounterIncrementDoneTriggerScriptId());
            }
            if (this.request.getCounterIncrementDoneTriggerQueueNamespaceId() != null) {
                json.put("counterIncrementDoneTriggerQueueNamespaceId", this.request.getCounterIncrementDoneTriggerQueueNamespaceId());
            }
            if (this.request.getReceiveRewardsTriggerScriptId() != null) {
                json.put("receiveRewardsTriggerScriptId", this.request.getReceiveRewardsTriggerScriptId());
            }
            if (this.request.getReceiveRewardsDoneTriggerScriptId() != null) {
                json.put("receiveRewardsDoneTriggerScriptId", this.request.getReceiveRewardsDoneTriggerScriptId());
            }
            if (this.request.getReceiveRewardsDoneTriggerQueueNamespaceId() != null) {
                json.put("receiveRewardsDoneTriggerQueueNamespaceId", this.request.getReceiveRewardsDoneTriggerQueueNamespaceId());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
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
                .replace("{service}", "mission")
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
                .replace("{service}", "mission")
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
     * 現在有効なミッションのマスターデータをエクスポートします<br>
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
     * 現在有効なミッションのマスターデータをエクスポートします<br>
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

    class GetCurrentMissionMasterTask extends Gs2RestSessionTask<GetCurrentMissionMasterResult> {
        private GetCurrentMissionMasterRequest request;

        public GetCurrentMissionMasterTask(
            GetCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentMissionMasterResult>> userCallback,
            Class<GetCurrentMissionMasterResult> clazz
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
                .replace("{service}", "mission")
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
     * 現在有効な現在有効なミッションを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentMissionMasterAsync(
            GetCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentMissionMasterResult>> callback
    ) {
        GetCurrentMissionMasterTask task = new GetCurrentMissionMasterTask(request, callback, GetCurrentMissionMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効なミッションを取得します<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateCurrentMissionMasterResult>> userCallback,
            Class<UpdateCurrentMissionMasterResult> clazz
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
                .replace("{service}", "mission")
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
     * 現在有効な現在有効なミッションを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentMissionMasterAsync(
            UpdateCurrentMissionMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentMissionMasterResult>> callback
    ) {
        UpdateCurrentMissionMasterTask task = new UpdateCurrentMissionMasterTask(request, callback, UpdateCurrentMissionMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効なミッションを更新します<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeMissionTaskModelMastersTask extends Gs2RestSessionTask<DescribeMissionTaskModelMastersResult> {
        private DescribeMissionTaskModelMastersRequest request;

        public DescribeMissionTaskModelMastersTask(
            DescribeMissionTaskModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelMastersResult>> userCallback,
            Class<DescribeMissionTaskModelMastersResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * ミッションタスクマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMissionTaskModelMastersAsync(
            DescribeMissionTaskModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelMastersResult>> callback
    ) {
        DescribeMissionTaskModelMastersTask task = new DescribeMissionTaskModelMastersTask(request, callback, DescribeMissionTaskModelMastersResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateMissionTaskModelMasterResult>> userCallback,
            Class<CreateMissionTaskModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getCounterName() != null) {
                json.put("counterName", this.request.getCounterName());
            }
            if (this.request.getResetType() != null) {
                json.put("resetType", this.request.getResetType());
            }
            if (this.request.getTargetValue() != null) {
                json.put("targetValue", this.request.getTargetValue());
            }
            if (this.request.getCompleteAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getCompleteAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("completeAcquireActions", array);
            }
            if (this.request.getChallengePeriodEventId() != null) {
                json.put("challengePeriodEventId", this.request.getChallengePeriodEventId());
            }
            if (this.request.getPremiseMissionTaskName() != null) {
                json.put("premiseMissionTaskName", this.request.getPremiseMissionTaskName());
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
     * ミッションタスクマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createMissionTaskModelMasterAsync(
            CreateMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMissionTaskModelMasterResult>> callback
    ) {
        CreateMissionTaskModelMasterTask task = new CreateMissionTaskModelMasterTask(request, callback, CreateMissionTaskModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetMissionTaskModelMasterResult>> userCallback,
            Class<GetMissionTaskModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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
     * ミッションタスクマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMissionTaskModelMasterAsync(
            GetMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<GetMissionTaskModelMasterResult>> callback
    ) {
        GetMissionTaskModelMasterTask task = new GetMissionTaskModelMasterTask(request, callback, GetMissionTaskModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateMissionTaskModelMasterResult>> userCallback,
            Class<UpdateMissionTaskModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getCounterName() != null) {
                json.put("counterName", this.request.getCounterName());
            }
            if (this.request.getResetType() != null) {
                json.put("resetType", this.request.getResetType());
            }
            if (this.request.getTargetValue() != null) {
                json.put("targetValue", this.request.getTargetValue());
            }
            if (this.request.getCompleteAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getCompleteAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("completeAcquireActions", array);
            }
            if (this.request.getChallengePeriodEventId() != null) {
                json.put("challengePeriodEventId", this.request.getChallengePeriodEventId());
            }
            if (this.request.getPremiseMissionTaskName() != null) {
                json.put("premiseMissionTaskName", this.request.getPremiseMissionTaskName());
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
     * ミッションタスクマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateMissionTaskModelMasterAsync(
            UpdateMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMissionTaskModelMasterResult>> callback
    ) {
        UpdateMissionTaskModelMasterTask task = new UpdateMissionTaskModelMasterTask(request, callback, UpdateMissionTaskModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteMissionTaskModelMasterResult>> userCallback,
            Class<DeleteMissionTaskModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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
     * ミッションタスクマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMissionTaskModelMasterAsync(
            DeleteMissionTaskModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMissionTaskModelMasterResult>> callback
    ) {
        DeleteMissionTaskModelMasterTask task = new DeleteMissionTaskModelMasterTask(request, callback, DeleteMissionTaskModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeCountersTask extends Gs2RestSessionTask<DescribeCountersResult> {
        private DescribeCountersRequest request;

        public DescribeCountersTask(
            DescribeCountersRequest request,
            AsyncAction<AsyncResult<DescribeCountersResult>> userCallback,
            Class<DescribeCountersResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter";

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
     * カウンターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCountersAsync(
            DescribeCountersRequest request,
            AsyncAction<AsyncResult<DescribeCountersResult>> callback
    ) {
        DescribeCountersTask task = new DescribeCountersTask(request, callback, DescribeCountersResult.class);
        session.execute(task);
    }

    /**
     * カウンターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeCountersByUserIdResult>> userCallback,
            Class<DescribeCountersByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter";

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
     * ユーザIDを指定してカウンターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCountersByUserIdAsync(
            DescribeCountersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCountersByUserIdResult>> callback
    ) {
        DescribeCountersByUserIdTask task = new DescribeCountersByUserIdTask(request, callback, DescribeCountersByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してカウンターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<IncreaseCounterByUserIdResult>> userCallback,
            Class<IncreaseCounterByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getValue() != null) {
                json.put("value", this.request.getValue());
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
     * カウンターに加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void increaseCounterByUserIdAsync(
            IncreaseCounterByUserIdRequest request,
            AsyncAction<AsyncResult<IncreaseCounterByUserIdResult>> callback
    ) {
        IncreaseCounterByUserIdTask task = new IncreaseCounterByUserIdTask(request, callback, IncreaseCounterByUserIdResult.class);
        session.execute(task);
    }

    /**
     * カウンターに加算<br>
     *
     * @param request リクエストパラメータ
     */
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

    class GetCounterTask extends Gs2RestSessionTask<GetCounterResult> {
        private GetCounterRequest request;

        public GetCounterTask(
            GetCounterRequest request,
            AsyncAction<AsyncResult<GetCounterResult>> userCallback,
            Class<GetCounterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * カウンターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCounterAsync(
            GetCounterRequest request,
            AsyncAction<AsyncResult<GetCounterResult>> callback
    ) {
        GetCounterTask task = new GetCounterTask(request, callback, GetCounterResult.class);
        session.execute(task);
    }

    /**
     * カウンターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetCounterByUserIdResult>> userCallback,
            Class<GetCounterByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
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
     * ユーザIDを指定してカウンターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCounterByUserIdAsync(
            GetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<GetCounterByUserIdResult>> callback
    ) {
        GetCounterByUserIdTask task = new GetCounterByUserIdTask(request, callback, GetCounterByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してカウンターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DeleteCounterByUserIdTask extends Gs2RestSessionTask<DeleteCounterByUserIdResult> {
        private DeleteCounterByUserIdRequest request;

        public DeleteCounterByUserIdTask(
            DeleteCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCounterByUserIdResult>> userCallback,
            Class<DeleteCounterByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * カウンターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteCounterByUserIdAsync(
            DeleteCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCounterByUserIdResult>> callback
    ) {
        DeleteCounterByUserIdTask task = new DeleteCounterByUserIdTask(request, callback, DeleteCounterByUserIdResult.class);
        session.execute(task);
    }

    /**
     * カウンターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<IncreaseByStampSheetResult>> userCallback,
            Class<IncreaseByStampSheetResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/increase";

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
     * カウンター加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void increaseByStampSheetAsync(
            IncreaseByStampSheetRequest request,
            AsyncAction<AsyncResult<IncreaseByStampSheetResult>> callback
    ) {
        IncreaseByStampSheetTask task = new IncreaseByStampSheetTask(request, callback, IncreaseByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * カウンター加算<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeMissionGroupModelsTask extends Gs2RestSessionTask<DescribeMissionGroupModelsResult> {
        private DescribeMissionGroupModelsRequest request;

        public DescribeMissionGroupModelsTask(
            DescribeMissionGroupModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelsResult>> userCallback,
            Class<DescribeMissionGroupModelsResult> clazz
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
                .replace("{service}", "mission")
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
     * ミッショングループの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMissionGroupModelsAsync(
            DescribeMissionGroupModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelsResult>> callback
    ) {
        DescribeMissionGroupModelsTask task = new DescribeMissionGroupModelsTask(request, callback, DescribeMissionGroupModelsResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetMissionGroupModelResult>> userCallback,
            Class<GetMissionGroupModelResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * ミッショングループを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMissionGroupModelAsync(
            GetMissionGroupModelRequest request,
            AsyncAction<AsyncResult<GetMissionGroupModelResult>> callback
    ) {
        GetMissionGroupModelTask task = new GetMissionGroupModelTask(request, callback, GetMissionGroupModelResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループを取得<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeMissionGroupModelMastersTask extends Gs2RestSessionTask<DescribeMissionGroupModelMastersResult> {
        private DescribeMissionGroupModelMastersRequest request;

        public DescribeMissionGroupModelMastersTask(
            DescribeMissionGroupModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelMastersResult>> userCallback,
            Class<DescribeMissionGroupModelMastersResult> clazz
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
                .replace("{service}", "mission")
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
     * ミッショングループマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMissionGroupModelMastersAsync(
            DescribeMissionGroupModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMissionGroupModelMastersResult>> callback
    ) {
        DescribeMissionGroupModelMastersTask task = new DescribeMissionGroupModelMastersTask(request, callback, DescribeMissionGroupModelMastersResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateMissionGroupModelMasterResult>> userCallback,
            Class<CreateMissionGroupModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getCompleteNotificationNamespaceId() != null) {
                json.put("completeNotificationNamespaceId", this.request.getCompleteNotificationNamespaceId());
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
     * ミッショングループマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createMissionGroupModelMasterAsync(
            CreateMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMissionGroupModelMasterResult>> callback
    ) {
        CreateMissionGroupModelMasterTask task = new CreateMissionGroupModelMasterTask(request, callback, CreateMissionGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetMissionGroupModelMasterResult>> userCallback,
            Class<GetMissionGroupModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * ミッショングループマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMissionGroupModelMasterAsync(
            GetMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<GetMissionGroupModelMasterResult>> callback
    ) {
        GetMissionGroupModelMasterTask task = new GetMissionGroupModelMasterTask(request, callback, GetMissionGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateMissionGroupModelMasterResult>> userCallback,
            Class<UpdateMissionGroupModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getCompleteNotificationNamespaceId() != null) {
                json.put("completeNotificationNamespaceId", this.request.getCompleteNotificationNamespaceId());
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
     * ミッショングループマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateMissionGroupModelMasterAsync(
            UpdateMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMissionGroupModelMasterResult>> callback
    ) {
        UpdateMissionGroupModelMasterTask task = new UpdateMissionGroupModelMasterTask(request, callback, UpdateMissionGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteMissionGroupModelMasterResult>> userCallback,
            Class<DeleteMissionGroupModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * ミッショングループマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMissionGroupModelMasterAsync(
            DeleteMissionGroupModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMissionGroupModelMasterResult>> callback
    ) {
        DeleteMissionGroupModelMasterTask task = new DeleteMissionGroupModelMasterTask(request, callback, DeleteMissionGroupModelMasterResult.class);
        session.execute(task);
    }

    /**
     * ミッショングループマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeCompletesTask extends Gs2RestSessionTask<DescribeCompletesResult> {
        private DescribeCompletesRequest request;

        public DescribeCompletesTask(
            DescribeCompletesRequest request,
            AsyncAction<AsyncResult<DescribeCompletesResult>> userCallback,
            Class<DescribeCompletesResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete";

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
     * 達成状況の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCompletesAsync(
            DescribeCompletesRequest request,
            AsyncAction<AsyncResult<DescribeCompletesResult>> callback
    ) {
        DescribeCompletesTask task = new DescribeCompletesTask(request, callback, DescribeCompletesResult.class);
        session.execute(task);
    }

    /**
     * 達成状況の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeCompletesByUserIdResult>> userCallback,
            Class<DescribeCompletesByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete";

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
     * ユーザIDを指定して達成状況の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCompletesByUserIdAsync(
            DescribeCompletesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCompletesByUserIdResult>> callback
    ) {
        DescribeCompletesByUserIdTask task = new DescribeCompletesByUserIdTask(request, callback, DescribeCompletesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して達成状況の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CompleteResult>> userCallback,
            Class<CompleteResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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
     * ミッション達成報酬を受領するためのスタンプシートを発行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void completeAsync(
            CompleteRequest request,
            AsyncAction<AsyncResult<CompleteResult>> callback
    ) {
        CompleteTask task = new CompleteTask(request, callback, CompleteResult.class);
        session.execute(task);
    }

    /**
     * ミッション達成報酬を受領するためのスタンプシートを発行<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CompleteByUserIdResult>> userCallback,
            Class<CompleteByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
     * 達成状況を新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void completeByUserIdAsync(
            CompleteByUserIdRequest request,
            AsyncAction<AsyncResult<CompleteByUserIdResult>> callback
    ) {
        CompleteByUserIdTask task = new CompleteByUserIdTask(request, callback, CompleteByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 達成状況を新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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

    class ReceiveByUserIdTask extends Gs2RestSessionTask<ReceiveByUserIdResult> {
        private ReceiveByUserIdRequest request;

        public ReceiveByUserIdTask(
            ReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveByUserIdResult>> userCallback,
            Class<ReceiveByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}/task/{missionTaskName}/receive";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
     * ミッション達成報酬を受領する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void receiveByUserIdAsync(
            ReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveByUserIdResult>> callback
    ) {
        ReceiveByUserIdTask task = new ReceiveByUserIdTask(request, callback, ReceiveByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ミッション達成報酬を受領する<br>
     *
     * @param request リクエストパラメータ
     */
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

    class GetCompleteTask extends Gs2RestSessionTask<GetCompleteResult> {
        private GetCompleteRequest request;

        public GetCompleteTask(
            GetCompleteRequest request,
            AsyncAction<AsyncResult<GetCompleteResult>> userCallback,
            Class<GetCompleteResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/complete/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * 達成状況を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCompleteAsync(
            GetCompleteRequest request,
            AsyncAction<AsyncResult<GetCompleteResult>> callback
    ) {
        GetCompleteTask task = new GetCompleteTask(request, callback, GetCompleteResult.class);
        session.execute(task);
    }

    /**
     * 達成状況を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetCompleteByUserIdResult>> userCallback,
            Class<GetCompleteByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
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
     * ユーザIDを指定して達成状況を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCompleteByUserIdAsync(
            GetCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<GetCompleteByUserIdResult>> callback
    ) {
        GetCompleteByUserIdTask task = new GetCompleteByUserIdTask(request, callback, GetCompleteByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して達成状況を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteCompleteByUserIdResult>> userCallback,
            Class<DeleteCompleteByUserIdResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/complete/group/{missionGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * 達成状況を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteCompleteByUserIdAsync(
            DeleteCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCompleteByUserIdResult>> callback
    ) {
        DeleteCompleteByUserIdTask task = new DeleteCompleteByUserIdTask(request, callback, DeleteCompleteByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 達成状況を削除<br>
     *
     * @param request リクエストパラメータ
     */
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

    class ReceiveByStampTaskTask extends Gs2RestSessionTask<ReceiveByStampTaskResult> {
        private ReceiveByStampTaskRequest request;

        public ReceiveByStampTaskTask(
            ReceiveByStampTaskRequest request,
            AsyncAction<AsyncResult<ReceiveByStampTaskResult>> userCallback,
            Class<ReceiveByStampTaskResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/receive";

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
     * 達成状況を作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void receiveByStampTaskAsync(
            ReceiveByStampTaskRequest request,
            AsyncAction<AsyncResult<ReceiveByStampTaskResult>> callback
    ) {
        ReceiveByStampTaskTask task = new ReceiveByStampTaskTask(request, callback, ReceiveByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * 達成状況を作成<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeCounterModelMastersTask extends Gs2RestSessionTask<DescribeCounterModelMastersResult> {
        private DescribeCounterModelMastersRequest request;

        public DescribeCounterModelMastersTask(
            DescribeCounterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelMastersResult>> userCallback,
            Class<DescribeCounterModelMastersResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter";

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
     * カウンターの種類マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCounterModelMastersAsync(
            DescribeCounterModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelMastersResult>> callback
    ) {
        DescribeCounterModelMastersTask task = new DescribeCounterModelMastersTask(request, callback, DescribeCounterModelMastersResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateCounterModelMasterResult>> userCallback,
            Class<CreateCounterModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getScopes() != null) {
                JSONArray array = new JSONArray();
                for(CounterScopeModel item : this.request.getScopes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("scopes", array);
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
     * カウンターの種類マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createCounterModelMasterAsync(
            CreateCounterModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCounterModelMasterResult>> callback
    ) {
        CreateCounterModelMasterTask task = new CreateCounterModelMasterTask(request, callback, CreateCounterModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetCounterModelMasterResult>> userCallback,
            Class<GetCounterModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * カウンターの種類マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCounterModelMasterAsync(
            GetCounterModelMasterRequest request,
            AsyncAction<AsyncResult<GetCounterModelMasterResult>> callback
    ) {
        GetCounterModelMasterTask task = new GetCounterModelMasterTask(request, callback, GetCounterModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateCounterModelMasterResult>> userCallback,
            Class<UpdateCounterModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getScopes() != null) {
                JSONArray array = new JSONArray();
                for(CounterScopeModel item : this.request.getScopes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("scopes", array);
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
     * カウンターの種類マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCounterModelMasterAsync(
            UpdateCounterModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCounterModelMasterResult>> callback
    ) {
        UpdateCounterModelMasterTask task = new UpdateCounterModelMasterTask(request, callback, UpdateCounterModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteCounterModelMasterResult>> userCallback,
            Class<DeleteCounterModelMasterResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * カウンターの種類マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteCounterModelMasterAsync(
            DeleteCounterModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCounterModelMasterResult>> callback
    ) {
        DeleteCounterModelMasterTask task = new DeleteCounterModelMasterTask(request, callback, DeleteCounterModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeCounterModelsTask extends Gs2RestSessionTask<DescribeCounterModelsResult> {
        private DescribeCounterModelsRequest request;

        public DescribeCounterModelsTask(
            DescribeCounterModelsRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelsResult>> userCallback,
            Class<DescribeCounterModelsResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/counter";

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
     * カウンターの種類の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCounterModelsAsync(
            DescribeCounterModelsRequest request,
            AsyncAction<AsyncResult<DescribeCounterModelsResult>> callback
    ) {
        DescribeCounterModelsTask task = new DescribeCounterModelsTask(request, callback, DescribeCounterModelsResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetCounterModelResult>> userCallback,
            Class<GetCounterModelResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/counter/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * カウンターの種類を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCounterModelAsync(
            GetCounterModelRequest request,
            AsyncAction<AsyncResult<GetCounterModelResult>> callback
    ) {
        GetCounterModelTask task = new GetCounterModelTask(request, callback, GetCounterModelResult.class);
        session.execute(task);
    }

    /**
     * カウンターの種類を取得<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeMissionTaskModelsTask extends Gs2RestSessionTask<DescribeMissionTaskModelsResult> {
        private DescribeMissionTaskModelsRequest request;

        public DescribeMissionTaskModelsTask(
            DescribeMissionTaskModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelsResult>> userCallback,
            Class<DescribeMissionTaskModelsResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{missionGroupName}/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));

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
     * ミッションタスクの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMissionTaskModelsAsync(
            DescribeMissionTaskModelsRequest request,
            AsyncAction<AsyncResult<DescribeMissionTaskModelsResult>> callback
    ) {
        DescribeMissionTaskModelsTask task = new DescribeMissionTaskModelsTask(request, callback, DescribeMissionTaskModelsResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetMissionTaskModelResult>> userCallback,
            Class<GetMissionTaskModelResult> clazz
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
                .replace("{service}", "mission")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/group/{missionGroupName}/task/{missionTaskName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{missionGroupName}", this.request.getMissionGroupName() == null|| this.request.getMissionGroupName().length() == 0 ? "null" : String.valueOf(this.request.getMissionGroupName()));
            url = url.replace("{missionTaskName}", this.request.getMissionTaskName() == null|| this.request.getMissionTaskName().length() == 0 ? "null" : String.valueOf(this.request.getMissionTaskName()));

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
     * ミッションタスクを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMissionTaskModelAsync(
            GetMissionTaskModelRequest request,
            AsyncAction<AsyncResult<GetMissionTaskModelResult>> callback
    ) {
        GetMissionTaskModelTask task = new GetMissionTaskModelTask(request, callback, GetMissionTaskModelResult.class);
        session.execute(task);
    }

    /**
     * ミッションタスクを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
}