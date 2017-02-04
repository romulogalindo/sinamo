function _act2xxxx(_requestStringValue, _transaId) {
    var request = JSON.parse(_requestStringValue);
    var returnUrl = "";

    var rs = API.SQL("_native", "update system.tbusuari set name='" + getValuefromRequest(request, 'name') + "'"
            + ",lastname='" + getValuefromRequest(request, 'lastname') + "'"
            + ",mail='" + getValuefromRequest(request, 'mail') + "'"
            + ",nickname='" + getValuefromRequest(request, 'nickname') + "'"
            + ",password='" + getValuefromRequest(request, 'password') + "'"
            + " where _id=" + getValuefromRequest(request, '_id') + ";");

    API.LOG('[JS] rpta update=' + rs);
    returnUrl = 'snm?ra=2';

    return returnUrl;
}