package com.shinhan.sbproject.VO2;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid")
//manytoone vs onetomany
//업무 방향을 생각해서 쓰자
public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	private String pwriter;
	//cascade 지울때 사용하는 속성
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	//fetch => 조회할때(EAGER 즉시 가져온다.)
	@JoinColumn(name = "pdsno") // PDSFile테이블에 pdsno 칼럼이 생성
	// JoinColumn이 없으면 중간 table이 생성된다.
	private List<PDSFile> files2;
}
