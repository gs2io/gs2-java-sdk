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

package io.gs2.jobQueue.model;

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
public class JobEntry implements IModel, Serializable {
	private String scriptId;
	private String args;
	private Integer maxTryCount;

	public String getScriptId() {
		return scriptId;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public JobEntry withScriptId(String scriptId) {
		this.scriptId = scriptId;
		return this;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public JobEntry withArgs(String args) {
		this.args = args;
		return this;
	}

	public Integer getMaxTryCount() {
		return maxTryCount;
	}

	public void setMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
	}

	public JobEntry withMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
		return this;
	}

    public static JobEntry fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new JobEntry()
            .withScriptId(data.get("scriptId") == null || data.get("scriptId").isNull() ? null : data.get("scriptId").asText())
            .withArgs(data.get("args") == null || data.get("args").isNull() ? null : data.get("args").asText())
            .withMaxTryCount(data.get("maxTryCount") == null || data.get("maxTryCount").isNull() ? null : data.get("maxTryCount").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("scriptId", getScriptId());
                put("args", getArgs());
                put("maxTryCount", getMaxTryCount());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.scriptId == null) ? 0 : this.scriptId.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.maxTryCount == null) ? 0 : this.maxTryCount.hashCode());
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
		JobEntry other = (JobEntry) o;
		if (scriptId == null) {
			return other.scriptId == null;
		} else if (!scriptId.equals(other.scriptId)) {
			return false;
		}
		if (args == null) {
			return other.args == null;
		} else if (!args.equals(other.args)) {
			return false;
		}
		if (maxTryCount == null) {
			return other.maxTryCount == null;
		} else if (!maxTryCount.equals(other.maxTryCount)) {
			return false;
		}
		return true;
	}
}