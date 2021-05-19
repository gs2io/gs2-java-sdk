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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 投票用紙
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Ballot implements IModel, Serializable {
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Ballot withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** レーティング計算に使用するレーティング名 */
	protected String ratingName;

	/**
	 * レーティング計算に使用するレーティング名を取得
	 *
	 * @return レーティング計算に使用するレーティング名
	 */
	public String getRatingName() {
		return ratingName;
	}

	/**
	 * レーティング計算に使用するレーティング名を設定
	 *
	 * @param ratingName レーティング計算に使用するレーティング名
	 */
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	/**
	 * レーティング計算に使用するレーティング名を設定
	 *
	 * @param ratingName レーティング計算に使用するレーティング名
	 * @return this
	 */
	public Ballot withRatingName(String ratingName) {
		this.ratingName = ratingName;
		return this;
	}
	/** 投票対象のギャザリング名 */
	protected String gatheringName;

	/**
	 * 投票対象のギャザリング名を取得
	 *
	 * @return 投票対象のギャザリング名
	 */
	public String getGatheringName() {
		return gatheringName;
	}

	/**
	 * 投票対象のギャザリング名を設定
	 *
	 * @param gatheringName 投票対象のギャザリング名
	 */
	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}

	/**
	 * 投票対象のギャザリング名を設定
	 *
	 * @param gatheringName 投票対象のギャザリング名
	 * @return this
	 */
	public Ballot withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}
	/** 参加人数 */
	protected Integer numberOfPlayer;

	/**
	 * 参加人数を取得
	 *
	 * @return 参加人数
	 */
	public Integer getNumberOfPlayer() {
		return numberOfPlayer;
	}

	/**
	 * 参加人数を設定
	 *
	 * @param numberOfPlayer 参加人数
	 */
	public void setNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	/**
	 * 参加人数を設定
	 *
	 * @param numberOfPlayer 参加人数
	 * @return this
	 */
	public Ballot withNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("userId", this.getUserId())
            .put("ratingName", this.getRatingName())
            .put("gatheringName", this.getGatheringName())
            .put("numberOfPlayer", this.getNumberOfPlayer());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.ratingName == null) ? 0 : this.ratingName.hashCode());
        result = prime * result + ((this.gatheringName == null) ? 0 : this.gatheringName.hashCode());
        result = prime * result + ((this.numberOfPlayer == null) ? 0 : this.numberOfPlayer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Ballot other = (Ballot) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (ratingName == null) {
			return other.ratingName == null;
		} else if (!ratingName.equals(other.ratingName)) {
			return false;
		}
		if (gatheringName == null) {
			return other.gatheringName == null;
		} else if (!gatheringName.equals(other.gatheringName)) {
			return false;
		}
		if (numberOfPlayer == null) {
			return other.numberOfPlayer == null;
		} else if (!numberOfPlayer.equals(other.numberOfPlayer)) {
			return false;
		}
		return true;
	}
}