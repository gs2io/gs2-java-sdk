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
 * レシート
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Receipt implements Serializable {

    /** ユーザID */
    private String userId;

    /** スロット番号 */
    private Integer slot;

    /** 種類 */
    private String type;

    /** 金額 */
    private Double price;

    /** 有償課金通貨 */
    private Integer paid;

    /** 無償課金通貨 */
    private Integer free;

    /** 総数 */
    private Integer total;

    /** 用途 */
    private Integer use;

    /** 決済日時(エポック秒) */
    private Integer createAt;


    /**
     * ユーザIDを取得
     *
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * スロット番号を取得
     *
     * @return スロット番号
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot スロット番号
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * 種類を取得
     *
     * @return 種類
     */
    public String getType() {
        return type;
    }

    /**
     * 種類を設定
     *
     * @param type 種類
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 金額を取得
     *
     * @return 金額
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 金額を設定
     *
     * @param price 金額
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 有償課金通貨を取得
     *
     * @return 有償課金通貨
     */
    public Integer getPaid() {
        return paid;
    }

    /**
     * 有償課金通貨を設定
     *
     * @param paid 有償課金通貨
     */
    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    /**
     * 無償課金通貨を取得
     *
     * @return 無償課金通貨
     */
    public Integer getFree() {
        return free;
    }

    /**
     * 無償課金通貨を設定
     *
     * @param free 無償課金通貨
     */
    public void setFree(Integer free) {
        this.free = free;
    }

    /**
     * 総数を取得
     *
     * @return 総数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 総数を設定
     *
     * @param total 総数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 用途を取得
     *
     * @return 用途
     */
    public Integer getUse() {
        return use;
    }

    /**
     * 用途を設定
     *
     * @param use 用途
     */
    public void setUse(Integer use) {
        this.use = use;
    }

    /**
     * 決済日時(エポック秒)を取得
     *
     * @return 決済日時(エポック秒)
     */
    public Integer getCreateAt() {
        return createAt;
    }

    /**
     * 決済日時(エポック秒)を設定
     *
     * @param createAt 決済日時(エポック秒)
     */
    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

}