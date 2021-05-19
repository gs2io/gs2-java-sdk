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
 * レーティング値の再計算を実行 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PutResultRequest extends Gs2BasicRequest<PutResultRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return レーティング値の再計算を実行
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName レーティング値の再計算を実行
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName レーティング値の再計算を実行
     * @return this
     */
    public PutResultRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** レーティング名 */
    private String ratingName;

    /**
     * レーティング名を取得
     *
     * @return レーティング値の再計算を実行
     */
    public String getRatingName() {
        return ratingName;
    }

    /**
     * レーティング名を設定
     *
     * @param ratingName レーティング値の再計算を実行
     */
    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    /**
     * レーティング名を設定
     *
     * @param ratingName レーティング値の再計算を実行
     * @return this
     */
    public PutResultRequest withRatingName(String ratingName) {
        setRatingName(ratingName);
        return this;
    }

    /** 対戦結果 */
    private List<GameResult> gameResults;

    /**
     * 対戦結果を取得
     *
     * @return レーティング値の再計算を実行
     */
    public List<GameResult> getGameResults() {
        return gameResults;
    }

    /**
     * 対戦結果を設定
     *
     * @param gameResults レーティング値の再計算を実行
     */
    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    /**
     * 対戦結果を設定
     *
     * @param gameResults レーティング値の再計算を実行
     * @return this
     */
    public PutResultRequest withGameResults(List<GameResult> gameResults) {
        setGameResults(gameResults);
        return this;
    }

}