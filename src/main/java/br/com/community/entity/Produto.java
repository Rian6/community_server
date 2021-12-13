package br.com.community.entity;

/**
 *
 * @author rian
 */
import br.com.community.enums.ProdutoStatusEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "codigo_barra")
    private String codigoBarra;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "status")
    private ProdutoStatusEnum status;

    @JoinColumn(name = "id_produto_imagem", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_produto_produto_imagem"))
    @ManyToOne(fetch = FetchType.LAZY)
    private ProdutoImagem produtoImagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ProdutoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProdutoStatusEnum status) {
        this.status = status;
    }

    public ProdutoImagem getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(ProdutoImagem produtoImagem) {
        this.produtoImagem = produtoImagem;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
