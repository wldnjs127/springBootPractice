package com.shinhan.sbproject.VO4;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="tbl_usercellphone2")
public class UserCellPhoneVO2 {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="phone_id")
Long phoneId;
String phoneNumber;
String model;

//대상테이블에서 참조하기...비식별자 (FK)
//cascade 의미(연관 table 영향) : 주 테이블 dML수행시 주에 영향을 주기
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "user_id")
UserVO2 user;
}

