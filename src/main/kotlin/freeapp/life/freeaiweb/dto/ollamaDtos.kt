

data class Model(
    val details: Details,
    val digest: String,
    val model: String,
    val modified_at: String,
    val name: String,
    val size: Long
)
data class Details(
    val families: List<String>,
    val family: String,
    val format: String,
    val parameter_size: String,
    val parent_model: String,
    val quantization_level: String
)

data class OllamaListModelDto(
    val models: List<Model>
)
