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
 * アイテムモデルマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateItemModelMasterRequest extends Gs2BasicRequest<UpdateItemModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return アイテムモデルマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName アイテムモデルマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの種類名 */
    private String inventoryName;

    /**
     * インベントリの種類名を取得
     *
     * @return アイテムモデルマスターを更新
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName アイテムモデルマスターを更新
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** アイテムモデルの種類名 */
    private String itemName;

    /**
     * アイテムモデルの種類名を取得
     *
     * @return アイテムモデルマスターを更新
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * アイテムモデルの種類名を設定
     *
     * @param itemName アイテムモデルマスターを更新
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * アイテムモデルの種類名を設定
     *
     * @param itemName アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withItemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    /** アイテムモデルマスターの説明 */
    private String description;

    /**
     * アイテムモデルマスターの説明を取得
     *
     * @return アイテムモデルマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * アイテムモデルマスターの説明を設定
     *
     * @param description アイテムモデルマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * アイテムモデルマスターの説明を設定
     *
     * @param description アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アイテムモデルの種類のメタデータ */
    private String metadata;

    /**
     * アイテムモデルの種類のメタデータを取得
     *
     * @return アイテムモデルマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * アイテムモデルの種類のメタデータを設定
     *
     * @param metadata アイテムモデルマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * アイテムモデルの種類のメタデータを設定
     *
     * @param metadata アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** スタック可能な最大数量 */
    private Long stackingLimit;

    /**
     * スタック可能な最大数量を取得
     *
     * @return アイテムモデルマスターを更新
     */
    public Long getStackingLimit() {
        return stackingLimit;
    }

    /**
     * スタック可能な最大数量を設定
     *
     * @param stackingLimit アイテムモデルマスターを更新
     */
    public void setStackingLimit(Long stackingLimit) {
        this.stackingLimit = stackingLimit;
    }

    /**
     * スタック可能な最大数量を設定
     *
     * @param stackingLimit アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withStackingLimit(Long stackingLimit) {
        setStackingLimit(stackingLimit);
        return this;
    }

    /** スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すか */
    private Boolean allowMultipleStacks;

    /**
     * スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すかを取得
     *
     * @return アイテムモデルマスターを更新
     */
    public Boolean getAllowMultipleStacks() {
        return allowMultipleStacks;
    }

    /**
     * スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すかを設定
     *
     * @param allowMultipleStacks アイテムモデルマスターを更新
     */
    public void setAllowMultipleStacks(Boolean allowMultipleStacks) {
        this.allowMultipleStacks = allowMultipleStacks;
    }

    /**
     * スタック可能な最大数量を超えた時複数枠にアイテムを保管することを許すかを設定
     *
     * @param allowMultipleStacks アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withAllowMultipleStacks(Boolean allowMultipleStacks) {
        setAllowMultipleStacks(allowMultipleStacks);
        return this;
    }

    /** 表示順番 */
    private Integer sortValue;

    /**
     * 表示順番を取得
     *
     * @return アイテムモデルマスターを更新
     */
    public Integer getSortValue() {
        return sortValue;
    }

    /**
     * 表示順番を設定
     *
     * @param sortValue アイテムモデルマスターを更新
     */
    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    /**
     * 表示順番を設定
     *
     * @param sortValue アイテムモデルマスターを更新
     * @return this
     */
    public UpdateItemModelMasterRequest withSortValue(Integer sortValue) {
        setSortValue(sortValue);
        return this;
    }

}