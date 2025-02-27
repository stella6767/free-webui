package gg.jte.generated.ondemand.component;
public final class JtechatIdBoxGenerated {
	public static final String JTE_NAME = "component/chatIdBox.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,2,2,2,2,2,2,3,3,3,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Long id) {
		jteOutput.writeContent("\n<input hx-swap-oob=\"true\" id=\"chat-id-box\" name=\"chatId\" type=\"hidden\"");
		var __jte_html_attribute_0 = id;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Long id = (Long)params.get("id");
		render(jteOutput, jteHtmlInterceptor, id);
	}
}
