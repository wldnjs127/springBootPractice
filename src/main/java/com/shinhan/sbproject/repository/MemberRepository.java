package com.shinhan.sbproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.VO.MemberDTO;

public interface MemberRepository extends CrudRepository<MemberDTO, String>{

}
