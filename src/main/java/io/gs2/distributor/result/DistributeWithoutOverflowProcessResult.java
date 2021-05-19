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

package io.gs2.distributor.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.distributor.model.*;

/**
 * 所持品を配布する(溢れた際の救済処置無し) のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistributeWithoutOverflowProcessResult implements IResult, Serializable {
	/** 処理した DistributeResource */
	private DistributeResource distributeResource;
	/** レスポンス内容 */
	private String result;

	/**
	 * 処理した DistributeResourceを取得
	 *
	 * @return 所持品を配布する(溢れた際の救済処置無し)
	 */
	public DistributeResource getDistributeResource() {
		return distributeResource;
	}

	/**
	 * 処理した DistributeResourceを設定
	 *
	 * @param distributeResource 所持品を配布する(溢れた際の救済処置無し)
	 */
	public void setDistributeResource(DistributeResource distributeResource) {
		this.distributeResource = distributeResource;
	}

	/**
	 * レスポンス内容を取得
	 *
	 * @return 所持品を配布する(溢れた際の救済処置無し)
	 */
	public String getResult() {
		return result;
	}

	/**
	 * レスポンス内容を設定
	 *
	 * @param result 所持品を配布する(溢れた際の救済処置無し)
	 */
	public void setResult(String result) {
		this.result = result;
	}
}