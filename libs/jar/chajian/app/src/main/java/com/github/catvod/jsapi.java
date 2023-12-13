package com.github.catvod;

import androidx.annotation.Keep;

import com.google.gson.Gson;
import com.quickjs.android.JSMethod;
import com.quickjs.android.QuickJSContext;

public class jsapi extends HtmlParser {
    QuickJSContext jsContext;

    public jsapi(QuickJSContext jsContext) {
        this.jsContext = jsContext;
    }
    @Keep
    public class test {
        @Keep
        @JSMethod
        public int add(int a, int b) {
            return a + b;
        }
        @Keep
        @JSMethod
        public Object hexToBytes( String code) {
            code = "let hexString = \"" + code + "\";\n" +
                    "let MAP_HEX = {\n" +
                    "    0: 0,\n" +
                    "    1: 1,\n" +
                    "    2: 2,\n" +
                    "    3: 3,\n" +
                    "    4: 4,\n" +
                    "    5: 5,\n" +
                    "    6: 6,\n" +
                    "    7: 7,\n" +
                    "    8: 8,\n" +
                    "    9: 9,\n" +
                    "    a: 10,\n" +
                    "    b: 11,\n" +
                    "    c: 12,\n" +
                    "    d: 13,\n" +
                    "    e: 14,\n" +
                    "    f: 15,\n" +
                    "    A: 10,\n" +
                    "    B: 11,\n" +
                    "    C: 12,\n" +
                    "    D: 13,\n" +
                    "    E: 14,\n" +
                    "    F: 15\n" +
                    "};\n" +
                    "    let bytes = new Uint8Array(Math.floor((hexString || \"\").length / 2));\n" +
                    "    let i;\n" +
                    "    for (i = 0; i < bytes.length; i++) {\n" +
                    "        let a = MAP_HEX[hexString[i * 2]];\n" +
                    "        let b = MAP_HEX[hexString[i * 2 + 1]];\n" +
                    "        if (a === undefined || b === undefined) {\n" +
                    "            break;\n" +
                    "        }\n" +
                    "        bytes[i] = (a << 4) | b;\n" +
                    "    }\n" +
                    "    i === bytes.length ? bytes : bytes.slice(0, i)";
            return jsContext.evaluate(code);
        }
    }
    @Keep
    public class test1 {
        @Keep
        @JSMethod
        public int add1(int a, int b) {
            return a + b;
        }

    }
    @Keep
    public class htmlParser {

        @Keep
        @JSMethod
        public String joinUrl(String parent, String child) {
            return pjoinUrl(parent, child);
        }
        @Keep
        @JSMethod
        public String pdfh(String html, String rule) {
            try {
                return parseDomForUrl(html, rule, "");
            } catch (Exception th) {
                return "";
            }
        }
        @Keep
        @JSMethod
        public String pd(String html, String rule, String urlKey) {
            try {
                return parseDomForUrl(html, rule, urlKey);
            } catch (Exception th) {
                return "";
            }
        }
        @Keep
        @JSMethod
        public Object pdfa(String html, String rule) {
            try {
                return jsContext.parse(new Gson().toJson(parseDomForArray(html, rule)));
            } catch (Exception th) {
                return jsContext.createNewJSArray();
            }
        }

        @Keep
        @JSMethod
        public Object pdfl(String html, String p1, String list_text, String list_url, String urlKey) {
            try {
                return jsContext.parse(new Gson().toJson(parseDomForList(html, p1, list_text, list_url, urlKey)));
            } catch (Exception th) {
                return jsContext.createNewJSArray();
            }
        }
    }
}
