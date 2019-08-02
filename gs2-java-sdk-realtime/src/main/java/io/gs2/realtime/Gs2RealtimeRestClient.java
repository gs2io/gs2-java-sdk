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

package io.gs2.realtime;

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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.model.IGs2Credential;
import io.gs2.realtime.request.*;
import io.gs2.realtime.result.*;
import io.gs2.realtime.model.*;

/**
 * GS2 Realtime API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2RealtimeRestClient extends AbstractGs2Client<Gs2RealtimeRestClient> {

	public static String ENDPOINT = "realtime";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2RealtimeRestClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2RealtimeRestClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2RealtimeRestClient(IGs2Credential credential, String region) {
		super(credential, region);

	}

	/**
	 * ネームスペースの一覧を取得<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public DescribeNamespacesResult describeNamespaces(DescribeNamespacesRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FNamespaceFunctionHandler.describeNamespaces";
        String pageToken = String.valueOf(request.getPageToken());
        String limit = String.valueOf(request.getLimit());
        List<NameValuePair> _queryString = new ArrayList<>();
        if(pageToken != null) {
            _queryString.add(new BasicNameValuePair("pageToken", String.valueOf(pageToken)));
        }
        if(limit != null) {
            _queryString.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpGet http = createHttpGet(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, DescribeNamespacesResult.class);
    }

	/**
	 * ネームスペースを新規作成<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public CreateNamespaceResult createNamespace(CreateNamespaceRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FNamespaceFunctionHandler.createNamespace";
        String name = null;
        if(request.getName() != null) {
            name = request.getName();
        }
        String description = null;
        if(request.getDescription() != null) {
            description = request.getDescription();
        }
        String serverType = null;
        if(request.getServerType() != null) {
            serverType = request.getServerType();
        }
        String serverSpec = null;
        if(request.getServerSpec() != null) {
            serverSpec = request.getServerSpec();
        }
        NotificationSetting createNotification = null;
        if(request.getCreateNotification() != null) {
            createNotification = request.getCreateNotification();
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
        if(name != null) {
            _body.put("name", name);
        }
        if(description != null) {
            _body.put("description", description);
        }
        if(serverType != null) {
            _body.put("serverType", serverType);
        }
        if(serverSpec != null) {
            _body.put("serverSpec", serverSpec);
        }
        if(createNotification != null) {
            _body.put("createNotification", createNotification.toJson());
        }
		HttpPost http = createHttpPost(
				url,
				ENDPOINT,
				_body.toString()
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, CreateNamespaceResult.class);
    }

	/**
	 * ネームスペースの状態を取得<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public GetNamespaceStatusResult getNamespaceStatus(GetNamespaceStatusRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FNamespaceFunctionHandler.getNamespaceStatus";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpGet http = createHttpGet(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, GetNamespaceStatusResult.class);
    }

	/**
	 * ネームスペースを取得<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public GetNamespaceResult getNamespace(GetNamespaceRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FNamespaceFunctionHandler.getNamespace";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpGet http = createHttpGet(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, GetNamespaceResult.class);
    }

	/**
	 * ネームスペースを更新<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public UpdateNamespaceResult updateNamespace(UpdateNamespaceRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FNamespaceFunctionHandler.updateNamespace";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        String description = null;
        if(request.getDescription() != null) {
            description = request.getDescription();
        }
        String serverType = null;
        if(request.getServerType() != null) {
            serverType = request.getServerType();
        }
        String serverSpec = null;
        if(request.getServerSpec() != null) {
            serverSpec = request.getServerSpec();
        }
        NotificationSetting createNotification = null;
        if(request.getCreateNotification() != null) {
            createNotification = request.getCreateNotification();
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
        if(description != null) {
            _body.put("description", description);
        }
        if(serverType != null) {
            _body.put("serverType", serverType);
        }
        if(serverSpec != null) {
            _body.put("serverSpec", serverSpec);
        }
        if(createNotification != null) {
            _body.put("createNotification", createNotification.toJson());
        }
		HttpPut http = createHttpPut(
				url,
				ENDPOINT,
				_body.toString()
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, UpdateNamespaceResult.class);
    }

	/**
	 * ネームスペースを削除<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public DeleteNamespaceResult deleteNamespace(DeleteNamespaceRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FNamespaceFunctionHandler.deleteNamespace";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpDelete http = createHttpDelete(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, DeleteNamespaceResult.class);
    }

	/**
	 * ルームの一覧を取得<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public DescribeRoomsResult describeRooms(DescribeRoomsRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FRoomFunctionHandler.describeRooms";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        String pageToken = String.valueOf(request.getPageToken());
        String limit = String.valueOf(request.getLimit());
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(pageToken != null) {
            _queryString.add(new BasicNameValuePair("pageToken", String.valueOf(pageToken)));
        }
        if(limit != null) {
            _queryString.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpGet http = createHttpGet(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, DescribeRoomsResult.class);
    }

	/**
	 * ルームの作成依頼。<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public WantRoomResult wantRoom(WantRoomRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FRoomFunctionHandler.wantRoom";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        String name = null;
        if(request.getName() != null) {
            name = request.getName();
        }
        ArrayNode notificationUserIds = null;
        if(request.getNotificationUserIds() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getNotificationUserIds()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            notificationUserIds = JsonNodeFactory.instance.arrayNode().addAll(node);
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
        if(name != null) {
            _body.put("name", name);
        }
        if(notificationUserIds != null) {
            _body.put("notificationUserIds", notificationUserIds);
        }
		HttpPost http = createHttpPost(
				url,
				ENDPOINT,
				_body.toString()
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, WantRoomResult.class);
    }

	/**
	 * ルームを取得<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public GetRoomResult getRoom(GetRoomRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FRoomFunctionHandler.getRoom";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        String roomName = null;
        if(request.getRoomName() != null) {
            roomName = request.getRoomName();
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(roomName != null) {
            _queryString.add(new BasicNameValuePair("roomName", String.valueOf(roomName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpGet http = createHttpGet(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, GetRoomResult.class);
    }

	/**
	 * ルームを削除<br>
	 *
	 * @param request リクエストパラメータ
	 * @return 結果
	 */
	public DeleteRoomResult deleteRoom(DeleteRoomRequest request) {
	    String url = Gs2Constant.ENDPOINT_HOST + "/realtime-handler?handler=gs2_realtime%2Fhandler%2FRoomFunctionHandler.deleteRoom";
        String namespaceName = null;
        if(request.getNamespaceName() != null) {
            namespaceName = request.getNamespaceName();
        }
        String roomName = null;
        if(request.getRoomName() != null) {
            roomName = request.getRoomName();
        }
        List<NameValuePair> _queryString = new ArrayList<>();
        if(namespaceName != null) {
            _queryString.add(new BasicNameValuePair("namespaceName", String.valueOf(namespaceName)));
        }
        if(roomName != null) {
            _queryString.add(new BasicNameValuePair("roomName", String.valueOf(roomName)));
        }
        if(_queryString.size() > 0) {
            url += "&" + URLEncodedUtils.format(_queryString, "UTF-8");
        }

		ObjectNode _body = JsonNodeFactory.instance.objectNode();
		HttpDelete http = createHttpDelete(
				url,
				ENDPOINT
        );
        if(request.getRequestId() != null) {
            http.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }
		return doRequest(http, DeleteRoomResult.class);
    }
}