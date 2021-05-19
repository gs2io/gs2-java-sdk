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
 * 対戦結果を投票します。 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class VoteRequest extends Gs2BasicRequest<VoteRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 対戦結果を投票します。
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 対戦結果を投票します。
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 対戦結果を投票します。
     * @return this
     */
    public VoteRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 投票用紙の署名対象のデータ */
    private String ballotBody;

    /**
     * 投票用紙の署名対象のデータを取得
     *
     * @return 対戦結果を投票します。
     */
    public String getBallotBody() {
        return ballotBody;
    }

    /**
     * 投票用紙の署名対象のデータを設定
     *
     * @param ballotBody 対戦結果を投票します。
     */
    public void setBallotBody(String ballotBody) {
        this.ballotBody = ballotBody;
    }

    /**
     * 投票用紙の署名対象のデータを設定
     *
     * @param ballotBody 対戦結果を投票します。
     * @return this
     */
    public VoteRequest withBallotBody(String ballotBody) {
        setBallotBody(ballotBody);
        return this;
    }

    /** 投票用紙の署名 */
    private String ballotSignature;

    /**
     * 投票用紙の署名を取得
     *
     * @return 対戦結果を投票します。
     */
    public String getBallotSignature() {
        return ballotSignature;
    }

    /**
     * 投票用紙の署名を設定
     *
     * @param ballotSignature 対戦結果を投票します。
     */
    public void setBallotSignature(String ballotSignature) {
        this.ballotSignature = ballotSignature;
    }

    /**
     * 投票用紙の署名を設定
     *
     * @param ballotSignature 対戦結果を投票します。
     * @return this
     */
    public VoteRequest withBallotSignature(String ballotSignature) {
        setBallotSignature(ballotSignature);
        return this;
    }

    /** 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリスト */
    private List<GameResult> gameResults;

    /**
     * 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリストを取得
     *
     * @return 対戦結果を投票します。
     */
    public List<GameResult> getGameResults() {
        return gameResults;
    }

    /**
     * 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリストを設定
     *
     * @param gameResults 対戦結果を投票します。
     */
    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    /**
     * 投票内容。対戦を行ったプレイヤーグループ1に所属するユーザIDのリストを設定
     *
     * @param gameResults 対戦結果を投票します。
     * @return this
     */
    public VoteRequest withGameResults(List<GameResult> gameResults) {
        setGameResults(gameResults);
        return this;
    }

    /** 投票用紙の署名検証に使用する暗号鍵 のGRN */
    private String keyId;

    /**
     * 投票用紙の署名検証に使用する暗号鍵 のGRNを取得
     *
     * @return 対戦結果を投票します。
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 投票用紙の署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 対戦結果を投票します。
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 投票用紙の署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 対戦結果を投票します。
     * @return this
     */
    public VoteRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

}