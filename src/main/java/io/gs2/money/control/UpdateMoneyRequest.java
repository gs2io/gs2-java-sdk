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

package io.gs2.money.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.money.Gs2Money;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateMoneyRequest extends Gs2BasicRequest<UpdateMoneyRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "UpdateMoney";
	}

	/** 取得する課金通貨の名前 */
	private String moneyName;

	/** 説明文(1024文字以内) */
	private String description;

	/** 支払い優先度 */
	private String priority;

	/** ストアプラットフォームのレシートの検証機能を利用するか */
	private Boolean useVerifyReceipt;

	/** Apple のアプリケーションバンドルID */
	private String appleKey;

	/** Google のレシート検証用公開鍵 */
	private String googleKey;

	/** ウォレット新規作成時 に実行されるGS2-Script */
	private String createWalletTriggerScript;

	/** ウォレット新規作成完了時 に実行されるGS2-Script */
	private String createWalletDoneTriggerScript;

	/** ウォレット残高加算時 に実行されるGS2-Script */
	private String chargeWalletTriggerScript;

	/** ウォレット残高加算完了時 に実行されるGS2-Script */
	private String chargeWalletDoneTriggerScript;

	/** ウォレット残高消費時 に実行されるGS2-Script */
	private String consumeWalletTriggerScript;

	/** ウォレット残高消費完了時 に実行されるGS2-Script */
	private String consumeWalletDoneTriggerScript;


	/**
	 * 取得する課金通貨の名前を取得
	 *
	 * @return 取得する課金通貨の名前
	 */
	public String getMoneyName() {
		return moneyName;
	}

	/**
	 * 取得する課金通貨の名前を設定
	 *
	 * @param moneyName 取得する課金通貨の名前
	 */
	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}

	/**
	 * 取得する課金通貨の名前を設定
	 *
	 * @param moneyName 取得する課金通貨の名前
	 * @return this
	 */
	public UpdateMoneyRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

	/**
	 * 説明文(1024文字以内)を取得
	 *
	 * @return 説明文(1024文字以内)
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文(1024文字以内)を設定
	 *
	 * @param description 説明文(1024文字以内)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文(1024文字以内)を設定
	 *
	 * @param description 説明文(1024文字以内)
	 * @return this
	 */
	public UpdateMoneyRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * 支払い優先度を取得
	 *
	 * @return 支払い優先度
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * 支払い優先度を設定
	 *
	 * @param priority 支払い優先度
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * 支払い優先度を設定
	 *
	 * @param priority 支払い優先度
	 * @return this
	 */
	public UpdateMoneyRequest withPriority(String priority) {
		setPriority(priority);
		return this;
	}

	/**
	 * ストアプラットフォームのレシートの検証機能を利用するかを取得
	 *
	 * @return ストアプラットフォームのレシートの検証機能を利用するか
	 */
	public Boolean getUseVerifyReceipt() {
		return useVerifyReceipt;
	}

	/**
	 * ストアプラットフォームのレシートの検証機能を利用するかを設定
	 *
	 * @param useVerifyReceipt ストアプラットフォームのレシートの検証機能を利用するか
	 */
	public void setUseVerifyReceipt(Boolean useVerifyReceipt) {
		this.useVerifyReceipt = useVerifyReceipt;
	}

	/**
	 * ストアプラットフォームのレシートの検証機能を利用するかを設定
	 *
	 * @param useVerifyReceipt ストアプラットフォームのレシートの検証機能を利用するか
	 * @return this
	 */
	public UpdateMoneyRequest withUseVerifyReceipt(Boolean useVerifyReceipt) {
		setUseVerifyReceipt(useVerifyReceipt);
		return this;
	}

	/**
	 * Apple のアプリケーションバンドルIDを取得
	 *
	 * @return Apple のアプリケーションバンドルID
	 */
	public String getAppleKey() {
		return appleKey;
	}

	/**
	 * Apple のアプリケーションバンドルIDを設定
	 *
	 * @param appleKey Apple のアプリケーションバンドルID
	 */
	public void setAppleKey(String appleKey) {
		this.appleKey = appleKey;
	}

	/**
	 * Apple のアプリケーションバンドルIDを設定
	 *
	 * @param appleKey Apple のアプリケーションバンドルID
	 * @return this
	 */
	public UpdateMoneyRequest withAppleKey(String appleKey) {
		setAppleKey(appleKey);
		return this;
	}

	/**
	 * Google のレシート検証用公開鍵を取得
	 *
	 * @return Google のレシート検証用公開鍵
	 */
	public String getGoogleKey() {
		return googleKey;
	}

	/**
	 * Google のレシート検証用公開鍵を設定
	 *
	 * @param googleKey Google のレシート検証用公開鍵
	 */
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	/**
	 * Google のレシート検証用公開鍵を設定
	 *
	 * @param googleKey Google のレシート検証用公開鍵
	 * @return this
	 */
	public UpdateMoneyRequest withGoogleKey(String googleKey) {
		setGoogleKey(googleKey);
		return this;
	}

	/**
	 * ウォレット新規作成時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレット新規作成時 に実行されるGS2-Script
	 */
	public String getCreateWalletTriggerScript() {
		return createWalletTriggerScript;
	}

	/**
	 * ウォレット新規作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletTriggerScript ウォレット新規作成時 に実行されるGS2-Script
	 */
	public void setCreateWalletTriggerScript(String createWalletTriggerScript) {
		this.createWalletTriggerScript = createWalletTriggerScript;
	}

	/**
	 * ウォレット新規作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletTriggerScript ウォレット新規作成時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMoneyRequest withCreateWalletTriggerScript(String createWalletTriggerScript) {
		setCreateWalletTriggerScript(createWalletTriggerScript);
		return this;
	}

	/**
	 * ウォレット新規作成完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレット新規作成完了時 に実行されるGS2-Script
	 */
	public String getCreateWalletDoneTriggerScript() {
		return createWalletDoneTriggerScript;
	}

	/**
	 * ウォレット新規作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletDoneTriggerScript ウォレット新規作成完了時 に実行されるGS2-Script
	 */
	public void setCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		this.createWalletDoneTriggerScript = createWalletDoneTriggerScript;
	}

	/**
	 * ウォレット新規作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletDoneTriggerScript ウォレット新規作成完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMoneyRequest withCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		setCreateWalletDoneTriggerScript(createWalletDoneTriggerScript);
		return this;
	}

	/**
	 * ウォレット残高加算時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレット残高加算時 に実行されるGS2-Script
	 */
	public String getChargeWalletTriggerScript() {
		return chargeWalletTriggerScript;
	}

	/**
	 * ウォレット残高加算時 に実行されるGS2-Scriptを設定
	 *
	 * @param chargeWalletTriggerScript ウォレット残高加算時 に実行されるGS2-Script
	 */
	public void setChargeWalletTriggerScript(String chargeWalletTriggerScript) {
		this.chargeWalletTriggerScript = chargeWalletTriggerScript;
	}

	/**
	 * ウォレット残高加算時 に実行されるGS2-Scriptを設定
	 *
	 * @param chargeWalletTriggerScript ウォレット残高加算時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMoneyRequest withChargeWalletTriggerScript(String chargeWalletTriggerScript) {
		setChargeWalletTriggerScript(chargeWalletTriggerScript);
		return this;
	}

	/**
	 * ウォレット残高加算完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレット残高加算完了時 に実行されるGS2-Script
	 */
	public String getChargeWalletDoneTriggerScript() {
		return chargeWalletDoneTriggerScript;
	}

	/**
	 * ウォレット残高加算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param chargeWalletDoneTriggerScript ウォレット残高加算完了時 に実行されるGS2-Script
	 */
	public void setChargeWalletDoneTriggerScript(String chargeWalletDoneTriggerScript) {
		this.chargeWalletDoneTriggerScript = chargeWalletDoneTriggerScript;
	}

	/**
	 * ウォレット残高加算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param chargeWalletDoneTriggerScript ウォレット残高加算完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMoneyRequest withChargeWalletDoneTriggerScript(String chargeWalletDoneTriggerScript) {
		setChargeWalletDoneTriggerScript(chargeWalletDoneTriggerScript);
		return this;
	}

	/**
	 * ウォレット残高消費時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレット残高消費時 に実行されるGS2-Script
	 */
	public String getConsumeWalletTriggerScript() {
		return consumeWalletTriggerScript;
	}

	/**
	 * ウォレット残高消費時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeWalletTriggerScript ウォレット残高消費時 に実行されるGS2-Script
	 */
	public void setConsumeWalletTriggerScript(String consumeWalletTriggerScript) {
		this.consumeWalletTriggerScript = consumeWalletTriggerScript;
	}

	/**
	 * ウォレット残高消費時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeWalletTriggerScript ウォレット残高消費時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMoneyRequest withConsumeWalletTriggerScript(String consumeWalletTriggerScript) {
		setConsumeWalletTriggerScript(consumeWalletTriggerScript);
		return this;
	}

	/**
	 * ウォレット残高消費完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレット残高消費完了時 に実行されるGS2-Script
	 */
	public String getConsumeWalletDoneTriggerScript() {
		return consumeWalletDoneTriggerScript;
	}

	/**
	 * ウォレット残高消費完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeWalletDoneTriggerScript ウォレット残高消費完了時 に実行されるGS2-Script
	 */
	public void setConsumeWalletDoneTriggerScript(String consumeWalletDoneTriggerScript) {
		this.consumeWalletDoneTriggerScript = consumeWalletDoneTriggerScript;
	}

	/**
	 * ウォレット残高消費完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeWalletDoneTriggerScript ウォレット残高消費完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMoneyRequest withConsumeWalletDoneTriggerScript(String consumeWalletDoneTriggerScript) {
		setConsumeWalletDoneTriggerScript(consumeWalletDoneTriggerScript);
		return this;
	}

}