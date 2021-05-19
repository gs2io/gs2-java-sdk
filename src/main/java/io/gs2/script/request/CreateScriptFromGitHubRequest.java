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
 * GitHubリポジトリのコードからスクリプトを新規作成します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateScriptFromGitHubRequest extends Gs2BasicRequest<CreateScriptFromGitHubRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GitHubリポジトリのコードからスクリプトを新規作成します
     * @return this
     */
    public CreateScriptFromGitHubRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スクリプト名 */
    private String name;

    /**
     * スクリプト名を取得
     *
     * @return GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public String getName() {
        return name;
    }

    /**
     * スクリプト名を設定
     *
     * @param name GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * スクリプト名を設定
     *
     * @param name GitHubリポジトリのコードからスクリプトを新規作成します
     * @return this
     */
    public CreateScriptFromGitHubRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description GitHubリポジトリのコードからスクリプトを新規作成します
     * @return this
     */
    public CreateScriptFromGitHubRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** GitHubからソースコードをチェックアウトしてくる設定 */
    private GitHubCheckoutSetting checkoutSetting;

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を取得
     *
     * @return GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public GitHubCheckoutSetting getCheckoutSetting() {
        return checkoutSetting;
    }

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting GitHubリポジトリのコードからスクリプトを新規作成します
     */
    public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        this.checkoutSetting = checkoutSetting;
    }

    /**
     * GitHubからソースコードをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting GitHubリポジトリのコードからスクリプトを新規作成します
     * @return this
     */
    public CreateScriptFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        setCheckoutSetting(checkoutSetting);
        return this;
    }

}