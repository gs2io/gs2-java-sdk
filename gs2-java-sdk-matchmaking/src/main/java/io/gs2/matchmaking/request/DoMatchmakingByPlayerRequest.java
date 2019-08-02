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
import io.gs2.control.Gs2BasicRequest;

/**
 * Player が参加できるギャザリングを探して参加する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DoMatchmakingByPlayerRequest extends Gs2BasicRequest<DoMatchmakingByPlayerRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return Player が参加できるギャザリングを探して参加する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName Player が参加できるギャザリングを探して参加する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName Player が参加できるギャザリングを探して参加する
     * @return this
     */
    public DoMatchmakingByPlayerRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** プレイヤー情報 */
    private Player player;

    /**
     * プレイヤー情報を取得
     *
     * @return Player が参加できるギャザリングを探して参加する
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * プレイヤー情報を設定
     *
     * @param player Player が参加できるギャザリングを探して参加する
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * プレイヤー情報を設定
     *
     * @param player Player が参加できるギャザリングを探して参加する
     * @return this
     */
    public DoMatchmakingByPlayerRequest withPlayer(Player player) {
        setPlayer(player);
        return this;
    }

    /** 検索の再開に使用する マッチメイキングの状態を保持するトークン */
    private String matchmakingContextToken;

    /**
     * 検索の再開に使用する マッチメイキングの状態を保持するトークンを取得
     *
     * @return Player が参加できるギャザリングを探して参加する
     */
    public String getMatchmakingContextToken() {
        return matchmakingContextToken;
    }

    /**
     * 検索の再開に使用する マッチメイキングの状態を保持するトークンを設定
     *
     * @param matchmakingContextToken Player が参加できるギャザリングを探して参加する
     */
    public void setMatchmakingContextToken(String matchmakingContextToken) {
        this.matchmakingContextToken = matchmakingContextToken;
    }

    /**
     * 検索の再開に使用する マッチメイキングの状態を保持するトークンを設定
     *
     * @param matchmakingContextToken Player が参加できるギャザリングを探して参加する
     * @return this
     */
    public DoMatchmakingByPlayerRequest withMatchmakingContextToken(String matchmakingContextToken) {
        setMatchmakingContextToken(matchmakingContextToken);
        return this;
    }

}