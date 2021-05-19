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

package io.gs2.limit.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 回数制限の種類マスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LimitModelMaster implements IModel, Serializable, Comparable<LimitModelMaster> {
	/** 回数制限の種類マスター */
	protected String limitModelId;

	/**
	 * 回数制限の種類マスターを取得
	 *
	 * @return 回数制限の種類マスター
	 */
	public String getLimitModelId() {
		return limitModelId;
	}

	/**
	 * 回数制限の種類マスターを設定
	 *
	 * @param limitModelId 回数制限の種類マスター
	 */
	public void setLimitModelId(String limitModelId) {
		this.limitModelId = limitModelId;
	}

	/**
	 * 回数制限の種類マスターを設定
	 *
	 * @param limitModelId 回数制限の種類マスター
	 * @return this
	 */
	public LimitModelMaster withLimitModelId(String limitModelId) {
		this.limitModelId = limitModelId;
		return this;
	}
	/** 回数制限の種類名 */
	protected String name;

	/**
	 * 回数制限の種類名を取得
	 *
	 * @return 回数制限の種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 回数制限の種類名を設定
	 *
	 * @param name 回数制限の種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 回数制限の種類名を設定
	 *
	 * @param name 回数制限の種類名
	 * @return this
	 */
	public LimitModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** 回数制限の種類マスターの説明 */
	protected String description;

	/**
	 * 回数制限の種類マスターの説明を取得
	 *
	 * @return 回数制限の種類マスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 回数制限の種類マスターの説明を設定
	 *
	 * @param description 回数制限の種類マスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 回数制限の種類マスターの説明を設定
	 *
	 * @param description 回数制限の種類マスターの説明
	 * @return this
	 */
	public LimitModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 回数制限の種類のメタデータ */
	protected String metadata;

	/**
	 * 回数制限の種類のメタデータを取得
	 *
	 * @return 回数制限の種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 回数制限の種類のメタデータを設定
	 *
	 * @param metadata 回数制限の種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 回数制限の種類のメタデータを設定
	 *
	 * @param metadata 回数制限の種類のメタデータ
	 * @return this
	 */
	public LimitModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** リセットタイミング */
	protected String resetType;

	/**
	 * リセットタイミングを取得
	 *
	 * @return リセットタイミング
	 */
	public String getResetType() {
		return resetType;
	}

	/**
	 * リセットタイミングを設定
	 *
	 * @param resetType リセットタイミング
	 */
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	/**
	 * リセットタイミングを設定
	 *
	 * @param resetType リセットタイミング
	 * @return this
	 */
	public LimitModelMaster withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	/** リセットをする日にち */
	protected Integer resetDayOfMonth;

	/**
	 * リセットをする日にちを取得
	 *
	 * @return リセットをする日にち
	 */
	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	/**
	 * リセットをする日にちを設定
	 *
	 * @param resetDayOfMonth リセットをする日にち
	 */
	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	/**
	 * リセットをする日にちを設定
	 *
	 * @param resetDayOfMonth リセットをする日にち
	 * @return this
	 */
	public LimitModelMaster withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}
	/** リセットする曜日 */
	protected String resetDayOfWeek;

	/**
	 * リセットする曜日を取得
	 *
	 * @return リセットする曜日
	 */
	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	/**
	 * リセットする曜日を設定
	 *
	 * @param resetDayOfWeek リセットする曜日
	 */
	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	/**
	 * リセットする曜日を設定
	 *
	 * @param resetDayOfWeek リセットする曜日
	 * @return this
	 */
	public LimitModelMaster withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}
	/** リセット時刻 */
	protected Integer resetHour;

	/**
	 * リセット時刻を取得
	 *
	 * @return リセット時刻
	 */
	public Integer getResetHour() {
		return resetHour;
	}

	/**
	 * リセット時刻を設定
	 *
	 * @param resetHour リセット時刻
	 */
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	/**
	 * リセット時刻を設定
	 *
	 * @param resetHour リセット時刻
	 * @return this
	 */
	public LimitModelMaster withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
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
	public LimitModelMaster withCreatedAt(Long createdAt) {
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
	public LimitModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("limitModelId", this.getLimitModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("resetType", this.getResetType())
            .put("resetDayOfMonth", this.getResetDayOfMonth())
            .put("resetDayOfWeek", this.getResetDayOfWeek())
            .put("resetHour", this.getResetHour())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(LimitModelMaster o) {
		return limitModelId.compareTo(o.limitModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.limitModelId == null) ? 0 : this.limitModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.resetDayOfMonth == null) ? 0 : this.resetDayOfMonth.hashCode());
        result = prime * result + ((this.resetDayOfWeek == null) ? 0 : this.resetDayOfWeek.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
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
		LimitModelMaster other = (LimitModelMaster) o;
		if (limitModelId == null) {
			return other.limitModelId == null;
		} else if (!limitModelId.equals(other.limitModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (resetType == null) {
			return other.resetType == null;
		} else if (!resetType.equals(other.resetType)) {
			return false;
		}
		if (resetDayOfMonth == null) {
			return other.resetDayOfMonth == null;
		} else if (!resetDayOfMonth.equals(other.resetDayOfMonth)) {
			return false;
		}
		if (resetDayOfWeek == null) {
			return other.resetDayOfWeek == null;
		} else if (!resetDayOfWeek.equals(other.resetDayOfWeek)) {
			return false;
		}
		if (resetHour == null) {
			return other.resetHour == null;
		} else if (!resetHour.equals(other.resetHour)) {
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