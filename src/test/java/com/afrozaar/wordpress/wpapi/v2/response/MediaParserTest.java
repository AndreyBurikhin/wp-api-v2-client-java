package com.afrozaar.wordpress.wpapi.v2.response;

import com.afrozaar.wordpress.wpapi.v2.model.Media;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MediaParserTest {

    @Test
    public void parse() throws Exception {

        String response_4_7 = getResourceAsString("/json/media-4.7.json");

        final Media parse = CustomRenderableParser.parseMedia(response_4_7);

        System.out.println("parse = " + parse);

        String response_4_6 = getResourceAsString("/json/media-4.6.json");

        final Media media = CustomRenderableParser.parseMedia(response_4_6);

        System.out.println("media = " + media);
    }

    private String getResourceAsString(String resource) throws IOException, URISyntaxException {
        return Files.readAllLines(Paths.get(MediaParserTest.class.getResource(resource).toURI())).stream().collect(Collectors.joining("\n"));
    }

    @Test
    public void parseArrayOfMedia() throws Exception {
        String mediaArray = getResourceAsString("/json/multi-media-response.json");

        final Media[] parse = CustomRenderableParser.parse(mediaArray, Media[].class);

        System.out.println("parse = " + Arrays.toString(parse));

        Assertions.assertThat(parse[0].getCaption()).isEqualTo("YmWDXWlmDNyITKTplpvndMPyAWcdDdiWpoQZaXKDOwkCTBIQBf");
    }

}