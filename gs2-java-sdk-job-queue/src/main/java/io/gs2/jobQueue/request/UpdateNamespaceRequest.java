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
 * ネームスペースを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ジョブキューにジョブが登録されたときののプッシュ通知 */
    private NotificationSetting pushNotification;

    /**
     * ジョブキューにジョブが登録されたときののプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getPushNotification() {
        return pushNotification;
    }

    /**
     * ジョブキューにジョブが登録されたときののプッシュ通知を設定
     *
     * @param pushNotification ネームスペースを更新
     */
    public void setPushNotification(NotificationSetting pushNotification) {
        this.pushNotification = pushNotification;
    }

    /**
     * ジョブキューにジョブが登録されたときののプッシュ通知を設定
     *
     * @param pushNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withPushNotification(NotificationSetting pushNotification) {
        setPushNotification(pushNotification);
        return this;
    }

}