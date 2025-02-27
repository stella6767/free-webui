package gg.jte.generated.ondemand.page;
public final class JtetestGenerated {
	public static final String JTE_NAME = "page/test.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,1,1,1,1,2,2,2,2,2,2,4,4,4,7,7,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n");
		java.util.function.Function<String,gg.jte.Content> component = (s) -> new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div>");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(s);
				jteOutput.writeContent("</div>");
			}
		};
		jteOutput.writeContent("\n\n");
		jteOutput.setContext("html", null);
		jteOutput.writeUserContent(component.apply("foo"));
		jteOutput.writeContent("\n\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
