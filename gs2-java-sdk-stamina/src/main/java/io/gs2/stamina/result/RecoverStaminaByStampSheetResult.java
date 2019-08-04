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

package io.gs2.stamina.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.stamina.model.*;

/**
 * スタンプシートを使用してスタミナを回復 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RecoverStaminaByStampSheetResult implements IResult, Serializable {
	/** スタミナ */
	private Stamina item;
	/** スタミナ値の上限を超えて受け取れずに GS2-Inbox に転送したスタミナ値 */
	private Long overflowValue;

	/**
	 * スタミナを取得
	 *
	 * @return スタンプシートを使用してスタミナを回復
	 */
	public Stamina getItem() {
		return item;
	}

	/**
	 * スタミナを設定
	 *
	 * @param item スタンプシートを使用してスタミナを回復
	 */
	public void setItem(Stamina item) {
		this.item = item;
	}

	/**
	 * スタミナ値の上限を超えて受け取れずに GS2-Inbox に転送したスタミナ値を取得
	 *
	 * @return スタンプシートを使用してスタミナを回復
	 */
	public Long getOverflowValue() {
		return overflowValue;
	}

	/**
	 * スタミナ値の上限を超えて受け取れずに GS2-Inbox に転送したスタミナ値を設定
	 *
	 * @param overflowValue スタンプシートを使用してスタミナを回復
	 */
	public void setOverflowValue(Long overflowValue) {
		this.overflowValue = overflowValue;
	}
}