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
 * アカウントを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateAccountRequest extends Gs2BasicRequest<CreateAccountRequest> {

    /** メールアドレス */
    private String email;

    /**
     * メールアドレスを取得
     *
     * @return アカウントを新規作成
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email アカウントを新規作成
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email アカウントを新規作成
     * @return this
     */
    public CreateAccountRequest withEmail(String email) {
        setEmail(email);
        return this;
    }

    /** フルネーム */
    private String fullName;

    /**
     * フルネームを取得
     *
     * @return アカウントを新規作成
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * フルネームを設定
     *
     * @param fullName アカウントを新規作成
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * フルネームを設定
     *
     * @param fullName アカウントを新規作成
     * @return this
     */
    public CreateAccountRequest withFullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    /** 会社名 */
    private String companyName;

    /**
     * 会社名を取得
     *
     * @return アカウントを新規作成
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 会社名を設定
     *
     * @param companyName アカウントを新規作成
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 会社名を設定
     *
     * @param companyName アカウントを新規作成
     * @return this
     */
    public CreateAccountRequest withCompanyName(String companyName) {
        setCompanyName(companyName);
        return this;
    }

    /** パスワード */
    private String password;

    /**
     * パスワードを取得
     *
     * @return アカウントを新規作成
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定
     *
     * @param password アカウントを新規作成
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを設定
     *
     * @param password アカウントを新規作成
     * @return this
     */
    public CreateAccountRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

}