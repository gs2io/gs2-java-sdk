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
 * ユーザーIDを指定してウォレットに残高を加算します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DepositByUserIdRequest extends Gs2BasicRequest<DepositByUserIdRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スロット番号 */
    private Integer slot;

    /**
     * スロット番号を取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withSlot(Integer slot) {
        setSlot(slot);
        return this;
    }

    /** 購入価格 */
    private Float price;

    /**
     * 購入価格を取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 購入価格を設定
     *
     * @param price ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 購入価格を設定
     *
     * @param price ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withPrice(Float price) {
        setPrice(price);
        return this;
    }

    /** 付与する課金通貨の数量 */
    private Integer count;

    /**
     * 付与する課金通貨の数量を取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 付与する課金通貨の数量を設定
     *
     * @param count ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 付与する課金通貨の数量を設定
     *
     * @param count ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withCount(Integer count) {
        setCount(count);
        return this;
    }

    /** トランザクションID */
    private String transactionId;

    /**
     * トランザクションIDを取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * トランザクションIDを設定
     *
     * @param transactionId ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * トランザクションIDを設定
     *
     * @param transactionId ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withTransactionId(String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    /** ストアプラットフォームで販売されているコンテンツID */
    private String contentsId;

    /**
     * ストアプラットフォームで販売されているコンテンツIDを取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public String getContentsId() {
        return contentsId;
    }

    /**
     * ストアプラットフォームで販売されているコンテンツIDを設定
     *
     * @param contentsId ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }

    /**
     * ストアプラットフォームで販売されているコンテンツIDを設定
     *
     * @param contentsId ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withContentsId(String contentsId) {
        setContentsId(contentsId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してウォレットに残高を加算します
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してウォレットに残高を加算します
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してウォレットに残高を加算します
     * @return this
     */
    public DepositByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}