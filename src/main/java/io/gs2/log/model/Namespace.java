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

package io.gs2.log.model;

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
	private String type;
	private String gcpCredentialJson;
	private String bigQueryDatasetName;
	private Integer logExpireDays;
	private String awsRegion;
	private String awsAccessKeyId;
	private String awsSecretAccessKey;
	private String firehoseStreamName;
	private String status;
	private Long createdAt;
	private Long updatedAt;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Namespace withType(String type) {
		this.type = type;
		return this;
	}
	public String getGcpCredentialJson() {
		return gcpCredentialJson;
	}
	public void setGcpCredentialJson(String gcpCredentialJson) {
		this.gcpCredentialJson = gcpCredentialJson;
	}
	public Namespace withGcpCredentialJson(String gcpCredentialJson) {
		this.gcpCredentialJson = gcpCredentialJson;
		return this;
	}
	public String getBigQueryDatasetName() {
		return bigQueryDatasetName;
	}
	public void setBigQueryDatasetName(String bigQueryDatasetName) {
		this.bigQueryDatasetName = bigQueryDatasetName;
	}
	public Namespace withBigQueryDatasetName(String bigQueryDatasetName) {
		this.bigQueryDatasetName = bigQueryDatasetName;
		return this;
	}
	public Integer getLogExpireDays() {
		return logExpireDays;
	}
	public void setLogExpireDays(Integer logExpireDays) {
		this.logExpireDays = logExpireDays;
	}
	public Namespace withLogExpireDays(Integer logExpireDays) {
		this.logExpireDays = logExpireDays;
		return this;
	}
	public String getAwsRegion() {
		return awsRegion;
	}
	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}
	public Namespace withAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
		return this;
	}
	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}
	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}
	public Namespace withAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
		return this;
	}
	public String getAwsSecretAccessKey() {
		return awsSecretAccessKey;
	}
	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
	}
	public Namespace withAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
		return this;
	}
	public String getFirehoseStreamName() {
		return firehoseStreamName;
	}
	public void setFirehoseStreamName(String firehoseStreamName) {
		this.firehoseStreamName = firehoseStreamName;
	}
	public Namespace withFirehoseStreamName(String firehoseStreamName) {
		this.firehoseStreamName = firehoseStreamName;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Namespace withStatus(String status) {
		this.status = status;
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

    public static Namespace fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Namespace()
            .withNamespaceId(data.get("namespaceId") == null || data.get("namespaceId").isNull() ? null : data.get("namespaceId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withGcpCredentialJson(data.get("gcpCredentialJson") == null || data.get("gcpCredentialJson").isNull() ? null : data.get("gcpCredentialJson").asText())
            .withBigQueryDatasetName(data.get("bigQueryDatasetName") == null || data.get("bigQueryDatasetName").isNull() ? null : data.get("bigQueryDatasetName").asText())
            .withLogExpireDays(data.get("logExpireDays") == null || data.get("logExpireDays").isNull() ? null : data.get("logExpireDays").intValue())
            .withAwsRegion(data.get("awsRegion") == null || data.get("awsRegion").isNull() ? null : data.get("awsRegion").asText())
            .withAwsAccessKeyId(data.get("awsAccessKeyId") == null || data.get("awsAccessKeyId").isNull() ? null : data.get("awsAccessKeyId").asText())
            .withAwsSecretAccessKey(data.get("awsSecretAccessKey") == null || data.get("awsSecretAccessKey").isNull() ? null : data.get("awsSecretAccessKey").asText())
            .withFirehoseStreamName(data.get("firehoseStreamName") == null || data.get("firehoseStreamName").isNull() ? null : data.get("firehoseStreamName").asText())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceId", getNamespaceId());
                put("name", getName());
                put("description", getDescription());
                put("type", getType());
                put("gcpCredentialJson", getGcpCredentialJson());
                put("bigQueryDatasetName", getBigQueryDatasetName());
                put("logExpireDays", getLogExpireDays());
                put("awsRegion", getAwsRegion());
                put("awsAccessKeyId", getAwsAccessKeyId());
                put("awsSecretAccessKey", getAwsSecretAccessKey());
                put("firehoseStreamName", getFirehoseStreamName());
                put("status", getStatus());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
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
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.gcpCredentialJson == null) ? 0 : this.gcpCredentialJson.hashCode());
        result = prime * result + ((this.bigQueryDatasetName == null) ? 0 : this.bigQueryDatasetName.hashCode());
        result = prime * result + ((this.logExpireDays == null) ? 0 : this.logExpireDays.hashCode());
        result = prime * result + ((this.awsRegion == null) ? 0 : this.awsRegion.hashCode());
        result = prime * result + ((this.awsAccessKeyId == null) ? 0 : this.awsAccessKeyId.hashCode());
        result = prime * result + ((this.awsSecretAccessKey == null) ? 0 : this.awsSecretAccessKey.hashCode());
        result = prime * result + ((this.firehoseStreamName == null) ? 0 : this.firehoseStreamName.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (gcpCredentialJson == null) {
			return other.gcpCredentialJson == null;
		} else if (!gcpCredentialJson.equals(other.gcpCredentialJson)) {
			return false;
		}
		if (bigQueryDatasetName == null) {
			return other.bigQueryDatasetName == null;
		} else if (!bigQueryDatasetName.equals(other.bigQueryDatasetName)) {
			return false;
		}
		if (logExpireDays == null) {
			return other.logExpireDays == null;
		} else if (!logExpireDays.equals(other.logExpireDays)) {
			return false;
		}
		if (awsRegion == null) {
			return other.awsRegion == null;
		} else if (!awsRegion.equals(other.awsRegion)) {
			return false;
		}
		if (awsAccessKeyId == null) {
			return other.awsAccessKeyId == null;
		} else if (!awsAccessKeyId.equals(other.awsAccessKeyId)) {
			return false;
		}
		if (awsSecretAccessKey == null) {
			return other.awsSecretAccessKey == null;
		} else if (!awsSecretAccessKey.equals(other.awsSecretAccessKey)) {
			return false;
		}
		if (firehoseStreamName == null) {
			return other.firehoseStreamName == null;
		} else if (!firehoseStreamName.equals(other.firehoseStreamName)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
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