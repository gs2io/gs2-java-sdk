
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

package io.gs2.serialKey;

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
import io.gs2.serialKey.request.*;
import io.gs2.serialKey.result.*;
import io.gs2.serialKey.model.*;public class Gs2SerialKeyRestClient extends AbstractGs2Client<Gs2SerialKeyRestClient> {

	public Gs2SerialKeyRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "serial-key")
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
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
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
                .replace("{service}", "serial-key")
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
                .replace("{service}", "serial-key")
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
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
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
                .replace("{service}", "serial-key")
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

    class DescribeIssueJobsTask extends Gs2RestSessionTask<DescribeIssueJobsResult> {
        private DescribeIssueJobsRequest request;

        public DescribeIssueJobsTask(
            DescribeIssueJobsRequest request,
            AsyncAction<AsyncResult<DescribeIssueJobsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeIssueJobsResult parse(JsonNode data) {
            return DescribeIssueJobsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign/{campaignModelName}/issue";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));

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

    public void describeIssueJobsAsync(
            DescribeIssueJobsRequest request,
            AsyncAction<AsyncResult<DescribeIssueJobsResult>> callback
    ) {
        DescribeIssueJobsTask task = new DescribeIssueJobsTask(request, callback);
        session.execute(task);
    }

    public DescribeIssueJobsResult describeIssueJobs(
            DescribeIssueJobsRequest request
    ) {
        final AsyncResult<DescribeIssueJobsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIssueJobsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetIssueJobTask extends Gs2RestSessionTask<GetIssueJobResult> {
        private GetIssueJobRequest request;

        public GetIssueJobTask(
            GetIssueJobRequest request,
            AsyncAction<AsyncResult<GetIssueJobResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetIssueJobResult parse(JsonNode data) {
            return GetIssueJobResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign/{campaignModelName}/issue/{issueJobName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));
            url = url.replace("{issueJobName}", this.request.getIssueJobName() == null || this.request.getIssueJobName().length() == 0 ? "null" : String.valueOf(this.request.getIssueJobName()));

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

    public void getIssueJobAsync(
            GetIssueJobRequest request,
            AsyncAction<AsyncResult<GetIssueJobResult>> callback
    ) {
        GetIssueJobTask task = new GetIssueJobTask(request, callback);
        session.execute(task);
    }

    public GetIssueJobResult getIssueJob(
            GetIssueJobRequest request
    ) {
        final AsyncResult<GetIssueJobResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIssueJobAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IssueTask extends Gs2RestSessionTask<IssueResult> {
        private IssueRequest request;

        public IssueTask(
            IssueRequest request,
            AsyncAction<AsyncResult<IssueResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IssueResult parse(JsonNode data) {
            return IssueResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign/{campaignModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("metadata", request.getMetadata());
                    put("issueRequestCount", request.getIssueRequestCount());
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

    public void issueAsync(
            IssueRequest request,
            AsyncAction<AsyncResult<IssueResult>> callback
    ) {
        IssueTask task = new IssueTask(request, callback);
        session.execute(task);
    }

    public IssueResult issue(
            IssueRequest request
    ) {
        final AsyncResult<IssueResult>[] resultAsyncResult = new AsyncResult[]{null};
        issueAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSerialKeysTask extends Gs2RestSessionTask<DescribeSerialKeysResult> {
        private DescribeSerialKeysRequest request;

        public DescribeSerialKeysTask(
            DescribeSerialKeysRequest request,
            AsyncAction<AsyncResult<DescribeSerialKeysResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSerialKeysResult parse(JsonNode data) {
            return DescribeSerialKeysResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign/{campaignModelName}/issue/{issueJobName}/serialKey";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));
            url = url.replace("{issueJobName}", this.request.getIssueJobName() == null || this.request.getIssueJobName().length() == 0 ? "null" : String.valueOf(this.request.getIssueJobName()));

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

    public void describeSerialKeysAsync(
            DescribeSerialKeysRequest request,
            AsyncAction<AsyncResult<DescribeSerialKeysResult>> callback
    ) {
        DescribeSerialKeysTask task = new DescribeSerialKeysTask(request, callback);
        session.execute(task);
    }

    public DescribeSerialKeysResult describeSerialKeys(
            DescribeSerialKeysRequest request
    ) {
        final AsyncResult<DescribeSerialKeysResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSerialKeysAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DownloadSerialCodesTask extends Gs2RestSessionTask<DownloadSerialCodesResult> {
        private DownloadSerialCodesRequest request;

        public DownloadSerialCodesTask(
            DownloadSerialCodesRequest request,
            AsyncAction<AsyncResult<DownloadSerialCodesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DownloadSerialCodesResult parse(JsonNode data) {
            return DownloadSerialCodesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign/{campaignModelName}/issue/{issueJobName}/serialCode/download";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));
            url = url.replace("{issueJobName}", this.request.getIssueJobName() == null || this.request.getIssueJobName().length() == 0 ? "null" : String.valueOf(this.request.getIssueJobName()));

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

    public void downloadSerialCodesAsync(
            DownloadSerialCodesRequest request,
            AsyncAction<AsyncResult<DownloadSerialCodesResult>> callback
    ) {
        DownloadSerialCodesTask task = new DownloadSerialCodesTask(request, callback);
        session.execute(task);
    }

    public DownloadSerialCodesResult downloadSerialCodes(
            DownloadSerialCodesRequest request
    ) {
        final AsyncResult<DownloadSerialCodesResult>[] resultAsyncResult = new AsyncResult[]{null};
        downloadSerialCodesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSerialKeyTask extends Gs2RestSessionTask<GetSerialKeyResult> {
        private GetSerialKeyRequest request;

        public GetSerialKeyTask(
            GetSerialKeyRequest request,
            AsyncAction<AsyncResult<GetSerialKeyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSerialKeyResult parse(JsonNode data) {
            return GetSerialKeyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/serialKey/{code}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{code}", this.request.getCode() == null || this.request.getCode().length() == 0 ? "null" : String.valueOf(this.request.getCode()));

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

    public void getSerialKeyAsync(
            GetSerialKeyRequest request,
            AsyncAction<AsyncResult<GetSerialKeyResult>> callback
    ) {
        GetSerialKeyTask task = new GetSerialKeyTask(request, callback);
        session.execute(task);
    }

    public GetSerialKeyResult getSerialKey(
            GetSerialKeyRequest request
    ) {
        final AsyncResult<GetSerialKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSerialKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UseTask extends Gs2RestSessionTask<UseResult> {
        private UseRequest request;

        public UseTask(
            UseRequest request,
            AsyncAction<AsyncResult<UseResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UseResult parse(JsonNode data) {
            return UseResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/serialKey";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("code", request.getCode());
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

    public void useAsync(
            UseRequest request,
            AsyncAction<AsyncResult<UseResult>> callback
    ) {
        UseTask task = new UseTask(request, callback);
        session.execute(task);
    }

    public UseResult use(
            UseRequest request
    ) {
        final AsyncResult<UseResult>[] resultAsyncResult = new AsyncResult[]{null};
        useAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UseByUserIdTask extends Gs2RestSessionTask<UseByUserIdResult> {
        private UseByUserIdRequest request;

        public UseByUserIdTask(
            UseByUserIdRequest request,
            AsyncAction<AsyncResult<UseByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UseByUserIdResult parse(JsonNode data) {
            return UseByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/serialKey";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("code", request.getCode());
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

            builder
                .build()
                .send();
        }
    }

    public void useByUserIdAsync(
            UseByUserIdRequest request,
            AsyncAction<AsyncResult<UseByUserIdResult>> callback
    ) {
        UseByUserIdTask task = new UseByUserIdTask(request, callback);
        session.execute(task);
    }

    public UseByUserIdResult useByUserId(
            UseByUserIdRequest request
    ) {
        final AsyncResult<UseByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        useByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UseByStampTaskTask extends Gs2RestSessionTask<UseByStampTaskResult> {
        private UseByStampTaskRequest request;

        public UseByStampTaskTask(
            UseByStampTaskRequest request,
            AsyncAction<AsyncResult<UseByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UseByStampTaskResult parse(JsonNode data) {
            return UseByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/serialKey/use";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
                    put("keyId", request.getKeyId());
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

    public void useByStampTaskAsync(
            UseByStampTaskRequest request,
            AsyncAction<AsyncResult<UseByStampTaskResult>> callback
    ) {
        UseByStampTaskTask task = new UseByStampTaskTask(request, callback);
        session.execute(task);
    }

    public UseByStampTaskResult useByStampTask(
            UseByStampTaskRequest request
    ) {
        final AsyncResult<UseByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        useByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCampaignModelsTask extends Gs2RestSessionTask<DescribeCampaignModelsResult> {
        private DescribeCampaignModelsRequest request;

        public DescribeCampaignModelsTask(
            DescribeCampaignModelsRequest request,
            AsyncAction<AsyncResult<DescribeCampaignModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCampaignModelsResult parse(JsonNode data) {
            return DescribeCampaignModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign";

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

    public void describeCampaignModelsAsync(
            DescribeCampaignModelsRequest request,
            AsyncAction<AsyncResult<DescribeCampaignModelsResult>> callback
    ) {
        DescribeCampaignModelsTask task = new DescribeCampaignModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeCampaignModelsResult describeCampaignModels(
            DescribeCampaignModelsRequest request
    ) {
        final AsyncResult<DescribeCampaignModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCampaignModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCampaignModelTask extends Gs2RestSessionTask<GetCampaignModelResult> {
        private GetCampaignModelRequest request;

        public GetCampaignModelTask(
            GetCampaignModelRequest request,
            AsyncAction<AsyncResult<GetCampaignModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCampaignModelResult parse(JsonNode data) {
            return GetCampaignModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/campaign/{campaignModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));

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

    public void getCampaignModelAsync(
            GetCampaignModelRequest request,
            AsyncAction<AsyncResult<GetCampaignModelResult>> callback
    ) {
        GetCampaignModelTask task = new GetCampaignModelTask(request, callback);
        session.execute(task);
    }

    public GetCampaignModelResult getCampaignModel(
            GetCampaignModelRequest request
    ) {
        final AsyncResult<GetCampaignModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCampaignModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCampaignModelMastersTask extends Gs2RestSessionTask<DescribeCampaignModelMastersResult> {
        private DescribeCampaignModelMastersRequest request;

        public DescribeCampaignModelMastersTask(
            DescribeCampaignModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCampaignModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCampaignModelMastersResult parse(JsonNode data) {
            return DescribeCampaignModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/campaign";

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

    public void describeCampaignModelMastersAsync(
            DescribeCampaignModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCampaignModelMastersResult>> callback
    ) {
        DescribeCampaignModelMastersTask task = new DescribeCampaignModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeCampaignModelMastersResult describeCampaignModelMasters(
            DescribeCampaignModelMastersRequest request
    ) {
        final AsyncResult<DescribeCampaignModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCampaignModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateCampaignModelMasterTask extends Gs2RestSessionTask<CreateCampaignModelMasterResult> {
        private CreateCampaignModelMasterRequest request;

        public CreateCampaignModelMasterTask(
            CreateCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCampaignModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateCampaignModelMasterResult parse(JsonNode data) {
            return CreateCampaignModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/campaign";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("enableCampaignCode", request.getEnableCampaignCode());
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

    public void createCampaignModelMasterAsync(
            CreateCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCampaignModelMasterResult>> callback
    ) {
        CreateCampaignModelMasterTask task = new CreateCampaignModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateCampaignModelMasterResult createCampaignModelMaster(
            CreateCampaignModelMasterRequest request
    ) {
        final AsyncResult<CreateCampaignModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createCampaignModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCampaignModelMasterTask extends Gs2RestSessionTask<GetCampaignModelMasterResult> {
        private GetCampaignModelMasterRequest request;

        public GetCampaignModelMasterTask(
            GetCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<GetCampaignModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCampaignModelMasterResult parse(JsonNode data) {
            return GetCampaignModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/campaign/{campaignModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));

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

    public void getCampaignModelMasterAsync(
            GetCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<GetCampaignModelMasterResult>> callback
    ) {
        GetCampaignModelMasterTask task = new GetCampaignModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetCampaignModelMasterResult getCampaignModelMaster(
            GetCampaignModelMasterRequest request
    ) {
        final AsyncResult<GetCampaignModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCampaignModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCampaignModelMasterTask extends Gs2RestSessionTask<UpdateCampaignModelMasterResult> {
        private UpdateCampaignModelMasterRequest request;

        public UpdateCampaignModelMasterTask(
            UpdateCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCampaignModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCampaignModelMasterResult parse(JsonNode data) {
            return UpdateCampaignModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/campaign/{campaignModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("enableCampaignCode", request.getEnableCampaignCode());
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

    public void updateCampaignModelMasterAsync(
            UpdateCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCampaignModelMasterResult>> callback
    ) {
        UpdateCampaignModelMasterTask task = new UpdateCampaignModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCampaignModelMasterResult updateCampaignModelMaster(
            UpdateCampaignModelMasterRequest request
    ) {
        final AsyncResult<UpdateCampaignModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCampaignModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCampaignModelMasterTask extends Gs2RestSessionTask<DeleteCampaignModelMasterResult> {
        private DeleteCampaignModelMasterRequest request;

        public DeleteCampaignModelMasterTask(
            DeleteCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCampaignModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteCampaignModelMasterResult parse(JsonNode data) {
            return DeleteCampaignModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/campaign/{campaignModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{campaignModelName}", this.request.getCampaignModelName() == null || this.request.getCampaignModelName().length() == 0 ? "null" : String.valueOf(this.request.getCampaignModelName()));

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

    public void deleteCampaignModelMasterAsync(
            DeleteCampaignModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCampaignModelMasterResult>> callback
    ) {
        DeleteCampaignModelMasterTask task = new DeleteCampaignModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteCampaignModelMasterResult deleteCampaignModelMaster(
            DeleteCampaignModelMasterRequest request
    ) {
        final AsyncResult<DeleteCampaignModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCampaignModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
            AsyncAction<AsyncResult<ExportMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ExportMasterResult parse(JsonNode data) {
            return ExportMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/export";

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

    public void exportMasterAsync(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> callback
    ) {
        ExportMasterTask task = new ExportMasterTask(request, callback);
        session.execute(task);
    }

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

    class GetCurrentCampaignMasterTask extends Gs2RestSessionTask<GetCurrentCampaignMasterResult> {
        private GetCurrentCampaignMasterRequest request;

        public GetCurrentCampaignMasterTask(
            GetCurrentCampaignMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentCampaignMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentCampaignMasterResult parse(JsonNode data) {
            return GetCurrentCampaignMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

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

    public void getCurrentCampaignMasterAsync(
            GetCurrentCampaignMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentCampaignMasterResult>> callback
    ) {
        GetCurrentCampaignMasterTask task = new GetCurrentCampaignMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentCampaignMasterResult getCurrentCampaignMaster(
            GetCurrentCampaignMasterRequest request
    ) {
        final AsyncResult<GetCurrentCampaignMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentCampaignMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentCampaignMasterTask extends Gs2RestSessionTask<UpdateCurrentCampaignMasterResult> {
        private UpdateCurrentCampaignMasterRequest request;

        public UpdateCurrentCampaignMasterTask(
            UpdateCurrentCampaignMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentCampaignMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentCampaignMasterResult parse(JsonNode data) {
            return UpdateCurrentCampaignMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("settings", request.getSettings());
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

    public void updateCurrentCampaignMasterAsync(
            UpdateCurrentCampaignMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentCampaignMasterResult>> callback
    ) {
        UpdateCurrentCampaignMasterTask task = new UpdateCurrentCampaignMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentCampaignMasterResult updateCurrentCampaignMaster(
            UpdateCurrentCampaignMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentCampaignMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentCampaignMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentCampaignMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentCampaignMasterFromGitHubResult> {
        private UpdateCurrentCampaignMasterFromGitHubRequest request;

        public UpdateCurrentCampaignMasterFromGitHubTask(
            UpdateCurrentCampaignMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentCampaignMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentCampaignMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentCampaignMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "serial-key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/from_git_hub";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("checkoutSetting", request.getCheckoutSetting() != null ? request.getCheckoutSetting().toJson() : null);
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

    public void updateCurrentCampaignMasterFromGitHubAsync(
            UpdateCurrentCampaignMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentCampaignMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentCampaignMasterFromGitHubTask task = new UpdateCurrentCampaignMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentCampaignMasterFromGitHubResult updateCurrentCampaignMasterFromGitHub(
            UpdateCurrentCampaignMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentCampaignMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentCampaignMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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