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

package io.gs2.money2.model;

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
public class VerifyReceiptEvent implements IModel, Serializable {
	private String contentName;
	private String platform;
	private AppleAppStoreVerifyReceiptEvent appleAppStoreVerifyReceiptEvent;
	private GooglePlayVerifyReceiptEvent googlePlayVerifyReceiptEvent;
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public VerifyReceiptEvent withContentName(String contentName) {
		this.contentName = contentName;
		return this;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public VerifyReceiptEvent withPlatform(String platform) {
		this.platform = platform;
		return this;
	}
	public AppleAppStoreVerifyReceiptEvent getAppleAppStoreVerifyReceiptEvent() {
		return appleAppStoreVerifyReceiptEvent;
	}
	public void setAppleAppStoreVerifyReceiptEvent(AppleAppStoreVerifyReceiptEvent appleAppStoreVerifyReceiptEvent) {
		this.appleAppStoreVerifyReceiptEvent = appleAppStoreVerifyReceiptEvent;
	}
	public VerifyReceiptEvent withAppleAppStoreVerifyReceiptEvent(AppleAppStoreVerifyReceiptEvent appleAppStoreVerifyReceiptEvent) {
		this.appleAppStoreVerifyReceiptEvent = appleAppStoreVerifyReceiptEvent;
		return this;
	}
	public GooglePlayVerifyReceiptEvent getGooglePlayVerifyReceiptEvent() {
		return googlePlayVerifyReceiptEvent;
	}
	public void setGooglePlayVerifyReceiptEvent(GooglePlayVerifyReceiptEvent googlePlayVerifyReceiptEvent) {
		this.googlePlayVerifyReceiptEvent = googlePlayVerifyReceiptEvent;
	}
	public VerifyReceiptEvent withGooglePlayVerifyReceiptEvent(GooglePlayVerifyReceiptEvent googlePlayVerifyReceiptEvent) {
		this.googlePlayVerifyReceiptEvent = googlePlayVerifyReceiptEvent;
		return this;
	}

    public static VerifyReceiptEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyReceiptEvent()
            .withContentName(data.get("contentName") == null || data.get("contentName").isNull() ? null : data.get("contentName").asText())
            .withPlatform(data.get("platform") == null || data.get("platform").isNull() ? null : data.get("platform").asText())
            .withAppleAppStoreVerifyReceiptEvent(data.get("appleAppStoreVerifyReceiptEvent") == null || data.get("appleAppStoreVerifyReceiptEvent").isNull() ? null : AppleAppStoreVerifyReceiptEvent.fromJson(data.get("appleAppStoreVerifyReceiptEvent")))
            .withGooglePlayVerifyReceiptEvent(data.get("googlePlayVerifyReceiptEvent") == null || data.get("googlePlayVerifyReceiptEvent").isNull() ? null : GooglePlayVerifyReceiptEvent.fromJson(data.get("googlePlayVerifyReceiptEvent")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("contentName", getContentName());
                put("platform", getPlatform());
                put("appleAppStoreVerifyReceiptEvent", getAppleAppStoreVerifyReceiptEvent() != null ? getAppleAppStoreVerifyReceiptEvent().toJson() : null);
                put("googlePlayVerifyReceiptEvent", getGooglePlayVerifyReceiptEvent() != null ? getGooglePlayVerifyReceiptEvent().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.contentName == null) ? 0 : this.contentName.hashCode());
        result = prime * result + ((this.platform == null) ? 0 : this.platform.hashCode());
        result = prime * result + ((this.appleAppStoreVerifyReceiptEvent == null) ? 0 : this.appleAppStoreVerifyReceiptEvent.hashCode());
        result = prime * result + ((this.googlePlayVerifyReceiptEvent == null) ? 0 : this.googlePlayVerifyReceiptEvent.hashCode());
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
		VerifyReceiptEvent other = (VerifyReceiptEvent) o;
		if (contentName == null) {
			return other.contentName == null;
		} else if (!contentName.equals(other.contentName)) {
			return false;
		}
		if (platform == null) {
			return other.platform == null;
		} else if (!platform.equals(other.platform)) {
			return false;
		}
		if (appleAppStoreVerifyReceiptEvent == null) {
			return other.appleAppStoreVerifyReceiptEvent == null;
		} else if (!appleAppStoreVerifyReceiptEvent.equals(other.appleAppStoreVerifyReceiptEvent)) {
			return false;
		}
		if (googlePlayVerifyReceiptEvent == null) {
			return other.googlePlayVerifyReceiptEvent == null;
		} else if (!googlePlayVerifyReceiptEvent.equals(other.googlePlayVerifyReceiptEvent)) {
			return false;
		}
		return true;
	}
}