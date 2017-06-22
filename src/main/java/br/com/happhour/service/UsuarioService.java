package br.com.happhour.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

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

@Service
@Transactional
public class UsuarioService {

	private final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void validateGoogleToken(Usuario user) throws Exception {

		final String CLIENT_ID = "784220670042-ib1ssv1utfr1c4cvfs67t7n9tgkck2vk.apps.googleusercontent.com";

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance())
				.setAudience(Collections.singletonList(CLIENT_ID)).build();

		try {
			GoogleIdToken idToken = verifier.verify(user.getProviderIdToken());

			if (idToken != null) {
				Payload payload = idToken.getPayload();

				// User identifier
				String userId = payload.getSubject();
				System.out.println(userId);
				// Get profile information from payload
				String email = payload.getEmail();
				boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				String name = (String) payload.get("name");
				String pictureUrl = (String) payload.get("picture");
				String locale = (String) payload.get("locale");
				String familyName = (String) payload.get("family_name");
				String givenName = (String) payload.get("given_name");

				// Use or store profile information
				// ...

			} else {
				System.out.println("Invalid ID token.");
			}
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
