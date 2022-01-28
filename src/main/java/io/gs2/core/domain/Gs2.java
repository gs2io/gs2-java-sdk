package io.gs2.core.domain;

import io.gs2.account.domain.Gs2Account;
import io.gs2.auth.domain.Gs2Auth;
import io.gs2.auth.model.AccessToken;
import io.gs2.chat.domain.Gs2Chat;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.core.net.Gs2WebSocketSession;
import io.gs2.datastore.domain.Gs2Datastore;
import io.gs2.dictionary.domain.Gs2Dictionary;
import io.gs2.distributor.domain.Gs2Distributor;
import io.gs2.enhance.domain.Gs2Enhance;
import io.gs2.exchange.domain.Gs2Exchange;
import io.gs2.experience.domain.Gs2Experience;
import io.gs2.formation.domain.Gs2Formation;
import io.gs2.friend.domain.Gs2Friend;
import io.gs2.gateway.domain.Gs2Gateway;
import io.gs2.identifier.domain.Gs2Identifier;
import io.gs2.inbox.domain.Gs2Inbox;
import io.gs2.inventory.domain.Gs2Inventory;
import io.gs2.jobQueue.domain.Gs2JobQueue;
import io.gs2.jobQueue.model.Job;
import io.gs2.jobQueue.model.JobResultBody;
import io.gs2.key.domain.Gs2Key;
import io.gs2.limit.domain.Gs2Limit;
import io.gs2.lock.domain.Gs2Lock;
import io.gs2.log.domain.Gs2Log;
import io.gs2.lottery.domain.Gs2Lottery;
import io.gs2.matchmaking.domain.Gs2Matchmaking;
import io.gs2.mission.domain.Gs2Mission;
import io.gs2.money.domain.Gs2Money;
import io.gs2.news.domain.Gs2News;
import io.gs2.quest.domain.Gs2Quest;
import io.gs2.ranking.domain.Gs2Ranking;
import io.gs2.realtime.domain.Gs2Realtime;
import io.gs2.schedule.domain.Gs2Schedule;
import io.gs2.script.domain.Gs2Script;
import io.gs2.showcase.domain.Gs2Showcase;
import io.gs2.stamina.domain.Gs2Stamina;
import io.gs2.version.domain.Gs2Version;

public class Gs2 {

    public static long defaultCacheMinutes = 15;

    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;

    public Gs2Account account;
    public Gs2Auth auth;
    public Gs2Chat chat;
    public Gs2Datastore datastore;
    public Gs2Dictionary dictionary;
    public Gs2Distributor distributor;
    public Gs2Enhance enhance;
    public Gs2Exchange exchange;
    public Gs2Experience experience;
    public Gs2Formation formation;
    public Gs2Friend friend;
    public Gs2Gateway gateway;
    public Gs2Identifier identifier;
    public Gs2Inbox inbox;
    public Gs2Inventory inventory;
    public Gs2JobQueue jobQueue;
    public Gs2Key key;
    public Gs2Limit limit;
    public Gs2Lock lock;
    public Gs2Log log;
    public Gs2Lottery lottery;
    public Gs2Matchmaking matchmaking;
    public Gs2Mission mission;
    public Gs2Money money;
    public Gs2News news;
    public Gs2Quest quest;
    public Gs2Ranking ranking;
    public Gs2Realtime realtime;
    public Gs2Schedule schedule;
    public Gs2Script script;
    public Gs2Showcase showcase;
    public Gs2Stamina stamina;
    public Gs2Version version;

    public Gs2(
            Gs2RestSession session
    ) {
        this(session, null);
    }

