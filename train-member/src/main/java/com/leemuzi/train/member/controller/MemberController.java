package com.leemuzi.train.member.controller;

import com.leemuzi.train.common.resp.CommonResp;
import com.leemuzi.train.member.req.MemberLoginReq;
import com.leemuzi.train.member.req.MemberRegisterReq;
import com.leemuzi.train.member.req.MemberSendCodeReq;
import com.leemuzi.train.member.resp.MemberLoginResp;
import com.leemuzi.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李Muzi
 * @Date 2025/3/21 19:07
 * @Description
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count = memberService.count();
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
 }
