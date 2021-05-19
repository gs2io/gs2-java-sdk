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

package io.gs2.deploy.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * GitHubからリソースをチェックアウトしてくる設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GitHubCheckoutSetting implements IModel, Serializable {
	/** リソースの取得に使用するGitHub のAPIキー のGRN */
	protected String gitHubApiKeyId;

	/**
	 * リソースの取得に使用するGitHub のAPIキー のGRNを取得
	 *
	 * @return リソースの取得に使用するGitHub のAPIキー のGRN
	 */
	public String getGitHubApiKeyId() {
		return gitHubApiKeyId;
	}

	/**
	 * リソースの取得に使用するGitHub のAPIキー のGRNを設定
	 *
	 * @param gitHubApiKeyId リソースの取得に使用するGitHub のAPIキー のGRN
	 */
	public void setGitHubApiKeyId(String gitHubApiKeyId) {
		this.gitHubApiKeyId = gitHubApiKeyId;
	}

	/**
	 * リソースの取得に使用するGitHub のAPIキー のGRNを設定
	 *
	 * @param gitHubApiKeyId リソースの取得に使用するGitHub のAPIキー のGRN
	 * @return this
	 */
	public GitHubCheckoutSetting withGitHubApiKeyId(String gitHubApiKeyId) {
		this.gitHubApiKeyId = gitHubApiKeyId;
		return this;
	}
	/** リポジトリ名 */
	protected String repositoryName;

	/**
	 * リポジトリ名を取得
	 *
	 * @return リポジトリ名
	 */
	public String getRepositoryName() {
		return repositoryName;
	}

	/**
	 * リポジトリ名を設定
	 *
	 * @param repositoryName リポジトリ名
	 */
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	/**
	 * リポジトリ名を設定
	 *
	 * @param repositoryName リポジトリ名
	 * @return this
	 */
	public GitHubCheckoutSetting withRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
		return this;
	}
	/** ソースコードのファイルパス */
	protected String sourcePath;

	/**
	 * ソースコードのファイルパスを取得
	 *
	 * @return ソースコードのファイルパス
	 */
	public String getSourcePath() {
		return sourcePath;
	}

	/**
	 * ソースコードのファイルパスを設定
	 *
	 * @param sourcePath ソースコードのファイルパス
	 */
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	/**
	 * ソースコードのファイルパスを設定
	 *
	 * @param sourcePath ソースコードのファイルパス
	 * @return this
	 */
	public GitHubCheckoutSetting withSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
		return this;
	}
	/** コードの取得元 */
	protected String referenceType;

	/**
	 * コードの取得元を取得
	 *
	 * @return コードの取得元
	 */
	public String getReferenceType() {
		return referenceType;
	}

	/**
	 * コードの取得元を設定
	 *
	 * @param referenceType コードの取得元
	 */
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	/**
	 * コードの取得元を設定
	 *
	 * @param referenceType コードの取得元
	 * @return this
	 */
	public GitHubCheckoutSetting withReferenceType(String referenceType) {
		this.referenceType = referenceType;
		return this;
	}
	/** コミットハッシュ */
	protected String commitHash;

	/**
	 * コミットハッシュを取得
	 *
	 * @return コミットハッシュ
	 */
	public String getCommitHash() {
		return commitHash;
	}

	/**
	 * コミットハッシュを設定
	 *
	 * @param commitHash コミットハッシュ
	 */
	public void setCommitHash(String commitHash) {
		this.commitHash = commitHash;
	}

	/**
	 * コミットハッシュを設定
	 *
	 * @param commitHash コミットハッシュ
	 * @return this
	 */
	public GitHubCheckoutSetting withCommitHash(String commitHash) {
		this.commitHash = commitHash;
		return this;
	}
	/** ブランチ名 */
	protected String branchName;

	/**
	 * ブランチ名を取得
	 *
	 * @return ブランチ名
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * ブランチ名を設定
	 *
	 * @param branchName ブランチ名
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * ブランチ名を設定
	 *
	 * @param branchName ブランチ名
	 * @return this
	 */
	public GitHubCheckoutSetting withBranchName(String branchName) {
		this.branchName = branchName;
		return this;
	}
	/** タグ名 */
	protected String tagName;

	/**
	 * タグ名を取得
	 *
	 * @return タグ名
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * タグ名を設定
	 *
	 * @param tagName タグ名
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * タグ名を設定
	 *
	 * @param tagName タグ名
	 * @return this
	 */
	public GitHubCheckoutSetting withTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("gitHubApiKeyId", this.getGitHubApiKeyId())
            .put("repositoryName", this.getRepositoryName())
            .put("sourcePath", this.getSourcePath())
            .put("referenceType", this.getReferenceType())
            .put("commitHash", this.getCommitHash())
            .put("branchName", this.getBranchName())
            .put("tagName", this.getTagName());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gitHubApiKeyId == null) ? 0 : this.gitHubApiKeyId.hashCode());
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
		if (gitHubApiKeyId == null) {
			return other.gitHubApiKeyId == null;
		} else if (!gitHubApiKeyId.equals(other.gitHubApiKeyId)) {
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