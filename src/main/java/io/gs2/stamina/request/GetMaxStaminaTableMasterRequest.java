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

package io.gs2.stamina.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.stamina.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スタミナの最大値テーブルマスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetMaxStaminaTableMasterRequest extends Gs2BasicRequest<GetMaxStaminaTableMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナの最大値テーブルマスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値テーブルマスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値テーブルマスターを取得
     * @return this
     */
    public GetMaxStaminaTableMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 最大スタミナ値テーブル名 */
    private String maxStaminaTableName;

    /**
     * 最大スタミナ値テーブル名を取得
     *
     * @return スタミナの最大値テーブルマスターを取得
     */
    public String getMaxStaminaTableName() {
        return maxStaminaTableName;
    }

    /**
     * 最大スタミナ値テーブル名を設定
     *
     * @param maxStaminaTableName スタミナの最大値テーブルマスターを取得
     */
    public void setMaxStaminaTableName(String maxStaminaTableName) {
        this.maxStaminaTableName = maxStaminaTableName;
    }

    /**
     * 最大スタミナ値テーブル名を設定
     *
     * @param maxStaminaTableName スタミナの最大値テーブルマスターを取得
     * @return this
     */
    public GetMaxStaminaTableMasterRequest withMaxStaminaTableName(String maxStaminaTableName) {
        setMaxStaminaTableName(maxStaminaTableName);
        return this;
    }

}