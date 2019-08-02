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

package io.gs2.mission.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.mission.model.*;

/**
 * ミッションタスクマスターを新規作成 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateMissionTaskModelMasterResult implements IResult, Serializable {
	/** 作成したミッションタスクマスター */
	private MissionTaskModelMaster item;

	/**
	 * 作成したミッションタスクマスターを取得
	 *
	 * @return ミッションタスクマスターを新規作成
	 */
	public MissionTaskModelMaster getItem() {
		return item;
	}

	/**
	 * 作成したミッションタスクマスターを設定
	 *
	 * @param item ミッションタスクマスターを新規作成
	 */
	public void setItem(MissionTaskModelMaster item) {
		this.item = item;
	}
}