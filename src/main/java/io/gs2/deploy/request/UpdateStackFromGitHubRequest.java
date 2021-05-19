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

package io.gs2.deploy.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.deploy.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スタックを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateStackFromGitHubRequest extends Gs2BasicRequest<UpdateStackFromGitHubRequest> {

    /** スタック名 */
    private String stackName;

    /**
     * スタック名を取得
     *
     * @return スタックを更新
     */
    public String getStackName() {
        return stackName;
    }

    /**
     * スタック名を設定
     *
     * @param stackName スタックを更新
     */
    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    /**
     * スタック名を設定
     *
     * @param stackName スタックを更新
     * @return this
     */
    public UpdateStackFromGitHubRequest withStackName(String stackName) {
        setStackName(stackName);
        return this;
    }

    /** スタックの説明 */
    private String description;

    /**
     * スタックの説明を取得
     *
     * @return スタックを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタックの説明を設定
     *
     * @param description スタックを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタックの説明を設定
     *
     * @param description スタックを更新
     * @return this
     */
    public UpdateStackFromGitHubRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** GitHubからソースコードをチェックアウトしてくる設定 */
    private GitHubCheckoutSetting checkoutSetting;

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を取得
     *
     * @return スタックを更新
     */
    public GitHubCheckoutSetting getCheckoutSetting() {
        return checkoutSetting;
    }

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting スタックを更新
     */
    public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        this.checkoutSetting = checkoutSetting;
    }

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting スタックを更新
     * @return this
     */
    public UpdateStackFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        setCheckoutSetting(checkoutSetting);
        return this;
    }

}