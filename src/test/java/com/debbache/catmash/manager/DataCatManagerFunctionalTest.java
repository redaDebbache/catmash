package com.debbache.catmash.manager;

import com.debbache.catmash.configuration.CatMashTest;
import com.debbache.catmash.dto.CatDTO;
import com.debbache.catmash.dto.Cats;
import com.debbache.catmash.model.Battle;
import com.debbache.catmash.model.Cat;
import com.debbache.catmash.repository.BattleRepository;
import com.debbache.catmash.repository.CatRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(SpringJUnit4ClassRunner.class)
@CatMashTest
public class DataCatManagerFunctionalTest {

    @Autowired
    private DataCatManager sut;

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity responseEntity;

    @Before
    public void init() {
        responseEntity = mock(ResponseEntity.class);
        when(restTemplate.getForEntity(anyString(), (Class<?>) any(Class.class))).thenReturn(responseEntity);
    }

    @Test
    public void given_an_empty_cat_list_when_donwload_cats_then_no_cats_should_be_stored_in_database() {
        //Given
        when(responseEntity.getBody()).thenReturn(new Cats());
        //When
        sut.dowloadCats();
        //Then
        List<Cat> allStoredCats = catRepository.findAll();
        assertThat(allStoredCats).isEmpty();
    }

    @Test
    public void given_3_cats_list_when_donwload_cats_then_those_cats_should_be_stored_in_database() {
        //Given
        insertSomeCats(3);
        //Then
        List<Cat> allStoredCats = catRepository.findAll();
        assertThat(allStoredCats).isNotEmpty();
        assertThat(allStoredCats.size()).isEqualTo(3);
    }

    @Test
    public void given_3_cats_in_database_and_having_a_already_stored_cat_then_it_will_not_be_saved() {
        //Given
        insertSomeCats(3);

        Cat alreadyStoredCat = catRepository.findAll().get(0);
        Cats wrapper = new Cats();
        wrapper.setImages(singleton(new CatDTO(alreadyStoredCat.getUrl(), alreadyStoredCat.getId())));
        when(responseEntity.getBody()).thenReturn(wrapper);
        //When
        sut.dowloadCats();
        //Then
        List<Cat> allStoredCats = catRepository.findAll();
        assertThat(allStoredCats.size()).isEqualTo(3);
    }

    @Test
    public void given_10_cats_when_download_cats_then_45_battles_should_be_stored_in_database() {
        //Given
        downloadCats(10);
        //When
        sut.dowloadCats();
        //Then
        List<Battle> storedBAttles = battleRepository.findAll();
        assertThat(storedBAttles.size()).isEqualTo(45);
    }

    @Test
    public void given_10_new_cats_and_having_10_cats_when_download_cats_should_create_190_battles_in_database() {
       //Before
        downloadCats(10);
        sut.dowloadCats();
        List<Battle> alreadyStoredBAttled = battleRepository.findAll();
        //Given
        downloadCats(10);
        //When
        sut.dowloadCats();
        //Then
        List<Battle> allBattles = battleRepository.findAll();
        assertThat(alreadyStoredBAttled.size()).isEqualTo(45);
        assertThat(allBattles.size()).isEqualTo(190);
    }

    private Cats buildCats(int size) {
        Cats cats = new Cats();
        buildCatsList(size, cats);
        return cats;
    }

    private void buildCatsList(int size, Cats cats) {
        cats.setImages(IntStream.range(0, size)
                .mapToObj(id -> new CatDTO("http://" + id, UUID.randomUUID().toString()))
                .collect(Collectors.toSet()));
    }


    private void downloadCats(int size) {
        when(responseEntity.getBody()).thenReturn(buildCats(size));
    }


    private void insertSomeCats(int size) {
        downloadCats(size);
        sut.dowloadCats();
    }
}
