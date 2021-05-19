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

package io.gs2.script.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.script.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * GithHub をデータソースとしてスクリプトを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateScriptFromGitHubRequest extends Gs2BasicRequest<UpdateScriptFromGitHubRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return GithHub をデータソースとしてスクリプトを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GithHub をデータソースとしてスクリプトを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GithHub をデータソースとしてスクリプトを更新します
     * @return this
     */
    public UpdateScriptFromGitHubRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スクリプト名 */
    private String scriptName;

    /**
     * スクリプト名を取得
     *
     * @return GithHub をデータソースとしてスクリプトを更新します
     */
    public String getScriptName() {
        return scriptName;
    }

    /**
     * スクリプト名を設定
     *
     * @param scriptName GithHub をデータソースとしてスクリプトを更新します
     */
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * スクリプト名を設定
     *
     * @param scriptName GithHub をデータソースとしてスクリプトを更新します
     * @return this
     */
    public UpdateScriptFromGitHubRequest withScriptName(String scriptName) {
        setScriptName(scriptName);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return GithHub をデータソースとしてスクリプトを更新します
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description GithHub をデータソースとしてスクリプトを更新します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description GithHub をデータソースとしてスクリプトを更新します
     * @return this
     */
    public UpdateScriptFromGitHubRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** GitHubからソースコードをチェックアウトしてくる設定 */
    private GitHubCheckoutSetting checkoutSetting;

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を取得
     *
     * @return GithHub をデータソースとしてスクリプトを更新します
     */
    public GitHubCheckoutSetting getCheckoutSetting() {
        return checkoutSetting;
    }

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting GithHub をデータソースとしてスクリプトを更新します
     */
    public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        this.checkoutSetting = checkoutSetting;
    }

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting GithHub をデータソースとしてスクリプトを更新します
     * @return this
     */
    public UpdateScriptFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        setCheckoutSetting(checkoutSetting);
        return this;
    }

}