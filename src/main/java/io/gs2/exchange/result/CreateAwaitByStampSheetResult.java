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

package io.gs2.exchange.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.exchange.model.*;
import io.gs2.exchange.model.Await;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateAwaitByStampSheetResult implements IResult, Serializable {
    private Await item;
    private Long unlockAt;

	public Await getItem() {
		return item;
	}

	public void setItem(Await item) {
		this.item = item;
	}

	public CreateAwaitByStampSheetResult withItem(Await item) {
		this.item = item;
		return this;
	}

	public Long getUnlockAt() {
		return unlockAt;
	}

	public void setUnlockAt(Long unlockAt) {
		this.unlockAt = unlockAt;
	}

	public CreateAwaitByStampSheetResult withUnlockAt(Long unlockAt) {
		this.unlockAt = unlockAt;
		return this;
	}

    public static CreateAwaitByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateAwaitByStampSheetResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Await.fromJson(data.get("item")))
            .withUnlockAt(data.get("unlockAt") == null || data.get("unlockAt").isNull() ? null : data.get("unlockAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("unlockAt", getUnlockAt());
            }}
        );
    }
}