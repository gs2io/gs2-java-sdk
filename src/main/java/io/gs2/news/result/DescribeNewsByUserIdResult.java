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
 * ユーザIDを指定してお知らせ記事の一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeNewsByUserIdResult implements IResult, Serializable {
	/** お知らせ記事のリスト */
	private List<News> items;
	/** お知らせ記事データのハッシュ値 */
	private String contentHash;
	/** テンプレートデータのハッシュ値 */
	private String templateHash;

	/**
	 * お知らせ記事のリストを取得
	 *
	 * @return ユーザIDを指定してお知らせ記事の一覧を取得
	 */
	public List<News> getItems() {
		return items;
	}

	/**
	 * お知らせ記事のリストを設定
	 *
	 * @param items ユーザIDを指定してお知らせ記事の一覧を取得
	 */
	public void setItems(List<News> items) {
		this.items = items;
	}

	/**
	 * お知らせ記事データのハッシュ値を取得
	 *
	 * @return ユーザIDを指定してお知らせ記事の一覧を取得
	 */
	public String getContentHash() {
		return contentHash;
	}

	/**
	 * お知らせ記事データのハッシュ値を設定
	 *
	 * @param contentHash ユーザIDを指定してお知らせ記事の一覧を取得
	 */
	public void setContentHash(String contentHash) {
		this.contentHash = contentHash;
	}

	/**
	 * テンプレートデータのハッシュ値を取得
	 *
	 * @return ユーザIDを指定してお知らせ記事の一覧を取得
	 */
	public String getTemplateHash() {
		return templateHash;
	}

	/**
	 * テンプレートデータのハッシュ値を設定
	 *
	 * @param templateHash ユーザIDを指定してお知らせ記事の一覧を取得
	 */
	public void setTemplateHash(String templateHash) {
		this.templateHash = templateHash;
	}
}