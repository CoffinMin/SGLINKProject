package com.sglink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
	Member findByUserEmail(String email);
	Member findByUserId(String id);
	
	static final String updateMember = "UPDATE user set "
			+ "user_name = :#{#member.userName}, "
			+ "user_pw = :#{#member.userPw}, "
			+ "update_time = SYSDATE() "
			+ "WHERE user_id = :#{#userId}";
	
	static final String updateMemberBoardId = "UPDATE user set "
			+ "board_id = :#{#boardId} "
			+ "WHERE user_id = :#{#userId}";
	
	static final String duplicatePw = "select user_pw from user where user_id = :#{#userId} and user_pw = :#{#userPw}";
	static final String duplicateName = "select user_Name from user where user_id = :#{#userId} and user_name = :#{#userName}";
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = updateMember, nativeQuery = true)
	public int updateMember(@Param("member") Member member, @Param("userId") String userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = updateMemberBoardId, nativeQuery = true)
	public int updateMemberBoardId(@Param("boardId") Long boardId, @Param("userId") String userId);
	
	@Transactional
	@Query(value = duplicatePw, nativeQuery = true)
	public String duplicatePw(@Param("userId") String userId, @Param("userPw") String userPw);
	
	@Transactional
	@Query(value = duplicateName, nativeQuery = true)
	public String duplicateName(@Param("userId") String userId, @Param("userName") String userName);
	
}
