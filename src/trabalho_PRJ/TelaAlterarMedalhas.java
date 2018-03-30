package trabalho_PRJ;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class TelaAlterarMedalhas extends JFrame implements ActionListener{
	Pais p = new Pais();
	Quadro q = new Quadro();

	private JLabel lblSiglaPais;
	private JLabel lblNomePais;
	private JLabel lblOuro = new JLabel("OUROS:");
	private JLabel lblPrata = new JLabel("PRATAS:");
	private JLabel lblBronze = new JLabel("BRONZES:");
	
	

	private JButton btnOk = new JButton("OK");
	private JButton btnDesfazer = new JButton("Desfazer");
	private JButton btnCancelar = new JButton("Cancelar");

	int valor, min = 0, max = 300, passo = 1, ouroInicial, prataInicial, bronzeInicial;
	
	String loginOperador, sigla;

	private JSpinner inserirOuro;
	private JSpinner inserirPrata;
	private JSpinner inserirBronze;

	public TelaAlterarMedalhas(String loginOp, String sigla) {
		this.sigla = sigla;
		loginOperador = loginOp;
		p = q.obterPais(sigla);
		
		setLayout(new BorderLayout());
		JPanel painelPais = new JPanel();
		JPanel resto = new JPanel(new GridLayout(3, 3, 3, 5));
		
		setTitle("Edição de Medalhas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);

		lblSiglaPais = new JLabel(sigla + " - ");
		painelPais.add(lblSiglaPais);
		
		lblNomePais = new JLabel(p.getNome());
		painelPais.add(lblNomePais);
		
		add(painelPais, BorderLayout.NORTH);

		

		p = q.obterPais(sigla);
		
		ouroInicial = p.getOuro();
		prataInicial = p.getPrata();
		bronzeInicial = p.getBronze();

		inserirOuro = new JSpinner(new SpinnerNumberModel(ouroInicial, min,
				max, passo));
		inserirPrata = new JSpinner(new SpinnerNumberModel(prataInicial, min,
				max, passo));
		inserirBronze = new JSpinner(new SpinnerNumberModel(bronzeInicial, min,
				max, passo));

		resto.add(lblOuro);
		resto.add(lblPrata);
		resto.add(lblBronze);
		
		resto.add(inserirOuro);
		resto.add(inserirPrata);
		resto.add(inserirBronze);
		
		
		btnOk.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnDesfazer.addActionListener(this);
		
		inserirOuro.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent ec) {
				p.setOuro((int)(inserirOuro.getValue()));

			}
		});

		inserirPrata.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent ec) {
				p.setPrata((int)(inserirPrata.getValue()));

			}
		});

		inserirBronze.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent ec) {
				p.setBronze((int)(inserirBronze.getValue()));

			}
		});

		

		resto.add(btnOk);
		resto.add(btnDesfazer);
		resto.add(btnCancelar);

		add(resto);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			
			q.alterar(sigla, p);
			TelaQuadro tela = new TelaQuadro(loginOperador, false);
			tela.setVisible(true);
			setVisible(false);
						
		}

		if (e.getSource() == btnDesfazer) {
			
			inserirOuro.getModel().setValue(ouroInicial);
			inserirPrata.getModel().setValue(prataInicial);
			inserirBronze.getModel().setValue(bronzeInicial);
			
		}

		if (e.getSource() == btnCancelar) {
			TelaQuadro tela = new TelaQuadro(loginOperador, false);
			tela.setVisible(true);
			setVisible(false);

		}

	}

	
	
	
}
