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

package io.gs2.account.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.account.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ネームスペースを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return ネームスペースを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アカウント引き継ぎ時にパスワードを変更するか */
    private Boolean changePasswordIfTakeOver;

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを取得
     *
     * @return ネームスペースを更新
     */
    public Boolean getChangePasswordIfTakeOver() {
        return changePasswordIfTakeOver;
    }

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを設定
     *
     * @param changePasswordIfTakeOver ネームスペースを更新
     */
    public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
        this.changePasswordIfTakeOver = changePasswordIfTakeOver;
    }

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを設定
     *
     * @param changePasswordIfTakeOver ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
        setChangePasswordIfTakeOver(changePasswordIfTakeOver);
        return this;
    }

    /** アカウント新規作成したときに実行するスクリプト */
    private ScriptSetting createAccountScript;

    /**
     * アカウント新規作成したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getCreateAccountScript() {
        return createAccountScript;
    }

    /**
     * アカウント新規作成したときに実行するスクリプトを設定
     *
     * @param createAccountScript ネームスペースを更新
     */
    public void setCreateAccountScript(ScriptSetting createAccountScript) {
        this.createAccountScript = createAccountScript;
    }

    /**
     * アカウント新規作成したときに実行するスクリプトを設定
     *
     * @param createAccountScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateAccountScript(ScriptSetting createAccountScript) {
        setCreateAccountScript(createAccountScript);
        return this;
    }

    /** 認証したときに実行するスクリプト */
    private ScriptSetting authenticationScript;

    /**
     * 認証したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getAuthenticationScript() {
        return authenticationScript;
    }

    /**
     * 認証したときに実行するスクリプトを設定
     *
     * @param authenticationScript ネームスペースを更新
     */
    public void setAuthenticationScript(ScriptSetting authenticationScript) {
        this.authenticationScript = authenticationScript;
    }

    /**
     * 認証したときに実行するスクリプトを設定
     *
     * @param authenticationScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAuthenticationScript(ScriptSetting authenticationScript) {
        setAuthenticationScript(authenticationScript);
        return this;
    }

    /** 引き継ぎ情報登録したときに実行するスクリプト */
    private ScriptSetting createTakeOverScript;

    /**
     * 引き継ぎ情報登録したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getCreateTakeOverScript() {
        return createTakeOverScript;
    }

    /**
     * 引き継ぎ情報登録したときに実行するスクリプトを設定
     *
     * @param createTakeOverScript ネームスペースを更新
     */
    public void setCreateTakeOverScript(ScriptSetting createTakeOverScript) {
        this.createTakeOverScript = createTakeOverScript;
    }

    /**
     * 引き継ぎ情報登録したときに実行するスクリプトを設定
     *
     * @param createTakeOverScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateTakeOverScript(ScriptSetting createTakeOverScript) {
        setCreateTakeOverScript(createTakeOverScript);
        return this;
    }

    /** 引き継ぎ実行したときに実行するスクリプト */
    private ScriptSetting doTakeOverScript;

    /**
     * 引き継ぎ実行したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getDoTakeOverScript() {
        return doTakeOverScript;
    }

    /**
     * 引き継ぎ実行したときに実行するスクリプトを設定
     *
     * @param doTakeOverScript ネームスペースを更新
     */
    public void setDoTakeOverScript(ScriptSetting doTakeOverScript) {
        this.doTakeOverScript = doTakeOverScript;
    }

    /**
     * 引き継ぎ実行したときに実行するスクリプトを設定
     *
     * @param doTakeOverScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDoTakeOverScript(ScriptSetting doTakeOverScript) {
        setDoTakeOverScript(doTakeOverScript);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを更新
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを更新
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}