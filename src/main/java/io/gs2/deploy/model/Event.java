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

package io.gs2.deploy.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 発生したイベント
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Event implements IModel, Serializable, Comparable<Event> {
	/** 発生したイベント */
	protected String eventId;

	/**
	 * 発生したイベントを取得
	 *
	 * @return 発生したイベント
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * 発生したイベントを設定
	 *
	 * @param eventId 発生したイベント
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * 発生したイベントを設定
	 *
	 * @param eventId 発生したイベント
	 * @return this
	 */
	public Event withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}
	/** イベント名 */
	protected String name;

	/**
	 * イベント名を取得
	 *
	 * @return イベント名
	 */
	public String getName() {
		return name;
	}

	/**
	 * イベント名を設定
	 *
	 * @param name イベント名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * イベント名を設定
	 *
	 * @param name イベント名
	 * @return this
	 */
	public Event withName(String name) {
		this.name = name;
		return this;
	}
	/** イベントの種類 */
	protected String resourceName;

	/**
	 * イベントの種類を取得
	 *
	 * @return イベントの種類
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * イベントの種類を設定
	 *
	 * @param resourceName イベントの種類
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * イベントの種類を設定
	 *
	 * @param resourceName イベントの種類
	 * @return this
	 */
	public Event withResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}
	/** イベントの種類 */
	protected String type;

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
	public Event withType(String type) {
		this.type = type;
		return this;
	}
	/** メッセージ */
	protected String message;

	/**
	 * メッセージを取得
	 *
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージを設定
	 *
	 * @param message メッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * メッセージを設定
	 *
	 * @param message メッセージ
	 * @return this
	 */
	public Event withMessage(String message) {
		this.message = message;
		return this;
	}
	/** 日時 */
	protected Long eventAt;

	/**
	 * 日時を取得
	 *
	 * @return 日時
	 */
	public Long getEventAt() {
		return eventAt;
	}

	/**
	 * 日時を設定
	 *
	 * @param eventAt 日時
	 */
	public void setEventAt(Long eventAt) {
		this.eventAt = eventAt;
	}

	/**
	 * 日時を設定
	 *
	 * @param eventAt 日時
	 * @return this
	 */
	public Event withEventAt(Long eventAt) {
		this.eventAt = eventAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("eventId", this.getEventId())
            .put("name", this.getName())
            .put("resourceName", this.getResourceName())
            .put("type", this.getType())
            .put("message", this.getMessage())
            .put("eventAt", this.getEventAt());
        return body_;
    }
	@Override
	public int compareTo(Event o) {
		return eventId.compareTo(o.eventId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eventId == null) ? 0 : this.eventId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.resourceName == null) ? 0 : this.resourceName.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
        result = prime * result + ((this.eventAt == null) ? 0 : this.eventAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Event other = (Event) o;
		if (eventId == null) {
			return other.eventId == null;
		} else if (!eventId.equals(other.eventId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (resourceName == null) {
			return other.resourceName == null;
		} else if (!resourceName.equals(other.resourceName)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (message == null) {
			return other.message == null;
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (eventAt == null) {
			return other.eventAt == null;
		} else if (!eventAt.equals(other.eventAt)) {
			return false;
		}
		return true;
	}
}