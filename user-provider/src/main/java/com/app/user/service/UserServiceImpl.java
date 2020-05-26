package com.app.user.service;

import app.generator.mapper.UserMapper;
import app.generator.modol.User;
import app.generator.modol.UserExample;
import app.generator.response.RpcResult;
import com.app.user.utils.UUIDutils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Service
@CacheConfig(cacheNames = {"user"},cacheManager = "redisCacheManager")
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    @Cacheable(key = "#user.getRandomNumber()")
    public RpcResult login(User user) {
        User user1;
        UserExample example = new UserExample();
        example
                .or()
                .andRandomNumberEqualTo(user.getRandomNumber())
                .andPasswordEqualTo(user.getPassword());
        System.out.println(user);
        List<User> userList = userMapper.selectByExample(example);
        System.out.println(userList);
        if (userList.size() != 0) {
            user1 = userList.get(0);
            RpcResult rpcResult = RpcResult.ok();
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("loginUser", user1);
            rpcResult.setData(dataMap);
            return rpcResult;
        } else {
            return RpcResult.not();
        }
    }

    @Override
    public RpcResult register(User user) {
        String random = UUIDutils.randomNumber();
        user.setRandomNumber(random);
        if (0 != userMapper.insert(user)) {
            RpcResult ok = RpcResult.ok();
            HashMap<String, Object> map = new HashMap<>();
            map.put(("registerUser"), user);
            ok.setData(map);
            return ok;
        } else return RpcResult.not();
    }
    @Override
    public RpcResult logout(User user) {
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public RpcResult resetPass(User loginUSer) {

        UserExample example = new UserExample();
        example.or()
                .andRandomNumberEqualTo(loginUSer.getRandomNumber());
        int i = userMapper.updateByExample(loginUSer, example);
        System.out.println(i);
        if (0 != i) {
            RpcResult ok = RpcResult.ok();
            HashMap<String, Object> map = new HashMap<>();
            map.put("loginUser", loginUSer);
            ok.setData(map);
            System.out.println(ok);
            return ok;
        } else {
            return RpcResult.not();
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(/*value="emp",*/key = "#user.getRandomNumber()")
            },
            put = {
                    @CachePut(key = "#result.getData().get('loginUser').getRandomNumber()")
            }
    )
    public RpcResult randomNum(User user) {
        String random = UUIDutils.randomNumber();
        UserExample example = new UserExample();
        example.or()
                .andIdEqualTo(user.getId());
        user.setRandomNumber(random);
        int i = userMapper.updateByExample(user, example);

        if (0 != i) {
            RpcResult ok = RpcResult.ok();
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("loginUser",user);
            ok.setData(dataMap);
            return ok;
        } else return RpcResult.not();
    }

    public static void main(String[] args) {
    }
}
