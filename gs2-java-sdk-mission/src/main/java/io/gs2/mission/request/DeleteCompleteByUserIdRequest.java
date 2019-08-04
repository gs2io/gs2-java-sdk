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

package io.gs2.mission.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.mission.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 達成状況を削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteCompleteByUserIdRequest extends Gs2BasicRequest<DeleteCompleteByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 達成状況を削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 達成状況を削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 達成状況を削除
     * @return this
     */
    public DeleteCompleteByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 達成状況を削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 達成状況を削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 達成状況を削除
     * @return this
     */
    public DeleteCompleteByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** ミッショングループ名 */
    private String missionGroupName;

    /**
     * ミッショングループ名を取得
     *
     * @return 達成状況を削除
     */
    public String getMissionGroupName() {
        return missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName 達成状況を削除
     */
    public void setMissionGroupName(String missionGroupName) {
        this.missionGroupName = missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName 達成状況を削除
     * @return this
     */
    public DeleteCompleteByUserIdRequest withMissionGroupName(String missionGroupName) {
        setMissionGroupName(missionGroupName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 達成状況を削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 達成状況を削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 達成状況を削除
     * @return this
     */
    public DeleteCompleteByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}