public class queen2{
    public static void main(String [] args){
        int s = 15;
        int [][] b = new int[s][s];
        for (int i=0; i<s; i++) for(int j=0; j<s; j++) b[i][j]=0;

        move(b, 0, s);
    }

    public static int [][] move(int [][] b, int r, int s){
        if(r == s) {
            printBoard (b, s);
            return b;
        }

        if (r == 0){
            for (int i = 0; i <s; i++){
                int [][]aux = new int[s][s];
                aux = copy(aux, b, s, r);
                aux[0][i] = 1;

                aux = move(aux, r+1, s);
                for(int j = 0; j < s; j++) if(aux[s-1][j] == 1) return aux;
            }
        }
        else{
            for (int i = 0; i<s; i++){
                int [][]aux = new int[s][s];
                aux = copy(aux, b, s, r);
                aux [r][i] = 1;


                int v = verify(aux, r, s, i);
                if(v == 1) {
                    aux = move(aux, r+1, s);
                    for(int j = 0; j < s; j++) if(aux[s-1][j] == 1) return aux;
                }
            }
        }
        

        return b;
    }

    public static int [][] copy (int [][] aux, int [][] b, int s, int r){
        int [][] copy = new int [s][s]; 
        for(int i = 0; i <= r; i++){
            for(int j = 0; j< s; j++){
                copy[i][j] = b [i][j];
            }
        }
        return copy;
    }

    public static int verify(int [][] b, int r, int s, int c){
        int e = c-1;
        int d = c+1;
        for(int i = r-1; i>= 0; i--){
            if(b[i][c] == 1)return 0;
            if(e>= 0){
                if(b[i][e] == 1)return 0;
                e--;
            }
            if(d< s){
                if(b[i][d] == 1)return 0;
                d++;
            }
        }
        return 1;
    }

    public static void printBoard(int [][] b, int s){
        for (int i=0; i<s; i++){ 
            for(int j=0; j<s; j++)System.out.print(b[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
    }
}