package com.scoder.jusic.configuration;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author H
 */
@Component
@ConfigurationProperties(prefix = "jusic")
@Data
@ToString
public class JusicProperties {

    public class SessionContainer{
        private Map<String, ConcurrentHashMap<String,WebSocketSession>> sessionContainer = new ConcurrentHashMap<>();

        public void remove(String houseId){
            sessionContainer.remove(houseId);
        }
        public ConcurrentHashMap<String,WebSocketSession> getHouseSession(String houseId){
            if(sessionContainer.containsKey(houseId)){
                ConcurrentHashMap<String,WebSocketSession> houseSession = sessionContainer.get(houseId);
                if(houseSession == null){
                    houseSession = new ConcurrentHashMap<String,WebSocketSession>();
                    sessionContainer.put(houseId,houseSession);
                }
                return houseSession;
            }else{
                ConcurrentHashMap<String,WebSocketSession> houseSession = new ConcurrentHashMap<String,WebSocketSession>();
                sessionContainer.put(houseId,houseSession);
                return houseSession;
            }
        }

//        public void put(String sessionId,WebSocketSession session,String houseId){
//                if(sessionContainer.containsKey(houseId)){
//                    ConcurrentHashMap<String,WebSocketSession> houseSession = sessionContainer.get(houseId);
//                    if(houseSession == null){
//                        houseSession = new ConcurrentHashMap<String,WebSocketSession>();
//                    }
//                    houseSession.put(sessionId,session);
//                    sessionContainer.put(houseId,houseSession);
//                }else{
//                    ConcurrentHashMap<String,WebSocketSession> houseSession = new ConcurrentHashMap<String,WebSocketSession>();
//                    houseSession.put(sessionId,session);
//                    sessionContainer.put(houseId,houseSession);
//                }
//        }
        public Map<String, ConcurrentHashMap<String,WebSocketSession>> get(){
            return sessionContainer;
        }
    }
    /**
     * ?????????????????????????????? session
     */
    private final SessionContainer sessions = new SessionContainer();
    public ConcurrentHashMap<String,WebSocketSession> getSessions(String houseId){
        return sessions.getHouseSession(houseId);
    }
    public SessionContainer getSession(){
        return sessions;
    }
    public void removeSessions(String houseId){
        sessions.remove(houseId);
    }
    /**
     * ?????????????????????????????????????????????????????????????????????
     */
    private Long musicExpireTime = 1200000L;
    /**
     * ?????????????????????????????????????????????????????????????????????
     */
    private Integer retryCount = 3;
    /**
     * ???????????????
     */
    private Float voteRate = 0.3F;
    /**
     * yml file ????????????????????????????????????????????????
     */
    private String defaultMusicFile;
    /**
     * springboot ??????????????????????????????????????????????????? defaultListFile ?????????????????????
     */
    private static List<String> defaultList = new LinkedList<>();

    public static void setDefaultListByJob(ArrayList<String> list){
        if(list != null && list.size() > 0){
            defaultList.clear();
            defaultList.addAll(list);
        }
    }
    public static List<String> getDefaultListForRepository(){
        return defaultList;
    }
    /**
     * root ??????
     */
    private String roleRootPassword = "654321";
    /**
     * admin ??????
     */
    private String roleAdminPassword = "123456";

    /**
     * ??????????????????url
     */
    private String wyTopUrl = "https://music.163.com/discover/toplist?id=3778678";
    /**
     * json?????????????????????for?????????
     */
    private String musicJson = "D:\\JAVA\\IdeaWorkspaces\\Jusic-serve\\src\\main\\resources\\lizhimusic.json";
    /**
     * ????????????
     */
    private String musicServeDomain = "http://localhost";
    private String musicServeDomainQq = "http://localhost";
    private String musicServeDomainMg = "http://localhost";
    private String musicServeDomainKwXm = "http://localhost";

    /**
     * mail send from
     */
    private String mailSendFrom = "18759478272@139.com";
    /**
     * mail send to
     */
    private String mailSendTo = "1040927107@qq.com";

    private String serverJUrl = "https://sc.ftqq.com/SCU64668T909ada7955daadfb64d5e7652b93fb135dad06e659369.send";
    private Integer houseSize = 10;
    private Integer ipHouse = 10;
    private Boolean goodModel = true;
    private Boolean randomModel = false;
    private String miniId = "";
    private String miniSecrect = "";

    public static final String HOUSE_DEFAULT_ID = "DEFAULT";
    public static final String HOUSE_DEFAULT_NAME = "???????????????";
    public static final String HOUSE_DEFAULT_DESC = "????????????????????????????????????";

    /**
     * redis keys
     */
    @Component
    @ConfigurationProperties(prefix = "jusic.redis.keys")
    @Data
    @ToString
    public static class RedisKeys {

        /**
         * ??????
         */
        private String configHash = "jusic_config";
        /**
         * ??????????????????
         */
        private String sessionHash = "jusic_session";

        /***
         * ?????????
         */
        private String retainKeyHash = "jusic_retain_key";
        /**
         * ?????????
         */
        private String sessionBlackHash = "jusic_session_black";
        /**
         * ????????????????????????????????????????????????????????????????????????????????????????????????
         * ???????????????????????? keyword?????????????????????????????? Music info ?????????????????????
         */
        private String defaultSet = "jusic_default";
        /**
         * ????????????????????? {@link com.scoder.jusic.model.Music} ??????
         */
        private String pickList = "jusic_pick";
        private String houses = "jusic_houses";

        /**
         * ????????????????????? {@link com.scoder.jusic.model.Music} ??????
         */
        private String playingList = "jusic_playing";
        /**
         * ??????????????? id
         */
        private String blackSet = "jusic_black";
        /**
         * ???????????????????????????????????????????????????????????? session ????????? id
         */
        private String skipSet = "jusic_skip";

        /**
         * root key, config ?????????
         */
        private String redisRoleRoot = "role_root_password";
        /**
         * admin key, config ?????????
         */
        private String redisRoleAdmin = "role_admin_password";
        /**
         * ????????????????????????, config ?????????
         */
        private String lastMusicPushTime = "last_music_push_time";
        /**
         * ???????????????????????????, config ?????????
         */
        private String lastMusicDuration = "last_music_duration";
        /**
         * ??????????????????, config ?????????
         */
        private String switchMusicPush = "switch_music_push";

        private String switchMusicEnable ="switch_music_enable";
        private String searchMusicEnable ="search_music_enable";
        private String goodModel = "good_model";
        private String randomModel = "random_model";
        /**
         * ???????????????, config ?????????
         */
        private String voteSkipRate = "vote_skip_rate";

        private final JusicEnvironment jusicEnvironment;

        public RedisKeys(JusicEnvironment jusicEnvironment) {
            this.jusicEnvironment = jusicEnvironment;
        }

        public String getConfigHash() {
            return this.configHash + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getSessionHash() {
            return this.sessionHash + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getRetainKeyHash() {
            return this.retainKeyHash + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getSessionBlackHash() {
            return this.sessionBlackHash + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getDefaultSet() {
            return this.defaultSet + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getPickList() {
            return this.pickList + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getHouses() {
            return this.houses + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getPlayingList() {
            return this.playingList + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getBlackSet() {
            return blackSet + "_" + jusicEnvironment.getServerPort()+"_";
        }

        public String getSkipSet() {
            return this.skipSet + "_" + jusicEnvironment.getServerPort()+"_";
        }
    }

}
