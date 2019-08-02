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
import io.gs2.control.Gs2BasicRequest;

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

}