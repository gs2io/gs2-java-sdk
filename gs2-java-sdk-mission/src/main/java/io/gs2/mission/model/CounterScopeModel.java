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

package io.gs2.mission.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * カウンターのリセットタイミング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CounterScopeModel implements IModel, Serializable {
	/** リセットタイミング */
	protected String resetType;

	/**
	 * リセットタイミングを取得
	 *
	 * @return リセットタイミング
	 */
	public String getResetType() {
		return resetType;
	}

	/**
	 * リセットタイミングを設定
	 *
	 * @param resetType リセットタイミング
	 */
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	/**
	 * リセットタイミングを設定
	 *
	 * @param resetType リセットタイミング
	 * @return this
	 */
	public CounterScopeModel withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	/** リセットをする日にち */
	protected Integer resetDayOfMonth;

	/**
	 * リセットをする日にちを取得
	 *
	 * @return リセットをする日にち
	 */
	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	/**
	 * リセットをする日にちを設定
	 *
	 * @param resetDayOfMonth リセットをする日にち
	 */
	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	/**
	 * リセットをする日にちを設定
	 *
	 * @param resetDayOfMonth リセットをする日にち
	 * @return this
	 */
	public CounterScopeModel withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}
	/** リセットする曜日 */
	protected String resetDayOfWeek;

	/**
	 * リセットする曜日を取得
	 *
	 * @return リセットする曜日
	 */
	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	/**
	 * リセットする曜日を設定
	 *
	 * @param resetDayOfWeek リセットする曜日
	 */
	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	/**
	 * リセットする曜日を設定
	 *
	 * @param resetDayOfWeek リセットする曜日
	 * @return this
	 */
	public CounterScopeModel withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}
	/** リセット時刻 */
	protected Integer resetHour;

	/**
	 * リセット時刻を取得
	 *
	 * @return リセット時刻
	 */
	public Integer getResetHour() {
		return resetHour;
	}

	/**
	 * リセット時刻を設定
	 *
	 * @param resetHour リセット時刻
	 */
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	/**
	 * リセット時刻を設定
	 *
	 * @param resetHour リセット時刻
	 * @return this
	 */
	public CounterScopeModel withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("resetType", this.getResetType())
            .put("resetDayOfMonth", this.getResetDayOfMonth())
            .put("resetDayOfWeek", this.getResetDayOfWeek())
            .put("resetHour", this.getResetHour());
        return body_;
    }
}