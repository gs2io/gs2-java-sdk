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
import io.gs2.model.IModel;

/**
 * 回数制限の種類
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LimitModel implements IModel, Serializable, Comparable<LimitModel> {
	/** 回数制限の種類 */
	protected String limitModelId;

	/**
	 * 回数制限の種類を取得
	 *
	 * @return 回数制限の種類
	 */
	public String getLimitModelId() {
		return limitModelId;
	}

	/**
	 * 回数制限の種類を設定
	 *
	 * @param limitModelId 回数制限の種類
	 */
	public void setLimitModelId(String limitModelId) {
		this.limitModelId = limitModelId;
	}

	/**
	 * 回数制限の種類を設定
	 *
	 * @param limitModelId 回数制限の種類
	 * @return this
	 */
	public LimitModel withLimitModelId(String limitModelId) {
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
	public LimitModel withName(String name) {
		this.name = name;
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
	public LimitModel withMetadata(String metadata) {
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
	public LimitModel withResetType(String resetType) {
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
	public LimitModel withResetDayOfMonth(Integer resetDayOfMonth) {
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
	public LimitModel withResetDayOfWeek(String resetDayOfWeek) {
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
	public LimitModel withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("limitModelId", this.getLimitModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("resetType", this.getResetType())
            .put("resetDayOfMonth", this.getResetDayOfMonth())
            .put("resetDayOfWeek", this.getResetDayOfWeek())
            .put("resetHour", this.getResetHour());
        return body_;
    }
	@Override
	public int compareTo(LimitModel o) {
		return limitModelId.compareTo(o.limitModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.limitModelId == null) ? 0 : this.limitModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.resetDayOfMonth == null) ? 0 : this.resetDayOfMonth.hashCode());
        result = prime * result + ((this.resetDayOfWeek == null) ? 0 : this.resetDayOfWeek.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
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
		LimitModel other = (LimitModel) o;
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
		return true;
	}
}