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
public class RefundEvent implements IModel, Serializable {
	private String contentName;
	private String platform;
	private AppleAppStoreVerifyReceiptEvent appleAppStoreRefundEvent;
	private GooglePlayVerifyReceiptEvent googlePlayRefundEvent;
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public RefundEvent withContentName(String contentName) {
		this.contentName = contentName;
		return this;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public RefundEvent withPlatform(String platform) {
		this.platform = platform;
		return this;
	}
	public AppleAppStoreVerifyReceiptEvent getAppleAppStoreRefundEvent() {
		return appleAppStoreRefundEvent;
	}
	public void setAppleAppStoreRefundEvent(AppleAppStoreVerifyReceiptEvent appleAppStoreRefundEvent) {
		this.appleAppStoreRefundEvent = appleAppStoreRefundEvent;
	}
	public RefundEvent withAppleAppStoreRefundEvent(AppleAppStoreVerifyReceiptEvent appleAppStoreRefundEvent) {
		this.appleAppStoreRefundEvent = appleAppStoreRefundEvent;
		return this;
	}
	public GooglePlayVerifyReceiptEvent getGooglePlayRefundEvent() {
		return googlePlayRefundEvent;
	}
	public void setGooglePlayRefundEvent(GooglePlayVerifyReceiptEvent googlePlayRefundEvent) {
		this.googlePlayRefundEvent = googlePlayRefundEvent;
	}
	public RefundEvent withGooglePlayRefundEvent(GooglePlayVerifyReceiptEvent googlePlayRefundEvent) {
		this.googlePlayRefundEvent = googlePlayRefundEvent;
		return this;
	}

    public static RefundEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RefundEvent()
            .withContentName(data.get("contentName") == null || data.get("contentName").isNull() ? null : data.get("contentName").asText())
            .withPlatform(data.get("platform") == null || data.get("platform").isNull() ? null : data.get("platform").asText())
            .withAppleAppStoreRefundEvent(data.get("appleAppStoreRefundEvent") == null || data.get("appleAppStoreRefundEvent").isNull() ? null : AppleAppStoreVerifyReceiptEvent.fromJson(data.get("appleAppStoreRefundEvent")))
            .withGooglePlayRefundEvent(data.get("googlePlayRefundEvent") == null || data.get("googlePlayRefundEvent").isNull() ? null : GooglePlayVerifyReceiptEvent.fromJson(data.get("googlePlayRefundEvent")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("contentName", getContentName());
                put("platform", getPlatform());
                put("appleAppStoreRefundEvent", getAppleAppStoreRefundEvent() != null ? getAppleAppStoreRefundEvent().toJson() : null);
                put("googlePlayRefundEvent", getGooglePlayRefundEvent() != null ? getGooglePlayRefundEvent().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.contentName == null) ? 0 : this.contentName.hashCode());
        result = prime * result + ((this.platform == null) ? 0 : this.platform.hashCode());
        result = prime * result + ((this.appleAppStoreRefundEvent == null) ? 0 : this.appleAppStoreRefundEvent.hashCode());
        result = prime * result + ((this.googlePlayRefundEvent == null) ? 0 : this.googlePlayRefundEvent.hashCode());
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
		RefundEvent other = (RefundEvent) o;
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
		if (appleAppStoreRefundEvent == null) {
			return other.appleAppStoreRefundEvent == null;
		} else if (!appleAppStoreRefundEvent.equals(other.appleAppStoreRefundEvent)) {
			return false;
		}
		if (googlePlayRefundEvent == null) {
			return other.googlePlayRefundEvent == null;
		} else if (!googlePlayRefundEvent.equals(other.googlePlayRefundEvent)) {
			return false;
		}
		return true;
	}
}