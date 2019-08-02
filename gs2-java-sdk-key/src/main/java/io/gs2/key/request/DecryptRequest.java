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
 * データを復号します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DecryptRequest extends Gs2BasicRequest<DecryptRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return データを復号します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName データを復号します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName データを復号します
     * @return this
     */
    public DecryptRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 暗号鍵名 */
    private String keyName;

    /**
     * 暗号鍵名を取得
     *
     * @return データを復号します
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param keyName データを復号します
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * 暗号鍵名を設定
     *
     * @param keyName データを復号します
     * @return this
     */
    public DecryptRequest withKeyName(String keyName) {
        setKeyName(keyName);
        return this;
    }

    /** None */
    private String data;

    /**
     * Noneを取得
     *
     * @return データを復号します
     */
    public String getData() {
        return data;
    }

    /**
     * Noneを設定
     *
     * @param data データを復号します
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Noneを設定
     *
     * @param data データを復号します
     * @return this
     */
    public DecryptRequest withData(String data) {
        setData(data);
        return this;
    }

}