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

package io.gs2.mission.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.mission.model.*;

/**
 * カウンターの種類マスターを更新 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateCounterModelMasterResult implements IResult, Serializable {
	/** 更新したカウンターの種類マスター */
	private CounterModelMaster item;

	/**
	 * 更新したカウンターの種類マスターを取得
	 *
	 * @return カウンターの種類マスターを更新
	 */
	public CounterModelMaster getItem() {
		return item;
	}

	/**
	 * 更新したカウンターの種類マスターを設定
	 *
	 * @param item カウンターの種類マスターを更新
	 */
	public void setItem(CounterModelMaster item) {
		this.item = item;
	}
}