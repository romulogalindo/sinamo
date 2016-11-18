var API = Java.type("com.sinamo.kernel.SinamoFactory");
var ResponseGoTo = Java.type("com.sinamo.bean.items.ResponseGoTo");

function xx(_R) {
    var R1 = API.SQL("_native", "select * from system.tbusuari where _id=" + _R.getParameter('r1') + ";");
    var _R = new java.util.HashMap();
    _R.put('R1', R1);
    return _R;
}

function _goto(script) {
    var sr = JSON.parse(script);
    return sr.funcName;
}