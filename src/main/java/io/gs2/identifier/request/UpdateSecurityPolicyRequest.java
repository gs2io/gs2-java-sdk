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
 * セキュリティポリシーを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateSecurityPolicyRequest extends Gs2BasicRequest<UpdateSecurityPolicyRequest> {

    /** セキュリティポリシー名 */
    private String securityPolicyName;

    /**
     * セキュリティポリシー名を取得
     *
     * @return セキュリティポリシーを更新します
     */
    public String getSecurityPolicyName() {
        return securityPolicyName;
    }

    /**
     * セキュリティポリシー名を設定
     *
     * @param securityPolicyName セキュリティポリシーを更新します
     */
    public void setSecurityPolicyName(String securityPolicyName) {
        this.securityPolicyName = securityPolicyName;
    }

    /**
     * セキュリティポリシー名を設定
     *
     * @param securityPolicyName セキュリティポリシーを更新します
     * @return this
     */
    public UpdateSecurityPolicyRequest withSecurityPolicyName(String securityPolicyName) {
        setSecurityPolicyName(securityPolicyName);
        return this;
    }

    /** セキュリティポリシーの説明 */
    private String description;

    /**
     * セキュリティポリシーの説明を取得
     *
     * @return セキュリティポリシーを更新します
     */
    public String getDescription() {
        return description;
    }

    /**
     * セキュリティポリシーの説明を設定
     *
     * @param description セキュリティポリシーを更新します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * セキュリティポリシーの説明を設定
     *
     * @param description セキュリティポリシーを更新します
     * @return this
     */
    public UpdateSecurityPolicyRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ポリシードキュメント */
    private String policy;

    /**
     * ポリシードキュメントを取得
     *
     * @return セキュリティポリシーを更新します
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * ポリシードキュメントを設定
     *
     * @param policy セキュリティポリシーを更新します
     */
    public void setPolicy(String policy) {
        this.policy = policy;
    }

    /**
     * ポリシードキュメントを設定
     *
     * @param policy セキュリティポリシーを更新します
     * @return this
     */
    public UpdateSecurityPolicyRequest withPolicy(String policy) {
        setPolicy(policy);
        return this;
    }

}