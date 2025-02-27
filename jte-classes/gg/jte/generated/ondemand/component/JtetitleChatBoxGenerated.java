package gg.jte.generated.ondemand.component;
public final class JtetitleChatBoxGenerated {
	public static final String JTE_NAME = "component/titleChatBox.jte";
	public static final int[] JTE_LINE_INFO = {4,4,4,4,4,4,4,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<div class=\"flex items-center w-full\" hx-swap-oob=\"true\" id=\"title-chat-box\">\n    <h1 class=\"text-2xl font-bold text-white ml-3 cursor-pointer\" id=\"header-title\"\n        onclick=\"window.location.href='/'\">Kotlin GPT</h1>\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
