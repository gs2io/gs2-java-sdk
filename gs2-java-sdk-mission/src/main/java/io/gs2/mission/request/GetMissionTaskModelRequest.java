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
 * ミッションタスクを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetMissionTaskModelRequest extends Gs2BasicRequest<GetMissionTaskModelRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ミッションタスクを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクを取得
     * @return this
     */
    public GetMissionTaskModelRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** グループ名 */
    private String missionGroupName;

    /**
     * グループ名を取得
     *
     * @return ミッションタスクを取得
     */
    public String getMissionGroupName() {
        return missionGroupName;
    }

    /**
     * グループ名を設定
     *
     * @param missionGroupName ミッションタスクを取得
     */
    public void setMissionGroupName(String missionGroupName) {
        this.missionGroupName = missionGroupName;
    }

    /**
     * グループ名を設定
     *
     * @param missionGroupName ミッションタスクを取得
     * @return this
     */
    public GetMissionTaskModelRequest withMissionGroupName(String missionGroupName) {
        setMissionGroupName(missionGroupName);
        return this;
    }

    /** タスク名 */
    private String missionTaskName;

    /**
     * タスク名を取得
     *
     * @return ミッションタスクを取得
     */
    public String getMissionTaskName() {
        return missionTaskName;
    }

    /**
     * タスク名を設定
     *
     * @param missionTaskName ミッションタスクを取得
     */
    public void setMissionTaskName(String missionTaskName) {
        this.missionTaskName = missionTaskName;
    }

    /**
     * タスク名を設定
     *
     * @param missionTaskName ミッションタスクを取得
     * @return this
     */
    public GetMissionTaskModelRequest withMissionTaskName(String missionTaskName) {
        setMissionTaskName(missionTaskName);
        return this;
    }

}