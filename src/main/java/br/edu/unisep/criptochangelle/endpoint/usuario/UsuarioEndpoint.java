package br.edu.unisep.criptochangelle.endpoint.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.criptochangelle.dao.usuario.UsuarioDAO;
import br.edu.unisep.criptochangelle.model.usuario.Usuario;
import br.edu.unisep.criptochangelle.utils.criptografia.CriptoUtils;

/**
 * Descrever o motivo da classe ter sido criada.
 *
 * @author Felipe Zanella
 * @since 1.0 (12/04/18)
 */
@RestController
@RequestMapping(UsuarioEndpoint.CONTEXT_PATH)
public class UsuarioEndpoint {

    public static final String CONTEXT_PATH = "/api/v1/usuario";

    @Autowired
    private UsuarioDAO dao;

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {

        Usuario u = dao.findByDsLogin(usuario.getDsLogin());
        if(u != null) {
            String descriptografa = CriptoUtils.descriptografa(u.getDsPassword());

            if(descriptografa.equals(usuario.getDsPassword())) {
                return "sucesso";
            }
        }
        return "Falha";
    }

    @GetMapping
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @GetMapping("/{idUsaurio}")
    public Usuario findByIdUsuario(@PathVariable Long idUsuario) {
        return dao.findById(idUsuario);
    }

    @PostMapping
    public Long insert(@RequestBody Usuario usuario) throws Exception {

        if(usuario.getDsPassword().length() > 8) {
            throw new Exception("Senha nao pode passar de 8 caracteres");
        }

        if (dao.findByDsLogin(usuario.getDsLogin()) != null) {
            throw new Exception("Usuario já cadastrado");
        }

        usuario.setDsPassword(CriptoUtils.criptografa(usuario.getDsPassword()));

        return dao.insert(usuario).getIdUsuario();
    }

    @PutMapping
    public void update(@RequestBody Usuario usuario) {

        usuario.setDsPassword(CriptoUtils.criptografa(usuario.getDsLogin()));

        dao.update(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void delete(@PathVariable Long idUsuario) {
        dao.delete(idUsuario);
    }

}
