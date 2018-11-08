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

package io.gs2.money.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.money.Gs2Money;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeReceiptRequest extends Gs2BasicRequest<DescribeReceiptRequest> {

	public static class Constant extends Gs2Money.Constant {
		public static final String FUNCTION = "DescribeReceipt";
	}

	/** 課金通貨の名前 */
	private String moneyName;

	/** データの取得開始日時(エポック秒) */
	private Integer begin;

	/** データの取得終了日時(エポック秒) */
	private Integer end;

	/** データの取得を開始する位置を指定するトークン */
	private String pageToken;

	/** データの取得件数 */
	private Integer limit;


	/**
	 * 課金通貨の名前を取得
	 *
	 * @return 課金通貨の名前
	 */
	public String getMoneyName() {
		return moneyName;
	}

	/**
	 * 課金通貨の名前を設定
	 *
	 * @param moneyName 課金通貨の名前
	 */
	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}

	/**
	 * 課金通貨の名前を設定
	 *
	 * @param moneyName 課金通貨の名前
	 * @return this
	 */
	public DescribeReceiptRequest withMoneyName(String moneyName) {
		setMoneyName(moneyName);
		return this;
	}

	/**
	 * データの取得開始日時(エポック秒)を取得
	 *
	 * @return データの取得開始日時(エポック秒)
	 */
	public Integer getBegin() {
		return begin;
	}

	/**
	 * データの取得開始日時(エポック秒)を設定
	 *
	 * @param begin データの取得開始日時(エポック秒)
	 */
	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	/**
	 * データの取得開始日時(エポック秒)を設定
	 *
	 * @param begin データの取得開始日時(エポック秒)
	 * @return this
	 */
	public DescribeReceiptRequest withBegin(Integer begin) {
		setBegin(begin);
		return this;
	}

	/**
	 * データの取得終了日時(エポック秒)を取得
	 *
	 * @return データの取得終了日時(エポック秒)
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * データの取得終了日時(エポック秒)を設定
	 *
	 * @param end データの取得終了日時(エポック秒)
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}

	/**
	 * データの取得終了日時(エポック秒)を設定
	 *
	 * @param end データの取得終了日時(エポック秒)
	 * @return this
	 */
	public DescribeReceiptRequest withEnd(Integer end) {
		setEnd(end);
		return this;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを取得
	 *
	 * @return データの取得を開始する位置を指定するトークン
	 */
	public String getPageToken() {
		return pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 */
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 * @return this
	 */
	public DescribeReceiptRequest withPageToken(String pageToken) {
		setPageToken(pageToken);
		return this;
	}

	/**
	 * データの取得件数を取得
	 *
	 * @return データの取得件数
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 * @return this
	 */
	public DescribeReceiptRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}