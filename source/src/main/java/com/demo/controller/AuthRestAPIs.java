package com.demo.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.helpers.Utility;
import com.demo.message.request.LoginForm;
import com.demo.message.request.SignUpForm;
import com.demo.message.response.BaseResponse;
import com.demo.message.response.JwtResponse;
import com.demo.message.response.UserInfoResponse;
import com.demo.model.Enums;
import com.demo.model.Role;
import com.demo.model.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.SQLRepository;
import com.demo.repository.UserRepository;
import com.demo.security.jwt.JwtProvider;
import com.demo.security.services.UserPrinciple;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs extends BaseRestAPIs {
	private static final Logger logger = LoggerFactory.getLogger(AuthRestAPIs.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	SQLRepository sqlRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm requestModel) {
		logger.info(" -> signin");

		BaseResponse responseModel = new BaseResponse();
		try {

			if (Utility.VerifyIsEmpty(requestModel.getUsername())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Username");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (Utility.VerifyIsEmpty(requestModel.getPassword())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Password");
				return ResponseEntity.badRequest().body(responseModel);
			}
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateJwtToken(authentication);
			
			responseModel.setSuccess(true);
			responseModel.setResponseType(Enums.RESPONSE_TYPE.SUCCESS);
			responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_0000);
			responseModel.setResponseMsg(Enums.RESPONSE_TYPE.SUCCESS.Description());
			responseModel.setData(new JwtResponse(jwt));
			
			return ResponseEntity.ok(responseModel);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("(-_-)!!! : ", ex);
			responseModel.setSuccess(false);
			responseModel.setResponseType(Enums.RESPONSE_TYPE.ERROR);
			responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9000);
			responseModel.setResponseMsg(ex.getMessage());
			return ResponseEntity.badRequest().body(responseModel);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm requestModel) {
		logger.info(" -> signup");

		BaseResponse responseModel = new BaseResponse();
		try {

			if (Utility.VerifyIsEmpty(requestModel.getUsername())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Username");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (Utility.VerifyIsEmpty(requestModel.getPassword())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Password");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (requestModel.getTitleCode() == null) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("TitleCode");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (Utility.VerifyIsEmpty(requestModel.getFirstName())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("FirstName");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (Utility.VerifyIsEmpty(requestModel.getLastName())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("LastName");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (Utility.VerifyIsEmpty(requestModel.getCitizenId())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("CitizenId");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (!Utility.VerifyIsEmpty(requestModel.getEmail())
					&& !Utility.VerifyEmailFormat(requestModel.getEmail())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Email");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (requestModel.getGenderCode() == null) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("GenderCode");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (!Utility.VerifyMobileFormat(requestModel.getMobileNumber())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("MobileNumber");
				return ResponseEntity.badRequest().body(responseModel);
			}
			if (requestModel.getSalary() <= 0) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Salary");
				return ResponseEntity.badRequest().body(responseModel);
			}

			/*
			 * Member type classify from salary
			 * 	- Platinum (salary > 50,000 baht)
			 * 	- Gold (salary between 30,000 to 50,000)
			 * 	- Silver (salary < 30,000)
			 * Reject if salary < 15,000
			 */
			Enums.MEMBER_TYPE memberType = Enums.MEMBER_TYPE.SILVER;
			if (requestModel.getSalary() > 50000) {
				memberType = Enums.MEMBER_TYPE.PLATINUM;
			} else if (requestModel.getSalary() >= 30000 && requestModel.getSalary() <= 50000) {
				memberType = Enums.MEMBER_TYPE.GOLD;
			} else if (requestModel.getSalary() >= 15000 && requestModel.getSalary() < 30000) {
				memberType = Enums.MEMBER_TYPE.SILVER;
			} else {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Salary < 15,000 baht");
				return ResponseEntity.badRequest().body(responseModel);
			}

			if (userRepository.existsByUsername(requestModel.getUsername())) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9004);
				responseModel.setResponseMsg("Username");
				return ResponseEntity.badRequest().body(responseModel);
			}
			
			String referenceCode = GetReferenceCode(new Date(), requestModel.getMobileNumber());
			
			// Creating user's account
			User user = new User();
			user.setReferenceCode(referenceCode);
			user.setUsername(requestModel.getUsername());
			user.setEncodedPassword(encoder.encode(requestModel.getPassword()));
			user.setStatus(Enums.APPLICATION_STATUS.ACTIVE);
			user.setTitleCode(requestModel.getTitleCode());
			user.setFirstName(requestModel.getFirstName());
			user.setLastName(requestModel.getLastName());
			user.setFirstNameEng(requestModel.getFirstNameEng());
			user.setLastNameEng(requestModel.getLastNameEng());
			user.setCitizenId(requestModel.getCitizenId());
			user.setAddress(requestModel.getAddress());
			user.setEmail(requestModel.getEmail());
			user.setGenderCode(requestModel.getGenderCode());
			user.setMobileNumber(requestModel.getMobileNumber());
			user.setPhoneNumber(requestModel.getPhoneNumber());
			user.setSalary(requestModel.getSalary());
			user.setMemberType(memberType);

			Set<String> strRoles = requestModel.getRole();
			Set<Role> roles = new HashSet<>();
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(Enums.ROLE.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(Enums.ROLE.ROLE_USER).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(userRole);
				}
			});
			user.setRoles(roles);
			
			userRepository.save(user);
			
			responseModel.setSuccess(true);
			responseModel.setResponseType(Enums.RESPONSE_TYPE.SUCCESS);
			responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_0000);
			responseModel.setResponseMsg(Enums.RESPONSE_TYPE.SUCCESS.Description());
			return ResponseEntity.ok(responseModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("(-_-)!!! : ", ex);
			responseModel.setSuccess(false);
			responseModel.setResponseType(Enums.RESPONSE_TYPE.ERROR);
			responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9000);
			responseModel.setResponseMsg(ex.getMessage());
			return ResponseEntity.badRequest().body(responseModel);
		}
	}
	
	@GetMapping("/userinfo/{refCode}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> userInfo(@PathVariable("refCode") String refCode) {
		logger.info(" -> userinfo : -> refCode = [" + refCode + "]");

		BaseResponse responseModel = new BaseResponse();
		try {

			if (Utility.VerifyIsEmpty(refCode)) {
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Reference Code");
				return ResponseEntity.badRequest().body(responseModel);
			}
			
			UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(userPrinciple.getRoles().contains(Enums.ROLE.ROLE_ADMIN) || refCode.equalsIgnoreCase(userPrinciple.getRefcode())) {
				UserInfoResponse data = sqlRepository.findByReferenceCode(refCode);
				
				responseModel.setSuccess(true);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.SUCCESS);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_0000);
				responseModel.setResponseMsg(Enums.RESPONSE_TYPE.SUCCESS.Description());
				responseModel.setData(data);
				return ResponseEntity.ok(responseModel);
			}else{
				responseModel.setSuccess(false);
				responseModel.setResponseType(Enums.RESPONSE_TYPE.WARNING);
				responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9002);
				responseModel.setResponseMsg("Authorize : Reference Code");
				return ResponseEntity.badRequest().body(responseModel);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("(-_-)!!! : ", ex);
			responseModel.setSuccess(false);
			responseModel.setResponseType(Enums.RESPONSE_TYPE.ERROR);
			responseModel.setResponseCode(Enums.RESPONSE_CODE.RES_9000);
			responseModel.setResponseMsg(ex.getMessage());
			return ResponseEntity.badRequest().body(responseModel);
		}
	}
}