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

package io.gs2.dictionary.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.dictionary.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * エントリーモデルマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateEntryModelMasterRequest extends Gs2BasicRequest<UpdateEntryModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return エントリーモデルマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName エントリーモデルマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName エントリーモデルマスターを更新
     * @return this
     */
    public UpdateEntryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** エントリーモデル名 */
    private String entryName;

    /**
     * エントリーモデル名を取得
     *
     * @return エントリーモデルマスターを更新
     */
    public String getEntryName() {
        return entryName;
    }

    /**
     * エントリーモデル名を設定
     *
     * @param entryName エントリーモデルマスターを更新
     */
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    /**
     * エントリーモデル名を設定
     *
     * @param entryName エントリーモデルマスターを更新
     * @return this
     */
    public UpdateEntryModelMasterRequest withEntryName(String entryName) {
        setEntryName(entryName);
        return this;
    }

    /** エントリーモデルマスターの説明 */
    private String description;

    /**
     * エントリーモデルマスターの説明を取得
     *
     * @return エントリーモデルマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * エントリーモデルマスターの説明を設定
     *
     * @param description エントリーモデルマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * エントリーモデルマスターの説明を設定
     *
     * @param description エントリーモデルマスターを更新
     * @return this
     */
    public UpdateEntryModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** エントリーモデルのメタデータ */
    private String metadata;

    /**
     * エントリーモデルのメタデータを取得
     *
     * @return エントリーモデルマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * エントリーモデルのメタデータを設定
     *
     * @param metadata エントリーモデルマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * エントリーモデルのメタデータを設定
     *
     * @param metadata エントリーモデルマスターを更新
     * @return this
     */
    public UpdateEntryModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

}