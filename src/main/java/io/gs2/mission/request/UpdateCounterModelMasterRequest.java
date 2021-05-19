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

package io.gs2.mission.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.mission.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * カウンターの種類マスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCounterModelMasterRequest extends Gs2BasicRequest<UpdateCounterModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return カウンターの種類マスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターの種類マスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターの種類マスターを更新
     * @return this
     */
    public UpdateCounterModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カウンター名 */
    private String counterName;

    /**
     * カウンター名を取得
     *
     * @return カウンターの種類マスターを更新
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName カウンターの種類マスターを更新
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName カウンターの種類マスターを更新
     * @return this
     */
    public UpdateCounterModelMasterRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return カウンターの種類マスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata カウンターの種類マスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata カウンターの種類マスターを更新
     * @return this
     */
    public UpdateCounterModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** カウンターの種類マスターの説明 */
    private String description;

    /**
     * カウンターの種類マスターの説明を取得
     *
     * @return カウンターの種類マスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * カウンターの種類マスターの説明を設定
     *
     * @param description カウンターの種類マスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * カウンターの種類マスターの説明を設定
     *
     * @param description カウンターの種類マスターを更新
     * @return this
     */
    public UpdateCounterModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** カウンターのリセットタイミング */
    private List<CounterScopeModel> scopes;

    /**
     * カウンターのリセットタイミングを取得
     *
     * @return カウンターの種類マスターを更新
     */
    public List<CounterScopeModel> getScopes() {
        return scopes;
    }

    /**
     * カウンターのリセットタイミングを設定
     *
     * @param scopes カウンターの種類マスターを更新
     */
    public void setScopes(List<CounterScopeModel> scopes) {
        this.scopes = scopes;
    }

    /**
     * カウンターのリセットタイミングを設定
     *
     * @param scopes カウンターの種類マスターを更新
     * @return this
     */
    public UpdateCounterModelMasterRequest withScopes(List<CounterScopeModel> scopes) {
        setScopes(scopes);
        return this;
    }

    /** カウントアップ可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * カウントアップ可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return カウンターの種類マスターを更新
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * カウントアップ可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId カウンターの種類マスターを更新
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * カウントアップ可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId カウンターの種類マスターを更新
     * @return this
     */
    public UpdateCounterModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

}