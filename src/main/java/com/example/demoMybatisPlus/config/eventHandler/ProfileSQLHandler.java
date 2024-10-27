package com.example.demoMybatisPlus.config.eventHandler;

import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEvent;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.protocol.Resultset;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfileSQLHandler implements ProfilerEventHandler {

    @Override
    public void init(Log log) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void consumeEvent(ProfilerEvent profilerEvent) {

    }

    @Override
    public void processEvent(byte eventType, Session session, Query query, Resultset resultSet, long eventDuration, Throwable eventCreationPoint, String message) {
        if(eventType==ProfilerEvent.TYPE_QUERY){
            while(message.contains("  ")){
                message=message.replaceAll("  "," ");
            }
            message=message.replaceAll("\n","");
            log.info("SQL-[{}]",message);
            log.info("Thread-[{}]",session.getThreadId());
        }
    }
}
