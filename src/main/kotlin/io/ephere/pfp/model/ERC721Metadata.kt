package io.ephere.pfp.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.springframework.stereotype.Component

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ERC721Metadata(
  val name: String,
  val image: String,
  val externalUrl: String,
  val description: String,
  val backgroundColor: String,
  val attributes: List<OpenSeaAttribute<*>>?
)

@JsonDeserialize(using = OpenSeaAttributeDeserializer::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
interface OpenSeaAttribute<T> {
  val displayType: String?
  val traitType: String
  val value: T
}

@Component
class OpenSeaAttributeDeserializer : StdDeserializer<OpenSeaAttribute<*>>(OpenSeaAttribute::class.java) {
  override fun deserialize(parser: JsonParser, p1: DeserializationContext?): OpenSeaAttribute<*> {
    val node: JsonNode = parser.codec.readTree(parser)
    val traitType: String = node.get("trait_type").textValue()
    val value: String = node.get("value").asText()

    return if (node.has("max_value")) {
      if (node.has("display_type")) {
        OpenSeaNumberAttribute(
          traitType = traitType,
          value = value.toInt(),
          maxValue = node.get("max_value").intValue()
        )
      } else {
        OpenSeaRankingAttribute(
          traitType = traitType,
          value = value.toInt(),
          maxValue = node.get("max_value").intValue()
        )
      }
    } else {
      OpenSeaStringAttribute(
        traitType = traitType,
        value = value
      )
    }
  }
}

data class OpenSeaNumberAttribute(
  override val traitType: String,
  override val value: Int,
  val maxValue: Int
) : OpenSeaAttribute<Int> {
  override val displayType: String = "number"
}

data class OpenSeaRankingAttribute(
  override val traitType: String,
  override val value: Int,
  val maxValue: Int
) : OpenSeaAttribute<Int> {
  override val displayType: String? = null
}

data class OpenSeaStringAttribute(
  override val traitType: String,
  override val value: String
) : OpenSeaAttribute<String> {
  override val displayType: String? = null
}
