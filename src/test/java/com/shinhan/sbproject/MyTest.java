package com.shinhan.sbproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shinhan.firstzone.repository.CarRepository;
import com.shinhan.sbproject.VO.CarVO;

import lombok.extern.slf4j.Slf4j;


/*JpaRepository가 기본패키지 하위에 존재하지 않는 경우 추가함 : @EnableJpaRepositories
 * 
 * */
@ComponentScan(basePackages = {"com.shinhan.firstzone"})
@EnableJpaRepositories({"com.shinhan.firstzone"})
@Slf4j
@SpringBootTest
public class MyTest {

	@Autowired
	CarRepository repo;

	@Test
	void f4() {
		repo.findAll().forEach(car -> {
			log.info(car.toString());
		});
	}

	//@Test
	void f3() {
		IntStream.rangeClosed(1, 10).forEach(i -> {

			CarVO c1 = CarVO.builder().model("ABC" + i).price(i * 2000).build();
			repo.save(c1);
		});
	}

}
