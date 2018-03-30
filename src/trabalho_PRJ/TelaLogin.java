package trabalho_PRJ;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JLabel lblLogin = new JLabel("LOGIN:");
	private JLabel lblSenha = new JLabel("SENHA:");
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JTextField txtLogin = new JTextField();
	private JPasswordField txtSenha = new JPasswordField();
	
	
	public TelaLogin(){
	
		    setLayout(new BorderLayout());
			JPanel botoes = new JPanel();
			JPanel resto = new JPanel(new GridLayout(2, 2, 2, 4));
		
			setTitle("Login");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(350, 140);
			setLocationRelativeTo(null);
			
			btnOk.setPreferredSize(new Dimension(100,25));
			btnCancelar.setPreferredSize(new Dimension(100,25));
		
			botoes.add(btnOk);
			btnOk.addActionListener(this);
			
			botoes.add(btnCancelar);
			btnCancelar.addActionListener(this);

			add(botoes, BorderLayout.SOUTH);

			resto.add(lblLogin);
			resto.add(txtLogin);

			resto.add(lblSenha);
			resto.add(txtSenha);

			add(resto);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOk){
				Login login = new Login();
				String senha = new String(txtSenha.getPassword());
				
				login.verificar(txtLogin.getText(), senha);
				setVisible(false);
			}
			if(e.getSource() == btnCancelar){
				
				TelaQuadro tela = new TelaQuadro(null, false);
				tela.setVisible(true);
				setVisible(false);
			}
			}

			
		
		
	}


