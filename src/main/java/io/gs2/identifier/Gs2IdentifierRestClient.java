
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
 *
 * deny overwrite
 */

package io.gs2.identifier;

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
import io.gs2.identifier.request.*;
import io.gs2.identifier.result.*;
import io.gs2.identifier.model.*;public class Gs2IdentifierRestClient extends AbstractGs2Client<Gs2IdentifierRestClient> {

	public Gs2IdentifierRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeUsersTask extends Gs2RestSessionTask<DescribeUsersResult> {
        private DescribeUsersRequest request;

        public DescribeUsersTask(
            DescribeUsersRequest request,
            AsyncAction<AsyncResult<DescribeUsersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeUsersResult parse(JsonNode data) {
            return DescribeUsersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user";

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

    public void describeUsersAsync(
            DescribeUsersRequest request,
            AsyncAction<AsyncResult<DescribeUsersResult>> callback
    ) {
        DescribeUsersTask task = new DescribeUsersTask(request, callback);
        session.execute(task);
    }

    public DescribeUsersResult describeUsers(
            DescribeUsersRequest request
    ) {
        final AsyncResult<DescribeUsersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeUsersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateUserTask extends Gs2RestSessionTask<CreateUserResult> {
        private CreateUserRequest request;

        public CreateUserTask(
            CreateUserRequest request,
            AsyncAction<AsyncResult<CreateUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateUserResult parse(JsonNode data) {
            return CreateUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
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

    public void createUserAsync(
            CreateUserRequest request,
            AsyncAction<AsyncResult<CreateUserResult>> callback
    ) {
        CreateUserTask task = new CreateUserTask(request, callback);
        session.execute(task);
    }

    public CreateUserResult createUser(
            CreateUserRequest request
    ) {
        final AsyncResult<CreateUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        createUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateUserTask extends Gs2RestSessionTask<UpdateUserResult> {
        private UpdateUserRequest request;

        public UpdateUserTask(
            UpdateUserRequest request,
            AsyncAction<AsyncResult<UpdateUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateUserResult parse(JsonNode data) {
            return UpdateUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
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

    public void updateUserAsync(
            UpdateUserRequest request,
            AsyncAction<AsyncResult<UpdateUserResult>> callback
    ) {
        UpdateUserTask task = new UpdateUserTask(request, callback);
        session.execute(task);
    }

    public UpdateUserResult updateUser(
            UpdateUserRequest request
    ) {
        final AsyncResult<UpdateUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetUserTask extends Gs2RestSessionTask<GetUserResult> {
        private GetUserRequest request;

        public GetUserTask(
            GetUserRequest request,
            AsyncAction<AsyncResult<GetUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetUserResult parse(JsonNode data) {
            return GetUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void getUserAsync(
            GetUserRequest request,
            AsyncAction<AsyncResult<GetUserResult>> callback
    ) {
        GetUserTask task = new GetUserTask(request, callback);
        session.execute(task);
    }

    public GetUserResult getUser(
            GetUserRequest request
    ) {
        final AsyncResult<GetUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        getUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteUserTask extends Gs2RestSessionTask<DeleteUserResult> {
        private DeleteUserRequest request;

        public DeleteUserTask(
            DeleteUserRequest request,
            AsyncAction<AsyncResult<DeleteUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteUserResult parse(JsonNode data) {
            return DeleteUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void deleteUserAsync(
            DeleteUserRequest request,
            AsyncAction<AsyncResult<DeleteUserResult>> callback
    ) {
        DeleteUserTask task = new DeleteUserTask(request, callback);
        session.execute(task);
    }

    public DeleteUserResult deleteUser(
            DeleteUserRequest request
    ) {
        final AsyncResult<DeleteUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSecurityPoliciesTask extends Gs2RestSessionTask<DescribeSecurityPoliciesResult> {
        private DescribeSecurityPoliciesRequest request;

        public DescribeSecurityPoliciesTask(
            DescribeSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeSecurityPoliciesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSecurityPoliciesResult parse(JsonNode data) {
            return DescribeSecurityPoliciesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy";

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

    public void describeSecurityPoliciesAsync(
            DescribeSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeSecurityPoliciesResult>> callback
    ) {
        DescribeSecurityPoliciesTask task = new DescribeSecurityPoliciesTask(request, callback);
        session.execute(task);
    }

    public DescribeSecurityPoliciesResult describeSecurityPolicies(
            DescribeSecurityPoliciesRequest request
    ) {
        final AsyncResult<DescribeSecurityPoliciesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSecurityPoliciesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCommonSecurityPoliciesTask extends Gs2RestSessionTask<DescribeCommonSecurityPoliciesResult> {
        private DescribeCommonSecurityPoliciesRequest request;

        public DescribeCommonSecurityPoliciesTask(
            DescribeCommonSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeCommonSecurityPoliciesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCommonSecurityPoliciesResult parse(JsonNode data) {
            return DescribeCommonSecurityPoliciesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/common";

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

    public void describeCommonSecurityPoliciesAsync(
            DescribeCommonSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeCommonSecurityPoliciesResult>> callback
    ) {
        DescribeCommonSecurityPoliciesTask task = new DescribeCommonSecurityPoliciesTask(request, callback);
        session.execute(task);
    }

    public DescribeCommonSecurityPoliciesResult describeCommonSecurityPolicies(
            DescribeCommonSecurityPoliciesRequest request
    ) {
        final AsyncResult<DescribeCommonSecurityPoliciesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCommonSecurityPoliciesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateSecurityPolicyTask extends Gs2RestSessionTask<CreateSecurityPolicyResult> {
        private CreateSecurityPolicyRequest request;

        public CreateSecurityPolicyTask(
            CreateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<CreateSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSecurityPolicyResult parse(JsonNode data) {
            return CreateSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("policy", request.getPolicy());
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

    public void createSecurityPolicyAsync(
            CreateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<CreateSecurityPolicyResult>> callback
    ) {
        CreateSecurityPolicyTask task = new CreateSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public CreateSecurityPolicyResult createSecurityPolicy(
            CreateSecurityPolicyRequest request
    ) {
        final AsyncResult<CreateSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateSecurityPolicyTask extends Gs2RestSessionTask<UpdateSecurityPolicyResult> {
        private UpdateSecurityPolicyRequest request;

        public UpdateSecurityPolicyTask(
            UpdateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<UpdateSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSecurityPolicyResult parse(JsonNode data) {
            return UpdateSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/{securityPolicyName}";

            url = url.replace("{securityPolicyName}", this.request.getSecurityPolicyName() == null || this.request.getSecurityPolicyName().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("policy", request.getPolicy());
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

    public void updateSecurityPolicyAsync(
            UpdateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<UpdateSecurityPolicyResult>> callback
    ) {
        UpdateSecurityPolicyTask task = new UpdateSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public UpdateSecurityPolicyResult updateSecurityPolicy(
            UpdateSecurityPolicyRequest request
    ) {
        final AsyncResult<UpdateSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSecurityPolicyTask extends Gs2RestSessionTask<GetSecurityPolicyResult> {
        private GetSecurityPolicyRequest request;

        public GetSecurityPolicyTask(
            GetSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSecurityPolicyResult parse(JsonNode data) {
            return GetSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/{securityPolicyName}";

            url = url.replace("{securityPolicyName}", this.request.getSecurityPolicyName() == null || this.request.getSecurityPolicyName().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyName()));

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

    public void getSecurityPolicyAsync(
            GetSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetSecurityPolicyResult>> callback
    ) {
        GetSecurityPolicyTask task = new GetSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public GetSecurityPolicyResult getSecurityPolicy(
            GetSecurityPolicyRequest request
    ) {
        final AsyncResult<GetSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSecurityPolicyTask extends Gs2RestSessionTask<DeleteSecurityPolicyResult> {
        private DeleteSecurityPolicyRequest request;

        public DeleteSecurityPolicyTask(
            DeleteSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DeleteSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSecurityPolicyResult parse(JsonNode data) {
            return DeleteSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/{securityPolicyName}";

            url = url.replace("{securityPolicyName}", this.request.getSecurityPolicyName() == null || this.request.getSecurityPolicyName().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyName()));

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

    public void deleteSecurityPolicyAsync(
            DeleteSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DeleteSecurityPolicyResult>> callback
    ) {
        DeleteSecurityPolicyTask task = new DeleteSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public DeleteSecurityPolicyResult deleteSecurityPolicy(
            DeleteSecurityPolicyRequest request
    ) {
        final AsyncResult<DeleteSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeIdentifiersTask extends Gs2RestSessionTask<DescribeIdentifiersResult> {
        private DescribeIdentifiersRequest request;

        public DescribeIdentifiersTask(
            DescribeIdentifiersRequest request,
            AsyncAction<AsyncResult<DescribeIdentifiersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeIdentifiersResult parse(JsonNode data) {
            return DescribeIdentifiersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void describeIdentifiersAsync(
            DescribeIdentifiersRequest request,
            AsyncAction<AsyncResult<DescribeIdentifiersResult>> callback
    ) {
        DescribeIdentifiersTask task = new DescribeIdentifiersTask(request, callback);
        session.execute(task);
    }

    public DescribeIdentifiersResult describeIdentifiers(
            DescribeIdentifiersRequest request
    ) {
        final AsyncResult<DescribeIdentifiersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIdentifiersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateIdentifierTask extends Gs2RestSessionTask<CreateIdentifierResult> {
        private CreateIdentifierRequest request;

        public CreateIdentifierTask(
            CreateIdentifierRequest request,
            AsyncAction<AsyncResult<CreateIdentifierResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateIdentifierResult parse(JsonNode data) {
            return CreateIdentifierResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void createIdentifierAsync(
            CreateIdentifierRequest request,
            AsyncAction<AsyncResult<CreateIdentifierResult>> callback
    ) {
        CreateIdentifierTask task = new CreateIdentifierTask(request, callback);
        session.execute(task);
    }

    public CreateIdentifierResult createIdentifier(
            CreateIdentifierRequest request
    ) {
        final AsyncResult<CreateIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        createIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetIdentifierTask extends Gs2RestSessionTask<GetIdentifierResult> {
        private GetIdentifierRequest request;

        public GetIdentifierTask(
            GetIdentifierRequest request,
            AsyncAction<AsyncResult<GetIdentifierResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetIdentifierResult parse(JsonNode data) {
            return GetIdentifierResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{clientId}", this.request.getClientId() == null || this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));

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

    public void getIdentifierAsync(
            GetIdentifierRequest request,
            AsyncAction<AsyncResult<GetIdentifierResult>> callback
    ) {
        GetIdentifierTask task = new GetIdentifierTask(request, callback);
        session.execute(task);
    }

    public GetIdentifierResult getIdentifier(
            GetIdentifierRequest request
    ) {
        final AsyncResult<GetIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteIdentifierTask extends Gs2RestSessionTask<DeleteIdentifierResult> {
        private DeleteIdentifierRequest request;

        public DeleteIdentifierTask(
            DeleteIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteIdentifierResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteIdentifierResult parse(JsonNode data) {
            return DeleteIdentifierResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{clientId}", this.request.getClientId() == null || this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));

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

    public void deleteIdentifierAsync(
            DeleteIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteIdentifierResult>> callback
    ) {
        DeleteIdentifierTask task = new DeleteIdentifierTask(request, callback);
        session.execute(task);
    }

    public DeleteIdentifierResult deleteIdentifier(
            DeleteIdentifierRequest request
    ) {
        final AsyncResult<DeleteIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeAttachedGuardsTask extends Gs2RestSessionTask<DescribeAttachedGuardsResult> {
        private DescribeAttachedGuardsRequest request;

        public DescribeAttachedGuardsTask(
            DescribeAttachedGuardsRequest request,
            AsyncAction<AsyncResult<DescribeAttachedGuardsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeAttachedGuardsResult parse(JsonNode data) {
            return DescribeAttachedGuardsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}/guard";

            url = url.replace("{clientId}", this.request.getClientId() == null || this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));
            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void describeAttachedGuardsAsync(
            DescribeAttachedGuardsRequest request,
            AsyncAction<AsyncResult<DescribeAttachedGuardsResult>> callback
    ) {
        DescribeAttachedGuardsTask task = new DescribeAttachedGuardsTask(request, callback);
        session.execute(task);
    }

    public DescribeAttachedGuardsResult describeAttachedGuards(
            DescribeAttachedGuardsRequest request
    ) {
        final AsyncResult<DescribeAttachedGuardsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAttachedGuardsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AttachGuardTask extends Gs2RestSessionTask<AttachGuardResult> {
        private AttachGuardRequest request;

        public AttachGuardTask(
            AttachGuardRequest request,
            AsyncAction<AsyncResult<AttachGuardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AttachGuardResult parse(JsonNode data) {
            return AttachGuardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}/guard";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{clientId}", this.request.getClientId() == null || this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("guardNamespaceId", request.getGuardNamespaceId());
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

    public void attachGuardAsync(
            AttachGuardRequest request,
            AsyncAction<AsyncResult<AttachGuardResult>> callback
    ) {
        AttachGuardTask task = new AttachGuardTask(request, callback);
        session.execute(task);
    }

    public AttachGuardResult attachGuard(
            AttachGuardRequest request
    ) {
        final AsyncResult<AttachGuardResult>[] resultAsyncResult = new AsyncResult[]{null};
        attachGuardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DetachGuardTask extends Gs2RestSessionTask<DetachGuardResult> {
        private DetachGuardRequest request;

        public DetachGuardTask(
            DetachGuardRequest request,
            AsyncAction<AsyncResult<DetachGuardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DetachGuardResult parse(JsonNode data) {
            return DetachGuardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}/guard/{guardNamespaceId}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{clientId}", this.request.getClientId() == null || this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));
            url = url.replace("{guardNamespaceId}", this.request.getGuardNamespaceId() == null || this.request.getGuardNamespaceId().length() == 0 ? "null" : String.valueOf(this.request.getGuardNamespaceId()));

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

    public void detachGuardAsync(
            DetachGuardRequest request,
            AsyncAction<AsyncResult<DetachGuardResult>> callback
    ) {
        DetachGuardTask task = new DetachGuardTask(request, callback);
        session.execute(task);
    }

    public DetachGuardResult detachGuard(
            DetachGuardRequest request
    ) {
        final AsyncResult<DetachGuardResult>[] resultAsyncResult = new AsyncResult[]{null};
        detachGuardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePasswordsTask extends Gs2RestSessionTask<DescribePasswordsResult> {
        private DescribePasswordsRequest request;

        public DescribePasswordsTask(
            DescribePasswordsRequest request,
            AsyncAction<AsyncResult<DescribePasswordsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePasswordsResult parse(JsonNode data) {
            return DescribePasswordsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void describePasswordsAsync(
            DescribePasswordsRequest request,
            AsyncAction<AsyncResult<DescribePasswordsResult>> callback
    ) {
        DescribePasswordsTask task = new DescribePasswordsTask(request, callback);
        session.execute(task);
    }

    public DescribePasswordsResult describePasswords(
            DescribePasswordsRequest request
    ) {
        final AsyncResult<DescribePasswordsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePasswordsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreatePasswordTask extends Gs2RestSessionTask<CreatePasswordResult> {
        private CreatePasswordRequest request;

        public CreatePasswordTask(
            CreatePasswordRequest request,
            AsyncAction<AsyncResult<CreatePasswordResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreatePasswordResult parse(JsonNode data) {
            return CreatePasswordResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("password", request.getPassword());
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

    public void createPasswordAsync(
            CreatePasswordRequest request,
            AsyncAction<AsyncResult<CreatePasswordResult>> callback
    ) {
        CreatePasswordTask task = new CreatePasswordTask(request, callback);
        session.execute(task);
    }

    public CreatePasswordResult createPassword(
            CreatePasswordRequest request
    ) {
        final AsyncResult<CreatePasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPasswordTask extends Gs2RestSessionTask<GetPasswordResult> {
        private GetPasswordRequest request;

        public GetPasswordTask(
            GetPasswordRequest request,
            AsyncAction<AsyncResult<GetPasswordResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPasswordResult parse(JsonNode data) {
            return GetPasswordResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password/entity";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void getPasswordAsync(
            GetPasswordRequest request,
            AsyncAction<AsyncResult<GetPasswordResult>> callback
    ) {
        GetPasswordTask task = new GetPasswordTask(request, callback);
        session.execute(task);
    }

    public GetPasswordResult getPassword(
            GetPasswordRequest request
    ) {
        final AsyncResult<GetPasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EnableMfaTask extends Gs2RestSessionTask<EnableMfaResult> {
        private EnableMfaRequest request;

        public EnableMfaTask(
            EnableMfaRequest request,
            AsyncAction<AsyncResult<EnableMfaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public EnableMfaResult parse(JsonNode data) {
            return EnableMfaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/mfa";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void enableMfaAsync(
            EnableMfaRequest request,
            AsyncAction<AsyncResult<EnableMfaResult>> callback
    ) {
        EnableMfaTask task = new EnableMfaTask(request, callback);
        session.execute(task);
    }

    public EnableMfaResult enableMfa(
            EnableMfaRequest request
    ) {
        final AsyncResult<EnableMfaResult>[] resultAsyncResult = new AsyncResult[]{null};
        enableMfaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ChallengeMfaTask extends Gs2RestSessionTask<ChallengeMfaResult> {
        private ChallengeMfaRequest request;

        public ChallengeMfaTask(
            ChallengeMfaRequest request,
            AsyncAction<AsyncResult<ChallengeMfaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ChallengeMfaResult parse(JsonNode data) {
            return ChallengeMfaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/mfa/challenge";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("passcode", request.getPasscode());
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

    public void challengeMfaAsync(
            ChallengeMfaRequest request,
            AsyncAction<AsyncResult<ChallengeMfaResult>> callback
    ) {
        ChallengeMfaTask task = new ChallengeMfaTask(request, callback);
        session.execute(task);
    }

    public ChallengeMfaResult challengeMfa(
            ChallengeMfaRequest request
    ) {
        final AsyncResult<ChallengeMfaResult>[] resultAsyncResult = new AsyncResult[]{null};
        challengeMfaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DisableMfaTask extends Gs2RestSessionTask<DisableMfaResult> {
        private DisableMfaRequest request;

        public DisableMfaTask(
            DisableMfaRequest request,
            AsyncAction<AsyncResult<DisableMfaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DisableMfaResult parse(JsonNode data) {
            return DisableMfaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/mfa";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void disableMfaAsync(
            DisableMfaRequest request,
            AsyncAction<AsyncResult<DisableMfaResult>> callback
    ) {
        DisableMfaTask task = new DisableMfaTask(request, callback);
        session.execute(task);
    }

    public DisableMfaResult disableMfa(
            DisableMfaRequest request
    ) {
        final AsyncResult<DisableMfaResult>[] resultAsyncResult = new AsyncResult[]{null};
        disableMfaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePasswordTask extends Gs2RestSessionTask<DeletePasswordResult> {
        private DeletePasswordRequest request;

        public DeletePasswordTask(
            DeletePasswordRequest request,
            AsyncAction<AsyncResult<DeletePasswordResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePasswordResult parse(JsonNode data) {
            return DeletePasswordResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password/entity";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void deletePasswordAsync(
            DeletePasswordRequest request,
            AsyncAction<AsyncResult<DeletePasswordResult>> callback
    ) {
        DeletePasswordTask task = new DeletePasswordTask(request, callback);
        session.execute(task);
    }

    public DeletePasswordResult deletePassword(
            DeletePasswordRequest request
    ) {
        final AsyncResult<DeletePasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetHasSecurityPolicyTask extends Gs2RestSessionTask<GetHasSecurityPolicyResult> {
        private GetHasSecurityPolicyRequest request;

        public GetHasSecurityPolicyTask(
            GetHasSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetHasSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetHasSecurityPolicyResult parse(JsonNode data) {
            return GetHasSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/securityPolicy";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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

    public void getHasSecurityPolicyAsync(
            GetHasSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetHasSecurityPolicyResult>> callback
    ) {
        GetHasSecurityPolicyTask task = new GetHasSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public GetHasSecurityPolicyResult getHasSecurityPolicy(
            GetHasSecurityPolicyRequest request
    ) {
        final AsyncResult<GetHasSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getHasSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AttachSecurityPolicyTask extends Gs2RestSessionTask<AttachSecurityPolicyResult> {
        private AttachSecurityPolicyRequest request;

        public AttachSecurityPolicyTask(
            AttachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<AttachSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AttachSecurityPolicyResult parse(JsonNode data) {
            return AttachSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/securityPolicy";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("securityPolicyId", request.getSecurityPolicyId());
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

    public void attachSecurityPolicyAsync(
            AttachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<AttachSecurityPolicyResult>> callback
    ) {
        AttachSecurityPolicyTask task = new AttachSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public AttachSecurityPolicyResult attachSecurityPolicy(
            AttachSecurityPolicyRequest request
    ) {
        final AsyncResult<AttachSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        attachSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DetachSecurityPolicyTask extends Gs2RestSessionTask<DetachSecurityPolicyResult> {
        private DetachSecurityPolicyRequest request;

        public DetachSecurityPolicyTask(
            DetachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DetachSecurityPolicyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DetachSecurityPolicyResult parse(JsonNode data) {
            return DetachSecurityPolicyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/securityPolicy/{securityPolicyId}";

            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{securityPolicyId}", this.request.getSecurityPolicyId() == null || this.request.getSecurityPolicyId().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyId()));

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

    public void detachSecurityPolicyAsync(
            DetachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DetachSecurityPolicyResult>> callback
    ) {
        DetachSecurityPolicyTask task = new DetachSecurityPolicyTask(request, callback);
        session.execute(task);
    }

    public DetachSecurityPolicyResult detachSecurityPolicy(
            DetachSecurityPolicyRequest request
    ) {
        final AsyncResult<DetachSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        detachSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class LoginTask extends Gs2RestSessionTask<LoginResult> {
        private LoginRequest request;

        public LoginTask(
            LoginRequest request,
            AsyncAction<AsyncResult<LoginResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public LoginResult parse(JsonNode data) {
            return LoginResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/projectToken/login";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("client_id", request.getClientId());
                    put("client_secret", request.getClientSecret());
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

    public void loginAsync(
            LoginRequest request,
            AsyncAction<AsyncResult<LoginResult>> callback
    ) {
        LoginTask task = new LoginTask(request, callback);
        session.execute(task);
    }

    public LoginResult login(
            LoginRequest request
    ) {
        final AsyncResult<LoginResult>[] resultAsyncResult = new AsyncResult[]{null};
        loginAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class LoginByUserTask extends Gs2RestSessionTask<LoginByUserResult> {
        private LoginByUserRequest request;

        public LoginByUserTask(
            LoginByUserRequest request,
            AsyncAction<AsyncResult<LoginByUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public LoginByUserResult parse(JsonNode data) {
            return LoginByUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/projectToken/login/user";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userName", request.getUserName());
                    put("password", request.getPassword());
                    put("otp", request.getOtp());
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

    public void loginByUserAsync(
            LoginByUserRequest request,
            AsyncAction<AsyncResult<LoginByUserResult>> callback
    ) {
        LoginByUserTask task = new LoginByUserTask(request, callback);
        session.execute(task);
    }

    public LoginByUserResult loginByUser(
            LoginByUserRequest request
    ) {
        final AsyncResult<LoginByUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        loginByUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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