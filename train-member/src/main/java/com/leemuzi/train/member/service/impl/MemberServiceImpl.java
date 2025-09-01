package com.leemuzi.train.member.service.impl;

import com.leemuzi.train.member.mapper.MemberMapper;
import com.leemuzi.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 李Muzi
 * @Date 2025/3/21 19:06
 * @Description
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
