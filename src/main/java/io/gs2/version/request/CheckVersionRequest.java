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

package io.gs2.version.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.version.model.Version;
import io.gs2.version.model.TargetVersion;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CheckVersionRequest extends Gs2BasicRequest<CheckVersionRequest> {
    private String namespaceName;
    private String accessToken;
    private List<TargetVersion> targetVersions;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CheckVersionRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public CheckVersionRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public List<TargetVersion> getTargetVersions() {
		return targetVersions;
	}
	public void setTargetVersions(List<TargetVersion> targetVersions) {
		this.targetVersions = targetVersions;
	}
	public CheckVersionRequest withTargetVersions(List<TargetVersion> targetVersions) {
		this.targetVersions = targetVersions;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CheckVersionRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CheckVersionRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CheckVersionRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withTargetVersions(data.get("targetVersions") == null || data.get("targetVersions").isNull() ? new ArrayList<TargetVersion>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("targetVersions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return TargetVersion.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("targetVersions", getTargetVersions() == null ? new ArrayList<TargetVersion>() :
                    getTargetVersions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}