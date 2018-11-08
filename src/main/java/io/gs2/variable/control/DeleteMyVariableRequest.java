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
public class DeleteMyVariableRequest extends Gs2UserRequest<DeleteMyVariableRequest> {

	public static class Constant extends Gs2Variable.Constant {
		public static final String FUNCTION = "DeleteMyVariable";
	}

	/** 変数名 */
	private String variableName;


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
	public DeleteMyVariableRequest withVariableName(String variableName) {
		setVariableName(variableName);
		return this;
	}

}