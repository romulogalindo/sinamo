/*
 * Nashron Api for SINAMO
 */
function sayHello(name) {
    print('Hola ' + name);
}

function getXmlUnit(serviceName) {
    return DBUTIL.getSinamoDriverDbUnit(serviceName);
}

function getValuefromCache(cacheName, key) {
    return CACHE.get(cacheName, key);
}
