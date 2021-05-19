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

package io.gs2.experience.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.experience.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ランクキャップ取得時 に実行されるスクリプト のGRN */
    private String experienceCapScriptId;

    /**
     * ランクキャップ取得時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getExperienceCapScriptId() {
        return experienceCapScriptId;
    }

    /**
     * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
     *
     * @param experienceCapScriptId ネームスペースを新規作成
     */
    public void setExperienceCapScriptId(String experienceCapScriptId) {
        this.experienceCapScriptId = experienceCapScriptId;
    }

    /**
     * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
     *
     * @param experienceCapScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withExperienceCapScriptId(String experienceCapScriptId) {
        setExperienceCapScriptId(experienceCapScriptId);
        return this;
    }

    /** 経験値変化したときに実行するスクリプト */
    private ScriptSetting changeExperienceScript;

    /**
     * 経験値変化したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getChangeExperienceScript() {
        return changeExperienceScript;
    }

    /**
     * 経験値変化したときに実行するスクリプトを設定
     *
     * @param changeExperienceScript ネームスペースを新規作成
     */
    public void setChangeExperienceScript(ScriptSetting changeExperienceScript) {
        this.changeExperienceScript = changeExperienceScript;
    }

    /**
     * 経験値変化したときに実行するスクリプトを設定
     *
     * @param changeExperienceScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeExperienceScript(ScriptSetting changeExperienceScript) {
        setChangeExperienceScript(changeExperienceScript);
        return this;
    }

    /** ランク変化したときに実行するスクリプト */
    private ScriptSetting changeRankScript;

    /**
     * ランク変化したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getChangeRankScript() {
        return changeRankScript;
    }

    /**
     * ランク変化したときに実行するスクリプトを設定
     *
     * @param changeRankScript ネームスペースを新規作成
     */
    public void setChangeRankScript(ScriptSetting changeRankScript) {
        this.changeRankScript = changeRankScript;
    }

    /**
     * ランク変化したときに実行するスクリプトを設定
     *
     * @param changeRankScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankScript(ScriptSetting changeRankScript) {
        setChangeRankScript(changeRankScript);
        return this;
    }

    /** ランクキャップ変化したときに実行するスクリプト */
    private ScriptSetting changeRankCapScript;

    /**
     * ランクキャップ変化したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getChangeRankCapScript() {
        return changeRankCapScript;
    }

    /**
     * ランクキャップ変化したときに実行するスクリプトを設定
     *
     * @param changeRankCapScript ネームスペースを新規作成
     */
    public void setChangeRankCapScript(ScriptSetting changeRankCapScript) {
        this.changeRankCapScript = changeRankCapScript;
    }

    /**
     * ランクキャップ変化したときに実行するスクリプトを設定
     *
     * @param changeRankCapScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankCapScript(ScriptSetting changeRankCapScript) {
        setChangeRankCapScript(changeRankCapScript);
        return this;
    }

    /** 経験値あふれしたときに実行するスクリプト */
    private ScriptSetting overflowExperienceScript;

    /**
     * 経験値あふれしたときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getOverflowExperienceScript() {
        return overflowExperienceScript;
    }

    /**
     * 経験値あふれしたときに実行するスクリプトを設定
     *
     * @param overflowExperienceScript ネームスペースを新規作成
     */
    public void setOverflowExperienceScript(ScriptSetting overflowExperienceScript) {
        this.overflowExperienceScript = overflowExperienceScript;
    }

    /**
     * 経験値あふれしたときに実行するスクリプトを設定
     *
     * @param overflowExperienceScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withOverflowExperienceScript(ScriptSetting overflowExperienceScript) {
        setOverflowExperienceScript(overflowExperienceScript);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを新規作成
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}