import com.whz.Controller.UserController;
import com.whz.HelperLoader;
import com.whz.helper.BeanHelper;
import com.whz.helper.ClassHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * IOC测试
 * IOC实现思路：1、获取配置路径下handwritten.framework.app.base_package=circular下的类文件
 *              放入Set<Class>中
 *              2、通过Set<Class>初始化bean，然后放在Map<Class,Bean>中
 *              3、IOC类中通过Map<Class,Bean>获取到类信息和Bean对象，并在需要的地方注入Bean信息作为字段值
 *              完成实例化
 * IOC测试思路：
 *              1、通过获取Class集合测试第一步
 *              2、通过获取Bean集合来测试第二步
 *              3、通过获取带有自动注入注解的Bean和注入的Bean来测试第三步
 *
 * 操作方法：打印获取的类信息
 * @auther whz
 * @create 2022-02-08 15:21
 */
public class IocTest {
    public static void main(String[] args) throws InterruptedException {
//        HelperLoader.init();
//        Set<Class<?>> beanClass = ClassHelper.getBeanClassSet();
//        System.out.println("\n");
//        System.out.println("beanClass:" + beanClass);
//        Set<Class<?>> classSet = ClassHelper.getClassSet();
//        for (Class<?> aClass : classSet) {
//            System.out.println(aClass);
//        }
//        System.out.println("\n");
//        UserController bean1 = BeanHelper.getBean(UserController.class);
//        System.out.println("bean1:" + bean1);
////        System.out.println(bean1.userService);

//        Integer i1 = 100; Integer i2 = 100; Integer i3 = 200; Integer i4 = 200; System.out.println(i1==i2); System.out.println(i3==i4);


    }
}
