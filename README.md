引入Swagger的jar包，由于我的是Maven项目，所以在pom.xml中（spring4.1）
<!-- swagger-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.2.2</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.5.3</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.3</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.5.3</version>
</dependency>

新增Swagger配置代码

@Configuration
//@EnableWebMvc
@EnableSwagger2
//@ComponentScan(basePackages ={"com.greenline.hrs.open.service"})
public class SwaggerConfig {

 /**
 * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple 
 * swagger groups i.e. same code base multiple swagger resource listings. 
 */
 @Bean
 public Docket customDocket(){
 return new Docket(DocumentationType.SWAGGER_2);

 }
}

注入SwaggerConfig
<bean class="swagger.SwaggerConfigCXF"/>
<mvc:annotation-driven/>
<!-- Required so swagger-springmvc can access spring's RequestMappingHandlerMapping  -->
<context:component-scan base-package="com.greenline.hrs.open.service"/>

增加一个测试的ContactController
@Api(value = "contacts-api", description = "有关于用户的CURD操作", position = 5)  
@Controller  
@RequestMapping("/contacts")  
public class ContactController {  
  @Autowired ContactService contactService;  
  @ResponseBody  
  @RequestMapping(value="/1.0/contact/get.do/{id}",method=RequestMethod.GET)  
  public Contact get(@PathVariable Long id) {  
    return contactService.find(id);  
  }  
  @ApiOperation(value = "创建用户", notes = "返回用户实体对象", response = Contact.class, position = 2)  
  @RequestMapping(value = "/1.0/contact/add.do", method=RequestMethod.POST)  
  public void add(@RequestBody Contact contact,HttpServletResponse response) {  
    contactService.create(contact);  
    String location = ServletUriComponentsBuilder.fromCurrentRequest()  
      .pathSegment("{id}").buildAndExpand(contact.getId())  
      .toUriString();  
   
    response.setHeader("Location",location);          
  }  
   
  @RequestMapping(value="/1.0/contact/update.do/{id}",method=RequestMethod.POST)  
  @ApiResponses(value = {  
            @ApiResponse(code = 200, message = "更新成功", response = Contact.class),  
            @ApiResponse(code = 404, message = "找不到页面"),  
            @ApiResponse(code = 500, message = "内部报错")}  
  )  
  public void update(@ApiParam(name="id", value="编号", required=true)@PathVariable Integer id,@RequestBody Contact contact) {  
    contact.setId(id);;  
    contactService.update(contact);  
  }  
}  
这样其实就可以部署启动，然后直接访问http://localhost:8080/项目名/v2/api-docs获取所有通过@Controller、@RequestMapping等注解配置的WEB接口生成的JSON字符串


集成 Swagger-ui界面
Swagger 提供了一个静态项目 Swagger-ui，可以帮忙将获得的JSON格式的API优美的展示出来并且帮助测试这些接口。
集成 Swagger-ui 很简单，从https://github.com/swagger-api/swagger-ui 获取其所有的 dist 目录下东西放到需要集成的项目里，比如这里放入 src/main/webapp/WEB-INF/swagger-ui/ 目录下（拷贝dist文件中的内容，而不是dist文件夹）
在web-xml中配置
<servlet>
   <servlet-name>swagger</servlet-name>
   <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   <load-on-startup>2</load-on-startup>
</servlet>
<servlet-mapping>
   <servlet-name>swagger</servlet-name>
   <url-pattern>/</url-pattern>
</servlet-mapping>

然后在 swagger-servlet.xml（此文件为新建的文件，在WEB-INF目录下） 里添加这些静态文件映射:
<mvc:resources mapping="/swagger/**" location="/WEB-INF/swagger-ui/"/>

修改swagger-ui中的index.html文件
将原先的http://petstore.swagger.io/v2/swagger.json改成相应的：http://localhost:8080/项目名/v2/api-docs

启动项目，访问：
http://localhost:8080/项目名/swagger/index.html即可


参考：http://albertchen.top/2015/05/26/Spring-MVC-%E9%9B%86%E6%88%90-Swagger/
http://blog.csdn.net/zth1002/article/details/46927187


swagger官网：http://swagger.io/










