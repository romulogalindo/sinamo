/* 
 * Sinamo Engine JS
 */
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

function sinamoEngine() {
    this.build = function (e, funcName) {
        var sr = new sinamoRequestItem(funcName);
        sr.add('r1', $('#' + e.getAttribute('id')).prop('id'));
        console.log('sr->' + sr);
        return sr;
    };
}

var sJS = new sinamoEngine();
//sJS.build(_e);

function execute(_jsonObject) {
    var _j = JSON.stringify(_jsonObject);
    console.log("rqy::" + _j);
    $.post("snm", {jsonOBject: _j}, function (data) {
        console.log(data);
    });
}

