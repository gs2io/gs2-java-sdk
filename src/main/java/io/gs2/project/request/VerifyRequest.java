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
 * GS2アカウントを有効化します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class VerifyRequest extends Gs2BasicRequest<VerifyRequest> {

    /** 有効化に使用するトークン */
    private String verifyToken;

    /**
     * 有効化に使用するトークンを取得
     *
     * @return GS2アカウントを有効化します
     */
    public String getVerifyToken() {
        return verifyToken;
    }

    /**
     * 有効化に使用するトークンを設定
     *
     * @param verifyToken GS2アカウントを有効化します
     */
    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }

    /**
     * 有効化に使用するトークンを設定
     *
     * @param verifyToken GS2アカウントを有効化します
     * @return this
     */
    public VerifyRequest withVerifyToken(String verifyToken) {
        setVerifyToken(verifyToken);
        return this;
    }

}