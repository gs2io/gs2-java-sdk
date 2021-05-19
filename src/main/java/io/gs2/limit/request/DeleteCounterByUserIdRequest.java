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

package io.gs2.limit.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.limit.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してカウンターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteCounterByUserIdRequest extends Gs2BasicRequest<DeleteCounterByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してカウンターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してカウンターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してカウンターを削除
     * @return this
     */
    public DeleteCounterByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 回数制限の種類の名前 */
    private String limitName;

    /**
     * 回数制限の種類の名前を取得
     *
     * @return ユーザIDを指定してカウンターを削除
     */
    public String getLimitName() {
        return limitName;
    }

    /**
     * 回数制限の種類の名前を設定
     *
     * @param limitName ユーザIDを指定してカウンターを削除
     */
    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    /**
     * 回数制限の種類の名前を設定
     *
     * @param limitName ユーザIDを指定してカウンターを削除
     * @return this
     */
    public DeleteCounterByUserIdRequest withLimitName(String limitName) {
        setLimitName(limitName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してカウンターを削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してカウンターを削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してカウンターを削除
     * @return this
     */
    public DeleteCounterByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** カウンターの名前 */
    private String counterName;

    /**
     * カウンターの名前を取得
     *
     * @return ユーザIDを指定してカウンターを削除
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンターの名前を設定
     *
     * @param counterName ユーザIDを指定してカウンターを削除
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンターの名前を設定
     *
     * @param counterName ユーザIDを指定してカウンターを削除
     * @return this
     */
    public DeleteCounterByUserIdRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してカウンターを削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してカウンターを削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してカウンターを削除
     * @return this
     */
    public DeleteCounterByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}