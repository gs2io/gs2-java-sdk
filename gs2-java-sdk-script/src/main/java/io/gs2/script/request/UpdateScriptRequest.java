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

package io.gs2.script.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.script.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * スクリプトを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateScriptRequest extends Gs2BasicRequest<UpdateScriptRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スクリプトを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スクリプトを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スクリプトを更新します
     * @return this
     */
    public UpdateScriptRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スクリプト名 */
    private String scriptName;

    /**
     * スクリプト名を取得
     *
     * @return スクリプトを更新します
     */
    public String getScriptName() {
        return scriptName;
    }

    /**
     * スクリプト名を設定
     *
     * @param scriptName スクリプトを更新します
     */
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * スクリプト名を設定
     *
     * @param scriptName スクリプトを更新します
     * @return this
     */
    public UpdateScriptRequest withScriptName(String scriptName) {
        setScriptName(scriptName);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return スクリプトを更新します
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description スクリプトを更新します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description スクリプトを更新します
     * @return this
     */
    public UpdateScriptRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** Luaスクリプト */
    private String script;

    /**
     * Luaスクリプトを取得
     *
     * @return スクリプトを更新します
     */
    public String getScript() {
        return script;
    }

    /**
     * Luaスクリプトを設定
     *
     * @param script スクリプトを更新します
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Luaスクリプトを設定
     *
     * @param script スクリプトを更新します
     * @return this
     */
    public UpdateScriptRequest withScript(String script) {
        setScript(script);
        return this;
    }

}