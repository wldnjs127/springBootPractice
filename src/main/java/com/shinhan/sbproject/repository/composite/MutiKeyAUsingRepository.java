package com.shinhan.sbproject.repository.composite;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.VO4.MultiKeyA;
import com.shinhan.sbproject.VO4.MultiKeyAUsing;

public interface MutiKeyAUsingRepository 
		extends CrudRepository<MultiKeyAUsing, MultiKeyA> {

}
