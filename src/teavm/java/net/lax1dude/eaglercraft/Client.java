package net.lax1dude.eaglercraft;

import static org.teavm.jso.webgl.WebGLRenderingContext.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSExceptions;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;
import org.teavm.jso.browser.Window;
import org.teavm.jso.core.JSError;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.webgl.WebGLRenderingContext;

import net.lax1dude.eaglercraft.adapter.DetectAnisotropicGlitch;
import net.lax1dude.eaglercraft.adapter.EaglerAdapterImpl2;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ServerList;

public class Client {
	private static final String crashImage = "data:image/png;base64,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";

	// avoid inlining of constant
	private static String crashImageWrapper() {
		return crashImage.substring(0);
	}
	
	@JSBody(params = { }, script = "if(window.eaglercraftOpts) { return (typeof window.eaglercraftOpts === \"string\") ? window.eaglercraftOpts :"
			+ "JSON.stringify(window.eaglercraftOpts); } else { return null; }")
	private static native String getEaglerOpts();
	
	public static HTMLElement rootElement = null;
	public static Minecraft instance = null;
	public static void main(String[] args) {
		String newArgs = getEaglerOpts();
		if(newArgs != null) {
			crashScreenOptsDump = "window.eaglercraftOpts = " + newArgs;
			try {
				newMain(new JSONObject(newArgs));
			}catch(JSONException ex) {
				Window.alert("There's a JSON syntax error in window.eaglercraftOpts:\n" + ex.toString());
				ex.printStackTrace();
				return;
			}
		}else {
			oldMain();
		}
	}
	
	private static String crashScreenOptsDump = null;

	private static void newMain(JSONObject conf) {

		String containerEl = conf.getString("container");

		rootElement = Window.current().getDocument().getElementById(containerEl);
		if(rootElement == null) {
			throw new JSONException("Container element \"" + containerEl + "\" does not exist in page");
		}

		EaglerAdapterImpl2.setServerToJoinOnLaunch(conf.optString("joinServer", null));

		String assetsURI = conf.getString("assetsURI");
		if(assetsURI.length() > 256) {
			conf.put("assetsURI", assetsURI.substring(0, 256) + " ... ");
			crashScreenOptsDump = "window.eaglercraftOpts = " + conf.toString();
		}
		String serverWorkerURI = conf.optString("serverWorkerURI", null);
		EaglerAdapterImpl2.setWorldDatabaseName(conf.optString("worldsFolder", "MAIN"));
		
		registerErrorHandler();

		try {
			
			EaglerAdapterImpl2.initializeContext(rootElement, assetsURI, serverWorkerURI);

			ServerList.loadDefaultServers(conf);
			AssetRepository.loadOverrides(conf);
			LocalStorageManager.loadStorage();
	
			run0();
			
		}catch(Throwable t) {
			showCrashScreen(t.toString() + "\n\n" + getStackTrace(t));
			return;
		}
	}

	private static void oldMain() {
		String[] e = getOpts();
		crashScreenOptsDump = "window.minecraftOpts = [ ";
		for(int i = 0; i < e.length; ++i) {
			String sh = e[i].length() > 512 ? (e[i].substring(0, 512) + "...") : e[i];
			if(i > 0) {
				crashScreenOptsDump += ", ";
			}
			crashScreenOptsDump += "\"" + sh + "\"";
		}
		crashScreenOptsDump += " ]";
		
		registerErrorHandler();
		
		try {
			
			EaglerAdapterImpl2.initializeContext(rootElement = Window.current().getDocument().getElementById(e[0]), e[1], "worker_bootstrap.js");
			
			LocalStorageManager.loadStorage();
			if(e.length > 2 && e[2].length() > 0) {
				ServerList.loadDefaultServers(e[2]);
			}
			if(e.length > 3) {
				EaglerAdapterImpl2.setServerToJoinOnLaunch(e[3]);
			}
			
			run0();
			
		}catch(Throwable t) {
			showCrashScreen(t.toString() + "\n\n" + getStackTrace(t));
			return;
		}
	}
	
	private static String getStackTrace(Throwable t) {
		JSObject obj = JSExceptions.getJSException(t);
		if(obj != null) {
			JSError err = (JSError)obj;
			return err.getStack() == null ? "[no stack trace]" : err.getStack();
		}else {
			return "[no stack trace]";
		}
	}

