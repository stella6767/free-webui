package gg.jte.generated.ondemand.component;
public final class JteaiSettingModalGenerated {
	public static final String JTE_NAME = "component/aiSettingModal.jte";
	public static final int[] JTE_LINE_INFO = {21,21,21,21,21,21,21,21,21,21,21};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<div aria-hidden=\"true\"\n     class=\"hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full\"\n     id=\"default-modal\" tabindex=\"-1\">\n    <div class=\"relative p-4 w-full max-w-2xl max-h-full\">\n        <div class=\"relative bg-white rounded-lg shadow-sm dark:bg-gray-700\">\n            <div class=\"flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600 border-gray-200\">\n                <h3 class=\"text-xl font-semibold text-gray-900 dark:text-white\">Model Setting</h3>\n                <button class=\"text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white\"\n                        data-modal-hide=\"default-modal\" type=\"button\">\n                    <svg aria-hidden=\"true\" class=\"w-3 h-3\" fill=\"none\" viewbox=\"0 0 14 14\">\n                        <path d=\"m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6\" stroke=\"currentColor\"\n                              stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\"></path>\n                    </svg>\n                    <span class=\"sr-only\">Close modal</span></button>\n            </div>\n            <div hx-get=\"/setting\" hx-trigger=\"load, click from:#ai-setting-btn\" id=\"ai-set-container-view\">\n                <div></div>\n            </div>\n        </div>\n    </div>\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
