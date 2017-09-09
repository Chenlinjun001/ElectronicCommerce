package com.dlut.ecwebsite.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dlut.ecwebsite.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	// ����ʵ��

	// ��ȡ�û��������ջ���ַ
	@Query(value = "select address.* from address where user_id=?1", nativeQuery = true)
	public Set<Address> getAddressListByUserId(int user_id);

	// �޸ĵ�ַ
	@Query(value = "update address set addr_detail=?2 and addr_zipcode=?3 and reg_id=?4 where addr_id=?1 ", nativeQuery = true)
	@Modifying
	public int updateAddress(int addr_id, String addr_detail, String addr_zipcode, int reg_id);

	// ����ΪĬ�ϵ�ַ
	@Query(value = "update address set is_default=1 where  addr_id=?1", nativeQuery = true)
	@Modifying
	public int setAddressDefault(int addr_id);

	// ɾ���ջ��ַ
	@Query(value = "delete FROM address where addr_id=?1", nativeQuery = true)
	@Modifying
	public int deleteAddressByaddr_id(int addr_id);

}
