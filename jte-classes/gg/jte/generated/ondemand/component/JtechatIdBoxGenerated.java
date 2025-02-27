package gg.jte.generated.ondemand.component;
public final class JtechatIdBoxGenerated {
	public static final String JTE_NAME = "component/chatIdBox.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,1,1,1,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<input hx-swap-oob=\"true\" id=\"chat-id-box\" name=\"chatId\" type=\"hidden\" value=\"0\">\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
