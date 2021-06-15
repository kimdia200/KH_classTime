package com.kh.spring.websocket.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	private String from;
	private String to;
	private String message;
	private String type;
	private long date;
}
