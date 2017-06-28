package br.com.happhour.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import br.com.happhour.HappyendApplication;
import br.com.happhour.domain.Usuario;
import br.com.happhour.domain.UsuarioSettings;
import br.com.happhour.repository.UsuarioRepository;
import br.com.happhour.service.dto.UsuarioDTO;
import br.com.happhour.service.mapper.UsuarioMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HappyendApplication.class)
@Transactional
public class UsuarioServiceIntTest {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	@Mock
	private GoogleIdTokenVerifier verifier;
	@Mock
	private GoogleIdToken idToken;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void criar_usuario_google_provider() throws Exception {
		// mocks para a api Google das informacoes do token
		Payload pay = new Payload();
		pay.set("name", "Marcio Soster");
		pay.set("family_name", "Soster");
		pay.set("given_name", "Marcio");
		pay.setEmail("marciososter@gmail.com");
		pay.setSubject("12345678910");
		when(verifier.verify("token")).thenReturn(idToken);
		when(idToken.getPayload()).thenReturn(pay);
		// criar o servico com o mock do google
		usuarioService.setVerifier(verifier);

		// setup e teste
		UsuarioDTO usu = new UsuarioDTO();
		usu.setEmail("marciotester@gmai.com");
		usu.setDisplayName("Marcio Teste");
		usu.setFamilyName("Teste");
		usu.setGivenName("Marcio Teste");
		usu.setTelephone("+55 69 993999777");
		usu.setImageUrl("https://lh3.googleusercontent.com/photo.jpg");
		usu.setProviderIdToken("token");
		usu.setProvider("google");

		usu = usuarioService.createUsuarioFromProvider(usu);

		assertThat(usu.getId()).isNotNull();
		assertThat(usu.getDisplayName()).isEqualTo("Marcio Soster");
		assertThat(usu.getFamilyName()).isEqualTo("Soster");
		assertThat(usu.getGivenName()).isEqualTo("Marcio");
		assertThat(usu.getProvider()).isEqualTo("google");
		assertThat(usu.getEmail()).isEqualTo("marciososter@gmail.com");
	}

	@Test
	public void criar_usuario_facebook_provider() {
		UsuarioDTO usu = new UsuarioDTO();
		usu.setEmail("marciotester@gmail.com");
		usu.setDisplayName("Marcio Teste");
		usu.setFamilyName("Teste");
		usu.setGivenName("Marcio Teste");
		usu.setTelephone("+55 69 993999777");
		usu.setImageUrl(
				"https://lh3.googleusercontent.com/-1wkcrKBKpl4/AAAAAAAAAAI/AAAAAAAAACs/V2h_JuDaKks/s96-c/photo.jpg");
		usu.setProvider("facebook");

		usu = usuarioService.createUsuarioFromProvider(usu);

		assertThat(usu.getId()).isNotNull();
		assertThat(usu.getDisplayName()).isEqualTo("Marcio Teste");
		assertThat(usu.getFamilyName()).isEqualTo("Teste");
		assertThat(usu.getGivenName()).isEqualTo("Marcio Teste");
		assertThat(usu.getProvider()).isEqualTo("facebook");
		assertThat(usu.getEmail()).isEqualTo("marciotester@gmail.com");
		assertThat(usu.getTelephone()).isEqualTo("+55 69 993999777");
	}

