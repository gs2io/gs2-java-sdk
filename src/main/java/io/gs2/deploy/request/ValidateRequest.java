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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * テンプレートを検証 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ValidateRequest extends Gs2BasicRequest<ValidateRequest> {

    /** テンプレートデータ */
    private String template;

    /**
     * テンプレートデータを取得
     *
     * @return テンプレートを検証
     */
    public String getTemplate() {
        return template;
    }

    /**
     * テンプレートデータを設定
     *
     * @param template テンプレートを検証
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * テンプレートデータを設定
     *
     * @param template テンプレートを検証
     * @return this
     */
    public ValidateRequest withTemplate(String template) {
        setTemplate(template);
        return this;
    }

}