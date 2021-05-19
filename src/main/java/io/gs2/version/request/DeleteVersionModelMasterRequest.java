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

package io.gs2.version.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.version.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * バージョンマスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteVersionModelMasterRequest extends Gs2BasicRequest<DeleteVersionModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return バージョンマスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンマスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンマスターを削除
     * @return this
     */
    public DeleteVersionModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** バージョン名 */
    private String versionName;

    /**
     * バージョン名を取得
     *
     * @return バージョンマスターを削除
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * バージョン名を設定
     *
     * @param versionName バージョンマスターを削除
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * バージョン名を設定
     *
     * @param versionName バージョンマスターを削除
     * @return this
     */
    public DeleteVersionModelMasterRequest withVersionName(String versionName) {
        setVersionName(versionName);
        return this;
    }

}