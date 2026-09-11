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

package io.gs2.adReward.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MobileNotificationMessage implements IModel, Serializable {
	private String locale;
	private String title;
	private String message;
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public MobileNotificationMessage withLocale(String locale) {
		this.locale = locale;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MobileNotificationMessage withTitle(String title) {
		this.title = title;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MobileNotificationMessage withMessage(String message) {
		this.message = message;
		return this;
	}

    public static MobileNotificationMessage fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MobileNotificationMessage()
            .withLocale(data.get("locale") == null || data.get("locale").isNull() ? null : data.get("locale").asText())
            .withTitle(data.get("title") == null || data.get("title").isNull() ? null : data.get("title").asText())
            .withMessage(data.get("message") == null || data.get("message").isNull() ? null : data.get("message").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("locale", getLocale());
                put("title", getTitle());
                put("message", getMessage());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.locale == null) ? 0 : this.locale.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
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
		MobileNotificationMessage other = (MobileNotificationMessage) o;
		if (locale == null) {
			return other.locale == null;
		} else if (!locale.equals(other.locale)) {
			return false;
		}
		if (title == null) {
			return other.title == null;
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (message == null) {
			return other.message == null;
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}
}