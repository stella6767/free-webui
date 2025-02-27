package gg.jte.generated.ondemand;
public final class JteexampleGenerated {
	public static final String JTE_NAME = "example.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,3,3,3,3,4,4,4,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, freeapp.life.freeaiweb.controller.ExampleController.DemoModel model) {
		jteOutput.writeContent("\nHello ");
		jteOutput.setContext("html", null);
		jteOutput.writeUserContent(model.getText());
		jteOutput.writeContent("!\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		freeapp.life.freeaiweb.controller.ExampleController.DemoModel model = (freeapp.life.freeaiweb.controller.ExampleController.DemoModel)params.get("model");
		render(jteOutput, jteHtmlInterceptor, model);
	}
}
