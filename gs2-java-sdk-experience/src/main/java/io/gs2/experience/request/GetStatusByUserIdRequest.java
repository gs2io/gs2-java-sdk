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

package io.gs2.experience.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.experience.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ステータスを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetStatusByUserIdRequest extends Gs2BasicRequest<GetStatusByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ステータスを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ステータスを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ステータスを取得
     * @return this
     */
    public GetStatusByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ステータスを取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ステータスを取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ステータスを取得
     * @return this
     */
    public GetStatusByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 経験値の種類の名前 */
    private String experienceName;

    /**
     * 経験値の種類の名前を取得
     *
     * @return ステータスを取得
     */
    public String getExperienceName() {
        return experienceName;
    }

    /**
     * 経験値の種類の名前を設定
     *
     * @param experienceName ステータスを取得
     */
    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    /**
     * 経験値の種類の名前を設定
     *
     * @param experienceName ステータスを取得
     * @return this
     */
    public GetStatusByUserIdRequest withExperienceName(String experienceName) {
        setExperienceName(experienceName);
        return this;
    }

    /** プロパティID */
    private String propertyId;

    /**
     * プロパティIDを取得
     *
     * @return ステータスを取得
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId ステータスを取得
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId ステータスを取得
     * @return this
     */
    public GetStatusByUserIdRequest withPropertyId(String propertyId) {
        setPropertyId(propertyId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ステータスを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ステータスを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ステータスを取得
     * @return this
     */
    public GetStatusByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}