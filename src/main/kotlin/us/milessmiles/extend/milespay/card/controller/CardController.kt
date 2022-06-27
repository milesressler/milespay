package us.milessmiles.extend.milespay.card.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import us.milessmiles.extend.milespay.card.model.VirtualCardResponse
import us.milessmiles.extend.milespay.card.service.CardService
import us.milessmiles.extend.milespay.common.model.Page

@RestController
class CardController {

    @Autowired
    lateinit var cardService: CardService

    @GetMapping("/card")
    fun signin(@RequestHeader("Authorization") authHeader: String): Page<VirtualCardResponse> {
        return cardService.getVirtualCards(authHeader, 1 , 1)
    }
}
