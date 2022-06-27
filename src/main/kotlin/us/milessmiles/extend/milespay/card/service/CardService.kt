package us.milessmiles.extend.milespay.card.service

import us.milessmiles.extend.milespay.card.model.VirtualCardResponse
import us.milessmiles.extend.milespay.common.model.Page

interface CardService {
    fun getVirtualCards(authorization: String, pageNumber: Int?, pageSize: Int?): Page<VirtualCardResponse>
}
