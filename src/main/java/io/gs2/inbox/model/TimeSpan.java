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

package io.gs2.inbox.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 差分時間設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimeSpan implements IModel, Serializable {
	/** 現在時刻からの日数 */
	protected Integer days;

	/**
	 * 現在時刻からの日数を取得
	 *
	 * @return 現在時刻からの日数
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * 現在時刻からの日数を設定
	 *
	 * @param days 現在時刻からの日数
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	/**
	 * 現在時刻からの日数を設定
	 *
	 * @param days 現在時刻からの日数
	 * @return this
	 */
	public TimeSpan withDays(Integer days) {
		this.days = days;
		return this;
	}
	/** 現在時刻からの時間 */
	protected Integer hours;

	/**
	 * 現在時刻からの時間を取得
	 *
	 * @return 現在時刻からの時間
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * 現在時刻からの時間を設定
	 *
	 * @param hours 現在時刻からの時間
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * 現在時刻からの時間を設定
	 *
	 * @param hours 現在時刻からの時間
	 * @return this
	 */
	public TimeSpan withHours(Integer hours) {
		this.hours = hours;
		return this;
	}
	/** 現在時刻からの分 */
	protected Integer minutes;

	/**
	 * 現在時刻からの分を取得
	 *
	 * @return 現在時刻からの分
	 */
	public Integer getMinutes() {
		return minutes;
	}

	/**
	 * 現在時刻からの分を設定
	 *
	 * @param minutes 現在時刻からの分
	 */
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	/**
	 * 現在時刻からの分を設定
	 *
	 * @param minutes 現在時刻からの分
	 * @return this
	 */
	public TimeSpan withMinutes(Integer minutes) {
		this.minutes = minutes;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("days", this.getDays())
            .put("hours", this.getHours())
            .put("minutes", this.getMinutes());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.days == null) ? 0 : this.days.hashCode());
        result = prime * result + ((this.hours == null) ? 0 : this.hours.hashCode());
        result = prime * result + ((this.minutes == null) ? 0 : this.minutes.hashCode());
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
		TimeSpan other = (TimeSpan) o;
		if (days == null) {
			return other.days == null;
		} else if (!days.equals(other.days)) {
			return false;
		}
		if (hours == null) {
			return other.hours == null;
		} else if (!hours.equals(other.hours)) {
			return false;
		}
		if (minutes == null) {
			return other.minutes == null;
		} else if (!minutes.equals(other.minutes)) {
			return false;
		}
		return true;
	}
}