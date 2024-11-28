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

package io.gs2.script.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.script.model.*;
import io.gs2.script.model.VerifyAction;
import io.gs2.script.model.ConsumeAction;
import io.gs2.script.model.AcquireAction;
import io.gs2.script.model.Transaction;
import io.gs2.script.model.RandomUsed;
import io.gs2.script.model.RandomStatus;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class InvokeByStampSheetResult implements IResult, Serializable {
    private Integer code;
    private String result;
    private Transaction transaction;
    private RandomStatus randomStatus;
    private Integer executeTime;
    private Integer charged;
    private List<String> output;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public InvokeByStampSheetResult withCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public InvokeByStampSheetResult withResult(String result) {
		this.result = result;
		return this;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public InvokeByStampSheetResult withTransaction(Transaction transaction) {
		this.transaction = transaction;
		return this;
	}

	public RandomStatus getRandomStatus() {
		return randomStatus;
	}

	public void setRandomStatus(RandomStatus randomStatus) {
		this.randomStatus = randomStatus;
	}

	public InvokeByStampSheetResult withRandomStatus(RandomStatus randomStatus) {
		this.randomStatus = randomStatus;
		return this;
	}

	public Integer getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}

	public InvokeByStampSheetResult withExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
		return this;
	}

	public Integer getCharged() {
		return charged;
	}

	public void setCharged(Integer charged) {
		this.charged = charged;
	}

	public InvokeByStampSheetResult withCharged(Integer charged) {
		this.charged = charged;
		return this;
	}

	public List<String> getOutput() {
		return output;
	}

	public void setOutput(List<String> output) {
		this.output = output;
	}

	public InvokeByStampSheetResult withOutput(List<String> output) {
		this.output = output;
		return this;
	}

    public static InvokeByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new InvokeByStampSheetResult()
            .withCode(data.get("code") == null || data.get("code").isNull() ? null : data.get("code").intValue())
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : data.get("result").asText())
            .withTransaction(data.get("transaction") == null || data.get("transaction").isNull() ? null : Transaction.fromJson(data.get("transaction")))
            .withRandomStatus(data.get("randomStatus") == null || data.get("randomStatus").isNull() ? null : RandomStatus.fromJson(data.get("randomStatus")))
            .withExecuteTime(data.get("executeTime") == null || data.get("executeTime").isNull() ? null : data.get("executeTime").intValue())
            .withCharged(data.get("charged") == null || data.get("charged").isNull() ? null : data.get("charged").intValue())
            .withOutput(data.get("output") == null || data.get("output").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("output").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("code", getCode());
                put("result", getResult());
                put("transaction", getTransaction() != null ? getTransaction().toJson() : null);
                put("randomStatus", getRandomStatus() != null ? getRandomStatus().toJson() : null);
                put("executeTime", getExecuteTime());
                put("charged", getCharged());
                put("output", getOutput() == null ? null :
                    getOutput().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}