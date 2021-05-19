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

package io.gs2.formation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * スクリプト設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScriptSetting implements IModel, Serializable {
	/** 実行前に使用する GS2-Script のスクリプト のGRN */
	protected String triggerScriptId;

	/**
	 * 実行前に使用する GS2-Script のスクリプト のGRNを取得
	 *
	 * @return 実行前に使用する GS2-Script のスクリプト のGRN
	 */
	public String getTriggerScriptId() {
		return triggerScriptId;
	}

	/**
	 * 実行前に使用する GS2-Script のスクリプト のGRNを設定
	 *
	 * @param triggerScriptId 実行前に使用する GS2-Script のスクリプト のGRN
	 */
	public void setTriggerScriptId(String triggerScriptId) {
		this.triggerScriptId = triggerScriptId;
	}

	/**
	 * 実行前に使用する GS2-Script のスクリプト のGRNを設定
	 *
	 * @param triggerScriptId 実行前に使用する GS2-Script のスクリプト のGRN
	 * @return this
	 */
	public ScriptSetting withTriggerScriptId(String triggerScriptId) {
		this.triggerScriptId = triggerScriptId;
		return this;
	}
	/** 完了通知の通知先 */
	protected String doneTriggerTargetType;

	/**
	 * 完了通知の通知先を取得
	 *
	 * @return 完了通知の通知先
	 */
	public String getDoneTriggerTargetType() {
		return doneTriggerTargetType;
	}

	/**
	 * 完了通知の通知先を設定
	 *
	 * @param doneTriggerTargetType 完了通知の通知先
	 */
	public void setDoneTriggerTargetType(String doneTriggerTargetType) {
		this.doneTriggerTargetType = doneTriggerTargetType;
	}

	/**
	 * 完了通知の通知先を設定
	 *
	 * @param doneTriggerTargetType 完了通知の通知先
	 * @return this
	 */
	public ScriptSetting withDoneTriggerTargetType(String doneTriggerTargetType) {
		this.doneTriggerTargetType = doneTriggerTargetType;
		return this;
	}
	/** 完了時に使用する GS2-Script のスクリプト のGRN */
	protected String doneTriggerScriptId;

	/**
	 * 完了時に使用する GS2-Script のスクリプト のGRNを取得
	 *
	 * @return 完了時に使用する GS2-Script のスクリプト のGRN
	 */
	public String getDoneTriggerScriptId() {
		return doneTriggerScriptId;
	}

	/**
	 * 完了時に使用する GS2-Script のスクリプト のGRNを設定
	 *
	 * @param doneTriggerScriptId 完了時に使用する GS2-Script のスクリプト のGRN
	 */
	public void setDoneTriggerScriptId(String doneTriggerScriptId) {
		this.doneTriggerScriptId = doneTriggerScriptId;
	}

	/**
	 * 完了時に使用する GS2-Script のスクリプト のGRNを設定
	 *
	 * @param doneTriggerScriptId 完了時に使用する GS2-Script のスクリプト のGRN
	 * @return this
	 */
	public ScriptSetting withDoneTriggerScriptId(String doneTriggerScriptId) {
		this.doneTriggerScriptId = doneTriggerScriptId;
		return this;
	}
	/** 完了時に使用する GS2-JobQueue のネームスペース のGRN */
	protected String doneTriggerQueueNamespaceId;

	/**
	 * 完了時に使用する GS2-JobQueue のネームスペース のGRNを取得
	 *
	 * @return 完了時に使用する GS2-JobQueue のネームスペース のGRN
	 */
	public String getDoneTriggerQueueNamespaceId() {
		return doneTriggerQueueNamespaceId;
	}

	/**
	 * 完了時に使用する GS2-JobQueue のネームスペース のGRNを設定
	 *
	 * @param doneTriggerQueueNamespaceId 完了時に使用する GS2-JobQueue のネームスペース のGRN
	 */
	public void setDoneTriggerQueueNamespaceId(String doneTriggerQueueNamespaceId) {
		this.doneTriggerQueueNamespaceId = doneTriggerQueueNamespaceId;
	}

	/**
	 * 完了時に使用する GS2-JobQueue のネームスペース のGRNを設定
	 *
	 * @param doneTriggerQueueNamespaceId 完了時に使用する GS2-JobQueue のネームスペース のGRN
	 * @return this
	 */
	public ScriptSetting withDoneTriggerQueueNamespaceId(String doneTriggerQueueNamespaceId) {
		this.doneTriggerQueueNamespaceId = doneTriggerQueueNamespaceId;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("triggerScriptId", this.getTriggerScriptId())
            .put("doneTriggerTargetType", this.getDoneTriggerTargetType())
            .put("doneTriggerScriptId", this.getDoneTriggerScriptId())
            .put("doneTriggerQueueNamespaceId", this.getDoneTriggerQueueNamespaceId());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.triggerScriptId == null) ? 0 : this.triggerScriptId.hashCode());
        result = prime * result + ((this.doneTriggerTargetType == null) ? 0 : this.doneTriggerTargetType.hashCode());
        result = prime * result + ((this.doneTriggerScriptId == null) ? 0 : this.doneTriggerScriptId.hashCode());
        result = prime * result + ((this.doneTriggerQueueNamespaceId == null) ? 0 : this.doneTriggerQueueNamespaceId.hashCode());
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
		ScriptSetting other = (ScriptSetting) o;
		if (triggerScriptId == null) {
			return other.triggerScriptId == null;
		} else if (!triggerScriptId.equals(other.triggerScriptId)) {
			return false;
		}
		if (doneTriggerTargetType == null) {
			return other.doneTriggerTargetType == null;
		} else if (!doneTriggerTargetType.equals(other.doneTriggerTargetType)) {
			return false;
		}
		if (doneTriggerScriptId == null) {
			return other.doneTriggerScriptId == null;
		} else if (!doneTriggerScriptId.equals(other.doneTriggerScriptId)) {
			return false;
		}
		if (doneTriggerQueueNamespaceId == null) {
			return other.doneTriggerQueueNamespaceId == null;
		} else if (!doneTriggerQueueNamespaceId.equals(other.doneTriggerQueueNamespaceId)) {
			return false;
		}
		return true;
	}
}