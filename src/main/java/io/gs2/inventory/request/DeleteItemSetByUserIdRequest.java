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
 * 有効期限ごとのアイテム所持数量を削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteItemSetByUserIdRequest extends Gs2BasicRequest<DeleteItemSetByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 有効期限ごとのアイテム所持数量を削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 有効期限ごとのアイテム所持数量を削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 有効期限ごとのアイテム所持数量を削除
     * @return this
     */
    public DeleteItemSetByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの名前 */
    private String inventoryName;

    /**
     * インベントリの名前を取得
     *
     * @return 有効期限ごとのアイテム所持数量を削除
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの名前を設定
     *
     * @param inventoryName 有効期限ごとのアイテム所持数量を削除
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの名前を設定
     *
     * @param inventoryName 有効期限ごとのアイテム所持数量を削除
     * @return this
     */
    public DeleteItemSetByUserIdRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 有効期限ごとのアイテム所持数量を削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 有効期限ごとのアイテム所持数量を削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 有効期限ごとのアイテム所持数量を削除
     * @return this
     */
    public DeleteItemSetByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** アイテムマスターの名前 */
    private String itemName;

    /**
     * アイテムマスターの名前を取得
     *
     * @return 有効期限ごとのアイテム所持数量を削除
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName 有効期限ごとのアイテム所持数量を削除
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName 有効期限ごとのアイテム所持数量を削除
     * @return this
     */
    public DeleteItemSetByUserIdRequest withItemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    /** アイテムセットを識別する名前 */
    private String itemSetName;

    /**
     * アイテムセットを識別する名前を取得
     *
     * @return 有効期限ごとのアイテム所持数量を削除
     */
    public String getItemSetName() {
        return itemSetName;
    }

    /**
     * アイテムセットを識別する名前を設定
     *
     * @param itemSetName 有効期限ごとのアイテム所持数量を削除
     */
    public void setItemSetName(String itemSetName) {
        this.itemSetName = itemSetName;
    }

    /**
     * アイテムセットを識別する名前を設定
     *
     * @param itemSetName 有効期限ごとのアイテム所持数量を削除
     * @return this
     */
    public DeleteItemSetByUserIdRequest withItemSetName(String itemSetName) {
        setItemSetName(itemSetName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 有効期限ごとのアイテム所持数量を削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 有効期限ごとのアイテム所持数量を削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 有効期限ごとのアイテム所持数量を削除
     * @return this
     */
    public DeleteItemSetByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}