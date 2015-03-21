package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation maps a POJO field to an XML/JSON element
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	public static final String MAP_VALUE_DEFAULT = "value";
	public static final String MAP_KEY_DEFAULT = "key";

	/**
	 * The name of the XML/JSON element
	 * 
	 * @return name
	 */
	public String name() default "";

	/**
	 * Used with collections. It's the name of elements contained in the
	 * collection or array, except byte array. Thus, name specified in annotation {@link Bind} will be used
	 * for container.
	 * 
	 * @return name of elements of collection. default is ""
	 */
	public String elementName() default "";
	
	/**
	 * name of element rapresents key of a map. <b>Used only by map type.</b>
	 * 
	 * @return
	 */
	public String mapKeyName() default MAP_KEY_DEFAULT;

	/**
	 * name of element rapresents value of a map. <b>Used only by map type.</b>
	 * 
	 * @return
	 */
	public String mapValueName() default MAP_VALUE_DEFAULT;
	
}
