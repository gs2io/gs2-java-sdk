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

package io.gs2.formation.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.formation.model.*;

/**
 * スタンプシートでアイテムをインベントリに追加 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireActionToFormPropertiesByStampSheetResult implements IResult, Serializable {
	/** フォーム */
	private Form item;
	/** 保存したフォーム */
	private Mold mold;
	/** スタンプシート */
	private String stampSheet;
	/** スタンプシートの署名計算に使用した暗号鍵GRN */
	private String stampSheetEncryptionKeyId;

	/**
	 * フォームを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public Form getItem() {
		return item;
	}

	/**
	 * フォームを設定
	 *
	 * @param item スタンプシートでアイテムをインベントリに追加
	 */
	public void setItem(Form item) {
		this.item = item;
	}

	/**
	 * 保存したフォームを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public Mold getMold() {
		return mold;
	}

	/**
	 * 保存したフォームを設定
	 *
	 * @param mold スタンプシートでアイテムをインベントリに追加
	 */
	public void setMold(Mold mold) {
		this.mold = mold;
	}

	/**
	 * スタンプシートを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public String getStampSheet() {
		return stampSheet;
	}

	/**
	 * スタンプシートを設定
	 *
	 * @param stampSheet スタンプシートでアイテムをインベントリに追加
	 */
	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	/**
	 * スタンプシートの署名計算に使用した暗号鍵GRNを取得
	 *
	 * @return スタンプシートでアイテムをインベントリに追加
	 */
	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	/**
	 * スタンプシートの署名計算に使用した暗号鍵GRNを設定
	 *
	 * @param stampSheetEncryptionKeyId スタンプシートでアイテムをインベントリに追加
	 */
	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}
}