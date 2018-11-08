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

package io.gs2.gold.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gold.model.*;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetLatestGainRequest extends Gs2BasicRequest<SetLatestGainRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "SetLatestGain";
	}

	/** ゴールドプールの名前 */
	private String goldPoolName;

	/** ゴールドの名前 */
	private String goldName;

	/** ウォレット所有者のユーザID */
	private String userId;

	/** 期間内の取得量 */
	private Long value;


	/**
	 * ゴールドプールの名前を取得
	 *
	 * @return ゴールドプールの名前
	 */
	public String getGoldPoolName() {
		return goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 */
	public void setGoldPoolName(String goldPoolName) {
		this.goldPoolName = goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 * @return this
	 */
	public SetLatestGainRequest withGoldPoolName(String goldPoolName) {
		setGoldPoolName(goldPoolName);
		return this;
	}

	/**
	 * ゴールドの名前を取得
	 *
	 * @return ゴールドの名前
	 */
	public String getGoldName() {
		return goldName;
	}

	/**
	 * ゴールドの名前を設定
	 *
	 * @param goldName ゴールドの名前
	 */
	public void setGoldName(String goldName) {
		this.goldName = goldName;
	}

	/**
	 * ゴールドの名前を設定
	 *
	 * @param goldName ゴールドの名前
	 * @return this
	 */
	public SetLatestGainRequest withGoldName(String goldName) {
		setGoldName(goldName);
		return this;
	}

	/**
	 * ウォレット所有者のユーザIDを取得
	 *
	 * @return ウォレット所有者のユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ウォレット所有者のユーザIDを設定
	 *
	 * @param userId ウォレット所有者のユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ウォレット所有者のユーザIDを設定
	 *
	 * @param userId ウォレット所有者のユーザID
	 * @return this
	 */
	public SetLatestGainRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 期間内の取得量を取得
	 *
	 * @return 期間内の取得量
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * 期間内の取得量を設定
	 *
	 * @param value 期間内の取得量
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * 期間内の取得量を設定
	 *
	 * @param value 期間内の取得量
	 * @return this
	 */
	public SetLatestGainRequest withValue(Long value) {
		setValue(value);
		return this;
	}

}