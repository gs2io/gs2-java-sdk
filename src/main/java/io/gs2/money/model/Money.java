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

package io.gs2.money.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 課金通貨
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Money implements Serializable {

    /** 課金通貨GRN */
    private String moneyId;

    /** オーナーID */
    private String ownerId;

    /** 課金通貨名 */
    private String name;

    /** 説明文 */
    private String description;

    /** 支払い優先度 */
    private String priority;

    /** 無償課金通貨を異なるスロットで共有するか */
    private Boolean shareFree;

    /** 通貨 */
    private String currency;

    /** ストアプラットフォームのレシートの検証機能を利用するか */
    private Boolean useVerifyReceipt;

    /** Apple のアプリケーションバンドルID */
    private String appleKey;

    /** Google のレシート検証用公開鍵 */
    private String googleKey;

    /** 未使用残高 */
    private Double balance;

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

    /** 作成日時(エポック秒) */
    private Integer createAt;

    /** 最終更新日時(エポック秒) */
    private Integer updateAt;


    /**
     * 課金通貨GRNを取得
     *
     * @return 課金通貨GRN
     */
    public String getMoneyId() {
        return moneyId;
    }

    /**
     * 課金通貨GRNを設定
     *
     * @param moneyId 課金通貨GRN
     */
    public void setMoneyId(String moneyId) {
        this.moneyId = moneyId;
    }

    /**
     * オーナーIDを取得
     *
     * @return オーナーID
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * オーナーIDを設定
     *
     * @param ownerId オーナーID
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 課金通貨名を取得
     *
     * @return 課金通貨名
     */
    public String getName() {
        return name;
    }

    /**
     * 課金通貨名を設定
     *
     * @param name 課金通貨名
     */
    public void setName(String name) {
        this.name = name;
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
     * 無償課金通貨を異なるスロットで共有するかを取得
     *
     * @return 無償課金通貨を異なるスロットで共有するか
     */
    public Boolean getShareFree() {
        return shareFree;
    }

    /**
     * 無償課金通貨を異なるスロットで共有するかを設定
     *
     * @param shareFree 無償課金通貨を異なるスロットで共有するか
     */
    public void setShareFree(Boolean shareFree) {
        this.shareFree = shareFree;
    }

    /**
     * 通貨を取得
     *
     * @return 通貨
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 通貨を設定
     *
     * @param currency 通貨
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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
     * 未使用残高を取得
     *
     * @return 未使用残高
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 未使用残高を設定
     *
     * @param balance 未使用残高
     */
    public void setBalance(Double balance) {
        this.balance = balance;
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

}