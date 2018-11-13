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

package io.gs2.money.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * プラットフォーム個別商品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PlatformedItem implements Serializable {

    /** プラットフォーム個別商品GRN */
    private String platformedItemId;

    /** 課金通貨GRN */
    private String moneyId;

    /** 商品GRN */
    private String itemId;

    /** 販売プラットフォーム */
    private String platform;

    /** アプリ内課金ID */
    private String name;

    /** 販売価格 */
    private Double price;

    /** 作成日時(エポック秒) */
    private Integer createAt;

    /** 最終更新日時(エポック秒) */
    private Integer updateAt;


    /**
     * プラットフォーム個別商品GRNを取得
     *
     * @return プラットフォーム個別商品GRN
     */
    public String getPlatformedItemId() {
        return platformedItemId;
    }

    /**
     * プラットフォーム個別商品GRNを設定
     *
     * @param platformedItemId プラットフォーム個別商品GRN
     */
    public void setPlatformedItemId(String platformedItemId) {
        this.platformedItemId = platformedItemId;
    }

    /**
     * 課金通貨GRNを取得
     *
     * @return 課金通貨GRN
     */
    public String getMoneyId() {
        return moneyId;
    }

    /**
     * 課金通貨GRNを設定
     *
     * @param moneyId 課金通貨GRN
     */
    public void setMoneyId(String moneyId) {
        this.moneyId = moneyId;
    }

    /**
     * 商品GRNを取得
     *
     * @return 商品GRN
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 商品GRNを設定
     *
     * @param itemId 商品GRN
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 販売プラットフォームを取得
     *
     * @return 販売プラットフォーム
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 販売プラットフォームを設定
     *
     * @param platform 販売プラットフォーム
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * アプリ内課金IDを取得
     *
     * @return アプリ内課金ID
     */
    public String getName() {
        return name;
    }

    /**
     * アプリ内課金IDを設定
     *
     * @param name アプリ内課金ID
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 販売価格を取得
     *
     * @return 販売価格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 販売価格を設定
     *
     * @param price 販売価格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 作成日時(エポック秒)を取得
     *
     * @return 作成日時(エポック秒)
     */
    public Integer getCreateAt() {
        return createAt;
    }

    /**
     * 作成日時(エポック秒)を設定
     *
     * @param createAt 作成日時(エポック秒)
     */
    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    /**
     * 最終更新日時(エポック秒)を取得
     *
     * @return 最終更新日時(エポック秒)
     */
    public Integer getUpdateAt() {
        return updateAt;
    }

    /**
     * 最終更新日時(エポック秒)を設定
     *
     * @param updateAt 最終更新日時(エポック秒)
     */
    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }

}