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
 * 投票状況
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Vote implements IModel, Serializable, Comparable<Vote> {
	/** 投票状況 */
	protected String voteId;

	/**
	 * 投票状況を取得
	 *
	 * @return 投票状況
	 */
	public String getVoteId() {
		return voteId;
	}

	/**
	 * 投票状況を設定
	 *
	 * @param voteId 投票状況
	 */
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	/**
	 * 投票状況を設定
	 *
	 * @param voteId 投票状況
	 * @return this
	 */
	public Vote withVoteId(String voteId) {
		this.voteId = voteId;
		return this;
	}
	/** レーティング名 */
	protected String ratingName;

	/**
	 * レーティング名を取得
	 *
	 * @return レーティング名
	 */
	public String getRatingName() {
		return ratingName;
	}

	/**
	 * レーティング名を設定
	 *
	 * @param ratingName レーティング名
	 */
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	/**
	 * レーティング名を設定
	 *
	 * @param ratingName レーティング名
	 * @return this
	 */
	public Vote withRatingName(String ratingName) {
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
	public Vote withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}
	/** 投票用紙のリスト */
	protected List<WrittenBallot> writtenBallots;

	/**
	 * 投票用紙のリストを取得
	 *
	 * @return 投票用紙のリスト
	 */
	public List<WrittenBallot> getWrittenBallots() {
		return writtenBallots;
	}

	/**
	 * 投票用紙のリストを設定
	 *
	 * @param writtenBallots 投票用紙のリスト
	 */
	public void setWrittenBallots(List<WrittenBallot> writtenBallots) {
		this.writtenBallots = writtenBallots;
	}

	/**
	 * 投票用紙のリストを設定
	 *
	 * @param writtenBallots 投票用紙のリスト
	 * @return this
	 */
	public Vote withWrittenBallots(List<WrittenBallot> writtenBallots) {
		this.writtenBallots = writtenBallots;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Vote withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Vote withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> writtenBallots = new ArrayList<>();
        if(this.writtenBallots != null) {
            for(WrittenBallot item : this.writtenBallots) {
                writtenBallots.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("voteId", this.getVoteId())
            .put("ratingName", this.getRatingName())
            .put("gatheringName", this.getGatheringName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("writtenBallots", JsonNodeFactory.instance.arrayNode().addAll(writtenBallots));
        return body_;
    }
	@Override
	public int compareTo(Vote o) {
		return voteId.compareTo(o.voteId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.voteId == null) ? 0 : this.voteId.hashCode());
        result = prime * result + ((this.ratingName == null) ? 0 : this.ratingName.hashCode());
        result = prime * result + ((this.gatheringName == null) ? 0 : this.gatheringName.hashCode());
        result = prime * result + ((this.writtenBallots == null) ? 0 : this.writtenBallots.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Vote other = (Vote) o;
		if (voteId == null) {
			return other.voteId == null;
		} else if (!voteId.equals(other.voteId)) {
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
		if (writtenBallots == null) {
			return other.writtenBallots == null;
		} else if (!writtenBallots.equals(other.writtenBallots)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}