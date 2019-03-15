
public class Ecc extends EccSetup {
	private int Px, Py, Qx, Qy;             //����Ű P�� Q�� ��ǥ
	private int C1x, C1y, C2x, C2y;         //C1�� C2�� ��ǥ
	private int x1=0, x2=0, x3, y1=0, y2=0, y3, k, z=0;  //���� ���꿡 �ʿ��� ������


	//A�� ����Ű P�� �����ϴ� �޼ҵ�
	public void setPublicKey_P() {
		x1 = getGx();     //���������� �����ϱ� ���� �ʿ��� (x1,y1)��(x2,y2)�� G��ǥ���� ����
		y1 = getGy(); 
		x2 = getGx(); 
		y2 = getGy();
		for(int i=1; i<getA(); i++) {
			Addition(1,11);
		}
		this.Px = x3;
		this.Py = y3;
		
	}
		
	//B�� ����Ű Q�� �����ϴ� �޼ҵ�
	public void setPublicKey_Q() {
		x1 = getGx();     //���������� �����ϱ� ���� �ʿ��� (x1,y1)��(x2,y2)�� G��ǥ���� ����
		y1 = getGy(); 
		x2 = getGx(); 
		y2 = getGy();
		for(int i=1; i<getB(); i++) {
			Addition(1,11);
		}
		this.Qx = x3;
		this.Qy = y3;
	}
	
	//���� ��ȣȭ�� C1�� �����ϴ� �޼ҵ�
	public void MakeC1() {
		x1 = Px;   y1 = Py; 
		x2 = Px;   y2 = Py;
		for(int i=1; i<getB(); i++) {
			Addition(1,11);
		}
		this.C1x = x3;
		this.C1y = y3;
		
		//���� ����
		x1 = C1x; y1 = C1y; 
		x2 = getMx();  y2 = getMy();
		Addition(1,11);
		this.C1x = x3;
		this.C1y = y3;
	}
	
	public void MakeC2() {
		x1 = Qx;   y1 = Qy; 
		x2 = Qx;   y2 = Qy;
		for(int i=1; i<getA(); i++) {
		Addition(1,11);
		}
		this.C2x = x3;
		this.C2y = y3;
	}
	
	public void Decode() {
		x1 = C1x;  y1 = C1y; 
		x2 = C2x;  y2 = -C2y;
		Addition(1,11);
		setM(x3, y3); 	
	}
	
	//���� ���� �޼ҵ� 
	private void Addition(int coef, int cons) {
	    //������ �ϴ� �� ��ǥ�� ���� ���
		if(x1==x2 && y1==y2) { 
	          
			// x^(-1) % n �� ����ϱ� ���� for��
	          for(int r=1; r<10; r++) {
	                if(((2*y1*r)-1) %11== 0) {
	                   z=r;
	            
	             }
	          }
	          
	          //�������꿡 �ʿ��� k���� ���ϴ� ��
	          k = (3*x1*x1+ coef )*z;
	          if(k<0) {
	        	  k = -k %getN();    // ������ ��� �����Ҷ� �ʿ��� ��
	        	  k =  getN() - k;
	          }
	         
	          //x3��ǥ�� ���ϴ� ��
	          x3 = (k*k - x1 - x2);
	          if(x3<0) {
	        	  x3 = -x3%getN();
	        	  x3 = getN() - x3;
	          }
	          else {
	        	  x3 = x3 % getN();
	          }
	          
	          //y3��ǥ�� ���ϴ� ��
	          y3 = k*(x1-x3) - y1;
	          if(y3<0) {
	        	  y3 =-y3 % getN();
	        	  y3 = getN() - y3;
	          }
	          else {
	        	  y3= y3 %getN();
	          }
	          
	       }
		
		//������ �ϴ� �� ��ǥ�� �ٸ� ���
	       else {
	    	   
	    	  // x^(-1) % n �� ����ϱ� ���� for��
	          int sum=0;
	    	   for(int r=1; r<20; r++) {
	    		   if(x2-x1 <0) {
	    			   sum = -(x2 - x1) % getN();
	    			   sum = getN() - sum;
	    			   if(((sum*r)-1) %11== 0) {
	                   z=r;
	    			   }
	            
	               }
	    		   else {
	    			   if((((x2-x1)*r)-1) %11== 0) {
		                   z=r;
	    		       }
	               }
	    	   }
	    	  
	    	  //���� ���꿡 �ʿ��� k���� ���ϴ� �� 
	          k = (y2-y1)*z;
	          if(k<0) {               //������ ��忬���Ҷ� �ʿ��� ��
	        	  k = -k %getN();
	        	  k =  getN() - k;
	          }
	          
	          //x3��ǥ�� ���ϴ� ��
	          x3 = (k*k - x1 - x2);
	          if(x3<0) {
	        	  x3 = -x3%getN();
	        	  x3 = getN() - x3;
	          }
	          else {
	        	  x3 = x3 % getN();
	          }	 
	          
	          //y3��ǥ�� ���ϴ� ��
	          y3 = (k*(x1-x3) - y1);
	          if(y3<0) {
	        	  y3 =-y3 % getN();
	        	  y3 =getN() - y3;
	          }
	          else {
	        	  y3= y3 %getN();
	          }
	       }
	      
		// ���ӵǴ� ���� ������ ��츦 ���� ���� ������ ���� ���� ��ǥ�� x1�� x2�� �� �Է�  
		x1 = x3;
		y1 = y3;
	
		
	}
	
	
	//������ ���� �ҷ����� �޼ҵ�
	public int getPublicKey_Px() {return Px;}
	public int getPublicKey_Py() {return Py;}
	public int getPublicKey_Qx() {return Qx;}
	public int getPublicKey_Qy() {return Qy;}
	public int getC1x() {return C1x;}
	public int getC1y() {return C1y;}
	public int getC2x() {return C2x;}
	public int getC2y() {return C2y;}

}
