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

package io.gs2.news.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * お知らせ記事
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class News implements IModel, Serializable {
	/** セクション名 */
	protected String section;

	/**
	 * セクション名を取得
	 *
	 * @return セクション名
	 */
	public String getSection() {
		return section;
	}

	/**
	 * セクション名を設定
	 *
	 * @param section セクション名
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * セクション名を設定
	 *
	 * @param section セクション名
	 * @return this
	 */
	public News withSection(String section) {
		this.section = section;
		return this;
	}
	/** コンテンツ名 */
	protected String content;

	/**
	 * コンテンツ名を取得
	 *
	 * @return コンテンツ名
	 */
	public String getContent() {
		return content;
	}

	/**
	 * コンテンツ名を設定
	 *
	 * @param content コンテンツ名
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * コンテンツ名を設定
	 *
	 * @param content コンテンツ名
	 * @return this
	 */
	public News withContent(String content) {
		this.content = content;
		return this;
	}
	/** 記事見出し */
	protected String title;

	/**
	 * 記事見出しを取得
	 *
	 * @return 記事見出し
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 記事見出しを設定
	 *
	 * @param title 記事見出し
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 記事見出しを設定
	 *
	 * @param title 記事見出し
	 * @return this
	 */
	public News withTitle(String title) {
		this.title = title;
		return this;
	}
	/** 配信期間を決定する GS2-Schedule のイベントID */
	protected String scheduleEventId;

	/**
	 * 配信期間を決定する GS2-Schedule のイベントIDを取得
	 *
	 * @return 配信期間を決定する GS2-Schedule のイベントID
	 */
	public String getScheduleEventId() {
		return scheduleEventId;
	}

	/**
	 * 配信期間を決定する GS2-Schedule のイベントIDを設定
	 *
	 * @param scheduleEventId 配信期間を決定する GS2-Schedule のイベントID
	 */
	public void setScheduleEventId(String scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
	}

	/**
	 * 配信期間を決定する GS2-Schedule のイベントIDを設定
	 *
	 * @param scheduleEventId 配信期間を決定する GS2-Schedule のイベントID
	 * @return this
	 */
	public News withScheduleEventId(String scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
		return this;
	}
	/** タイムスタンプ */
	protected Long timestamp;

	/**
	 * タイムスタンプを取得
	 *
	 * @return タイムスタンプ
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * タイムスタンプを設定
	 *
	 * @param timestamp タイムスタンプ
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * タイムスタンプを設定
	 *
	 * @param timestamp タイムスタンプ
	 * @return this
	 */
	public News withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	/** Front Matter */
	protected String frontMatter;

	/**
	 * Front Matterを取得
	 *
	 * @return Front Matter
	 */
	public String getFrontMatter() {
		return frontMatter;
	}

	/**
	 * Front Matterを設定
	 *
	 * @param frontMatter Front Matter
	 */
	public void setFrontMatter(String frontMatter) {
		this.frontMatter = frontMatter;
	}

	/**
	 * Front Matterを設定
	 *
	 * @param frontMatter Front Matter
	 * @return this
	 */
	public News withFrontMatter(String frontMatter) {
		this.frontMatter = frontMatter;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("section", this.getSection())
            .put("content", this.getContent())
            .put("title", this.getTitle())
            .put("scheduleEventId", this.getScheduleEventId())
            .put("timestamp", this.getTimestamp())
            .put("frontMatter", this.getFrontMatter());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.section == null) ? 0 : this.section.hashCode());
        result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.scheduleEventId == null) ? 0 : this.scheduleEventId.hashCode());
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.frontMatter == null) ? 0 : this.frontMatter.hashCode());
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
		News other = (News) o;
		if (section == null) {
			return other.section == null;
		} else if (!section.equals(other.section)) {
			return false;
		}
		if (content == null) {
			return other.content == null;
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (title == null) {
			return other.title == null;
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (scheduleEventId == null) {
			return other.scheduleEventId == null;
		} else if (!scheduleEventId.equals(other.scheduleEventId)) {
			return false;
		}
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (frontMatter == null) {
			return other.frontMatter == null;
		} else if (!frontMatter.equals(other.frontMatter)) {
			return false;
		}
		return true;
	}
}