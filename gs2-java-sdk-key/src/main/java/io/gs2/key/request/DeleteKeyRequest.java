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
 * 暗号鍵を削除します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteKeyRequest extends Gs2BasicRequest<DeleteKeyRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 暗号鍵を削除します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 暗号鍵を削除します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 暗号鍵を削除します
     * @return this
     */
    public DeleteKeyRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 暗号鍵名 */
    private String keyName;

    /**
     * 暗号鍵名を取得
     *
     * @return 暗号鍵を削除します
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param keyName 暗号鍵を削除します
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param keyName 暗号鍵を削除します
     * @return this
     */
    public DeleteKeyRequest withKeyName(String keyName) {
        setKeyName(keyName);
        return this;
    }

}