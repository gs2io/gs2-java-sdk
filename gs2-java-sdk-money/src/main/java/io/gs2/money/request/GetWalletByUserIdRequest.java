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
 * ユーザーIDを指定してウォレットを取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetWalletByUserIdRequest extends Gs2BasicRequest<GetWalletByUserIdRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return ユーザーIDを指定してウォレットを取得します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ユーザーIDを指定してウォレットを取得します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ユーザーIDを指定してウォレットを取得します
     * @return this
     */
    public GetWalletByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザーIDを指定してウォレットを取得します
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してウォレットを取得します
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定してウォレットを取得します
     * @return this
     */
    public GetWalletByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スロット番号 */
    private Integer slot;

    /**
     * スロット番号を取得
     *
     * @return ユーザーIDを指定してウォレットを取得します
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot ユーザーIDを指定してウォレットを取得します
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * スロット番号を設定
     *
     * @param slot ユーザーIDを指定してウォレットを取得します
     * @return this
     */
    public GetWalletByUserIdRequest withSlot(Integer slot) {
        setSlot(slot);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してウォレットを取得します
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してウォレットを取得します
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してウォレットを取得します
     * @return this
     */
    public GetWalletByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}