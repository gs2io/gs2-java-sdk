
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
import java.util.concurrent.atomic.AtomicReference;
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
import io.gs2.log.model.*;

public class Gs2LogRestClient extends AbstractGs2Client<Gs2LogRestClient> {

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
                    put("firehoseCompressData", request.getFirehoseCompressData());
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
                    put("firehoseCompressData", request.getFirehoseCompressData());
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

    class GetServiceVersionTask extends Gs2RestSessionTask<GetServiceVersionResult> {
        private GetServiceVersionRequest request;

        public GetServiceVersionTask(
            GetServiceVersionRequest request,
            AsyncAction<AsyncResult<GetServiceVersionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetServiceVersionResult parse(JsonNode data) {
            return GetServiceVersionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/system/version";

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

    public void getServiceVersionAsync(
            GetServiceVersionRequest request,
            AsyncAction<AsyncResult<GetServiceVersionResult>> callback
    ) {
        GetServiceVersionTask task = new GetServiceVersionTask(request, callback);
        session.execute(task);
    }

    public GetServiceVersionResult getServiceVersion(
            GetServiceVersionRequest request
    ) {
        final AsyncResult<GetServiceVersionResult>[] resultAsyncResult = new AsyncResult[]{null};
        getServiceVersionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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

    class QueryInGameLogTask extends Gs2RestSessionTask<QueryInGameLogResult> {
        private QueryInGameLogRequest request;

        public QueryInGameLogTask(
            QueryInGameLogRequest request,
            AsyncAction<AsyncResult<QueryInGameLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryInGameLogResult parse(JsonNode data) {
            return QueryInGameLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ingame/log";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("tags", request.getTags() == null ? null :
                        request.getTags().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("begin", request.getBegin());
                    put("end", request.getEnd());
                    put("longTerm", request.getLongTerm());
                    put("pageToken", request.getPageToken());
                    put("limit", request.getLimit());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void queryInGameLogAsync(
            QueryInGameLogRequest request,
            AsyncAction<AsyncResult<QueryInGameLogResult>> callback
    ) {
        QueryInGameLogTask task = new QueryInGameLogTask(request, callback);
        session.execute(task);
    }

    public QueryInGameLogResult queryInGameLog(
            QueryInGameLogRequest request
    ) {
        final AsyncResult<QueryInGameLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryInGameLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendInGameLogTask extends Gs2RestSessionTask<SendInGameLogResult> {
        private SendInGameLogRequest request;

        public SendInGameLogTask(
            SendInGameLogRequest request,
            AsyncAction<AsyncResult<SendInGameLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendInGameLogResult parse(JsonNode data) {
            return SendInGameLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ingame/log/user/me/send";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("tags", request.getTags() == null ? null :
                        request.getTags().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
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

    public void sendInGameLogAsync(
            SendInGameLogRequest request,
            AsyncAction<AsyncResult<SendInGameLogResult>> callback
    ) {
        SendInGameLogTask task = new SendInGameLogTask(request, callback);
        session.execute(task);
    }

    public SendInGameLogResult sendInGameLog(
            SendInGameLogRequest request
    ) {
        final AsyncResult<SendInGameLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendInGameLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendInGameLogByUserIdTask extends Gs2RestSessionTask<SendInGameLogByUserIdResult> {
        private SendInGameLogByUserIdRequest request;

        public SendInGameLogByUserIdTask(
            SendInGameLogByUserIdRequest request,
            AsyncAction<AsyncResult<SendInGameLogByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendInGameLogByUserIdResult parse(JsonNode data) {
            return SendInGameLogByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ingame/log/user/{userId}/send";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("tags", request.getTags() == null ? null :
                        request.getTags().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void sendInGameLogByUserIdAsync(
            SendInGameLogByUserIdRequest request,
            AsyncAction<AsyncResult<SendInGameLogByUserIdResult>> callback
    ) {
        SendInGameLogByUserIdTask task = new SendInGameLogByUserIdTask(request, callback);
        session.execute(task);
    }

    public SendInGameLogByUserIdResult sendInGameLogByUserId(
            SendInGameLogByUserIdRequest request
    ) {
        final AsyncResult<SendInGameLogByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendInGameLogByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
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

    class DescribeFacetModelsTask extends Gs2RestSessionTask<DescribeFacetModelsResult> {
        private DescribeFacetModelsRequest request;

        public DescribeFacetModelsTask(
            DescribeFacetModelsRequest request,
            AsyncAction<AsyncResult<DescribeFacetModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeFacetModelsResult parse(JsonNode data) {
            return DescribeFacetModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/facet";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getNamePrefix() != null) {
                queryStrings.add("namePrefix=" + EncodingUtil.urlEncode((String.valueOf(this.request.getNamePrefix()))));
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

    public void describeFacetModelsAsync(
            DescribeFacetModelsRequest request,
            AsyncAction<AsyncResult<DescribeFacetModelsResult>> callback
    ) {
        DescribeFacetModelsTask task = new DescribeFacetModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeFacetModelsResult describeFacetModels(
            DescribeFacetModelsRequest request
    ) {
        final AsyncResult<DescribeFacetModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFacetModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateFacetModelTask extends Gs2RestSessionTask<CreateFacetModelResult> {
        private CreateFacetModelRequest request;

        public CreateFacetModelTask(
            CreateFacetModelRequest request,
            AsyncAction<AsyncResult<CreateFacetModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateFacetModelResult parse(JsonNode data) {
            return CreateFacetModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/facet";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("field", request.getField());
                    put("type", request.getType());
                    put("displayName", request.getDisplayName());
                    put("order", request.getOrder());
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

    public void createFacetModelAsync(
            CreateFacetModelRequest request,
            AsyncAction<AsyncResult<CreateFacetModelResult>> callback
    ) {
        CreateFacetModelTask task = new CreateFacetModelTask(request, callback);
        session.execute(task);
    }

    public CreateFacetModelResult createFacetModel(
            CreateFacetModelRequest request
    ) {
        final AsyncResult<CreateFacetModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        createFacetModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFacetModelTask extends Gs2RestSessionTask<GetFacetModelResult> {
        private GetFacetModelRequest request;

        public GetFacetModelTask(
            GetFacetModelRequest request,
            AsyncAction<AsyncResult<GetFacetModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetFacetModelResult parse(JsonNode data) {
            return GetFacetModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/facet/{field}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{field}", this.request.getField() == null || this.request.getField().length() == 0 ? "null" : String.valueOf(this.request.getField()));

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

    public void getFacetModelAsync(
            GetFacetModelRequest request,
            AsyncAction<AsyncResult<GetFacetModelResult>> callback
    ) {
        GetFacetModelTask task = new GetFacetModelTask(request, callback);
        session.execute(task);
    }

    public GetFacetModelResult getFacetModel(
            GetFacetModelRequest request
    ) {
        final AsyncResult<GetFacetModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFacetModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateFacetModelTask extends Gs2RestSessionTask<UpdateFacetModelResult> {
        private UpdateFacetModelRequest request;

        public UpdateFacetModelTask(
            UpdateFacetModelRequest request,
            AsyncAction<AsyncResult<UpdateFacetModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateFacetModelResult parse(JsonNode data) {
            return UpdateFacetModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/facet/{field}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{field}", this.request.getField() == null || this.request.getField().length() == 0 ? "null" : String.valueOf(this.request.getField()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("displayName", request.getDisplayName());
                    put("order", request.getOrder());
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

    public void updateFacetModelAsync(
            UpdateFacetModelRequest request,
            AsyncAction<AsyncResult<UpdateFacetModelResult>> callback
    ) {
        UpdateFacetModelTask task = new UpdateFacetModelTask(request, callback);
        session.execute(task);
    }

    public UpdateFacetModelResult updateFacetModel(
            UpdateFacetModelRequest request
    ) {
        final AsyncResult<UpdateFacetModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateFacetModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFacetModelTask extends Gs2RestSessionTask<DeleteFacetModelResult> {
        private DeleteFacetModelRequest request;

        public DeleteFacetModelTask(
            DeleteFacetModelRequest request,
            AsyncAction<AsyncResult<DeleteFacetModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteFacetModelResult parse(JsonNode data) {
            return DeleteFacetModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/facet/{field}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{field}", this.request.getField() == null || this.request.getField().length() == 0 ? "null" : String.valueOf(this.request.getField()));

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

    public void deleteFacetModelAsync(
            DeleteFacetModelRequest request,
            AsyncAction<AsyncResult<DeleteFacetModelResult>> callback
    ) {
        DeleteFacetModelTask task = new DeleteFacetModelTask(request, callback);
        session.execute(task);
    }

    public DeleteFacetModelResult deleteFacetModel(
            DeleteFacetModelRequest request
    ) {
        final AsyncResult<DeleteFacetModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFacetModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeDashboardsTask extends Gs2RestSessionTask<DescribeDashboardsResult> {
        private DescribeDashboardsRequest request;

        public DescribeDashboardsTask(
            DescribeDashboardsRequest request,
            AsyncAction<AsyncResult<DescribeDashboardsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeDashboardsResult parse(JsonNode data) {
            return DescribeDashboardsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/dashboard";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getNamePrefix() != null) {
                queryStrings.add("namePrefix=" + EncodingUtil.urlEncode((String.valueOf(this.request.getNamePrefix()))));
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

    public void describeDashboardsAsync(
            DescribeDashboardsRequest request,
            AsyncAction<AsyncResult<DescribeDashboardsResult>> callback
    ) {
        DescribeDashboardsTask task = new DescribeDashboardsTask(request, callback);
        session.execute(task);
    }

    public DescribeDashboardsResult describeDashboards(
            DescribeDashboardsRequest request
    ) {
        final AsyncResult<DescribeDashboardsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDashboardsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateDashboardTask extends Gs2RestSessionTask<CreateDashboardResult> {
        private CreateDashboardRequest request;

        public CreateDashboardTask(
            CreateDashboardRequest request,
            AsyncAction<AsyncResult<CreateDashboardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateDashboardResult parse(JsonNode data) {
            return CreateDashboardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/dashboard";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
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

    public void createDashboardAsync(
            CreateDashboardRequest request,
            AsyncAction<AsyncResult<CreateDashboardResult>> callback
    ) {
        CreateDashboardTask task = new CreateDashboardTask(request, callback);
        session.execute(task);
    }

    public CreateDashboardResult createDashboard(
            CreateDashboardRequest request
    ) {
        final AsyncResult<CreateDashboardResult>[] resultAsyncResult = new AsyncResult[]{null};
        createDashboardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetDashboardTask extends Gs2RestSessionTask<GetDashboardResult> {
        private GetDashboardRequest request;

        public GetDashboardTask(
            GetDashboardRequest request,
            AsyncAction<AsyncResult<GetDashboardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDashboardResult parse(JsonNode data) {
            return GetDashboardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/dashboard/{dashboardName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dashboardName}", this.request.getDashboardName() == null || this.request.getDashboardName().length() == 0 ? "null" : String.valueOf(this.request.getDashboardName()));

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

    public void getDashboardAsync(
            GetDashboardRequest request,
            AsyncAction<AsyncResult<GetDashboardResult>> callback
    ) {
        GetDashboardTask task = new GetDashboardTask(request, callback);
        session.execute(task);
    }

    public GetDashboardResult getDashboard(
            GetDashboardRequest request
    ) {
        final AsyncResult<GetDashboardResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDashboardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateDashboardTask extends Gs2RestSessionTask<UpdateDashboardResult> {
        private UpdateDashboardRequest request;

        public UpdateDashboardTask(
            UpdateDashboardRequest request,
            AsyncAction<AsyncResult<UpdateDashboardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateDashboardResult parse(JsonNode data) {
            return UpdateDashboardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/dashboard/{dashboardName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dashboardName}", this.request.getDashboardName() == null || this.request.getDashboardName().length() == 0 ? "null" : String.valueOf(this.request.getDashboardName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("description", request.getDescription());
                    put("payload", request.getPayload());
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

    public void updateDashboardAsync(
            UpdateDashboardRequest request,
            AsyncAction<AsyncResult<UpdateDashboardResult>> callback
    ) {
        UpdateDashboardTask task = new UpdateDashboardTask(request, callback);
        session.execute(task);
    }

    public UpdateDashboardResult updateDashboard(
            UpdateDashboardRequest request
    ) {
        final AsyncResult<UpdateDashboardResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateDashboardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DuplicateDashboardTask extends Gs2RestSessionTask<DuplicateDashboardResult> {
        private DuplicateDashboardRequest request;

        public DuplicateDashboardTask(
            DuplicateDashboardRequest request,
            AsyncAction<AsyncResult<DuplicateDashboardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DuplicateDashboardResult parse(JsonNode data) {
            return DuplicateDashboardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/dashboard/{dashboardName}/copy";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dashboardName}", this.request.getDashboardName() == null || this.request.getDashboardName().length() == 0 ? "null" : String.valueOf(this.request.getDashboardName()));

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

    public void duplicateDashboardAsync(
            DuplicateDashboardRequest request,
            AsyncAction<AsyncResult<DuplicateDashboardResult>> callback
    ) {
        DuplicateDashboardTask task = new DuplicateDashboardTask(request, callback);
        session.execute(task);
    }

    public DuplicateDashboardResult duplicateDashboard(
            DuplicateDashboardRequest request
    ) {
        final AsyncResult<DuplicateDashboardResult>[] resultAsyncResult = new AsyncResult[]{null};
        duplicateDashboardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteDashboardTask extends Gs2RestSessionTask<DeleteDashboardResult> {
        private DeleteDashboardRequest request;

        public DeleteDashboardTask(
            DeleteDashboardRequest request,
            AsyncAction<AsyncResult<DeleteDashboardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteDashboardResult parse(JsonNode data) {
            return DeleteDashboardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/dashboard/{dashboardName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dashboardName}", this.request.getDashboardName() == null || this.request.getDashboardName().length() == 0 ? "null" : String.valueOf(this.request.getDashboardName()));

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

    public void deleteDashboardAsync(
            DeleteDashboardRequest request,
            AsyncAction<AsyncResult<DeleteDashboardResult>> callback
    ) {
        DeleteDashboardTask task = new DeleteDashboardTask(request, callback);
        session.execute(task);
    }

    public DeleteDashboardResult deleteDashboard(
            DeleteDashboardRequest request
    ) {
        final AsyncResult<DeleteDashboardResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteDashboardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryLogTask extends Gs2RestSessionTask<QueryLogResult> {
        private QueryLogRequest request;

        public QueryLogTask(
            QueryLogRequest request,
            AsyncAction<AsyncResult<QueryLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryLogResult parse(JsonNode data) {
            return QueryLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/v2/query";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("begin", request.getBegin());
                    put("end", request.getEnd());
                    put("query", request.getQuery());
                    put("pageToken", request.getPageToken());
                    put("limit", request.getLimit());
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

    public void queryLogAsync(
            QueryLogRequest request,
            AsyncAction<AsyncResult<QueryLogResult>> callback
    ) {
        QueryLogTask task = new QueryLogTask(request, callback);
        session.execute(task);
    }

    public QueryLogResult queryLog(
            QueryLogRequest request
    ) {
        final AsyncResult<QueryLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLogTask extends Gs2RestSessionTask<GetLogResult> {
        private GetLogRequest request;

        public GetLogTask(
            GetLogRequest request,
            AsyncAction<AsyncResult<GetLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLogResult parse(JsonNode data) {
            return GetLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/v2/query/{logRequestId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{logRequestId}", this.request.getLogRequestId() == null || this.request.getLogRequestId().length() == 0 ? "null" : String.valueOf(this.request.getLogRequestId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
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

    public void getLogAsync(
            GetLogRequest request,
            AsyncAction<AsyncResult<GetLogResult>> callback
    ) {
        GetLogTask task = new GetLogTask(request, callback);
        session.execute(task);
    }

    public GetLogResult getLog(
            GetLogRequest request
    ) {
        final AsyncResult<GetLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLogAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryFacetsTask extends Gs2RestSessionTask<QueryFacetsResult> {
        private QueryFacetsRequest request;

        public QueryFacetsTask(
            QueryFacetsRequest request,
            AsyncAction<AsyncResult<QueryFacetsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryFacetsResult parse(JsonNode data) {
            return QueryFacetsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/v2/query/facet";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("begin", request.getBegin());
                    put("end", request.getEnd());
                    put("query", request.getQuery());
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

    public void queryFacetsAsync(
            QueryFacetsRequest request,
            AsyncAction<AsyncResult<QueryFacetsResult>> callback
    ) {
        QueryFacetsTask task = new QueryFacetsTask(request, callback);
        session.execute(task);
    }

    public QueryFacetsResult queryFacets(
            QueryFacetsRequest request
    ) {
        final AsyncResult<QueryFacetsResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryFacetsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryTimeseriesTask extends Gs2RestSessionTask<QueryTimeseriesResult> {
        private QueryTimeseriesRequest request;

        public QueryTimeseriesTask(
            QueryTimeseriesRequest request,
            AsyncAction<AsyncResult<QueryTimeseriesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryTimeseriesResult parse(JsonNode data) {
            return QueryTimeseriesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/v2/timeseries";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("begin", request.getBegin());
                    put("end", request.getEnd());
                    put("query", request.getQuery());
                    put("groupBy", request.getGroupBy() == null ? null :
                        request.getGroupBy().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("aggregation", request.getAggregation() != null ? request.getAggregation().toJson() : null);
                    put("interval", request.getInterval());
                    put("seriesLimit", request.getSeriesLimit());
                    put("pageToken", request.getPageToken());
                    put("limit", request.getLimit());
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

    public void queryTimeseriesAsync(
            QueryTimeseriesRequest request,
            AsyncAction<AsyncResult<QueryTimeseriesResult>> callback
    ) {
        QueryTimeseriesTask task = new QueryTimeseriesTask(request, callback);
        session.execute(task);
    }

    public QueryTimeseriesResult queryTimeseries(
            QueryTimeseriesRequest request
    ) {
        final AsyncResult<QueryTimeseriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryTimeseriesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetTraceTask extends Gs2RestSessionTask<GetTraceResult> {
        private GetTraceRequest request;

        public GetTraceTask(
            GetTraceRequest request,
            AsyncAction<AsyncResult<GetTraceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetTraceResult parse(JsonNode data) {
            return GetTraceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/log/v2/trace/{traceId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{traceId}", this.request.getTraceId() == null || this.request.getTraceId().length() == 0 ? "null" : String.valueOf(this.request.getTraceId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
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

    public void getTraceAsync(
            GetTraceRequest request,
            AsyncAction<AsyncResult<GetTraceResult>> callback
    ) {
        GetTraceTask task = new GetTraceTask(request, callback);
        session.execute(task);
    }

    public GetTraceResult getTrace(
            GetTraceRequest request
    ) {
        final AsyncResult<GetTraceResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTraceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryMetricsTimeseriesTask extends Gs2RestSessionTask<QueryMetricsTimeseriesResult> {
        private QueryMetricsTimeseriesRequest request;

        public QueryMetricsTimeseriesTask(
            QueryMetricsTimeseriesRequest request,
            AsyncAction<AsyncResult<QueryMetricsTimeseriesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public QueryMetricsTimeseriesResult parse(JsonNode data) {
            return QueryMetricsTimeseriesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/metrics/timeseries";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("begin", request.getBegin());
                    put("end", request.getEnd());
                    put("query", request.getQuery());
                    put("groupBy", request.getGroupBy() == null ? null :
                        request.getGroupBy().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("aggregations", request.getAggregations() == null ? null :
                        request.getAggregations().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("interval", request.getInterval());
                    put("seriesLimit", request.getSeriesLimit());
                    put("orderKey", request.getOrderKey());
                    put("orderBy", request.getOrderBy());
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

    public void queryMetricsTimeseriesAsync(
            QueryMetricsTimeseriesRequest request,
            AsyncAction<AsyncResult<QueryMetricsTimeseriesResult>> callback
    ) {
        QueryMetricsTimeseriesTask task = new QueryMetricsTimeseriesTask(request, callback);
        session.execute(task);
    }

    public QueryMetricsTimeseriesResult queryMetricsTimeseries(
            QueryMetricsTimeseriesRequest request
    ) {
        final AsyncResult<QueryMetricsTimeseriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryMetricsTimeseriesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMetricsTask extends Gs2RestSessionTask<DescribeMetricsResult> {
        private DescribeMetricsRequest request;

        public DescribeMetricsTask(
            DescribeMetricsRequest request,
            AsyncAction<AsyncResult<DescribeMetricsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMetricsResult parse(JsonNode data) {
            return DescribeMetricsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/metrics";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getNamePrefix() != null) {
                queryStrings.add("namePrefix=" + EncodingUtil.urlEncode((String.valueOf(this.request.getNamePrefix()))));
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

    public void describeMetricsAsync(
            DescribeMetricsRequest request,
            AsyncAction<AsyncResult<DescribeMetricsResult>> callback
    ) {
        DescribeMetricsTask task = new DescribeMetricsTask(request, callback);
        session.execute(task);
    }

    public DescribeMetricsResult describeMetrics(
            DescribeMetricsRequest request
    ) {
        final AsyncResult<DescribeMetricsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMetricsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeLabelValuesTask extends Gs2RestSessionTask<DescribeLabelValuesResult> {
        private DescribeLabelValuesRequest request;

        public DescribeLabelValuesTask(
            DescribeLabelValuesRequest request,
            AsyncAction<AsyncResult<DescribeLabelValuesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeLabelValuesResult parse(JsonNode data) {
            return DescribeLabelValuesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "log")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/metrics/{metricName}/label";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{metricName}", this.request.getMetricName() == null || this.request.getMetricName().length() == 0 ? "null" : String.valueOf(this.request.getMetricName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getLabelNamePrefix() != null) {
                queryStrings.add("labelNamePrefix=" + EncodingUtil.urlEncode((String.valueOf(this.request.getLabelNamePrefix()))));
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

    public void describeLabelValuesAsync(
            DescribeLabelValuesRequest request,
            AsyncAction<AsyncResult<DescribeLabelValuesResult>> callback
    ) {
        DescribeLabelValuesTask task = new DescribeLabelValuesTask(request, callback);
        session.execute(task);
    }

    public DescribeLabelValuesResult describeLabelValues(
            DescribeLabelValuesRequest request
    ) {
        final AsyncResult<DescribeLabelValuesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLabelValuesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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