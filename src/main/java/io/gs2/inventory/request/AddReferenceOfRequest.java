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
 * 参照元を追加 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AddReferenceOfRequest extends Gs2BasicRequest<AddReferenceOfRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 参照元を追加
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 参照元を追加
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 参照元を追加
     * @return this
     */
    public AddReferenceOfRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** インベントリの名前 */
    private String inventoryName;

    /**
     * インベントリの名前を取得
     *
     * @return 参照元を追加
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * インベントリの名前を設定
     *
     * @param inventoryName 参照元を追加
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * インベントリの名前を設定
     *
     * @param inventoryName 参照元を追加
     * @return this
     */
    public AddReferenceOfRequest withInventoryName(String inventoryName) {
        setInventoryName(inventoryName);
        return this;
    }

    /** アイテムマスターの名前 */
    private String itemName;

    /**
     * アイテムマスターの名前を取得
     *
     * @return 参照元を追加
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName 参照元を追加
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * アイテムマスターの名前を設定
     *
     * @param itemName 参照元を追加
     * @return this
     */
    public AddReferenceOfRequest withItemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    /** アイテムセットを識別する名前 */
    private String itemSetName;

    /**
     * アイテムセットを識別する名前を取得
     *
     * @return 参照元を追加
     */
    public String getItemSetName() {
        return itemSetName;
    }

    /**
     * アイテムセットを識別する名前を設定
     *
     * @param itemSetName 参照元を追加
     */
    public void setItemSetName(String itemSetName) {
        this.itemSetName = itemSetName;
    }

    /**
     * アイテムセットを識別する名前を設定
     *
     * @param itemSetName 参照元を追加
     * @return this
     */
    public AddReferenceOfRequest withItemSetName(String itemSetName) {
        setItemSetName(itemSetName);
        return this;
    }

    /** この所持品の参照元 */
    private String referenceOf;

    /**
     * この所持品の参照元を取得
     *
     * @return 参照元を追加
     */
    public String getReferenceOf() {
        return referenceOf;
    }

    /**
     * この所持品の参照元を設定
     *
     * @param referenceOf 参照元を追加
     */
    public void setReferenceOf(String referenceOf) {
        this.referenceOf = referenceOf;
    }

    /**
     * この所持品の参照元を設定
     *
     * @param referenceOf 参照元を追加
     * @return this
     */
    public AddReferenceOfRequest withReferenceOf(String referenceOf) {
        setReferenceOf(referenceOf);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 参照元を追加
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 参照元を追加
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 参照元を追加
     * @return this
     */
    public AddReferenceOfRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public AddReferenceOfRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}