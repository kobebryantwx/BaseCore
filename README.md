
![QuickCore](http://omkx1cfwq.bkt.clouddn.com/quick-core-logo.png)

# QuickCore

[![API](https://img.shields.io/badge/API-15%2B-blue.svg)](https://android-arsenal.com/api?level=15#l15)
[![alpha](https://img.shields.io/badge/alpha-1.0.0-blue.svg)]()

适用于MVP架构模式的**Android快速开发框架**。

框架使用`Dagger2`对**MVP三层解耦设计**。

为解决传统MVP架构开发中 `Model`、`View`、`Presenter`**三层过度依赖，不便于快速迭代开发。**

## 接入

下载或`clone`工程

```
> git clone https://git.oschina.net/militch/quickcore.git
```

导入`Module`到项目中，并且添加`Gradle`依赖

```
dependencies{
    compile project(':quickcore')
    annotationProcessor project(':quickcore-compiler')
    annotationProcessor 'com.google.dagger:dagger-compiler:2.11'
}
```

## LICENSE
    Copyright 2017 militch

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
