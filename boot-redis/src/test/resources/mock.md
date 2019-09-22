####    RequestBuilder/MockMvcRequestBuilders：

    MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的MockHttpServletRequestBuilder；如get(/user/{id}, 1L)；
    MockHttpServletRequestBuilder post(String urlTemplate, Object... urlVariables)：同get类似，但是是POST方法；
    MockHttpServletRequestBuilder put(String urlTemplate, Object... urlVariables)：同get类似，但是是PUT方法；
    MockHttpServletRequestBuilder delete(String urlTemplate, Object... urlVariables) ：同get类似，但是是DELETE方法；
    MockHttpServletRequestBuilder options(String urlTemplate, Object... urlVariables)：同get类似，但是是OPTIONS方法；
    MockHttpServletRequestBuilder request(HttpMethod httpMethod, String urlTemplate, Object... urlVariables)： 提供自己的Http请求方法及uri模板和uri变量，如上API都是委托给这个API；
    MockMultipartHttpServletRequestBuilder fileUpload(String urlTemplate, Object... urlVariables)：提供文件上传方式的请求，得到MockMultipartHttpServletRequestBuilder；
    RequestBuilder asyncDispatch(final MvcResult mvcResult)：创建一个从启动异步处理的请求的MvcResult进行异步分派的RequestBuilder；

MockMvcRequestBuilders通过方法得到两类Builder，一个是MockHttpServletRequestBuilder ，一个是MockMultipartHttpServletRequestBuilder （上传文件）

####    MockHttpServletRequestBuilder：
MockHttpServletRequestBuilder 主要有一下API：

    MockHttpServletRequestBuilder header(String name, Object... values)/MockHttpServletRequestBuilder headers(HttpHeaders httpHeaders)：添加头信息；
    MockHttpServletRequestBuilder contentType(MediaType mediaType)：指定请求的contentType头信息；
    MockHttpServletRequestBuilder accept(MediaType... mediaTypes)/MockHttpServletRequestBuilder accept(String... mediaTypes)：指定请求的Accept头信息；
    MockHttpServletRequestBuilder content(byte[] content)/MockHttpServletRequestBuilder content(String content)：指定请求Body体内容；
    MockHttpServletRequestBuilder param(String name,String... values)：请求传入参数
    MockHttpServletRequestBuilder cookie(Cookie... cookies)：指定请求的Cookie；
    MockHttpServletRequestBuilder locale(Locale locale)：指定请求的Locale；
    MockHttpServletRequestBuilder characterEncoding(String encoding)：指定请求字符编码；
    MockHttpServletRequestBuilder requestAttr(String name, Object value) ：设置请求属性数据；
    MockHttpServletRequestBuilder sessionAttr(String name, Object value)/MockHttpServletRequestBuilder sessionAttrs(Map<string, object=""> sessionAttributes)：设置请求session属性数据；
    MockHttpServletRequestBuilder flashAttr(String name, Object value)/MockHttpServletRequestBuilder flashAttrs(Map<string, object=""> flashAttributes)：指定请求的flash信息，比如重定向后的属性信息；
    MockHttpServletRequestBuilder session(MockHttpSession session) ：指定请求的Session；
    MockHttpServletRequestBuilder principal(Principal principal) ：指定请求的Principal；
    MockHttpServletRequestBuilder contextPath(String contextPath) ：指定请求的上下文路径，必须以“/”开头，且不能以“/”结尾；
    MockHttpServletRequestBuilder pathInfo(String pathInfo) ：请求的路径信息，必须以“/”开头；
    MockHttpServletRequestBuilder secure(boolean secure)：请求是否使用安全通道；
    MockHttpServletRequestBuilder with(RequestPostProcessor postProcessor)：请求的后处理器，用于自定义一些请求处理的扩展点；
    
####    MockMultipartHttpServletRequestBuilder：    
    MockMultipartHttpServletRequestBuilder继承自MockHttpServletRequestBuilder，又提供了如下API：
    
    MockMultipartHttpServletRequestBuilder file(String name, byte[] content)/MockMultipartHttpServletRequestBuilder file(MockMultipartFile file)：指定要上传的文件；
    
####    ResultActions：
    调用MockMvc.perform(RequestBuilder requestBuilder)后将得到ResultActions，通过ResultActions完成如下三件事：
    
    ResultActions andExpect(ResultMatcher matcher) ：添加验证断言来判断执行请求后的结果是否是预期的；
    ResultActions andDo(ResultHandler handler) ：添加结果处理器，用于对验证成功后执行的动作，如输出下请求/结果信息用于调试；
    MvcResult andReturn() ：返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理；
    
####    ResultMatcher/MockMvcResultMatchers：

ResultMatcher用来匹配执行完请求后的结果验证，其就一个match(MvcResult result)断言方法，如果匹配失败将抛出相应的异常；此类案例中并为使用，请自行查看。具体提供以下API：

    HandlerResultMatchers handler()：请求的Handler验证器，比如验证处理器类型/方法名；此处的Handler其实就是处理请求的控制器；
    RequestResultMatchers request()：得到RequestResultMatchers验证器；
    ModelResultMatchers model()：得到模型验证器；
    ViewResultMatchers view()：得到视图验证器；
    FlashAttributeResultMatchers flash()：得到Flash属性验证；
    StatusResultMatchers status()：得到响应状态验证器；
    HeaderResultMatchers header()：得到响应Header验证器；
    CookieResultMatchers cookie()：得到响应Cookie验证器；
    ContentResultMatchers content()：得到响应内容验证器；
    JsonPathResultMatchers jsonPath(String expression, Object ... args)/ResultMatcher jsonPath(String expression, Matcher matcher)：得到Json表达式验证器；
    XpathResultMatchers xpath(String expression, Object... args)/XpathResultMatchers xpath(String expression, Map<string, string=""> namespaces, Object... args)：得到Xpath表达式验证器；
    ResultMatcher forwardedUrl(final String expectedUrl)：验证处理完请求后转发的url（绝对匹配）；
    ResultMatcher forwardedUrlPattern(final String urlPattern)：验证处理完请求后转发的url（Ant风格模式匹配，@since spring4）；
    ResultMatcher redirectedUrl(final String expectedUrl)：验证处理完请求后重定向的url（绝对匹配）；
    ResultMatcher redirectedUrlPattern(final String expectedUrl)：验证处理完请求后重定向的url（Ant风格模式匹配，@since spring4）；
    
    
    
    
    
    
    