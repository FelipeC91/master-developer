package br.com.softblue.bluefood.domain.cliente;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softblue.bluefood.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotBlank(message = "O nome n�o pode ser vazio")
	@Size(max = 80, message = "O nome � muito grande")
	private String nome;
	
	@NotBlank(message = "O e-mail n�o pode ser vazio")
	@Size (max = 60, message = "O e-mail � muito grande")
	@Email(message = "O e-mail � inv�lido")
	private String email;

	@NotBlank(message = "A senha n�o pode ser vazia")
	@Size(max = 80, message = "A senha � muito grande")
	private String senha;
	
	@NotBlank(message = "O telefone n�o pode ser vazio")
	@Column(name = "telefone", nullable = false, length = 11)
	@Pattern(regexp = "[0-9]{10,11}", message = "O telefone n�o � v�lido")
	private String telefone;
	
	public void encryptSenha() {
		this.senha = StringUtils.encrypt(this.senha);
	}
}
