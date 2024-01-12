package com.shinhan.sbproject.VO5;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Emp11VO {
	@Id
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private Double salary; 
	//Wrapper class�� ������ ���� :jsp���������� ���Է¾��� null=>�⺻�������� ���� 
	@Column(nullable = true)
	private Double commissionPct;
	@Column(nullable = true)
	private Integer managerId;
	@Column(nullable = true)
	private Integer departmentId;
	 
	 
	 
    
	 
}
