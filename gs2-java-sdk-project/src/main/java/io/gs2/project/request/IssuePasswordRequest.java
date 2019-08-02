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
 * パスワードを再発行 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class IssuePasswordRequest extends Gs2BasicRequest<IssuePasswordRequest> {

    /** パスワードを再発行するために必要なトークン */
    private String issuePasswordToken;

    /**
     * パスワードを再発行するために必要なトークンを取得
     *
     * @return パスワードを再発行
     */
    public String getIssuePasswordToken() {
        return issuePasswordToken;
    }

    /**
     * パスワードを再発行するために必要なトークンを設定
     *
     * @param issuePasswordToken パスワードを再発行
     */
    public void setIssuePasswordToken(String issuePasswordToken) {
        this.issuePasswordToken = issuePasswordToken;
    }

    /**
     * パスワードを再発行するために必要なトークンを設定
     *
     * @param issuePasswordToken パスワードを再発行
     * @return this
     */
    public IssuePasswordRequest withIssuePasswordToken(String issuePasswordToken) {
        setIssuePasswordToken(issuePasswordToken);
        return this;
    }

}