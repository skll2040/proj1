package smbro;

public class Function {
    int l, hand, i,j,k;

    
    // 들어오는 문자들을 숫자형으로 리턴 해주는 메소드, 
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

    
    
    
  
//     * 한 패(hand) 값 → value[line][hand][5]
//    한 패의 모양(suit)은 flush[][](5장 모두 같은지) 여부만 체크
//    * 입력받은 value[][][5]를 작은 순서대로 재배열함 (ORDERING)
    void input_to_value(String[] input, int[][][] value, boolean[][] flush){
        int L=value.length, temp;
        System.out.println("00000000000000"+L);
        char c, suit='0';	
        boolean suit_check=true;
        
        
        //	 2H 4S 4C 2D 4H 
        for(l=0;l<L;l++){	//	 몇 개임 한것을 아는놈 
            for(hand=0; hand<2; hand++){	//	2명의 게이머의 패를 hand =0 -->> 1픽, hand = 1 -->> 2픽
            	
                /*input[l] → value[l][hand][5]*/
                for(i=hand*5, j=0, suit_check=true; i<(hand+1)*5; i++,j++){
                	
                    c=input[l].charAt(i*3);
                    value[l][hand][j]=c_to_v(c);
                }
                
                /*flush CHECK*/	// 카드 모양이 프러쉬인지 알아보는 놈 
                for(i=hand*5,j=0;i<(hand+1)*5;i++,j++){
                	
                	// suit -->> '0' -->> 48
                    if(j==0) suit=input[l].charAt(i*3+1);// h
                    else if(input[l].charAt(i*3+1)!=suit) { 
                    	flush[l][hand]=false; break;
                    	}
                } 
                if(j==5) flush[l][hand]=true;
                
                /*ORDERING THE VALUES IN A HAND*/	// 집어넣은 value(카드 숫자 비교 ) 를 작은 순으로 넣어줌?
                
				for (j = 0; j < 4; j++)
					for (k = 1; j + k < 5; k++)

						if (value[l][hand][j] > value[l][hand][j + k]) {
							temp = value[l][hand][j];
							value[l][hand][j] = value[l][hand][j + k];
							value[l][hand][j + k] = temp;
						}
			}	// 한패 돌릴때 끝
		}	//	한게임 돌리고 끝
	}	//	 카드 비교 끝 
    
    
    
    
    
    
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
				comp[l][hand] = value[l][hand][4]; // 0: HighCard ←Default
				//	value 안에 있는 최대 큰 값을 comp 에 넣어준다.
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
					} // if(count1==4) → Straight
				}

				
				if (count0 > 0)
					comp[l][hand] = compare_num; // count0 of diff → comp: pair
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
						// same → compare comp2
					break;
				case 3:
					if (con0 == 2)
						type[l][hand] = 7; // 7: 4 of a kind
					else {
						type[l][hand] = 6; // 6: Full house
						if (diff[l][hand][1] == 0)
							comp[l][hand] = value[l][hand][0];
						// full house) diff:0010 → value:aaabb → comp=a;(a<b,
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
    
    
    
    
    
 // * 변수를 type[][], comp, comp2, value 순서대로 이용하여 승패 결정
	void win(int[][] type, int[][] comp, int[][] comp2, int[][][] value, int[] result) {
		int L = type.length;
		for (l = 0; l < L; l++) { // result= 0:Black wins. 1:White wins. 2:Tie.
			if (type[l][0] != type[l][1])
				result[l] = (type[l][0] > type[l][1]) ? 0 : 1;
			else {
				if (comp[l][0] != comp[l][1])
					result[l] = (comp[l][0] > comp[l][1]) ? 0 : 1;
				else if (type[l][0] == 2 && comp2[l][0] != comp2[l][1])// 버린다
					result[l] = (comp2[l][0] > comp2[l][1]) ? 0 : 1;
				else {
					for (j = 4; j >= 0 && value[l][0][j] == value[l][1][j]; j--)
						;
					if (j == -1)
						result[l] = 2; // 2: Tie. ←all values are the same.
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
