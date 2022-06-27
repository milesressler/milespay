package us.milessmiles.extend.milespay.card.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.milessmiles.extend.milespay.card.model.VirtualCardResponse
import us.milessmiles.extend.milespay.common.model.Page
import us.milessmiles.extend.milespay.integration.common.ExternalCardService

@Service
class DefaultCardService: CardService {

    @Autowired
    lateinit var externalAuthenticationService: ExternalCardService

    override fun getVirtualCards(authorization: String, pageNumber: Int?, pageSize: Int?): Page<VirtualCardResponse> {
        return externalAuthenticationService.getVirtualCards(authorization)
    }

}
