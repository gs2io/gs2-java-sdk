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

package io.gs2.log.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.log.model.InGameLogTag;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SendInGameLogRequest extends Gs2BasicRequest<SendInGameLogRequest> {
    private String namespaceName;
    private String accessToken;
    private List<InGameLogTag> tags;
    private String payload;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SendInGameLogRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public SendInGameLogRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public List<InGameLogTag> getTags() {
		return tags;
	}
	public void setTags(List<InGameLogTag> tags) {
		this.tags = tags;
	}
	public SendInGameLogRequest withTags(List<InGameLogTag> tags) {
		this.tags = tags;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public SendInGameLogRequest withPayload(String payload) {
		this.payload = payload;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SendInGameLogRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SendInGameLogRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SendInGameLogRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withTags(data.get("tags") == null || data.get("tags").isNull() ? new ArrayList<InGameLogTag>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tags").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return InGameLogTag.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("tags", getTags() == null ? new ArrayList<InGameLogTag>() :
                    getTags().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("payload", getPayload());
            }}
        );
    }
}