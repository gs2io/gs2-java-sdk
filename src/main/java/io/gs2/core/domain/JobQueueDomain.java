package io.gs2.core.domain;

import io.gs2.auth.model.AccessToken;
import io.gs2.jobQueue.domain.model.JobAccessTokenDomain;
import io.gs2.jobQueue.domain.model.JobDomain;
import io.gs2.jobQueue.model.Job;
import io.gs2.jobQueue.request.RunByUserIdRequest;
import io.gs2.jobQueue.request.RunRequest;

import java.util.ArrayList;
import java.util.List;

public class JobQueueDomain {

    private final Object lock = new Object();

    private Gs2 gs2;
    private final List<String> tasks = new ArrayList<>();

    public JobQueueDomain(
            Gs2 gs2
    ) {
        this.gs2 = gs2;
    }

    public void push(
            String namespaceName
    ) {
        synchronized (lock) {
            tasks.add(namespaceName);
        }
    }

    public boolean run(
            AccessToken accessToken
    ) {
        String namespaceName = null;
        synchronized (lock) {
            if (tasks.size() > 0) {
                namespaceName = tasks.get(0);
            }
        }
        if (namespaceName != null) {
            JobAccessTokenDomain job = gs2.jobQueue.namespace(
                    namespaceName
            ).accessToken(
                    accessToken
            ).run(
                    new RunRequest()
            );
            if (job.getIsLastJob()) {
                synchronized (lock) {
                    tasks.remove(namespaceName);
                }
            }
        }
        return tasks.size() == 0;
    }

    public boolean runByUserId(
            String userId
    ) {
        String namespaceName = null;
        synchronized (lock) {
            if (tasks.size() > 0) {
                namespaceName = tasks.get(0);
            }
        }
        if (namespaceName != null) {
            JobDomain job = gs2.jobQueue.namespace(
                    namespaceName
            ).user(
                    userId
            ).run(
                    new RunByUserIdRequest()
            );
            if (job.getIsLastJob()) {
                synchronized (lock) {
                    tasks.remove(namespaceName);
                }
            }
        }
        return tasks.size() == 0;
    }
}
