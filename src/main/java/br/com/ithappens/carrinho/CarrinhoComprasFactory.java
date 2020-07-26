package br.com.ithappens.carrinho;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {
	
	private List<CarrinhoCompras> lstCarrinhos;

	public CarrinhoComprasFactory() {
		lstCarrinhos = new ArrayList<>();
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
		CarrinhoCompras carrinho = null;
		if(lstCarrinhos != null && identificacaoCliente != null){
			carrinho = lstCarrinhos.stream()
					.filter(c -> c.getIdentificadorCarrinho().equalsIgnoreCase(identificacaoCliente))
					.findAny()
					.orElse(null);
			if(carrinho == null) {
				carrinho = new CarrinhoCompras(identificacaoCliente);
				lstCarrinhos.add(carrinho);
			}
		}
		return carrinho;
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
		BigDecimal valorTotalCarrinhos = lstCarrinhos.stream()
				.map(x -> x.getValorTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal qtdCarrinhos = new BigDecimal(lstCarrinhos.size());
		BigDecimal valorTicketMedio = valorTotalCarrinhos.divide(qtdCarrinhos, 2,
				RoundingMode.HALF_EVEN);
		
		return valorTicketMedio;
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
		if (lstCarrinhos.removeIf(c -> c.getIdentificadorCarrinho().equalsIgnoreCase(identificacaoCliente))) {
			return true;
		}
		return false;
    }
}
