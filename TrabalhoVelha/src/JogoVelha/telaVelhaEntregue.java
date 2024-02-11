package JogoVelha;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

public class telaVelhaEntregue extends JFrame {

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
	private JLabel lbNome;
	private JTextField txtNome;
	private JButton btReiniciar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaVelhaEntregue frame = new telaVelhaEntregue();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void fazerJogadaDoJogador() {
		for (JLabel j : jb) {
			if((j.getMousePosition(false)!=null)&&!t1.verificarGanhador() && !t1.empate()) {
				j1.setNome(txtNome.getText());
				j2.setNome("Jabba");
				if(!(txtNome.getText().equals(""))) {
					txtNome.setVisible(false);
					lbNome.setVisible(false);
				}
				
				String vetor [] = j.getName().split(",");
				int linha = Integer.parseInt(vetor[0]);
				int coluna = Integer.parseInt(vetor[1]);
				if(t1.verificarPosicao(linha, coluna)) {
					t1.jogar(linha, coluna, 'X');
					j.setIcon(new ImageIcon("x.jpg"));
					t.push(linha);
					t.push(coluna);
					if(!t1.verificarGanhador() && !t1.empate()) {
						fazerJogadaDoComputador();
						
					}else {
						if (t1.getVerf() == 'X') {
				            System.out.println("Vencedor: " + j1.getNome()+"\nSimbolo: " +t1.getVerf());
				            jbStatus.setText("Vencedor: " + j1.getNome()+" Simbolo: " +t1.getVerf());
				            if(!t.isEmpty()) {
			            		reiniciar();
			            		System.out.println("AQUI");
			            	}
				        } else {
				            if (t1.getVerf() == 'O') {
				                System.out.println("Vencedor: " + j2.getNome()+"\nSimbolo: " +t1.getVerf());
				                jbStatus.setText("Vencedor: " + j2.getNome()+" Simbolo: " +t1.getVerf());
				                if(!t.isEmpty()) {
				            		reiniciar();
				            		System.out.println("AQUI");
				            	}
				            }else {
				            	jbStatus.setText("Empate!");
				            	if(!t.isEmpty()) {
				            		reiniciar();
				            	}
				            }
				        }
					}
				}
			}
		}
	}
	
	private void fazerJogadaDoComputador() {
		Random gerador = new Random();
		int linha, coluna;
		do {
			linha = gerador.nextInt(3);
			coluna = gerador.nextInt(3);
		}while(!t1.verificarPosicao(linha, coluna));
		t1.jogar(linha, coluna, 'O');
		String n = "";
		n += linha+",";
		n += coluna;
		for(JLabel j : jb) {
			if( n.equals(j.getName()) ) {
				j.setIcon(new ImageIcon("o.jpg"));
				t.push(linha);
				t.push(coluna);
				t1.desenhar();
				
				
				if(t1.verificarGanhador()) {
					if (t1.getVerf() == 'X') {
			            System.out.println("Vencedor: " + j1.getNome()+"\nSimbolo: " +t1.getVerf());
			            jbStatus.setText("Vencedor: " + j1.getNome()+" Simbolo: " +t1.getVerf());
			            if(!tab.isEmpty()) {
		            		reiniciar();
		            	}
			        } else {
			            if (t1.getVerf() == 'O') {
			                System.out.println("Vencedor: " + j2.getNome()+"\nSimbolo: " +t1.getVerf());
			                jbStatus.setText("Vencedor: " + j2.getNome()+" Simbolo: " +t1.getVerf());
			                if(!tab.isEmpty()) {
			            		reiniciar();
			            	}
			            }else {
							if(t1.empate()) {
								jbStatus.setText("Empate!");
								if(!t.isEmpty()) {
				            		reiniciar();
				            	}
							}
						}
			        }
				}
			}
		}
	}
	
	private void desfazer() {
		if((!t.isEmpty())&&(!t1.verificarGanhador())&&(!t1.empate())) {
			int co = t.pop();
			int li = t.pop();
		    t1.jogar(li, co, '-');
		    String n = "";
		    n += li+",";
		    n += co;
		    
		    for(JLabel j : jb) {
		    	if(n.equals(j.getName())) {
		    		j.setIcon(new ImageIcon("hifen.jpg"));
		    		
		    	}
			}
		    
		    co = t.pop();
			li = t.pop();
		    t1.jogar(li, co, '-');
		    n = "";
		    n += li+",";
		    n += co;
		    
		    for(JLabel j : jb) {
		    	if(n.equals(j.getName())) {
		    		j.setIcon(new ImageIcon("hifen.jpg"));
		    		
		    	}
			}
		    
		    t1.desenhar();
		}
	}
	
	public void reiniciar() {
		if((!t.isEmpty())&&(!(t.size()==0))) {
			t1.iniciarTabuleiro();
			t1.desenhar();
			for (JLabel la : jb) {
				la.setIcon(new ImageIcon("hifen.jpg"));
			}
			txtNome.setVisible(true);
			lbNome.setVisible(true);
			j1.setNome("");
			txtNome.setText("");
			t = new Stack<Integer>();
		}
	}
	
