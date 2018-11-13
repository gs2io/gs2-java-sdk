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
 * ウォレット
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Summary implements Serializable {

    /** ユーザID */
    private String userId;

    /** スロット番号 */
    private Integer slot;

    /** 有償課金通貨所持量 */
    private Integer paid;

    /** 無償課金通貨所持量 */
    private Integer free;

    /** 作成日時(エポック秒) */
    private Integer createAt;

    /** 最終更新日時(エポック秒) */
    private Integer updateAt;


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
     * 有償課金通貨所持量を取得
     *
     * @return 有償課金通貨所持量
     */
    public Integer getPaid() {
        return paid;
    }

    /**
     * 有償課金通貨所持量を設定
     *
     * @param paid 有償課金通貨所持量
     */
    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    /**
     * 無償課金通貨所持量を取得
     *
     * @return 無償課金通貨所持量
     */
    public Integer getFree() {
        return free;
    }

    /**
     * 無償課金通貨所持量を設定
     *
     * @param free 無償課金通貨所持量
     */
    public void setFree(Integer free) {
        this.free = free;
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