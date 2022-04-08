# cordova-plugin-icenter-data

## 介绍

iCenter离线数据加载cordova插件，Android平台版本。

## 说明

使用步骤

1. 新建cordova工程，配置Android平台
2. 安装iCenter离线数据发布插件[cordova-plugin-icenter-data](http://172.17.30.8/chixy/cordova-plugin-icenter-data.git)
3. 将需要使用的数据解压，导入/sdcard/下面的目录中
4. 调用相关Api（可以在cordova插件的js文件中看到）控制服务启停，以及设置相关参数
5. 配置cordova工程相关权限，以及开启`usesCleartextTraffic`权限
6. 编写mapbox相关代码加载`source`以及`layer`



## API说明

详情参考 www/icenterdata.js

1. 服务启停

   ```javascript
    cordova.plugins.icenterdata.dataServer.start("aaa", s=>{console.log(s)}, e=>{console.warn(e)} )
    cordova.plugins.icenterdata.dataServer.stop("aaa", s=>{console.log(s)}, e=>{console.warn(e)} )
   ```

   参数说明：

   Param1: 预留参数（可以为空字符串）

   Param2: 成功回调

   Param3: 失败回调

2. 设置数据路径

   ```javascript
    cordova.plugins.icenterdata.dataServer.setDataDir("data/test", s=>{console.log(s)}, e=>{console.warn(e)} )
   ```

   参数说明：

   Param1: 数据路径（为相对于sdcard目录的路径）

   Param2: 成功回调

   Param3: 失败回调

3. 其他接口，暂时用不到，不介绍。
