package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import service.Move;
import service.ProductMM;
import service.BuyMM;
import service.MemberMM;
//서블릿 페이지
@WebServlet({ "/menu", "/newitem", "/bestitem", "/access", "/joinfrm", "/logout", "/proupfrm", "/insertproduct","/prodetail","/cart","/showcart","/buy","/buylist"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //인코딩을 다국어로 함
		String cmd = request.getServletPath(); //url를 받아옴
		Forward fw = null; //쓰기 귀찮아서 위에다 맴버 변수처럼 씀
		MemberMM mm = new MemberMM(request, response); //매니지먼트를 생성하기 전에 리퀘스트랑
		//response 를 꼽아놓음 생성자
		ProductMM pm = new ProductMM(request, response);
		BuyMM bm= new BuyMM(request,response);
		Move move = new Move(request, response);
		System.out.println(cmd);
		switch (cmd) {
		case "/buylist":
			fw=bm.buyList();
			break;
		case "/showcart":
			fw=bm.showcart();
			break;
		case "/cart":
			fw=bm.cart();
			break;
		case "/prodetail":
			fw=pm.prodetail();
			break;
		case "/proupfrm":
			fw = move.proupfrm();
			break;
		case "/logout":
			fw = move.logOut();
			break;
		case "/access":
			fw = mm.access();
			break;
		case "/menu":
			fw = move.menu();
			break;
		case "/newitem":
			fw = pm.getItemList("new");
			break;
		case "/bestitem":
			fw = pm.getItemList("best");
			break;
		case "/insertproduct":
			fw = pm.insertProduct();
			break;
		case "/buy":
			fw=bm.buy();
			break;
		}
		if (fw != null) {
			if (fw.isReDirect()) {
				response.sendRedirect(fw.getPath());
			} else {
				request.getRequestDispatcher(fw.getPath()).forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
		//doget과 dopost 방식을 받아올 경우 doProcess로 묶어서 작업함
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
