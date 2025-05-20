# How To Compile & Run This Project

1. Compile the project using the following command:

```bash
.\packprogram.bat
```

> 
>

2. Ensure the `lib` path is at the same dictionary with LMS.jar
3. Make sure java version is `1.8.0`or`23.0.2`(**I don’t know other version Java can/cannot Compile**)

   > The default script will be compiled in java8, and there will be some warning scrolling, which will not affect the smoothness of the compilation.
4. Run the project using the following command:

```bash
java -cp "LMS.jar;lib/*" start.Main
```

5. If you use Mysql, Please create a New sql named `LMS_sql`, then program will auto generate Table.
