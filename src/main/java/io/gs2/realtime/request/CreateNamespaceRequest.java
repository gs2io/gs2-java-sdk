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

    /** サーバの種類 */
    private String serverType;

    /**
     * サーバの種類を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getServerType() {
        return serverType;
    }

    /**
     * サーバの種類を設定
     *
     * @param serverType ネームスペースを新規作成
     */
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    /**
     * サーバの種類を設定
     *
     * @param serverType ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withServerType(String serverType) {
        setServerType(serverType);
        return this;
    }

    /** サーバのスペック */
    private String serverSpec;

    /**
     * サーバのスペックを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getServerSpec() {
        return serverSpec;
    }

    /**
     * サーバのスペックを設定
     *
     * @param serverSpec ネームスペースを新規作成
     */
    public void setServerSpec(String serverSpec) {
        this.serverSpec = serverSpec;
    }

    /**
     * サーバのスペックを設定
     *
     * @param serverSpec ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withServerSpec(String serverSpec) {
        setServerSpec(serverSpec);
        return this;
    }

    /** ルームの作成が終わったときのプッシュ通知 */
    private NotificationSetting createNotification;

    /**
     * ルームの作成が終わったときのプッシュ通知を取得
     *
     * @return ネームスペースを新規作成
     */
    public NotificationSetting getCreateNotification() {
        return createNotification;
    }

    /**
     * ルームの作成が終わったときのプッシュ通知を設定
     *
     * @param createNotification ネームスペースを新規作成
     */
    public void setCreateNotification(NotificationSetting createNotification) {
        this.createNotification = createNotification;
    }

    /**
     * ルームの作成が終わったときのプッシュ通知を設定
     *
     * @param createNotification ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateNotification(NotificationSetting createNotification) {
        setCreateNotification(createNotification);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを新規作成
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}