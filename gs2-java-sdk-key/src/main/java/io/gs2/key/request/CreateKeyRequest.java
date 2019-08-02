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
 * 暗号鍵を新規作成します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateKeyRequest extends Gs2BasicRequest<CreateKeyRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 暗号鍵を新規作成します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 暗号鍵を新規作成します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 暗号鍵を新規作成します
     * @return this
     */
    public CreateKeyRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 暗号鍵名 */
    private String name;

    /**
     * 暗号鍵名を取得
     *
     * @return 暗号鍵を新規作成します
     */
    public String getName() {
        return name;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param name 暗号鍵を新規作成します
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param name 暗号鍵を新規作成します
     * @return this
     */
    public CreateKeyRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return 暗号鍵を新規作成します
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description 暗号鍵を新規作成します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description 暗号鍵を新規作成します
     * @return this
     */
    public CreateKeyRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

}