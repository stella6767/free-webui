package gg.jte.generated.ondemand.component;
public final class JtetitleChatBoxGenerated {
	public static final String JTE_NAME = "component/titleChatBox.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,5,5,5,10,10,10,11,11,15,15,15,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String name) {
		jteOutput.writeContent("\n<div class=\"flex items-center w-full\" hx-swap-oob=\"true\" id=\"title-chat-box\">\n    <h1 class=\"text-2xl font-bold text-white ml-3 cursor-pointer\" id=\"header-title\"\n        onclick=\"window.location.href='/'\">Kotlin GPT</h1>\n    ");
		if (org.springframework.util.StringUtils.hasText(name)) {
			jteOutput.writeContent("\n        <svg class=\"mx-3 w-4 h-4 text-gray-800 dark:text-white\" aria-hidden=\"true\" fill=\"none\" viewBox=\"0 0 8 14\">\n            <path stroke=\"currentColor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\"\n                  d=\"m1 13 5.7-5.326a.909.909 0 0 0 0-1.348L1 1\"></path>\n        </svg>\n        <h2 class=\"text-xl font-bold text-white\">");
			jteOutput.setContext("h2", null);
			jteOutput.writeUserContent(name);
			jteOutput.writeContent("</h2>\n    ");
		}
		jteOutput.writeContent("\n\n</div>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String name = (String)params.get("name");
		render(jteOutput, jteHtmlInterceptor, name);
	}
}
