package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private BoardService boardService;
	
	@Autowired
	private ResourceLoader resourceLoader;

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
	
	/**
	 * ResponseEntity를 사용하는 이유
	 * 1. status code를 커스터마이징 할 수 있다.
	 * 2. 응답 Header값을 커스터마이징 하기 편하다(Response 객체 가져오지 않아도됨)
	 * 3. 자동으로 @ResponseBody 기능을 내포하고있다.
	 * 
	 * @param no
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping("fileDownload.do")
	public ResponseEntity<Resource> fileDownloadWithResponseEntity(@RequestParam int no) throws UnsupportedEncodingException{
		ResponseEntity<Resource> responseEntity = null;
		try {
		//1. 업무로직 : db조회 
		Attachment attach = boardService.selectOneAttachment(no+1);
		if(attach == null) {
			return ResponseEntity.notFound().build();
		}
		
		//2. Resource객체 만들기
		String saveDirectory = application.getRealPath("/resources/upload/board");
		File downFile = new File(saveDirectory, attach.getRenamedFilename());
		Resource resource = resourceLoader.getResource("file:"+downFile);
		
		String filename = new String(attach.getOriginalFilename().getBytes("utf-8"), "iso-8859-1");
		//3. ResponseEntity객체 생성 및 리턴
		//3.1 Builder패턴 (두가지중 하나 쓰면됨)
		responseEntity = 
				ResponseEntity
					.ok()//응답코드 200으로 설정
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+filename)
					.body(resource);
			
		} catch (Exception e) {
			log.error("파일 다운로드 오류",e);
			throw e;
		}
		return responseEntity;
	}
	
//	@GetMapping(value = "/fileDownload.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody //응답메세지에 return 객체를 직접 출력
	//Resouce = org.springframework.core.io.Resource;
	public Resource fileDownload(@RequestParam int no, HttpServletResponse response) throws UnsupportedEncodingException {
		log.debug("no = {}" , no);
		//1. 업무로직 : db에서 첨부파일 정보 조회
		Attachment attach = boardService.selectOneAttachment(no);
		log.debug("attach = {}", attach);
		
		if(attach == null) {
			throw new IllegalArgumentException("해당 첨부파일은 존재하지 않습니다 : "+no);
		}
		
		//2. Resource객체를 리턴 : 응답메세지에서 출력은 String-container가 처리
		String originalFilename = attach.getOriginalFilename();
		String renamedFilename = attach.getRenamedFilename();
		String saveDirectory = application.getRealPath("/resources/upload/board");
		File downFile = new File(saveDirectory, renamedFilename);
		
		//웹상의 자원, 서버컴퓨터의 자원을 모두 다룰 수 있는 스프링의 추상화 layer
		//파라미터로는 파일다운로드 할수있는 프로토콜을 보내주는것
		String location = "file:"+downFile.toString();
		log.debug("location = {}",location);
		Resource resource = resourceLoader.getResource(location);
		
		//3. 응답헤더 
		
		//한글 깨짐 방지처리
		originalFilename = new String(originalFilename.getBytes("utf-8"),"iso-8859-1");
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+originalFilename);
		
		
		return resource;
	}
	
	@GetMapping("autocomplete.do")
	public ResponseEntity<List<Map<String, Object>>> autocomplete(@RequestParam String search){
		log.debug("search = {}", search);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<BoardExt> list2 = boardService.selectBoardList(search);
		for(BoardExt b : list2) {
			Map<String, Object> map = new HashMap<>();
			map.put("label", b.getTitle());
			map.put("value", b.getNo());
			list.add(map);
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_UTF8_VALUE).body(list);
	}
}
