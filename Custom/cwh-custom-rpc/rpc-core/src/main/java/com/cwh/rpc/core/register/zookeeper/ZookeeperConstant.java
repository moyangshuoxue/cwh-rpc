//package com.cwh.rpc.core.register.zookeeper;
//
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * @Author 蔡文瀚
// * @Date 2024/5/11 13:31
// * @Version 1.0
// * @ClassName ZookeeperConstant
// * @Description This is a general-purpose Java class.
// */
//@Component
//public class ZookeeperConstant implements InitializingBean {
//    @Value("${zookeeper.sessionTimeout}")
//    private int sessionTimeout;
//
//    @Value("${zookeeper.connectionTimeout}")
//    private int connectionTimeout;
//
//    @Value("${zookeeper.baseSleepTime}")
//    private int baseSleepTime;
//
//    @Value("${zookeeper.maxRetry}")
//    private int maxRetry;
//
//    @Value("${zookeeper.basePath}")
//    private String basePath;
//    @Value("${zookeeper.connectAddress}")
//    private String connectAddress;
//    /**
//     *zooke
//     * */
//    public static  int SESSION_TIMEOUT ;
//
//    public static  int CONNECT_TIMEOUT;
//
//    public static  int BASE_SLEEP_TIME ;
//
//    public static  int MAX_RETRY ;
//
//    public static  String BASE_PATH ;
//    public static  String CONNECT_ADDRESS ;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//            SESSION_TIMEOUT =sessionTimeout;
//            CONNECT_TIMEOUT=connectionTimeout;
//            BASE_PATH=basePath;
//            BASE_SLEEP_TIME=baseSleepTime;
//            MAX_RETRY=maxRetry;
//            CONNECT_ADDRESS = connectAddress;
//
//    }
//
//}
