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
import io.gs2.gold.model.*;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateGoldPoolRequest extends Gs2BasicRequest<UpdateGoldPoolRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "UpdateGoldPool";
	}

	/** ゴールドプールの名前 */
	private String goldPoolName;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

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
	 * ゴールドプールの名前を取得
	 *
	 * @return ゴールドプールの名前
	 */
	public String getGoldPoolName() {
		return goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 */
	public void setGoldPoolName(String goldPoolName) {
		this.goldPoolName = goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 * @return this
	 */
	public UpdateGoldPoolRequest withGoldPoolName(String goldPoolName) {
		setGoldPoolName(goldPoolName);
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
	public UpdateGoldPoolRequest withDescription(String description) {
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
	public UpdateGoldPoolRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
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
	public UpdateGoldPoolRequest withCreateWalletTriggerScript(String createWalletTriggerScript) {
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
	public UpdateGoldPoolRequest withCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
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
	public UpdateGoldPoolRequest withDepositIntoWalletTriggerScript(String depositIntoWalletTriggerScript) {
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
	public UpdateGoldPoolRequest withDepositIntoWalletDoneTriggerScript(String depositIntoWalletDoneTriggerScript) {
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
	public UpdateGoldPoolRequest withWithdrawFromWalletTriggerScript(String withdrawFromWalletTriggerScript) {
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
	public UpdateGoldPoolRequest withWithdrawFromWalletDoneTriggerScript(String withdrawFromWalletDoneTriggerScript) {
		setWithdrawFromWalletDoneTriggerScript(withdrawFromWalletDoneTriggerScript);
		return this;
	}

}