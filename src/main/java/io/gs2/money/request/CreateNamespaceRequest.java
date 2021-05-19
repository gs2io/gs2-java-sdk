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

package io.gs2.money.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.money.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペースの名前 */
    private String name;

    /**
     * ネームスペースの名前を取得
     *
     * @return ネームスペースを新規作成します
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param name ネームスペースを新規作成します
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param name ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成します
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 消費優先度 */
    private String priority;

    /**
     * 消費優先度を取得
     *
     * @return ネームスペースを新規作成します
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 消費優先度を設定
     *
     * @param priority ネームスペースを新規作成します
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * 消費優先度を設定
     *
     * @param priority ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withPriority(String priority) {
        setPriority(priority);
        return this;
    }

    /** 無償課金通貨を異なるスロットで共有するか */
    private Boolean shareFree;

    /**
     * 無償課金通貨を異なるスロットで共有するかを取得
     *
     * @return ネームスペースを新規作成します
     */
    public Boolean getShareFree() {
        return shareFree;
    }

    /**
     * 無償課金通貨を異なるスロットで共有するかを設定
     *
     * @param shareFree ネームスペースを新規作成します
     */
    public void setShareFree(Boolean shareFree) {
        this.shareFree = shareFree;
    }

    /**
     * 無償課金通貨を異なるスロットで共有するかを設定
     *
     * @param shareFree ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withShareFree(Boolean shareFree) {
        setShareFree(shareFree);
        return this;
    }

    /** 通貨の種類 */
    private String currency;

    /**
     * 通貨の種類を取得
     *
     * @return ネームスペースを新規作成します
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 通貨の種類を設定
     *
     * @param currency ネームスペースを新規作成します
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 通貨の種類を設定
     *
     * @param currency ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withCurrency(String currency) {
        setCurrency(currency);
        return this;
    }

    /** Apple AppStore のバンドルID */
    private String appleKey;

    /**
     * Apple AppStore のバンドルIDを取得
     *
     * @return ネームスペースを新規作成します
     */
    public String getAppleKey() {
        return appleKey;
    }

    /**
     * Apple AppStore のバンドルIDを設定
     *
     * @param appleKey ネームスペースを新規作成します
     */
    public void setAppleKey(String appleKey) {
        this.appleKey = appleKey;
    }

    /**
     * Apple AppStore のバンドルIDを設定
     *
     * @param appleKey ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withAppleKey(String appleKey) {
        setAppleKey(appleKey);
        return this;
    }

    /** Google PlayStore の秘密鍵 */
    private String googleKey;

    /**
     * Google PlayStore の秘密鍵を取得
     *
     * @return ネームスペースを新規作成します
     */
    public String getGoogleKey() {
        return googleKey;
    }

    /**
     * Google PlayStore の秘密鍵を設定
     *
     * @param googleKey ネームスペースを新規作成します
     */
    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    /**
     * Google PlayStore の秘密鍵を設定
     *
     * @param googleKey ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withGoogleKey(String googleKey) {
        setGoogleKey(googleKey);
        return this;
    }

    /** UnityEditorが出力する偽のレシートで決済できるようにするか */
    private Boolean enableFakeReceipt;

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを取得
     *
     * @return ネームスペースを新規作成します
     */
    public Boolean getEnableFakeReceipt() {
        return enableFakeReceipt;
    }

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
     *
     * @param enableFakeReceipt ネームスペースを新規作成します
     */
    public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
        this.enableFakeReceipt = enableFakeReceipt;
    }

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
     *
     * @param enableFakeReceipt ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withEnableFakeReceipt(Boolean enableFakeReceipt) {
        setEnableFakeReceipt(enableFakeReceipt);
        return this;
    }

    /** ウォレット新規作成したときに実行するスクリプト */
    private ScriptSetting createWalletScript;

    /**
     * ウォレット新規作成したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成します
     */
    public ScriptSetting getCreateWalletScript() {
        return createWalletScript;
    }

    /**
     * ウォレット新規作成したときに実行するスクリプトを設定
     *
     * @param createWalletScript ネームスペースを新規作成します
     */
    public void setCreateWalletScript(ScriptSetting createWalletScript) {
        this.createWalletScript = createWalletScript;
    }

    /**
     * ウォレット新規作成したときに実行するスクリプトを設定
     *
     * @param createWalletScript ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withCreateWalletScript(ScriptSetting createWalletScript) {
        setCreateWalletScript(createWalletScript);
        return this;
    }

    /** ウォレット残高加算したときに実行するスクリプト */
    private ScriptSetting depositScript;

    /**
     * ウォレット残高加算したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成します
     */
    public ScriptSetting getDepositScript() {
        return depositScript;
    }

    /**
     * ウォレット残高加算したときに実行するスクリプトを設定
     *
     * @param depositScript ネームスペースを新規作成します
     */
    public void setDepositScript(ScriptSetting depositScript) {
        this.depositScript = depositScript;
    }

    /**
     * ウォレット残高加算したときに実行するスクリプトを設定
     *
     * @param depositScript ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withDepositScript(ScriptSetting depositScript) {
        setDepositScript(depositScript);
        return this;
    }

    /** ウォレット残高消費したときに実行するスクリプト */
    private ScriptSetting withdrawScript;

    /**
     * ウォレット残高消費したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成します
     */
    public ScriptSetting getWithdrawScript() {
        return withdrawScript;
    }

    /**
     * ウォレット残高消費したときに実行するスクリプトを設定
     *
     * @param withdrawScript ネームスペースを新規作成します
     */
    public void setWithdrawScript(ScriptSetting withdrawScript) {
        this.withdrawScript = withdrawScript;
    }

    /**
     * ウォレット残高消費したときに実行するスクリプトを設定
     *
     * @param withdrawScript ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withWithdrawScript(ScriptSetting withdrawScript) {
        setWithdrawScript(withdrawScript);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを新規作成します
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成します
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成します
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}