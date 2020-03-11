package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import service.MemberMM;

public class Move {
	HttpServletRequest request;
	HttpServletResponse response;

	public Move(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	
	public Forward menu() {
		Forward fw= new Forward();
		fw.setPath("menu.jsp");
		fw.setReDirect(false);
		return fw;
	}

	public Forward newItem() {
		Forward fw=new Forward();
		fw.setPath("newitem.jsp");
		fw.setReDirect(false);
		return fw;
	}

	public Forward bestItem() {
		Forward fw=new Forward();
		fw.setPath("bestitem.jsp");
		fw.setReDirect(false);		
		return fw;
	}

	public Forward access() {
		MemberMM mm=new MemberMM();
		Forward fw=new Forward();
		fw=mm.access();
		return fw;
	}

	public Forward logOut() {
		Forward fw=new Forward();
		HttpSession session=request.getSession();
		session.invalidate(); //세션 무효화 꼭!!!
		fw.setPath("index.jsp"); //index.jsp
		fw.setReDirect(true);
		return fw;
	}

	public Forward proupfrm() {
		Forward fw=new Forward();
		fw.setPath("product/proUpFrm.jsp");
		fw.setReDirect(false);
		return fw;
	}

}
