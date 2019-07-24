package com.ez.jamong.message.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ez.jamong.common.FileUploadUtility;
import com.ez.jamong.message.model.MessageService;
import com.ez.jamong.message.model.MessageVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

@Controller
public class MessageController {
	private Logger logger = LoggerFactory.getLogger(MessageController.class);
	@Autowired private MessageService messageService;	
	@Autowired private FileUploadUtility fileUtility;
	
	@RequestMapping("/main/mypage/message.do")
	public String message(Model model,HttpServletRequest request) {
		logger.info("쪽지함 화면");
		List<MessageVO> list=null;
		HttpSession session = request.getSession();
		list = messageService.selectRecieveMessage((String)session.getAttribute("userId"));
		model.addAttribute("list", list);
		return "main/mypage/message";
	}
	
	@RequestMapping(value="/main/mypage/messageT.do", method = RequestMethod.GET)
	public String message_get(@RequestParam int type,Model model,HttpServletRequest request) {
		logger.info("쪽지함 화면 type={}",type);
		List<MessageVO> list=null;
		HttpSession session = request.getSession();
		if(type==1) {
			list = messageService.selectRecieveMessage((String)session.getAttribute("userId"));
		}else if(type==2) {
			list = messageService.selectSendMessage((String)session.getAttribute("userId"));
		}else {
			list = messageService.trashRecieveMessage((String)session.getAttribute("userId"));
		}
		model.addAttribute("list", list);
		return "main/mypage/message";
	}
	
	@RequestMapping("/main/mypage/messageup1.do")
	public String messageUpdate1(@RequestParam int messageNo) {
		logger.info("수신자 휴지통 업데이트 파라미터 messageNo={}",messageNo);
		int cnt = 0;
		cnt = messageService.updateRecieveMessage(messageNo);

		logger.info("수신자 휴지통 업데이트 cnt = {}",cnt);
		return "main/mypage/message";
	}
	
	@RequestMapping("/main/mypage/messageup2.do")
	public String messageUpdate2(@RequestParam int messageNo) {
		logger.info("발신자 휴지통 업데이트 파라미터 messageNo={}",messageNo);
		int cnt = 0;
		cnt = messageService.updateSendMessage(messageNo);
		logger.info("발신자 휴지통 업데이트 cnt = {}",cnt);
		return "main/mypage/message";
	}
	
	@RequestMapping("/main/mypage/messageup3.do")
	public String messageUpdate3(@RequestParam int messageNo) {
		logger.info("수신자 휴지통 삭제 파라미터 messageNo={}",messageNo);
		int cnt = 0;
		cnt = messageService.deleteRecieveMessage(messageNo);

		logger.info("수신자 휴지통에서 삭제 cnt = {}",cnt);
		return "main/mypage/message";
	}
	
	@RequestMapping("/main/mypage/messageup4.do")
	public String messageUpdate4(@RequestParam int messageNo) {
		logger.info("수신자 휴지통 복구 파라미터 messageNo={}",messageNo);
		int cnt = 0;
		cnt = messageService.recoveryRecieveMessage(messageNo);

		logger.info("수신자 휴지통에서 복구 cnt = {}",cnt);
		return "main/mypage/message";
	}
	
	@RequestMapping(value="/main/mypage/message_send.do",method=RequestMethod.POST)
	public String message_send(@ModelAttribute MessageVO vo,
			HttpServletRequest request, Model model) {
		logger.info("메시지 전송 파라미터 vo={}",vo);
		
		//2.파일업로드
		String fileName="", originalFileName="";
		long fileSize=0;
		List<Map<String, Object>> fileList=fileUtility.fileUpload(request,FileUploadUtility.MESSAGEFILE_UPLOAD);		
		for(int i=0;i<fileList.size();i++) {
			Map<String, Object> map=fileList.get(i);
			
			fileName=(String) map.get("fileName");
			originalFileName=(String) map.get("originalFileName");
			fileSize=(Long) map.get("fileSize");
		}
		vo.setFileName(fileName);
		vo.setFileSize(fileSize);
		vo.setOriginalFileName(originalFileName);
		
		//db작업
		int cnt=messageService.insertMessage(vo);
		logger.info("메시지 전송 결과, cnt={}", cnt);
		
		//3. model에 결과 저장
		String msg="", url="";
		if(cnt>0) {
			msg="메시지 전송 처리되었습니다.";
			url="/main/mypage/message.do";
		}else {
			msg="메시지 전송 실패";
			url="/main/mypage/message.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4. 뷰페이지 리턴
		return "common/message";
	}
	
	
}
