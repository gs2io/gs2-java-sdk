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
package io.gs2.model;

import java.util.Objects;

/**
 * リージョン情報
 * 
 * @author Game Server Services, Inc.
 *
 */
public enum Region {
	/** アジアパシフィック北東1(東京) */
	AP_NORTHEAST_1("ap-northeast-1");
	
	/** リージョン名 */
	String name;
	
	/**
	 * コンストラクタ。
	 * 
	 * @param name リージョン名
	 */
	Region(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Region prettyValueOf(String value) {
		for(Region region : Region.values()) {
			if(Objects.equals(region.name, value)) {
				return region;
			}
		}
		return AP_NORTHEAST_1;
	}
}
