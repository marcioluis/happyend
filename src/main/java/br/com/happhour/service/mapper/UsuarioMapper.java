package br.com.happhour.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.happhour.domain.Usuario;
import br.com.happhour.service.dto.UsuarioDTO;

/**
 * Mapper for the entity Usuario and its DTO UsuarioDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper <UsuarioDTO, Usuario> {
    
	@Override
    @Mapping(target = "empresas", ignore = true)
    @Mapping(target = "eventos", ignore = true)
    @Mapping(target = "pontos", ignore = true)
    @Mapping(target = "settings", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO); 

	@Override
	@Mapping(target = "providerIdToken", ignore = true)
	UsuarioDTO toDto(Usuario entity);

    default Usuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
