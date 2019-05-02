package com.debbache.catmash.manager;

import com.debbache.catmash.dto.CatDTO;
import com.debbache.catmash.dto.Cats;
import com.debbache.catmash.model.Cat;
import com.debbache.catmash.repository.CatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.debbache.catmash.util.Constants.EMPTY;

@Component
@Slf4j
public class DataCatManager {

    private final RestTemplate catTemplate;
    private final CatRepository catRepository;
    private final BattleManager battleManager;

    public DataCatManager(RestTemplate catTemplate, CatRepository catRepository, BattleManager battleManager) {
        this.catTemplate = catTemplate;
        this.catRepository = catRepository;
        this.battleManager = battleManager;
    }

    public void dowloadCats() {
        try {
            ResponseEntity<Cats> response = catTemplate.getForEntity(EMPTY, Cats.class);
            Cats catsToDownload = response.getBody();
            Optional.ofNullable(catsToDownload)
                    .map(Cats::getCats)
                    .ifPresent(this::layInCats);
        }catch (Exception ex){
           log.error("An erro occured while accessing remote cat repository: ", ex);
        }
    }

   private void layInCats(List<CatDTO> cats) {
        List<String> alreadyStoredcatIds = catRepository.findAllStoredCatsIds();

        List<Cat> newCats = getNewCats(cats, alreadyStoredcatIds);

        catRepository.saveAll(newCats);

        this.battleManager.createAndStoreBattles(newCats, alreadyStoredcatIds);

    }

    private List<Cat> getNewCats(List<CatDTO> cats, List<String> alreadyStoredcatIds) {
        return cats.stream().distinct()
                .filter(cat -> !alreadyStoredcatIds.contains(cat.getId()))
                .map(cat -> new Cat(cat.getId(), cat.getUrl()))
                .collect(Collectors.toList());
    }
}
