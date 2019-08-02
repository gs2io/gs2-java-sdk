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
 * 経験値を加算 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AddExperienceByUserIdRequest extends Gs2BasicRequest<AddExperienceByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 経験値を加算
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値を加算
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値を加算
     * @return this
     */
    public AddExperienceByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 経験値を加算
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 経験値を加算
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 経験値を加算
     * @return this
     */
    public AddExperienceByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 経験値の種類の名前 */
    private String experienceName;

    /**
     * 経験値の種類の名前を取得
     *
     * @return 経験値を加算
     */
    public String getExperienceName() {
        return experienceName;
    }

    /**
     * 経験値の種類の名前を設定
     *
     * @param experienceName 経験値を加算
     */
    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    /**
     * 経験値の種類の名前を設定
     *
     * @param experienceName 経験値を加算
     * @return this
     */
    public AddExperienceByUserIdRequest withExperienceName(String experienceName) {
        setExperienceName(experienceName);
        return this;
    }

    /** プロパティID */
    private String propertyId;

    /**
     * プロパティIDを取得
     *
     * @return 経験値を加算
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId 経験値を加算
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId 経験値を加算
     * @return this
     */
    public AddExperienceByUserIdRequest withPropertyId(String propertyId) {
        setPropertyId(propertyId);
        return this;
    }

    /** 加算する経験値量 */
    private Long experienceValue;

    /**
     * 加算する経験値量を取得
     *
     * @return 経験値を加算
     */
    public Long getExperienceValue() {
        return experienceValue;
    }

    /**
     * 加算する経験値量を設定
     *
     * @param experienceValue 経験値を加算
     */
    public void setExperienceValue(Long experienceValue) {
        this.experienceValue = experienceValue;
    }

    /**
     * 加算する経験値量を設定
     *
     * @param experienceValue 経験値を加算
     * @return this
     */
    public AddExperienceByUserIdRequest withExperienceValue(Long experienceValue) {
        setExperienceValue(experienceValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 経験値を加算
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 経験値を加算
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 経験値を加算
     * @return this
     */
    public AddExperienceByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}