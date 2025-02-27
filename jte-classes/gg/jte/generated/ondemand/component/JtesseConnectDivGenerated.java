package gg.jte.generated.ondemand.component;
public final class JtesseConnectDivGenerated {
	public static final String JTE_NAME = "component/sseConnectDiv.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,5,5,5,5,8,8,8,8,8,8};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		String connectionId = java.util.UUID.randomUUID().toString();
		jteOutput.writeContent("\n\n<div>\n    <input id=\"client-id\" name=\"clientId\" type=\"hidden\"");
		var __jte_html_attribute_0 = connectionId;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n    <div hx-ext=\"sse\" hx-swap-oob=\"true\" id=\"sse-listener\"\n         sse-connect=\"/chat-sse/");
		jteOutput.setContext("div", "sse-connect");
		jteOutput.writeUserContent(connectionId);
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\" sse-swap=\"ai-response\"></div>\n</div>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
