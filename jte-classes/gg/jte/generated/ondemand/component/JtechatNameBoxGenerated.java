package gg.jte.generated.ondemand.component;
import freeapp.life.freeaiweb.dto.ChatRespDto;
import java.util.Optional;
public final class JtechatNameBoxGenerated {
	public static final String JTE_NAME = "component/chatNameBox.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,6,6,6,6,6,8,8,8,8,12,12,12,14,14,14,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ChatRespDto chat) {
		jteOutput.writeContent("\n<div class=\"w-5/6 text-gray-300 hover:text-white\"\n     id=\"chat-name-div-");
		jteOutput.setContext("div", "id");
		jteOutput.writeUserContent(chat != null ? chat.getId() : 0L);
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\"\n     hx-trigger=\"click\"\n     hx-get=\"/chat/");
		jteOutput.setContext("div", "hx-get");
		jteOutput.writeUserContent(chat != null ? chat.getId() : 0L);
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\"\n     hx-target=\"#main-container\"\n     hx-swap=\"innerHTML scroll:#chatArea:bottom\"\n     hx-push-url=\"true\">\n    ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(chat != null ? chat.getName() : "");
		jteOutput.writeContent("\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ChatRespDto chat = (ChatRespDto)params.get("chat");
		render(jteOutput, jteHtmlInterceptor, chat);
	}
}
