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

package io.gs2.freeze.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.freeze.model.*;
import io.gs2.freeze.model.Stage;
import io.gs2.freeze.model.Microservice;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetStageResult implements IResult, Serializable {
    private Stage item;
    private List<Microservice> source;
    private List<Microservice> current;

	public Stage getItem() {
		return item;
	}

	public void setItem(Stage item) {
		this.item = item;
	}

	public GetStageResult withItem(Stage item) {
		this.item = item;
		return this;
	}

	public List<Microservice> getSource() {
		return source;
	}

	public void setSource(List<Microservice> source) {
		this.source = source;
	}

	public GetStageResult withSource(List<Microservice> source) {
		this.source = source;
		return this;
	}

	public List<Microservice> getCurrent() {
		return current;
	}

	public void setCurrent(List<Microservice> current) {
		this.current = current;
	}

	public GetStageResult withCurrent(List<Microservice> current) {
		this.current = current;
		return this;
	}

    public static GetStageResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetStageResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Stage.fromJson(data.get("item")))
            .withSource(data.get("source") == null || data.get("source").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("source").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Microservice.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCurrent(data.get("current") == null || data.get("current").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("current").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Microservice.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("source", getSource() == null ? null :
                    getSource().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("current", getCurrent() == null ? null :
                    getCurrent().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}