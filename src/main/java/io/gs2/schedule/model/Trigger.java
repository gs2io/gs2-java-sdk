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

package io.gs2.schedule.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * トリガー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Trigger implements Serializable {

	/** ユーザID */
	private String userId;

	/** トリガーID */
	private String triggerName;

	/** トリガーを引いた時間(エポック秒) */
	private Integer triggerAt;


	/**
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public Trigger withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * トリガーIDを取得
	 *
	 * @return トリガーID
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * トリガーIDを設定
	 *
	 * @param triggerName トリガーID
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	/**
	 * トリガーIDを設定
	 *
	 * @param triggerName トリガーID
	 * @return this
	 */
	public Trigger withTriggerName(String triggerName) {
		this.triggerName = triggerName;
		return this;
	}

	/**
	 * トリガーを引いた時間(エポック秒)を取得
	 *
	 * @return トリガーを引いた時間(エポック秒)
	 */
	public Integer getTriggerAt() {
		return triggerAt;
	}

	/**
	 * トリガーを引いた時間(エポック秒)を設定
	 *
	 * @param triggerAt トリガーを引いた時間(エポック秒)
	 */
	public void setTriggerAt(Integer triggerAt) {
		this.triggerAt = triggerAt;
	}

	/**
	 * トリガーを引いた時間(エポック秒)を設定
	 *
	 * @param triggerAt トリガーを引いた時間(エポック秒)
	 * @return this
	 */
	public Trigger withTriggerAt(Integer triggerAt) {
		this.triggerAt = triggerAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("userId", this.getUserId())
            .put("triggerName", this.getTriggerName())
            .put("triggerAt", this.getTriggerAt());

        return body;
    }
}