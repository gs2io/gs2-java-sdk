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

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.log.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ログの書き出し方法 */
    private String type;

    /**
     * ログの書き出し方法を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getType() {
        return type;
    }

    /**
     * ログの書き出し方法を設定
     *
     * @param type ネームスペースを新規作成
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * ログの書き出し方法を設定
     *
     * @param type ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withType(String type) {
        setType(type);
        return this;
    }

    /** GCPのクレデンシャル */
    private String gcpCredentialJson;

    /**
     * GCPのクレデンシャルを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getGcpCredentialJson() {
        return gcpCredentialJson;
    }

    /**
     * GCPのクレデンシャルを設定
     *
     * @param gcpCredentialJson ネームスペースを新規作成
     */
    public void setGcpCredentialJson(String gcpCredentialJson) {
        this.gcpCredentialJson = gcpCredentialJson;
    }

    /**
     * GCPのクレデンシャルを設定
     *
     * @param gcpCredentialJson ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withGcpCredentialJson(String gcpCredentialJson) {
        setGcpCredentialJson(gcpCredentialJson);
        return this;
    }

    /** BigQueryのデータセット名 */
    private String bigQueryDatasetName;

    /**
     * BigQueryのデータセット名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getBigQueryDatasetName() {
        return bigQueryDatasetName;
    }

    /**
     * BigQueryのデータセット名を設定
     *
     * @param bigQueryDatasetName ネームスペースを新規作成
     */
    public void setBigQueryDatasetName(String bigQueryDatasetName) {
        this.bigQueryDatasetName = bigQueryDatasetName;
    }

    /**
     * BigQueryのデータセット名を設定
     *
     * @param bigQueryDatasetName ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withBigQueryDatasetName(String bigQueryDatasetName) {
        setBigQueryDatasetName(bigQueryDatasetName);
        return this;
    }

    /** ログの保存期間(日) */
    private Integer logExpireDays;

    /**
     * ログの保存期間(日)を取得
     *
     * @return ネームスペースを新規作成
     */
    public Integer getLogExpireDays() {
        return logExpireDays;
    }

    /**
     * ログの保存期間(日)を設定
     *
     * @param logExpireDays ネームスペースを新規作成
     */
    public void setLogExpireDays(Integer logExpireDays) {
        this.logExpireDays = logExpireDays;
    }

    /**
     * ログの保存期間(日)を設定
     *
     * @param logExpireDays ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogExpireDays(Integer logExpireDays) {
        setLogExpireDays(logExpireDays);
        return this;
    }

    /** AWSのリージョン */
    private String awsRegion;

    /**
     * AWSのリージョンを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAwsRegion() {
        return awsRegion;
    }

    /**
     * AWSのリージョンを設定
     *
     * @param awsRegion ネームスペースを新規作成
     */
    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    /**
     * AWSのリージョンを設定
     *
     * @param awsRegion ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAwsRegion(String awsRegion) {
        setAwsRegion(awsRegion);
        return this;
    }

    /** AWSのアクセスキーID */
    private String awsAccessKeyId;

    /**
     * AWSのアクセスキーIDを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    /**
     * AWSのアクセスキーIDを設定
     *
     * @param awsAccessKeyId ネームスペースを新規作成
     */
    public void setAwsAccessKeyId(String awsAccessKeyId) {
        this.awsAccessKeyId = awsAccessKeyId;
    }

    /**
     * AWSのアクセスキーIDを設定
     *
     * @param awsAccessKeyId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAwsAccessKeyId(String awsAccessKeyId) {
        setAwsAccessKeyId(awsAccessKeyId);
        return this;
    }

    /** AWSのシークレットアクセスキー */
    private String awsSecretAccessKey;

    /**
     * AWSのシークレットアクセスキーを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAwsSecretAccessKey() {
        return awsSecretAccessKey;
    }

    /**
     * AWSのシークレットアクセスキーを設定
     *
     * @param awsSecretAccessKey ネームスペースを新規作成
     */
    public void setAwsSecretAccessKey(String awsSecretAccessKey) {
        this.awsSecretAccessKey = awsSecretAccessKey;
    }

    /**
     * AWSのシークレットアクセスキーを設定
     *
     * @param awsSecretAccessKey ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAwsSecretAccessKey(String awsSecretAccessKey) {
        setAwsSecretAccessKey(awsSecretAccessKey);
        return this;
    }

    /** Kinesis Firehose のストリーム名 */
    private String firehoseStreamName;

    /**
     * Kinesis Firehose のストリーム名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getFirehoseStreamName() {
        return firehoseStreamName;
    }

    /**
     * Kinesis Firehose のストリーム名を設定
     *
     * @param firehoseStreamName ネームスペースを新規作成
     */
    public void setFirehoseStreamName(String firehoseStreamName) {
        this.firehoseStreamName = firehoseStreamName;
    }

    /**
     * Kinesis Firehose のストリーム名を設定
     *
     * @param firehoseStreamName ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withFirehoseStreamName(String firehoseStreamName) {
        setFirehoseStreamName(firehoseStreamName);
        return this;
    }

}