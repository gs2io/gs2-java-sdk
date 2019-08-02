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
import io.gs2.model.IModel;

/**
 * 景品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Prize implements IModel, Serializable {
	/** 景品の種類 */
	protected String type;

	/**
	 * 景品の種類を取得
	 *
	 * @return 景品の種類
	 */
	public String getType() {
		return type;
	}

	/**
	 * 景品の種類を設定
	 *
	 * @param type 景品の種類
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 景品の種類を設定
	 *
	 * @param type 景品の種類
	 * @return this
	 */
	public Prize withType(String type) {
		this.type = type;
		return this;
	}
	/** 景品の入手アクションリスト */
	protected List<AcquireAction> acquireActions;

	/**
	 * 景品の入手アクションリストを取得
	 *
	 * @return 景品の入手アクションリスト
	 */
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	/**
	 * 景品の入手アクションリストを設定
	 *
	 * @param acquireActions 景品の入手アクションリスト
	 */
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	/**
	 * 景品の入手アクションリストを設定
	 *
	 * @param acquireActions 景品の入手アクションリスト
	 * @return this
	 */
	public Prize withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	/** 排出確率テーブルの名前 */
	protected String prizeTableName;

	/**
	 * 排出確率テーブルの名前を取得
	 *
	 * @return 排出確率テーブルの名前
	 */
	public String getPrizeTableName() {
		return prizeTableName;
	}

	/**
	 * 排出確率テーブルの名前を設定
	 *
	 * @param prizeTableName 排出確率テーブルの名前
	 */
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}

	/**
	 * 排出確率テーブルの名前を設定
	 *
	 * @param prizeTableName 排出確率テーブルの名前
	 * @return this
	 */
	public Prize withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}
	/** 排出重み */
	protected Integer weight;

	/**
	 * 排出重みを取得
	 *
	 * @return 排出重み
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 排出重みを設定
	 *
	 * @param weight 排出重み
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 排出重みを設定
	 *
	 * @param weight 排出重み
	 * @return this
	 */
	public Prize withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> acquireActions = new ArrayList<>();
        if(this.acquireActions != null) {
            for(AcquireAction item : this.acquireActions) {
                acquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("type", this.getType())
            .put("prizeTableName", this.getPrizeTableName())
            .put("weight", this.getWeight());
        body_.set("acquireActions", JsonNodeFactory.instance.arrayNode().addAll(acquireActions));
        return body_;
    }
}