	public telaVelhaEntregue() {
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
                    fazerJogadaDoJogador();
                }
            });
        }
		
        t1.iniciarTabuleiro();
        t1.desenhar();
        
        
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 796);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.DARK_GRAY);
		this.contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		
				setContentPane(this.contentPane);
		this.contentPane.setLayout(new MigLayout("", "[60.00][80.00,fill][80.00,fill][80.00,fill][60.00]", "[30.00][fill][80.00,fill][80.00,fill][80.00,fill][40.00,fill][40.00,fill]"));
		
		this.jbStatus = new JLabel("Status");
		this.jbStatus.setForeground(Color.WHITE);
		this.jbStatus.setFont(new Font("Verdana", Font.BOLD, 16));
		this.jbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		this.jbStatus.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.jbStatus, "cell 1 0 3 1");
		
		this.lbNome = new JLabel("Nome:");
		this.lbNome.setHorizontalAlignment(SwingConstants.CENTER);
		this.lbNome.setForeground(Color.WHITE);
		this.lbNome.setFont(new Font("Verdana", Font.BOLD, 16));
		this.lbNome.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.contentPane.add(this.lbNome, "cell 1 1,grow");
		
		this.txtNome = new JTextField();
		this.txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtNome.setForeground(new Color(0, 0, 0));
		this.txtNome.setFont(new Font("Verdana", Font.BOLD, 16));
		this.contentPane.add(this.txtNome, "cell 2 1 2 1,grow");
		this.txtNome.setColumns(10);
		
		this.jb0 = new JLabel("");
		this.jb0.setLocation(new Point(0, 0));
		this.jb0.setIcon(new ImageIcon("hifen.jpg"));
		this.jb0.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb0.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb0.setName("0,0");
		this.contentPane.add(this.jb0, "cell 1 2,growx");
		
		this.jb1 = new JLabel("");
		this.jb1.setLocation(new Point(0, 1));
		this.jb1.setIcon(new ImageIcon("hifen.jpg"));
		this.jb1.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb1.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb1.setName("0,1");
		this.contentPane.add(this.jb1, "cell 2 2");
		
		this.jb2 = new JLabel("");
		this.jb2.setLocation(new Point(0, 2));
		this.jb2.setIcon(new ImageIcon("hifen.jpg"));
		this.jb2.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb2.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb2.setName("0,2");
		this.contentPane.add(this.jb2, "cell 3 2");
		
		this.jb3 = new JLabel("");
		this.jb3.setLocation(new Point(1, 0));
		this.jb3.setIcon(new ImageIcon("hifen.jpg"));
		this.jb3.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb3.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb3.setName("1,0");
		this.contentPane.add(this.jb3, "cell 1 3");
		
		this.jb4 = new JLabel("");
		this.jb4.setLocation(new Point(1, 1));
		this.jb4.setIcon(new ImageIcon("hifen.jpg"));
		this.jb4.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb4.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb4.setName("1,1");
		this.contentPane.add(this.jb4, "cell 2 3");
		
		this.jb5 = new JLabel("");
		this.jb5.setLocation(new Point(1, 2));
		this.jb5.setIcon(new ImageIcon("hifen.jpg"));
		this.jb5.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb5.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb5.setName("1,2");
		this.contentPane.add(this.jb5, "cell 3 3");
		
		this.jb6 = new JLabel("");
		this.jb6.setLocation(new Point(2, 0));
		this.jb6.setIcon(new ImageIcon("hifen.jpg"));
		this.jb6.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb6.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb6.setName("2,0");
		this.contentPane.add(this.jb6, "cell 1 4");
		
		this.jb7 = new JLabel("");
		this.jb7.setLocation(new Point(2, 1));
		this.jb7.setIcon(new ImageIcon("hifen.jpg"));
		this.jb7.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb7.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb7.setName("2,1");
		this.contentPane.add(this.jb7, "cell 2 4");
		
		this.jb8 = new JLabel("");
		this.jb8.setLocation(new Point(2, 2));
		this.jb8.setIcon(new ImageIcon("hifen.jpg"));
		this.jb8.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		this.jb8.setHorizontalAlignment(SwingConstants.CENTER);
		this.jb8.setName("2,2");
		this.contentPane.add(this.jb8, "cell 3 4");
		
		this.btnNewButton = new JButton("Desfazer");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desfazer();
			}
		});
		this.btnNewButton.setForeground(Color.WHITE);
		this.btnNewButton.setBackground(Color.GRAY);
		this.btnNewButton.setFont(new Font("Verdana", Font.BOLD, 16));
		this.contentPane.add(this.btnNewButton, "cell 1 5 3 1");
		
		this.btReiniciar = new JButton("Reiniciar");
		btReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		this.btReiniciar.setForeground(Color.WHITE);
		this.btReiniciar.setFont(new Font("Verdana", Font.BOLD, 16));
		this.btReiniciar.setBackground(Color.GRAY);
		this.contentPane.add(btReiniciar, "cell 1 6 3 1");
	}

}

