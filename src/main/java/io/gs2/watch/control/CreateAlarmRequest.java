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
public class CreateAlarmRequest extends Gs2BasicRequest<CreateAlarmRequest> {

	public static class Constant extends Gs2Watch.Constant {
		public static final String FUNCTION = "CreateAlarm";
	}

	/** 名前 */
	private String name;

	/** 説明文 */
	private String description;

	/** サービス名 */
	private String service;

	/** リソースGRN */
	private String serviceId;

	/** 操作名 */
	private String operation;

	/** 演算子 */
	private String expression;

	/** 閾値 */
	private Double threshold;

	/** 通知先 GS2-Notification 通知GRN */
	private String notificationId;


	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 * @return this
	 */
	public CreateAlarmRequest withName(String name) {
		setName(name);
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public CreateAlarmRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * サービス名を取得
	 *
	 * @return サービス名
	 */
	public String getService() {
		return service;
	}

	/**
	 * サービス名を設定
	 *
	 * @param service サービス名
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * サービス名を設定
	 *
	 * @param service サービス名
	 * @return this
	 */
	public CreateAlarmRequest withService(String service) {
		setService(service);
		return this;
	}

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
	public CreateAlarmRequest withServiceId(String serviceId) {
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
	public CreateAlarmRequest withOperation(String operation) {
		setOperation(operation);
		return this;
	}

	/**
	 * 演算子を取得
	 *
	 * @return 演算子
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * 演算子を設定
	 *
	 * @param expression 演算子
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * 演算子を設定
	 *
	 * @param expression 演算子
	 * @return this
	 */
	public CreateAlarmRequest withExpression(String expression) {
		setExpression(expression);
		return this;
	}

	/**
	 * 閾値を取得
	 *
	 * @return 閾値
	 */
	public Double getThreshold() {
		return threshold;
	}

	/**
	 * 閾値を設定
	 *
	 * @param threshold 閾値
	 */
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	/**
	 * 閾値を設定
	 *
	 * @param threshold 閾値
	 * @return this
	 */
	public CreateAlarmRequest withThreshold(Double threshold) {
		setThreshold(threshold);
		return this;
	}

	/**
	 * 通知先 GS2-Notification 通知GRNを取得
	 *
	 * @return 通知先 GS2-Notification 通知GRN
	 */
	public String getNotificationId() {
		return notificationId;
	}

	/**
	 * 通知先 GS2-Notification 通知GRNを設定
	 *
	 * @param notificationId 通知先 GS2-Notification 通知GRN
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * 通知先 GS2-Notification 通知GRNを設定
	 *
	 * @param notificationId 通知先 GS2-Notification 通知GRN
	 * @return this
	 */
	public CreateAlarmRequest withNotificationId(String notificationId) {
		setNotificationId(notificationId);
		return this;
	}

}