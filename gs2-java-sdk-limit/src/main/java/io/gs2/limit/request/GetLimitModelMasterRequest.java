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

package io.gs2.limit.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.limit.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 回数制限の種類マスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetLimitModelMasterRequest extends Gs2BasicRequest<GetLimitModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 回数制限の種類マスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 回数制限の種類マスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 回数制限の種類マスターを取得
     * @return this
     */
    public GetLimitModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 回数制限の種類名 */
    private String limitName;

    /**
     * 回数制限の種類名を取得
     *
     * @return 回数制限の種類マスターを取得
     */
    public String getLimitName() {
        return limitName;
    }

    /**
     * 回数制限の種類名を設定
     *
     * @param limitName 回数制限の種類マスターを取得
     */
    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    /**
     * 回数制限の種類名を設定
     *
     * @param limitName 回数制限の種類マスターを取得
     * @return this
     */
    public GetLimitModelMasterRequest withLimitName(String limitName) {
        setLimitName(limitName);
        return this;
    }

}