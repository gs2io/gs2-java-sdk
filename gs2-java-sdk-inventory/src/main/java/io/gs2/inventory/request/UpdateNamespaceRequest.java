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
 * ネームスペースを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return ネームスペースを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName ネームスペースを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アイテム入手時 に実行されるスクリプト のGRN */
    private String acquireTriggerScriptId;

    /**
     * アイテム入手時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAcquireTriggerScriptId() {
        return acquireTriggerScriptId;
    }

    /**
     * アイテム入手時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireTriggerScriptId ネームスペースを更新
     */
    public void setAcquireTriggerScriptId(String acquireTriggerScriptId) {
        this.acquireTriggerScriptId = acquireTriggerScriptId;
    }

    /**
     * アイテム入手時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcquireTriggerScriptId(String acquireTriggerScriptId) {
        setAcquireTriggerScriptId(acquireTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に実行されるスクリプト のGRN */
    private String acquireDoneTriggerScriptId;

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAcquireDoneTriggerScriptId() {
        return acquireDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireDoneTriggerScriptId ネームスペースを更新
     */
    public void setAcquireDoneTriggerScriptId(String acquireDoneTriggerScriptId) {
        this.acquireDoneTriggerScriptId = acquireDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param acquireDoneTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcquireDoneTriggerScriptId(String acquireDoneTriggerScriptId) {
        setAcquireDoneTriggerScriptId(acquireDoneTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN */
    private String acquireDoneTriggerQueueNamespaceId;

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAcquireDoneTriggerQueueNamespaceId() {
        return acquireDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param acquireDoneTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setAcquireDoneTriggerQueueNamespaceId(String acquireDoneTriggerQueueNamespaceId) {
        this.acquireDoneTriggerQueueNamespaceId = acquireDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param acquireDoneTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcquireDoneTriggerQueueNamespaceId(String acquireDoneTriggerQueueNamespaceId) {
        setAcquireDoneTriggerQueueNamespaceId(acquireDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 入手上限に当たって入手できなかった数量を通知する スクリプト のGRN */
    private String overflowTriggerScriptId;

    /**
     * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getOverflowTriggerScriptId() {
        return overflowTriggerScriptId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを設定
     *
     * @param overflowTriggerScriptId ネームスペースを更新
     */
    public void setOverflowTriggerScriptId(String overflowTriggerScriptId) {
        this.overflowTriggerScriptId = overflowTriggerScriptId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知する スクリプト のGRNを設定
     *
     * @param overflowTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withOverflowTriggerScriptId(String overflowTriggerScriptId) {
        setOverflowTriggerScriptId(overflowTriggerScriptId);
        return this;
    }

    /** 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRN */
    private String overflowTriggerQueueNamespaceId;

    /**
     * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getOverflowTriggerQueueNamespaceId() {
        return overflowTriggerQueueNamespaceId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param overflowTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setOverflowTriggerQueueNamespaceId(String overflowTriggerQueueNamespaceId) {
        this.overflowTriggerQueueNamespaceId = overflowTriggerQueueNamespaceId;
    }

    /**
     * 入手上限に当たって入手できなかった数量を通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param overflowTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withOverflowTriggerQueueNamespaceId(String overflowTriggerQueueNamespaceId) {
        setOverflowTriggerQueueNamespaceId(overflowTriggerQueueNamespaceId);
        return this;
    }

    /** アイテム消費時 に実行されるスクリプト のGRN */
    private String consumeTriggerScriptId;

    /**
     * アイテム消費時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getConsumeTriggerScriptId() {
        return consumeTriggerScriptId;
    }

    /**
     * アイテム消費時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeTriggerScriptId ネームスペースを更新
     */
    public void setConsumeTriggerScriptId(String consumeTriggerScriptId) {
        this.consumeTriggerScriptId = consumeTriggerScriptId;
    }

    /**
     * アイテム消費時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withConsumeTriggerScriptId(String consumeTriggerScriptId) {
        setConsumeTriggerScriptId(consumeTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に実行されるスクリプト のGRN */
    private String consumeDoneTriggerScriptId;

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getConsumeDoneTriggerScriptId() {
        return consumeDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeDoneTriggerScriptId ネームスペースを更新
     */
    public void setConsumeDoneTriggerScriptId(String consumeDoneTriggerScriptId) {
        this.consumeDoneTriggerScriptId = consumeDoneTriggerScriptId;
    }

    /**
     * アイテム入手完了時 に実行されるスクリプト のGRNを設定
     *
     * @param consumeDoneTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withConsumeDoneTriggerScriptId(String consumeDoneTriggerScriptId) {
        setConsumeDoneTriggerScriptId(consumeDoneTriggerScriptId);
        return this;
    }

    /** アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRN */
    private String consumeDoneTriggerQueueNamespaceId;

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getConsumeDoneTriggerQueueNamespaceId() {
        return consumeDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param consumeDoneTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setConsumeDoneTriggerQueueNamespaceId(String consumeDoneTriggerQueueNamespaceId) {
        this.consumeDoneTriggerQueueNamespaceId = consumeDoneTriggerQueueNamespaceId;
    }

    /**
     * アイテム入手完了時 に通知するジョブを追加する ネームスペース のGRNを設定
     *
     * @param consumeDoneTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withConsumeDoneTriggerQueueNamespaceId(String consumeDoneTriggerQueueNamespaceId) {
        setConsumeDoneTriggerQueueNamespaceId(consumeDoneTriggerQueueNamespaceId);
        return this;
    }

}