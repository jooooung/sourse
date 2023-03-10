package com.lec.Ex;
//배열은 대부분 1차원, cf.다차원 배열
public class Ex05_multiDimension {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3}; ///1차원 배열
		System.out.println(arr[0]);
		int[][] test = {{1, 2, 3}, {4, 5, 6}} ;  //2차원 배열 
		test[0][1] = 200;     //0행 1열의 값을 200으로 지정
		//다차원 배열의 출력
		for(int i=0 ; i<test.length ; i++) {  //행(i)
			for(int j=0 ; j<test[i].length ; j++) {  //열(j)
				System.out.printf("test[%d][%d] = %d\n", i, j, test[i][j]);
			}//for-j
		}//for-i
	}//main
}//class