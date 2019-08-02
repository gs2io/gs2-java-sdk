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

package io.gs2.quest.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.quest.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 現在有効なクエストマスターのマスターデータをエクスポートします のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ExportMasterRequest extends Gs2BasicRequest<ExportMasterRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return 現在有効なクエストマスターのマスターデータをエクスポートします
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName 現在有効なクエストマスターのマスターデータをエクスポートします
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName 現在有効なクエストマスターのマスターデータをエクスポートします
     * @return this
     */
    public ExportMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

}