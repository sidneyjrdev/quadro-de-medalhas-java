package trabalho_PRJ;

public class Pais
{
    public String sigla, nome;
    public int ouro, prata, bronze, posicao;

    public Pais()
    {
        sigla = nome = "";
        ouro = prata = bronze = posicao = 0;
    }
    
    
    
    public String getSigla() {
		return sigla;
	}



	public void setSigla(String sigla) {
		this.sigla = sigla;
		
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getOuro() {
		return ouro;
	}



	public void setOuro(int ouro) {
		this.ouro = ouro;
	}



	public int getPrata() {
		return prata;
	}



	public void setPrata(int prata) {
		this.prata = prata;
	}



	public int getBronze() {
		return bronze;
	}



	public void setBronze(int bronze) {
		this.bronze = bronze;
	}

	

	public int getPosicao() {
		return posicao;
	}



	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}



	public int totalMedalhas() {
        return ouro + prata + bronze;
    }

    

    



}
