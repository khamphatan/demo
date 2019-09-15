package com.demo.repository;

import org.springframework.stereotype.Repository;

import com.demo.message.response.UserInfoResponse;

@Repository
public interface SQLRepository{
	UserInfoResponse findByReferenceCode(String refCode);
}