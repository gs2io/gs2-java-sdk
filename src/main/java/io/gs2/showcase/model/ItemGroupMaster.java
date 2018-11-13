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
 * 商品グループ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemGroupMaster implements Serializable {

	/** 商品グループGRN */
	private String itemGroupId;

	/** 商品グループ名 */
	private String name;

	/** 販売している商品名のリスト */
	private List<String> itemNames;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * 商品グループGRNを取得
	 *
	 * @return 商品グループGRN
	 */
	public String getItemGroupId() {
		return itemGroupId;
	}

	/**
	 * 商品グループGRNを設定
	 *
	 * @param itemGroupId 商品グループGRN
	 */
	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

	/**
	 * 商品グループGRNを設定
	 *
	 * @param itemGroupId 商品グループGRN
	 * @return this
	 */
	public ItemGroupMaster withItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
		return this;
	}

	/**
	 * 商品グループ名を取得
	 *
	 * @return 商品グループ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品グループ名を設定
	 *
	 * @param name 商品グループ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品グループ名を設定
	 *
	 * @param name 商品グループ名
	 * @return this
	 */
	public ItemGroupMaster withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 販売している商品名のリストを取得
	 *
	 * @return 販売している商品名のリスト
	 */
	public List<String> getItemNames() {
		return itemNames;
	}

	/**
	 * 販売している商品名のリストを設定
	 *
	 * @param itemNames 販売している商品名のリスト
	 */
	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}

	/**
	 * 販売している商品名のリストを設定
	 *
	 * @param itemNames 販売している商品名のリスト
	 * @return this
	 */
	public ItemGroupMaster withItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
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
	public ItemGroupMaster withCreateAt(Integer createAt) {
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
	public ItemGroupMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

        List<JsonNode> itemNamesNode = new ArrayList<>();
        if(this.itemNames != null) {
            for(String item : this.itemNames) {
                itemNamesNode.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("itemGroupId", this.getItemGroupId())
            .put("name", this.getName())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        body.set("itemNames", JsonNodeFactory.instance.arrayNode().addAll(itemNamesNode));
        return body;
    }
}