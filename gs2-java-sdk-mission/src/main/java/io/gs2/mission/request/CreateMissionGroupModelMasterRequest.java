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
 * ミッショングループマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateMissionGroupModelMasterRequest extends Gs2BasicRequest<CreateMissionGroupModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ミッショングループマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッショングループマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッショングループマスターを新規作成
     * @return this
     */
    public CreateMissionGroupModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ミッショングループ名 */
    private String name;

    /**
     * ミッショングループ名を取得
     *
     * @return ミッショングループマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param name ミッショングループマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param name ミッショングループマスターを新規作成
     * @return this
     */
    public CreateMissionGroupModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ミッショングループマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッショングループマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッショングループマスターを新規作成
     * @return this
     */
    public CreateMissionGroupModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** ミッショングループの説明 */
    private String description;

    /**
     * ミッショングループの説明を取得
     *
     * @return ミッショングループマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ミッショングループの説明を設定
     *
     * @param description ミッショングループマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ミッショングループの説明を設定
     *
     * @param description ミッショングループマスターを新規作成
     * @return this
     */
    public CreateMissionGroupModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ミッションを達成したときの通知先ネームスペース のGRN */
    private String completeNotificationNamespaceId;

    /**
     * ミッションを達成したときの通知先ネームスペース のGRNを取得
     *
     * @return ミッショングループマスターを新規作成
     */
    public String getCompleteNotificationNamespaceId() {
        return completeNotificationNamespaceId;
    }

    /**
     * ミッションを達成したときの通知先ネームスペース のGRNを設定
     *
     * @param completeNotificationNamespaceId ミッショングループマスターを新規作成
     */
    public void setCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
        this.completeNotificationNamespaceId = completeNotificationNamespaceId;
    }

    /**
     * ミッションを達成したときの通知先ネームスペース のGRNを設定
     *
     * @param completeNotificationNamespaceId ミッショングループマスターを新規作成
     * @return this
     */
    public CreateMissionGroupModelMasterRequest withCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
        setCompleteNotificationNamespaceId(completeNotificationNamespaceId);
        return this;
    }

}