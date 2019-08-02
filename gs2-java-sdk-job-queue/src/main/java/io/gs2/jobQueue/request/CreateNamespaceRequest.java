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

package io.gs2.jobQueue.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.jobQueue.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ジョブキューにジョブが登録されたときののプッシュ通知 */
    private NotificationSetting pushNotification;

    /**
     * ジョブキューにジョブが登録されたときののプッシュ通知を取得
     *
     * @return ネームスペースを新規作成
     */
    public NotificationSetting getPushNotification() {
        return pushNotification;
    }

    /**
     * ジョブキューにジョブが登録されたときののプッシュ通知を設定
     *
     * @param pushNotification ネームスペースを新規作成
     */
    public void setPushNotification(NotificationSetting pushNotification) {
        this.pushNotification = pushNotification;
    }

    /**
     * ジョブキューにジョブが登録されたときののプッシュ通知を設定
     *
     * @param pushNotification ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withPushNotification(NotificationSetting pushNotification) {
        setPushNotification(pushNotification);
        return this;
    }

}