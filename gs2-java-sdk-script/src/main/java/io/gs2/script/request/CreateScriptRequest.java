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
 * スクリプトを新規作成します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateScriptRequest extends Gs2BasicRequest<CreateScriptRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スクリプトを新規作成します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スクリプトを新規作成します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スクリプトを新規作成します
     * @return this
     */
    public CreateScriptRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スクリプト名 */
    private String name;

    /**
     * スクリプト名を取得
     *
     * @return スクリプトを新規作成します
     */
    public String getName() {
        return name;
    }

    /**
     * スクリプト名を設定
     *
     * @param name スクリプトを新規作成します
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * スクリプト名を設定
     *
     * @param name スクリプトを新規作成します
     * @return this
     */
    public CreateScriptRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return スクリプトを新規作成します
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description スクリプトを新規作成します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description スクリプトを新規作成します
     * @return this
     */
    public CreateScriptRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** Luaスクリプト */
    private String script;

    /**
     * Luaスクリプトを取得
     *
     * @return スクリプトを新規作成します
     */
    public String getScript() {
        return script;
    }

    /**
     * Luaスクリプトを設定
     *
     * @param script スクリプトを新規作成します
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Luaスクリプトを設定
     *
     * @param script スクリプトを新規作成します
     * @return this
     */
    public CreateScriptRequest withScript(String script) {
        setScript(script);
        return this;
    }

}