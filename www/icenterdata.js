var exec = require('cordova/exec');

const dataServer = {}
dataServer.info = function(arg0, success, error) {
    exec(success, error, 'icenterdata', 'info',[arg0])
}

exports.dataServer = dataServer