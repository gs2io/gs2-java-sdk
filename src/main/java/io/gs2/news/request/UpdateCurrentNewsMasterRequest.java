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

package io.gs2.news.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.news.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 現在有効なお知らせを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCurrentNewsMasterRequest extends Gs2BasicRequest<UpdateCurrentNewsMasterRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return 現在有効なお知らせを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName 現在有効なお知らせを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName 現在有効なお知らせを更新します
     * @return this
     */
    public UpdateCurrentNewsMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** アップロード準備で受け取ったトークン */
    private String uploadToken;

    /**
     * アップロード準備で受け取ったトークンを取得
     *
     * @return 現在有効なお知らせを更新します
     */
    public String getUploadToken() {
        return uploadToken;
    }

    /**
     * アップロード準備で受け取ったトークンを設定
     *
     * @param uploadToken 現在有効なお知らせを更新します
     */
    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    /**
     * アップロード準備で受け取ったトークンを設定
     *
     * @param uploadToken 現在有効なお知らせを更新します
     * @return this
     */
    public UpdateCurrentNewsMasterRequest withUploadToken(String uploadToken) {
        setUploadToken(uploadToken);
        return this;
    }

}