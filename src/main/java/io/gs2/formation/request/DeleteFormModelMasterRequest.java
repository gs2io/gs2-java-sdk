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
 * フォームマスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteFormModelMasterRequest extends Gs2BasicRequest<DeleteFormModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return フォームマスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームマスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームマスターを削除
     * @return this
     */
    public DeleteFormModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォーム名 */
    private String formModelName;

    /**
     * フォーム名を取得
     *
     * @return フォームマスターを削除
     */
    public String getFormModelName() {
        return formModelName;
    }

    /**
     * フォーム名を設定
     *
     * @param formModelName フォームマスターを削除
     */
    public void setFormModelName(String formModelName) {
        this.formModelName = formModelName;
    }

    /**
     * フォーム名を設定
     *
     * @param formModelName フォームマスターを削除
     * @return this
     */
    public DeleteFormModelMasterRequest withFormModelName(String formModelName) {
        setFormModelName(formModelName);
        return this;
    }

}