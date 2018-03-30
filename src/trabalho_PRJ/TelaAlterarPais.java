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

public class TelaAlterarPais extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Pais p = new Pais();
	Quadro q = new Quadro();
	
	String nomeOriginal, siglaOriginal, sigla;
	private JLabel lblSigla = new JLabel("SIGLA:");
	private JLabel lblNome = new JLabel("NOME:");
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JTextField txtSigla;
	private JTextField txtNome;
	
	
	 public TelaAlterarPais(String sigla){
		this.sigla = sigla; 
		 
		setLayout(new BorderLayout());
		setTitle("Alterar País");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,200);
		setLocationRelativeTo(null);
		
		JPanel botoes = new JPanel();
		JPanel resto = new JPanel(new GridLayout(2, 2, 3, 5));
		
		p = q.obterPais(sigla);
		
		nomeOriginal = p.getNome();
		siglaOriginal = p.getSigla();
		
		txtSigla  = new JTextField(siglaOriginal);
		txtNome  = new JTextField(nomeOriginal);
		
		botoes.add(btnOk);
		btnOk.addActionListener(this);
		
		botoes.add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		add(botoes, BorderLayout.SOUTH);
		
		resto.add(lblNome);
	    resto.add(txtNome);
	    
	    resto.add(lblSigla);
	    resto.add(txtSigla);
	
		add(resto);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == btnOk) {
			
			String siglaNova = txtSigla.getText();
			Pais condicao = q.obterPais(siglaNova);
			
			if((condicao == null || siglaNova.equals(siglaOriginal)) && !(siglaNova.isEmpty())){
			p.setSigla(txtSigla.getText());
			p.setNome(txtNome.getText());
			
			q.alterar(sigla, p);
			TelaQuadro tela = new TelaQuadro(null, true);
			tela.setVisible(true);
			setVisible(false);
			}
			else if(condicao != null)
				JOptionPane.showMessageDialog(null, "Não é permitido alterar a sigla do país para uma já existente");
			else
				JOptionPane.showMessageDialog(null, "Não é permitido alterar a sigla do país para uma em branco");
		}
		
		
		
		if (e.getSource() == btnCancelar) {
			TelaQuadro tela = new TelaQuadro(null, true);
			tela.setVisible(true);
			setVisible(false);
		}
		
	}
	
}
