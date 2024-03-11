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

package io.gs2.dictionary.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ImportUserDataByUserIdRequest extends Gs2BasicRequest<ImportUserDataByUserIdRequest> {
    private String userId;
    private String uploadToken;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ImportUserDataByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public ImportUserDataByUserIdRequest withUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public ImportUserDataByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public ImportUserDataByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static ImportUserDataByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ImportUserDataByUserIdRequest()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withUploadToken(data.get("uploadToken") == null || data.get("uploadToken").isNull() ? null : data.get("uploadToken").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("uploadToken", getUploadToken());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}