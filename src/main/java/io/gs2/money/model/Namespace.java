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

package io.gs2.money.model;

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
	/** ネームスペースの名前 */
	protected String name;

	/**
	 * ネームスペースの名前を取得
	 *
	 * @return ネームスペースの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * ネームスペースの名前を設定
	 *
	 * @param name ネームスペースの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ネームスペースの名前を設定
	 *
	 * @param name ネームスペースの名前
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
	/** 消費優先度 */
	protected String priority;

	/**
	 * 消費優先度を取得
	 *
	 * @return 消費優先度
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * 消費優先度を設定
	 *
	 * @param priority 消費優先度
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * 消費優先度を設定
	 *
	 * @param priority 消費優先度
	 * @return this
	 */
	public Namespace withPriority(String priority) {
		this.priority = priority;
		return this;
	}
	/** 無償課金通貨を異なるスロットで共有するか */
	protected Boolean shareFree;

	/**
	 * 無償課金通貨を異なるスロットで共有するかを取得
	 *
	 * @return 無償課金通貨を異なるスロットで共有するか
	 */
	public Boolean getShareFree() {
		return shareFree;
	}

	/**
	 * 無償課金通貨を異なるスロットで共有するかを設定
	 *
	 * @param shareFree 無償課金通貨を異なるスロットで共有するか
	 */
	public void setShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
	}

	/**
	 * 無償課金通貨を異なるスロットで共有するかを設定
	 *
	 * @param shareFree 無償課金通貨を異なるスロットで共有するか
	 * @return this
	 */
	public Namespace withShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
		return this;
	}
	/** 通貨の種類 */
	protected String currency;

	/**
	 * 通貨の種類を取得
	 *
	 * @return 通貨の種類
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 通貨の種類を設定
	 *
	 * @param currency 通貨の種類
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 通貨の種類を設定
	 *
	 * @param currency 通貨の種類
	 * @return this
	 */
	public Namespace withCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	/** Apple AppStore のバンドルID */
	protected String appleKey;

	/**
	 * Apple AppStore のバンドルIDを取得
	 *
	 * @return Apple AppStore のバンドルID
	 */
	public String getAppleKey() {
		return appleKey;
	}

	/**
	 * Apple AppStore のバンドルIDを設定
	 *
	 * @param appleKey Apple AppStore のバンドルID
	 */
	public void setAppleKey(String appleKey) {
		this.appleKey = appleKey;
	}

	/**
	 * Apple AppStore のバンドルIDを設定
	 *
	 * @param appleKey Apple AppStore のバンドルID
	 * @return this
	 */
	public Namespace withAppleKey(String appleKey) {
		this.appleKey = appleKey;
		return this;
	}
	/** Google PlayStore の秘密鍵 */
	protected String googleKey;

	/**
	 * Google PlayStore の秘密鍵を取得
	 *
	 * @return Google PlayStore の秘密鍵
	 */
	public String getGoogleKey() {
		return googleKey;
	}

	/**
	 * Google PlayStore の秘密鍵を設定
	 *
	 * @param googleKey Google PlayStore の秘密鍵
	 */
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	/**
	 * Google PlayStore の秘密鍵を設定
	 *
	 * @param googleKey Google PlayStore の秘密鍵
	 * @return this
	 */
	public Namespace withGoogleKey(String googleKey) {
		this.googleKey = googleKey;
		return this;
	}
	/** UnityEditorが出力する偽のレシートで決済できるようにするか */
	protected Boolean enableFakeReceipt;

	/**
	 * UnityEditorが出力する偽のレシートで決済できるようにするかを取得
	 *
	 * @return UnityEditorが出力する偽のレシートで決済できるようにするか
	 */
	public Boolean getEnableFakeReceipt() {
		return enableFakeReceipt;
	}

	/**
	 * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
	 *
	 * @param enableFakeReceipt UnityEditorが出力する偽のレシートで決済できるようにするか
	 */
	public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
	}

	/**
	 * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
	 *
	 * @param enableFakeReceipt UnityEditorが出力する偽のレシートで決済できるようにするか
	 * @return this
	 */
	public Namespace withEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
		return this;
	}
	/** ウォレット新規作成したときに実行するスクリプト */
	protected ScriptSetting createWalletScript;

	/**
	 * ウォレット新規作成したときに実行するスクリプトを取得
	 *
	 * @return ウォレット新規作成したときに実行するスクリプト
	 */
	public ScriptSetting getCreateWalletScript() {
		return createWalletScript;
	}

	/**
	 * ウォレット新規作成したときに実行するスクリプトを設定
	 *
	 * @param createWalletScript ウォレット新規作成したときに実行するスクリプト
	 */
	public void setCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
	}

	/**
	 * ウォレット新規作成したときに実行するスクリプトを設定
	 *
	 * @param createWalletScript ウォレット新規作成したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
		return this;
	}
	/** ウォレット残高加算したときに実行するスクリプト */
	protected ScriptSetting depositScript;

	/**
	 * ウォレット残高加算したときに実行するスクリプトを取得
	 *
	 * @return ウォレット残高加算したときに実行するスクリプト
	 */
	public ScriptSetting getDepositScript() {
		return depositScript;
	}

	/**
	 * ウォレット残高加算したときに実行するスクリプトを設定
	 *
	 * @param depositScript ウォレット残高加算したときに実行するスクリプト
	 */
	public void setDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
	}

	/**
	 * ウォレット残高加算したときに実行するスクリプトを設定
	 *
	 * @param depositScript ウォレット残高加算したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
		return this;
	}
	/** ウォレット残高消費したときに実行するスクリプト */
	protected ScriptSetting withdrawScript;

	/**
	 * ウォレット残高消費したときに実行するスクリプトを取得
	 *
	 * @return ウォレット残高消費したときに実行するスクリプト
	 */
	public ScriptSetting getWithdrawScript() {
		return withdrawScript;
	}

	/**
	 * ウォレット残高消費したときに実行するスクリプトを設定
	 *
	 * @param withdrawScript ウォレット残高消費したときに実行するスクリプト
	 */
	public void setWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
	}

	/**
	 * ウォレット残高消費したときに実行するスクリプトを設定
	 *
	 * @param withdrawScript ウォレット残高消費したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
		return this;
	}
	/** 未使用残高 */
	protected Double balance;

	/**
	 * 未使用残高を取得
	 *
	 * @return 未使用残高
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * 未使用残高を設定
	 *
	 * @param balance 未使用残高
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * 未使用残高を設定
	 *
	 * @param balance 未使用残高
	 * @return this
	 */
	public Namespace withBalance(Double balance) {
		this.balance = balance;
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
        JsonNode createWalletScript = this.getCreateWalletScript().toJson();
        JsonNode depositScript = this.getDepositScript().toJson();
        JsonNode withdrawScript = this.getWithdrawScript().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("priority", this.getPriority())
            .put("shareFree", this.getShareFree())
            .put("currency", this.getCurrency())
            .put("appleKey", this.getAppleKey())
            .put("googleKey", this.getGoogleKey())
            .put("enableFakeReceipt", this.getEnableFakeReceipt())
            .put("balance", this.getBalance())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("createWalletScript", createWalletScript);
        body_.set("depositScript", depositScript);
        body_.set("withdrawScript", withdrawScript);
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
        result = prime * result + ((this.priority == null) ? 0 : this.priority.hashCode());
        result = prime * result + ((this.shareFree == null) ? 0 : this.shareFree.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.appleKey == null) ? 0 : this.appleKey.hashCode());
        result = prime * result + ((this.googleKey == null) ? 0 : this.googleKey.hashCode());
        result = prime * result + ((this.enableFakeReceipt == null) ? 0 : this.enableFakeReceipt.hashCode());
        result = prime * result + ((this.createWalletScript == null) ? 0 : this.createWalletScript.hashCode());
        result = prime * result + ((this.depositScript == null) ? 0 : this.depositScript.hashCode());
        result = prime * result + ((this.withdrawScript == null) ? 0 : this.withdrawScript.hashCode());
        result = prime * result + ((this.balance == null) ? 0 : this.balance.hashCode());
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
		if (priority == null) {
			return other.priority == null;
		} else if (!priority.equals(other.priority)) {
			return false;
		}
		if (shareFree == null) {
			return other.shareFree == null;
		} else if (!shareFree.equals(other.shareFree)) {
			return false;
		}
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (appleKey == null) {
			return other.appleKey == null;
		} else if (!appleKey.equals(other.appleKey)) {
			return false;
		}
		if (googleKey == null) {
			return other.googleKey == null;
		} else if (!googleKey.equals(other.googleKey)) {
			return false;
		}
		if (enableFakeReceipt == null) {
			return other.enableFakeReceipt == null;
		} else if (!enableFakeReceipt.equals(other.enableFakeReceipt)) {
			return false;
		}
		if (createWalletScript == null) {
			return other.createWalletScript == null;
		} else if (!createWalletScript.equals(other.createWalletScript)) {
			return false;
		}
		if (depositScript == null) {
			return other.depositScript == null;
		} else if (!depositScript.equals(other.depositScript)) {
			return false;
		}
		if (withdrawScript == null) {
			return other.withdrawScript == null;
		} else if (!withdrawScript.equals(other.withdrawScript)) {
			return false;
		}
		if (balance == null) {
			return other.balance == null;
		} else if (!balance.equals(other.balance)) {
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