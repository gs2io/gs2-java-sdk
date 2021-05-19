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
 * 投票結果
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WrittenBallot implements IModel, Serializable {
	/** 投票用紙 */
	protected Ballot ballot;

	/**
	 * 投票用紙を取得
	 *
	 * @return 投票用紙
	 */
	public Ballot getBallot() {
		return ballot;
	}

	/**
	 * 投票用紙を設定
	 *
	 * @param ballot 投票用紙
	 */
	public void setBallot(Ballot ballot) {
		this.ballot = ballot;
	}

	/**
	 * 投票用紙を設定
	 *
	 * @param ballot 投票用紙
	 * @return this
	 */
	public WrittenBallot withBallot(Ballot ballot) {
		this.ballot = ballot;
		return this;
	}
	/** 投票内容。対戦結果のリスト */
	protected List<GameResult> gameResults;

	/**
	 * 投票内容。対戦結果のリストを取得
	 *
	 * @return 投票内容。対戦結果のリスト
	 */
	public List<GameResult> getGameResults() {
		return gameResults;
	}

	/**
	 * 投票内容。対戦結果のリストを設定
	 *
	 * @param gameResults 投票内容。対戦結果のリスト
	 */
	public void setGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
	}

	/**
	 * 投票内容。対戦結果のリストを設定
	 *
	 * @param gameResults 投票内容。対戦結果のリスト
	 * @return this
	 */
	public WrittenBallot withGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode ballot = this.getBallot().toJson();
        List<JsonNode> gameResults = new ArrayList<>();
        if(this.gameResults != null) {
            for(GameResult item : this.gameResults) {
                gameResults.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode();
        body_.set("ballot", ballot);
        body_.set("gameResults", JsonNodeFactory.instance.arrayNode().addAll(gameResults));
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ballot == null) ? 0 : this.ballot.hashCode());
        result = prime * result + ((this.gameResults == null) ? 0 : this.gameResults.hashCode());
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
		WrittenBallot other = (WrittenBallot) o;
		if (ballot == null) {
			return other.ballot == null;
		} else if (!ballot.equals(other.ballot)) {
			return false;
		}
		if (gameResults == null) {
			return other.gameResults == null;
		} else if (!gameResults.equals(other.gameResults)) {
			return false;
		}
		return true;
	}
}