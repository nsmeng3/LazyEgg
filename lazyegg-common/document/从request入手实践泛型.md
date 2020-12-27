# 从request入手实践泛型

## 一、场景

- 很多时候我们在调用api时，会考虑传参的问题。 以query的形式传还是body的形式传

  - query形式：
    ```http request
    POST http://localhost:8080/login?username=admin&password=admin
    ```
    这种形式传参，对应后台就要通过一下方式取值
    ```java
    public class LoginController {
        public Response login(HttpServletRequest request) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // 省略不知道多少行代码 ...
        }
    }
    ```

  - body形式

    ```http request
    POST http://localhost:8080/login?username=admin&password=admin
    Content-Type: application/json
    
    {
        "username": "admin",
        "password": "admin",
        "age": 18,
        "email": "admin@163.com"
    }
    ```
    后台对应需要类似如下方式取值
    
    ```java
    public class LoginController {
        public Response login(HttpServletRequest request) {
            JSONObject result = new JSONObject();
            ServletInputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                JSONObject jsonObject = JSONObject.parseObject(json);
                String username = jsonObject.get("username");
                String password = jsonObject.get("password");
                // 省略不知道多少行代码 ...
            }
        }
    
    }

    ```
  - 其他形式 - header里传参等不一一列举了。
    
## 二、实操
- 为了兼容如此多的传参方式，为了让我们的代码更OOP，那我们假设有下面的方法看看效果怎么样。
  - 更面向对象OOP，定义一个UserCO.java 接收参数
    ```java
    public class UserCO {
        private String username;
        private String password;
        private Integer age;
        private String email;
    }
        
    ```
    
  - 我们把request里面的各种形式的参数转换成一个User对象,就是这个requestParams方法。
    ```java
    public class LoginController {
        public Response login(HttpServletRequest request) {
            // request 转换对象
            UserCO user = requestParams(request);
            
            String username = user.getUsername();
            String email = user.getEmail();
            // 省略不知道多少行代码 ...
        }
    
    }
    ```
    有如下实现方式，我们来看一看
    
    1. 从request里面获取塞到UserCO对象中，直接上代码 - 第一版
    ```java
    public class LoginController {
        // 省略 login()
    
        public UserCO requestParams(HttpServletRequest request) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // 获取 email age ...
            UserCO user = new UserCO();
            user.setUsername(username);
            user.setPassword(password);
            // set email age ...
            return user;
        }
    
    }
    ```
    评价一下这个代码：没什么优点，重复劳动，写这样的代码，能有什么提高，就是一个字，累。。。
    
    2. 简化代码 - 第二版
    ```java
    public class LoginController {
        // 省略 login()
        
        public UserCO requestParams(HttpServletRequest request) {
            JSONObject result = new JSONObject();
            // 获取所有query参数的名字
            Enumeration<String> parameterNames = request.getParameterNames();
            // 遍历参数名，通过参数名取参数值,存到JSONObject对现在  fastjson 包中提供
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String[] parameterValues = request.getParameterValues(name);
                String value;
                if (parameterValues.length == 1) {
                    value = parameterValues[0];
                    result.put(name, value);
                } else {
                    result.put(name, parameterValues);
                }
            }
            // JSONObject 转换成想要的对象
            UserCO user = JSONObject.toJavaObject(result, UserCO.class);
            return user;
        }
    
    }
    ```
    这个方法虽然看起来代码也不少，但是如果参数有100个那么和之前手动 get set比起来还是要好太多，
    并且有一定的复用性了，我们只要替换最后JSONObject 转换方法中的 第二个参数就可以对不同对象进行赋值，
    然而这样还是避免了要copy一下，再改一下参数，继续优化 --- 快到终点啦！应用泛型
    
    3. 为了提高复用，减少copy，我们将方法提取到一个单独的类，我们给他命名为 BaseController.java
    我们来写一下。
    ```java
    public class BaseController {
        // 泛型方法 - 将泛型用在方法上
        public <T> UserCO requestParams(HttpServletRequest request, Class<T> clientObject) {
                JSONObject result = new JSONObject();
                // 获取所有query参数的名字
                Enumeration<String> parameterNames = request.getParameterNames();
                // 遍历参数名，通过参数名取参数值,存到JSONObject对现在  fastjson 包中提供
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    String[] parameterValues = request.getParameterValues(name);
                    String value;
                    if (parameterValues.length == 1) {
                        value = parameterValues[0];
                        result.put(name, value);
                    } else {
                        result.put(name, parameterValues);
                    }
                }
                // JSONObject 转换成想要的对象
                // 此处第二个参数 UserCO.class 换成了 方法形参的 clientObject
                // 返回值类型变成了 定义的泛型类型 上面 <T> 是对泛型的定义
                // 这个时候co 返回的类型就是我们传入参数的类型
                T co = JSONObject.toJavaObject(result, clientObject);
                return co;
            }
    }
    ```
    
    使用

    ```java
    // 继承 BaseController 
    public class LoginController extends BaseController {
        public Response login(HttpServletRequest request) {
            // request 转换对象  注意：第二个参数传的是 Class对象
            UserCO user = requestParams(request, UserCO.class);
            
            String username = user.getUsername();
            String email = user.getEmail();
            // 省略不知道多少行代码 ...
        }
    
    }
    ```
    
    这样无论有多少对象，我们也我自有的使用，且不需要类型强制转换。
    
    如果大家对泛型还没有什么了解，看我这可能会比较晕，给大家推荐一个文章，对泛型介绍的浅显易懂且全面
    > [https://segmentfault.com/a/1190000014120746](https://segmentfault.com/a/1190000014120746)
