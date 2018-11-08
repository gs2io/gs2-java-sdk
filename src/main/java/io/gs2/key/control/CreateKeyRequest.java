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

package io.gs2.key.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.key.Gs2Key;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateKeyRequest extends Gs2BasicRequest<CreateKeyRequest> {

	public static class Constant extends Gs2Key.Constant {
		public static final String FUNCTION = "CreateKey";
	}

	/** 暗号鍵の名前 */
	private String name;


	/**
	 * 暗号鍵の名前を取得
	 *
	 * @return 暗号鍵の名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 暗号鍵の名前を設定
	 *
	 * @param name 暗号鍵の名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 暗号鍵の名前を設定
	 *
	 * @param name 暗号鍵の名前
	 * @return this
	 */
	public CreateKeyRequest withName(String name) {
		setName(name);
		return this;
	}

}