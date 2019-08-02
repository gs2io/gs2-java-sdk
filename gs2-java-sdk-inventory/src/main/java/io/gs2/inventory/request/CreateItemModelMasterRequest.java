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
 * アイテムモデルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateItemModelMasterRequest extends Gs2BasicRequest<CreateItemModelMasterRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName アイテムモデルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** アイテムの種類名 */
    private String inventoryName;

    /**
     * アイテムの種類名を取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * アイテムの種類名を設定
     *
     * @param inventoryName アイテムモデルマスターを新規作成
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * アイテムの種類名を設定
     *
     * @param inventoryName アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** アイテムモデルの種類名 */
    private String name;

    /**
     * アイテムモデルの種類名を取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * アイテムモデルの種類名を設定
     *
     * @param name アイテムモデルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * アイテムモデルの種類名を設定
     *
     * @param name アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** アイテムモデルマスターの説明 */
    private String description;

    /**
     * アイテムモデルマスターの説明を取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * アイテムモデルマスターの説明を設定
     *
     * @param description アイテムモデルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * アイテムモデルマスターの説明を設定
     *
     * @param description アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アイテムモデルの種類のメタデータ */
    private String metadata;

    /**
     * アイテムモデルの種類のメタデータを取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * アイテムモデルの種類のメタデータを設定
     *
     * @param metadata アイテムモデルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * アイテムモデルの種類のメタデータを設定
     *
     * @param metadata アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 所有可能な最大数量 */
    private Long maxCount;

    /**
     * 所有可能な最大数量を取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public Long getMaxCount() {
        return maxCount;
    }

    /**
     * 所有可能な最大数量を設定
     *
     * @param maxCount アイテムモデルマスターを新規作成
     */
    public void setMaxCount(Long maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * 所有可能な最大数量を設定
     *
     * @param maxCount アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withMaxCount(Long maxCount) {
        setMaxCount(maxCount);
        return this;
    }

    /** 表示順番 */
    private Integer sortValue;

    /**
     * 表示順番を取得
     *
     * @return アイテムモデルマスターを新規作成
     */
    public Integer getSortValue() {
        return sortValue;
    }

    /**
     * 表示順番を設定
     *
     * @param sortValue アイテムモデルマスターを新規作成
     */
    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    /**
     * 表示順番を設定
     *
     * @param sortValue アイテムモデルマスターを新規作成
     * @return this
     */
    public CreateItemModelMasterRequest withSortValue(Integer sortValue) {
        setSortValue(sortValue);
        return this;
    }

}