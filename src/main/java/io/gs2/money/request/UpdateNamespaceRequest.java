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
 * ネームスペースを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return ネームスペースを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ネームスペースを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを更新します
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 消費優先度 */
    private String priority;

    /**
     * 消費優先度を取得
     *
     * @return ネームスペースを更新します
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 消費優先度を設定
     *
     * @param priority ネームスペースを更新します
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * 消費優先度を設定
     *
     * @param priority ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withPriority(String priority) {
        setPriority(priority);
        return this;
    }

    /** Apple AppStore のバンドルID */
    private String appleKey;

    /**
     * Apple AppStore のバンドルIDを取得
     *
     * @return ネームスペースを更新します
     */
    public String getAppleKey() {
        return appleKey;
    }

    /**
     * Apple AppStore のバンドルIDを設定
     *
     * @param appleKey ネームスペースを更新します
     */
    public void setAppleKey(String appleKey) {
        this.appleKey = appleKey;
    }

    /**
     * Apple AppStore のバンドルIDを設定
     *
     * @param appleKey ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withAppleKey(String appleKey) {
        setAppleKey(appleKey);
        return this;
    }

    /** Google PlayStore の秘密鍵 */
    private String googleKey;

    /**
     * Google PlayStore の秘密鍵を取得
     *
     * @return ネームスペースを更新します
     */
    public String getGoogleKey() {
        return googleKey;
    }

    /**
     * Google PlayStore の秘密鍵を設定
     *
     * @param googleKey ネームスペースを更新します
     */
    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    /**
     * Google PlayStore の秘密鍵を設定
     *
     * @param googleKey ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withGoogleKey(String googleKey) {
        setGoogleKey(googleKey);
        return this;
    }

    /** UnityEditorが出力する偽のレシートで決済できるようにするか */
    private Boolean enableFakeReceipt;

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを取得
     *
     * @return ネームスペースを更新します
     */
    public Boolean getEnableFakeReceipt() {
        return enableFakeReceipt;
    }

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
     *
     * @param enableFakeReceipt ネームスペースを更新します
     */
    public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
        this.enableFakeReceipt = enableFakeReceipt;
    }

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
     *
     * @param enableFakeReceipt ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withEnableFakeReceipt(Boolean enableFakeReceipt) {
        setEnableFakeReceipt(enableFakeReceipt);
        return this;
    }

    /** ウォレット新規作成したときに実行するスクリプト */
    private ScriptSetting createWalletScript;

    /**
     * ウォレット新規作成したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新します
     */
    public ScriptSetting getCreateWalletScript() {
        return createWalletScript;
    }

    /**
     * ウォレット新規作成したときに実行するスクリプトを設定
     *
     * @param createWalletScript ネームスペースを更新します
     */
    public void setCreateWalletScript(ScriptSetting createWalletScript) {
        this.createWalletScript = createWalletScript;
    }

    /**
     * ウォレット新規作成したときに実行するスクリプトを設定
     *
     * @param createWalletScript ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withCreateWalletScript(ScriptSetting createWalletScript) {
        setCreateWalletScript(createWalletScript);
        return this;
    }

    /** ウォレット残高加算したときに実行するスクリプト */
    private ScriptSetting depositScript;

    /**
     * ウォレット残高加算したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新します
     */
    public ScriptSetting getDepositScript() {
        return depositScript;
    }

    /**
     * ウォレット残高加算したときに実行するスクリプトを設定
     *
     * @param depositScript ネームスペースを更新します
     */
    public void setDepositScript(ScriptSetting depositScript) {
        this.depositScript = depositScript;
    }

    /**
     * ウォレット残高加算したときに実行するスクリプトを設定
     *
     * @param depositScript ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withDepositScript(ScriptSetting depositScript) {
        setDepositScript(depositScript);
        return this;
    }

    /** ウォレット残高消費したときに実行するスクリプト */
    private ScriptSetting withdrawScript;

    /**
     * ウォレット残高消費したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新します
     */
    public ScriptSetting getWithdrawScript() {
        return withdrawScript;
    }

    /**
     * ウォレット残高消費したときに実行するスクリプトを設定
     *
     * @param withdrawScript ネームスペースを更新します
     */
    public void setWithdrawScript(ScriptSetting withdrawScript) {
        this.withdrawScript = withdrawScript;
    }

    /**
     * ウォレット残高消費したときに実行するスクリプトを設定
     *
     * @param withdrawScript ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withWithdrawScript(ScriptSetting withdrawScript) {
        setWithdrawScript(withdrawScript);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを更新します
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを更新します
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}