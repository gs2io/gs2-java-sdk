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

package io.gs2.lottery.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.lottery.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 排出確率テーブルマスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeletePrizeTableMasterRequest extends Gs2BasicRequest<DeletePrizeTableMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 排出確率テーブルマスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 排出確率テーブルマスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 排出確率テーブルマスターを削除
     * @return this
     */
    public DeletePrizeTableMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 排出確率テーブル名 */
    private String prizeTableName;

    /**
     * 排出確率テーブル名を取得
     *
     * @return 排出確率テーブルマスターを削除
     */
    public String getPrizeTableName() {
        return prizeTableName;
    }

    /**
     * 排出確率テーブル名を設定
     *
     * @param prizeTableName 排出確率テーブルマスターを削除
     */
    public void setPrizeTableName(String prizeTableName) {
        this.prizeTableName = prizeTableName;
    }

    /**
     * 排出確率テーブル名を設定
     *
     * @param prizeTableName 排出確率テーブルマスターを削除
     * @return this
     */
    public DeletePrizeTableMasterRequest withPrizeTableName(String prizeTableName) {
        setPrizeTableName(prizeTableName);
        return this;
    }

}