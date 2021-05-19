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

package io.gs2.distributor.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.distributor.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 配信設定マスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteDistributorModelMasterRequest extends Gs2BasicRequest<DeleteDistributorModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 配信設定マスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 配信設定マスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 配信設定マスターを削除
     * @return this
     */
    public DeleteDistributorModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 配信設定名 */
    private String distributorName;

    /**
     * 配信設定名を取得
     *
     * @return 配信設定マスターを削除
     */
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * 配信設定名を設定
     *
     * @param distributorName 配信設定マスターを削除
     */
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * 配信設定名を設定
     *
     * @param distributorName 配信設定マスターを削除
     * @return this
     */
    public DeleteDistributorModelMasterRequest withDistributorName(String distributorName) {
        setDistributorName(distributorName);
        return this;
    }

}