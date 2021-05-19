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

package io.gs2.experience;

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
import io.gs2.experience.request.*;
import io.gs2.experience.result.*;
import io.gs2.experience.model.*;

/**
 * GS2 Experience API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ExperienceRestClient extends AbstractGs2Client<Gs2ExperienceRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2ExperienceRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "experience")
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
                .replace("{service}", "experience")
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
            if (this.request.getExperienceCapScriptId() != null) {
                json.put("experienceCapScriptId", this.request.getExperienceCapScriptId());
            }
            if (this.request.getChangeExperienceScript() != null) {
                try {
                    json.put("changeExperienceScript", new JSONObject(mapper.writeValueAsString(this.request.getChangeExperienceScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getChangeRankScript() != null) {
                try {
                    json.put("changeRankScript", new JSONObject(mapper.writeValueAsString(this.request.getChangeRankScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getChangeRankCapScript() != null) {
                try {
                    json.put("changeRankCapScript", new JSONObject(mapper.writeValueAsString(this.request.getChangeRankCapScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getOverflowExperienceScript() != null) {
                try {
                    json.put("overflowExperienceScript", new JSONObject(mapper.writeValueAsString(this.request.getOverflowExperienceScript())));
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
                .replace("{service}", "experience")
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
                .replace("{service}", "experience")
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getExperienceCapScriptId() != null) {
                json.put("experienceCapScriptId", this.request.getExperienceCapScriptId());
            }
            if (this.request.getChangeExperienceScript() != null) {
                try {
                    json.put("changeExperienceScript", new JSONObject(mapper.writeValueAsString(this.request.getChangeExperienceScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getChangeRankScript() != null) {
                try {
                    json.put("changeRankScript", new JSONObject(mapper.writeValueAsString(this.request.getChangeRankScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getChangeRankCapScript() != null) {
                try {
                    json.put("changeRankCapScript", new JSONObject(mapper.writeValueAsString(this.request.getChangeRankCapScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getOverflowExperienceScript() != null) {
                try {
                    json.put("overflowExperienceScript", new JSONObject(mapper.writeValueAsString(this.request.getOverflowExperienceScript())));
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
                .replace("{service}", "experience")
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

    class DescribeExperienceModelMastersTask extends Gs2RestSessionTask<DescribeExperienceModelMastersResult> {
        private DescribeExperienceModelMastersRequest request;

        public DescribeExperienceModelMastersTask(
            DescribeExperienceModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelMastersResult>> userCallback,
            Class<DescribeExperienceModelMastersResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

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
     * 経験値の種類マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeExperienceModelMastersAsync(
            DescribeExperienceModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelMastersResult>> callback
    ) {
        DescribeExperienceModelMastersTask task = new DescribeExperienceModelMastersTask(request, callback, DescribeExperienceModelMastersResult.class);
        session.execute(task);
    }

    /**
     * 経験値の種類マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeExperienceModelMastersResult describeExperienceModelMasters(
            DescribeExperienceModelMastersRequest request
    ) {
        final AsyncResult<DescribeExperienceModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeExperienceModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateExperienceModelMasterTask extends Gs2RestSessionTask<CreateExperienceModelMasterResult> {
        private CreateExperienceModelMasterRequest request;

        public CreateExperienceModelMasterTask(
            CreateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<CreateExperienceModelMasterResult>> userCallback,
            Class<CreateExperienceModelMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

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
            if (this.request.getDefaultExperience() != null) {
                json.put("defaultExperience", this.request.getDefaultExperience());
            }
            if (this.request.getDefaultRankCap() != null) {
                json.put("defaultRankCap", this.request.getDefaultRankCap());
            }
            if (this.request.getMaxRankCap() != null) {
                json.put("maxRankCap", this.request.getMaxRankCap());
            }
            if (this.request.getRankThresholdId() != null) {
                json.put("rankThresholdId", this.request.getRankThresholdId());
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
     * 経験値の種類マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createExperienceModelMasterAsync(
            CreateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<CreateExperienceModelMasterResult>> callback
    ) {
        CreateExperienceModelMasterTask task = new CreateExperienceModelMasterTask(request, callback, CreateExperienceModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 経験値の種類マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateExperienceModelMasterResult createExperienceModelMaster(
            CreateExperienceModelMasterRequest request
    ) {
        final AsyncResult<CreateExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createExperienceModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetExperienceModelMasterTask extends Gs2RestSessionTask<GetExperienceModelMasterResult> {
        private GetExperienceModelMasterRequest request;

        public GetExperienceModelMasterTask(
            GetExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<GetExperienceModelMasterResult>> userCallback,
            Class<GetExperienceModelMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

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
     * 経験値の種類マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getExperienceModelMasterAsync(
            GetExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<GetExperienceModelMasterResult>> callback
    ) {
        GetExperienceModelMasterTask task = new GetExperienceModelMasterTask(request, callback, GetExperienceModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 経験値の種類マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetExperienceModelMasterResult getExperienceModelMaster(
            GetExperienceModelMasterRequest request
    ) {
        final AsyncResult<GetExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getExperienceModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateExperienceModelMasterTask extends Gs2RestSessionTask<UpdateExperienceModelMasterResult> {
        private UpdateExperienceModelMasterRequest request;

        public UpdateExperienceModelMasterTask(
            UpdateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateExperienceModelMasterResult>> userCallback,
            Class<UpdateExperienceModelMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDefaultExperience() != null) {
                json.put("defaultExperience", this.request.getDefaultExperience());
            }
            if (this.request.getDefaultRankCap() != null) {
                json.put("defaultRankCap", this.request.getDefaultRankCap());
            }
            if (this.request.getMaxRankCap() != null) {
                json.put("maxRankCap", this.request.getMaxRankCap());
            }
            if (this.request.getRankThresholdId() != null) {
                json.put("rankThresholdId", this.request.getRankThresholdId());
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
     * 経験値の種類マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateExperienceModelMasterAsync(
            UpdateExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateExperienceModelMasterResult>> callback
    ) {
        UpdateExperienceModelMasterTask task = new UpdateExperienceModelMasterTask(request, callback, UpdateExperienceModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 経験値の種類マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateExperienceModelMasterResult updateExperienceModelMaster(
            UpdateExperienceModelMasterRequest request
    ) {
        final AsyncResult<UpdateExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateExperienceModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteExperienceModelMasterTask extends Gs2RestSessionTask<DeleteExperienceModelMasterResult> {
        private DeleteExperienceModelMasterRequest request;

        public DeleteExperienceModelMasterTask(
            DeleteExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteExperienceModelMasterResult>> userCallback,
            Class<DeleteExperienceModelMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

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
     * 経験値の種類マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteExperienceModelMasterAsync(
            DeleteExperienceModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteExperienceModelMasterResult>> callback
    ) {
        DeleteExperienceModelMasterTask task = new DeleteExperienceModelMasterTask(request, callback, DeleteExperienceModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 経験値の種類マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteExperienceModelMasterResult deleteExperienceModelMaster(
            DeleteExperienceModelMasterRequest request
    ) {
        final AsyncResult<DeleteExperienceModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteExperienceModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeExperienceModelsTask extends Gs2RestSessionTask<DescribeExperienceModelsResult> {
        private DescribeExperienceModelsRequest request;

        public DescribeExperienceModelsTask(
            DescribeExperienceModelsRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelsResult>> userCallback,
            Class<DescribeExperienceModelsResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model";

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
     * 経験値・ランクアップ閾値モデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeExperienceModelsAsync(
            DescribeExperienceModelsRequest request,
            AsyncAction<AsyncResult<DescribeExperienceModelsResult>> callback
    ) {
        DescribeExperienceModelsTask task = new DescribeExperienceModelsTask(request, callback, DescribeExperienceModelsResult.class);
        session.execute(task);
    }

    /**
     * 経験値・ランクアップ閾値モデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeExperienceModelsResult describeExperienceModels(
            DescribeExperienceModelsRequest request
    ) {
        final AsyncResult<DescribeExperienceModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeExperienceModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetExperienceModelTask extends Gs2RestSessionTask<GetExperienceModelResult> {
        private GetExperienceModelRequest request;

        public GetExperienceModelTask(
            GetExperienceModelRequest request,
            AsyncAction<AsyncResult<GetExperienceModelResult>> userCallback,
            Class<GetExperienceModelResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{experienceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));

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
     * 経験値・ランクアップ閾値モデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getExperienceModelAsync(
            GetExperienceModelRequest request,
            AsyncAction<AsyncResult<GetExperienceModelResult>> callback
    ) {
        GetExperienceModelTask task = new GetExperienceModelTask(request, callback, GetExperienceModelResult.class);
        session.execute(task);
    }

    /**
     * 経験値・ランクアップ閾値モデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetExperienceModelResult getExperienceModel(
            GetExperienceModelRequest request
    ) {
        final AsyncResult<GetExperienceModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getExperienceModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeThresholdMastersTask extends Gs2RestSessionTask<DescribeThresholdMastersResult> {
        private DescribeThresholdMastersRequest request;

        public DescribeThresholdMastersTask(
            DescribeThresholdMastersRequest request,
            AsyncAction<AsyncResult<DescribeThresholdMastersResult>> userCallback,
            Class<DescribeThresholdMastersResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold";

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
     * ランクアップ閾値マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeThresholdMastersAsync(
            DescribeThresholdMastersRequest request,
            AsyncAction<AsyncResult<DescribeThresholdMastersResult>> callback
    ) {
        DescribeThresholdMastersTask task = new DescribeThresholdMastersTask(request, callback, DescribeThresholdMastersResult.class);
        session.execute(task);
    }

    /**
     * ランクアップ閾値マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeThresholdMastersResult describeThresholdMasters(
            DescribeThresholdMastersRequest request
    ) {
        final AsyncResult<DescribeThresholdMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeThresholdMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateThresholdMasterTask extends Gs2RestSessionTask<CreateThresholdMasterResult> {
        private CreateThresholdMasterRequest request;

        public CreateThresholdMasterTask(
            CreateThresholdMasterRequest request,
            AsyncAction<AsyncResult<CreateThresholdMasterResult>> userCallback,
            Class<CreateThresholdMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold";

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
            if (this.request.getValues() != null) {
                JSONArray array = new JSONArray();
                for(Long item : this.request.getValues())
                {
                    array.put(item);
                }
                json.put("values", array);
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
     * ランクアップ閾値マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createThresholdMasterAsync(
            CreateThresholdMasterRequest request,
            AsyncAction<AsyncResult<CreateThresholdMasterResult>> callback
    ) {
        CreateThresholdMasterTask task = new CreateThresholdMasterTask(request, callback, CreateThresholdMasterResult.class);
        session.execute(task);
    }

    /**
     * ランクアップ閾値マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateThresholdMasterResult createThresholdMaster(
            CreateThresholdMasterRequest request
    ) {
        final AsyncResult<CreateThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createThresholdMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetThresholdMasterTask extends Gs2RestSessionTask<GetThresholdMasterResult> {
        private GetThresholdMasterRequest request;

        public GetThresholdMasterTask(
            GetThresholdMasterRequest request,
            AsyncAction<AsyncResult<GetThresholdMasterResult>> userCallback,
            Class<GetThresholdMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold/{thresholdName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{thresholdName}", this.request.getThresholdName() == null|| this.request.getThresholdName().length() == 0 ? "null" : String.valueOf(this.request.getThresholdName()));

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
     * ランクアップ閾値マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getThresholdMasterAsync(
            GetThresholdMasterRequest request,
            AsyncAction<AsyncResult<GetThresholdMasterResult>> callback
    ) {
        GetThresholdMasterTask task = new GetThresholdMasterTask(request, callback, GetThresholdMasterResult.class);
        session.execute(task);
    }

    /**
     * ランクアップ閾値マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetThresholdMasterResult getThresholdMaster(
            GetThresholdMasterRequest request
    ) {
        final AsyncResult<GetThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getThresholdMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateThresholdMasterTask extends Gs2RestSessionTask<UpdateThresholdMasterResult> {
        private UpdateThresholdMasterRequest request;

        public UpdateThresholdMasterTask(
            UpdateThresholdMasterRequest request,
            AsyncAction<AsyncResult<UpdateThresholdMasterResult>> userCallback,
            Class<UpdateThresholdMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold/{thresholdName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{thresholdName}", this.request.getThresholdName() == null|| this.request.getThresholdName().length() == 0 ? "null" : String.valueOf(this.request.getThresholdName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getValues() != null) {
                JSONArray array = new JSONArray();
                for(Long item : this.request.getValues())
                {
                    array.put(item);
                }
                json.put("values", array);
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
     * ランクアップ閾値マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateThresholdMasterAsync(
            UpdateThresholdMasterRequest request,
            AsyncAction<AsyncResult<UpdateThresholdMasterResult>> callback
    ) {
        UpdateThresholdMasterTask task = new UpdateThresholdMasterTask(request, callback, UpdateThresholdMasterResult.class);
        session.execute(task);
    }

    /**
     * ランクアップ閾値マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateThresholdMasterResult updateThresholdMaster(
            UpdateThresholdMasterRequest request
    ) {
        final AsyncResult<UpdateThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateThresholdMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteThresholdMasterTask extends Gs2RestSessionTask<DeleteThresholdMasterResult> {
        private DeleteThresholdMasterRequest request;

        public DeleteThresholdMasterTask(
            DeleteThresholdMasterRequest request,
            AsyncAction<AsyncResult<DeleteThresholdMasterResult>> userCallback,
            Class<DeleteThresholdMasterResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/threshold/{thresholdName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{thresholdName}", this.request.getThresholdName() == null|| this.request.getThresholdName().length() == 0 ? "null" : String.valueOf(this.request.getThresholdName()));

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
     * ランクアップ閾値マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteThresholdMasterAsync(
            DeleteThresholdMasterRequest request,
            AsyncAction<AsyncResult<DeleteThresholdMasterResult>> callback
    ) {
        DeleteThresholdMasterTask task = new DeleteThresholdMasterTask(request, callback, DeleteThresholdMasterResult.class);
        session.execute(task);
    }

    /**
     * ランクアップ閾値マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteThresholdMasterResult deleteThresholdMaster(
            DeleteThresholdMasterRequest request
    ) {
        final AsyncResult<DeleteThresholdMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteThresholdMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "experience")
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
     * 現在有効な経験値設定のマスターデータをエクスポートします<br>
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
     * 現在有効な経験値設定のマスターデータをエクスポートします<br>
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

    class GetCurrentExperienceMasterTask extends Gs2RestSessionTask<GetCurrentExperienceMasterResult> {
        private GetCurrentExperienceMasterRequest request;

        public GetCurrentExperienceMasterTask(
            GetCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentExperienceMasterResult>> userCallback,
            Class<GetCurrentExperienceMasterResult> clazz
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
                .replace("{service}", "experience")
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
     * 現在有効な経験値設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentExperienceMasterAsync(
            GetCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentExperienceMasterResult>> callback
    ) {
        GetCurrentExperienceMasterTask task = new GetCurrentExperienceMasterTask(request, callback, GetCurrentExperienceMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な経験値設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentExperienceMasterResult getCurrentExperienceMaster(
            GetCurrentExperienceMasterRequest request
    ) {
        final AsyncResult<GetCurrentExperienceMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentExperienceMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentExperienceMasterTask extends Gs2RestSessionTask<UpdateCurrentExperienceMasterResult> {
        private UpdateCurrentExperienceMasterRequest request;

        public UpdateCurrentExperienceMasterTask(
            UpdateCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterResult>> userCallback,
            Class<UpdateCurrentExperienceMasterResult> clazz
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
                .replace("{service}", "experience")
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
     * 現在有効な経験値設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentExperienceMasterAsync(
            UpdateCurrentExperienceMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterResult>> callback
    ) {
        UpdateCurrentExperienceMasterTask task = new UpdateCurrentExperienceMasterTask(request, callback, UpdateCurrentExperienceMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な経験値設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentExperienceMasterResult updateCurrentExperienceMaster(
            UpdateCurrentExperienceMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentExperienceMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentExperienceMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentExperienceMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentExperienceMasterFromGitHubResult> {
        private UpdateCurrentExperienceMasterFromGitHubRequest request;

        public UpdateCurrentExperienceMasterFromGitHubTask(
            UpdateCurrentExperienceMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentExperienceMasterFromGitHubResult> clazz
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
                .replace("{service}", "experience")
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
     * 現在有効な経験値設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentExperienceMasterFromGitHubAsync(
            UpdateCurrentExperienceMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentExperienceMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentExperienceMasterFromGitHubTask task = new UpdateCurrentExperienceMasterFromGitHubTask(request, callback, UpdateCurrentExperienceMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な経験値設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentExperienceMasterFromGitHubResult updateCurrentExperienceMasterFromGitHub(
            UpdateCurrentExperienceMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentExperienceMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentExperienceMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStatusesTask extends Gs2RestSessionTask<DescribeStatusesResult> {
        private DescribeStatusesRequest request;

        public DescribeStatusesTask(
            DescribeStatusesRequest request,
            AsyncAction<AsyncResult<DescribeStatusesResult>> userCallback,
            Class<DescribeStatusesResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getExperienceName() != null) {
                queryStrings.add("experienceName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getExperienceName()))));
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
     * ステータスの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStatusesAsync(
            DescribeStatusesRequest request,
            AsyncAction<AsyncResult<DescribeStatusesResult>> callback
    ) {
        DescribeStatusesTask task = new DescribeStatusesTask(request, callback, DescribeStatusesResult.class);
        session.execute(task);
    }

    /**
     * ステータスの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStatusesResult describeStatuses(
            DescribeStatusesRequest request
    ) {
        final AsyncResult<DescribeStatusesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStatusesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStatusesByUserIdTask extends Gs2RestSessionTask<DescribeStatusesByUserIdResult> {
        private DescribeStatusesByUserIdRequest request;

        public DescribeStatusesByUserIdTask(
            DescribeStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStatusesByUserIdResult>> userCallback,
            Class<DescribeStatusesByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getExperienceName() != null) {
                queryStrings.add("experienceName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getExperienceName()))));
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
     * ステータスの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStatusesByUserIdAsync(
            DescribeStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStatusesByUserIdResult>> callback
    ) {
        DescribeStatusesByUserIdTask task = new DescribeStatusesByUserIdTask(request, callback, DescribeStatusesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ステータスの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStatusesByUserIdResult describeStatusesByUserId(
            DescribeStatusesByUserIdRequest request
    ) {
        final AsyncResult<DescribeStatusesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStatusesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStatusTask extends Gs2RestSessionTask<GetStatusResult> {
        private GetStatusRequest request;

        public GetStatusTask(
            GetStatusRequest request,
            AsyncAction<AsyncResult<GetStatusResult>> userCallback,
            Class<GetStatusResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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
     * ステータスを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStatusAsync(
            GetStatusRequest request,
            AsyncAction<AsyncResult<GetStatusResult>> callback
    ) {
        GetStatusTask task = new GetStatusTask(request, callback, GetStatusResult.class);
        session.execute(task);
    }

    /**
     * ステータスを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStatusResult getStatus(
            GetStatusRequest request
    ) {
        final AsyncResult<GetStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStatusByUserIdTask extends Gs2RestSessionTask<GetStatusByUserIdResult> {
        private GetStatusByUserIdRequest request;

        public GetStatusByUserIdTask(
            GetStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetStatusByUserIdResult>> userCallback,
            Class<GetStatusByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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
     * ステータスを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStatusByUserIdAsync(
            GetStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetStatusByUserIdResult>> callback
    ) {
        GetStatusByUserIdTask task = new GetStatusByUserIdTask(request, callback, GetStatusByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ステータスを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStatusByUserIdResult getStatusByUserId(
            GetStatusByUserIdRequest request
    ) {
        final AsyncResult<GetStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStatusWithSignatureTask extends Gs2RestSessionTask<GetStatusWithSignatureResult> {
        private GetStatusWithSignatureRequest request;

        public GetStatusWithSignatureTask(
            GetStatusWithSignatureRequest request,
            AsyncAction<AsyncResult<GetStatusWithSignatureResult>> userCallback,
            Class<GetStatusWithSignatureResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/status/model/{experienceName}/property/{propertyId}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getKeyId() != null) {
                queryStrings.add("keyId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getKeyId()))));
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
     * ステータスを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStatusWithSignatureAsync(
            GetStatusWithSignatureRequest request,
            AsyncAction<AsyncResult<GetStatusWithSignatureResult>> callback
    ) {
        GetStatusWithSignatureTask task = new GetStatusWithSignatureTask(request, callback, GetStatusWithSignatureResult.class);
        session.execute(task);
    }

    /**
     * ステータスを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStatusWithSignatureResult getStatusWithSignature(
            GetStatusWithSignatureRequest request
    ) {
        final AsyncResult<GetStatusWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStatusWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddExperienceByUserIdTask extends Gs2RestSessionTask<AddExperienceByUserIdResult> {
        private AddExperienceByUserIdRequest request;

        public AddExperienceByUserIdTask(
            AddExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<AddExperienceByUserIdResult>> userCallback,
            Class<AddExperienceByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getExperienceValue() != null) {
                json.put("experienceValue", this.request.getExperienceValue());
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
     * 経験値を加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addExperienceByUserIdAsync(
            AddExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<AddExperienceByUserIdResult>> callback
    ) {
        AddExperienceByUserIdTask task = new AddExperienceByUserIdTask(request, callback, AddExperienceByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 経験値を加算<br>
     *
     * @param request リクエストパラメータ
     */
    public AddExperienceByUserIdResult addExperienceByUserId(
            AddExperienceByUserIdRequest request
    ) {
        final AsyncResult<AddExperienceByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addExperienceByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetExperienceByUserIdTask extends Gs2RestSessionTask<SetExperienceByUserIdResult> {
        private SetExperienceByUserIdRequest request;

        public SetExperienceByUserIdTask(
            SetExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<SetExperienceByUserIdResult>> userCallback,
            Class<SetExperienceByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getExperienceValue() != null) {
                json.put("experienceValue", this.request.getExperienceValue());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * 累計獲得経験値を設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setExperienceByUserIdAsync(
            SetExperienceByUserIdRequest request,
            AsyncAction<AsyncResult<SetExperienceByUserIdResult>> callback
    ) {
        SetExperienceByUserIdTask task = new SetExperienceByUserIdTask(request, callback, SetExperienceByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 累計獲得経験値を設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetExperienceByUserIdResult setExperienceByUserId(
            SetExperienceByUserIdRequest request
    ) {
        final AsyncResult<SetExperienceByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setExperienceByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddRankCapByUserIdTask extends Gs2RestSessionTask<AddRankCapByUserIdResult> {
        private AddRankCapByUserIdRequest request;

        public AddRankCapByUserIdTask(
            AddRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<AddRankCapByUserIdResult>> userCallback,
            Class<AddRankCapByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}/cap";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getRankCapValue() != null) {
                json.put("rankCapValue", this.request.getRankCapValue());
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
     * ランクキャップを加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addRankCapByUserIdAsync(
            AddRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<AddRankCapByUserIdResult>> callback
    ) {
        AddRankCapByUserIdTask task = new AddRankCapByUserIdTask(request, callback, AddRankCapByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ランクキャップを加算<br>
     *
     * @param request リクエストパラメータ
     */
    public AddRankCapByUserIdResult addRankCapByUserId(
            AddRankCapByUserIdRequest request
    ) {
        final AsyncResult<AddRankCapByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addRankCapByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRankCapByUserIdTask extends Gs2RestSessionTask<SetRankCapByUserIdResult> {
        private SetRankCapByUserIdRequest request;

        public SetRankCapByUserIdTask(
            SetRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<SetRankCapByUserIdResult>> userCallback,
            Class<SetRankCapByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}/cap";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getRankCapValue() != null) {
                json.put("rankCapValue", this.request.getRankCapValue());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * ランクキャップを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setRankCapByUserIdAsync(
            SetRankCapByUserIdRequest request,
            AsyncAction<AsyncResult<SetRankCapByUserIdResult>> callback
    ) {
        SetRankCapByUserIdTask task = new SetRankCapByUserIdTask(request, callback, SetRankCapByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ランクキャップを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetRankCapByUserIdResult setRankCapByUserId(
            SetRankCapByUserIdRequest request
    ) {
        final AsyncResult<SetRankCapByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRankCapByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStatusByUserIdTask extends Gs2RestSessionTask<DeleteStatusByUserIdResult> {
        private DeleteStatusByUserIdRequest request;

        public DeleteStatusByUserIdTask(
            DeleteStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStatusByUserIdResult>> userCallback,
            Class<DeleteStatusByUserIdResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/status/model/{experienceName}/property/{propertyId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{experienceName}", this.request.getExperienceName() == null|| this.request.getExperienceName().length() == 0 ? "null" : String.valueOf(this.request.getExperienceName()));
            url = url.replace("{propertyId}", this.request.getPropertyId() == null|| this.request.getPropertyId().length() == 0 ? "null" : String.valueOf(this.request.getPropertyId()));

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
     * ステータスを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteStatusByUserIdAsync(
            DeleteStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStatusByUserIdResult>> callback
    ) {
        DeleteStatusByUserIdTask task = new DeleteStatusByUserIdTask(request, callback, DeleteStatusByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ステータスを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteStatusByUserIdResult deleteStatusByUserId(
            DeleteStatusByUserIdRequest request
    ) {
        final AsyncResult<DeleteStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStatusByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddExperienceByStampSheetTask extends Gs2RestSessionTask<AddExperienceByStampSheetResult> {
        private AddExperienceByStampSheetRequest request;

        public AddExperienceByStampSheetTask(
            AddExperienceByStampSheetRequest request,
            AsyncAction<AsyncResult<AddExperienceByStampSheetResult>> userCallback,
            Class<AddExperienceByStampSheetResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/experience/add";

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
     * 経験値を加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addExperienceByStampSheetAsync(
            AddExperienceByStampSheetRequest request,
            AsyncAction<AsyncResult<AddExperienceByStampSheetResult>> callback
    ) {
        AddExperienceByStampSheetTask task = new AddExperienceByStampSheetTask(request, callback, AddExperienceByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * 経験値を加算<br>
     *
     * @param request リクエストパラメータ
     */
    public AddExperienceByStampSheetResult addExperienceByStampSheet(
            AddExperienceByStampSheetRequest request
    ) {
        final AsyncResult<AddExperienceByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addExperienceByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddRankCapByStampSheetTask extends Gs2RestSessionTask<AddRankCapByStampSheetResult> {
        private AddRankCapByStampSheetRequest request;

        public AddRankCapByStampSheetTask(
            AddRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<AddRankCapByStampSheetResult>> userCallback,
            Class<AddRankCapByStampSheetResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rankCap/add";

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
     * ランクキャップを加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addRankCapByStampSheetAsync(
            AddRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<AddRankCapByStampSheetResult>> callback
    ) {
        AddRankCapByStampSheetTask task = new AddRankCapByStampSheetTask(request, callback, AddRankCapByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * ランクキャップを加算<br>
     *
     * @param request リクエストパラメータ
     */
    public AddRankCapByStampSheetResult addRankCapByStampSheet(
            AddRankCapByStampSheetRequest request
    ) {
        final AsyncResult<AddRankCapByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addRankCapByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetRankCapByStampSheetTask extends Gs2RestSessionTask<SetRankCapByStampSheetResult> {
        private SetRankCapByStampSheetRequest request;

        public SetRankCapByStampSheetTask(
            SetRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRankCapByStampSheetResult>> userCallback,
            Class<SetRankCapByStampSheetResult> clazz
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
                .replace("{service}", "experience")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/rankCap/set";

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
     * ランクキャップを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setRankCapByStampSheetAsync(
            SetRankCapByStampSheetRequest request,
            AsyncAction<AsyncResult<SetRankCapByStampSheetResult>> callback
    ) {
        SetRankCapByStampSheetTask task = new SetRankCapByStampSheetTask(request, callback, SetRankCapByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * ランクキャップを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetRankCapByStampSheetResult setRankCapByStampSheet(
            SetRankCapByStampSheetRequest request
    ) {
        final AsyncResult<SetRankCapByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setRankCapByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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