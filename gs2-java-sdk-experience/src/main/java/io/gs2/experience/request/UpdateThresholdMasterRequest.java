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

package io.gs2.experience.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.experience.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ランクアップ閾値マスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateThresholdMasterRequest extends Gs2BasicRequest<UpdateThresholdMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ランクアップ閾値マスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ランクアップ閾値マスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ランクアップ閾値マスターを更新
     * @return this
     */
    public UpdateThresholdMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ランクアップ閾値名 */
    private String thresholdName;

    /**
     * ランクアップ閾値名を取得
     *
     * @return ランクアップ閾値マスターを更新
     */
    public String getThresholdName() {
        return thresholdName;
    }

    /**
     * ランクアップ閾値名を設定
     *
     * @param thresholdName ランクアップ閾値マスターを更新
     */
    public void setThresholdName(String thresholdName) {
        this.thresholdName = thresholdName;
    }

    /**
     * ランクアップ閾値名を設定
     *
     * @param thresholdName ランクアップ閾値マスターを更新
     * @return this
     */
    public UpdateThresholdMasterRequest withThresholdName(String thresholdName) {
        setThresholdName(thresholdName);
        return this;
    }

    /** ランクアップ閾値マスターの説明 */
    private String description;

    /**
     * ランクアップ閾値マスターの説明を取得
     *
     * @return ランクアップ閾値マスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ランクアップ閾値マスターの説明を設定
     *
     * @param description ランクアップ閾値マスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ランクアップ閾値マスターの説明を設定
     *
     * @param description ランクアップ閾値マスターを更新
     * @return this
     */
    public UpdateThresholdMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ランクアップ閾値のメタデータ */
    private String metadata;

    /**
     * ランクアップ閾値のメタデータを取得
     *
     * @return ランクアップ閾値マスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * ランクアップ閾値のメタデータを設定
     *
     * @param metadata ランクアップ閾値マスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * ランクアップ閾値のメタデータを設定
     *
     * @param metadata ランクアップ閾値マスターを更新
     * @return this
     */
    public UpdateThresholdMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** ランクアップ経験値閾値リスト */
    private List<Long> values;

    /**
     * ランクアップ経験値閾値リストを取得
     *
     * @return ランクアップ閾値マスターを更新
     */
    public List<Long> getValues() {
        return values;
    }

    /**
     * ランクアップ経験値閾値リストを設定
     *
     * @param values ランクアップ閾値マスターを更新
     */
    public void setValues(List<Long> values) {
        this.values = values;
    }

    /**
     * ランクアップ経験値閾値リストを設定
     *
     * @param values ランクアップ閾値マスターを更新
     * @return this
     */
    public UpdateThresholdMasterRequest withValues(List<Long> values) {
        setValues(values);
        return this;
    }

}