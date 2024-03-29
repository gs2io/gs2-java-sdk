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

package io.gs2.inventory.model;

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
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	private String namespaceId;
	private String name;
	private String description;
	private ScriptSetting acquireScript;
	private ScriptSetting overflowScript;
	private ScriptSetting consumeScript;
	private ScriptSetting simpleItemAcquireScript;
	private ScriptSetting simpleItemConsumeScript;
	private ScriptSetting bigItemAcquireScript;
	private ScriptSetting bigItemConsumeScript;
	private LogSetting logSetting;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getNamespaceId() {
		return namespaceId;
	}
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	public ScriptSetting getAcquireScript() {
		return acquireScript;
	}
	public void setAcquireScript(ScriptSetting acquireScript) {
		this.acquireScript = acquireScript;
	}
	public Namespace withAcquireScript(ScriptSetting acquireScript) {
		this.acquireScript = acquireScript;
		return this;
	}
	public ScriptSetting getOverflowScript() {
		return overflowScript;
	}
	public void setOverflowScript(ScriptSetting overflowScript) {
		this.overflowScript = overflowScript;
	}
	public Namespace withOverflowScript(ScriptSetting overflowScript) {
		this.overflowScript = overflowScript;
		return this;
	}
	public ScriptSetting getConsumeScript() {
		return consumeScript;
	}
	public void setConsumeScript(ScriptSetting consumeScript) {
		this.consumeScript = consumeScript;
	}
	public Namespace withConsumeScript(ScriptSetting consumeScript) {
		this.consumeScript = consumeScript;
		return this;
	}
	public ScriptSetting getSimpleItemAcquireScript() {
		return simpleItemAcquireScript;
	}
	public void setSimpleItemAcquireScript(ScriptSetting simpleItemAcquireScript) {
		this.simpleItemAcquireScript = simpleItemAcquireScript;
	}
	public Namespace withSimpleItemAcquireScript(ScriptSetting simpleItemAcquireScript) {
		this.simpleItemAcquireScript = simpleItemAcquireScript;
		return this;
	}
	public ScriptSetting getSimpleItemConsumeScript() {
		return simpleItemConsumeScript;
	}
	public void setSimpleItemConsumeScript(ScriptSetting simpleItemConsumeScript) {
		this.simpleItemConsumeScript = simpleItemConsumeScript;
	}
	public Namespace withSimpleItemConsumeScript(ScriptSetting simpleItemConsumeScript) {
		this.simpleItemConsumeScript = simpleItemConsumeScript;
		return this;
	}
	public ScriptSetting getBigItemAcquireScript() {
		return bigItemAcquireScript;
	}
	public void setBigItemAcquireScript(ScriptSetting bigItemAcquireScript) {
		this.bigItemAcquireScript = bigItemAcquireScript;
	}
	public Namespace withBigItemAcquireScript(ScriptSetting bigItemAcquireScript) {
		this.bigItemAcquireScript = bigItemAcquireScript;
		return this;
	}
	public ScriptSetting getBigItemConsumeScript() {
		return bigItemConsumeScript;
	}
	public void setBigItemConsumeScript(ScriptSetting bigItemConsumeScript) {
		this.bigItemConsumeScript = bigItemConsumeScript;
	}
	public Namespace withBigItemConsumeScript(ScriptSetting bigItemConsumeScript) {
		this.bigItemConsumeScript = bigItemConsumeScript;
		return this;
	}
	public LogSetting getLogSetting() {
		return logSetting;
	}
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}
	public Namespace withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Namespace withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Namespace withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Namespace fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Namespace()
            .withNamespaceId(data.get("namespaceId") == null || data.get("namespaceId").isNull() ? null : data.get("namespaceId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withAcquireScript(data.get("acquireScript") == null || data.get("acquireScript").isNull() ? null : ScriptSetting.fromJson(data.get("acquireScript")))
            .withOverflowScript(data.get("overflowScript") == null || data.get("overflowScript").isNull() ? null : ScriptSetting.fromJson(data.get("overflowScript")))
            .withConsumeScript(data.get("consumeScript") == null || data.get("consumeScript").isNull() ? null : ScriptSetting.fromJson(data.get("consumeScript")))
            .withSimpleItemAcquireScript(data.get("simpleItemAcquireScript") == null || data.get("simpleItemAcquireScript").isNull() ? null : ScriptSetting.fromJson(data.get("simpleItemAcquireScript")))
            .withSimpleItemConsumeScript(data.get("simpleItemConsumeScript") == null || data.get("simpleItemConsumeScript").isNull() ? null : ScriptSetting.fromJson(data.get("simpleItemConsumeScript")))
            .withBigItemAcquireScript(data.get("bigItemAcquireScript") == null || data.get("bigItemAcquireScript").isNull() ? null : ScriptSetting.fromJson(data.get("bigItemAcquireScript")))
            .withBigItemConsumeScript(data.get("bigItemConsumeScript") == null || data.get("bigItemConsumeScript").isNull() ? null : ScriptSetting.fromJson(data.get("bigItemConsumeScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceId", getNamespaceId());
                put("name", getName());
                put("description", getDescription());
                put("acquireScript", getAcquireScript() != null ? getAcquireScript().toJson() : null);
                put("overflowScript", getOverflowScript() != null ? getOverflowScript().toJson() : null);
                put("consumeScript", getConsumeScript() != null ? getConsumeScript().toJson() : null);
                put("simpleItemAcquireScript", getSimpleItemAcquireScript() != null ? getSimpleItemAcquireScript().toJson() : null);
                put("simpleItemConsumeScript", getSimpleItemConsumeScript() != null ? getSimpleItemConsumeScript().toJson() : null);
                put("bigItemAcquireScript", getBigItemAcquireScript() != null ? getBigItemAcquireScript().toJson() : null);
                put("bigItemConsumeScript", getBigItemConsumeScript() != null ? getBigItemConsumeScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Namespace o) {
		return namespaceId.compareTo(o.namespaceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceId == null) ? 0 : this.namespaceId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.acquireScript == null) ? 0 : this.acquireScript.hashCode());
        result = prime * result + ((this.overflowScript == null) ? 0 : this.overflowScript.hashCode());
        result = prime * result + ((this.consumeScript == null) ? 0 : this.consumeScript.hashCode());
        result = prime * result + ((this.simpleItemAcquireScript == null) ? 0 : this.simpleItemAcquireScript.hashCode());
        result = prime * result + ((this.simpleItemConsumeScript == null) ? 0 : this.simpleItemConsumeScript.hashCode());
        result = prime * result + ((this.bigItemAcquireScript == null) ? 0 : this.bigItemAcquireScript.hashCode());
        result = prime * result + ((this.bigItemConsumeScript == null) ? 0 : this.bigItemConsumeScript.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		Namespace other = (Namespace) o;
		if (namespaceId == null) {
			return other.namespaceId == null;
		} else if (!namespaceId.equals(other.namespaceId)) {
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
		if (acquireScript == null) {
			return other.acquireScript == null;
		} else if (!acquireScript.equals(other.acquireScript)) {
			return false;
		}
		if (overflowScript == null) {
			return other.overflowScript == null;
		} else if (!overflowScript.equals(other.overflowScript)) {
			return false;
		}
		if (consumeScript == null) {
			return other.consumeScript == null;
		} else if (!consumeScript.equals(other.consumeScript)) {
			return false;
		}
		if (simpleItemAcquireScript == null) {
			return other.simpleItemAcquireScript == null;
		} else if (!simpleItemAcquireScript.equals(other.simpleItemAcquireScript)) {
			return false;
		}
		if (simpleItemConsumeScript == null) {
			return other.simpleItemConsumeScript == null;
		} else if (!simpleItemConsumeScript.equals(other.simpleItemConsumeScript)) {
			return false;
		}
		if (bigItemAcquireScript == null) {
			return other.bigItemAcquireScript == null;
		} else if (!bigItemAcquireScript.equals(other.bigItemAcquireScript)) {
			return false;
		}
		if (bigItemConsumeScript == null) {
			return other.bigItemConsumeScript == null;
		} else if (!bigItemConsumeScript.equals(other.bigItemConsumeScript)) {
			return false;
		}
		if (logSetting == null) {
			return other.logSetting == null;
		} else if (!logSetting.equals(other.logSetting)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}