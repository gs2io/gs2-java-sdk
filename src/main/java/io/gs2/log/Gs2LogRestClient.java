
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

package io.gs2.log;

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
import io.gs2.log.request.*;
import io.gs2.log.result.*;
import io.gs2.log.model.*;public class Gs2LogRestClient extends AbstractGs2Client<Gs2LogRestClient> {

	public Gs2LogRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "log")
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
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("type", request.getType());
                    put("gcpCredentialJson", request.getGcpCredentialJson());
                    put("bigQueryDatasetName", request.getBigQueryDatasetName());
                    put("logExpireDays", request.getLogExpireDays());
                    put("awsRegion", request.getAwsRegion());
                    put("awsAccessKeyId", request.getAwsAccessKeyId());
                    put("awsSecretAccessKey", request.getAwsSecretAccessKey());
                    put("firehoseStreamName", request.getFirehoseStreamName());
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
                .replace("{service}", "log")
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
                .replace("{service}", "log")
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
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("type", request.getType());
                    put("gcpCredentialJson", request.getGcpCredentialJson());
                    put("bigQueryDatasetName", request.getBigQueryDatasetName());
                    put("logExpireDays", request.getLogExpireDays());
                    put("awsRegion", request.getAwsRegion());
                    put("awsAccessKeyId", request.getAwsAccessKeyId());
                    put("awsSecretAccessKey", request.getAwsSecretAccessKey());
                    put("firehoseStreamName", request.getFirehoseStreamName());
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
                .replace("{service}", "log")
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

    class QueryAccessLogTask extends Gs2RestSessionTask<QueryAccessLogResult> {
        private QueryAccessLogRequest request;

        public QueryAccessLogTask(
            QueryAccessLogRequest request,
            AsyncAction<AsyncResult<QueryAccessLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryAccessLogResult parse(JsonNode data) {
            return QueryAccessLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/access";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + EncodingUtil.urlEncode((String.valueOf(this.request.getService()))));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + EncodingUtil.urlEncode((String.valueOf(this.request.getMethod()))));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void queryAccessLogAsync(
            QueryAccessLogRequest request,
            AsyncAction<AsyncResult<QueryAccessLogResult>> callback
    ) {
        QueryAccessLogTask task = new QueryAccessLogTask(request, callback);
        session.execute(task);
    }

    public QueryAccessLogResult queryAccessLog(
            QueryAccessLogRequest request
    ) {
        final AsyncResult<QueryAccessLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryAccessLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountAccessLogTask extends Gs2RestSessionTask<CountAccessLogResult> {
        private CountAccessLogRequest request;

        public CountAccessLogTask(
            CountAccessLogRequest request,
            AsyncAction<AsyncResult<CountAccessLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CountAccessLogResult parse(JsonNode data) {
            return CountAccessLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/access/count";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + String.valueOf(this.request.getService()));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + String.valueOf(this.request.getMethod()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + String.valueOf(this.request.getUserId()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void countAccessLogAsync(
            CountAccessLogRequest request,
            AsyncAction<AsyncResult<CountAccessLogResult>> callback
    ) {
        CountAccessLogTask task = new CountAccessLogTask(request, callback);
        session.execute(task);
    }

    public CountAccessLogResult countAccessLog(
            CountAccessLogRequest request
    ) {
        final AsyncResult<CountAccessLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        countAccessLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryIssueStampSheetLogTask extends Gs2RestSessionTask<QueryIssueStampSheetLogResult> {
        private QueryIssueStampSheetLogRequest request;

        public QueryIssueStampSheetLogTask(
            QueryIssueStampSheetLogRequest request,
            AsyncAction<AsyncResult<QueryIssueStampSheetLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryIssueStampSheetLogResult parse(JsonNode data) {
            return QueryIssueStampSheetLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/issue/stamp/sheet";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + EncodingUtil.urlEncode((String.valueOf(this.request.getService()))));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + EncodingUtil.urlEncode((String.valueOf(this.request.getMethod()))));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
            }
            if (this.request.getAction() != null) {
                queryStrings.add("action=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAction()))));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void queryIssueStampSheetLogAsync(
            QueryIssueStampSheetLogRequest request,
            AsyncAction<AsyncResult<QueryIssueStampSheetLogResult>> callback
    ) {
        QueryIssueStampSheetLogTask task = new QueryIssueStampSheetLogTask(request, callback);
        session.execute(task);
    }

    public QueryIssueStampSheetLogResult queryIssueStampSheetLog(
            QueryIssueStampSheetLogRequest request
    ) {
        final AsyncResult<QueryIssueStampSheetLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryIssueStampSheetLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountIssueStampSheetLogTask extends Gs2RestSessionTask<CountIssueStampSheetLogResult> {
        private CountIssueStampSheetLogRequest request;

        public CountIssueStampSheetLogTask(
            CountIssueStampSheetLogRequest request,
            AsyncAction<AsyncResult<CountIssueStampSheetLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CountIssueStampSheetLogResult parse(JsonNode data) {
            return CountIssueStampSheetLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/issue/stamp/sheet/count";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + String.valueOf(this.request.getService()));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + String.valueOf(this.request.getMethod()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + String.valueOf(this.request.getUserId()));
            }
            if (this.request.getAction() != null) {
                queryStrings.add("action=" + String.valueOf(this.request.getAction()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void countIssueStampSheetLogAsync(
            CountIssueStampSheetLogRequest request,
            AsyncAction<AsyncResult<CountIssueStampSheetLogResult>> callback
    ) {
        CountIssueStampSheetLogTask task = new CountIssueStampSheetLogTask(request, callback);
        session.execute(task);
    }

    public CountIssueStampSheetLogResult countIssueStampSheetLog(
            CountIssueStampSheetLogRequest request
    ) {
        final AsyncResult<CountIssueStampSheetLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        countIssueStampSheetLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryExecuteStampSheetLogTask extends Gs2RestSessionTask<QueryExecuteStampSheetLogResult> {
        private QueryExecuteStampSheetLogRequest request;

        public QueryExecuteStampSheetLogTask(
            QueryExecuteStampSheetLogRequest request,
            AsyncAction<AsyncResult<QueryExecuteStampSheetLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryExecuteStampSheetLogResult parse(JsonNode data) {
            return QueryExecuteStampSheetLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/execute/stamp/sheet";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + EncodingUtil.urlEncode((String.valueOf(this.request.getService()))));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + EncodingUtil.urlEncode((String.valueOf(this.request.getMethod()))));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
            }
            if (this.request.getAction() != null) {
                queryStrings.add("action=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAction()))));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void queryExecuteStampSheetLogAsync(
            QueryExecuteStampSheetLogRequest request,
            AsyncAction<AsyncResult<QueryExecuteStampSheetLogResult>> callback
    ) {
        QueryExecuteStampSheetLogTask task = new QueryExecuteStampSheetLogTask(request, callback);
        session.execute(task);
    }

    public QueryExecuteStampSheetLogResult queryExecuteStampSheetLog(
            QueryExecuteStampSheetLogRequest request
    ) {
        final AsyncResult<QueryExecuteStampSheetLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryExecuteStampSheetLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountExecuteStampSheetLogTask extends Gs2RestSessionTask<CountExecuteStampSheetLogResult> {
        private CountExecuteStampSheetLogRequest request;

        public CountExecuteStampSheetLogTask(
            CountExecuteStampSheetLogRequest request,
            AsyncAction<AsyncResult<CountExecuteStampSheetLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CountExecuteStampSheetLogResult parse(JsonNode data) {
            return CountExecuteStampSheetLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/execute/stamp/sheet/count";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + String.valueOf(this.request.getService()));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + String.valueOf(this.request.getMethod()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + String.valueOf(this.request.getUserId()));
            }
            if (this.request.getAction() != null) {
                queryStrings.add("action=" + String.valueOf(this.request.getAction()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void countExecuteStampSheetLogAsync(
            CountExecuteStampSheetLogRequest request,
            AsyncAction<AsyncResult<CountExecuteStampSheetLogResult>> callback
    ) {
        CountExecuteStampSheetLogTask task = new CountExecuteStampSheetLogTask(request, callback);
        session.execute(task);
    }

    public CountExecuteStampSheetLogResult countExecuteStampSheetLog(
            CountExecuteStampSheetLogRequest request
    ) {
        final AsyncResult<CountExecuteStampSheetLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        countExecuteStampSheetLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryExecuteStampTaskLogTask extends Gs2RestSessionTask<QueryExecuteStampTaskLogResult> {
        private QueryExecuteStampTaskLogRequest request;

        public QueryExecuteStampTaskLogTask(
            QueryExecuteStampTaskLogRequest request,
            AsyncAction<AsyncResult<QueryExecuteStampTaskLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryExecuteStampTaskLogResult parse(JsonNode data) {
            return QueryExecuteStampTaskLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/execute/stamp/task";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + EncodingUtil.urlEncode((String.valueOf(this.request.getService()))));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + EncodingUtil.urlEncode((String.valueOf(this.request.getMethod()))));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
            }
            if (this.request.getAction() != null) {
                queryStrings.add("action=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAction()))));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void queryExecuteStampTaskLogAsync(
            QueryExecuteStampTaskLogRequest request,
            AsyncAction<AsyncResult<QueryExecuteStampTaskLogResult>> callback
    ) {
        QueryExecuteStampTaskLogTask task = new QueryExecuteStampTaskLogTask(request, callback);
        session.execute(task);
    }

    public QueryExecuteStampTaskLogResult queryExecuteStampTaskLog(
            QueryExecuteStampTaskLogRequest request
    ) {
        final AsyncResult<QueryExecuteStampTaskLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryExecuteStampTaskLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountExecuteStampTaskLogTask extends Gs2RestSessionTask<CountExecuteStampTaskLogResult> {
        private CountExecuteStampTaskLogRequest request;

        public CountExecuteStampTaskLogTask(
            CountExecuteStampTaskLogRequest request,
            AsyncAction<AsyncResult<CountExecuteStampTaskLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CountExecuteStampTaskLogResult parse(JsonNode data) {
            return CountExecuteStampTaskLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/execute/stamp/task/count";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + String.valueOf(this.request.getService()));
            }
            if (this.request.getMethod() != null) {
                queryStrings.add("method=" + String.valueOf(this.request.getMethod()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + String.valueOf(this.request.getUserId()));
            }
            if (this.request.getAction() != null) {
                queryStrings.add("action=" + String.valueOf(this.request.getAction()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void countExecuteStampTaskLogAsync(
            CountExecuteStampTaskLogRequest request,
            AsyncAction<AsyncResult<CountExecuteStampTaskLogResult>> callback
    ) {
        CountExecuteStampTaskLogTask task = new CountExecuteStampTaskLogTask(request, callback);
        session.execute(task);
    }

    public CountExecuteStampTaskLogResult countExecuteStampTaskLog(
            CountExecuteStampTaskLogRequest request
    ) {
        final AsyncResult<CountExecuteStampTaskLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        countExecuteStampTaskLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryAccessLogWithTelemetryTask extends Gs2RestSessionTask<QueryAccessLogWithTelemetryResult> {
        private QueryAccessLogWithTelemetryRequest request;

        public QueryAccessLogWithTelemetryTask(
            QueryAccessLogWithTelemetryRequest request,
            AsyncAction<AsyncResult<QueryAccessLogWithTelemetryResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryAccessLogWithTelemetryResult parse(JsonNode data) {
            return QueryAccessLogWithTelemetryResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/access/telemetry";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
            }
            if (this.request.getLongTerm() != null) {
                queryStrings.add("longTerm=" + String.valueOf(this.request.getLongTerm()));
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

    public void queryAccessLogWithTelemetryAsync(
            QueryAccessLogWithTelemetryRequest request,
            AsyncAction<AsyncResult<QueryAccessLogWithTelemetryResult>> callback
    ) {
        QueryAccessLogWithTelemetryTask task = new QueryAccessLogWithTelemetryTask(request, callback);
        session.execute(task);
    }

    public QueryAccessLogWithTelemetryResult queryAccessLogWithTelemetry(
            QueryAccessLogWithTelemetryRequest request
    ) {
        final AsyncResult<QueryAccessLogWithTelemetryResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryAccessLogWithTelemetryAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutLogTask extends Gs2RestSessionTask<PutLogResult> {
        private PutLogRequest request;

        public PutLogTask(
            PutLogRequest request,
            AsyncAction<AsyncResult<PutLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutLogResult parse(JsonNode data) {
            return PutLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/log/put";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("loggingNamespaceId", request.getLoggingNamespaceId());
                    put("logCategory", request.getLogCategory());
                    put("payload", request.getPayload());
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

    public void putLogAsync(
            PutLogRequest request,
            AsyncAction<AsyncResult<PutLogResult>> callback
    ) {
        PutLogTask task = new PutLogTask(request, callback);
        session.execute(task);
    }

    public PutLogResult putLog(
            PutLogRequest request
    ) {
        final AsyncResult<PutLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        putLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeInsightsTask extends Gs2RestSessionTask<DescribeInsightsResult> {
        private DescribeInsightsRequest request;

        public DescribeInsightsTask(
            DescribeInsightsRequest request,
            AsyncAction<AsyncResult<DescribeInsightsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeInsightsResult parse(JsonNode data) {
            return DescribeInsightsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/insight";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    public void describeInsightsAsync(
            DescribeInsightsRequest request,
            AsyncAction<AsyncResult<DescribeInsightsResult>> callback
    ) {
        DescribeInsightsTask task = new DescribeInsightsTask(request, callback);
        session.execute(task);
    }

    public DescribeInsightsResult describeInsights(
            DescribeInsightsRequest request
    ) {
        final AsyncResult<DescribeInsightsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInsightsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateInsightTask extends Gs2RestSessionTask<CreateInsightResult> {
        private CreateInsightRequest request;

        public CreateInsightTask(
            CreateInsightRequest request,
            AsyncAction<AsyncResult<CreateInsightResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateInsightResult parse(JsonNode data) {
            return CreateInsightResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/insight";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    public void createInsightAsync(
            CreateInsightRequest request,
            AsyncAction<AsyncResult<CreateInsightResult>> callback
    ) {
        CreateInsightTask task = new CreateInsightTask(request, callback);
        session.execute(task);
    }

    public CreateInsightResult createInsight(
            CreateInsightRequest request
    ) {
        final AsyncResult<CreateInsightResult>[] resultAsyncResult = new AsyncResult[]{null};
        createInsightAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetInsightTask extends Gs2RestSessionTask<GetInsightResult> {
        private GetInsightRequest request;

        public GetInsightTask(
            GetInsightRequest request,
            AsyncAction<AsyncResult<GetInsightResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetInsightResult parse(JsonNode data) {
            return GetInsightResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/insight/{insightName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{insightName}", this.request.getInsightName() == null || this.request.getInsightName().length() == 0 ? "null" : String.valueOf(this.request.getInsightName()));

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

    public void getInsightAsync(
            GetInsightRequest request,
            AsyncAction<AsyncResult<GetInsightResult>> callback
    ) {
        GetInsightTask task = new GetInsightTask(request, callback);
        session.execute(task);
    }

    public GetInsightResult getInsight(
            GetInsightRequest request
    ) {
        final AsyncResult<GetInsightResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInsightAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteInsightTask extends Gs2RestSessionTask<DeleteInsightResult> {
        private DeleteInsightRequest request;

        public DeleteInsightTask(
            DeleteInsightRequest request,
            AsyncAction<AsyncResult<DeleteInsightResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteInsightResult parse(JsonNode data) {
            return DeleteInsightResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/insight/{insightName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{insightName}", this.request.getInsightName() == null || this.request.getInsightName().length() == 0 ? "null" : String.valueOf(this.request.getInsightName()));

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

    public void deleteInsightAsync(
            DeleteInsightRequest request,
            AsyncAction<AsyncResult<DeleteInsightResult>> callback
    ) {
        DeleteInsightTask task = new DeleteInsightTask(request, callback);
        session.execute(task);
    }

    public DeleteInsightResult deleteInsight(
            DeleteInsightRequest request
    ) {
        final AsyncResult<DeleteInsightResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteInsightAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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