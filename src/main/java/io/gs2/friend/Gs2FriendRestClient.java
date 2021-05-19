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

package io.gs2.friend;

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
import io.gs2.friend.request.*;
import io.gs2.friend.result.*;
import io.gs2.friend.model.*;

/**
 * GS2 Friend API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2FriendRestClient extends AbstractGs2Client<Gs2FriendRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2FriendRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "friend")
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
                .replace("{service}", "friend")
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
            if (this.request.getFollowScript() != null) {
                try {
                    json.put("followScript", new JSONObject(mapper.writeValueAsString(this.request.getFollowScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUnfollowScript() != null) {
                try {
                    json.put("unfollowScript", new JSONObject(mapper.writeValueAsString(this.request.getUnfollowScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getSendRequestScript() != null) {
                try {
                    json.put("sendRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getSendRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCancelRequestScript() != null) {
                try {
                    json.put("cancelRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getCancelRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getAcceptRequestScript() != null) {
                try {
                    json.put("acceptRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getAcceptRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getRejectRequestScript() != null) {
                try {
                    json.put("rejectRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getRejectRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDeleteFriendScript() != null) {
                try {
                    json.put("deleteFriendScript", new JSONObject(mapper.writeValueAsString(this.request.getDeleteFriendScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUpdateProfileScript() != null) {
                try {
                    json.put("updateProfileScript", new JSONObject(mapper.writeValueAsString(this.request.getUpdateProfileScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getFollowNotification() != null) {
                try {
                    json.put("followNotification", new JSONObject(mapper.writeValueAsString(this.request.getFollowNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getReceiveRequestNotification() != null) {
                try {
                    json.put("receiveRequestNotification", new JSONObject(mapper.writeValueAsString(this.request.getReceiveRequestNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getAcceptRequestNotification() != null) {
                try {
                    json.put("acceptRequestNotification", new JSONObject(mapper.writeValueAsString(this.request.getAcceptRequestNotification())));
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
                .replace("{service}", "friend")
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
                .replace("{service}", "friend")
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getFollowScript() != null) {
                try {
                    json.put("followScript", new JSONObject(mapper.writeValueAsString(this.request.getFollowScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUnfollowScript() != null) {
                try {
                    json.put("unfollowScript", new JSONObject(mapper.writeValueAsString(this.request.getUnfollowScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getSendRequestScript() != null) {
                try {
                    json.put("sendRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getSendRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCancelRequestScript() != null) {
                try {
                    json.put("cancelRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getCancelRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getAcceptRequestScript() != null) {
                try {
                    json.put("acceptRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getAcceptRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getRejectRequestScript() != null) {
                try {
                    json.put("rejectRequestScript", new JSONObject(mapper.writeValueAsString(this.request.getRejectRequestScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDeleteFriendScript() != null) {
                try {
                    json.put("deleteFriendScript", new JSONObject(mapper.writeValueAsString(this.request.getDeleteFriendScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUpdateProfileScript() != null) {
                try {
                    json.put("updateProfileScript", new JSONObject(mapper.writeValueAsString(this.request.getUpdateProfileScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getFollowNotification() != null) {
                try {
                    json.put("followNotification", new JSONObject(mapper.writeValueAsString(this.request.getFollowNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getReceiveRequestNotification() != null) {
                try {
                    json.put("receiveRequestNotification", new JSONObject(mapper.writeValueAsString(this.request.getReceiveRequestNotification())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getAcceptRequestNotification() != null) {
                try {
                    json.put("acceptRequestNotification", new JSONObject(mapper.writeValueAsString(this.request.getAcceptRequestNotification())));
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
                .replace("{service}", "friend")
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

    class DescribeProfilesTask extends Gs2RestSessionTask<DescribeProfilesResult> {
        private DescribeProfilesRequest request;

        public DescribeProfilesTask(
            DescribeProfilesRequest request,
            AsyncAction<AsyncResult<DescribeProfilesResult>> userCallback,
            Class<DescribeProfilesResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/profile";

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
     * ユーザーIDを指定してプロフィールの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeProfilesAsync(
            DescribeProfilesRequest request,
            AsyncAction<AsyncResult<DescribeProfilesResult>> callback
    ) {
        DescribeProfilesTask task = new DescribeProfilesTask(request, callback, DescribeProfilesResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してプロフィールの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeProfilesResult describeProfiles(
            DescribeProfilesRequest request
    ) {
        final AsyncResult<DescribeProfilesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProfilesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProfileTask extends Gs2RestSessionTask<GetProfileResult> {
        private GetProfileRequest request;

        public GetProfileTask(
            GetProfileRequest request,
            AsyncAction<AsyncResult<GetProfileResult>> userCallback,
            Class<GetProfileResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/profile";

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
     * プロフィールを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProfileAsync(
            GetProfileRequest request,
            AsyncAction<AsyncResult<GetProfileResult>> callback
    ) {
        GetProfileTask task = new GetProfileTask(request, callback, GetProfileResult.class);
        session.execute(task);
    }

    /**
     * プロフィールを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProfileResult getProfile(
            GetProfileRequest request
    ) {
        final AsyncResult<GetProfileResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProfileAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProfileByUserIdTask extends Gs2RestSessionTask<GetProfileByUserIdResult> {
        private GetProfileByUserIdRequest request;

        public GetProfileByUserIdTask(
            GetProfileByUserIdRequest request,
            AsyncAction<AsyncResult<GetProfileByUserIdResult>> userCallback,
            Class<GetProfileByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile";

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
     * ユーザーIDを指定してプロフィールを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProfileByUserIdAsync(
            GetProfileByUserIdRequest request,
            AsyncAction<AsyncResult<GetProfileByUserIdResult>> callback
    ) {
        GetProfileByUserIdTask task = new GetProfileByUserIdTask(request, callback, GetProfileByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してプロフィールを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProfileByUserIdResult getProfileByUserId(
            GetProfileByUserIdRequest request
    ) {
        final AsyncResult<GetProfileByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProfileByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateProfileTask extends Gs2RestSessionTask<UpdateProfileResult> {
        private UpdateProfileRequest request;

        public UpdateProfileTask(
            UpdateProfileRequest request,
            AsyncAction<AsyncResult<UpdateProfileResult>> userCallback,
            Class<UpdateProfileResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/profile";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPublicProfile() != null) {
                json.put("publicProfile", this.request.getPublicProfile());
            }
            if (this.request.getFollowerProfile() != null) {
                json.put("followerProfile", this.request.getFollowerProfile());
            }
            if (this.request.getFriendProfile() != null) {
                json.put("friendProfile", this.request.getFriendProfile());
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
     * プロフィールを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateProfileAsync(
            UpdateProfileRequest request,
            AsyncAction<AsyncResult<UpdateProfileResult>> callback
    ) {
        UpdateProfileTask task = new UpdateProfileTask(request, callback, UpdateProfileResult.class);
        session.execute(task);
    }

    /**
     * プロフィールを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateProfileResult updateProfile(
            UpdateProfileRequest request
    ) {
        final AsyncResult<UpdateProfileResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateProfileAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateProfileByUserIdTask extends Gs2RestSessionTask<UpdateProfileByUserIdResult> {
        private UpdateProfileByUserIdRequest request;

        public UpdateProfileByUserIdTask(
            UpdateProfileByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateProfileByUserIdResult>> userCallback,
            Class<UpdateProfileByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPublicProfile() != null) {
                json.put("publicProfile", this.request.getPublicProfile());
            }
            if (this.request.getFollowerProfile() != null) {
                json.put("followerProfile", this.request.getFollowerProfile());
            }
            if (this.request.getFriendProfile() != null) {
                json.put("friendProfile", this.request.getFriendProfile());
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
     * ユーザーIDを指定してプロフィールを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateProfileByUserIdAsync(
            UpdateProfileByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateProfileByUserIdResult>> callback
    ) {
        UpdateProfileByUserIdTask task = new UpdateProfileByUserIdTask(request, callback, UpdateProfileByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してプロフィールを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateProfileByUserIdResult updateProfileByUserId(
            UpdateProfileByUserIdRequest request
    ) {
        final AsyncResult<UpdateProfileByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateProfileByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProfileByUserIdTask extends Gs2RestSessionTask<DeleteProfileByUserIdResult> {
        private DeleteProfileByUserIdRequest request;

        public DeleteProfileByUserIdTask(
            DeleteProfileByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProfileByUserIdResult>> userCallback,
            Class<DeleteProfileByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile";

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
     * プロフィールを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProfileByUserIdAsync(
            DeleteProfileByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProfileByUserIdResult>> callback
    ) {
        DeleteProfileByUserIdTask task = new DeleteProfileByUserIdTask(request, callback, DeleteProfileByUserIdResult.class);
        session.execute(task);
    }

    /**
     * プロフィールを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProfileByUserIdResult deleteProfileByUserId(
            DeleteProfileByUserIdRequest request
    ) {
        final AsyncResult<DeleteProfileByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProfileByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPublicProfileTask extends Gs2RestSessionTask<GetPublicProfileResult> {
        private GetPublicProfileRequest request;

        public GetPublicProfileTask(
            GetPublicProfileRequest request,
            AsyncAction<AsyncResult<GetPublicProfileResult>> userCallback,
            Class<GetPublicProfileResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile/public";

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
     * 公開プロフィールを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getPublicProfileAsync(
            GetPublicProfileRequest request,
            AsyncAction<AsyncResult<GetPublicProfileResult>> callback
    ) {
        GetPublicProfileTask task = new GetPublicProfileTask(request, callback, GetPublicProfileResult.class);
        session.execute(task);
    }

    /**
     * 公開プロフィールを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetPublicProfileResult getPublicProfile(
            GetPublicProfileRequest request
    ) {
        final AsyncResult<GetPublicProfileResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPublicProfileAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFollowsTask extends Gs2RestSessionTask<DescribeFollowsResult> {
        private DescribeFollowsRequest request;

        public DescribeFollowsTask(
            DescribeFollowsRequest request,
            AsyncAction<AsyncResult<DescribeFollowsResult>> userCallback,
            Class<DescribeFollowsResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * フォローの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFollowsAsync(
            DescribeFollowsRequest request,
            AsyncAction<AsyncResult<DescribeFollowsResult>> callback
    ) {
        DescribeFollowsTask task = new DescribeFollowsTask(request, callback, DescribeFollowsResult.class);
        session.execute(task);
    }

    /**
     * フォローの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFollowsResult describeFollows(
            DescribeFollowsRequest request
    ) {
        final AsyncResult<DescribeFollowsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFollowsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFollowsByUserIdTask extends Gs2RestSessionTask<DescribeFollowsByUserIdResult> {
        private DescribeFollowsByUserIdRequest request;

        public DescribeFollowsByUserIdTask(
            DescribeFollowsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFollowsByUserIdResult>> userCallback,
            Class<DescribeFollowsByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * ユーザーIDを指定してフォローの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFollowsByUserIdAsync(
            DescribeFollowsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFollowsByUserIdResult>> callback
    ) {
        DescribeFollowsByUserIdTask task = new DescribeFollowsByUserIdTask(request, callback, DescribeFollowsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフォローの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFollowsByUserIdResult describeFollowsByUserId(
            DescribeFollowsByUserIdRequest request
    ) {
        final AsyncResult<DescribeFollowsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFollowsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFollowTask extends Gs2RestSessionTask<GetFollowResult> {
        private GetFollowRequest request;

        public GetFollowTask(
            GetFollowRequest request,
            AsyncAction<AsyncResult<GetFollowResult>> userCallback,
            Class<GetFollowResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * フォローを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFollowAsync(
            GetFollowRequest request,
            AsyncAction<AsyncResult<GetFollowResult>> callback
    ) {
        GetFollowTask task = new GetFollowTask(request, callback, GetFollowResult.class);
        session.execute(task);
    }

    /**
     * フォローを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFollowResult getFollow(
            GetFollowRequest request
    ) {
        final AsyncResult<GetFollowResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFollowAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFollowByUserIdTask extends Gs2RestSessionTask<GetFollowByUserIdResult> {
        private GetFollowByUserIdRequest request;

        public GetFollowByUserIdTask(
            GetFollowByUserIdRequest request,
            AsyncAction<AsyncResult<GetFollowByUserIdResult>> userCallback,
            Class<GetFollowByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * ユーザーIDを指定してフォローを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFollowByUserIdAsync(
            GetFollowByUserIdRequest request,
            AsyncAction<AsyncResult<GetFollowByUserIdResult>> callback
    ) {
        GetFollowByUserIdTask task = new GetFollowByUserIdTask(request, callback, GetFollowByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフォローを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFollowByUserIdResult getFollowByUserId(
            GetFollowByUserIdRequest request
    ) {
        final AsyncResult<GetFollowByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFollowByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class FollowTask extends Gs2RestSessionTask<FollowResult> {
        private FollowRequest request;

        public FollowTask(
            FollowRequest request,
            AsyncAction<AsyncResult<FollowResult>> userCallback,
            Class<FollowResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * フォロー<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void followAsync(
            FollowRequest request,
            AsyncAction<AsyncResult<FollowResult>> callback
    ) {
        FollowTask task = new FollowTask(request, callback, FollowResult.class);
        session.execute(task);
    }

    /**
     * フォロー<br>
     *
     * @param request リクエストパラメータ
     */
    public FollowResult follow(
            FollowRequest request
    ) {
        final AsyncResult<FollowResult>[] resultAsyncResult = new AsyncResult[]{null};
        followAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class FollowByUserIdTask extends Gs2RestSessionTask<FollowByUserIdResult> {
        private FollowByUserIdRequest request;

        public FollowByUserIdTask(
            FollowByUserIdRequest request,
            AsyncAction<AsyncResult<FollowByUserIdResult>> userCallback,
            Class<FollowByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * ユーザーIDを指定してフォロー<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void followByUserIdAsync(
            FollowByUserIdRequest request,
            AsyncAction<AsyncResult<FollowByUserIdResult>> callback
    ) {
        FollowByUserIdTask task = new FollowByUserIdTask(request, callback, FollowByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフォロー<br>
     *
     * @param request リクエストパラメータ
     */
    public FollowByUserIdResult followByUserId(
            FollowByUserIdRequest request
    ) {
        final AsyncResult<FollowByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        followByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnfollowTask extends Gs2RestSessionTask<UnfollowResult> {
        private UnfollowRequest request;

        public UnfollowTask(
            UnfollowRequest request,
            AsyncAction<AsyncResult<UnfollowResult>> userCallback,
            Class<UnfollowResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * アンフォロー<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unfollowAsync(
            UnfollowRequest request,
            AsyncAction<AsyncResult<UnfollowResult>> callback
    ) {
        UnfollowTask task = new UnfollowTask(request, callback, UnfollowResult.class);
        session.execute(task);
    }

    /**
     * アンフォロー<br>
     *
     * @param request リクエストパラメータ
     */
    public UnfollowResult unfollow(
            UnfollowRequest request
    ) {
        final AsyncResult<UnfollowResult>[] resultAsyncResult = new AsyncResult[]{null};
        unfollowAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnfollowByUserIdTask extends Gs2RestSessionTask<UnfollowByUserIdResult> {
        private UnfollowByUserIdRequest request;

        public UnfollowByUserIdTask(
            UnfollowByUserIdRequest request,
            AsyncAction<AsyncResult<UnfollowByUserIdResult>> userCallback,
            Class<UnfollowByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザーIDを指定してアンフォロー<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unfollowByUserIdAsync(
            UnfollowByUserIdRequest request,
            AsyncAction<AsyncResult<UnfollowByUserIdResult>> callback
    ) {
        UnfollowByUserIdTask task = new UnfollowByUserIdTask(request, callback, UnfollowByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してアンフォロー<br>
     *
     * @param request リクエストパラメータ
     */
    public UnfollowByUserIdResult unfollowByUserId(
            UnfollowByUserIdRequest request
    ) {
        final AsyncResult<UnfollowByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        unfollowByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFriendsTask extends Gs2RestSessionTask<DescribeFriendsResult> {
        private DescribeFriendsRequest request;

        public DescribeFriendsTask(
            DescribeFriendsRequest request,
            AsyncAction<AsyncResult<DescribeFriendsResult>> userCallback,
            Class<DescribeFriendsResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/friend";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * フレンドを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFriendsAsync(
            DescribeFriendsRequest request,
            AsyncAction<AsyncResult<DescribeFriendsResult>> callback
    ) {
        DescribeFriendsTask task = new DescribeFriendsTask(request, callback, DescribeFriendsResult.class);
        session.execute(task);
    }

    /**
     * フレンドを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFriendsResult describeFriends(
            DescribeFriendsRequest request
    ) {
        final AsyncResult<DescribeFriendsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFriendsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFriendsByUserIdTask extends Gs2RestSessionTask<DescribeFriendsByUserIdResult> {
        private DescribeFriendsByUserIdRequest request;

        public DescribeFriendsByUserIdTask(
            DescribeFriendsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFriendsByUserIdResult>> userCallback,
            Class<DescribeFriendsByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/friend";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * ユーザーIDを指定してフレンドを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFriendsByUserIdAsync(
            DescribeFriendsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFriendsByUserIdResult>> callback
    ) {
        DescribeFriendsByUserIdTask task = new DescribeFriendsByUserIdTask(request, callback, DescribeFriendsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFriendsByUserIdResult describeFriendsByUserId(
            DescribeFriendsByUserIdRequest request
    ) {
        final AsyncResult<DescribeFriendsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFriendsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFriendTask extends Gs2RestSessionTask<GetFriendResult> {
        private GetFriendRequest request;

        public GetFriendTask(
            GetFriendRequest request,
            AsyncAction<AsyncResult<GetFriendResult>> userCallback,
            Class<GetFriendResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * フレンドを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFriendAsync(
            GetFriendRequest request,
            AsyncAction<AsyncResult<GetFriendResult>> callback
    ) {
        GetFriendTask task = new GetFriendTask(request, callback, GetFriendResult.class);
        session.execute(task);
    }

    /**
     * フレンドを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFriendResult getFriend(
            GetFriendRequest request
    ) {
        final AsyncResult<GetFriendResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFriendAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFriendByUserIdTask extends Gs2RestSessionTask<GetFriendByUserIdResult> {
        private GetFriendByUserIdRequest request;

        public GetFriendByUserIdTask(
            GetFriendByUserIdRequest request,
            AsyncAction<AsyncResult<GetFriendByUserIdResult>> userCallback,
            Class<GetFriendByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getWithProfile() != null) {
                queryStrings.add("withProfile=" + String.valueOf(this.request.getWithProfile()));
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
     * ユーザーIDを指定してフレンドを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFriendByUserIdAsync(
            GetFriendByUserIdRequest request,
            AsyncAction<AsyncResult<GetFriendByUserIdResult>> callback
    ) {
        GetFriendByUserIdTask task = new GetFriendByUserIdTask(request, callback, GetFriendByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFriendByUserIdResult getFriendByUserId(
            GetFriendByUserIdRequest request
    ) {
        final AsyncResult<GetFriendByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFriendByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFriendTask extends Gs2RestSessionTask<DeleteFriendResult> {
        private DeleteFriendRequest request;

        public DeleteFriendTask(
            DeleteFriendRequest request,
            AsyncAction<AsyncResult<DeleteFriendResult>> userCallback,
            Class<DeleteFriendResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * フレンドを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFriendAsync(
            DeleteFriendRequest request,
            AsyncAction<AsyncResult<DeleteFriendResult>> callback
    ) {
        DeleteFriendTask task = new DeleteFriendTask(request, callback, DeleteFriendResult.class);
        session.execute(task);
    }

    /**
     * フレンドを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFriendResult deleteFriend(
            DeleteFriendRequest request
    ) {
        final AsyncResult<DeleteFriendResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFriendAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFriendByUserIdTask extends Gs2RestSessionTask<DeleteFriendByUserIdResult> {
        private DeleteFriendByUserIdRequest request;

        public DeleteFriendByUserIdTask(
            DeleteFriendByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFriendByUserIdResult>> userCallback,
            Class<DeleteFriendByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザーIDを指定してフレンドを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFriendByUserIdAsync(
            DeleteFriendByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFriendByUserIdResult>> callback
    ) {
        DeleteFriendByUserIdTask task = new DeleteFriendByUserIdTask(request, callback, DeleteFriendByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFriendByUserIdResult deleteFriendByUserId(
            DeleteFriendByUserIdRequest request
    ) {
        final AsyncResult<DeleteFriendByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFriendByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSendRequestsTask extends Gs2RestSessionTask<DescribeSendRequestsResult> {
        private DescribeSendRequestsRequest request;

        public DescribeSendRequestsTask(
            DescribeSendRequestsRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsResult>> userCallback,
            Class<DescribeSendRequestsResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox";

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
     * 送信したフレンドリクエストの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSendRequestsAsync(
            DescribeSendRequestsRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsResult>> callback
    ) {
        DescribeSendRequestsTask task = new DescribeSendRequestsTask(request, callback, DescribeSendRequestsResult.class);
        session.execute(task);
    }

    /**
     * 送信したフレンドリクエストの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSendRequestsResult describeSendRequests(
            DescribeSendRequestsRequest request
    ) {
        final AsyncResult<DescribeSendRequestsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSendRequestsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSendRequestsByUserIdTask extends Gs2RestSessionTask<DescribeSendRequestsByUserIdResult> {
        private DescribeSendRequestsByUserIdRequest request;

        public DescribeSendRequestsByUserIdTask(
            DescribeSendRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsByUserIdResult>> userCallback,
            Class<DescribeSendRequestsByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox";

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
     * ユーザーIDを指定して送信したフレンドリクエストの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSendRequestsByUserIdAsync(
            DescribeSendRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsByUserIdResult>> callback
    ) {
        DescribeSendRequestsByUserIdTask task = new DescribeSendRequestsByUserIdTask(request, callback, DescribeSendRequestsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して送信したフレンドリクエストの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSendRequestsByUserIdResult describeSendRequestsByUserId(
            DescribeSendRequestsByUserIdRequest request
    ) {
        final AsyncResult<DescribeSendRequestsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSendRequestsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSendRequestTask extends Gs2RestSessionTask<GetSendRequestResult> {
        private GetSendRequestRequest request;

        public GetSendRequestTask(
            GetSendRequestRequest request,
            AsyncAction<AsyncResult<GetSendRequestResult>> userCallback,
            Class<GetSendRequestResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * 送信したフレンドリクエストを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSendRequestAsync(
            GetSendRequestRequest request,
            AsyncAction<AsyncResult<GetSendRequestResult>> callback
    ) {
        GetSendRequestTask task = new GetSendRequestTask(request, callback, GetSendRequestResult.class);
        session.execute(task);
    }

    /**
     * 送信したフレンドリクエストを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetSendRequestResult getSendRequest(
            GetSendRequestRequest request
    ) {
        final AsyncResult<GetSendRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSendRequestAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSendRequestByUserIdTask extends Gs2RestSessionTask<GetSendRequestByUserIdResult> {
        private GetSendRequestByUserIdRequest request;

        public GetSendRequestByUserIdTask(
            GetSendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetSendRequestByUserIdResult>> userCallback,
            Class<GetSendRequestByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザーIDを指定して送信したフレンドリクエストを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSendRequestByUserIdAsync(
            GetSendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetSendRequestByUserIdResult>> callback
    ) {
        GetSendRequestByUserIdTask task = new GetSendRequestByUserIdTask(request, callback, GetSendRequestByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して送信したフレンドリクエストを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetSendRequestByUserIdResult getSendRequestByUserId(
            GetSendRequestByUserIdRequest request
    ) {
        final AsyncResult<GetSendRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSendRequestByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendRequestTask extends Gs2RestSessionTask<SendRequestResult> {
        private SendRequestRequest request;

        public SendRequestTask(
            SendRequestRequest request,
            AsyncAction<AsyncResult<SendRequestResult>> userCallback,
            Class<SendRequestResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * フレンドリクエストを送信<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void sendRequestAsync(
            SendRequestRequest request,
            AsyncAction<AsyncResult<SendRequestResult>> callback
    ) {
        SendRequestTask task = new SendRequestTask(request, callback, SendRequestResult.class);
        session.execute(task);
    }

    /**
     * フレンドリクエストを送信<br>
     *
     * @param request リクエストパラメータ
     */
    public SendRequestResult sendRequest(
            SendRequestRequest request
    ) {
        final AsyncResult<SendRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendRequestAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendRequestByUserIdTask extends Gs2RestSessionTask<SendRequestByUserIdResult> {
        private SendRequestByUserIdRequest request;

        public SendRequestByUserIdTask(
            SendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<SendRequestByUserIdResult>> userCallback,
            Class<SendRequestByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * ユーザーIDを指定してフレンドリクエストを送信<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void sendRequestByUserIdAsync(
            SendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<SendRequestByUserIdResult>> callback
    ) {
        SendRequestByUserIdTask task = new SendRequestByUserIdTask(request, callback, SendRequestByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドリクエストを送信<br>
     *
     * @param request リクエストパラメータ
     */
    public SendRequestByUserIdResult sendRequestByUserId(
            SendRequestByUserIdRequest request
    ) {
        final AsyncResult<SendRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendRequestByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRequestTask extends Gs2RestSessionTask<DeleteRequestResult> {
        private DeleteRequestRequest request;

        public DeleteRequestTask(
            DeleteRequestRequest request,
            AsyncAction<AsyncResult<DeleteRequestResult>> userCallback,
            Class<DeleteRequestResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * フレンドリクエストを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRequestAsync(
            DeleteRequestRequest request,
            AsyncAction<AsyncResult<DeleteRequestResult>> callback
    ) {
        DeleteRequestTask task = new DeleteRequestTask(request, callback, DeleteRequestResult.class);
        session.execute(task);
    }

    /**
     * フレンドリクエストを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteRequestResult deleteRequest(
            DeleteRequestRequest request
    ) {
        final AsyncResult<DeleteRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRequestAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRequestByUserIdTask extends Gs2RestSessionTask<DeleteRequestByUserIdResult> {
        private DeleteRequestByUserIdRequest request;

        public DeleteRequestByUserIdTask(
            DeleteRequestByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRequestByUserIdResult>> userCallback,
            Class<DeleteRequestByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザーIDを指定してフレンドリクエストを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRequestByUserIdAsync(
            DeleteRequestByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRequestByUserIdResult>> callback
    ) {
        DeleteRequestByUserIdTask task = new DeleteRequestByUserIdTask(request, callback, DeleteRequestByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドリクエストを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteRequestByUserIdResult deleteRequestByUserId(
            DeleteRequestByUserIdRequest request
    ) {
        final AsyncResult<DeleteRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRequestByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeReceiveRequestsTask extends Gs2RestSessionTask<DescribeReceiveRequestsResult> {
        private DescribeReceiveRequestsRequest request;

        public DescribeReceiveRequestsTask(
            DescribeReceiveRequestsRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsResult>> userCallback,
            Class<DescribeReceiveRequestsResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox";

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
     * 受信したフレンドリクエストの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeReceiveRequestsAsync(
            DescribeReceiveRequestsRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsResult>> callback
    ) {
        DescribeReceiveRequestsTask task = new DescribeReceiveRequestsTask(request, callback, DescribeReceiveRequestsResult.class);
        session.execute(task);
    }

    /**
     * 受信したフレンドリクエストの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeReceiveRequestsResult describeReceiveRequests(
            DescribeReceiveRequestsRequest request
    ) {
        final AsyncResult<DescribeReceiveRequestsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiveRequestsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeReceiveRequestsByUserIdTask extends Gs2RestSessionTask<DescribeReceiveRequestsByUserIdResult> {
        private DescribeReceiveRequestsByUserIdRequest request;

        public DescribeReceiveRequestsByUserIdTask(
            DescribeReceiveRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsByUserIdResult>> userCallback,
            Class<DescribeReceiveRequestsByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox";

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
     * ユーザーIDを指定して受信したフレンドリクエストの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeReceiveRequestsByUserIdAsync(
            DescribeReceiveRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsByUserIdResult>> callback
    ) {
        DescribeReceiveRequestsByUserIdTask task = new DescribeReceiveRequestsByUserIdTask(request, callback, DescribeReceiveRequestsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して受信したフレンドリクエストの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeReceiveRequestsByUserIdResult describeReceiveRequestsByUserId(
            DescribeReceiveRequestsByUserIdRequest request
    ) {
        final AsyncResult<DescribeReceiveRequestsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiveRequestsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetReceiveRequestTask extends Gs2RestSessionTask<GetReceiveRequestResult> {
        private GetReceiveRequestRequest request;

        public GetReceiveRequestTask(
            GetReceiveRequestRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestResult>> userCallback,
            Class<GetReceiveRequestResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null|| this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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
     * 受信したフレンドリクエストを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getReceiveRequestAsync(
            GetReceiveRequestRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestResult>> callback
    ) {
        GetReceiveRequestTask task = new GetReceiveRequestTask(request, callback, GetReceiveRequestResult.class);
        session.execute(task);
    }

    /**
     * 受信したフレンドリクエストを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetReceiveRequestResult getReceiveRequest(
            GetReceiveRequestRequest request
    ) {
        final AsyncResult<GetReceiveRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReceiveRequestAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetReceiveRequestByUserIdTask extends Gs2RestSessionTask<GetReceiveRequestByUserIdResult> {
        private GetReceiveRequestByUserIdRequest request;

        public GetReceiveRequestByUserIdTask(
            GetReceiveRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestByUserIdResult>> userCallback,
            Class<GetReceiveRequestByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null|| this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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
     * ユーザーIDを指定して受信したフレンドリクエストを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getReceiveRequestByUserIdAsync(
            GetReceiveRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestByUserIdResult>> callback
    ) {
        GetReceiveRequestByUserIdTask task = new GetReceiveRequestByUserIdTask(request, callback, GetReceiveRequestByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して受信したフレンドリクエストを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetReceiveRequestByUserIdResult getReceiveRequestByUserId(
            GetReceiveRequestByUserIdRequest request
    ) {
        final AsyncResult<GetReceiveRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReceiveRequestByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcceptRequestTask extends Gs2RestSessionTask<AcceptRequestResult> {
        private AcceptRequestRequest request;

        public AcceptRequestTask(
            AcceptRequestRequest request,
            AsyncAction<AsyncResult<AcceptRequestResult>> userCallback,
            Class<AcceptRequestResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null|| this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * フレンドリクエストを承諾<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acceptRequestAsync(
            AcceptRequestRequest request,
            AsyncAction<AsyncResult<AcceptRequestResult>> callback
    ) {
        AcceptRequestTask task = new AcceptRequestTask(request, callback, AcceptRequestResult.class);
        session.execute(task);
    }

    /**
     * フレンドリクエストを承諾<br>
     *
     * @param request リクエストパラメータ
     */
    public AcceptRequestResult acceptRequest(
            AcceptRequestRequest request
    ) {
        final AsyncResult<AcceptRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        acceptRequestAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcceptRequestByUserIdTask extends Gs2RestSessionTask<AcceptRequestByUserIdResult> {
        private AcceptRequestByUserIdRequest request;

        public AcceptRequestByUserIdTask(
            AcceptRequestByUserIdRequest request,
            AsyncAction<AsyncResult<AcceptRequestByUserIdResult>> userCallback,
            Class<AcceptRequestByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null|| this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * ユーザーIDを指定してフレンドリクエストを承諾<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acceptRequestByUserIdAsync(
            AcceptRequestByUserIdRequest request,
            AsyncAction<AsyncResult<AcceptRequestByUserIdResult>> callback
    ) {
        AcceptRequestByUserIdTask task = new AcceptRequestByUserIdTask(request, callback, AcceptRequestByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドリクエストを承諾<br>
     *
     * @param request リクエストパラメータ
     */
    public AcceptRequestByUserIdResult acceptRequestByUserId(
            AcceptRequestByUserIdRequest request
    ) {
        final AsyncResult<AcceptRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acceptRequestByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RejectRequestTask extends Gs2RestSessionTask<RejectRequestResult> {
        private RejectRequestRequest request;

        public RejectRequestTask(
            RejectRequestRequest request,
            AsyncAction<AsyncResult<RejectRequestResult>> userCallback,
            Class<RejectRequestResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null|| this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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
     * フレンドリクエストを拒否<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void rejectRequestAsync(
            RejectRequestRequest request,
            AsyncAction<AsyncResult<RejectRequestResult>> callback
    ) {
        RejectRequestTask task = new RejectRequestTask(request, callback, RejectRequestResult.class);
        session.execute(task);
    }

    /**
     * フレンドリクエストを拒否<br>
     *
     * @param request リクエストパラメータ
     */
    public RejectRequestResult rejectRequest(
            RejectRequestRequest request
    ) {
        final AsyncResult<RejectRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        rejectRequestAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RejectRequestByUserIdTask extends Gs2RestSessionTask<RejectRequestByUserIdResult> {
        private RejectRequestByUserIdRequest request;

        public RejectRequestByUserIdTask(
            RejectRequestByUserIdRequest request,
            AsyncAction<AsyncResult<RejectRequestByUserIdResult>> userCallback,
            Class<RejectRequestByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null|| this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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
     * ユーザーIDを指定してフレンドリクエストを拒否<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void rejectRequestByUserIdAsync(
            RejectRequestByUserIdRequest request,
            AsyncAction<AsyncResult<RejectRequestByUserIdResult>> callback
    ) {
        RejectRequestByUserIdTask task = new RejectRequestByUserIdTask(request, callback, RejectRequestByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してフレンドリクエストを拒否<br>
     *
     * @param request リクエストパラメータ
     */
    public RejectRequestByUserIdResult rejectRequestByUserId(
            RejectRequestByUserIdRequest request
    ) {
        final AsyncResult<RejectRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        rejectRequestByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBlackListTask extends Gs2RestSessionTask<DescribeBlackListResult> {
        private DescribeBlackListRequest request;

        public DescribeBlackListTask(
            DescribeBlackListRequest request,
            AsyncAction<AsyncResult<DescribeBlackListResult>> userCallback,
            Class<DescribeBlackListResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/blackList";

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
     * ブラックリストを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeBlackListAsync(
            DescribeBlackListRequest request,
            AsyncAction<AsyncResult<DescribeBlackListResult>> callback
    ) {
        DescribeBlackListTask task = new DescribeBlackListTask(request, callback, DescribeBlackListResult.class);
        session.execute(task);
    }

    /**
     * ブラックリストを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeBlackListResult describeBlackList(
            DescribeBlackListRequest request
    ) {
        final AsyncResult<DescribeBlackListResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBlackListAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBlackListByUserIdTask extends Gs2RestSessionTask<DescribeBlackListByUserIdResult> {
        private DescribeBlackListByUserIdRequest request;

        public DescribeBlackListByUserIdTask(
            DescribeBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBlackListByUserIdResult>> userCallback,
            Class<DescribeBlackListByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/blackList";

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
     * ユーザーIDを指定してブラックリストを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeBlackListByUserIdAsync(
            DescribeBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBlackListByUserIdResult>> callback
    ) {
        DescribeBlackListByUserIdTask task = new DescribeBlackListByUserIdTask(request, callback, DescribeBlackListByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してブラックリストを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeBlackListByUserIdResult describeBlackListByUserId(
            DescribeBlackListByUserIdRequest request
    ) {
        final AsyncResult<DescribeBlackListByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBlackListByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RegisterBlackListTask extends Gs2RestSessionTask<RegisterBlackListResult> {
        private RegisterBlackListRequest request;

        public RegisterBlackListTask(
            RegisterBlackListRequest request,
            AsyncAction<AsyncResult<RegisterBlackListResult>> userCallback,
            Class<RegisterBlackListResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ブラックリストに登録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void registerBlackListAsync(
            RegisterBlackListRequest request,
            AsyncAction<AsyncResult<RegisterBlackListResult>> callback
    ) {
        RegisterBlackListTask task = new RegisterBlackListTask(request, callback, RegisterBlackListResult.class);
        session.execute(task);
    }

    /**
     * ブラックリストに登録<br>
     *
     * @param request リクエストパラメータ
     */
    public RegisterBlackListResult registerBlackList(
            RegisterBlackListRequest request
    ) {
        final AsyncResult<RegisterBlackListResult>[] resultAsyncResult = new AsyncResult[]{null};
        registerBlackListAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RegisterBlackListByUserIdTask extends Gs2RestSessionTask<RegisterBlackListByUserIdResult> {
        private RegisterBlackListByUserIdRequest request;

        public RegisterBlackListByUserIdTask(
            RegisterBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<RegisterBlackListByUserIdResult>> userCallback,
            Class<RegisterBlackListByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザーIDを指定してブラックリストに登録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void registerBlackListByUserIdAsync(
            RegisterBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<RegisterBlackListByUserIdResult>> callback
    ) {
        RegisterBlackListByUserIdTask task = new RegisterBlackListByUserIdTask(request, callback, RegisterBlackListByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してブラックリストに登録<br>
     *
     * @param request リクエストパラメータ
     */
    public RegisterBlackListByUserIdResult registerBlackListByUserId(
            RegisterBlackListByUserIdRequest request
    ) {
        final AsyncResult<RegisterBlackListByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        registerBlackListByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnregisterBlackListTask extends Gs2RestSessionTask<UnregisterBlackListResult> {
        private UnregisterBlackListRequest request;

        public UnregisterBlackListTask(
            UnregisterBlackListRequest request,
            AsyncAction<AsyncResult<UnregisterBlackListResult>> userCallback,
            Class<UnregisterBlackListResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ブラックリストからユーザを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unregisterBlackListAsync(
            UnregisterBlackListRequest request,
            AsyncAction<AsyncResult<UnregisterBlackListResult>> callback
    ) {
        UnregisterBlackListTask task = new UnregisterBlackListTask(request, callback, UnregisterBlackListResult.class);
        session.execute(task);
    }

    /**
     * ブラックリストからユーザを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public UnregisterBlackListResult unregisterBlackList(
            UnregisterBlackListRequest request
    ) {
        final AsyncResult<UnregisterBlackListResult>[] resultAsyncResult = new AsyncResult[]{null};
        unregisterBlackListAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnregisterBlackListByUserIdTask extends Gs2RestSessionTask<UnregisterBlackListByUserIdResult> {
        private UnregisterBlackListByUserIdRequest request;

        public UnregisterBlackListByUserIdTask(
            UnregisterBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<UnregisterBlackListByUserIdResult>> userCallback,
            Class<UnregisterBlackListByUserIdResult> clazz
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザーIDを指定してブラックリストからユーザを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unregisterBlackListByUserIdAsync(
            UnregisterBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<UnregisterBlackListByUserIdResult>> callback
    ) {
        UnregisterBlackListByUserIdTask task = new UnregisterBlackListByUserIdTask(request, callback, UnregisterBlackListByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してブラックリストからユーザを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public UnregisterBlackListByUserIdResult unregisterBlackListByUserId(
            UnregisterBlackListByUserIdRequest request
    ) {
        final AsyncResult<UnregisterBlackListByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        unregisterBlackListByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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