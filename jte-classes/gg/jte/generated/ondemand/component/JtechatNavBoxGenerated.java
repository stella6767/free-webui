package gg.jte.generated.ondemand.component;
import freeapp.life.freeaiweb.dto.ChatRespDto;
import org.springframework.data.domain.Page;
public final class JtechatNavBoxGenerated {
	public static final String JTE_NAME = "component/chatNavBox.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,7,7,8,8,8,8,11,11,12,12,14,14,14,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ChatRespDto chat) {
		jteOutput.writeContent("\n<div class=\"mt-5\" hx-get=\"/chats\" hx-on--after-request=\"javascript:menualInitFlowbite()\"\n     hx-swap=\"innerHTML\" hx-target=\"#chat-nav-list\" hx-trigger=\"load, newChatEvent from:body\"\n        ");
		jteOutput.writeContent("\n     hx-vals='{\"chatId\": \"");
		jteOutput.setContext("div", "hx-vals");
		jteOutput.writeUserContent(chat == null ? 0 : chat.getId());
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\"}'\n     hx-swap-oob=\"true\"\n     id=\"chat-nav-box\">\n    ");
		Page<ChatRespDto> emptyPage = Page.empty();
		jteOutput.writeContent("\n    ");
		gg.jte.generated.ondemand.component.JtechatsNavGenerated.render(jteOutput, jteHtmlInterceptor, chat, emptyPage);
		jteOutput.writeContent("\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ChatRespDto chat = (ChatRespDto)params.get("chat");
		render(jteOutput, jteHtmlInterceptor, chat);
	}
}
