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
	/** ウォレット新規作成時 に実行されるスクリプト のGRN */
	protected String createWalletTriggerScriptId;

	/**
	 * ウォレット新規作成時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ウォレット新規作成時 に実行されるスクリプト のGRN
	 */
	public String getCreateWalletTriggerScriptId() {
		return createWalletTriggerScriptId;
	}

	/**
	 * ウォレット新規作成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createWalletTriggerScriptId ウォレット新規作成時 に実行されるスクリプト のGRN
	 */
	public void setCreateWalletTriggerScriptId(String createWalletTriggerScriptId) {
		this.createWalletTriggerScriptId = createWalletTriggerScriptId;
	}

	/**
	 * ウォレット新規作成時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createWalletTriggerScriptId ウォレット新規作成時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateWalletTriggerScriptId(String createWalletTriggerScriptId) {
		this.createWalletTriggerScriptId = createWalletTriggerScriptId;
		return this;
	}
	/** ウォレット新規作成完了時 に実行されるスクリプト のGRN */
	protected String createWalletDoneTriggerScriptId;

	/**
	 * ウォレット新規作成完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ウォレット新規作成完了時 に実行されるスクリプト のGRN
	 */
	public String getCreateWalletDoneTriggerScriptId() {
		return createWalletDoneTriggerScriptId;
	}

	/**
	 * ウォレット新規作成完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createWalletDoneTriggerScriptId ウォレット新規作成完了時 に実行されるスクリプト のGRN
	 */
	public void setCreateWalletDoneTriggerScriptId(String createWalletDoneTriggerScriptId) {
		this.createWalletDoneTriggerScriptId = createWalletDoneTriggerScriptId;
	}

	/**
	 * ウォレット新規作成完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param createWalletDoneTriggerScriptId ウォレット新規作成完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withCreateWalletDoneTriggerScriptId(String createWalletDoneTriggerScriptId) {
		this.createWalletDoneTriggerScriptId = createWalletDoneTriggerScriptId;
		return this;
	}
	/** ウォレット新規作成完了時 にジョブを登録するネームスペース のGRN */
	protected String createWalletDoneTriggerNamespaceId;

	/**
	 * ウォレット新規作成完了時 にジョブを登録するネームスペース のGRNを取得
	 *
	 * @return ウォレット新規作成完了時 にジョブを登録するネームスペース のGRN
	 */
	public String getCreateWalletDoneTriggerNamespaceId() {
		return createWalletDoneTriggerNamespaceId;
	}

	/**
	 * ウォレット新規作成完了時 にジョブを登録するネームスペース のGRNを設定
	 *
	 * @param createWalletDoneTriggerNamespaceId ウォレット新規作成完了時 にジョブを登録するネームスペース のGRN
	 */
	public void setCreateWalletDoneTriggerNamespaceId(String createWalletDoneTriggerNamespaceId) {
		this.createWalletDoneTriggerNamespaceId = createWalletDoneTriggerNamespaceId;
	}

	/**
	 * ウォレット新規作成完了時 にジョブを登録するネームスペース のGRNを設定
	 *
	 * @param createWalletDoneTriggerNamespaceId ウォレット新規作成完了時 にジョブを登録するネームスペース のGRN
	 * @return this
	 */
	public Namespace withCreateWalletDoneTriggerNamespaceId(String createWalletDoneTriggerNamespaceId) {
		this.createWalletDoneTriggerNamespaceId = createWalletDoneTriggerNamespaceId;
		return this;
	}
	/** ウォレット残高加算時 に実行されるスクリプト のGRN */
	protected String depositTriggerScriptId;

	/**
	 * ウォレット残高加算時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ウォレット残高加算時 に実行されるスクリプト のGRN
	 */
	public String getDepositTriggerScriptId() {
		return depositTriggerScriptId;
	}

	/**
	 * ウォレット残高加算時 に実行されるスクリプト のGRNを設定
	 *
	 * @param depositTriggerScriptId ウォレット残高加算時 に実行されるスクリプト のGRN
	 */
	public void setDepositTriggerScriptId(String depositTriggerScriptId) {
		this.depositTriggerScriptId = depositTriggerScriptId;
	}

	/**
	 * ウォレット残高加算時 に実行されるスクリプト のGRNを設定
	 *
	 * @param depositTriggerScriptId ウォレット残高加算時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withDepositTriggerScriptId(String depositTriggerScriptId) {
		this.depositTriggerScriptId = depositTriggerScriptId;
		return this;
	}
	/** ウォレット残高加算完了時 に実行されるスクリプト のGRN */
	protected String depositDoneTriggerScriptId;

	/**
	 * ウォレット残高加算完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ウォレット残高加算完了時 に実行されるスクリプト のGRN
	 */
	public String getDepositDoneTriggerScriptId() {
		return depositDoneTriggerScriptId;
	}

	/**
	 * ウォレット残高加算完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param depositDoneTriggerScriptId ウォレット残高加算完了時 に実行されるスクリプト のGRN
	 */
	public void setDepositDoneTriggerScriptId(String depositDoneTriggerScriptId) {
		this.depositDoneTriggerScriptId = depositDoneTriggerScriptId;
	}

	/**
	 * ウォレット残高加算完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param depositDoneTriggerScriptId ウォレット残高加算完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withDepositDoneTriggerScriptId(String depositDoneTriggerScriptId) {
		this.depositDoneTriggerScriptId = depositDoneTriggerScriptId;
		return this;
	}
	/** ウォレット残高加算完了時 にジョブを登録するネームスペース のGRN */
	protected String depositDoneTriggerNamespaceId;

	/**
	 * ウォレット残高加算完了時 にジョブを登録するネームスペース のGRNを取得
	 *
	 * @return ウォレット残高加算完了時 にジョブを登録するネームスペース のGRN
	 */
	public String getDepositDoneTriggerNamespaceId() {
		return depositDoneTriggerNamespaceId;
	}

	/**
	 * ウォレット残高加算完了時 にジョブを登録するネームスペース のGRNを設定
	 *
	 * @param depositDoneTriggerNamespaceId ウォレット残高加算完了時 にジョブを登録するネームスペース のGRN
	 */
	public void setDepositDoneTriggerNamespaceId(String depositDoneTriggerNamespaceId) {
		this.depositDoneTriggerNamespaceId = depositDoneTriggerNamespaceId;
	}

	/**
	 * ウォレット残高加算完了時 にジョブを登録するネームスペース のGRNを設定
	 *
	 * @param depositDoneTriggerNamespaceId ウォレット残高加算完了時 にジョブを登録するネームスペース のGRN
	 * @return this
	 */
	public Namespace withDepositDoneTriggerNamespaceId(String depositDoneTriggerNamespaceId) {
		this.depositDoneTriggerNamespaceId = depositDoneTriggerNamespaceId;
		return this;
	}
	/** ウォレット残高消費時 に実行されるスクリプト のGRN */
	protected String withdrawTriggerScriptId;

	/**
	 * ウォレット残高消費時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ウォレット残高消費時 に実行されるスクリプト のGRN
	 */
	public String getWithdrawTriggerScriptId() {
		return withdrawTriggerScriptId;
	}

	/**
	 * ウォレット残高消費時 に実行されるスクリプト のGRNを設定
	 *
	 * @param withdrawTriggerScriptId ウォレット残高消費時 に実行されるスクリプト のGRN
	 */
	public void setWithdrawTriggerScriptId(String withdrawTriggerScriptId) {
		this.withdrawTriggerScriptId = withdrawTriggerScriptId;
	}

	/**
	 * ウォレット残高消費時 に実行されるスクリプト のGRNを設定
	 *
	 * @param withdrawTriggerScriptId ウォレット残高消費時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withWithdrawTriggerScriptId(String withdrawTriggerScriptId) {
		this.withdrawTriggerScriptId = withdrawTriggerScriptId;
		return this;
	}
	/** ウォレット残高消費完了時 に実行されるスクリプト のGRN */
	protected String withdrawDoneTriggerScriptId;

	/**
	 * ウォレット残高消費完了時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ウォレット残高消費完了時 に実行されるスクリプト のGRN
	 */
	public String getWithdrawDoneTriggerScriptId() {
		return withdrawDoneTriggerScriptId;
	}

	/**
	 * ウォレット残高消費完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param withdrawDoneTriggerScriptId ウォレット残高消費完了時 に実行されるスクリプト のGRN
	 */
	public void setWithdrawDoneTriggerScriptId(String withdrawDoneTriggerScriptId) {
		this.withdrawDoneTriggerScriptId = withdrawDoneTriggerScriptId;
	}

	/**
	 * ウォレット残高消費完了時 に実行されるスクリプト のGRNを設定
	 *
	 * @param withdrawDoneTriggerScriptId ウォレット残高消費完了時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withWithdrawDoneTriggerScriptId(String withdrawDoneTriggerScriptId) {
		this.withdrawDoneTriggerScriptId = withdrawDoneTriggerScriptId;
		return this;
	}
	/** ウォレット残高消費完了時 にジョブを登録するネームスペース のGRN */
	protected String withdrawDoneTriggerNamespaceId;

	/**
	 * ウォレット残高消費完了時 にジョブを登録するネームスペース のGRNを取得
	 *
	 * @return ウォレット残高消費完了時 にジョブを登録するネームスペース のGRN
	 */
	public String getWithdrawDoneTriggerNamespaceId() {
		return withdrawDoneTriggerNamespaceId;
	}

	/**
	 * ウォレット残高消費完了時 にジョブを登録するネームスペース のGRNを設定
	 *
	 * @param withdrawDoneTriggerNamespaceId ウォレット残高消費完了時 にジョブを登録するネームスペース のGRN
	 */
	public void setWithdrawDoneTriggerNamespaceId(String withdrawDoneTriggerNamespaceId) {
		this.withdrawDoneTriggerNamespaceId = withdrawDoneTriggerNamespaceId;
	}

	/**
	 * ウォレット残高消費完了時 にジョブを登録するネームスペース のGRNを設定
	 *
	 * @param withdrawDoneTriggerNamespaceId ウォレット残高消費完了時 にジョブを登録するネームスペース のGRN
	 * @return this
	 */
	public Namespace withWithdrawDoneTriggerNamespaceId(String withdrawDoneTriggerNamespaceId) {
		this.withdrawDoneTriggerNamespaceId = withdrawDoneTriggerNamespaceId;
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
            .put("priority", this.getPriority())
            .put("shareFree", this.getShareFree())
            .put("currency", this.getCurrency())
            .put("appleKey", this.getAppleKey())
            .put("googleKey", this.getGoogleKey())
            .put("enableFakeReceipt", this.getEnableFakeReceipt())
            .put("createWalletTriggerScriptId", this.getCreateWalletTriggerScriptId())
            .put("createWalletDoneTriggerScriptId", this.getCreateWalletDoneTriggerScriptId())
            .put("createWalletDoneTriggerNamespaceId", this.getCreateWalletDoneTriggerNamespaceId())
            .put("depositTriggerScriptId", this.getDepositTriggerScriptId())
            .put("depositDoneTriggerScriptId", this.getDepositDoneTriggerScriptId())
            .put("depositDoneTriggerNamespaceId", this.getDepositDoneTriggerNamespaceId())
            .put("withdrawTriggerScriptId", this.getWithdrawTriggerScriptId())
            .put("withdrawDoneTriggerScriptId", this.getWithdrawDoneTriggerScriptId())
            .put("withdrawDoneTriggerNamespaceId", this.getWithdrawDoneTriggerNamespaceId())
            .put("balance", this.getBalance())
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
        result = prime * result + ((this.priority == null) ? 0 : this.priority.hashCode());
        result = prime * result + ((this.shareFree == null) ? 0 : this.shareFree.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.appleKey == null) ? 0 : this.appleKey.hashCode());
        result = prime * result + ((this.googleKey == null) ? 0 : this.googleKey.hashCode());
        result = prime * result + ((this.enableFakeReceipt == null) ? 0 : this.enableFakeReceipt.hashCode());
        result = prime * result + ((this.createWalletTriggerScriptId == null) ? 0 : this.createWalletTriggerScriptId.hashCode());
        result = prime * result + ((this.createWalletDoneTriggerScriptId == null) ? 0 : this.createWalletDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.createWalletDoneTriggerNamespaceId == null) ? 0 : this.createWalletDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.depositTriggerScriptId == null) ? 0 : this.depositTriggerScriptId.hashCode());
        result = prime * result + ((this.depositDoneTriggerScriptId == null) ? 0 : this.depositDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.depositDoneTriggerNamespaceId == null) ? 0 : this.depositDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.withdrawTriggerScriptId == null) ? 0 : this.withdrawTriggerScriptId.hashCode());
        result = prime * result + ((this.withdrawDoneTriggerScriptId == null) ? 0 : this.withdrawDoneTriggerScriptId.hashCode());
        result = prime * result + ((this.withdrawDoneTriggerNamespaceId == null) ? 0 : this.withdrawDoneTriggerNamespaceId.hashCode());
        result = prime * result + ((this.balance == null) ? 0 : this.balance.hashCode());
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
		if (createWalletTriggerScriptId == null) {
			return other.createWalletTriggerScriptId == null;
		} else if (!createWalletTriggerScriptId.equals(other.createWalletTriggerScriptId)) {
			return false;
		}
		if (createWalletDoneTriggerScriptId == null) {
			return other.createWalletDoneTriggerScriptId == null;
		} else if (!createWalletDoneTriggerScriptId.equals(other.createWalletDoneTriggerScriptId)) {
			return false;
		}
		if (createWalletDoneTriggerNamespaceId == null) {
			return other.createWalletDoneTriggerNamespaceId == null;
		} else if (!createWalletDoneTriggerNamespaceId.equals(other.createWalletDoneTriggerNamespaceId)) {
			return false;
		}
		if (depositTriggerScriptId == null) {
			return other.depositTriggerScriptId == null;
		} else if (!depositTriggerScriptId.equals(other.depositTriggerScriptId)) {
			return false;
		}
		if (depositDoneTriggerScriptId == null) {
			return other.depositDoneTriggerScriptId == null;
		} else if (!depositDoneTriggerScriptId.equals(other.depositDoneTriggerScriptId)) {
			return false;
		}
		if (depositDoneTriggerNamespaceId == null) {
			return other.depositDoneTriggerNamespaceId == null;
		} else if (!depositDoneTriggerNamespaceId.equals(other.depositDoneTriggerNamespaceId)) {
			return false;
		}
		if (withdrawTriggerScriptId == null) {
			return other.withdrawTriggerScriptId == null;
		} else if (!withdrawTriggerScriptId.equals(other.withdrawTriggerScriptId)) {
			return false;
		}
		if (withdrawDoneTriggerScriptId == null) {
			return other.withdrawDoneTriggerScriptId == null;
		} else if (!withdrawDoneTriggerScriptId.equals(other.withdrawDoneTriggerScriptId)) {
			return false;
		}
		if (withdrawDoneTriggerNamespaceId == null) {
			return other.withdrawDoneTriggerNamespaceId == null;
		} else if (!withdrawDoneTriggerNamespaceId.equals(other.withdrawDoneTriggerNamespaceId)) {
			return false;
		}
		if (balance == null) {
			return other.balance == null;
		} else if (!balance.equals(other.balance)) {
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