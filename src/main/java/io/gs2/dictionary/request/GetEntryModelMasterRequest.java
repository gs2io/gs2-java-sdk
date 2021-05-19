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
 * エントリーモデルマスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetEntryModelMasterRequest extends Gs2BasicRequest<GetEntryModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return エントリーモデルマスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName エントリーモデルマスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName エントリーモデルマスターを取得
     * @return this
     */
    public GetEntryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** エントリーモデル名 */
    private String entryName;

    /**
     * エントリーモデル名を取得
     *
     * @return エントリーモデルマスターを取得
     */
    public String getEntryName() {
        return entryName;
    }

    /**
     * エントリーモデル名を設定
     *
     * @param entryName エントリーモデルマスターを取得
     */
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    /**
     * エントリーモデル名を設定
     *
     * @param entryName エントリーモデルマスターを取得
     * @return this
     */
    public GetEntryModelMasterRequest withEntryName(String entryName) {
        setEntryName(entryName);
        return this;
    }

}