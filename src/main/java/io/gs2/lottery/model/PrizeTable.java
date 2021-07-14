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
public class PrizeTable implements IModel, Serializable, Comparable<PrizeTable> {
	private String prizeTableId;
	private String name;
	private String metadata;
	private List<Prize> prizes;

	public String getPrizeTableId() {
		return prizeTableId;
	}

	public void setPrizeTableId(String prizeTableId) {
		this.prizeTableId = prizeTableId;
	}

	public PrizeTable withPrizeTableId(String prizeTableId) {
		this.prizeTableId = prizeTableId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PrizeTable withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public PrizeTable withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}

	public PrizeTable withPrizes(List<Prize> prizes) {
		this.prizes = prizes;
		return this;
	}

    public static PrizeTable fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrizeTable()
            .withPrizeTableId(data.get("prizeTableId") == null || data.get("prizeTableId").isNull() ? null : data.get("prizeTableId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withPrizes(data.get("prizes") == null || data.get("prizes").isNull() ? new ArrayList<Prize>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("prizes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Prize.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("prizeTableId", getPrizeTableId());
                put("name", getName());
                put("metadata", getMetadata());
                put("prizes", getPrizes() == null ? new ArrayList<Prize>() :
                    getPrizes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(PrizeTable o) {
		return prizeTableId.compareTo(o.prizeTableId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prizeTableId == null) ? 0 : this.prizeTableId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.prizes == null) ? 0 : this.prizes.hashCode());
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
		PrizeTable other = (PrizeTable) o;
		if (prizeTableId == null) {
			return other.prizeTableId == null;
		} else if (!prizeTableId.equals(other.prizeTableId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (prizes == null) {
			return other.prizes == null;
		} else if (!prizes.equals(other.prizes)) {
			return false;
		}
		return true;
	}
}