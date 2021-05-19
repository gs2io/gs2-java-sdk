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

package io.gs2.news.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.news.model.*;

/**
 * 現在有効なお知らせを更新準備する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareUpdateCurrentNewsMasterResult implements IResult, Serializable {
	/** アップロード後に結果を反映する際に使用するトークン */
	private String uploadToken;
	/** テンプレートアップロード処理の実行に使用するURL */
	private String templateUploadUrl;

	/**
	 * アップロード後に結果を反映する際に使用するトークンを取得
	 *
	 * @return 現在有効なお知らせを更新準備する
	 */
	public String getUploadToken() {
		return uploadToken;
	}

	/**
	 * アップロード後に結果を反映する際に使用するトークンを設定
	 *
	 * @param uploadToken 現在有効なお知らせを更新準備する
	 */
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}

	/**
	 * テンプレートアップロード処理の実行に使用するURLを取得
	 *
	 * @return 現在有効なお知らせを更新準備する
	 */
	public String getTemplateUploadUrl() {
		return templateUploadUrl;
	}

	/**
	 * テンプレートアップロード処理の実行に使用するURLを設定
	 *
	 * @param templateUploadUrl 現在有効なお知らせを更新準備する
	 */
	public void setTemplateUploadUrl(String templateUploadUrl) {
		this.templateUploadUrl = templateUploadUrl;
	}
}