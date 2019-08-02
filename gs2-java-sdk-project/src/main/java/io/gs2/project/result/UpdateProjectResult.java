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

package io.gs2.project.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.project.model.*;

/**
 * プロジェクトを更新 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateProjectResult implements IResult, Serializable {
	/** 更新したプロジェクト */
	private Project item;

	/**
	 * 更新したプロジェクトを取得
	 *
	 * @return プロジェクトを更新
	 */
	public Project getItem() {
		return item;
	}

	/**
	 * 更新したプロジェクトを設定
	 *
	 * @param item プロジェクトを更新
	 */
	public void setItem(Project item) {
		this.item = item;
	}
}