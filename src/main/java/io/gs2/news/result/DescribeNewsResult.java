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

package io.gs2.news.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.news.model.*;
import io.gs2.news.model.News;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeNewsResult implements IResult, Serializable {
    private List<News> items;
    private String contentHash;
    private String templateHash;

	public List<News> getItems() {
		return items;
	}

	public void setItems(List<News> items) {
		this.items = items;
	}

	public DescribeNewsResult withItems(List<News> items) {
		this.items = items;
		return this;
	}

	public String getContentHash() {
		return contentHash;
	}

	public void setContentHash(String contentHash) {
		this.contentHash = contentHash;
	}

	public DescribeNewsResult withContentHash(String contentHash) {
		this.contentHash = contentHash;
		return this;
	}

	public String getTemplateHash() {
		return templateHash;
	}

	public void setTemplateHash(String templateHash) {
		this.templateHash = templateHash;
	}

	public DescribeNewsResult withTemplateHash(String templateHash) {
		this.templateHash = templateHash;
		return this;
	}

    public static DescribeNewsResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeNewsResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<News>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return News.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withContentHash(data.get("contentHash") == null || data.get("contentHash").isNull() ? null : data.get("contentHash").asText())
            .withTemplateHash(data.get("templateHash") == null || data.get("templateHash").isNull() ? null : data.get("templateHash").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<News>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("contentHash", getContentHash());
                put("templateHash", getTemplateHash());
            }}
        );
    }
}