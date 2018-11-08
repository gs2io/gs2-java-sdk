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

package io.gs2.stamina.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.stamina.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ConsumeStaminaResult {

	/** スタミナ */
	private Stamina item;

	/** 次にスタミナが回復する時間 */
	private Integer nextIncreaseTimestamp;


	/**
	 * スタミナを取得
	 *
	 * @return スタミナ
	 */
	public Stamina getItem() {
		return item;
	}

	/**
	 * スタミナを設定
	 *
	 * @param item スタミナ
	 */
	public void setItem(Stamina item) {
		this.item = item;
	}

	/**
	 * 次にスタミナが回復する時間を取得
	 *
	 * @return 次にスタミナが回復する時間
	 */
	public Integer getNextIncreaseTimestamp() {
		return nextIncreaseTimestamp;
	}

	/**
	 * 次にスタミナが回復する時間を設定
	 *
	 * @param nextIncreaseTimestamp 次にスタミナが回復する時間
	 */
	public void setNextIncreaseTimestamp(Integer nextIncreaseTimestamp) {
		this.nextIncreaseTimestamp = nextIncreaseTimestamp;
	}

}