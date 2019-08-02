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
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 景品付与処理をジョブとして追加するキューのネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 景品付与処理をジョブとして追加するキューのネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 景品付与処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを新規作成
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 景品付与処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** 景品付与処理のスタンプシートで使用する暗号鍵GRN */
    private String keyId;

    /**
     * 景品付与処理のスタンプシートで使用する暗号鍵GRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 景品付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを新規作成
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 景品付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 抽選処理時 に実行されるスクリプト のGRN */
    private String lotteryTriggerScriptId;

    /**
     * 抽選処理時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getLotteryTriggerScriptId() {
        return lotteryTriggerScriptId;
    }

    /**
     * 抽選処理時 に実行されるスクリプト のGRNを設定
     *
     * @param lotteryTriggerScriptId ネームスペースを新規作成
     */
    public void setLotteryTriggerScriptId(String lotteryTriggerScriptId) {
        this.lotteryTriggerScriptId = lotteryTriggerScriptId;
    }

    /**
     * 抽選処理時 に実行されるスクリプト のGRNを設定
     *
     * @param lotteryTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLotteryTriggerScriptId(String lotteryTriggerScriptId) {
        setLotteryTriggerScriptId(lotteryTriggerScriptId);
        return this;
    }

    /** 排出テーブル選択時 に実行されるスクリプト のGRN */
    private String choicePrizeTableScriptId;

    /**
     * 排出テーブル選択時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChoicePrizeTableScriptId() {
        return choicePrizeTableScriptId;
    }

    /**
     * 排出テーブル選択時 に実行されるスクリプト のGRNを設定
     *
     * @param choicePrizeTableScriptId ネームスペースを新規作成
     */
    public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
        this.choicePrizeTableScriptId = choicePrizeTableScriptId;
    }

    /**
     * 排出テーブル選択時 に実行されるスクリプト のGRNを設定
     *
     * @param choicePrizeTableScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
        setChoicePrizeTableScriptId(choicePrizeTableScriptId);
        return this;
    }

}