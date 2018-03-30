package trabalho_PRJ;

import java.util.LinkedList;
import java.util.List;



    public class Quadro
    {
        
        private BancodeDados bd;

        public Quadro()
        {
            bd = new BancodeDados();
            
            
        }

       /* public void excluirBanco(){
        	bd.excluirBanco();
        }
        */

        public void adicionar(Pais p) {

            bd.adicionarPais(p);
        }

        public void remover(String sigla) {
            bd.removerPais(sigla);
            
            

        }

        public void alterar(String sigla, Pais p) {
            bd.alterarPais(sigla, p);

        }

        public LinkedList<Pais> filtrar(String parte){
    		Quadro q = new Quadro();
    		LinkedList<Pais> original = q.obterQuadro();
    		LinkedList<Pais> filtrada = new LinkedList<>();
    		
    		java.util.Iterator<Pais> itr = original.iterator();
    	     while(itr.hasNext()) {
    	        Pais p = itr.next();
    		if(p.getNome().contains(parte) || p.getSigla().contains(parte)){
    			filtrada.add(p);
    		}
    	     }
    		return filtrada;
    	}
        
        public int obterQtd(){
        	int qtd = bd.obterQtdPaises();
        	return qtd;
        }
        
        public void classificar(List<Pais> paises)
        {
            int qtd = paises.size(), contador = 1;
            Pais aux = new Pais(), p;
            java.util.Iterator<Pais> itr = paises.iterator();
            
            while(itr.hasNext()) {
            	
                p = itr.next();
                p.setPosicao(contador);
                contador++;
            }

            for (int i = qtd - 1; i >= 1; i--)
            {
                for (int j = 0; j < i; j++)
                {
                	
                    if (
                      (paises.get(j + 1).getOuro() > paises.get(j).getOuro()) ||
                      (paises.get(j + 1).getOuro() == paises.get(j).getOuro() && paises.get(j + 1).getPrata() > paises.get(j).getPrata()) ||
                      (paises.get(j + 1).getOuro() == paises.get(j).getOuro() && paises.get(j + 1).getPrata() == paises.get(j).getPrata() && paises.get(j + 1).getBronze() > paises.get(j).getBronze())
                        )
                    {
                    	
                        aux = paises.get(j);
                        paises.set(j, paises.get(j + 1));
                        paises.set(j + 1, aux);
                        paises.get(j + 1).setPosicao(j + 2);
                        paises.get(j).setPosicao(j + 1);

                    }

                    if (paises.get(j + 1).getOuro() == paises.get(j).getOuro() && paises.get(j + 1).getPrata() == paises.get(j).getPrata() && paises.get(j + 1).getBronze() == paises.get(j).getBronze())

                    {
                        
                        paises.get(j + 1).setPosicao(j + 1);
                        
                    }
                    
                }
            }
            
            java.util.Iterator<Pais> ite = paises.iterator();
            while(ite.hasNext()) {
               p = ite.next();
            alterar(p.getSigla(), p);}

        }
        
        public LinkedList<Pais> obterQuadro(){
        	LinkedList<Pais> l = new LinkedList<>();
        	l = bd.obterQuadro();
        	
        	if(l != null)
        	classificar(l);
        	
        	return l;
        }

        
        
        public Pais obterPais(String sigla){
        	Pais p = bd.obterPais(sigla);
        	return p;
        }

        

 

    }
        
   
   

