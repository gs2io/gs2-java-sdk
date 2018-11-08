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
public class UpdateGachaMasterRequest extends Gs2BasicRequest<UpdateGachaMasterRequest> {

	public static class Constant extends Gs2Gacha.Constant {
		public static final String FUNCTION = "UpdateGachaMaster";
	}

	/** ガチャプールの名前を指定します。 */
	private String gachaPoolName;

	/** ガチャの名前を指定します。 */
	private String gachaName;

	/** メタデータ */
	private String meta;

	/** 排出確率名リスト */
	private List<String> prizeTableNames;

	/** 景品の排出処理に使用する GS2-JobQueue */
	private String prizeJobQueueName;

	/** 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script */
	private String prizeJobQueueScriptName;


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
	public UpdateGachaMasterRequest withGachaPoolName(String gachaPoolName) {
		setGachaPoolName(gachaPoolName);
		return this;
	}

	/**
	 * ガチャの名前を指定します。を取得
	 *
	 * @return ガチャの名前を指定します。
	 */
	public String getGachaName() {
		return gachaName;
	}

	/**
	 * ガチャの名前を指定します。を設定
	 *
	 * @param gachaName ガチャの名前を指定します。
	 */
	public void setGachaName(String gachaName) {
		this.gachaName = gachaName;
	}

	/**
	 * ガチャの名前を指定します。を設定
	 *
	 * @param gachaName ガチャの名前を指定します。
	 * @return this
	 */
	public UpdateGachaMasterRequest withGachaName(String gachaName) {
		setGachaName(gachaName);
		return this;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 * @return this
	 */
	public UpdateGachaMasterRequest withMeta(String meta) {
		setMeta(meta);
		return this;
	}

	/**
	 * 排出確率名リストを取得
	 *
	 * @return 排出確率名リスト
	 */
	public List<String> getPrizeTableNames() {
		return prizeTableNames;
	}

	/**
	 * 排出確率名リストを設定
	 *
	 * @param prizeTableNames 排出確率名リスト
	 */
	public void setPrizeTableNames(List<String> prizeTableNames) {
		this.prizeTableNames = prizeTableNames;
	}

	/**
	 * 排出確率名リストを設定
	 *
	 * @param prizeTableNames 排出確率名リスト
	 * @return this
	 */
	public UpdateGachaMasterRequest withPrizeTableNames(List<String> prizeTableNames) {
		setPrizeTableNames(prizeTableNames);
		return this;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueueを取得
	 *
	 * @return 景品の排出処理に使用する GS2-JobQueue
	 */
	public String getPrizeJobQueueName() {
		return prizeJobQueueName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueueを設定
	 *
	 * @param prizeJobQueueName 景品の排出処理に使用する GS2-JobQueue
	 */
	public void setPrizeJobQueueName(String prizeJobQueueName) {
		this.prizeJobQueueName = prizeJobQueueName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueueを設定
	 *
	 * @param prizeJobQueueName 景品の排出処理に使用する GS2-JobQueue
	 * @return this
	 */
	public UpdateGachaMasterRequest withPrizeJobQueueName(String prizeJobQueueName) {
		setPrizeJobQueueName(prizeJobQueueName);
		return this;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Scriptを取得
	 *
	 * @return 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script
	 */
	public String getPrizeJobQueueScriptName() {
		return prizeJobQueueScriptName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Scriptを設定
	 *
	 * @param prizeJobQueueScriptName 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script
	 */
	public void setPrizeJobQueueScriptName(String prizeJobQueueScriptName) {
		this.prizeJobQueueScriptName = prizeJobQueueScriptName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Scriptを設定
	 *
	 * @param prizeJobQueueScriptName 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script
	 * @return this
	 */
	public UpdateGachaMasterRequest withPrizeJobQueueScriptName(String prizeJobQueueScriptName) {
		setPrizeJobQueueScriptName(prizeJobQueueScriptName);
		return this;
	}

}