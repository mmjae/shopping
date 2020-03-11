package bean;

public class Forward { 
	//자바빈 일회용 Forward false일경우 disPacher.Forward 실행
	//true면 sendRedirect 포워딩 2개의 값을 담아서 리턴 하기 위하여 생성
	private boolean reDirect;
	private String path;
	
	public boolean isReDirect() {
		return reDirect;
	}
	public void setReDirect(boolean reDirect) {
		this.reDirect = reDirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