    public Gs2(
            Gs2RestSession session,
            Gs2WebSocketSession wssession
    ) {
        StampSheetConfiguration stampSheetConfiguration = StampSheetConfiguration.builder().build();
        cache = new CacheDatabase();
        jobQueueDomain = new JobQueueDomain(this);
        
        account = new Gs2Account(cache, jobQueueDomain, stampSheetConfiguration, session);
        auth = new Gs2Auth(cache, jobQueueDomain, stampSheetConfiguration, session);
        chat = new Gs2Chat(cache, jobQueueDomain, stampSheetConfiguration, session);
        datastore = new Gs2Datastore(cache, jobQueueDomain, stampSheetConfiguration, session);
        dictionary = new Gs2Dictionary(cache, jobQueueDomain, stampSheetConfiguration, session);
        distributor = new Gs2Distributor(cache, jobQueueDomain, stampSheetConfiguration, session);
        enhance = new Gs2Enhance(cache, jobQueueDomain, stampSheetConfiguration, session);
        exchange = new Gs2Exchange(cache, jobQueueDomain, stampSheetConfiguration, session);
        experience = new Gs2Experience(cache, jobQueueDomain, stampSheetConfiguration, session);
        formation = new Gs2Formation(cache, jobQueueDomain, stampSheetConfiguration, session);
        friend = new Gs2Friend(cache, jobQueueDomain, stampSheetConfiguration, session);
        gateway = new Gs2Gateway(cache, jobQueueDomain, stampSheetConfiguration, session, wssession);
        identifier = new Gs2Identifier(cache, jobQueueDomain, stampSheetConfiguration, session);
        inbox = new Gs2Inbox(cache, jobQueueDomain, stampSheetConfiguration, session);
        inventory = new Gs2Inventory(cache, jobQueueDomain, stampSheetConfiguration, session);
        jobQueue = new Gs2JobQueue(cache, jobQueueDomain, stampSheetConfiguration, session);
        key = new Gs2Key(cache, jobQueueDomain, stampSheetConfiguration, session);
        limit = new Gs2Limit(cache, jobQueueDomain, stampSheetConfiguration, session);
        lock = new Gs2Lock(cache, jobQueueDomain, stampSheetConfiguration, session);
        log = new Gs2Log(cache, jobQueueDomain, stampSheetConfiguration, session);
        lottery = new Gs2Lottery(cache, jobQueueDomain, stampSheetConfiguration, session);
        matchmaking = new Gs2Matchmaking(cache, jobQueueDomain, stampSheetConfiguration, session);
        mission = new Gs2Mission(cache, jobQueueDomain, stampSheetConfiguration, session);
        money = new Gs2Money(cache, jobQueueDomain, stampSheetConfiguration, session);
        news = new Gs2News(cache, jobQueueDomain, stampSheetConfiguration, session);
        quest = new Gs2Quest(cache, jobQueueDomain, stampSheetConfiguration, session);
        ranking = new Gs2Ranking(cache, jobQueueDomain, stampSheetConfiguration, session);
        realtime = new Gs2Realtime(cache, jobQueueDomain, stampSheetConfiguration, session);
        schedule = new Gs2Schedule(cache, jobQueueDomain, stampSheetConfiguration, session);
        script = new Gs2Script(cache, jobQueueDomain, stampSheetConfiguration, session);
        showcase = new Gs2Showcase(cache, jobQueueDomain, stampSheetConfiguration, session);
        stamina = new Gs2Stamina(cache, jobQueueDomain, stampSheetConfiguration, session);
        version = new Gs2Version(cache, jobQueueDomain, stampSheetConfiguration, session);
    }

    public void clearCache() {
        cache.clear();
    }

    public void dispatch(
            AccessToken accessToken
    ) {
        while (true) {
            if (jobQueueDomain.run(
                    accessToken
            )) {
                break;
            }
        }
    }

    public void dispatchByUserId(
            String userId
    ) {
        while (true) {
            if (jobQueueDomain.runByUserId(
                    userId
            )) {
                break;
            }
        }
    }

    public static void updateCacheFromStampSheet(
            CacheDatabase cache,
            String action,
            String request,
            String result
    ) {
        if (action.contains(":")) {
            String service = action.substring(0, action.indexOf(':'));
            String method = action.substring(action.indexOf(':') + 1);
            switch (service) {
                case "Gs2Account":
                    Gs2Account.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Auth":
                    Gs2Auth.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Chat":
                    Gs2Chat.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Datastore":
                    Gs2Datastore.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Dictionary":
                    Gs2Dictionary.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Distributor":
                    Gs2Distributor.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Enhance":
                    Gs2Enhance.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Exchange":
                    Gs2Exchange.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Experience":
                    Gs2Experience.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Formation":
                    Gs2Formation.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Friend":
                    Gs2Friend.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Gateway":
                    Gs2Gateway.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Identifier":
                    Gs2Identifier.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Inbox":
                    Gs2Inbox.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Inventory":
                    Gs2Inventory.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2JobQueue":
                    Gs2JobQueue.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Key":
                    Gs2Key.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Limit":
                    Gs2Limit.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Lock":
                    Gs2Lock.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Log":
                    Gs2Log.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Lottery":
                    Gs2Lottery.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Matchmaking":
                    Gs2Matchmaking.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Mission":
                    Gs2Mission.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Money":
                    Gs2Money.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2News":
                    Gs2News.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Quest":
                    Gs2Quest.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Ranking":
                    Gs2Ranking.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Realtime":
                    Gs2Realtime.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Schedule":
                    Gs2Schedule.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Script":
                    Gs2Script.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Showcase":
                    Gs2Showcase.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Stamina":
                    Gs2Stamina.updateCacheFromStampSheet(cache, method, request, result);
                    break;
                case "Gs2Version":
                    Gs2Version.updateCacheFromStampSheet(cache, method, request, result);
                    break;
            }
        }
    }

