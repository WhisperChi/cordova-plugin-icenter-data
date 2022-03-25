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

