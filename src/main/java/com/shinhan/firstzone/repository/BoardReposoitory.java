package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shinhan.sbproject.vo.BoardVO;

//CrudRepository 상속벋은 PagingAndSortingRepository
//1. 기본 crud 작업 -> CrudRepository 상속 => 가능한 것 : findAll,findById, deleteById, save, count
public interface BoardReposoitory extends CrudRepository<BoardVO,Long> 
	, PagingAndSortingRepository<BoardVO, Long> 
	, QuerydslPredicateExecutor<BoardVO>{
	//2. 특정 규칙에 맞는 메서드 정의
	List<BoardVO> findByWriter(String writer2);
	List<BoardVO> findByContent(String content2); //where content=?
	List<BoardVO> findByBnoGreaterThan(Long bno); //where bno>=?
	List<BoardVO> findByContentLike(String content2); //where content like?
	List<BoardVO> findByContentContaining(String content3); //where content like'%'||?||'%'?
	List<BoardVO> findByBnoGreaterThanAndBnoLessThanEqual(Long bno, Long bno2);
	List<BoardVO> findByWriterOrderByRegDateDesc(String writer);
	List<BoardVO> findByContentContainingOrTitleContaining(String Content2, String title);
	
	//특정 writer가 작성한 board 건 수
	int countByWriter(String writer2);
	
	//paging, sort추가 <- Oracle 12부터 사용가능한 구문
	List<BoardVO> findByBnoGreaterThan(Long bno,Pageable paging);
	
	//3.JPQL(JPA Query Language : 규칙에 맞는 함수정의가 한계가 있음. 이를 해결하는 방법.
	@Query("select b from BoardVO b "
			+ "where b.title like %?1% and b.writer like %?2% and b.bno > ?3 "
			+ "order by b.bno desc ")
	List<BoardVO> selectByTitleAndWriter2(String title, String writer, Long bno);
	
	//4.SQL문 : nativeQuery, table 이름, *가능
	@Query(value = "select * from t_boards_review b "
			+ "where b.title like %?1% and b.writer like %?2% and b.bno > ?3 "
			+ "order by bno desc",nativeQuery = true)
	List<BoardVO> selectByTitleAndWriter3(String title, String writer,Long bno);
	
	@Query("select b from BoardVO b "
			+ "where b.title like %:bb% and b.writer like %:dd% and b.bno > :cc "
			+ "order by bno desc")
	List<BoardVO> selectByTitleAndWriter4(@Param("bb")String title,@Param("dd") String writer,@Param("cc")Long bno);
	
	@Query("select b from #{#entityName} b "
			+ "where b.title like %:bb% and b.writer like %:dd% and b.bno > :cc "
			+ "order by bno desc")
	List<BoardVO> selectByTitleAndWriter5(@Param("bb")String title,@Param("dd") String writer,@Param("cc")Long bno);

	@Query("select b.title, b.writer, b.content from #{#entityName} b "
			+ "where b.title like %:bb% and b.writer like %:dd% and b.bno > :cc "
			+ "order by bno desc")
	List<Object[]> selectByTitleAndWriter6(@Param("bb")String title,@Param("dd") String writer,@Param("cc")Long bno);

	@Query("select board.title, board.writer, board.content, board.bno, board.regDate from #{#entityName} board where board.writer = :dd")
	List<String[]> selectByWriter(@Param("dd") String writer);
}