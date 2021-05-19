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
 * インベントリのアイテムを消費 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ConsumeItemSetByUserIdRequest extends Gs2BasicRequest<ConsumeItemSetByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return インベントリのアイテムを消費
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName インベントリのアイテムを消費
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの名前 */
    private String inventoryName;

    /**
     * インベントリの名前を取得
     *
     * @return インベントリのアイテムを消費
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの名前を設定
     *
     * @param inventoryName インベントリのアイテムを消費
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの名前を設定
     *
     * @param inventoryName インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return インベントリのアイテムを消費
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId インベントリのアイテムを消費
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** アイテムマスターの名前 */
    private String itemName;

    /**
     * アイテムマスターの名前を取得
     *
     * @return インベントリのアイテムを消費
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName インベントリのアイテムを消費
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withItemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    /** 消費する量 */
    private Long consumeCount;

    /**
     * 消費する量を取得
     *
     * @return インベントリのアイテムを消費
     */
    public Long getConsumeCount() {
        return consumeCount;
    }

    /**
     * 消費する量を設定
     *
     * @param consumeCount インベントリのアイテムを消費
     */
    public void setConsumeCount(Long consumeCount) {
        this.consumeCount = consumeCount;
    }

    /**
     * 消費する量を設定
     *
     * @param consumeCount インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withConsumeCount(Long consumeCount) {
        setConsumeCount(consumeCount);
        return this;
    }

    /** アイテムセットを識別する名前 */
    private String itemSetName;

    /**
     * アイテムセットを識別する名前を取得
     *
     * @return インベントリのアイテムを消費
     */
    public String getItemSetName() {
        return itemSetName;
    }

    /**
     * アイテムセットを識別する名前を設定
     *
     * @param itemSetName インベントリのアイテムを消費
     */
    public void setItemSetName(String itemSetName) {
        this.itemSetName = itemSetName;
    }

    /**
     * アイテムセットを識別する名前を設定
     *
     * @param itemSetName インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withItemSetName(String itemSetName) {
        setItemSetName(itemSetName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return インベントリのアイテムを消費
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider インベントリのアイテムを消費
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider インベントリのアイテムを消費
     * @return this
     */
    public ConsumeItemSetByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}