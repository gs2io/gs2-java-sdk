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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * アイテムモデルマスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteItemModelMasterRequest extends Gs2BasicRequest<DeleteItemModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return アイテムモデルマスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName アイテムモデルマスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName アイテムモデルマスターを削除
     * @return this
     */
    public DeleteItemModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの種類名 */
    private String inventoryName;

    /**
     * インベントリの種類名を取得
     *
     * @return アイテムモデルマスターを削除
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName アイテムモデルマスターを削除
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName アイテムモデルマスターを削除
     * @return this
     */
    public DeleteItemModelMasterRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** アイテムモデルの種類名 */
    private String itemName;

    /**
     * アイテムモデルの種類名を取得
     *
     * @return アイテムモデルマスターを削除
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * アイテムモデルの種類名を設定
     *
     * @param itemName アイテムモデルマスターを削除
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * アイテムモデルの種類名を設定
     *
     * @param itemName アイテムモデルマスターを削除
     * @return this
     */
    public DeleteItemModelMasterRequest withItemName(String itemName) {
        setItemName(itemName);
        return this;
    }

}