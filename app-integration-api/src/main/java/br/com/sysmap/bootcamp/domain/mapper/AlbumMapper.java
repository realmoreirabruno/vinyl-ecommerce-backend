package br.com.sysmap.bootcamp.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

@Named("AlbumMapper")
@Mapper
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    List<AlbumModel> toModel(AlbumSimplified[] albumSimplifiedPaging);

}
