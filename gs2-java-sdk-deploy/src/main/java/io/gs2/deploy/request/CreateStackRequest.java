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

package io.gs2.deploy.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.deploy.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * スタックを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateStackRequest extends Gs2BasicRequest<CreateStackRequest> {

    /** スタック名 */
    private String name;

    /**
     * スタック名を取得
     *
     * @return スタックを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * スタック名を設定
     *
     * @param name スタックを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * スタック名を設定
     *
     * @param name スタックを新規作成
     * @return this
     */
    public CreateStackRequest withName(String name) {
        setName(name);
        return this;
    }

    /** スタックの説明 */
    private String description;

    /**
     * スタックの説明を取得
     *
     * @return スタックを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタックの説明を設定
     *
     * @param description スタックを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタックの説明を設定
     *
     * @param description スタックを新規作成
     * @return this
     */
    public CreateStackRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** テンプレートデータ */
    private String template;

    /**
     * テンプレートデータを取得
     *
     * @return スタックを新規作成
     */
    public String getTemplate() {
        return template;
    }

    /**
     * テンプレートデータを設定
     *
     * @param template スタックを新規作成
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * テンプレートデータを設定
     *
     * @param template スタックを新規作成
     * @return this
     */
    public CreateStackRequest withTemplate(String template) {
        setTemplate(template);
        return this;
    }

}