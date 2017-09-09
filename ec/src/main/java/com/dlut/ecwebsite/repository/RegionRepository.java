package com.dlut.ecwebsite.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dlut.ecwebsite.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

	// ��������reg_id��ֱ���Ӻ���
	@Query(value = "SELECT  region.* from region where reg_super =?1", nativeQuery = true)
	public Set<Region> findRegionChildById(int reg_id);
	
	//���Ұ���id������·��
	@Query(value="select get_region(?1)",nativeQuery=true)
	public String findRegionPathById(int reg_id);

}
