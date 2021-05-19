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
import io.gs2.watch.request.*;
import io.gs2.watch.result.*;
import io.gs2.watch.model.*;

/**
 * GS2 Watch API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2WatchRestClient extends AbstractGs2Client<Gs2WatchRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2WatchRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class GetChartTask extends Gs2RestSessionTask<GetChartResult> {
        private GetChartRequest request;

        public GetChartTask(
            GetChartRequest request,
            AsyncAction<AsyncResult<GetChartResult>> userCallback,
            Class<GetChartResult> clazz
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
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/chart/{metrics}";

            url = url.replace("{metrics}", this.request.getMetrics() == null|| this.request.getMetrics().length() == 0 ? "null" : String.valueOf(this.request.getMetrics()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getGrn() != null) {
                json.put("grn", this.request.getGrn());
            }
            if (this.request.getQueries() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getQueries())
                {
                    array.put(item);
                }
                json.put("queries", array);
            }
            if (this.request.getBy() != null) {
                json.put("by", this.request.getBy());
            }
            if (this.request.getTimeframe() != null) {
                json.put("timeframe", this.request.getTimeframe());
            }
            if (this.request.getSize() != null) {
                json.put("size", this.request.getSize());
            }
            if (this.request.getFormat() != null) {
                json.put("format", this.request.getFormat());
            }
            if (this.request.getAggregator() != null) {
                json.put("aggregator", this.request.getAggregator());
            }
            if (this.request.getStyle() != null) {
                json.put("style", this.request.getStyle());
            }
            if (this.request.getTitle() != null) {
                json.put("title", this.request.getTitle());
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
     * チャートを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getChartAsync(
            GetChartRequest request,
            AsyncAction<AsyncResult<GetChartResult>> callback
    ) {
        GetChartTask task = new GetChartTask(request, callback, GetChartResult.class);
        session.execute(task);
    }

    /**
     * チャートを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetCumulativeResult>> userCallback,
            Class<GetCumulativeResult> clazz
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
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/cumulative/{name}";

            url = url.replace("{name}", this.request.getName() == null|| this.request.getName().length() == 0 ? "null" : String.valueOf(this.request.getName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getResourceGrn() != null) {
                json.put("resourceGrn", this.request.getResourceGrn());
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
     * 累積値を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCumulativeAsync(
            GetCumulativeRequest request,
            AsyncAction<AsyncResult<GetCumulativeResult>> callback
    ) {
        GetCumulativeTask task = new GetCumulativeTask(request, callback, GetCumulativeResult.class);
        session.execute(task);
    }

    /**
     * 累積値を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeBillingActivitiesResult>> userCallback,
            Class<DescribeBillingActivitiesResult> clazz
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
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/billingActivity/{year}/{month}";

            url = url.replace("{year}", this.request.getYear() == null ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null ? "null" : String.valueOf(this.request.getMonth()));

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

    /**
     * 請求にまつわるアクティビティの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeBillingActivitiesAsync(
            DescribeBillingActivitiesRequest request,
            AsyncAction<AsyncResult<DescribeBillingActivitiesResult>> callback
    ) {
        DescribeBillingActivitiesTask task = new DescribeBillingActivitiesTask(request, callback, DescribeBillingActivitiesResult.class);
        session.execute(task);
    }

    /**
     * 請求にまつわるアクティビティの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetBillingActivityResult>> userCallback,
            Class<GetBillingActivityResult> clazz
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
                .replace("{service}", "watch")
                .replace("{region}", session.getRegion().getName())
                + "/billingActivity/{year}/{month}/{service}/{activityType}";

            url = url.replace("{year}", this.request.getYear() == null ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null ? "null" : String.valueOf(this.request.getMonth()));
            url = url.replace("{service}", this.request.getService() == null|| this.request.getService().length() == 0 ? "null" : String.valueOf(this.request.getService()));
            url = url.replace("{activityType}", this.request.getActivityType() == null|| this.request.getActivityType().length() == 0 ? "null" : String.valueOf(this.request.getActivityType()));

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

            builder
                .build()
                .send();
        }
    }

    /**
     * 請求にまつわるアクティビティを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getBillingActivityAsync(
            GetBillingActivityRequest request,
            AsyncAction<AsyncResult<GetBillingActivityResult>> callback
    ) {
        GetBillingActivityTask task = new GetBillingActivityTask(request, callback, GetBillingActivityResult.class);
        session.execute(task);
    }

    /**
     * 請求にまつわるアクティビティを取得<br>
     *
     * @param request リクエストパラメータ
     */
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