package com.md.member.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.member.dao.PermissionsMapper;
import com.md.member.model.Permissions;
import com.md.member.service.IPermissionsService;

@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService{

}
