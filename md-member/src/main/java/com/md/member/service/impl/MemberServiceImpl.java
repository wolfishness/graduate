package com.md.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.util.ToolUtil;
import com.md.member.dao.MemberMapper;
import com.md.member.model.Member;
import com.md.member.service.IMemberService;

@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

	@Resource
	MemberMapper memberMapper;

	@Override
	public Member findOne(String openid) {
		Wrapper<Member> wrapper = new EntityWrapper<>();
		wrapper.eq("openid", openid);
		wrapper.eq("status", 1);
		List<Member> memberList = memberMapper.selectList(wrapper);
		if (ToolUtil.isNotEmpty(memberList)) {
			return memberList.get(0);
		} else {
			return null;
		}
	}

}
