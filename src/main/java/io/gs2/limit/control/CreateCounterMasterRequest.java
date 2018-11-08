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

package io.gs2.limit.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.limit.Gs2Limit;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateCounterMasterRequest extends Gs2BasicRequest<CreateCounterMasterRequest> {

	public static class Constant extends Gs2Limit.Constant {
		public static final String FUNCTION = "CreateCounterMaster";
	}

	/** 回数制限の名前を指定します。 */
	private String limitName;

	/** カウンターマスター名 */
	private String name;

	/** カウンターの最大値 */
	private Integer max;

	/** リセット周期 */
	private String resetType;

	/** 期間内の取得量をリセットする日にち */
	private Integer resetDayOfMonth;

	/** 期間内の取得量をリセットする曜日 */
	private String resetDayOfWeek;

	/** 期間内の取得量をリセットする時 */
	private Integer resetHour;


	/**
	 * 回数制限の名前を指定します。を取得
	 *
	 * @return 回数制限の名前を指定します。
	 */
	public String getLimitName() {
		return limitName;
	}

	/**
	 * 回数制限の名前を指定します。を設定
	 *
	 * @param limitName 回数制限の名前を指定します。
	 */
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	/**
	 * 回数制限の名前を指定します。を設定
	 *
	 * @param limitName 回数制限の名前を指定します。
	 * @return this
	 */
	public CreateCounterMasterRequest withLimitName(String limitName) {
		setLimitName(limitName);
		return this;
	}

	/**
	 * カウンターマスター名を取得
	 *
	 * @return カウンターマスター名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カウンターマスター名を設定
	 *
	 * @param name カウンターマスター名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カウンターマスター名を設定
	 *
	 * @param name カウンターマスター名
	 * @return this
	 */
	public CreateCounterMasterRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * カウンターの最大値を取得
	 *
	 * @return カウンターの最大値
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * カウンターの最大値を設定
	 *
	 * @param max カウンターの最大値
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * カウンターの最大値を設定
	 *
	 * @param max カウンターの最大値
	 * @return this
	 */
	public CreateCounterMasterRequest withMax(Integer max) {
		setMax(max);
		return this;
	}

	/**
	 * リセット周期を取得
	 *
	 * @return リセット周期
	 */
	public String getResetType() {
		return resetType;
	}

	/**
	 * リセット周期を設定
	 *
	 * @param resetType リセット周期
	 */
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	/**
	 * リセット周期を設定
	 *
	 * @param resetType リセット周期
	 * @return this
	 */
	public CreateCounterMasterRequest withResetType(String resetType) {
		setResetType(resetType);
		return this;
	}

	/**
	 * 期間内の取得量をリセットする日にちを取得
	 *
	 * @return 期間内の取得量をリセットする日にち
	 */
	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	/**
	 * 期間内の取得量をリセットする日にちを設定
	 *
	 * @param resetDayOfMonth 期間内の取得量をリセットする日にち
	 */
	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	/**
	 * 期間内の取得量をリセットする日にちを設定
	 *
	 * @param resetDayOfMonth 期間内の取得量をリセットする日にち
	 * @return this
	 */
	public CreateCounterMasterRequest withResetDayOfMonth(Integer resetDayOfMonth) {
		setResetDayOfMonth(resetDayOfMonth);
		return this;
	}

	/**
	 * 期間内の取得量をリセットする曜日を取得
	 *
	 * @return 期間内の取得量をリセットする曜日
	 */
	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	/**
	 * 期間内の取得量をリセットする曜日を設定
	 *
	 * @param resetDayOfWeek 期間内の取得量をリセットする曜日
	 */
	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	/**
	 * 期間内の取得量をリセットする曜日を設定
	 *
	 * @param resetDayOfWeek 期間内の取得量をリセットする曜日
	 * @return this
	 */
	public CreateCounterMasterRequest withResetDayOfWeek(String resetDayOfWeek) {
		setResetDayOfWeek(resetDayOfWeek);
		return this;
	}

	/**
	 * 期間内の取得量をリセットする時を取得
	 *
	 * @return 期間内の取得量をリセットする時
	 */
	public Integer getResetHour() {
		return resetHour;
	}

	/**
	 * 期間内の取得量をリセットする時を設定
	 *
	 * @param resetHour 期間内の取得量をリセットする時
	 */
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	/**
	 * 期間内の取得量をリセットする時を設定
	 *
	 * @param resetHour 期間内の取得量をリセットする時
	 * @return this
	 */
	public CreateCounterMasterRequest withResetHour(Integer resetHour) {
		setResetHour(resetHour);
		return this;
	}

}