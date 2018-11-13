package io.gs2.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 請求情報
 * 
 * @author Game Server Services, Inc.
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Billing {

	/** 請求レコードID */
	String billingId;
	/** オーナーID */
	String ownerId;
	/** サービス名 */
	String service;
	/** 操作名 */
	String operation;
	/** 請求カウント */
	Long count;
	/** 更新日時 */
	Long updateAt;

	/**
	 * 請求レコードIDを取得
	 * 
	 * @return 請求レコードID
	 */
	public String getBillingId() {
		return billingId;
	}
	
	/**
	 * 請求レコードIDを設定
	 * 
	 * @param billingId 請求レコードID
	 */
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	/**
	 * オーナーIDを取得
	 * 
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}
	
	/**
	 * オーナーIDを設定
	 * 
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * サービスを取得
	 * 
	 * @return サービス
	 */
	public String getService() {
		return service;
	}
	
	/**
	 * サービスを設定
	 * 
	 * @param service サービス
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * オペレーションを取得
	 * 
	 * @return オペレーション
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * オペレーションを設定
	 * 
	 * @param operation オペレーション
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * カウントを取得
	 * 
	 * @return カウント
	 */
	public Long getCount() {
		return count;
	}
	
	/**
	 * カウントを設定
	 * 
	 * @param count カウント
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * 更新日時を取得
	 * 
	 * @return 更新日時
	 */
	public Long getUpdateAt() {
		return updateAt;
	}
	
	/**
	 * 更新日時を設定
	 * 
	 * @param updateAt 更新日時
	 */
	public void setUpdateAt(Long updateAt) {
		this.updateAt = updateAt;
	}
}
