package com.leemuzi.train.member.service;

import com.leemuzi.train.member.req.MemberLoginReq;
import com.leemuzi.train.member.req.MemberRegisterReq;
import com.leemuzi.train.member.req.MemberSendCodeReq;
import com.leemuzi.train.member.resp.MemberLoginResp;

/**
 * @Author 李Muzi
 * @Date 2025/3/21 19:06
 * @Description
 */
public interface MemberService {

    int count();

    long register(MemberRegisterReq req);

    void sendCode(MemberSendCodeReq req);

    MemberLoginResp login(MemberLoginReq req);
}
