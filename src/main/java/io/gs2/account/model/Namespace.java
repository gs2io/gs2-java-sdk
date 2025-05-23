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

package io.gs2.account.model;

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
	private Boolean changePasswordIfTakeOver;
	private Boolean differentUserIdForLoginAndDataRetention;
	private ScriptSetting createAccountScript;
	private ScriptSetting authenticationScript;
	private ScriptSetting createTakeOverScript;
	private ScriptSetting doTakeOverScript;
	private ScriptSetting banScript;
	private ScriptSetting unBanScript;
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
	public Boolean getChangePasswordIfTakeOver() {
		return changePasswordIfTakeOver;
	}
	public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
	}
	public Namespace withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
		return this;
	}
	public Boolean getDifferentUserIdForLoginAndDataRetention() {
		return differentUserIdForLoginAndDataRetention;
	}
	public void setDifferentUserIdForLoginAndDataRetention(Boolean differentUserIdForLoginAndDataRetention) {
		this.differentUserIdForLoginAndDataRetention = differentUserIdForLoginAndDataRetention;
	}
	public Namespace withDifferentUserIdForLoginAndDataRetention(Boolean differentUserIdForLoginAndDataRetention) {
		this.differentUserIdForLoginAndDataRetention = differentUserIdForLoginAndDataRetention;
		return this;
	}
	public ScriptSetting getCreateAccountScript() {
		return createAccountScript;
	}
	public void setCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
	}
	public Namespace withCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
		return this;
	}
	public ScriptSetting getAuthenticationScript() {
		return authenticationScript;
	}
	public void setAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
	}
	public Namespace withAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
		return this;
	}
	public ScriptSetting getCreateTakeOverScript() {
		return createTakeOverScript;
	}
	public void setCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
	}
	public Namespace withCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
		return this;
	}
	public ScriptSetting getDoTakeOverScript() {
		return doTakeOverScript;
	}
	public void setDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
	}
	public Namespace withDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
		return this;
	}
	public ScriptSetting getBanScript() {
		return banScript;
	}
	public void setBanScript(ScriptSetting banScript) {
		this.banScript = banScript;
	}
	public Namespace withBanScript(ScriptSetting banScript) {
		this.banScript = banScript;
		return this;
	}
	public ScriptSetting getUnBanScript() {
		return unBanScript;
	}
	public void setUnBanScript(ScriptSetting unBanScript) {
		this.unBanScript = unBanScript;
	}
	public Namespace withUnBanScript(ScriptSetting unBanScript) {
		this.unBanScript = unBanScript;
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
            .withChangePasswordIfTakeOver(data.get("changePasswordIfTakeOver") == null || data.get("changePasswordIfTakeOver").isNull() ? null : data.get("changePasswordIfTakeOver").booleanValue())
            .withDifferentUserIdForLoginAndDataRetention(data.get("differentUserIdForLoginAndDataRetention") == null || data.get("differentUserIdForLoginAndDataRetention").isNull() ? null : data.get("differentUserIdForLoginAndDataRetention").booleanValue())
            .withCreateAccountScript(data.get("createAccountScript") == null || data.get("createAccountScript").isNull() ? null : ScriptSetting.fromJson(data.get("createAccountScript")))
            .withAuthenticationScript(data.get("authenticationScript") == null || data.get("authenticationScript").isNull() ? null : ScriptSetting.fromJson(data.get("authenticationScript")))
            .withCreateTakeOverScript(data.get("createTakeOverScript") == null || data.get("createTakeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("createTakeOverScript")))
            .withDoTakeOverScript(data.get("doTakeOverScript") == null || data.get("doTakeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("doTakeOverScript")))
            .withBanScript(data.get("banScript") == null || data.get("banScript").isNull() ? null : ScriptSetting.fromJson(data.get("banScript")))
            .withUnBanScript(data.get("unBanScript") == null || data.get("unBanScript").isNull() ? null : ScriptSetting.fromJson(data.get("unBanScript")))
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
                put("changePasswordIfTakeOver", getChangePasswordIfTakeOver());
                put("differentUserIdForLoginAndDataRetention", getDifferentUserIdForLoginAndDataRetention());
                put("createAccountScript", getCreateAccountScript() != null ? getCreateAccountScript().toJson() : null);
                put("authenticationScript", getAuthenticationScript() != null ? getAuthenticationScript().toJson() : null);
                put("createTakeOverScript", getCreateTakeOverScript() != null ? getCreateTakeOverScript().toJson() : null);
                put("doTakeOverScript", getDoTakeOverScript() != null ? getDoTakeOverScript().toJson() : null);
                put("banScript", getBanScript() != null ? getBanScript().toJson() : null);
                put("unBanScript", getUnBanScript() != null ? getUnBanScript().toJson() : null);
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
        result = prime * result + ((this.changePasswordIfTakeOver == null) ? 0 : this.changePasswordIfTakeOver.hashCode());
        result = prime * result + ((this.differentUserIdForLoginAndDataRetention == null) ? 0 : this.differentUserIdForLoginAndDataRetention.hashCode());
        result = prime * result + ((this.createAccountScript == null) ? 0 : this.createAccountScript.hashCode());
        result = prime * result + ((this.authenticationScript == null) ? 0 : this.authenticationScript.hashCode());
        result = prime * result + ((this.createTakeOverScript == null) ? 0 : this.createTakeOverScript.hashCode());
        result = prime * result + ((this.doTakeOverScript == null) ? 0 : this.doTakeOverScript.hashCode());
        result = prime * result + ((this.banScript == null) ? 0 : this.banScript.hashCode());
        result = prime * result + ((this.unBanScript == null) ? 0 : this.unBanScript.hashCode());
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
		if (changePasswordIfTakeOver == null) {
			return other.changePasswordIfTakeOver == null;
		} else if (!changePasswordIfTakeOver.equals(other.changePasswordIfTakeOver)) {
			return false;
		}
		if (differentUserIdForLoginAndDataRetention == null) {
			return other.differentUserIdForLoginAndDataRetention == null;
		} else if (!differentUserIdForLoginAndDataRetention.equals(other.differentUserIdForLoginAndDataRetention)) {
			return false;
		}
		if (createAccountScript == null) {
			return other.createAccountScript == null;
		} else if (!createAccountScript.equals(other.createAccountScript)) {
			return false;
		}
		if (authenticationScript == null) {
			return other.authenticationScript == null;
		} else if (!authenticationScript.equals(other.authenticationScript)) {
			return false;
		}
		if (createTakeOverScript == null) {
			return other.createTakeOverScript == null;
		} else if (!createTakeOverScript.equals(other.createTakeOverScript)) {
			return false;
		}
		if (doTakeOverScript == null) {
			return other.doTakeOverScript == null;
		} else if (!doTakeOverScript.equals(other.doTakeOverScript)) {
			return false;
		}
		if (banScript == null) {
			return other.banScript == null;
		} else if (!banScript.equals(other.banScript)) {
			return false;
		}
		if (unBanScript == null) {
			return other.unBanScript == null;
		} else if (!unBanScript.equals(other.unBanScript)) {
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