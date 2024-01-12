package com.shinhan.sbproject.VO;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NonNull
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Data
@Builder
@Entity
@Table(name="tbl_departments")
public class DeptVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//Oracle : sequence, MySQL : auto increment(identified->auto로 하면 table로 생성됨)
	Integer departmentId;
	@Column(length = 50)
	String departmentName;
	
	Integer managerId;
	Integer locationId;
	
	@CreationTimestamp
	Timestamp regDate;
	
	@UpdateTimestamp
	Timestamp updateDate;
}
