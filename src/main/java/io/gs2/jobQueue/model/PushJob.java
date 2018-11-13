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

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * キューに追加するジョブ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PushJob implements Serializable {

	/** スクリプト名 */
	private String scriptName;

	/** 引数 */
	private String args;

	/** 最大リトライ回数 */
	private Integer maxRetry;


	/**
	 * スクリプト名を取得
	 *
	 * @return スクリプト名
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * スクリプト名を設定
	 *
	 * @param scriptName スクリプト名
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * スクリプト名を設定
	 *
	 * @param scriptName スクリプト名
	 * @return this
	 */
	public PushJob withScriptName(String scriptName) {
		this.scriptName = scriptName;
		return this;
	}

	/**
	 * 引数を取得
	 *
	 * @return 引数
	 */
	public String getArgs() {
		return args;
	}

	/**
	 * 引数を設定
	 *
	 * @param args 引数
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * 引数を設定
	 *
	 * @param args 引数
	 * @return this
	 */
	public PushJob withArgs(String args) {
		this.args = args;
		return this;
	}

	/**
	 * 最大リトライ回数を取得
	 *
	 * @return 最大リトライ回数
	 */
	public Integer getMaxRetry() {
		return maxRetry;
	}

	/**
	 * 最大リトライ回数を設定
	 *
	 * @param maxRetry 最大リトライ回数
	 */
	public void setMaxRetry(Integer maxRetry) {
		this.maxRetry = maxRetry;
	}

	/**
	 * 最大リトライ回数を設定
	 *
	 * @param maxRetry 最大リトライ回数
	 * @return this
	 */
	public PushJob withMaxRetry(Integer maxRetry) {
		this.maxRetry = maxRetry;
		return this;
	}


    public ObjectNode toJson() {
		return JsonNodeFactory.instance.objectNode()

            .put("scriptName", this.getScriptName())
            .put("args", this.getArgs())
            .put("maxRetry", this.getMaxRetry());
    }
}