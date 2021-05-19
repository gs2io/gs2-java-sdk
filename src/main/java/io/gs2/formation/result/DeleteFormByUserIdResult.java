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
 * ユーザIDを指定してフォームを削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteFormByUserIdResult implements IResult, Serializable {
	/** フォーム */
	private Form item;
	/** 保存したフォーム */
	private Mold mold;
	/** フォームの保存領域 */
	private MoldModel moldModel;
	/** フォームモデル */
	private FormModel formModel;

	/**
	 * フォームを取得
	 *
	 * @return ユーザIDを指定してフォームを削除
	 */
	public Form getItem() {
		return item;
	}

	/**
	 * フォームを設定
	 *
	 * @param item ユーザIDを指定してフォームを削除
	 */
	public void setItem(Form item) {
		this.item = item;
	}

	/**
	 * 保存したフォームを取得
	 *
	 * @return ユーザIDを指定してフォームを削除
	 */
	public Mold getMold() {
		return mold;
	}

	/**
	 * 保存したフォームを設定
	 *
	 * @param mold ユーザIDを指定してフォームを削除
	 */
	public void setMold(Mold mold) {
		this.mold = mold;
	}

	/**
	 * フォームの保存領域を取得
	 *
	 * @return ユーザIDを指定してフォームを削除
	 */
	public MoldModel getMoldModel() {
		return moldModel;
	}

	/**
	 * フォームの保存領域を設定
	 *
	 * @param moldModel ユーザIDを指定してフォームを削除
	 */
	public void setMoldModel(MoldModel moldModel) {
		this.moldModel = moldModel;
	}

	/**
	 * フォームモデルを取得
	 *
	 * @return ユーザIDを指定してフォームを削除
	 */
	public FormModel getFormModel() {
		return formModel;
	}

	/**
	 * フォームモデルを設定
	 *
	 * @param formModel ユーザIDを指定してフォームを削除
	 */
	public void setFormModel(FormModel formModel) {
		this.formModel = formModel;
	}
}