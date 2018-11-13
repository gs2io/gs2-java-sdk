package io.gs2.billing.control;

import io.gs2.billing.Gs2Billing;
import io.gs2.control.Gs2BasicRequest;

/**
 * 請求情報一覧の取得リクエスト。
 * 
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
public class DescribeBillingRequest extends Gs2BasicRequest<DescribeBillingRequest> {

	public static class Constant extends Gs2Billing.Constant {
		public static final String FUNCTION = "DescribeBill";
	}

	/** 請求情報取得年 */
	Integer year;
	/** 請求情報取得月 */
	Integer month;
	/** 取得開始位置トークン */
	String pageToken;
	/** 取得件数 */
	Integer limit;

	/**
	 * 請求情報取得年を取得。
	 * 
	 * @return 請求情報取得年
	 */
	public Integer getYear() {
		return year;
	}
	
	/**
	 * 請求情報取得年を設定。
	 * 
	 * @param year 請求情報取得年
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * 請求情報取得年を設定。
	 * 
	 * @param year 請求情報取得年
	 * @return this
	 */
	public DescribeBillingRequest withYear(Integer year) {
		setYear(year);
		return this;
	}

	/**
	 * 請求情報取得月を取得。
	 * 
	 * @return 請求情報取得月
	 */
	public Integer getMonth() {
		return month;
	}
	
	/**
	 * 請求情報取得月を設定。
	 * 
	 * @param month 請求情報取得月
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	/**
	 * 請求情報取得月を設定。
	 * 
	 * @param month 請求情報取得月
	 * @return this
	 */
	public DescribeBillingRequest withMonth(Integer month) {
		setMonth(month);
		return this;
	}

	/**
	 * 取得開始位置トークンを取得。
	 * 
	 * @return 取得開始位置トークン
	 */
	public String getPageToken() {
		return pageToken;
	}
	
	/**
	 * 取得開始位置トークンを設定。
	 * 
	 * @param pageToken 取得開始位置トークン
	 */
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	
	/**
	 * 取得開始位置トークンを設定。
	 * 
	 * @param pageToken 取得開始位置トークン
	 * @return this
	 */
	public DescribeBillingRequest withPageToken(String pageToken) {
		setPageToken(pageToken);
		return this;
	}

	/**
	 * 取得件数を取得。
	 * 
	 * @return 取得件数
	 */
	public Integer getLimit() {
		return limit;
	}
	
	/**
	 * 取得件数を設定。
	 * 
	 * @param limit 取得件数
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	/**
	 * 取得件数を設定。
	 * 
	 * @param limit 取得件数
	 * @return this
	 */
	public DescribeBillingRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}
}
