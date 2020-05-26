package com.app.user.service;

import app.generator.modol.User;
import app.generator.response.RpcResult;

public interface UserService {
    RpcResult login(User user);

    RpcResult register(User user);

    RpcResult logout(User user);

    RpcResult randomNum(User user);

    RpcResult resetPass(User loginUSer);

}
