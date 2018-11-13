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
public class UpdateAlarmRequest extends Gs2BasicRequest<UpdateAlarmRequest> {

	public static class Constant extends Gs2Watch.Constant {
		public static final String FUNCTION = "UpdateAlarm";
	}

	/** アラームの名前を指定します。 */
	private String alarmName;

	/** 説明文 */
	private String description;

	/** 演算子 */
	private String expression;

	/** 閾値 */
	private Double threshold;

	/** 通知先 GS2-Notification 通知GRN */
	private String notificationId;


	/**
	 * アラームの名前を指定します。を取得
	 *
	 * @return アラームの名前を指定します。
	 */
	public String getAlarmName() {
		return alarmName;
	}

	/**
	 * アラームの名前を指定します。を設定
	 *
	 * @param alarmName アラームの名前を指定します。
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	/**
	 * アラームの名前を指定します。を設定
	 *
	 * @param alarmName アラームの名前を指定します。
	 * @return this
	 */
	public UpdateAlarmRequest withAlarmName(String alarmName) {
		setAlarmName(alarmName);
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
	public UpdateAlarmRequest withDescription(String description) {
		setDescription(description);
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
	public UpdateAlarmRequest withExpression(String expression) {
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
	public UpdateAlarmRequest withThreshold(Double threshold) {
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
	public UpdateAlarmRequest withNotificationId(String notificationId) {
		setNotificationId(notificationId);
		return this;
	}

}