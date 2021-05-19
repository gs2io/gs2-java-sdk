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

package io.gs2.dictionary.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.dictionary.model.*;

/**
 * ユーザIDを指定してエントリーを入手済みとして登録 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AddEntriesByUserIdResult implements IResult, Serializable {
	/** 登録した{model_name}のリスト */
	private List<Entry> items;

	/**
	 * 登録した{model_name}のリストを取得
	 *
	 * @return ユーザIDを指定してエントリーを入手済みとして登録
	 */
	public List<Entry> getItems() {
		return items;
	}

	/**
	 * 登録した{model_name}のリストを設定
	 *
	 * @param items ユーザIDを指定してエントリーを入手済みとして登録
	 */
	public void setItems(List<Entry> items) {
		this.items = items;
	}
}