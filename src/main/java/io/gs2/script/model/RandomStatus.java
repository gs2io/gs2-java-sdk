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

package io.gs2.script.model;

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
public class RandomStatus implements IModel, Serializable {
	private Long seed;
	private List<RandomUsed> used;
	public Long getSeed() {
		return seed;
	}
	public void setSeed(Long seed) {
		this.seed = seed;
	}
	public RandomStatus withSeed(Long seed) {
		this.seed = seed;
		return this;
	}
	public List<RandomUsed> getUsed() {
		return used;
	}
	public void setUsed(List<RandomUsed> used) {
		this.used = used;
	}
	public RandomStatus withUsed(List<RandomUsed> used) {
		this.used = used;
		return this;
	}

    public static RandomStatus fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RandomStatus()
            .withSeed(data.get("seed") == null || data.get("seed").isNull() ? null : data.get("seed").longValue())
            .withUsed(data.get("used") == null || data.get("used").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("used").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RandomUsed.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("seed", getSeed());
                put("used", getUsed() == null ? null :
                    getUsed().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.seed == null) ? 0 : this.seed.hashCode());
        result = prime * result + ((this.used == null) ? 0 : this.used.hashCode());
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
		RandomStatus other = (RandomStatus) o;
		if (seed == null) {
			return other.seed == null;
		} else if (!seed.equals(other.seed)) {
			return false;
		}
		if (used == null) {
			return other.used == null;
		} else if (!used.equals(other.used)) {
			return false;
		}
		return true;
	}
}