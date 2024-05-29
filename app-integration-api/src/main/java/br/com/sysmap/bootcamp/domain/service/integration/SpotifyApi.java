package br.com.sysmap.bootcamp.domain.service.integration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;

import br.com.sysmap.bootcamp.domain.mapper.AlbumMapper;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Service
public class SpotifyApi {

    private final se.michaelthelin.spotify.SpotifyApi spotifyApi = new se.michaelthelin.spotify.SpotifyApi.Builder()
        .setClientId(/*insert your client id*/)
        .setClientSecret(/*insert your client secret*/)
        .build();

    public List<AlbumModel> getAlbums(String search) throws ParseException, SpotifyWebApiException, IOException {
        spotifyApi.setAccessToken(getToken());

        // Convert the Albums to a list of AlbumModel using AlbumMapper
        List<AlbumModel> albumList = AlbumMapper.INSTANCE.toModel(spotifyApi.searchAlbums(search)
        .market(CountryCode.BR)
        .limit(30)
        .build()
        .execute()
        .getItems());

        // Iterate over the list and set a random value for each album
        albumList.forEach(album -> album.setValue(generateRandomValue()));

        // Return the list of albums
        return albumList;

    }

    public static BigDecimal generateRandomValue() {
        // Define the minimum and maximum values
        BigDecimal minValue = BigDecimal.valueOf(12.00);
        BigDecimal maxValue = BigDecimal.valueOf(100.00);

        // Generate a random value between the minimum and maximum values
        BigDecimal randomValue = minValue.add(
                new BigDecimal(Math.random()).multiply(maxValue.subtract(minValue)));

        // Round the random value to two decimal places using HALF_UP rounding mode
        return randomValue.setScale(2, RoundingMode.HALF_UP);
    }

    public String getToken() throws ParseException, SpotifyWebApiException, IOException {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        return clientCredentialsRequest.execute().getAccessToken();
    }

}
