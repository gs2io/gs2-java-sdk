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

package io.gs2.inventory.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inventory.model.*;
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

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アイテム入手したときに実行するスクリプト */
    private ScriptSetting acquireScript;

    /**
     * アイテム入手したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getAcquireScript() {
        return acquireScript;
    }

    /**
     * アイテム入手したときに実行するスクリプトを設定
     *
     * @param acquireScript ネームスペースを更新
     */
    public void setAcquireScript(ScriptSetting acquireScript) {
        this.acquireScript = acquireScript;
    }

    /**
     * アイテム入手したときに実行するスクリプトを設定
     *
     * @param acquireScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcquireScript(ScriptSetting acquireScript) {
        setAcquireScript(acquireScript);
        return this;
    }

    /** 入手上限に当たって入手できなかったときに実行するスクリプト */
    private ScriptSetting overflowScript;

    /**
     * 入手上限に当たって入手できなかったときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getOverflowScript() {
        return overflowScript;
    }

    /**
     * 入手上限に当たって入手できなかったときに実行するスクリプトを設定
     *
     * @param overflowScript ネームスペースを更新
     */
    public void setOverflowScript(ScriptSetting overflowScript) {
        this.overflowScript = overflowScript;
    }

    /**
     * 入手上限に当たって入手できなかったときに実行するスクリプトを設定
     *
     * @param overflowScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withOverflowScript(ScriptSetting overflowScript) {
        setOverflowScript(overflowScript);
        return this;
    }

    /** アイテム消費するときに実行するスクリプト */
    private ScriptSetting consumeScript;

    /**
     * アイテム消費するときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getConsumeScript() {
        return consumeScript;
    }

    /**
     * アイテム消費するときに実行するスクリプトを設定
     *
     * @param consumeScript ネームスペースを更新
     */
    public void setConsumeScript(ScriptSetting consumeScript) {
        this.consumeScript = consumeScript;
    }

    /**
     * アイテム消費するときに実行するスクリプトを設定
     *
     * @param consumeScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withConsumeScript(ScriptSetting consumeScript) {
        setConsumeScript(consumeScript);
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