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

package io.gs2.gacha.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.gacha.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetProbabilityResult {

	/** レアリティ毎の排出率 */
	private List<Probability> items;


	/**
	 * レアリティ毎の排出率を取得
	 *
	 * @return レアリティ毎の排出率
	 */
	public List<Probability> getItems() {
		return items;
	}

	/**
	 * レアリティ毎の排出率を設定
	 *
	 * @param items レアリティ毎の排出率
	 */
	public void setItems(List<Probability> items) {
		this.items = items;
	}

}