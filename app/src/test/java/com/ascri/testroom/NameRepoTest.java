package com.ascri.testroom;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


public class NameRepoTest {
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    @Mock
    FileReader fileReader;
    NameRepo nameRepository;

    @Before
    public void setUp() throws IOException {
        when(fileReader.readFile()).thenReturn("{name : Sasha}");
        nameRepository = new NameRepo(fileReader);
    }

    @Test
    public void getName_isSasha() throws Exception {
        String name = nameRepository.getName();
        assertThat(name).isEqualTo("Sasha");
    }
}
