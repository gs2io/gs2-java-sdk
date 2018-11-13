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

package io.gs2.showcase.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 商品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item implements Serializable {

	/** 陳列商品ID */
	private String showcaseItemId;

	/** 商品名 */
	private String name;

	/** メタデータ */
	private String meta;

	/** 販売通貨 */
	private String currencyType;

	/** GS2-Money 課金通貨名 */
	private String currencyMoneyName;

	/** GS2-Gold 通貨プール名 */
	private String currencyGoldPoolName;

	/** GS2-Gold 通貨名 */
	private String currencyGoldName;

	/** GS2-ConsumableItem アイテムプール名 */
	private String currencyConsumableItemItemPoolName;

	/** GS2-ConsumableItem アイテム名 */
	private String currencyConsumableItemName;

	/** 対価消費処理にまつわるオプション値 */
	private String currencyOption;

	/** 販売価格 */
	private Float price;

	/** 入手アイテムの種類 */
	private String itemType;

	/** GS2-Money 課金通貨名 */
	private String itemMoneyName;

	/** GS2-Gold 通貨プール名 */
	private String itemGoldPoolName;

	/** GS2-Gold 通貨名 */
	private String itemGoldName;

	/** GS2-Stamina スタミナプール名 */
	private String itemStaminaStaminaPoolName;

	/** GS2-ConsumableItem アイテムプール名 */
	private String itemConsumableItemItemPoolName;

	/** GS2-ConsumableItem アイテム名 */
	private String itemConsumableItemItemName;

	/** GS2-Gacha ガチャプール名 */
	private String itemGachaGachaPoolName;

	/** GS2-Gacha ガチャ名 */
	private String itemGachaGachaName;

	/** 入手数量 */
	private Integer itemAmount;

	/** アイテムの入手処理にまつわるオプション値 */
	private String itemOption;

	/** 購入可能か */
	private Boolean canBuy;


	/**
	 * 陳列商品IDを取得
	 *
	 * @return 陳列商品ID
	 */
	public String getShowcaseItemId() {
		return showcaseItemId;
	}

	/**
	 * 陳列商品IDを設定
	 *
	 * @param showcaseItemId 陳列商品ID
	 */
	public void setShowcaseItemId(String showcaseItemId) {
		this.showcaseItemId = showcaseItemId;
	}

	/**
	 * 陳列商品IDを設定
	 *
	 * @param showcaseItemId 陳列商品ID
	 * @return this
	 */
	public Item withShowcaseItemId(String showcaseItemId) {
		this.showcaseItemId = showcaseItemId;
		return this;
	}

	/**
	 * 商品名を取得
	 *
	 * @return 商品名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名を設定
	 *
	 * @param name 商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品名を設定
	 *
	 * @param name 商品名
	 * @return this
	 */
	public Item withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 * @return this
	 */
	public Item withMeta(String meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * 販売通貨を取得
	 *
	 * @return 販売通貨
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * 販売通貨を設定
	 *
	 * @param currencyType 販売通貨
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * 販売通貨を設定
	 *
	 * @param currencyType 販売通貨
	 * @return this
	 */
	public Item withCurrencyType(String currencyType) {
		this.currencyType = currencyType;
		return this;
	}

	/**
	 * GS2-Money 課金通貨名を取得
	 *
	 * @return GS2-Money 課金通貨名
	 */
	public String getCurrencyMoneyName() {
		return currencyMoneyName;
	}

	/**
	 * GS2-Money 課金通貨名を設定
	 *
	 * @param currencyMoneyName GS2-Money 課金通貨名
	 */
	public void setCurrencyMoneyName(String currencyMoneyName) {
		this.currencyMoneyName = currencyMoneyName;
	}

	/**
	 * GS2-Money 課金通貨名を設定
	 *
	 * @param currencyMoneyName GS2-Money 課金通貨名
	 * @return this
	 */
	public Item withCurrencyMoneyName(String currencyMoneyName) {
		this.currencyMoneyName = currencyMoneyName;
		return this;
	}

	/**
	 * GS2-Gold 通貨プール名を取得
	 *
	 * @return GS2-Gold 通貨プール名
	 */
	public String getCurrencyGoldPoolName() {
		return currencyGoldPoolName;
	}

	/**
	 * GS2-Gold 通貨プール名を設定
	 *
	 * @param currencyGoldPoolName GS2-Gold 通貨プール名
	 */
	public void setCurrencyGoldPoolName(String currencyGoldPoolName) {
		this.currencyGoldPoolName = currencyGoldPoolName;
	}

	/**
	 * GS2-Gold 通貨プール名を設定
	 *
	 * @param currencyGoldPoolName GS2-Gold 通貨プール名
	 * @return this
	 */
	public Item withCurrencyGoldPoolName(String currencyGoldPoolName) {
		this.currencyGoldPoolName = currencyGoldPoolName;
		return this;
	}

	/**
	 * GS2-Gold 通貨名を取得
	 *
	 * @return GS2-Gold 通貨名
	 */
	public String getCurrencyGoldName() {
		return currencyGoldName;
	}

	/**
	 * GS2-Gold 通貨名を設定
	 *
	 * @param currencyGoldName GS2-Gold 通貨名
	 */
	public void setCurrencyGoldName(String currencyGoldName) {
		this.currencyGoldName = currencyGoldName;
	}

	/**
	 * GS2-Gold 通貨名を設定
	 *
	 * @param currencyGoldName GS2-Gold 通貨名
	 * @return this
	 */
	public Item withCurrencyGoldName(String currencyGoldName) {
		this.currencyGoldName = currencyGoldName;
		return this;
	}

	/**
	 * GS2-ConsumableItem アイテムプール名を取得
	 *
	 * @return GS2-ConsumableItem アイテムプール名
	 */
	public String getCurrencyConsumableItemItemPoolName() {
		return currencyConsumableItemItemPoolName;
	}

	/**
	 * GS2-ConsumableItem アイテムプール名を設定
	 *
	 * @param currencyConsumableItemItemPoolName GS2-ConsumableItem アイテムプール名
	 */
	public void setCurrencyConsumableItemItemPoolName(String currencyConsumableItemItemPoolName) {
		this.currencyConsumableItemItemPoolName = currencyConsumableItemItemPoolName;
	}

	/**
	 * GS2-ConsumableItem アイテムプール名を設定
	 *
	 * @param currencyConsumableItemItemPoolName GS2-ConsumableItem アイテムプール名
	 * @return this
	 */
	public Item withCurrencyConsumableItemItemPoolName(String currencyConsumableItemItemPoolName) {
		this.currencyConsumableItemItemPoolName = currencyConsumableItemItemPoolName;
		return this;
	}

	/**
	 * GS2-ConsumableItem アイテム名を取得
	 *
	 * @return GS2-ConsumableItem アイテム名
	 */
	public String getCurrencyConsumableItemName() {
		return currencyConsumableItemName;
	}

	/**
	 * GS2-ConsumableItem アイテム名を設定
	 *
	 * @param currencyConsumableItemName GS2-ConsumableItem アイテム名
	 */
	public void setCurrencyConsumableItemName(String currencyConsumableItemName) {
		this.currencyConsumableItemName = currencyConsumableItemName;
	}

	/**
	 * GS2-ConsumableItem アイテム名を設定
	 *
	 * @param currencyConsumableItemName GS2-ConsumableItem アイテム名
	 * @return this
	 */
	public Item withCurrencyConsumableItemName(String currencyConsumableItemName) {
		this.currencyConsumableItemName = currencyConsumableItemName;
		return this;
	}

	/**
	 * 対価消費処理にまつわるオプション値を取得
	 *
	 * @return 対価消費処理にまつわるオプション値
	 */
	public String getCurrencyOption() {
		return currencyOption;
	}

	/**
	 * 対価消費処理にまつわるオプション値を設定
	 *
	 * @param currencyOption 対価消費処理にまつわるオプション値
	 */
	public void setCurrencyOption(String currencyOption) {
		this.currencyOption = currencyOption;
	}

	/**
	 * 対価消費処理にまつわるオプション値を設定
	 *
	 * @param currencyOption 対価消費処理にまつわるオプション値
	 * @return this
	 */
	public Item withCurrencyOption(String currencyOption) {
		this.currencyOption = currencyOption;
		return this;
	}

	/**
	 * 販売価格を取得
	 *
	 * @return 販売価格
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * 販売価格を設定
	 *
	 * @param price 販売価格
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * 販売価格を設定
	 *
	 * @param price 販売価格
	 * @return this
	 */
	public Item withPrice(Float price) {
		this.price = price;
		return this;
	}

	/**
	 * 入手アイテムの種類を取得
	 *
	 * @return 入手アイテムの種類
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * 入手アイテムの種類を設定
	 *
	 * @param itemType 入手アイテムの種類
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * 入手アイテムの種類を設定
	 *
	 * @param itemType 入手アイテムの種類
	 * @return this
	 */
	public Item withItemType(String itemType) {
		this.itemType = itemType;
		return this;
	}

	/**
	 * GS2-Money 課金通貨名を取得
	 *
	 * @return GS2-Money 課金通貨名
	 */
	public String getItemMoneyName() {
		return itemMoneyName;
	}

	/**
	 * GS2-Money 課金通貨名を設定
	 *
	 * @param itemMoneyName GS2-Money 課金通貨名
	 */
	public void setItemMoneyName(String itemMoneyName) {
		this.itemMoneyName = itemMoneyName;
	}

	/**
	 * GS2-Money 課金通貨名を設定
	 *
	 * @param itemMoneyName GS2-Money 課金通貨名
	 * @return this
	 */
	public Item withItemMoneyName(String itemMoneyName) {
		this.itemMoneyName = itemMoneyName;
		return this;
	}

	/**
	 * GS2-Gold 通貨プール名を取得
	 *
	 * @return GS2-Gold 通貨プール名
	 */
	public String getItemGoldPoolName() {
		return itemGoldPoolName;
	}

	/**
	 * GS2-Gold 通貨プール名を設定
	 *
	 * @param itemGoldPoolName GS2-Gold 通貨プール名
	 */
	public void setItemGoldPoolName(String itemGoldPoolName) {
		this.itemGoldPoolName = itemGoldPoolName;
	}

	/**
	 * GS2-Gold 通貨プール名を設定
	 *
	 * @param itemGoldPoolName GS2-Gold 通貨プール名
	 * @return this
	 */
	public Item withItemGoldPoolName(String itemGoldPoolName) {
		this.itemGoldPoolName = itemGoldPoolName;
		return this;
	}

	/**
	 * GS2-Gold 通貨名を取得
	 *
	 * @return GS2-Gold 通貨名
	 */
	public String getItemGoldName() {
		return itemGoldName;
	}

	/**
	 * GS2-Gold 通貨名を設定
	 *
	 * @param itemGoldName GS2-Gold 通貨名
	 */
	public void setItemGoldName(String itemGoldName) {
		this.itemGoldName = itemGoldName;
	}

	/**
	 * GS2-Gold 通貨名を設定
	 *
	 * @param itemGoldName GS2-Gold 通貨名
	 * @return this
	 */
	public Item withItemGoldName(String itemGoldName) {
		this.itemGoldName = itemGoldName;
		return this;
	}

	/**
	 * GS2-Stamina スタミナプール名を取得
	 *
	 * @return GS2-Stamina スタミナプール名
	 */
	public String getItemStaminaStaminaPoolName() {
		return itemStaminaStaminaPoolName;
	}

	/**
	 * GS2-Stamina スタミナプール名を設定
	 *
	 * @param itemStaminaStaminaPoolName GS2-Stamina スタミナプール名
	 */
	public void setItemStaminaStaminaPoolName(String itemStaminaStaminaPoolName) {
		this.itemStaminaStaminaPoolName = itemStaminaStaminaPoolName;
	}

	/**
	 * GS2-Stamina スタミナプール名を設定
	 *
	 * @param itemStaminaStaminaPoolName GS2-Stamina スタミナプール名
	 * @return this
	 */
	public Item withItemStaminaStaminaPoolName(String itemStaminaStaminaPoolName) {
		this.itemStaminaStaminaPoolName = itemStaminaStaminaPoolName;
		return this;
	}

	/**
	 * GS2-ConsumableItem アイテムプール名を取得
	 *
	 * @return GS2-ConsumableItem アイテムプール名
	 */
	public String getItemConsumableItemItemPoolName() {
		return itemConsumableItemItemPoolName;
	}

	/**
	 * GS2-ConsumableItem アイテムプール名を設定
	 *
	 * @param itemConsumableItemItemPoolName GS2-ConsumableItem アイテムプール名
	 */
	public void setItemConsumableItemItemPoolName(String itemConsumableItemItemPoolName) {
		this.itemConsumableItemItemPoolName = itemConsumableItemItemPoolName;
	}

	/**
	 * GS2-ConsumableItem アイテムプール名を設定
	 *
	 * @param itemConsumableItemItemPoolName GS2-ConsumableItem アイテムプール名
	 * @return this
	 */
	public Item withItemConsumableItemItemPoolName(String itemConsumableItemItemPoolName) {
		this.itemConsumableItemItemPoolName = itemConsumableItemItemPoolName;
		return this;
	}

	/**
	 * GS2-ConsumableItem アイテム名を取得
	 *
	 * @return GS2-ConsumableItem アイテム名
	 */
	public String getItemConsumableItemItemName() {
		return itemConsumableItemItemName;
	}

	/**
	 * GS2-ConsumableItem アイテム名を設定
	 *
	 * @param itemConsumableItemItemName GS2-ConsumableItem アイテム名
	 */
	public void setItemConsumableItemItemName(String itemConsumableItemItemName) {
		this.itemConsumableItemItemName = itemConsumableItemItemName;
	}

	/**
	 * GS2-ConsumableItem アイテム名を設定
	 *
	 * @param itemConsumableItemItemName GS2-ConsumableItem アイテム名
	 * @return this
	 */
	public Item withItemConsumableItemItemName(String itemConsumableItemItemName) {
		this.itemConsumableItemItemName = itemConsumableItemItemName;
		return this;
	}

	/**
	 * GS2-Gacha ガチャプール名を取得
	 *
	 * @return GS2-Gacha ガチャプール名
	 */
	public String getItemGachaGachaPoolName() {
		return itemGachaGachaPoolName;
	}

	/**
	 * GS2-Gacha ガチャプール名を設定
	 *
	 * @param itemGachaGachaPoolName GS2-Gacha ガチャプール名
	 */
	public void setItemGachaGachaPoolName(String itemGachaGachaPoolName) {
		this.itemGachaGachaPoolName = itemGachaGachaPoolName;
	}

	/**
	 * GS2-Gacha ガチャプール名を設定
	 *
	 * @param itemGachaGachaPoolName GS2-Gacha ガチャプール名
	 * @return this
	 */
	public Item withItemGachaGachaPoolName(String itemGachaGachaPoolName) {
		this.itemGachaGachaPoolName = itemGachaGachaPoolName;
		return this;
	}

	/**
	 * GS2-Gacha ガチャ名を取得
	 *
	 * @return GS2-Gacha ガチャ名
	 */
	public String getItemGachaGachaName() {
		return itemGachaGachaName;
	}

	/**
	 * GS2-Gacha ガチャ名を設定
	 *
	 * @param itemGachaGachaName GS2-Gacha ガチャ名
	 */
	public void setItemGachaGachaName(String itemGachaGachaName) {
		this.itemGachaGachaName = itemGachaGachaName;
	}

	/**
	 * GS2-Gacha ガチャ名を設定
	 *
	 * @param itemGachaGachaName GS2-Gacha ガチャ名
	 * @return this
	 */
	public Item withItemGachaGachaName(String itemGachaGachaName) {
		this.itemGachaGachaName = itemGachaGachaName;
		return this;
	}

	/**
	 * 入手数量を取得
	 *
	 * @return 入手数量
	 */
	public Integer getItemAmount() {
		return itemAmount;
	}

	/**
	 * 入手数量を設定
	 *
	 * @param itemAmount 入手数量
	 */
	public void setItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
	}

	/**
	 * 入手数量を設定
	 *
	 * @param itemAmount 入手数量
	 * @return this
	 */
	public Item withItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
		return this;
	}

	/**
	 * アイテムの入手処理にまつわるオプション値を取得
	 *
	 * @return アイテムの入手処理にまつわるオプション値
	 */
	public String getItemOption() {
		return itemOption;
	}

	/**
	 * アイテムの入手処理にまつわるオプション値を設定
	 *
	 * @param itemOption アイテムの入手処理にまつわるオプション値
	 */
	public void setItemOption(String itemOption) {
		this.itemOption = itemOption;
	}

	/**
	 * アイテムの入手処理にまつわるオプション値を設定
	 *
	 * @param itemOption アイテムの入手処理にまつわるオプション値
	 * @return this
	 */
	public Item withItemOption(String itemOption) {
		this.itemOption = itemOption;
		return this;
	}

	/**
	 * 購入可能かを取得
	 *
	 * @return 購入可能か
	 */
	public Boolean getCanBuy() {
		return canBuy;
	}

	/**
	 * 購入可能かを設定
	 *
	 * @param canBuy 購入可能か
	 */
	public void setCanBuy(Boolean canBuy) {
		this.canBuy = canBuy;
	}

	/**
	 * 購入可能かを設定
	 *
	 * @param canBuy 購入可能か
	 * @return this
	 */
	public Item withCanBuy(Boolean canBuy) {
		this.canBuy = canBuy;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("showcaseItemId", this.getShowcaseItemId())
            .put("name", this.getName())
            .put("meta", this.getMeta())
            .put("currencyType", this.getCurrencyType())
            .put("currencyMoneyName", this.getCurrencyMoneyName())
            .put("currencyGoldPoolName", this.getCurrencyGoldPoolName())
            .put("currencyGoldName", this.getCurrencyGoldName())
            .put("currencyConsumableItemItemPoolName", this.getCurrencyConsumableItemItemPoolName())
            .put("currencyConsumableItemName", this.getCurrencyConsumableItemName())
            .put("currencyOption", this.getCurrencyOption())
            .put("price", this.getPrice())
            .put("itemType", this.getItemType())
            .put("itemMoneyName", this.getItemMoneyName())
            .put("itemGoldPoolName", this.getItemGoldPoolName())
            .put("itemGoldName", this.getItemGoldName())
            .put("itemStaminaStaminaPoolName", this.getItemStaminaStaminaPoolName())
            .put("itemConsumableItemItemPoolName", this.getItemConsumableItemItemPoolName())
            .put("itemConsumableItemItemName", this.getItemConsumableItemItemName())
            .put("itemGachaGachaPoolName", this.getItemGachaGachaPoolName())
            .put("itemGachaGachaName", this.getItemGachaGachaName())
            .put("itemAmount", this.getItemAmount())
            .put("itemOption", this.getItemOption())
            .put("canBuy", this.getCanBuy());

        return body;
    }
}