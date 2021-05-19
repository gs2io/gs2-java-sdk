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
 * アイテムをインベントリに追加 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AcquireItemSetByUserIdRequest extends Gs2BasicRequest<AcquireItemSetByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return アイテムをインベントリに追加
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName アイテムをインベントリに追加
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの種類名 */
    private String inventoryName;

    /**
     * インベントリの種類名を取得
     *
     * @return アイテムをインベントリに追加
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName アイテムをインベントリに追加
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの種類名を設定
     *
     * @param inventoryName アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** アイテムマスターの名前 */
    private String itemName;

    /**
     * アイテムマスターの名前を取得
     *
     * @return アイテムをインベントリに追加
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName アイテムをインベントリに追加
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withItemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return アイテムをインベントリに追加
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId アイテムをインベントリに追加
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 入手する量 */
    private Long acquireCount;

    /**
     * 入手する量を取得
     *
     * @return アイテムをインベントリに追加
     */
    public Long getAcquireCount() {
        return acquireCount;
    }

    /**
     * 入手する量を設定
     *
     * @param acquireCount アイテムをインベントリに追加
     */
    public void setAcquireCount(Long acquireCount) {
        this.acquireCount = acquireCount;
    }

    /**
     * 入手する量を設定
     *
     * @param acquireCount アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withAcquireCount(Long acquireCount) {
        setAcquireCount(acquireCount);
        return this;
    }

    /** 有効期限 */
    private Long expiresAt;

    /**
     * 有効期限を取得
     *
     * @return アイテムをインベントリに追加
     */
    public Long getExpiresAt() {
        return expiresAt;
    }

    /**
     * 有効期限を設定
     *
     * @param expiresAt アイテムをインベントリに追加
     */
    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * 有効期限を設定
     *
     * @param expiresAt アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withExpiresAt(Long expiresAt) {
        setExpiresAt(expiresAt);
        return this;
    }

    /** 既存の ItemSet に空きがあったとしても、新しい ItemSet を作成するか */
    private Boolean createNewItemSet;

    /**
     * 既存の ItemSet に空きがあったとしても、新しい ItemSet を作成するかを取得
     *
     * @return アイテムをインベントリに追加
     */
    public Boolean getCreateNewItemSet() {
        return createNewItemSet;
    }

    /**
     * 既存の ItemSet に空きがあったとしても、新しい ItemSet を作成するかを設定
     *
     * @param createNewItemSet アイテムをインベントリに追加
     */
    public void setCreateNewItemSet(Boolean createNewItemSet) {
        this.createNewItemSet = createNewItemSet;
    }

    /**
     * 既存の ItemSet に空きがあったとしても、新しい ItemSet を作成するかを設定
     *
     * @param createNewItemSet アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withCreateNewItemSet(Boolean createNewItemSet) {
        setCreateNewItemSet(createNewItemSet);
        return this;
    }

    /** 追加先のアイテムセットの名前 */
    private String itemSetName;

    /**
     * 追加先のアイテムセットの名前を取得
     *
     * @return アイテムをインベントリに追加
     */
    public String getItemSetName() {
        return itemSetName;
    }

    /**
     * 追加先のアイテムセットの名前を設定
     *
     * @param itemSetName アイテムをインベントリに追加
     */
    public void setItemSetName(String itemSetName) {
        this.itemSetName = itemSetName;
    }

    /**
     * 追加先のアイテムセットの名前を設定
     *
     * @param itemSetName アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withItemSetName(String itemSetName) {
        setItemSetName(itemSetName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return アイテムをインベントリに追加
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider アイテムをインベントリに追加
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider アイテムをインベントリに追加
     * @return this
     */
    public AcquireItemSetByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}