package com.dlut.ecwebsite.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dlut.ecwebsite.entity.Cart;

@Service
public interface UserInfoService {

	// ��ȡ���ﳵ��Ϣ
	public List<Map<String, Object>> getCartInfo(int user_id);

	// ���ﳵ�������Ʒ
	public int addCart(int user_id, int goods_id, int amount);

	// �޸Ĺ��ﳵ���������
	public int updateCart(int cart_id, int amount);

	// ɾ�����ﳵ����
	public int deleteCart(int cart_id, int user_id);

	// ��ȡ������Ϣ
	public Map<String, List<Map<String, String>>> getRegionInfo(int reg_id);

	// ���һ���ջ���ַ
	public int addAddress(int user_id,String addr_detail, String addr_zipcode, int reg_id);

	// �޸��ջ���ַ
	public int modifyAddress(int addr_id, String addr_detail, String addr_zipcode, int reg_id);
	
	//ɾ���ջ���ַ
	public int deleteAddress(int addr_id);
	
	//����ΪĬ���ջ��ַ
	public Boolean setDefaultAddress(int addr_id);
	
	//��ȡ�����ջ���ַ
	public List<Map<String, Object>> getAddressList(int user_id);
}
