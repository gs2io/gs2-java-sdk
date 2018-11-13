package io.gs2.billing.control;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.gs2.billing.model.Billing;

/**
 * 請求情報一覧取得結果。
 * 
 * @author Game Server Services, Inc.
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeBillingResult {

	/** 請求情報一覧 */
	List<Billing> items;
	/** 次のページを取得するためのトークン */
	String nextPageToken;
	
	/**
	 * 取得した請求情報数を取得。
	 * 
	 * @return 取得した請求情報数
	 */
	public Integer getCount() {
		return items == null ? null : items.size();
	}
	
	@Deprecated
	public void setCount(Integer count){ }
	
	/**
	 * 取得した請求情報一覧を取得。
	 * 
	 * @return 請求情報一覧
	 */
	public List<Billing> getItems() {
		return items;
	}
	
	/**
	 * 請求情報一覧を設定。
	 * 
	 * @param items 請求情報一覧
	 */
	public void setItems(List<Billing> items) {
		this.items = items;
	}
	
	/**
	 * 次のページを取得するためのトークンを取得。
	 * 
	 * {@link DescribeBillingRequest} に指定することで、次のページを取得できます。
	 * 
	 * @return トークン
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}
	
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}
