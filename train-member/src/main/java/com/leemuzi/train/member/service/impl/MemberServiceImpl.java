package com.leemuzi.train.member.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.leemuzi.train.common.exception.BusinessException;
import com.leemuzi.train.common.exception.BusinessExceptionEnum;
import com.leemuzi.train.common.util.SnowUtil;
import com.leemuzi.train.member.domain.Member;
import com.leemuzi.train.member.domain.MemberExample;
import com.leemuzi.train.member.mapper.MemberMapper;
import com.leemuzi.train.member.req.MemberLoginReq;
import com.leemuzi.train.member.req.MemberRegisterReq;
import com.leemuzi.train.member.req.MemberSendCodeReq;
import com.leemuzi.train.member.resp.MemberLoginResp;
import com.leemuzi.train.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 使用手机号注册
     * @param req
     * @return 返回注册id，如果手机号已经注册，抛出异常
     */
    @Override
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();

        //创建查询条件，查询手机号是否已经注册
        Member memberDB = selectByMobile(mobile);
        // 如果查询结果不为空，证明手机号已经注册，返回对应id或抛出异常
        if(ObjectUtil.isNotNull(memberDB)) {
//            return members.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        //如果手机号没有注册，进行新注册
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    @Override
    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        // 检验手机号是否已经注册
        Member memberDB = selectByMobile(mobile);
        if(ObjectUtil.isNull(memberDB)){
            // 如果手机号没有注册过，先注册在发送验证码
            LOG.info("手机号不存在，插入一条新纪录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }

        // 已经存在或新注册完成，生成验证码
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);
        // TODO 将验证码放入验证码记录表：手机号、短信验证码、有效期、发送时间、使用时间、使用状态、业务类型
        LOG.info("保存验证码记录表");

        // TODO 对接短信通道，发送短信
        LOG.info("对接短信通道");
    }

    @Override
    public MemberLoginResp login(MemberLoginReq req) {
        // 获取手机号和验证码
        String mobile = req.getMobile();
        String code = req.getCode();

        // 查询手机号是否已经注册，如果没有注册证明没有进行获取验证码操作，不新注册并抛出异常
        Member member = selectByMobile(mobile);
        if (ObjectUtil.isNull(member)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        // 判断验证码是否正确
        // TODO 暂时使用固定验证码，后期需补充：通过手机号获取对应验证码状态、时效
        if (!"8888".equals(code)) {
            // 如果验证码不正确，抛出异常
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        // 手机号已注册，验证码正确，返回用户信息
        return MemberLoginResp.builder()
                .id(member.getId())
                .mobile(member.getMobile())
                .build();
    }

    /**
     * 通过手机号获取Member对象
     * @param mobile
     * @return
     */
    private Member selectByMobile(String mobile) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(members)) {
            return null;
        } else {
            return members.get(0);
        }
    }


}
