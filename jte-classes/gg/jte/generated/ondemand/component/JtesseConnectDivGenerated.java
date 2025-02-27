package gg.jte.generated.ondemand.component;
public final class JtesseConnectDivGenerated {
	public static final String JTE_NAME = "component/sseConnectDiv.jte";
	public static final int[] JTE_LINE_INFO = {7,7,7,7,7,7,7,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n\n<div>\n    <input id=\"client-id\" name=\"clientId\" type=\"hidden\" value=\"b5897abf-e020-4efa-820f-878d628edd4f\">\n    <div hx-ext=\"sse\" hx-swap-oob=\"true\" id=\"sse-listener\"\n         sse-connect=\"/chat-sse/b5897abf-e020-4efa-820f-878d628edd4f\" sse-swap=\"ai-response\"></div>\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
