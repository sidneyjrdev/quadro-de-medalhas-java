package trabalho_PRJ;

import java.util.LinkedList;

public class Equipe{

	 private BancodeDados bd;
	 boolean EhAdm = false;

     public Equipe()
     {
         bd = new BancodeDados();
         
         
     }


     public void adicionar(Usuario u) {
    	
         bd.adicionarUsuario(u);
     }

     public void remover(String login) {
         bd.removerUsuario(login);;
         
         

     }

     public void alterar(String login, Usuario u) {
         bd.alterarUsuario(login, u);

     }
     
     public Usuario obterUsuario(String login){
    	 Usuario u = new Usuario();
    	 u = bd.obterUsuario(login);
    	 return u;
     }
     
     public LinkedList<Usuario> obterEquipe(){
     	LinkedList<Usuario> l = new LinkedList<>();
     	
     	l = bd.obterEquipe();
     	return l;
     }
     
     
     public LinkedList<Usuario> filtrar(String parte){
 		Equipe e = new Equipe();
 		LinkedList<Usuario> original = e.obterEquipe();
 		LinkedList<Usuario> filtrada = new LinkedList<>();
 		
 		java.util.Iterator<Usuario> itr = original.iterator();
 	     while(itr.hasNext()) {
 	        Usuario u = itr.next();
 		if(u.getLogin().contains(parte) || u.getNome().contains(parte) || u.getTipo().contains(parte)){
 			filtrada.add(u);
 		}
 	     }
 		return filtrada;
 	}
     
     public int obterQtd(){
     	int qtd = bd.obterQtdUsuarios();
     	return qtd;
     }
 	
 	public void trocarSenha(String login, String senha){
 		Usuario u = new Usuario();
 		Equipe e = new Equipe();
 		u = e.obterUsuario(login); 
 		
 		u.setSenha(senha);
 		bd.alterarUsuario(login, u);
 	}

	
}
