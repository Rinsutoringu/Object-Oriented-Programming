# Development Notes

### Development Source:

This project is developed using the Java Swing component library.

**Dependencies**

MySQL

### Encapsulation Details

All utility classes in this project that use a single instance follow the Singleton pattern.

Use the `getInstance` method to obtain an instance of the utility class.

Utility classes must implement the `StandardUTIL` interface from the `standard` package.

### Utility Classes

To obtain a handle for a specific utility, simply use the `<TargetUtilityClassName>.getInstance()` method.

This method returns the handle of the utility class.

**Database-related Utilities**

- `DataBaseUtils`: Contains various common database operations such as CRUD.

**Swing-related Utilities**

- `ImageUtilse`: Contains image processing logic, encapsulating the implementation from the system file system to UI display.

- `UIUtils`: Provides further encapsulation for GBC.

- **Standard Package**

  | Class Name          | Source                                   | Purpose                           |
  | ------------------- | ---------------------------------------- | --------------------------------- |
  | `StandardUI`        | Parent class of all `xxxUI`, extends `JPanel` | Provides support for layout and key registration |
  | `standardUILogical` | Parent class of all `xxxLogical`, extends `StandardUI` | Provides support for dynamic layout and key event registration |
  | `standardUTIL`      | Object                                   | Standardized implementation of utility classes |

**Error Handling Utilities**

- `CatchException`: Error registry, where all possible errors are registered and relevant error handlers are invoked as needed.
- `errorHandler`: A general error handler that reports errors via pop-ups and logs.

### Adding a New Panel

Essentially, every display frame in the project is an independent page.

Therefore, to add an additional display frame, simply create a new page package.

> [!NOTE]
>
> Page structure:
>
> - `xxxUI.java`: Extends `StandardUI`.
>
> - `xxxLogic.java`: Extends `StandardUILogical`.
>
> - Subpage directory (if the panel has subpanels):
>   - Subpages are placed here to improve the logical organization of the interface.
>   - Subpages follow the same page structure, recursively.

**Example**

Refer to the `local.ui.example` package.

**Technical Details:**

- `UI` is responsible for registering UI components and constructing the interface. It provides a `hashMap` containing registered data such as `buttons`, `panels`, `images`, and `checkBoxs`.

- All required utility classes are already defined in the parent class, and corresponding methods can be used directly.
- `UI` contains a collection of PLs.

- `Logic` is responsible for adding events to the components registered in the `UI` class (retrieved from the aforementioned `hashMap`).

> [!WARNING]
>
> Please note that the relationship between `logic` and `UI` is not `extend` but `have`!
>
> This means that when loading the visual interface, the handle of `xxxUI` (not the handle of `xxxlogic`) must be used!
>
> I have provided a `getThis` method. Whether it is `logic` or `UI`, they should point to the handle of `UI`, enabling recursive implementation of the UI.

### How to Add Module Debugging

After completing the construction of `ui` and `logic`, you need to add a test method.

Add a line of case in the `src\test\factory\UIModuleFactory.java` path:

```java
case "<LaunchCommand>":
  return new <ClassToTest>();