var exec = require("cordova/exec");

const dataServer = {
  info: function (arg0, success, error) {
    exec(success, error, "icenterdata", "info", [arg0]);
  },
  setParams: function(arg0, success, error) {
    exec(success, error, "icenterdata", "setParams", [arg0]);
  },
  start: function (arg0, success, error) {
    exec(success, error, "icenterdata", "startServer", [arg0]);
  },
  stop: function (arg0, success, error) {
    exec(success, error, "icenterdata", "stopServer", [arg0]);
  },
  setScale: function (arg0, success, error) {
    exec(success, error, "icenterdata", "setScale", [arg0]);
  },
  setDataDir: function (arg0, success, error) {
    exec(success, error, "icenterdata", "setDataDir", [arg0]);
  }
};

exports.dataServer = dataServer;
