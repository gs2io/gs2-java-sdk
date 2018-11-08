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
 * 消費型アイテム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemMaster implements Serializable {

	/** 消費型アイテムID */
	private String itemId;

	/** 消費型アイテム名 */
	private String name;

	/** 所持数の上限 */
	private Integer max;

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
	 * 消費型アイテムIDを取得
	 *
	 * @return 消費型アイテムID
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * 消費型アイテムIDを設定
	 *
	 * @param itemId 消費型アイテムID
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 消費型アイテムIDを設定
	 *
	 * @param itemId 消費型アイテムID
	 * @return this
	 */
	public ItemMaster withItemId(String itemId) {
		this.itemId = itemId;
		return this;
	}

	/**
	 * 消費型アイテム名を取得
	 *
	 * @return 消費型アイテム名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 消費型アイテム名を設定
	 *
	 * @param name 消費型アイテム名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 消費型アイテム名を設定
	 *
	 * @param name 消費型アイテム名
	 * @return this
	 */
	public ItemMaster withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 所持数の上限を取得
	 *
	 * @return 所持数の上限
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * 所持数の上限を設定
	 *
	 * @param max 所持数の上限
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * 所持数の上限を設定
	 *
	 * @param max 所持数の上限
	 * @return this
	 */
	public ItemMaster withMax(Integer max) {
		this.max = max;
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
	public ItemMaster withAcquisitionItemTriggerScript(String acquisitionItemTriggerScript) {
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
	public ItemMaster withAcquisitionItemDoneTriggerScript(String acquisitionItemDoneTriggerScript) {
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
	public ItemMaster withConsumeItemTriggerScript(String consumeItemTriggerScript) {
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
	public ItemMaster withConsumeItemDoneTriggerScript(String consumeItemDoneTriggerScript) {
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
	public ItemMaster withCreateAt(Integer createAt) {
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
	public ItemMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("itemId", this.getItemId())
            .put("name", this.getName())
            .put("max", this.getMax())
            .put("acquisitionItemTriggerScript", this.getAcquisitionItemTriggerScript())
            .put("acquisitionItemDoneTriggerScript", this.getAcquisitionItemDoneTriggerScript())
            .put("consumeItemTriggerScript", this.getConsumeItemTriggerScript())
            .put("consumeItemDoneTriggerScript", this.getConsumeItemDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}