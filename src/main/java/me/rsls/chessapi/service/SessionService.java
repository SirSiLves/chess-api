package me.rsls.chessapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class SessionService {
    public String getHttpSessionId() {
        try{
            return RequestContextHolder.currentRequestAttributes().getSessionId();
        }
        catch(Exception e){
            return "unknown";
        }
    }
}
