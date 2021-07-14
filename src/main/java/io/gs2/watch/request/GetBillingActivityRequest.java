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

package io.gs2.watch.request;

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
public class GetBillingActivityRequest extends Gs2BasicRequest<GetBillingActivityRequest> {
    private Integer year;
    private Integer month;
    private String service;
    private String activityType;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public GetBillingActivityRequest withYear(Integer year) {
		this.year = year;
		return this;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public GetBillingActivityRequest withMonth(Integer month) {
		this.month = month;
		return this;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public GetBillingActivityRequest withService(String service) {
		this.service = service;
		return this;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public GetBillingActivityRequest withActivityType(String activityType) {
		this.activityType = activityType;
		return this;
	}

    public static GetBillingActivityRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetBillingActivityRequest()
            .withYear(data.get("year") == null || data.get("year").isNull() ? null : data.get("year").intValue())
            .withMonth(data.get("month") == null || data.get("month").isNull() ? null : data.get("month").intValue())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withActivityType(data.get("activityType") == null || data.get("activityType").isNull() ? null : data.get("activityType").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("year", getYear());
                put("month", getMonth());
                put("service", getService());
                put("activityType", getActivityType());
            }}
        );
    }
}