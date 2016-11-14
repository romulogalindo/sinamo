var API = Java.type("com.sinamo.kernel.SinamoFactory");
var ResponseGoTo = Java.type("com.sinamo.bean.items.ResponseGoTo");

function xx(_R) {
    var R1 = API.SQL("_native", "select * from system.tbusuari where _id=" + _R.getParameter('r1') + ";");
    var _R = new java.util.HashMap();
    _R.put('R1', R1);
    return _R;
}

function goto(_R) {
    if (_R.id == '_act1') {
        var goTo = new ResponseGoTo();
        goTo.addValue('r1', _R.GET('_id'));
        goTo.setGoTo('5');
        return goTo;
    }
}

function sinamoRequestItem(fN) {
    var map;
    var funcName;

    this.init = function () {
        console.log('Iniciando sinamoRequestItem');
        this.map = new Object();
        this.funcName = fN;
    };

    this.add = function (k, v) {
        this.map[k] = v;
    };

    this.GET = function (k) {
        return this.map[k];
    };

    this.getFuncName = function () {
        return this.funcName;
    };

    this.init();
}

function _goto(script) {
    var sr = JSON.parse(script);
    API.LOG('JSLOG: ' + script);
    API.LOG('JSLOG: funcName =' + script.funcName);
    API.LOG('JSLOG: sr =' + sr.funcName);
//    var sr = eval(script);
//    console.log('JSLOG: ' + sr);
//    console.log('JSLOG: ' + sr.getFuncName());
    return eval(sr.getFuncName() + '(' + sr + ')');
}