	private static void run0() {
		System.out.println(" -------- starting minecraft -------- ");
		instance = new Minecraft();
		run1();
	}

	private static void run1() {
		instance.run();
	}

	@JSBody(params = { }, script = "return window.minecraftOpts;")
	public static native String[] getOpts();
	
	public static void registerErrorHandler() {
		setWindowErrorHandler(new WindowErrorHandler() {

			@Override
			public void call(String message, String file, int line, int col, JSError error) {
				StringBuilder str = new StringBuilder();
				
				str.append("Native Browser Exception\n");
				str.append("----------------------------------\n");
				str.append("  Line: ").append((file == null ? "unknown" : file) + ":" + line + ":" + col).append('\n');
				str.append("  Type: ").append(error == null ? "generic" : error.getName()).append('\n');
				
				if(error != null) {
					str.append("  Desc: ").append(error.getMessage() == null ? "null" : error.getMessage()).append('\n');
				}
				
				if(message != null) {
					if(error == null || error.getMessage() == null || !message.endsWith(error.getMessage())) {
						str.append("  Desc: ").append(message).append('\n');
					}
				}
				
				str.append("----------------------------------\n\n");
				str.append(error.getStack() == null ? "No stack trace is available" : error.getStack()).append('\n');
				
				showCrashScreen(str.toString());
			}
			
		});
	}

	@JSFunctor
	private static interface WindowErrorHandler extends JSObject {
		void call(String message, String file, int line, int col, JSError error);
	}
	
	@JSBody(params = { "handler" }, script = "window.addEventListener(\"error\", function(e) { handler("
			+ "(typeof e.message === \"string\") ? e.message : null,"
			+ "(typeof e.filename === \"string\") ? e.filename : null,"
			+ "(typeof e.lineno === \"number\") ? e.lineno : 0,"
			+ "(typeof e.colno === \"number\") ? e.colno : 0,"
			+ "(typeof e.error === \"undefined\") ? null : e.error); });")
	public static native void setWindowErrorHandler(WindowErrorHandler handler);

	private static boolean isCrashed = false;

	private static void showCrashScreen(String t) {
		if(!isCrashed) {
			isCrashed = true;

			StringBuilder str = new StringBuilder();
			str.append("Game Crashed! I have fallen and I can't get up! If this has happened more than once then please copy the text on this screen and publish it in the issues feed of this fork's GitHub repository.\n\nThe URL to this fork's GitHub repository is: " + ConfigConstants.forkMe + "\n\n");
			str.append(t);
			str.append('\n').append('\n');
			str.append("kone Client.version = \"").append(ConfigConstants.version).append("\"\n");
			str.append("kone Client.minecraft = \"1.5.2\"\n");
			str.append("kone Client.brand = \"kone\"\n");
			str.append("kone Client.username = \"").append(EaglerProfile.username).append("\"\n");
			str.append('\n');
			str.append(addWebGLToCrash());
			str.append('\n');
			str.append(crashScreenOptsDump).append('\n');
			str.append('\n');
			addDebugNav(str, "userAgent");
			addDebugNav(str, "vendor");
			addDebugNav(str, "language");
			addDebugNav(str, "hardwareConcurrency");
			addDebugNav(str, "deviceMemory");
			addDebugNav(str, "platform");
			addDebugNav(str, "product");
			str.append('\n');
			str.append("rootElement.clientWidth = ").append(rootElement.getClientWidth()).append('\n');
			str.append("rootElement.clientHeight = ").append(rootElement.getClientHeight()).append('\n');
			addDebug(str, "innerWidth");
			addDebug(str, "innerHeight");
			addDebug(str, "outerWidth");
			addDebug(str, "outerHeight");
			addDebug(str, "devicePixelRatio");
			addDebugScreen(str, "availWidth");
			addDebugScreen(str, "availHeight");
			addDebugScreen(str, "colorDepth");
			addDebugScreen(str, "pixelDepth");
			str.append('\n');
			addDebugLocation(str, "href");
			str.append("\n----- Begin Minecraft Config -----\n");
			str.append(LocalStorageManager.dumpConfiguration());
			str.append("\n----- End Minecraft Config -----\n\n");
			addDebug(str, "minecraftServer");

			String s = rootElement.getAttribute("style");
			rootElement.setAttribute("style", (s == null ? "" : s) + "position:relative;");
			HTMLDocument doc = Window.current().getDocument();
			HTMLElement img = doc.createElement("img");
			HTMLElement div = doc.createElement("div");
			img.setAttribute("style", "z-index:100;position:absolute;top:10px;left:calc(50% - 151px);");
			img.setAttribute("src", crashImageWrapper());
			div.setAttribute("style", "z-index:100;position:absolute;top:135px;left:10%;right:10%;bottom:30px;background-color:white;border:1px solid #cccccc;overflow-x:hidden;overflow-y:scroll;overflow-wrap:break-word;white-space:pre-wrap;font: 14px monospace;padding:10px;");
			rootElement.appendChild(img);
			rootElement.appendChild(div);
			div.appendChild(doc.createTextNode(str.toString()));
			
			EaglerAdapterImpl2.removeEventHandlers();

		}
	}

