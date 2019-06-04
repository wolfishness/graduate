package com.md.member.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.md.member.model.Address;

public interface IAddressService extends IService<Address>{

	List<Address> findByMemberId(Long memberId);

	boolean insert(Address address);

	void update(Address address);

	void delete(Long addressId);

	void setDefault(Long addressId);

	List<Map<String, Object>> myReceiver(Long memberId, boolean isdefault, Long addressId);

	Address findMyDefaultReceive(Long memberId);

}
