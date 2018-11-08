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

package io.gs2.consumableItem.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 消費型アイテムプール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemPool implements Serializable {

	/** 消費型アイテムプールID */
	private String itemPoolId;

	/** オーナーID */
	private String ownerId;

	/** 消費型アイテムプール名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** アイテム入手時 に実行されるGS2-Script */
	private String acquisitionItemTriggerScript;

	/** アイテム入手完了時 に実行されるGS2-Script */
	private String acquisitionItemDoneTriggerScript;

	/** アイテム消費時 に実行されるGS2-Script */
	private String consumeItemTriggerScript;

	/** アイテム消費完了時 に実行されるGS2-Script */
	private String consumeItemDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 消費型アイテムプールIDを取得
	 *
	 * @return 消費型アイテムプールID
	 */
	public String getItemPoolId() {
		return itemPoolId;
	}

	/**
	 * 消費型アイテムプールIDを設定
	 *
	 * @param itemPoolId 消費型アイテムプールID
	 */
	public void setItemPoolId(String itemPoolId) {
		this.itemPoolId = itemPoolId;
	}

	/**
	 * 消費型アイテムプールIDを設定
	 *
	 * @param itemPoolId 消費型アイテムプールID
	 * @return this
	 */
	public ItemPool withItemPoolId(String itemPoolId) {
		this.itemPoolId = itemPoolId;
		return this;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public ItemPool withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * 消費型アイテムプール名を取得
	 *
	 * @return 消費型アイテムプール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 消費型アイテムプール名を設定
	 *
	 * @param name 消費型アイテムプール名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 消費型アイテムプール名を設定
	 *
	 * @param name 消費型アイテムプール名
	 * @return this
	 */
	public ItemPool withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public ItemPool withDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 * @return this
	 */
	public ItemPool withServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
		return this;
	}

	/**
	 * アイテム入手時 に実行されるGS2-Scriptを取得
	 *
	 * @return アイテム入手時 に実行されるGS2-Script
	 */
	public String getAcquisitionItemTriggerScript() {
		return acquisitionItemTriggerScript;
	}

	/**
	 * アイテム入手時 に実行されるGS2-Scriptを設定
	 *
	 * @param acquisitionItemTriggerScript アイテム入手時 に実行されるGS2-Script
	 */
	public void setAcquisitionItemTriggerScript(String acquisitionItemTriggerScript) {
		this.acquisitionItemTriggerScript = acquisitionItemTriggerScript;
	}

	/**
	 * アイテム入手時 に実行されるGS2-Scriptを設定
	 *
	 * @param acquisitionItemTriggerScript アイテム入手時 に実行されるGS2-Script
	 * @return this
	 */
	public ItemPool withAcquisitionItemTriggerScript(String acquisitionItemTriggerScript) {
		this.acquisitionItemTriggerScript = acquisitionItemTriggerScript;
		return this;
	}

	/**
	 * アイテム入手完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return アイテム入手完了時 に実行されるGS2-Script
	 */
	public String getAcquisitionItemDoneTriggerScript() {
		return acquisitionItemDoneTriggerScript;
	}

	/**
	 * アイテム入手完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param acquisitionItemDoneTriggerScript アイテム入手完了時 に実行されるGS2-Script
	 */
	public void setAcquisitionItemDoneTriggerScript(String acquisitionItemDoneTriggerScript) {
		this.acquisitionItemDoneTriggerScript = acquisitionItemDoneTriggerScript;
	}

	/**
	 * アイテム入手完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param acquisitionItemDoneTriggerScript アイテム入手完了時 に実行されるGS2-Script
	 * @return this
	 */
	public ItemPool withAcquisitionItemDoneTriggerScript(String acquisitionItemDoneTriggerScript) {
		this.acquisitionItemDoneTriggerScript = acquisitionItemDoneTriggerScript;
		return this;
	}

	/**
	 * アイテム消費時 に実行されるGS2-Scriptを取得
	 *
	 * @return アイテム消費時 に実行されるGS2-Script
	 */
	public String getConsumeItemTriggerScript() {
		return consumeItemTriggerScript;
	}

	/**
	 * アイテム消費時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeItemTriggerScript アイテム消費時 に実行されるGS2-Script
	 */
	public void setConsumeItemTriggerScript(String consumeItemTriggerScript) {
		this.consumeItemTriggerScript = consumeItemTriggerScript;
	}

	/**
	 * アイテム消費時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeItemTriggerScript アイテム消費時 に実行されるGS2-Script
	 * @return this
	 */
	public ItemPool withConsumeItemTriggerScript(String consumeItemTriggerScript) {
		this.consumeItemTriggerScript = consumeItemTriggerScript;
		return this;
	}

	/**
	 * アイテム消費完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return アイテム消費完了時 に実行されるGS2-Script
	 */
	public String getConsumeItemDoneTriggerScript() {
		return consumeItemDoneTriggerScript;
	}

	/**
	 * アイテム消費完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeItemDoneTriggerScript アイテム消費完了時 に実行されるGS2-Script
	 */
	public void setConsumeItemDoneTriggerScript(String consumeItemDoneTriggerScript) {
		this.consumeItemDoneTriggerScript = consumeItemDoneTriggerScript;
	}

	/**
	 * アイテム消費完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param consumeItemDoneTriggerScript アイテム消費完了時 に実行されるGS2-Script
	 * @return this
	 */
	public ItemPool withConsumeItemDoneTriggerScript(String consumeItemDoneTriggerScript) {
		this.consumeItemDoneTriggerScript = consumeItemDoneTriggerScript;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public ItemPool withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public ItemPool withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("itemPoolId", this.getItemPoolId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("serviceClass", this.getServiceClass())
            .put("acquisitionItemTriggerScript", this.getAcquisitionItemTriggerScript())
            .put("acquisitionItemDoneTriggerScript", this.getAcquisitionItemDoneTriggerScript())
            .put("consumeItemTriggerScript", this.getConsumeItemTriggerScript())
            .put("consumeItemDoneTriggerScript", this.getConsumeItemDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}