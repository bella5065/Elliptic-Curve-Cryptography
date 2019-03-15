import java.util.Scanner;
public class EccTest {

	public static void main(String[] args) {
		Ecc test = new Ecc();
		Scanner input = new Scanner(System.in);
		
		int a,b,Gx,Gy,n,Mx,My;
		System.out.println("타원곡선 y^2 = x^3 + x + 6");
		System.out.print("G점의 x좌표와 y좌표를 입력하시오 :");
		Gx = input.nextInt();
		Gy = input.nextInt();
		test.setG(Gx, Gy);
		
		System.out.print("모드 연산에 필요한 소수를 입력하시오 :");
		n = input.nextInt();
		test.setModuloNumber(n);
		
		System.out.print("A의 개인키 a를 입력하시오(1<a<n-1) :");
		a = input.nextInt();
		test.setPrivateKey_A(a);
        
		System.out.print("B의 개인키 b를 입력하시오(1<b<n-1) :");
		b = input.nextInt();
		test.setPrivateKey_B(b);
		
		System.out.print("암호화할 평문을 입력하시오 :");
		Mx = input.nextInt();
		My = input.nextInt();
		test.setM(Mx, My);
		
		System.out.println("-----------------------------");

		test.setPublicKey_P();
		test.setPublicKey_Q();
		System.out.println("A의 공개키 P ("+test.getPublicKey_Px()+","+test.getPublicKey_Py()+")");
		System.out.println("B의 공개키 Q ("+test.getPublicKey_Qx()+","+test.getPublicKey_Qy()+")");
		System.out.println("-----------------------------");
		test.MakeC1();
		System.out.println("암호문 C1 ("+test.getC1x()+","+test.getC1y()+")");
		System.out.println("-----------------------------");
		test.MakeC2();
		System.out.println("필요한 식 C2 ("+test.getC2x()+","+test.getC2y()+")");
		System.out.println("-----------------------------");
		test.Decode();
		System.out.println("C1-C2를 통해 구한 평문 M ("+test.getMx()+","+test.getMy()+")");
		
	}

}
