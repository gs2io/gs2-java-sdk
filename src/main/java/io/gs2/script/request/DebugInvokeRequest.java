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

package io.gs2.script.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.script.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スクリプトを実行します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DebugInvokeRequest extends Gs2BasicRequest<DebugInvokeRequest> {

    /** スクリプト */
    private String script;

    /**
     * スクリプトを取得
     *
     * @return スクリプトを実行します
     */
    public String getScript() {
        return script;
    }

    /**
     * スクリプトを設定
     *
     * @param script スクリプトを実行します
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * スクリプトを設定
     *
     * @param script スクリプトを実行します
     * @return this
     */
    public DebugInvokeRequest withScript(String script) {
        setScript(script);
        return this;
    }

    /** None */
    private String args;

    /**
     * Noneを取得
     *
     * @return スクリプトを実行します
     */
    public String getArgs() {
        return args;
    }

    /**
     * Noneを設定
     *
     * @param args スクリプトを実行します
     */
    public void setArgs(String args) {
        this.args = args;
    }

    /**
     * Noneを設定
     *
     * @param args スクリプトを実行します
     * @return this
     */
    public DebugInvokeRequest withArgs(String args) {
        setArgs(args);
        return this;
    }

}