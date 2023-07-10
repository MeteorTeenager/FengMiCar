package com.qfedu.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.qfedu.sys.domain.User;
import com.qfedu.sys.domain.UserVo;
import com.qfedu.sys.service.IUserService;
import com.qfedu.sys.utils.SysConstant;
import com.qfedu.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */

@RequestMapping("login")
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到 view/main/login.jsp页面
     * 返回值：String 就是上面跳转的路径地址
     * 参数：没有
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/main/login";
    }

    /**
     * 生成验证码：生成一个图片，以io流的方式输出到前台
     *参数：session（验证生成后要放到session）  response  获取输出量
     * 返回值： 空
     */
    @RequestMapping("getCode")
    public void getCoode(HttpServletResponse response, HttpSession session) throws IOException{
        //1.使用工具类生成图片
        //参数 宽度 116     高度 37   字符数 4      干扰线 5
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 35, 4, 5);

        //2.把生成的code 验证码放到session中
        session.setAttribute("code",lineCaptcha.getCode());

        //3.以流的方式输出
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);

    }

    /**
     * 登陆操作
     * 返回值类型： 页面跳转，Strign
     * 参数： UserVo 目的：封装前台传递的三个参数  username pwd code;
     *   Model 对象，存放一些提示信息
     */
    @RequestMapping("login")
    public String login(UserVo userVo, Model model){
        //1.获取验证码
        HttpSession session = WebUtils.getHttpSession();
        String sessionCode = WebUtils.getHttpSession().getAttribute("code").toString();
        String userCode = userVo.getCode();
        if(userCode.equals(sessionCode)){
            //2.判断用户是否存在
            User user = userService.login(userVo);
            if(user != null){
                //3.如果存在 放到session中，页面跳转到 system/main/index
                session.setAttribute("user",user);
                return "system/main/index";
            }else{
                //4.如果用户不存在返回用户名或密码错误，并跳转到login.jsp（用户名或密码的问题）
                model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        }else{
            //5.验证码错误
            model.addAttribute("error", SysConstant.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }
    }
}
