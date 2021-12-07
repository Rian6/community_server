package br.com.community.entity;

/**
 *
 * @author rian
 */
import java.io.Serializable;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEndereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "endereco")
    private String endereco;
        
    @Column(name = "numero")
    private String numero;
            
    @Column(name = "complemento")
    private String complemento;
    
    @Column(name = "cep")
    private String cep;
        
 /*   @JoinColumn(name = "id_cidade", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pessoa_endereco_cidade"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Cidade cidade;
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

 /*   public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
*/
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final PessoaEndereco other = (PessoaEndereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
