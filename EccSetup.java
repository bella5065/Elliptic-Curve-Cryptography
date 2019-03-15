
public class EccSetup {
	private int Gx, Gy;                     //타원 곡선 위의 임의의 점
	private int n;                          //모드 연산에 필요한 소수
	private int a, b;                       // 개인키  
	private int Mx, My;                     // 평문 M의 좌표

	
	
	public void setG(int Gx, int Gy) {
		this.Gx = Gx;
		this.Gy = Gy;
	}
	
	public void setPrivateKey_A(int a) {
		this.a =a;
	}
	
	public void setPrivateKey_B(int b) {
		this.b=b;
	}
	
	public void setModuloNumber(int n) {
		this.n= n;
	}
	
	public void setM(int Mx,int My) {
		this.Mx=Mx;
		this.My=My;
	}
	
	//G좌표와 개인키 a,b 그리고 모드 연산에 사용될 n값을 불러오는 메소드
	public int getGx() {return Gx;}
	public int getGy() {return Gy;}
	public int getN() {return n;}
	public int getA() {return a;}
	public int getB() {return b;}
	public int getMx()  {return Mx;}
	public int getMy()  {return My;}
	
}
