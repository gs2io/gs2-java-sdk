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

package io.gs2.lottery.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 排出された景品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DrawnPrize implements IModel, Serializable {
	/** 入手アクションのリスト */
	protected List<AcquireAction> acquireActions;

	/**
	 * 入手アクションのリストを取得
	 *
	 * @return 入手アクションのリスト
	 */
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	/**
	 * 入手アクションのリストを設定
	 *
	 * @param acquireActions 入手アクションのリスト
	 */
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	/**
	 * 入手アクションのリストを設定
	 *
	 * @param acquireActions 入手アクションのリスト
	 * @return this
	 */
	public DrawnPrize withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> acquireActions = new ArrayList<>();
        if(this.acquireActions != null) {
            for(AcquireAction item : this.acquireActions) {
                acquireActions.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode();
        body_.set("acquireActions", JsonNodeFactory.instance.arrayNode().addAll(acquireActions));
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
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
		DrawnPrize other = (DrawnPrize) o;
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
			return false;
		}
		return true;
	}
}