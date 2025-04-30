# 实验室物资管理系统

> [!NOTE]
>
> # 需求文档
>
> - 使用数据库来操作！我自己也要用！
>
> - 最好再整个安卓APP出来！ WebUI也不是不行
>
> - 拿来做实验室\个人工作间物资统计系统，以后可以真的拿来给自己用。
>
> - 它应该能让我很方便地进行物资消耗计数、入库等，有快捷操作
>
> - 要支持待办清单功能
>
> - 一些操作要加权限验证
>
> - 其他人也可以登进这个系统进行操作，所有操作都有日志进行记录
> - 回滚功能

> [!WARNING]
>
> **来自老师的作业要求**
>
> - 使用至少一个接口来定义多个类必须实现的一组方法。
> - 使用至少一个抽象类为相关类提供公共功能。
> - 通过创建类层次结构（例如，基类和至少两个子类）来演示继承。
> - 使用至少6个互联的类。
> - 使用方法重载。
> - 使用方法重写。
> - 使用多态性使不同类的对象可以被视为公共超类的对象。
> - 使用异常处理来处理错误消息。必须至少有两个用户定义的异常类。

## 开发计划

> [!IMPORTANT]
>
> ==LMS Project==
>
> src
>
> ├─Main.java
> │
> ├─database
>
> │   ├─db
>
> │   │  `DataBase.java` Connect to DB
>
> │   │  `DataBaseUtils.java` CRUD logic
>
> │   │
>
> │   └─error
>
> │      `DBConnectError.java` Connect to DB
>
> │      `IOError.java` CRUD logic
>
> │
> ├─laboratory
>
> │   ├─ lab
>
> │   │   │  `LabAccessCard.java` Permission logic
>
> │   │   │  `LabNotebook.java` ToDo Subsystem，Enable to check material as Project 
>
> │   │   │  `LabUtils.java` tools class
>
> │   │   │  `LabWorkers.java` Staff list in lab
>
> │   │   │  `LabPrinter.java` output\input csv file to manage object in lab
>
> │   │   │
>
> │   │   └─shelf
>
> │   │   │ `StorageShelf.java` Management object on Lab Shelf
>
> │   │   │ `material.java` a material, all things will extend from here.
>
> │   │   │
>
> │   │   └─workers
>
> │   │    `LabWorkers.java` lab worker basic class
>
> │   │    `Admin.java` lab admin
>
> │   │    `Guest.java` lab guest
>
> │   │    `User.java` lab normal users
>
> │   │
>
> │   └─error
>
> │      `AccessError.java` CRUD logic
> │
> ├─local
>
> │  └─ui
> │      │  `LocalApiClient.java` front end connect to after end
>
> │      │  `Start.java` Start UI(without after end)
>
> │      │
> │      ├─login
>
> │      │      `LoginLogic.java` loginUI logic
>
> │      │      `LoginUI.java` UI
> │      │
> │      └─mainwindow
>
> │              `MainWindowLogic.java` mainwindow logic
>
> │              `MainWindowUI.java` UI
> │
> ├─web
>
> │   ├─server
>
> │   │      `ApiServer.java` web server
> │   │
> │   └─webui
>
> │           `WebApiClient.java` Connect to server
>
> │           `WebDashboard.java` UI
>
> │
>
> ├─test
>
> │
>
> │
>
> └─log
>
> ​    `Loggable(interface)`

> [!TIP]
>
> **我的设计理念**
>
> - 实验室物料管理系统
>   - 根据本人半年多的时间在实验室干活的经验进行区块划分
>   - 对每件物品作为一个单独的对象进行管理
>   - 采取sql-lite作为数据存储后端，提高存储效率、跨平台性能、安全性
>   - 进行明确的用户权限划分，提高数据安全性与私密性
>
> - 运用前后端分离理念
>   - 前端与后端独立设计，通过通用的接口进行通信
>   - 前端进行进一步的功能细化，确保显示与操作逻辑分离，便于设计
>   - 后端本身可以作为无头客户端启动充当web服务器为可能存在的webui进行在线服务
>   - 提高项目可维护性（如果需要的话）
> - 继承理念
>   - 架子上的所有物品都来自同一个根物品，都是其子类
>   - 实验室里的所有人都是实验室工作人员类的子类
>
> - 把储物架作为一个实体整体进行考虑，将其出入库等行为封装成通用接口
> - 既允许以物品为单位进行管理，又支持以BOM表（项目）为单位进行管理

---

**This is my note**

### `local`包

本地前端

### `web`包

webui前端

#### UI包

### database包

`Database`类

- 提供对sqlite数据库的连接服务

- 提供数据库的查增改删基本服务

`DatabaseUtils`类

- 工具类

### server包

`ApiServer`类

- 提供前端的数据接口，为之后开发的，面向用户的webui预留

### laboratory包

`StorageShelf`类

- 一个赛博储物架，也许可以瞅瞅上面都有些啥？

- 对db类的上层封装
- 提供明确的用户服务

`LabUtils`类

- 工具类
- 为StorageShelf提供便捷工具

`LabWorkers`类

- 实验室里干活的人
- 人会放东西到架子上，还会从架子上拿东西走，还会瞅瞅架子上都有啥

`LabNotebook`类

- 实验室的人可能会从网上买东西，他们得写计划书。

- 计划做了得执行

`LabAccessCard`类

- 人进实验室需要刷卡

- 提供鉴权服务

### log包

`Log`类

- 负责记录系统操作日志

- 提供日志查询和管理功能
