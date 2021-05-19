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
 * バージョンマスターを新規作成 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateVersionModelMasterResult implements IResult, Serializable {
	/** 作成したバージョンマスター */
	private VersionModelMaster item;

	/**
	 * 作成したバージョンマスターを取得
	 *
	 * @return バージョンマスターを新規作成
	 */
	public VersionModelMaster getItem() {
		return item;
	}

	/**
	 * 作成したバージョンマスターを設定
	 *
	 * @param item バージョンマスターを新規作成
	 */
	public void setItem(VersionModelMaster item) {
		this.item = item;
	}
}