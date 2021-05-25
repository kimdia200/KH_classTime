package com.kh.spring.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Attachment {
	private int no;
	private int boardNo;
	private String orginalFilename;
	private String renamedFilename;
	private Date upload;
	private int downloadCount;
	private boolean status;  //DB-status  = check('Y','N') 이기 때문에 typeHandler 필요
	
}
