package cl.hiperactivo.javapi.unificado.utils.security;

public enum UnificadoAccessLevel {

    CLIENT_ADMIN,CLOUD_ADMIN,CLOUD_USER;
    //Because why not
    public final static String ROLE_CLIENT_ADMIN="ROLE_CLIENT_ADMIN";
    public final static String ROLE_CLOUD_ADMIN="ROLE_CLOUD_ADMIN";
    public final static String ROLE_CLOUD_USER="ROLE_CLOUD_USER";


    public final String getAsRole(){
        return "ROLE_"+this.name();
    }
}
