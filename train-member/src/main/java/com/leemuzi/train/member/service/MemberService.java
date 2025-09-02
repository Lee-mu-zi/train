package com.leemuzi.train.member.service;

import com.leemuzi.train.member.req.MemberRegisterReq;

/**
 * @Author 李Muzi
 * @Date 2025/3/21 19:06
 * @Description
 */
public interface MemberService {

    public int count();

    public long register(MemberRegisterReq req);
}
