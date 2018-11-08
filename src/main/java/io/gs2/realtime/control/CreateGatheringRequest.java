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
public class CreateGatheringRequest extends Gs2BasicRequest<CreateGatheringRequest> {

	public static class Constant extends Gs2Realtime.Constant {
		public static final String FUNCTION = "CreateGathering";
	}

	/** ギャザリングプールの名前を指定します。 */
	private String gatheringPoolName;

	/** ギャザリング名 */
	private String name;

	/** ギャザリングへの参加を許可するユーザIDリスト */
	private List<String> userIds;


	/**
	 * ギャザリングプールの名前を指定します。を取得
	 *
	 * @return ギャザリングプールの名前を指定します。
	 */
	public String getGatheringPoolName() {
		return gatheringPoolName;
	}

	/**
	 * ギャザリングプールの名前を指定します。を設定
	 *
	 * @param gatheringPoolName ギャザリングプールの名前を指定します。
	 */
	public void setGatheringPoolName(String gatheringPoolName) {
		this.gatheringPoolName = gatheringPoolName;
	}

	/**
	 * ギャザリングプールの名前を指定します。を設定
	 *
	 * @param gatheringPoolName ギャザリングプールの名前を指定します。
	 * @return this
	 */
	public CreateGatheringRequest withGatheringPoolName(String gatheringPoolName) {
		setGatheringPoolName(gatheringPoolName);
		return this;
	}

	/**
	 * ギャザリング名を取得
	 *
	 * @return ギャザリング名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ギャザリング名を設定
	 *
	 * @param name ギャザリング名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ギャザリング名を設定
	 *
	 * @param name ギャザリング名
	 * @return this
	 */
	public CreateGatheringRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * ギャザリングへの参加を許可するユーザIDリストを取得
	 *
	 * @return ギャザリングへの参加を許可するユーザIDリスト
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * ギャザリングへの参加を許可するユーザIDリストを設定
	 *
	 * @param userIds ギャザリングへの参加を許可するユーザIDリスト
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	/**
	 * ギャザリングへの参加を許可するユーザIDリストを設定
	 *
	 * @param userIds ギャザリングへの参加を許可するユーザIDリスト
	 * @return this
	 */
	public CreateGatheringRequest withUserIds(List<String> userIds) {
		setUserIds(userIds);
		return this;
	}

}