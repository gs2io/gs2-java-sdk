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

package io.gs2.schedule;

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
import io.gs2.schedule.request.*;
import io.gs2.schedule.result.*;
import io.gs2.schedule.model.*;

/**
 * GS2 Schedule API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ScheduleRestClient extends AbstractGs2Client<Gs2ScheduleRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2ScheduleRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "schedule")
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
                .replace("{service}", "schedule")
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
                .replace("{service}", "schedule")
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
                .replace("{service}", "schedule")
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
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
                .replace("{service}", "schedule")
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

    class DescribeEventMastersTask extends Gs2RestSessionTask<DescribeEventMastersResult> {
        private DescribeEventMastersRequest request;

        public DescribeEventMastersTask(
            DescribeEventMastersRequest request,
            AsyncAction<AsyncResult<DescribeEventMastersResult>> userCallback,
            Class<DescribeEventMastersResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/event";

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
     * イベントマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEventMastersAsync(
            DescribeEventMastersRequest request,
            AsyncAction<AsyncResult<DescribeEventMastersResult>> callback
    ) {
        DescribeEventMastersTask task = new DescribeEventMastersTask(request, callback, DescribeEventMastersResult.class);
        session.execute(task);
    }

    /**
     * イベントマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEventMastersResult describeEventMasters(
            DescribeEventMastersRequest request
    ) {
        final AsyncResult<DescribeEventMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEventMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateEventMasterTask extends Gs2RestSessionTask<CreateEventMasterResult> {
        private CreateEventMasterRequest request;

        public CreateEventMasterTask(
            CreateEventMasterRequest request,
            AsyncAction<AsyncResult<CreateEventMasterResult>> userCallback,
            Class<CreateEventMasterResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/event";

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
            if (this.request.getScheduleType() != null) {
                json.put("scheduleType", this.request.getScheduleType());
            }
            if (this.request.getAbsoluteBegin() != null) {
                json.put("absoluteBegin", this.request.getAbsoluteBegin());
            }
            if (this.request.getAbsoluteEnd() != null) {
                json.put("absoluteEnd", this.request.getAbsoluteEnd());
            }
            if (this.request.getRelativeTriggerName() != null) {
                json.put("relativeTriggerName", this.request.getRelativeTriggerName());
            }
            if (this.request.getRelativeDuration() != null) {
                json.put("relativeDuration", this.request.getRelativeDuration());
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
     * イベントマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createEventMasterAsync(
            CreateEventMasterRequest request,
            AsyncAction<AsyncResult<CreateEventMasterResult>> callback
    ) {
        CreateEventMasterTask task = new CreateEventMasterTask(request, callback, CreateEventMasterResult.class);
        session.execute(task);
    }

    /**
     * イベントマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateEventMasterResult createEventMaster(
            CreateEventMasterRequest request
    ) {
        final AsyncResult<CreateEventMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createEventMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEventMasterTask extends Gs2RestSessionTask<GetEventMasterResult> {
        private GetEventMasterRequest request;

        public GetEventMasterTask(
            GetEventMasterRequest request,
            AsyncAction<AsyncResult<GetEventMasterResult>> userCallback,
            Class<GetEventMasterResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/event/{eventName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

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
     * イベントマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEventMasterAsync(
            GetEventMasterRequest request,
            AsyncAction<AsyncResult<GetEventMasterResult>> callback
    ) {
        GetEventMasterTask task = new GetEventMasterTask(request, callback, GetEventMasterResult.class);
        session.execute(task);
    }

    /**
     * イベントマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEventMasterResult getEventMaster(
            GetEventMasterRequest request
    ) {
        final AsyncResult<GetEventMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEventMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateEventMasterTask extends Gs2RestSessionTask<UpdateEventMasterResult> {
        private UpdateEventMasterRequest request;

        public UpdateEventMasterTask(
            UpdateEventMasterRequest request,
            AsyncAction<AsyncResult<UpdateEventMasterResult>> userCallback,
            Class<UpdateEventMasterResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/event/{eventName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getScheduleType() != null) {
                json.put("scheduleType", this.request.getScheduleType());
            }
            if (this.request.getAbsoluteBegin() != null) {
                json.put("absoluteBegin", this.request.getAbsoluteBegin());
            }
            if (this.request.getAbsoluteEnd() != null) {
                json.put("absoluteEnd", this.request.getAbsoluteEnd());
            }
            if (this.request.getRelativeTriggerName() != null) {
                json.put("relativeTriggerName", this.request.getRelativeTriggerName());
            }
            if (this.request.getRelativeDuration() != null) {
                json.put("relativeDuration", this.request.getRelativeDuration());
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
     * イベントマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateEventMasterAsync(
            UpdateEventMasterRequest request,
            AsyncAction<AsyncResult<UpdateEventMasterResult>> callback
    ) {
        UpdateEventMasterTask task = new UpdateEventMasterTask(request, callback, UpdateEventMasterResult.class);
        session.execute(task);
    }

    /**
     * イベントマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateEventMasterResult updateEventMaster(
            UpdateEventMasterRequest request
    ) {
        final AsyncResult<UpdateEventMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateEventMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteEventMasterTask extends Gs2RestSessionTask<DeleteEventMasterResult> {
        private DeleteEventMasterRequest request;

        public DeleteEventMasterTask(
            DeleteEventMasterRequest request,
            AsyncAction<AsyncResult<DeleteEventMasterResult>> userCallback,
            Class<DeleteEventMasterResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/event/{eventName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

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
     * イベントマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteEventMasterAsync(
            DeleteEventMasterRequest request,
            AsyncAction<AsyncResult<DeleteEventMasterResult>> callback
    ) {
        DeleteEventMasterTask task = new DeleteEventMasterTask(request, callback, DeleteEventMasterResult.class);
        session.execute(task);
    }

    /**
     * イベントマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteEventMasterResult deleteEventMaster(
            DeleteEventMasterRequest request
    ) {
        final AsyncResult<DeleteEventMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteEventMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeTriggersTask extends Gs2RestSessionTask<DescribeTriggersResult> {
        private DescribeTriggersRequest request;

        public DescribeTriggersTask(
            DescribeTriggersRequest request,
            AsyncAction<AsyncResult<DescribeTriggersResult>> userCallback,
            Class<DescribeTriggersResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/trigger";

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
     * トリガーの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeTriggersAsync(
            DescribeTriggersRequest request,
            AsyncAction<AsyncResult<DescribeTriggersResult>> callback
    ) {
        DescribeTriggersTask task = new DescribeTriggersTask(request, callback, DescribeTriggersResult.class);
        session.execute(task);
    }

    /**
     * トリガーの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeTriggersResult describeTriggers(
            DescribeTriggersRequest request
    ) {
        final AsyncResult<DescribeTriggersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTriggersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeTriggersByUserIdTask extends Gs2RestSessionTask<DescribeTriggersByUserIdResult> {
        private DescribeTriggersByUserIdRequest request;

        public DescribeTriggersByUserIdTask(
            DescribeTriggersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeTriggersByUserIdResult>> userCallback,
            Class<DescribeTriggersByUserIdResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/trigger";

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
     * ユーザIDを指定してトリガーの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeTriggersByUserIdAsync(
            DescribeTriggersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeTriggersByUserIdResult>> callback
    ) {
        DescribeTriggersByUserIdTask task = new DescribeTriggersByUserIdTask(request, callback, DescribeTriggersByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してトリガーの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeTriggersByUserIdResult describeTriggersByUserId(
            DescribeTriggersByUserIdRequest request
    ) {
        final AsyncResult<DescribeTriggersByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTriggersByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetTriggerTask extends Gs2RestSessionTask<GetTriggerResult> {
        private GetTriggerRequest request;

        public GetTriggerTask(
            GetTriggerRequest request,
            AsyncAction<AsyncResult<GetTriggerResult>> userCallback,
            Class<GetTriggerResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/trigger/{triggerName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{triggerName}", this.request.getTriggerName() == null|| this.request.getTriggerName().length() == 0 ? "null" : String.valueOf(this.request.getTriggerName()));

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
     * トリガーを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getTriggerAsync(
            GetTriggerRequest request,
            AsyncAction<AsyncResult<GetTriggerResult>> callback
    ) {
        GetTriggerTask task = new GetTriggerTask(request, callback, GetTriggerResult.class);
        session.execute(task);
    }

    /**
     * トリガーを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetTriggerResult getTrigger(
            GetTriggerRequest request
    ) {
        final AsyncResult<GetTriggerResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTriggerAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetTriggerByUserIdTask extends Gs2RestSessionTask<GetTriggerByUserIdResult> {
        private GetTriggerByUserIdRequest request;

        public GetTriggerByUserIdTask(
            GetTriggerByUserIdRequest request,
            AsyncAction<AsyncResult<GetTriggerByUserIdResult>> userCallback,
            Class<GetTriggerByUserIdResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/trigger/{triggerName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{triggerName}", this.request.getTriggerName() == null|| this.request.getTriggerName().length() == 0 ? "null" : String.valueOf(this.request.getTriggerName()));

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
     * ユーザIDを指定してトリガーを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getTriggerByUserIdAsync(
            GetTriggerByUserIdRequest request,
            AsyncAction<AsyncResult<GetTriggerByUserIdResult>> callback
    ) {
        GetTriggerByUserIdTask task = new GetTriggerByUserIdTask(request, callback, GetTriggerByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してトリガーを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetTriggerByUserIdResult getTriggerByUserId(
            GetTriggerByUserIdRequest request
    ) {
        final AsyncResult<GetTriggerByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTriggerByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class TriggerByUserIdTask extends Gs2RestSessionTask<TriggerByUserIdResult> {
        private TriggerByUserIdRequest request;

        public TriggerByUserIdTask(
            TriggerByUserIdRequest request,
            AsyncAction<AsyncResult<TriggerByUserIdResult>> userCallback,
            Class<TriggerByUserIdResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/trigger/{triggerName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{triggerName}", this.request.getTriggerName() == null|| this.request.getTriggerName().length() == 0 ? "null" : String.valueOf(this.request.getTriggerName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTriggerStrategy() != null) {
                json.put("triggerStrategy", this.request.getTriggerStrategy());
            }
            if (this.request.getTtl() != null) {
                json.put("ttl", this.request.getTtl());
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
     * ユーザIDを指定してトリガーを登録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void triggerByUserIdAsync(
            TriggerByUserIdRequest request,
            AsyncAction<AsyncResult<TriggerByUserIdResult>> callback
    ) {
        TriggerByUserIdTask task = new TriggerByUserIdTask(request, callback, TriggerByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してトリガーを登録<br>
     *
     * @param request リクエストパラメータ
     */
    public TriggerByUserIdResult triggerByUserId(
            TriggerByUserIdRequest request
    ) {
        final AsyncResult<TriggerByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        triggerByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteTriggerTask extends Gs2RestSessionTask<DeleteTriggerResult> {
        private DeleteTriggerRequest request;

        public DeleteTriggerTask(
            DeleteTriggerRequest request,
            AsyncAction<AsyncResult<DeleteTriggerResult>> userCallback,
            Class<DeleteTriggerResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/trigger/{triggerName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{triggerName}", this.request.getTriggerName() == null|| this.request.getTriggerName().length() == 0 ? "null" : String.valueOf(this.request.getTriggerName()));

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
     * トリガーを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteTriggerAsync(
            DeleteTriggerRequest request,
            AsyncAction<AsyncResult<DeleteTriggerResult>> callback
    ) {
        DeleteTriggerTask task = new DeleteTriggerTask(request, callback, DeleteTriggerResult.class);
        session.execute(task);
    }

    /**
     * トリガーを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteTriggerResult deleteTrigger(
            DeleteTriggerRequest request
    ) {
        final AsyncResult<DeleteTriggerResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTriggerAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteTriggerByUserIdTask extends Gs2RestSessionTask<DeleteTriggerByUserIdResult> {
        private DeleteTriggerByUserIdRequest request;

        public DeleteTriggerByUserIdTask(
            DeleteTriggerByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteTriggerByUserIdResult>> userCallback,
            Class<DeleteTriggerByUserIdResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/trigger/{triggerName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{triggerName}", this.request.getTriggerName() == null|| this.request.getTriggerName().length() == 0 ? "null" : String.valueOf(this.request.getTriggerName()));

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
     * ユーザIDを指定してトリガーを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteTriggerByUserIdAsync(
            DeleteTriggerByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteTriggerByUserIdResult>> callback
    ) {
        DeleteTriggerByUserIdTask task = new DeleteTriggerByUserIdTask(request, callback, DeleteTriggerByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してトリガーを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteTriggerByUserIdResult deleteTriggerByUserId(
            DeleteTriggerByUserIdRequest request
    ) {
        final AsyncResult<DeleteTriggerByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTriggerByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeEventsTask extends Gs2RestSessionTask<DescribeEventsResult> {
        private DescribeEventsRequest request;

        public DescribeEventsTask(
            DescribeEventsRequest request,
            AsyncAction<AsyncResult<DescribeEventsResult>> userCallback,
            Class<DescribeEventsResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/event";

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
     * イベントの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEventsAsync(
            DescribeEventsRequest request,
            AsyncAction<AsyncResult<DescribeEventsResult>> callback
    ) {
        DescribeEventsTask task = new DescribeEventsTask(request, callback, DescribeEventsResult.class);
        session.execute(task);
    }

    /**
     * イベントの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEventsResult describeEvents(
            DescribeEventsRequest request
    ) {
        final AsyncResult<DescribeEventsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEventsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            AsyncAction<AsyncResult<DescribeEventsByUserIdResult>> userCallback,
            Class<DescribeEventsByUserIdResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/event";

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
     * ユーザIDを指定してイベントの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEventsByUserIdAsync(
            DescribeEventsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeEventsByUserIdResult>> callback
    ) {
        DescribeEventsByUserIdTask task = new DescribeEventsByUserIdTask(request, callback, DescribeEventsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してイベントの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeRawEventsTask extends Gs2RestSessionTask<DescribeRawEventsResult> {
        private DescribeRawEventsRequest request;

        public DescribeRawEventsTask(
            DescribeRawEventsRequest request,
            AsyncAction<AsyncResult<DescribeRawEventsResult>> userCallback,
            Class<DescribeRawEventsResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/event";

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
     * イベントの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRawEventsAsync(
            DescribeRawEventsRequest request,
            AsyncAction<AsyncResult<DescribeRawEventsResult>> callback
    ) {
        DescribeRawEventsTask task = new DescribeRawEventsTask(request, callback, DescribeRawEventsResult.class);
        session.execute(task);
    }

    /**
     * イベントの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRawEventsResult describeRawEvents(
            DescribeRawEventsRequest request
    ) {
        final AsyncResult<DescribeRawEventsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRawEventsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEventTask extends Gs2RestSessionTask<GetEventResult> {
        private GetEventRequest request;

        public GetEventTask(
            GetEventRequest request,
            AsyncAction<AsyncResult<GetEventResult>> userCallback,
            Class<GetEventResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/event/{eventName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

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
     * イベントを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEventAsync(
            GetEventRequest request,
            AsyncAction<AsyncResult<GetEventResult>> callback
    ) {
        GetEventTask task = new GetEventTask(request, callback, GetEventResult.class);
        session.execute(task);
    }

    /**
     * イベントを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEventResult getEvent(
            GetEventRequest request
    ) {
        final AsyncResult<GetEventResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEventAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEventByUserIdTask extends Gs2RestSessionTask<GetEventByUserIdResult> {
        private GetEventByUserIdRequest request;

        public GetEventByUserIdTask(
            GetEventByUserIdRequest request,
            AsyncAction<AsyncResult<GetEventByUserIdResult>> userCallback,
            Class<GetEventByUserIdResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/event/{eventName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));
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
     * ユーザIDを指定してイベントを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEventByUserIdAsync(
            GetEventByUserIdRequest request,
            AsyncAction<AsyncResult<GetEventByUserIdResult>> callback
    ) {
        GetEventByUserIdTask task = new GetEventByUserIdTask(request, callback, GetEventByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してイベントを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEventByUserIdResult getEventByUserId(
            GetEventByUserIdRequest request
    ) {
        final AsyncResult<GetEventByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEventByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRawEventTask extends Gs2RestSessionTask<GetRawEventResult> {
        private GetRawEventRequest request;

        public GetRawEventTask(
            GetRawEventRequest request,
            AsyncAction<AsyncResult<GetRawEventResult>> userCallback,
            Class<GetRawEventResult> clazz
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
                .replace("{service}", "schedule")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/event/{eventName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{eventName}", this.request.getEventName() == null|| this.request.getEventName().length() == 0 ? "null" : String.valueOf(this.request.getEventName()));

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
     * イベントを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRawEventAsync(
            GetRawEventRequest request,
            AsyncAction<AsyncResult<GetRawEventResult>> callback
    ) {
        GetRawEventTask task = new GetRawEventTask(request, callback, GetRawEventResult.class);
        session.execute(task);
    }

    /**
     * イベントを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRawEventResult getRawEvent(
            GetRawEventRequest request
    ) {
        final AsyncResult<GetRawEventResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRawEventAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "schedule")
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
     * 現在有効なイベントスケジュールマスターのマスターデータをエクスポートします<br>
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
     * 現在有効なイベントスケジュールマスターのマスターデータをエクスポートします<br>
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

    class GetCurrentEventMasterTask extends Gs2RestSessionTask<GetCurrentEventMasterResult> {
        private GetCurrentEventMasterRequest request;

        public GetCurrentEventMasterTask(
            GetCurrentEventMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentEventMasterResult>> userCallback,
            Class<GetCurrentEventMasterResult> clazz
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
                .replace("{service}", "schedule")
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
     * 現在有効な現在有効なイベントスケジュールマスターを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentEventMasterAsync(
            GetCurrentEventMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentEventMasterResult>> callback
    ) {
        GetCurrentEventMasterTask task = new GetCurrentEventMasterTask(request, callback, GetCurrentEventMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効なイベントスケジュールマスターを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentEventMasterResult getCurrentEventMaster(
            GetCurrentEventMasterRequest request
    ) {
        final AsyncResult<GetCurrentEventMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentEventMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentEventMasterTask extends Gs2RestSessionTask<UpdateCurrentEventMasterResult> {
        private UpdateCurrentEventMasterRequest request;

        public UpdateCurrentEventMasterTask(
            UpdateCurrentEventMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentEventMasterResult>> userCallback,
            Class<UpdateCurrentEventMasterResult> clazz
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
                .replace("{service}", "schedule")
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
     * 現在有効な現在有効なイベントスケジュールマスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentEventMasterAsync(
            UpdateCurrentEventMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentEventMasterResult>> callback
    ) {
        UpdateCurrentEventMasterTask task = new UpdateCurrentEventMasterTask(request, callback, UpdateCurrentEventMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効なイベントスケジュールマスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentEventMasterResult updateCurrentEventMaster(
            UpdateCurrentEventMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentEventMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentEventMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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