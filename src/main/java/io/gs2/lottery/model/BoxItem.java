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

package io.gs2.lottery.model;

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
public class BoxItem implements IModel, Serializable {
	private String prizeId;
	private List<AcquireAction> acquireActions;
	private Integer remaining;
	private Integer initial;
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}
	public BoxItem withPrizeId(String prizeId) {
		this.prizeId = prizeId;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public BoxItem withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	public Integer getRemaining() {
		return remaining;
	}
	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}
	public BoxItem withRemaining(Integer remaining) {
		this.remaining = remaining;
		return this;
	}
	public Integer getInitial() {
		return initial;
	}
	public void setInitial(Integer initial) {
		this.initial = initial;
	}
	public BoxItem withInitial(Integer initial) {
		this.initial = initial;
		return this;
	}

    public static BoxItem fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BoxItem()
            .withPrizeId(data.get("prizeId") == null || data.get("prizeId").isNull() ? null : data.get("prizeId").asText())
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRemaining(data.get("remaining") == null || data.get("remaining").isNull() ? null : data.get("remaining").intValue())
            .withInitial(data.get("initial") == null || data.get("initial").isNull() ? null : data.get("initial").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("prizeId", getPrizeId());
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("remaining", getRemaining());
                put("initial", getInitial());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prizeId == null) ? 0 : this.prizeId.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
        result = prime * result + ((this.remaining == null) ? 0 : this.remaining.hashCode());
        result = prime * result + ((this.initial == null) ? 0 : this.initial.hashCode());
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
		BoxItem other = (BoxItem) o;
		if (prizeId == null) {
			return other.prizeId == null;
		} else if (!prizeId.equals(other.prizeId)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
			return false;
		}
		if (remaining == null) {
			return other.remaining == null;
		} else if (!remaining.equals(other.remaining)) {
			return false;
		}
		if (initial == null) {
			return other.initial == null;
		} else if (!initial.equals(other.initial)) {
			return false;
		}
		return true;
	}
}