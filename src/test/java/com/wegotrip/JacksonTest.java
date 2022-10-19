package com.wegotrip;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wegotrip.jsonClass.Team;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {
    ClassLoader cl = JacksonTest.class.getClassLoader();

    @Test
    public void jsonPlantsTest() throws Exception {
        File file = new File("src/test/resources/developers.json");
        assertThat(file.exists()).isTrue();
        InputStream js = cl.getResourceAsStream("src/test/resources/developers.json");
        ObjectMapper mapper = new ObjectMapper();
        Team team = mapper.readValue(file, Team.class);
        assertThat(team.team.get(0).first_name).isEqualTo("Stas");
        assertThat(team.team.get(1).first_name).isEqualTo("Nikita");
        assertThat(team.team.get(2).first_name).isEqualTo("Ilya");
    }
}
