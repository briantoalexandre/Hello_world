class BootstrapCard(val title: String, val content: Map<String, String> = mapOf("text" to "text")) {
    fun render(): String {
        val html: String = """<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title">${title}</h5>
    <p class="card-text">${content["text"]}</p>
  </div>
</div>""".trimIndent()
        return html
    }

}