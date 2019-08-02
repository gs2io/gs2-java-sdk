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
 * インベントリモデルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateInventoryModelMasterRequest extends Gs2BasicRequest<CreateInventoryModelMasterRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return インベントリモデルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName インベントリモデルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName インベントリモデルマスターを新規作成
     * @return this
     */
    public CreateInventoryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの種類名 */
    private String name;

    /**
     * インベントリの種類名を取得
     *
     * @return インベントリモデルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param name インベントリモデルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param name インベントリモデルマスターを新規作成
     * @return this
     */
    public CreateInventoryModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** インベントリモデルマスターの説明 */
    private String description;

    /**
     * インベントリモデルマスターの説明を取得
     *
     * @return インベントリモデルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * インベントリモデルマスターの説明を設定
     *
     * @param description インベントリモデルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * インベントリモデルマスターの説明を設定
     *
     * @param description インベントリモデルマスターを新規作成
     * @return this
     */
    public CreateInventoryModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** インベントリの種類のメタデータ */
    private String metadata;

    /**
     * インベントリの種類のメタデータを取得
     *
     * @return インベントリモデルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * インベントリの種類のメタデータを設定
     *
     * @param metadata インベントリモデルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * インベントリの種類のメタデータを設定
     *
     * @param metadata インベントリモデルマスターを新規作成
     * @return this
     */
    public CreateInventoryModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** インベントリの初期サイズ */
    private Integer initialCapacity;

    /**
     * インベントリの初期サイズを取得
     *
     * @return インベントリモデルマスターを新規作成
     */
    public Integer getInitialCapacity() {
        return initialCapacity;
    }

    /**
     * インベントリの初期サイズを設定
     *
     * @param initialCapacity インベントリモデルマスターを新規作成
     */
    public void setInitialCapacity(Integer initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    /**
     * インベントリの初期サイズを設定
     *
     * @param initialCapacity インベントリモデルマスターを新規作成
     * @return this
     */
    public CreateInventoryModelMasterRequest withInitialCapacity(Integer initialCapacity) {
        setInitialCapacity(initialCapacity);
        return this;
    }

    /** インベントリの最大サイズ */
    private Integer maxCapacity;

    /**
     * インベントリの最大サイズを取得
     *
     * @return インベントリモデルマスターを新規作成
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * インベントリの最大サイズを設定
     *
     * @param maxCapacity インベントリモデルマスターを新規作成
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * インベントリの最大サイズを設定
     *
     * @param maxCapacity インベントリモデルマスターを新規作成
     * @return this
     */
    public CreateInventoryModelMasterRequest withMaxCapacity(Integer maxCapacity) {
        setMaxCapacity(maxCapacity);
        return this;
    }

}