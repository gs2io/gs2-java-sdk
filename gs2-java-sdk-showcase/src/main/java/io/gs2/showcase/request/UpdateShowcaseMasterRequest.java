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

package io.gs2.showcase.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.showcase.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 陳列棚マスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateShowcaseMasterRequest extends Gs2BasicRequest<UpdateShowcaseMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 陳列棚マスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 陳列棚マスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 陳列棚マスターを更新
     * @return this
     */
    public UpdateShowcaseMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 陳列棚名 */
    private String showcaseName;

    /**
     * 陳列棚名を取得
     *
     * @return 陳列棚マスターを更新
     */
    public String getShowcaseName() {
        return showcaseName;
    }

    /**
     * 陳列棚名を設定
     *
     * @param showcaseName 陳列棚マスターを更新
     */
    public void setShowcaseName(String showcaseName) {
        this.showcaseName = showcaseName;
    }

    /**
     * 陳列棚名を設定
     *
     * @param showcaseName 陳列棚マスターを更新
     * @return this
     */
    public UpdateShowcaseMasterRequest withShowcaseName(String showcaseName) {
        setShowcaseName(showcaseName);
        return this;
    }

    /** 陳列棚マスターの説明 */
    private String description;

    /**
     * 陳列棚マスターの説明を取得
     *
     * @return 陳列棚マスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 陳列棚マスターの説明を設定
     *
     * @param description 陳列棚マスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 陳列棚マスターの説明を設定
     *
     * @param description 陳列棚マスターを更新
     * @return this
     */
    public UpdateShowcaseMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 商品のメタデータ */
    private String metadata;

    /**
     * 商品のメタデータを取得
     *
     * @return 陳列棚マスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 商品のメタデータを設定
     *
     * @param metadata 陳列棚マスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 商品のメタデータを設定
     *
     * @param metadata 陳列棚マスターを更新
     * @return this
     */
    public UpdateShowcaseMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 陳列する商品モデル一覧 */
    private List<DisplayItemMaster> displayItems;

    /**
     * 陳列する商品モデル一覧を取得
     *
     * @return 陳列棚マスターを更新
     */
    public List<DisplayItemMaster> getDisplayItems() {
        return displayItems;
    }

    /**
     * 陳列する商品モデル一覧を設定
     *
     * @param displayItems 陳列棚マスターを更新
     */
    public void setDisplayItems(List<DisplayItemMaster> displayItems) {
        this.displayItems = displayItems;
    }

    /**
     * 陳列する商品モデル一覧を設定
     *
     * @param displayItems 陳列棚マスターを更新
     * @return this
     */
    public UpdateShowcaseMasterRequest withDisplayItems(List<DisplayItemMaster> displayItems) {
        setDisplayItems(displayItems);
        return this;
    }

    /** 販売期間とするイベントマスター のGRN */
    private String salesPeriodEventId;

    /**
     * 販売期間とするイベントマスター のGRNを取得
     *
     * @return 陳列棚マスターを更新
     */
    public String getSalesPeriodEventId() {
        return salesPeriodEventId;
    }

    /**
     * 販売期間とするイベントマスター のGRNを設定
     *
     * @param salesPeriodEventId 陳列棚マスターを更新
     */
    public void setSalesPeriodEventId(String salesPeriodEventId) {
        this.salesPeriodEventId = salesPeriodEventId;
    }

    /**
     * 販売期間とするイベントマスター のGRNを設定
     *
     * @param salesPeriodEventId 陳列棚マスターを更新
     * @return this
     */
    public UpdateShowcaseMasterRequest withSalesPeriodEventId(String salesPeriodEventId) {
        setSalesPeriodEventId(salesPeriodEventId);
        return this;
    }

}