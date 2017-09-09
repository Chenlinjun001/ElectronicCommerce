package com.dlut.ecwebsite.service;

import org.springframework.stereotype.Service;

import com.dlut.ecwebsite.entity.User;

@Service
public interface UserService {

	// �����û�
	public User saveUserByNameAndPwd(User user);

	// ���û��������û�
	public User findUserByName(String name);
	
	//�����û���Ϣ
	public int updateUserInfo(User user);
}
