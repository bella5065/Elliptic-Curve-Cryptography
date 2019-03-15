
public class Ecc extends EccSetup {
	private int Px, Py, Qx, Qy;             //공개키 P와 Q의 좌표
	private int C1x, C1y, C2x, C2y;         //C1와 C2의 좌표
	private int x1=0, x2=0, x3, y1=0, y2=0, y3, k, z=0;  //덧셈 연산에 필요한 변수들


	//A의 공개키 P를 생성하는 메소드
	public void setPublicKey_P() {
		x1 = getGx();     //덧셈연산을 진행하기 위해 필요한 (x1,y1)과(x2,y2)에 G좌표값을 넣음
		y1 = getGy(); 
		x2 = getGx(); 
		y2 = getGy();
		for(int i=1; i<getA(); i++) {
			Addition(1,11);
		}
		this.Px = x3;
		this.Py = y3;
		
	}
		
	//B의 공개키 Q를 생성하는 메소드
	public void setPublicKey_Q() {
		x1 = getGx();     //덧셈연산을 진행하기 위해 필요한 (x1,y1)과(x2,y2)에 G좌표값을 넣음
		y1 = getGy(); 
		x2 = getGx(); 
		y2 = getGy();
		for(int i=1; i<getB(); i++) {
			Addition(1,11);
		}
		this.Qx = x3;
		this.Qy = y3;
	}
	
	//평문을 암호화한 C1을 생성하는 메소드
	public void MakeC1() {
		x1 = Px;   y1 = Py; 
		x2 = Px;   y2 = Py;
		for(int i=1; i<getB(); i++) {
			Addition(1,11);
		}
		this.C1x = x3;
		this.C1y = y3;
		
		//평문을 더함
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
	
	//덧셈 연산 메소드 
	private void Addition(int coef, int cons) {
	    //덧셈을 하는 두 좌표가 같을 경우
		if(x1==x2 && y1==y2) { 
	          
			// x^(-1) % n 을 계산하기 위한 for문
	          for(int r=1; r<10; r++) {
	                if(((2*y1*r)-1) %11== 0) {
	                   z=r;
	            
	             }
	          }
	          
	          //덧셈연산에 필요한 k값을 구하는 식
	          k = (3*x1*x1+ coef )*z;
	          if(k<0) {
	        	  k = -k %getN();    // 음수를 모드 연산할때 필요한 식
	        	  k =  getN() - k;
	          }
	         
	          //x3좌표를 구하는 식
	          x3 = (k*k - x1 - x2);
	          if(x3<0) {
	        	  x3 = -x3%getN();
	        	  x3 = getN() - x3;
	          }
	          else {
	        	  x3 = x3 % getN();
	          }
	          
	          //y3좌표를 구하는 식
	          y3 = k*(x1-x3) - y1;
	          if(y3<0) {
	        	  y3 =-y3 % getN();
	        	  y3 = getN() - y3;
	          }
	          else {
	        	  y3= y3 %getN();
	          }
	          
	       }
		
		//덧셈을 하는 두 좌표가 다를 경우
	       else {
	    	   
	    	  // x^(-1) % n 을 계산하기 위한 for문
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
	    	  
	    	  //덧셈 연산에 필요한 k값을 구하는 식 
	          k = (y2-y1)*z;
	          if(k<0) {               //음수를 모드연산할때 필요한 식
	        	  k = -k %getN();
	        	  k =  getN() - k;
	          }
	          
	          //x3좌표를 구하는 식
	          x3 = (k*k - x1 - x2);
	          if(x3<0) {
	        	  x3 = -x3%getN();
	        	  x3 = getN() - x3;
	          }
	          else {
	        	  x3 = x3 % getN();
	          }	 
	          
	          //y3좌표를 구하는 식
	          y3 = (k*(x1-x3) - y1);
	          if(y3<0) {
	        	  y3 =-y3 % getN();
	        	  y3 =getN() - y3;
	          }
	          else {
	        	  y3= y3 %getN();
	          }
	       }
	      
		// 연속되는 덧셈 연산의 경우를 위해 덧셈 연산을 통해 구한 좌표를 x1과 x2에 값 입력  
		x1 = x3;
		y1 = y3;
	
		
	}
	
	
	//설정한 값을 불러오는 메소드
	public int getPublicKey_Px() {return Px;}
	public int getPublicKey_Py() {return Py;}
	public int getPublicKey_Qx() {return Qx;}
	public int getPublicKey_Qy() {return Qy;}
	public int getC1x() {return C1x;}
	public int getC1y() {return C1y;}
	public int getC2x() {return C2x;}
	public int getC2y() {return C2y;}

}
