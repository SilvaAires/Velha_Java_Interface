package JogoVelha;

public class Tabuleiro {
	private char matriz[][] = new char[3][3];
    private char verf;
    private int linha;
    private int coluna;
    
    public Tabuleiro() {
    }
    
    public Tabuleiro(Tabuleiro j) {
        this.linha = j.linha;
        this.coluna = j.coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public char getVerf() {
        return verf;
    }

    public void setVerf(char verf) {
        this.verf = verf;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public void iniciarTabuleiro() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                this.matriz[i][j] = '-';
            }
        }
    }

    public void desenhar() {
        System.out.println("TABULEIRO");
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public boolean verificarPosicao(int l, int c) {
        if (this.matriz[l][c] == '-') {
            return true;
        }
        System.out.println("Posição já ocupada!");
        return false;
    }

    public boolean empate() {
        int cont = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (this.matriz[i][j] != '-') {
                    cont++;
                }
            }
        }
        if (cont == 9 && !verificarGanhador()) {
            System.out.println("Empate!");
            return true;
        }
        return false;
    }

    public void jogar(int l, int c, char x) {
        this.matriz[l][c] = x;
    }

    public boolean verificarGanhador() {
        if ((this.matriz[0][0] == this.matriz[1][1] && this.matriz[1][1] == this.matriz[2][2] && this.matriz[0][0] != '-')
                || (this.matriz[0][2] == this.matriz[1][1] && this.matriz[1][1] == this.matriz[2][0] && this.matriz[0][2] != '-')) {
            verf = this.matriz[1][1];
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (this.matriz[i][0] == this.matriz[i][1] && this.matriz[i][1] == this.matriz[i][2] && this.matriz[i][0] != '-') {
                verf = this.matriz[i][1];
                return true;
            }
            if (this.matriz[0][i] == this.matriz[1][i] && this.matriz[1][i] == this.matriz[2][i] && this.matriz[0][i] != '-') {
                verf = this.matriz[1][i];
                return true;
            }
        }
        return false;
    }
}
