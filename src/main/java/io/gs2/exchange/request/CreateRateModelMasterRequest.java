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

package io.gs2.exchange.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.exchange.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 交換レートマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRateModelMasterRequest extends Gs2BasicRequest<CreateRateModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 交換レートマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 交換レートマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 交換レート名 */
    private String name;

    /**
     * 交換レート名を取得
     *
     * @return 交換レートマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 交換レート名を設定
     *
     * @param name 交換レートマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 交換レート名を設定
     *
     * @param name 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 交換レートマスターの説明 */
    private String description;

    /**
     * 交換レートマスターの説明を取得
     *
     * @return 交換レートマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 交換レートマスターの説明を設定
     *
     * @param description 交換レートマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 交換レートマスターの説明を設定
     *
     * @param description 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 交換レートのメタデータ */
    private String metadata;

    /**
     * 交換レートのメタデータを取得
     *
     * @return 交換レートマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 交換レートのメタデータを設定
     *
     * @param metadata 交換レートマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 交換レートのメタデータを設定
     *
     * @param metadata 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 交換の種類 */
    private String timingType;

    /**
     * 交換の種類を取得
     *
     * @return 交換レートマスターを新規作成
     */
    public String getTimingType() {
        return timingType;
    }

    /**
     * 交換の種類を設定
     *
     * @param timingType 交換レートマスターを新規作成
     */
    public void setTimingType(String timingType) {
        this.timingType = timingType;
    }

    /**
     * 交換の種類を設定
     *
     * @param timingType 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withTimingType(String timingType) {
        setTimingType(timingType);
        return this;
    }

    /** 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分） */
    private Integer lockTime;

    /**
     * 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）を取得
     *
     * @return 交換レートマスターを新規作成
     */
    public Integer getLockTime() {
        return lockTime;
    }

    /**
     * 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）を設定
     *
     * @param lockTime 交換レートマスターを新規作成
     */
    public void setLockTime(Integer lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）を設定
     *
     * @param lockTime 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withLockTime(Integer lockTime) {
        setLockTime(lockTime);
        return this;
    }

    /** スキップをすることができるか */
    private Boolean enableSkip;

    /**
     * スキップをすることができるかを取得
     *
     * @return 交換レートマスターを新規作成
     */
    public Boolean getEnableSkip() {
        return enableSkip;
    }

    /**
     * スキップをすることができるかを設定
     *
     * @param enableSkip 交換レートマスターを新規作成
     */
    public void setEnableSkip(Boolean enableSkip) {
        this.enableSkip = enableSkip;
    }

    /**
     * スキップをすることができるかを設定
     *
     * @param enableSkip 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withEnableSkip(Boolean enableSkip) {
        setEnableSkip(enableSkip);
        return this;
    }

    /** 時短消費アクションリスト */
    private List<ConsumeAction> skipConsumeActions;

    /**
     * 時短消費アクションリストを取得
     *
     * @return 交換レートマスターを新規作成
     */
    public List<ConsumeAction> getSkipConsumeActions() {
        return skipConsumeActions;
    }

    /**
     * 時短消費アクションリストを設定
     *
     * @param skipConsumeActions 交換レートマスターを新規作成
     */
    public void setSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
        this.skipConsumeActions = skipConsumeActions;
    }

    /**
     * 時短消費アクションリストを設定
     *
     * @param skipConsumeActions 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
        setSkipConsumeActions(skipConsumeActions);
        return this;
    }

    /** 入手アクションリスト */
    private List<AcquireAction> acquireActions;

    /**
     * 入手アクションリストを取得
     *
     * @return 交換レートマスターを新規作成
     */
    public List<AcquireAction> getAcquireActions() {
        return acquireActions;
    }

    /**
     * 入手アクションリストを設定
     *
     * @param acquireActions 交換レートマスターを新規作成
     */
    public void setAcquireActions(List<AcquireAction> acquireActions) {
        this.acquireActions = acquireActions;
    }

    /**
     * 入手アクションリストを設定
     *
     * @param acquireActions 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withAcquireActions(List<AcquireAction> acquireActions) {
        setAcquireActions(acquireActions);
        return this;
    }

    /** 消費アクションリスト */
    private List<ConsumeAction> consumeActions;

    /**
     * 消費アクションリストを取得
     *
     * @return 交換レートマスターを新規作成
     */
    public List<ConsumeAction> getConsumeActions() {
        return consumeActions;
    }

    /**
     * 消費アクションリストを設定
     *
     * @param consumeActions 交換レートマスターを新規作成
     */
    public void setConsumeActions(List<ConsumeAction> consumeActions) {
        this.consumeActions = consumeActions;
    }

    /**
     * 消費アクションリストを設定
     *
     * @param consumeActions 交換レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withConsumeActions(List<ConsumeAction> consumeActions) {
        setConsumeActions(consumeActions);
        return this;
    }

}