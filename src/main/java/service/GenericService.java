package service;

import model.BaseModel;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class GenericService<T extends BaseModel<PK>, PK extends Serializable> extends FactoryEntityManager implements Serializable {

    protected GenericService(){
    }

    private Class<T> getType(){
        return this.getTypeClass();
    }

    private Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T objeto) throws PersistenceException {
        try {
            getEntityManager();
            entityManager.getTransaction().begin();
            if (objeto.getPk() == null) {
                this.salvar(objeto);
            } else {
                objeto = this.editar(objeto);
            }
            flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException pex) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException();
        } finally {
            fecharConexao();
        }
        return objeto;
    }

    private void flush() {
        entityManager.flush();
    }

    private T editar(T objeto) {
        return (T) entityManager.merge(objeto);
    }

    private void salvar(T objeto) {
        entityManager.persist(objeto);
    }

    public void remover(T objeto) {
        this.remover(objeto.getPk());
    }

    private void remover(PK pk) throws PersistenceException {
        try {
            getEntityManager().getTransaction().begin();
            BaseModel<PK> t = (T) entityManager.find(this.getType(), pk);
            entityManager.remove(t);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pex) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException();
        } finally {
            fecharConexao();
        }
    }

}
