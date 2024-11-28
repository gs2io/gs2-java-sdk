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

package io.gs2.seasonRating.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WrittenBallot implements IModel, Serializable {
	private Ballot ballot;
	private List<GameResult> gameResults;
	public Ballot getBallot() {
		return ballot;
	}
	public void setBallot(Ballot ballot) {
		this.ballot = ballot;
	}
	public WrittenBallot withBallot(Ballot ballot) {
		this.ballot = ballot;
		return this;
	}
	public List<GameResult> getGameResults() {
		return gameResults;
	}
	public void setGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
	}
	public WrittenBallot withGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
		return this;
	}

    public static WrittenBallot fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WrittenBallot()
            .withBallot(data.get("ballot") == null || data.get("ballot").isNull() ? null : Ballot.fromJson(data.get("ballot")))
            .withGameResults(data.get("gameResults") == null || data.get("gameResults").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("gameResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return GameResult.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("ballot", getBallot() != null ? getBallot().toJson() : null);
                put("gameResults", getGameResults() == null ? null :
                    getGameResults().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
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