	@Test
	public void usuario_facebook_provider_existente() {
		// cria um usuario na base
		UsuarioDTO usu = new UsuarioDTO();
		usu.setEmail("marciotester@gmail.com");
		usu.setDisplayName("Marcio Teste");
		usu.setFamilyName("Teste");
		usu.setGivenName("Marcio Teste");
		usu.setTelephone("+55 69 993999777");
		usu.setProvider("facebook");
		usu.setProviderUserId("123456789");
		usuarioRepository.save(usuarioMapper.toEntity(usu));

		// novo usuario simulando um novo login
		usu = new UsuarioDTO();
		usu.setEmail("novoEmail@gmail.com");
		usu.setDisplayName("Marcio Novo");
		usu.setFamilyName("Re login");
		usu.setGivenName("Marcio Novo");
		usu.setTelephone("+55 69 993999778");
		usu.setProvider("facebook");
		usu.setProviderUserId("123456789");

		usu = usuarioService.createUsuarioFromProvider(usu);

		assertThat(usu.getId()).isNotNull();
		assertThat(usu.getDisplayName()).isEqualTo("Marcio Novo");
		assertThat(usu.getFamilyName()).isEqualTo("Re login");
		assertThat(usu.getGivenName()).isEqualTo("Marcio Novo");
		assertThat(usu.getEmail()).isEqualTo("novoEmail@gmail.com");
		assertThat(usu.getTelephone()).isEqualTo("+55 69 993999778");
		assertThat(usu.getProvider()).isEqualTo("facebook");
		assertThat(usu.getProviderUserId()).isEqualTo("123456789");
	}

	@Test
	public void usuario_google_provider_existente() throws Exception {
		// mocks para a api Google das informacoes do token
		Payload pay = new Payload();
		pay.set("name", "Marcio Novo");
		pay.set("family_name", "Re login");
		pay.set("given_name", "Marcio Novo");
		pay.setEmail("novoEmail@gmail.com");
		pay.setSubject("123456789");/// provider user id
		when(verifier.verify("token")).thenReturn(idToken);
		when(idToken.getPayload()).thenReturn(pay);
		// criar o servico com o mock do google
		usuarioService.setVerifier(verifier);

		// setup e testes
		// cria um usuario na base
		UsuarioDTO usu = new UsuarioDTO();
		usu.setEmail("marciotester@gmail.com");
		usu.setDisplayName("Marcio Teste");
		usu.setFamilyName("Teste");
		usu.setGivenName("Marcio Teste");
		usu.setTelephone("+55 69 993999777");
		usu.setProvider("google");
		usu.setProviderUserId("123456789");
		usuarioRepository.save(usuarioMapper.toEntity(usu));
		// novo usuario simulando um novo login
		usu = new UsuarioDTO();
		usu.setEmail("novoEmail@gmail.com");
		usu.setDisplayName("Marcio Replace pelo token");
		usu.setFamilyName("Re login");
		usu.setGivenName("Marcio replace");
		usu.setTelephone("+55 69 993999778");
		usu.setProvider("google");
		usu.setProviderIdToken("token");

		usu = usuarioService.createUsuarioFromProvider(usu);

		assertThat(usu.getId()).isNotNull();
		assertThat(usu.getDisplayName()).isEqualTo("Marcio Novo");
		assertThat(usu.getFamilyName()).isEqualTo("Re login");
		assertThat(usu.getGivenName()).isEqualTo("Marcio Novo");
		assertThat(usu.getEmail()).isEqualTo("novoEmail@gmail.com");
		assertThat(usu.getTelephone()).isEqualTo("+55 69 993999778");
		assertThat(usu.getProvider()).isEqualTo("google");
		assertThat(usu.getProviderUserId()).isEqualTo("123456789");
	}

	@Test
	public void atualizar_settings() {
		// cria um usuario na base
		Usuario usu = new Usuario();
		usu.setEmail("marciotester@gmail.com");
		usu.setDisplayName("Marcio Teste");
		usu.setFamilyName("Teste");
		usu.setGivenName("Marcio Teste");
		usu.setTelephone("+55 69 993999777");
		usu.setProvider("facebook");
		usu.setProviderUserId("123456789");
		usuarioRepository.save(usu);

		Long usuarioID = usu.getId();

		UsuarioSettings settings = new UsuarioSettings();
		settings.setGeofances(true);
		settings.setNotifications(false);
		settings.setPromotions(true);
		settings.setSearchRadius(500);

		Usuario usuario = usuarioService.updateSettings(settings, usuarioID);

		settings = usuario.getSettings();
		assertThat(settings.getId()).isNotNull();
		assertThat(settings.getSearchRadius()).isEqualTo(500);
		assertThat(settings.isGeofances()).isTrue();
		assertThat(settings.isNotifications()).isFalse();
		assertThat(settings.isPromotions()).isTrue();
	}
}
