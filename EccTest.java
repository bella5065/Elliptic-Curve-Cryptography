import java.util.Scanner;
public class EccTest {

	public static void main(String[] args) {
		Ecc test = new Ecc();
		Scanner input = new Scanner(System.in);
		
		int a,b,Gx,Gy,n,Mx,My;
		System.out.println("Ÿ��� y^2 = x^3 + x + 6");
		System.out.print("G���� x��ǥ�� y��ǥ�� �Է��Ͻÿ� :");
		Gx = input.nextInt();
		Gy = input.nextInt();
		test.setG(Gx, Gy);
		
		System.out.print("��� ���꿡 �ʿ��� �Ҽ��� �Է��Ͻÿ� :");
		n = input.nextInt();
		test.setModuloNumber(n);
		
		System.out.print("A�� ����Ű a�� �Է��Ͻÿ�(1<a<n-1) :");
		a = input.nextInt();
		test.setPrivateKey_A(a);
        
		System.out.print("B�� ����Ű b�� �Է��Ͻÿ�(1<b<n-1) :");
		b = input.nextInt();
		test.setPrivateKey_B(b);
		
		System.out.print("��ȣȭ�� ���� �Է��Ͻÿ� :");
		Mx = input.nextInt();
		My = input.nextInt();
		test.setM(Mx, My);
		
		System.out.println("-----------------------------");

		test.setPublicKey_P();
		test.setPublicKey_Q();
		System.out.println("A�� ����Ű P ("+test.getPublicKey_Px()+","+test.getPublicKey_Py()+")");
		System.out.println("B�� ����Ű Q ("+test.getPublicKey_Qx()+","+test.getPublicKey_Qy()+")");
		System.out.println("-----------------------------");
		test.MakeC1();
		System.out.println("��ȣ�� C1 ("+test.getC1x()+","+test.getC1y()+")");
		System.out.println("-----------------------------");
		test.MakeC2();
		System.out.println("�ʿ��� �� C2 ("+test.getC2x()+","+test.getC2y()+")");
		System.out.println("-----------------------------");
		test.Decode();
		System.out.println("C1-C2�� ���� ���� �� M ("+test.getMx()+","+test.getMy()+")");
		
	}

}
