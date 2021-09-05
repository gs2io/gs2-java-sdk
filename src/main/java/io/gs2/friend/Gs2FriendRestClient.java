
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
import io.gs2.friend.request.*;
import io.gs2.friend.result.*;
import io.gs2.friend.model.*;public class Gs2FriendRestClient extends AbstractGs2Client<Gs2FriendRestClient> {

	public Gs2FriendRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("followScript", request.getFollowScript() != null ? request.getFollowScript().toJson() : null);
                    put("unfollowScript", request.getUnfollowScript() != null ? request.getUnfollowScript().toJson() : null);
                    put("sendRequestScript", request.getSendRequestScript() != null ? request.getSendRequestScript().toJson() : null);
                    put("cancelRequestScript", request.getCancelRequestScript() != null ? request.getCancelRequestScript().toJson() : null);
                    put("acceptRequestScript", request.getAcceptRequestScript() != null ? request.getAcceptRequestScript().toJson() : null);
                    put("rejectRequestScript", request.getRejectRequestScript() != null ? request.getRejectRequestScript().toJson() : null);
                    put("deleteFriendScript", request.getDeleteFriendScript() != null ? request.getDeleteFriendScript().toJson() : null);
                    put("updateProfileScript", request.getUpdateProfileScript() != null ? request.getUpdateProfileScript().toJson() : null);
                    put("followNotification", request.getFollowNotification() != null ? request.getFollowNotification().toJson() : null);
                    put("receiveRequestNotification", request.getReceiveRequestNotification() != null ? request.getReceiveRequestNotification().toJson() : null);
                    put("acceptRequestNotification", request.getAcceptRequestNotification() != null ? request.getAcceptRequestNotification().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
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
                .replace("{service}", "friend")
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
                .replace("{service}", "friend")
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
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("followScript", request.getFollowScript() != null ? request.getFollowScript().toJson() : null);
                    put("unfollowScript", request.getUnfollowScript() != null ? request.getUnfollowScript().toJson() : null);
                    put("sendRequestScript", request.getSendRequestScript() != null ? request.getSendRequestScript().toJson() : null);
                    put("cancelRequestScript", request.getCancelRequestScript() != null ? request.getCancelRequestScript().toJson() : null);
                    put("acceptRequestScript", request.getAcceptRequestScript() != null ? request.getAcceptRequestScript().toJson() : null);
                    put("rejectRequestScript", request.getRejectRequestScript() != null ? request.getRejectRequestScript().toJson() : null);
                    put("deleteFriendScript", request.getDeleteFriendScript() != null ? request.getDeleteFriendScript().toJson() : null);
                    put("updateProfileScript", request.getUpdateProfileScript() != null ? request.getUpdateProfileScript().toJson() : null);
                    put("followNotification", request.getFollowNotification() != null ? request.getFollowNotification().toJson() : null);
                    put("receiveRequestNotification", request.getReceiveRequestNotification() != null ? request.getReceiveRequestNotification().toJson() : null);
                    put("acceptRequestNotification", request.getAcceptRequestNotification() != null ? request.getAcceptRequestNotification().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
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
                .replace("{service}", "friend")
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

    class GetProfileTask extends Gs2RestSessionTask<GetProfileResult> {
        private GetProfileRequest request;

        public GetProfileTask(
            GetProfileRequest request,
            AsyncAction<AsyncResult<GetProfileResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetProfileResult parse(JsonNode data) {
            return GetProfileResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/profile";

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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getProfileAsync(
            GetProfileRequest request,
            AsyncAction<AsyncResult<GetProfileResult>> callback
    ) {
        GetProfileTask task = new GetProfileTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetProfileByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetProfileByUserIdResult parse(JsonNode data) {
            return GetProfileByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

            builder
                .build()
                .send();
        }
    }

    public void getProfileByUserIdAsync(
            GetProfileByUserIdRequest request,
            AsyncAction<AsyncResult<GetProfileByUserIdResult>> callback
    ) {
        GetProfileByUserIdTask task = new GetProfileByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UpdateProfileResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateProfileResult parse(JsonNode data) {
            return UpdateProfileResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/profile";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("publicProfile", request.getPublicProfile());
                    put("followerProfile", request.getFollowerProfile());
                    put("friendProfile", request.getFriendProfile());
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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateProfileAsync(
            UpdateProfileRequest request,
            AsyncAction<AsyncResult<UpdateProfileResult>> callback
    ) {
        UpdateProfileTask task = new UpdateProfileTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UpdateProfileByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateProfileByUserIdResult parse(JsonNode data) {
            return UpdateProfileByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("publicProfile", request.getPublicProfile());
                    put("followerProfile", request.getFollowerProfile());
                    put("friendProfile", request.getFriendProfile());
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

    public void updateProfileByUserIdAsync(
            UpdateProfileByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateProfileByUserIdResult>> callback
    ) {
        UpdateProfileByUserIdTask task = new UpdateProfileByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteProfileByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteProfileByUserIdResult parse(JsonNode data) {
            return DeleteProfileByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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

    public void deleteProfileByUserIdAsync(
            DeleteProfileByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProfileByUserIdResult>> callback
    ) {
        DeleteProfileByUserIdTask task = new DeleteProfileByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetPublicProfileResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPublicProfileResult parse(JsonNode data) {
            return GetPublicProfileResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/profile/public";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

            builder
                .build()
                .send();
        }
    }

    public void getPublicProfileAsync(
            GetPublicProfileRequest request,
            AsyncAction<AsyncResult<GetPublicProfileResult>> callback
    ) {
        GetPublicProfileTask task = new GetPublicProfileTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeFollowsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFollowsResult parse(JsonNode data) {
            return DescribeFollowsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

            builder
                .build()
                .send();
        }
    }

    public void describeFollowsAsync(
            DescribeFollowsRequest request,
            AsyncAction<AsyncResult<DescribeFollowsResult>> callback
    ) {
        DescribeFollowsTask task = new DescribeFollowsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeFollowsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFollowsByUserIdResult parse(JsonNode data) {
            return DescribeFollowsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void describeFollowsByUserIdAsync(
            DescribeFollowsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFollowsByUserIdResult>> callback
    ) {
        DescribeFollowsByUserIdTask task = new DescribeFollowsByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetFollowResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFollowResult parse(JsonNode data) {
            return GetFollowResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void getFollowAsync(
            GetFollowRequest request,
            AsyncAction<AsyncResult<GetFollowResult>> callback
    ) {
        GetFollowTask task = new GetFollowTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetFollowByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFollowByUserIdResult parse(JsonNode data) {
            return GetFollowByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void getFollowByUserIdAsync(
            GetFollowByUserIdRequest request,
            AsyncAction<AsyncResult<GetFollowByUserIdResult>> callback
    ) {
        GetFollowByUserIdTask task = new GetFollowByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<FollowResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FollowResult parse(JsonNode data) {
            return FollowResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void followAsync(
            FollowRequest request,
            AsyncAction<AsyncResult<FollowResult>> callback
    ) {
        FollowTask task = new FollowTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<FollowByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FollowByUserIdResult parse(JsonNode data) {
            return FollowByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void followByUserIdAsync(
            FollowByUserIdRequest request,
            AsyncAction<AsyncResult<FollowByUserIdResult>> callback
    ) {
        FollowByUserIdTask task = new FollowByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UnfollowResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnfollowResult parse(JsonNode data) {
            return UnfollowResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void unfollowAsync(
            UnfollowRequest request,
            AsyncAction<AsyncResult<UnfollowResult>> callback
    ) {
        UnfollowTask task = new UnfollowTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UnfollowByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnfollowByUserIdResult parse(JsonNode data) {
            return UnfollowByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/follow/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void unfollowByUserIdAsync(
            UnfollowByUserIdRequest request,
            AsyncAction<AsyncResult<UnfollowByUserIdResult>> callback
    ) {
        UnfollowByUserIdTask task = new UnfollowByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeFriendsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFriendsResult parse(JsonNode data) {
            return DescribeFriendsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/friend";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

            builder
                .build()
                .send();
        }
    }

    public void describeFriendsAsync(
            DescribeFriendsRequest request,
            AsyncAction<AsyncResult<DescribeFriendsResult>> callback
    ) {
        DescribeFriendsTask task = new DescribeFriendsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeFriendsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFriendsByUserIdResult parse(JsonNode data) {
            return DescribeFriendsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/friend";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void describeFriendsByUserIdAsync(
            DescribeFriendsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFriendsByUserIdResult>> callback
    ) {
        DescribeFriendsByUserIdTask task = new DescribeFriendsByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetFriendResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFriendResult parse(JsonNode data) {
            return GetFriendResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void getFriendAsync(
            GetFriendRequest request,
            AsyncAction<AsyncResult<GetFriendResult>> callback
    ) {
        GetFriendTask task = new GetFriendTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetFriendByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFriendByUserIdResult parse(JsonNode data) {
            return GetFriendByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void getFriendByUserIdAsync(
            GetFriendByUserIdRequest request,
            AsyncAction<AsyncResult<GetFriendByUserIdResult>> callback
    ) {
        GetFriendByUserIdTask task = new GetFriendByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteFriendResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFriendResult parse(JsonNode data) {
            return DeleteFriendResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void deleteFriendAsync(
            DeleteFriendRequest request,
            AsyncAction<AsyncResult<DeleteFriendResult>> callback
    ) {
        DeleteFriendTask task = new DeleteFriendTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteFriendByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFriendByUserIdResult parse(JsonNode data) {
            return DeleteFriendByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/friend/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void deleteFriendByUserIdAsync(
            DeleteFriendByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFriendByUserIdResult>> callback
    ) {
        DeleteFriendByUserIdTask task = new DeleteFriendByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeSendRequestsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSendRequestsResult parse(JsonNode data) {
            return DescribeSendRequestsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox";

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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeSendRequestsAsync(
            DescribeSendRequestsRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsResult>> callback
    ) {
        DescribeSendRequestsTask task = new DescribeSendRequestsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeSendRequestsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSendRequestsByUserIdResult parse(JsonNode data) {
            return DescribeSendRequestsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

            builder
                .build()
                .send();
        }
    }

    public void describeSendRequestsByUserIdAsync(
            DescribeSendRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsByUserIdResult>> callback
    ) {
        DescribeSendRequestsByUserIdTask task = new DescribeSendRequestsByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetSendRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSendRequestResult parse(JsonNode data) {
            return GetSendRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void getSendRequestAsync(
            GetSendRequestRequest request,
            AsyncAction<AsyncResult<GetSendRequestResult>> callback
    ) {
        GetSendRequestTask task = new GetSendRequestTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetSendRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSendRequestByUserIdResult parse(JsonNode data) {
            return GetSendRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void getSendRequestByUserIdAsync(
            GetSendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetSendRequestByUserIdResult>> callback
    ) {
        GetSendRequestByUserIdTask task = new GetSendRequestByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<SendRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendRequestResult parse(JsonNode data) {
            return SendRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void sendRequestAsync(
            SendRequestRequest request,
            AsyncAction<AsyncResult<SendRequestResult>> callback
    ) {
        SendRequestTask task = new SendRequestTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<SendRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendRequestByUserIdResult parse(JsonNode data) {
            return SendRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void sendRequestByUserIdAsync(
            SendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<SendRequestByUserIdResult>> callback
    ) {
        SendRequestByUserIdTask task = new SendRequestByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRequestResult parse(JsonNode data) {
            return DeleteRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void deleteRequestAsync(
            DeleteRequestRequest request,
            AsyncAction<AsyncResult<DeleteRequestResult>> callback
    ) {
        DeleteRequestTask task = new DeleteRequestTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRequestByUserIdResult parse(JsonNode data) {
            return DeleteRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void deleteRequestByUserIdAsync(
            DeleteRequestByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRequestByUserIdResult>> callback
    ) {
        DeleteRequestByUserIdTask task = new DeleteRequestByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeReceiveRequestsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiveRequestsResult parse(JsonNode data) {
            return DescribeReceiveRequestsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox";

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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeReceiveRequestsAsync(
            DescribeReceiveRequestsRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsResult>> callback
    ) {
        DescribeReceiveRequestsTask task = new DescribeReceiveRequestsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeReceiveRequestsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiveRequestsByUserIdResult parse(JsonNode data) {
            return DescribeReceiveRequestsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

            builder
                .build()
                .send();
        }
    }

    public void describeReceiveRequestsByUserIdAsync(
            DescribeReceiveRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsByUserIdResult>> callback
    ) {
        DescribeReceiveRequestsByUserIdTask task = new DescribeReceiveRequestsByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetReceiveRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReceiveRequestResult parse(JsonNode data) {
            return GetReceiveRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

    public void getReceiveRequestAsync(
            GetReceiveRequestRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestResult>> callback
    ) {
        GetReceiveRequestTask task = new GetReceiveRequestTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetReceiveRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReceiveRequestByUserIdResult parse(JsonNode data) {
            return GetReceiveRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

    public void getReceiveRequestByUserIdAsync(
            GetReceiveRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestByUserIdResult>> callback
    ) {
        GetReceiveRequestByUserIdTask task = new GetReceiveRequestByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<AcceptRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcceptRequestResult parse(JsonNode data) {
            return AcceptRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void acceptRequestAsync(
            AcceptRequestRequest request,
            AsyncAction<AsyncResult<AcceptRequestResult>> callback
    ) {
        AcceptRequestTask task = new AcceptRequestTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<AcceptRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcceptRequestByUserIdResult parse(JsonNode data) {
            return AcceptRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void acceptRequestByUserIdAsync(
            AcceptRequestByUserIdRequest request,
            AsyncAction<AsyncResult<AcceptRequestByUserIdResult>> callback
    ) {
        AcceptRequestByUserIdTask task = new AcceptRequestByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<RejectRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RejectRequestResult parse(JsonNode data) {
            return RejectRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void rejectRequestAsync(
            RejectRequestRequest request,
            AsyncAction<AsyncResult<RejectRequestResult>> callback
    ) {
        RejectRequestTask task = new RejectRequestTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<RejectRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RejectRequestByUserIdResult parse(JsonNode data) {
            return RejectRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

    public void rejectRequestByUserIdAsync(
            RejectRequestByUserIdRequest request,
            AsyncAction<AsyncResult<RejectRequestByUserIdResult>> callback
    ) {
        RejectRequestByUserIdTask task = new RejectRequestByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeBlackListResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBlackListResult parse(JsonNode data) {
            return DescribeBlackListResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/blackList";

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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeBlackListAsync(
            DescribeBlackListRequest request,
            AsyncAction<AsyncResult<DescribeBlackListResult>> callback
    ) {
        DescribeBlackListTask task = new DescribeBlackListTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeBlackListByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBlackListByUserIdResult parse(JsonNode data) {
            return DescribeBlackListByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/blackList";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

            builder
                .build()
                .send();
        }
    }

    public void describeBlackListByUserIdAsync(
            DescribeBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBlackListByUserIdResult>> callback
    ) {
        DescribeBlackListByUserIdTask task = new DescribeBlackListByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<RegisterBlackListResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RegisterBlackListResult parse(JsonNode data) {
            return RegisterBlackListResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void registerBlackListAsync(
            RegisterBlackListRequest request,
            AsyncAction<AsyncResult<RegisterBlackListResult>> callback
    ) {
        RegisterBlackListTask task = new RegisterBlackListTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<RegisterBlackListByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RegisterBlackListByUserIdResult parse(JsonNode data) {
            return RegisterBlackListByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void registerBlackListByUserIdAsync(
            RegisterBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<RegisterBlackListByUserIdResult>> callback
    ) {
        RegisterBlackListByUserIdTask task = new RegisterBlackListByUserIdTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UnregisterBlackListResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnregisterBlackListResult parse(JsonNode data) {
            return UnregisterBlackListResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void unregisterBlackListAsync(
            UnregisterBlackListRequest request,
            AsyncAction<AsyncResult<UnregisterBlackListResult>> callback
    ) {
        UnregisterBlackListTask task = new UnregisterBlackListTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UnregisterBlackListByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnregisterBlackListByUserIdResult parse(JsonNode data) {
            return UnregisterBlackListByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "friend")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/blackList/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void unregisterBlackListByUserIdAsync(
            UnregisterBlackListByUserIdRequest request,
            AsyncAction<AsyncResult<UnregisterBlackListByUserIdResult>> callback
    ) {
        UnregisterBlackListByUserIdTask task = new UnregisterBlackListByUserIdTask(request, callback);
        session.execute(task);
    }

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