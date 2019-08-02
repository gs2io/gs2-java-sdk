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
 * 累計獲得経験値を設定 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetExperienceByUserIdRequest extends Gs2BasicRequest<SetExperienceByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 累計獲得経験値を設定
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 累計獲得経験値を設定
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 累計獲得経験値を設定
     * @return this
     */
    public SetExperienceByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 累計獲得経験値を設定
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 累計獲得経験値を設定
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 累計獲得経験値を設定
     * @return this
     */
    public SetExperienceByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 経験値の種類の名前 */
    private String experienceName;

    /**
     * 経験値の種類の名前を取得
     *
     * @return 累計獲得経験値を設定
     */
    public String getExperienceName() {
        return experienceName;
    }

    /**
     * 経験値の種類の名前を設定
     *
     * @param experienceName 累計獲得経験値を設定
     */
    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    /**
     * 経験値の種類の名前を設定
     *
     * @param experienceName 累計獲得経験値を設定
     * @return this
     */
    public SetExperienceByUserIdRequest withExperienceName(String experienceName) {
        setExperienceName(experienceName);
        return this;
    }

    /** プロパティID */
    private String propertyId;

    /**
     * プロパティIDを取得
     *
     * @return 累計獲得経験値を設定
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId 累計獲得経験値を設定
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId 累計獲得経験値を設定
     * @return this
     */
    public SetExperienceByUserIdRequest withPropertyId(String propertyId) {
        setPropertyId(propertyId);
        return this;
    }

    /** 累計獲得経験値 */
    private Long experienceValue;

    /**
     * 累計獲得経験値を取得
     *
     * @return 累計獲得経験値を設定
     */
    public Long getExperienceValue() {
        return experienceValue;
    }

    /**
     * 累計獲得経験値を設定
     *
     * @param experienceValue 累計獲得経験値を設定
     */
    public void setExperienceValue(Long experienceValue) {
        this.experienceValue = experienceValue;
    }

    /**
     * 累計獲得経験値を設定
     *
     * @param experienceValue 累計獲得経験値を設定
     * @return this
     */
    public SetExperienceByUserIdRequest withExperienceValue(Long experienceValue) {
        setExperienceValue(experienceValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 累計獲得経験値を設定
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 累計獲得経験値を設定
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 累計獲得経験値を設定
     * @return this
     */
    public SetExperienceByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}