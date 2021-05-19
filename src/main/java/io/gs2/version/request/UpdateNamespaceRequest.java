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

package io.gs2.version.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.version.model.*;
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

    /** バージョンチェック通過後に改めて発行するプロジェクトトークンの権限判定に使用する ユーザ のGRN */
    private String assumeUserId;

    /**
     * バージョンチェック通過後に改めて発行するプロジェクトトークンの権限判定に使用する ユーザ のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAssumeUserId() {
        return assumeUserId;
    }

    /**
     * バージョンチェック通過後に改めて発行するプロジェクトトークンの権限判定に使用する ユーザ のGRNを設定
     *
     * @param assumeUserId ネームスペースを更新
     */
    public void setAssumeUserId(String assumeUserId) {
        this.assumeUserId = assumeUserId;
    }

    /**
     * バージョンチェック通過後に改めて発行するプロジェクトトークンの権限判定に使用する ユーザ のGRNを設定
     *
     * @param assumeUserId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAssumeUserId(String assumeUserId) {
        setAssumeUserId(assumeUserId);
        return this;
    }

    /** バージョンを承認したときに実行するスクリプト */
    private ScriptSetting acceptVersionScript;

    /**
     * バージョンを承認したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getAcceptVersionScript() {
        return acceptVersionScript;
    }

    /**
     * バージョンを承認したときに実行するスクリプトを設定
     *
     * @param acceptVersionScript ネームスペースを更新
     */
    public void setAcceptVersionScript(ScriptSetting acceptVersionScript) {
        this.acceptVersionScript = acceptVersionScript;
    }

    /**
     * バージョンを承認したときに実行するスクリプトを設定
     *
     * @param acceptVersionScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcceptVersionScript(ScriptSetting acceptVersionScript) {
        setAcceptVersionScript(acceptVersionScript);
        return this;
    }

    /** バージョンチェック時 に実行されるスクリプト のGRN */
    private String checkVersionTriggerScriptId;

    /**
     * バージョンチェック時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCheckVersionTriggerScriptId() {
        return checkVersionTriggerScriptId;
    }

    /**
     * バージョンチェック時 に実行されるスクリプト のGRNを設定
     *
     * @param checkVersionTriggerScriptId ネームスペースを更新
     */
    public void setCheckVersionTriggerScriptId(String checkVersionTriggerScriptId) {
        this.checkVersionTriggerScriptId = checkVersionTriggerScriptId;
    }

    /**
     * バージョンチェック時 に実行されるスクリプト のGRNを設定
     *
     * @param checkVersionTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCheckVersionTriggerScriptId(String checkVersionTriggerScriptId) {
        setCheckVersionTriggerScriptId(checkVersionTriggerScriptId);
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