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
 * 商品マスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteSalesItemMasterRequest extends Gs2BasicRequest<DeleteSalesItemMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 商品マスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 商品マスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 商品マスターを削除
     * @return this
     */
    public DeleteSalesItemMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 商品名 */
    private String salesItemName;

    /**
     * 商品名を取得
     *
     * @return 商品マスターを削除
     */
    public String getSalesItemName() {
        return salesItemName;
    }

    /**
     * 商品名を設定
     *
     * @param salesItemName 商品マスターを削除
     */
    public void setSalesItemName(String salesItemName) {
        this.salesItemName = salesItemName;
    }

    /**
     * 商品名を設定
     *
     * @param salesItemName 商品マスターを削除
     * @return this
     */
    public DeleteSalesItemMasterRequest withSalesItemName(String salesItemName) {
        setSalesItemName(salesItemName);
        return this;
    }

}