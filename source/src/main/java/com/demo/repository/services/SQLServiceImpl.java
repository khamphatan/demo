package com.demo.repository.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.helpers.Utility;
import com.demo.message.response.UserInfoResponse;
import com.demo.model.Enums;
import com.demo.model.stored.SpUserInfoDto;
import com.demo.repository.SQLRepository;

@Service
public class SQLServiceImpl implements SQLRepository {
	private static final Logger logger = LoggerFactory.getLogger(SQLServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserInfoResponse findByReferenceCode(String refCode) {
		final UserInfoResponse model = new UserInfoResponse();

		Session session = (Session) entityManager.getDelegate();
		session.doWork(new Work() {
			@Override
			public void execute(Connection connectionToUse) throws SQLException {
				CallableStatement callableStatement = null;
				ResultSet rs = null;
				try {
					String sql = "{call SP_GET_USER_INFO (?)}";
					logger.info("- GetUserInformation(refCode = [" + refCode + "]) : SQL = " + sql);
					connectionToUse.setReadOnly(true);
					callableStatement = connectionToUse.prepareCall(sql);
					callableStatement.setString(1, Utility.Trim(refCode));
					rs = callableStatement.executeQuery();
					if (rs != null && rs.next()) {
						SpUserInfoDto data = FormatUserInformation(rs);

						model.setReferenceCode(data.getReferenceCode());
						model.setUsername(data.getUsername());
						model.setStatus(Enums.APPLICATION_STATUS.valueOf(data.getStatus()));

						model.setFirstName(data.getFirstName());
						model.setLastName(data.getLastName());
						model.setFirstNameEng(data.getFirstNameEng());
						model.setLastNameEng(data.getLastNameEng());
						model.setCitizenId(data.getCitizenId());
						model.setAddress(data.getAddress());
						model.setEmail(data.getEmail());
						model.setMobileNumber(data.getMobileNumber());
						model.setPhoneNumber(data.getPhoneNumber());
						model.setSalary(data.getSalary());
						model.setMemberType(Enums.MEMBER_TYPE.valueOf(data.getMemberType()));

						model.setTitleCode(Enums.TITLE_NAME.valueOf(data.getTitleCode()));
						model.setTitleName(data.getTitleName());
						model.setTitleNameEng(data.getTitleNameEng());

						model.setGenderCode(Enums.GENDER.valueOf(data.getGenderCode()));
						model.setGenderName(data.getGenderName());
						model.setGenderNameEng(data.getGenderNameEng());

					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("(-_-)!!! : ", e);
					throw e;
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (callableStatement != null)
							callableStatement.close();
					} catch (Exception ex) {
						ex.printStackTrace();
						logger.error("(-_-)!!! : ", ex);
					}
				}
			}
		});

		return model;
	}

	private SpUserInfoDto FormatUserInformation(ResultSet rs) throws SQLException {
		SpUserInfoDto data = new SpUserInfoDto();
		data.setReferenceCode(Utility.ObjectToString(rs.getObject("REFERENCE_CODE")));
		data.setUsername(Utility.ObjectToString(rs.getObject("USERNAME")));
		data.setStatus(Utility.ObjectToString(rs.getObject("STATUS")));

		data.setFirstName(Utility.ObjectToString(rs.getObject("FIRST_NAME")));
		data.setLastName(Utility.ObjectToString(rs.getObject("LAST_NAME")));
		data.setFirstNameEng(Utility.ObjectToString(rs.getObject("FIRST_NAME_ENG")));
		data.setLastNameEng(Utility.ObjectToString(rs.getObject("LAST_NAME_ENG")));
		data.setCitizenId(Utility.ObjectToString(rs.getObject("CITIZEN_ID")));
		data.setAddress(Utility.ObjectToString(rs.getObject("ADDRESS")));
		data.setEmail(Utility.ObjectToString(rs.getObject("EMAIL")));
		data.setMobileNumber(Utility.ObjectToString(rs.getObject("MOBILE_NUMBER")));
		data.setPhoneNumber(Utility.ObjectToString(rs.getObject("PHONE_NUMBER")));
		data.setSalary(Utility.ObjectToDouble(rs.getObject("SALARY")));
		data.setMemberType(Utility.ObjectToString(rs.getObject("MEMBER_TYPE")));

		data.setTitleCode(Utility.ObjectToString(rs.getObject("TITLE_CODE")));
		data.setTitleName(Utility.ObjectToString(rs.getObject("TITLE_NAME")));
		data.setTitleNameEng(Utility.ObjectToString(rs.getObject("TITLE_NAME_ENG")));

		data.setGenderCode(Utility.ObjectToString(rs.getObject("GENDER_CODE")));
		data.setGenderName(Utility.ObjectToString(rs.getObject("GENDER_NAME")));
		data.setGenderNameEng(Utility.ObjectToString(rs.getObject("GENDER_NAME_ENG")));
		return data;
	}
}