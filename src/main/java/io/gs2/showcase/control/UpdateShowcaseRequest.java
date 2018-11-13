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

package io.gs2.showcase.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.showcase.model.*;
import io.gs2.showcase.Gs2Showcase;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateShowcaseRequest extends Gs2BasicRequest<UpdateShowcaseRequest> {

	public static class Constant extends Gs2Showcase.Constant {
		public static final String FUNCTION = "UpdateShowcase";
	}

	/** ショーケースの名前 */
	private String showcaseName;

	/** 説明文 */
	private String description;

	/** 公開許可判定 に実行されるGS2-Script */
	private String releaseConditionTriggerScript;

	/** 購入直前 に実行されるGS2-Script */
	private String buyTriggerScript;


	/**
	 * ショーケースの名前を取得
	 *
	 * @return ショーケースの名前
	 */
	public String getShowcaseName() {
		return showcaseName;
	}

	/**
	 * ショーケースの名前を設定
	 *
	 * @param showcaseName ショーケースの名前
	 */
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}

	/**
	 * ショーケースの名前を設定
	 *
	 * @param showcaseName ショーケースの名前
	 * @return this
	 */
	public UpdateShowcaseRequest withShowcaseName(String showcaseName) {
		setShowcaseName(showcaseName);
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
	public UpdateShowcaseRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Scriptを取得
	 *
	 * @return 公開許可判定 に実行されるGS2-Script
	 */
	public String getReleaseConditionTriggerScript() {
		return releaseConditionTriggerScript;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Scriptを設定
	 *
	 * @param releaseConditionTriggerScript 公開許可判定 に実行されるGS2-Script
	 */
	public void setReleaseConditionTriggerScript(String releaseConditionTriggerScript) {
		this.releaseConditionTriggerScript = releaseConditionTriggerScript;
	}

	/**
	 * 公開許可判定 に実行されるGS2-Scriptを設定
	 *
	 * @param releaseConditionTriggerScript 公開許可判定 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateShowcaseRequest withReleaseConditionTriggerScript(String releaseConditionTriggerScript) {
		setReleaseConditionTriggerScript(releaseConditionTriggerScript);
		return this;
	}

	/**
	 * 購入直前 に実行されるGS2-Scriptを取得
	 *
	 * @return 購入直前 に実行されるGS2-Script
	 */
	public String getBuyTriggerScript() {
		return buyTriggerScript;
	}

	/**
	 * 購入直前 に実行されるGS2-Scriptを設定
	 *
	 * @param buyTriggerScript 購入直前 に実行されるGS2-Script
	 */
	public void setBuyTriggerScript(String buyTriggerScript) {
		this.buyTriggerScript = buyTriggerScript;
	}

	/**
	 * 購入直前 に実行されるGS2-Scriptを設定
	 *
	 * @param buyTriggerScript 購入直前 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateShowcaseRequest withBuyTriggerScript(String buyTriggerScript) {
		setBuyTriggerScript(buyTriggerScript);
		return this;
	}

}