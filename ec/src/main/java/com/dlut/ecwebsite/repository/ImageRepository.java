package com.dlut.ecwebsite.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dlut.ecwebsite.entity.Images;

public interface ImageRepository extends JpaRepository<Images, Integer> {

	// ����Ʒ��id������ͼƬ��ַ
	@Query(value = "select img from Goods g join g.images img  WHERE g.id=?1")
	public Set<Images> findImagesByGoodsID(int id);
}
