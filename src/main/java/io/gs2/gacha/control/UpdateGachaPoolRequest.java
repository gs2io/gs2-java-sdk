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
public class UpdateGachaPoolRequest extends Gs2BasicRequest<UpdateGachaPoolRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "UpdateGachaPool";
	}

	/** ガチャプールの名前を指定します。 */
	private String gachaPoolName;

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
	 * ガチャプールの名前を指定します。を取得
	 *
	 * @return ガチャプールの名前を指定します。
	 */
	public String getGachaPoolName() {
		return gachaPoolName;
	}

	/**
	 * ガチャプールの名前を指定します。を設定
	 *
	 * @param gachaPoolName ガチャプールの名前を指定します。
	 */
	public void setGachaPoolName(String gachaPoolName) {
		this.gachaPoolName = gachaPoolName;
	}

	/**
	 * ガチャプールの名前を指定します。を設定
	 *
	 * @param gachaPoolName ガチャプールの名前を指定します。
	 * @return this
	 */
	public UpdateGachaPoolRequest withGachaPoolName(String gachaPoolName) {
		setGachaPoolName(gachaPoolName);
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
	public UpdateGachaPoolRequest withDescription(String description) {
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
	public UpdateGachaPoolRequest withPublicDrawRate(String publicDrawRate) {
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
	public UpdateGachaPoolRequest withAllowAccessGachaInfo(String allowAccessGachaInfo) {
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
	public UpdateGachaPoolRequest withRestrict(String restrict) {
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
	public UpdateGachaPoolRequest withDrawPrizeTriggerScript(String drawPrizeTriggerScript) {
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
	public UpdateGachaPoolRequest withDrawPrizeDoneTriggerScript(String drawPrizeDoneTriggerScript) {
		setDrawPrizeDoneTriggerScript(drawPrizeDoneTriggerScript);
		return this;
	}

}