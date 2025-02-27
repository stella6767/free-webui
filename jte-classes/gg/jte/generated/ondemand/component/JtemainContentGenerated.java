package gg.jte.generated.ondemand.component;
import freeapp.life.freeaiweb.dto.ChatRespDto;
import freeapp.life.freeaiweb.entity.MessagePair;
public final class JtemainContentGenerated {
	public static final String JTE_NAME = "component/mainContent.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,9,9,9,10,10,11,11,36,36,36,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ChatRespDto chat) {
		jteOutput.writeContent("\n<div class=\"flex transition-all duration-300\" id=\"content\">\n    <main class=\"flex flex-col flex-1 h-[90vh] xl:px-48 py-6 transition-all duration-300\">\n        <section class=\"flex-1 p-4 overflow-y-auto space-y-4 no-scrollbar\"\n                 hx-on--after-swap=\"this.scrollTop = this.scrollHeight\" id=\"chatArea\">\n            ");
		for (MessagePair pair : chat.getMessagePairs()) {
			jteOutput.writeContent("\n                ");
			gg.jte.generated.ondemand.component.JtemsgPairGenerated.render(jteOutput, jteHtmlInterceptor, pair);
			jteOutput.writeContent("\n            ");
		}
		jteOutput.writeContent("\n        </section>\n        <div class=\"border-t border-gray-700 p-4\">\n            <form class=\"flex\" hx-include=\"#client-id, #chat-id-box\"\n                  hx-on--after-request=\"javascript:document.getElementById('chatInput').value = ''\"\n                  hx-post=\"/message\" hx-swap=\"beforeend\" hx-target=\"#chatArea\"\n                  hx-trigger=\"keydown[!shiftKey && key=='Enter'] from:#chatInput, click from:#chat-input-btn\"\n                  id=\"chatForm\">\n                        <textarea autocomplete=\"off\"\n                                  class=\"flex-1 px-4 py-2 rounded-l-md bg-gray-700 border border-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500 text-white\"\n                                  id=\"chatInput\" name=\"msg\" placeholder=\"Message AI\" required=\"required\"\n                                  rows=\"3\"></textarea>\n                <button class=\"px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500 text-white font-bold flex items-center justify-center\"\n                        id=\"chat-input-btn\" type=\"submit\">\n                    <svg aria-hidden=\"true\" class=\"w-6 h-6\" fill=\"none\" viewbox=\"0 0 10 14\">\n                        <path d=\"M5 13V1m0 0L1 5m4-4 4 4\" stroke=\"currentColor\" stroke-linecap=\"round\"\n                              stroke-linejoin=\"round\" stroke-width=\"2\"></path>\n                    </svg>\n                </button>\n            </form>\n        </div>\n    </main>\n</div>\n\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ChatRespDto chat = (ChatRespDto)params.get("chat");
		render(jteOutput, jteHtmlInterceptor, chat);
	}
}
