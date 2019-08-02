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
 * プロジェクトトークンを発行します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetProjectTokenResult implements IResult, Serializable {
	/** サインインしたプロジェクト */
	private Project item;
	/** オーナーID */
	private String ownerId;
	/** プロジェクトトークン */
	private String projectToken;

	/**
	 * サインインしたプロジェクトを取得
	 *
	 * @return プロジェクトトークンを発行します
	 */
	public Project getItem() {
		return item;
	}

	/**
	 * サインインしたプロジェクトを設定
	 *
	 * @param item プロジェクトトークンを発行します
	 */
	public void setItem(Project item) {
		this.item = item;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return プロジェクトトークンを発行します
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId プロジェクトトークンを発行します
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * プロジェクトトークンを取得
	 *
	 * @return プロジェクトトークンを発行します
	 */
	public String getProjectToken() {
		return projectToken;
	}

	/**
	 * プロジェクトトークンを設定
	 *
	 * @param projectToken プロジェクトトークンを発行します
	 */
	public void setProjectToken(String projectToken) {
		this.projectToken = projectToken;
	}
}