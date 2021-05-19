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

package io.gs2.realtime.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.realtime.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ルームの作成依頼。 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class WantRoomRequest extends Gs2BasicRequest<WantRoomRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ルームの作成依頼。
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームの作成依頼。
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームの作成依頼。
     * @return this
     */
    public WantRoomRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String name;

    /**
     * ルーム名を取得
     *
     * @return ルームの作成依頼。
     */
    public String getName() {
        return name;
    }

    /**
     * ルーム名を設定
     *
     * @param name ルームの作成依頼。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ルーム名を設定
     *
     * @param name ルームの作成依頼。
     * @return this
     */
    public WantRoomRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ルームの作成が終わったときに通知を受けるユーザIDリスト */
    private List<String> notificationUserIds;

    /**
     * ルームの作成が終わったときに通知を受けるユーザIDリストを取得
     *
     * @return ルームの作成依頼。
     */
    public List<String> getNotificationUserIds() {
        return notificationUserIds;
    }

    /**
     * ルームの作成が終わったときに通知を受けるユーザIDリストを設定
     *
     * @param notificationUserIds ルームの作成依頼。
     */
    public void setNotificationUserIds(List<String> notificationUserIds) {
        this.notificationUserIds = notificationUserIds;
    }

    /**
     * ルームの作成が終わったときに通知を受けるユーザIDリストを設定
     *
     * @param notificationUserIds ルームの作成依頼。
     * @return this
     */
    public WantRoomRequest withNotificationUserIds(List<String> notificationUserIds) {
        setNotificationUserIds(notificationUserIds);
        return this;
    }

}