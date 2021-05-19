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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ネームスペース
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	/** ネームスペース */
	protected String namespaceId;

	/**
	 * ネームスペースを取得
	 *
	 * @return ネームスペース
	 */
	public String getNamespaceId() {
		return namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 */
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 * @return this
	 */
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Namespace withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** ネームスペース名 */
	protected String name;

	/**
	 * ネームスペース名を取得
	 *
	 * @return ネームスペース名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 * @return this
	 */
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	/** ネームスペースの説明 */
	protected String description;

	/**
	 * ネームスペースの説明を取得
	 *
	 * @return ネームスペースの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 * @return this
	 */
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	/** ログの書き出し方法 */
	protected String type;

	/**
	 * ログの書き出し方法を取得
	 *
	 * @return ログの書き出し方法
	 */
	public String getType() {
		return type;
	}

	/**
	 * ログの書き出し方法を設定
	 *
	 * @param type ログの書き出し方法
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ログの書き出し方法を設定
	 *
	 * @param type ログの書き出し方法
	 * @return this
	 */
	public Namespace withType(String type) {
		this.type = type;
		return this;
	}
	/** GCPのクレデンシャル */
	protected String gcpCredentialJson;

	/**
	 * GCPのクレデンシャルを取得
	 *
	 * @return GCPのクレデンシャル
	 */
	public String getGcpCredentialJson() {
		return gcpCredentialJson;
	}

	/**
	 * GCPのクレデンシャルを設定
	 *
	 * @param gcpCredentialJson GCPのクレデンシャル
	 */
	public void setGcpCredentialJson(String gcpCredentialJson) {
		this.gcpCredentialJson = gcpCredentialJson;
	}

	/**
	 * GCPのクレデンシャルを設定
	 *
	 * @param gcpCredentialJson GCPのクレデンシャル
	 * @return this
	 */
	public Namespace withGcpCredentialJson(String gcpCredentialJson) {
		this.gcpCredentialJson = gcpCredentialJson;
		return this;
	}
	/** BigQueryのデータセット名 */
	protected String bigQueryDatasetName;

	/**
	 * BigQueryのデータセット名を取得
	 *
	 * @return BigQueryのデータセット名
	 */
	public String getBigQueryDatasetName() {
		return bigQueryDatasetName;
	}

	/**
	 * BigQueryのデータセット名を設定
	 *
	 * @param bigQueryDatasetName BigQueryのデータセット名
	 */
	public void setBigQueryDatasetName(String bigQueryDatasetName) {
		this.bigQueryDatasetName = bigQueryDatasetName;
	}

	/**
	 * BigQueryのデータセット名を設定
	 *
	 * @param bigQueryDatasetName BigQueryのデータセット名
	 * @return this
	 */
	public Namespace withBigQueryDatasetName(String bigQueryDatasetName) {
		this.bigQueryDatasetName = bigQueryDatasetName;
		return this;
	}
	/** ログの保存期間(日) */
	protected Integer logExpireDays;

	/**
	 * ログの保存期間(日)を取得
	 *
	 * @return ログの保存期間(日)
	 */
	public Integer getLogExpireDays() {
		return logExpireDays;
	}

	/**
	 * ログの保存期間(日)を設定
	 *
	 * @param logExpireDays ログの保存期間(日)
	 */
	public void setLogExpireDays(Integer logExpireDays) {
		this.logExpireDays = logExpireDays;
	}

	/**
	 * ログの保存期間(日)を設定
	 *
	 * @param logExpireDays ログの保存期間(日)
	 * @return this
	 */
	public Namespace withLogExpireDays(Integer logExpireDays) {
		this.logExpireDays = logExpireDays;
		return this;
	}
	/** AWSのリージョン */
	protected String awsRegion;

	/**
	 * AWSのリージョンを取得
	 *
	 * @return AWSのリージョン
	 */
	public String getAwsRegion() {
		return awsRegion;
	}

	/**
	 * AWSのリージョンを設定
	 *
	 * @param awsRegion AWSのリージョン
	 */
	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}

	/**
	 * AWSのリージョンを設定
	 *
	 * @param awsRegion AWSのリージョン
	 * @return this
	 */
	public Namespace withAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
		return this;
	}
	/** AWSのアクセスキーID */
	protected String awsAccessKeyId;

	/**
	 * AWSのアクセスキーIDを取得
	 *
	 * @return AWSのアクセスキーID
	 */
	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}

	/**
	 * AWSのアクセスキーIDを設定
	 *
	 * @param awsAccessKeyId AWSのアクセスキーID
	 */
	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}

	/**
	 * AWSのアクセスキーIDを設定
	 *
	 * @param awsAccessKeyId AWSのアクセスキーID
	 * @return this
	 */
	public Namespace withAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
		return this;
	}
	/** AWSのシークレットアクセスキー */
	protected String awsSecretAccessKey;

	/**
	 * AWSのシークレットアクセスキーを取得
	 *
	 * @return AWSのシークレットアクセスキー
	 */
	public String getAwsSecretAccessKey() {
		return awsSecretAccessKey;
	}

	/**
	 * AWSのシークレットアクセスキーを設定
	 *
	 * @param awsSecretAccessKey AWSのシークレットアクセスキー
	 */
	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
	}

	/**
	 * AWSのシークレットアクセスキーを設定
	 *
	 * @param awsSecretAccessKey AWSのシークレットアクセスキー
	 * @return this
	 */
	public Namespace withAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
		return this;
	}
	/** Kinesis Firehose のストリーム名 */
	protected String firehoseStreamName;

	/**
	 * Kinesis Firehose のストリーム名を取得
	 *
	 * @return Kinesis Firehose のストリーム名
	 */
	public String getFirehoseStreamName() {
		return firehoseStreamName;
	}

	/**
	 * Kinesis Firehose のストリーム名を設定
	 *
	 * @param firehoseStreamName Kinesis Firehose のストリーム名
	 */
	public void setFirehoseStreamName(String firehoseStreamName) {
		this.firehoseStreamName = firehoseStreamName;
	}

	/**
	 * Kinesis Firehose のストリーム名を設定
	 *
	 * @param firehoseStreamName Kinesis Firehose のストリーム名
	 * @return this
	 */
	public Namespace withFirehoseStreamName(String firehoseStreamName) {
		this.firehoseStreamName = firehoseStreamName;
		return this;
	}
	/** None */
	protected String status;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Noneを設定
	 *
	 * @param status None
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Noneを設定
	 *
	 * @param status None
	 * @return this
	 */
	public Namespace withStatus(String status) {
		this.status = status;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Namespace withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("type", this.getType())
            .put("gcpCredentialJson", this.getGcpCredentialJson())
            .put("bigQueryDatasetName", this.getBigQueryDatasetName())
            .put("logExpireDays", this.getLogExpireDays())
            .put("awsRegion", this.getAwsRegion())
            .put("awsAccessKeyId", this.getAwsAccessKeyId())
            .put("awsSecretAccessKey", this.getAwsSecretAccessKey())
            .put("firehoseStreamName", this.getFirehoseStreamName())
            .put("status", this.getStatus())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
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
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
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
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
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