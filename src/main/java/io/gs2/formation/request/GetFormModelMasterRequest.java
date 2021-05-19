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

package io.gs2.formation.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.formation.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * フォームマスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetFormModelMasterRequest extends Gs2BasicRequest<GetFormModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return フォームマスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームマスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームマスターを取得
     * @return this
     */
    public GetFormModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォーム名 */
    private String formModelName;

    /**
     * フォーム名を取得
     *
     * @return フォームマスターを取得
     */
    public String getFormModelName() {
        return formModelName;
    }

    /**
     * フォーム名を設定
     *
     * @param formModelName フォームマスターを取得
     */
    public void setFormModelName(String formModelName) {
        this.formModelName = formModelName;
    }

    /**
     * フォーム名を設定
     *
     * @param formModelName フォームマスターを取得
     * @return this
     */
    public GetFormModelMasterRequest withFormModelName(String formModelName) {
        setFormModelName(formModelName);
        return this;
    }

}