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

package io.gs2.datastore.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.datastore.model.*;
import io.gs2.datastore.model.DataObject;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareDownloadByGenerationResult implements IResult, Serializable {
    private DataObject item;
    private String fileUrl;
    private Long contentLength;

	public DataObject getItem() {
		return item;
	}

	public void setItem(DataObject item) {
		this.item = item;
	}

	public PrepareDownloadByGenerationResult withItem(DataObject item) {
		this.item = item;
		return this;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public PrepareDownloadByGenerationResult withFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		return this;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public PrepareDownloadByGenerationResult withContentLength(Long contentLength) {
		this.contentLength = contentLength;
		return this;
	}

    public static PrepareDownloadByGenerationResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrepareDownloadByGenerationResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : DataObject.fromJson(data.get("item")))
            .withFileUrl(data.get("fileUrl") == null || data.get("fileUrl").isNull() ? null : data.get("fileUrl").asText())
            .withContentLength(data.get("contentLength") == null || data.get("contentLength").isNull() ? null : data.get("contentLength").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("fileUrl", getFileUrl());
                put("contentLength", getContentLength());
            }}
        );
    }
}