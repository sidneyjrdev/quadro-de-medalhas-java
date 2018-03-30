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

public class TelaQuadro extends JFrame implements ActionListener {  

	private static final long serialVersionUID = 1L;
	private JTextField txtCaixaPesquisa = new JTextField(20);
	private JButton btnLogar = new JButton("LOGAR");
	
	private ImageIcon imgMedalha = new ImageIcon(getClass().getResource("/imagens/medalha.png"));
	private JButton btnEditarMedalhas = new JButton(imgMedalha);
	
	private JButton btnVoltar = new JButton("Voltar");
	
	private ImageIcon imgChave = new ImageIcon(getClass().getResource("/imagens/chave.png"));
	private JButton btnTrocarSenha = new JButton(imgChave);

	private ImageIcon imgLogout = new ImageIcon(getClass().getResource("/imagens/logout.png"));
	private JButton btnLogout = new JButton(imgLogout);

	private ImageIcon imgLupa = new ImageIcon(getClass().getResource("/imagens/lupa.png"));
	private JButton btnLupa = new JButton(imgLupa);

	private ImageIcon imgMais = new ImageIcon(getClass().getResource("/imagens/mais.png"));
	private JButton btnAdicionarPais = new JButton(imgMais);

	private ImageIcon imgMenos = new ImageIcon(getClass().getResource("/imagens/menos.png"));
	private JButton btnRemoverPais = new JButton(imgMenos);

	private ImageIcon imgAlterar = new ImageIcon(getClass().getResource("/imagens/alterar.png"));
	private JButton btnAlterarPais = new JButton(imgAlterar);

	private ImageIcon imgGerenciar = new ImageIcon(getClass().getResource("/imagens/gerenciar.png"));
	private JButton btnGerenciarUsuario = new JButton(imgGerenciar);

	private JTable tabela = new JTable();

	DefaultTableModel model;
	
	String loginOperador;

	
	
	public TelaQuadro(String loginOp, boolean ehAdm){
	
	loginOperador = loginOp;
	
	setLayout(new BorderLayout());
    setTitle("Quadro de Medalhas RIO 2016 - Aluno Sidney");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(800,600);
	setLocationRelativeTo(null);
	
	
	JPanel botoes = new JPanel();
	JPanel pesquisa = new JPanel();
	
	btnVoltar.setVisible(false);
	pesquisa.add(btnVoltar);
	pesquisa.add(txtCaixaPesquisa);
	pesquisa.add(btnLupa);
	
	botoes.add(pesquisa);
	
	if(loginOperador != null){
	
	botoes.add(btnEditarMedalhas);
	botoes.add(btnTrocarSenha);
	botoes.add(btnLogout);
	
	}
	else if(ehAdm){
	
	botoes.add(btnAdicionarPais);
	botoes.add(btnRemoverPais);
	botoes.add(btnAlterarPais);
	botoes.add(btnGerenciarUsuario);
	botoes.add(btnLogout);
	
	}
	
	else{
	botoes.add(btnLogar);
	}
	
	add(botoes, BorderLayout.NORTH);
	
	btnLogar.addActionListener(this);
	
	btnEditarMedalhas.addActionListener(this);
	btnEditarMedalhas.setToolTipText("Editar Medalhas");
	
	btnTrocarSenha.addActionListener(this);
	btnTrocarSenha.setToolTipText("Trocar Senha");

	btnLogout.addActionListener(this);
	btnLogout.setToolTipText("Logout");
	btnLupa.addActionListener(this);
	btnLupa.setToolTipText("Pesquisar");
	
	btnAdicionarPais.addActionListener(this);
	btnAdicionarPais.setToolTipText("Adicionar País");
	btnAlterarPais.addActionListener(this);
	btnAlterarPais.setToolTipText("Alterar País");
	btnRemoverPais.addActionListener(this);
	btnRemoverPais.setToolTipText("Remover País");
	btnGerenciarUsuario.addActionListener(this);
	btnGerenciarUsuario.setToolTipText("Gerenciamento de Usuário");
	btnVoltar.addActionListener(this);
	btnVoltar.setToolTipText("Todos os registros");
	
	String[] colunas = new String[]{"Rank", "Sigla", "Nome", "Ouro", "Prata", "Bronze", "Total"};
	 
	String[][] dados = new String[][]{
	    
	};
	 

	model = new DefaultTableModel(dados,colunas);
	tabela.setModel(model);
	JScrollPane scroll = new JScrollPane();
	scroll.setViewportView(tabela);
	add(scroll);
	
	listarQuadro(null);
	
}	
	
