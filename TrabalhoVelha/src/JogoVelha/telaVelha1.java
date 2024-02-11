package JogoVelha;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaVelha1 extends JFrame {

	private JPanel contentPane;
	private JLabel jb0;
	private JLabel jb1;
	private JLabel jb4;
	private JLabel jb6;
	private JLabel jb7;
	private JLabel jb8;
	private JLabel jb5;
	private JLabel jb2;
	private JLabel jbStatus;
	private JLabel jb3;
	private JButton btnNewButton;
	private LinkedList<JLabel> jb;
	private Tabuleiro t1;
	private Jogador j1;
	private Jogador j2;
	private Stack<Tabuleiro> tab = new Stack<Tabuleiro>();;
	private Stack<Integer> t = new Stack<Integer>();
	private boolean jogador1Vez = true;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaVelha1 frame = new telaVelha1();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void fazerJogadaDoJogador(JLabel label) {
        int index = jb.indexOf(label);
        System.out.println(index);
        int linha = index / 3;
        int coluna = index % 3;
        System.out.println(linha);
        System.out.println(coluna);
        if (t1.verificarPosicao(linha, coluna)) {
            t1.jogar(linha, coluna, 'X');
            label.setIcon(new ImageIcon("x.jpg")); 
            tab.push(new Tabuleiro(t1));
            t.push(linha);
            t.push(coluna);
            jogador1Vez = false;

            if (t1.verificarGanhador()) {
                jbStatus.setText("Jogador 1 ganhou!");
            } else if (t1.empate()) {
                jbStatus.setText("Empate!");
            } else {
                fazerJogadaDoComputador();
                t1.desenhar();
            }
        }
    }
	
	private void fazerJogadaDoComputador() {
        Random rand = new Random();
        int linha, coluna;
        do {
            linha = rand.nextInt(3);
            coluna = rand.nextInt(3);
        } while (!t1.verificarPosicao(linha, coluna));
        t1.jogar(linha, coluna, 'O');
        int index = linha * 3 + coluna;
        jb.get(index).setIcon(new ImageIcon("o.jpg"));
        tab.push(new Tabuleiro(t1));
        t.push(linha);
        t.push(coluna);
        
        System.out.print("Tamanho: "+tab.size());
        
        jogador1Vez = true;

        if (t1.verificarGanhador()) {
            jbStatus.setText("Computador ganhou!");
        } else if (t1.empate()) {
            jbStatus.setText("Empate!");
        }
    }

	private void desfazer() {
		if(!tab.isEmpty()) {
			System.out.println("Tamanho: "+t.size());
			System.out.println("Aqui"+t.firstElement());
			int co = t.pop();
			int li = t.pop();
		    t1.jogar(li, co, '-');
		    int index = li* 3 + co;
		    jb.get(index).setIcon(new ImageIcon("hifen.jpg"));
		    t1.desenhar();
		}
	}
	
	public telaVelha1() {
		initComponents();
		jb = new LinkedList<JLabel>();
		t1 = new Tabuleiro();
		tab = new Stack<Tabuleiro>();
		j1 = new Jogador();
		j2 = new Jogador();
		jb.add(jb0);
		jb.add(jb1);
		jb.add(jb2);
		jb.add(jb3);
		jb.add(jb4);
		jb.add(jb5);
		jb.add(jb6);
		jb.add(jb7);
		jb.add(jb8);
		
		
		for (JLabel label : jb) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (jogador1Vez) {
                        fazerJogadaDoJogador(label);
                    }
                }
            });
        }
		
        t1.iniciarTabuleiro();
        t1.desenhar();
        
        
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 772);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.DARK_GRAY);
		this.contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		
				setContentPane(this.contentPane);
		this.contentPane.setLayout(new MigLayout("", "[60.00][80.00,fill][80.00,fill][80.00,fill][60.00]", "[30.00][80.00,fill][80.00,fill][80.00,fill][40.00,fill]"));
		
		this.jbStatus = new JLabel("Status");
		this.jbStatus.setForeground(Color.WHITE);
		this.jbStatus.setFont(new Font("Verdana", Font.BOLD, 16));
		this.jbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		this.jbStatus.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jbStatus, "cell 1 0 3 1");
		
		this.jb0 = new JLabel("");
		this.jb0.setLocation(new Point(0, 0));
		this.jb0.setIcon(new ImageIcon("hifen.jpg"));
		this.jb0.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb0.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jb0, "cell 1 1,growx");
		
		this.jb1 = new JLabel("");
		this.jb1.setLocation(new Point(0, 1));
		this.jb1.setIcon(new ImageIcon("hifen.jpg"));
		this.jb1.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb1.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jb1, "cell 2 1");
		
		this.jb2 = new JLabel("");
		this.jb2.setLocation(new Point(0, 2));
		this.jb2.setIcon(new ImageIcon("hifen.jpg"));
		this.jb2.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb2.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(this.jb2, "cell 3 1");
		
		this.jb3 = new JLabel("");
		this.jb3.setLocation(new Point(1, 0));
		this.jb3.setIcon(new ImageIcon("hifen.jpg"));
		this.jb3.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb3.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jb3, "cell 1 2");
		
		this.jb4 = new JLabel("");
		this.jb4.setLocation(new Point(1, 1));
		this.jb4.setIcon(new ImageIcon("hifen.jpg"));
		this.jb4.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb4.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(this.jb4, "cell 2 2");
		
		this.jb5 = new JLabel("");
		this.jb5.setLocation(new Point(1, 2));
		this.jb5.setIcon(new ImageIcon("hifen.jpg"));
		this.jb5.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb5.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jb5, "cell 3 2");
		
		this.jb6 = new JLabel("");
		this.jb6.setLocation(new Point(2, 0));
		this.jb6.setIcon(new ImageIcon("hifen.jpg"));
		this.jb6.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb6.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(this.jb6, "cell 1 3");
		
		this.jb7 = new JLabel("");
		this.jb7.setLocation(new Point(2, 1));
		this.jb7.setIcon(new ImageIcon("hifen.jpg"));
		this.jb7.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb7.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jb7, "cell 2 3");
		
		this.jb8 = new JLabel("");
		this.jb8.setLocation(new Point(2, 2));
		this.jb8.setIcon(new ImageIcon("hifen.jpg"));
		this.jb8.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb8.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(this.jb8, "cell 3 3");
		
		this.btnNewButton = new JButton("Desfazer");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desfazer();
			}
		});
		this.btnNewButton.setForeground(Color.WHITE);
		this.btnNewButton.setBackground(Color.GRAY);
		this.btnNewButton.setFont(new Font("Verdana", Font.BOLD, 16));
		this.contentPane.add(this.btnNewButton, "cell 1 4 3 1");
	}

}
