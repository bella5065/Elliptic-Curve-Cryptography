
public class EccSetup {
	private int Gx, Gy;                     //Ÿ�� � ���� ������ ��
	private int n;                          //��� ���꿡 �ʿ��� �Ҽ�
	private int a, b;                       // ����Ű  
	private int Mx, My;                     // �� M�� ��ǥ

	
	
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
	
	//G��ǥ�� ����Ű a,b �׸��� ��� ���꿡 ���� n���� �ҷ����� �޼ҵ�
	public int getGx() {return Gx;}
	public int getGy() {return Gy;}
	public int getN() {return n;}
	public int getA() {return a;}
	public int getB() {return b;}
	public int getMx()  {return Mx;}
	public int getMy()  {return My;}
	
}
