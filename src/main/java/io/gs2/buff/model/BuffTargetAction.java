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

package io.gs2.buff.model;

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
public class BuffTargetAction implements IModel, Serializable {
	private String targetActionName;
	private String targetFieldName;
	private List<BuffTargetGrn> conditionGrns;
	private Float rate;
	public String getTargetActionName() {
		return targetActionName;
	}
	public void setTargetActionName(String targetActionName) {
		this.targetActionName = targetActionName;
	}
	public BuffTargetAction withTargetActionName(String targetActionName) {
		this.targetActionName = targetActionName;
		return this;
	}
	public String getTargetFieldName() {
		return targetFieldName;
	}
	public void setTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
	}
	public BuffTargetAction withTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
		return this;
	}
	public List<BuffTargetGrn> getConditionGrns() {
		return conditionGrns;
	}
	public void setConditionGrns(List<BuffTargetGrn> conditionGrns) {
		this.conditionGrns = conditionGrns;
	}
	public BuffTargetAction withConditionGrns(List<BuffTargetGrn> conditionGrns) {
		this.conditionGrns = conditionGrns;
		return this;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public BuffTargetAction withRate(Float rate) {
		this.rate = rate;
		return this;
	}

    public static BuffTargetAction fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BuffTargetAction()
            .withTargetActionName(data.get("targetActionName") == null || data.get("targetActionName").isNull() ? null : data.get("targetActionName").asText())
            .withTargetFieldName(data.get("targetFieldName") == null || data.get("targetFieldName").isNull() ? null : data.get("targetFieldName").asText())
            .withConditionGrns(data.get("conditionGrns") == null || data.get("conditionGrns").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("conditionGrns").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BuffTargetGrn.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRate(data.get("rate") == null || data.get("rate").isNull() ? null : data.get("rate").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("targetActionName", getTargetActionName());
                put("targetFieldName", getTargetFieldName());
                put("conditionGrns", getConditionGrns() == null ? null :
                    getConditionGrns().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("rate", getRate());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.targetActionName == null) ? 0 : this.targetActionName.hashCode());
        result = prime * result + ((this.targetFieldName == null) ? 0 : this.targetFieldName.hashCode());
        result = prime * result + ((this.conditionGrns == null) ? 0 : this.conditionGrns.hashCode());
        result = prime * result + ((this.rate == null) ? 0 : this.rate.hashCode());
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
		BuffTargetAction other = (BuffTargetAction) o;
		if (targetActionName == null) {
			return other.targetActionName == null;
		} else if (!targetActionName.equals(other.targetActionName)) {
			return false;
		}
		if (targetFieldName == null) {
			return other.targetFieldName == null;
		} else if (!targetFieldName.equals(other.targetFieldName)) {
			return false;
		}
		if (conditionGrns == null) {
			return other.conditionGrns == null;
		} else if (!conditionGrns.equals(other.conditionGrns)) {
			return false;
		}
		if (rate == null) {
			return other.rate == null;
		} else if (!rate.equals(other.rate)) {
			return false;
		}
		return true;
	}
}