	private static String addWebGLToCrash() {
		StringBuilder ret = new StringBuilder();
		
		WebGLRenderingContext ctx = EaglerAdapterImpl2.webgl;
		
		if(ctx == null) {
			HTMLCanvasElement cvs = (HTMLCanvasElement) Window.current().getDocument().createElement("canvas");
			
			cvs.setWidth(64);
			cvs.setHeight(64);
			
			ctx = (WebGLRenderingContext)cvs.getContext("webgl");
		}
		
		if(ctx != null) {
			if(EaglerAdapterImpl2.webgl != null) {
				ret.append("webgl.version = ").append(ctx.getParameterString(VERSION)).append('\n');
			}
			if(ctx.getExtension("WEBGL_debug_renderer_info") != null) {
				ret.append("webgl.renderer = ").append(ctx.getParameterString(/* UNMASKED_RENDERER_WEBGL */ 0x9246)).append('\n');
				ret.append("webgl.vendor = ").append(ctx.getParameterString(/* UNMASKED_VENDOR_WEBGL */ 0x9245)).append('\n');
			}else {
				ret.append("webgl.renderer = ").append("" + ctx.getParameterString(RENDERER) + " [masked]").append('\n');
				ret.append("webgl.vendor = ").append("" + ctx.getParameterString(VENDOR) + " [masked]").append('\n');
			}
			ret.append("\nwebgl.anisotropicGlitch = ").append(DetectAnisotropicGlitch.hasGlitch()).append('\n');
		}else {
			ret.append("Failed to query GPU info!\n");
		}
		
		return ret.toString();
	}

