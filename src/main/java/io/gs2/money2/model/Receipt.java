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

package io.gs2.money2.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Receipt implements IModel, Serializable {
	private String store;
	private String transactionID;
	private String payload;
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public Receipt withStore(String store) {
		this.store = store;
		return this;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public Receipt withTransactionID(String transactionID) {
		this.transactionID = transactionID;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Receipt withPayload(String payload) {
		this.payload = payload;
		return this;
	}

    public static Receipt fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Receipt()
            .withStore(data.get("Store") == null || data.get("Store").isNull() ? null : data.get("Store").asText())
            .withTransactionID(data.get("TransactionID") == null || data.get("TransactionID").isNull() ? null : data.get("TransactionID").asText())
            .withPayload(data.get("Payload") == null || data.get("Payload").isNull() ? null : data.get("Payload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("Store", getStore());
                put("TransactionID", getTransactionID());
                put("Payload", getPayload());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.store == null) ? 0 : this.store.hashCode());
        result = prime * result + ((this.transactionID == null) ? 0 : this.transactionID.hashCode());
        result = prime * result + ((this.payload == null) ? 0 : this.payload.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Receipt other = (Receipt) o;
		if (store == null) {
			return other.store == null;
		} else if (!store.equals(other.store)) {
			return false;
		}
		if (transactionID == null) {
			return other.transactionID == null;
		} else if (!transactionID.equals(other.transactionID)) {
			return false;
		}
		if (payload == null) {
			return other.payload == null;
		} else if (!payload.equals(other.payload)) {
			return false;
		}
		return true;
	}
}