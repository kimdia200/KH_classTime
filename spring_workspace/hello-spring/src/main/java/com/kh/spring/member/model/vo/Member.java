package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String id;
	private String password;
	private String name;
	private String gender;
	/**
	 * 지금은 시분초 필요없어서 sql.date
	 * 필요하면 util.date로 하면된다
	 */
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	//enabled는 활성화여부를 0,1로 관리함 true=1, false=0
	private boolean enabled;
}
