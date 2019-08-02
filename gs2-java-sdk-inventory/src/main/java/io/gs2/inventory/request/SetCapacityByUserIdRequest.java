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
 * キャパシティサイズを設定 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetCapacityByUserIdRequest extends Gs2BasicRequest<SetCapacityByUserIdRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return キャパシティサイズを設定
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName キャパシティサイズを設定
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName キャパシティサイズを設定
     * @return this
     */
    public SetCapacityByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリモデル名 */
    private String inventoryName;

    /**
     * インベントリモデル名を取得
     *
     * @return キャパシティサイズを設定
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリモデル名を設定
     *
     * @param inventoryName キャパシティサイズを設定
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリモデル名を設定
     *
     * @param inventoryName キャパシティサイズを設定
     * @return this
     */
    public SetCapacityByUserIdRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return キャパシティサイズを設定
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId キャパシティサイズを設定
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId キャパシティサイズを設定
     * @return this
     */
    public SetCapacityByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 現在のインベントリの最大キャパシティ */
    private Integer newCapacityValue;

    /**
     * 現在のインベントリの最大キャパシティを取得
     *
     * @return キャパシティサイズを設定
     */
    public Integer getNewCapacityValue() {
        return newCapacityValue;
    }

    /**
     * 現在のインベントリの最大キャパシティを設定
     *
     * @param newCapacityValue キャパシティサイズを設定
     */
    public void setNewCapacityValue(Integer newCapacityValue) {
        this.newCapacityValue = newCapacityValue;
    }

    /**
     * 現在のインベントリの最大キャパシティを設定
     *
     * @param newCapacityValue キャパシティサイズを設定
     * @return this
     */
    public SetCapacityByUserIdRequest withNewCapacityValue(Integer newCapacityValue) {
        setNewCapacityValue(newCapacityValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return キャパシティサイズを設定
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider キャパシティサイズを設定
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider キャパシティサイズを設定
     * @return this
     */
    public SetCapacityByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}