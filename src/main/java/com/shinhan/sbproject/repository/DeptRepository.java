package com.shinhan.sbproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.VO.DeptVO;


//1. CrudRepository 상속 => 가능한 것 : findAll,findById, deleteById
public interface DeptRepository extends CrudRepository<DeptVO, Integer> {

	//2. 규칙에 맞는 함수 정의
	//특정 managerid가 관리하는 부서들의 부서이름 뒤에 "OK"라는 문자를 추가(수정)
	List<DeptVO >findByManagerId(int mid);
}
