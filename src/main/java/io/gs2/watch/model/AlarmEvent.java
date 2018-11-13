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
 * アラームイベント
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AlarmEvent implements Serializable {

	/** イベントID */
	private String eventId;

	/** アラームID */
	private String alarmId;

	/** イベントの種類 */
	private String type;

	/** 発生日時(エポック秒) */
	private Integer eventAt;


	/**
	 * イベントIDを取得
	 *
	 * @return イベントID
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * イベントIDを設定
	 *
	 * @param eventId イベントID
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * イベントIDを設定
	 *
	 * @param eventId イベントID
	 * @return this
	 */
	public AlarmEvent withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}

	/**
	 * アラームIDを取得
	 *
	 * @return アラームID
	 */
	public String getAlarmId() {
		return alarmId;
	}

	/**
	 * アラームIDを設定
	 *
	 * @param alarmId アラームID
	 */
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	/**
	 * アラームIDを設定
	 *
	 * @param alarmId アラームID
	 * @return this
	 */
	public AlarmEvent withAlarmId(String alarmId) {
		this.alarmId = alarmId;
		return this;
	}

	/**
	 * イベントの種類を取得
	 *
	 * @return イベントの種類
	 */
	public String getType() {
		return type;
	}

	/**
	 * イベントの種類を設定
	 *
	 * @param type イベントの種類
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * イベントの種類を設定
	 *
	 * @param type イベントの種類
	 * @return this
	 */
	public AlarmEvent withType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * 発生日時(エポック秒)を取得
	 *
	 * @return 発生日時(エポック秒)
	 */
	public Integer getEventAt() {
		return eventAt;
	}

	/**
	 * 発生日時(エポック秒)を設定
	 *
	 * @param eventAt 発生日時(エポック秒)
	 */
	public void setEventAt(Integer eventAt) {
		this.eventAt = eventAt;
	}

	/**
	 * 発生日時(エポック秒)を設定
	 *
	 * @param eventAt 発生日時(エポック秒)
	 * @return this
	 */
	public AlarmEvent withEventAt(Integer eventAt) {
		this.eventAt = eventAt;
		return this;
	}


    public ObjectNode toJson() {

		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("eventId", this.getEventId())
            .put("alarmId", this.getAlarmId())
            .put("type", this.getType())
            .put("eventAt", this.getEventAt());

        return body;
    }
}