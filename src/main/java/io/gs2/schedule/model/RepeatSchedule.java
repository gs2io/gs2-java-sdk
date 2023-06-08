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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RepeatSchedule implements IModel, Serializable {
	private Integer repeatCount;
	private Long currentRepeatStartAt;
	private Long currentRepeatEndAt;
	private Long lastRepeatEndAt;
	private Long nextRepeatStartAt;
	public Integer getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}
	public RepeatSchedule withRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
		return this;
	}
	public Long getCurrentRepeatStartAt() {
		return currentRepeatStartAt;
	}
	public void setCurrentRepeatStartAt(Long currentRepeatStartAt) {
		this.currentRepeatStartAt = currentRepeatStartAt;
	}
	public RepeatSchedule withCurrentRepeatStartAt(Long currentRepeatStartAt) {
		this.currentRepeatStartAt = currentRepeatStartAt;
		return this;
	}
	public Long getCurrentRepeatEndAt() {
		return currentRepeatEndAt;
	}
	public void setCurrentRepeatEndAt(Long currentRepeatEndAt) {
		this.currentRepeatEndAt = currentRepeatEndAt;
	}
	public RepeatSchedule withCurrentRepeatEndAt(Long currentRepeatEndAt) {
		this.currentRepeatEndAt = currentRepeatEndAt;
		return this;
	}
	public Long getLastRepeatEndAt() {
		return lastRepeatEndAt;
	}
	public void setLastRepeatEndAt(Long lastRepeatEndAt) {
		this.lastRepeatEndAt = lastRepeatEndAt;
	}
	public RepeatSchedule withLastRepeatEndAt(Long lastRepeatEndAt) {
		this.lastRepeatEndAt = lastRepeatEndAt;
		return this;
	}
	public Long getNextRepeatStartAt() {
		return nextRepeatStartAt;
	}
	public void setNextRepeatStartAt(Long nextRepeatStartAt) {
		this.nextRepeatStartAt = nextRepeatStartAt;
	}
	public RepeatSchedule withNextRepeatStartAt(Long nextRepeatStartAt) {
		this.nextRepeatStartAt = nextRepeatStartAt;
		return this;
	}

    public static RepeatSchedule fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RepeatSchedule()
            .withRepeatCount(data.get("repeatCount") == null || data.get("repeatCount").isNull() ? null : data.get("repeatCount").intValue())
            .withCurrentRepeatStartAt(data.get("currentRepeatStartAt") == null || data.get("currentRepeatStartAt").isNull() ? null : data.get("currentRepeatStartAt").longValue())
            .withCurrentRepeatEndAt(data.get("currentRepeatEndAt") == null || data.get("currentRepeatEndAt").isNull() ? null : data.get("currentRepeatEndAt").longValue())
            .withLastRepeatEndAt(data.get("lastRepeatEndAt") == null || data.get("lastRepeatEndAt").isNull() ? null : data.get("lastRepeatEndAt").longValue())
            .withNextRepeatStartAt(data.get("nextRepeatStartAt") == null || data.get("nextRepeatStartAt").isNull() ? null : data.get("nextRepeatStartAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("repeatCount", getRepeatCount());
                put("currentRepeatStartAt", getCurrentRepeatStartAt());
                put("currentRepeatEndAt", getCurrentRepeatEndAt());
                put("lastRepeatEndAt", getLastRepeatEndAt());
                put("nextRepeatStartAt", getNextRepeatStartAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.repeatCount == null) ? 0 : this.repeatCount.hashCode());
        result = prime * result + ((this.currentRepeatStartAt == null) ? 0 : this.currentRepeatStartAt.hashCode());
        result = prime * result + ((this.currentRepeatEndAt == null) ? 0 : this.currentRepeatEndAt.hashCode());
        result = prime * result + ((this.lastRepeatEndAt == null) ? 0 : this.lastRepeatEndAt.hashCode());
        result = prime * result + ((this.nextRepeatStartAt == null) ? 0 : this.nextRepeatStartAt.hashCode());
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
		RepeatSchedule other = (RepeatSchedule) o;
		if (repeatCount == null) {
			return other.repeatCount == null;
		} else if (!repeatCount.equals(other.repeatCount)) {
			return false;
		}
		if (currentRepeatStartAt == null) {
			return other.currentRepeatStartAt == null;
		} else if (!currentRepeatStartAt.equals(other.currentRepeatStartAt)) {
			return false;
		}
		if (currentRepeatEndAt == null) {
			return other.currentRepeatEndAt == null;
		} else if (!currentRepeatEndAt.equals(other.currentRepeatEndAt)) {
			return false;
		}
		if (lastRepeatEndAt == null) {
			return other.lastRepeatEndAt == null;
		} else if (!lastRepeatEndAt.equals(other.lastRepeatEndAt)) {
			return false;
		}
		if (nextRepeatStartAt == null) {
			return other.nextRepeatStartAt == null;
		} else if (!nextRepeatStartAt.equals(other.nextRepeatStartAt)) {
			return false;
		}
		return true;
	}
}