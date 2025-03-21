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

package io.gs2.gateway.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.gateway.model.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SendNotificationResult implements IResult, Serializable {
    private String protocol;
    private List<String> sendConnectionIds;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public SendNotificationResult withProtocol(String protocol) {
		this.protocol = protocol;
		return this;
	}

	public List<String> getSendConnectionIds() {
		return sendConnectionIds;
	}

	public void setSendConnectionIds(List<String> sendConnectionIds) {
		this.sendConnectionIds = sendConnectionIds;
	}

	public SendNotificationResult withSendConnectionIds(List<String> sendConnectionIds) {
		this.sendConnectionIds = sendConnectionIds;
		return this;
	}

    public static SendNotificationResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SendNotificationResult()
            .withProtocol(data.get("protocol") == null || data.get("protocol").isNull() ? null : data.get("protocol").asText())
            .withSendConnectionIds(data.get("sendConnectionIds") == null || data.get("sendConnectionIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("sendConnectionIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("protocol", getProtocol());
                put("sendConnectionIds", getSendConnectionIds() == null ? null :
                    getSendConnectionIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}