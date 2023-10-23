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

package io.gs2.experience.model;

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
	private TransactionSetting transactionSetting;
	private String rankCapScriptId;
	private ScriptSetting changeExperienceScript;
	private ScriptSetting changeRankScript;
	private ScriptSetting changeRankCapScript;
	private String overflowExperienceScript;
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
	public TransactionSetting getTransactionSetting() {
		return transactionSetting;
	}
	public void setTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
	}
	public Namespace withTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
		return this;
	}
	public String getRankCapScriptId() {
		return rankCapScriptId;
	}
	public void setRankCapScriptId(String rankCapScriptId) {
		this.rankCapScriptId = rankCapScriptId;
	}
	public Namespace withRankCapScriptId(String rankCapScriptId) {
		this.rankCapScriptId = rankCapScriptId;
		return this;
	}
	public ScriptSetting getChangeExperienceScript() {
		return changeExperienceScript;
	}
	public void setChangeExperienceScript(ScriptSetting changeExperienceScript) {
		this.changeExperienceScript = changeExperienceScript;
	}
	public Namespace withChangeExperienceScript(ScriptSetting changeExperienceScript) {
		this.changeExperienceScript = changeExperienceScript;
		return this;
	}
	public ScriptSetting getChangeRankScript() {
		return changeRankScript;
	}
	public void setChangeRankScript(ScriptSetting changeRankScript) {
		this.changeRankScript = changeRankScript;
	}
	public Namespace withChangeRankScript(ScriptSetting changeRankScript) {
		this.changeRankScript = changeRankScript;
		return this;
	}
	public ScriptSetting getChangeRankCapScript() {
		return changeRankCapScript;
	}
	public void setChangeRankCapScript(ScriptSetting changeRankCapScript) {
		this.changeRankCapScript = changeRankCapScript;
	}
	public Namespace withChangeRankCapScript(ScriptSetting changeRankCapScript) {
		this.changeRankCapScript = changeRankCapScript;
		return this;
	}
	public String getOverflowExperienceScript() {
		return overflowExperienceScript;
	}
	public void setOverflowExperienceScript(String overflowExperienceScript) {
		this.overflowExperienceScript = overflowExperienceScript;
	}
	public Namespace withOverflowExperienceScript(String overflowExperienceScript) {
		this.overflowExperienceScript = overflowExperienceScript;
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
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withRankCapScriptId(data.get("rankCapScriptId") == null || data.get("rankCapScriptId").isNull() ? null : data.get("rankCapScriptId").asText())
            .withChangeExperienceScript(data.get("changeExperienceScript") == null || data.get("changeExperienceScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeExperienceScript")))
            .withChangeRankScript(data.get("changeRankScript") == null || data.get("changeRankScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRankScript")))
            .withChangeRankCapScript(data.get("changeRankCapScript") == null || data.get("changeRankCapScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRankCapScript")))
            .withOverflowExperienceScript(data.get("overflowExperienceScript") == null || data.get("overflowExperienceScript").isNull() ? null : data.get("overflowExperienceScript").asText())
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
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("rankCapScriptId", getRankCapScriptId());
                put("changeExperienceScript", getChangeExperienceScript() != null ? getChangeExperienceScript().toJson() : null);
                put("changeRankScript", getChangeRankScript() != null ? getChangeRankScript().toJson() : null);
                put("changeRankCapScript", getChangeRankCapScript() != null ? getChangeRankCapScript().toJson() : null);
                put("overflowExperienceScript", getOverflowExperienceScript());
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
        result = prime * result + ((this.transactionSetting == null) ? 0 : this.transactionSetting.hashCode());
        result = prime * result + ((this.rankCapScriptId == null) ? 0 : this.rankCapScriptId.hashCode());
        result = prime * result + ((this.changeExperienceScript == null) ? 0 : this.changeExperienceScript.hashCode());
        result = prime * result + ((this.changeRankScript == null) ? 0 : this.changeRankScript.hashCode());
        result = prime * result + ((this.changeRankCapScript == null) ? 0 : this.changeRankCapScript.hashCode());
        result = prime * result + ((this.overflowExperienceScript == null) ? 0 : this.overflowExperienceScript.hashCode());
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
		if (transactionSetting == null) {
			return other.transactionSetting == null;
		} else if (!transactionSetting.equals(other.transactionSetting)) {
			return false;
		}
		if (rankCapScriptId == null) {
			return other.rankCapScriptId == null;
		} else if (!rankCapScriptId.equals(other.rankCapScriptId)) {
			return false;
		}
		if (changeExperienceScript == null) {
			return other.changeExperienceScript == null;
		} else if (!changeExperienceScript.equals(other.changeExperienceScript)) {
			return false;
		}
		if (changeRankScript == null) {
			return other.changeRankScript == null;
		} else if (!changeRankScript.equals(other.changeRankScript)) {
			return false;
		}
		if (changeRankCapScript == null) {
			return other.changeRankCapScript == null;
		} else if (!changeRankCapScript.equals(other.changeRankCapScript)) {
			return false;
		}
		if (overflowExperienceScript == null) {
			return other.overflowExperienceScript == null;
		} else if (!overflowExperienceScript.equals(other.overflowExperienceScript)) {
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