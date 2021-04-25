
/**
 * Beans that support customized output of JSON text shall implement this interface.  
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
package cenfotec.datos.jsonparser;
public interface JSONAware {
	/**
	 * @return JSON text
	 */
	String toJSONString();
}
