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

package io.gs2.news.model;

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
public class View implements IModel, Serializable {
	private List<Content> contents;
	private List<Content> removeContents;
	public List<Content> getContents() {
		return contents;
	}
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	public View withContents(List<Content> contents) {
		this.contents = contents;
		return this;
	}
	public List<Content> getRemoveContents() {
		return removeContents;
	}
	public void setRemoveContents(List<Content> removeContents) {
		this.removeContents = removeContents;
	}
	public View withRemoveContents(List<Content> removeContents) {
		this.removeContents = removeContents;
		return this;
	}

    public static View fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new View()
            .withContents(data.get("contents") == null || data.get("contents").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("contents").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Content.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRemoveContents(data.get("removeContents") == null || data.get("removeContents").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("removeContents").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Content.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("contents", getContents() == null ? null :
                    getContents().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("removeContents", getRemoveContents() == null ? null :
                    getRemoveContents().stream().map(item -> {
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
        result = prime * result + ((this.contents == null) ? 0 : this.contents.hashCode());
        result = prime * result + ((this.removeContents == null) ? 0 : this.removeContents.hashCode());
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
		View other = (View) o;
		if (contents == null) {
			return other.contents == null;
		} else if (!contents.equals(other.contents)) {
			return false;
		}
		if (removeContents == null) {
			return other.removeContents == null;
		} else if (!removeContents.equals(other.removeContents)) {
			return false;
		}
		return true;
	}
}