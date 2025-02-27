package gg.jte.generated.ondemand.layout;
public final class JtemainGenerated {
	public static final String JTE_NAME = "layout/main.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,20,20,20,20,24,24,24,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, gg.jte.Content content) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html>\n<head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n    <script defer src=\"/js/htmx.min.js\"></script>\n    <script defer src=\"/js/sse.js\"></script>\n    <script defer src=\"/js/custom-htmx.js\"></script>\n    <script defer src=\"/js/tailwind.min.js\"></script>\n    <script defer src=\"/js/flowbite.min.js\"></script>\n    <link href=\"/css/flowbite.min.css\" rel=\"stylesheet\">\n    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\">\n    <meta charset=\"UTF-8\">\n    <meta content=\"stella6767\" name=\"author\">\n    <meta content=\"Kotlin,htmx\" name=\"keywords\">\n    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">\n</head>\n<body xmlns:hx-on=\"http://www.w3.org/1999/xhtml\" class=\"bg-gray-900 text-gray-200 font-sans\" id=\"content-body\">\n\n");
		jteOutput.setContext("body", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		gg.jte.Content content = (gg.jte.Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, content);
	}
}
