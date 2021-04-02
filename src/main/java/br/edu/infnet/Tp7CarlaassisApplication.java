package br.edu.infnet;

import br.edu.infnet.domain.usuarios.Usuario;
import br.edu.infnet.infra.usuarios.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.util.StringUtils;

@SpringBootApplication
@EnableFeignClients
public class Tp7CarlaassisApplication implements CommandLineRunner {
    
    private Logger log = LoggerFactory.getLogger(Tp7CarlaassisApplication.class);
    
    @Autowired
    private UsuarioService usuarioService;

    public static void main(String[] args) {
        
	SpringApplication.run(Tp7CarlaassisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Usuario candidato = this.logarNoSite("enzorod@gmail.com", "faisca");
        if(candidato != null) {
            
            log.info(candidato.getId() + "::" + candidato.getNome());
        } else {
            
            log.info("Erro de Login");
        }
    }
    private Usuario logarNoSite(String email, String senha) {
      
        Usuario retorno = null;
            if(org.apache.commons.lang3.StringUtils.isNotBlank(email) && org.apache.commons.lang3.StringUtils.isNotBlank(senha)){
            
            Usuario usuario = usuarioService.obterPorEmail(email);
            if (usuario != null && senha.equals(usuario.getSenha())) {
                
                retorno = usuario;
            }
        }
        return retorno;

    }
}





