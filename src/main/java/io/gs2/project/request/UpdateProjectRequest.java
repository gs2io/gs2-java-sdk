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

package io.gs2.project.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.project.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * プロジェクトを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateProjectRequest extends Gs2BasicRequest<UpdateProjectRequest> {

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return プロジェクトを更新
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken プロジェクトを更新
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

    /** プロジェクト名 */
    private String projectName;

    /**
     * プロジェクト名を取得
     *
     * @return プロジェクトを更新
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定
     *
     * @param projectName プロジェクトを更新
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * プロジェクト名を設定
     *
     * @param projectName プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withProjectName(String projectName) {
        setProjectName(projectName);
        return this;
    }

    /** プロジェクトの説明 */
    private String description;

    /**
     * プロジェクトの説明を取得
     *
     * @return プロジェクトを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * プロジェクトの説明を設定
     *
     * @param description プロジェクトを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * プロジェクトの説明を設定
     *
     * @param description プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 契約プラン */
    private String plan;

    /**
     * 契約プランを取得
     *
     * @return プロジェクトを更新
     */
    public String getPlan() {
        return plan;
    }

    /**
     * 契約プランを設定
     *
     * @param plan プロジェクトを更新
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * 契約プランを設定
     *
     * @param plan プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withPlan(String plan) {
        setPlan(plan);
        return this;
    }

    /** 支払い方法名 */
    private String billingMethodName;

    /**
     * 支払い方法名を取得
     *
     * @return プロジェクトを更新
     */
    public String getBillingMethodName() {
        return billingMethodName;
    }

    /**
     * 支払い方法名を設定
     *
     * @param billingMethodName プロジェクトを更新
     */
    public void setBillingMethodName(String billingMethodName) {
        this.billingMethodName = billingMethodName;
    }

    /**
     * 支払い方法名を設定
     *
     * @param billingMethodName プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withBillingMethodName(String billingMethodName) {
        setBillingMethodName(billingMethodName);
        return this;
    }

    /** AWS EventBridge の設定 */
    private String enableEventBridge;

    /**
     * AWS EventBridge の設定を取得
     *
     * @return プロジェクトを更新
     */
    public String getEnableEventBridge() {
        return enableEventBridge;
    }

    /**
     * AWS EventBridge の設定を設定
     *
     * @param enableEventBridge プロジェクトを更新
     */
    public void setEnableEventBridge(String enableEventBridge) {
        this.enableEventBridge = enableEventBridge;
    }

    /**
     * AWS EventBridge の設定を設定
     *
     * @param enableEventBridge プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withEnableEventBridge(String enableEventBridge) {
        setEnableEventBridge(enableEventBridge);
        return this;
    }

    /** 通知に使用するAWSアカウントのID */
    private String eventBridgeAwsAccountId;

    /**
     * 通知に使用するAWSアカウントのIDを取得
     *
     * @return プロジェクトを更新
     */
    public String getEventBridgeAwsAccountId() {
        return eventBridgeAwsAccountId;
    }

    /**
     * 通知に使用するAWSアカウントのIDを設定
     *
     * @param eventBridgeAwsAccountId プロジェクトを更新
     */
    public void setEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
        this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
    }

    /**
     * 通知に使用するAWSアカウントのIDを設定
     *
     * @param eventBridgeAwsAccountId プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
        setEventBridgeAwsAccountId(eventBridgeAwsAccountId);
        return this;
    }

    /** 通知に使用するAWSリージョン */
    private String eventBridgeAwsRegion;

    /**
     * 通知に使用するAWSリージョンを取得
     *
     * @return プロジェクトを更新
     */
    public String getEventBridgeAwsRegion() {
        return eventBridgeAwsRegion;
    }

    /**
     * 通知に使用するAWSリージョンを設定
     *
     * @param eventBridgeAwsRegion プロジェクトを更新
     */
    public void setEventBridgeAwsRegion(String eventBridgeAwsRegion) {
        this.eventBridgeAwsRegion = eventBridgeAwsRegion;
    }

    /**
     * 通知に使用するAWSリージョンを設定
     *
     * @param eventBridgeAwsRegion プロジェクトを更新
     * @return this
     */
    public UpdateProjectRequest withEventBridgeAwsRegion(String eventBridgeAwsRegion) {
        setEventBridgeAwsRegion(eventBridgeAwsRegion);
        return this;
    }

}