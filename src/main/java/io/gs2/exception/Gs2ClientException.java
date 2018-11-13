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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.gs2.model.RequestError;

@SuppressWarnings("serial")
abstract public class Gs2ClientException extends RuntimeException {

	List<RequestError> errors = new ArrayList<>();
	
	public Gs2ClientException(String message) {
		super(message);
		try {
			JSONArray errors = new JSONArray(getMessage());
			for(int i=0; i<errors.length(); i++) {
				RequestError e = new RequestError();
				JSONObject error = errors.getJSONObject(i);
				e.setComponent(error.getString("component"));
				e.setMessage(error.getString("message"));
				this.errors.add(e);
			}
		} catch (JSONException e) {
			this.errors.add(new RequestError("unknown", getMessage()));
		}
	}
	
	public Gs2ClientException(List<RequestError> errors) {
		this.errors = errors;
	}
	
	public List<RequestError> getErrors() {
		return errors;
	}
}
