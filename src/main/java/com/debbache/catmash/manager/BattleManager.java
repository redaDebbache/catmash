package com.debbache.catmash.manager;

import com.debbache.catmash.model.Battle;
import com.debbache.catmash.model.Cat;
import com.debbache.catmash.repository.BattleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BattleManager {

    private final BattleRepository battleRepository;

    public BattleManager(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @Transactional
    public void createAndStoreBattles(List<Cat> newCats, List<String> alreadyStoredcatIds) {
        List<Battle> battles = createBattlesWith(newCats, alreadyStoredcatIds);
        this.battleRepository.saveAll(battles);
    }

     List<Battle> createBattlesWith(List<Cat> newCats, List<String> alreadyStoredcatIds) {
        List<Cat> cats = alreadyStoredcatIds.stream().map(Cat::new).collect(Collectors.toList());
        cats.addAll(newCats);
        return generateBattlesFrom(cats);
    }

    List<Battle> generateNewCatBattles(List<Cat> newCats) {
        return generateBattlesFrom(newCats);
    }

    private List<Battle> generateBattlesFrom(List<Cat> cats) {
        return cats
                .stream()
                .map(cat -> cats.stream()
                        .map(newCat -> buildBattle(cat, newCat)))
                .flatMap(s -> s)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .distinct()
                .collect(Collectors.toList());
    }

    private Optional<Battle> buildBattle(Cat first, Cat second) {
        return first.equals(second) ? Optional.empty() : Optional.of(new Battle(first, second));
    }
}
