package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BuyMM;
import service.ProductMM;


@WebServlet({ "/ajaxDetail" ,"/qtyUp","/buydetail"})
public class RestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd=request.getServletPath();
		ProductMM pm= new ProductMM(request, response);
		BuyMM bm= new BuyMM(request, response);
		String json = null;
		switch(cmd) {
		case "/buydetail":
			json=bm.buyDetail();
			break;
		case "/ajaxDetail":
			json=pm.getAjaxDetail();
			break;
		}
		if(json!=null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out=response.getWriter();
			out.write(json);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
