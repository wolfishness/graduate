package com.ljs.guns.rest.modular.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ljs.guns.core.util.HttpRequest;
import com.ljs.guns.core.util.ToolUtil;
import com.ljs.guns.rest.modular.member.dto.MemberRequest;
import com.md.cart.service.ICartItemService;
import com.md.cart.service.ICartService;
import com.md.member.model.Address;
import com.md.member.model.Member;
import com.md.member.service.IAddressService;
import com.md.member.service.IMemberService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member")
//@RequestMapping("/user/sign")
public class ApiMemberController{

	@Resource
	IMemberService memberService;
	
	@Resource
	IAddressService addressService;
	
	@Resource
	ICartItemService cartItemService;
	
	@Resource
	ICartService cartService;

	
	@ApiOperation(value = "自动登录", notes = "自动登录")
	@RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
	//@RequestMapping(value = "/in", method = RequestMethod.POST)
	public ResponseEntity<?> autoLogin(@RequestBody MemberRequest memberRequest) {

		JSONObject jb = new JSONObject();
		String wx_url ="https://api.weixin.qq.com/sns/jscode2session";
		StringBuffer params = new StringBuffer();
		params.append("appid=wx306ee4e859f342da&secret=15104aa340cf62bcc805aa25ab49157c&js_code=");
		params.append(memberRequest.getCode());
		params.append("&grant_type=authorization_code");
		
		//System.out.println(params.toString());
		JSONObject jObject = JSONObject.parseObject(HttpRequest.sendGet(wx_url, params.toString()));
		System.out.println(jObject);
		Member memberFind = memberService.findOne(jObject.getString("openid"));
		if (ToolUtil.isNotEmpty(memberFind)) {
			memberFind.setName(memberRequest.getName());
			memberFind.setSex(memberRequest.getSex());
			memberFind.setStatus(1);
			memberService.updateById(memberFind);
			memberFind.setOpenId("");
			memberFind.setPassword("");
			memberFind.setUnionId("");
			jb.put("userInfo", memberFind);
		}else{
			Member member = new Member();
			member.setName(memberRequest.getName());
			member.setOpenId(memberRequest.getOpenId());
			member.setSex(memberRequest.getSex());
			member.setStatus(1);
			member.setPermissionsId(1);
			member.setOpenId(jObject.getString("openid"));
			memberService.insert(member);
			member.setOpenId("");
			member.setPassword("");
			member.setUnionId("");
			jb.put("userInfo", member);
		}
		
		jb.put("code", "0");
		jb.put("status", 1);
		jb.put("token", "true");
		return ResponseEntity.ok(jb);
	}
	
	/**
	 * 获取用户个人信息详情
	 * 
	 * @param memberId
	 * @return
	 */
	@ApiOperation(value = "获取用户个人信息详情", notes = "获取用户个人信息详情")
	@RequestMapping(value = "/getMemberInfo", method = RequestMethod.POST)
	public ResponseEntity<?> getMemberInfo(@RequestBody MemberRequest memberRequest) {
		System.out.println("获取用户信息！");
		JSONObject jb = new JSONObject();
		Wrapper<Member> wrapper = new EntityWrapper<>();
		wrapper.eq("id", memberRequest.getMemberId());
		List<Map<String, Object>> memberList = memberService.selectMaps(wrapper);
		jb.put("data", memberList);
		return ResponseEntity.ok(jb);
	}
	
	/**
	 * 修改用户个人信息详情
	 * 
	 * @param memberId
	 * @return
	 */
	@ApiOperation(value = "修改用户个人信息详情", notes = "修改用户个人信息详情")
	@RequestMapping(value = "/updateMemberInfo", method = RequestMethod.POST)
	@ApiImplicitParam(name = "member", value = "用户信息", required = true, dataType = "Member", paramType = "body")
	public ResponseEntity<?> updateMemberInfo(@RequestBody MemberRequest memberRequest) {
		Member member = memberService.selectById(memberRequest.getMemberId());
		member.setSex(memberRequest.getSex());
		member.setName(memberRequest.getName());
		boolean num = memberService.updateById(member);
		if (num) {
			return ResponseEntity.ok(member);
		}
		return ResponseEntity.ok("修改失败");
	}
	
