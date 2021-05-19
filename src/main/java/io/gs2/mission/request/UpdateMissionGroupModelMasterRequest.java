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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ミッショングループマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateMissionGroupModelMasterRequest extends Gs2BasicRequest<UpdateMissionGroupModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッショングループマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ミッショングループ名 */
    private String missionGroupName;

    /**
     * ミッショングループ名を取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getMissionGroupName() {
        return missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッショングループマスターを更新
     */
    public void setMissionGroupName(String missionGroupName) {
        this.missionGroupName = missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withMissionGroupName(String missionGroupName) {
        setMissionGroupName(missionGroupName);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッショングループマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** ミッショングループの説明 */
    private String description;

    /**
     * ミッショングループの説明を取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ミッショングループの説明を設定
     *
     * @param description ミッショングループマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ミッショングループの説明を設定
     *
     * @param description ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** リセットタイミング */
    private String resetType;

    /**
     * リセットタイミングを取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * リセットタイミングを設定
     *
     * @param resetType ミッショングループマスターを更新
     */
    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    /**
     * リセットタイミングを設定
     *
     * @param resetType ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withResetType(String resetType) {
        setResetType(resetType);
        return this;
    }

    /** リセットをする日にち */
    private Integer resetDayOfMonth;

    /**
     * リセットをする日にちを取得
     *
     * @return ミッショングループマスターを更新
     */
    public Integer getResetDayOfMonth() {
        return resetDayOfMonth;
    }

    /**
     * リセットをする日にちを設定
     *
     * @param resetDayOfMonth ミッショングループマスターを更新
     */
    public void setResetDayOfMonth(Integer resetDayOfMonth) {
        this.resetDayOfMonth = resetDayOfMonth;
    }

    /**
     * リセットをする日にちを設定
     *
     * @param resetDayOfMonth ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withResetDayOfMonth(Integer resetDayOfMonth) {
        setResetDayOfMonth(resetDayOfMonth);
        return this;
    }

    /** リセットする曜日 */
    private String resetDayOfWeek;

    /**
     * リセットする曜日を取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getResetDayOfWeek() {
        return resetDayOfWeek;
    }

    /**
     * リセットする曜日を設定
     *
     * @param resetDayOfWeek ミッショングループマスターを更新
     */
    public void setResetDayOfWeek(String resetDayOfWeek) {
        this.resetDayOfWeek = resetDayOfWeek;
    }

    /**
     * リセットする曜日を設定
     *
     * @param resetDayOfWeek ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withResetDayOfWeek(String resetDayOfWeek) {
        setResetDayOfWeek(resetDayOfWeek);
        return this;
    }

    /** リセット時刻 */
    private Integer resetHour;

    /**
     * リセット時刻を取得
     *
     * @return ミッショングループマスターを更新
     */
    public Integer getResetHour() {
        return resetHour;
    }

    /**
     * リセット時刻を設定
     *
     * @param resetHour ミッショングループマスターを更新
     */
    public void setResetHour(Integer resetHour) {
        this.resetHour = resetHour;
    }

    /**
     * リセット時刻を設定
     *
     * @param resetHour ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withResetHour(Integer resetHour) {
        setResetHour(resetHour);
        return this;
    }

    /** ミッションを達成したときの通知先ネームスペース のGRN */
    private String completeNotificationNamespaceId;

    /**
     * ミッションを達成したときの通知先ネームスペース のGRNを取得
     *
     * @return ミッショングループマスターを更新
     */
    public String getCompleteNotificationNamespaceId() {
        return completeNotificationNamespaceId;
    }

    /**
     * ミッションを達成したときの通知先ネームスペース のGRNを設定
     *
     * @param completeNotificationNamespaceId ミッショングループマスターを更新
     */
    public void setCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
        this.completeNotificationNamespaceId = completeNotificationNamespaceId;
    }

    /**
     * ミッションを達成したときの通知先ネームスペース のGRNを設定
     *
     * @param completeNotificationNamespaceId ミッショングループマスターを更新
     * @return this
     */
    public UpdateMissionGroupModelMasterRequest withCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
        setCompleteNotificationNamespaceId(completeNotificationNamespaceId);
        return this;
    }

}