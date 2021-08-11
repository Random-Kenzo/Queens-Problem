//if you want all the possible answers to this problem just comment the lines 25 and 38.

public class queen{
    public static void main(String [] args){                                
        int s = 8;                                                                  //the size of the board (s x s) and also the number of queens.
        int [][] b = new int[s][s];                                                 //matrix that simulate a chess board of the size previously defined.
        for (int i=0; i<s; i++) for(int j=0; j<s; j++) b[i][j]=0;                   //here we set all the board squares to '0' (0 means that this square doesn't have a queen)

        move(b, 0, s);                                                              //method responsible for performing the movemeents on our board. -
    }                                                                               //It starts on the first row, after finding a valid square it move to the next row.

    public static int [][] move(int [][] b, int r, int s){                          //this method receive the board 'b', the current row 'r' and the board's size 's'.
        if(r == s) {                                                                //if the current row equals to the size of the board, we print it.-
            printBoard (b, s);                                                      //Because that means we successively move all the queens in valid squares.
            return b;
        }

        if (r == 0){                                                                //if we are on the first row of the board:
            for (int i = 0; i < s; i++){                                            //loop where we find a valid square (in this case this loop will pass over all the rows)
                int [][]aux = new int[s][s];                                        //here we create a auxiliary board, we need to do this so will not cause problems, -
                aux = copy(b, s, r);                                                //like having two queens in the same row
                aux[0][i] = 1;                                                      //now we 'move' a queen in the current square (with row 'r' and column 'i') 

                aux = move(aux, r+1, s);                                            //here we proceed to the next row of the board.
                for(int j = 0; j < s; j++) if(aux[s-1][j] == 1) return aux;         //if aux has a queen in any square on the last row, meaning that we put all -
            }                                                                       //the 's' queens in valid squares, means that we have found a valid answer, -
        }                                                                           //so we end the execution of this method.
        
        else{                                                                       //if we are on any other row:
            for (int i = 0; i < s; i++){                                            //loop for finding a valid square on the current row.
                int [][]aux = new int[s][s];                                        //creation of a auxiliary board.
                aux = copy(b, s, r);
                aux [r][i] = 1;                                                     //now we 'move' a queen in the current square (with row 'r' and column 'i')

                int v = verify(aux, r, s, i);                                       //this method verify if the last 'movement' we did put a queen in a valid square.
                if(v == 1) {                                                        //if it is a valid:
                    aux = move(aux, r+1, s);                                        //we proced to the next row.
                    for(int j = 0; j < s; j++) if(aux[s-1][j] == 1) return aux;     //in the case that the last row have a valid queen we return, so we can end this program.
                }
            }
        }

        return b;
    }

    public static int [][] copy (int [][] b, int s, int r){                         //method responsible for copying a board, it receive the board 'b', the size 's' -
                                                                                    //and the current row 'r' (the last row with an actual queen in it)
        int [][] copy = new int [s][s];
        for(int i = 0; i <= r; i++){                                                //for each valid row (rows wuith a queen in it), we copy the value in each column -
            for(int j = 0; j< s; j++){                                              //of the copy of the board b.
                copy[i][j] = b [i][j];
            }
        }
        return copy;
    }

    public static int verify(int [][] b, int r, int s, int c){                      //this method receive the board 'b', the current row 'r', the size of the board 's' -
                                                                                    //and the current column 'c'.
        int ld = c-1;                                                               //this variable is responsible to memorize the squares in the left diagonal of the queen.
        int rd = c+1;                                                               //this variable is responsible to memorize the squares in the right diagonal of the queen.
        
        for(int i = r-1; i>= 0; i--){                                               //loop to test all the rows above the current one.
            if(b[i][c] == 1)return 0;                                               //here we test if the square in the current row in the same column of the queen been-
                                                                                    //tested have or not a queen, if it has one the queen tested is in an invalid square.
            if(ld >= 0){                                                            //here we test if the square in the current row and in the left diagonal have a -
                if(b[i][ld] == 1)return 0;                                          //queen in it, in the case it has one the queen is in an invalid square.
                ld--;                                                               //here ld will be changed to the next column.                                    
            }
            if(rd < s){                                                             //the same, but now for the right diagonal.
                if(b[i][rd] == 1)return 0;
                rd++;
            }
        }
        return 1;                                                                   //if it is a valid square, return '1'.
    }

    public static void printBoard(int [][] b, int s){                               //method that prints the board.
        for (int i=0; i<s; i++){ 
            for(int j=0; j<s; j++)System.out.print(b[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
    }
}
