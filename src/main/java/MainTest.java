import model.TipoUsuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //Classe tosca para demostrar como funciona a percistencia com JPA

        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setDescricao("Primero tipo de usuario - 333");
        tipoUsuario.setStatus(true);

        TipoUsuario tipoUsuario2 = new TipoUsuario();
        tipoUsuario2.setPk(1L);
        tipoUsuario2.setDescricao("Segundo tipo de usuario, foi editado");
        tipoUsuario2.setStatus(true);

        System.out.println(tipoUsuario2);

        // utilizando o banco
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("br.com.codepampa_masterPU");

        // criando uma entityManager
        EntityManager em = emf.createEntityManager();

        // iniciando uma transacao
        em.getTransaction().begin();

        // persistindo uma entidade
        salvar(tipoUsuario2, em);

        // comitando
        em.getTransaction().commit();

        em.close();
        emf.close();

    }

    private static void salvar(TipoUsuario tipoUsuario2, EntityManager em) {
        if(tipoUsuario2.isNew()) {
            em.persist(tipoUsuario2);
        } else {
            em.merge(tipoUsuario2);
        }
    }

}
