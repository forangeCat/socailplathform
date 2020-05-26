package controller;

import app.generator.modol.Station;
import app.generator.modol.User;
import app.generator.response.RpcResult;
import com.app.user.service.UserService;
import com.app.user.utils.UUIDutils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.app.station.service.StationService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    UserService userService;
    @Reference
    StationService stationService;

    @GetMapping("/login")
    public RpcResult login(HttpSession session, HttpServletResponse response,String userid, String password) {
        RpcResult rpcResult = null;
        User user = new User();
        user.setRandomNumber(userid);
        user.setPassword(password);
        System.out.println(user);
        rpcResult = userService.login(user);
        System.out.println(rpcResult);
        if (rpcResult.getCode() == 200) {
            session.setAttribute("loginUser", rpcResult.getData().get("loginUser"));
        } else {
            rpcResult.setCode(400);
            rpcResult.setMessage("userid or password error");
        }
        String token = UUIDutils.createToken();
        session.setAttribute("token", token);
        response.addCookie(new Cookie("token", token));
        return rpcResult;
    }

    @PostMapping("/register")
    public RpcResult register(String password) {
        RpcResult rpcResult;
        User ruser = new User();
        ruser.setPassword(password);
        rpcResult = userService.register(ruser);
        return rpcResult;
    }

    @PutMapping("/rerandom")
    public RpcResult rerandom(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loginUser");
        RpcResult rpcResult = userService.randomNum(user);
        httpSession.removeAttribute("loginUser");
        httpSession.setAttribute("loginUser", rpcResult.getData().get("loginUser"));
        System.out.println(rpcResult);
        return rpcResult;
    }

    @GetMapping("/logout")
    public RpcResult logout(HttpSession httpSession) {
        httpSession.removeAttribute("logout");
        httpSession.removeAttribute("token");
        return RpcResult.ok();
    }

    @PostMapping("/create_station")
    public RpcResult createStation(Station station, HttpSession httpSession) {
        System.out.println(station);
        return stationService.createStation(station);
    }

    @PutMapping("/reset_password")
    public RpcResult resetPass(String oldPass, String newPass, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println(oldPass+newPass);
        System.out.println(loginUser);
        if (!loginUser.getPassword().equals(oldPass.trim())) {
            RpcResult result;
            result = RpcResult.not();
            result.setMessage("旧密码错误");
            return result;
        } else {
            loginUser.setPassword(newPass);
            RpcResult rpcResult = userService.resetPass(loginUser);
            session.setAttribute("loginUser", loginUser);
            return rpcResult;
        }
    }
}
