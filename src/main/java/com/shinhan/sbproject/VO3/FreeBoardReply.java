package com.shinhan.sbproject.VO3;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_free_replies")
public class FreeBoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long rno;
	String reply;
	String replyer;
	
	@CreationTimestamp
	Timestamp regdate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	@ManyToOne //FK : board2_bno
	FreeBoard board2;
}
