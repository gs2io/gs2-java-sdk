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
 * キャパシティサイズを加算 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AddCapacityByUserIdRequest extends Gs2BasicRequest<AddCapacityByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return キャパシティサイズを加算
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName キャパシティサイズを加算
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName キャパシティサイズを加算
     * @return this
     */
    public AddCapacityByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリモデル名 */
    private String inventoryName;

    /**
     * インベントリモデル名を取得
     *
     * @return キャパシティサイズを加算
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリモデル名を設定
     *
     * @param inventoryName キャパシティサイズを加算
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリモデル名を設定
     *
     * @param inventoryName キャパシティサイズを加算
     * @return this
     */
    public AddCapacityByUserIdRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return キャパシティサイズを加算
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId キャパシティサイズを加算
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId キャパシティサイズを加算
     * @return this
     */
    public AddCapacityByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 加算するキャパシティサイズ */
    private Integer addCapacityValue;

    /**
     * 加算するキャパシティサイズを取得
     *
     * @return キャパシティサイズを加算
     */
    public Integer getAddCapacityValue() {
        return addCapacityValue;
    }

    /**
     * 加算するキャパシティサイズを設定
     *
     * @param addCapacityValue キャパシティサイズを加算
     */
    public void setAddCapacityValue(Integer addCapacityValue) {
        this.addCapacityValue = addCapacityValue;
    }

    /**
     * 加算するキャパシティサイズを設定
     *
     * @param addCapacityValue キャパシティサイズを加算
     * @return this
     */
    public AddCapacityByUserIdRequest withAddCapacityValue(Integer addCapacityValue) {
        setAddCapacityValue(addCapacityValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return キャパシティサイズを加算
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider キャパシティサイズを加算
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider キャパシティサイズを加算
     * @return this
     */
    public AddCapacityByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}