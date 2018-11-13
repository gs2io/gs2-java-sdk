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

package io.gs2.level.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * リソースプール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResourcePool implements Serializable {

	/** リソースプールGRN */
	private String resourcePoolId;

	/** オーナーID */
	private String ownerId;

	/** リソースプール名 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** レベルキャップ取得時 に実行されるGS2-Script */
	private String levelCapScript;

	/** 経験値変化時 に実行されるGS2-Script */
	private String changeExperienceTriggerScript;

	/** 経験値変化完了時 に実行されるGS2-Script */
	private String changeExperienceDoneTriggerScript;

	/** レベル変化時 に実行されるGS2-Script */
	private String changeLevelTriggerScript;

	/** レベル変化完了時 に実行されるGS2-Script */
	private String changeLevelDoneTriggerScript;

	/** レベルキャップ変化時 に実行されるGS2-Script */
	private String changeLevelCapTriggerScript;

	/** レベルキャップ変化完了時 に実行されるGS2-Script */
	private String changeLevelCapDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * リソースプールGRNを取得
	 *
	 * @return リソースプールGRN
	 */
	public String getResourcePoolId() {
		return resourcePoolId;
	}

	/**
	 * リソースプールGRNを設定
	 *
	 * @param resourcePoolId リソースプールGRN
	 */
	public void setResourcePoolId(String resourcePoolId) {
		this.resourcePoolId = resourcePoolId;
	}

	/**
	 * リソースプールGRNを設定
	 *
	 * @param resourcePoolId リソースプールGRN
	 * @return this
	 */
	public ResourcePool withResourcePoolId(String resourcePoolId) {
		this.resourcePoolId = resourcePoolId;
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
	public ResourcePool withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * リソースプール名を取得
	 *
	 * @return リソースプール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * リソースプール名を設定
	 *
	 * @param name リソースプール名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * リソースプール名を設定
	 *
	 * @param name リソースプール名
	 * @return this
	 */
	public ResourcePool withName(String name) {
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
	public ResourcePool withDescription(String description) {
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
	public ResourcePool withServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
		return this;
	}

	/**
	 * レベルキャップ取得時 に実行されるGS2-Scriptを取得
	 *
	 * @return レベルキャップ取得時 に実行されるGS2-Script
	 */
	public String getLevelCapScript() {
		return levelCapScript;
	}

	/**
	 * レベルキャップ取得時 に実行されるGS2-Scriptを設定
	 *
	 * @param levelCapScript レベルキャップ取得時 に実行されるGS2-Script
	 */
	public void setLevelCapScript(String levelCapScript) {
		this.levelCapScript = levelCapScript;
	}

	/**
	 * レベルキャップ取得時 に実行されるGS2-Scriptを設定
	 *
	 * @param levelCapScript レベルキャップ取得時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withLevelCapScript(String levelCapScript) {
		this.levelCapScript = levelCapScript;
		return this;
	}

	/**
	 * 経験値変化時 に実行されるGS2-Scriptを取得
	 *
	 * @return 経験値変化時 に実行されるGS2-Script
	 */
	public String getChangeExperienceTriggerScript() {
		return changeExperienceTriggerScript;
	}

	/**
	 * 経験値変化時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeExperienceTriggerScript 経験値変化時 に実行されるGS2-Script
	 */
	public void setChangeExperienceTriggerScript(String changeExperienceTriggerScript) {
		this.changeExperienceTriggerScript = changeExperienceTriggerScript;
	}

	/**
	 * 経験値変化時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeExperienceTriggerScript 経験値変化時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withChangeExperienceTriggerScript(String changeExperienceTriggerScript) {
		this.changeExperienceTriggerScript = changeExperienceTriggerScript;
		return this;
	}

	/**
	 * 経験値変化完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 経験値変化完了時 に実行されるGS2-Script
	 */
	public String getChangeExperienceDoneTriggerScript() {
		return changeExperienceDoneTriggerScript;
	}

	/**
	 * 経験値変化完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeExperienceDoneTriggerScript 経験値変化完了時 に実行されるGS2-Script
	 */
	public void setChangeExperienceDoneTriggerScript(String changeExperienceDoneTriggerScript) {
		this.changeExperienceDoneTriggerScript = changeExperienceDoneTriggerScript;
	}

	/**
	 * 経験値変化完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeExperienceDoneTriggerScript 経験値変化完了時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withChangeExperienceDoneTriggerScript(String changeExperienceDoneTriggerScript) {
		this.changeExperienceDoneTriggerScript = changeExperienceDoneTriggerScript;
		return this;
	}

	/**
	 * レベル変化時 に実行されるGS2-Scriptを取得
	 *
	 * @return レベル変化時 に実行されるGS2-Script
	 */
	public String getChangeLevelTriggerScript() {
		return changeLevelTriggerScript;
	}

	/**
	 * レベル変化時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelTriggerScript レベル変化時 に実行されるGS2-Script
	 */
	public void setChangeLevelTriggerScript(String changeLevelTriggerScript) {
		this.changeLevelTriggerScript = changeLevelTriggerScript;
	}

	/**
	 * レベル変化時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelTriggerScript レベル変化時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withChangeLevelTriggerScript(String changeLevelTriggerScript) {
		this.changeLevelTriggerScript = changeLevelTriggerScript;
		return this;
	}

	/**
	 * レベル変化完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return レベル変化完了時 に実行されるGS2-Script
	 */
	public String getChangeLevelDoneTriggerScript() {
		return changeLevelDoneTriggerScript;
	}

	/**
	 * レベル変化完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelDoneTriggerScript レベル変化完了時 に実行されるGS2-Script
	 */
	public void setChangeLevelDoneTriggerScript(String changeLevelDoneTriggerScript) {
		this.changeLevelDoneTriggerScript = changeLevelDoneTriggerScript;
	}

	/**
	 * レベル変化完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelDoneTriggerScript レベル変化完了時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withChangeLevelDoneTriggerScript(String changeLevelDoneTriggerScript) {
		this.changeLevelDoneTriggerScript = changeLevelDoneTriggerScript;
		return this;
	}

	/**
	 * レベルキャップ変化時 に実行されるGS2-Scriptを取得
	 *
	 * @return レベルキャップ変化時 に実行されるGS2-Script
	 */
	public String getChangeLevelCapTriggerScript() {
		return changeLevelCapTriggerScript;
	}

	/**
	 * レベルキャップ変化時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelCapTriggerScript レベルキャップ変化時 に実行されるGS2-Script
	 */
	public void setChangeLevelCapTriggerScript(String changeLevelCapTriggerScript) {
		this.changeLevelCapTriggerScript = changeLevelCapTriggerScript;
	}

	/**
	 * レベルキャップ変化時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelCapTriggerScript レベルキャップ変化時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withChangeLevelCapTriggerScript(String changeLevelCapTriggerScript) {
		this.changeLevelCapTriggerScript = changeLevelCapTriggerScript;
		return this;
	}

	/**
	 * レベルキャップ変化完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return レベルキャップ変化完了時 に実行されるGS2-Script
	 */
	public String getChangeLevelCapDoneTriggerScript() {
		return changeLevelCapDoneTriggerScript;
	}

	/**
	 * レベルキャップ変化完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelCapDoneTriggerScript レベルキャップ変化完了時 に実行されるGS2-Script
	 */
	public void setChangeLevelCapDoneTriggerScript(String changeLevelCapDoneTriggerScript) {
		this.changeLevelCapDoneTriggerScript = changeLevelCapDoneTriggerScript;
	}

	/**
	 * レベルキャップ変化完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param changeLevelCapDoneTriggerScript レベルキャップ変化完了時 に実行されるGS2-Script
	 * @return this
	 */
	public ResourcePool withChangeLevelCapDoneTriggerScript(String changeLevelCapDoneTriggerScript) {
		this.changeLevelCapDoneTriggerScript = changeLevelCapDoneTriggerScript;
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
	public ResourcePool withCreateAt(Integer createAt) {
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
	public ResourcePool withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("resourcePoolId", this.getResourcePoolId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("serviceClass", this.getServiceClass())
            .put("levelCapScript", this.getLevelCapScript())
            .put("changeExperienceTriggerScript", this.getChangeExperienceTriggerScript())
            .put("changeExperienceDoneTriggerScript", this.getChangeExperienceDoneTriggerScript())
            .put("changeLevelTriggerScript", this.getChangeLevelTriggerScript())
            .put("changeLevelDoneTriggerScript", this.getChangeLevelDoneTriggerScript())
            .put("changeLevelCapTriggerScript", this.getChangeLevelCapTriggerScript())
            .put("changeLevelCapDoneTriggerScript", this.getChangeLevelCapDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}