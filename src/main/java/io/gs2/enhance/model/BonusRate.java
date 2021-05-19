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

package io.gs2.enhance.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 消費アクション
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BonusRate implements IModel, Serializable {
	/** 経験値ボーナスの倍率(1.0=ボーナスなし) */
	protected Float rate;

	/**
	 * 経験値ボーナスの倍率(1.0=ボーナスなし)を取得
	 *
	 * @return 経験値ボーナスの倍率(1.0=ボーナスなし)
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * 経験値ボーナスの倍率(1.0=ボーナスなし)を設定
	 *
	 * @param rate 経験値ボーナスの倍率(1.0=ボーナスなし)
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * 経験値ボーナスの倍率(1.0=ボーナスなし)を設定
	 *
	 * @param rate 経験値ボーナスの倍率(1.0=ボーナスなし)
	 * @return this
	 */
	public BonusRate withRate(Float rate) {
		this.rate = rate;
		return this;
	}
	/** 抽選重み */
	protected Integer weight;

	/**
	 * 抽選重みを取得
	 *
	 * @return 抽選重み
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 抽選重みを設定
	 *
	 * @param weight 抽選重み
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 抽選重みを設定
	 *
	 * @param weight 抽選重み
	 * @return this
	 */
	public BonusRate withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("rate", this.getRate())
            .put("weight", this.getWeight());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rate == null) ? 0 : this.rate.hashCode());
        result = prime * result + ((this.weight == null) ? 0 : this.weight.hashCode());
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
		BonusRate other = (BonusRate) o;
		if (rate == null) {
			return other.rate == null;
		} else if (!rate.equals(other.rate)) {
			return false;
		}
		if (weight == null) {
			return other.weight == null;
		} else if (!weight.equals(other.weight)) {
			return false;
		}
		return true;
	}
}