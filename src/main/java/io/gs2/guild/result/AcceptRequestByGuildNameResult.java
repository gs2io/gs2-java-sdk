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

package io.gs2.guild.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.guild.model.*;
import io.gs2.guild.model.ReceiveMemberRequest;
import io.gs2.guild.model.RoleModel;
import io.gs2.guild.model.Member;
import io.gs2.guild.model.Guild;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcceptRequestByGuildNameResult implements IResult, Serializable {
    private ReceiveMemberRequest item;
    private Guild guild;

	public ReceiveMemberRequest getItem() {
		return item;
	}

	public void setItem(ReceiveMemberRequest item) {
		this.item = item;
	}

	public AcceptRequestByGuildNameResult withItem(ReceiveMemberRequest item) {
		this.item = item;
		return this;
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public AcceptRequestByGuildNameResult withGuild(Guild guild) {
		this.guild = guild;
		return this;
	}

    public static AcceptRequestByGuildNameResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcceptRequestByGuildNameResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : ReceiveMemberRequest.fromJson(data.get("item")))
            .withGuild(data.get("guild") == null || data.get("guild").isNull() ? null : Guild.fromJson(data.get("guild")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("guild", getGuild() != null ? getGuild().toJson() : null);
            }}
        );
    }
}