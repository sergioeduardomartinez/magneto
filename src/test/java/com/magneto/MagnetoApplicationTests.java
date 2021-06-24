package com.magneto;

import com.magneto.service.MutantController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class MagnetoApplicationTests {
    @Autowired
    MutantController mutantController;

    @Test
    void contextLoads() {

    }

}
