package com.person.erp.identity.controller;

import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.utils.ResultUtils;
import com.itexplore.core.common.utils.io.CaptchaRender;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.DealResultUtils;
import com.person.erp.common.utils.RedisUtils;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.common.valid.LoginPhone;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.LoginDTO;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.service.ILoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>LoginController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/23 12:38
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Resource
    private ILoginService loginService;

    @GetMapping("/code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //实例生成验证码对象
        CaptchaRender captchaRender = new CaptchaRender(CaptchaRender.Type.NUMBER);

        // 记录验证码
        String sessionId = request.getSession().getId();
        RedisUtils.set(WebConstant.VERIFY_CODE + sessionId, captchaRender.getCode(), 5L, TimeUnit.MINUTES);

        //向页面输出验证码图片
        captchaRender.write(response.getOutputStream());

    }

    @PostMapping("/into")
    public ResponseEntity loginInto(@RequestBody @Validated LoginDTO dto, HttpServletRequest request) {

        // 校验验证码
        String verifyCodeKey = WebConstant.VERIFY_CODE + request.getSession().getId();
        String code = (String) RedisUtils.get(verifyCodeKey);

        if (code == null) {

            throw new ApiException(HttpStatus.BAD_REQUEST, "验证码已失效");

        } else if (!code.equals(dto.getCode())) {

            throw new ApiException(HttpStatus.BAD_REQUEST, "验证码错误");

        } else {

            RedisUtils.remove(verifyCodeKey);

        }

        String token = loginService.loginInto(dto.getUserCode(), dto.getSystemTag(), dto.getUserPwd());

        return DealResultUtils.dealData("token", token);

    }

    @PostMapping("/phone")
    public ResponseEntity loginIntoByPhone(@RequestBody @Validated({LoginPhone.class}) LoginDTO dto) {

        String token = loginService.loginIntoByPhone(dto.getMobilePhone(), dto.getUserPwd());

        return DealResultUtils.dealData("token", token);

    }

    @PutMapping("/out")
    public ResponseEntity loginOut() {

        User user = TokenUtils.getUser();

        TokenUtils.removeUser(user);

        TokenUtils.removePermission();

        return ResultUtils.asserts(user != null);
    }

    @GetMapping("/user")
    public ResponseEntity getLoginUser() {

        User user = TokenUtils.getUser();

        return ResultUtils.asserts(user);

    }

    @GetMapping("/permission")
    public ResponseEntity getLoginUserPermission() {

        List<MenuDTO> permission = TokenUtils.getPermission();

        return ResultUtils.asserts(permission);
    }

}
