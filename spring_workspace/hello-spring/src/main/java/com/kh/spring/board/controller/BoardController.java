package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.BoardExt;
import com.kh.spring.common.util.HelloSpringUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Autowired
	private ServletContext application;
	
	@Autowired
	BoardService boardService;

	@GetMapping("/boardList.do")
	public void boardList(@RequestParam(required = true, defaultValue = "1") int cPage, Model model, HttpServletRequest request) {
		
		try {
			log.debug("cPage = {}",cPage);
			final int limit = 10; //한페이지에 10개씩 보여줄거임
			final int offset = (cPage - 1) * limit;//앞에 몇개를 건너 뛸건지
			Map<String, Object> param = new HashMap<>();
			param.put("limit", limit);
			param.put("offset", offset);
			//1. 업무로직 : content영역 - Rowbounds
			List<BoardExt> list = boardService.selectBoardList(param);
			
			int totalContent = boardService.selectBoardTotalContents();
			String url = request.getRequestURI();
			log.debug("totalContent={}, url = {}",totalContent,url);
			String pageBar = HelloSpringUtils.getPageBar(totalContent, cPage, limit, url);
			
			//2. jsp에 위임
			model.addAttribute("list",list);
			model.addAttribute("pageBar", pageBar);
			log.debug("list = {}", list);
		} catch (Exception e) {
			log.debug("list loading 실패 {}", e);
			throw e;
		}
	}
	
	@GetMapping("/boardForm.do")
	public void boardForm() {
		
	}
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(@ModelAttribute BoardExt board,
							  //MultipartFile upFile  파일이 하나일경우
							  @RequestParam(name="upFile") MultipartFile[] upFiles,
							  RedirectAttributes redirectAttr
							  ) throws IllegalStateException, IOException {
		log.debug("board = {}", board);
		
		//파일을 업로드 하지 않아도 null이 넘어오진 않는다
//		for(MultipartFile upFile : upFiles) {
//			log.debug("upFile = {}", upFile);
//			log.debug("upFile.name = {}", upFile.getOriginalFilename());
//			log.debug("upFile.size = {}", upFile.getSize());
//			log.debug("--------------------------------------------");
//		}
		
		//1. 파일 저장 : 절대경로 /resources/upload/board
		//생명주기 pageContext:PageContext - request:HttpServletRequest - session:HttpSession - application:ServletContext
		String saveDirectory = application.getRealPath("/resources/upload/board");
		
		//디렉토리 생성
		File dir = new File(saveDirectory);
		if(!dir.exists()) {
			dir.mkdirs(); //복수개의 디렉토리를 생성할수있다
		}
		List<Attachment> attachList = new ArrayList<>();
		for(MultipartFile upFile : upFiles) {
			//upFile은 파일이없어도 null로 나오지 않기 때문에 empty로 처리함
			//비어있으면 continue로 반복문 다음꺼실행
			if(upFile.isEmpty()) continue;
			
			//a. 서버컴퓨터에 저장
			//renamedPolicy 손수구현...
			String renamedFiledname = HelloSpringUtils.getRenamedFilename(upFile.getOriginalFilename());
			
			//saveDirectory에 renamedFilename파일을 만듦
			File dest = new File(saveDirectory, renamedFiledname);
			upFile.transferTo(dest);//파일 이동
			
			//b. 저장된 데이터를 Attachment객체에 저장 및 list에 추가
			Attachment attach = new Attachment();
			attach.setOriginalFilename(upFile.getOriginalFilename());
			attach.setRenamedFilename(renamedFiledname);
			attachList.add(attach);
			
		}
		
		log.debug("attachList = {}",attachList);
		//board객체에 설정
		board.setAttachList(attachList);
		//2. 업무로직 : db저장 board, attachment
		int result = boardService.insertBoard(board);
		
		//3. 사용자 피드백 & 리다이렉트
		redirectAttr.addFlashAttribute("msg", "게시글등록 성공!");
		
		return "redirect:/board/boardDetail.do?no="+board.getNo();
	}
	
	@GetMapping("/boardDetail.do")
	public void selectOneBoard(@RequestParam int no, Model model) {
		//1. 업무로직
//		BoardExt board = boardService.selectBoardOne(no);
		BoardExt board = boardService.selectBoardOneCollection(no);
		
		model.addAttribute("board",board);
	}
	
	@GetMapping("/fileDownload.do")
	public void fileDownload(@RequestParam int no) {
		log.debug("no = {}" , no);
	}
}
