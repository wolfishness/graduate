package com.md.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.util.ToolUtil;
import com.md.member.dao.AddressMapper;
import com.md.member.model.Address;
import com.md.member.service.IAddressService;

@Service
@Transactional
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService{

	@Resource
	AddressMapper addressMapper;
	
	@Override
	public List<Address> findByMemberId(Long memberId) {
		Wrapper<Address> wrapper = new EntityWrapper<>();
		wrapper.eq("memberId", memberId);
		return addressMapper.selectList(wrapper);
	}
	
	@Override
	public boolean insert(Address address) {
		Address address2  = addressMapper.selectOne(address);
		if (ToolUtil.isNotEmpty(address2)) {
			return true;
		}else{
			int num = addressMapper.insert(address);
			if (num > 0) {
				return true;
			}
			return false;
		}
		
	}

	@Override
	public void update(Address address) {
		addressMapper.updateById(address);
	}

	@Override
	public void delete(Long addressId) {
		addressMapper.deleteById(addressId);
	}

	@Override
	public void setDefault(Long addressId) {
		Address address = addressMapper.selectById(addressId);
		// 将该用户的其他默认地址设为false
		Wrapper<Address> wrapper = new EntityWrapper<>();
		wrapper.eq("memberId", address.getMemberId());
		wrapper.eq("isDefault", 1);
		List<Address> selectList = addressMapper.selectList(wrapper);
		if (selectList != null) {
			for (Address oldAddress : selectList) {
				oldAddress.setIsDefault(0);
				addressMapper.updateById(oldAddress);
			}
		}
		// 将该地址的是否是默认地址，设为true
		address.setIsDefault(1);
		this.update(address);
	}

	@Override
	public List<Map<String, Object>> myReceiver(Long memberId, boolean isdefault,Long addressId) {
		// TODO 自动生成的方法存根
		Wrapper<Address> wrapper = new EntityWrapper<>();
		wrapper.eq("memberId", memberId);
		if(ToolUtil.isNotEmpty(addressId)){
			wrapper.eq("id", addressId);
		}
		if(isdefault){
			wrapper.eq("isdefault", isdefault);
		}
		return addressMapper.selectMaps(wrapper);
	}
	
	@Override
	public Address findMyDefaultReceive(Long memberId){
		Address address = new Address();
		address.setMemberId(memberId);
		address.setIsDefault(1);

		return addressMapper.selectOne(address);
	}
}
