package br.edu.unisep.criptochangelle.dao.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.unisep.criptochangelle.dao.usuario.mapper.UsuarioMapper;
import br.edu.unisep.criptochangelle.model.usuario.Usuario;

/**
 * Descrever o motivo da classe ter sido criada.
 *
 * @author Felipe Zanella
 * @since 1.0 (12/04/18)
 */
@Repository
public class UsuarioDAO {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    JdbcTemplate jdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Usuario> findAll() {

        String sql = "SELECT id_usuario, ds_login, ds_password FROM usuario";

        return namedParameterJdbcTemplate.query(sql, new UsuarioMapper());
    }

    public Usuario findById(Long idUsuario) {

        Map<String, Object> params = new HashMap<>();
        params.put("id_usuario", idUsuario);

        String sql = "SELECT id_usuario, ds_login, ds_password FROM usuario WHERE id_usuario = :id_usuario";
        return namedParameterJdbcTemplate.queryForObject(sql, params, new UsuarioMapper());
    }

    public Usuario insert(Usuario usuario) {

        Map<String, Object> params = new HashMap<>();
        params.put("ds_login", usuario.getDsLogin());
        params.put("ds_password", usuario.getDsPassword());

        String sql = "INSERT INTO usuario (ds_login, ds_password) VALUES (:ds_login, :ds_password)";

        Long idUsuario = (long) namedParameterJdbcTemplate.update(sql, params);

        usuario.setIdUsuario(idUsuario);

        return usuario;
    }

    public Usuario update(Usuario usuario) {

        Map<String, Object> params = new HashMap<>();
        params.put("id_usuario", usuario.getIdUsuario());
        params.put("ds_login", usuario.getDsLogin());
        params.put("ds_password", usuario.getDsPassword());

        String sql = "UPDATE usuario SET ds_login = :ds_login, ds_password = :ds_password WHERE id_usuario = :id_usuario";

        Long idUsuario = (long) namedParameterJdbcTemplate.update(sql, params);

        usuario.setIdUsuario(idUsuario);

        return usuario;
    }

    public void delete(Long idUsuario) {

        Map<String, Object> params = new HashMap<>();
        params.put("id_usuario", idUsuario);

        String sql = "DELETE FROM usuario WHERE id_usuario = :id_usuario";

        namedParameterJdbcTemplate.update(sql, params);
    }

    public Usuario findByDsLogin(String dsLogin) {

        Map<String, Object> params = new HashMap<>();
        params.put("ds_login", dsLogin);

        String sql = "SELECT id_usuario, ds_login, ds_password FROM usuario WHERE ds_login = :ds_login";

        Usuario usuario = null;
        try {
            usuario = namedParameterJdbcTemplate.queryForObject(sql, params, new UsuarioMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

        return usuario;
    }
}
