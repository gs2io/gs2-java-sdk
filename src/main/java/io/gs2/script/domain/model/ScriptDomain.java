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
package io.gs2.script.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.script.Gs2ScriptRestClient;
import io.gs2.script.domain.iterator.*;
import io.gs2.script.model.*;
import io.gs2.script.request.*;
import io.gs2.script.result.*;

import java.util.List;


public class ScriptDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ScriptRestClient client;
    private final String namespaceName;
    private final String scriptName;

    private final String parentKey;
    Integer code;
    public Integer getCode() {
        return this.code;
    }
    String result;
    public String getResult() {
        return this.result;
    }
    Integer executeTime;
    public Integer getExecuteTime() {
        return this.executeTime;
    }
    Integer charged;
    public Integer getCharged() {
        return this.charged;
    }
    List<String> output;
    public List<String> getOutput() {
        return this.output;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getScriptName() {
        return scriptName;
    }

    public ScriptDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        String scriptName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ScriptRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.scriptName = scriptName;
        this.parentKey = io.gs2.script.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Script"
        );
    }

    private Script get(
        GetScriptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withScriptName(this.scriptName);
        GetScriptResult result = this.client.getScript(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                    request.getScriptName() != null ? request.getScriptName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public ScriptDomain update(
        UpdateScriptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withScriptName(this.scriptName);
        UpdateScriptResult result = this.client.updateScript(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                    request.getScriptName() != null ? request.getScriptName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ScriptDomain domain = this;

        return domain;
    }

    public ScriptDomain updateFromGitHub(
        UpdateScriptFromGitHubRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withScriptName(this.scriptName);
        UpdateScriptFromGitHubResult result = this.client.updateScriptFromGitHub(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                    request.getScriptName() != null ? request.getScriptName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ScriptDomain domain = this;

        return domain;
    }

    public ScriptDomain delete(
        DeleteScriptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withScriptName(this.scriptName);
        DeleteScriptResult result = null;
        try {
            result = this.client.deleteScript(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                request.getScriptName() != null ? request.getScriptName().toString() : null
            ),
            Script.class
        );
        ScriptDomain domain = this;

        return domain;
    }

    public static String createCacheParentKey(
        String namespaceName,
        String scriptName,
        String childType
    )
    {
        return String.join(
            ":",
            "script",
            namespaceName,
            scriptName,
            childType
        );
    }

    public static String createCacheKey(
        String scriptName
    )
    {
        return String.join(
            ":",
            scriptName
        );
    }

    public Script model() {
        Script value = cache.get(
            parentKey,
            io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                this.getScriptName() != null ? this.getScriptName().toString() : null
            ),
            Script.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetScriptRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                        this.getScriptName() != null ? this.getScriptName().toString() : null
                    ),
                    Script.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                this.getScriptName() != null ? this.getScriptName().toString() : null
            ),
            Script.class
        );
        }
        return value;
    }

}
