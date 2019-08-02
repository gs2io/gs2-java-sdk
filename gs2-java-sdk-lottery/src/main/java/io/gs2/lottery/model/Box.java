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
 * ボックス
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Box implements IModel, Serializable, Comparable<Box> {
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
	public Box withBoxId(String boxId) {
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
	public Box withPrizeTableName(String prizeTableName) {
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
	public Box withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 排出済み景品のインデックスのリスト */
	protected List<Integer> drawnIndexes;

	/**
	 * 排出済み景品のインデックスのリストを取得
	 *
	 * @return 排出済み景品のインデックスのリスト
	 */
	public List<Integer> getDrawnIndexes() {
		return drawnIndexes;
	}

	/**
	 * 排出済み景品のインデックスのリストを設定
	 *
	 * @param drawnIndexes 排出済み景品のインデックスのリスト
	 */
	public void setDrawnIndexes(List<Integer> drawnIndexes) {
		this.drawnIndexes = drawnIndexes;
	}

	/**
	 * 排出済み景品のインデックスのリストを設定
	 *
	 * @param drawnIndexes 排出済み景品のインデックスのリスト
	 * @return this
	 */
	public Box withDrawnIndexes(List<Integer> drawnIndexes) {
		this.drawnIndexes = drawnIndexes;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Box withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Box withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> drawnIndexes = new ArrayList<>();
        if(this.drawnIndexes != null) {
            for(Integer item : this.drawnIndexes) {
                drawnIndexes.add(JsonNodeFactory.instance.numberNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("boxId", this.getBoxId())
            .put("prizeTableName", this.getPrizeTableName())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("drawnIndexes", JsonNodeFactory.instance.arrayNode().addAll(drawnIndexes));
        return body_;
    }
	@Override
	public int compareTo(Box o) {
		return boxId.compareTo(o.boxId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.boxId == null) ? 0 : this.boxId.hashCode());
        result = prime * result + ((this.prizeTableName == null) ? 0 : this.prizeTableName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.drawnIndexes == null) ? 0 : this.drawnIndexes.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Box other = (Box) o;
		if (boxId == null) {
			return other.boxId == null;
		} else if (!boxId.equals(other.boxId)) {
			return false;
		}
		if (prizeTableName == null) {
			return other.prizeTableName == null;
		} else if (!prizeTableName.equals(other.prizeTableName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (drawnIndexes == null) {
			return other.drawnIndexes == null;
		} else if (!drawnIndexes.equals(other.drawnIndexes)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}