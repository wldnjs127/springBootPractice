package com.shinhan.sbproject.repository.composite;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.VO4.MultiKeyB;
import com.shinhan.sbproject.VO4.MultiKeyBDTO;

public interface MutiKeyBRepository 
		extends CrudRepository<MultiKeyBDTO, MultiKeyB> {

}
