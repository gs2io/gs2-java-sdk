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
 * 陳列商品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ShowcaseItemMaster implements Serializable {

	/** 陳列商品GRN */
	private String showcaseItemId;

	/** 商品の種類 */
	private String category;

	/** 商品名 */
	private String itemName;

	/** グループ名 */
	private String itemGroupName;

	/** 公開判定の種類 */
	private String releaseConditionType;

	/** 公開許可判定 に実行されるGS2-Schedule */
	private String releaseConditionScheduleName;

	/** 公開許可判定 に実行されるGS2-Schedule のイベント名 */
	private String releaseConditionScheduleEventName;

	/** 応答順序優先度 */
	private Integer priority;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 陳列商品GRNを取得
	 *
	 * @return 陳列商品GRN
	 */
	public String getShowcaseItemId() {
		return showcaseItemId;
	}

	/**
	 * 陳列商品GRNを設定
	 *
	 * @param showcaseItemId 陳列商品GRN
	 */
	public void setShowcaseItemId(String showcaseItemId) {
		this.showcaseItemId = showcaseItemId;
	}

	/**
	 * 陳列商品GRNを設定
	 *
	 * @param showcaseItemId 陳列商品GRN
	 * @return this
	 */
	public ShowcaseItemMaster withShowcaseItemId(String showcaseItemId) {
		this.showcaseItemId = showcaseItemId;
		return this;
	}

	/**
	 * 商品の種類を取得
	 *
	 * @return 商品の種類
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 商品の種類を設定
	 *
	 * @param category 商品の種類
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 商品の種類を設定
	 *
	 * @param category 商品の種類
	 * @return this
	 */
	public ShowcaseItemMaster withCategory(String category) {
		this.category = category;
		return this;
	}

	/**
	 * 商品名を取得
	 *
	 * @return 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 商品名を設定
	 *
	 * @param itemName 商品名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 商品名を設定
	 *
	 * @param itemName 商品名
	 * @return this
	 */
	public ShowcaseItemMaster withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}

	/**
	 * グループ名を取得
	 *
	 * @return グループ名
	 */
	public String getItemGroupName() {
		return itemGroupName;
	}

	/**
	 * グループ名を設定
	 *
	 * @param itemGroupName グループ名
	 */
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	/**
	 * グループ名を設定
	 *
	 * @param itemGroupName グループ名
	 * @return this
	 */
	public ShowcaseItemMaster withItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
		return this;
	}

	/**
	 * 公開判定の種類を取得
	 *
	 * @return 公開判定の種類
	 */
	public String getReleaseConditionType() {
		return releaseConditionType;
	}

	/**
	 * 公開判定の種類を設定
	 *
	 * @param releaseConditionType 公開判定の種類
	 */
	public void setReleaseConditionType(String releaseConditionType) {
		this.releaseConditionType = releaseConditionType;
	}

	/**
	 * 公開判定の種類を設定
	 *
	 * @param releaseConditionType 公開判定の種類
	 * @return this
	 */
	public ShowcaseItemMaster withReleaseConditionType(String releaseConditionType) {
		this.releaseConditionType = releaseConditionType;
		return this;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Scheduleを取得
	 *
	 * @return 公開許可判定 に実行されるGS2-Schedule
	 */
	public String getReleaseConditionScheduleName() {
		return releaseConditionScheduleName;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Scheduleを設定
	 *
	 * @param releaseConditionScheduleName 公開許可判定 に実行されるGS2-Schedule
	 */
	public void setReleaseConditionScheduleName(String releaseConditionScheduleName) {
		this.releaseConditionScheduleName = releaseConditionScheduleName;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Scheduleを設定
	 *
	 * @param releaseConditionScheduleName 公開許可判定 に実行されるGS2-Schedule
	 * @return this
	 */
	public ShowcaseItemMaster withReleaseConditionScheduleName(String releaseConditionScheduleName) {
		this.releaseConditionScheduleName = releaseConditionScheduleName;
		return this;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Schedule のイベント名を取得
	 *
	 * @return 公開許可判定 に実行されるGS2-Schedule のイベント名
	 */
	public String getReleaseConditionScheduleEventName() {
		return releaseConditionScheduleEventName;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Schedule のイベント名を設定
	 *
	 * @param releaseConditionScheduleEventName 公開許可判定 に実行されるGS2-Schedule のイベント名
	 */
	public void setReleaseConditionScheduleEventName(String releaseConditionScheduleEventName) {
		this.releaseConditionScheduleEventName = releaseConditionScheduleEventName;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Schedule のイベント名を設定
	 *
	 * @param releaseConditionScheduleEventName 公開許可判定 に実行されるGS2-Schedule のイベント名
	 * @return this
	 */
	public ShowcaseItemMaster withReleaseConditionScheduleEventName(String releaseConditionScheduleEventName) {
		this.releaseConditionScheduleEventName = releaseConditionScheduleEventName;
		return this;
	}

	/**
	 * 応答順序優先度を取得
	 *
	 * @return 応答順序優先度
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 応答順序優先度を設定
	 *
	 * @param priority 応答順序優先度
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * 応答順序優先度を設定
	 *
	 * @param priority 応答順序優先度
	 * @return this
	 */
	public ShowcaseItemMaster withPriority(Integer priority) {
		this.priority = priority;
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
	public ShowcaseItemMaster withCreateAt(Integer createAt) {
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
	public ShowcaseItemMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("showcaseItemId", this.getShowcaseItemId())
            .put("category", this.getCategory())
            .put("itemName", this.getItemName())
            .put("itemGroupName", this.getItemGroupName())
            .put("releaseConditionType", this.getReleaseConditionType())
            .put("releaseConditionScheduleName", this.getReleaseConditionScheduleName())
            .put("releaseConditionScheduleEventName", this.getReleaseConditionScheduleEventName())
            .put("priority", this.getPriority())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}