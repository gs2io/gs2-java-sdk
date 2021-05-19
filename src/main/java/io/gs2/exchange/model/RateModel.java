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

package io.gs2.exchange.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 交換レートモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RateModel implements IModel, Serializable, Comparable<RateModel> {
	/** 交換レートマスター */
	protected String rateModelId;

	/**
	 * 交換レートマスターを取得
	 *
	 * @return 交換レートマスター
	 */
	public String getRateModelId() {
		return rateModelId;
	}

	/**
	 * 交換レートマスターを設定
	 *
	 * @param rateModelId 交換レートマスター
	 */
	public void setRateModelId(String rateModelId) {
		this.rateModelId = rateModelId;
	}

	/**
	 * 交換レートマスターを設定
	 *
	 * @param rateModelId 交換レートマスター
	 * @return this
	 */
	public RateModel withRateModelId(String rateModelId) {
		this.rateModelId = rateModelId;
		return this;
	}
	/** 交換レートの種類名 */
	protected String name;

	/**
	 * 交換レートの種類名を取得
	 *
	 * @return 交換レートの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 交換レートの種類名を設定
	 *
	 * @param name 交換レートの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 交換レートの種類名を設定
	 *
	 * @param name 交換レートの種類名
	 * @return this
	 */
	public RateModel withName(String name) {
		this.name = name;
		return this;
	}
	/** 交換レートの種類のメタデータ */
	protected String metadata;

	/**
	 * 交換レートの種類のメタデータを取得
	 *
	 * @return 交換レートの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 交換レートの種類のメタデータを設定
	 *
	 * @param metadata 交換レートの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 交換レートの種類のメタデータを設定
	 *
	 * @param metadata 交換レートの種類のメタデータ
	 * @return this
	 */
	public RateModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 消費アクションリスト */
	protected List<ConsumeAction> consumeActions;

	/**
	 * 消費アクションリストを取得
	 *
	 * @return 消費アクションリスト
	 */
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}

	/**
	 * 消費アクションリストを設定
	 *
	 * @param consumeActions 消費アクションリスト
	 */
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}

	/**
	 * 消費アクションリストを設定
	 *
	 * @param consumeActions 消費アクションリスト
	 * @return this
	 */
	public RateModel withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	/** 交換の種類 */
	protected String timingType;

	/**
	 * 交換の種類を取得
	 *
	 * @return 交換の種類
	 */
	public String getTimingType() {
		return timingType;
	}

	/**
	 * 交換の種類を設定
	 *
	 * @param timingType 交換の種類
	 */
	public void setTimingType(String timingType) {
		this.timingType = timingType;
	}

	/**
	 * 交換の種類を設定
	 *
	 * @param timingType 交換の種類
	 * @return this
	 */
	public RateModel withTimingType(String timingType) {
		this.timingType = timingType;
		return this;
	}
	/** 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分） */
	protected Integer lockTime;

	/**
	 * 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）を取得
	 *
	 * @return 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）
	 */
	public Integer getLockTime() {
		return lockTime;
	}

	/**
	 * 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）を設定
	 *
	 * @param lockTime 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）
	 */
	public void setLockTime(Integer lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）を設定
	 *
	 * @param lockTime 交換実行から実際に報酬を受け取れるようになるまでの待ち時間（分）
	 * @return this
	 */
	public RateModel withLockTime(Integer lockTime) {
		this.lockTime = lockTime;
		return this;
	}
	/** スキップをすることができるか */
	protected Boolean enableSkip;

	/**
	 * スキップをすることができるかを取得
	 *
	 * @return スキップをすることができるか
	 */
	public Boolean getEnableSkip() {
		return enableSkip;
	}

	/**
	 * スキップをすることができるかを設定
	 *
	 * @param enableSkip スキップをすることができるか
	 */
	public void setEnableSkip(Boolean enableSkip) {
		this.enableSkip = enableSkip;
	}

	/**
	 * スキップをすることができるかを設定
	 *
	 * @param enableSkip スキップをすることができるか
	 * @return this
	 */
	public RateModel withEnableSkip(Boolean enableSkip) {
		this.enableSkip = enableSkip;
		return this;
	}
	/** 時短消費アクションリスト */
	protected List<ConsumeAction> skipConsumeActions;

	/**
	 * 時短消費アクションリストを取得
	 *
	 * @return 時短消費アクションリスト
	 */
	public List<ConsumeAction> getSkipConsumeActions() {
		return skipConsumeActions;
	}

	/**
	 * 時短消費アクションリストを設定
	 *
	 * @param skipConsumeActions 時短消費アクションリスト
	 */
	public void setSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
		this.skipConsumeActions = skipConsumeActions;
	}

	/**
	 * 時短消費アクションリストを設定
	 *
	 * @param skipConsumeActions 時短消費アクションリスト
	 * @return this
	 */
	public RateModel withSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
		this.skipConsumeActions = skipConsumeActions;
		return this;
	}
	/** 入手アクションリスト */
	protected List<AcquireAction> acquireActions;

	/**
	 * 入手アクションリストを取得
	 *
	 * @return 入手アクションリスト
	 */
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	/**
	 * 入手アクションリストを設定
	 *
	 * @param acquireActions 入手アクションリスト
	 */
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	/**
	 * 入手アクションリストを設定
	 *
	 * @param acquireActions 入手アクションリスト
	 * @return this
	 */
	public RateModel withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> consumeActions = new ArrayList<>();
        if(this.consumeActions != null) {
            for(ConsumeAction item : this.consumeActions) {
                consumeActions.add(item.toJson());
            }
        }
        List<JsonNode> skipConsumeActions = new ArrayList<>();
        if(this.skipConsumeActions != null) {
            for(ConsumeAction item : this.skipConsumeActions) {
                skipConsumeActions.add(item.toJson());
            }
        }
        List<JsonNode> acquireActions = new ArrayList<>();
        if(this.acquireActions != null) {
            for(AcquireAction item : this.acquireActions) {
                acquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("rateModelId", this.getRateModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("timingType", this.getTimingType())
            .put("lockTime", this.getLockTime())
            .put("enableSkip", this.getEnableSkip());
        body_.set("consumeActions", JsonNodeFactory.instance.arrayNode().addAll(consumeActions));
        body_.set("skipConsumeActions", JsonNodeFactory.instance.arrayNode().addAll(skipConsumeActions));
        body_.set("acquireActions", JsonNodeFactory.instance.arrayNode().addAll(acquireActions));
        return body_;
    }
	@Override
	public int compareTo(RateModel o) {
		return rateModelId.compareTo(o.rateModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rateModelId == null) ? 0 : this.rateModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.timingType == null) ? 0 : this.timingType.hashCode());
        result = prime * result + ((this.lockTime == null) ? 0 : this.lockTime.hashCode());
        result = prime * result + ((this.enableSkip == null) ? 0 : this.enableSkip.hashCode());
        result = prime * result + ((this.skipConsumeActions == null) ? 0 : this.skipConsumeActions.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
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
		RateModel other = (RateModel) o;
		if (rateModelId == null) {
			return other.rateModelId == null;
		} else if (!rateModelId.equals(other.rateModelId)) {
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
		if (consumeActions == null) {
			return other.consumeActions == null;
		} else if (!consumeActions.equals(other.consumeActions)) {
			return false;
		}
		if (timingType == null) {
			return other.timingType == null;
		} else if (!timingType.equals(other.timingType)) {
			return false;
		}
		if (lockTime == null) {
			return other.lockTime == null;
		} else if (!lockTime.equals(other.lockTime)) {
			return false;
		}
		if (enableSkip == null) {
			return other.enableSkip == null;
		} else if (!enableSkip.equals(other.enableSkip)) {
			return false;
		}
		if (skipConsumeActions == null) {
			return other.skipConsumeActions == null;
		} else if (!skipConsumeActions.equals(other.skipConsumeActions)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
			return false;
		}
		return true;
	}
}