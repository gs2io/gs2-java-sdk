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

package io.gs2.lottery.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 排出レート
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Probability implements IModel, Serializable {
	/** 景品の種類 */
	protected DrawnPrize prize;

	/**
	 * 景品の種類を取得
	 *
	 * @return 景品の種類
	 */
	public DrawnPrize getPrize() {
		return prize;
	}

	/**
	 * 景品の種類を設定
	 *
	 * @param prize 景品の種類
	 */
	public void setPrize(DrawnPrize prize) {
		this.prize = prize;
	}

	/**
	 * 景品の種類を設定
	 *
	 * @param prize 景品の種類
	 * @return this
	 */
	public Probability withPrize(DrawnPrize prize) {
		this.prize = prize;
		return this;
	}
	/** 排出確率(0.0〜1.0) */
	protected Float rate;

	/**
	 * 排出確率(0.0〜1.0)を取得
	 *
	 * @return 排出確率(0.0〜1.0)
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * 排出確率(0.0〜1.0)を設定
	 *
	 * @param rate 排出確率(0.0〜1.0)
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * 排出確率(0.0〜1.0)を設定
	 *
	 * @param rate 排出確率(0.0〜1.0)
	 * @return this
	 */
	public Probability withRate(Float rate) {
		this.rate = rate;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode prize = this.getPrize().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("rate", this.getRate());
        body_.set("prize", prize);
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prize == null) ? 0 : this.prize.hashCode());
        result = prime * result + ((this.rate == null) ? 0 : this.rate.hashCode());
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
		Probability other = (Probability) o;
		if (prize == null) {
			return other.prize == null;
		} else if (!prize.equals(other.prize)) {
			return false;
		}
		if (rate == null) {
			return other.rate == null;
		} else if (!rate.equals(other.rate)) {
			return false;
		}
		return true;
	}
}