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

package io.gs2.gold.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gold.model.*;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetCurrentGoldMasterRequest extends Gs2BasicRequest<GetCurrentGoldMasterRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "GetCurrentGoldMaster";
	}

	/** ゴールドプールの名前 */
	private String goldPoolName;


	/**
	 * ゴールドプールの名前を取得
	 *
	 * @return ゴールドプールの名前
	 */
	public String getGoldPoolName() {
		return goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 */
	public void setGoldPoolName(String goldPoolName) {
		this.goldPoolName = goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 * @return this
	 */
	public GetCurrentGoldMasterRequest withGoldPoolName(String goldPoolName) {
		setGoldPoolName(goldPoolName);
		return this;
	}

}