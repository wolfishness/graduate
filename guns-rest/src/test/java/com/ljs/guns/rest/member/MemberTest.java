package com.ljs.guns.rest.member;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ljs.guns.rest.GunsRestApplication;
import com.ljs.guns.rest.modular.member.ApiMemberController;
import com.ljs.guns.rest.modular.member.dto.MemberRequest;
import com.md.member.dao.MemberMapper;
import com.md.member.service.IMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GunsRestApplication.class)
public class MemberTest {
	
	@Resource
	IMemberService memberService;
	
	@Resource
	MemberMapper memberMapper;
	
	@Resource
	ApiMemberController apiMemberController;
	
	//@Test
	public void memTest() throws Exception{
		/*System.out.println(memberService.selectById(1).toString());
		System.out.println("11111");*/
		MemberRequest memberRequest = new MemberRequest();
		memberRequest.setMemberId((long)1);
		//System.out.println(apiMemberController.getMemberInfo(memberRequest).toString());
	}
	
	//@Test
	public void memberTest() throws Exception{
		System.out.println(memberService.findOne("oO0PI5bwiKJuaLyE5JhF0qSKtrhU"));
		
	}
	
	@Test
	public void getMemberList() throws Exception{
		//System.out.println(memberMapper.getMemberList().toString());
		List<String> strings = new ArrayList<>();
		strings.add("../../image/quick1.jpg");
		strings.add("../../image/quick2.jpg");
		System.out.println(strings);
	}
}
