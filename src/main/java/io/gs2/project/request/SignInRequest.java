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
 * サインインします のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SignInRequest extends Gs2BasicRequest<SignInRequest> {

    /** メールアドレス */
    private String email;

    /**
     * メールアドレスを取得
     *
     * @return サインインします
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email サインインします
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email サインインします
     * @return this
     */
    public SignInRequest withEmail(String email) {
        setEmail(email);
        return this;
    }

    /** パスワード */
    private String password;

    /**
     * パスワードを取得
     *
     * @return サインインします
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定
     *
     * @param password サインインします
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを設定
     *
     * @param password サインインします
     * @return this
     */
    public SignInRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

}