package trabalho_PRJ;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaGerenciarUsuario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtPesquisa = new JTextField(20);

	private JButton btnResetarSenha = new JButton("***");
	private JButton btnVoltar = new JButton("Voltar");

	private ImageIcon imgMedalha = new ImageIcon(getClass().getResource(
			"/imagens/medalha.png"));
	private JButton btnMedalha = new JButton(imgMedalha);

	private ImageIcon imgLupa = new ImageIcon(getClass().getResource(
			"/imagens/lupa.png"));
	private JButton btnLupa = new JButton(imgLupa);

	private ImageIcon imgMais = new ImageIcon(getClass().getResource(
			"/imagens/mais.png"));
	private JButton btnAdicionarUsuario = new JButton(imgMais);

	private ImageIcon imgMenos = new ImageIcon(getClass().getResource(
			"/imagens/menos.png"));
	private JButton btnRemoverUsuario = new JButton(imgMenos);

	private ImageIcon imgAlterar = new ImageIcon(getClass().getResource(
			"/imagens/alterar.png"));
	private JButton btnAlterarUsuario = new JButton(imgAlterar);

	private JTable tabela = new JTable();

	DefaultTableModel model;

	public TelaGerenciarUsuario() {

		setLayout(new BorderLayout());
		setTitle("Painel do administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		JPanel botoes = new JPanel();
		JPanel pesquisa = new JPanel();

		btnVoltar.setVisible(false);
		pesquisa.add(btnVoltar);
		pesquisa.add(txtPesquisa);
		pesquisa.add(btnLupa);

		botoes.add(pesquisa);

		botoes.add(btnAdicionarUsuario);
		botoes.add(btnRemoverUsuario);
		botoes.add(btnAlterarUsuario);
		botoes.add(btnResetarSenha);
		botoes.add(btnMedalha);

		add(botoes, BorderLayout.NORTH);

		btnMedalha.addActionListener(this);
		btnMedalha.setToolTipText("Voltar para o quadro");
		btnLupa.addActionListener(this);
		btnLupa.setToolTipText("Pesquisar");
		btnAdicionarUsuario.addActionListener(this);
		btnAdicionarUsuario.setToolTipText("Adicionar Usuário");
		btnAlterarUsuario.addActionListener(this);
		btnAlterarUsuario.setToolTipText("Alterar Usuário");
		btnRemoverUsuario.addActionListener(this);
		btnRemoverUsuario.setToolTipText("Remover Usuário");
		btnResetarSenha.addActionListener(this);
		btnResetarSenha.setToolTipText("Resetar Senha");
		btnVoltar.addActionListener(this);
		btnVoltar.setToolTipText("Todos os registros");

		String[] colunas = new String[] { "Login", "Nome", "Tipo" };

		String[][] dados = new String[][] {};

		model = new DefaultTableModel(dados, colunas);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tabela);
		add(scroll);

		listarEquipe(null);
	}

	public void listarEquipe(String parte) {

		Equipe equipe = new Equipe();
		List<Usuario> l = new LinkedList<>();

		if (parte == null || parte.equals("")) {
			l = equipe.obterEquipe();

		} else {
			l = equipe.filtrar(parte);
			limparTela();
			btnVoltar.setVisible(true);

		}
		java.util.Iterator<Usuario> itr = l.iterator();
		while (itr.hasNext()) {
			Usuario u = itr.next();

			String LoginString = u.getLogin() + "";
			String NomeString = u.getNome() + "";
			String TipoString = u.getTipo() + "";

			String[] v = { LoginString, NomeString, TipoString };
			model.addRow(v);

		}
		setVisible(true);

	}

	public void limparTela() {
		model.setRowCount(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnLupa) {

			String parte = txtPesquisa.getText();
			if (parte.equals("")) {
				JOptionPane.showMessageDialog(null, "caixa de texto vazia");
			} else {
				setVisible(false);
				listarEquipe(parte);
			}

		}

		if (e.getSource() == btnVoltar) {

			limparTela();
			txtPesquisa.setText("");
			setVisible(false);
			listarEquipe(null);
			

		}

		if (e.getSource() == btnResetarSenha) {

			try {

				int linha = tabela.getSelectedRow();
				String login = (String) tabela.getValueAt(linha, 0);
				TelaTrocarSenha tela = new TelaTrocarSenha(login);
				tela.setVisible(true);

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Selecione uma linha da tabela");
			}

		}

		if (e.getSource() == btnMedalha) {
			TelaQuadro tela = new TelaQuadro(null, true);
			setVisible(false);
			tela.setVisible(true);
			
		}

		if (e.getSource() == btnRemoverUsuario) {
			try {
				Equipe eq = new Equipe();
				int linha = tabela.getSelectedRow();
				String login = (String) tabela.getValueAt(linha, 0);
				
				if (!(login.equals("primeiro"))) {
					eq.remover(login);
					setVisible(false);
					TelaGerenciarUsuario tela = new TelaGerenciarUsuario();
					tela.setVisible(true);
					
				}
				else
					JOptionPane.showMessageDialog(null,
							"Não é permitido remover o primeiro usuário");

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Selecione uma linha do quadro");
			}

		}

		if (e.getSource() == btnAdicionarUsuario) {
			setVisible(false);
			TelaAdicionarUsuario tela = new TelaAdicionarUsuario();
			tela.setVisible(true);
			

		}

		if (e.getSource() == btnAlterarUsuario) {
			try {
			
				int linha = tabela.getSelectedRow();
				String login = (String) tabela.getValueAt(linha, 0);
				setVisible(false);
				TelaAlterarUsuario tela = new TelaAlterarUsuario(login);
				tela.setVisible(true);
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Selecione uma linha da tabela");
			}

		}

	}

}
