package controller;

import Util.JSFUtil;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import model.Usuario;
import org.omnifaces.util.Messages;
import service.UsuarioService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@ViewScoped
@Named(value = "usuarioController")
@URLBeanName("usuarioController")
@URLMappings(mappings = {
        @URLMapping(id = "usuario-novo", pattern = "/novo-usuario/", viewId = "/pages/protegido/usuario/usuario.xhtml")
})
public class UsuarioController implements Serializable {

    @Inject
    UsuarioService usuarioService;

    @Getter
    @Setter
    private long usuarioPK;

    @Getter
    @Setter
    private Usuario usuario;

    public UsuarioController(){ usuario = new Usuario();}


    public void salvar(){
        try{
            usuarioService.save(usuario);
            usuarioPK = usuario.getPk();
            Messages.addGlobalInfo("OK");
            JSFUtil.prettyRedirect("", usuarioPK);
        }catch (Exception e){
            Messages.addGlobalError("N-Ok");
        }
    }




}
