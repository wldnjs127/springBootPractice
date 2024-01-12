package com.shinhan.sbproject.repository.composite;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.VO4.UserVO;

public interface UserVORepository
	extends CrudRepository<UserVO, String>{

}
