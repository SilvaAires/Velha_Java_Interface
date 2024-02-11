package JogoVelha;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Tabuleiro t1 = new Tabuleiro();
        Jogador j1 = new Jogador();
        Jogador j2 = new Jogador();
        Stack<Tabuleiro> tab = new Stack<>();

        System.out.println("Jogo da Velha");
        Scanner s = new Scanner(System.in);
        Random gerador = new Random();
        t1.iniciarTabuleiro();
        t1.desenhar();
        System.out.println("\nDigite o nome de Jogador 1:");
        j1.setNome(s.nextLine());
        j2.setNome("Jabba");

        s = new Scanner(System.in);
        while (!t1.verificarGanhador() && !t1.empate()) {
            do {
                s = new Scanner(System.in);
                System.out.print("\nDigite a linha da jogada: ");
                t1.setLinha(s.nextInt());
                System.out.print("\nDigite a coluna da jogada: ");
                t1.setColuna(s.nextInt());
            } while (!t1.verificarPosicao(t1.getLinha(), t1.getColuna()));
            t1.jogar(t1.getLinha(), t1.getColuna(), 'X');
            tab.push(new Tabuleiro(t1));

            if (!t1.empate() && !t1.verificarGanhador()) {
                do {
                    t1.setLinha(gerador.nextInt(3));
                    t1.setColuna(gerador.nextInt(3));
                } while (!t1.verificarPosicao(t1.getLinha(), t1.getColuna()));
                t1.jogar(t1.getLinha(), t1.getColuna(), 'O');
                tab.push(new Tabuleiro(t1));
            }

            t1.desenhar();
            s = new Scanner(System.in);
            String r = "";
            if (!t1.verificarGanhador() && !t1.empate()) {
                System.out.println("Deseja refazer a joga? S/N \nJogadas feitas e salvas: " + tab.size());
                r = s.nextLine();
            }

            while (!"n".equalsIgnoreCase(r) && !tab.empty() && !t1.verificarGanhador() && !t1.empate()) {
                Tabuleiro teste = new Tabuleiro(t1);
                teste = tab.pop();
                t1.jogar(teste.getLinha(), teste.getColuna(), '-');
                teste = tab.pop();
                t1.jogar(teste.getLinha(), teste.getColuna(), '-');
                t1.desenhar();
                s = new Scanner(System.in);
                System.out.println("\nDeseja refazer mais? S/N \nJogadas feitas e salvas: " + tab.size());
                r = s.nextLine();
            }
        }
        if (t1.getVerf() == 'X') {
            System.out.println("Vencedor: " + j1.getNome()+"\nSimbolo: " +t1.getVerf());
        } else {
            if (t1.getVerf() == 'O') {
                System.out.println("Vencedor: " + j2.getNome()+"\nSimbolo: " +t1.getVerf());
            }
        }
    }
}
