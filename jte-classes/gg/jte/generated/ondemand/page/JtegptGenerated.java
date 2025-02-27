package gg.jte.generated.ondemand.page;
import freeapp.life.freeaiweb.dto.ChatRespDto;
public final class JtegptGenerated {
	public static final String JTE_NAME = "page/gpt.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,5,5,5,5,6,6,8,8,11,11,11,12,12,12,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ChatRespDto chat) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.component.JteheaderGenerated.render(jteOutput, jteHtmlInterceptor, chat);
				jteOutput.writeContent("\n    <div class=\"ml-64\" id=\"main-container\">\n        ");
				gg.jte.generated.ondemand.component.JtemainContentGenerated.render(jteOutput, jteHtmlInterceptor, chat);
				jteOutput.writeContent("\n    </div>\n\n");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ChatRespDto chat = (ChatRespDto)params.get("chat");
		render(jteOutput, jteHtmlInterceptor, chat);
	}
}