	public static void showIncompatibleScreen(String t) {
		if(!isCrashed) {
			isCrashed = true;
			
			String s = rootElement.getAttribute("style");
			rootElement.setAttribute("style", (s == null ? "" : s) + "position:relative;");
			HTMLDocument doc = Window.current().getDocument();
			HTMLElement img = doc.createElement("img");
			HTMLElement div = doc.createElement("div");
			img.setAttribute("style", "z-index:100;position:absolute;top:10px;left:calc(50% - 151px);");
			img.setAttribute("src", crashImageWrapper());
			div.setAttribute("style", "z-index:100;position:absolute;top:135px;left:10%;right:10%;bottom:30px;background-color:white;border:1px solid #cccccc;overflow-x:hidden;overflow-y:scroll;font:18px sans-serif;padding:40px;");
			rootElement.appendChild(img);
			rootElement.appendChild(div);
			div.setInnerHTML("<h2><svg style=\"vertical-align:middle;margin:0px 16px 8px 8px;\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\" fill=\"none\"><path stroke=\"#000000\" stroke-width=\"3\" stroke-linecap=\"square\" d=\"M1.5 8.5v34h45v-28m-3-3h-10v-3m-3-3h-10m15 6h-18v-3m-3-3h-10\"/><path stroke=\"#000000\" stroke-width=\"2\" stroke-linecap=\"square\" d=\"M12 21h0m0 4h0m4 0h0m0-4h0m-2 2h0m20-2h0m0 4h0m4 0h0m0-4h0m-2 2h0\"/><path stroke=\"#000000\" stroke-width=\"2\" stroke-linecap=\"square\" d=\"M20 30h0 m2 2h0 m2 2h0 m2 2h0 m2 -2h0 m2 -2h0 m2 -2h0\"/></svg> This device is incompatible with kone Client&ensp;:(</h2>"
					+ "<div style=\"margin-left:40px;\">"
					+ "<p style=\"font-size:1.2em;\"><b style=\"font-size:1.1em;\">Issue:</b> <span style=\"color:#BB0000;\" id=\"crashReason\"></span><br /></p>"
					+ "<p style=\"margin-left:10px;font:0.9em monospace;\" id=\"crashUserAgent\"></p>"
					+ "<p style=\"margin-left:10px;font:0.9em monospace;\" id=\"crashWebGL\"></p>"
					+ "<p><br /><span style=\"font-size:1.1em;border-bottom:1px dashed #AAAAAA;padding-bottom:5px;\">Things you can try:</span></p>"
					+ "<ol>"
					+ "<li><span style=\"font-weight:bold;\">Just try using kone Client on a different device</span>, it isn't a bug it's common sense</li>"
					+ "<li style=\"margin-top:7px;\">If you are on a mobile device, please try a proper desktop or a laptop computer</li>"
					+ "<li style=\"margin-top:7px;\">If you are using a device with no mouse cursor, please use a device with a mouse cursor</li>"
					+ "<li style=\"margin-top:7px;\">If you are not using Chrome/Edge, try installing the latest Google Chrome</li>"
					+ "<li style=\"margin-top:7px;\">If your browser is out of date, please update it to the latest version</li>"
					+ "<li style=\"margin-top:7px;\">If you are using an old OS such as Windows 7, please try Windows 10 or 11</li>"
					+ "<li style=\"margin-top:7px;\">If you have a GPU launched before 2009, WebGL 2.0 support may be impossible</li>"
					+ "</ol>"
					+ "</div>");
			
			div.querySelector("#crashReason").appendChild(doc.createTextNode(t));
			div.querySelector("#crashUserAgent").appendChild(doc.createTextNode(getStringNav("userAgent")));
			
			EaglerAdapterImpl2.removeEventHandlers();
			
			String webGLRenderer = "No GL_RENDERER string could be queried";
			
			try {
				HTMLCanvasElement cvs = (HTMLCanvasElement) Window.current().getDocument().createElement("canvas");
				
				cvs.setWidth(64);
				cvs.setHeight(64);
				
				WebGLRenderingContext ctx = (WebGLRenderingContext)cvs.getContext("webgl");
				
				if(ctx != null) {
					String r;
					if(ctx.getExtension("WEBGL_debug_renderer_info") != null) {
						r = ctx.getParameterString(/* UNMASKED_RENDERER_WEBGL */ 0x9246);
					}else {
						r = ctx.getParameterString(RENDERER);
						if(r != null) {
							r += " [masked]";
						}
					}
					if(r != null) {
						webGLRenderer = r;
					}
				}
			}catch(Throwable tt) {
			}
			
			div.querySelector("#crashWebGL").appendChild(doc.createTextNode(webGLRenderer));
			
		}
	}

	@JSBody(params = { "v" }, script = "try { return \"\"+window[v]; } catch(e) { return \"<error>\"; }")
	private static native String getString(String var);

	@JSBody(params = { "v" }, script = "try { return \"\"+window.navigator[v]; } catch(e) { return \"<error>\"; }")
	private static native String getStringNav(String var);

	@JSBody(params = { "v" }, script = "try { return \"\"+window.screen[v]; } catch(e) { return \"<error>\"; }")
	private static native String getStringScreen(String var);

	@JSBody(params = { "v" }, script = "try { return \"\"+window.location[v]; } catch(e) { return \"<error>\"; }")
	private static native String getStringLocation(String var);

	private static void addDebug(StringBuilder str, String var) {
		str.append("window.").append(var).append(" = ").append(getString(var)).append('\n');
	}

	private static void addDebugNav(StringBuilder str, String var) {
		str.append("window.navigator.").append(var).append(" = ").append(getStringNav(var)).append('\n');
	}

	private static void addDebugScreen(StringBuilder str, String var) {
		str.append("window.screen.").append(var).append(" = ").append(getStringScreen(var)).append('\n');
	}

	private static void addDebugLocation(StringBuilder str, String var) {
		str.append("window.location.").append(var).append(" = ").append(getStringLocation(var)).append('\n');
	}

	private static void addArray(StringBuilder str, String var) {
		str.append("window.").append(var).append(" = ").append(getArray(var)).append('\n');
	}

	@JSBody(params = { "v" }, script = "try { return (typeof window[v] !== \"undefined\") ? JSON.stringify(window[v]) : \"[\\\"<error>\\\"]\"; } catch(e) { return \"[\\\"<error>\\\"]\"; }")
	private static native String getArray(String var);

}
