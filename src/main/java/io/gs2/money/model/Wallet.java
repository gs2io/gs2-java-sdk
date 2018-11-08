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

package io.gs2.money.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ウォレットの詳細
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Wallet implements Serializable {

	/** 単価 */
	private Double price;

	/** 所持数 */
	private Integer count;


	/**
	 * 単価を取得
	 *
	 * @return 単価
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 単価を設定
	 *
	 * @param price 単価
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 所持数を取得
	 *
	 * @return 所持数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 所持数を設定
	 *
	 * @param count 所持数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

}