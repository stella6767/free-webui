@import freeapp.life.freeaiweb.dto.ChatRespDto

@param ChatRespDto chat


@template.layout.main(content = @`
    @template.component.header(chat = chat)
    <div class="ml-64" id="main-container">
        @template.component.mainContent(chat = chat)
    </div>

`)
