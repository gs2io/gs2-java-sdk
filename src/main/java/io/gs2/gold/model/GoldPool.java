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

package io.gs2.gold.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ゴールドプール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GoldPool implements Serializable {

	/** ゴールドプールGRN */
	private String goldPoolId;

	/** オーナーID */
	private String ownerId;

	/** ゴールドプール名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** ウォレットの生成時 に実行されるGS2-Script */
	private String createWalletTriggerScript;

	/** ウォレットの生成完了時 に実行されるGS2-Script */
	private String createWalletDoneTriggerScript;

	/** ウォレットへの加算時 に実行されるGS2-Script */
	private String depositIntoWalletTriggerScript;

	/** ウォレットへの加算完了時 に実行されるGS2-Script */
	private String depositIntoWalletDoneTriggerScript;

	/** ウォレットからの減算時 に実行されるGS2-Script */
	private String withdrawFromWalletTriggerScript;

	/** ウォレットからの減算完了時 に実行されるGS2-Script */
	private String withdrawFromWalletDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ゴールドプールGRNを取得
	 *
	 * @return ゴールドプールGRN
	 */
	public String getGoldPoolId() {
		return goldPoolId;
	}

	/**
	 * ゴールドプールGRNを設定
	 *
	 * @param goldPoolId ゴールドプールGRN
	 */
	public void setGoldPoolId(String goldPoolId) {
		this.goldPoolId = goldPoolId;
	}

	/**
	 * ゴールドプールGRNを設定
	 *
	 * @param goldPoolId ゴールドプールGRN
	 * @return this
	 */
	public GoldPool withGoldPoolId(String goldPoolId) {
		this.goldPoolId = goldPoolId;
		return this;
	}

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
	public GoldPool withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * ゴールドプール名を取得
	 *
	 * @return ゴールドプール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ゴールドプール名を設定
	 *
	 * @param name ゴールドプール名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ゴールドプール名を設定
	 *
	 * @param name ゴールドプール名
	 * @return this
	 */
	public GoldPool withName(String name) {
		this.name = name;
		return this;
	}

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
	public GoldPool withDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 * @return this
	 */
	public GoldPool withServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
		return this;
	}

	/**
	 * ウォレットの生成時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットの生成時 に実行されるGS2-Script
	 */
	public String getCreateWalletTriggerScript() {
		return createWalletTriggerScript;
	}

	/**
	 * ウォレットの生成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletTriggerScript ウォレットの生成時 に実行されるGS2-Script
	 */
	public void setCreateWalletTriggerScript(String createWalletTriggerScript) {
		this.createWalletTriggerScript = createWalletTriggerScript;
	}

	/**
	 * ウォレットの生成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletTriggerScript ウォレットの生成時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldPool withCreateWalletTriggerScript(String createWalletTriggerScript) {
		this.createWalletTriggerScript = createWalletTriggerScript;
		return this;
	}

	/**
	 * ウォレットの生成完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットの生成完了時 に実行されるGS2-Script
	 */
	public String getCreateWalletDoneTriggerScript() {
		return createWalletDoneTriggerScript;
	}

	/**
	 * ウォレットの生成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletDoneTriggerScript ウォレットの生成完了時 に実行されるGS2-Script
	 */
	public void setCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		this.createWalletDoneTriggerScript = createWalletDoneTriggerScript;
	}

	/**
	 * ウォレットの生成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createWalletDoneTriggerScript ウォレットの生成完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldPool withCreateWalletDoneTriggerScript(String createWalletDoneTriggerScript) {
		this.createWalletDoneTriggerScript = createWalletDoneTriggerScript;
		return this;
	}

	/**
	 * ウォレットへの加算時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットへの加算時 に実行されるGS2-Script
	 */
	public String getDepositIntoWalletTriggerScript() {
		return depositIntoWalletTriggerScript;
	}

	/**
	 * ウォレットへの加算時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletTriggerScript ウォレットへの加算時 に実行されるGS2-Script
	 */
	public void setDepositIntoWalletTriggerScript(String depositIntoWalletTriggerScript) {
		this.depositIntoWalletTriggerScript = depositIntoWalletTriggerScript;
	}

	/**
	 * ウォレットへの加算時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletTriggerScript ウォレットへの加算時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldPool withDepositIntoWalletTriggerScript(String depositIntoWalletTriggerScript) {
		this.depositIntoWalletTriggerScript = depositIntoWalletTriggerScript;
		return this;
	}

	/**
	 * ウォレットへの加算完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットへの加算完了時 に実行されるGS2-Script
	 */
	public String getDepositIntoWalletDoneTriggerScript() {
		return depositIntoWalletDoneTriggerScript;
	}

	/**
	 * ウォレットへの加算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletDoneTriggerScript ウォレットへの加算完了時 に実行されるGS2-Script
	 */
	public void setDepositIntoWalletDoneTriggerScript(String depositIntoWalletDoneTriggerScript) {
		this.depositIntoWalletDoneTriggerScript = depositIntoWalletDoneTriggerScript;
	}

	/**
	 * ウォレットへの加算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param depositIntoWalletDoneTriggerScript ウォレットへの加算完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldPool withDepositIntoWalletDoneTriggerScript(String depositIntoWalletDoneTriggerScript) {
		this.depositIntoWalletDoneTriggerScript = depositIntoWalletDoneTriggerScript;
		return this;
	}

	/**
	 * ウォレットからの減算時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットからの減算時 に実行されるGS2-Script
	 */
	public String getWithdrawFromWalletTriggerScript() {
		return withdrawFromWalletTriggerScript;
	}

	/**
	 * ウォレットからの減算時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletTriggerScript ウォレットからの減算時 に実行されるGS2-Script
	 */
	public void setWithdrawFromWalletTriggerScript(String withdrawFromWalletTriggerScript) {
		this.withdrawFromWalletTriggerScript = withdrawFromWalletTriggerScript;
	}

	/**
	 * ウォレットからの減算時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletTriggerScript ウォレットからの減算時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldPool withWithdrawFromWalletTriggerScript(String withdrawFromWalletTriggerScript) {
		this.withdrawFromWalletTriggerScript = withdrawFromWalletTriggerScript;
		return this;
	}

	/**
	 * ウォレットからの減算完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ウォレットからの減算完了時 に実行されるGS2-Script
	 */
	public String getWithdrawFromWalletDoneTriggerScript() {
		return withdrawFromWalletDoneTriggerScript;
	}

	/**
	 * ウォレットからの減算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletDoneTriggerScript ウォレットからの減算完了時 に実行されるGS2-Script
	 */
	public void setWithdrawFromWalletDoneTriggerScript(String withdrawFromWalletDoneTriggerScript) {
		this.withdrawFromWalletDoneTriggerScript = withdrawFromWalletDoneTriggerScript;
	}

	/**
	 * ウォレットからの減算完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param withdrawFromWalletDoneTriggerScript ウォレットからの減算完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GoldPool withWithdrawFromWalletDoneTriggerScript(String withdrawFromWalletDoneTriggerScript) {
		this.withdrawFromWalletDoneTriggerScript = withdrawFromWalletDoneTriggerScript;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public GoldPool withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public GoldPool withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("goldPoolId", this.getGoldPoolId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("serviceClass", this.getServiceClass())
            .put("createWalletTriggerScript", this.getCreateWalletTriggerScript())
            .put("createWalletDoneTriggerScript", this.getCreateWalletDoneTriggerScript())
            .put("depositIntoWalletTriggerScript", this.getDepositIntoWalletTriggerScript())
            .put("depositIntoWalletDoneTriggerScript", this.getDepositIntoWalletDoneTriggerScript())
            .put("withdrawFromWalletTriggerScript", this.getWithdrawFromWalletTriggerScript())
            .put("withdrawFromWalletDoneTriggerScript", this.getWithdrawFromWalletDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}