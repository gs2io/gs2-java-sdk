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
 * パスワード再発行トークンを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ForgetRequest extends Gs2BasicRequest<ForgetRequest> {

    /** メールアドレス */
    private String email;

    /**
     * メールアドレスを取得
     *
     * @return パスワード再発行トークンを取得
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email パスワード再発行トークンを取得
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * メールアドレスを設定
     *
     * @param email パスワード再発行トークンを取得
     * @return this
     */
    public ForgetRequest withEmail(String email) {
        setEmail(email);
        return this;
    }

}