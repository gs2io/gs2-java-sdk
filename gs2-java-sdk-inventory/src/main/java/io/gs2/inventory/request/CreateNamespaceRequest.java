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

package io.gs2.inventory.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inventory.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** カテゴリー名 */
    private String name;

    /**
     * カテゴリー名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * カテゴリー名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * カテゴリー名を設定
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

    /** アイテム入手時 に実行されるスクリプト のGRN */
    private String acquireTriggerScriptId;

    /**
     * アイテム入手時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAcquireTriggerScriptId() {
        return acquireTriggerScriptId;
    }

    /**
     * アイテム入手時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireTriggerScriptId ネームスペースを新規作成
     */
    public void setAcquireTriggerScriptId(String acquireTriggerScriptId) {
        this.acquireTriggerScriptId = acquireTriggerScriptId;
    }

    /**
     * アイテム入手時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAcquireTriggerScriptId(String acquireTriggerScriptId) {
        setAcquireTriggerScriptId(acquireTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に実行されるスクリプト のGRN */
    private String acquireDoneTriggerScriptId;

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAcquireDoneTriggerScriptId() {
        return acquireDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setAcquireDoneTriggerScriptId(String acquireDoneTriggerScriptId) {
        this.acquireDoneTriggerScriptId = acquireDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAcquireDoneTriggerScriptId(String acquireDoneTriggerScriptId) {
        setAcquireDoneTriggerScriptId(acquireDoneTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN */
    private String acquireDoneTriggerQueueNamespaceId;

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAcquireDoneTriggerQueueNamespaceId() {
        return acquireDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param acquireDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setAcquireDoneTriggerQueueNamespaceId(String acquireDoneTriggerQueueNamespaceId) {
        this.acquireDoneTriggerQueueNamespaceId = acquireDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param acquireDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAcquireDoneTriggerQueueNamespaceId(String acquireDoneTriggerQueueNamespaceId) {
        setAcquireDoneTriggerQueueNamespaceId(acquireDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 入手上限に当たって入手できなかった数量を通知する スクリプト のGRN */
    private String overflowTriggerScriptId;

    /**
     * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getOverflowTriggerScriptId() {
        return overflowTriggerScriptId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを設定
     *
     * @param overflowTriggerScriptId ネームスペースを新規作成
     */
    public void setOverflowTriggerScriptId(String overflowTriggerScriptId) {
        this.overflowTriggerScriptId = overflowTriggerScriptId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを設定
     *
     * @param overflowTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withOverflowTriggerScriptId(String overflowTriggerScriptId) {
        setOverflowTriggerScriptId(overflowTriggerScriptId);
        return this;
    }

    /** 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRN */
    private String overflowTriggerQueueNamespaceId;

    /**
     * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getOverflowTriggerQueueNamespaceId() {
        return overflowTriggerQueueNamespaceId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param overflowTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setOverflowTriggerQueueNamespaceId(String overflowTriggerQueueNamespaceId) {
        this.overflowTriggerQueueNamespaceId = overflowTriggerQueueNamespaceId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param overflowTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withOverflowTriggerQueueNamespaceId(String overflowTriggerQueueNamespaceId) {
        setOverflowTriggerQueueNamespaceId(overflowTriggerQueueNamespaceId);
        return this;
    }

    /** アイテム消費時 に実行されるスクリプト のGRN */
    private String consumeTriggerScriptId;

    /**
     * アイテム消費時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getConsumeTriggerScriptId() {
        return consumeTriggerScriptId;
    }

    /**
     * アイテム消費時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeTriggerScriptId ネームスペースを新規作成
     */
    public void setConsumeTriggerScriptId(String consumeTriggerScriptId) {
        this.consumeTriggerScriptId = consumeTriggerScriptId;
    }

    /**
     * アイテム消費時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withConsumeTriggerScriptId(String consumeTriggerScriptId) {
        setConsumeTriggerScriptId(consumeTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に実行されるスクリプト のGRN */
    private String consumeDoneTriggerScriptId;

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getConsumeDoneTriggerScriptId() {
        return consumeDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setConsumeDoneTriggerScriptId(String consumeDoneTriggerScriptId) {
        this.consumeDoneTriggerScriptId = consumeDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withConsumeDoneTriggerScriptId(String consumeDoneTriggerScriptId) {
        setConsumeDoneTriggerScriptId(consumeDoneTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN */
    private String consumeDoneTriggerQueueNamespaceId;

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getConsumeDoneTriggerQueueNamespaceId() {
        return consumeDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param consumeDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setConsumeDoneTriggerQueueNamespaceId(String consumeDoneTriggerQueueNamespaceId) {
        this.consumeDoneTriggerQueueNamespaceId = consumeDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param consumeDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withConsumeDoneTriggerQueueNamespaceId(String consumeDoneTriggerQueueNamespaceId) {
        setConsumeDoneTriggerQueueNamespaceId(consumeDoneTriggerQueueNamespaceId);
        return this;
    }

}