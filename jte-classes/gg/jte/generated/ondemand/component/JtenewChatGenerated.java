package gg.jte.generated.ondemand.component;
public final class JtenewChatGenerated {
	public static final String JTE_NAME = "component/newChat.jte";
	public static final int[] JTE_LINE_INFO = {23,23,23,23,23,23,23,23,23,23,23};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n<div id=\"content\">\n    <div class=\"bg-gray-900 h-[80vh] flex items-center justify-center\">\n        <div class=\"text-center\">\n            <h1 class=\"text-2xl font-semibold text-white mb-6\">What can I help with?</h1>\n            <div class=\"flex items-stretch justify-center\" hx-include=\"#client-id, #chat-input-area\"\n                 hx-post=\"/chat\" hx-target=\"#main-container\"\n                 hx-trigger=\"keydown[!shiftKey &amp;&amp; key=='Enter'] from:#chat-input-area, click from:#chat-init-btn\"\n                 id=\"new-chat-msg-box\">\n                        <textarea\n                                class=\"bg-gray-700 w-96 p-3 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500\"\n                                id=\"chat-input-area\" name=\"msg\" placeholder=\"Message AI\" required=\"required\"></textarea>\n                <button class=\"px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500 text-white font-bold flex items-center justify-center\"\n                        id=\"chat-init-btn\" type=\"submit\">\n                    <svg aria-hidden=\"true\" class=\"w-6 h-6\" fill=\"none\" viewbox=\"0 0 10 14\">\n                        <path d=\"M5 13V1m0 0L1 5m4-4 4 4\" stroke=\"currentColor\" stroke-linecap=\"round\"\n                              stroke-linejoin=\"round\" stroke-width=\"2\"></path>\n                    </svg>\n                </button>\n            </div>\n        </div>\n    </div>\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
