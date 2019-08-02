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

package io.gs2.gateway.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.gateway.model.*;

/**
 * WebsocketセッションにユーザIDを設定 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetUserIdResult implements IResult, Serializable {
	/** 更新したWebsocketセッション */
	private WebSocketSession item;

	/**
	 * 更新したWebsocketセッションを取得
	 *
	 * @return WebsocketセッションにユーザIDを設定
	 */
	public WebSocketSession getItem() {
		return item;
	}

	/**
	 * 更新したWebsocketセッションを設定
	 *
	 * @param item WebsocketセッションにユーザIDを設定
	 */
	public void setItem(WebSocketSession item) {
		this.item = item;
	}
}