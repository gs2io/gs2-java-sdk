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

package io.gs2.mission.model;

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
public class GitHubCheckoutSetting implements IModel, Serializable {
	private String apiKeyId;
	private String repositoryName;
	private String sourcePath;
	private String referenceType;
	private String commitHash;
	private String branchName;
	private String tagName;
	public String getApiKeyId() {
		return apiKeyId;
	}
	public void setApiKeyId(String apiKeyId) {
		this.apiKeyId = apiKeyId;
	}
	public GitHubCheckoutSetting withApiKeyId(String apiKeyId) {
		this.apiKeyId = apiKeyId;
		return this;
	}
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	public GitHubCheckoutSetting withRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
		return this;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public GitHubCheckoutSetting withSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
		return this;
	}
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	public GitHubCheckoutSetting withReferenceType(String referenceType) {
		this.referenceType = referenceType;
		return this;
	}
	public String getCommitHash() {
		return commitHash;
	}
	public void setCommitHash(String commitHash) {
		this.commitHash = commitHash;
	}
	public GitHubCheckoutSetting withCommitHash(String commitHash) {
		this.commitHash = commitHash;
		return this;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public GitHubCheckoutSetting withBranchName(String branchName) {
		this.branchName = branchName;
		return this;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public GitHubCheckoutSetting withTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

    public static GitHubCheckoutSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GitHubCheckoutSetting()
            .withApiKeyId(data.get("apiKeyId") == null || data.get("apiKeyId").isNull() ? null : data.get("apiKeyId").asText())
            .withRepositoryName(data.get("repositoryName") == null || data.get("repositoryName").isNull() ? null : data.get("repositoryName").asText())
            .withSourcePath(data.get("sourcePath") == null || data.get("sourcePath").isNull() ? null : data.get("sourcePath").asText())
            .withReferenceType(data.get("referenceType") == null || data.get("referenceType").isNull() ? null : data.get("referenceType").asText())
            .withCommitHash(data.get("commitHash") == null || data.get("commitHash").isNull() ? null : data.get("commitHash").asText())
            .withBranchName(data.get("branchName") == null || data.get("branchName").isNull() ? null : data.get("branchName").asText())
            .withTagName(data.get("tagName") == null || data.get("tagName").isNull() ? null : data.get("tagName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("apiKeyId", getApiKeyId());
                put("repositoryName", getRepositoryName());
                put("sourcePath", getSourcePath());
                put("referenceType", getReferenceType());
                put("commitHash", getCommitHash());
                put("branchName", getBranchName());
                put("tagName", getTagName());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.apiKeyId == null) ? 0 : this.apiKeyId.hashCode());
        result = prime * result + ((this.repositoryName == null) ? 0 : this.repositoryName.hashCode());
        result = prime * result + ((this.sourcePath == null) ? 0 : this.sourcePath.hashCode());
        result = prime * result + ((this.referenceType == null) ? 0 : this.referenceType.hashCode());
        result = prime * result + ((this.commitHash == null) ? 0 : this.commitHash.hashCode());
        result = prime * result + ((this.branchName == null) ? 0 : this.branchName.hashCode());
        result = prime * result + ((this.tagName == null) ? 0 : this.tagName.hashCode());
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
		GitHubCheckoutSetting other = (GitHubCheckoutSetting) o;
		if (apiKeyId == null) {
			return other.apiKeyId == null;
		} else if (!apiKeyId.equals(other.apiKeyId)) {
			return false;
		}
		if (repositoryName == null) {
			return other.repositoryName == null;
		} else if (!repositoryName.equals(other.repositoryName)) {
			return false;
		}
		if (sourcePath == null) {
			return other.sourcePath == null;
		} else if (!sourcePath.equals(other.sourcePath)) {
			return false;
		}
		if (referenceType == null) {
			return other.referenceType == null;
		} else if (!referenceType.equals(other.referenceType)) {
			return false;
		}
		if (commitHash == null) {
			return other.commitHash == null;
		} else if (!commitHash.equals(other.commitHash)) {
			return false;
		}
		if (branchName == null) {
			return other.branchName == null;
		} else if (!branchName.equals(other.branchName)) {
			return false;
		}
		if (tagName == null) {
			return other.tagName == null;
		} else if (!tagName.equals(other.tagName)) {
			return false;
		}
		return true;
	}
}