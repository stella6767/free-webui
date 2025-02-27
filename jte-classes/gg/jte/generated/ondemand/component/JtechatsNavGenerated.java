package gg.jte.generated.ondemand.component;
import freeapp.life.freeaiweb.dto.ChatRespDto;
import org.springframework.data.domain.Page;
import java.util.function.Function;
import gg.jte.Content;
public final class JtechatsNavGenerated {
	public static final String JTE_NAME = "component/chatsNav.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,9,9,9,9,11,11,13,13,15,15,15,15,16,16,16,16,26,26,29,29,29,29,29,29,29,29,38,38,38,38,40,40,40,40,41,41,41,41,42,42,42,42,44,44,44,44,47,47,47,47,49,49,49,49,57,57,59,59,59,63,63,65,65,65,65,69,69,69,71,71,73,73,73,75,75,81,81,81,5,6,6,6,6};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ChatRespDto currentChat, Page<ChatRespDto> chats) {
		jteOutput.writeContent("\n\n");
		Function<Page<ChatRespDto>,Content> component = (chatList) -> new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <ul class=\"space-y-2\">\n        ");
				for (ChatRespDto chat : chatList.getContent()) {
					jteOutput.writeContent("\n\n            ");
					var selectedColor = (currentChat != null && chat.getId() == currentChat.getId()) ? "bg-[#b4b4b480]" : "";
					jteOutput.writeContent("\n\n            <div class=\"flex truncate group cursor-pointer tab-active hover:bg-[#b4b4b480] ");
					jteOutput.setContext("div", "class");
					jteOutput.writeUserContent(selectedColor);
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\"\n                 id=\"chat-li-");
					jteOutput.setContext("div", "id");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\"\n                 hx-on--after-on-load=\"let currentTab = document.querySelector('#chat-nav-list');\n                        if (currentTab) {\n                            currentTab.classList.remove('bg-[#b4b4b480]');\n                            currentTab.querySelectorAll('.bg-\\\\[\\\\#b4b4b480\\\\]').forEach(el =&gt; {\n                            el.classList.remove('bg-[#b4b4b480]');\n                          });\n                        }\n                        let newTab = event.currentTarget\n                        newTab.classList.add('bg-[#b4b4b480]') \">\n                ");
					gg.jte.generated.ondemand.component.JtechatNameBoxGenerated.render(jteOutput, jteHtmlInterceptor, chat);
					jteOutput.writeContent("\n\n                <div class=\"w-1/6 cursor-pointer opacity-0 group-hover:opacity-100 transition-opacity duration-200\"\n                     id=\"dropdown-btn-");
					jteOutput.setContext("div", "id");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\" data-dropdown-toggle=\"dropdown-");
					jteOutput.setContext("div", "data-dropdown-toggle");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\">\n                    <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"icon-md z-50\" width=\"24\" height=\"24\"\n                         viewbox=\"0 0 24 24\" fill=\"none\">\n                        <path fill-rule=\"evenodd\" clip-rule=\"evenodd\"\n                              d=\"M3 12C3 10.8954 3.89543 10 5 10C6.10457 10 7 10.8954 7 12C7 13.1046 6.10457 14 5 14C3.89543 14 3 13.1046 3 12ZM10 12C10 10.8954 10.8954 10 12 10C13.1046 10 14 10.8954 14 12C14 13.1046 13.1046 14 12 14C10.8954 14 10 13.1046 10 12ZM17 12C17 10.8954 17.8954 10 19 10C20.1046 10 21 10.8954 21 12C21 13.1046 20.1046 14 19 14C17.8954 14 17 13.1046 17 12Z\"\n                              fill=\"currentColor\"></path>\n                    </svg>\n                </div>\n                <div class=\"z-50 hidden bg-white divide-y divide-gray-100 rounded-lg shadow-sm w-44 dark:bg-gray-700\"\n                     id=\"dropdown-");
					jteOutput.setContext("div", "id");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\">\n                    <ul class=\"py-2 text-sm text-gray-700 dark:text-gray-200\"\n                        aria-labelledby=\"dropdown-btn-");
					jteOutput.setContext("ul", "aria-labelledby");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("ul", null);
					jteOutput.writeContent("\">\n                        <li hx-get=\"/chat/rename/");
					jteOutput.setContext("li", "hx-get");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("li", null);
					jteOutput.writeContent("\" hx-trigger=\"click\"\n                            hx-target=\"#chat-name-div-");
					jteOutput.setContext("li", "hx-target");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("li", null);
					jteOutput.writeContent("\"\n                            hx-swap=\"outerHTML\" hx-ext=\"debug\"\n                            hx-vals='{\"name\": \"");
					jteOutput.setContext("li", "hx-vals");
					jteOutput.writeUserContent(chat.getName());
					jteOutput.setContext("li", null);
					jteOutput.writeContent("\" } '>\n                            <span class=\"block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white\">Rename</span>\n                        </li>\n                        <li hx-delete=\"/chat/");
					jteOutput.setContext("li", "hx-delete");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("li", null);
					jteOutput.writeContent("\" hx-confirm=\"Are you sure you wish to delete?\"\n                            hx-trigger=\"click\"\n                            hx-target=\"#chat-li-");
					jteOutput.setContext("li", "hx-target");
					jteOutput.writeUserContent(chat.getId());
					jteOutput.setContext("li", null);
					jteOutput.writeContent("\"\n                            hx-swap=\"delete\"\n                        ><span\n                                    class=\"block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white\">Delete</span>\n                        </li>\n                    </ul>\n                </div>\n            </div>\n        ");
				}
				jteOutput.writeContent("\n    </ul>\n");
			}
		};
		jteOutput.writeContent("\n\n\n\n");
		if (!chats.isLast()) {
			jteOutput.writeContent("\n    <nav class=\"mt-2\"\n         hx-get=\"/chats?page=");
			jteOutput.setContext("nav", "hx-get");
			jteOutput.writeUserContent(chats.getPageable().getPageNumber() + 1);
			jteOutput.setContext("nav", null);
			jteOutput.writeContent("\"\n         hx-trigger=\"revealed\"\n         hx-on--after-request=\"javascript:menualInitFlowbite()\"\n         hx-swap=\"afterend\">\n        ");
			jteOutput.setContext("nav", null);
			jteOutput.writeUserContent(component.apply(chats));
			jteOutput.writeContent("\n    </nav>\n");
		} else {
			jteOutput.writeContent("\n    <nav class=\"mt-2\">\n        ");
			jteOutput.setContext("nav", null);
			jteOutput.writeUserContent(component.apply(chats));
			jteOutput.writeContent("\n    </nav>\n");
		}
		jteOutput.writeContent("\n\n\n\n\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ChatRespDto currentChat = (ChatRespDto)params.get("currentChat");
		Page<ChatRespDto> chats = (Page<ChatRespDto>)params.get("chats");
		render(jteOutput, jteHtmlInterceptor, currentChat, chats);
	}
}
