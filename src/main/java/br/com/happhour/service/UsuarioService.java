package br.com.happhour.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import br.com.happhour.domain.Usuario;
import br.com.happhour.repository.UsuarioRepository;

/**
 * Service Implementation for managing Usuario.
 */
@Service
@Transactional
public class UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
    }

	public Usuario createUsuarioFromProvider(Usuario user) {
		switch (user.getProvider()) {
		case "google":
			validateAndMergeGoogleTokenInfo(user);
			return usuarioRepository.save(user);
		case "facebook":
		default:
			return usuarioRepository.save(user);
		}
	}

	/**
	 * Valida o token Id do google e atualiza algumas informações do usuario com
	 * os dados do token
	 * 
	 * @param user
	 *            usuário com um provider id token do Google
	 */
	public void validateAndMergeGoogleTokenInfo(Usuario user) {

		// TODO: Google Web Client num arquivo de properties
		final String CLIENT_ID = "784220670042-ib1ssv1utfr1c4cvfs67t7n9tgkck2vk.apps.googleusercontent.com";

		// proxy para funcionar
		NetHttpTransport netHttpTransport = new NetHttpTransport.Builder()
				.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 3128))).build();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(netHttpTransport,
				JacksonFactory.getDefaultInstance()).setAudience(Collections.singletonList(CLIENT_ID)).build();

		try {
			GoogleIdToken idToken = verifier.verify(user.getProviderIdToken());

			if (idToken != null) {
				Payload payload = idToken.getPayload();

				// User identifier
				String userId = payload.getSubject();

				// Get profile information from payload
				// https://accounts.google.com/.well-known/openid-configuration
				String name = (String) payload.get("name");
				String familyName = (String) payload.get("family_name");
				String givenName = (String) payload.get("given_name");
				String pictureUrl = (String) payload.get("picture");
				String email = payload.getEmail();
				// boolean emailVerified =
				// Boolean.valueOf(payload.getEmailVerified());

				user.setDisplayName(name);
				user.setFamilyName(familyName);
				user.setGivenName(givenName);
				user.setImageUrl(pictureUrl);
				user.setEmail(email);
				user.setProviderUserId(userId);

			} else {
				// TODO: Google Token Excessao customizada se não validar o
				// token
				log.error("Google token inválido enviado. Usuário email: {}", user.getEmail());
			}
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			// TODO: Google Token Excessao customizada se não validar o token
		}
	}

    /**
     * Save a usuario.
     *
     * @param Usuario the entity to save
     * @return the persisted entity
     */
	public Usuario save(Usuario Usuario) {
		log.debug("Request to save Usuario : {}", Usuario);
		return usuarioRepository.save(Usuario);
    }

    /**
     *  Get all the usuarios.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
	public List<Usuario> findAll() {
        log.debug("Request to get all Usuarios");
		return usuarioRepository.findAll().stream()
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one usuario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
	public Usuario findOne(Long id) {
        log.debug("Request to get Usuario : {}", id);
		return usuarioRepository.findOne(id);
    }

    /**
     *  Delete the  usuario by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Usuario : {}", id);
        usuarioRepository.delete(id);
    }
}
