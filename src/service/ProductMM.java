package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Forward;
import bean.Product;
import dao.ProductDao;

public class ProductMM {
	HttpServletRequest request;
	HttpServletResponse response;
	public ProductMM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	public Forward insertProduct() {
		Forward fw=null;
			fw= new Forward();
		if(request.getSession().getAttribute("id")==null) {
			fw.setPath("./"); //index.jsp
			fw.setReDirect(true);
			return fw;
		}
		String uploadPath=request.getSession().getServletContext().getRealPath("upload");
		System.out.println(uploadPath); //경로 찾아 보고싶을때
		File dir=new File(uploadPath);
		if(!dir.exists()) { //업로드 폴더가 서버 clean시 삭제될경우
			dir.mkdir(); //최하위 폴더 하나만 s를 붙이면 상위 폴더까지 다만듬
		}
		int size=10*1024*1024;
		try {//리퀘스트 담고 물리적 경로 담고 사이즈 담고 인코딩 담고 숫자 자동증가 담고
			MultipartRequest multi=new MultipartRequest(request, uploadPath,size,"UTF-8",
					new DefaultFileRenamePolicy());
			//리퀘스트상위 버전
			String kind=multi.getParameter("p_kind");
			String name=multi.getParameter("p_name");
			int price=Integer.parseInt(multi.getParameter("p_price"));
			int qty=Integer.parseInt(multi.getParameter("p_qty"));
			String contents=multi.getParameter("p_contents");
			String oriFileName=multi.getOriginalFileName("p_file");//디비 까봐서 오리 파일 확인
			String sysFileName=multi.getFilesystemName("p_file");
			HttpSession session=request.getSession();
			Product product=new Product();
			product.setP_id(session.getAttribute("id").toString());
			product.setP_kind(kind);
			product.setP_name(name);
			product.setP_price(price);
			product.setP_qty(qty);
			product.setP_contents(contents);
			product.setP_oriFileName(oriFileName);
			product.setP_sysFileName(sysFileName);
			ProductDao pDao=new ProductDao();//커넥션
			if(pDao.insertProduct(product)) {
				System.out.println("상품등록 성공");
			}
			else {
				System.out.println("상품등록 실패");
			}
			//등록후에 신상품/인기상품 으로 갈지 정해야함
			if(product.getP_kind().equals("new")) {
				session.setAttribute("page", "newitem");
			}else {
				session.setAttribute("page", "bestitem");
			}
			fw= new Forward();
			fw.setPath("./");
			fw.setReDirect(true);
			//새로고침(f5)시 파일 재업로드를 방지
			//page속성을 session영역에 저장하고 true로 설정
			pDao.close();
		} catch (IOException e) {
			System.out.println("상품 등록 예외");
			e.printStackTrace();
		}
		
		return fw;
	}
	public Forward getItemList(String kind) {
		//로그인 한경우만 리스트를 볼수 있다면
		Forward fw= new Forward();
		//index.jsp에서 로그인 검사를 같이 제거하거나 같이 추가 해야함 리스트는 보여주기 위해서 아이디 검사안함
//		if(request.getSession().getAttribute("id")==null) {
//			fw.setPath("./"); //index.jsp
//			fw.setReDirect(true);
//			return fw;
//		}
		ProductDao pDao=new ProductDao();//커넥션
		List<Product> pList=pDao.getItemList(kind);
		pDao.close();
		if(pList!=null&&pList.size()!=0) {
			String pListHtml=makeHtml_PList(pList);
			request.setAttribute("pListHtml", pListHtml);
		}
		fw.setPath("main.jsp");
		fw.setReDirect(false);
		return fw;
	}
	private String makeHtml_PList(List<Product> pList) {
		StringBuilder sb= new StringBuilder();
		
		for(int i=0;i<pList.size();i++) {
			Product p=pList.get(i);
			sb.append("<div id='list'>"); //onclick=\"detail('"+p.getP_code()+"')\"
			sb.append("<a href=prodetail?code="+p.getP_code()+"><img src='upload/"+p.getP_sysFileName()+"'width='100'></a>");
			sb.append("</div>");
		}
		//"<a href=productinfo?code="+p.getP_code()+"></a>
		return sb.toString();
	}
	//만들때 생성자로 불러온 request랑 response 있음
	public String getAjaxDetail() {
		String code=request.getParameter("pCode");
		Gson gson=new Gson();
		ProductDao pDao=new ProductDao();//커넥션 받음
		Product p=new Product();
		p=pDao.getAjaxDetail(code);
		pDao.close();
		String jsonStr=gson.toJson(p);
		return jsonStr;
	}
	public Forward prodetail() {
		Forward fw= new Forward();
		if(request.getSession().getAttribute("id")==null) {
			fw.setPath("./"); //index.jsp
			fw.setReDirect(true);
			return fw;
		}
		String code=request.getParameter("code");
		System.out.println("코드는"+code);
		ProductDao pDao=new ProductDao();//커넥션 받아서
		Product p= new Product();
		p=pDao.prodetail(code);
		pDao.close();
		Gson gson=new Gson();
		String jsonStr=gson.toJson(p);
		request.setAttribute("detail",jsonStr);
		System.out.println(p);
		fw.setPath("proDetail.jsp");
		fw.setReDirect(false);
		return fw;
	}
	

}
