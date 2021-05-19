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

package io.gs2.formation.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.formation.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定して保存したフォームのキャパシティを追加 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AddMoldCapacityByUserIdRequest extends Gs2BasicRequest<AddMoldCapacityByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して保存したフォームのキャパシティを追加
     * @return this
     */
    public AddMoldCapacityByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して保存したフォームのキャパシティを追加
     * @return this
     */
    public AddMoldCapacityByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** フォームの保存領域の名前 */
    private String moldName;

    /**
     * フォームの保存領域の名前を取得
     *
     * @return ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public String getMoldName() {
        return moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public void setMoldName(String moldName) {
        this.moldName = moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName ユーザIDを指定して保存したフォームのキャパシティを追加
     * @return this
     */
    public AddMoldCapacityByUserIdRequest withMoldName(String moldName) {
        setMoldName(moldName);
        return this;
    }

    /** 加算するキャパシティ量 */
    private Integer capacity;

    /**
     * 加算するキャパシティ量を取得
     *
     * @return ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * 加算するキャパシティ量を設定
     *
     * @param capacity ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 加算するキャパシティ量を設定
     *
     * @param capacity ユーザIDを指定して保存したフォームのキャパシティを追加
     * @return this
     */
    public AddMoldCapacityByUserIdRequest withCapacity(Integer capacity) {
        setCapacity(capacity);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して保存したフォームのキャパシティを追加
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して保存したフォームのキャパシティを追加
     * @return this
     */
    public AddMoldCapacityByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}