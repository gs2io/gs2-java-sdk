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
public class CreateShowcaseRequest extends Gs2BasicRequest<CreateShowcaseRequest> {

	public static class Constant extends Gs2Showcase.Constant {
		public static final String FUNCTION = "CreateShowcase";
	}

	/** ショーケース名 */
	private String name;

	/** 説明文 */
	private String description;

	/** 公開許可判定 に実行されるGS2-Script */
	private String releaseConditionTriggerScript;

	/** 購入直前 に実行されるGS2-Script */
	private String buyTriggerScript;


	/**
	 * ショーケース名を取得
	 *
	 * @return ショーケース名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ショーケース名を設定
	 *
	 * @param name ショーケース名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ショーケース名を設定
	 *
	 * @param name ショーケース名
	 * @return this
	 */
	public CreateShowcaseRequest withName(String name) {
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
	public CreateShowcaseRequest withDescription(String description) {
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
	public CreateShowcaseRequest withReleaseConditionTriggerScript(String releaseConditionTriggerScript) {
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
	public CreateShowcaseRequest withBuyTriggerScript(String buyTriggerScript) {
		setBuyTriggerScript(buyTriggerScript);
		return this;
	}

}