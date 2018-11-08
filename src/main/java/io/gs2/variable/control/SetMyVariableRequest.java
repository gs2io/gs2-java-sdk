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

package io.gs2.variable.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.variable.Gs2Variable;
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetMyVariableRequest extends Gs2UserRequest<SetMyVariableRequest> {

	public static class Constant extends Gs2Variable.Constant {
		public static final String FUNCTION = "SetMyVariable";
	}

	/** 変数名 */
	private String variableName;

	/** 値 */
	private String value;

	/** 変数の有効期間(秒) */
	private Integer ttl;


	/**
	 * 変数名を取得
	 *
	 * @return 変数名
	 */
	public String getVariableName() {
		return variableName;
	}

	/**
	 * 変数名を設定
	 *
	 * @param variableName 変数名
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	/**
	 * 変数名を設定
	 *
	 * @param variableName 変数名
	 * @return this
	 */
	public SetMyVariableRequest withVariableName(String variableName) {
		setVariableName(variableName);
		return this;
	}

	/**
	 * 値を取得
	 *
	 * @return 値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 値を設定
	 *
	 * @param value 値
	 * @return this
	 */
	public SetMyVariableRequest withValue(String value) {
		setValue(value);
		return this;
	}

	/**
	 * 変数の有効期間(秒)を取得
	 *
	 * @return 変数の有効期間(秒)
	 */
	public Integer getTtl() {
		return ttl;
	}

	/**
	 * 変数の有効期間(秒)を設定
	 *
	 * @param ttl 変数の有効期間(秒)
	 */
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

	/**
	 * 変数の有効期間(秒)を設定
	 *
	 * @param ttl 変数の有効期間(秒)
	 * @return this
	 */
	public SetMyVariableRequest withTtl(Integer ttl) {
		setTtl(ttl);
		return this;
	}

}