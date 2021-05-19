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

package io.gs2.datastore.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.datastore.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * データオブジェクトの管理情報を修復する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class RestoreDataObjectRequest extends Gs2BasicRequest<RestoreDataObjectRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return データオブジェクトの管理情報を修復する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName データオブジェクトの管理情報を修復する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName データオブジェクトの管理情報を修復する
     * @return this
     */
    public RestoreDataObjectRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** データオブジェクト */
    private String dataObjectId;

    /**
     * データオブジェクトを取得
     *
     * @return データオブジェクトの管理情報を修復する
     */
    public String getDataObjectId() {
        return dataObjectId;
    }

    /**
     * データオブジェクトを設定
     *
     * @param dataObjectId データオブジェクトの管理情報を修復する
     */
    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    /**
     * データオブジェクトを設定
     *
     * @param dataObjectId データオブジェクトの管理情報を修復する
     * @return this
     */
    public RestoreDataObjectRequest withDataObjectId(String dataObjectId) {
        setDataObjectId(dataObjectId);
        return this;
    }

}