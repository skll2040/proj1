package smbro;

public class Function {
    int l, hand, i,j,k;

    
    // ������ ���ڵ��� ���������� ���� ���ִ� �޼ҵ�, 
    int c_to_v(char c){ //char to value function
        int n=(int)c;//2
        if(n>=50 && n<=57) return (n-48); //(int)'2'==50, (int)'9'==57
        else
            switch(c){
            case'T': return 10; case'J': return 11;
            case'Q': return 12; case'K': return 13;
            case'A': return 14; default: return 0; //0 couldn't be returned.
            }
    }

    
    
    
  
//     * �� ��(hand) �� �� value[line][hand][5]
//    �� ���� ���(suit)�� flush[][](5�� ��� ������) ���θ� üũ
//    * �Է¹��� value[][][5]�� ���� ������� ��迭�� (ORDERING)
    void input_to_value(String[] input, int[][][] value, boolean[][] flush){
        int L=value.length, temp;
        System.out.println("00000000000000"+L);
        char c, suit='0';	
        boolean suit_check=true;
        
        
        //	 2H 4S 4C 2D 4H 
        for(l=0;l<L;l++){	//	 �� ���� �Ѱ��� �ƴ³� 
            for(hand=0; hand<2; hand++){	//	2���� ���̸��� �и� hand =0 -->> 1��, hand = 1 -->> 2��
            	
                /*input[l] �� value[l][hand][5]*/
                for(i=hand*5, j=0, suit_check=true; i<(hand+1)*5; i++,j++){
                	
                    c=input[l].charAt(i*3);
                    value[l][hand][j]=c_to_v(c);
                }
                
                /*flush CHECK*/	// ī�� ����� ���������� �˾ƺ��� �� 
                for(i=hand*5,j=0;i<(hand+1)*5;i++,j++){
                	
                	// suit -->> '0' -->> 48
                    if(j==0) suit=input[l].charAt(i*3+1);// h
                    else if(input[l].charAt(i*3+1)!=suit) { 
                    	flush[l][hand]=false; break;
                    	}
                } 
                if(j==5) flush[l][hand]=true;
                
                /*ORDERING THE VALUES IN A HAND*/	// ������� value(ī�� ���� �� ) �� ���� ������ �־���?
                
				for (j = 0; j < 4; j++)
					for (k = 1; j + k < 5; k++)

						if (value[l][hand][j] > value[l][hand][j + k]) {
							temp = value[l][hand][j];
							value[l][hand][j] = value[l][hand][j + k];
							value[l][hand][j + k] = temp;
						}
			}	// ���� ������ ��
		}	//	�Ѱ��� ������ ��
	}	//	 ī�� �� �� 
    
    
    
    
    
    
// 2H 3D 5S 9C 13D    flush false    type    comp      comp2
//	2C 3H 4S 8C AH
    void typef(int[][][] value, boolean[][] flush, int[][] type, int[][] comp, int[][]comp2){
        int L=value.length, count0,count1,con0,compare_num=0; //con0: continuous 0 of diff
        int[][][] diff=new int[L][2][4];

        
		for (l = 0; l < L; l++)
			for (hand = 0; hand < 2; hand++) {
				count0 = 0;
				count1 = 0;
				con0 = 0;
				type[l][hand] = 0;
				comp[l][hand] = value[l][hand][4]; // 0: HighCard ��Default
				//	value �ȿ� �ִ� �ִ� ū ���� comp �� �־��ش�.
				//	0-->		13
				//	1-->	  	14

				for (j = 0; j < 4; j++) {
					diff[l][hand][j] = value[l][hand][j + 1] - value[l][hand][j];
				//		0-->	1				1-->	3	 -		0-->	2
				//		1-->	2				2-->	5	 -		1-->	3
				//		2-->	4				3-->	9	 -		2-->	5
				//		3-->	4				4-->	13	 -		3-->	9
					switch (diff[l][hand][j]) {
					case 0:
						count0++; // count0 of diff : a pair of values are the
									// same.
						if (j > 0 && diff[l][hand][j - 1] == 0)
							con0++; // con0: continuous 0
						if (compare_num < value[l][hand][j])
							compare_num = value[l][hand][j];
						break;
					case 1:
						count1++;
						break;
					} // if(count1==4) �� Straight
				}

				
				if (count0 > 0)
					comp[l][hand] = compare_num; // count0 of diff �� comp: pair
													// number
				switch (count0) {
				case 1:
					type[l][hand] = 1;
					break; // 1:One Pair
				case 2:
					if (con0 == 1)
						type[l][hand] = 3; // 3: 3 of a kind
					else {
						type[l][hand] = 2; // 2:Two Pairs
						comp2[l][hand] = (diff[l][hand][0] == 0) ? value[l][hand][0] : value[l][hand][1];
					} // comp2: both 2 hands are Two Pairs && both comps are the
						// same �� compare comp2
					break;
				case 3:
					if (con0 == 2)
						type[l][hand] = 7; // 7: 4 of a kind
					else {
						type[l][hand] = 6; // 6: Full house
						if (diff[l][hand][1] == 0)
							comp[l][hand] = value[l][hand][0];
						// full house) diff:0010 �� value:aaabb �� comp=a;(a<b,
						// but not b)
					}
					break;
				}

				if (count1 == 4 || flush[l][hand]) {
					if (count1 == 4 && flush[l][hand])
						type[l][hand] = 8; // 8:Straight Flush
					else if (type[l][hand] < 4) {
						if (flush[l][hand])
							type[l][hand] = 5; // 5:flush
						else if (count1 == 4)
							type[l][hand] = 4; // 4:Straight
					}
					comp[l][hand] = value[l][hand][4]; // compare_number=the
														// highest number
				}
			}
	}
    
    
    
    
    
 // * ������ type[][], comp, comp2, value ������� �̿��Ͽ� ���� ����
	void win(int[][] type, int[][] comp, int[][] comp2, int[][][] value, int[] result) {
		int L = type.length;
		for (l = 0; l < L; l++) { // result= 0:Black wins. 1:White wins. 2:Tie.
			if (type[l][0] != type[l][1])
				result[l] = (type[l][0] > type[l][1]) ? 0 : 1;
			else {
				if (comp[l][0] != comp[l][1])
					result[l] = (comp[l][0] > comp[l][1]) ? 0 : 1;
				else if (type[l][0] == 2 && comp2[l][0] != comp2[l][1])// ������
					result[l] = (comp2[l][0] > comp2[l][1]) ? 0 : 1;
				else {
					for (j = 4; j >= 0 && value[l][0][j] == value[l][1][j]; j--)
						;
					if (j == -1)
						result[l] = 2; // 2: Tie. ��all values are the same.
					else
						result[l] = (value[l][0][j] > value[l][1][j]) ? 0 : 1; // using
																				// the
																				// rules
																				// for
																				// High
																				// Card.
				}
			}
		}
	}
    

}
