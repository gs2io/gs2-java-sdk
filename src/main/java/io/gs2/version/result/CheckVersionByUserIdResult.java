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

package io.gs2.version.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.version.model.*;

/**
 * バージョンチェック のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CheckVersionByUserIdResult implements IResult, Serializable {
	/** プロジェクトトークン */
	private String projectToken;
	/** バージョンの検証結果のリスト */
	private List<Status> warnings;
	/** バージョンの検証結果のリスト */
	private List<Status> errors;

	/**
	 * プロジェクトトークンを取得
	 *
	 * @return バージョンチェック
	 */
	public String getProjectToken() {
		return projectToken;
	}

	/**
	 * プロジェクトトークンを設定
	 *
	 * @param projectToken バージョンチェック
	 */
	public void setProjectToken(String projectToken) {
		this.projectToken = projectToken;
	}

	/**
	 * バージョンの検証結果のリストを取得
	 *
	 * @return バージョンチェック
	 */
	public List<Status> getWarnings() {
		return warnings;
	}

	/**
	 * バージョンの検証結果のリストを設定
	 *
	 * @param warnings バージョンチェック
	 */
	public void setWarnings(List<Status> warnings) {
		this.warnings = warnings;
	}

	/**
	 * バージョンの検証結果のリストを取得
	 *
	 * @return バージョンチェック
	 */
	public List<Status> getErrors() {
		return errors;
	}

	/**
	 * バージョンの検証結果のリストを設定
	 *
	 * @param errors バージョンチェック
	 */
	public void setErrors(List<Status> errors) {
		this.errors = errors;
	}
}