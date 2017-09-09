package com.dlut.ecwebsite.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dlut.ecwebsite.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	// ��ȡ���ﳵ���е��������
	@Query(value = "select c from Cart c where c.user.id=?1")
	public Set<Cart> findCartById(int user_id);

	// ���ﳵ�������Ʒ
	// �ø��ӿ�

	// �޸Ĺ��ﳵ���������
	@Query(value = "UPDATE cart SET  cart_amount=?2 where cart_id =?1", nativeQuery = true)
	@Modifying
	public int updateCart(int cart_id, int amount);

	// ɾ�����ﳵ����
	@Query(value = "DELETE from cart where cart_id=?1", nativeQuery = true)
	@Modifying
	public int deleteCart(int cart_id);

	// ����ĳ�ù��ﳵ����
	@Query(value = "select count(cart_id) from cart where user_id=?1", nativeQuery = true)
	public int getCartCount(int user_id);

	// �жϹ��ﳵ�Ƿ���ڴ���Ʒ
	@Query(value = "select cart.* from cart where user_id=?1 and goods_id=?2", nativeQuery = true)
	public Cart getCartItem(int user_id, int goods_id);

}
