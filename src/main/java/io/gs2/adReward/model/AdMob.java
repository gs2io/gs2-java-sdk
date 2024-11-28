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
public class AdMob implements IModel, Serializable {
	private List<String> allowAdUnitIds;
	public List<String> getAllowAdUnitIds() {
		return allowAdUnitIds;
	}
	public void setAllowAdUnitIds(List<String> allowAdUnitIds) {
		this.allowAdUnitIds = allowAdUnitIds;
	}
	public AdMob withAllowAdUnitIds(List<String> allowAdUnitIds) {
		this.allowAdUnitIds = allowAdUnitIds;
		return this;
	}

    public static AdMob fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AdMob()
            .withAllowAdUnitIds(data.get("allowAdUnitIds") == null || data.get("allowAdUnitIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("allowAdUnitIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("allowAdUnitIds", getAllowAdUnitIds() == null ? null :
                    getAllowAdUnitIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.allowAdUnitIds == null) ? 0 : this.allowAdUnitIds.hashCode());
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
		AdMob other = (AdMob) o;
		if (allowAdUnitIds == null) {
			return other.allowAdUnitIds == null;
		} else if (!allowAdUnitIds.equals(other.allowAdUnitIds)) {
			return false;
		}
		return true;
	}
}