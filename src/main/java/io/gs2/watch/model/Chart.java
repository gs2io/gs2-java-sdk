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

package io.gs2.watch.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * チャート
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Chart implements IModel, Serializable, Comparable<Chart> {
	/** Datadog のJSON 形式のグラフ定義 */
	protected String chartId;

	/**
	 * Datadog のJSON 形式のグラフ定義を取得
	 *
	 * @return Datadog のJSON 形式のグラフ定義
	 */
	public String getChartId() {
		return chartId;
	}

	/**
	 * Datadog のJSON 形式のグラフ定義を設定
	 *
	 * @param chartId Datadog のJSON 形式のグラフ定義
	 */
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}

	/**
	 * Datadog のJSON 形式のグラフ定義を設定
	 *
	 * @param chartId Datadog のJSON 形式のグラフ定義
	 * @return this
	 */
	public Chart withChartId(String chartId) {
		this.chartId = chartId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Chart withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** Datadog から払い出された組み込みID */
	protected String embedId;

	/**
	 * Datadog から払い出された組み込みIDを取得
	 *
	 * @return Datadog から払い出された組み込みID
	 */
	public String getEmbedId() {
		return embedId;
	}

	/**
	 * Datadog から払い出された組み込みIDを設定
	 *
	 * @param embedId Datadog から払い出された組み込みID
	 */
	public void setEmbedId(String embedId) {
		this.embedId = embedId;
	}

	/**
	 * Datadog から払い出された組み込みIDを設定
	 *
	 * @param embedId Datadog から払い出された組み込みID
	 * @return this
	 */
	public Chart withEmbedId(String embedId) {
		this.embedId = embedId;
		return this;
	}
	/** Datadog から払い出された組み込み用HTML */
	protected String html;

	/**
	 * Datadog から払い出された組み込み用HTMLを取得
	 *
	 * @return Datadog から払い出された組み込み用HTML
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * Datadog から払い出された組み込み用HTMLを設定
	 *
	 * @param html Datadog から払い出された組み込み用HTML
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * Datadog から払い出された組み込み用HTMLを設定
	 *
	 * @param html Datadog から払い出された組み込み用HTML
	 * @return this
	 */
	public Chart withHtml(String html) {
		this.html = html;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("chartId", this.getChartId())
            .put("ownerId", this.getOwnerId())
            .put("embedId", this.getEmbedId())
            .put("html", this.getHtml());
        return body_;
    }
	@Override
	public int compareTo(Chart o) {
		return chartId.compareTo(o.chartId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.chartId == null) ? 0 : this.chartId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.embedId == null) ? 0 : this.embedId.hashCode());
        result = prime * result + ((this.html == null) ? 0 : this.html.hashCode());
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
		Chart other = (Chart) o;
		if (chartId == null) {
			return other.chartId == null;
		} else if (!chartId.equals(other.chartId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (embedId == null) {
			return other.embedId == null;
		} else if (!embedId.equals(other.embedId)) {
			return false;
		}
		if (html == null) {
			return other.html == null;
		} else if (!html.equals(other.html)) {
			return false;
		}
		return true;
	}
}