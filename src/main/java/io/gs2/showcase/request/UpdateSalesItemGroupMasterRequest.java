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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 商品グループマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateSalesItemGroupMasterRequest extends Gs2BasicRequest<UpdateSalesItemGroupMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 商品グループマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 商品グループマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 商品グループマスターを更新
     * @return this
     */
    public UpdateSalesItemGroupMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 商品名 */
    private String salesItemGroupName;

    /**
     * 商品名を取得
     *
     * @return 商品グループマスターを更新
     */
    public String getSalesItemGroupName() {
        return salesItemGroupName;
    }

    /**
     * 商品名を設定
     *
     * @param salesItemGroupName 商品グループマスターを更新
     */
    public void setSalesItemGroupName(String salesItemGroupName) {
        this.salesItemGroupName = salesItemGroupName;
    }

    /**
     * 商品名を設定
     *
     * @param salesItemGroupName 商品グループマスターを更新
     * @return this
     */
    public UpdateSalesItemGroupMasterRequest withSalesItemGroupName(String salesItemGroupName) {
        setSalesItemGroupName(salesItemGroupName);
        return this;
    }

    /** 商品グループマスターの説明 */
    private String description;

    /**
     * 商品グループマスターの説明を取得
     *
     * @return 商品グループマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 商品グループマスターの説明を設定
     *
     * @param description 商品グループマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 商品グループマスターの説明を設定
     *
     * @param description 商品グループマスターを更新
     * @return this
     */
    public UpdateSalesItemGroupMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 商品のメタデータ */
    private String metadata;

    /**
     * 商品のメタデータを取得
     *
     * @return 商品グループマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 商品のメタデータを設定
     *
     * @param metadata 商品グループマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 商品のメタデータを設定
     *
     * @param metadata 商品グループマスターを更新
     * @return this
     */
    public UpdateSalesItemGroupMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 商品グループに含める商品リスト */
    private List<String> salesItemNames;

    /**
     * 商品グループに含める商品リストを取得
     *
     * @return 商品グループマスターを更新
     */
    public List<String> getSalesItemNames() {
        return salesItemNames;
    }

    /**
     * 商品グループに含める商品リストを設定
     *
     * @param salesItemNames 商品グループマスターを更新
     */
    public void setSalesItemNames(List<String> salesItemNames) {
        this.salesItemNames = salesItemNames;
    }

    /**
     * 商品グループに含める商品リストを設定
     *
     * @param salesItemNames 商品グループマスターを更新
     * @return this
     */
    public UpdateSalesItemGroupMasterRequest withSalesItemNames(List<String> salesItemNames) {
        setSalesItemNames(salesItemNames);
        return this;
    }

}