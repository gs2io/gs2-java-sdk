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
import io.gs2.control.Gs2BasicRequest;

/**
 * 暗号鍵を更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateKeyRequest extends Gs2BasicRequest<UpdateKeyRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 暗号鍵を更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 暗号鍵を更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 暗号鍵を更新
     * @return this
     */
    public UpdateKeyRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 暗号鍵名 */
    private String keyName;

    /**
     * 暗号鍵名を取得
     *
     * @return 暗号鍵を更新
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param keyName 暗号鍵を更新
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param keyName 暗号鍵を更新
     * @return this
     */
    public UpdateKeyRequest withKeyName(String keyName) {
        setKeyName(keyName);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return 暗号鍵を更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description 暗号鍵を更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description 暗号鍵を更新
     * @return this
     */
    public UpdateKeyRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

}