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

package io.gs2.version.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.version.model.*;
import io.gs2.version.model.Version;
import io.gs2.version.model.VersionModel;
import io.gs2.version.model.Status;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CheckVersionResult implements IResult, Serializable {
    private String projectToken;
    private List<Status> warnings;
    private List<Status> errors;

	public String getProjectToken() {
		return projectToken;
	}

	public void setProjectToken(String projectToken) {
		this.projectToken = projectToken;
	}

	public CheckVersionResult withProjectToken(String projectToken) {
		this.projectToken = projectToken;
		return this;
	}

	public List<Status> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<Status> warnings) {
		this.warnings = warnings;
	}

	public CheckVersionResult withWarnings(List<Status> warnings) {
		this.warnings = warnings;
		return this;
	}

	public List<Status> getErrors() {
		return errors;
	}

	public void setErrors(List<Status> errors) {
		this.errors = errors;
	}

	public CheckVersionResult withErrors(List<Status> errors) {
		this.errors = errors;
		return this;
	}

    public static CheckVersionResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CheckVersionResult()
            .withProjectToken(data.get("projectToken") == null || data.get("projectToken").isNull() ? null : data.get("projectToken").asText())
            .withWarnings(data.get("warnings") == null || data.get("warnings").isNull() ? new ArrayList<Status>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("warnings").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Status.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withErrors(data.get("errors") == null || data.get("errors").isNull() ? new ArrayList<Status>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("errors").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Status.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("projectToken", getProjectToken());
                put("warnings", getWarnings() == null ? new ArrayList<Status>() :
                    getWarnings().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("errors", getErrors() == null ? new ArrayList<Status>() :
                    getErrors().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}