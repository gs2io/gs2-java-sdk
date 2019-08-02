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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

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
	/** 説明文 */
	protected String description;

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	/** アカウント引き継ぎ時にパスワードを変更するか */
	protected Boolean changePasswordIfTakeOver;

	/**
	 * アカウント引き継ぎ時にパスワードを変更するかを取得
	 *
	 * @return アカウント引き継ぎ時にパスワードを変更するか
	 */
	public Boolean getChangePasswordIfTakeOver() {
		return changePasswordIfTakeOver;
	}

	/**
	 * アカウント引き継ぎ時にパスワードを変更するかを設定
	 *
	 * @param changePasswordIfTakeOver アカウント引き継ぎ時にパスワードを変更するか
	 */
	public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
	}

	/**
	 * アカウント引き継ぎ時にパスワードを変更するかを設定
	 *
	 * @param changePasswordIfTakeOver アカウント引き継ぎ時にパスワードを変更するか
	 * @return this
	 */
	public Namespace withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
		return this;
	}
	/** アカウント新規作成時 に実行されるスクリプト のGRN */
	protected String createAccountTriggerScriptId;

	/**
	 * アカウント新規作成時 に実行されるスクリプト のGRNを取得
	 *
	 * @return アカウント新規作成時 に実行されるスクリプト のGRN
	 */
	public String getCreateAccountTriggerScriptId() {
		return createAccountTriggerScriptId;
	}

	/**
	 * アカウント新規作成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createAccountTriggerScriptId アカウント新規作成時 に実行されるスクリプト のGRN
	 */
	public void setCreateAccountTriggerScriptId(String createAccountTriggerScriptId) {
		this.createAccountTriggerScriptId = createAccountTriggerScriptId;
	}

	/**
	 * アカウント新規作成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createAccountTriggerScriptId アカウント新規作成時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateAccountTriggerScriptId(String createAccountTriggerScriptId) {
		this.createAccountTriggerScriptId = createAccountTriggerScriptId;
		return this;
	}
	/** アカウント新規作成完了時 に実行されるスクリプト のGRN */
	protected String createAccountDoneTriggerScriptId;

	/**
	 * アカウント新規作成完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return アカウント新規作成完了時 に実行されるスクリプト のGRN
	 */
	public String getCreateAccountDoneTriggerScriptId() {
		return createAccountDoneTriggerScriptId;
	}

	/**
	 * アカウント新規作成完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createAccountDoneTriggerScriptId アカウント新規作成完了時 に実行されるスクリプト のGRN
	 */
	public void setCreateAccountDoneTriggerScriptId(String createAccountDoneTriggerScriptId) {
		this.createAccountDoneTriggerScriptId = createAccountDoneTriggerScriptId;
	}

	/**
	 * アカウント新規作成完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createAccountDoneTriggerScriptId アカウント新規作成完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateAccountDoneTriggerScriptId(String createAccountDoneTriggerScriptId) {
		this.createAccountDoneTriggerScriptId = createAccountDoneTriggerScriptId;
		return this;
	}
	/** アカウント新規作成完了時 にジョブが登録されるネームスペース のGRN */
	protected String createAccountDoneTriggerQueueNamespaceId;

	/**
	 * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return アカウント新規作成完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getCreateAccountDoneTriggerQueueNamespaceId() {
		return createAccountDoneTriggerQueueNamespaceId;
	}

	/**
	 * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param createAccountDoneTriggerQueueNamespaceId アカウント新規作成完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setCreateAccountDoneTriggerQueueNamespaceId(String createAccountDoneTriggerQueueNamespaceId) {
		this.createAccountDoneTriggerQueueNamespaceId = createAccountDoneTriggerQueueNamespaceId;
	}

	/**
	 * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param createAccountDoneTriggerQueueNamespaceId アカウント新規作成完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withCreateAccountDoneTriggerQueueNamespaceId(String createAccountDoneTriggerQueueNamespaceId) {
		this.createAccountDoneTriggerQueueNamespaceId = createAccountDoneTriggerQueueNamespaceId;
		return this;
	}
	/** 認証時 に実行されるスクリプト のGRN */
	protected String authenticationTriggerScriptId;

	/**
	 * 認証時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 認証時 に実行されるスクリプト のGRN
	 */
	public String getAuthenticationTriggerScriptId() {
		return authenticationTriggerScriptId;
	}

	/**
	 * 認証時 に実行されるスクリプト のGRNを設定
	 *
	 * @param authenticationTriggerScriptId 認証時 に実行されるスクリプト のGRN
	 */
	public void setAuthenticationTriggerScriptId(String authenticationTriggerScriptId) {
		this.authenticationTriggerScriptId = authenticationTriggerScriptId;
	}

	/**
	 * 認証時 に実行されるスクリプト のGRNを設定
	 *
	 * @param authenticationTriggerScriptId 認証時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withAuthenticationTriggerScriptId(String authenticationTriggerScriptId) {
		this.authenticationTriggerScriptId = authenticationTriggerScriptId;
		return this;
	}
	/** 認証完了時 に実行されるスクリプト のGRN */
	protected String authenticationDoneTriggerScriptId;

	/**
	 * 認証完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 認証完了時 に実行されるスクリプト のGRN
	 */
	public String getAuthenticationDoneTriggerScriptId() {
		return authenticationDoneTriggerScriptId;
	}

	/**
	 * 認証完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param authenticationDoneTriggerScriptId 認証完了時 に実行されるスクリプト のGRN
	 */
	public void setAuthenticationDoneTriggerScriptId(String authenticationDoneTriggerScriptId) {
		this.authenticationDoneTriggerScriptId = authenticationDoneTriggerScriptId;
	}

	/**
	 * 認証完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param authenticationDoneTriggerScriptId 認証完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withAuthenticationDoneTriggerScriptId(String authenticationDoneTriggerScriptId) {
		this.authenticationDoneTriggerScriptId = authenticationDoneTriggerScriptId;
		return this;
	}
	/** 認証完了時 にジョブが登録されるネームスペース のGRN */
	protected String authenticationDoneTriggerQueueNamespaceId;

	/**
	 * 認証完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return 認証完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getAuthenticationDoneTriggerQueueNamespaceId() {
		return authenticationDoneTriggerQueueNamespaceId;
	}

	/**
	 * 認証完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param authenticationDoneTriggerQueueNamespaceId 認証完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setAuthenticationDoneTriggerQueueNamespaceId(String authenticationDoneTriggerQueueNamespaceId) {
		this.authenticationDoneTriggerQueueNamespaceId = authenticationDoneTriggerQueueNamespaceId;
	}

	/**
	 * 認証完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param authenticationDoneTriggerQueueNamespaceId 認証完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withAuthenticationDoneTriggerQueueNamespaceId(String authenticationDoneTriggerQueueNamespaceId) {
		this.authenticationDoneTriggerQueueNamespaceId = authenticationDoneTriggerQueueNamespaceId;
		return this;
	}
	/** 引き継ぎ情報登録時 に実行されるスクリプト のGRN */
	protected String createTakeOverTriggerScriptId;

	/**
	 * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 引き継ぎ情報登録時 に実行されるスクリプト のGRN
	 */
	public String getCreateTakeOverTriggerScriptId() {
		return createTakeOverTriggerScriptId;
	}

	/**
	 * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createTakeOverTriggerScriptId 引き継ぎ情報登録時 に実行されるスクリプト のGRN
	 */
	public void setCreateTakeOverTriggerScriptId(String createTakeOverTriggerScriptId) {
		this.createTakeOverTriggerScriptId = createTakeOverTriggerScriptId;
	}

	/**
	 * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createTakeOverTriggerScriptId 引き継ぎ情報登録時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateTakeOverTriggerScriptId(String createTakeOverTriggerScriptId) {
		this.createTakeOverTriggerScriptId = createTakeOverTriggerScriptId;
		return this;
	}
	/** 引き継ぎ情報登録完了時 に実行されるスクリプト のGRN */
	protected String createTakeOverDoneTriggerScriptId;

	/**
	 * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 引き継ぎ情報登録完了時 に実行されるスクリプト のGRN
	 */
	public String getCreateTakeOverDoneTriggerScriptId() {
		return createTakeOverDoneTriggerScriptId;
	}

	/**
	 * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createTakeOverDoneTriggerScriptId 引き継ぎ情報登録完了時 に実行されるスクリプト のGRN
	 */
	public void setCreateTakeOverDoneTriggerScriptId(String createTakeOverDoneTriggerScriptId) {
		this.createTakeOverDoneTriggerScriptId = createTakeOverDoneTriggerScriptId;
	}

	/**
	 * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createTakeOverDoneTriggerScriptId 引き継ぎ情報登録完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateTakeOverDoneTriggerScriptId(String createTakeOverDoneTriggerScriptId) {
		this.createTakeOverDoneTriggerScriptId = createTakeOverDoneTriggerScriptId;
		return this;
	}
	/** 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRN */
	protected String createTakeOverDoneTriggerQueueNamespaceId;

	/**
	 * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getCreateTakeOverDoneTriggerQueueNamespaceId() {
		return createTakeOverDoneTriggerQueueNamespaceId;
	}

	/**
	 * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param createTakeOverDoneTriggerQueueNamespaceId 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setCreateTakeOverDoneTriggerQueueNamespaceId(String createTakeOverDoneTriggerQueueNamespaceId) {
		this.createTakeOverDoneTriggerQueueNamespaceId = createTakeOverDoneTriggerQueueNamespaceId;
	}

	/**
	 * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param createTakeOverDoneTriggerQueueNamespaceId 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withCreateTakeOverDoneTriggerQueueNamespaceId(String createTakeOverDoneTriggerQueueNamespaceId) {
		this.createTakeOverDoneTriggerQueueNamespaceId = createTakeOverDoneTriggerQueueNamespaceId;
		return this;
	}
	/** 引き継ぎ実行時 に実行されるスクリプト のGRN */
	protected String doTakeOverTriggerScriptId;

	/**
	 * 引き継ぎ実行時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 引き継ぎ実行時 に実行されるスクリプト のGRN
	 */
	public String getDoTakeOverTriggerScriptId() {
		return doTakeOverTriggerScriptId;
	}

	/**
	 * 引き継ぎ実行時 に実行されるスクリプト のGRNを設定
	 *
	 * @param doTakeOverTriggerScriptId 引き継ぎ実行時 に実行されるスクリプト のGRN
	 */
	public void setDoTakeOverTriggerScriptId(String doTakeOverTriggerScriptId) {
		this.doTakeOverTriggerScriptId = doTakeOverTriggerScriptId;
	}

	/**
	 * 引き継ぎ実行時 に実行されるスクリプト のGRNを設定
	 *
	 * @param doTakeOverTriggerScriptId 引き継ぎ実行時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withDoTakeOverTriggerScriptId(String doTakeOverTriggerScriptId) {
		this.doTakeOverTriggerScriptId = doTakeOverTriggerScriptId;
		return this;
	}
	/** 引き継ぎ実行完了時 に実行されるスクリプト のGRN */
	protected String doTakeOverDoneTriggerScriptId;

	/**
	 * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return 引き継ぎ実行完了時 に実行されるスクリプト のGRN
	 */
	public String getDoTakeOverDoneTriggerScriptId() {
		return doTakeOverDoneTriggerScriptId;
	}

	/**
	 * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param doTakeOverDoneTriggerScriptId 引き継ぎ実行完了時 に実行されるスクリプト のGRN
	 */
	public void setDoTakeOverDoneTriggerScriptId(String doTakeOverDoneTriggerScriptId) {
		this.doTakeOverDoneTriggerScriptId = doTakeOverDoneTriggerScriptId;
	}

	/**
	 * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param doTakeOverDoneTriggerScriptId 引き継ぎ実行完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withDoTakeOverDoneTriggerScriptId(String doTakeOverDoneTriggerScriptId) {
		this.doTakeOverDoneTriggerScriptId = doTakeOverDoneTriggerScriptId;
		return this;
	}
	/** 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRN */
	protected String doTakeOverDoneTriggerQueueNamespaceId;

	/**
	 * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを取得
	 *
	 * @return 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRN
	 */
	public String getDoTakeOverDoneTriggerQueueNamespaceId() {
		return doTakeOverDoneTriggerQueueNamespaceId;
	}

	/**
	 * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param doTakeOverDoneTriggerQueueNamespaceId 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRN
	 */
	public void setDoTakeOverDoneTriggerQueueNamespaceId(String doTakeOverDoneTriggerQueueNamespaceId) {
		this.doTakeOverDoneTriggerQueueNamespaceId = doTakeOverDoneTriggerQueueNamespaceId;
	}

	/**
	 * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを設定
	 *
	 * @param doTakeOverDoneTriggerQueueNamespaceId 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRN
	 * @return this
	 */
	public Namespace withDoTakeOverDoneTriggerQueueNamespaceId(String doTakeOverDoneTriggerQueueNamespaceId) {
		this.doTakeOverDoneTriggerQueueNamespaceId = doTakeOverDoneTriggerQueueNamespaceId;
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
            .put("changePasswordIfTakeOver", this.getChangePasswordIfTakeOver())
            .put("createAccountTriggerScriptId", this.getCreateAccountTriggerScriptId())
            .put("createAccountDoneTriggerScriptId", this.getCreateAccountDoneTriggerScriptId())
            .put("createAccountDoneTriggerQueueNamespaceId", this.getCreateAccountDoneTriggerQueueNamespaceId())
            .put("authenticationTriggerScriptId", this.getAuthenticationTriggerScriptId())
            .put("authenticationDoneTriggerScriptId", this.getAuthenticationDoneTriggerScriptId())
            .put("authenticationDoneTriggerQueueNamespaceId", this.getAuthenticationDoneTriggerQueueNamespaceId())
            .put("createTakeOverTriggerScriptId", this.getCreateTakeOverTriggerScriptId())
            .put("createTakeOverDoneTriggerScriptId", this.getCreateTakeOverDoneTriggerScriptId())
            .put("createTakeOverDoneTriggerQueueNamespaceId", this.getCreateTakeOverDoneTriggerQueueNamespaceId())
            .put("doTakeOverTriggerScriptId", this.getDoTakeOverTriggerScriptId())
            .put("doTakeOverDoneTriggerScriptId", this.getDoTakeOverDoneTriggerScriptId())
            .put("doTakeOverDoneTriggerQueueNamespaceId", this.getDoTakeOverDoneTriggerQueueNamespaceId())
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
        result = prime * result + ((this.changePasswordIfTakeOver == null) ? 0 : this.changePasswordIfTakeOver.hashCode());
        result = prime * result + ((this.createAccountTriggerScriptId == null) ? 0 : this.createAccountTriggerScriptId.hashCode());
        result = prime * result + ((this.createAccountDoneTriggerScriptId == null) ? 0 : this.createAccountDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.createAccountDoneTriggerQueueNamespaceId == null) ? 0 : this.createAccountDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.authenticationTriggerScriptId == null) ? 0 : this.authenticationTriggerScriptId.hashCode());
        result = prime * result + ((this.authenticationDoneTriggerScriptId == null) ? 0 : this.authenticationDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.authenticationDoneTriggerQueueNamespaceId == null) ? 0 : this.authenticationDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.createTakeOverTriggerScriptId == null) ? 0 : this.createTakeOverTriggerScriptId.hashCode());
        result = prime * result + ((this.createTakeOverDoneTriggerScriptId == null) ? 0 : this.createTakeOverDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.createTakeOverDoneTriggerQueueNamespaceId == null) ? 0 : this.createTakeOverDoneTriggerQueueNamespaceId.hashCode());
        result = prime * result + ((this.doTakeOverTriggerScriptId == null) ? 0 : this.doTakeOverTriggerScriptId.hashCode());
        result = prime * result + ((this.doTakeOverDoneTriggerScriptId == null) ? 0 : this.doTakeOverDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.doTakeOverDoneTriggerQueueNamespaceId == null) ? 0 : this.doTakeOverDoneTriggerQueueNamespaceId.hashCode());
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
		if (changePasswordIfTakeOver == null) {
			return other.changePasswordIfTakeOver == null;
		} else if (!changePasswordIfTakeOver.equals(other.changePasswordIfTakeOver)) {
			return false;
		}
		if (createAccountTriggerScriptId == null) {
			return other.createAccountTriggerScriptId == null;
		} else if (!createAccountTriggerScriptId.equals(other.createAccountTriggerScriptId)) {
			return false;
		}
		if (createAccountDoneTriggerScriptId == null) {
			return other.createAccountDoneTriggerScriptId == null;
		} else if (!createAccountDoneTriggerScriptId.equals(other.createAccountDoneTriggerScriptId)) {
			return false;
		}
		if (createAccountDoneTriggerQueueNamespaceId == null) {
			return other.createAccountDoneTriggerQueueNamespaceId == null;
		} else if (!createAccountDoneTriggerQueueNamespaceId.equals(other.createAccountDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (authenticationTriggerScriptId == null) {
			return other.authenticationTriggerScriptId == null;
		} else if (!authenticationTriggerScriptId.equals(other.authenticationTriggerScriptId)) {
			return false;
		}
		if (authenticationDoneTriggerScriptId == null) {
			return other.authenticationDoneTriggerScriptId == null;
		} else if (!authenticationDoneTriggerScriptId.equals(other.authenticationDoneTriggerScriptId)) {
			return false;
		}
		if (authenticationDoneTriggerQueueNamespaceId == null) {
			return other.authenticationDoneTriggerQueueNamespaceId == null;
		} else if (!authenticationDoneTriggerQueueNamespaceId.equals(other.authenticationDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (createTakeOverTriggerScriptId == null) {
			return other.createTakeOverTriggerScriptId == null;
		} else if (!createTakeOverTriggerScriptId.equals(other.createTakeOverTriggerScriptId)) {
			return false;
		}
		if (createTakeOverDoneTriggerScriptId == null) {
			return other.createTakeOverDoneTriggerScriptId == null;
		} else if (!createTakeOverDoneTriggerScriptId.equals(other.createTakeOverDoneTriggerScriptId)) {
			return false;
		}
		if (createTakeOverDoneTriggerQueueNamespaceId == null) {
			return other.createTakeOverDoneTriggerQueueNamespaceId == null;
		} else if (!createTakeOverDoneTriggerQueueNamespaceId.equals(other.createTakeOverDoneTriggerQueueNamespaceId)) {
			return false;
		}
		if (doTakeOverTriggerScriptId == null) {
			return other.doTakeOverTriggerScriptId == null;
		} else if (!doTakeOverTriggerScriptId.equals(other.doTakeOverTriggerScriptId)) {
			return false;
		}
		if (doTakeOverDoneTriggerScriptId == null) {
			return other.doTakeOverDoneTriggerScriptId == null;
		} else if (!doTakeOverDoneTriggerScriptId.equals(other.doTakeOverDoneTriggerScriptId)) {
			return false;
		}
		if (doTakeOverDoneTriggerQueueNamespaceId == null) {
			return other.doTakeOverDoneTriggerQueueNamespaceId == null;
		} else if (!doTakeOverDoneTriggerQueueNamespaceId.equals(other.doTakeOverDoneTriggerQueueNamespaceId)) {
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