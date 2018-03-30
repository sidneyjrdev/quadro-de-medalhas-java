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

public class TelaAdicionarUsuario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Usuario u = new Usuario();
	Equipe equipe = new Equipe();

	boolean ehAdm;

	private JLabel lblNome = new JLabel("NOME:");
	private JLabel lblLogin = new JLabel("LOGIN:");
	private JLabel lblSenha = new JLabel("SENHA:");
	private JLabel lblTipo = new JLabel("TIPO:");

	private JButton btnOk = new JButton("OK");
	private JButton btnDesfazer = new JButton("Desfazer");
	private JButton btnCancelar = new JButton("Cancelar");

	private ButtonGroup bg = new ButtonGroup();

	private JTextField txtNome = new JTextField();
	private JTextField txtLogin = new JTextField();
	private JTextField txtSenha = new JTextField();
	
	private JRadioButton btnAdministrador = new JRadioButton("administrador", true);
	private JRadioButton btnOperador = new JRadioButton("operador");

	public TelaAdicionarUsuario() {
		setLayout(new BorderLayout());
		JPanel botoes = new JPanel();
		JPanel resto = new JPanel(new GridLayout(4, 2, 3, 12));
		JPanel radiobuttons = new JPanel();

		btnAdministrador.addItemListener(new CheckListener());

		btnOperador.addItemListener(new CheckListener());

		bg.add(btnAdministrador);
		radiobuttons.add(btnAdministrador);
		bg.add(btnOperador);
		radiobuttons.add(btnOperador);

		botoes.add(btnOk);
		btnOk.addActionListener(this);
		botoes.add(btnDesfazer);
		btnDesfazer.addActionListener(this);
		botoes.add(btnCancelar);
		btnCancelar.addActionListener(this);

		add(botoes, BorderLayout.SOUTH);

		setTitle("Adicionar Usuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(430,250);		
		setLocationRelativeTo(null);

		resto.add(lblNome);
		resto.add(txtNome);

		resto.add(lblLogin);
		resto.add(txtLogin);

		resto.add(lblSenha);
		resto.add(txtSenha);

		resto.add(lblTipo);
		resto.add(radiobuttons);

		add(resto);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			
			Equipe eq = new Equipe();
			String loginNovo = txtLogin.getText();
			
			Usuario condicao = eq.obterUsuario(loginNovo);
			
			if(condicao == null && !(loginNovo.isEmpty())){
				
				u.setNome(txtNome.getText());
				u.setLogin(txtLogin.getText());
				u.setSenha(txtSenha.getText());
				if (ehAdm) 
					u.setTipo("Administrador");
				else 
					u.setTipo("Operador");
				
				
				equipe.adicionar(u);
				
				TelaGerenciarUsuario tela = new TelaGerenciarUsuario();
				setVisible(false);
				tela.setVisible(true);
				
			}
			else if(condicao != null)
				JOptionPane.showMessageDialog(null, "Não é permitido adicionar um usuário com login já existente");
			else
				JOptionPane.showMessageDialog(null, "Não é permitido adicionar um usuário com login em branco");
	
		}

		if (e.getSource() == btnDesfazer) {

			txtNome.setText("");
			txtLogin.setText("");
			txtSenha.setText("");
			
		}

		if (e.getSource() == btnCancelar) {
			
			TelaGerenciarUsuario tela = new TelaGerenciarUsuario();
			setVisible(false);
			tela.setVisible(true);
			

		}

	}

	public class CheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (btnAdministrador.isSelected()) {
				ehAdm = true;
			} else {
				ehAdm = false;
			}

		}

	}

}
