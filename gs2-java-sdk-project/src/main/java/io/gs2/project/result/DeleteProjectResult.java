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
 * プロジェクトを削除 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeleteProjectResult implements IResult, Serializable {
	/** 削除したプロジェクト */
	private Project item;

	/**
	 * 削除したプロジェクトを取得
	 *
	 * @return プロジェクトを削除
	 */
	public Project getItem() {
		return item;
	}

	/**
	 * 削除したプロジェクトを設定
	 *
	 * @param item プロジェクトを削除
	 */
	public void setItem(Project item) {
		this.item = item;
	}
}