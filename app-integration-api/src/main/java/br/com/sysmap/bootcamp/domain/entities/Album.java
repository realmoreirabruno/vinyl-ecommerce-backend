package br.com.sysmap.bootcamp.domain.entities;

import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ALBUM")
@Schema(description = "Album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "ID_SPOTIFY", nullable = false, length = 100)
    private String idSpotify;

    @Column(name = "ARTIST_NAME", nullable = false, length = 150)
    private String artistName;

    @Column(name = "IMAGE_URL", nullable = false, length = 150)
    private String imageUrl;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private Users users;

}