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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ロギング通知設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LogSetting implements IModel, Serializable {
	/** ログの記録に使用する GS2-Log のネームスペース のGRN */
	protected String loggingNamespaceId;

	/**
	 * ログの記録に使用する GS2-Log のネームスペース のGRNを取得
	 *
	 * @return ログの記録に使用する GS2-Log のネームスペース のGRN
	 */
	public String getLoggingNamespaceId() {
		return loggingNamespaceId;
	}

	/**
	 * ログの記録に使用する GS2-Log のネームスペース のGRNを設定
	 *
	 * @param loggingNamespaceId ログの記録に使用する GS2-Log のネームスペース のGRN
	 */
	public void setLoggingNamespaceId(String loggingNamespaceId) {
		this.loggingNamespaceId = loggingNamespaceId;
	}

	/**
	 * ログの記録に使用する GS2-Log のネームスペース のGRNを設定
	 *
	 * @param loggingNamespaceId ログの記録に使用する GS2-Log のネームスペース のGRN
	 * @return this
	 */
	public LogSetting withLoggingNamespaceId(String loggingNamespaceId) {
		this.loggingNamespaceId = loggingNamespaceId;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("loggingNamespaceId", this.getLoggingNamespaceId());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.loggingNamespaceId == null) ? 0 : this.loggingNamespaceId.hashCode());
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
		LogSetting other = (LogSetting) o;
		if (loggingNamespaceId == null) {
			return other.loggingNamespaceId == null;
		} else if (!loggingNamespaceId.equals(other.loggingNamespaceId)) {
			return false;
		}
		return true;
	}
}