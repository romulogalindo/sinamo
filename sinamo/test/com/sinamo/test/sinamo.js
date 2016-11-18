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
function _func5(_R) {
    var R1 = API.SQL("_native", "select * from system.tbusuari where _id=" + _R.getParameter('r1') + ";");
    var _R = new java.util.HashMap();
    _R.put('R1', R1);
    return _R;
}
function _act5(_requestStringValue) {}
function _func2(_R) {
    var R1 = API.SQL("_native", "select _id, nickname, (lastname || ' ' || name) as fullname, mail,'person'\\:\\:varchar as icon,'Admin'\\:\\:varchar as type from system.tbusuari;");
    var _R = new java.util.HashMap();
    _R.put('R1', R1);
    return _R;
}
function _act2(_requestStringValue) {
    var _R = JSON.parse(_requestStringValue);
    API.LOG('[JS] _R.xactName=' + _R.xactName);
    if (_R.xactName == 'xact1') {
        var goTo = new ResponseGoTo();
        goTo.addValue('r1', _R.map.r1);
        goTo.setGoTo('5');
        return goTo;
    }
}
function _func1(_R) {}
function _act1(_requestStringValue) {}
function _func3(_R) {}
function _act3(_requestStringValue) {}
function _func4(_R) {}
function _act4(_requestStringValue) {}