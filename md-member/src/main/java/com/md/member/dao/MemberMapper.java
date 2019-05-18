package com.md.member.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.md.member.model.Member;


public interface MemberMapper extends BaseMapper<Member> {

	List<Member> getMemberList();
}
