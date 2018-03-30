package trabalho_PRJ;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TelaAlterarUsuario extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Usuario u = new Usuario();
	Equipe eq = new Equipe();
	
	String nomeOriginal, loginOriginal, tipoOriginal, login;
	boolean ehAdm;
	
	private JLabel lblLogin = new JLabel("LOGIN:");
	private JLabel lblNome = new JLabel("NOME:");
	private JLabel lblTipo = new JLabel("TIPO:");
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JTextField txtLogin;
	private JTextField txtNome;
	
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton adm;
	private JRadioButton op;
	
	 public TelaAlterarUsuario(String login){
		this.login = login;
		setTitle("Alterar Usuário");
		setLayout(new BorderLayout());
		setSize(450, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel botoes = new JPanel();
		JPanel resto = new JPanel(new GridLayout(3, 2, 3, 5));
		JPanel radiobuttons = new JPanel();
		
		u= eq.obterUsuario(login);
		
		nomeOriginal = u.getNome();
		loginOriginal = u.getLogin();
		tipoOriginal = u.getTipo();
		
		if(tipoOriginal.equals("Administrador")){
			adm = new JRadioButton("Administrador", true);
			op = new JRadioButton("Operador");
			
		}else{
			adm = new JRadioButton("Administrador");
			op = new JRadioButton("Operador", true);
			
		}
		
		txtLogin = new JTextField(loginOriginal);
		txtNome = new JTextField(nomeOriginal);
		
		adm.addItemListener(new CheckListener());

		op.addItemListener(new CheckListener());

		bg.add(adm);
		radiobuttons.add(adm);
		
		if(!(login.equals("primeiro"))){
		bg.add(op);
		radiobuttons.add(op);
		}
		
		botoes.add(btnOk);
		btnOk.addActionListener(this);
		
		botoes.add(btnCancelar);
		btnCancelar.addActionListener(this);

		add(botoes, BorderLayout.SOUTH);

		
		resto.add(lblNome);
		resto.add(txtNome);

		resto.add(lblLogin);
		resto.add(txtLogin);

		

		resto.add(lblTipo);
		resto.add(radiobuttons);

		add(resto);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			
			String loginNovo = txtLogin.getText();
			Usuario condicao = eq.obterUsuario(loginNovo);
			
			if((condicao == null || loginNovo.equals(loginOriginal)) && !(loginNovo.isEmpty())){
				
				u.setLogin(txtLogin.getText());
				u.setNome(txtNome.getText());
				if (ehAdm)
					u.setTipo("Administrador");
				else 
					u.setTipo("Operador");
				
				eq.alterar(login, u);
			
				TelaGerenciarUsuario tela = new TelaGerenciarUsuario();
				tela.setVisible(true);
				setVisible(false);
			}
			
			else if(condicao != null)
				JOptionPane.showMessageDialog(null, "Não é permitido alterar o login do usuário para um já existente");
			else
				JOptionPane.showMessageDialog(null, "Não é permitido alterar o login do usuário para um em branco");

				
		}
		
		
		
		if (e.getSource() == btnCancelar) {
			
			TelaGerenciarUsuario tela = new TelaGerenciarUsuario();
			tela.setVisible(true);
			setVisible(false);

		}
		
	}
	
	public class CheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (adm.isSelected()) {
				ehAdm = true;
			} else {
				ehAdm = false;
			}

		}

	}
	
	
}
