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

package io.gs2.jobQueue.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * ジョブの実行結果
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class JobResultBody implements IModel, Serializable {
	/** 試行回数 */
	protected Integer tryNumber;

	/**
	 * 試行回数を取得
	 *
	 * @return 試行回数
	 */
	public Integer getTryNumber() {
		return tryNumber;
	}

	/**
	 * 試行回数を設定
	 *
	 * @param tryNumber 試行回数
	 */
	public void setTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
	}

	/**
	 * 試行回数を設定
	 *
	 * @param tryNumber 試行回数
	 * @return this
	 */
	public JobResultBody withTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
		return this;
	}
	/** ステータスコード */
	protected Integer statusCode;

	/**
	 * ステータスコードを取得
	 *
	 * @return ステータスコード
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * ステータスコードを設定
	 *
	 * @param statusCode ステータスコード
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * ステータスコードを設定
	 *
	 * @param statusCode ステータスコード
	 * @return this
	 */
	public JobResultBody withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	/** レスポンスの内容 */
	protected String result;

	/**
	 * レスポンスの内容を取得
	 *
	 * @return レスポンスの内容
	 */
	public String getResult() {
		return result;
	}

	/**
	 * レスポンスの内容を設定
	 *
	 * @param result レスポンスの内容
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * レスポンスの内容を設定
	 *
	 * @param result レスポンスの内容
	 * @return this
	 */
	public JobResultBody withResult(String result) {
		this.result = result;
		return this;
	}
	/** 実行日時 */
	protected Long tryAt;

	/**
	 * 実行日時を取得
	 *
	 * @return 実行日時
	 */
	public Long getTryAt() {
		return tryAt;
	}

	/**
	 * 実行日時を設定
	 *
	 * @param tryAt 実行日時
	 */
	public void setTryAt(Long tryAt) {
		this.tryAt = tryAt;
	}

	/**
	 * 実行日時を設定
	 *
	 * @param tryAt 実行日時
	 * @return this
	 */
	public JobResultBody withTryAt(Long tryAt) {
		this.tryAt = tryAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("tryNumber", this.getTryNumber())
            .put("statusCode", this.getStatusCode())
            .put("result", this.getResult())
            .put("tryAt", this.getTryAt());
        return body_;
    }
}