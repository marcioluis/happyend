package br.com.happhour.service;

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
		Usuario usu = new Usuario();

		usu.setEmail("marciososter@gmail.com");
		usu.setDisplayName("Marcio Soster");
		usu.setFamilyName("Soster");
		usu.setGivenName("Marcio");
		usu.setImageUrl(
				"https://lh3.googleusercontent.com/-1wkcrKBKpl4/AAAAAAAAAAI/AAAAAAAAACs/V2h_JuDaKks/s96-c/photo.jpg");
		usu.setProviderIdToken(
				"eyJhbGciOiJSUzI1NiIsImtpZCI6ImVjZmZlZmYyZjYzZjhlOThlZjM2YWRiZGViNzI5MTVlM2E0NmVmMmUifQ.eyJhenAiOiI3ODQyMjA2NzAwNDItOGJ2aHBsaTdvNmF1cWlwdjAyZDM2Ymdpam9wcDhnbWUuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3ODQyMjA2NzAwNDItaWIxc3N2MXV0ZnIxYzRjdmZzNjd0N245dGdrY2sydmsuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDc3NjkxNDk1NDgyMTgwMzY5MzMiLCJlbWFpbCI6Im1hcmNpb3Nvc3RlckBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDk4MTY2MjExLCJleHAiOjE0OTgxNjk4MTEsIm5hbWUiOiJNYXJjaW8gU29zdGVyIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS8tMXdrY3JLQktwbDQvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQ3MvVjJoX0p1RGFLa3Mvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNpbyIsImZhbWlseV9uYW1lIjoiU29zdGVyIiwibG9jYWxlIjoicHQifQ.gAqc-gw8xVYn4NqO7SSE_og1lVDTPbzgLFUjLJGA3ym5fIGgha6htaofW6S-vClu7UIHu7J8KzZ_C3q_RDdGuFXeNvtFydSXkXcJxUhzfmJIGu8C0SzI4VAzy2Z1s7fQ9W13D6yoaRDtsiLJqh5wvgpRwvZXiD9Es3vK0OJbwI0DqVpxyqinTdN4wDftEwsVEldTubOQdVe1p1veibQhtsL6s1I98opLfzJRD_s1pTzER_yUfE3mcZJawBf8EhQy4UT1dd3LBFcpx22DFIvkJrHk6XgZbngp-iN-1GA53TKS7kHBelcY69iEVrXv1rNBmCaVZXQpy4ta-qRpx-PVVw");
		usu.setProvider("google");

		usuarioService.validateGoogleToken(usu);

	}
}
