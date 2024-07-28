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

package io.gs2.distributor.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.distributor.model.VerifyAction;
import io.gs2.distributor.model.ConsumeAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IfExpressionByUserIdRequest extends Gs2BasicRequest<IfExpressionByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private VerifyAction condition;
    private List<ConsumeAction> trueActions;
    private List<ConsumeAction> falseActions;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public IfExpressionByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public IfExpressionByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public VerifyAction getCondition() {
		return condition;
	}
	public void setCondition(VerifyAction condition) {
		this.condition = condition;
	}
	public IfExpressionByUserIdRequest withCondition(VerifyAction condition) {
		this.condition = condition;
		return this;
	}
	public List<ConsumeAction> getTrueActions() {
		return trueActions;
	}
	public void setTrueActions(List<ConsumeAction> trueActions) {
		this.trueActions = trueActions;
	}
	public IfExpressionByUserIdRequest withTrueActions(List<ConsumeAction> trueActions) {
		this.trueActions = trueActions;
		return this;
	}
	public List<ConsumeAction> getFalseActions() {
		return falseActions;
	}
	public void setFalseActions(List<ConsumeAction> falseActions) {
		this.falseActions = falseActions;
	}
	public IfExpressionByUserIdRequest withFalseActions(List<ConsumeAction> falseActions) {
		this.falseActions = falseActions;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public IfExpressionByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public IfExpressionByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static IfExpressionByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IfExpressionByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCondition(data.get("condition") == null || data.get("condition").isNull() ? null : VerifyAction.fromJson(data.get("condition")))
            .withTrueActions(data.get("trueActions") == null || data.get("trueActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("trueActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withFalseActions(data.get("falseActions") == null || data.get("falseActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("falseActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("condition", getCondition() != null ? getCondition().toJson() : null);
                put("trueActions", getTrueActions() == null ? new ArrayList<ConsumeAction>() :
                    getTrueActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("falseActions", getFalseActions() == null ? new ArrayList<ConsumeAction>() :
                    getFalseActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}