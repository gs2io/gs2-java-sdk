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
 * ボックスから取り出したアイテムのリスト
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BoxItems implements IModel, Serializable {
	/** ボックス */
	protected String boxId;

	/**
	 * ボックスを取得
	 *
	 * @return ボックス
	 */
	public String getBoxId() {
		return boxId;
	}

	/**
	 * ボックスを設定
	 *
	 * @param boxId ボックス
	 */
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	/**
	 * ボックスを設定
	 *
	 * @param boxId ボックス
	 * @return this
	 */
	public BoxItems withBoxId(String boxId) {
		this.boxId = boxId;
		return this;
	}
	/** 排出確率テーブル名 */
	protected String prizeTableName;

	/**
	 * 排出確率テーブル名を取得
	 *
	 * @return 排出確率テーブル名
	 */
	public String getPrizeTableName() {
		return prizeTableName;
	}

	/**
	 * 排出確率テーブル名を設定
	 *
	 * @param prizeTableName 排出確率テーブル名
	 */
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}

	/**
	 * 排出確率テーブル名を設定
	 *
	 * @param prizeTableName 排出確率テーブル名
	 * @return this
	 */
	public BoxItems withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public BoxItems withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** ボックスから取り出したアイテムのリスト */
	protected List<BoxItem> items;

	/**
	 * ボックスから取り出したアイテムのリストを取得
	 *
	 * @return ボックスから取り出したアイテムのリスト
	 */
	public List<BoxItem> getItems() {
		return items;
	}

	/**
	 * ボックスから取り出したアイテムのリストを設定
	 *
	 * @param items ボックスから取り出したアイテムのリスト
	 */
	public void setItems(List<BoxItem> items) {
		this.items = items;
	}

	/**
	 * ボックスから取り出したアイテムのリストを設定
	 *
	 * @param items ボックスから取り出したアイテムのリスト
	 * @return this
	 */
	public BoxItems withItems(List<BoxItem> items) {
		this.items = items;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> items = new ArrayList<>();
        if(this.items != null) {
            for(BoxItem item : this.items) {
                items.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("boxId", this.getBoxId())
            .put("prizeTableName", this.getPrizeTableName())
            .put("userId", this.getUserId());
        body_.set("items", JsonNodeFactory.instance.arrayNode().addAll(items));
        return body_;
    }
}