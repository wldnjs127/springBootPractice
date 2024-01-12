package com.shinhan.sbproject.VO3;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @ToString(exclude = "board2")
@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "tbl_freeboards")
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bno;
	@Column(nullable = false)
	String title;
	String writer;
	String content;
	
	@CreationTimestamp
	Timestamp regdate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	//무한 loop 되지 않도록 FreeBoardReply -> 다시 FreeBoard로 가는것을 막아야함.
	//@JsonIgnore //자바객체가 브라우저로 내려갈때 제이슨 데이터로 변경되어 내려간다 <- com.fasterxml.jackson.databind 오류때문에 추가
	//연관관계설정하기...하나의 board에 댓글이 여러개이다.
	@OneToMany(mappedBy = "board2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<FreeBoardReply> replies;

}
