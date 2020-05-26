package controller;
import app.generator.response.websockt.entiry.MsgVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 房间号 -> 组成员信息
     */
    private static ConcurrentHashMap<String, List<WebSocketSession>> groupMemberInfoMap = new ConcurrentHashMap<>();
    /**
     * 房间号 -> 在线人数
     */
    private static ConcurrentHashMap<String, Set<String>> onlineUserMap = new ConcurrentHashMap<>();



    /**
     * 收到消息调用的方法，群成员发送消息
     *
     */
    public void onMessage(String sid, String userId,  String message) {
        System.out.println("on message");
        List<WebSocketSession> sessionList = groupMemberInfoMap.get(sid);
        Set<String> onlineUserList = onlineUserMap.get(sid);
        // 先一个群组内的成员发送消息
        AtomicInteger sendCount = new AtomicInteger(0);
        sessionList.forEach(item -> {
            System.out.println("正在向第" + sendCount + "为用户推送消息");
            sendCount.getAndIncrement();
            try {
                // json字符串转对象
                MsgVO msg = objectMapper.readValue(message, MsgVO.class);
                msg.setCount(onlineUserList.size());
                // json对象转字符串
                String text = objectMapper.writeValueAsString(msg);
//                MyWebSockeMessage<String> message1 = new MyWebSockeMessage<String>();
//                message1.setByteCount(text.getBytes().length);
//                message1.setLast(true);
//                message1.setPayLoad(text);
                System.out.println("发送成功");
                item.sendMessage(new TextMessage(text));
            } catch (IOException e) {
                System.out.println("发送失败");
                e.printStackTrace();
            }
        });
    }

    /**
     * 建立连接调用的方法，群成员加入
     */
    public void onOpen(WebSocketSession session, String sid, String userId) throws JsonProcessingException {
        System.out.println(userId+"加入房间"+sid);
        List<WebSocketSession> sessionList;
        Set<String> onlineUserList;
        if (groupMemberInfoMap.containsKey(sid)) {
            sessionList = groupMemberInfoMap.get(sid);
            sessionList.add(session);
            System.out.println("房间已初始化，"+userId+"直接进入房间");
        }else {
            ArrayList<WebSocketSession> sessions = new ArrayList<>();
            sessions.add(session);
            sessionList = sessions;
            groupMemberInfoMap.put(sid, sessions);
            System.out.println(groupMemberInfoMap);
            System.out.println("房间未初始化，先构建房间再将"+userId+"加入房间");
        }
        if (onlineUserMap.containsKey(sid)) {
            onlineUserList = onlineUserMap.get(sid);
            onlineUserList.add(userId);
        }else {
            HashSet<String> s = new HashSet<>();
            s.add(userId);
            onlineUserList = s;
            onlineUserMap.put(sid, s);
        }
        System.out.println("房间信息" + groupMemberInfoMap.get(sid));
        System.out.println("用户在线信息" + onlineUserMap.get(sid));

//        sessionList = groupMemberInfoMap.computeIfPresent(sid, k -> {
//            ArrayList<WebSocketSession> sessions = new ArrayList<>();
//            sessions.add(session);
//            groupMemberInfoMap.put(k, sessions);
//            return sessions;
//        });
//        onlineUserList = onlineUserMap.computeIfPresent(sid, k -> {
//            HashSet<String> s = new HashSet<>();
//            s.add(userId);
//            onlineUserMap.put(k, s);
//            return s;
//        });
        // 发送上线通知
        sendInfo(sid, userId, onlineUserList.size(), "已上线");
    }


    public void sendInfo(String sid, String userId, Integer onlineSum, String info) throws JsonProcessingException {
        System.out.println("on sendInfo"+sid+"中的"+userId+"说："+info);
        // 发送通知
        MsgVO msg = new MsgVO();
        msg.setCount(onlineSum);
        msg.setUserId(userId);
        msg.setMsg(info);
        // json对象转字符串
        String text = objectMapper.writeValueAsString(msg);
        onMessage(sid, userId, text);
    }

    /**
     * 关闭连接调用的方法，群成员退出
     *
     */
    public void onClose(WebSocketSession session, String sid, String userId) throws JsonProcessingException {
        List<WebSocketSession> sessionList = groupMemberInfoMap.get(sid);
        sessionList.remove(session);
        Set<String> onlineUserList = onlineUserMap.get(sid);
        onlineUserList.remove(userId);
        // 发送离线通知
        sendInfo(sid, userId, onlineUserList.size(), "已下线");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Map<String, Object> map = session.getAttributes();
        String uid = (String) map.get("uid");
        String sid = (String) map.get("sid");
        try {
            onClose(session, sid, uid);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // ...
        Map<String, Object> map = session.getAttributes();
        String uid = (String) map.get("uid");
        String sid = (String) map.get("sid");
        MsgVO msgVO = new MsgVO();
        msgVO.setMsg(message.getPayload());
        msgVO.setUserId(uid);
        try {
            onMessage(sid,uid,objectMapper.writeValueAsString(msgVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> map = session.getAttributes();
        String uid = (String) map.get("uid");
        String sid = (String) map.get("sid");
        try {
            onOpen(session, sid, uid);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}