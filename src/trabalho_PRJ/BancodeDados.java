package trabalho_PRJ;
import java.util.*;
import java.sql.*;

import javax.swing.JOptionPane;



public class BancodeDados
{

  private static final String CONEXAO="jdbc:sqlite:";
  private static final String NOME_BANCO="rio2016.bd";
  public BancodeDados()
  {
    java.io.File f = new java.io.File(NOME_BANCO);
    if (!f.exists())
      criarBanco();
  }

 
  public void criarBanco()
  {
      try{
            
            Class.forName("org.sqlite.JDBC");
           
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
            
            Statement stm = conn.createStatement();
            Statement sta = conn.createStatement();
            

            
            String comandoStm =
            "CREATE TABLE paises" +
             "(sigla varchar(3) NOT NULL primary key, "+
              "nome varchar(30), "+
              "ouro int," +
              " prata int," +
               " bronze int)";
                             
            stm.executeUpdate(comandoStm);
            
           
            String comandoSta = 
            "CREATE TABLE usuarios" +
             "(nome varchar(30), " +
             "login varchar(30) NOT NULL primary key, "+
             "senha varchar(30)," +
             "tipo varchar(15))";
                                     
            
            sta.executeUpdate(comandoSta);
            
            PreparedStatement pst = conn.prepareStatement("insert into usuarios (nome, login, senha, tipo) values (?,?,?,?)");
            pst.setString(1, "primeiro");
            pst.setString(2, "primeiro");
            pst.setString(3, "primeiro");
            pst.setString(4, "Administrador");
            
            pst.executeUpdate();
            
            conn.close();
            
            String mensagem = "Anote seus dados de primeiro acesso:" + "\n" + "\n" + "\n" +
            				  "Login: primeiro" + "\n" +
            				  "Senha: primeiro";
            
            JOptionPane.showMessageDialog(null, mensagem);
           
            
        } catch(Exception e){
            e.printStackTrace();
        }
  }
  
