/* 
 * Sinamo Engine JS
 */
function sinamoRequestItem(xN, fN) {
    var map;
    var funcName;
    var xactName;

    this.init = function () {
        console.log('Iniciando sinamoRequestItem');
        this.map = new Object();
        this.xactName = xN;
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

    this.getXactName = function () {
        return this.xactName;
    };

    this.init();
}

function sinamoEngine() {
    this.build = function (e, xactName, funcName) {
        var sr = new sinamoRequestItem(xactName, funcName);
//        sr.add('r1', $('#' + e.getAttribute('id')).prop('id'));
        sr.add('r1', $('#' + e.getAttribute('id') + '_v').val());
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
        console.log('rpta=' + data);
        var _R = JSON.parse(data);
        var modulo = _R.goTo;
        var url = "http://127.0.0.1:8080/sinamo/snm?ra=" + modulo + "&r1=" + _R.values.r1;
//        for(var i = 0 ; i< _R.values.length;i++){
//            
//        }
        window.location = url;
    });
}

/*
 * 
 * @type sinamo
 */
function Sinamo() {
    this.goto = function (url) {
        window.location.href = url;
    };

    this.call = function (funcName, transaId) {
        //conseguir todos los objetos en base al transaId,
        var jsonValues = '{"params":[';
        $("input[id^='" + transaId + "_']").each(function (index) {
            var key = $(this).attr('id').replace(transaId + "_", "");
            var val = $(this).val();
            jsonValues = jsonValues + '{"' + key + '":"' + val + '"},';
        });
        jsonValues = jsonValues.substring(0, jsonValues.length - 1);
        jsonValues = jsonValues + "]}";
        console.log('json creado:' + jsonValues);

        $.post("snm?func=" + funcName + "&transaId=" + transaId, {jsonOBject: jsonValues}, function (data) {
            console.log('rpta=' + data);
            snm.goto(data);
        });

//        alert('estamos ejecutando!!');
    };
}
var snm = new Sinamo();
