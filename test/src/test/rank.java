package test;

public class rank {
	
	Card cc;	//	ī�� Ŭ���� ���������� 
	String card, shape;	//	ī�� �޾ƿ°�  ||   ��� Ȯ���Ұ�
	int number;	//	ī�� ���� Ȯ���Ϸ���
	boolean [] flush = new boolean[1];	//	���� �÷������� �ƴ��� Ȯ���Ϸ��� 
	int [] value = new int[7];	//	���� ī�带 ���ڸ� �־� Ȯ���Ϸ��³�
	String [] SDHC = new String[7];	//	shape �� ���� ī���� ����� �迭 ���·� ������� -->> �����ַ���
	int temp = 0;	//	value �� ���� ������ ���� �������� �������ֱ� ���ؼ� �ʿ��Ѱ� 
	int cnt = 0;	//	flush �� true, false ���� Ȯ���Ҷ� ��� 
	String suit = "";	//	SDHC �� �ϳ��� �����ͼ� ���Ҷ� ����ҳ�
	
	int [] type = new int[1];	//	ī���� Ÿ���� �������� �˾Ƽ� int ������ �����ٳ�
	int [] comp = new int[1];	//	īƮ ���� �ְ� ���� ���ڸ� ��������� 
	
	
	
	
	
	
	public void initValue(Card cc) {
		
		this.cc = cc;
		System.out.println("ó�� ���� value");
		for (int i = 0; i < cc.card().size(); i++) {
			
			card = ""+ cc.card().get(i);
			shape = card.substring(0, 1);
			number = Integer.parseInt(card.substring(1));
			
//			System.out.println("card : " +card);
//			System.out.println("shape : " +shape);
//			System.out.println("number : " + number);

			value[i] = number;
			SDHC[i] = shape;
			
//			System.out.println("���� �� ����    : "+ value[i]);
//			System.out.println("��� �� ���ڿ� : " + SDHC[i]);
			System.out.print(value[i] +"\t");
		}
			
		
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < cc.card().size(); j++) {
				if(SDHC[i].equals(SDHC[j])) {
					cnt++;
					if(cnt==5)  {
						flush[0] = true;
						break;
					}	//cnt ==5 ��
				}	//	SDHC �� ��
			}	//	j for �� ��
			System.out.println();
			System.out.println("��� ����? : "+cnt);
		}	//	i for �� ��
		
		
		
		
		System.out.println(flush[0]);
		
		
/////	�� ���� ���������� ���� �ٽ� ���ֱ� 	
		for (int j = 0; j < 6; j++) {
			for (int k = 1; j + k < 7; k++) {

				if (value[j] > value[j + k]) {
					temp = value[j];
					value[j] = value[j + k];
					value[j + k] = temp;
					
					
				}	// if �� ��
			}	// K for �� ��
		}	// j for �� ��
		System.out.println("���� �������� value");
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
