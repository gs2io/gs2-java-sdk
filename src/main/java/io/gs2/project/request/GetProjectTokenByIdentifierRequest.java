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
 * プロジェクトトークンを発行します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetProjectTokenByIdentifierRequest extends Gs2BasicRequest<GetProjectTokenByIdentifierRequest> {

    /** GS2アカウントの名前 */
    private String accountName;

    /**
     * GS2アカウントの名前を取得
     *
     * @return プロジェクトトークンを発行します
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * GS2アカウントの名前を設定
     *
     * @param accountName プロジェクトトークンを発行します
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * GS2アカウントの名前を設定
     *
     * @param accountName プロジェクトトークンを発行します
     * @return this
     */
    public GetProjectTokenByIdentifierRequest withAccountName(String accountName) {
        setAccountName(accountName);
        return this;
    }

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
    public GetProjectTokenByIdentifierRequest withProjectName(String projectName) {
        setProjectName(projectName);
        return this;
    }

    /** ユーザ名 */
    private String userName;

    /**
     * ユーザ名を取得
     *
     * @return プロジェクトトークンを発行します
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名を設定
     *
     * @param userName プロジェクトトークンを発行します
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * ユーザ名を設定
     *
     * @param userName プロジェクトトークンを発行します
     * @return this
     */
    public GetProjectTokenByIdentifierRequest withUserName(String userName) {
        setUserName(userName);
        return this;
    }

    /** パスワード */
    private String password;

    /**
     * パスワードを取得
     *
     * @return プロジェクトトークンを発行します
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定
     *
     * @param password プロジェクトトークンを発行します
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを設定
     *
     * @param password プロジェクトトークンを発行します
     * @return this
     */
    public GetProjectTokenByIdentifierRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

}