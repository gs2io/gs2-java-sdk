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
 * お知らせ記事に加算 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WantGrantByUserIdResult implements IResult, Serializable {
	/** お知らせコンテンツにアクセスするために設定の必要なクッキー のリスト */
	private List<SetCookieRequestEntry> items;
	/** お知らせコンテンツにアクセスするためのURL */
	private String browserUrl;
	/** ZIP形式のお知らせコンテンツにアクセスするためのURL Cookieの設定は不要 */
	private String zipUrl;

	/**
	 * お知らせコンテンツにアクセスするために設定の必要なクッキー のリストを取得
	 *
	 * @return お知らせ記事に加算
	 */
	public List<SetCookieRequestEntry> getItems() {
		return items;
	}

	/**
	 * お知らせコンテンツにアクセスするために設定の必要なクッキー のリストを設定
	 *
	 * @param items お知らせ記事に加算
	 */
	public void setItems(List<SetCookieRequestEntry> items) {
		this.items = items;
	}

	/**
	 * お知らせコンテンツにアクセスするためのURLを取得
	 *
	 * @return お知らせ記事に加算
	 */
	public String getBrowserUrl() {
		return browserUrl;
	}

	/**
	 * お知らせコンテンツにアクセスするためのURLを設定
	 *
	 * @param browserUrl お知らせ記事に加算
	 */
	public void setBrowserUrl(String browserUrl) {
		this.browserUrl = browserUrl;
	}

	/**
	 * ZIP形式のお知らせコンテンツにアクセスするためのURL Cookieの設定は不要を取得
	 *
	 * @return お知らせ記事に加算
	 */
	public String getZipUrl() {
		return zipUrl;
	}

	/**
	 * ZIP形式のお知らせコンテンツにアクセスするためのURL Cookieの設定は不要を設定
	 *
	 * @param zipUrl お知らせ記事に加算
	 */
	public void setZipUrl(String zipUrl) {
		this.zipUrl = zipUrl;
	}
}