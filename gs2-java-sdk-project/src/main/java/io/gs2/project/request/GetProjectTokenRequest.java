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
 * プロジェクトトークンを発行します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetProjectTokenRequest extends Gs2BasicRequest<GetProjectTokenRequest> {

    /** プロジェクト名 */
    private String projectName;

    /**
     * プロジェクト名を取得
     *
     * @return プロジェクトトークンを発行します
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定
     *
     * @param projectName プロジェクトトークンを発行します
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * プロジェクト名を設定
     *
     * @param projectName プロジェクトトークンを発行します
     * @return this
     */
    public GetProjectTokenRequest withProjectName(String projectName) {
        setProjectName(projectName);
        return this;
    }

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return プロジェクトトークンを発行します
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken プロジェクトトークンを発行します
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken プロジェクトトークンを発行します
     * @return this
     */
    public GetProjectTokenRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

}