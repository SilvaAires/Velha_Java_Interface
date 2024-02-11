package JogoVelha;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class teste extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teste frame = new teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public teste() {
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(this.contentPane);
		this.contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		this.scrollPane = new JScrollPane();
		this.contentPane.add(this.scrollPane, "cell 0 0,grow");
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		this.scrollPane.setViewportView(this.table);
	}

}

/*private void jogada() {
	for(JLabel jl : jb) {
		ii = jl.getLocation().x;
		i = jl.getLocation().y;
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jl.setIcon(new ImageIcon("x.jpg"));
				if(!t1.verificarGanhador() && !t1.empate()) {
					t1.setLinha(i);
					t1.setColuna(ii);
					System.out.print("1");
					if(t1.verificarPosicao(i, ii)) {
						t1.jogar(i, ii, 'X');
			            tab.push(new Tabuleiro(t1));
			            System.out.print("1");
			            if (!t1.empate() && !t1.verificarGanhador()) {
							t1.setLinha(gerador.nextInt(3));
		                    t1.setColuna(gerador.nextInt(3));
		                    System.out.print("1");
		                    for(JLabel j : jb) {
		                    	System.out.print(j.getLocation().x);
		                    	j.getLocation();
		    					if((j.getLocation().x == t1.getLinha()) && (j.getLocation().y == t1.getColuna())) {
		    						j.setIcon(new ImageIcon("o.jpg"));
		    						System.out.print("entrou");
		    					}
		                    }
		                    if(t1.verificarPosicao(t1.getLinha(), t1.getColuna())) {
								t1.jogar(t1.getLinha(), t1.getColuna(), 'O');
				                tab.push(new Tabuleiro(t1));
				                System.out.print("1");
							}
							t1.desenhar();
						}
					}
				}

				t1.desenhar();
			}
		});
	}
}*/

