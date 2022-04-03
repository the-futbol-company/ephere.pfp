package io.ephere.pfp.service

import io.ephere.pfp.model.ERC721Metadata
import org.springframework.stereotype.Service

@Service
class EphereProfilePicService {

  fun getMetadataFromTokenId(tokenId: Int): ERC721Metadata {
    return when (tokenId) {
      0 -> ERC721Metadata(
        name = "Ephere Football",
        image = "https://ipfs.ephere.io/ipfs/Qma4zhyssz6GfZjYbfyiTcQJHVnqHCsvDJ3HLT96hAjGA5",
        externalUrl = "https://ephere.football",
        description = "The football metaverse where anyone can play and earn",
        backgroundColor = "#000000",
        attributes = null
      )
      1 -> ERC721Metadata(
        name = "Agu",
        image = "https://ipfs.ephere.io/ipfs/QmPnVeLN6MboMUB5UjjdordDzSBBLLzBFot3RjXmJzKTHe",
        externalUrl = "https://agu.uy",
        description = "Entrepeneur",
        backgroundColor = "#000000",
        attributes = null
      )
      else -> throw Error("Not found")
    }
  }

}