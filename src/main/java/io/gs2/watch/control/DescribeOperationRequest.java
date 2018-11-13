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

package io.gs2.watch.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.watch.model.*;
import io.gs2.watch.Gs2Watch;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeOperationRequest extends Gs2BasicRequest<DescribeOperationRequest> {

	public static class Constant extends Gs2Watch.Constant {
		public static final String FUNCTION = "DescribeOperation";
	}

	/** サービス名。 */
	private String service;


	/**
	 * サービス名。を取得
	 *
	 * @return サービス名。
	 */
	public String getService() {
		return service;
	}

	/**
	 * サービス名。を設定
	 *
	 * @param service サービス名。
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * サービス名。を設定
	 *
	 * @param service サービス名。
	 * @return this
	 */
	public DescribeOperationRequest withService(String service) {
		setService(service);
		return this;
	}

}