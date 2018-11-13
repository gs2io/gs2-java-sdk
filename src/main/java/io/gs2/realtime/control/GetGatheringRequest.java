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
public class GetGatheringRequest extends Gs2BasicRequest<GetGatheringRequest> {

	public static class Constant extends Gs2Realtime.Constant {
		public static final String FUNCTION = "GetGathering";
	}

	/** ギャザリングプールの名前を指定します。 */
	private String gatheringPoolName;

	/** ギャザリングの名前を指定します。 */
	private String gatheringName;


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
	public GetGatheringRequest withGatheringPoolName(String gatheringPoolName) {
		setGatheringPoolName(gatheringPoolName);
		return this;
	}

	/**
	 * ギャザリングの名前を指定します。を取得
	 *
	 * @return ギャザリングの名前を指定します。
	 */
	public String getGatheringName() {
		return gatheringName;
	}

	/**
	 * ギャザリングの名前を指定します。を設定
	 *
	 * @param gatheringName ギャザリングの名前を指定します。
	 */
	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}

	/**
	 * ギャザリングの名前を指定します。を設定
	 *
	 * @param gatheringName ギャザリングの名前を指定します。
	 * @return this
	 */
	public GetGatheringRequest withGatheringName(String gatheringName) {
		setGatheringName(gatheringName);
		return this;
	}

}