	/**
	 * 我的收货地址
	 */
	@ApiOperation(value = "获取我的收货地址列表", notes = "获取我的收货地址列表")
	@RequestMapping(value = "/myReceiver", method = { RequestMethod.POST })
	public   ResponseEntity<?> listReceiver(@RequestBody MemberRequest memberRequest) {
		JSONObject jb = new JSONObject();
		List<Map<String, Object>> addressList = addressService.myReceiver(memberRequest.getMemberId(),memberRequest.isIsdefault(),memberRequest.getAddressId());
		jb.put("data", addressList);
		return ResponseEntity.ok(jb);
	}
	
	/**
	 * 我的所有收货地址
	 */
	@ApiOperation(value = "获取我的收货地址列表", notes = "获取我的收货地址列表")
	@RequestMapping(value = "/findMyReceiver", method = { RequestMethod.POST })
	public   ResponseEntity<?> findMyReceiver(@RequestBody MemberRequest memberRequest) {
		JSONObject jb = new JSONObject();
		List<Address> addressList = addressService.findByMemberId(memberRequest.getMemberId());
		jb.put("data", addressList);
		return ResponseEntity.ok(jb);
	}
	
	/**
	 * 我的默认收货地址
	 */
	@ApiOperation(value = "获取我的默认收货地址", notes = "获取我的默认收货地址")
	@RequestMapping(value = "/findMyDefaultReceiver", method = { RequestMethod.POST })
	public   ResponseEntity<?> findMyDefaultReceiver(@RequestBody MemberRequest memberRequest) {
		JSONObject jb = new JSONObject();
		Address address = addressService.findMyDefaultReceive(memberRequest.getMemberId());
		jb.put("data", address);
		return ResponseEntity.ok(jb);
	}
	
	/**
	 * 添加收货地址
	 */
	@ApiOperation(value = "添加我的收货地址", notes = "添加我的收货地址")
	@RequestMapping(value = "/addReceiver", method = { RequestMethod.POST })
	@ApiImplicitParam(name = "address", value = "收货地址", required = true, dataType = "Address", paramType = "body")
	public ResponseEntity<?> addReceiver(@RequestBody Address address) {
		List<Address> aList = addressService.findByMemberId(address.getMemberId());
		if (ToolUtil.isEmpty(aList)) {
			address.setIsDefault(1);
		}
		boolean num = addressService.insert(address);
		if (num) {
			return ResponseEntity.ok("添加成功");
		}

		return ResponseEntity.ok("添加失败");
	}

	/**
	 * 修改收货地址
	 */
	@ApiOperation(value = "修改我的收货地址", notes = "修改我的收货地址")
	@RequestMapping(value = "/updateReceiver", method = { RequestMethod.POST })
	@ApiImplicitParam(name = "address", value = "收货地址", required = true, dataType = "Address", paramType = "body")
	public   ResponseEntity<?> updateReceiver(@RequestBody Address address) {
		JSONObject jb = new JSONObject();
		if(address.getIsDefault() == 1){
			List<Map<String, Object>> addressList = addressService.myReceiver(address.getMemberId(), true, null);
			if (ToolUtil.isNotEmpty(addressList)) {
				Address defaultAddress = addressService.selectById(Long.valueOf(String.valueOf(addressList.get(0).get("id"))));
				defaultAddress.setIsDefault(0);
				addressService.update(defaultAddress);
			}
		}
		boolean num = addressService.updateById(address);
		if (num) {
			return ResponseEntity.ok(address);
		}
		jb.put("errorMsg", "修改失败！");
		return ResponseEntity.ok(jb);
	}
	
	/**
	 * 修改收货地址
	 */
	@ApiOperation(value = "修改默认收货地址", notes = "修改我的收货地址")
	@RequestMapping(value = "/updateDefaultReceiver", method = { RequestMethod.POST })
	@ApiImplicitParam(name = "address", value = "收货地址", required = true, dataType = "Address", paramType = "body")
	public   ResponseEntity<?> updateDefaultReceiver(@RequestBody Address address) {
		Address addre = addressService.selectById(address.getId());
		if (addre.getMemberId() == address.getMemberId()) {
			addressService.setDefault(address.getId());
			return ResponseEntity.ok("修改成功");
		}
		
		return ResponseEntity.ok("修改失败");
	}
	

	/**
	 * 删除收货地址
	 */
	@ApiOperation(value = "删除收货地址", notes = "删除收货地址")
	@RequestMapping(value = "/deleteReceiver", method = { RequestMethod.POST })
	public ResponseEntity<?> deleteReceiver(@RequestBody MemberRequest memberRequest) {
		boolean num = addressService.deleteById(memberRequest.getAddressId());
		if (num) {
			return ResponseEntity.ok("成功删除！");
		}
		return ResponseEntity.ok("删除失败！");
	}
	
	
}
