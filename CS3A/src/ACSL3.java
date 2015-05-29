import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/*Seneca Meeks
 * ACSL 3
 * 3/4/15
 */

public class ACSL3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while(true){
			//DECLRARATIONS
			Scanner scan = new Scanner(System.in); 
			String[] input = scan.nextLine().split(", ");
			int piece1 = Integer.parseInt(input[0]); 
			int piece2 = Integer.parseInt(input[1]);
			final int blank = 0; //location empty
			final int player1 = 1; //player 1's piece
			final int player2 = 2; //player 2's piece
			final int barrier = 3; //location blocked 
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0; 

			//PLAYER 1		
			if(Integer.parseInt(input[0])%7 == 0){
				x1 = 6;
				y1 = Integer.parseInt(input[0])/7 -1;
			}
			else{
				x1 = Integer.parseInt(input[0])%7 - 1; 
				y1 = Integer.parseInt(input[0])/7;
			}

			//PLAYER 2
			if(Integer.parseInt(input[1])%7 == 0){
				x2 = 6;
				y2 = Integer.parseInt(input[1])/7 - 1;
			}
			else{
				x2 = Integer.parseInt(input[1])%7 - 1; 
				y2 = Integer.parseInt(input[1])/7;
			}

			//BOARD 
			int board[][] = new int[7][7]; //(X(remainder) ,Y(int)
			for (int i = 0; i < 7; i++) { 
				for (int j = 0; j < 7; j++) {
					board[i][j] = blank; //creates empty board
				}
			}

			//STARTING POSITIONS AND BARRIERS 
			board[x1][y1] = player1; 	
			board[x2][y2] = player2; 
			for (int i = 2; i < input.length - 1; i++) {  	
				if(Integer.parseInt(input[i])%7 == 0){
					board[6][Integer.parseInt(input[i])/7 - 1] = barrier;
				}
				else{
					board[Integer.parseInt(input[i])%7 - 1][Integer.parseInt(input[i])/7] = barrier;
				}
			}

			//FIND AVAILABLE POSITIONS AROUND PLAYER 1 
			ArrayList<Integer> surroundings = new ArrayList<Integer>();
			ArrayList<Integer> adjacent = new ArrayList<Integer>();
			ArrayList<Integer> path = new ArrayList<Integer>();

			//FAR RIGHT SIDE
			if(x1 == 6){
				if(y1 == 0){ //bottom right corner
					if(board[5][0] == blank){
						surroundings.add(6);
						adjacent.add(6);
					}
					if(board[6][1] == blank){
						surroundings.add(14);
						adjacent.add(14);
					}
					if(board[5][1] == blank){
						surroundings.add(13); 
					}
				}
				else if(y1 == 6){ //top right corner
					if(board[5][6] == blank){
						surroundings.add(48);
						adjacent.add(48);
					}
					if(board[6][5] == blank){
						surroundings.add(42);
						adjacent.add(42);
					}
					if(board[5][5] == blank){
						surroundings.add(41);
					}
				}	
				else{ 
					if(board[6][y1+1] == blank){ //up
						surroundings.add(piece1 + 7);
						adjacent.add(piece1 + 7);
					}
					if(board[6][y1-1] == blank){ //down
						surroundings.add(piece1 - 7);
						adjacent.add(piece1 - 7);
					}		
					if(board[5][y1] == blank){ //left
						surroundings.add(piece1 - 1);
						adjacent.add(piece1 - 1);
					}
					if(board[5][y1+1] == blank){ //left diagonal up
						surroundings.add(piece1 + 6);
					}
					if(board[5][y1-1] == blank){ //left diagonal down
						surroundings.add(piece1 - 8);
					}
				}
			}
			//FAR LEFT SIDE
			else if(x1 == 0){
				if(y1 == 0){//left bottom corner
					if(board[0][1] == blank){
						surroundings.add(8);
						adjacent.add(8);
					}
					if(board[1][1] == blank){
						surroundings.add(9);
					}
					if(board[1][0] == blank){
						surroundings.add(2); 
						adjacent.add(2);
					}
				}
				else if(y1 == 6){
					if(board[0][5] == blank){
						surroundings.add(36);
						adjacent.add(36);
					}
					if(board[1][5] == blank){
						surroundings.add(37);
					}
					if(board[1][5] == blank){
						surroundings.add(44);
						adjacent.add(44);
					}
				}	
				else{
					if(board[0][y1+1] == blank){ //up
						surroundings.add(piece1 + 7);
						adjacent.add(piece1 + 7);
					}
					if(board[0][y1-1] == blank){ //down
						surroundings.add(piece1 - 7);
						adjacent.add(piece1 - 7);
					}		
					if(board[1][y1] == blank){ //right
						surroundings.add(piece1 + 1);
						adjacent.add(piece1 + 1);
					}
					if(board[1][y1+1] == blank){ //right diagonal up
						surroundings.add(piece1 + 8);
					}
					if(board[1][y1-1] == blank){ //right diagonal down
						surroundings.add(piece1 - 6);
					}
				}
			}

