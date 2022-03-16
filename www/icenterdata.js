var exec = require("cordova/exec");

const dataServer = {
  info: function (arg0, success, error) {
    exec(success, error, "icenterdata", "info", [arg0]);
  },
  start: function (arg0, success, error) {
    exec(success, error, "icenterdata", "startServer", [arg0]);
  },
  stop: function (arg0, success, error) {
    exec(success, error, "icenterdata", "stopServer", [arg0]);
  },
};

exports.dataServer = dataServer;
