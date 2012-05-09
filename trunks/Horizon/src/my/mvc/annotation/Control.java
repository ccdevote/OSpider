package my.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来控制Action执行的后续操作<br/>
 * 用法如下：<br/>
 * Type stype=[forward(1)|redirect(2)|chain(3)]用来设定返回结果为success的操作类型   即：<br/>
 * ·转发 =forward  <br/>
 * ·重定向=redirect <br/>
 * ·执行另一个aciton=chain <br/>
 * ·默认为forward<br/>
 * Type itype=[forward|redirect|chain]用来设定返回结果为input的操作类型 即：<br/>
 * ·转发 =forward<br/>  
 * ·重定向=redirect <br/>
 * ·执行另一个aciton=chain<br/>
 * ·默认为redirect<br/>
 * String success=[String类型的uri]  用来设置返回结果为success的  uri 默认为index.html<br/>
 * String input =[String类型的uri]  用来设置返回结果为success的  uri 默认为  error.html
 * @author MzyAiLqq
 * @version 0.0.01
 * @date 2012-01-28 22:40:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Control {
	Type stype() default @Type(1);
	String success() default "index.html";
	Type itype() default @Type(2);
	String input() default "error.html";
}
