package com.xa3ti.business.service.impl;

import com.xa3ti.base.util.BeanMapper;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.repository.AdminRepository;
import com.xa3ti.business.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AdminService")
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public Admin validAdminUser(String userName, String password) {
		Admin dbItem = adminRepository.findByAdminName(
				userName);
		if (XaUtil.isNotEmpty(dbItem)) {
			if (dbItem.getPassword().equals(password)) {
				Admin user = new Admin();
				BeanMapper.copy(dbItem, user);
				return user;
			}
		}
		return null;
	}

	
	
}
