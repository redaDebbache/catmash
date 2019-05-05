package com.debbache.catmash.endPoints;

import com.debbache.catmash.dto.BattleDTO;
import com.debbache.catmash.repository.BattleRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battles")
@Api(description = "Récupération des Battles entre chats!")
public class BattleController {

    private static final int DEFAULT_PAGE_SIZE = 100;
    private static final int FIRST_PAGE = 0;
    private final BattleRepository battlePageableRepository;

    public BattleController(BattleRepository battlePageableRepository) {
        this.battlePageableRepository = battlePageableRepository;
    }

    @ApiOperation(value = "Récupère 100 Battles au hasard qui n'ont pas encore été soumis au vote de l'utilisateur",
            notes = "On envoie une page de 100 battles pour éviter de faire un appel de battle apres chaque vote, du coup on a 10 fois moins d'appels")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved battles")
    }
    )
    @CrossOrigin
    @GetMapping(value = "/next")
    public Page<BattleDTO> nextBattle(@ApiParam(value = "id du votant.", required = true) @RequestHeader("userId") String userId) {
        return battlePageableRepository.findNext(userId, PageRequest.of(FIRST_PAGE, DEFAULT_PAGE_SIZE));
    }

}
