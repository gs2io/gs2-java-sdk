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

package io.gs2.schedule.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateEventMasterRequest extends Gs2BasicRequest<CreateEventMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private String scheduleType;
    private Long absoluteBegin;
    private Long absoluteEnd;
    private String repeatType;
    private Integer repeatBeginDayOfMonth;
    private Integer repeatEndDayOfMonth;
    private String repeatBeginDayOfWeek;
    private String repeatEndDayOfWeek;
    private Integer repeatBeginHour;
    private Integer repeatEndHour;
    private String relativeTriggerName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateEventMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateEventMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateEventMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateEventMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public CreateEventMasterRequest withScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
		return this;
	}
	public Long getAbsoluteBegin() {
		return absoluteBegin;
	}
	public void setAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
	}
	public CreateEventMasterRequest withAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
		return this;
	}
	public Long getAbsoluteEnd() {
		return absoluteEnd;
	}
	public void setAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
	}
	public CreateEventMasterRequest withAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
		return this;
	}
	public String getRepeatType() {
		return repeatType;
	}
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}
	public CreateEventMasterRequest withRepeatType(String repeatType) {
		this.repeatType = repeatType;
		return this;
	}
	public Integer getRepeatBeginDayOfMonth() {
		return repeatBeginDayOfMonth;
	}
	public void setRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
	}
	public CreateEventMasterRequest withRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
		return this;
	}
	public Integer getRepeatEndDayOfMonth() {
		return repeatEndDayOfMonth;
	}
	public void setRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
	}
	public CreateEventMasterRequest withRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
		return this;
	}
	public String getRepeatBeginDayOfWeek() {
		return repeatBeginDayOfWeek;
	}
	public void setRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
	}
	public CreateEventMasterRequest withRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
		return this;
	}
	public String getRepeatEndDayOfWeek() {
		return repeatEndDayOfWeek;
	}
	public void setRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
	}
	public CreateEventMasterRequest withRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
		return this;
	}
	public Integer getRepeatBeginHour() {
		return repeatBeginHour;
	}
	public void setRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
	}
	public CreateEventMasterRequest withRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
		return this;
	}
	public Integer getRepeatEndHour() {
		return repeatEndHour;
	}
	public void setRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
	}
	public CreateEventMasterRequest withRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
		return this;
	}
	public String getRelativeTriggerName() {
		return relativeTriggerName;
	}
	public void setRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
	}
	public CreateEventMasterRequest withRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
		return this;
	}

    public static CreateEventMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateEventMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withScheduleType(data.get("scheduleType") == null || data.get("scheduleType").isNull() ? null : data.get("scheduleType").asText())
            .withAbsoluteBegin(data.get("absoluteBegin") == null || data.get("absoluteBegin").isNull() ? null : data.get("absoluteBegin").longValue())
            .withAbsoluteEnd(data.get("absoluteEnd") == null || data.get("absoluteEnd").isNull() ? null : data.get("absoluteEnd").longValue())
            .withRepeatType(data.get("repeatType") == null || data.get("repeatType").isNull() ? null : data.get("repeatType").asText())
            .withRepeatBeginDayOfMonth(data.get("repeatBeginDayOfMonth") == null || data.get("repeatBeginDayOfMonth").isNull() ? null : data.get("repeatBeginDayOfMonth").intValue())
            .withRepeatEndDayOfMonth(data.get("repeatEndDayOfMonth") == null || data.get("repeatEndDayOfMonth").isNull() ? null : data.get("repeatEndDayOfMonth").intValue())
            .withRepeatBeginDayOfWeek(data.get("repeatBeginDayOfWeek") == null || data.get("repeatBeginDayOfWeek").isNull() ? null : data.get("repeatBeginDayOfWeek").asText())
            .withRepeatEndDayOfWeek(data.get("repeatEndDayOfWeek") == null || data.get("repeatEndDayOfWeek").isNull() ? null : data.get("repeatEndDayOfWeek").asText())
            .withRepeatBeginHour(data.get("repeatBeginHour") == null || data.get("repeatBeginHour").isNull() ? null : data.get("repeatBeginHour").intValue())
            .withRepeatEndHour(data.get("repeatEndHour") == null || data.get("repeatEndHour").isNull() ? null : data.get("repeatEndHour").intValue())
            .withRelativeTriggerName(data.get("relativeTriggerName") == null || data.get("relativeTriggerName").isNull() ? null : data.get("relativeTriggerName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("scheduleType", getScheduleType());
                put("absoluteBegin", getAbsoluteBegin());
                put("absoluteEnd", getAbsoluteEnd());
                put("repeatType", getRepeatType());
                put("repeatBeginDayOfMonth", getRepeatBeginDayOfMonth());
                put("repeatEndDayOfMonth", getRepeatEndDayOfMonth());
                put("repeatBeginDayOfWeek", getRepeatBeginDayOfWeek());
                put("repeatEndDayOfWeek", getRepeatEndDayOfWeek());
                put("repeatBeginHour", getRepeatBeginHour());
                put("repeatEndHour", getRepeatEndHour());
                put("relativeTriggerName", getRelativeTriggerName());
            }}
        );
    }
}