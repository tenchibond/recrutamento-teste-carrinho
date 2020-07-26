/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ithappens.carrinho;

import java.math.BigDecimal;

/**
 *
 * @author Victor de Lima Alves
 */
public class Main {
	
	public static void main(String args[]) {
		
		System.out.println("Teste");
		
		CarrinhoComprasFactory carrinhoFactory = new CarrinhoComprasFactory();
		
		Produto arroz = new Produto(123l, "Arroz");
		Produto feijao = new Produto(456l, "Feijao");
		Produto carne = new Produto(789l, "Carne");
		
		CarrinhoCompras carrinhoVictor = carrinhoFactory.criar("victor");
		CarrinhoCompras carrinhoMonica = carrinhoFactory.criar("Monica");
		
		carrinhoVictor.adicionarItem(arroz, new BigDecimal("4.50"), 5);
		carrinhoVictor.adicionarItem(feijao, new BigDecimal("7.75"), 3);
		carrinhoVictor.adicionarItem(carne, new BigDecimal("11.28"), 5);
		System.out.println("Total carrinho Victor: " + carrinhoVictor.getValorTotal());

		carrinhoMonica.adicionarItem(feijao, new BigDecimal("7.75"), 6);
		carrinhoMonica.adicionarItem(carne, new BigDecimal("11.28"), 5);
		System.out.println("Total carrinho Monica: " + carrinhoMonica.getValorTotal());
		
		System.out.println("Valor do ticket médio: " + carrinhoFactory.getValorTicketMedio());
		
		carrinhoMonica.adicionarItem(carne, new BigDecimal("10.38"), 2);
		carrinhoMonica.removerItem(feijao);
		System.out.println("Total carrinho Monica: (apos remocao do feijao e alteracao da quant. e preco da carne) "
				+ carrinhoMonica.getValorTotal());
		
		carrinhoFactory.invalidar("victor");
		
		System.out.println("Valor do ticket médio: (apos alteracoes no carrinho Monica e remocao do carrinho Victor) "
				+ carrinhoFactory.getValorTicketMedio());
	}
}
