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

package io.gs2.schedule.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.schedule.model.*;
import io.gs2.schedule.model.Event;
import io.gs2.schedule.model.RepeatSchedule;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetEventResult implements IResult, Serializable {
    private Event item;
    private Integer repeatCount;
    private Boolean inSchedule;
    private RepeatSchedule repeatSchedule;

	public Event getItem() {
		return item;
	}

	public void setItem(Event item) {
		this.item = item;
	}

	public GetEventResult withItem(Event item) {
		this.item = item;
		return this;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public GetEventResult withRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
		return this;
	}

	public Boolean getInSchedule() {
		return inSchedule;
	}

	public void setInSchedule(Boolean inSchedule) {
		this.inSchedule = inSchedule;
	}

	public GetEventResult withInSchedule(Boolean inSchedule) {
		this.inSchedule = inSchedule;
		return this;
	}

	public RepeatSchedule getRepeatSchedule() {
		return repeatSchedule;
	}

	public void setRepeatSchedule(RepeatSchedule repeatSchedule) {
		this.repeatSchedule = repeatSchedule;
	}

	public GetEventResult withRepeatSchedule(RepeatSchedule repeatSchedule) {
		this.repeatSchedule = repeatSchedule;
		return this;
	}

    public static GetEventResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetEventResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Event.fromJson(data.get("item")))
            .withRepeatCount(data.get("repeatCount") == null || data.get("repeatCount").isNull() ? null : data.get("repeatCount").intValue())
            .withInSchedule(data.get("inSchedule") == null || data.get("inSchedule").isNull() ? null : data.get("inSchedule").booleanValue())
            .withRepeatSchedule(data.get("repeatSchedule") == null || data.get("repeatSchedule").isNull() ? null : RepeatSchedule.fromJson(data.get("repeatSchedule")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("repeatCount", getRepeatCount());
                put("inSchedule", getInSchedule());
                put("repeatSchedule", getRepeatSchedule() != null ? getRepeatSchedule().toJson() : null);
            }}
        );
    }
}