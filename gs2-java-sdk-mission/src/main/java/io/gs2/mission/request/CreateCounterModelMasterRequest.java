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
import io.gs2.control.Gs2BasicRequest;

/**
 * カウンターの種類マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateCounterModelMasterRequest extends Gs2BasicRequest<CreateCounterModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return カウンターの種類マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターの種類マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターの種類マスターを新規作成
     * @return this
     */
    public CreateCounterModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カウンター名 */
    private String name;

    /**
     * カウンター名を取得
     *
     * @return カウンターの種類マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * カウンター名を設定
     *
     * @param name カウンターの種類マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * カウンター名を設定
     *
     * @param name カウンターの種類マスターを新規作成
     * @return this
     */
    public CreateCounterModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return カウンターの種類マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata カウンターの種類マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata カウンターの種類マスターを新規作成
     * @return this
     */
    public CreateCounterModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** カウンターの種類マスターの説明 */
    private String description;

    /**
     * カウンターの種類マスターの説明を取得
     *
     * @return カウンターの種類マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * カウンターの種類マスターの説明を設定
     *
     * @param description カウンターの種類マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * カウンターの種類マスターの説明を設定
     *
     * @param description カウンターの種類マスターを新規作成
     * @return this
     */
    public CreateCounterModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** カウンターのリセットタイミング */
    private List<CounterScopeModel> scopes;

    /**
     * カウンターのリセットタイミングを取得
     *
     * @return カウンターの種類マスターを新規作成
     */
    public List<CounterScopeModel> getScopes() {
        return scopes;
    }

    /**
     * カウンターのリセットタイミングを設定
     *
     * @param scopes カウンターの種類マスターを新規作成
     */
    public void setScopes(List<CounterScopeModel> scopes) {
        this.scopes = scopes;
    }

    /**
     * カウンターのリセットタイミングを設定
     *
     * @param scopes カウンターの種類マスターを新規作成
     * @return this
     */
    public CreateCounterModelMasterRequest withScopes(List<CounterScopeModel> scopes) {
        setScopes(scopes);
        return this;
    }

    /** カウントアップ可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * カウントアップ可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return カウンターの種類マスターを新規作成
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * カウントアップ可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId カウンターの種類マスターを新規作成
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * カウントアップ可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId カウンターの種類マスターを新規作成
     * @return this
     */
    public CreateCounterModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

}