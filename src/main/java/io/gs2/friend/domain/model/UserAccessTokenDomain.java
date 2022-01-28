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
package io.gs2.friend.domain.model;

import io.gs2.core.domain.CacheDatabase;
import io.gs2.core.domain.JobQueueDomain;
import io.gs2.core.domain.StampSheetConfiguration;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.auth.model.AccessToken;
import io.gs2.friend.Gs2FriendRestClient;
import io.gs2.friend.domain.iterator.*;
import io.gs2.friend.model.*;
import io.gs2.friend.request.*;
import io.gs2.friend.result.*;

import java.util.List;


public class UserAccessTokenDomain {
    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final StampSheetConfiguration stampSheetConfiguration;
    private final Gs2RestSession session;
    private final Gs2FriendRestClient client;
    private final String namespaceName;
    private final AccessToken accessToken;

    private final String parentKey;
    String nextPageToken;
    public String getNextPageToken() {
        return this.nextPageToken;
    }
    public String getNamespaceName() {
        return namespaceName;
    }
    public String getUserId() {
        return accessToken.getUserId();
    }

    public UserAccessTokenDomain(
        CacheDatabase cache,
        JobQueueDomain jobQueueDomain,
        StampSheetConfiguration stampSheetConfiguration,
        Gs2RestSession session,
        String namespaceName,
        AccessToken accessToken
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.stampSheetConfiguration = stampSheetConfiguration;
        this.session = session;
        this.client = new Gs2FriendRestClient(
            session
        );
        this.namespaceName = namespaceName;
        this.accessToken = accessToken;
        this.parentKey = io.gs2.friend.domain.model.NamespaceDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            "User"
        );
    }

    public FriendRequestAccessTokenDomain sendRequest(
        SendRequestRequest request
    ) {
        request
            .withNamespaceName(this.namespaceName)
            .withAccessToken(this.accessToken != null ? this.accessToken.getToken() : null);
        SendRequestResult result = this.client.sendRequest(
            request
        );
        String parentKey = io.gs2.friend.domain.model.UserDomain.createCacheParentKey(
            this.namespaceName != null ? this.namespaceName.toString() : null,
            this.accessToken != null && this.accessToken.getUserId() != null ? this.accessToken.getUserId().toString() : null,
            "FriendRequest"
        );
                
        if (result.getItem() != null) {
            cache.put(
                parentKey,
                io.gs2.friend.domain.model.FriendRequestDomain.createCacheKey(
                    request.getTargetUserId() != null ? request.getTargetUserId().toString() : null
                ),
                result.getItem(),
                System.currentTimeMillis() + 1000 * 60 * io.gs2.core.domain.Gs2.defaultCacheMinutes
            );
        }
        FriendRequestAccessTokenDomain domain = new FriendRequestAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            request.getNamespaceName(),
            this.accessToken,
            result.getItem().getTargetUserId()
        );

        return domain;
    }

    public ProfileAccessTokenDomain profile(
    ) {
        return new ProfileAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken
        );
    }

    public PublicProfileAccessTokenDomain publicProfile(
    ) {
        return new PublicProfileAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken
        );
    }

    public DescribeBlackListIterator blackLists(
    )
    {
        return new DescribeBlackListIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken
        );
    }

    public BlackListAccessTokenDomain blackList(
    ) {
        return new BlackListAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken
        );
    }

    public DescribeFollowsIterator follows(
        Boolean withProfile
    )
    {
        return new DescribeFollowsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken,
            withProfile
        );
    }

    public FollowAccessTokenDomain follow(
        Boolean withProfile
    ) {
        return new FollowAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            withProfile
        );
    }

    public DescribeFriendsIterator friends(
        Boolean withProfile
    )
    {
        return new DescribeFriendsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken,
            withProfile
        );
    }

    public FriendAccessTokenDomain friend(
        Boolean withProfile
    ) {
        return new FriendAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            withProfile
        );
    }

    public SendBoxAccessTokenDomain sendBox(
    ) {
        return new SendBoxAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken
        );
    }

    public FriendRequestAccessTokenDomain friendRequest(
        String targetUserId
    ) {
        return new FriendRequestAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            targetUserId
        );
    }

    public DescribeSendRequestsIterator sendRequests(
    )
    {
        return new DescribeSendRequestsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken
        );
    }

    public SendFriendRequestAccessTokenDomain sendFriendRequest(
        String targetUserId
    ) {
        return new SendFriendRequestAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            targetUserId
        );
    }

    public InboxAccessTokenDomain inbox(
    ) {
        return new InboxAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken
        );
    }

    public DescribeReceiveRequestsIterator receiveRequests(
    )
    {
        return new DescribeReceiveRequestsIterator(
            this.cache,
            this.client,
            this.namespaceName,
            this.accessToken
        );
    }

    public ReceiveFriendRequestAccessTokenDomain receiveFriendRequest(
        String fromUserId
    ) {
        return new ReceiveFriendRequestAccessTokenDomain(
            this.cache,
            this.jobQueueDomain,
            this.stampSheetConfiguration,
            this.session,
            this.namespaceName,
            this.accessToken,
            fromUserId
        );
    }

    public static String createCacheParentKey(
        String namespaceName,
        String userId,
        String childType
    )
    {
        return String.join(
            ":",
            "friend",
            namespaceName,
            userId,
            childType
        );
    }

    public static String createCacheKey(
        String userId
    )
    {
        return String.join(
            ":",
            userId
        );
    }

}
