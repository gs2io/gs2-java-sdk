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

package io.gs2.quest.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * コンテンツ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Contents implements IModel, Serializable {
	/** クエストモデルのメタデータ */
	protected String metadata;

	/**
	 * クエストモデルのメタデータを取得
	 *
	 * @return クエストモデルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * クエストモデルのメタデータを設定
	 *
	 * @param metadata クエストモデルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * クエストモデルのメタデータを設定
	 *
	 * @param metadata クエストモデルのメタデータ
	 * @return this
	 */
	public Contents withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** クエストクリア時の報酬 */
	protected List<AcquireAction> completeAcquireActions;

	/**
	 * クエストクリア時の報酬を取得
	 *
	 * @return クエストクリア時の報酬
	 */
	public List<AcquireAction> getCompleteAcquireActions() {
		return completeAcquireActions;
	}

	/**
	 * クエストクリア時の報酬を設定
	 *
	 * @param completeAcquireActions クエストクリア時の報酬
	 */
	public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
	}

	/**
	 * クエストクリア時の報酬を設定
	 *
	 * @param completeAcquireActions クエストクリア時の報酬
	 * @return this
	 */
	public Contents withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
		return this;
	}
	/** 抽選する重み */
	protected Integer weight;

	/**
	 * 抽選する重みを取得
	 *
	 * @return 抽選する重み
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 抽選する重みを設定
	 *
	 * @param weight 抽選する重み
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 抽選する重みを設定
	 *
	 * @param weight 抽選する重み
	 * @return this
	 */
	public Contents withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> completeAcquireActions = new ArrayList<>();
        if(this.completeAcquireActions != null) {
            for(AcquireAction item : this.completeAcquireActions) {
                completeAcquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("metadata", this.getMetadata())
            .put("weight", this.getWeight());
        body_.set("completeAcquireActions", JsonNodeFactory.instance.arrayNode().addAll(completeAcquireActions));
        return body_;
    }
}