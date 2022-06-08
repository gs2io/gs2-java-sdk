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

package io.gs2.news.model;

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
public class News implements IModel, Serializable {
	private String section;
	private String content;
	private String title;
	private String scheduleEventId;
	private Long timestamp;
	private String frontMatter;
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public News withSection(String section) {
		this.section = section;
		return this;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public News withContent(String content) {
		this.content = content;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public News withTitle(String title) {
		this.title = title;
		return this;
	}
	public String getScheduleEventId() {
		return scheduleEventId;
	}
	public void setScheduleEventId(String scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
	}
	public News withScheduleEventId(String scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
		return this;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public News withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	public String getFrontMatter() {
		return frontMatter;
	}
	public void setFrontMatter(String frontMatter) {
		this.frontMatter = frontMatter;
	}
	public News withFrontMatter(String frontMatter) {
		this.frontMatter = frontMatter;
		return this;
	}

    public static News fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new News()
            .withSection(data.get("section") == null || data.get("section").isNull() ? null : data.get("section").asText())
            .withContent(data.get("content") == null || data.get("content").isNull() ? null : data.get("content").asText())
            .withTitle(data.get("title") == null || data.get("title").isNull() ? null : data.get("title").asText())
            .withScheduleEventId(data.get("scheduleEventId") == null || data.get("scheduleEventId").isNull() ? null : data.get("scheduleEventId").asText())
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue())
            .withFrontMatter(data.get("frontMatter") == null || data.get("frontMatter").isNull() ? null : data.get("frontMatter").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("section", getSection());
                put("content", getContent());
                put("title", getTitle());
                put("scheduleEventId", getScheduleEventId());
                put("timestamp", getTimestamp());
                put("frontMatter", getFrontMatter());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.section == null) ? 0 : this.section.hashCode());
        result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.scheduleEventId == null) ? 0 : this.scheduleEventId.hashCode());
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.frontMatter == null) ? 0 : this.frontMatter.hashCode());
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
		News other = (News) o;
		if (section == null) {
			return other.section == null;
		} else if (!section.equals(other.section)) {
			return false;
		}
		if (content == null) {
			return other.content == null;
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (title == null) {
			return other.title == null;
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (scheduleEventId == null) {
			return other.scheduleEventId == null;
		} else if (!scheduleEventId.equals(other.scheduleEventId)) {
			return false;
		}
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (frontMatter == null) {
			return other.frontMatter == null;
		} else if (!frontMatter.equals(other.frontMatter)) {
			return false;
		}
		return true;
	}
}