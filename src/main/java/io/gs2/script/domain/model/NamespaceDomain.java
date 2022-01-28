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


public class NamespaceDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2ScriptRestClient client;
    private final String namespaceName;

    private final String parentKey;
    String status;
    public String getStatus() {
        return this.status;
    }
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
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }

    public NamespaceDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2ScriptRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.parentKey = "script:Namespace";
    }

    public NamespaceDomain getStatus(
        GetNamespaceStatusRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetNamespaceStatusResult result = this.client.getNamespaceStatus(
            request
        );
        NamespaceDomain domain = this;
        this.status = result.getStatus();
        return domain;
    }

    private Namespace get(
        GetNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        GetNamespaceResult result = this.client.getNamespace(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.NamespaceDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        return result.getItem();
    }

    public NamespaceDomain update(
        UpdateNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        UpdateNamespaceResult result = this.client.updateNamespace(
            request
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.NamespaceDomain.createCacheKey(
                    request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        NamespaceDomain domain = this;

        return domain;
    }

    public NamespaceDomain delete(
        DeleteNamespaceRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        DeleteNamespaceResult result = null;
        try {
            result = this.client.deleteNamespace(
                request
            );
        } catch(io.gs2.core.exception.NotFoundException e) {}
        cache.delete(
            parentKey,
            io.gs2.script.domain.model.NamespaceDomain.createCacheKey(
                request.getNamespaceName() != null ? request.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        NamespaceDomain domain = this;

        return domain;
    }

    public ScriptDomain createScript(
        CreateScriptRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        CreateScriptResult result = this.client.createScript(
            request
        );
        String parentKey = io.gs2.script.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Script"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ScriptDomain domain = new ScriptDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getName()
        );

        return domain;
    }

    public ScriptDomain createScriptFromGitHub(
        CreateScriptFromGitHubRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName);
        CreateScriptFromGitHubResult result = this.client.createScriptFromGitHub(
            request
        );
        String parentKey = io.gs2.script.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "Script"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.script.domain.model.ScriptDomain.createCacheKey(
                    result.getItem().getName() != null ? result.getItem().getName().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        ScriptDomain domain = new ScriptDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            result.getItem().getName()
        );

        return domain;
    }

    public NamespaceDomain invokeScript(
        InvokeScriptRequest request
    ) {
        InvokeScriptResult result = this.client.invokeScript(
            request
        );
        for (String item : result.getOutput()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    item,
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        NamespaceDomain domain = this;
        this.code = result.getCode();
        this.result = result.getResult();
        this.charged = result.getCharged();
        this.output = result.getOutput();
        return domain;
    }

    public NamespaceDomain debugInvoke(
        DebugInvokeRequest request
    ) {
        DebugInvokeResult result = this.client.debugInvoke(
            request
        );
        for (String item : result.getOutput()) {
                
            if (item != null) {
                cache.put(
                    parentKey,
                    item,
                    item,
                    System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
                );
            }
        }
        NamespaceDomain domain = this;
        this.code = result.getCode();
        this.result = result.getResult();
        this.executeTime = result.getExecuteTime();
        this.charged = result.getCharged();
        this.output = result.getOutput();
        return domain;
    }

    public DescribeScriptsIterator scripts(
    )
    {
        return new DescribeScriptsIterator(
            this.cache,
            this.client,
            this.namespaceName
        );
    }

    public ScriptDomain script(
        String scriptName
    ) {
        return new ScriptDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            scriptName
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String childType
    )
    {
        return String.join(
            ":",
            "script",
            namespaceName,
            childType
        );
    }

    public static String createCacheKey(
        String namespaceName
    )
    {
        return String.join(
            ":",
            namespaceName
        );
    }

    public Namespace model() {
        Namespace value = cache.get(
            parentKey,
            io.gs2.script.domain.model.NamespaceDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        if (value == null) {
            try {
                this.get(
                    new GetNamespaceRequest()
                );
            } catch(io.gs2.core.exception.NotFoundException e) {
                cache.delete(
                    parentKey,
                    io.gs2.script.domain.model.NamespaceDomain.createCacheKey(
                        this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
                    ),
                    Namespace.class
                );
            }
            value = cache.get(
            parentKey,
            io.gs2.script.domain.model.NamespaceDomain.createCacheKey(
                this.getNamespaceName() != null ? this.getNamespaceName().toString() : null
            ),
            Namespace.class
        );
        }
        return value;
    }

}
