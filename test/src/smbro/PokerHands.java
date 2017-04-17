package smbro;

import java.util.Scanner;

public class PokerHands {

	public static void main(String[] args) {
		int N = 20; // N:input lines Limit
		Scanner in = new Scanner(System.in);
		Function F = new Function();
		int l, i, hand, L;
		//char c;
		String[] input = new String[N];
		
		
		System.out.println(input.length);
		System.out.println("input. To stop inputting, please input '0'");
		for (l = 0; l < N; l++) {
			input[l] = in.nextLine();
			if (input[l].charAt(0) == '0')	//	'0'-->>48
				break;
		}
//		System.out.println(input[0]);
//		System.out.println(input[l].length()+1+"input[l] 길이 이다");
//		System.out.println(input[0]+1+"input[l] 길이 이다" );
//		System.out.println("너는 뭐니1?"+input[0].charAt(0));
//		System.out.println("너는 뭐니2?"+input[0].charAt(1));
//		System.out.println("너는 뭐니3?"+input[0].charAt(2));
//		System.out.println("너는 뭐니4?"+input[0].charAt(3));
		L = l; /* Input */	// 입력받은 줄수 새는 놈 1줄 넣으면 L --->> 1 줄 값을 int 형태로 나타냄 
//		System.out.println("나는 N : " + N);// N - 20 인데 20줄 까지만 입력 가능
//		System.out.println("넌 누구니"+L);
		int[][][] value = new int[L][2][5]; // value[line][hand][i]
		boolean[][] flush = new boolean[L][2]; // flush[line][hand]
		int[][] type = new int[L][2]; // type[line][hand]
		int[][] comp = new int[L][2]; // compared number: comp[line][hand] ←2
										// hands=the same type.
		int[][] comp2 = new int[L][2]; // 2 hands=both 2 pairs && both comps are
										// the same → compare comp2
		int[] result = new int[L];

		F.input_to_value(input, value, flush);
		F.typef(value, flush, type, comp, comp2);
		F.win(type, comp, comp2, value, result);

		for (l = 0; l < L; l++) {
			// System.out.print(result[l]+" ");
			switch (result[l]) {
			case 0:
				System.out.print("Black wins.");
				break;
			case 1:
				System.out.print("White wins.");
				break;
			case 2:
				System.out.print("Tie.       ");
				break;
			}
			System.out.println();
			
//			System.out.println("dddddd" + value.length);

			// PRINT the Process 
			for (hand = 0; hand < 2; hand++) {
				for (i = 0; i < 5; i++)
					
				System.out.print((value[l][hand][i] < 10 ? " " : "") + value[l][hand][i] + " ");
				System.out.print( + type[l][hand] + " comp:" + ((comp[l][hand] < 10) ? " " : "") + comp[l][hand]);
				System.out.print(((hand == 0) ? " (vs) " : "\n"));
			}
			
			
			
//			for (hand = 0; hand < 2; hand++) {
//				for (i = 0; i < 5; i++)
//				System.out.println("첫번째놈 나옴?"+(value[l][hand][i] < 10 ? " " : "") + value[l][hand][i] + " ");
//				System.out.println("두번째놈"+"type:" + type[l][hand] + " comp:" + ((comp[l][hand] < 10) ? " " : "") + comp[l][hand]);
//				System.out.println("세번째놈"+((hand == 0) ? " (vs) " : "\n"));
//			}
									 
		}
	}
}
