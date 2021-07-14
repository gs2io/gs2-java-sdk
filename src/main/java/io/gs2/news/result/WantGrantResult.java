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
import io.gs2.news.model.SetCookieRequestEntry;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WantGrantResult implements IResult, Serializable {
    private List<SetCookieRequestEntry> items;
    private String browserUrl;
    private String zipUrl;

	public List<SetCookieRequestEntry> getItems() {
		return items;
	}

	public void setItems(List<SetCookieRequestEntry> items) {
		this.items = items;
	}

	public WantGrantResult withItems(List<SetCookieRequestEntry> items) {
		this.items = items;
		return this;
	}

	public String getBrowserUrl() {
		return browserUrl;
	}

	public void setBrowserUrl(String browserUrl) {
		this.browserUrl = browserUrl;
	}

	public WantGrantResult withBrowserUrl(String browserUrl) {
		this.browserUrl = browserUrl;
		return this;
	}

	public String getZipUrl() {
		return zipUrl;
	}

	public void setZipUrl(String zipUrl) {
		this.zipUrl = zipUrl;
	}

	public WantGrantResult withZipUrl(String zipUrl) {
		this.zipUrl = zipUrl;
		return this;
	}

    public static WantGrantResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WantGrantResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<SetCookieRequestEntry>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SetCookieRequestEntry.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBrowserUrl(data.get("browserUrl") == null || data.get("browserUrl").isNull() ? null : data.get("browserUrl").asText())
            .withZipUrl(data.get("zipUrl") == null || data.get("zipUrl").isNull() ? null : data.get("zipUrl").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<SetCookieRequestEntry>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("browserUrl", getBrowserUrl());
                put("zipUrl", getZipUrl());
            }}
        );
    }
}