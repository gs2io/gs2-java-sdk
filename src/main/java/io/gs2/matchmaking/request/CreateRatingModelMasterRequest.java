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

package io.gs2.matchmaking.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.matchmaking.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * レーティングモデルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRatingModelMasterRequest extends Gs2BasicRequest<CreateRatingModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return レーティングモデルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName レーティングモデルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName レーティングモデルマスターを新規作成
     * @return this
     */
    public CreateRatingModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** レーティングの種類名 */
    private String name;

    /**
     * レーティングの種類名を取得
     *
     * @return レーティングモデルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * レーティングの種類名を設定
     *
     * @param name レーティングモデルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * レーティングの種類名を設定
     *
     * @param name レーティングモデルマスターを新規作成
     * @return this
     */
    public CreateRatingModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** レーティングモデルマスターの説明 */
    private String description;

    /**
     * レーティングモデルマスターの説明を取得
     *
     * @return レーティングモデルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * レーティングモデルマスターの説明を設定
     *
     * @param description レーティングモデルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * レーティングモデルマスターの説明を設定
     *
     * @param description レーティングモデルマスターを新規作成
     * @return this
     */
    public CreateRatingModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** レーティングの種類のメタデータ */
    private String metadata;

    /**
     * レーティングの種類のメタデータを取得
     *
     * @return レーティングモデルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * レーティングの種類のメタデータを設定
     *
     * @param metadata レーティングモデルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * レーティングの種類のメタデータを設定
     *
     * @param metadata レーティングモデルマスターを新規作成
     * @return this
     */
    public CreateRatingModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** レート値の変動の大きさ */
    private Integer volatility;

    /**
     * レート値の変動の大きさを取得
     *
     * @return レーティングモデルマスターを新規作成
     */
    public Integer getVolatility() {
        return volatility;
    }

    /**
     * レート値の変動の大きさを設定
     *
     * @param volatility レーティングモデルマスターを新規作成
     */
    public void setVolatility(Integer volatility) {
        this.volatility = volatility;
    }

    /**
     * レート値の変動の大きさを設定
     *
     * @param volatility レーティングモデルマスターを新規作成
     * @return this
     */
    public CreateRatingModelMasterRequest withVolatility(Integer volatility) {
        setVolatility(volatility);
        return this;
    }

}