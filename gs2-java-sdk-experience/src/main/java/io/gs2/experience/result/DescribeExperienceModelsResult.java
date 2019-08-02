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

package io.gs2.experience.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.experience.model.*;

/**
 * 経験値・ランクアップ閾値モデルの一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeExperienceModelsResult implements IResult, Serializable {
	/** 経験値・ランクアップ閾値モデルのリスト */
	private List<ExperienceModel> items;

	/**
	 * 経験値・ランクアップ閾値モデルのリストを取得
	 *
	 * @return 経験値・ランクアップ閾値モデルの一覧を取得
	 */
	public List<ExperienceModel> getItems() {
		return items;
	}

	/**
	 * 経験値・ランクアップ閾値モデルのリストを設定
	 *
	 * @param items 経験値・ランクアップ閾値モデルの一覧を取得
	 */
	public void setItems(List<ExperienceModel> items) {
		this.items = items;
	}
}