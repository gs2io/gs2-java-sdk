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
 * スタンプシートでキャパシティサイズを設定 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetCapacityByStampSheetResult implements IResult, Serializable {
	/** 更新後の保存したフォーム */
	private Mold item;
	/** フォームの保存領域 */
	private MoldModel moldModel;

	/**
	 * 更新後の保存したフォームを取得
	 *
	 * @return スタンプシートでキャパシティサイズを設定
	 */
	public Mold getItem() {
		return item;
	}

	/**
	 * 更新後の保存したフォームを設定
	 *
	 * @param item スタンプシートでキャパシティサイズを設定
	 */
	public void setItem(Mold item) {
		this.item = item;
	}

	/**
	 * フォームの保存領域を取得
	 *
	 * @return スタンプシートでキャパシティサイズを設定
	 */
	public MoldModel getMoldModel() {
		return moldModel;
	}

	/**
	 * フォームの保存領域を設定
	 *
	 * @param moldModel スタンプシートでキャパシティサイズを設定
	 */
	public void setMoldModel(MoldModel moldModel) {
		this.moldModel = moldModel;
	}
}