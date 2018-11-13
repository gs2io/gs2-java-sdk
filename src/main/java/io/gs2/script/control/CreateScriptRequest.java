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

package io.gs2.script.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.script.Gs2Script;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateScriptRequest extends Gs2BasicRequest<CreateScriptRequest> {

	public static class Constant extends Gs2Script.Constant {
		public static final String FUNCTION = "CreateScript";
	}

	/** スクリプトの名前 */
	private String name;

	/** 説明文 */
	private String description;

	/** Luaスクリプト */
	private String script;


	/**
	 * スクリプトの名前を取得
	 *
	 * @return スクリプトの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * スクリプトの名前を設定
	 *
	 * @param name スクリプトの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スクリプトの名前を設定
	 *
	 * @param name スクリプトの名前
	 * @return this
	 */
	public CreateScriptRequest withName(String name) {
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
	public CreateScriptRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * Luaスクリプトを取得
	 *
	 * @return Luaスクリプト
	 */
	public String getScript() {
		return script;
	}

	/**
	 * Luaスクリプトを設定
	 *
	 * @param script Luaスクリプト
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * Luaスクリプトを設定
	 *
	 * @param script Luaスクリプト
	 * @return this
	 */
	public CreateScriptRequest withScript(String script) {
		setScript(script);
		return this;
	}

}