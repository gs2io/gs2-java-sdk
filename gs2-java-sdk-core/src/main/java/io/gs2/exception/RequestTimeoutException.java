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
package io.gs2.exception;

import java.util.List;

import io.gs2.model.RequestError;

@SuppressWarnings("serial")
public class RequestTimeoutException extends Gs2Exception {

	public RequestTimeoutException(String message) {
		super(message);
	}

	public RequestTimeoutException(List<RequestError> errors) {
		super(errors);
	}
}
