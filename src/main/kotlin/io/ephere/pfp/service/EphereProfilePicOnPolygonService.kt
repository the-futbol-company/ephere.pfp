package io.ephere.pfp.service

import io.ephere.pfp.model.ERC721Metadata
import org.springframework.stereotype.Service

@Service
class EphereProfilePicOnPolygonService {

  fun getMetadataFromTokenId(tokenId: Int): ERC721Metadata {
    return when (tokenId) {
      0 -> ERC721Metadata(
        name = "Agu Rodríguez @ Ephere Football",
        image = "https://ipfs.ephere.io/ipfs/QmfEcCop6rb1AriJjmVUHjuVvzASmmRMaTmhyfpN2F4Whe",
        externalUrl = "https://ephere.football",
        description = "Agu Rodríguez",
        backgroundColor = "#000000",
        attributes = null
      )
      else -> throw Error("Not found")
    }
  }

}