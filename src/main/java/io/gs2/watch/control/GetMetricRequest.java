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
public class GetMetricRequest extends Gs2BasicRequest<GetMetricRequest> {

	public static class Constant extends Gs2Watch.Constant {
		public static final String FUNCTION = "GetMetric";
	}

	/** リソースGRN */
	private String serviceId;

	/** 操作名 */
	private String operation;

	/** データの取得開始日時 */
	private Integer begin;

	/** データの取得終了日時 */
	private Integer end;

	/** 長期間のデータ取得を許可するか */
	private Boolean allowLongTerm;


	/**
	 * リソースGRNを取得
	 *
	 * @return リソースGRN
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * リソースGRNを設定
	 *
	 * @param serviceId リソースGRN
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * リソースGRNを設定
	 *
	 * @param serviceId リソースGRN
	 * @return this
	 */
	public GetMetricRequest withServiceId(String serviceId) {
		setServiceId(serviceId);
		return this;
	}

	/**
	 * 操作名を取得
	 *
	 * @return 操作名
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * 操作名を設定
	 *
	 * @param operation 操作名
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 操作名を設定
	 *
	 * @param operation 操作名
	 * @return this
	 */
	public GetMetricRequest withOperation(String operation) {
		setOperation(operation);
		return this;
	}

	/**
	 * データの取得開始日時を取得
	 *
	 * @return データの取得開始日時
	 */
	public Integer getBegin() {
		return begin;
	}

	/**
	 * データの取得開始日時を設定
	 *
	 * @param begin データの取得開始日時
	 */
	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	/**
	 * データの取得開始日時を設定
	 *
	 * @param begin データの取得開始日時
	 * @return this
	 */
	public GetMetricRequest withBegin(Integer begin) {
		setBegin(begin);
		return this;
	}

	/**
	 * データの取得終了日時を取得
	 *
	 * @return データの取得終了日時
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * データの取得終了日時を設定
	 *
	 * @param end データの取得終了日時
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}

	/**
	 * データの取得終了日時を設定
	 *
	 * @param end データの取得終了日時
	 * @return this
	 */
	public GetMetricRequest withEnd(Integer end) {
		setEnd(end);
		return this;
	}

	/**
	 * 長期間のデータ取得を許可するかを取得
	 *
	 * @return 長期間のデータ取得を許可するか
	 */
	public Boolean getAllowLongTerm() {
		return allowLongTerm;
	}

	/**
	 * 長期間のデータ取得を許可するかを設定
	 *
	 * @param allowLongTerm 長期間のデータ取得を許可するか
	 */
	public void setAllowLongTerm(Boolean allowLongTerm) {
		this.allowLongTerm = allowLongTerm;
	}

	/**
	 * 長期間のデータ取得を許可するかを設定
	 *
	 * @param allowLongTerm 長期間のデータ取得を許可するか
	 * @return this
	 */
	public GetMetricRequest withAllowLongTerm(Boolean allowLongTerm) {
		setAllowLongTerm(allowLongTerm);
		return this;
	}

}