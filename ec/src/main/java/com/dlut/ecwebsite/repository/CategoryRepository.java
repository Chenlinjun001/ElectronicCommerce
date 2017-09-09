package com.dlut.ecwebsite.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dlut.ecwebsite.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	//ͨ��cate_id��ѯ����ѯ���������Ϣ
	@Query(value="SELECT category.*  FROM  category where cate_super=?1",nativeQuery=true)
	public Set<Category> findByCateSuperId(int id);
	
	//���Ұ���id������·��
	@Query(value="SELECT  get_category(?1);",nativeQuery=true)
	public String findByCatePath(int id);
}
