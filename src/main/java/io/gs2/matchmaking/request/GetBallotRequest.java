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
 * 投票用紙を取得します。 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetBallotRequest extends Gs2BasicRequest<GetBallotRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 投票用紙を取得します。
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 投票用紙を取得します。
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 投票用紙を取得します。
     * @return this
     */
    public GetBallotRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** レーティング名 */
    private String ratingName;

    /**
     * レーティング名を取得
     *
     * @return 投票用紙を取得します。
     */
    public String getRatingName() {
        return ratingName;
    }

    /**
     * レーティング名を設定
     *
     * @param ratingName 投票用紙を取得します。
     */
    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    /**
     * レーティング名を設定
     *
     * @param ratingName 投票用紙を取得します。
     * @return this
     */
    public GetBallotRequest withRatingName(String ratingName) {
        setRatingName(ratingName);
        return this;
    }

    /** 投票対象のギャザリング名 */
    private String gatheringName;

    /**
     * 投票対象のギャザリング名を取得
     *
     * @return 投票用紙を取得します。
     */
    public String getGatheringName() {
        return gatheringName;
    }

    /**
     * 投票対象のギャザリング名を設定
     *
     * @param gatheringName 投票用紙を取得します。
     */
    public void setGatheringName(String gatheringName) {
        this.gatheringName = gatheringName;
    }

    /**
     * 投票対象のギャザリング名を設定
     *
     * @param gatheringName 投票用紙を取得します。
     * @return this
     */
    public GetBallotRequest withGatheringName(String gatheringName) {
        setGatheringName(gatheringName);
        return this;
    }

    /** 参加人数 */
    private Integer numberOfPlayer;

    /**
     * 参加人数を取得
     *
     * @return 投票用紙を取得します。
     */
    public Integer getNumberOfPlayer() {
        return numberOfPlayer;
    }

    /**
     * 参加人数を設定
     *
     * @param numberOfPlayer 投票用紙を取得します。
     */
    public void setNumberOfPlayer(Integer numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    /**
     * 参加人数を設定
     *
     * @param numberOfPlayer 投票用紙を取得します。
     * @return this
     */
    public GetBallotRequest withNumberOfPlayer(Integer numberOfPlayer) {
        setNumberOfPlayer(numberOfPlayer);
        return this;
    }

    /** 投票用紙の署名計算に使用する暗号鍵 のGRN */
    private String keyId;

    /**
     * 投票用紙の署名計算に使用する暗号鍵 のGRNを取得
     *
     * @return 投票用紙を取得します。
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 投票用紙の署名計算に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 投票用紙を取得します。
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 投票用紙の署名計算に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 投票用紙を取得します。
     * @return this
     */
    public GetBallotRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 投票用紙を取得します。
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 投票用紙を取得します。
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 投票用紙を取得します。
     * @return this
     */
    public GetBallotRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public GetBallotRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}