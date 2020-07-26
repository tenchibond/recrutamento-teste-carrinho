package br.com.ithappens.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {
	
	private List<Item> lstItensCarrinho;
	private String identificadorCarrinho;
	
	public CarrinhoCompras(String identificadorCarrinho) {
		this.identificadorCarrinho = identificadorCarrinho;
		lstItensCarrinho = new ArrayList<>();
	}

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		Item item = lstItensCarrinho.stream()
				.filter(i -> i.getProduto().getCodigo() == produto.getCodigo())
				.findAny()
				.orElse(null);
		if(item != null) {
			int novaQtd = item.getQuantidade() + quantidade;
			BigDecimal novoValor = (item.getValorUnitario() != valorUnitario) ? valorUnitario : item.getValorUnitario();
			lstItensCarrinho.remove(item);
			lstItensCarrinho.add(new Item(produto, novoValor, novaQtd));
		} else {
			lstItensCarrinho.add(new Item(produto, valorUnitario, quantidade));
		}
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
		if(produto != null) {
			return lstItensCarrinho.removeIf(i -> i.getProduto().getCodigo() == produto.getCodigo());
		}
		return false;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
		Item removido = lstItensCarrinho.remove(posicaoItem);
		if(removido != null) {
			return true;
		}
		return false;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
		return lstItensCarrinho.stream()
				.map(x -> x.getValorTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
		return lstItensCarrinho;
    }
	
	/**
	 * Retorna o identificador do carrinho de compras.
	 * 
	 * @return String
	 */
	public String getIdentificadorCarrinho() {
		return this.identificadorCarrinho;
	}
}