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
public class Content implements IModel, Serializable {
	private String section;
	private String content;
	private String frontMatter;
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Content withSection(String section) {
		this.section = section;
		return this;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Content withContent(String content) {
		this.content = content;
		return this;
	}
	public String getFrontMatter() {
		return frontMatter;
	}
	public void setFrontMatter(String frontMatter) {
		this.frontMatter = frontMatter;
	}
	public Content withFrontMatter(String frontMatter) {
		this.frontMatter = frontMatter;
		return this;
	}

    public static Content fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Content()
            .withSection(data.get("section") == null || data.get("section").isNull() ? null : data.get("section").asText())
            .withContent(data.get("content") == null || data.get("content").isNull() ? null : data.get("content").asText())
            .withFrontMatter(data.get("frontMatter") == null || data.get("frontMatter").isNull() ? null : data.get("frontMatter").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("section", getSection());
                put("content", getContent());
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
		Content other = (Content) o;
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
		if (frontMatter == null) {
			return other.frontMatter == null;
		} else if (!frontMatter.equals(other.frontMatter)) {
			return false;
		}
		return true;
	}
}