package br.com.happhour.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.happhour.HappyendApplication;
import br.com.happhour.domain.Usuario;
import br.com.happhour.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HappyendApplication.class)
@Transactional
public class UsuarioServiceIntTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;

	@Test
	public void testaValidacaoGoogleToken() throws Exception {

		// NetHttpTransport netHttpTransport = new NetHttpTransport.Builder()
		// .setProxy(new Proxy(Proxy.Type.HTTP, new
		// InetSocketAddress("localhost", 3128))).build();

		// final String CLIENT_ID =
		// "784220670042-ib1ssv1utfr1c4cvfs67t7n9tgkck2vk.apps.googleusercontent.com";
		// final String CLIENT_SECRET = "0dqkRIe2R4HAodPduh_eI0th";
		// final String AUTH_CODE =
		// "4/tnTc2an1BH2X8TMAPRWpfIph7VqBmVbpr7ptnjx49E4";
		//
		// GoogleTokenResponse tokenResponse = new
		// GoogleAuthorizationCodeTokenRequest(netHttpTransport,
		// JacksonFactory.getDefaultInstance(),
		// "https://www.googleapis.com/oauth2/v4/token", CLIENT_ID,
		// CLIENT_SECRET, AUTH_CODE, "").execute();
		//
		// String accessToken = tokenResponse.getAccessToken();
		// String idToken = tokenResponse.getIdToken();
		// String refreshToken = tokenResponse.getRefreshToken();
		//
		// System.out.println("Access: " + accessToken);
		// System.out.println("Id: " + idToken);
		// System.out.println("Refresh: " + refreshToken);
		
		Usuario usu = new Usuario();
		usu.setEmail("marciotester@gmai.com");
		usu.setDisplayName("Marcio Teste");
		usu.setFamilyName("Teste");
		usu.setGivenName("Marcio Teste");
		usu.setImageUrl(
				"https://lh3.googleusercontent.com/-1wkcrKBKpl4/AAAAAAAAAAI/AAAAAAAAACs/V2h_JuDaKks/s96-c/photo.jpg");
		usu.setProviderIdToken(
				"eyJhbGciOiJSUzI1NiIsImtpZCI6IjM1MWIwZjZjNDM0NDgwOGQ1NzBlNzkyMTVjYWI1NDk5NzI1ZTM2ZjIifQ.eyJhenAiOiI3ODQyMjA2NzAwNDItOGJ2aHBsaTdvNmF1cWlwdjAyZDM2Ymdpam9wcDhnbWUuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3ODQyMjA2NzAwNDItaWIxc3N2MXV0ZnIxYzRjdmZzNjd0N245dGdrY2sydmsuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDc3NjkxNDk1NDgyMTgwMzY5MzMiLCJlbWFpbCI6Im1hcmNpb3Nvc3RlckBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDk4MjI1MzA2LCJleHAiOjE0OTgyMjg5MDYsIm5hbWUiOiJNYXJjaW8gU29zdGVyIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS8tMXdrY3JLQktwbDQvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQ3MvVjJoX0p1RGFLa3Mvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNpbyIsImZhbWlseV9uYW1lIjoiU29zdGVyIiwibG9jYWxlIjoicHQifQ.w5i5xdp5T6WiMrmo82x0L0dWXm7WBNtDB-IFthRDeeLTT7D3oumoCt68Q2l26ioLYD4_Af6BTma2m1-FB_ARhdTDz6RlBce-vTMHgRmv1Ac9BlnYB0PZuW260_u6ki2k3pdTwTy_J8HBoE0jeOCyeOQ_sAH8BaADsuSLDzpOvkA1xhQASVzawx-x_mIiHShRF3tmoD_lqxfWZlxiqQyCjZg1bLIhXNsLKooUI2-HIciNDQ1OEfqfK1psXXygUlq0YuAXy8_nLKQLUbjmsVq755aguUDsEI5_J0EcqzXet5o6EqAMuQaFsB8myGlxnjs0YH_YhG3_VHpkHFoUlRWqJQ");
		usu.setProvider("google");

		usuarioService.validateAndMergeGoogleTokenInfo(usu);

		assertThat(usu.getDisplayName()).isEqualTo("Marcio Soster");
		assertThat(usu.getFamilyName()).isEqualTo("Soster");
		assertThat(usu.getGivenName()).isEqualTo("Marcio");
		assertThat(usu.getProvider()).isEqualTo("google");
		assertThat(usu.getEmail()).isEqualTo("marciososter@gmail.com");
	}
}
