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
 * 排出確率テーブルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreatePrizeTableMasterRequest extends Gs2BasicRequest<CreatePrizeTableMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 排出確率テーブルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 排出確率テーブルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 排出確率テーブルマスターを新規作成
     * @return this
     */
    public CreatePrizeTableMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 排出確率テーブル名 */
    private String name;

    /**
     * 排出確率テーブル名を取得
     *
     * @return 排出確率テーブルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 排出確率テーブル名を設定
     *
     * @param name 排出確率テーブルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 排出確率テーブル名を設定
     *
     * @param name 排出確率テーブルマスターを新規作成
     * @return this
     */
    public CreatePrizeTableMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 排出確率テーブルマスターの説明 */
    private String description;

    /**
     * 排出確率テーブルマスターの説明を取得
     *
     * @return 排出確率テーブルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 排出確率テーブルマスターの説明を設定
     *
     * @param description 排出確率テーブルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 排出確率テーブルマスターの説明を設定
     *
     * @param description 排出確率テーブルマスターを新規作成
     * @return this
     */
    public CreatePrizeTableMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 排出確率テーブルのメタデータ */
    private String metadata;

    /**
     * 排出確率テーブルのメタデータを取得
     *
     * @return 排出確率テーブルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 排出確率テーブルのメタデータを設定
     *
     * @param metadata 排出確率テーブルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 排出確率テーブルのメタデータを設定
     *
     * @param metadata 排出確率テーブルマスターを新規作成
     * @return this
     */
    public CreatePrizeTableMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 景品リスト */
    private List<Prize> prizes;

    /**
     * 景品リストを取得
     *
     * @return 排出確率テーブルマスターを新規作成
     */
    public List<Prize> getPrizes() {
        return prizes;
    }

    /**
     * 景品リストを設定
     *
     * @param prizes 排出確率テーブルマスターを新規作成
     */
    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    /**
     * 景品リストを設定
     *
     * @param prizes 排出確率テーブルマスターを新規作成
     * @return this
     */
    public CreatePrizeTableMasterRequest withPrizes(List<Prize> prizes) {
        setPrizes(prizes);
        return this;
    }

}