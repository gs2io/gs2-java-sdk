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

package io.gs2.exchange.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.exchange.model.*;

/**
 * 交換待機を作成 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateAwaitByUserIdResult implements IResult, Serializable {
	/** 交換待機 */
	private Await item;
	/** 取得できるようになる日時 */
	private Long unlockAt;

	/**
	 * 交換待機を取得
	 *
	 * @return 交換待機を作成
	 */
	public Await getItem() {
		return item;
	}

	/**
	 * 交換待機を設定
	 *
	 * @param item 交換待機を作成
	 */
	public void setItem(Await item) {
		this.item = item;
	}

	/**
	 * 取得できるようになる日時を取得
	 *
	 * @return 交換待機を作成
	 */
	public Long getUnlockAt() {
		return unlockAt;
	}

	/**
	 * 取得できるようになる日時を設定
	 *
	 * @param unlockAt 交換待機を作成
	 */
	public void setUnlockAt(Long unlockAt) {
		this.unlockAt = unlockAt;
	}
}