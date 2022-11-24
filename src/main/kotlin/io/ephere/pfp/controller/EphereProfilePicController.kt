package io.ephere.pfp.controller

import io.ephere.pfp.model.ERC721Metadata
import io.ephere.pfp.service.EphereProfilePicOnPolygonService
import io.ephere.pfp.service.EphereProfilePicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/erc721")
class EphereProfilePicController(
  private val ephereProfilePicService: EphereProfilePicService,
  private val ephereProfilePicOnPolygonService: EphereProfilePicOnPolygonService
) {

  @GetMapping("/{tokenId}")
  fun getById(@PathVariable tokenId: Int): ERC721Metadata {
    return ephereProfilePicService.getMetadataFromTokenId(tokenId)
  }

  @GetMapping("/polygon/{tokenId}")
  fun getByIdOnPolygon(@PathVariable tokenId: Int): ERC721Metadata {
    return ephereProfilePicOnPolygonService.getMetadataFromTokenId(tokenId)
  }

}
