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

package io.gs2.money.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.money.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ユーザーIDを指定してウォレットから残高を消費します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class WithdrawByUserIdRequest extends Gs2BasicRequest<WithdrawByUserIdRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return ユーザーIDを指定してウォレットから残高を消費します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ユーザーIDを指定してウォレットから残高を消費します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ユーザーIDを指定してウォレットから残高を消費します
     * @return this
     */
    public WithdrawByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザーIDを指定してウォレットから残高を消費します
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してウォレットから残高を消費します
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してウォレットから残高を消費します
     * @return this
     */
    public WithdrawByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スロット番号 */
    private Integer slot;

    /**
     * スロット番号を取得
     *
     * @return ユーザーIDを指定してウォレットから残高を消費します
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot ユーザーIDを指定してウォレットから残高を消費します
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot ユーザーIDを指定してウォレットから残高を消費します
     * @return this
     */
    public WithdrawByUserIdRequest withSlot(Integer slot) {
        setSlot(slot);
        return this;
    }

    /** 消費する課金通貨の数量 */
    private Integer count;

    /**
     * 消費する課金通貨の数量を取得
     *
     * @return ユーザーIDを指定してウォレットから残高を消費します
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 消費する課金通貨の数量を設定
     *
     * @param count ユーザーIDを指定してウォレットから残高を消費します
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 消費する課金通貨の数量を設定
     *
     * @param count ユーザーIDを指定してウォレットから残高を消費します
     * @return this
     */
    public WithdrawByUserIdRequest withCount(Integer count) {
        setCount(count);
        return this;
    }

    /** 有償課金通貨のみを対象とするか */
    private Boolean paidOnly;

    /**
     * 有償課金通貨のみを対象とするかを取得
     *
     * @return ユーザーIDを指定してウォレットから残高を消費します
     */
    public Boolean getPaidOnly() {
        return paidOnly;
    }

    /**
     * 有償課金通貨のみを対象とするかを設定
     *
     * @param paidOnly ユーザーIDを指定してウォレットから残高を消費します
     */
    public void setPaidOnly(Boolean paidOnly) {
        this.paidOnly = paidOnly;
    }

    /**
     * 有償課金通貨のみを対象とするかを設定
     *
     * @param paidOnly ユーザーIDを指定してウォレットから残高を消費します
     * @return this
     */
    public WithdrawByUserIdRequest withPaidOnly(Boolean paidOnly) {
        setPaidOnly(paidOnly);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してウォレットから残高を消費します
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してウォレットから残高を消費します
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してウォレットから残高を消費します
     * @return this
     */
    public WithdrawByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}