			//BOTTOM
			else if(y1 == 0){
				if(board[x1-1][0] == blank){ //left
					surroundings.add(piece1 - 1);
					adjacent.add(piece1 - 1);
				}
				if(board[x1 + 1][0] == blank){ //right
					surroundings.add(piece1 + 1);
					adjacent.add(piece1 + 1);
				}		
				if(board[x1][1] == blank){ //up
					surroundings.add(piece1 + 7);
					adjacent.add(piece1 + 7);
				}
				if(board[x1-1][1] == blank){ //left diagonal up
					surroundings.add(piece1 + 6);
				}
				if(board[x1 + 1][1] == blank){ //right diagonal up
					surroundings.add(piece1 + 8);
				}
			}	

			//TOP
			else if(y1 == 6){
				if(board[x1-1][6] == blank){ //left
					surroundings.add(piece1 - 1);
					adjacent.add(piece1 - 1);
				}
				if(board[x1 + 1][6] == blank){ //right
					surroundings.add(piece1 + 1);
					adjacent.add(piece1 + 1);
				}		
				if(board[x1][5] == blank){ //down
					surroundings.add(piece1 - 7);
					adjacent.add(piece1 - 7);
				}
				if(board[x1-1][5] == blank){ //left diagonal down
					surroundings.add(piece1 - 8);
				}
				if(board[x1 + 1][5] == blank){ //right diagonal up
					surroundings.add(piece1 - 6);
				}
			}

			//EVERYWHERE ELSE
			else{
				if(board[x1][y1+1] == blank){ //one up
					surroundings.add(piece1 + 7);
					adjacent.add(piece1 + 7);
				}
				if(board[x1][y1-1] == blank){ //one down
					surroundings.add(piece1 - 7);
					adjacent.add(piece1 - 7);
				}
				if(board[x1-1][y1] == blank){ //left
					surroundings.add(piece1 - 1);
					adjacent.add(piece1 - 1);
				}
				if(board[x1+1][y1] == blank){ //right
					surroundings.add(piece1 + 1);
					adjacent.add(piece1 + 1);
				}
				if(board[x1+1][y1-1] == blank){ //right diagonal down
					surroundings.add(piece1 - 6);
				}	
				if(board[x1+1][y1+1] == blank){ //right diagonal up
					surroundings.add(piece1 + 8);
				}	
				if(board[x1-1][y1-1] == blank){ //left diagonal down
					surroundings.add(piece1 - 8);
				}	
				if(board[x1-1][y1+1] == blank){ //left diagonal up
					surroundings.add(piece1 + 6);
				}	
			}

