package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Usuario extends BaseEntity {

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String telefone;

    @ManyToOne
    private TipoUsuario tipousuario;

    @Column
    private Boolean status;


}
