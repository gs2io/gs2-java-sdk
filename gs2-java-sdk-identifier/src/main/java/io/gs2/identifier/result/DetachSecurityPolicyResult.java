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

package io.gs2.identifier.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.identifier.model.*;

/**
 * 割り当てられたセキュリティポリシーをユーザーから外します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DetachSecurityPolicyResult implements IResult, Serializable {
	/** 剥奪したあとユーザーに引き続き割り当てられているセキュリティポリシーのリスト */
	private List<SecurityPolicy> items;

	/**
	 * 剥奪したあとユーザーに引き続き割り当てられているセキュリティポリシーのリストを取得
	 *
	 * @return 割り当てられたセキュリティポリシーをユーザーから外します
	 */
	public List<SecurityPolicy> getItems() {
		return items;
	}

	/**
	 * 剥奪したあとユーザーに引き続き割り当てられているセキュリティポリシーのリストを設定
	 *
	 * @param items 割り当てられたセキュリティポリシーをユーザーから外します
	 */
	public void setItems(List<SecurityPolicy> items) {
		this.items = items;
	}
}