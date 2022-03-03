//package com.whz.dao;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
//import org.apache.shiro.session.mgt.eis.SessionDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
///**
// * @auther whz
// * @create 2022-02-28 11:04
// */
//@Slf4j
//@Component
//public class ShiroSessionDao extends CachingSessionDAO {
//
//    @Autowired
//    private SessionDAO sessionDAO;
//
//    @Override
//    protected void doUpdate(Session session) {
//        log.info("doUpdate...");
//    }
//
//    @Override
//    protected void doDelete(Session session) {
//        log.info("doDelete...");
//    }
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        return null;
//    }
//
//    @Override
//    protected Session doReadSession(Serializable serializable) {
//        return null;
//    }
//}
