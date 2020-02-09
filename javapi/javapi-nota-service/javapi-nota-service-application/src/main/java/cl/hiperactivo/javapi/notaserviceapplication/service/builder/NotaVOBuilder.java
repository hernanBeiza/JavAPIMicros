package cl.hiperactivo.javapi.notaserviceapplication.service.builder;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import cl.hiperactivo.javapi.notaserviceapplication.repository.model.Nota;

public class NotaVOBuilder {

    private Nota nota;

    NotaVOBuilder(){ }

    public NotaVOBuilder fromNota(Nota nota) {
        this.nota = nota;
        return this;
    }

    public NotaVO build() {
        if (nota == null) {
            return null;
        }
        NotaVO notaVO = new NotaVO();
        notaVO.setIdNota(nota.getIdNota());
        notaVO.setTitulo(nota.getTitulo());
        notaVO.setCuerpo(nota.getCuerpo());
        notaVO.setValid(nota.getValid());
        return notaVO;
    }

}
