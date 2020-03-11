package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import dao.MemberDao;

public class MemberMM {
	HttpServletRequest request;
	HttpServletResponse response;

	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public MemberMM() {
	}

	public Forward access() {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		MemberDao mDao=new MemberDao();
		int result=mDao.access(id,pw); 
		mDao.close();//1=성공 , -1=id 부재 , 0=pw 부재
		if(result==-1) {
			request.setAttribute("msgAccess", "ID가 존재하지 않습니다.");
		}else if(result==0) {
			request.setAttribute("msgAccess", "PW가 존재하지 않습니다.");
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			request.setAttribute("msgAccess", "로그인.");
		}
		Forward fw=new Forward();
		fw.setPath("./");
		fw.setReDirect(false);
		return fw;
	}

}
