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

package io.gs2.watch.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * アラーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Alarm implements Serializable {

	/** アラームGRN */
	private String alarmId;

	/** オーナーID */
	private String ownerId;

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

	/** 現在の状態 */
	private String status;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;

	/** 最終ステータス変化日時(エポック秒) */
	private Integer lastStatusChangeAt;


	/**
	 * アラームGRNを取得
	 *
	 * @return アラームGRN
	 */
	public String getAlarmId() {
		return alarmId;
	}

	/**
	 * アラームGRNを設定
	 *
	 * @param alarmId アラームGRN
	 */
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	/**
	 * アラームGRNを設定
	 *
	 * @param alarmId アラームGRN
	 * @return this
	 */
	public Alarm withAlarmId(String alarmId) {
		this.alarmId = alarmId;
		return this;
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
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Alarm withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

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
	public Alarm withName(String name) {
		this.name = name;
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
	public Alarm withDescription(String description) {
		this.description = description;
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
	public Alarm withService(String service) {
		this.service = service;
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
	public Alarm withServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	public Alarm withOperation(String operation) {
		this.operation = operation;
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
	public Alarm withExpression(String expression) {
		this.expression = expression;
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
	public Alarm withThreshold(Double threshold) {
		this.threshold = threshold;
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
	public Alarm withNotificationId(String notificationId) {
		this.notificationId = notificationId;
		return this;
	}

	/**
	 * 現在の状態を取得
	 *
	 * @return 現在の状態
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 現在の状態を設定
	 *
	 * @param status 現在の状態
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 現在の状態を設定
	 *
	 * @param status 現在の状態
	 * @return this
	 */
	public Alarm withStatus(String status) {
		this.status = status;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public Alarm withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public Alarm withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}

	/**
	 * 最終ステータス変化日時(エポック秒)を取得
	 *
	 * @return 最終ステータス変化日時(エポック秒)
	 */
	public Integer getLastStatusChangeAt() {
		return lastStatusChangeAt;
	}

	/**
	 * 最終ステータス変化日時(エポック秒)を設定
	 *
	 * @param lastStatusChangeAt 最終ステータス変化日時(エポック秒)
	 */
	public void setLastStatusChangeAt(Integer lastStatusChangeAt) {
		this.lastStatusChangeAt = lastStatusChangeAt;
	}

	/**
	 * 最終ステータス変化日時(エポック秒)を設定
	 *
	 * @param lastStatusChangeAt 最終ステータス変化日時(エポック秒)
	 * @return this
	 */
	public Alarm withLastStatusChangeAt(Integer lastStatusChangeAt) {
		this.lastStatusChangeAt = lastStatusChangeAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("alarmId", this.getAlarmId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("service", this.getService())
            .put("serviceId", this.getServiceId())
            .put("operation", this.getOperation())
            .put("expression", this.getExpression())
            .put("threshold", this.getThreshold())
            .put("notificationId", this.getNotificationId())
            .put("status", this.getStatus())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt())
            .put("lastStatusChangeAt", this.getLastStatusChangeAt());

        return body;
    }
}