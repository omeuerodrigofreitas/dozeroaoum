package controller;


import Util.JSFUtil;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import model.TipoUsuario;
import org.omnifaces.util.Messages;
import service.TipoUsuarioService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "tipoUsuarioController")
@URLBeanName("tipoUsuarioController")
@URLMappings(mappings = {
        @URLMapping(id = "tipo-usuario-nova", pattern = "/novao-tipo-usuario/", viewId = "/pages/protegido/tipo-usuario/tipo-usuario.xhtml"),
        @URLMapping(id = "tipo-usuarios", pattern = "/tipos-usuarios/", viewId = "/pages/protegido/tipo-usuario/tipo-usuarios.xhtml"),
        @URLMapping(id = "tipo-usuario-editar", pattern = "/editar-tipo-usuarios/#{tipoUsuarioController.tipoUsuarioPk}/",
                viewId = "/pages/protegido/tipo-usuario/tipo-usuario.xhtml")
})
public class TipoUsuarioController implements Serializable {


    @Inject
    TipoUsuarioService tipoUsuarioService;

    @Setter
    @Getter
    private long tipoUsuarioPk;

    @Getter
    @Setter
    private TipoUsuario tipoUsuario;

    public TipoUsuarioController(){
        tipoUsuario = new TipoUsuario();
    }

    @Getter
    private List<TipoUsuario> tipoUsuarios = new ArrayList<>();

    public void salvar() {
        try {
            tipoUsuarioService.save(tipoUsuario);
            tipoUsuarioPk = tipoUsuario.getPk();
            Messages.addGlobalInfo("OK");
            JSFUtil.prettyRedirect("tipo-usuario-editar", tipoUsuarioPk);
        } catch (Exception e){
            Messages.addGlobalError("N-Ok");
        }
    }

    @URLAction(mappingId = "tipo-usuario-editar", onPostback = false)
    public void carregarTipoUsuarioPorPk() {
        tipoUsuario = tipoUsuarioService.findById(tipoUsuarioPk);
    }

    @URLAction(mappingId = "tipo-usuarios", onPostback = false)
    public List<TipoUsuario> pupularLista() {
        if (tipoUsuarios.isEmpty()) {
            tipoUsuarios = tipoUsuarioService.findAll();
        }
        return tipoUsuarios;
    }
}
