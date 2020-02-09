package cl.hiperactivo.javapi.notaserviceapplication.repository.impl;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import cl.hiperactivo.javapi.notaserviceapplication.repository.NotaDAO;
import cl.hiperactivo.javapi.notaserviceapplication.repository.model.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class NotaDAOImpl implements NotaDAO {

    private final EntityManager entityManager;

    @Autowired
    public NotaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Nota> obtener() {
        return this.entityManager.createQuery("from Nota", Nota.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Nota> obtenerConIdNota(Long idNota) {
        Nota nota = this.entityManager.find(Nota.class,idNota);
        return Optional.ofNullable(nota);
    }

    @Override
    @Transactional
    public List<Nota> obtenerConIdUsuario(Long idUsuario) {
        return this.entityManager.createQuery("from Nota n WHERE n.idUsuario="+idUsuario.toString(), Nota.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Nota> guardar(NotaVO notaVO) {
        Nota nota = new Nota();
        nota.setIdUsuario(notaVO.getIdUsuario());
        nota.setTitulo(notaVO.getTitulo());
        nota.setCuerpo(notaVO.getCuerpo());
        nota.setValid(Long.valueOf(1));
        try {
            this.entityManager.persist(nota);
        } catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return Optional.ofNullable(nota);
    }

    @Override
    @Transactional
    public Optional<Nota> editar(NotaVO notaVO) {
        Nota nota = new Nota();
        nota.setIdNota(notaVO.getIdNota());
        nota.setIdUsuario(notaVO.getIdUsuario());
        nota.setTitulo(notaVO.getTitulo());
        nota.setCuerpo(notaVO.getCuerpo());
        nota.setValid(notaVO.getValid());
        try {
            this.entityManager.merge(nota);
        } catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return Optional.ofNullable(nota);
    }

    @Override
    @Transactional
    public boolean eliminar(Long idNota) {
        Optional<Nota> nota = this.obtenerConIdNota(idNota);
        if(nota.isPresent()){
            try {
                this.entityManager.remove(nota.get());
                return true;
            } catch(Exception e){
                System.out.println(e.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }
}
