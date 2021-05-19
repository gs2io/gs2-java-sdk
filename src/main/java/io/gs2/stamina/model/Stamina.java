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

package io.gs2.stamina.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * スタミナ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Stamina implements IModel, Serializable, Comparable<Stamina> {
	/** スタミナ */
	protected String staminaId;

	/**
	 * スタミナを取得
	 *
	 * @return スタミナ
	 */
	public String getStaminaId() {
		return staminaId;
	}

	/**
	 * スタミナを設定
	 *
	 * @param staminaId スタミナ
	 */
	public void setStaminaId(String staminaId) {
		this.staminaId = staminaId;
	}

	/**
	 * スタミナを設定
	 *
	 * @param staminaId スタミナ
	 * @return this
	 */
	public Stamina withStaminaId(String staminaId) {
		this.staminaId = staminaId;
		return this;
	}
	/** スタミナモデルの名前 */
	protected String staminaName;

	/**
	 * スタミナモデルの名前を取得
	 *
	 * @return スタミナモデルの名前
	 */
	public String getStaminaName() {
		return staminaName;
	}

	/**
	 * スタミナモデルの名前を設定
	 *
	 * @param staminaName スタミナモデルの名前
	 */
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}

	/**
	 * スタミナモデルの名前を設定
	 *
	 * @param staminaName スタミナモデルの名前
	 * @return this
	 */
	public Stamina withStaminaName(String staminaName) {
		this.staminaName = staminaName;
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
	public Stamina withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 最終更新時におけるスタミナ値 */
	protected Integer value;

	/**
	 * 最終更新時におけるスタミナ値を取得
	 *
	 * @return 最終更新時におけるスタミナ値
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 最終更新時におけるスタミナ値を設定
	 *
	 * @param value 最終更新時におけるスタミナ値
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * 最終更新時におけるスタミナ値を設定
	 *
	 * @param value 最終更新時におけるスタミナ値
	 * @return this
	 */
	public Stamina withValue(Integer value) {
		this.value = value;
		return this;
	}
	/** スタミナの最大値 */
	protected Integer maxValue;

	/**
	 * スタミナの最大値を取得
	 *
	 * @return スタミナの最大値
	 */
	public Integer getMaxValue() {
		return maxValue;
	}

	/**
	 * スタミナの最大値を設定
	 *
	 * @param maxValue スタミナの最大値
	 */
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * スタミナの最大値を設定
	 *
	 * @param maxValue スタミナの最大値
	 * @return this
	 */
	public Stamina withMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		return this;
	}
	/** スタミナの回復間隔(分) */
	protected Integer recoverIntervalMinutes;

	/**
	 * スタミナの回復間隔(分)を取得
	 *
	 * @return スタミナの回復間隔(分)
	 */
	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}

	/**
	 * スタミナの回復間隔(分)を設定
	 *
	 * @param recoverIntervalMinutes スタミナの回復間隔(分)
	 */
	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}

	/**
	 * スタミナの回復間隔(分)を設定
	 *
	 * @param recoverIntervalMinutes スタミナの回復間隔(分)
	 * @return this
	 */
	public Stamina withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}
	/** スタミナの回復量 */
	protected Integer recoverValue;

	/**
	 * スタミナの回復量を取得
	 *
	 * @return スタミナの回復量
	 */
	public Integer getRecoverValue() {
		return recoverValue;
	}

	/**
	 * スタミナの回復量を設定
	 *
	 * @param recoverValue スタミナの回復量
	 */
	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}

	/**
	 * スタミナの回復量を設定
	 *
	 * @param recoverValue スタミナの回復量
	 * @return this
	 */
	public Stamina withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}
	/** スタミナの最大値を超えて格納されているスタミナ値 */
	protected Integer overflowValue;

	/**
	 * スタミナの最大値を超えて格納されているスタミナ値を取得
	 *
	 * @return スタミナの最大値を超えて格納されているスタミナ値
	 */
	public Integer getOverflowValue() {
		return overflowValue;
	}

	/**
	 * スタミナの最大値を超えて格納されているスタミナ値を設定
	 *
	 * @param overflowValue スタミナの最大値を超えて格納されているスタミナ値
	 */
	public void setOverflowValue(Integer overflowValue) {
		this.overflowValue = overflowValue;
	}

	/**
	 * スタミナの最大値を超えて格納されているスタミナ値を設定
	 *
	 * @param overflowValue スタミナの最大値を超えて格納されているスタミナ値
	 * @return this
	 */
	public Stamina withOverflowValue(Integer overflowValue) {
		this.overflowValue = overflowValue;
		return this;
	}
	/** 次回スタミナが回復する時間 */
	protected Long nextRecoverAt;

	/**
	 * 次回スタミナが回復する時間を取得
	 *
	 * @return 次回スタミナが回復する時間
	 */
	public Long getNextRecoverAt() {
		return nextRecoverAt;
	}

	/**
	 * 次回スタミナが回復する時間を設定
	 *
	 * @param nextRecoverAt 次回スタミナが回復する時間
	 */
	public void setNextRecoverAt(Long nextRecoverAt) {
		this.nextRecoverAt = nextRecoverAt;
	}

	/**
	 * 次回スタミナが回復する時間を設定
	 *
	 * @param nextRecoverAt 次回スタミナが回復する時間
	 * @return this
	 */
	public Stamina withNextRecoverAt(Long nextRecoverAt) {
		this.nextRecoverAt = nextRecoverAt;
		return this;
	}
	/** 作成日時 */
	protected Long lastRecoveredAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getLastRecoveredAt() {
		return lastRecoveredAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param lastRecoveredAt 作成日時
	 */
	public void setLastRecoveredAt(Long lastRecoveredAt) {
		this.lastRecoveredAt = lastRecoveredAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param lastRecoveredAt 作成日時
	 * @return this
	 */
	public Stamina withLastRecoveredAt(Long lastRecoveredAt) {
		this.lastRecoveredAt = lastRecoveredAt;
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
	public Stamina withCreatedAt(Long createdAt) {
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
	public Stamina withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("staminaId", this.getStaminaId())
            .put("staminaName", this.getStaminaName())
            .put("userId", this.getUserId())
            .put("value", this.getValue())
            .put("maxValue", this.getMaxValue())
            .put("recoverIntervalMinutes", this.getRecoverIntervalMinutes())
            .put("recoverValue", this.getRecoverValue())
            .put("overflowValue", this.getOverflowValue())
            .put("nextRecoverAt", this.getNextRecoverAt())
            .put("lastRecoveredAt", this.getLastRecoveredAt())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Stamina o) {
		return staminaId.compareTo(o.staminaId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.staminaId == null) ? 0 : this.staminaId.hashCode());
        result = prime * result + ((this.staminaName == null) ? 0 : this.staminaName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
        result = prime * result + ((this.maxValue == null) ? 0 : this.maxValue.hashCode());
        result = prime * result + ((this.recoverIntervalMinutes == null) ? 0 : this.recoverIntervalMinutes.hashCode());
        result = prime * result + ((this.recoverValue == null) ? 0 : this.recoverValue.hashCode());
        result = prime * result + ((this.overflowValue == null) ? 0 : this.overflowValue.hashCode());
        result = prime * result + ((this.nextRecoverAt == null) ? 0 : this.nextRecoverAt.hashCode());
        result = prime * result + ((this.lastRecoveredAt == null) ? 0 : this.lastRecoveredAt.hashCode());
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
		Stamina other = (Stamina) o;
		if (staminaId == null) {
			return other.staminaId == null;
		} else if (!staminaId.equals(other.staminaId)) {
			return false;
		}
		if (staminaName == null) {
			return other.staminaName == null;
		} else if (!staminaName.equals(other.staminaName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		if (maxValue == null) {
			return other.maxValue == null;
		} else if (!maxValue.equals(other.maxValue)) {
			return false;
		}
		if (recoverIntervalMinutes == null) {
			return other.recoverIntervalMinutes == null;
		} else if (!recoverIntervalMinutes.equals(other.recoverIntervalMinutes)) {
			return false;
		}
		if (recoverValue == null) {
			return other.recoverValue == null;
		} else if (!recoverValue.equals(other.recoverValue)) {
			return false;
		}
		if (overflowValue == null) {
			return other.overflowValue == null;
		} else if (!overflowValue.equals(other.overflowValue)) {
			return false;
		}
		if (nextRecoverAt == null) {
			return other.nextRecoverAt == null;
		} else if (!nextRecoverAt.equals(other.nextRecoverAt)) {
			return false;
		}
		if (lastRecoveredAt == null) {
			return other.lastRecoveredAt == null;
		} else if (!lastRecoveredAt.equals(other.lastRecoveredAt)) {
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