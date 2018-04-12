package br.edu.unisep.criptochangelle.dao.usuario.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import br.edu.unisep.criptochangelle.model.usuario.Usuario;

/**
 * Descrever o motivo da classe ter sido criada.
 *
 * @author Felipe Zanella
 * @since 1.0 (12/04/18)
 */
public class UsuarioMapper implements RowMapper<Usuario> {

    @Nullable
    @Override
    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(resultSet.getLong("id_usuario"));
        usuario.setDsLogin(resultSet.getString("ds_login"));
        usuario.setDsPassword(resultSet.getString("ds_password"));

        return usuario;
    }
}
