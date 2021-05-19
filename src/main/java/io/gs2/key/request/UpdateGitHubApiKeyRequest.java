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
 * GitHub のAPIキーを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateGitHubApiKeyRequest extends Gs2BasicRequest<UpdateGitHubApiKeyRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return GitHub のAPIキーを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GitHub のAPIキーを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName GitHub のAPIキーを更新
     * @return this
     */
    public UpdateGitHubApiKeyRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** GitHub APIキー名 */
    private String apiKeyName;

    /**
     * GitHub APIキー名を取得
     *
     * @return GitHub のAPIキーを更新
     */
    public String getApiKeyName() {
        return apiKeyName;
    }

    /**
     * GitHub APIキー名を設定
     *
     * @param apiKeyName GitHub のAPIキーを更新
     */
    public void setApiKeyName(String apiKeyName) {
        this.apiKeyName = apiKeyName;
    }

    /**
     * GitHub APIキー名を設定
     *
     * @param apiKeyName GitHub のAPIキーを更新
     * @return this
     */
    public UpdateGitHubApiKeyRequest withApiKeyName(String apiKeyName) {
        setApiKeyName(apiKeyName);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return GitHub のAPIキーを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description GitHub のAPIキーを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description GitHub のAPIキーを更新
     * @return this
     */
    public UpdateGitHubApiKeyRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** APIキー */
    private String apiKey;

    /**
     * APIキーを取得
     *
     * @return GitHub のAPIキーを更新
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * APIキーを設定
     *
     * @param apiKey GitHub のAPIキーを更新
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * APIキーを設定
     *
     * @param apiKey GitHub のAPIキーを更新
     * @return this
     */
    public UpdateGitHubApiKeyRequest withApiKey(String apiKey) {
        setApiKey(apiKey);
        return this;
    }

    /** APIキーの暗号化に使用する暗号鍵名 */
    private String encryptionKeyName;

    /**
     * APIキーの暗号化に使用する暗号鍵名を取得
     *
     * @return GitHub のAPIキーを更新
     */
    public String getEncryptionKeyName() {
        return encryptionKeyName;
    }

    /**
     * APIキーの暗号化に使用する暗号鍵名を設定
     *
     * @param encryptionKeyName GitHub のAPIキーを更新
     */
    public void setEncryptionKeyName(String encryptionKeyName) {
        this.encryptionKeyName = encryptionKeyName;
    }

    /**
     * APIキーの暗号化に使用する暗号鍵名を設定
     *
     * @param encryptionKeyName GitHub のAPIキーを更新
     * @return this
     */
    public UpdateGitHubApiKeyRequest withEncryptionKeyName(String encryptionKeyName) {
        setEncryptionKeyName(encryptionKeyName);
        return this;
    }

}