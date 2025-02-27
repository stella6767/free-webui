package gg.jte.generated.ondemand.page;
public final class JteindexGenerated {
	public static final String JTE_NAME = "page/index.jte";
	public static final int[] JTE_LINE_INFO = {2,2,2,2,2,2,2,2,3,3,5,5,7,7,7,8,8,8,8,8,8};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.component.JteheaderGenerated.render(jteOutput, jteHtmlInterceptor, null);
				jteOutput.writeContent("\n    <div class=\"ml-64\" id=\"main-container\">\n        ");
				gg.jte.generated.ondemand.component.JtenewChatGenerated.render(jteOutput, jteHtmlInterceptor);
				jteOutput.writeContent("\n    </div>\n");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
