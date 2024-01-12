package com.shinhan.sbproject.VO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity //JPA가 객체를 Entity라고 명명
@Table(name="tbl_car_jw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarVO {
	@Id //필수 annotation
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long carNum;
	String model;
	int price;
}