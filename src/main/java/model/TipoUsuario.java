package model;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class TipoUsuario extends BaseEntity {

    @Column
    private String descricao;

    @Column
    private boolean status;

}
