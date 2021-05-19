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

package io.gs2.datastore.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.datastore.model.*;

/**
 * ユーザIDを指定してデータオブジェクトをアップロード準備する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareUploadByUserIdResult implements IResult, Serializable {
	/** データオブジェクト */
	private DataObject item;
	/** アップロード処理の実行に使用するURL */
	private String uploadUrl;

	/**
	 * データオブジェクトを取得
	 *
	 * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
	 */
	public DataObject getItem() {
		return item;
	}

	/**
	 * データオブジェクトを設定
	 *
	 * @param item ユーザIDを指定してデータオブジェクトをアップロード準備する
	 */
	public void setItem(DataObject item) {
		this.item = item;
	}

	/**
	 * アップロード処理の実行に使用するURLを取得
	 *
	 * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
	 */
	public String getUploadUrl() {
		return uploadUrl;
	}

	/**
	 * アップロード処理の実行に使用するURLを設定
	 *
	 * @param uploadUrl ユーザIDを指定してデータオブジェクトをアップロード準備する
	 */
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}
}