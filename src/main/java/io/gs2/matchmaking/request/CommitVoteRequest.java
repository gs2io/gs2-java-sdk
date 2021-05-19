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
 * 投票状況を強制確定 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CommitVoteRequest extends Gs2BasicRequest<CommitVoteRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 投票状況を強制確定
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 投票状況を強制確定
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 投票状況を強制確定
     * @return this
     */
    public CommitVoteRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** レーティング名 */
    private String ratingName;

    /**
     * レーティング名を取得
     *
     * @return 投票状況を強制確定
     */
    public String getRatingName() {
        return ratingName;
    }

    /**
     * レーティング名を設定
     *
     * @param ratingName 投票状況を強制確定
     */
    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    /**
     * レーティング名を設定
     *
     * @param ratingName 投票状況を強制確定
     * @return this
     */
    public CommitVoteRequest withRatingName(String ratingName) {
        setRatingName(ratingName);
        return this;
    }

    /** 投票対象のギャザリング名 */
    private String gatheringName;

    /**
     * 投票対象のギャザリング名を取得
     *
     * @return 投票状況を強制確定
     */
    public String getGatheringName() {
        return gatheringName;
    }

    /**
     * 投票対象のギャザリング名を設定
     *
     * @param gatheringName 投票状況を強制確定
     */
    public void setGatheringName(String gatheringName) {
        this.gatheringName = gatheringName;
    }

    /**
     * 投票対象のギャザリング名を設定
     *
     * @param gatheringName 投票状況を強制確定
     * @return this
     */
    public CommitVoteRequest withGatheringName(String gatheringName) {
        setGatheringName(gatheringName);
        return this;
    }

}