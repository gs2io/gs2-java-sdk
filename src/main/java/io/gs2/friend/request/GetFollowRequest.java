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

package io.gs2.friend.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.friend.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * フォローを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetFollowRequest extends Gs2BasicRequest<GetFollowRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return フォローを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォローを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォローを取得
     * @return this
     */
    public GetFollowRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォローされているユーザID */
    private String targetUserId;

    /**
     * フォローされているユーザIDを取得
     *
     * @return フォローを取得
     */
    public String getTargetUserId() {
        return targetUserId;
    }

    /**
     * フォローされているユーザIDを設定
     *
     * @param targetUserId フォローを取得
     */
    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    /**
     * フォローされているユーザIDを設定
     *
     * @param targetUserId フォローを取得
     * @return this
     */
    public GetFollowRequest withTargetUserId(String targetUserId) {
        setTargetUserId(targetUserId);
        return this;
    }

    /** プロフィールも一緒に取得するか */
    private Boolean withProfile;

    /**
     * プロフィールも一緒に取得するかを取得
     *
     * @return フォローを取得
     */
    public Boolean getWithProfile() {
        return withProfile;
    }

    /**
     * プロフィールも一緒に取得するかを設定
     *
     * @param withProfile フォローを取得
     */
    public void setWithProfile(Boolean withProfile) {
        this.withProfile = withProfile;
    }

    /**
     * プロフィールも一緒に取得するかを設定
     *
     * @param withProfile フォローを取得
     * @return this
     */
    public GetFollowRequest withWithProfile(Boolean withProfile) {
        setWithProfile(withProfile);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return フォローを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider フォローを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider フォローを取得
     * @return this
     */
    public GetFollowRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public GetFollowRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}