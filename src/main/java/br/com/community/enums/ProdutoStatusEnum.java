package br.com.community.enums;
/**
 *
 * @author rian
 */
public enum ProdutoStatusEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String descricao;

    ProdutoStatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}