			//NO POSSIBILITIES
			if(surroundings.size() > 3 || surroundings.size() == 0 || adjacent.size() == 3 || surroundings.size() - adjacent.size() >= 3){
				System.out.println("NONE");
			}
			//YES POSSIBILITIES 
			else{
				//NEED case for if piece2 is in surroundings 
				if(surroundings.size() == 3){ //THREE IN A ROW
					int avg = 0;
					Collections.sort(surroundings);
					for(int n : surroundings){
						avg += n; 
					}
					if (avg/3 == surroundings.get(1)){
						if(surroundings.get(1) - surroundings.get(0) == 1){
							if(piece2 < surroundings.get(0)){ //keep going right
								while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 6){
									x2 += 1;
									path.add(piece2);
									piece2 += 1; 
									//path.add(piece2);
								}
								path.remove(0); 
								System.out.println(path.toString().replaceAll("[\\[\\]]",""));

							}
							else{ //keep going left
								while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0 ){
									x2 -= 1;
									path.add(piece2);
									piece2 -= 1;
								}
								path.remove(0); 
								System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							}
						}
						else if(surroundings.get(1) - surroundings.get(0) == 7){
							if(piece2 < surroundings.get(0)){ //keep going up
								while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 &&  y2!=6){
									y2 += 1;
									path.add(piece2);
									piece2 += 7;
								}
								path.remove(0); 
								System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							}
							else{ //keep going down
								while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && y2!=0){
									y2 -= 1;
									path.add(piece2);
									piece2 -= 1;
								}
								path.remove(0); 
								System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							}
						}
					}
					else{
						System.out.println("NONE");
					}
				}

				else if(adjacent.size() == 2){ //TWO ADJACENT POSITIONS AVAILABLE (Go Diagonally)
					Collections.sort(adjacent);
					if(adjacent.get(1) - adjacent.get(0) == 8){  
						if(piece2 < surroundings.get(0)){ //keep going up diagonally to the right
							piece2 += 8;
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0 && y2!=0){
								x2 += 1;
								y2 += 1; 
								path.add(piece2);
								piece2 += 8;
							}
							path.remove(0); 
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
						else{ //keep going down diagonally to the left
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0 && y2!=0){
								x2 -= 1;
								y2 -= 1; 
								path.add(piece2);
								piece2 -= 8;
							}
							path.remove(0); 
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
					}
					else if(adjacent.get(1) - adjacent.get(0) == 6){
						if(piece2 < surroundings.get(0)){ //keep going up diagonally to the left
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0 && y2!=6){ 
								path.add(piece2);
								piece2 += 6;
								x2 -= 1;
								y2 += 1;
								//System.out.println(path);
							}
							path.remove(0); 
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							//System.out.println("here 2: " + piece2);
							//System.out.println("here 1: " + piece1); 
						}
						else{ //keep going down diagonally to the right
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 6 && y2!=0){
								x2 += 1;
								y2 -= 1; 
								path.add(piece2);
								piece2 -= 6;
							}
							path.remove(0);
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
					}
					else{
						System.out.println("NONE");
					}
				}
				else if(adjacent.size() == 1 && surroundings.size() == 2){ //ONE ADJACENT AND ONE DIAGONAL
					if(piece2 < surroundings.get(0)){ //keep going right
						while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 6){
							x2 += 1; 
							path.add(piece2);
							piece2 += 1;
						}
						path.remove(0); 
						System.out.println(path.toString().replaceAll("[\\[\\]]",""));
					}
					else{ //keep going left
						while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0 ){
							x2 -= 1;
							path.add(piece2);
							piece2 += 1; 
						}
						path.remove(0); 
						System.out.println(path.toString().replaceAll("[\\[\\]]",""));
					}
				}
				else if(adjacent.size() == 1 && surroundings.size() == 1){ //ONE ADJACENT AVAILABLE 
					if(piece2 < surroundings.get(0)){ //keep going up or to the right
						if(x1 == x2){ //go up
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && y2!=6){
								y2 += 1;
								path.add(piece2);
								piece2 += 7; 
							}
							path.remove(0); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
						else{
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 6){
								x2 += 1;
								path.add(piece2);
								piece2 += 1; 
							}
							path.remove(0); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
					}
					else{ //keep going down or to the left
						if(x1 == x2){ //go down
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && y2!=0){
								y2 -= 1;
								path.add(piece2);
								piece2 -= 7; 
							}
							path.remove(0); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
						else{
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0){
								x2 -= 1;
								path.add(piece2);
								piece2 -= 1; 
							}
							path.remove(0); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
					}
				}		
				else if(adjacent.size() == 0 && surroundings.size() == 1){  //ONLY DIAGONAL AVAILABLE 				
					if(x2 < x1){
						if(piece2 < piece1){
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 6 && y2 != 6){
								x2 += 1;
								y2 += 1;
								path.add(piece2);
								piece2 += 8;
								
							}
							path.remove(0);
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
						}
						else{ 
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 6 && y2!=0){
								x2 += 1;
								y2 -= 1;
								path.add(piece2);
								piece2 -= 6; 
							}
							path.remove(0); 
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							//System.out.println("I'm Here");
						}
					}
					else{
						if(piece2 < piece1){
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1){
								x2 -= 1;
								y2 +=1; 
								path.add(piece2);	
								piece2 += 6; 
							}
							path.remove(0); 
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							//System.out.println("I'm Here");
						}
						else{
							while(board[x2][y2] != player1 && board[x2][y2] != barrier && piece2 != piece1 && x2 != 0 && y2!=0){
								x2 -= 1;
								y2 -=1;
								path.add(piece2);
								piece2 -= 8; 
							}
							path.remove(0); 
							path.add(piece2); 
							System.out.println(path.toString().replaceAll("[\\[\\]]",""));
							//System.out.println("I'm Here");
						}
					}
				}
				else{
					System.out.println("NONE");
				}
			}
		}
	}
}

