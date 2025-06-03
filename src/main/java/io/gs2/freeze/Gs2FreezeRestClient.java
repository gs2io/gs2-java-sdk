
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

package io.gs2.freeze;

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
import io.gs2.freeze.request.*;
import io.gs2.freeze.result.*;
import io.gs2.freeze.model.*;

public class Gs2FreezeRestClient extends AbstractGs2Client<Gs2FreezeRestClient> {

	public Gs2FreezeRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeStagesTask extends Gs2RestSessionTask<DescribeStagesResult> {
        private DescribeStagesRequest request;

        public DescribeStagesTask(
            DescribeStagesRequest request,
            AsyncAction<AsyncResult<DescribeStagesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeStagesResult parse(JsonNode data) {
            return DescribeStagesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "freeze")
                .replace("{region}", session.getRegion().getName())
                + "/";

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

    public void describeStagesAsync(
            DescribeStagesRequest request,
            AsyncAction<AsyncResult<DescribeStagesResult>> callback
    ) {
        DescribeStagesTask task = new DescribeStagesTask(request, callback);
        session.execute(task);
    }

    public DescribeStagesResult describeStages(
            DescribeStagesRequest request
    ) {
        final AsyncResult<DescribeStagesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStagesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStageTask extends Gs2RestSessionTask<GetStageResult> {
        private GetStageRequest request;

        public GetStageTask(
            GetStageRequest request,
            AsyncAction<AsyncResult<GetStageResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetStageResult parse(JsonNode data) {
            return GetStageResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "freeze")
                .replace("{region}", session.getRegion().getName())
                + "/{stageName}";

            url = url.replace("{stageName}", this.request.getStageName() == null || this.request.getStageName().length() == 0 ? "null" : String.valueOf(this.request.getStageName()));

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

    public void getStageAsync(
            GetStageRequest request,
            AsyncAction<AsyncResult<GetStageResult>> callback
    ) {
        GetStageTask task = new GetStageTask(request, callback);
        session.execute(task);
    }

    public GetStageResult getStage(
            GetStageRequest request
    ) {
        final AsyncResult<GetStageResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PromoteStageTask extends Gs2RestSessionTask<PromoteStageResult> {
        private PromoteStageRequest request;

        public PromoteStageTask(
            PromoteStageRequest request,
            AsyncAction<AsyncResult<PromoteStageResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PromoteStageResult parse(JsonNode data) {
            return PromoteStageResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "freeze")
                .replace("{region}", session.getRegion().getName())
                + "/{stageName}/promote";

            url = url.replace("{stageName}", this.request.getStageName() == null || this.request.getStageName().length() == 0 ? "null" : String.valueOf(this.request.getStageName()));

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

    public void promoteStageAsync(
            PromoteStageRequest request,
            AsyncAction<AsyncResult<PromoteStageResult>> callback
    ) {
        PromoteStageTask task = new PromoteStageTask(request, callback);
        session.execute(task);
    }

    public PromoteStageResult promoteStage(
            PromoteStageRequest request
    ) {
        final AsyncResult<PromoteStageResult>[] resultAsyncResult = new AsyncResult[]{null};
        promoteStageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RollbackStageTask extends Gs2RestSessionTask<RollbackStageResult> {
        private RollbackStageRequest request;

        public RollbackStageTask(
            RollbackStageRequest request,
            AsyncAction<AsyncResult<RollbackStageResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RollbackStageResult parse(JsonNode data) {
            return RollbackStageResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "freeze")
                .replace("{region}", session.getRegion().getName())
                + "/{stageName}/rollback";

            url = url.replace("{stageName}", this.request.getStageName() == null || this.request.getStageName().length() == 0 ? "null" : String.valueOf(this.request.getStageName()));

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

    public void rollbackStageAsync(
            RollbackStageRequest request,
            AsyncAction<AsyncResult<RollbackStageResult>> callback
    ) {
        RollbackStageTask task = new RollbackStageTask(request, callback);
        session.execute(task);
    }

    public RollbackStageResult rollbackStage(
            RollbackStageRequest request
    ) {
        final AsyncResult<RollbackStageResult>[] resultAsyncResult = new AsyncResult[]{null};
        rollbackStageAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeOutputsTask extends Gs2RestSessionTask<DescribeOutputsResult> {
        private DescribeOutputsRequest request;

        public DescribeOutputsTask(
            DescribeOutputsRequest request,
            AsyncAction<AsyncResult<DescribeOutputsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeOutputsResult parse(JsonNode data) {
            return DescribeOutputsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "freeze")
                .replace("{region}", session.getRegion().getName())
                + "/{stageName}/progress/output";

            url = url.replace("{stageName}", this.request.getStageName() == null || this.request.getStageName().length() == 0 ? "null" : String.valueOf(this.request.getStageName()));

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

    public void describeOutputsAsync(
            DescribeOutputsRequest request,
            AsyncAction<AsyncResult<DescribeOutputsResult>> callback
    ) {
        DescribeOutputsTask task = new DescribeOutputsTask(request, callback);
        session.execute(task);
    }

    public DescribeOutputsResult describeOutputs(
            DescribeOutputsRequest request
    ) {
        final AsyncResult<DescribeOutputsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeOutputsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetOutputTask extends Gs2RestSessionTask<GetOutputResult> {
        private GetOutputRequest request;

        public GetOutputTask(
            GetOutputRequest request,
            AsyncAction<AsyncResult<GetOutputResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetOutputResult parse(JsonNode data) {
            return GetOutputResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "freeze")
                .replace("{region}", session.getRegion().getName())
                + "/{stageName}/progress/output/{outputName}";

            url = url.replace("{stageName}", this.request.getStageName() == null || this.request.getStageName().length() == 0 ? "null" : String.valueOf(this.request.getStageName()));
            url = url.replace("{outputName}", this.request.getOutputName() == null || this.request.getOutputName().length() == 0 ? "null" : String.valueOf(this.request.getOutputName()));

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

    public void getOutputAsync(
            GetOutputRequest request,
            AsyncAction<AsyncResult<GetOutputResult>> callback
    ) {
        GetOutputTask task = new GetOutputTask(request, callback);
        session.execute(task);
    }

    public GetOutputResult getOutput(
            GetOutputRequest request
    ) {
        final AsyncResult<GetOutputResult>[] resultAsyncResult = new AsyncResult[]{null};
        getOutputAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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