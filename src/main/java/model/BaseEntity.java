package model;

import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long pk;

    @Override
    public Long getPk(){
       return pk;
    }

    @Override
    public boolean isNew(){
        return pk == null;
    }

}
