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

package io.gs2.datastore.model;

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
public class TransactionSetting implements IModel, Serializable {
	private Boolean enableAutoRun;
	private Boolean enableAtomicCommit;
	private Boolean transactionUseDistributor;
	private Boolean commitScriptResultInUseDistributor;
	private Boolean acquireActionUseJobQueue;
	private String distributorNamespaceId;
	private String keyId;
	private String queueNamespaceId;
	public Boolean getEnableAutoRun() {
		return enableAutoRun;
	}
	public void setEnableAutoRun(Boolean enableAutoRun) {
		this.enableAutoRun = enableAutoRun;
	}
	public TransactionSetting withEnableAutoRun(Boolean enableAutoRun) {
		this.enableAutoRun = enableAutoRun;
		return this;
	}
	public Boolean getEnableAtomicCommit() {
		return enableAtomicCommit;
	}
	public void setEnableAtomicCommit(Boolean enableAtomicCommit) {
		this.enableAtomicCommit = enableAtomicCommit;
	}
	public TransactionSetting withEnableAtomicCommit(Boolean enableAtomicCommit) {
		this.enableAtomicCommit = enableAtomicCommit;
		return this;
	}
	public Boolean getTransactionUseDistributor() {
		return transactionUseDistributor;
	}
	public void setTransactionUseDistributor(Boolean transactionUseDistributor) {
		this.transactionUseDistributor = transactionUseDistributor;
	}
	public TransactionSetting withTransactionUseDistributor(Boolean transactionUseDistributor) {
		this.transactionUseDistributor = transactionUseDistributor;
		return this;
	}
	public Boolean getCommitScriptResultInUseDistributor() {
		return commitScriptResultInUseDistributor;
	}
	public void setCommitScriptResultInUseDistributor(Boolean commitScriptResultInUseDistributor) {
		this.commitScriptResultInUseDistributor = commitScriptResultInUseDistributor;
	}
	public TransactionSetting withCommitScriptResultInUseDistributor(Boolean commitScriptResultInUseDistributor) {
		this.commitScriptResultInUseDistributor = commitScriptResultInUseDistributor;
		return this;
	}
	public Boolean getAcquireActionUseJobQueue() {
		return acquireActionUseJobQueue;
	}
	public void setAcquireActionUseJobQueue(Boolean acquireActionUseJobQueue) {
		this.acquireActionUseJobQueue = acquireActionUseJobQueue;
	}
	public TransactionSetting withAcquireActionUseJobQueue(Boolean acquireActionUseJobQueue) {
		this.acquireActionUseJobQueue = acquireActionUseJobQueue;
		return this;
	}
	public String getDistributorNamespaceId() {
		return distributorNamespaceId;
	}
	public void setDistributorNamespaceId(String distributorNamespaceId) {
		this.distributorNamespaceId = distributorNamespaceId;
	}
	public TransactionSetting withDistributorNamespaceId(String distributorNamespaceId) {
		this.distributorNamespaceId = distributorNamespaceId;
		return this;
	}
    @Deprecated
	public String getKeyId() {
		return keyId;
	}
    @Deprecated
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
    @Deprecated
	public TransactionSetting withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}
	public TransactionSetting withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}

    public static TransactionSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TransactionSetting()
            .withEnableAutoRun(data.get("enableAutoRun") == null || data.get("enableAutoRun").isNull() ? null : data.get("enableAutoRun").booleanValue())
            .withEnableAtomicCommit(data.get("enableAtomicCommit") == null || data.get("enableAtomicCommit").isNull() ? null : data.get("enableAtomicCommit").booleanValue())
            .withTransactionUseDistributor(data.get("transactionUseDistributor") == null || data.get("transactionUseDistributor").isNull() ? null : data.get("transactionUseDistributor").booleanValue())
            .withCommitScriptResultInUseDistributor(data.get("commitScriptResultInUseDistributor") == null || data.get("commitScriptResultInUseDistributor").isNull() ? null : data.get("commitScriptResultInUseDistributor").booleanValue())
            .withAcquireActionUseJobQueue(data.get("acquireActionUseJobQueue") == null || data.get("acquireActionUseJobQueue").isNull() ? null : data.get("acquireActionUseJobQueue").booleanValue())
            .withDistributorNamespaceId(data.get("distributorNamespaceId") == null || data.get("distributorNamespaceId").isNull() ? null : data.get("distributorNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("enableAutoRun", getEnableAutoRun());
                put("enableAtomicCommit", getEnableAtomicCommit());
                put("transactionUseDistributor", getTransactionUseDistributor());
                put("commitScriptResultInUseDistributor", getCommitScriptResultInUseDistributor());
                put("acquireActionUseJobQueue", getAcquireActionUseJobQueue());
                put("distributorNamespaceId", getDistributorNamespaceId());
                put("keyId", getKeyId());
                put("queueNamespaceId", getQueueNamespaceId());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.enableAutoRun == null) ? 0 : this.enableAutoRun.hashCode());
        result = prime * result + ((this.enableAtomicCommit == null) ? 0 : this.enableAtomicCommit.hashCode());
        result = prime * result + ((this.transactionUseDistributor == null) ? 0 : this.transactionUseDistributor.hashCode());
        result = prime * result + ((this.commitScriptResultInUseDistributor == null) ? 0 : this.commitScriptResultInUseDistributor.hashCode());
        result = prime * result + ((this.acquireActionUseJobQueue == null) ? 0 : this.acquireActionUseJobQueue.hashCode());
        result = prime * result + ((this.distributorNamespaceId == null) ? 0 : this.distributorNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
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
		TransactionSetting other = (TransactionSetting) o;
		if (enableAutoRun == null) {
			return other.enableAutoRun == null;
		} else if (!enableAutoRun.equals(other.enableAutoRun)) {
			return false;
		}
		if (enableAtomicCommit == null) {
			return other.enableAtomicCommit == null;
		} else if (!enableAtomicCommit.equals(other.enableAtomicCommit)) {
			return false;
		}
		if (transactionUseDistributor == null) {
			return other.transactionUseDistributor == null;
		} else if (!transactionUseDistributor.equals(other.transactionUseDistributor)) {
			return false;
		}
		if (commitScriptResultInUseDistributor == null) {
			return other.commitScriptResultInUseDistributor == null;
		} else if (!commitScriptResultInUseDistributor.equals(other.commitScriptResultInUseDistributor)) {
			return false;
		}
		if (acquireActionUseJobQueue == null) {
			return other.acquireActionUseJobQueue == null;
		} else if (!acquireActionUseJobQueue.equals(other.acquireActionUseJobQueue)) {
			return false;
		}
		if (distributorNamespaceId == null) {
			return other.distributorNamespaceId == null;
		} else if (!distributorNamespaceId.equals(other.distributorNamespaceId)) {
			return false;
		}
		if (keyId == null) {
			return other.keyId == null;
		} else if (!keyId.equals(other.keyId)) {
			return false;
		}
		if (queueNamespaceId == null) {
			return other.queueNamespaceId == null;
		} else if (!queueNamespaceId.equals(other.queueNamespaceId)) {
			return false;
		}
		return true;
	}
}