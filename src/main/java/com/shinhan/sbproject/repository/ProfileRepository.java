package com.shinhan.sbproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.VO.MemberDTO;
import com.shinhan.sbproject.VO.ProfileDTO;

public interface ProfileRepository extends CrudRepository<ProfileDTO, Long>
	, QuerydslPredicateExecutor<ProfileDTO>{

	List<ProfileDTO> findByMember(MemberDTO member);
	List<ProfileDTO> findByCurrentYnIsTrue();
	
	@Query(value = "select m.mname, p.fname from tbl_members_pjw2 m "
			+ "left outer join tbl_profile_pjw2 p on(m.mid = p.member_mid )", nativeQuery = true)
	List<Object[]> selectByJoin();
	
}
