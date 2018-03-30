package trabalho_PRJ;

import javax.swing.JOptionPane;

public class Login {
	
	Usuario u = new Usuario();
	Equipe e = new Equipe();
	
	
	public Login(){     
		
		
	}
	
	public void verificar(String login, String senha){
		u = e.obterUsuario(login);
		
		if(u != null && u.getSenha().equals(senha)){
			
			if(u.getTipo().equals("Administrador")){
				TelaQuadro tela = new TelaQuadro(null, true);
				tela.setVisible(true);
			}
			else{
				TelaQuadro tela = new TelaQuadro(u.getLogin(), false);
				tela.setVisible(true);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Login e/ou senha incorreto. Por favor, tente novamente.");
			TelaLogin tela = new TelaLogin();
			tela.setVisible(true);
		}
		
		}
}
	