  public void adicionarPais(Pais p)
  {
      try{
            
            Class.forName("org.sqlite.JDBC");
           
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
            
            PreparedStatement stm = conn.prepareStatement("insert into paises (sigla, nome, ouro, prata, bronze) values (?,?,?,?,?)");

            stm.setString(1, p.getSigla());
            stm.setString(2, p.getNome());
            stm.setInt(3, p.getOuro());
            stm.setInt(4, p.getPrata());
            stm.setInt(5,  p.getBronze());
            stm.executeUpdate();
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
      
  }

  public void alterarPais(String sigla, Pais p)
  {
      try{
          
            Class.forName("org.sqlite.JDBC");
           
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
            
            PreparedStatement stm = conn.prepareStatement("update paises set sigla=?, nome=?, ouro=?, prata=?, bronze=? where sigla=?");
            

            stm.setString(1, p.sigla);
            stm.setString(2, p.nome);
            stm.setInt(3, p.ouro);
            stm.setInt(4, p.prata);
            stm.setInt(5, p.bronze);
            stm.setString(6, sigla);
            stm.executeUpdate();
            conn.close();
        } catch(Exception e){
            e.printStackTrace();   
        }
  }

  public void removerPais(String sigla)
  {
      try{
            
            Class.forName("org.sqlite.JDBC");
          
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
           
            PreparedStatement stm = conn.prepareStatement("delete from paises where sigla=?");
            stm.setString(1, sigla);
            stm.executeUpdate();
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
  }

 
  public Pais obterPais(String sigla)
  {
      Pais p= null;
      try{
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
           
            PreparedStatement stm = conn.prepareStatement("select sigla, nome, ouro, prata, bronze from paises where sigla=?");
            stm.setString(1, sigla);
            ResultSet r = stm.executeQuery();
            if (r.next())
            {
              p = new Pais();
              p.setSigla(r.getString("sigla"));
              p.setNome(r.getString("nome"));
              p.setOuro(r.getInt("ouro"));
              p.setPrata(r.getInt("prata"));
              p.setBronze(r.getInt("bronze"));
              
              
            }
            r.close();
            conn.close();
            return p;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
  }

  public LinkedList<Pais> obterQuadro()
  {
	  LinkedList<Pais> lista = new LinkedList<Pais>();
      try{
           
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
            
            Statement stm = conn.createStatement();
            ResultSet r = stm.executeQuery("select sigla, nome, ouro, prata, bronze from paises");
            while (r.next())
            {
              Pais p = new Pais();
              p.setSigla(r.getString(1));
              p.setNome(r.getString(2));
              p.setOuro(r.getInt(3));
              p.setPrata(r.getInt(4));
              p.setBronze(r.getInt(5));
              lista.add(p);
            }
            r.close();
            conn.close();
            return lista;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
  }
  
  public int obterQtdPaises(){
      
      try{
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
           
            PreparedStatement stm = conn.prepareStatement("select count(*) from paises");
            
            ResultSet r = stm.executeQuery();
            conn.close();
            return r.getInt(1);
            
           
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        }
  }
  
  public void adicionarUsuario(Usuario u){
	  try{
          
          Class.forName("org.sqlite.JDBC");
         
          Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
          
          PreparedStatement stm = conn.prepareStatement("insert into usuarios (nome, login, senha, tipo) values (?,?,?,?)");
          
          stm.setString(1, u.getNome());
          stm.setString(2, u.getLogin());
          stm.setString(3, u.getSenha());
          stm.setString(4, u.getTipo());
          
          stm.executeUpdate();
         
          conn.close();
      } catch(Exception e){
          e.printStackTrace();
      }
  }
  
  public void alterarUsuario(String login,Usuario u){
	  try{
          
          Class.forName("org.sqlite.JDBC");
         
          Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
          
          PreparedStatement stm = conn.prepareStatement("update usuarios set nome=?, login=?, senha=?, tipo=? where login=?");

          stm.setString(1, u.nome);
          stm.setString(2, u.login);
          stm.setString(3, u.senha);
          stm.setString(4, u.tipo);
          stm.setString(5, login);
          
          stm.executeUpdate();
          
          conn.close();
      } catch(Exception e){
          e.printStackTrace();   
      }
  }
  
  public void removerUsuario(String login){
	 
	      try{
	            
	            Class.forName("org.sqlite.JDBC");
	          
	            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
	           
	            PreparedStatement stm = conn.prepareStatement("delete from usuarios where login=?");
	            stm.setString(1, login);
	            stm.executeUpdate();
	            conn.close();
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	  }

  public Usuario obterUsuario(String login)
  {
      Usuario u = null;
      try{
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
           
            PreparedStatement stm = conn.prepareStatement("select nome, login, senha, tipo from usuarios where login=?");
            stm.setString(1, login);
            ResultSet r = stm.executeQuery();
            if (r.next())
            {
              u = new Usuario();
              u.setNome(r.getString("nome"));
              u.setLogin(r.getString("login"));
              u.setSenha(r.getString("senha"));
              u.setTipo(r.getString("tipo"));
              
              
              
            }
            r.close();
            conn.close();
            return u;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
  }
  
  
  public LinkedList<Usuario> obterEquipe()
  {
	  LinkedList<Usuario> lista = new LinkedList<Usuario>();
      try{
           
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
            
            Statement stm = conn.createStatement();
            ResultSet r = stm.executeQuery("select login, nome, tipo from usuarios order by login");
            while (r.next())
            {
              Usuario u = new Usuario();
              u.setLogin(r.getString(1));
              u.setNome(r.getString(2));
              u.setTipo(r.getString(3));
              
              lista.add(u);
            }
            r.close();
            conn.close();
            return lista;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
  }

public int obterQtdUsuarios(){
      
      try{
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(CONEXAO + NOME_BANCO);
           
            PreparedStatement stm = conn.prepareStatement("select count(*) from usuarios");
            
            ResultSet r = stm.executeQuery();
            conn.close();
            return r.getInt(1);
            
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        }
  }
  
  
   public static void main(String s[])
  {
  
	@SuppressWarnings("unused")
	BancodeDados bd = new BancodeDados();
    
  } 
}
