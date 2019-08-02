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
 * プロジェクトを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateProjectRequest extends Gs2BasicRequest<CreateProjectRequest> {

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return プロジェクトを新規作成
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken プロジェクトを新規作成
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken プロジェクトを新規作成
     * @return this
     */
    public CreateProjectRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

    /** プロジェクト名 */
    private String name;

    /**
     * プロジェクト名を取得
     *
     * @return プロジェクトを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * プロジェクト名を設定
     *
     * @param name プロジェクトを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * プロジェクト名を設定
     *
     * @param name プロジェクトを新規作成
     * @return this
     */
    public CreateProjectRequest withName(String name) {
        setName(name);
        return this;
    }

    /** プロジェクトの説明 */
    private String description;

    /**
     * プロジェクトの説明を取得
     *
     * @return プロジェクトを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * プロジェクトの説明を設定
     *
     * @param description プロジェクトを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * プロジェクトの説明を設定
     *
     * @param description プロジェクトを新規作成
     * @return this
     */
    public CreateProjectRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

}