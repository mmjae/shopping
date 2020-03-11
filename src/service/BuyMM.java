package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.BuyDetail;
import bean.BuyList;
import bean.Cart;
import bean.Forward;
import bean.showCart;
import dao.OrderDao;

public class BuyMM {
	HttpServletRequest request;
	HttpServletResponse response;

	public BuyMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward cart() {
		Forward fw = new Forward();
		HttpSession session = request.getSession();
		String c_id = session.getAttribute("id").toString();
		System.out.println(c_id);
		String c_code = request.getParameter("p_code");
		System.out.println(c_code);
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		System.out.println(cnt);
		OrderDao oDao = new OrderDao();// 커넥션
		Cart c = new Cart();
		c.setC_id(c_id);
		c.setC_code(c_code);
		c.setC_qty(cnt);
		int result=oDao.check(c_code,c_id);
		if(result==0) {
		if (oDao.cart(c)) {// 작업
			System.out.println("장바구니 담기 성공");
		} else {
			System.out.println("장바구니 담기 실패");
		}
		}else if(result==1) {
			if(oDao.cartUpdate(c)) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("장바구니 업데이트 실패");
			}
		}
		oDao.close();// 반납
		// 디비에 한번 가서 장바구니에 담음 있으면 업데이트 없으면 인서트
		// 수량은 네임으로 해결
		// 최대 수량 보다 더 클경우 잘못된 수량을 입력했다고 스크립트에 써야함
		// a 태그로 잔머리 ㄴㄴ (해결)
		fw.setPath("cartMove.jsp");
		fw.setReDirect(false);
		return fw;
	}

	public Forward showcart() {
		Forward fw = new Forward();
		OrderDao oDao = new OrderDao();// 커넥션
		HttpSession session = request.getSession();
		String c_id = session.getAttribute("id").toString();
		List<showCart> showCartList= oDao.showcart(c_id);
		oDao.close();
		String json=new Gson().toJson(showCartList);
		request.setAttribute("showcart", json);
		fw.setPath("cart.jsp");
		fw.setReDirect(false);
		return fw;
	}

	public Forward buy() {
		OrderDao oDao=null;
		Forward fw= new Forward();
		String sum=request.getParameter("sum");
		HttpSession session = request.getSession();
		String b_id = session.getAttribute("id").toString();
		System.out.println("총합"+sum);
		System.out.println(b_id);
		oDao = new OrderDao();
		boolean result=oDao.move();
		if(result) {
			System.out.println("옮기기 성공");
			boolean result2=oDao.buy(sum,b_id);
			if(result2) {
				System.out.println("새로운 인설트 성공");
				boolean result3=oDao.cartDel(b_id);
				oDao.close();
				if(result3) {
					System.out.println("카드 삭제 성공");
				}else {
					System.out.println("카드 삭제 실패");
				}
				System.out.println("옮기기 최종 성공");
			}else {
				System.out.println("옮기기 최종적으로 실패");
				fw.setPath("./");
				fw.setReDirect(false);
			}
			fw.setPath("buyListMove.jsp");
			fw.setReDirect(false);
		}else {
			System.out.println("구매테이블 인서트 실패");
			fw.setPath("./");
			fw.setReDirect(false);
		}
		oDao.close();
		return fw;
	}

	public Forward buyList() {
		Forward fw= new Forward();
		HttpSession session = request.getSession();
		String b_id = session.getAttribute("id").toString();
		OrderDao oDao = new OrderDao();
		List<BuyList> bList=oDao.buyList(b_id);
		if(bList!=null&&bList.size()!=0) {
			String jsonStr=new Gson().toJson(bList);
			request.setAttribute("buyList", jsonStr);
			System.out.println("구매테이블 불러오기 마지막 성공"); 
			fw.setPath("buyList.jsp");
			fw.setReDirect(false);
		}else {
			fw.setPath("./");
			fw.setReDirect(false);
			System.out.println("구매테이블 불러오기 마지막 실패");
		}
		return fw;
	}

	public String buyDetail() {
		String orderNum=request.getParameter("data");
		System.out.println(orderNum);
		OrderDao oDao = new OrderDao();//커넥션
		List<BuyDetail> bdList= oDao.buyDetail(orderNum);
		String jsonstr=new Gson().toJson(bdList);
		return jsonstr;
	}

}
