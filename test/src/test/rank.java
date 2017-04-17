package test;

public class rank {
	
	Card cc;	//	카드 클래스 가져오려고 
	String card, shape;	//	카드 받아온거  ||   모양 확인할것
	int number;	//	카드 숫자 확인하려고
	boolean [] flush = new boolean[1];	//	먼저 플러쉬인지 아닌지 확인하려고 
	int [] value = new int[7];	//	들어온 카드를 숫자만 넣어 확인하려는놈
	String [] SDHC = new String[7];	//	shape 로 들어온 카드의 모양을 배열 행태로 집어넣음 -->> 비교해주려고
	int temp = 0;	//	value 로 들어온 값들을 작은 순서부터 정렬해주기 위해서 필요한것 
	int cnt = 0;	//	flush 가 true, false 인지 확인할때 사용 
	String suit = "";	//	SDHC 을 하나씩 가져와서 비교할때 사용할놈
	
	int [] type = new int[1];	//	카드의 타입이 무엇인지 알아서 int 형으로 비교해줄놈
	int [] comp = new int[1];	//	카트 패의 최고 높은 숫자를 집어넣을때 
	
	
	
	
	
	
	public void initValue(Card cc) {
		
		this.cc = cc;
		System.out.println("처음 들어온 value");
		for (int i = 0; i < cc.card().size(); i++) {
			
			card = ""+ cc.card().get(i);
			shape = card.substring(0, 1);
			number = Integer.parseInt(card.substring(1));
			
//			System.out.println("card : " +card);
//			System.out.println("shape : " +shape);
//			System.out.println("number : " + number);

			value[i] = number;
			SDHC[i] = shape;
			
//			System.out.println("벨류 는 숫자    : "+ value[i]);
//			System.out.println("모양 은 문자열 : " + SDHC[i]);
			System.out.print(value[i] +"\t");
		}
			
		
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < cc.card().size(); j++) {
				if(SDHC[i].equals(SDHC[j])) {
					cnt++;
					if(cnt==5)  {
						flush[0] = true;
						break;
					}	//cnt ==5 끝
				}	//	SDHC 비교 끝
			}	//	j for 문 끝
			System.out.println();
			System.out.println("몇개나 같어? : "+cnt);
		}	//	i for 문 끝
		
		
		
		
		System.out.println(flush[0]);
		
		
/////	패 숫자 작은순으로 정렬 다시 해주기 	
		for (int j = 0; j < 6; j++) {
			for (int k = 1; j + k < 7; k++) {

				if (value[j] > value[j + k]) {
					temp = value[j];
					value[j] = value[j + k];
					value[j + k] = temp;
					
					
				}	// if 문 끝
			}	// K for 문 끝
		}	// j for 문 끝
		System.out.println("순서 정렬해준 value");
		for (int i = 0; i < cc.card().size(); i++) {
		
		System.out.print(value[i] +"\t");
		}
		
		//System.out.println(value[0]);

	}
	

	
	
///	-------------------------------------------------------------------------	
	public void type() {
		int count0, count1, con0, compare_num = 0;
		int [] diff=new int[4];
		
	}
	
			
			
			
			
		
		
		

		
		
	
	
	public static void main(String[] args) {
		
		Card cc = new Card();
		rank rr = new rank();
		rr.initValue(cc);
	}

}
