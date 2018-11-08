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

package io.gs2.gacha.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gacha.model.*;
import io.gs2.gacha.Gs2Gacha;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGachaPoolRequest extends Gs2BasicRequest<CreateGachaPoolRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "CreateGachaPool";
	}

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
	public CreateGachaPoolRequest withName(String name) {
		setName(name);
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
	public CreateGachaPoolRequest withDescription(String description) {
		setDescription(description);
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
	public CreateGachaPoolRequest withPublicDrawRate(String publicDrawRate) {
		setPublicDrawRate(publicDrawRate);
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
	public CreateGachaPoolRequest withAllowAccessGachaInfo(String allowAccessGachaInfo) {
		setAllowAccessGachaInfo(allowAccessGachaInfo);
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
	public CreateGachaPoolRequest withRestrict(String restrict) {
		setRestrict(restrict);
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
	public CreateGachaPoolRequest withDrawPrizeTriggerScript(String drawPrizeTriggerScript) {
		setDrawPrizeTriggerScript(drawPrizeTriggerScript);
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
	public CreateGachaPoolRequest withDrawPrizeDoneTriggerScript(String drawPrizeDoneTriggerScript) {
		setDrawPrizeDoneTriggerScript(drawPrizeDoneTriggerScript);
		return this;
	}

}