class BootstrapNavbar(val routes: Map<String, String>, active: String? = null) {
    fun render(): String {
        fun dropDown(): String {
            val l: MutableList<String> = mutableListOf()
            val element = """                <li class="nav-item">
                  <a class="nav-link" href="{{key}}">{{value}}</a>
                </li>"""
            this.routes.forEach() { K, V ->
                l.add(element.replace("{{key}}", K).replace("{{value}}", V))
            }
            return l.joinToString("\n")
        }
        val html = """    <!-- BootstrapNavbar.kt -->
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <div class="dropdown d-lg-none">
              <button class="btn btn-primary dropdown-toggle" type="button" onclick="toggleDropdown()">Menu</button>
              <ul class="dropdown-menu" id="myDropdown">
${dropDown()}
              </ul>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"> <span class="navbar-toggler-icon"></span> </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
${dropDown()}
              </ul>
            </div>
          </div>
        </nav>""".trimIndent()
        return html
    }

}