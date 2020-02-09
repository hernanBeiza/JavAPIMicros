package cl.hiperactivo.javapi.notaserviceapplication.service.builder;

import cl.hiperactivo.javapi.notaserviceapplication.repository.model.Nota;

public class VOBuilderFactory {

    public VOBuilderFactory() {
    }

    public static NotaVOBuilder getNotaVOBuilder(Nota nota) {
        return new NotaVOBuilder().fromNota(nota);
    }

}
