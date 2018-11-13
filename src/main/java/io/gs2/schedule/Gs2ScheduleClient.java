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

package io.gs2.schedule;

import java.util.ArrayList;
import java.util.List;

import io.gs2.model.Region;
import io.gs2.util.EncodingUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.model.IGs2Credential;
import io.gs2.schedule.control.*;

/**
 * GS2 Schedule API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ScheduleClient extends AbstractGs2Client<Gs2ScheduleClient> {

	public static String ENDPOINT = "schedule";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2ScheduleClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ScheduleClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ScheduleClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * 現在適用されているイベントマスターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentEventMasterResult getCurrentEventMaster(GetCurrentEventMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/event/master";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentEventMasterRequest.Constant.MODULE,
				GetCurrentEventMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentEventMasterResult.class);

	}


	/**
	 * イベントマスターを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentEventMasterResult updateCurrentEventMaster(UpdateCurrentEventMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/event/master",
				credential,
				ENDPOINT,
				UpdateCurrentEventMasterRequest.Constant.MODULE,
				UpdateCurrentEventMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentEventMasterResult.class);

	}


	/**
	 * イベントマスターを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateEventMasterResult createEventMaster(CreateEventMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("type", request.getType());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getAbsoluteBegin() != null) body.put("absoluteBegin", request.getAbsoluteBegin());
        if(request.getAbsoluteEnd() != null) body.put("absoluteEnd", request.getAbsoluteEnd());
        if(request.getRelativeTriggerName() != null) body.put("relativeTriggerName", request.getRelativeTriggerName());
        if(request.getRelativeSpan() != null) body.put("relativeSpan", request.getRelativeSpan());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/master/event",
				credential,
				ENDPOINT,
				CreateEventMasterRequest.Constant.MODULE,
				CreateEventMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateEventMasterResult.class);

	}


	/**
	 * イベントマスターを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteEventMaster(DeleteEventMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/master/event/" + (request.getEventName() == null || request.getEventName().equals("") ? "null" : request.getEventName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteEventMasterRequest.Constant.MODULE,
				DeleteEventMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * イベントマスターの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeEventMasterResult describeEventMaster(DescribeEventMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/master/event";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeEventMasterRequest.Constant.MODULE,
				DescribeEventMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeEventMasterResult.class);

	}


	/**
	 * イベントマスターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetEventMasterResult getEventMaster(GetEventMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/master/event/" + (request.getEventName() == null || request.getEventName().equals("") ? "null" : request.getEventName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetEventMasterRequest.Constant.MODULE,
				GetEventMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetEventMasterResult.class);

	}


	/**
	 * イベントマスターを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateEventMasterResult updateEventMaster(UpdateEventMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("type", request.getType());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getAbsoluteBegin() != null) body.put("absoluteBegin", request.getAbsoluteBegin());
        if(request.getAbsoluteEnd() != null) body.put("absoluteEnd", request.getAbsoluteEnd());
        if(request.getRelativeTriggerName() != null) body.put("relativeTriggerName", request.getRelativeTriggerName());
        if(request.getRelativeSpan() != null) body.put("relativeSpan", request.getRelativeSpan());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/master/event/" + (request.getEventName() == null || request.getEventName().equals("") ? "null" : request.getEventName()) + "",
				credential,
				ENDPOINT,
				UpdateEventMasterRequest.Constant.MODULE,
				UpdateEventMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateEventMasterResult.class);

	}


	/**
	 * 開催中のイベントの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeEventResult describeEvent(DescribeEventRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/event";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getEventNames() != null) queryString.add(new BasicNameValuePair("eventNames", toString(request.getEventNames())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeEventRequest.Constant.MODULE,
				DescribeEventRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeEventResult.class);

	}


	/**
	 * 開催中のイベントの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeEventByUserIdResult describeEventByUserId(DescribeEventByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/event/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getEventNames() != null) queryString.add(new BasicNameValuePair("eventNames", toString(request.getEventNames())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeEventByUserIdRequest.Constant.MODULE,
				DescribeEventByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeEventByUserIdResult.class);

	}


	/**
	 * 開催中のイベントを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetEventResult getEvent(GetEventRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/event/" + (request.getEventName() == null || request.getEventName().equals("") ? "null" : request.getEventName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetEventRequest.Constant.MODULE,
				GetEventRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetEventResult.class);

	}


	/**
	 * 開催中のイベントを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetEventByUserIdResult getEventByUserId(GetEventByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/event/" + (request.getEventName() == null || request.getEventName().equals("") ? "null" : request.getEventName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetEventByUserIdRequest.Constant.MODULE,
				GetEventByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetEventByUserIdResult.class);

	}


	/**
	 * イベントマスターをエクスポートします<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/master";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				ExportMasterRequest.Constant.MODULE,
				ExportMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, ExportMasterResult.class);

	}


	/**
	 * スケジュールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateScheduleResult createSchedule(CreateScheduleRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/schedule",
				credential,
				ENDPOINT,
				CreateScheduleRequest.Constant.MODULE,
				CreateScheduleRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateScheduleResult.class);

	}


	/**
	 * スケジュールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteSchedule(DeleteScheduleRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteScheduleRequest.Constant.MODULE,
				DeleteScheduleRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * スケジュールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeScheduleResult describeSchedule(DescribeScheduleRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeScheduleRequest.Constant.MODULE,
				DescribeScheduleRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeScheduleResult.class);

	}


	/**
	 * スケジュールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetScheduleResult getSchedule(GetScheduleRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetScheduleRequest.Constant.MODULE,
				GetScheduleRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetScheduleResult.class);

	}


	/**
	 * スケジュールの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetScheduleStatusResult getScheduleStatus(GetScheduleStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/status";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetScheduleStatusRequest.Constant.MODULE,
				GetScheduleStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetScheduleStatusResult.class);

	}


	/**
	 * スケジュールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateScheduleResult updateSchedule(UpdateScheduleRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "",
				credential,
				ENDPOINT,
				UpdateScheduleRequest.Constant.MODULE,
				UpdateScheduleRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateScheduleResult.class);

	}


	/**
	 * トリガーを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteTrigger(DeleteTriggerRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/trigger/" + (request.getTriggerName() == null || request.getTriggerName().equals("") ? "null" : request.getTriggerName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteTriggerRequest.Constant.MODULE,
				DeleteTriggerRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * トリガーの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeTriggerResult describeTrigger(DescribeTriggerRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/trigger";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeTriggerRequest.Constant.MODULE,
				DescribeTriggerRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeTriggerResult.class);

	}


	/**
	 * ユーザのトリガーの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeTriggerByUserIdResult describeTriggerByUserId(DescribeTriggerByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/trigger";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeTriggerByUserIdRequest.Constant.MODULE,
				DescribeTriggerByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeTriggerByUserIdResult.class);

	}


	/**
	 * トリガーを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetTriggerResult getTrigger(GetTriggerRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/trigger/" + (request.getTriggerName() == null || request.getTriggerName().equals("") ? "null" : request.getTriggerName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetTriggerRequest.Constant.MODULE,
				GetTriggerRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetTriggerResult.class);

	}


	/**
	 * トリガーを引きます<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public PullTriggerResult pullTrigger(PullTriggerRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("action", request.getAction());
        if(request.getTtl() != null) body.put("ttl", request.getTtl());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/schedule/" + (request.getScheduleName() == null || request.getScheduleName().equals("") ? "null" : request.getScheduleName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/trigger/" + (request.getTriggerName() == null || request.getTriggerName().equals("") ? "null" : request.getTriggerName()) + "",
				credential,
				ENDPOINT,
				PullTriggerRequest.Constant.MODULE,
				PullTriggerRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, PullTriggerResult.class);

	}


}