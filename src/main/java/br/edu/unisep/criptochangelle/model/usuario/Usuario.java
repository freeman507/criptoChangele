package br.edu.unisep.criptochangelle.model.usuario;

/**
 * Descrever o motivo da classe ter sido criada.
 *
 * @author Felipe Zanella
 * @since 1.0 (12/04/18)
 */
public class Usuario {

    private Long idUsuario;

    private String dsLogin;

    private String dsPassword;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDsLogin() {
        return dsLogin;
    }

    public void setDsLogin(String dsLogin) {
        this.dsLogin = dsLogin;
    }

    public String getDsPassword() {
        return dsPassword;
    }

    public void setDsPassword(String dsPassword) {
        this.dsPassword = dsPassword;
    }
}
