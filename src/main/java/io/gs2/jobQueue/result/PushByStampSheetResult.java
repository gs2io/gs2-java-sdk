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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PushByStampSheetResult implements IResult, Serializable {
    private List<Job> items;
    private Boolean autoRun;

	public List<Job> getItems() {
		return items;
	}

	public void setItems(List<Job> items) {
		this.items = items;
	}

	public PushByStampSheetResult withItems(List<Job> items) {
		this.items = items;
		return this;
	}

	public Boolean getAutoRun() {
		return autoRun;
	}

	public void setAutoRun(Boolean autoRun) {
		this.autoRun = autoRun;
	}

	public PushByStampSheetResult withAutoRun(Boolean autoRun) {
		this.autoRun = autoRun;
		return this;
	}

    public static PushByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PushByStampSheetResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<Job>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Job.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAutoRun(data.get("autoRun") == null || data.get("autoRun").isNull() ? null : data.get("autoRun").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<Job>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("autoRun", getAutoRun());
            }}
        );
    }
}