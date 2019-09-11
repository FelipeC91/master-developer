package br.com.softblue.bluefood.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.infrastructure.web.security.LoggedUser;

public class SecurityUtils {

	public static LoggedUser loggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}
		
		return (LoggedUser) authentication.getPrincipal();
	}
	
	public static String loggedClienteCep() {
		LoggedUser loggedUser = loggedUser();
		if (loggedUser == null) {
			throw new IllegalStateException("N�o existe um usu�rio logado");
		}
		
		if (!(loggedUser.getUsuario() instanceof Cliente)) {
			throw new IllegalStateException("O usu�rio logado n�o � um cliente");
		}
		
		Cliente cliente = (Cliente) loggedUser.getUsuario();
		return cliente.getCep();
	}
}
