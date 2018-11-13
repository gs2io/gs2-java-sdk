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

package io.gs2.realtime.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.realtime.model.*;
import io.gs2.realtime.Gs2Realtime;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGatheringPoolRequest extends Gs2BasicRequest<CreateGatheringPoolRequest> {

	public static class Constant extends Gs2Realtime.Constant {
		public static final String FUNCTION = "CreateGatheringPool";
	}

	/** ギャザリングプールの名前 */
	private String name;

	/** ギャザリングプールの説明 */
	private String description;


	/**
	 * ギャザリングプールの名前を取得
	 *
	 * @return ギャザリングプールの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * ギャザリングプールの名前を設定
	 *
	 * @param name ギャザリングプールの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ギャザリングプールの名前を設定
	 *
	 * @param name ギャザリングプールの名前
	 * @return this
	 */
	public CreateGatheringPoolRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * ギャザリングプールの説明を取得
	 *
	 * @return ギャザリングプールの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ギャザリングプールの説明を設定
	 *
	 * @param description ギャザリングプールの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ギャザリングプールの説明を設定
	 *
	 * @param description ギャザリングプールの説明
	 * @return this
	 */
	public CreateGatheringPoolRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

}