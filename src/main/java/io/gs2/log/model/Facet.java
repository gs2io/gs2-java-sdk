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

package io.gs2.log.model;

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
public class Facet implements IModel, Serializable {
	private String field;
	private List<FacetValueCount> values;
	private NumericRange range;
	private NumericRange globalRange;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Facet withField(String field) {
		this.field = field;
		return this;
	}
	public List<FacetValueCount> getValues() {
		return values;
	}
	public void setValues(List<FacetValueCount> values) {
		this.values = values;
	}
	public Facet withValues(List<FacetValueCount> values) {
		this.values = values;
		return this;
	}
	public NumericRange getRange() {
		return range;
	}
	public void setRange(NumericRange range) {
		this.range = range;
	}
	public Facet withRange(NumericRange range) {
		this.range = range;
		return this;
	}
	public NumericRange getGlobalRange() {
		return globalRange;
	}
	public void setGlobalRange(NumericRange globalRange) {
		this.globalRange = globalRange;
	}
	public Facet withGlobalRange(NumericRange globalRange) {
		this.globalRange = globalRange;
		return this;
	}

    public static Facet fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Facet()
            .withField(data.get("field") == null || data.get("field").isNull() ? null : data.get("field").asText())
            .withValues(data.get("values") == null || data.get("values").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("values").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return FacetValueCount.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRange(data.get("range") == null || data.get("range").isNull() ? null : NumericRange.fromJson(data.get("range")))
            .withGlobalRange(data.get("globalRange") == null || data.get("globalRange").isNull() ? null : NumericRange.fromJson(data.get("globalRange")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("field", getField());
                put("values", getValues() == null ? null :
                    getValues().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("range", getRange() != null ? getRange().toJson() : null);
                put("globalRange", getGlobalRange() != null ? getGlobalRange().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.field == null) ? 0 : this.field.hashCode());
        result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
        result = prime * result + ((this.range == null) ? 0 : this.range.hashCode());
        result = prime * result + ((this.globalRange == null) ? 0 : this.globalRange.hashCode());
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
		Facet other = (Facet) o;
		if (field == null) {
			return other.field == null;
		} else if (!field.equals(other.field)) {
			return false;
		}
		if (values == null) {
			return other.values == null;
		} else if (!values.equals(other.values)) {
			return false;
		}
		if (range == null) {
			return other.range == null;
		} else if (!range.equals(other.range)) {
			return false;
		}
		if (globalRange == null) {
			return other.globalRange == null;
		} else if (!globalRange.equals(other.globalRange)) {
			return false;
		}
		return true;
	}
}