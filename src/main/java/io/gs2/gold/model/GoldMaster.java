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

package io.gs2.gold.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ゴールド
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GoldMaster implements Serializable {

	/** ゴールドGRN */
	private String goldId;

	/** ゴールド名 */
	private String name;

	/** メタデータ */
	private String meta;

	/** 各ウォレットの残高の最大値 */
	private Long balanceMax;

	/** 取得量の期間制限のタイプ */
	private String resetType;

	/** 期間内の取得量をリセットする日にち */
	private Integer resetDayOfMonth;

	/** 期間内の取得量をリセットする曜日 */
	private String resetDayOfWeek;

	/** 期間内の取得量をリセットする時 */
	private Integer resetHour;

	/** 期間内の最大取得量 */
	private Long latestGainMax;

	/** ウォレットの生成時 に実行されるGS2-Script */
	private String createWalletTriggerScript;

	/** ウォレットの生成完了時 に実行されるGS2-Script */
	private String createWalletDoneTriggerScript;

	/** ウォレットへの加算時 に実行されるGS2-Script */
	private String depositIntoWalletTriggerScript;

	/** ウォレットへの加算完了時 に実行されるGS2-Script */
	private String depositIntoWalletDoneTriggerScript;

	/** ウォレットからの減算時 に実行されるGS2-Script */
	private String withdrawFromWalletTriggerScript;

	/** ウォレットからの減算完了時 に実行されるGS2-Script */
	private String withdrawFromWalletDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ゴールドGRNを取得
	 *
	 * @return ゴールドGRN
	 */
	public String getGoldId() {
		return goldId;
	}

	/**
	 * ゴールドGRNを設定
	 *
	 * @param goldId ゴールドGRN
	 */
	public void setGoldId(String goldId) {
		this.goldId = goldId;
	}

	/**
	 * ゴールドGRNを設定
	 *
	 * @param goldId ゴールドGRN
	 * @return this
	 */
	public GoldMaster withGoldId(String goldId) {
		this.goldId = goldId;
		return this;
	}

	/**
	 * ゴールド名を取得
	 *
	 * @return ゴールド名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ゴールド名を設定
	 *
	 * @param name ゴールド名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ゴールド名を設定
	 *
	 * @param name ゴールド名
	 * @return this
	 */
	public GoldMaster withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 * @return this
	 */
	public GoldMaster withMeta(String meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * 各ウォレットの残高の最大値を取得
	 *
	 * @return 各ウォレットの残高の最大値
	 */
	public Long getBalanceMax() {
		return balanceMax;
	}

	/**
	 * 各ウォレットの残高の最大値を設定
	 *
	 * @param balanceMax 各ウォレットの残高の最大値
	 */
	public void setBalanceMax(Long balanceMax) {
		this.balanceMax = balanceMax;
	}

	/**
	 * 各ウォレットの残高の最大値を設定
	 *
	 * @param balanceMax 各ウォレットの残高の最大値
	 * @return this
	 */
	public GoldMaster withBalanceMax(Long balanceMax) {
		this.balanceMax = balanceMax;
		return this;
	}

	/**
	 * 取得量の期間制限のタイプを取得
	 *
	 * @return 取得量の期間制限のタイプ
	 */
	public String getResetType() {
		return resetType;
	}

	/**
	 * 取得量の期間制限のタイプを設定
	 *
	 * @param resetType 取得量の期間制限のタイプ
	 */
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	/**
	 * 取得量の期間制限のタイプを設定
	 *
	 * @param resetType 取得量の期間制限のタイプ
	 * @return this
	 */
	public GoldMaster withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}

	/**
	 * 期間内の取得量をリセットする日にちを取得
	 *
	 * @return 期間内の取得量をリセットする日にち
	 */
	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	/**
	 * 期間内の取得量をリセットする日にちを設定
	 *
	 * @param resetDayOfMonth 期間内の取得量をリセットする日にち
	 */
	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	/**
	 * 期間内の取得量をリセットする日にちを設定
	 *
	 * @param resetDayOfMonth 期間内の取得量をリセットする日にち
	 * @return this
	 */
	public GoldMaster withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}

	/**
	 * 期間内の取得量をリセットする曜日を取得
	 *
	 * @return 期間内の取得量をリセットする曜日
	 */
	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	/**
	 * 期間内の取得量をリセットする曜日を設定
	 *
	 * @param resetDayOfWeek 期間内の取得量をリセットする曜日
	 */
	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	/**
	 * 期間内の取得量をリセットする曜日を設定
	 *
	 * @param resetDayOfWeek 期間内の取得量をリセットする曜日
	 * @return this
	 */
	public GoldMaster withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}

	/**
	 * 期間内の取得量をリセットする時を取得
	 *
	 * @return 期間内の取得量をリセットする時
	 */
	public Integer getResetHour() {
		return resetHour;
	}

	/**
	 * 期間内の取得量をリセットする時を設定
	 *
	 * @param resetHour 期間内の取得量をリセットする時
	 */
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	/**
	 * 期間内の取得量をリセットする時を設定
	 *
	 * @param resetHour 期間内の取得量をリセットする時
	 * @return this
	 */
	public GoldMaster withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}

	/**
	 * 期間内の最大取得量を取得
	 *
	 * @return 期間内の最大取得量
	 */
	public Long getLatestGainMax() {
		return latestGainMax;
	}

	/**
	 * 期間内の最大取得量を設定
	 *
	 * @param latestGainMax 期間内の最大取得量
	 */
	public void setLatestGainMax(Long latestGainMax) {
		this.latestGainMax = latestGainMax;
	}

	/**
	 * 期間内の最大取得量を設定
	 *
	 * @param latestGainMax 期間内の最大取得量
	 * @return this
	 */
	public GoldMaster withLatestGainMax(Long latestGainMax) {
		this.latestGainMax = latestGainMax;
		return this;
	}

	/**
	 * ウォレットの生成時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットの生成時 に実行されるGS2-Script
	 */
	public String getCreateWalletTriggerScript() {
		return createWalletTriggerScript;
	}

	/**
	 * ウォレットの生成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletTriggerScript ウォレットの生成時 に実行されるGS2-Script
	 */
	public void setCreateWalletTriggerScript(String createWalletTriggerScript) {
		this.createWalletTriggerScript = createWalletTriggerScript;
	}

	/**
	 * ウォレットの生成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletTriggerScript ウォレットの生成時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldMaster withCreateWalletTriggerScript(String createWalletTriggerScript) {
		this.createWalletTriggerScript = createWalletTriggerScript;
		return this;
	}

	/**
	 * ウォレットの生成完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットの生成完了時 に実行されるGS2-Script
	 */
	public String getCreateWalletDoneTriggerScript() {
		return createWalletDoneTriggerScript;
	}

	/**
	 * ウォレットの生成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletDoneTriggerScript ウォレットの生成完了時 に実行されるGS2-Script
	 */
	public void setCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		this.createWalletDoneTriggerScript = createWalletDoneTriggerScript;
	}

	/**
	 * ウォレットの生成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletDoneTriggerScript ウォレットの生成完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldMaster withCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		this.createWalletDoneTriggerScript = createWalletDoneTriggerScript;
		return this;
	}

	/**
	 * ウォレットへの加算時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットへの加算時 に実行されるGS2-Script
	 */
	public String getDepositIntoWalletTriggerScript() {
		return depositIntoWalletTriggerScript;
	}

	/**
	 * ウォレットへの加算時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletTriggerScript ウォレットへの加算時 に実行されるGS2-Script
	 */
	public void setDepositIntoWalletTriggerScript(String depositIntoWalletTriggerScript) {
		this.depositIntoWalletTriggerScript = depositIntoWalletTriggerScript;
	}

	/**
	 * ウォレットへの加算時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletTriggerScript ウォレットへの加算時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldMaster withDepositIntoWalletTriggerScript(String depositIntoWalletTriggerScript) {
		this.depositIntoWalletTriggerScript = depositIntoWalletTriggerScript;
		return this;
	}

	/**
	 * ウォレットへの加算完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットへの加算完了時 に実行されるGS2-Script
	 */
	public String getDepositIntoWalletDoneTriggerScript() {
		return depositIntoWalletDoneTriggerScript;
	}

	/**
	 * ウォレットへの加算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletDoneTriggerScript ウォレットへの加算完了時 に実行されるGS2-Script
	 */
	public void setDepositIntoWalletDoneTriggerScript(String depositIntoWalletDoneTriggerScript) {
		this.depositIntoWalletDoneTriggerScript = depositIntoWalletDoneTriggerScript;
	}

	/**
	 * ウォレットへの加算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletDoneTriggerScript ウォレットへの加算完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldMaster withDepositIntoWalletDoneTriggerScript(String depositIntoWalletDoneTriggerScript) {
		this.depositIntoWalletDoneTriggerScript = depositIntoWalletDoneTriggerScript;
		return this;
	}

	/**
	 * ウォレットからの減算時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットからの減算時 に実行されるGS2-Script
	 */
	public String getWithdrawFromWalletTriggerScript() {
		return withdrawFromWalletTriggerScript;
	}

	/**
	 * ウォレットからの減算時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletTriggerScript ウォレットからの減算時 に実行されるGS2-Script
	 */
	public void setWithdrawFromWalletTriggerScript(String withdrawFromWalletTriggerScript) {
		this.withdrawFromWalletTriggerScript = withdrawFromWalletTriggerScript;
	}

	/**
	 * ウォレットからの減算時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletTriggerScript ウォレットからの減算時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldMaster withWithdrawFromWalletTriggerScript(String withdrawFromWalletTriggerScript) {
		this.withdrawFromWalletTriggerScript = withdrawFromWalletTriggerScript;
		return this;
	}

	/**
	 * ウォレットからの減算完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットからの減算完了時 に実行されるGS2-Script
	 */
	public String getWithdrawFromWalletDoneTriggerScript() {
		return withdrawFromWalletDoneTriggerScript;
	}

	/**
	 * ウォレットからの減算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletDoneTriggerScript ウォレットからの減算完了時 に実行されるGS2-Script
	 */
	public void setWithdrawFromWalletDoneTriggerScript(String withdrawFromWalletDoneTriggerScript) {
		this.withdrawFromWalletDoneTriggerScript = withdrawFromWalletDoneTriggerScript;
	}

	/**
	 * ウォレットからの減算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletDoneTriggerScript ウォレットからの減算完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldMaster withWithdrawFromWalletDoneTriggerScript(String withdrawFromWalletDoneTriggerScript) {
		this.withdrawFromWalletDoneTriggerScript = withdrawFromWalletDoneTriggerScript;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public GoldMaster withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public GoldMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("goldId", this.getGoldId())
            .put("name", this.getName())
            .put("meta", this.getMeta())
            .put("balanceMax", this.getBalanceMax())
            .put("resetType", this.getResetType())
            .put("resetDayOfMonth", this.getResetDayOfMonth())
            .put("resetDayOfWeek", this.getResetDayOfWeek())
            .put("resetHour", this.getResetHour())
            .put("latestGainMax", this.getLatestGainMax())
            .put("createWalletTriggerScript", this.getCreateWalletTriggerScript())
            .put("createWalletDoneTriggerScript", this.getCreateWalletDoneTriggerScript())
            .put("depositIntoWalletTriggerScript", this.getDepositIntoWalletTriggerScript())
            .put("depositIntoWalletDoneTriggerScript", this.getDepositIntoWalletDoneTriggerScript())
            .put("withdrawFromWalletTriggerScript", this.getWithdrawFromWalletTriggerScript())
            .put("withdrawFromWalletDoneTriggerScript", this.getWithdrawFromWalletDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}