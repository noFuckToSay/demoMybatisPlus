package com.example.demoMybatisPlus.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demoMybatisPlus.base.AjaxResult;
import com.example.demoMybatisPlus.base.UserContext;
import com.example.demoMybatisPlus.config.jwt.JWTUtils;
import com.example.demoMybatisPlus.user.dto.UserCdto;
import com.example.demoMybatisPlus.user.dto.UserRdto;
import com.example.demoMybatisPlus.user.dto.UserUdto;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import com.example.demoMybatisPlus.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dingzk
 * @since 2023/04/17
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "userController",description = "用户管理")
public class UserController {

    private UserService service;

    @Autowired
    public UserController( UserService service) {
        this.service = service;
    }

    /**
     * 新增
     */
    @PostMapping("/create")
    @ApiOperation(value = "新增用户",httpMethod = "POST")
    public AjaxResult create(
            @RequestBody @Valid UserCdto dto) {
        User user=new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setCreateBy(UserContext.currentUser().getUserName());
        user.setUpdateBy(UserContext.currentUser().getUserName());
        service.save(user);
        return AjaxResult.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新用户",httpMethod = "POST")
    public AjaxResult update(
            @RequestBody @Valid UserUdto dto) {

        User user=new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setUpdateBy(UserContext.currentUser().getUserName());
        service.updateById(user);
        return AjaxResult.ok();
    }

    /**
     * 集合查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询用户集合",httpMethod = "POST")
    public AjaxResult<List<UserVo>> list(
            @RequestBody UserRdto userRdto) {
        return AjaxResult.ok();
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询用户列表",httpMethod = "POST")
    public AjaxResult<IPage<UserVo>> page(@RequestBody UserRdto userRdto) {

        return AjaxResult.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除用户",httpMethod = "POST")
    public AjaxResult delete(
            @RequestBody List<Long> ids) {
        return AjaxResult.ok();
    }

    /**
     * 提交
     */
    @PostMapping("/submit")
    @ApiOperation(value = "提交",httpMethod = "POST")
    public AjaxResult submit(
            @RequestBody List<Long> ids) {
        return AjaxResult.ok();
    }

    /**
     * 撤销
     */
    @PostMapping("/unSubmit")
    @ApiOperation(value = "撤销",httpMethod = "POST")
    public AjaxResult unSubmit(
            @RequestBody List<Long> ids) {
        return AjaxResult.ok();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录",httpMethod = "POST")
    public AjaxResult login(
            @RequestBody User dto) {
        User user=service.getOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getUserName,dto.getUserName())
                .eq(User::getPassword,dto.getPassword())
        );
        if(null==user){
            throw new RuntimeException("用户名/密码错误");
        }

        return AjaxResult.ok(JWTUtils.sign(user));
    }
}
