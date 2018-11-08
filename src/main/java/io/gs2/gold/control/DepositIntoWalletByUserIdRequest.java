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

package io.gs2.gold.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.gold.model.*;
import io.gs2.gold.Gs2Gold;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DepositIntoWalletByUserIdRequest extends Gs2BasicRequest<DepositIntoWalletByUserIdRequest> {

	public static class Constant extends Gs2Gold.Constant {
		public static final String FUNCTION = "DepositIntoWalletByUserId";
	}

	/** ゴールドプールの名前 */
	private String goldPoolName;

	/** ゴールドの名前 */
	private String goldName;

	/** ウォレット所有者のユーザID */
	private String userId;

	/** 加算量 */
	private Long value;

	/** コンテキスト */
	private String context;


	/**
	 * ゴールドプールの名前を取得
	 *
	 * @return ゴールドプールの名前
	 */
	public String getGoldPoolName() {
		return goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 */
	public void setGoldPoolName(String goldPoolName) {
		this.goldPoolName = goldPoolName;
	}

	/**
	 * ゴールドプールの名前を設定
	 *
	 * @param goldPoolName ゴールドプールの名前
	 * @return this
	 */
	public DepositIntoWalletByUserIdRequest withGoldPoolName(String goldPoolName) {
		setGoldPoolName(goldPoolName);
		return this;
	}

	/**
	 * ゴールドの名前を取得
	 *
	 * @return ゴールドの名前
	 */
	public String getGoldName() {
		return goldName;
	}

	/**
	 * ゴールドの名前を設定
	 *
	 * @param goldName ゴールドの名前
	 */
	public void setGoldName(String goldName) {
		this.goldName = goldName;
	}

	/**
	 * ゴールドの名前を設定
	 *
	 * @param goldName ゴールドの名前
	 * @return this
	 */
	public DepositIntoWalletByUserIdRequest withGoldName(String goldName) {
		setGoldName(goldName);
		return this;
	}

	/**
	 * ウォレット所有者のユーザIDを取得
	 *
	 * @return ウォレット所有者のユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ウォレット所有者のユーザIDを設定
	 *
	 * @param userId ウォレット所有者のユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ウォレット所有者のユーザIDを設定
	 *
	 * @param userId ウォレット所有者のユーザID
	 * @return this
	 */
	public DepositIntoWalletByUserIdRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 加算量を取得
	 *
	 * @return 加算量
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * 加算量を設定
	 *
	 * @param value 加算量
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * 加算量を設定
	 *
	 * @param value 加算量
	 * @return this
	 */
	public DepositIntoWalletByUserIdRequest withValue(Long value) {
		setValue(value);
		return this;
	}

	/**
	 * コンテキストを取得
	 *
	 * @return コンテキスト
	 */
	public String getContext() {
		return context;
	}

	/**
	 * コンテキストを設定
	 *
	 * @param context コンテキスト
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * コンテキストを設定
	 *
	 * @param context コンテキスト
	 * @return this
	 */
	public DepositIntoWalletByUserIdRequest withContext(String context) {
		setContext(context);
		return this;
	}

}