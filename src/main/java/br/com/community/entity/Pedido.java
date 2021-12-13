package br.com.community.entity;

/**
 *
 * @author rian
 */
import java.io.Serializable;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "id_vendedor", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pedido_usuario"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario vendedor;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pedido_pessoa"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
