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
public class EncryptRequest extends Gs2BasicRequest<EncryptRequest> {

	public static class Constant extends Gs2Key.Constant {
		public static final String FUNCTION = "Encrypt";
	}

	/** 暗号鍵の名前を指定します。 */
	private String keyName;

	/** 暗号化するデータ */
	private String data;


	/**
	 * 暗号鍵の名前を指定します。を取得
	 *
	 * @return 暗号鍵の名前を指定します。
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * 暗号鍵の名前を指定します。を設定
	 *
	 * @param keyName 暗号鍵の名前を指定します。
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * 暗号鍵の名前を指定します。を設定
	 *
	 * @param keyName 暗号鍵の名前を指定します。
	 * @return this
	 */
	public EncryptRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}

	/**
	 * 暗号化するデータを取得
	 *
	 * @return 暗号化するデータ
	 */
	public String getData() {
		return data;
	}

	/**
	 * 暗号化するデータを設定
	 *
	 * @param data 暗号化するデータ
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 暗号化するデータを設定
	 *
	 * @param data 暗号化するデータ
	 * @return this
	 */
	public EncryptRequest withData(String data) {
		setData(data);
		return this;
	}

}