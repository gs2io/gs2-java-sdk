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
 * ミッションタスクマスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteMissionTaskModelMasterRequest extends Gs2BasicRequest<DeleteMissionTaskModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ミッションタスクマスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクマスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクマスターを削除
     * @return this
     */
    public DeleteMissionTaskModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ミッショングループ名 */
    private String missionGroupName;

    /**
     * ミッショングループ名を取得
     *
     * @return ミッションタスクマスターを削除
     */
    public String getMissionGroupName() {
        return missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッションタスクマスターを削除
     */
    public void setMissionGroupName(String missionGroupName) {
        this.missionGroupName = missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッションタスクマスターを削除
     * @return this
     */
    public DeleteMissionTaskModelMasterRequest withMissionGroupName(String missionGroupName) {
        setMissionGroupName(missionGroupName);
        return this;
    }

    /** タスク名 */
    private String missionTaskName;

    /**
     * タスク名を取得
     *
     * @return ミッションタスクマスターを削除
     */
    public String getMissionTaskName() {
        return missionTaskName;
    }

    /**
     * タスク名を設定
     *
     * @param missionTaskName ミッションタスクマスターを削除
     */
    public void setMissionTaskName(String missionTaskName) {
        this.missionTaskName = missionTaskName;
    }

    /**
     * タスク名を設定
     *
     * @param missionTaskName ミッションタスクマスターを削除
     * @return this
     */
    public DeleteMissionTaskModelMasterRequest withMissionTaskName(String missionTaskName) {
        setMissionTaskName(missionTaskName);
        return this;
    }

}