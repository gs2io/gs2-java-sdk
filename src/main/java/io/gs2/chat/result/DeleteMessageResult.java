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

package io.gs2.chat.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.chat.model.*;

/**
 * メッセージを削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteMessageResult implements IResult, Serializable {
	/** 削除したメッセージ */
	private Message item;

	/**
	 * 削除したメッセージを取得
	 *
	 * @return メッセージを削除
	 */
	public Message getItem() {
		return item;
	}

	/**
	 * 削除したメッセージを設定
	 *
	 * @param item メッセージを削除
	 */
	public void setItem(Message item) {
		this.item = item;
	}
}