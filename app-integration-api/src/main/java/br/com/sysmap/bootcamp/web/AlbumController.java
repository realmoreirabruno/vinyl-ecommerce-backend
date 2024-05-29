package br.com.sysmap.bootcamp.web;


import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import br.com.sysmap.bootcamp.domain.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;


    @Operation(summary = "Search for an album", method = "GET")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "200", description = "The search was made"),
    })
    @Parameters(value = {
            @Parameter(description = "Search description", name = "search", example = "Eminem"),

    })
    @GetMapping("/all")
    public ResponseEntity<List<AlbumModel>> getAlbums(@RequestParam("search") String search) throws IOException, ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(this.albumService.getAlbums(search));
    }

    @Operation(summary = "Purchase an album", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "200", description = "The purchase was made"),
    })
    @Parameters(value = {
            @Parameter(description = "Search by artist name or album name", name = "name", example = "Abba"),
            @Parameter(description = "Spotify AlbumID", name = "idSpotify", example = "5zT1JLIj9E57p3e1rFm9Uq"),
            @Parameter(description = "Image URL", name = "imageUrl", example = "https://i.scdn.co/image/ab67616d0000b273db8df35632c1be1caeb9461d"),
            @Parameter(description = "Album price", name = "value", example = "12"),
    })
    @PostMapping("/sale")
    public ResponseEntity<Album> saveAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(this.albumService.saveAlbum(album));
    }

    @Operation(summary = "Delete an album", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "200", description = "The album was sucessfully removed"),
    })
    @Parameter(description = "AlbumID", name = "id", example = "1")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeAlbum(@PathVariable Long id){
        this.albumService.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }

}