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
 * GS2アカウントを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateAccountRequest extends Gs2BasicRequest<UpdateAccountRequest> {

    /** メールアドレス */
    private String email;

    /**
     * メールアドレスを取得
     *
     * @return GS2アカウントを更新します
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email GS2アカウントを更新します
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email GS2アカウントを更新します
     * @return this
     */
    public UpdateAccountRequest withEmail(String email) {
        setEmail(email);
        return this;
    }

    /** フルネーム */
    private String fullName;

    /**
     * フルネームを取得
     *
     * @return GS2アカウントを更新します
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * フルネームを設定
     *
     * @param fullName GS2アカウントを更新します
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * フルネームを設定
     *
     * @param fullName GS2アカウントを更新します
     * @return this
     */
    public UpdateAccountRequest withFullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    /** 会社名 */
    private String companyName;

    /**
     * 会社名を取得
     *
     * @return GS2アカウントを更新します
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 会社名を設定
     *
     * @param companyName GS2アカウントを更新します
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 会社名を設定
     *
     * @param companyName GS2アカウントを更新します
     * @return this
     */
    public UpdateAccountRequest withCompanyName(String companyName) {
        setCompanyName(companyName);
        return this;
    }

    /** パスワード */
    private String password;

    /**
     * パスワードを取得
     *
     * @return GS2アカウントを更新します
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定
     *
     * @param password GS2アカウントを更新します
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを設定
     *
     * @param password GS2アカウントを更新します
     * @return this
     */
    public UpdateAccountRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return GS2アカウントを更新します
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken GS2アカウントを更新します
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken GS2アカウントを更新します
     * @return this
     */
    public UpdateAccountRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

}