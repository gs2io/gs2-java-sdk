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
 * 抽選の種類マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateLotteryModelMasterRequest extends Gs2BasicRequest<CreateLotteryModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 抽選の種類マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 抽選モデルの種類名 */
    private String name;

    /**
     * 抽選モデルの種類名を取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 抽選モデルの種類名を設定
     *
     * @param name 抽選の種類マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 抽選モデルの種類名を設定
     *
     * @param name 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 抽選の種類マスターの説明 */
    private String description;

    /**
     * 抽選の種類マスターの説明を取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 抽選の種類マスターの説明を設定
     *
     * @param description 抽選の種類マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 抽選の種類マスターの説明を設定
     *
     * @param description 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 抽選モデルの種類のメタデータ */
    private String metadata;

    /**
     * 抽選モデルの種類のメタデータを取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 抽選モデルの種類のメタデータを設定
     *
     * @param metadata 抽選の種類マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 抽選モデルの種類のメタデータを設定
     *
     * @param metadata 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 抽選モード */
    private String mode;

    /**
     * 抽選モードを取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getMode() {
        return mode;
    }

    /**
     * 抽選モードを設定
     *
     * @param mode 抽選の種類マスターを新規作成
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * 抽選モードを設定
     *
     * @param mode 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withMode(String mode) {
        setMode(mode);
        return this;
    }

    /** 抽選回数 */
    private Integer count;

    /**
     * 抽選回数を取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 抽選回数を設定
     *
     * @param count 抽選の種類マスターを新規作成
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 抽選回数を設定
     *
     * @param count 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withCount(Integer count) {
        setCount(count);
        return this;
    }

    /** 抽選方法 */
    private String method;

    /**
     * 抽選方法を取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getMethod() {
        return method;
    }

    /**
     * 抽選方法を設定
     *
     * @param method 抽選の種類マスターを新規作成
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 抽選方法を設定
     *
     * @param method 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withMethod(String method) {
        setMethod(method);
        return this;
    }

    /** 景品テーブルの名前 */
    private String prizeTableName;

    /**
     * 景品テーブルの名前を取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getPrizeTableName() {
        return prizeTableName;
    }

    /**
     * 景品テーブルの名前を設定
     *
     * @param prizeTableName 抽選の種類マスターを新規作成
     */
    public void setPrizeTableName(String prizeTableName) {
        this.prizeTableName = prizeTableName;
    }

    /**
     * 景品テーブルの名前を設定
     *
     * @param prizeTableName 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withPrizeTableName(String prizeTableName) {
        setPrizeTableName(prizeTableName);
        return this;
    }

    /** 抽選テーブルを確定するスクリプト のGRN */
    private String choicePrizeTableScriptId;

    /**
     * 抽選テーブルを確定するスクリプト のGRNを取得
     *
     * @return 抽選の種類マスターを新規作成
     */
    public String getChoicePrizeTableScriptId() {
        return choicePrizeTableScriptId;
    }

    /**
     * 抽選テーブルを確定するスクリプト のGRNを設定
     *
     * @param choicePrizeTableScriptId 抽選の種類マスターを新規作成
     */
    public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
        this.choicePrizeTableScriptId = choicePrizeTableScriptId;
    }

    /**
     * 抽選テーブルを確定するスクリプト のGRNを設定
     *
     * @param choicePrizeTableScriptId 抽選の種類マスターを新規作成
     * @return this
     */
    public CreateLotteryModelMasterRequest withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
        setChoicePrizeTableScriptId(choicePrizeTableScriptId);
        return this;
    }

}