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

package io.gs2.inventory.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inventory.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 現在有効な所持品マスターを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCurrentItemModelMasterFromGitHubRequest extends Gs2BasicRequest<UpdateCurrentItemModelMasterFromGitHubRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 現在有効な所持品マスターを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 現在有効な所持品マスターを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 現在有効な所持品マスターを更新します
     * @return this
     */
    public UpdateCurrentItemModelMasterFromGitHubRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** GitHubからマスターデータをチェックアウトしてくる設定 */
    private GitHubCheckoutSetting checkoutSetting;

    /**
     * GitHubからマスターデータをチェックアウトしてくる設定を取得
     *
     * @return 現在有効な所持品マスターを更新します
     */
    public GitHubCheckoutSetting getCheckoutSetting() {
        return checkoutSetting;
    }

    /**
     * GitHubからマスターデータをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting 現在有効な所持品マスターを更新します
     */
    public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        this.checkoutSetting = checkoutSetting;
    }

    /**
     * GitHubからマスターデータをチェックアウトしてくる設定を設定
     *
     * @param checkoutSetting 現在有効な所持品マスターを更新します
     * @return this
     */
    public UpdateCurrentItemModelMasterFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
        setCheckoutSetting(checkoutSetting);
        return this;
    }

}