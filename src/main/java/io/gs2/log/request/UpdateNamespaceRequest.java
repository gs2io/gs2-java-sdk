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

package io.gs2.log.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private String type;
    private String gcpCredentialJson;
    private String bigQueryDatasetName;
    private Integer logExpireDays;
    private String awsRegion;
    private String awsAccessKeyId;
    private String awsSecretAccessKey;
    private String firehoseStreamName;
    private String firehoseCompressData;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateNamespaceRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UpdateNamespaceRequest withType(String type) {
		this.type = type;
		return this;
	}
	public String getGcpCredentialJson() {
		return gcpCredentialJson;
	}
	public void setGcpCredentialJson(String gcpCredentialJson) {
		this.gcpCredentialJson = gcpCredentialJson;
	}
	public UpdateNamespaceRequest withGcpCredentialJson(String gcpCredentialJson) {
		this.gcpCredentialJson = gcpCredentialJson;
		return this;
	}
	public String getBigQueryDatasetName() {
		return bigQueryDatasetName;
	}
	public void setBigQueryDatasetName(String bigQueryDatasetName) {
		this.bigQueryDatasetName = bigQueryDatasetName;
	}
	public UpdateNamespaceRequest withBigQueryDatasetName(String bigQueryDatasetName) {
		this.bigQueryDatasetName = bigQueryDatasetName;
		return this;
	}
	public Integer getLogExpireDays() {
		return logExpireDays;
	}
	public void setLogExpireDays(Integer logExpireDays) {
		this.logExpireDays = logExpireDays;
	}
	public UpdateNamespaceRequest withLogExpireDays(Integer logExpireDays) {
		this.logExpireDays = logExpireDays;
		return this;
	}
	public String getAwsRegion() {
		return awsRegion;
	}
	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}
	public UpdateNamespaceRequest withAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
		return this;
	}
	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}
	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}
	public UpdateNamespaceRequest withAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
		return this;
	}
	public String getAwsSecretAccessKey() {
		return awsSecretAccessKey;
	}
	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
	}
	public UpdateNamespaceRequest withAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
		return this;
	}
	public String getFirehoseStreamName() {
		return firehoseStreamName;
	}
	public void setFirehoseStreamName(String firehoseStreamName) {
		this.firehoseStreamName = firehoseStreamName;
	}
	public UpdateNamespaceRequest withFirehoseStreamName(String firehoseStreamName) {
		this.firehoseStreamName = firehoseStreamName;
		return this;
	}
	public String getFirehoseCompressData() {
		return firehoseCompressData;
	}
	public void setFirehoseCompressData(String firehoseCompressData) {
		this.firehoseCompressData = firehoseCompressData;
	}
	public UpdateNamespaceRequest withFirehoseCompressData(String firehoseCompressData) {
		this.firehoseCompressData = firehoseCompressData;
		return this;
	}

    public static UpdateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateNamespaceRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withGcpCredentialJson(data.get("gcpCredentialJson") == null || data.get("gcpCredentialJson").isNull() ? null : data.get("gcpCredentialJson").asText())
            .withBigQueryDatasetName(data.get("bigQueryDatasetName") == null || data.get("bigQueryDatasetName").isNull() ? null : data.get("bigQueryDatasetName").asText())
            .withLogExpireDays(data.get("logExpireDays") == null || data.get("logExpireDays").isNull() ? null : data.get("logExpireDays").intValue())
            .withAwsRegion(data.get("awsRegion") == null || data.get("awsRegion").isNull() ? null : data.get("awsRegion").asText())
            .withAwsAccessKeyId(data.get("awsAccessKeyId") == null || data.get("awsAccessKeyId").isNull() ? null : data.get("awsAccessKeyId").asText())
            .withAwsSecretAccessKey(data.get("awsSecretAccessKey") == null || data.get("awsSecretAccessKey").isNull() ? null : data.get("awsSecretAccessKey").asText())
            .withFirehoseStreamName(data.get("firehoseStreamName") == null || data.get("firehoseStreamName").isNull() ? null : data.get("firehoseStreamName").asText())
            .withFirehoseCompressData(data.get("firehoseCompressData") == null || data.get("firehoseCompressData").isNull() ? null : data.get("firehoseCompressData").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("type", getType());
                put("gcpCredentialJson", getGcpCredentialJson());
                put("bigQueryDatasetName", getBigQueryDatasetName());
                put("logExpireDays", getLogExpireDays());
                put("awsRegion", getAwsRegion());
                put("awsAccessKeyId", getAwsAccessKeyId());
                put("awsSecretAccessKey", getAwsSecretAccessKey());
                put("firehoseStreamName", getFirehoseStreamName());
                put("firehoseCompressData", getFirehoseCompressData());
            }}
        );
    }
}