	public void listarQuadro(String parte) {

		Quadro quadro = new Quadro();
		List<Pais> l = new LinkedList<>();
		

			if (parte == null || parte.equals(""))
				l = quadro.obterQuadro();
		
			else{
				l = quadro.filtrar(parte);
				limparTela();
				btnVoltar.setVisible(true);
			 	}
			
			if(l != null){
			java.util.Iterator<Pais> itr = l.iterator();
			while (itr.hasNext()) {
				Pais p = itr.next();

				String PosicaoString = p.posicao + "";
				String OuroString = p.ouro + "";
				String PrataString = p.prata + "";
				String BronzeString = p.bronze + "";
				String TotalString = p.totalMedalhas() + "";
				String[] v = { PosicaoString, p.sigla, p.nome, OuroString,
						PrataString, BronzeString, TotalString };
				model.addRow(v);
			
			}
		}
			setVisible(true);

	}
	
	public void limparTela(){
		model.setRowCount(0);
	}

    

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == btnLupa) {
			
			String parte = txtCaixaPesquisa.getText();
			if(parte.equals("")){
				JOptionPane.showMessageDialog(null, "caixa de texto vazia");
			}
			else{
				setVisible(false);
				listarQuadro(parte);
				
			}

		}
		
		if (e.getSource() == btnVoltar) {
			
			limparTela();
			txtCaixaPesquisa.setText("");
			btnVoltar.setVisible(false);
			setVisible(false);
			listarQuadro(null);
		

		}

		if (e.getSource() == btnLogar) {
			
			TelaLogin tela = new TelaLogin();
			setVisible(false);
			tela.setVisible(true);
		
		}

		if (e.getSource() == btnEditarMedalhas) {
			try{
			int linha = tabela.getSelectedRow();
			String nome = (String) tabela.getValueAt(linha, 1);
			setVisible(false);
			TelaAlterarMedalhas tela = new TelaAlterarMedalhas(loginOperador, nome);
			tela.setVisible(true);
			
			}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, "Selecione uma linha do quadro");
			}

		}

		if (e.getSource() == btnTrocarSenha) {
			
			TelaTrocarSenha tela = new TelaTrocarSenha(loginOperador);
			tela.setVisible(true);
		
		}

		if (e.getSource() == btnLogout) {
			
			TelaQuadro tela = new TelaQuadro(null, false);
			setVisible(false);
			tela.setVisible(true);
			
		}

		if (e.getSource() == btnRemoverPais) {

			try{
				int linha = tabela.getSelectedRow();
				String sigla = (String) tabela.getValueAt(linha, 1);
				Quadro q = new Quadro();
				q.remover(sigla);
				
				TelaQuadro tela = new TelaQuadro(null, true);
				setVisible(false);
				tela.setVisible(true);
			
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Selecione uma linha do quadro");
			}
		}

		if (e.getSource() == btnAdicionarPais) {
			
			TelaAdicionarPais tela = new TelaAdicionarPais();
			setVisible(false);
			tela.setVisible(true);
		
		}

		if (e.getSource() == btnAlterarPais) {

			try{
				int linha = tabela.getSelectedRow();
				String sigla = (String) tabela.getValueAt(linha, 1);
				
				TelaAlterarPais tela = new TelaAlterarPais(sigla);
				setVisible(false);
				tela.setVisible(true);
				
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Selecione uma linha do quadro");
				}
		}

		if (e.getSource() == btnGerenciarUsuario) {
			
			TelaGerenciarUsuario tela = new TelaGerenciarUsuario();
			setVisible(false);
			tela.setVisible(true);
		}
	}

	static public void main(String args[]) {
		
		TelaQuadro tela = new TelaQuadro(null, false);
		tela.setVisible(true);

	}

}
