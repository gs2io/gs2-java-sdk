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
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetGoldRequest extends Gs2BasicRequest<GetGoldRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "GetGold";
	}

	/** ゴールドの名前を指定します。 */
	private String goldName;


	/**
	 * ゴールドの名前を指定します。を取得
	 *
	 * @return ゴールドの名前を指定します。
	 */
	public String getGoldName() {
		return goldName;
	}

	/**
	 * ゴールドの名前を指定します。を設定
	 *
	 * @param goldName ゴールドの名前を指定します。
	 */
	public void setGoldName(String goldName) {
		this.goldName = goldName;
	}

	/**
	 * ゴールドの名前を指定します。を設定
	 *
	 * @param goldName ゴールドの名前を指定します。
	 * @return this
	 */
	public GetGoldRequest withGoldName(String goldName) {
		setGoldName(goldName);
		return this;
	}

}