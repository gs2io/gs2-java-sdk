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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.variable.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetVariableResult {

	/** 値 */
	private String value;

	/** 有効期限(エポック秒) */
	private Integer expire;


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
	 * 有効期限(エポック秒)を取得
	 *
	 * @return 有効期限(エポック秒)
	 */
	public Integer getExpire() {
		return expire;
	}

	/**
	 * 有効期限(エポック秒)を設定
	 *
	 * @param expire 有効期限(エポック秒)
	 */
	public void setExpire(Integer expire) {
		this.expire = expire;
	}

}