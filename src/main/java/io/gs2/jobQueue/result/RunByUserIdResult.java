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

package io.gs2.jobQueue.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.jobQueue.model.*;
import io.gs2.jobQueue.model.Job;
import io.gs2.jobQueue.model.JobResultBody;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunByUserIdResult implements IResult, Serializable {
    private Job item;
    private JobResultBody result;
    private Boolean isLastJob;

	public Job getItem() {
		return item;
	}

	public void setItem(Job item) {
		this.item = item;
	}

	public RunByUserIdResult withItem(Job item) {
		this.item = item;
		return this;
	}

	public JobResultBody getResult() {
		return result;
	}

	public void setResult(JobResultBody result) {
		this.result = result;
	}

	public RunByUserIdResult withResult(JobResultBody result) {
		this.result = result;
		return this;
	}

	public Boolean getIsLastJob() {
		return isLastJob;
	}

	public void setIsLastJob(Boolean isLastJob) {
		this.isLastJob = isLastJob;
	}

	public RunByUserIdResult withIsLastJob(Boolean isLastJob) {
		this.isLastJob = isLastJob;
		return this;
	}

    public static RunByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RunByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Job.fromJson(data.get("item")))
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : JobResultBody.fromJson(data.get("result")))
            .withIsLastJob(data.get("isLastJob") == null || data.get("isLastJob").isNull() ? null : data.get("isLastJob").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("result", getResult() != null ? getResult().toJson() : null);
                put("isLastJob", getIsLastJob());
            }}
        );
    }
}