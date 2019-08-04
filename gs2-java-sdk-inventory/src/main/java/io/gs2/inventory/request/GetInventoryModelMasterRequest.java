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
 * インベントリモデルマスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetInventoryModelMasterRequest extends Gs2BasicRequest<GetInventoryModelMasterRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return インベントリモデルマスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName インベントリモデルマスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName インベントリモデルマスターを取得
     * @return this
     */
    public GetInventoryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの種類名 */
    private String inventoryName;

    /**
     * インベントリの種類名を取得
     *
     * @return インベントリモデルマスターを取得
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName インベントリモデルマスターを取得
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName インベントリモデルマスターを取得
     * @return this
     */
    public GetInventoryModelMasterRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

}