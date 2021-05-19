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

package io.gs2.key.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.key.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * GitHub のAPIキーを削除します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteGitHubApiKeyRequest extends Gs2BasicRequest<DeleteGitHubApiKeyRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return GitHub のAPIキーを削除します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GitHub のAPIキーを削除します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GitHub のAPIキーを削除します
     * @return this
     */
    public DeleteGitHubApiKeyRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** GitHub APIキー名 */
    private String apiKeyName;

    /**
     * GitHub APIキー名を取得
     *
     * @return GitHub のAPIキーを削除します
     */
    public String getApiKeyName() {
        return apiKeyName;
    }

    /**
     * GitHub APIキー名を設定
     *
     * @param apiKeyName GitHub のAPIキーを削除します
     */
    public void setApiKeyName(String apiKeyName) {
        this.apiKeyName = apiKeyName;
    }

    /**
     * GitHub APIキー名を設定
     *
     * @param apiKeyName GitHub のAPIキーを削除します
     * @return this
     */
    public DeleteGitHubApiKeyRequest withApiKeyName(String apiKeyName) {
        setApiKeyName(apiKeyName);
        return this;
    }

}