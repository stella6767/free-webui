package gg.jte.generated.ondemand.component;
import freeapp.life.freeaiweb.dto.OllamaResponseRto;
import freeapp.life.freeaiweb.dto.AIModelDto;
public final class JtesettingModalFormGenerated {
	public static final String JTE_NAME = "component/settingModalForm.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,14,14,14,15,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,17,17,25,25,25,25,25,25,25,25,25,28,28,28,28,28,28,28,28,28,32,32,33,33,33,34,34,40,40,40,40,40,40,40,40,40,44,44,45,45,45,46,46,54,54,54,54,54,54,54,54,54,63,63,63,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, OllamaResponseRto setting) {
		jteOutput.writeContent("\n\n<form class=\"\" hx-put=\"/setting\" hx-swap=\"none\" hx-on--after-swap=\"closeModal()\">\n    <div class=\"p-4 md:p-5 space-y-4\">\n        <div class=\"mb-5\">\n            <label for=\"models\"\n                   class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Model</label><select\n                    id=\"models\" name=\"model\"\n                    class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\">\n\n                ");
		for (AIModelDto model : setting.getModels()) {
			jteOutput.writeContent("\n                    <option");
			var __jte_html_attribute_0 = model.getModel();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" value=\"");
				jteOutput.setContext("option", "value");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("option", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent("\n                           ");
			var __jte_html_attribute_1 = setting.getCurrentModel().equals(model.getModel());
			if (__jte_html_attribute_1) {
			jteOutput.writeContent(" selected");
			}
			jteOutput.writeContent(">");
			jteOutput.setContext("option", null);
			jteOutput.writeUserContent(model.getModel());
			jteOutput.writeContent("</option>\n                ");
		}
		jteOutput.writeContent("\n\n            </select>\n        </div>\n        <div class=\"mb-5\">\n            <label for=\"host\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Your\n                Host</label><input name=\"host\" type=\"text\" id=\"host\"\n                                   class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\"\n                                  ");
		var __jte_html_attribute_2 = setting.getHost();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </div>\n        <div class=\"mb-5\"><label for=\"temperature\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Temperature</label><input\n                    id=\"temperature\" name=\"temperature\" type=\"range\"");
		var __jte_html_attribute_3 = setting.getTemperature();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_3);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" min=\"0\" max=\"1\"\n                    step=\"0.1\"\n                    class=\"w-full h-2 bg-gray-200 rounded-lg cursor-pointer dark:bg-gray-600\">\n            <div class=\"flex w-full justify-between px-2 text-xs\">\n                ");
		for (int i = 0; i <= 10; ++i) {
			jteOutput.writeContent("\n                    <span>");
			jteOutput.setContext("span", null);
			jteOutput.writeUserContent(String.format("%.1f", i / 10.0));
			jteOutput.writeContent("</span>\n                ");
		}
		jteOutput.writeContent("\n            </div>\n        </div>\n\n\n        <div class=\"mb-5\"><label for=\"topp\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Top\n                P</label><input id=\"topp\" type=\"range\" name=\"topP\"");
		var __jte_html_attribute_4 = setting.getTopP();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_4);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" min=\"0\" max=\"1\"\n                                step=\"0.1\"\n                                class=\"w-full h-2 bg-gray-200 rounded-lg cursor-pointer dark:bg-gray-700\">\n            <div class=\"flex w-full justify-between px-2 text-xs\">\n                ");
		for (int i = 0; i <= 10; ++i) {
			jteOutput.writeContent("\n                    <span>");
			jteOutput.setContext("span", null);
			jteOutput.writeUserContent(String.format("%.1f", i / 10.0));
			jteOutput.writeContent("</span>\n                ");
		}
		jteOutput.writeContent("\n            </div>\n        </div>\n        <div class=\"mb-5\"><label for=\"number-input\"\n                                 class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Top\n                K</label><input type=\"number\" id=\"number-input\" name=\"topK\" max=\"100\" min=\"0\"\n                                aria-describedby=\"helper-text-explanation\"\n                                class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\"\n                               ");
		var __jte_html_attribute_5 = setting.getTopK();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_5);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required=\"\"></div>\n    </div>\n    <div class=\"flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600 \">\n        <button type=\"submit\"\n                class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800\">\n            Change\n        </button>\n    </div>\n</form>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		OllamaResponseRto setting = (OllamaResponseRto)params.get("setting");
		render(jteOutput, jteHtmlInterceptor, setting);
	}
}
