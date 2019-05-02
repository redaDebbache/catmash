package com.debbache.catmash.manager;

import com.debbache.catmash.model.Battle;
import com.debbache.catmash.model.Cat;
import com.debbache.catmash.repository.BattleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class BattleManagerTest {

    @InjectMocks
    private BattleManager battleManager;

    @Mock
    private BattleRepository battleRepository;

    @Test
    public void should_generate_new_cat_battles_create_unique_cat_battles() {
        assertRightCombination(3, 3);
        assertRightCombination(6, 15);
        assertRightCombination(15, 105);
    }

    @Test
    public void should_create_battles_with_new_cats_and_old_cats_battles() {
        List<Cat> cats = buildCatList(3);
        List<String> alreadyStoredIds = Arrays.asList(generateRandomID(), generateRandomID(), generateRandomID());
        List<Battle> battles = battleManager.createBattlesWith(cats, alreadyStoredIds);
        assertThat(battles.size()).isEqualTo(15);
    }

    private String generateRandomID() {
        return UUID.randomUUID().toString();
    }

    private void assertRightCombination(int n, int combination) {
        //Given
        List<Cat> cats = buildCatList(n);
        //When
        List<Battle> battles = battleManager.generateNewCatBattles(cats);
        //Then
        assertThat(battles.size()).isEqualTo(combination);
    }

    private List<Cat> buildCatList(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> buildCat(generateRandomID()))
                .collect(Collectors.toList());
    }

    private Cat buildCat(String id) {
        return new Cat(id, "image");
    }
}
