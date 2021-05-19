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

package io.gs2.matchmaking.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.matchmaking.model.*;

/**
 * 現在有効なレーティングマスターのマスターデータをエクスポートします のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExportMasterResult implements IResult, Serializable {
	/** 現在有効なレーティングマスター */
	private CurrentRatingModelMaster item;

	/**
	 * 現在有効なレーティングマスターを取得
	 *
	 * @return 現在有効なレーティングマスターのマスターデータをエクスポートします
	 */
	public CurrentRatingModelMaster getItem() {
		return item;
	}

	/**
	 * 現在有効なレーティングマスターを設定
	 *
	 * @param item 現在有効なレーティングマスターのマスターデータをエクスポートします
	 */
	public void setItem(CurrentRatingModelMaster item) {
		this.item = item;
	}
}