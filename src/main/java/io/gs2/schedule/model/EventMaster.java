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

package io.gs2.schedule.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * イベントマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EventMaster implements IModel, Serializable, Comparable<EventMaster> {
	/** イベントマスター */
	protected String eventId;

	/**
	 * イベントマスターを取得
	 *
	 * @return イベントマスター
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * イベントマスターを設定
	 *
	 * @param eventId イベントマスター
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * イベントマスターを設定
	 *
	 * @param eventId イベントマスター
	 * @return this
	 */
	public EventMaster withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}
	/** イベントの種類名 */
	protected String name;

	/**
	 * イベントの種類名を取得
	 *
	 * @return イベントの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * イベントの種類名を設定
	 *
	 * @param name イベントの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * イベントの種類名を設定
	 *
	 * @param name イベントの種類名
	 * @return this
	 */
	public EventMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** イベントマスターの説明 */
	protected String description;

	/**
	 * イベントマスターの説明を取得
	 *
	 * @return イベントマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * イベントマスターの説明を設定
	 *
	 * @param description イベントマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * イベントマスターの説明を設定
	 *
	 * @param description イベントマスターの説明
	 * @return this
	 */
	public EventMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** イベントの種類のメタデータ */
	protected String metadata;

	/**
	 * イベントの種類のメタデータを取得
	 *
	 * @return イベントの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * イベントの種類のメタデータを設定
	 *
	 * @param metadata イベントの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * イベントの種類のメタデータを設定
	 *
	 * @param metadata イベントの種類のメタデータ
	 * @return this
	 */
	public EventMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** イベント期間の種類 */
	protected String scheduleType;

	/**
	 * イベント期間の種類を取得
	 *
	 * @return イベント期間の種類
	 */
	public String getScheduleType() {
		return scheduleType;
	}

	/**
	 * イベント期間の種類を設定
	 *
	 * @param scheduleType イベント期間の種類
	 */
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	/**
	 * イベント期間の種類を設定
	 *
	 * @param scheduleType イベント期間の種類
	 * @return this
	 */
	public EventMaster withScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
		return this;
	}
	/** 繰り返しの種類 */
	protected String repeatType;

	/**
	 * 繰り返しの種類を取得
	 *
	 * @return 繰り返しの種類
	 */
	public String getRepeatType() {
		return repeatType;
	}

	/**
	 * 繰り返しの種類を設定
	 *
	 * @param repeatType 繰り返しの種類
	 */
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}

	/**
	 * 繰り返しの種類を設定
	 *
	 * @param repeatType 繰り返しの種類
	 * @return this
	 */
	public EventMaster withRepeatType(String repeatType) {
		this.repeatType = repeatType;
		return this;
	}
	/** イベントの開始日時 */
	protected Long absoluteBegin;

	/**
	 * イベントの開始日時を取得
	 *
	 * @return イベントの開始日時
	 */
	public Long getAbsoluteBegin() {
		return absoluteBegin;
	}

	/**
	 * イベントの開始日時を設定
	 *
	 * @param absoluteBegin イベントの開始日時
	 */
	public void setAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
	}

	/**
	 * イベントの開始日時を設定
	 *
	 * @param absoluteBegin イベントの開始日時
	 * @return this
	 */
	public EventMaster withAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
		return this;
	}
	/** イベントの終了日時 */
	protected Long absoluteEnd;

	/**
	 * イベントの終了日時を取得
	 *
	 * @return イベントの終了日時
	 */
	public Long getAbsoluteEnd() {
		return absoluteEnd;
	}

	/**
	 * イベントの終了日時を設定
	 *
	 * @param absoluteEnd イベントの終了日時
	 */
	public void setAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
	}

	/**
	 * イベントの終了日時を設定
	 *
	 * @param absoluteEnd イベントの終了日時
	 * @return this
	 */
	public EventMaster withAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
		return this;
	}
	/** イベントの繰り返し開始日 */
	protected Integer repeatBeginDayOfMonth;

	/**
	 * イベントの繰り返し開始日を取得
	 *
	 * @return イベントの繰り返し開始日
	 */
	public Integer getRepeatBeginDayOfMonth() {
		return repeatBeginDayOfMonth;
	}

	/**
	 * イベントの繰り返し開始日を設定
	 *
	 * @param repeatBeginDayOfMonth イベントの繰り返し開始日
	 */
	public void setRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
	}

	/**
	 * イベントの繰り返し開始日を設定
	 *
	 * @param repeatBeginDayOfMonth イベントの繰り返し開始日
	 * @return this
	 */
	public EventMaster withRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
		return this;
	}
	/** イベントの繰り返し終了日 */
	protected Integer repeatEndDayOfMonth;

	/**
	 * イベントの繰り返し終了日を取得
	 *
	 * @return イベントの繰り返し終了日
	 */
	public Integer getRepeatEndDayOfMonth() {
		return repeatEndDayOfMonth;
	}

	/**
	 * イベントの繰り返し終了日を設定
	 *
	 * @param repeatEndDayOfMonth イベントの繰り返し終了日
	 */
	public void setRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
	}

	/**
	 * イベントの繰り返し終了日を設定
	 *
	 * @param repeatEndDayOfMonth イベントの繰り返し終了日
	 * @return this
	 */
	public EventMaster withRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
		return this;
	}
	/** イベントの繰り返し開始曜日 */
	protected String repeatBeginDayOfWeek;

	/**
	 * イベントの繰り返し開始曜日を取得
	 *
	 * @return イベントの繰り返し開始曜日
	 */
	public String getRepeatBeginDayOfWeek() {
		return repeatBeginDayOfWeek;
	}

	/**
	 * イベントの繰り返し開始曜日を設定
	 *
	 * @param repeatBeginDayOfWeek イベントの繰り返し開始曜日
	 */
	public void setRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
	}

	/**
	 * イベントの繰り返し開始曜日を設定
	 *
	 * @param repeatBeginDayOfWeek イベントの繰り返し開始曜日
	 * @return this
	 */
	public EventMaster withRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
		return this;
	}
	/** イベントの繰り返し終了曜日 */
	protected String repeatEndDayOfWeek;

	/**
	 * イベントの繰り返し終了曜日を取得
	 *
	 * @return イベントの繰り返し終了曜日
	 */
	public String getRepeatEndDayOfWeek() {
		return repeatEndDayOfWeek;
	}

	/**
	 * イベントの繰り返し終了曜日を設定
	 *
	 * @param repeatEndDayOfWeek イベントの繰り返し終了曜日
	 */
	public void setRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
	}

	/**
	 * イベントの繰り返し終了曜日を設定
	 *
	 * @param repeatEndDayOfWeek イベントの繰り返し終了曜日
	 * @return this
	 */
	public EventMaster withRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
		return this;
	}
	/** イベントの繰り返し開始時間 */
	protected Integer repeatBeginHour;

	/**
	 * イベントの繰り返し開始時間を取得
	 *
	 * @return イベントの繰り返し開始時間
	 */
	public Integer getRepeatBeginHour() {
		return repeatBeginHour;
	}

	/**
	 * イベントの繰り返し開始時間を設定
	 *
	 * @param repeatBeginHour イベントの繰り返し開始時間
	 */
	public void setRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
	}

	/**
	 * イベントの繰り返し開始時間を設定
	 *
	 * @param repeatBeginHour イベントの繰り返し開始時間
	 * @return this
	 */
	public EventMaster withRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
		return this;
	}
	/** イベントの繰り返し終了時間 */
	protected Integer repeatEndHour;

	/**
	 * イベントの繰り返し終了時間を取得
	 *
	 * @return イベントの繰り返し終了時間
	 */
	public Integer getRepeatEndHour() {
		return repeatEndHour;
	}

	/**
	 * イベントの繰り返し終了時間を設定
	 *
	 * @param repeatEndHour イベントの繰り返し終了時間
	 */
	public void setRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
	}

	/**
	 * イベントの繰り返し終了時間を設定
	 *
	 * @param repeatEndHour イベントの繰り返し終了時間
	 * @return this
	 */
	public EventMaster withRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
		return this;
	}
	/** イベントの開始トリガー名 */
	protected String relativeTriggerName;

	/**
	 * イベントの開始トリガー名を取得
	 *
	 * @return イベントの開始トリガー名
	 */
	public String getRelativeTriggerName() {
		return relativeTriggerName;
	}

	/**
	 * イベントの開始トリガー名を設定
	 *
	 * @param relativeTriggerName イベントの開始トリガー名
	 */
	public void setRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
	}

	/**
	 * イベントの開始トリガー名を設定
	 *
	 * @param relativeTriggerName イベントの開始トリガー名
	 * @return this
	 */
	public EventMaster withRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
		return this;
	}
	/** イベントの開催期間(秒) */
	protected Integer relativeDuration;

	/**
	 * イベントの開催期間(秒)を取得
	 *
	 * @return イベントの開催期間(秒)
	 */
	public Integer getRelativeDuration() {
		return relativeDuration;
	}

	/**
	 * イベントの開催期間(秒)を設定
	 *
	 * @param relativeDuration イベントの開催期間(秒)
	 */
	public void setRelativeDuration(Integer relativeDuration) {
		this.relativeDuration = relativeDuration;
	}

	/**
	 * イベントの開催期間(秒)を設定
	 *
	 * @param relativeDuration イベントの開催期間(秒)
	 * @return this
	 */
	public EventMaster withRelativeDuration(Integer relativeDuration) {
		this.relativeDuration = relativeDuration;
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
	public EventMaster withCreatedAt(Long createdAt) {
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
	public EventMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("eventId", this.getEventId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("scheduleType", this.getScheduleType())
            .put("repeatType", this.getRepeatType())
            .put("absoluteBegin", this.getAbsoluteBegin())
            .put("absoluteEnd", this.getAbsoluteEnd())
            .put("repeatBeginDayOfMonth", this.getRepeatBeginDayOfMonth())
            .put("repeatEndDayOfMonth", this.getRepeatEndDayOfMonth())
            .put("repeatBeginDayOfWeek", this.getRepeatBeginDayOfWeek())
            .put("repeatEndDayOfWeek", this.getRepeatEndDayOfWeek())
            .put("repeatBeginHour", this.getRepeatBeginHour())
            .put("repeatEndHour", this.getRepeatEndHour())
            .put("relativeTriggerName", this.getRelativeTriggerName())
            .put("relativeDuration", this.getRelativeDuration())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(EventMaster o) {
		return eventId.compareTo(o.eventId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eventId == null) ? 0 : this.eventId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.scheduleType == null) ? 0 : this.scheduleType.hashCode());
        result = prime * result + ((this.repeatType == null) ? 0 : this.repeatType.hashCode());
        result = prime * result + ((this.absoluteBegin == null) ? 0 : this.absoluteBegin.hashCode());
        result = prime * result + ((this.absoluteEnd == null) ? 0 : this.absoluteEnd.hashCode());
        result = prime * result + ((this.repeatBeginDayOfMonth == null) ? 0 : this.repeatBeginDayOfMonth.hashCode());
        result = prime * result + ((this.repeatEndDayOfMonth == null) ? 0 : this.repeatEndDayOfMonth.hashCode());
        result = prime * result + ((this.repeatBeginDayOfWeek == null) ? 0 : this.repeatBeginDayOfWeek.hashCode());
        result = prime * result + ((this.repeatEndDayOfWeek == null) ? 0 : this.repeatEndDayOfWeek.hashCode());
        result = prime * result + ((this.repeatBeginHour == null) ? 0 : this.repeatBeginHour.hashCode());
        result = prime * result + ((this.repeatEndHour == null) ? 0 : this.repeatEndHour.hashCode());
        result = prime * result + ((this.relativeTriggerName == null) ? 0 : this.relativeTriggerName.hashCode());
        result = prime * result + ((this.relativeDuration == null) ? 0 : this.relativeDuration.hashCode());
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
		EventMaster other = (EventMaster) o;
		if (eventId == null) {
			return other.eventId == null;
		} else if (!eventId.equals(other.eventId)) {
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
		if (scheduleType == null) {
			return other.scheduleType == null;
		} else if (!scheduleType.equals(other.scheduleType)) {
			return false;
		}
		if (repeatType == null) {
			return other.repeatType == null;
		} else if (!repeatType.equals(other.repeatType)) {
			return false;
		}
		if (absoluteBegin == null) {
			return other.absoluteBegin == null;
		} else if (!absoluteBegin.equals(other.absoluteBegin)) {
			return false;
		}
		if (absoluteEnd == null) {
			return other.absoluteEnd == null;
		} else if (!absoluteEnd.equals(other.absoluteEnd)) {
			return false;
		}
		if (repeatBeginDayOfMonth == null) {
			return other.repeatBeginDayOfMonth == null;
		} else if (!repeatBeginDayOfMonth.equals(other.repeatBeginDayOfMonth)) {
			return false;
		}
		if (repeatEndDayOfMonth == null) {
			return other.repeatEndDayOfMonth == null;
		} else if (!repeatEndDayOfMonth.equals(other.repeatEndDayOfMonth)) {
			return false;
		}
		if (repeatBeginDayOfWeek == null) {
			return other.repeatBeginDayOfWeek == null;
		} else if (!repeatBeginDayOfWeek.equals(other.repeatBeginDayOfWeek)) {
			return false;
		}
		if (repeatEndDayOfWeek == null) {
			return other.repeatEndDayOfWeek == null;
		} else if (!repeatEndDayOfWeek.equals(other.repeatEndDayOfWeek)) {
			return false;
		}
		if (repeatBeginHour == null) {
			return other.repeatBeginHour == null;
		} else if (!repeatBeginHour.equals(other.repeatBeginHour)) {
			return false;
		}
		if (repeatEndHour == null) {
			return other.repeatEndHour == null;
		} else if (!repeatEndHour.equals(other.repeatEndHour)) {
			return false;
		}
		if (relativeTriggerName == null) {
			return other.relativeTriggerName == null;
		} else if (!relativeTriggerName.equals(other.relativeTriggerName)) {
			return false;
		}
		if (relativeDuration == null) {
			return other.relativeDuration == null;
		} else if (!relativeDuration.equals(other.relativeDuration)) {
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