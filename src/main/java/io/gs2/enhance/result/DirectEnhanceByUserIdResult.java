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

package io.gs2.enhance.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.enhance.model.*;

/**
 * ユーザIDを指定して強化を実行 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DirectEnhanceByUserIdResult implements IResult, Serializable {
	/** 強化レートモデル */
	private RateModel item;
	/** 強化処理の実行に使用するスタンプシート */
	private String stampSheet;
	/** スタンプシートの署名計算に使用した暗号鍵GRN */
	private String stampSheetEncryptionKeyId;
	/** 獲得経験値量 */
	private Long acquireExperience;
	/** 経験値ボーナスの倍率(1.0=ボーナスなし) */
	private Float bonusRate;

	/**
	 * 強化レートモデルを取得
	 *
	 * @return ユーザIDを指定して強化を実行
	 */
	public RateModel getItem() {
		return item;
	}

	/**
	 * 強化レートモデルを設定
	 *
	 * @param item ユーザIDを指定して強化を実行
	 */
	public void setItem(RateModel item) {
		this.item = item;
	}

	/**
	 * 強化処理の実行に使用するスタンプシートを取得
	 *
	 * @return ユーザIDを指定して強化を実行
	 */
	public String getStampSheet() {
		return stampSheet;
	}

	/**
	 * 強化処理の実行に使用するスタンプシートを設定
	 *
	 * @param stampSheet ユーザIDを指定して強化を実行
	 */
	public void setStampSheet(String stampSheet) {
		this.stampSheet = stampSheet;
	}

	/**
	 * スタンプシートの署名計算に使用した暗号鍵GRNを取得
	 *
	 * @return ユーザIDを指定して強化を実行
	 */
	public String getStampSheetEncryptionKeyId() {
		return stampSheetEncryptionKeyId;
	}

	/**
	 * スタンプシートの署名計算に使用した暗号鍵GRNを設定
	 *
	 * @param stampSheetEncryptionKeyId ユーザIDを指定して強化を実行
	 */
	public void setStampSheetEncryptionKeyId(String stampSheetEncryptionKeyId) {
		this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
	}

	/**
	 * 獲得経験値量を取得
	 *
	 * @return ユーザIDを指定して強化を実行
	 */
	public Long getAcquireExperience() {
		return acquireExperience;
	}

	/**
	 * 獲得経験値量を設定
	 *
	 * @param acquireExperience ユーザIDを指定して強化を実行
	 */
	public void setAcquireExperience(Long acquireExperience) {
		this.acquireExperience = acquireExperience;
	}

	/**
	 * 経験値ボーナスの倍率(1.0=ボーナスなし)を取得
	 *
	 * @return ユーザIDを指定して強化を実行
	 */
	public Float getBonusRate() {
		return bonusRate;
	}

	/**
	 * 経験値ボーナスの倍率(1.0=ボーナスなし)を設定
	 *
	 * @param bonusRate ユーザIDを指定して強化を実行
	 */
	public void setBonusRate(Float bonusRate) {
		this.bonusRate = bonusRate;
	}
}