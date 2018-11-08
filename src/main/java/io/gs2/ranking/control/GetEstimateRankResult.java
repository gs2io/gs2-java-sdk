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

package io.gs2.ranking.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.ranking.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetEstimateRankResult {

	/** 推定最小順位 */
	private Long min;

	/** 推定最大順位 */
	private Long max;


	/**
	 * 推定最小順位を取得
	 *
	 * @return 推定最小順位
	 */
	public Long getMin() {
		return min;
	}

	/**
	 * 推定最小順位を設定
	 *
	 * @param min 推定最小順位
	 */
	public void setMin(Long min) {
		this.min = min;
	}

	/**
	 * 推定最大順位を取得
	 *
	 * @return 推定最大順位
	 */
	public Long getMax() {
		return max;
	}

	/**
	 * 推定最大順位を設定
	 *
	 * @param max 推定最大順位
	 */
	public void setMax(Long max) {
		this.max = max;
	}

}