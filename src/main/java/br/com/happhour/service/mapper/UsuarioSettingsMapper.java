package br.com.happhour.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.happhour.domain.UsuarioSettings;
import br.com.happhour.service.dto.UsuarioSettingsDTO;

/**
 * Mapper for the entity Usuario and its DTO UsuarioDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsuarioSettingsMapper extends EntityMapper<UsuarioSettingsDTO, UsuarioSettings> {
    
	UsuarioSettingsMapper INSTANCE = Mappers.getMapper(UsuarioSettingsMapper.class);

	@Override
	@Mapping(target = "usuario", ignore = true)
	UsuarioSettings toEntity(UsuarioSettingsDTO settings);

	@Override
	@Mapping(target = "usuarioId", source = "usuario.id")
	UsuarioSettingsDTO toDto(UsuarioSettings entity);

	default UsuarioSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
		UsuarioSettings usuario = new UsuarioSettings();
        usuario.setId(id);
        return usuario;
    }
}