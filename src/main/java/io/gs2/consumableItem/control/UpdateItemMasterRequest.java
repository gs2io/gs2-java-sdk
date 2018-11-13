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

package io.gs2.consumableItem.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.consumableItem.model.*;
import io.gs2.consumableItem.Gs2ConsumableItem;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateItemMasterRequest extends Gs2BasicRequest<UpdateItemMasterRequest> {

	public static class Constant extends Gs2ConsumableItem.Constant {
		public static final String FUNCTION = "UpdateItemMaster";
	}

	/** 消費型アイテムプールの名前 */
	private String itemPoolName;

	/** 消費型アイテムの名前 */
	private String itemName;

	/** 最大所持数。 */
	private Integer max;

	/** アイテム入手時 に実行されるGS2-Script */
	private String acquisitionItemTriggerScript;

	/** アイテム入手完了時 に実行されるGS2-Script */
	private String acquisitionItemDoneTriggerScript;

	/** アイテム消費時 に実行されるGS2-Script */
	private String consumeItemTriggerScript;

	/** アイテム消費完了時 に実行されるGS2-Script */
	private String consumeItemDoneTriggerScript;


	/**
	 * 消費型アイテムプールの名前を取得
	 *
	 * @return 消費型アイテムプールの名前
	 */
	public String getItemPoolName() {
		return itemPoolName;
	}

	/**
	 * 消費型アイテムプールの名前を設定
	 *
	 * @param itemPoolName 消費型アイテムプールの名前
	 */
	public void setItemPoolName(String itemPoolName) {
		this.itemPoolName = itemPoolName;
	}

	/**
	 * 消費型アイテムプールの名前を設定
	 *
	 * @param itemPoolName 消費型アイテムプールの名前
	 * @return this
	 */
	public UpdateItemMasterRequest withItemPoolName(String itemPoolName) {
		setItemPoolName(itemPoolName);
		return this;
	}

	/**
	 * 消費型アイテムの名前を取得
	 *
	 * @return 消費型アイテムの名前
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 消費型アイテムの名前を設定
	 *
	 * @param itemName 消費型アイテムの名前
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 消費型アイテムの名前を設定
	 *
	 * @param itemName 消費型アイテムの名前
	 * @return this
	 */
	public UpdateItemMasterRequest withItemName(String itemName) {
		setItemName(itemName);
		return this;
	}

	/**
	 * 最大所持数。を取得
	 *
	 * @return 最大所持数。
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * 最大所持数。を設定
	 *
	 * @param max 最大所持数。
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * 最大所持数。を設定
	 *
	 * @param max 最大所持数。
	 * @return this
	 */
	public UpdateItemMasterRequest withMax(Integer max) {
		setMax(max);
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
	public UpdateItemMasterRequest withAcquisitionItemTriggerScript(String acquisitionItemTriggerScript) {
		setAcquisitionItemTriggerScript(acquisitionItemTriggerScript);
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
	public UpdateItemMasterRequest withAcquisitionItemDoneTriggerScript(String acquisitionItemDoneTriggerScript) {
		setAcquisitionItemDoneTriggerScript(acquisitionItemDoneTriggerScript);
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
	public UpdateItemMasterRequest withConsumeItemTriggerScript(String consumeItemTriggerScript) {
		setConsumeItemTriggerScript(consumeItemTriggerScript);
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
	public UpdateItemMasterRequest withConsumeItemDoneTriggerScript(String consumeItemDoneTriggerScript) {
		setConsumeItemDoneTriggerScript(consumeItemDoneTriggerScript);
		return this;
	}

}