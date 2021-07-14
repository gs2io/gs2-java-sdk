
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

package io.gs2.watch;

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
import io.gs2.watch.request.*;
import io.gs2.watch.result.*;
import io.gs2.watch.model.*;public class Gs2WatchRestClient extends AbstractGs2Client<Gs2WatchRestClient> {

	public Gs2WatchRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class GetChartTask extends Gs2RestSessionTask<GetChartResult> {
        private GetChartRequest request;

        public GetChartTask(
            GetChartRequest request,
            AsyncAction<AsyncResult<GetChartResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetChartResult parse(JsonNode data) {
            return GetChartResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/chart/{metrics}";

            url = url.replace("{metrics}", this.request.getMetrics() == null || this.request.getMetrics().length() == 0 ? "null" : String.valueOf(this.request.getMetrics()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("grn", request.getGrn());
                    put("queries", request.getQueries() == null ? new ArrayList<String>() :
                        request.getQueries().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("by", request.getBy());
                    put("timeframe", request.getTimeframe());
                    put("size", request.getSize());
                    put("format", request.getFormat());
                    put("aggregator", request.getAggregator());
                    put("style", request.getStyle());
                    put("title", request.getTitle());
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

    public void getChartAsync(
            GetChartRequest request,
            AsyncAction<AsyncResult<GetChartResult>> callback
    ) {
        GetChartTask task = new GetChartTask(request, callback);
        session.execute(task);
    }

    public GetChartResult getChart(
            GetChartRequest request
    ) {
        final AsyncResult<GetChartResult>[] resultAsyncResult = new AsyncResult[]{null};
        getChartAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCumulativeTask extends Gs2RestSessionTask<GetCumulativeResult> {
        private GetCumulativeRequest request;

        public GetCumulativeTask(
            GetCumulativeRequest request,
            AsyncAction<AsyncResult<GetCumulativeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCumulativeResult parse(JsonNode data) {
            return GetCumulativeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/cumulative/{name}";

            url = url.replace("{name}", this.request.getName() == null || this.request.getName().length() == 0 ? "null" : String.valueOf(this.request.getName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("resourceGrn", request.getResourceGrn());
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

    public void getCumulativeAsync(
            GetCumulativeRequest request,
            AsyncAction<AsyncResult<GetCumulativeResult>> callback
    ) {
        GetCumulativeTask task = new GetCumulativeTask(request, callback);
        session.execute(task);
    }

    public GetCumulativeResult getCumulative(
            GetCumulativeRequest request
    ) {
        final AsyncResult<GetCumulativeResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCumulativeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBillingActivitiesTask extends Gs2RestSessionTask<DescribeBillingActivitiesResult> {
        private DescribeBillingActivitiesRequest request;

        public DescribeBillingActivitiesTask(
            DescribeBillingActivitiesRequest request,
            AsyncAction<AsyncResult<DescribeBillingActivitiesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBillingActivitiesResult parse(JsonNode data) {
            return DescribeBillingActivitiesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/billingActivity/{year}/{month}";

            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null  ? "null" : String.valueOf(this.request.getMonth()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + EncodingUtil.urlEncode((String.valueOf(this.request.getService()))));
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

    public void describeBillingActivitiesAsync(
            DescribeBillingActivitiesRequest request,
            AsyncAction<AsyncResult<DescribeBillingActivitiesResult>> callback
    ) {
        DescribeBillingActivitiesTask task = new DescribeBillingActivitiesTask(request, callback);
        session.execute(task);
    }

    public DescribeBillingActivitiesResult describeBillingActivities(
            DescribeBillingActivitiesRequest request
    ) {
        final AsyncResult<DescribeBillingActivitiesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBillingActivitiesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBillingActivityTask extends Gs2RestSessionTask<GetBillingActivityResult> {
        private GetBillingActivityRequest request;

        public GetBillingActivityTask(
            GetBillingActivityRequest request,
            AsyncAction<AsyncResult<GetBillingActivityResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBillingActivityResult parse(JsonNode data) {
            return GetBillingActivityResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/billingActivity/{year}/{month}/{service}/{activityType}";

            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null  ? "null" : String.valueOf(this.request.getMonth()));
            url = url.replace("{service}", this.request.getService() == null || this.request.getService().length() == 0 ? "null" : String.valueOf(this.request.getService()));
            url = url.replace("{activityType}", this.request.getActivityType() == null || this.request.getActivityType().length() == 0 ? "null" : String.valueOf(this.request.getActivityType()));

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

    public void getBillingActivityAsync(
            GetBillingActivityRequest request,
            AsyncAction<AsyncResult<GetBillingActivityResult>> callback
    ) {
        GetBillingActivityTask task = new GetBillingActivityTask(request, callback);
        session.execute(task);
    }

    public GetBillingActivityResult getBillingActivity(
            GetBillingActivityRequest request
    ) {
        final AsyncResult<GetBillingActivityResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBillingActivityAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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