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
 * フォームの保存領域マスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteMoldModelMasterRequest extends Gs2BasicRequest<DeleteMoldModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return フォームの保存領域マスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームの保存領域マスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームの保存領域マスターを削除
     * @return this
     */
    public DeleteMoldModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォームの保存領域名 */
    private String moldName;

    /**
     * フォームの保存領域名を取得
     *
     * @return フォームの保存領域マスターを削除
     */
    public String getMoldName() {
        return moldName;
    }

    /**
     * フォームの保存領域名を設定
     *
     * @param moldName フォームの保存領域マスターを削除
     */
    public void setMoldName(String moldName) {
        this.moldName = moldName;
    }

    /**
     * フォームの保存領域名を設定
     *
     * @param moldName フォームの保存領域マスターを削除
     * @return this
     */
    public DeleteMoldModelMasterRequest withMoldName(String moldName) {
        setMoldName(moldName);
        return this;
    }

}