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

package io.gs2.mission.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * カウンターの種類
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CounterModel implements IModel, Serializable, Comparable<CounterModel> {
	/** カウンターの種類 */
	protected String counterId;

	/**
	 * カウンターの種類を取得
	 *
	 * @return カウンターの種類
	 */
	public String getCounterId() {
		return counterId;
	}

	/**
	 * カウンターの種類を設定
	 *
	 * @param counterId カウンターの種類
	 */
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	/**
	 * カウンターの種類を設定
	 *
	 * @param counterId カウンターの種類
	 * @return this
	 */
	public CounterModel withCounterId(String counterId) {
		this.counterId = counterId;
		return this;
	}
	/** カウンター名 */
	protected String name;

	/**
	 * カウンター名を取得
	 *
	 * @return カウンター名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カウンター名を設定
	 *
	 * @param name カウンター名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カウンター名を設定
	 *
	 * @param name カウンター名
	 * @return this
	 */
	public CounterModel withName(String name) {
		this.name = name;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public CounterModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** カウンターのリセットタイミング */
	protected List<CounterScopeModel> scopes;

	/**
	 * カウンターのリセットタイミングを取得
	 *
	 * @return カウンターのリセットタイミング
	 */
	public List<CounterScopeModel> getScopes() {
		return scopes;
	}

	/**
	 * カウンターのリセットタイミングを設定
	 *
	 * @param scopes カウンターのリセットタイミング
	 */
	public void setScopes(List<CounterScopeModel> scopes) {
		this.scopes = scopes;
	}

	/**
	 * カウンターのリセットタイミングを設定
	 *
	 * @param scopes カウンターのリセットタイミング
	 * @return this
	 */
	public CounterModel withScopes(List<CounterScopeModel> scopes) {
		this.scopes = scopes;
		return this;
	}
	/** カウントアップ可能な期間を指定するイベントマスター のGRN */
	protected String challengePeriodEventId;

	/**
	 * カウントアップ可能な期間を指定するイベントマスター のGRNを取得
	 *
	 * @return カウントアップ可能な期間を指定するイベントマスター のGRN
	 */
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}

	/**
	 * カウントアップ可能な期間を指定するイベントマスター のGRNを設定
	 *
	 * @param challengePeriodEventId カウントアップ可能な期間を指定するイベントマスター のGRN
	 */
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}

	/**
	 * カウントアップ可能な期間を指定するイベントマスター のGRNを設定
	 *
	 * @param challengePeriodEventId カウントアップ可能な期間を指定するイベントマスター のGRN
	 * @return this
	 */
	public CounterModel withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> scopes = new ArrayList<>();
        if(this.scopes != null) {
            for(CounterScopeModel item : this.scopes) {
                scopes.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("counterId", this.getCounterId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("challengePeriodEventId", this.getChallengePeriodEventId());
        body_.set("scopes", JsonNodeFactory.instance.arrayNode().addAll(scopes));
        return body_;
    }
	@Override
	public int compareTo(CounterModel o) {
		return counterId.compareTo(o.counterId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.counterId == null) ? 0 : this.counterId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.scopes == null) ? 0 : this.scopes.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
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
		CounterModel other = (CounterModel) o;
		if (counterId == null) {
			return other.counterId == null;
		} else if (!counterId.equals(other.counterId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (scopes == null) {
			return other.scopes == null;
		} else if (!scopes.equals(other.scopes)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		return true;
	}
}