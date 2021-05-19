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
 * レーティングモデルマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateRatingModelMasterRequest extends Gs2BasicRequest<UpdateRatingModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return レーティングモデルマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName レーティングモデルマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName レーティングモデルマスターを更新
     * @return this
     */
    public UpdateRatingModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** レーティングの種類名 */
    private String ratingName;

    /**
     * レーティングの種類名を取得
     *
     * @return レーティングモデルマスターを更新
     */
    public String getRatingName() {
        return ratingName;
    }

    /**
     * レーティングの種類名を設定
     *
     * @param ratingName レーティングモデルマスターを更新
     */
    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    /**
     * レーティングの種類名を設定
     *
     * @param ratingName レーティングモデルマスターを更新
     * @return this
     */
    public UpdateRatingModelMasterRequest withRatingName(String ratingName) {
        setRatingName(ratingName);
        return this;
    }

    /** レーティングモデルマスターの説明 */
    private String description;

    /**
     * レーティングモデルマスターの説明を取得
     *
     * @return レーティングモデルマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * レーティングモデルマスターの説明を設定
     *
     * @param description レーティングモデルマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * レーティングモデルマスターの説明を設定
     *
     * @param description レーティングモデルマスターを更新
     * @return this
     */
    public UpdateRatingModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** レーティングの種類のメタデータ */
    private String metadata;

    /**
     * レーティングの種類のメタデータを取得
     *
     * @return レーティングモデルマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * レーティングの種類のメタデータを設定
     *
     * @param metadata レーティングモデルマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * レーティングの種類のメタデータを設定
     *
     * @param metadata レーティングモデルマスターを更新
     * @return this
     */
    public UpdateRatingModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** レート値の変動の大きさ */
    private Integer volatility;

    /**
     * レート値の変動の大きさを取得
     *
     * @return レーティングモデルマスターを更新
     */
    public Integer getVolatility() {
        return volatility;
    }

    /**
     * レート値の変動の大きさを設定
     *
     * @param volatility レーティングモデルマスターを更新
     */
    public void setVolatility(Integer volatility) {
        this.volatility = volatility;
    }

    /**
     * レート値の変動の大きさを設定
     *
     * @param volatility レーティングモデルマスターを更新
     * @return this
     */
    public UpdateRatingModelMasterRequest withVolatility(Integer volatility) {
        setVolatility(volatility);
        return this;
    }

}