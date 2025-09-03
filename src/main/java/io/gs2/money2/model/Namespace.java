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

package io.gs2.money2.model;

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
	private String currencyUsagePriority;
	private Boolean sharedFreeCurrency;
	private PlatformSetting platformSetting;
	private ScriptSetting depositBalanceScript;
	private ScriptSetting withdrawBalanceScript;
	private ScriptSetting verifyReceiptScript;
	private String subscribeScript;
	private String renewScript;
	private String unsubscribeScript;
	private ScriptSetting takeOverScript;
	private NotificationSetting changeSubscriptionStatusNotification;
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
	public String getCurrencyUsagePriority() {
		return currencyUsagePriority;
	}
	public void setCurrencyUsagePriority(String currencyUsagePriority) {
		this.currencyUsagePriority = currencyUsagePriority;
	}
	public Namespace withCurrencyUsagePriority(String currencyUsagePriority) {
		this.currencyUsagePriority = currencyUsagePriority;
		return this;
	}
	public Boolean getSharedFreeCurrency() {
		return sharedFreeCurrency;
	}
	public void setSharedFreeCurrency(Boolean sharedFreeCurrency) {
		this.sharedFreeCurrency = sharedFreeCurrency;
	}
	public Namespace withSharedFreeCurrency(Boolean sharedFreeCurrency) {
		this.sharedFreeCurrency = sharedFreeCurrency;
		return this;
	}
	public PlatformSetting getPlatformSetting() {
		return platformSetting;
	}
	public void setPlatformSetting(PlatformSetting platformSetting) {
		this.platformSetting = platformSetting;
	}
	public Namespace withPlatformSetting(PlatformSetting platformSetting) {
		this.platformSetting = platformSetting;
		return this;
	}
	public ScriptSetting getDepositBalanceScript() {
		return depositBalanceScript;
	}
	public void setDepositBalanceScript(ScriptSetting depositBalanceScript) {
		this.depositBalanceScript = depositBalanceScript;
	}
	public Namespace withDepositBalanceScript(ScriptSetting depositBalanceScript) {
		this.depositBalanceScript = depositBalanceScript;
		return this;
	}
	public ScriptSetting getWithdrawBalanceScript() {
		return withdrawBalanceScript;
	}
	public void setWithdrawBalanceScript(ScriptSetting withdrawBalanceScript) {
		this.withdrawBalanceScript = withdrawBalanceScript;
	}
	public Namespace withWithdrawBalanceScript(ScriptSetting withdrawBalanceScript) {
		this.withdrawBalanceScript = withdrawBalanceScript;
		return this;
	}
	public ScriptSetting getVerifyReceiptScript() {
		return verifyReceiptScript;
	}
	public void setVerifyReceiptScript(ScriptSetting verifyReceiptScript) {
		this.verifyReceiptScript = verifyReceiptScript;
	}
	public Namespace withVerifyReceiptScript(ScriptSetting verifyReceiptScript) {
		this.verifyReceiptScript = verifyReceiptScript;
		return this;
	}
	public String getSubscribeScript() {
		return subscribeScript;
	}
	public void setSubscribeScript(String subscribeScript) {
		this.subscribeScript = subscribeScript;
	}
	public Namespace withSubscribeScript(String subscribeScript) {
		this.subscribeScript = subscribeScript;
		return this;
	}
	public String getRenewScript() {
		return renewScript;
	}
	public void setRenewScript(String renewScript) {
		this.renewScript = renewScript;
	}
	public Namespace withRenewScript(String renewScript) {
		this.renewScript = renewScript;
		return this;
	}
	public String getUnsubscribeScript() {
		return unsubscribeScript;
	}
	public void setUnsubscribeScript(String unsubscribeScript) {
		this.unsubscribeScript = unsubscribeScript;
	}
	public Namespace withUnsubscribeScript(String unsubscribeScript) {
		this.unsubscribeScript = unsubscribeScript;
		return this;
	}
	public ScriptSetting getTakeOverScript() {
		return takeOverScript;
	}
	public void setTakeOverScript(ScriptSetting takeOverScript) {
		this.takeOverScript = takeOverScript;
	}
	public Namespace withTakeOverScript(ScriptSetting takeOverScript) {
		this.takeOverScript = takeOverScript;
		return this;
	}
	public NotificationSetting getChangeSubscriptionStatusNotification() {
		return changeSubscriptionStatusNotification;
	}
	public void setChangeSubscriptionStatusNotification(NotificationSetting changeSubscriptionStatusNotification) {
		this.changeSubscriptionStatusNotification = changeSubscriptionStatusNotification;
	}
	public Namespace withChangeSubscriptionStatusNotification(NotificationSetting changeSubscriptionStatusNotification) {
		this.changeSubscriptionStatusNotification = changeSubscriptionStatusNotification;
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
            .withCurrencyUsagePriority(data.get("currencyUsagePriority") == null || data.get("currencyUsagePriority").isNull() ? null : data.get("currencyUsagePriority").asText())
            .withSharedFreeCurrency(data.get("sharedFreeCurrency") == null || data.get("sharedFreeCurrency").isNull() ? null : data.get("sharedFreeCurrency").booleanValue())
            .withPlatformSetting(data.get("platformSetting") == null || data.get("platformSetting").isNull() ? null : PlatformSetting.fromJson(data.get("platformSetting")))
            .withDepositBalanceScript(data.get("depositBalanceScript") == null || data.get("depositBalanceScript").isNull() ? null : ScriptSetting.fromJson(data.get("depositBalanceScript")))
            .withWithdrawBalanceScript(data.get("withdrawBalanceScript") == null || data.get("withdrawBalanceScript").isNull() ? null : ScriptSetting.fromJson(data.get("withdrawBalanceScript")))
            .withVerifyReceiptScript(data.get("verifyReceiptScript") == null || data.get("verifyReceiptScript").isNull() ? null : ScriptSetting.fromJson(data.get("verifyReceiptScript")))
            .withSubscribeScript(data.get("subscribeScript") == null || data.get("subscribeScript").isNull() ? null : data.get("subscribeScript").asText())
            .withRenewScript(data.get("renewScript") == null || data.get("renewScript").isNull() ? null : data.get("renewScript").asText())
            .withUnsubscribeScript(data.get("unsubscribeScript") == null || data.get("unsubscribeScript").isNull() ? null : data.get("unsubscribeScript").asText())
            .withTakeOverScript(data.get("takeOverScript") == null || data.get("takeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("takeOverScript")))
            .withChangeSubscriptionStatusNotification(data.get("changeSubscriptionStatusNotification") == null || data.get("changeSubscriptionStatusNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changeSubscriptionStatusNotification")))
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
                put("currencyUsagePriority", getCurrencyUsagePriority());
                put("sharedFreeCurrency", getSharedFreeCurrency());
                put("platformSetting", getPlatformSetting() != null ? getPlatformSetting().toJson() : null);
                put("depositBalanceScript", getDepositBalanceScript() != null ? getDepositBalanceScript().toJson() : null);
                put("withdrawBalanceScript", getWithdrawBalanceScript() != null ? getWithdrawBalanceScript().toJson() : null);
                put("verifyReceiptScript", getVerifyReceiptScript() != null ? getVerifyReceiptScript().toJson() : null);
                put("subscribeScript", getSubscribeScript());
                put("renewScript", getRenewScript());
                put("unsubscribeScript", getUnsubscribeScript());
                put("takeOverScript", getTakeOverScript() != null ? getTakeOverScript().toJson() : null);
                put("changeSubscriptionStatusNotification", getChangeSubscriptionStatusNotification() != null ? getChangeSubscriptionStatusNotification().toJson() : null);
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
        result = prime * result + ((this.currencyUsagePriority == null) ? 0 : this.currencyUsagePriority.hashCode());
        result = prime * result + ((this.sharedFreeCurrency == null) ? 0 : this.sharedFreeCurrency.hashCode());
        result = prime * result + ((this.platformSetting == null) ? 0 : this.platformSetting.hashCode());
        result = prime * result + ((this.depositBalanceScript == null) ? 0 : this.depositBalanceScript.hashCode());
        result = prime * result + ((this.withdrawBalanceScript == null) ? 0 : this.withdrawBalanceScript.hashCode());
        result = prime * result + ((this.verifyReceiptScript == null) ? 0 : this.verifyReceiptScript.hashCode());
        result = prime * result + ((this.subscribeScript == null) ? 0 : this.subscribeScript.hashCode());
        result = prime * result + ((this.renewScript == null) ? 0 : this.renewScript.hashCode());
        result = prime * result + ((this.unsubscribeScript == null) ? 0 : this.unsubscribeScript.hashCode());
        result = prime * result + ((this.takeOverScript == null) ? 0 : this.takeOverScript.hashCode());
        result = prime * result + ((this.changeSubscriptionStatusNotification == null) ? 0 : this.changeSubscriptionStatusNotification.hashCode());
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
		if (currencyUsagePriority == null) {
			return other.currencyUsagePriority == null;
		} else if (!currencyUsagePriority.equals(other.currencyUsagePriority)) {
			return false;
		}
		if (sharedFreeCurrency == null) {
			return other.sharedFreeCurrency == null;
		} else if (!sharedFreeCurrency.equals(other.sharedFreeCurrency)) {
			return false;
		}
		if (platformSetting == null) {
			return other.platformSetting == null;
		} else if (!platformSetting.equals(other.platformSetting)) {
			return false;
		}
		if (depositBalanceScript == null) {
			return other.depositBalanceScript == null;
		} else if (!depositBalanceScript.equals(other.depositBalanceScript)) {
			return false;
		}
		if (withdrawBalanceScript == null) {
			return other.withdrawBalanceScript == null;
		} else if (!withdrawBalanceScript.equals(other.withdrawBalanceScript)) {
			return false;
		}
		if (verifyReceiptScript == null) {
			return other.verifyReceiptScript == null;
		} else if (!verifyReceiptScript.equals(other.verifyReceiptScript)) {
			return false;
		}
		if (subscribeScript == null) {
			return other.subscribeScript == null;
		} else if (!subscribeScript.equals(other.subscribeScript)) {
			return false;
		}
		if (renewScript == null) {
			return other.renewScript == null;
		} else if (!renewScript.equals(other.renewScript)) {
			return false;
		}
		if (unsubscribeScript == null) {
			return other.unsubscribeScript == null;
		} else if (!unsubscribeScript.equals(other.unsubscribeScript)) {
			return false;
		}
		if (takeOverScript == null) {
			return other.takeOverScript == null;
		} else if (!takeOverScript.equals(other.takeOverScript)) {
			return false;
		}
		if (changeSubscriptionStatusNotification == null) {
			return other.changeSubscriptionStatusNotification == null;
		} else if (!changeSubscriptionStatusNotification.equals(other.changeSubscriptionStatusNotification)) {
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