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

package io.gs2.mission.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.mission.model.*;
import io.gs2.mission.model.TargetCounterModel;
import io.gs2.mission.model.VerifyAction;
import io.gs2.mission.model.AcquireAction;
import io.gs2.mission.model.MissionTaskModel;
import io.gs2.mission.model.MissionGroupModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetMissionGroupModelResult implements IResult, Serializable {
    private MissionGroupModel item;

	public MissionGroupModel getItem() {
		return item;
	}

	public void setItem(MissionGroupModel item) {
		this.item = item;
	}

	public GetMissionGroupModelResult withItem(MissionGroupModel item) {
		this.item = item;
		return this;
	}

    public static GetMissionGroupModelResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetMissionGroupModelResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : MissionGroupModel.fromJson(data.get("item")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
            }}
        );
    }
}