package com.leemuzi.train.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.leemuzi.train.member.domain.Member;
import com.leemuzi.train.member.domain.MemberExample;
import com.leemuzi.train.member.mapper.MemberMapper;
import com.leemuzi.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 使用手机号注册
     * @param mobile
     * @return 返回注册id，如果手机号已经注册，抛出异常
     */
    @Override
    public long register(String mobile) {

        //创建查询条件，查询手机号是否已经注册
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        // 获取查询结果
        List<Member> members = memberMapper.selectByExample(example);
        // 如果查询结果不为空，证明手机号已经注册，返回对应id或抛出异常
        if(CollUtil.isNotEmpty(members)) {
//            return members.get(0).getId();
            throw new RuntimeException("该手机号已经注册");
        }

        //如果手机号没有注册，进行新注册
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
