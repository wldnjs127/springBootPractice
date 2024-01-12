package com.shinhan.sbproject.webBoard;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WebReplyRepository
	extends CrudRepository<WebReply, Long>, PagingAndSortingRepository<WebReply, Long>,
	QuerydslPredicateExecutor<WebReply>{

}
