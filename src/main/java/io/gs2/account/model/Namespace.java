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
	/** アカウント新規作成したときに実行するスクリプト */
	protected ScriptSetting createAccountScript;

	/**
	 * アカウント新規作成したときに実行するスクリプトを取得
	 *
	 * @return アカウント新規作成したときに実行するスクリプト
	 */
	public ScriptSetting getCreateAccountScript() {
		return createAccountScript;
	}

	/**
	 * アカウント新規作成したときに実行するスクリプトを設定
	 *
	 * @param createAccountScript アカウント新規作成したときに実行するスクリプト
	 */
	public void setCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
	}

	/**
	 * アカウント新規作成したときに実行するスクリプトを設定
	 *
	 * @param createAccountScript アカウント新規作成したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
		return this;
	}
	/** 認証したときに実行するスクリプト */
	protected ScriptSetting authenticationScript;

	/**
	 * 認証したときに実行するスクリプトを取得
	 *
	 * @return 認証したときに実行するスクリプト
	 */
	public ScriptSetting getAuthenticationScript() {
		return authenticationScript;
	}

	/**
	 * 認証したときに実行するスクリプトを設定
	 *
	 * @param authenticationScript 認証したときに実行するスクリプト
	 */
	public void setAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
	}

	/**
	 * 認証したときに実行するスクリプトを設定
	 *
	 * @param authenticationScript 認証したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
		return this;
	}
	/** 引き継ぎ情報登録したときに実行するスクリプト */
	protected ScriptSetting createTakeOverScript;

	/**
	 * 引き継ぎ情報登録したときに実行するスクリプトを取得
	 *
	 * @return 引き継ぎ情報登録したときに実行するスクリプト
	 */
	public ScriptSetting getCreateTakeOverScript() {
		return createTakeOverScript;
	}

	/**
	 * 引き継ぎ情報登録したときに実行するスクリプトを設定
	 *
	 * @param createTakeOverScript 引き継ぎ情報登録したときに実行するスクリプト
	 */
	public void setCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
	}

	/**
	 * 引き継ぎ情報登録したときに実行するスクリプトを設定
	 *
	 * @param createTakeOverScript 引き継ぎ情報登録したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
		return this;
	}
	/** 引き継ぎ実行したときに実行するスクリプト */
	protected ScriptSetting doTakeOverScript;

	/**
	 * 引き継ぎ実行したときに実行するスクリプトを取得
	 *
	 * @return 引き継ぎ実行したときに実行するスクリプト
	 */
	public ScriptSetting getDoTakeOverScript() {
		return doTakeOverScript;
	}

	/**
	 * 引き継ぎ実行したときに実行するスクリプトを設定
	 *
	 * @param doTakeOverScript 引き継ぎ実行したときに実行するスクリプト
	 */
	public void setDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
	}

	/**
	 * 引き継ぎ実行したときに実行するスクリプトを設定
	 *
	 * @param doTakeOverScript 引き継ぎ実行したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
		return this;
	}
	/** ログの出力設定 */
	protected LogSetting logSetting;

	/**
	 * ログの出力設定を取得
	 *
	 * @return ログの出力設定
	 */
	public LogSetting getLogSetting() {
		return logSetting;
	}

	/**
	 * ログの出力設定を設定
	 *
	 * @param logSetting ログの出力設定
	 */
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}

	/**
	 * ログの出力設定を設定
	 *
	 * @param logSetting ログの出力設定
	 * @return this
	 */
	public Namespace withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
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
        JsonNode createAccountScript = this.getCreateAccountScript().toJson();
        JsonNode authenticationScript = this.getAuthenticationScript().toJson();
        JsonNode createTakeOverScript = this.getCreateTakeOverScript().toJson();
        JsonNode doTakeOverScript = this.getDoTakeOverScript().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("changePasswordIfTakeOver", this.getChangePasswordIfTakeOver())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("createAccountScript", createAccountScript);
        body_.set("authenticationScript", authenticationScript);
        body_.set("createTakeOverScript", createTakeOverScript);
        body_.set("doTakeOverScript", doTakeOverScript);
        body_.set("logSetting", logSetting);
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
        result = prime * result + ((this.createAccountScript == null) ? 0 : this.createAccountScript.hashCode());
        result = prime * result + ((this.authenticationScript == null) ? 0 : this.authenticationScript.hashCode());
        result = prime * result + ((this.createTakeOverScript == null) ? 0 : this.createTakeOverScript.hashCode());
        result = prime * result + ((this.doTakeOverScript == null) ? 0 : this.doTakeOverScript.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
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
		if (createAccountScript == null) {
			return other.createAccountScript == null;
		} else if (!createAccountScript.equals(other.createAccountScript)) {
			return false;
		}
		if (authenticationScript == null) {
			return other.authenticationScript == null;
		} else if (!authenticationScript.equals(other.authenticationScript)) {
			return false;
		}
		if (createTakeOverScript == null) {
			return other.createTakeOverScript == null;
		} else if (!createTakeOverScript.equals(other.createTakeOverScript)) {
			return false;
		}
		if (doTakeOverScript == null) {
			return other.doTakeOverScript == null;
		} else if (!doTakeOverScript.equals(other.doTakeOverScript)) {
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
		return true;
	}
}