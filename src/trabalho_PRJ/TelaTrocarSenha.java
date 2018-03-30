package trabalho_PRJ;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class TelaTrocarSenha extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	JLabel lblPrimeiraSenha = new JLabel("Nova senha");
	JPasswordField txtPrimeiraSenha = new JPasswordField();
	
	JLabel lblSegundaSenha = new JLabel("Confirme a senha");
	JPasswordField txtSegundaSenha = new JPasswordField();
	
	JButton btnOk = new JButton("OK");
	JButton btnCancelar = new JButton("Cancelar");
	
	boolean gerenciaAdm;
	String loginUsuario;

	public TelaTrocarSenha(String loginUser) {
		
		loginUsuario = loginUser;
		
		
		setLayout(new BorderLayout());
		JPanel botoes = new JPanel();
		JPanel resto = new JPanel(new GridLayout(2,2));

		setTitle("Troca de senha");
		setSize(350, 140);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		btnOk.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		botoes.add(btnOk);
		botoes.add(btnCancelar);
		add(botoes, BorderLayout.SOUTH);
		
		resto.add(lblPrimeiraSenha);
		resto.add(txtPrimeiraSenha);
		
		resto.add(lblSegundaSenha);
		resto.add(txtSegundaSenha);
		
		add(resto);
	
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnOk) {
			char[] senha1 = txtPrimeiraSenha.getPassword();
			char[] senha2 = txtSegundaSenha.getPassword();
			String senhaNova = new String(senha1);
			
			if (Arrays.equals(senha1, senha2)){
				
				Equipe equipe = new Equipe();
				equipe.trocarSenha(loginUsuario, senhaNova);
				setVisible(false);
				
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Senhas não conferem. Por favor, tente novamente");
				
			}
			 
		}
		if (e.getSource() == btnCancelar) {
			 setVisible(false);

		}

	}

}
