/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.gs2.control;

import io.gs2.model.IRequest;

import java.io.Serializable;

@SuppressWarnings("serial")
abstract public class Gs2BasicRequest<T extends Gs2BasicRequest<?>> implements IRequest, Serializable {

	/** GS2リクエストID */
	private String xGs2RequestId;
	/** コンテキストスタック */
	private String contextStack;

    /**
     * GS2リクエストIDを取得。
     *
     * @return GS2リクエストID
     */
    public String getRequestId() {
        return xGs2RequestId;
    }

    /**
     * GS2リクエストIDを設定。
     *
     * @param xGs2RequestId GS2リクエストID
     */
    public void setRequestId(String xGs2RequestId) {
        this.xGs2RequestId = xGs2RequestId;
    }

    /**
     * GS2リクエストIDを設定。
     *
     * @param xGs2RequestId GS2リクエストID
	 * @return this
     */
    @SuppressWarnings("unchecked")
    public T withRequestId(String xGs2RequestId) {
        setRequestId(xGs2RequestId);
        return (T)this;
    }

	/**
	 * コンテキストスタックを取得。
	 *
	 * @return コンテキストスタック
	 */
	public String getContextStack() {
		return contextStack;
	}

	/**
	 * コンテキストスタックを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 *
	 * @param contextStack コンテキストスタック
	 */
	@Deprecated
	public void setContextStack(String contextStack) {
		this.contextStack = contextStack;
	}

	/**
	 * コンテキストスタックを設定。
	 * 通常は自動的に計算されるため、この値を設定する必要はありません。
	 *
	 * @param contextStack コンテキストスタック
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public T withContextStack(String contextStack) {
		setContextStack(contextStack);
		return (T)this;
	}

}
