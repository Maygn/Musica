package musica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cuenta") // Ruta base para este controlador
public class CuentaController {


	   @Autowired
	    private CuentaService cuentaService;
	
	//asignar json a nuevo usuario
	@PostMapping("/nuevo")
	public ResponseEntity<String> crearJson(@RequestParam String usuario, @RequestParam String defJson) {
	
		 
		try { //se usa responseentity porque deja manipular el tipo de error.
	        return new ResponseEntity<String>(cuentaService.guardarCuenta(usuario, defJson).getNombre()+" guardado en BDD",HttpStatus.OK); 
    	}
    	catch(DataIntegrityViolationException e) {
    		return new ResponseEntity<String>("Ya tenemos ese usuario",HttpStatus.BAD_REQUEST);
    	}
		

	}
	//recuperar json de usuario
	@GetMapping("{usuario}")
	public String verJson(@PathVariable String usuario) {
		Cuenta c1= cuentaService.buscarPorNombre(usuario);
		String musica= c1.musica;
		return musica;
	}
	//modificar json de usuario
	@PostMapping("/modificar")
	public String modificarJSon(String usuario) {
		return "";
	}
	//borrar un usuario del todo
	@PostMapping("/borrarUsuario")
	
	public String borrarUsuario(String usuario) {
		return "";
	}
}
