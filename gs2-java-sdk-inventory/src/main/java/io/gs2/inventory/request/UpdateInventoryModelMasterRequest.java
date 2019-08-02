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
 * インベントリモデルマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateInventoryModelMasterRequest extends Gs2BasicRequest<UpdateInventoryModelMasterRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return インベントリモデルマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName インベントリモデルマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName インベントリモデルマスターを更新
     * @return this
     */
    public UpdateInventoryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの種類名 */
    private String inventoryName;

    /**
     * インベントリの種類名を取得
     *
     * @return インベントリモデルマスターを更新
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName インベントリモデルマスターを更新
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName インベントリモデルマスターを更新
     * @return this
     */
    public UpdateInventoryModelMasterRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** インベントリモデルマスターの説明 */
    private String description;

    /**
     * インベントリモデルマスターの説明を取得
     *
     * @return インベントリモデルマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * インベントリモデルマスターの説明を設定
     *
     * @param description インベントリモデルマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * インベントリモデルマスターの説明を設定
     *
     * @param description インベントリモデルマスターを更新
     * @return this
     */
    public UpdateInventoryModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** インベントリの種類のメタデータ */
    private String metadata;

    /**
     * インベントリの種類のメタデータを取得
     *
     * @return インベントリモデルマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * インベントリの種類のメタデータを設定
     *
     * @param metadata インベントリモデルマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * インベントリの種類のメタデータを設定
     *
     * @param metadata インベントリモデルマスターを更新
     * @return this
     */
    public UpdateInventoryModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** インベントリの初期サイズ */
    private Integer initialCapacity;

    /**
     * インベントリの初期サイズを取得
     *
     * @return インベントリモデルマスターを更新
     */
    public Integer getInitialCapacity() {
        return initialCapacity;
    }

    /**
     * インベントリの初期サイズを設定
     *
     * @param initialCapacity インベントリモデルマスターを更新
     */
    public void setInitialCapacity(Integer initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    /**
     * インベントリの初期サイズを設定
     *
     * @param initialCapacity インベントリモデルマスターを更新
     * @return this
     */
    public UpdateInventoryModelMasterRequest withInitialCapacity(Integer initialCapacity) {
        setInitialCapacity(initialCapacity);
        return this;
    }

    /** インベントリの最大サイズ */
    private Integer maxCapacity;

    /**
     * インベントリの最大サイズを取得
     *
     * @return インベントリモデルマスターを更新
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * インベントリの最大サイズを設定
     *
     * @param maxCapacity インベントリモデルマスターを更新
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * インベントリの最大サイズを設定
     *
     * @param maxCapacity インベントリモデルマスターを更新
     * @return this
     */
    public UpdateInventoryModelMasterRequest withMaxCapacity(Integer maxCapacity) {
        setMaxCapacity(maxCapacity);
        return this;
    }

}