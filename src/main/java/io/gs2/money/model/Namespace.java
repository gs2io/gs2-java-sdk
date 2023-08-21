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

package io.gs2.money.model;

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
	private String priority;
	private Boolean shareFree;
	private String currency;
	private String appleKey;
	private String googleKey;
	private Boolean enableFakeReceipt;
	private ScriptSetting createWalletScript;
	private ScriptSetting depositScript;
	private ScriptSetting withdrawScript;
	private Double balance;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Namespace withPriority(String priority) {
		this.priority = priority;
		return this;
	}
	public Boolean getShareFree() {
		return shareFree;
	}
	public void setShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
	}
	public Namespace withShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Namespace withCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getAppleKey() {
		return appleKey;
	}
	public void setAppleKey(String appleKey) {
		this.appleKey = appleKey;
	}
	public Namespace withAppleKey(String appleKey) {
		this.appleKey = appleKey;
		return this;
	}
	public String getGoogleKey() {
		return googleKey;
	}
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}
	public Namespace withGoogleKey(String googleKey) {
		this.googleKey = googleKey;
		return this;
	}
	public Boolean getEnableFakeReceipt() {
		return enableFakeReceipt;
	}
	public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
	}
	public Namespace withEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
		return this;
	}
	public ScriptSetting getCreateWalletScript() {
		return createWalletScript;
	}
	public void setCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
	}
	public Namespace withCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
		return this;
	}
	public ScriptSetting getDepositScript() {
		return depositScript;
	}
	public void setDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
	}
	public Namespace withDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
		return this;
	}
	public ScriptSetting getWithdrawScript() {
		return withdrawScript;
	}
	public void setWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
	}
	public Namespace withWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
		return this;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Namespace withBalance(Double balance) {
		this.balance = balance;
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
            .withPriority(data.get("priority") == null || data.get("priority").isNull() ? null : data.get("priority").asText())
            .withShareFree(data.get("shareFree") == null || data.get("shareFree").isNull() ? null : data.get("shareFree").booleanValue())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withAppleKey(data.get("appleKey") == null || data.get("appleKey").isNull() ? null : data.get("appleKey").asText())
            .withGoogleKey(data.get("googleKey") == null || data.get("googleKey").isNull() ? null : data.get("googleKey").asText())
            .withEnableFakeReceipt(data.get("enableFakeReceipt") == null || data.get("enableFakeReceipt").isNull() ? null : data.get("enableFakeReceipt").booleanValue())
            .withCreateWalletScript(data.get("createWalletScript") == null || data.get("createWalletScript").isNull() ? null : ScriptSetting.fromJson(data.get("createWalletScript")))
            .withDepositScript(data.get("depositScript") == null || data.get("depositScript").isNull() ? null : ScriptSetting.fromJson(data.get("depositScript")))
            .withWithdrawScript(data.get("withdrawScript") == null || data.get("withdrawScript").isNull() ? null : ScriptSetting.fromJson(data.get("withdrawScript")))
            .withBalance(data.get("balance") == null || data.get("balance").isNull() ? null : data.get("balance").doubleValue())
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
                put("priority", getPriority());
                put("shareFree", getShareFree());
                put("currency", getCurrency());
                put("appleKey", getAppleKey());
                put("googleKey", getGoogleKey());
                put("enableFakeReceipt", getEnableFakeReceipt());
                put("createWalletScript", getCreateWalletScript() != null ? getCreateWalletScript().toJson() : null);
                put("depositScript", getDepositScript() != null ? getDepositScript().toJson() : null);
                put("withdrawScript", getWithdrawScript() != null ? getWithdrawScript().toJson() : null);
                put("balance", getBalance());
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
        result = prime * result + ((this.priority == null) ? 0 : this.priority.hashCode());
        result = prime * result + ((this.shareFree == null) ? 0 : this.shareFree.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.appleKey == null) ? 0 : this.appleKey.hashCode());
        result = prime * result + ((this.googleKey == null) ? 0 : this.googleKey.hashCode());
        result = prime * result + ((this.enableFakeReceipt == null) ? 0 : this.enableFakeReceipt.hashCode());
        result = prime * result + ((this.createWalletScript == null) ? 0 : this.createWalletScript.hashCode());
        result = prime * result + ((this.depositScript == null) ? 0 : this.depositScript.hashCode());
        result = prime * result + ((this.withdrawScript == null) ? 0 : this.withdrawScript.hashCode());
        result = prime * result + ((this.balance == null) ? 0 : this.balance.hashCode());
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
		if (priority == null) {
			return other.priority == null;
		} else if (!priority.equals(other.priority)) {
			return false;
		}
		if (shareFree == null) {
			return other.shareFree == null;
		} else if (!shareFree.equals(other.shareFree)) {
			return false;
		}
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (appleKey == null) {
			return other.appleKey == null;
		} else if (!appleKey.equals(other.appleKey)) {
			return false;
		}
		if (googleKey == null) {
			return other.googleKey == null;
		} else if (!googleKey.equals(other.googleKey)) {
			return false;
		}
		if (enableFakeReceipt == null) {
			return other.enableFakeReceipt == null;
		} else if (!enableFakeReceipt.equals(other.enableFakeReceipt)) {
			return false;
		}
		if (createWalletScript == null) {
			return other.createWalletScript == null;
		} else if (!createWalletScript.equals(other.createWalletScript)) {
			return false;
		}
		if (depositScript == null) {
			return other.depositScript == null;
		} else if (!depositScript.equals(other.depositScript)) {
			return false;
		}
		if (withdrawScript == null) {
			return other.withdrawScript == null;
		} else if (!withdrawScript.equals(other.withdrawScript)) {
			return false;
		}
		if (balance == null) {
			return other.balance == null;
		} else if (!balance.equals(other.balance)) {
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