package trabalho_PRJ;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAdicionarPais extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Pais p = new Pais();
	Quadro q = new Quadro();

	private JLabel lblSigla = new JLabel("SIGLA:");
	private JLabel lblNome = new JLabel("NOME:");
	private JLabel lblOuro = new JLabel("OUROS:");
	private JLabel lblPrata = new JLabel("PRATAS:");
	private JLabel lblBronze = new JLabel("BRONZES:");

	private JButton btnOk = new JButton("OK");
	private JButton btnDesfazer = new JButton("Desfazer");
	private JButton btnCancelar = new JButton("Cancelar");

	private JTextField txtSigla = new JTextField();
	private JTextField txtNome = new JTextField();
	private JTextField txtOuro = new JTextField();
	private JTextField txtPrata = new JTextField();
	private JTextField txtBronze = new JTextField();

	public TelaAdicionarPais() {
		setLayout(new BorderLayout());
		setTitle("Adicionar País");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 270);
		setLocationRelativeTo(null);

		JPanel botoes = new JPanel();
		JPanel resto = new JPanel(new GridLayout(5, 2, 3, 12));

		botoes.add(btnOk);
		btnOk.addActionListener(this);
		botoes.add(btnDesfazer);
		btnDesfazer.addActionListener(this);
		botoes.add(btnCancelar);
		btnCancelar.addActionListener(this);

		add(botoes, BorderLayout.SOUTH);

		resto.add(lblNome);
		resto.add(txtNome);

		resto.add(lblSigla);
		resto.add(txtSigla);

		resto.add(lblOuro);
		resto.add(txtOuro);

		resto.add(lblPrata);
		resto.add(txtPrata);

		resto.add(lblBronze);
		resto.add(txtBronze);

		add(resto);

	}

	@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnOk) {
				
				String siglaNova = txtSigla.getText();
				Pais condicao = q.obterPais(siglaNova);
				
				if(condicao == null && !(siglaNova.isEmpty())){
					p.setSigla(siglaNova);
					p.setNome(txtNome.getText());
					p.setOuro(Integer.parseInt(txtOuro.getText()));
					p.setPrata(Integer.parseInt(txtPrata.getText()));
					p.setBronze(Integer.parseInt(txtBronze.getText()));
				
					q.adicionar(p);
				
					TelaQuadro tela = new TelaQuadro(null, true);
					tela.setVisible(true);
					setVisible(false);
				}
				else if(condicao != null)
					JOptionPane.showMessageDialog(null, "Não é permitido adicionar um país com sigla já existente");
				else
					JOptionPane.showMessageDialog(null, "Não é permitido adicionar um país com sigla em branco");
					
			}
			
			if(e.getSource() == btnDesfazer){
				txtSigla.setText("");
				txtNome.setText("");
				txtOuro.setText("");
				txtPrata.setText("");
				txtBronze.setText("");
			}
			
			if (e.getSource() == btnCancelar) {
				
				TelaQuadro tela = new TelaQuadro(null, true);
				tela.setVisible(true);
				setVisible(false);
			}
			
		}
}
