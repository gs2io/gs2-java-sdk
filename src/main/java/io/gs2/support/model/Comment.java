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

package io.gs2.support.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * コメント
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Comment implements Serializable {

	/** 本文 */
	private String body;

	/** コメントID */
	private String commentId;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** ケースID */
	private String issueId;

	/** オーナーID */
	private String ownerId;


	/**
	 * 本文を取得
	 *
	 * @return 本文
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 本文を設定
	 *
	 * @param body 本文
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * コメントIDを取得
	 *
	 * @return コメントID
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * コメントIDを設定
	 *
	 * @param commentId コメントID
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * ケースIDを取得
	 *
	 * @return ケースID
	 */
	public String getIssueId() {
		return issueId;
	}

	/**
	 * ケースIDを設定
	 *
	 * @param issueId ケースID
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

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

}