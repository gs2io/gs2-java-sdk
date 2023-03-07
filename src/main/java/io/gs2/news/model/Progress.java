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
public class Progress implements IModel, Serializable, Comparable<Progress> {
	private String progressId;
	private String uploadToken;
	private Integer generated;
	private Integer patternCount;
	private Long createdAt;
	private Long updatedAt;
	public String getProgressId() {
		return progressId;
	}
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	public Progress withProgressId(String progressId) {
		this.progressId = progressId;
		return this;
	}
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public Progress withUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
		return this;
	}
	public Integer getGenerated() {
		return generated;
	}
	public void setGenerated(Integer generated) {
		this.generated = generated;
	}
	public Progress withGenerated(Integer generated) {
		this.generated = generated;
		return this;
	}
	public Integer getPatternCount() {
		return patternCount;
	}
	public void setPatternCount(Integer patternCount) {
		this.patternCount = patternCount;
	}
	public Progress withPatternCount(Integer patternCount) {
		this.patternCount = patternCount;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Progress withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Progress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Progress fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Progress()
            .withProgressId(data.get("progressId") == null || data.get("progressId").isNull() ? null : data.get("progressId").asText())
            .withUploadToken(data.get("uploadToken") == null || data.get("uploadToken").isNull() ? null : data.get("uploadToken").asText())
            .withGenerated(data.get("generated") == null || data.get("generated").isNull() ? null : data.get("generated").intValue())
            .withPatternCount(data.get("patternCount") == null || data.get("patternCount").isNull() ? null : data.get("patternCount").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("progressId", getProgressId());
                put("uploadToken", getUploadToken());
                put("generated", getGenerated());
                put("patternCount", getPatternCount());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Progress o) {
		return progressId.compareTo(o.progressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.progressId == null) ? 0 : this.progressId.hashCode());
        result = prime * result + ((this.uploadToken == null) ? 0 : this.uploadToken.hashCode());
        result = prime * result + ((this.generated == null) ? 0 : this.generated.hashCode());
        result = prime * result + ((this.patternCount == null) ? 0 : this.patternCount.hashCode());
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
		Progress other = (Progress) o;
		if (progressId == null) {
			return other.progressId == null;
		} else if (!progressId.equals(other.progressId)) {
			return false;
		}
		if (uploadToken == null) {
			return other.uploadToken == null;
		} else if (!uploadToken.equals(other.uploadToken)) {
			return false;
		}
		if (generated == null) {
			return other.generated == null;
		} else if (!generated.equals(other.generated)) {
			return false;
		}
		if (patternCount == null) {
			return other.patternCount == null;
		} else if (!patternCount.equals(other.patternCount)) {
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