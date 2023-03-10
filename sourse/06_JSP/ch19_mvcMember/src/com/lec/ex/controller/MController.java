package com.lec.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.ex.service.MAllViewService;
import com.lec.ex.service.MJoinService;
import com.lec.ex.service.MLoginService;
import com.lec.ex.service.MLogoutService;
import com.lec.ex.service.MMoidfyService;
import com.lec.ex.service.MWithdrawalService;
import com.lec.ex.service.Service;

@WebServlet("*.do")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri 		= request.getRequestURI();
		String conPath  = request.getContextPath();
		String command  = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) { // 메인
			viewPage = "member/main.jsp";
		}else if(command.equals("/loginView.do")) {	//로그인 페이지
			viewPage = "member/login.jsp";
		}else if(command.equals("/login.do")) {	// 로그인 처리
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/joinView.do")) {  // 회원가입 페이지
			viewPage = "member/join.jsp";
		}else if(command.equals("/join.do")) { // 회원가입 처리
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(command.equals("/logout.do")) { // 로그아웃
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/modifyView.do")) { // 정보수정 페이지
//			service = new MGetMemberService();	// 
//			service.execute(request, response);
			viewPage = "member/modify.jsp";
		}else if(command.equals("/modify.do")) {  // 정보수정
			service = new MMoidfyService();
			service.execute(request, response);
			viewPage = "member/main.jsp";
		}else if(command.equals("/allView.do")) {  // 회원 목록 출력(paging)
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}else if(command.equals("/withdrawal.do")) {  // 회원 탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "member/main.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
