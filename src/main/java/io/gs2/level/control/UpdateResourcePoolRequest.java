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

package io.gs2.level.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.level.model.*;
import io.gs2.level.Gs2Level;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateResourcePoolRequest extends Gs2BasicRequest<UpdateResourcePoolRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "UpdateResourcePool";
	}

	/** リソースプール */
	private String resourcePoolName;

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


	/**
	 * リソースプールを取得
	 *
	 * @return リソースプール
	 */
	public String getResourcePoolName() {
		return resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 */
	public void setResourcePoolName(String resourcePoolName) {
		this.resourcePoolName = resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 * @return this
	 */
	public UpdateResourcePoolRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
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
	public UpdateResourcePoolRequest withDescription(String description) {
		setDescription(description);
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
	public UpdateResourcePoolRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
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
	public UpdateResourcePoolRequest withLevelCapScript(String levelCapScript) {
		setLevelCapScript(levelCapScript);
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
	public UpdateResourcePoolRequest withChangeExperienceTriggerScript(String changeExperienceTriggerScript) {
		setChangeExperienceTriggerScript(changeExperienceTriggerScript);
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
	public UpdateResourcePoolRequest withChangeExperienceDoneTriggerScript(String changeExperienceDoneTriggerScript) {
		setChangeExperienceDoneTriggerScript(changeExperienceDoneTriggerScript);
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
	public UpdateResourcePoolRequest withChangeLevelTriggerScript(String changeLevelTriggerScript) {
		setChangeLevelTriggerScript(changeLevelTriggerScript);
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
	public UpdateResourcePoolRequest withChangeLevelDoneTriggerScript(String changeLevelDoneTriggerScript) {
		setChangeLevelDoneTriggerScript(changeLevelDoneTriggerScript);
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
	public UpdateResourcePoolRequest withChangeLevelCapTriggerScript(String changeLevelCapTriggerScript) {
		setChangeLevelCapTriggerScript(changeLevelCapTriggerScript);
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
	public UpdateResourcePoolRequest withChangeLevelCapDoneTriggerScript(String changeLevelCapDoneTriggerScript) {
		setChangeLevelCapDoneTriggerScript(changeLevelCapDoneTriggerScript);
		return this;
	}

}