package br.com.sysmap.bootcamp.domain.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

@Data
@Schema(description = "AlbumModel")
public class AlbumModel {

    private String name;
    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private ExternalUrl externalUrl;
    private String id;
    private Image[] images;
    private String releaseDate;
    private ModelObjectType type;
    private BigDecimal value;

}
