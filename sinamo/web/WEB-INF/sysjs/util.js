function _act2xxxx(_requestStringValue, _transaId) {
    var request = JSON.parse(_requestStringValue);
    var returnUrl = "";

    //Valores:: asumiendo que todo es un string!
    var _id = getValuefromRequest(request, '_id')
    var name = getValuefromRequest(request, 'name')
    var lastname = getValuefromRequest(request, 'lastname')
    var mail = getValuefromRequest(request, 'mail')
    var nickname = getValuefromRequest(request, 'nickname')
    var password = getValuefromRequest(request, 'password')

    //respuesta a la ejecucion
    var rs;
    API.LOG('[JS] _id=' + _id);
    if (_id == '-1') {
        //Logica para guardar
        rs = API.SQL_UPDATE("_native", "insert into system.tbusuari(name,lastname,mail,nickname,password) values('" + name + "','" + lastname + "','" + mail + "','" + nickname + "','" + password + "');");
    } else {
        //logica para actualizar
        rs = API.SQL_UPDATE("_native", "update system.tbusuari set name='" + name + "'"
                + ",lastname='" + lastname + "'"
                + ",mail='" + mail + "'"
                + ",nickname='" + nickname + "'"
                + ",password='" + password + "'"
                + " where _id=" + _id + ";");
    }


    API.LOG('[JS] rpta update=' + rs);
    returnUrl = 'snm?ra=2';

    //others
    var act = getValuefromRequest(request, 'act');
    var R1;

    if (act == 'new') {
        var list = new java.util.ArrayList();
        var map = new java.util.HashMap();
        map.put('_id', '-1');
        map.put('name', '');
        map.put('lastname', '');
        map.put('mail', '');
        map.put('nickname', '');
        map.put('password', '');
        list.add(map);
        R1 = list;
    } else {
        R1 = API.SQL("_native", "select * from system.tbusuari where _id=" + getValuefromRequest(request, 'p1') + ";");
    }
    responsedatamap.put('R1', R1);

//    var R1 = API.SQL("_native", "select * from system.tbusuari where _id=" + getValuefromRequest(request, 'p1') + ";");
//    responsedatamap.put('R1', R1);


    return returnUrl;
}