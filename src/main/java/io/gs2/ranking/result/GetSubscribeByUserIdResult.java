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

package io.gs2.ranking.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.ranking.model.*;

/**
 * ユーザIDを指定して購読を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetSubscribeByUserIdResult implements IResult, Serializable {
	/** 購読対象 */
	private SubscribeUser item;

	/**
	 * 購読対象を取得
	 *
	 * @return ユーザIDを指定して購読を取得
	 */
	public SubscribeUser getItem() {
		return item;
	}

	/**
	 * 購読対象を設定
	 *
	 * @param item ユーザIDを指定して購読を取得
	 */
	public void setItem(SubscribeUser item) {
		this.item = item;
	}
}