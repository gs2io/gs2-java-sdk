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
 * 商品マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateSalesItemMasterRequest extends Gs2BasicRequest<CreateSalesItemMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 商品マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 商品マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 商品マスターを新規作成
     * @return this
     */
    public CreateSalesItemMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 商品名 */
    private String name;

    /**
     * 商品名を取得
     *
     * @return 商品マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名を設定
     *
     * @param name 商品マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品名を設定
     *
     * @param name 商品マスターを新規作成
     * @return this
     */
    public CreateSalesItemMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 商品マスターの説明 */
    private String description;

    /**
     * 商品マスターの説明を取得
     *
     * @return 商品マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 商品マスターの説明を設定
     *
     * @param description 商品マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 商品マスターの説明を設定
     *
     * @param description 商品マスターを新規作成
     * @return this
     */
    public CreateSalesItemMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 商品のメタデータ */
    private String metadata;

    /**
     * 商品のメタデータを取得
     *
     * @return 商品マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 商品のメタデータを設定
     *
     * @param metadata 商品マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 商品のメタデータを設定
     *
     * @param metadata 商品マスターを新規作成
     * @return this
     */
    public CreateSalesItemMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 消費アクションリスト */
    private List<ConsumeAction> consumeActions;

    /**
     * 消費アクションリストを取得
     *
     * @return 商品マスターを新規作成
     */
    public List<ConsumeAction> getConsumeActions() {
        return consumeActions;
    }

    /**
     * 消費アクションリストを設定
     *
     * @param consumeActions 商品マスターを新規作成
     */
    public void setConsumeActions(List<ConsumeAction> consumeActions) {
        this.consumeActions = consumeActions;
    }

    /**
     * 消費アクションリストを設定
     *
     * @param consumeActions 商品マスターを新規作成
     * @return this
     */
    public CreateSalesItemMasterRequest withConsumeActions(List<ConsumeAction> consumeActions) {
        setConsumeActions(consumeActions);
        return this;
    }

    /** 入手アクションリスト */
    private List<AcquireAction> acquireActions;

    /**
     * 入手アクションリストを取得
     *
     * @return 商品マスターを新規作成
     */
    public List<AcquireAction> getAcquireActions() {
        return acquireActions;
    }

    /**
     * 入手アクションリストを設定
     *
     * @param acquireActions 商品マスターを新規作成
     */
    public void setAcquireActions(List<AcquireAction> acquireActions) {
        this.acquireActions = acquireActions;
    }

    /**
     * 入手アクションリストを設定
     *
     * @param acquireActions 商品マスターを新規作成
     * @return this
     */
    public CreateSalesItemMasterRequest withAcquireActions(List<AcquireAction> acquireActions) {
        setAcquireActions(acquireActions);
        return this;
    }

}