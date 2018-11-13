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

package io.gs2.gacha.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ガチャプール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GachaPool implements Serializable {

	/** ガチャプールGRN */
	private String gachaPoolId;

	/** オーナーID */
	private String ownerId;

	/** ガチャプール名 */
	private String name;

	/** 説明文 */
	private String description;

	/** 排出確率を公開するか */
	private String publicDrawRate;

	/** ガチャ一覧の取得を許すか */
	private String allowAccessGachaInfo;

	/** 抽選実行を制限するか */
	private String restrict;

	/** 排出判定時 に実行されるGS2-Script */
	private String drawPrizeTriggerScript;

	/** 排出判定完了時 に実行されるGS2-Script */
	private String drawPrizeDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ガチャプールGRNを取得
	 *
	 * @return ガチャプールGRN
	 */
	public String getGachaPoolId() {
		return gachaPoolId;
	}

	/**
	 * ガチャプールGRNを設定
	 *
	 * @param gachaPoolId ガチャプールGRN
	 */
	public void setGachaPoolId(String gachaPoolId) {
		this.gachaPoolId = gachaPoolId;
	}

	/**
	 * ガチャプールGRNを設定
	 *
	 * @param gachaPoolId ガチャプールGRN
	 * @return this
	 */
	public GachaPool withGachaPoolId(String gachaPoolId) {
		this.gachaPoolId = gachaPoolId;
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
	public GachaPool withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	/**
	 * ガチャプール名を取得
	 *
	 * @return ガチャプール名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ガチャプール名を設定
	 *
	 * @param name ガチャプール名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ガチャプール名を設定
	 *
	 * @param name ガチャプール名
	 * @return this
	 */
	public GachaPool withName(String name) {
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
	public GachaPool withDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * 排出確率を公開するかを取得
	 *
	 * @return 排出確率を公開するか
	 */
	public String getPublicDrawRate() {
		return publicDrawRate;
	}

	/**
	 * 排出確率を公開するかを設定
	 *
	 * @param publicDrawRate 排出確率を公開するか
	 */
	public void setPublicDrawRate(String publicDrawRate) {
		this.publicDrawRate = publicDrawRate;
	}

	/**
	 * 排出確率を公開するかを設定
	 *
	 * @param publicDrawRate 排出確率を公開するか
	 * @return this
	 */
	public GachaPool withPublicDrawRate(String publicDrawRate) {
		this.publicDrawRate = publicDrawRate;
		return this;
	}

	/**
	 * ガチャ一覧の取得を許すかを取得
	 *
	 * @return ガチャ一覧の取得を許すか
	 */
	public String getAllowAccessGachaInfo() {
		return allowAccessGachaInfo;
	}

	/**
	 * ガチャ一覧の取得を許すかを設定
	 *
	 * @param allowAccessGachaInfo ガチャ一覧の取得を許すか
	 */
	public void setAllowAccessGachaInfo(String allowAccessGachaInfo) {
		this.allowAccessGachaInfo = allowAccessGachaInfo;
	}

	/**
	 * ガチャ一覧の取得を許すかを設定
	 *
	 * @param allowAccessGachaInfo ガチャ一覧の取得を許すか
	 * @return this
	 */
	public GachaPool withAllowAccessGachaInfo(String allowAccessGachaInfo) {
		this.allowAccessGachaInfo = allowAccessGachaInfo;
		return this;
	}

	/**
	 * 抽選実行を制限するかを取得
	 *
	 * @return 抽選実行を制限するか
	 */
	public String getRestrict() {
		return restrict;
	}

	/**
	 * 抽選実行を制限するかを設定
	 *
	 * @param restrict 抽選実行を制限するか
	 */
	public void setRestrict(String restrict) {
		this.restrict = restrict;
	}

	/**
	 * 抽選実行を制限するかを設定
	 *
	 * @param restrict 抽選実行を制限するか
	 * @return this
	 */
	public GachaPool withRestrict(String restrict) {
		this.restrict = restrict;
		return this;
	}

	/**
	 * 排出判定時 に実行されるGS2-Scriptを取得
	 *
	 * @return 排出判定時 に実行されるGS2-Script
	 */
	public String getDrawPrizeTriggerScript() {
		return drawPrizeTriggerScript;
	}

	/**
	 * 排出判定時 に実行されるGS2-Scriptを設定
	 *
	 * @param drawPrizeTriggerScript 排出判定時 に実行されるGS2-Script
	 */
	public void setDrawPrizeTriggerScript(String drawPrizeTriggerScript) {
		this.drawPrizeTriggerScript = drawPrizeTriggerScript;
	}

	/**
	 * 排出判定時 に実行されるGS2-Scriptを設定
	 *
	 * @param drawPrizeTriggerScript 排出判定時 に実行されるGS2-Script
	 * @return this
	 */
	public GachaPool withDrawPrizeTriggerScript(String drawPrizeTriggerScript) {
		this.drawPrizeTriggerScript = drawPrizeTriggerScript;
		return this;
	}

	/**
	 * 排出判定完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 排出判定完了時 に実行されるGS2-Script
	 */
	public String getDrawPrizeDoneTriggerScript() {
		return drawPrizeDoneTriggerScript;
	}

	/**
	 * 排出判定完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param drawPrizeDoneTriggerScript 排出判定完了時 に実行されるGS2-Script
	 */
	public void setDrawPrizeDoneTriggerScript(String drawPrizeDoneTriggerScript) {
		this.drawPrizeDoneTriggerScript = drawPrizeDoneTriggerScript;
	}

	/**
	 * 排出判定完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param drawPrizeDoneTriggerScript 排出判定完了時 に実行されるGS2-Script
	 * @return this
	 */
	public GachaPool withDrawPrizeDoneTriggerScript(String drawPrizeDoneTriggerScript) {
		this.drawPrizeDoneTriggerScript = drawPrizeDoneTriggerScript;
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
	public GachaPool withCreateAt(Integer createAt) {
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
	public GachaPool withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("gachaPoolId", this.getGachaPoolId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("publicDrawRate", this.getPublicDrawRate())
            .put("allowAccessGachaInfo", this.getAllowAccessGachaInfo())
            .put("restrict", this.getRestrict())
            .put("drawPrizeTriggerScript", this.getDrawPrizeTriggerScript())
            .put("drawPrizeDoneTriggerScript", this.getDrawPrizeDoneTriggerScript())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        return body;
    }
}