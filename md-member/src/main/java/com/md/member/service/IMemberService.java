package com.md.member.service;

import com.baomidou.mybatisplus.service.IService;
import com.md.member.model.Member;

public interface IMemberService extends IService<Member>{

	Member findOne(String openid);

}
