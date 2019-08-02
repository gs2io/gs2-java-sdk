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
package io.gs2;

/**
 * GS2の設定値
 * 
 * @author Game Server Services, Inc.
 *
 */
public class Gs2Constant {

	/** リクエストのタイムスタンプの有効レンジ(±sec) */
	public static final int REQUEST_VALID_TIME_RANGE = 60 * 5;
	
	/** リトライ回数 */
	public static final int RETRY_NUM = 3;
	
	/** リトライウェイト(msec) */
	public static final int RETRY_WAIT = 1000;
	
	/** APIエンドポイント */
	public static final String ENDPOINT_HOST = "https://asia-northeast1-gs2-on-gcp.cloudfunctions.net";

}
