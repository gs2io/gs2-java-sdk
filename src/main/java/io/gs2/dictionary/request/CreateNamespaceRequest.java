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

package io.gs2.dictionary.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.dictionary.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
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
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** エントリー登録時に実行するスクリプト */
    private ScriptSetting entryScript;

    /**
     * エントリー登録時に実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getEntryScript() {
        return entryScript;
    }

    /**
     * エントリー登録時に実行するスクリプトを設定
     *
     * @param entryScript ネームスペースを新規作成
     */
    public void setEntryScript(ScriptSetting entryScript) {
        this.entryScript = entryScript;
    }

    /**
     * エントリー登録時に実行するスクリプトを設定
     *
     * @param entryScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withEntryScript(ScriptSetting entryScript) {
        setEntryScript(entryScript);
        return this;
    }

    /** 登録済みのエントリーを再度登録しようとした時に実行するスクリプト */
    private ScriptSetting duplicateEntryScript;

    /**
     * 登録済みのエントリーを再度登録しようとした時に実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getDuplicateEntryScript() {
        return duplicateEntryScript;
    }

    /**
     * 登録済みのエントリーを再度登録しようとした時に実行するスクリプトを設定
     *
     * @param duplicateEntryScript ネームスペースを新規作成
     */
    public void setDuplicateEntryScript(ScriptSetting duplicateEntryScript) {
        this.duplicateEntryScript = duplicateEntryScript;
    }

    /**
     * 登録済みのエントリーを再度登録しようとした時に実行するスクリプトを設定
     *
     * @param duplicateEntryScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDuplicateEntryScript(ScriptSetting duplicateEntryScript) {
        setDuplicateEntryScript(duplicateEntryScript);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを新規作成
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}