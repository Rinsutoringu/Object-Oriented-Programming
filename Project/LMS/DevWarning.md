# 开发须知

### 开发来源：

本项目使用Java Swing组件库开发

**依赖相关**

mysql

### 封装详情

本项目所有使用单一实例的工具类均采用单实例模式

使用`getInstance`方法来获取工具类实例

工具类实现`standard`包中的`StandardUTIL`接口即可

### 工具类

要想获取相关工具句柄，只需要使用`<目标工具类名称>.getInstance()`方法即可

该方法返回工具类的句柄

**数据库操作相关工具**

- `DataBaseUtils` 内含数据库的各种常用操作 增查改删什么的

**Swing操作相关工具**

- `ImageUtilse`内含图像处理逻辑，封装了从系统文件系统到展示在UI上的实现

- `UIUtils`提供了对GBC的进一步封装

- **standard包**

  | 类名                | 来源                                       | 用途                               |
  | ------------------- | ------------------------------------------ | ---------------------------------- |
  | `StandardUI`        | 所有`xxxUI`的父类，继承自`Jpanel`          | 提供了布局、注册按键相关的支持     |
  | `standardUILogical` | 所有`xxxLogical`的父类，继承自`StandardUI` | 提供了动态布局、按键事件注册的支持 |
  | `standardUTIL`      | Object                                     | 工具类的标准化实现                 |

**错误处理相关工具**

- `CatchException` 错误注册表，在这里注册所有可能发生的错误并视情况调用相关的错误处理器
- `errorHandler`通用的错误处理器，以弹窗和log的方式报错

### 添加新的面板

本质上项目的每一个显示框都是一组独立的page

所以，如果想要添加额外的显示框，只需要创建一个新的page包即可。

> [!NOTE]
>
> page结构：
>
> - `xxxUI.java`继承`StandardUI`
>
> - `xxxLogic.java`继承`StandardUILogical`
>
> - subpage目录（如果该面板有子面板的话） 
>   - 把subpage放在这里只是为了提高界面组织的逻辑性
>   - subpage里也是一样的page结构，这是递归的

**Example**

参照`local.ui.example`包即可

**技术细节：**

- `UI`负责注册UI组件、画面构架, 提供包含`buttons, panels, images, checkBoxs`等注册数据的`hashMap`

- 其父类中已经定义好所有需要的工具类，直接使用对应方法就可以

- Logic负责给UI类中注册的组件添加事件（从上述`hashmap`中获取组件）

### 如何添加模块调试

完成`ui`和`logic`的构建后，需要添加测试方法

`src\test\factory\UIModuleFactory.java`路径 添加一行case

```java
case "<启动指令>":
  return new <要测试的类>();
```

然后去`.vscode`文件夹的`launch.json` 文件，`inputs -> options`添加一行预设指令

```json
"<启动指令>",
```

按下F5运行，选择对应的预设指令即可