    public static void updateCacheFromStampTask(
            CacheDatabase cache,
            String action,
            String request,
            String result
    ) {
        if (action.contains(":")) {
            String service = action.substring(0, action.indexOf(':'));
            String method = action.substring(action.indexOf(':') + 1);
            switch (service) {
                case "Gs2Account":
                    Gs2Account.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Auth":
                    Gs2Auth.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Chat":
                    Gs2Chat.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Datastore":
                    Gs2Datastore.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Dictionary":
                    Gs2Dictionary.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Distributor":
                    Gs2Distributor.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Enhance":
                    Gs2Enhance.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Exchange":
                    Gs2Exchange.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Experience":
                    Gs2Experience.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Formation":
                    Gs2Formation.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Friend":
                    Gs2Friend.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Gateway":
                    Gs2Gateway.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Identifier":
                    Gs2Identifier.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Inbox":
                    Gs2Inbox.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Inventory":
                    Gs2Inventory.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2JobQueue":
                    Gs2JobQueue.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Key":
                    Gs2Key.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Limit":
                    Gs2Limit.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Lock":
                    Gs2Lock.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Log":
                    Gs2Log.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Lottery":
                    Gs2Lottery.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Matchmaking":
                    Gs2Matchmaking.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Mission":
                    Gs2Mission.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Money":
                    Gs2Money.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2News":
                    Gs2News.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Quest":
                    Gs2Quest.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Ranking":
                    Gs2Ranking.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Realtime":
                    Gs2Realtime.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Schedule":
                    Gs2Schedule.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Script":
                    Gs2Script.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Showcase":
                    Gs2Showcase.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Stamina":
                    Gs2Stamina.updateCacheFromStampTask(cache, method, request, result);
                    break;
                case "Gs2Version":
                    Gs2Version.updateCacheFromStampTask(cache, method, request, result);
                    break;
            }
        }
    }

    public static void pushJobQueue(
            JobQueueDomain jobQueueDomain,
            String namespaceName
    ) {
        jobQueueDomain.push(namespaceName);
    }

    public static void updateCacheFromJobResult(
            CacheDatabase cache,
            Job job,
            JobResultBody result
    ) {
        if (job.getScriptId().split(":").length > 4) {
            if (job.getScriptId().split(":")[3].equals("system")) {
                String scriptName = job.getScriptId().substring(job.getScriptId().lastIndexOf(':') + 1);
                if (scriptName.startsWith("execute_")) {
                    String scriptNameTemp = scriptName.replace("execute_", "");
                    String service = scriptNameTemp.split("_")[0];
                    String method = scriptNameTemp.substring(scriptNameTemp.indexOf("_") + 1);
                    switch (service) {
                        case "account":
                            Gs2Account.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "auth":
                            Gs2Auth.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "chat":
                            Gs2Chat.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "datastore":
                            Gs2Datastore.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "dictionary":
                            Gs2Dictionary.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "distributor":
                            Gs2Distributor.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "enhance":
                            Gs2Enhance.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "exchange":
                            Gs2Exchange.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "experience":
                            Gs2Experience.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "formation":
                            Gs2Formation.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "friend":
                            Gs2Friend.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "gateway":
                            Gs2Gateway.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "identifier":
                            Gs2Identifier.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "inbox":
                            Gs2Inbox.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "inventory":
                            Gs2Inventory.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "job_queue":
                            Gs2JobQueue.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "key":
                            Gs2Key.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "limit":
                            Gs2Limit.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "lock":
                            Gs2Lock.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "log":
                            Gs2Log.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "lottery":
                            Gs2Lottery.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "matchmaking":
                            Gs2Matchmaking.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "mission":
                            Gs2Mission.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "money":
                            Gs2Money.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "news":
                            Gs2News.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "quest":
                            Gs2Quest.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "ranking":
                            Gs2Ranking.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "realtime":
                            Gs2Realtime.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "schedule":
                            Gs2Schedule.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "script":
                            Gs2Script.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "showcase":
                            Gs2Showcase.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "stamina":
                            Gs2Stamina.updateCacheFromJobResult(cache, method, job, result);
                            break;
                        case "version":
                            Gs2Version.updateCacheFromJobResult(cache, method, job, result);
                            break;
                    }
                }
            }
        }
    }
}
