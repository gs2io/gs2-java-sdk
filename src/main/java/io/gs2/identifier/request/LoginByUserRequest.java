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

package io.gs2.identifier.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.identifier.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * プロジェクトトークン を取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class LoginByUserRequest extends Gs2BasicRequest<LoginByUserRequest> {

    /** GS2-Identifier のユーザ名 */
    private String userName;

    /**
     * GS2-Identifier のユーザ名を取得
     *
     * @return プロジェクトトークン を取得します
     */
    public String getUserName() {
        return userName;
    }

    /**
     * GS2-Identifier のユーザ名を設定
     *
     * @param userName プロジェクトトークン を取得します
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * GS2-Identifier のユーザ名を設定
     *
     * @param userName プロジェクトトークン を取得します
     * @return this
     */
    public LoginByUserRequest withUserName(String userName) {
        setUserName(userName);
        return this;
    }

    /** GS2-Identifier のユーザのパスワード */
    private String password;

    /**
     * GS2-Identifier のユーザのパスワードを取得
     *
     * @return プロジェクトトークン を取得します
     */
    public String getPassword() {
        return password;
    }

    /**
     * GS2-Identifier のユーザのパスワードを設定
     *
     * @param password プロジェクトトークン を取得します
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * GS2-Identifier のユーザのパスワードを設定
     *
     * @param password プロジェクトトークン を取得します
     * @return this
     */
    public LoginByUserRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

}