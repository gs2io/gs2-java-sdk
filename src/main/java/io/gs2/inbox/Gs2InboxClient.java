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

package io.gs2.inbox;

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
import io.gs2.inbox.control.*;

/**
 * GS2 Inbox API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2InboxClient extends AbstractGs2Client<Gs2InboxClient> {

	public static String ENDPOINT = "inbox";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2InboxClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2InboxClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2InboxClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * 受信ボックスを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateInboxResult createInbox(CreateInboxRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getAutoDelete() != null) body.put("autoDelete", request.getAutoDelete());
        if(request.getCooperationUrl() != null) body.put("cooperationUrl", request.getCooperationUrl());
        if(request.getReceiveMessageTriggerScript() != null) body.put("receiveMessageTriggerScript", request.getReceiveMessageTriggerScript());
        if(request.getReceiveMessageDoneTriggerScript() != null) body.put("receiveMessageDoneTriggerScript", request.getReceiveMessageDoneTriggerScript());
        if(request.getReadMessageTriggerScript() != null) body.put("readMessageTriggerScript", request.getReadMessageTriggerScript());
        if(request.getReadMessageDoneTriggerScript() != null) body.put("readMessageDoneTriggerScript", request.getReadMessageDoneTriggerScript());
        if(request.getDeleteMessageTriggerScript() != null) body.put("deleteMessageTriggerScript", request.getDeleteMessageTriggerScript());
        if(request.getDeleteMessageDoneTriggerScript() != null) body.put("deleteMessageDoneTriggerScript", request.getDeleteMessageDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/inbox",
				credential,
				ENDPOINT,
				CreateInboxRequest.Constant.MODULE,
				CreateInboxRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateInboxResult.class);

	}


	/**
	 * 受信ボックスを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteInbox(DeleteInboxRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteInboxRequest.Constant.MODULE,
				DeleteInboxRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 受信ボックスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeInboxResult describeInbox(DescribeInboxRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox";

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
				DescribeInboxRequest.Constant.MODULE,
				DescribeInboxRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeInboxResult.class);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/serviceClass";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeServiceClassRequest.Constant.MODULE,
				DescribeServiceClassRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeServiceClassResult.class);

	}


	/**
	 * 受信ボックスを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetInboxResult getInbox(GetInboxRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetInboxRequest.Constant.MODULE,
				GetInboxRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetInboxResult.class);

	}


	/**
	 * 受信ボックスの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetInboxStatusResult getInboxStatus(GetInboxStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/status";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetInboxStatusRequest.Constant.MODULE,
				GetInboxStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetInboxStatusResult.class);

	}


	/**
	 * 受信ボックスを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateInboxResult updateInbox(UpdateInboxRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getServiceClass() != null) body.put("serviceClass", request.getServiceClass());
        if(request.getCooperationUrl() != null) body.put("cooperationUrl", request.getCooperationUrl());
        if(request.getReceiveMessageTriggerScript() != null) body.put("receiveMessageTriggerScript", request.getReceiveMessageTriggerScript());
        if(request.getReceiveMessageDoneTriggerScript() != null) body.put("receiveMessageDoneTriggerScript", request.getReceiveMessageDoneTriggerScript());
        if(request.getReadMessageTriggerScript() != null) body.put("readMessageTriggerScript", request.getReadMessageTriggerScript());
        if(request.getReadMessageDoneTriggerScript() != null) body.put("readMessageDoneTriggerScript", request.getReadMessageDoneTriggerScript());
        if(request.getDeleteMessageTriggerScript() != null) body.put("deleteMessageTriggerScript", request.getDeleteMessageTriggerScript());
        if(request.getDeleteMessageDoneTriggerScript() != null) body.put("deleteMessageDoneTriggerScript", request.getDeleteMessageDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "",
				credential,
				ENDPOINT,
				UpdateInboxRequest.Constant.MODULE,
				UpdateInboxRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateInboxResult.class);

	}


	/**
	 * メッセージを削除します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteMessage(DeleteMessageRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message/" + (request.getMessageId() == null || request.getMessageId().equals("") ? "null" : request.getMessageId()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteMessageRequest.Constant.MODULE,
				DeleteMessageRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 複数のメッセージをまとめて削除します<br>
	 * <br>
	 * - 消費クオータ: 削除するメッセージの数 * 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteMessages(DeleteMessagesRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message/multiple";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getMessageIds() != null) queryString.add(new BasicNameValuePair("messageIds", toString(request.getMessageIds())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteMessagesRequest.Constant.MODULE,
				DeleteMessagesRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 受信メッセージの一覧を取得します<br>
	 * <br>
	 * - 消費クオータ: 50件あたり5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeMessageResult describeMessage(DescribeMessageRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message";

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
				DescribeMessageRequest.Constant.MODULE,
				DescribeMessageRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeMessageResult.class);

	}


	/**
	 * メッセージを取得します<br>
	 * <br>
	 * - 消費クオータ: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMessageResult getMessage(GetMessageRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message/" + (request.getMessageId() == null || request.getMessageId().equals("") ? "null" : request.getMessageId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMessageRequest.Constant.MODULE,
				GetMessageRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetMessageResult.class);

	}


	/**
	 * メッセージを開封します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ReadMessageResult readMessage(ReadMessageRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message/" + (request.getMessageId() == null || request.getMessageId().equals("") ? "null" : request.getMessageId()) + "",
				credential,
				ENDPOINT,
				ReadMessageRequest.Constant.MODULE,
				ReadMessageRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ReadMessageResult.class);

	}


	/**
	 * 複数のメッセージをまとめて開封します<br>
	 * <br>
	 * 連携用URLと複数メッセージの開封処理を同時に利用する場合は、200レスポンスを応答すると、GS2側では指定されたすべてのメッセージを開封したことにします。<br>
	 * <br>
	 * 200 以外のステータスコードを応答する場合はレスポンスボディにJSON形式で、<br>
	 * "success" というパラメータに開封に成功したメッセージIDのリストを返す必要があります。<br>
	 * success に指定されたメッセージIDのみ開封成功処理を行い、BadGateway(502)応答を返します。<br>
	 * <br>
	 * BadGateway(502) のレスポンスボディには、コールバックで返された値がそのまま含まれます。<br>
	 * 例えば、メッセージにアイテムを添付されていたが、一部アイテムが所有できる上限を超えていたため開封できなかった。という場合<br>
	 * success にはアイテムを付与できたメッセージIDのみを応答し、reason など任意のパラメータでアイテムの所持上限を迎えたため<br>
	 * メッセージID hoge のメッセージは開封に失敗した。というようなレスポンスを返すことでクライアントにも開封に失敗した理由を伝えることができます。<br>
	 * <br>
	 * - 消費クオータ: 開封するメッセージの数 * 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ReadMessagesResult readMessages(ReadMessagesRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getMessageIds() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getMessageIds()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("messageIds", JsonNodeFactory.instance.arrayNode().addAll(node));
		}

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message/multiple",
				credential,
				ENDPOINT,
				ReadMessagesRequest.Constant.MODULE,
				ReadMessagesRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ReadMessagesResult.class);

	}


	/**
	 * メッセージを送信します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SendMessageResult sendMessage(SendMessageRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("userId", request.getUserId())
				.put("message", request.getMessage());
        if(request.getCooperation() != null) body.put("cooperation", request.getCooperation());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/inbox/" + (request.getInboxName() == null || request.getInboxName().equals("") ? "null" : request.getInboxName()) + "/message",
				credential,
				ENDPOINT,
				SendMessageRequest.Constant.MODULE,
				SendMessageRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, SendMessageResult.class);

	}


}