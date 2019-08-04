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
import io.gs2.control.Gs2BasicRequest;

/**
 * 現在有効な現在有効な所持品マスターを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCurrentItemModelMasterRequest extends Gs2BasicRequest<UpdateCurrentItemModelMasterRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return 現在有効な現在有効な所持品マスターを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName 現在有効な現在有効な所持品マスターを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName 現在有効な現在有効な所持品マスターを更新します
     * @return this
     */
    public UpdateCurrentItemModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** マスターデータ */
    private String settings;

    /**
     * マスターデータを取得
     *
     * @return 現在有効な現在有効な所持品マスターを更新します
     */
    public String getSettings() {
        return settings;
    }

    /**
     * マスターデータを設定
     *
     * @param settings 現在有効な現在有効な所持品マスターを更新します
     */
    public void setSettings(String settings) {
        this.settings = settings;
    }

    /**
     * マスターデータを設定
     *
     * @param settings 現在有効な現在有効な所持品マスターを更新します
     * @return this
     */
    public UpdateCurrentItemModelMasterRequest withSettings(String settings) {
        setSettings(settings);
        return this;
    }

}