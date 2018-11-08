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

package io.gs2.level.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.level.model.*;
import io.gs2.level.Gs2Level;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateLevelTableMasterRequest extends Gs2BasicRequest<CreateLevelTableMasterRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "CreateLevelTableMaster";
	}

	/** リソースプール */
	private String resourcePoolName;

	/** レベルテーブル名 */
	private String name;


	/**
	 * リソースプールを取得
	 *
	 * @return リソースプール
	 */
	public String getResourcePoolName() {
		return resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 */
	public void setResourcePoolName(String resourcePoolName) {
		this.resourcePoolName = resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 * @return this
	 */
	public CreateLevelTableMasterRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
		return this;
	}

	/**
	 * レベルテーブル名を取得
	 *
	 * @return レベルテーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * レベルテーブル名を設定
	 *
	 * @param name レベルテーブル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * レベルテーブル名を設定
	 *
	 * @param name レベルテーブル名
	 * @return this
	 */
	public CreateLevelTableMasterRequest withName(String name) {
		setName(name);
		return this;
	}

}