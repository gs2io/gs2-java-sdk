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
 * 対戦結果をまとめて投票します。 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class VoteMultipleRequest extends Gs2BasicRequest<VoteMultipleRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 対戦結果をまとめて投票します。
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 対戦結果をまとめて投票します。
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 対戦結果をまとめて投票します。
     * @return this
     */
    public VoteMultipleRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 署名付の投票用紙リスト */
    private List<SignedBallot> signedBallots;

    /**
     * 署名付の投票用紙リストを取得
     *
     * @return 対戦結果をまとめて投票します。
     */
    public List<SignedBallot> getSignedBallots() {
        return signedBallots;
    }

    /**
     * 署名付の投票用紙リストを設定
     *
     * @param signedBallots 対戦結果をまとめて投票します。
     */
    public void setSignedBallots(List<SignedBallot> signedBallots) {
        this.signedBallots = signedBallots;
    }

    /**
     * 署名付の投票用紙リストを設定
     *
     * @param signedBallots 対戦結果をまとめて投票します。
     * @return this
     */
    public VoteMultipleRequest withSignedBallots(List<SignedBallot> signedBallots) {
        setSignedBallots(signedBallots);
        return this;
    }

    /** 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリスト */
    private List<GameResult> gameResults;

    /**
     * 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリストを取得
     *
     * @return 対戦結果をまとめて投票します。
     */
    public List<GameResult> getGameResults() {
        return gameResults;
    }

    /**
     * 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリストを設定
     *
     * @param gameResults 対戦結果をまとめて投票します。
     */
    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    /**
     * 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリストを設定
     *
     * @param gameResults 対戦結果をまとめて投票します。
     * @return this
     */
    public VoteMultipleRequest withGameResults(List<GameResult> gameResults) {
        setGameResults(gameResults);
        return this;
    }

    /** 投票用紙の署名検証に使用する暗号鍵 のGRN */
    private String keyId;

    /**
     * 投票用紙の署名検証に使用する暗号鍵 のGRNを取得
     *
     * @return 対戦結果をまとめて投票します。
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 投票用紙の署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 対戦結果をまとめて投票します。
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 投票用紙の署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 対戦結果をまとめて投票します。
     * @return this
     */
    public VoteMultipleRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

}