/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.gs2.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SignUtil {

	public static byte[] sign(String clientSecret, String module, String function, Long timestamp) {
		return sign(Base64.getDecoder().decode(clientSecret.getBytes()), module + ":" + function + ":" + timestamp);
	}

	public static byte[] sign(byte[] clientSecret, String body) {
		try {
			SecretKey key = new SecretKeySpec(clientSecret, "HmacSHA256");
			Mac m = Mac.getInstance("HmacSHA256");
			m.init(key);
			m.update(body.getBytes());
			return m.doFinal();
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
