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

package io.gs2.gold.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGoldRequest extends Gs2BasicRequest<CreateGoldRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "CreateGold";
	}

	/** ゴールド名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

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

	/** 通知先 URL */
	private String notificationUrl;

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
	public CreateGoldRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public CreateGoldRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 * @return this
	 */
	public CreateGoldRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
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
	public CreateGoldRequest withBalanceMax(Long balanceMax) {
		setBalanceMax(balanceMax);
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
	public CreateGoldRequest withResetType(String resetType) {
		setResetType(resetType);
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
	public CreateGoldRequest withResetDayOfMonth(Integer resetDayOfMonth) {
		setResetDayOfMonth(resetDayOfMonth);
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
	public CreateGoldRequest withResetDayOfWeek(String resetDayOfWeek) {
		setResetDayOfWeek(resetDayOfWeek);
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
	public CreateGoldRequest withResetHour(Integer resetHour) {
		setResetHour(resetHour);
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
	public CreateGoldRequest withLatestGainMax(Long latestGainMax) {
		setLatestGainMax(latestGainMax);
		return this;
	}

	/**
	 * 通知先 URLを取得
	 *
	 * @return 通知先 URL
	 */
	public String getNotificationUrl() {
		return notificationUrl;
	}

	/**
	 * 通知先 URLを設定
	 *
	 * @param notificationUrl 通知先 URL
	 */
	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	/**
	 * 通知先 URLを設定
	 *
	 * @param notificationUrl 通知先 URL
	 * @return this
	 */
	public CreateGoldRequest withNotificationUrl(String notificationUrl) {
		setNotificationUrl(notificationUrl);
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
	public CreateGoldRequest withCreateWalletTriggerScript(String createWalletTriggerScript) {
		setCreateWalletTriggerScript(createWalletTriggerScript);
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
	public CreateGoldRequest withCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		setCreateWalletDoneTriggerScript(createWalletDoneTriggerScript);
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
	public CreateGoldRequest withDepositIntoWalletTriggerScript(String depositIntoWalletTriggerScript) {
		setDepositIntoWalletTriggerScript(depositIntoWalletTriggerScript);
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
	public CreateGoldRequest withDepositIntoWalletDoneTriggerScript(String depositIntoWalletDoneTriggerScript) {
		setDepositIntoWalletDoneTriggerScript(depositIntoWalletDoneTriggerScript);
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
	public CreateGoldRequest withWithdrawFromWalletTriggerScript(String withdrawFromWalletTriggerScript) {
		setWithdrawFromWalletTriggerScript(withdrawFromWalletTriggerScript);
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
	public CreateGoldRequest withWithdrawFromWalletDoneTriggerScript(String withdrawFromWalletDoneTriggerScript) {
		setWithdrawFromWalletDoneTriggerScript(withdrawFromWalletDoneTriggerScript);
		return this;
	}

}