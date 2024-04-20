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

package io.gs2.mission.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.mission.model.ScopedValue;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetCounterByUserIdRequest extends Gs2BasicRequest<SetCounterByUserIdRequest> {
    private String namespaceName;
    private String counterName;
    private String userId;
    private List<ScopedValue> values;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetCounterByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public SetCounterByUserIdRequest withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SetCounterByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<ScopedValue> getValues() {
		return values;
	}
	public void setValues(List<ScopedValue> values) {
		this.values = values;
	}
	public SetCounterByUserIdRequest withValues(List<ScopedValue> values) {
		this.values = values;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public SetCounterByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetCounterByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetCounterByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetCounterByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withValues(data.get("values") == null || data.get("values").isNull() ? new ArrayList<ScopedValue>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("values").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ScopedValue.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("counterName", getCounterName());
                put("userId", getUserId());
                put("values", getValues() == null ? new ArrayList<ScopedValue>() :
                    getValues().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}