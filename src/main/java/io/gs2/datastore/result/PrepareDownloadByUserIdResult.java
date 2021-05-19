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
 * ユーザIDを指定してデータオブジェクトをダウンロード準備する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareDownloadByUserIdResult implements IResult, Serializable {
	/** データオブジェクト */
	private DataObject item;
	/** ファイルをダウンロードするためのURL */
	private String fileUrl;
	/** ファイルの容量 */
	private Long contentLength;

	/**
	 * データオブジェクトを取得
	 *
	 * @return ユーザIDを指定してデータオブジェクトをダウンロード準備する
	 */
	public DataObject getItem() {
		return item;
	}

	/**
	 * データオブジェクトを設定
	 *
	 * @param item ユーザIDを指定してデータオブジェクトをダウンロード準備する
	 */
	public void setItem(DataObject item) {
		this.item = item;
	}

	/**
	 * ファイルをダウンロードするためのURLを取得
	 *
	 * @return ユーザIDを指定してデータオブジェクトをダウンロード準備する
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * ファイルをダウンロードするためのURLを設定
	 *
	 * @param fileUrl ユーザIDを指定してデータオブジェクトをダウンロード準備する
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * ファイルの容量を取得
	 *
	 * @return ユーザIDを指定してデータオブジェクトをダウンロード準備する
	 */
	public Long getContentLength() {
		return contentLength;
	}

	/**
	 * ファイルの容量を設定
	 *
	 * @param contentLength ユーザIDを指定してデータオブジェクトをダウンロード準備する
	 */
	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}
}