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
public class UnityAd implements IModel, Serializable {
	private List<String> keys;
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public UnityAd withKeys(List<String> keys) {
		this.keys = keys;
		return this;
	}

    public static UnityAd fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnityAd()
            .withKeys(data.get("keys") == null || data.get("keys").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("keys").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("keys", getKeys() == null ? null :
                    getKeys().stream().map(item -> {
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
        result = prime * result + ((this.keys == null) ? 0 : this.keys.hashCode());
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
		UnityAd other = (UnityAd) o;
		if (keys == null) {
			return other.keys == null;
		} else if (!keys.equals(other.keys)) {
			return false;
		}
		return true;
	}
}