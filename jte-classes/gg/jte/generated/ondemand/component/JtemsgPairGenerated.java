package gg.jte.generated.ondemand.component;
import java.util.Optional;
import freeapp.life.freeaiweb.entity.MessagePair;
public final class JtemsgPairGenerated {
	public static final String JTE_NAME = "component/msgPair.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,4,4,4,6,6,6,6,6,8,8,8,10,10,10,10,11,11,14,14,14,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MessagePair msg) {
		jteOutput.writeContent("\n<div id=\"msg-pair-");
		jteOutput.setContext("div", "id");
		jteOutput.writeUserContent(msg.getId());
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\">\n    <div class=\"flex justify-end\">\n        <div class=\"bg-[#414158] p-3 rounded-lg max-w-md text-white\">");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(msg.getHumanMessage().getContent());
		jteOutput.writeContent("</div>\n    </div>\n    <div id=\"ai-response-div-");
		jteOutput.setContext("div", "id");
		jteOutput.writeUserContent(msg.getId());
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\" class=\"my-3 p-1\">\n        ");
		jteOutput.writeUnsafeContent(Optional.ofNullable(msg.getAiMessage()).map((val)->val.getContent()).orElse("") );
		jteOutput.writeContent("\n    </div>\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MessagePair msg = (MessagePair)params.get("msg");
		render(jteOutput, jteHtmlInterceptor, msg);
	}
}
